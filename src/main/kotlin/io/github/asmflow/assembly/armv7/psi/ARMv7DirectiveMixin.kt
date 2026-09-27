package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.assembler.directives.ARMv7DirectiveParameter

interface ARMv7DirectiveMixin : PsiElement {
    val parameters: List<ARMv7DirectiveParameter>
}
