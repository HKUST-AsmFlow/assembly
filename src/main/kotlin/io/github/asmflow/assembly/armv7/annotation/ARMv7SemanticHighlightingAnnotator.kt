package io.github.asmflow.assembly.armv7.annotation

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7TextAttributes
import io.github.asmflow.assembly.armv7.psi.*

class ARMv7SemanticHighlightingAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ARMv7Directive -> holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(element.directiveName)
                .textAttributes(ARMv7TextAttributes.ARMv7_DIRECTIVE)
                .create()

            is ARMv7Instruction -> if (ARMv7InstructionDatabase.any(element)) {
                holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                    .range(element.mnemonic)
                    .textAttributes(ARMv7TextAttributes.ARMv7_INSTRUCTION)
                    .create()
            } else {
                holder.newAnnotation(HighlightSeverity.ERROR, "Unknown instruction ${element.mnemonic}")
                    .range(element)
                    .highlightType(ProblemHighlightType.ERROR)
                    .create()
            }

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
