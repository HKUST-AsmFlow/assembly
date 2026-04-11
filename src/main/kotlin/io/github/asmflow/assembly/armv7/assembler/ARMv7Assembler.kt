package io.github.asmflow.assembly.armv7.assembler

import com.intellij.execution.ui.ConsoleView
import com.intellij.psi.PsiFile
import io.github.asmflow.assembly.armv7.assembler.psuedo.ARMv7PsuedoEncoder
import io.github.asmflow.assembly.armv7.assembler.psuedo.PsuedoEncoderFactory
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.database.InstructionFormat
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.armv7.psi.ARMv7LabelWithColon
import io.github.asmflow.assembly.assembler.*
import io.github.asmflow.assembly.util.functional.Err
import io.github.asmflow.assembly.util.functional.resultOfException

class ARMv7Assembler(console: ConsoleView) : Assembler(console) {
    fun getEncoderFromInstruction(instruction: ARMv7Instruction, symbols: HashMap<String, Int>): ARMv7InstructionEncoder {
        return when(ARMv7InstructionDatabase.get(instruction.mnemonic.text).unwrap().format){
            InstructionFormat.DATA_PROCESSING -> ARMv7DataProcessingEncoder
            InstructionFormat.BRANCH -> ARMv7BranchEncoder(symbols)
            InstructionFormat.PSUEDO -> PsuedoEncoderFactory.getEncoder(instruction.mnemonic.text)
            else -> throw AssemblySyntaxException("Mnemonic for ${instruction.text} is invalid in the database.")
        }
    }
    fun encodeInstruction(instruction: ARMv7Instruction, symbols: HashMap<String, Int>, addrCounter: Int): AssemblerResult<List<Int>, AssemblerError> {
        // TODO: make sure the instruction actually takes operands before returning an error
        val operands =
            instruction.operands ?: return Err(
                AssemblerError(
                    "Instruction ${instruction.text} has no operands.",
                    instruction
                )
            )

        if (ARMv7InstructionDatabase.get(instruction.mnemonic.text).isNone())
            return Err(AssemblerError("Instruction ${instruction.text} is not supported by AsmFlow.", instruction))

        return try {
            resultOfException { getEncoderFromInstruction(instruction, symbols).encode(
                instruction,
                operands.operandList.requireNoNulls(),
                addrCounter
            )}
                .mapErr { AssemblerError(it.message.orEmpty(), instruction) }
        } catch (_: IllegalArgumentException) {
            throw Exception("This should not happen")
        }
    }

    override fun assemble(files: List<PsiFile>): AssemblerResult<List<AssembledInstruction>, List<AssemblerError>> {
        val file = files[0] // For now support one file
        val errors = mutableListOf<AssemblerError>()
        val symbols = HashMap<String, Int>()
        // ROUND 1: Resolve labels
        // Use PsuedoEncoder.expandsTo to get the number of real instructions
        // for each psuedo,
        // otherwise increment by 1
        // Build a map of labels corresponding to the Address/4
        var addrCounter: Int = 0 // Assuming text section starts at 0x00000000
        for (child in file.children){
            if (child is ARMv7Instruction) {
                val encoder = getEncoderFromInstruction(child, symbols)
                addrCounter += if (encoder is ARMv7PsuedoEncoder){
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
        for (child in file.children) {
            if (child is ARMv7Instruction) {
                val encoder = getEncoderFromInstruction(child, symbols)
                addrCounter += if (encoder is ARMv7PsuedoEncoder){
                    encoder.expandsTo
                } else {
                    1
                }
                val result = encodeInstruction(child, symbols, addrCounter)
                if (result.isErr())
                    errors.add(result.unwrapErr())
                else
                    debug("Original: ${child.text}, Encoded: ${
                        result.unwrap().joinToString(separator = "\n") { x -> x.toUInt().toString(16) }
                    }\n")
            }
        }

        return Err(errors)
        // TODO: return the successfully compiled version
    }
}
