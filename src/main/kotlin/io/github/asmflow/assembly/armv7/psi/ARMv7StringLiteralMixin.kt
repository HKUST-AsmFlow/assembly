package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement

interface ARMv7StringLiteralMixin : PsiElement {
    val value: String
}
