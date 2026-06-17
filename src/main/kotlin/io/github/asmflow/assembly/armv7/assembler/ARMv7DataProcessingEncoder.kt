package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7Immediate
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase.getOpcode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.util.functional.Option

/**
 * Object providing support for encoding data processing instructions.
 */
object ARMv7DataProcessingEncoder : ARMv7InstructionEncoder {
    fun Boolean.toInt() = if (this) 1 else 0
    fun encodeRegisterVariant(
        condition: ARMv7InstructionConditionCode,
        opcode: Int,
        S: Boolean,
        Rn: ARMv7Register,
        Rd: ARMv7Register,
        Rm: ARMv7Register,
        shift: Option<ARMv7InstructionOperand.Register.Shift>
    ): Int {
        val shiftType = shift.map { it.shiftType }.unwrapOr(ARMv7ShiftType.LSL)
        val shiftImmediate = if (shift.isSome()) {
            val number = shift.unwrap().shiftBy as? ARMv7InstructionOperand.Number
                ?: throw AssemblySyntaxException("No number provided for $shiftType shift.")
            ARMv7Immediate.encode5bitImmediate(
                number.value.toString(),
                shiftType
            )
        } else 0

        val instruction =
            ((condition.code shl 28) or (0b000 shl 25) or (opcode shl 21) or (S.toInt() shl 20) or (Rn.getIDSafe() shl 16)
                    or (Rd.getIDSafe() shl 12) or (shiftImmediate shl 7) or (shiftType.code shl 5) or (0b0 shl 4) or (Rm.getIDSafe()))
        return instruction
    }

    fun encodeImmediateVariant(
        condition: ARMv7InstructionConditionCode,
        opcode: Int,
        S: Boolean,
        Rn: ARMv7Register,
        Rd: ARMv7Register,
        imm12: Int
    ): Int {
        val instruction =
            ((condition.code shl 28) or (0b001 shl 25) or (opcode shl 21) or (S.toInt() shl 20) or (Rn.getIDSafe() shl 16)
                    or (Rd.getIDSafe() shl 12) or imm12)
        return instruction
    }

    fun encodeRSRVariant(
        condition: ARMv7InstructionConditionCode,
        opcode: Int,
        S: Boolean,
        Rn: ARMv7Register,
        Rd: ARMv7Register,
        Rs: ARMv7Register,
        Rm: ARMv7Register,
        shiftType: ARMv7ShiftType,
    ): Int {
        val instruction =
            ((condition.code shl 28) or (0b000 shl 25) or (opcode shl 21) or (S.toInt() shl 20) or (Rn.getIDSafe() shl 16) or
                    (Rd.getIDSafe() shl 12) or (Rs.getIDSafe() shl 8) or (0b0 shl 7) or (shiftType.code shl 5) or (0b1 shl 4) or (Rm.getIDSafe()))
        return instruction
    }

    fun processRegisterVariant(
        instruction: ARMv7InstructionMixin,
        Rn: ARMv7InstructionOperand.Register,
        Rd: ARMv7InstructionOperand.Register,
        Rm: ARMv7InstructionOperand.Register
    ) =
        when (instruction.baseMnemonic) {
            "adc", "add", "and", "sub" -> encodeRegisterVariant(
                instruction.conditionCode,
                getOpcode(instruction.baseMnemonic),
                instruction.setsFlags,
                Rn.register,
                Rd.register,
                Rm.register,
                Rm.shift
            )

            else -> throw AssemblySyntaxException("Invalid mnemonic for register DP format: ${instruction.baseMnemonic}")
        }

