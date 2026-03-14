package io.github.asmflow.assembly.armv7.annotation

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.lang.annotation.HighlightSeverity
import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7TextAttributes
import io.github.asmflow.assembly.armv7.psi.*

class ARMv7InstructionAnnotator : Annotator {
    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is ARMv7Instruction -> validateAndHighlight(element, holder)
        }
    }

    private fun validateAndHighlight(instruction: ARMv7Instruction, holder: AnnotationHolder) {
        val mnemonic = instruction.mnemonic
        val text = mnemonic.text.trim()
        val valid = ARMv7InstructionDatabase.get(instruction.baseMnemonic).isSome()

        if (valid) {
            holder.newSilentAnnotation(HighlightSeverity.INFORMATION)
                .range(mnemonic)
                .textAttributes(ARMv7TextAttributes.ARMv7_INSTRUCTION)
                .create()
        } else {
            holder.newAnnotation(HighlightSeverity.ERROR, "Unknown instruction $text")
                .range(mnemonic)
                .create()
        }
    }
}
