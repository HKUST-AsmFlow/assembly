package io.github.asmflow.assembly.armv7.lexer

import com.intellij.lexer.LayeredLexer
import com.intellij.lexer.StringLiteralLexer
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes

class ARMv7HighlightingLexer : LayeredLexer(ARMv7Lexer()) {
    init {
        registerSelfStoppingLayer(
            object : StringLiteralLexer(NO_QUOTE_CHAR, ARMv7TokenTypes.STRING, false, "9", true, false) {
                override fun shouldAllowSlashZero(): Boolean = true
            },
            arrayOf(ARMv7TokenTypes.STRING),
            emptyArray()
        )
    }
}
