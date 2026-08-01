package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement

interface ARMv7SignMixin : PsiElement {
    val multiplier: Int
}
