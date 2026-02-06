package io.github.asmflow.assembly.armv7.lexer

import com.intellij.lexer.FlexAdapter

class ARMv7Lexer : FlexAdapter(ARMv7LexerImpl(null))
