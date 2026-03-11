package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.editorActions.AssemblyCompletionContext
import io.github.asmflow.assembly.editorActions.AssemblyCompletionContextDetector
import io.github.asmflow.assembly.util.functional.toOption

object ARMv7CompletionContextDetector : AssemblyCompletionContextDetector() {
    override val contextRules = emptyList<Rule>()

    override fun detectContext(position: PsiElement): AssemblyCompletionContext {
        val file = position.containingFile ?: return AssemblyCompletionContext.General
        val text = file.text
        val offset = position.textOffset

        return contextRules.sortedByDescending { it.precedence }
            .firstOrNull { it.predicate(position, text, offset) }
            .toOption()
            .map { it.context }
            .unwrapOr(AssemblyCompletionContext.General)
    }
}
