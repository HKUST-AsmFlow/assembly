package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Preindexed
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes

abstract class ARMv7PreindexedOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Preindexed {
    override val operand by lazy {
        ARMv7InstructionOperand.RegisterWithOffset(
            register.register,
            flexibleOffset?.offset ?: ARMv7InstructionOperand.Offset.ZERO,
            ARMv7InstructionOperand.AddressingFlags(
                preIndexed = true,
                add = flexibleOffset?.add ?: true,
                writeBack = node.findChildByType(ARMv7TokenTypes.BANG) != null
            )
        )
    }
}
