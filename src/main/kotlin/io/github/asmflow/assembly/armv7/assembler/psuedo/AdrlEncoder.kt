package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

class AdrlEncoder(val symbols: HashMap<String, Int>) : ARMv7PsuedoEncoder {
    // This is awkward - we should only output a single ADD / SUB if possible
    // However, the current implementation does not allow this.
    // Hence, we set this to two for simplicity.
    override val expandsTo: Int
        get() = 2 // Two adds or subs

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        TODO("Not yet implemented")
    }
}