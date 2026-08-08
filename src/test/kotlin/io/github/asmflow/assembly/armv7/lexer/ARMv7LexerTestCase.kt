package io.github.asmflow.assembly.armv7.lexer

import io.github.asmflow.assembly.lexer.AssemblyLexerTestCase

class ARMv7LexerTestCase : AssemblyLexerTestCase() {
    override fun createLexer() = ARMv7Lexer()

    override fun getDirPath() = TODO("Not yet implemented")
}
