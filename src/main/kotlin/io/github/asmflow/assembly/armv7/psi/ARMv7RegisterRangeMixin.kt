package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.execution.ARMv7Register

interface ARMv7RegisterRangeMixin : PsiElement {
    val start: ARMv7Register
    val end: ARMv7Register

    val expanded: Set<ARMv7Register>
}
