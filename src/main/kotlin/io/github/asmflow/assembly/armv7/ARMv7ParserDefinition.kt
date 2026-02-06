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
import io.github.asmflow.assembly.lang.AssemblyParserDefinition

class ARMv7ParserDefinition : AssemblyParserDefinition() {
    override fun createLexer(p0: Project?): Lexer = ARMv7Lexer()

    override fun createParser(p0: Project?): PsiParser = ARMv7Parser()

    override fun getFileNodeType(): IFileElementType {
        TODO("Not yet implemented")
    }

    override fun getCommentTokens(): TokenSet {
        TODO("Not yet implemented")
    }

    override fun getStringLiteralElements(): TokenSet {
        TODO("Not yet implemented")
    }

    override fun createElement(p0: ASTNode?): PsiElement {
        TODO("Not yet implemented")
    }

    override fun createFile(p0: FileViewProvider): PsiFile {
        TODO("Not yet implemented")
    }

}
