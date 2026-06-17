package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.armv7.assembler.ARMv7InstructionEncoder

/**
 * Interface for all encoders of ARMv7 psuedoinstructions.
 */
interface ARMv7PsuedoEncoder : ARMv7InstructionEncoder {
    /**
     * Value representing the number of actual instructions that this pseudoinstruction expands to.
     */
    val expandsTo: Int
}