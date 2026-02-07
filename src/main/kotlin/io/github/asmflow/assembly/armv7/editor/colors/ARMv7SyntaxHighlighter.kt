package io.github.asmflow.assembly.armv7.editor.colors

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.TokenType
import com.intellij.psi.tree.IElementType
import io.github.asmflow.assembly.armv7.lexer.ARMv7HighlightingLexer
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenSets
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes

class ARMv7SyntaxHighlighter : SyntaxHighlighterBase() {
    val keys: MutableMap<IElementType, TextAttributesKey> = mutableMapOf()

    init {
        keys[TokenType.BAD_CHARACTER] = HighlighterColors.BAD_CHARACTER

        keys[ARMv7TokenTypes.IDENTIFIER] = ARMv7TextAttributes.ARMv7_LABEL

        fillMap(keys, ARMv7TokenSets.COMMENTS, ARMv7TextAttributes.ARMv7_COMMENT)
        fillMap(keys, ARMv7TokenSets.DIRECTIVES, ARMv7TextAttributes.ARMv7_DIRECTIVE)
        fillMap(keys, ARMv7TokenSets.INSTRUCTIONS, ARMv7TextAttributes.ARMv7_INSTRUCTION)
        fillMap(keys, ARMv7TokenSets.NUMBERS, ARMv7TextAttributes.ARMv7_NUMBER)
        fillMap(keys, ARMv7TokenSets.REGISTERS, ARMv7TextAttributes.ARMv7_REGISTERS)
        fillMap(keys, ARMv7TokenSets.SHIFT_TYPES, ARMv7TextAttributes.ARMv7_SHIFT_TYPE)
        fillMap(keys, ARMv7TokenSets.STRINGS, ARMv7TextAttributes.ARMv7_STRING)
    }

    override fun getHighlightingLexer(): Lexer = ARMv7HighlightingLexer()

    override fun getTokenHighlights(elementType: IElementType?): Array<out TextAttributesKey?>
        = pack(keys[elementType])
}
