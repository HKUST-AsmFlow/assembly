package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7StringLiteral

fun String.unquote(): String {
    require(length >= 2 && first() == '"' && last() == '"') {
        "malformed string literal: $this"
    }
    return substring(1, length - 1)
}

abstract class ARMv7StringLiteralMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7StringLiteral {
    override val value: String
        get() = text.unquote()
}
