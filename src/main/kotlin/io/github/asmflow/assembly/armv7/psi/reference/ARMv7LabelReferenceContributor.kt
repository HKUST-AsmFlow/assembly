package io.github.asmflow.assembly.armv7.psi.reference

import com.intellij.patterns.PlatformPatterns
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiReference
import com.intellij.psi.PsiReferenceContributor
import com.intellij.psi.PsiReferenceProvider
import com.intellij.psi.PsiReferenceRegistrar
import com.intellij.util.ProcessingContext
import io.github.asmflow.assembly.armv7.psi.ARMv7Label

class ARMv7LabelReferenceContributor : PsiReferenceContributor() {
    override fun registerReferenceProviders(registrar: PsiReferenceRegistrar) {
        registrar.registerReferenceProvider(
            PlatformPatterns.psiElement(ARMv7Label::class.java),
            object : PsiReferenceProvider() {
                override fun getReferencesByElement(
                    label: PsiElement,
                    context: ProcessingContext
                ): Array<out PsiReference?> {
                    return arrayOf(ARMv7LabelReference(label as ARMv7Label))
                }
            })
    }
}
