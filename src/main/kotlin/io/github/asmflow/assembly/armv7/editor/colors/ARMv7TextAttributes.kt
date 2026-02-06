package io.github.asmflow.assembly.armv7.editor.colors

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey

object ARMv7TextAttributes {
    val ARMv7_COMMENT = createTextAttributesKey("ARMv7.comment", DefaultLanguageHighlighterColors.LINE_COMMENT)
    val ARMv7_INSTRUCTION = createTextAttributesKey("ARMv7.instruction", DefaultLanguageHighlighterColors.KEYWORD)
    val ARMv7_LABEL = createTextAttributesKey("ARMv7.label", DefaultLanguageHighlighterColors.LABEL)
    val ARMv7_NUMBER = createTextAttributesKey("ARMv7.string", DefaultLanguageHighlighterColors.NUMBER)
    val ARMv7_REGISTERS = createTextAttributesKey("ARMv7.registers", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
    val ARMv7_STRING = createTextAttributesKey("ARMv7.string", DefaultLanguageHighlighterColors.STRING)
}
