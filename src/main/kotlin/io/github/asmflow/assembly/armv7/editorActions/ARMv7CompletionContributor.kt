package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns
import io.github.asmflow.assembly.armv7.ARMv7Language

class ARMv7CompletionContributor : CompletionContributor() {
    init {
        extend(
            CompletionType.BASIC,
            PlatformPatterns.psiElement().withLanguage(ARMv7Language),
            ARMv7ContextAwareCompletionProvider()
        )
    }
}
