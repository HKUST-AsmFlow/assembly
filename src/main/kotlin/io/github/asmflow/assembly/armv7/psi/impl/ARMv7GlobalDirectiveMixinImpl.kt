package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.IARMv7GlobalDirectiveMixin

open class ARMv7GlobalDirectiveMixinImpl(node: ASTNode) : IARMv7GlobalDirectiveMixin, ASTWrapperPsiElement(node) {
    override fun getLabel(): String = node.text.substringAfter(".global ")
}
