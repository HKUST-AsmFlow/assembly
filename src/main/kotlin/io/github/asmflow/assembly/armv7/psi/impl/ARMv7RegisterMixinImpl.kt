package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7Register as PsiARMv7Register

abstract class ARMv7RegisterMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), PsiARMv7Register {
    override val register: ARMv7Register
        get() = ARMv7Register.entries.firstOrNull { text.equals(it.name, true) } ?: error("Unknown register: $text")
}
