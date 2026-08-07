package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7LiteralLoad
import io.github.asmflow.assembly.armv7.psi.ARMv7NumberMixin

abstract class ARMv7LiteralLoadOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7LiteralLoad {
    override val operand =
        ARMv7InstructionOperand.LiteralLoad(children.filterIsInstance<ARMv7NumberMixin>().single().value)
}
