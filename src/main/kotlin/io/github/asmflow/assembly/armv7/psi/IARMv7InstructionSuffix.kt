package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode

interface IARMv7InstructionSuffix : PsiElement {
    fun conditionCode(): ARMv7InstructionConditionCode?

    fun setsFlags(): Boolean
}
