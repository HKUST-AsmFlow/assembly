package io.github.asmflow.assembly.armv7.assembler.encoder.pseudo

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

class AdrEncoder(val symbols: Map<String, UInt>) : ARMv7PseudoEncoder {
    override val expandsTo: Int
        get() = 1 // Either add or sub

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        TODO("Not yet implemented")
    }
}