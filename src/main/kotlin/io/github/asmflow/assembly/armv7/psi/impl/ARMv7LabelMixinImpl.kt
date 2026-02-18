package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.IARMv7LabelMixin

open class ARMv7LabelMixinImpl(node: ASTNode) : IARMv7LabelMixin, ASTWrapperPsiElement(node) {
    override fun getLabelName(): String = node.text.dropLast(1)
}
