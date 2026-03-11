package io.github.asmflow.assembly.armv7.assembler

import com.intellij.rml.dfa.utils.toInt
import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7Immediate
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.psi.ARMv7Shift
import io.github.asmflow.assembly.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.util.functional.Option

object ARMv7DataProcessingEncoder : ARMv7InstructionEncoder {
    fun encodeRegisterVariant(
        condition: ARMv7InstructionConditionCode,
        opcode: Int,
        S: Boolean,
        Rn: ARMv7Register,
        Rd: ARMv7Register,
        Rm: ARMv7Register,
        shift: Option<ARMv7Shift>
    ): Int {
        val shiftType =
            if (shift.isSome()) ARMv7ShiftType.fromString(shift.unwrap().shiftType.text) else ARMv7ShiftType.LSL
        val shiftImmediate = if (shift.isSome()) ARMv7Immediate.encode5bitImmediate(
            shift.unwrap().number?.text ?: throw AssemblySyntaxException("No number provided for $shiftType shift."),
            shiftType
        ) else 0
        val instruction =
            ((condition.code shl 28) or (0b000 shl 25) or (opcode shl 21) or (S.toInt() shl 20) or (Rn.getIDSafe() shl 16)
                    or (Rd.getIDSafe() shl 12) or (shiftImmediate shl 7) or (shiftType.code shl 5) or (0b0 shl 4) or (Rm.getIDSafe()))
        return instruction
    }

    fun processRegisterVariant(
        instruction: ARMv7InstructionMixin,
        Rn: ARMv7InstructionOperand.Register,
        Rd: ARMv7InstructionOperand.Register,
        Rm: ARMv7InstructionOperand.Register
    ) =
        when (instruction.baseMnemonic) {
            "adc" -> encodeRegisterVariant(
                instruction.conditionCode,
                0b0101,
                instruction.setsFlags,
                Rn.register,
                Rd.register,
                Rm.register,
                Rm.shift
            )

            "add" -> encodeRegisterVariant(
                instruction.conditionCode,
                0b0100,
                instruction.setsFlags,
                Rn.register,
                Rd.register,
                Rm.register,
                Rm.shift
            )

            "and" -> encodeRegisterVariant(
                instruction.conditionCode,
                0b0000,
                instruction.setsFlags,
                Rn.register,
                Rd.register,
                Rm.register,
                Rm.shift
            )

            "asr" -> encodeRegisterVariant(
                instruction.conditionCode,
                0b0000,
                instruction.setsFlags,
                Rn.register,
                Rd.register,
                Rm.register,
                Rm.shift
            )

            else -> throw AssemblySyntaxException("Invalid mnemonic for register DP format: $instruction.baseMnemonic")
        }

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>
    ): Int {
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
                if (!rd.shift.isSome() && !rn.shift.isSome()) return processRegisterVariant(instruction, rn, rd, rm)
            }
        }
        /*
        if (operands.size == 3 && operands[0].operand is ARMv7InstructionOperand.Register && operands[1].operand is ARMv7InstructionOperand.Register && operands[2].operand is ARMv7InstructionOperand.Register) {
            // First two register need to be without shift, second one may have a shift
            when (instruction.baseMnemonic){
                //"and" -> encodeRegisterVariant(instruction.conditionCode, 0b0000, instruction.setsFlags, operands[1].registerWithShift, operands[0], operands[2], operands[2].registerWithShift)
            }
        }

        operands[0].isShiftlessRegister()
        // Check for case a: Instruction typically written as MNEMONIC{S}{<c>} {<Rd>,} <Rn>, #<const>
        if (operands.size == 3 && operands[1].isShiftlessRegister() && operands[2].isNumber()) {
            //throw Exception("Case 1")
        }
        if (operands.size == 2 && operands[1].isNumber()) {
            //throw Exception("Case 2")
        }
        //throw Exception("Case 3")
        operands[1].registerWithShift.shift.shiftType
        return 0
        */
        return 0

    }
}