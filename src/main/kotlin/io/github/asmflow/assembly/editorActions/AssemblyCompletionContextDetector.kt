package io.github.asmflow.assembly.editorActions

import com.intellij.psi.PsiElement

abstract class AssemblyCompletionContextDetector {
    data class Rule(
        val context: AssemblyCompletionContext,
        val precedence: Int,
        val predicate: (PsiElement, String, Int) -> Boolean
    )

    abstract val contextRules: List<Rule>

    abstract fun detectContext(position: PsiElement): AssemblyCompletionContext
}
