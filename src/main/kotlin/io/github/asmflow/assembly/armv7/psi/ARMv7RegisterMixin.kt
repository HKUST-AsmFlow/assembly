package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7Register

interface ARMv7RegisterMixin : PsiElement {
    val register: ARMv7Register
}
