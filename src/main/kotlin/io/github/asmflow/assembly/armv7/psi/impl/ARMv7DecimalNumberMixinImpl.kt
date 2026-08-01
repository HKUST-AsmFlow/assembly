package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7DecimalLiteral

abstract class ARMv7DecimalNumberMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7DecimalLiteral {
    override val value: Int = text.toInt(10)
}
