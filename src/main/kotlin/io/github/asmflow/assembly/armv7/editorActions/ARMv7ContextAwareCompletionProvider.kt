package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase

class ARMv7ContextAwareCompletionProvider : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters,
        context: ProcessingContext,
        resultSet: CompletionResultSet
    ) {
        val context = ARMv7CompletionContextDetector.detectContext(parameters.position)

        if (context.inInstruction) {
            resultSet.addAllElements(ARMv7InstructionDatabase.allInstructions()
                .map { LookupElementBuilder.create(it.mnemonic) })
        }
    }
}
