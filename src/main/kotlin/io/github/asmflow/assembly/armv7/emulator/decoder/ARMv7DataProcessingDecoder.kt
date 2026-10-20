package io.github.asmflow.assembly.armv7.emulator.decoder

import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.database.InstructionFormat
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.execution.ShiftResult
import io.github.asmflow.assembly.armv7.emulator.EmulationException

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

sealed class DecodedOperand2 {
    /**
     * Computes the operand and the shifter carry-out from the current register file. Call it once,
     * before writing the destination register, since the destination may be one of the sources.
     */
    abstract fun evaluate(): ShiftResult

    fun getValue(): Int = evaluate().result
    fun getCarryOut(): Boolean = evaluate().carryOut

    data class Immediate(val imm12: Int, private val registers: ARMv7RegisterState) : DecodedOperand2() {

        private val imm8 = imm12 and 0xFF
        private val rotateAmount = ((imm12 ushr 8) and 0xF) * 2

        override fun evaluate(): ShiftResult {
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
        override fun evaluate(): ShiftResult =
            shiftType.shift(registers.get(rm), shiftImm, registers.getCPSR().C)
    }

    /**
     * Register-shifted register: Rm shifted by the bottom byte of Rs.
     */
    data class RegisterShiftedRegister(
        val rm: Int,
        val shiftType: ARMv7ShiftType,
        val rs: Int,
        private val registers: ARMv7RegisterState
    ) : DecodedOperand2() {
        override fun evaluate(): ShiftResult =
            shiftType.shiftC(registers.get(rm), registers.get(rs) and 0xFF, registers.getCPSR().C)
    }

    /**
     * The 16-bit immediate of MOVW / MOVT. Neither sets flags, so the carry is passed through.
     */
    data class Imm16(val imm16: Int, private val registers: ARMv7RegisterState) : DecodedOperand2() {
        override fun evaluate(): ShiftResult = ShiftResult(result = imm16, carryOut = registers.getCPSR().C)
    }
}


/**
 * Universal decoder for data-processing instructions:
 * - supports I=1 immediate operand2
 * - supports I=0 register + immediate shift (bit4 = 0)
 * - supports I=0 register-shifted register (bit4 = 1, bit7 = 0)
 * - resolves MOVW / MOVT, which live in the compare opcode space with S = 0
 */
class ARMv7DataProcessingDecoder(private val registers: ARMv7RegisterState) {
    fun decode(raw: Int): DecodedDataProcessingInstruction {
        val iBit = ((raw ushr 25) and 1) == 1
        val opcodeBits = (raw ushr 21) and 0xF
        val setFlags = ((raw ushr 20) and 1) == 1

        val instruction = resolveInstruction(iBit, opcodeBits, setFlags)
        val isWideMove = instruction.mnemonic in WIDE_MOVES

        val rn = (raw ushr 16) and 0xF
        val rd = (raw ushr 12) and 0xF

        val operand2 = when {
            // imm16 = imm4 (bits 19:16) : imm12 (bits 11:0); bits 19:16 are not Rn here
            isWideMove -> DecodedOperand2.Imm16((rn shl 12) or (raw and 0xFFF), registers)
            iBit -> decodeImmediateOperand2(raw and 0xFFF)
            else -> decodeRegisterOperand2(raw and 0xFFF)
        }

        return DecodedDataProcessingInstruction(
            instruction = instruction,
            setFlags = setFlags,
            rn = rn,
            rd = rd,
            operand2 = operand2
        )
    }

    /**
     * The compare opcodes `10xx` (TST, TEQ, CMP, CMN) are only data-processing with S = 1.
     * With S = 0, that space holds MOVW (I=1, 1000), MOVT (I=1, 1010), MSR / hints (I=1, 1001 and 1011)
     * and the miscellaneous instructions (I=0).
     */
    private fun resolveInstruction(iBit: Boolean, opcode: Int, setFlags: Boolean): ARMv7InstructionDatabase.Instruction {
        if ((opcode and 0b1100) == 0b1000 && !setFlags) {
            val mnemonic = when {
                iBit && opcode == 0b1000 -> "movw"
                iBit && opcode == 0b1010 -> "movt"
                else -> throw EmulationException(
                    "Unsupported instruction in the compare opcode space with S=0 " +
                            "(I=${if (iBit) 1 else 0}, opcode=${opcode.toString(2).padStart(4, '0')})"
                )
            }
            return ARMv7InstructionDatabase.get(mnemonic)
                .unwrapOrThrow { EmulationException("Database incomplete for mnemonic $mnemonic") }
        }

        return ARMv7InstructionDatabase
            .getByOpcode(opcode, format = InstructionFormat.DATA_PROCESSING) { it.mnemonic !in WIDE_MOVES }
            .unwrapOrThrow { EmulationException("Unsupported data-processing opcode: $opcode") }
    }

    private fun decodeImmediateOperand2(imm12: Int): DecodedOperand2.Immediate {

        return DecodedOperand2.Immediate(imm12, registers)
    }

    private fun decodeRegisterOperand2(op2: Int): DecodedOperand2 {
        val rm = op2 and 0xF
        val shiftTypeCode = (op2 ushr 5) and 0b11

        if (((op2 ushr 4) and 1) == 1) {
            // bit7 = 1 with bit4 = 1 is the multiply / extra load-store space, not RSR
            if (((op2 ushr 7) and 1) == 1) {
                throw EmulationException("Not a data-processing instruction (bit7 = 1 and bit4 = 1)")
            }

            // With a register amount, type 0b11 is always ROR (RRX has no amount)
            val shiftType = when (shiftTypeCode) {
                ARMv7ShiftType.LSL.code -> ARMv7ShiftType.LSL
                ARMv7ShiftType.LSR.code -> ARMv7ShiftType.LSR
                ARMv7ShiftType.ASR.code -> ARMv7ShiftType.ASR
                else -> ARMv7ShiftType.ROR
            }

            return DecodedOperand2.RegisterShiftedRegister(
                rm = rm,
                shiftType = shiftType,
                rs = (op2 ushr 8) and 0xF,
                registers = registers
            )
        }

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

    companion object {
        private val WIDE_MOVES = setOf("movw", "movt")
    }
}