    fun processTwoArgRegisterVariant(
        instruction: ARMv7InstructionMixin,
        Rm: ARMv7InstructionOperand.Register,
        Rd: ARMv7InstructionOperand.Register
    ) = when(instruction.baseMnemonic) {
        "adc", "add", "and", "sub" -> encodeRegisterVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            Rd.register,
            Rd.register,
            Rm.register,
            Rm.shift
        )
        // Fuck the UAL
        "mov", "mvn" -> encodeRegisterVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            ARMv7Register.R0,
            Rd.register,
            Rm.register,
            Rm.shift
        )
        else -> throw AssemblySyntaxException("Invalid mnemonic for two-argument register DP format: ${instruction.baseMnemonic}")
    }

    // TODO also support negative immediate for MVN/SUB
    // Maybe in a cleaner way in code :)
    fun processTwoArgImmediateVariant(
        instruction: ARMv7InstructionMixin,
        Rd: ARMv7InstructionOperand.Register,
        immediateNumber: Int
    ) = when(instruction.baseMnemonic){
        "add" if immediateNumber < 0 -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode("sub"),
            instruction.setsFlags,
             Rd.register,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate((-immediateNumber).toUInt())
        )
        "mov" if immediateNumber < 0 -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode("mvn"),
            instruction.setsFlags,
            ARMv7Register.R0,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate((-immediateNumber - 1).toUInt()) // Two's complement
        )
        "adc", "add", "and", "sub" -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            Rd.register,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate(immediateNumber.toUInt())
        )
        "mov", "mvn" -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            ARMv7Register.R0,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate(immediateNumber.toUInt())
        )
        else -> throw AssemblySyntaxException("Invalid mnemonic for two-argument immediate format: ${instruction.baseMnemonic}")
    }

    fun processImmediateVariant(
        instruction: ARMv7InstructionMixin,
        Rn: ARMv7InstructionOperand.Register,
        Rd: ARMv7InstructionOperand.Register,
        immediateNumber: Int
    ) = when (instruction.baseMnemonic) {
        // No need to support certain weird psuedos with negative immediates since
        // these are not required to be supported by ARM standards
        "add" if immediateNumber < 0 -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode("sub"),
            instruction.setsFlags,
            Rn.register,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate((-immediateNumber).toUInt())
        ) // Support add with negative immediate by encoding as SUB
        "adc", "add", "and", "sub" -> encodeImmediateVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            Rn.register,
            Rd.register,
            ARMv7Immediate.encode12bitImmediate(immediateNumber.toUInt())
        )

        else -> throw AssemblySyntaxException("Invalid mnemonic for immediate DP format: ${instruction.baseMnemonic}")
    }

    fun processRSRVariant(
        instruction: ARMv7InstructionMixin,
        Rn: ARMv7InstructionOperand.Register,
        Rd: ARMv7InstructionOperand.Register,
        Rm: ARMv7Register,
        Rs: ARMv7Register,
        shiftType: ARMv7ShiftType
    ) = when (instruction.baseMnemonic) {
        "adc", "add", "and" -> encodeRSRVariant(
            instruction.conditionCode,
            getOpcode(instruction.baseMnemonic),
            instruction.setsFlags,
            Rn.register,
            Rd.register,
            Rs,
            Rm,
            shiftType
        )

        else -> throw AssemblySyntaxException("Invalid mnemonic for immediate RSR format: ${instruction.baseMnemonic}")

    }

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        //val cond = instruction.conditionCode

        // For now, make the following assumptions:
        // 1. All data processing instructions have [27:26] = 00 (by definition)
        // 2. All instructions can be executed conditionally, so always parse the instruction codes into bits [31: 28]
        // 3. The S suffix encoded as [20] is either supported (use the sBit variable), or not supported (forced as either 0 or 1)
        // 4. Ignore certain instructions such as MUL, LDRH, BX, MOVW etc. that use [27:26] = 00 but do not have the standard format for now.
        // 5. All instructions require at least one register. For instructions that require two, they are written as Rd, Rn and encoded Rn-Rd till bit 12.
        // Otherwise, for instructions that require only one register, either Rn or Rd must be encoded as 0b0000.
        // 6. Bits [11: 0] are the complicated part. For now, we assume each instruction has one of the following 3 encodings:
        // a) The bottom 12 bits are an immediate. The I bit must be set to 1 and, it is split into an 8 bit immediate and 4 bit rotate right multipler.
        // b) Register shift encoding: Bottom 12 bits specify another register, type of shift, and 5 bit immediate for shift amount
        // c) Register shifted register: Bottom 12 bits specify a register, which is shifted by the amount specified in another register, according to a certain type.

        if (operands.size !in 1..3) throw AssemblySyntaxException("Too few or too little operands in instruction ${instruction.text}") // Register with shift is encoded as one operand
        // TODO: perform some common checks on operands,
        // for this type of instructions
        // Check for register variant
        if (operands.size == 3) {
            val (rd, rn, rm) = operands.map { it.operand }
            if (rd is ARMv7InstructionOperand.Register && rn is ARMv7InstructionOperand.Register && rm is ARMv7InstructionOperand.Register) {
                if (!rd.shift.isSome() && !rn.shift.isSome()) return listOf(
                    processRegisterVariant(
                        instruction,
                        rn,
                        rd,
                        rm
                    )
                )
            }
        }

        // Register variant with only two arguments
        // Two cases: either <Rn> = <Rd>
        // Or instruction itself requires two arguments, e.g. mov

        if (operands.size == 2){
            val (rd, rm) = operands.map{it.operand}
            if (rd is ARMv7InstructionOperand.Register && rm is ARMv7InstructionOperand.Register){
                if (!rd.shift.isSome()){
                    return listOf(processTwoArgRegisterVariant(
                        instruction,
                        rm,
                        rd
                    ))
                }
            }
        }

        // Immediate variant with only two arguments
        if (operands.size == 2){
            val (rd, imm) = operands.map{it.operand}
            if (rd is ARMv7InstructionOperand.Register && imm is ARMv7InstructionOperand.Number){
                if (!rd.shift.isSome()) return listOf(processTwoArgImmediateVariant(instruction, rd, imm.value))
            }
        }

        // Check for immediate variant
        if (operands.size == 3) {
            val (rd, rn, imm) = operands.map { it.operand }
            if (rd is ARMv7InstructionOperand.Register && rn is ARMv7InstructionOperand.Register && imm is ARMv7InstructionOperand.Number) {
                if (!rd.shift.isSome() && !rn.shift.isSome()) return listOf(
                    processImmediateVariant(
                        instruction,
                        rn,
                        rd,
                        imm.value
                    )
                )
            }
        }

        // RSR variant
        if (operands.size == 3) {
            val (rd, rn, rsr) = operands.map { it.operand }
            if (rd is ARMv7InstructionOperand.Register && rn is ARMv7InstructionOperand.Register && rsr is ARMv7InstructionOperand.Register
                && rsr.shift.isSome() && rsr.shift.unwrap().shiftBy is ARMv7InstructionOperand.Register
            ) {
                return listOf(
                    processRSRVariant(
                        instruction,
                        rn,
                        rd,
                        rsr.register,
                        (rsr.shift.unwrap().shiftBy as ARMv7InstructionOperand.Register).register,
                        rsr.shift.unwrap().shiftType
                    )
                )
            }
        }

        throw AssemblySyntaxException("Invalid or unsupported format for mnemonic ${instruction.baseMnemonic}")
    }
}