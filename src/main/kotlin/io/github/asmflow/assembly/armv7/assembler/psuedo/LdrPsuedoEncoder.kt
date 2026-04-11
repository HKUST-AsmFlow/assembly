package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

object LdrPsuedoEncoder: ARMv7PsuedoEncoder {
    override val expandsTo: Int
        get() = TODO("Not yet implemented")

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        TODO("Not yet implemented")
    }
}