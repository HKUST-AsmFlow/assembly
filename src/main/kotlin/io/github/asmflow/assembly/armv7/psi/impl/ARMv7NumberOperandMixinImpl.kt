package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Number
import io.github.asmflow.assembly.armv7.psi.ARMv7NumberMixin

abstract class ARMv7NumberOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Number {
    override val operand by lazy {
        ARMv7InstructionOperand.Number((sign?.multiplier ?: 1) * literal.value)
    }

    private val literal = children.filterIsInstance<ARMv7NumberMixin>().single()
}
