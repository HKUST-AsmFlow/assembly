package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionOperandMixin

open class ARMv7InstructionOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7InstructionOperandMixin {

}
