package io.github.asmflow.assembly.armv7.parser

import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import com.intellij.psi.TokenType

object ARMv7ParserUtil : GeneratedParserUtilBase() {
    @JvmStatic
    fun afterWhitespace(psiBuilder: PsiBuilder, @Suppress("unused") level: Int): Boolean =
        psiBuilder.rawLookup(-1) == TokenType.WHITE_SPACE
}
