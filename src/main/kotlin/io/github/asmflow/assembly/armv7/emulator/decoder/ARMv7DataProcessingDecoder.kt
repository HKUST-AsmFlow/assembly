package io.github.asmflow.assembly.armv7.emulator

import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7Immediate
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.execution.ShiftResult
import io.github.asmflow.assembly.emulator.EmulationException
import io.github.asmflow.assembly.util.functional.None
import io.github.asmflow.assembly.util.functional.Option
import io.github.asmflow.assembly.util.functional.Some
import io.github.asmflow.assembly.util.functional.toOption

/**
 * Basic decoded representation for ARM data-processing instructions.
 */
data class DecodedDataProcessingInstruction(
    val instruction: ARMv7InstructionDatabase.Instruction,
    val setFlags: Boolean,
    val rn: Int,
    val rd: Int,
    val operand2: DecodedOperand2
)

sealed class DecodedOperand2() {
    abstract fun getValue(): Int
    abstract fun getCarryOut(): Boolean // only for register shift operations
    data class Immediate(val imm12: Int, private val registers: ARMv7RegisterState) : DecodedOperand2() {

        private val imm8 = imm12 and 0xFF
        private val rotateAmount = ((imm12 ushr 8) and 0xF) * 2

        override fun getValue(): Int {
            return compute().result
        }

        override fun getCarryOut(): Boolean {
            return compute().carryOut
        }

        private fun compute(): ShiftResult {
            val carryIn = registers.getCPSR().C
            return if (rotateAmount == 0) {
                // TRAP AVOIDED: If we pass 0 to ROR.shift, our shifter will
                // think it's an RRX instruction!
                // Immediates with 0 rotation just return the value and preserve the carry.
                ShiftResult(result = imm8, carryOut = carryIn)
            } else {
                // For any other rotation, we perfectly reuse the ROR hardware logic!
                ARMv7ShiftType.ROR.shift(imm8, rotateAmount, carryIn)
            }
        }
    }

    data class Register(
        val rm: Int,
        val shiftType: ARMv7ShiftType,
        val shiftImm: Int,
        private val registers: ARMv7RegisterState
    ) : DecodedOperand2() {
        override fun getValue(): Int {
            val result = shiftType.shift(registers.get(rm), shiftImm, registers.getCPSR().C)
            return result.result
        }

        override fun getCarryOut(): Boolean {
            val result = shiftType.shift(registers.get(rm), shiftImm, registers.getCPSR().C)
            return result.carryOut
        }
    }
    // TODO add rsr support
}


/**
 * Universal decoder for data-processing instructions:
 * - supports I=1 immediate operand2
 * - supports I=0 register + immediate shift (bit4 must be 0)
 * - rejects RSR (bit4==1) for now
 */
class ARMv7DataProcessingDecoder(private val registers: ARMv7RegisterState) {
    fun decode(raw: Int): DecodedDataProcessingInstruction {
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
            instruction = instruction,
            setFlags = setFlags,
            rn = rn,
            rd = rd,
            operand2 = operand2
        )
    }

    private fun decodeImmediateOperand2(imm12: Int): DecodedOperand2.Immediate {

        return DecodedOperand2.Immediate(imm12, registers)
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
            shiftImm = shiftImm,
            registers = registers
        )
    }
}
