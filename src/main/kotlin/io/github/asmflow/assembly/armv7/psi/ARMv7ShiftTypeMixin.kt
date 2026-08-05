package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType

interface ARMv7ShiftTypeMixin : PsiElement {
    val shiftType: ARMv7ShiftType
}
