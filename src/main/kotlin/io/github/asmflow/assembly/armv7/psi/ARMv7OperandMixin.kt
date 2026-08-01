package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand

interface ARMv7OperandMixin : PsiElement {
    val operand: ARMv7InstructionOperand
}
