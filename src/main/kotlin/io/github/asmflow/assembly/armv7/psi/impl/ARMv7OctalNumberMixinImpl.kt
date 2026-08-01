package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7OctalLiteral
import io.github.asmflow.assembly.util.removeFirstMatchingPrefix

abstract class ARMv7OctalNumberMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7OctalLiteral {
    override val value: Int
        get() = text.removeFirstMatchingPrefix(listOf("0o", "0O", "o", "O")).toInt(8)
}
