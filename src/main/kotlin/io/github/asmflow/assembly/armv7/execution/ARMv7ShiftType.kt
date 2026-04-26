package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.assembler.AssemblySyntaxException


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

    fun shift(item: Int, imm5: Int, carryIn: Boolean): ShiftResult {
        return when (this) {
            LSL -> {
                if (imm5 == 0) {
                    // LSL #0 means no shift happens. Carry is untouched.
                    ShiftResult(result = item, carryOut = carryIn)
                } else {
                    val result = item shl imm5
                    // The last bit pushed off the left side is at position (32 - imm5)
                    val carryOut = ((item ushr (32 - imm5)) and 1) == 1
                    ShiftResult(result, carryOut)
                }
            }

            LSR -> {
                if (imm5 == 0) {
                    // ARM Quirk: LSR #0 is actually LSR #32.
                    // Result is 0. Carry out is the last bit pushed out (Bit 31 of original).
                    val carryOut = (item ushr 31) == 1
                    ShiftResult(result = 0, carryOut = carryOut)
                } else {
                    val result = item ushr imm5
                    // The last bit pushed off the right side is at position (imm5 - 1)
                    val carryOut = ((item ushr (imm5 - 1)) and 1) == 1
                    ShiftResult(result, carryOut)
                }
            }

            ASR -> {
                if (imm5 == 0) {
                    // ARM Quirk: ASR #0 is actually ASR #32.
                    // It copies the sign bit (Bit 31) across the entire register.
                    val result = item shr 31
                    val carryOut = (item ushr 31) == 1
                    ShiftResult(result, carryOut)
                } else {
                    val result = item shr imm5
                    // The last bit pushed off the right side is at position (imm5 - 1)
                    val carryOut = ((item ushr (imm5 - 1)) and 1) == 1
                    ShiftResult(result, carryOut)
                }
            }

            ROR -> {
                val result = Integer.rotateRight(item, imm5)
                // The last bit pushed off the right side is at position (imm5 - 1)
                val carryOut = ((item ushr (imm5 - 1)) and 1) == 1
                ShiftResult(result, carryOut)
            }

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