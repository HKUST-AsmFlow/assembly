package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.assembler.AssembledInstruction

class Armv7AssembledInstruction(
    override val address: UInt,
    override val text: String,
    override val lineNumber: UInt,
    val rawBytes: Int
) : AssembledInstruction {
    override val machineCode: ByteArray = byteArrayOf(
        // ARM is little endian by default
        (rawBytes and 0xFF).toByte(),
        (rawBytes ushr 8 and 0xFF).toByte(),
        (rawBytes ushr 16 and 0xFF).toByte(),
        (rawBytes ushr 24 and 0xFF).toByte(),
    )

}