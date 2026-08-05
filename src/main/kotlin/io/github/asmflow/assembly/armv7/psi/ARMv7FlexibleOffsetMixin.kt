package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand

interface ARMv7FlexibleOffsetMixin : PsiElement {
    val add: Boolean
    val offset: ARMv7InstructionOperand.Offset
}
