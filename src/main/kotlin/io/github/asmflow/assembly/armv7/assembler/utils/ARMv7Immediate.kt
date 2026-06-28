package io.github.asmflow.assembly.armv7.assembler.utils

import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.assembler.AssemblySyntaxException

object ARMv7Immediate {
    fun preProcessImmediate(input: String): UInt {
        if (input.isEmpty()) throw AssemblySyntaxException("Immediate is empty in $input")
        val trimmed = if (input.startsWith("#")) input.substring(1) else input
        val radix: Int
        val numberString: String

        when {
            trimmed.startsWith("0x", ignoreCase = true) -> {
                radix = 16
                numberString = trimmed.substring(2)
            }

            trimmed.startsWith("0b", ignoreCase = true) -> {
                radix = 2
                numberString = trimmed.substring(2)
            }

            else -> {
                radix = 10
                numberString = trimmed
            }
        }
        val value = numberString.toUIntOrNull(radix)
            ?: throw AssemblySyntaxException("Invalid immediate format or characters in $input")
        return value

    }

    fun encode12bitImmediate(input: String): Int {
        val value = preProcessImmediate(input)
        return encode12bitImmediate(value)
    }

    fun encode12bitImmediate(input: UInt): Int {
        val value = input

        // The formula is: value == imm8 ROR (rot * 2)
        // To solve for imm8, we reverse it: imm8 == value ROL (rot * 2)
        // We test all 16 possible even rotations (0, 2, 4 ... 30).

        for (rot in 0..15) {
            val shiftAmount = rot * 2

            // Perform a 32-bit Rotate Left (ROL)
            val rotated = if (shiftAmount == 0) {
                value
            } else {
                (value shl shiftAmount) or (value shr (32 - shiftAmount))
            }

            // Check if the rotated value fits inside the 8-bit window (<= 255)
            if (rotated <= 255u) {
                val imm8 = rotated.toInt()

                // Build the 12-bit encoding:
                // Top 20 bits are 0.
                // Bits [11:8] = rot
                // Bits [7:0] = imm8
                return (rot shl 8) or imm8
            }
        }

        // If we complete the loop, the '1' bits span more than 8 bits
        // or cannot be aligned to an even rotation boundary.
        throw AssemblySyntaxException(
            "Value $value (0x${value.toString(16).uppercase()}) cannot be encoded. " +
                    "Its active bits exceed the 8-bit window or cannot be aligned via an even-numbered rotation."
        )
    }

    // returns (imm4, imm12)
    fun encode16bitImmediate(input: UInt): Pair<Int, Int> {
        if (input >= (1u shl 16)) throw AssemblySyntaxException("Value $input cannot be encoded as a 16 bit integer because it is too large.")
        return (input shr 12).toInt() to (input and 0xFFFu).toInt()
    }

    fun decode12bitImmediate(imm12: Int): Int {
        val shiftAmount = (imm12 ushr 8) and 0xF // bits [11:8]
        val rotateAmount = shiftAmount * 2 // ROR amount
        val raw = imm12 and 0xFF // bits [7:0]
        val immediate = Integer.rotateRight(raw, rotateAmount)
        return immediate
    }

    fun encode5bitImmediate(input: String, type: ARMv7ShiftType): Int {
        if (type == ARMv7ShiftType.RRX) return 0
        // For bit shifts with immediates
        val value = preProcessImmediate(input)
        if (type == ARMv7ShiftType.LSL)
            if (value == 32u) return 0
        if (value !in 1u..32u) throw AssemblySyntaxException("Shift value $value out of range.")
        return value.toInt()

    }
}