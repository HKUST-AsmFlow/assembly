package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7RegisterWithShift
import io.github.asmflow.assembly.util.functional.toOption

abstract class ARMv7RegisterWithShiftOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7RegisterWithShift {
    override val operand: ARMv7InstructionOperand by lazy {
        ARMv7InstructionOperand.Register(register.register, shift?.shift.toOption())
    }
}
