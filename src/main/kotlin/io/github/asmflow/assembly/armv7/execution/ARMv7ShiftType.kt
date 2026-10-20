package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.armv7.assembler.AssemblySyntaxException


data class ShiftResult(
    val result: Int,
    val carryOut: Boolean
)

enum class ARMv7ShiftType(val code: Int, val description: String) {
    ASR(0b10, "Arithmetic shift right"),
    LSL(0b00, "Logical shift left"),
    LSR(0b01, "Logical shift right"),
    ROR(0b11, "Rotate right"),
    RRX(0b11, "Rotate right extended");

    /**
     * Shifts [item] by an amount encoded as a 5-bit immediate, applying the spec's DecodeImmShift:
     * LSR #0 and ASR #0 encode a shift by 32, and ROR #0 encodes RRX.
     */
    fun shift(item: Int, imm5: Int, carryIn: Boolean): ShiftResult {
        return when (this) {
            LSL, ROR -> shiftC(item, imm5, carryIn)
            LSR, ASR -> shiftC(item, if (imm5 == 0) 32 else imm5, carryIn)
            RRX -> {
                // RRX shifts right by exactly 1. imm5 is completely ignored.
                // It shifts the number right by 1, and inserts the old Carry flag into Bit 31.
                val carryBit = if (carryIn) (1 shl 31) else 0
                val result = (item ushr 1) or carryBit

                // The bit that fell off the right side is the very first bit (Bit 0).
                val carryOut = (item and 1) == 1
                ShiftResult(result, carryOut)
            }
        }
    }

    /**
     * Shifts [item] by an actual [amount] (0..255), following the spec's Shift_C.
     * An amount of 0 leaves both the value and the carry unchanged.
     *
     * JVM shifts only use the low 5 bits of the count (`x shl 32 == x`), so amounts of 32 and
     * above are handled explicitly.
     */
    fun shiftC(item: Int, amount: Int, carryIn: Boolean): ShiftResult {
        if (amount == 0) return ShiftResult(result = item, carryOut = carryIn)

        return when (this) {
            LSL -> when {
                amount < 32 -> ShiftResult(
                    result = item shl amount,
                    // The last bit pushed off the left side is at position (32 - amount)
                    carryOut = ((item ushr (32 - amount)) and 1) == 1
                )
                amount == 32 -> ShiftResult(result = 0, carryOut = (item and 1) == 1)
                else -> ShiftResult(result = 0, carryOut = false)
            }

            LSR -> when {
                amount < 32 -> ShiftResult(
                    result = item ushr amount,
                    // The last bit pushed off the right side is at position (amount - 1)
                    carryOut = ((item ushr (amount - 1)) and 1) == 1
                )
                amount == 32 -> ShiftResult(result = 0, carryOut = (item ushr 31) == 1)
                else -> ShiftResult(result = 0, carryOut = false)
            }

            ASR -> if (amount < 32) {
                ShiftResult(
                    result = item shr amount,
                    carryOut = ((item ushr (amount - 1)) and 1) == 1
                )
            } else {
                // Shifting by 32 or more copies the sign bit (Bit 31) across the entire register.
                ShiftResult(result = item shr 31, carryOut = (item ushr 31) == 1)
            }

            ROR -> {
                // Rotating by a multiple of 32 leaves the value unchanged; carry is always the new Bit 31
                val result = Integer.rotateRight(item, amount and 31)
                ShiftResult(result, carryOut = (result ushr 31) == 1)
            }

            // RRX always shifts by exactly 1, so the amount is irrelevant
            RRX -> shift(item, 1, carryIn)
        }
    }

    companion object {
        fun fromCodeAndImmediate(code: Int, immediate: Int): ARMv7ShiftType? {
            if (code == 0b11 && immediate == 0b00) return RRX
            if (code == 0b11) return ROR
            return entries.firstOrNull { it.code == code }
        }

        fun fromString(value: String): ARMv7ShiftType {
            try {
                val shift = ARMv7ShiftType.valueOf(value.uppercase())
                return shift
            } catch (_: IllegalArgumentException) {
                throw AssemblySyntaxException("Invalid shift type: $value")
            }
        }
    }
}