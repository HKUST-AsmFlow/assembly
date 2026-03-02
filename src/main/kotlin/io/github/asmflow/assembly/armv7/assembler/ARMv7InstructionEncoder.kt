package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

interface ARMv7InstructionEncoder {
    fun encode(instruction: ARMv7InstructionMixin, operands: List<ARMv7Operand>): Int
}