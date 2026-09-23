package io.github.asmflow.assembly.armv7.assembler

interface AssembledInstruction {
    val address: UInt
    val text: String
    val lineNumber: UInt
    val machineCode: ByteArray
    val machineCodeHex: String get() = machineCode.joinToString("") { "%02X".format(it) }

}