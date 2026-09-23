package io.github.asmflow.assembly.armv7.assembler

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.util.functional.Result

typealias AssemblerResult<T, E> = Result<T, E>

data class AssemblerError(
    val message: String,
    val element: PsiElement,
)
