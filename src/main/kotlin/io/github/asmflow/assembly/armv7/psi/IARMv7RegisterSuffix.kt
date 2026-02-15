package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement

interface IARMv7RegisterSuffix : PsiElement {
    fun writeBack(): Boolean
}
