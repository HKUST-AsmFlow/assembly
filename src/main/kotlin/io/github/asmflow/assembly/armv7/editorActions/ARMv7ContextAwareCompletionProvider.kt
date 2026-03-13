package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.icons.AllIcons
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
            resultSet.addAllElements(
                ARMv7InstructionDatabase.allInstructions()
                    .map {
                        var builder = LookupElementBuilder.create(it.mnemonic)
                            .withIcon(AllIcons.Nodes.Mnemonic)
                            .withTypeText(it.details.shortDescription, true)

                        val instructionSuffixes = buildString {
                            if (it.supportsFlags)
                                append("{s}")

                            if (it.supportsConditionCodes)
                                append("<c>")
                        }

                        if (instructionSuffixes.isNotEmpty())
                            builder = builder.withTailText(instructionSuffixes, true)

                        builder.bold()
                    })
        }
    }
}
