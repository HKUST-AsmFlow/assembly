package io.github.asmflow.assembly.armv7.annotation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7TextAttributes
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction

class ARMv7MnemonicAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ARMv7Instruction -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.mnemonic)
                .textAttributes(ARMv7TextAttributes.ARMv7_INSTRUCTION)
                .create()
        }
    }
}
