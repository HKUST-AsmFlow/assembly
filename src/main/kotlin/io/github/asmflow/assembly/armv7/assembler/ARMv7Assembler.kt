package io.github.asmflow.assembly.armv7.assembler

import com.intellij.execution.ui.ConsoleView
import com.intellij.psi.PsiFile
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.assembler.psuedo.ARMv7PseudoEncoder
import io.github.asmflow.assembly.armv7.assembler.psuedo.PsuedoEncoderFactory
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.database.InstructionFormat
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.armv7.psi.ARMv7LabelWithColon
import io.github.asmflow.assembly.armv7.util.functional.Err
import io.github.asmflow.assembly.armv7.util.functional.Ok
import io.github.asmflow.assembly.armv7.util.functional.resultOfException

/**
 * Class representing a ARMv7 assembler which transforms assembly into encoded instructions (integers).
 *
 * After resolving all labels to a fixed address, the class delegates the task of encoding an instruction
 * to different encoders, depending on the type of instruction used.
 */
class ARMv7Assembler(console: ConsoleView) : Assembler(console) {
    /**
     * Gets the encoder for a single ARMv7 instruction, throwing an exception if it does not exist.
     */
    fun getEncoderFromInstruction(
        instruction: ARMv7Instruction,
        symbols: HashMap<String, Int>
    ): ARMv7InstructionEncoder {
        // TODO: Special-case LDR pseudoinstructions vs regular LDR instructions
        return when (ARMv7InstructionDatabase.get(instruction.baseMnemonic).unwrap().format) {
            InstructionFormat.DATA_PROCESSING -> ARMv7DataProcessingEncoder
            InstructionFormat.BRANCH -> ARMv7BranchEncoder(symbols)
            InstructionFormat.BRANCH_EXCHANGE -> ARMv7BranchExchangeEncoder
            InstructionFormat.MEMORY_ACCESS -> ARMv7MemoryAccessEncoder(symbols)
            InstructionFormat.SUPERVISOR_CALL -> ARMv7SupervisorCallEncoder
            InstructionFormat.PSUEDO -> PsuedoEncoderFactory.getEncoder(instruction.baseMnemonic, symbols)
            else -> throw AssemblySyntaxException("Mnemonic for ${instruction.text} is invalid in the database.")
        }
    }

    /**
     * Validates the existence and correctness of an instruction,
     * and converts it into (a sequence of) integers.
     */
    fun encodeInstruction(
        instruction: ARMv7Instruction,
        symbols: HashMap<String, Int>,
        addrCounter: Int
    ): AssemblerResult<List<Int>, AssemblerError> {
        // TODO: make sure the instruction actually takes operands before returning an error
        val operands =
            instruction.operands ?: return Err(
                AssemblerError(
                    "Instruction ${instruction.text} has no operands.",
                    instruction
                )
            )

        val databaseInstruction = ARMv7InstructionDatabase.get(instruction.baseMnemonic)
        if (databaseInstruction.isNone()) {
            return Err(AssemblerError("Instruction ${instruction.text} is not supported by AsmFlow.", instruction))
        }

        val instructionEntry = databaseInstruction.unwrap()

        if (instruction.setsFlags && !instructionEntry.supportsFlags) {
            return Err(
                AssemblerError(
                    "Instruction ${instruction.text} does not support the S suffix.",
                    instruction
                )
            )
        }

        if (instruction.conditionCode != ARMv7InstructionConditionCode.AL && !instructionEntry.supportsConditionCodes) {
            return Err(
                AssemblerError(
                    "Instruction ${instruction.text} does not support condition codes.",
                    instruction
                )
            )
        }

        val hasWriteback = operands.operandList.any {
            (it.operand as? ARMv7InstructionOperand.Register)?.writeBack == true
        }
        if (hasWriteback && !ARMv7MemoryAccessEncoder.isBlockTransfer(instruction.baseMnemonic)) {
            return Err(
                AssemblerError(
                    "Instruction ${instruction.text} cannot use `!` on a register; it is only valid on the base register of LDM/STM.",
                    instruction
                )
            )
        }

        return try {
            resultOfException {
                getEncoderFromInstruction(instruction, symbols).encode(
                    instruction,
                    operands.operandList.requireNoNulls(),
                    addrCounter
                )
            }
                .mapErr { AssemblerError(it.message.orEmpty(), instruction) }
        } catch (_: IllegalArgumentException) {
            throw Exception("This should not happen")
        }
    }

