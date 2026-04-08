package io.github.asmflow.assembly.armv7.assembler

interface ARMv7PsuedoEncoder: ARMv7InstructionEncoder {
    val expandsTo: Int
}