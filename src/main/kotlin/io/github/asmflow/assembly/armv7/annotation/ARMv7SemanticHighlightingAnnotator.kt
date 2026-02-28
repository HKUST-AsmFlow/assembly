package io.github.asmflow.assembly.armv7.annotation

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7TextAttributes
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.armv7.psi.ARMv7Label
import io.github.asmflow.assembly.armv7.psi.ARMv7Number
import io.github.asmflow.assembly.armv7.psi.ARMv7ShiftType

class ARMv7SemanticHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ARMv7Directive -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.directiveName)
                .textAttributes(ARMv7TextAttributes.ARMv7_DIRECTIVE)
                .create()
            is ARMv7Instruction -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.mnemonic)
                .textAttributes(ARMv7TextAttributes.ARMv7_INSTRUCTION)
                .create()
            is ARMv7Label -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(ARMv7TextAttributes.ARMv7_LABEL)
                .create()
            is ARMv7Number -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(ARMv7TextAttributes.ARMv7_NUMBER)
                .create()
            is ARMv7ShiftType -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element)
                .textAttributes(ARMv7TextAttributes.ARMv7_SHIFT_TYPE)
                .create()
        }
    }
}
