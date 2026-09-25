package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.assembler.context.ProgramSymbol

data class AssembledProgram(
    val text: List<Int>,
    val data: ByteArray,
    val symbols: Map<String, ProgramSymbol>,
)
