package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7Sign

abstract class ARMv7SignMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Sign {
    override val multiplier: Int
        get() = if (textMatches("-")) -1 else 1
}
