package io.github.asmflow.assembly.assembler

import com.intellij.psi.PsiElement

sealed class AssemblerResult {
    data class Success(
        val output: List<AssembledInstruction>
    ): AssemblerResult()
    data class Failure(
        val errors: List<AssemblerError>
    ): AssemblerResult()
}

data class AssemblerError(
    val message: String,
    val element: PsiElement,
)

