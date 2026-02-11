package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.IARMv7RegisterSuffix

open class ARMv7RegisterSuffixImpl(node: ASTNode) : IARMv7RegisterSuffix, ASTWrapperPsiElement(node) {
    override fun inplaceWrite(): Boolean = false
}
