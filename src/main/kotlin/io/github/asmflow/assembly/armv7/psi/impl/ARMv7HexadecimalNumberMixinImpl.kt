package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7HexadecimalLiteral
import io.github.asmflow.assembly.util.removeFirstMatchingPrefix

abstract class ARMv7HexadecimalNumberMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7HexadecimalLiteral {
    override val value: Int
        get() = text.removeFirstMatchingPrefix(listOf("0x", "0X", "x", "X")).toInt(16)
}
