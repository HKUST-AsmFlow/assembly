package io.github.asmflow.assembly.editorActions

import com.intellij.psi.PsiElement

abstract class AssemblyCompletionContextDetector {
    abstract fun detectContext(position: PsiElement): AssemblyCompletionContext
}
