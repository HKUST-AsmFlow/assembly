package io.github.asmflow.assembly.armv7.assembler.psuedo

import io.github.asmflow.assembly.armv7.assembler.ARMv7InstructionEncoder

interface ARMv7PsuedoEncoder: ARMv7InstructionEncoder {
    val expandsTo: Int
}