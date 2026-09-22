package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7RegisterWithWriteback
import io.github.asmflow.assembly.util.functional.None

abstract class ARMv7RegisterWithWritebackOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7RegisterWithWriteback {
    override val operand: ARMv7InstructionOperand
        get() = ARMv7InstructionOperand.Register(register.register, None, writeBack = true)
}
