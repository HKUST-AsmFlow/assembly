package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode

interface ARMv7InstructionMixin : PsiElement {
    val baseMnemonic: String
    val setsFlags: Boolean
    val conditionCode: ARMv7InstructionConditionCode
}
