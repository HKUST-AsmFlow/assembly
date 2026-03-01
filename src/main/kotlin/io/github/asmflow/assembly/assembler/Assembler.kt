package io.github.asmflow.assembly.assembler

import com.intellij.psi.PsiFile

interface Assembler {
    fun assemble(files: List<PsiFile>): AssemblerResult
}