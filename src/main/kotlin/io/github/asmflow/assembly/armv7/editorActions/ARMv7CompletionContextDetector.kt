package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.editorActions.AssemblyCompletionContext
import io.github.asmflow.assembly.editorActions.AssemblyCompletionContextDetector

object ARMv7CompletionContextDetector : AssemblyCompletionContextDetector() {
    override fun detectContext(position: PsiElement): AssemblyCompletionContext {
        TODO("Not yet implemented")
    }
}
