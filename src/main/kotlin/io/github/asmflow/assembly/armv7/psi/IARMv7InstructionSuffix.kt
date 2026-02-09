package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.emulation.ARMv7InstructionConditionCode
import org.jetbrains.annotations.Nullable

interface IARMv7InstructionSuffix : PsiElement {
    fun conditionCode(): @Nullable ARMv7InstructionConditionCode

    fun setsFlags(): Boolean
}
