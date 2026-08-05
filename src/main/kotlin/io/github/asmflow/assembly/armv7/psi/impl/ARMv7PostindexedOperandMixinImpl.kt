package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Postindexed

abstract class ARMv7PostindexedOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Postindexed {
    override val operand: ARMv7InstructionOperand
        get() = ARMv7InstructionOperand.RegisterWithOffset(
            register.register,
            flexibleOffset.offset,
            ARMv7InstructionOperand.AddressingFlags(
                preIndexed = false,
                add = flexibleOffset.add,
                writeBack = true,
            )
        )
}
