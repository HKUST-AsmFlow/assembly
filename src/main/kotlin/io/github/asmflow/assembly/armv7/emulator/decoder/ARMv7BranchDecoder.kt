package io.github.asmflow.assembly.armv7.emulator.decoder

import io.github.asmflow.assembly.armv7.emulator.EmulationException

/**
 * Basic decoded representation for ARM B and BL instructions.
 */
data class DecodedBranchInstruction(
    val isLink: Boolean,   // true if BL, false if B
    val byteOffset: Int    // The fully calculated, sign-extended byte offset
)

/**
 * Decoded representation for ARM BX and BLX (register) instructions.
 */
data class DecodedBranchRegisterInstruction(
    val isLink: Boolean,   // true if BLX, false if BX
    val rm: Int            // register holding the target address
)

class ARMv7BranchDecoder {
    companion object {
        private const val BRANCH_REGISTER_MASK = 0x0FFFFFF0
        private const val BX_SIGNATURE = 0x012FFF10
        private const val BLX_SIGNATURE = 0x012FFF30

        /**
         * Whether [raw] is a BX or BLX (register) instruction, ignoring the condition and Rm.
         */
        fun isBranchRegister(raw: Int): Boolean {
            val signature = raw and BRANCH_REGISTER_MASK
            return signature == BX_SIGNATURE || signature == BLX_SIGNATURE
        }
    }

    /**
     * Decodes a raw BX or BLX (register) instruction into a DecodedBranchRegisterInstruction.
     */
    fun decodeRegister(raw: Int): DecodedBranchRegisterInstruction {
        if (!isBranchRegister(raw)) {
            throw EmulationException("Invalid BX/BLX signature: 0x${(raw and BRANCH_REGISTER_MASK).toUInt().toString(16)}")
        }

        return DecodedBranchRegisterInstruction(
            isLink = (raw and BRANCH_REGISTER_MASK) == BLX_SIGNATURE,
            rm = raw and 0xF
        )
    }

    /**
     * Decodes a raw branch instruction into a DecodedBranchInstruction.
     *
     * First checks whether the branch signature (bits 25 to 28) are correct.
     * Then, checks whether this instruction is a link instruction (BL),
     * and computes the byte offset by sign-extending the remaining bits.
     */
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