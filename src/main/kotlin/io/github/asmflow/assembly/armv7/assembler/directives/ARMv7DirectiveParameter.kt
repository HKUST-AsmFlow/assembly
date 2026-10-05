package io.github.asmflow.assembly.armv7.assembler.directives

import com.intellij.psi.PsiElement

sealed class ARMv7DirectiveParameter(val element: PsiElement) {
    data class Number(val value: Int, val elem: PsiElement) : ARMv7DirectiveParameter(elem)
    data class StringLit(val value: String, val elem: PsiElement) : ARMv7DirectiveParameter(elem)
    data class Label(val value: String, val elem: PsiElement) : ARMv7DirectiveParameter(elem)
}
