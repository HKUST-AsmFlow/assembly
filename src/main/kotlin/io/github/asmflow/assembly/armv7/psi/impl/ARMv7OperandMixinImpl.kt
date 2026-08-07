package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.util.unreachable

abstract class ARMv7OperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Operand {
    override val operand: ARMv7InstructionOperand
        get() = when {
            label != null -> ARMv7InstructionOperand.Label(label = label!!.text)
            literalLoad != null -> literalLoad!!.operand
            number != null -> number!!.operand
            postindexed != null -> postindexed!!.operand
            preindexed != null -> preindexed!!.operand
            registerList != null -> registerList!!.operand
            registerWithShift != null -> registerWithShift!!.operand

            else -> unreachable()
        }
}
