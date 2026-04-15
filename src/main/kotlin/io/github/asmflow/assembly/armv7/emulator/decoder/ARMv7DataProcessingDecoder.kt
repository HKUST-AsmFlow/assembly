package io.github.asmflow.assembly.armv7.emulator

import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.emulator.EmulationException

/**
 * Basic decoded representation for ARM data-processing instructions.
 */
data class DecodedDataProcessingInstruction(
    val condition: ARMv7InstructionConditionCode,
    val instruction: ARMv7InstructionDatabase.Instruction,
    val setFlags: Boolean,
    val rn: Int,
    val rd: Int,
    val operand2: DecodedOperand2
)

sealed class DecodedOperand2 {
    data class Immediate(val imm32: Int, val rotateRight: Int, val imm8: Int) : DecodedOperand2()
    data class Register(
        val rm: Int,
        val shiftType: ARMv7ShiftType,
        val shiftImm: Int
    ) : DecodedOperand2()
    // TODO add rsr support
}


/**
 * Universal decoder for data-processing instructions:
 * - supports I=1 immediate operand2
 * - supports I=0 register + immediate shift (bit4 must be 0)
 * - rejects RSR (bit4==1) for now
 */
object ARMv7DataProcessingDecoder {
    fun decode(raw: Int): DecodedDataProcessingInstruction {
        val condBits = (raw ushr 28) and 0xF

        val condition = ARMv7InstructionConditionCode.fromCode(condBits)
            ?: throw EmulationException("Invalid condition code: $condBits")

        val iBit = ((raw ushr 25) and 1) == 1
        val opcodeBits = (raw ushr 21) and 0xF

        val instruction = ARMv7InstructionDatabase
            .getByOpcode(opcodeBits)
            .unwrapOrThrow { EmulationException("Unsupported data-processing opcode: $opcodeBits") }

        val setFlags = ((raw ushr 20) and 1) == 1
        val rn = (raw ushr 16) and 0xF
        val rd = (raw ushr 12) and 0xF

        val operand2 = if (iBit) {
            decodeImmediateOperand2(raw and 0xFFF)
        } else {
            decodeRegisterOperand2(raw and 0xFFF)
        }

        return DecodedDataProcessingInstruction(
            condition = condition,
            instruction = instruction,
            setFlags = setFlags,
            rn = rn,
            rd = rd,
            operand2 = operand2
        )
    }

    private fun decodeImmediateOperand2(imm12: Int): DecodedOperand2.Immediate {
        val rotateField = (imm12 ushr 8) and 0xF
        val rotateRight = rotateField * 2
        val imm8 = imm12 and 0xFF
        val imm32 = Integer.rotateRight(imm8, rotateRight)
        return DecodedOperand2.Immediate(imm32 = imm32, rotateRight = rotateRight, imm8 = imm8)
    }

    private fun decodeRegisterOperand2(op2: Int): DecodedOperand2.Register {
        val bit4 = (op2 ushr 4) and 1
        if (bit4 == 1) {
            throw EmulationException("RSR form not supported yet (bit4=1)")
        }

        val rm = op2 and 0xF
        val shiftTypeCode = (op2 ushr 5) and 0b11
        val shiftImm = (op2 ushr 7) and 0x1F
        val shiftType = ARMv7ShiftType.fromCodeAndImmediate(shiftTypeCode, shiftImm)
            ?: throw EmulationException("Invalid shift type code=$shiftTypeCode shiftImm=$shiftImm")

        return DecodedOperand2.Register(
            rm = rm,
            shiftType = shiftType,
            shiftImm = shiftImm
        )
    }
}
