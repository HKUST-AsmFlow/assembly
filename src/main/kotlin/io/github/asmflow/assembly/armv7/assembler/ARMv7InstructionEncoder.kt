package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

/**
 * Interface for all ARMv7 instruction encoders.
 */
interface ARMv7InstructionEncoder {
    /**
     * Encodes a ARMv7 instruction given its operands and the address counter.
     */
    fun encode(instruction: ARMv7InstructionMixin, operands: List<ARMv7Operand>, addrCounter: Int): List<Int>
}
