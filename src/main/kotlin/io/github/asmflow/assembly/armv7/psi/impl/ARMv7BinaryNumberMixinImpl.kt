package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7BinaryLiteral
import io.github.asmflow.assembly.util.removeFirstMatchingPrefix

abstract class ARMv7BinaryNumberMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7BinaryLiteral {
    override val value: Int
        get() = text.removeFirstMatchingPrefix(listOf("0b", "0B", "b", "B")).toInt(2)
}
