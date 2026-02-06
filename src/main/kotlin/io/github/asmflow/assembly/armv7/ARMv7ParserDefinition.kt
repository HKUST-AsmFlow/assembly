package io.github.asmflow.assembly.armv7

import com.intellij.lang.ASTNode
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import io.github.asmflow.assembly.armv7.lexer.ARMv7Lexer
import io.github.asmflow.assembly.armv7.parser.ARMv7Parser
import io.github.asmflow.assembly.armv7.psi.ARMv7FileElementType
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenSets
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.lang.AssemblyParserDefinition

class ARMv7ParserDefinition : AssemblyParserDefinition() {
    override fun createLexer(p0: Project?): Lexer = ARMv7Lexer()

    override fun createParser(p0: Project?): PsiParser = ARMv7Parser()

    override fun getFileNodeType(): IFileElementType = ARMv7FileElementType

    override fun getCommentTokens(): TokenSet = ARMv7TokenSets.COMMENTS

    override fun getStringLiteralElements(): TokenSet = ARMv7TokenSets.STRINGS

    override fun createElement(node: ASTNode?): PsiElement = ARMv7TokenTypes.Factory.createElement(node)

    override fun createFile(viewProvider: FileViewProvider): PsiFile = ARMv7File(viewProvider)
}