    fun collectSymbolsAndSizes(file: PsiFile, ctx: AssemblerContext, errors: MutableList<AssemblerError>) {
        file.children.forEach {
            when (it) {
                is ARMv7LabelWithColon -> defineLabel(it, ctx, errors)
                is ARMv7Instruction -> advance(it, ctx, errors)
                is ARMv7Directive -> advance(it, ctx, errors)
            }
        }
    }

    fun emitInstructions(file: PsiFile, ctx: AssemblerContext, errors: MutableList<AssemblerError>) {}

    private fun defineLabel(label: ARMv7LabelWithColon, ctx: AssemblerContext, errors: MutableList<AssemblerError>) {
        val name = label.label.text
        if (name in ctx.symbols) {
            errors.add(AssemblerError("duplicate label $name", label))
            return
        }

        ctx.symbols[name] = AssemblerContext.Symbol(ctx.section, ctx.currentOffset())
    }

    fun advance(instruction: ARMv7Instruction, ctx: AssemblerContext, errors: MutableList<AssemblerError>) {
        if (ctx.section != AssemblerContext.Section.Text) {
            errors.add(AssemblerError("instructions can only be in the text section", instruction))
            return
        }

        // todo: we do not need the symbols to compute word-size of instructions, but we will pass the symbols from
        //       context eventually anyway, so the function needs to be updated
        val encoder = getEncoderFromInstruction(instruction, HashMap())
        val words = if (encoder is ARMv7PseudoEncoder) encoder.expandsTo else 1

        ctx.advanceText(words)
    }

    fun advance(directive: ARMv7Directive, ctx: AssemblerContext, errors: MutableList<AssemblerError>) {
        when (val name = directive.directiveName.text.lowercase()) {
            "data" -> ctx.section = AssemblerContext.Section.Data
            "text" -> ctx.section = AssemblerContext.Section.Text
            else -> errors.add(AssemblerError("unknown directive $name", directive))
        }
    }

    /**
     * Assembles a file.
     *
     * Currently, the implementation only supports a single file; multi-file support may be implemented in the future.
     */
    override fun assemble(files: List<PsiFile>): AssemblerResult<AssembledProgram, List<AssemblerError>> {
        val file = files[0] // For now support one file
        val context = AssemblerContext()
        val errors = mutableListOf<AssemblerError>()

        collectSymbolsAndSizes(file, context, errors)
        if (errors.isNotEmpty())
            return Err(errors)

        emitInstructions(file, context, errors)
        if (errors.isNotEmpty())
            return Err(errors)

        return Ok(AssembledProgram(
            context.text,
            context.data.toByteArray(),
            context.symbols,
        ))

        val symbols = HashMap<String, Int>()
        // ROUND 1: Resolve labels
        // Use PsuedoEncoder.expandsTo to get the number of real instructions
        // for each psuedo,
        // otherwise increment by 1
        // Build a map of labels corresponding to the Address/4
        var addrCounter: Int = 0 // Assuming text section starts at 0x00000000
        for (child in file.children) {
            if (child is ARMv7Instruction) {
                val encoder = getEncoderFromInstruction(child, symbols)
                addrCounter += if (encoder is ARMv7PseudoEncoder) {
                    encoder.expandsTo
                } else {
                    1
                }
            }
            if (child is ARMv7LabelWithColon) {
                symbols[child.label.text] = addrCounter
            }
        }

        // ROUND 2: Convert all the instructions into bytecode
        addrCounter = 0 // Assuming text section starts at 0x00000000
        val instructions = mutableListOf<Int>()
        for (child in file.children) {
            if (child is ARMv7Instruction) {
                val encoder = getEncoderFromInstruction(child, symbols)
                val result = encodeInstruction(child, symbols, addrCounter)
                if (result.isErr()) {
                    errors.add(result.unwrapErr())
                    debug("Error: ${result.unwrapErr().message} \n")
                } else {
                    val encoded = result.unwrap()
                    encoded.forEach { word ->
                        debug(
                            "Encoded ${child.text} -> 0x${word.toUInt().toString(16).uppercase().padStart(8, '0')}\n"
                        )
                    }
                    instructions.addAll(encoded)
                }

                addrCounter += if (encoder is ARMv7PseudoEncoder) {
                    encoder.expandsTo
                } else {
                    1
                }
            }
        }

        if (errors.isNotEmpty()) {
            return Err(errors)
        }
    }
}
