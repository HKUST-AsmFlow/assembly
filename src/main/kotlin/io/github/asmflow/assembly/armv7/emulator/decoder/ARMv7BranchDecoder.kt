package io.github.asmflow.assembly.armv7.emulator.decoder

import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.emulator.EmulationException

/**
 * Basic decoded representation for ARM B and BL instructions.
 */
data class DecodedBranchInstruction(
    val isLink: Boolean,   // true if BL, false if B
    val byteOffset: Int    // The fully calculated, sign-extended byte offset
)

class ARMv7BranchDecoder {

    fun decode(raw: Int): DecodedBranchInstruction {

        val signature = (raw ushr 25) and 0b111
        if (signature != 0b101) {
            throw EmulationException("Invalid branch signature: expected 101, got ${signature.toString(2)}")
        }

        val isLink = ((raw ushr 24) and 1) == 1
        val imm24 = raw and 0xFFFFFF

        // Sign-Extend the 24-bit number to 32 bits
        val signExtendedImm = (imm24 shl 8) shr 8
        val byteOffset = signExtendedImm shl 2

        return DecodedBranchInstruction(
            isLink = isLink,
            byteOffset = byteOffset
        )
    }
}