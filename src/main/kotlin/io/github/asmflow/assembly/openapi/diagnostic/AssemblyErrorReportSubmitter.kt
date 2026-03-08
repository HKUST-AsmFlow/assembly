package io.github.asmflow.assembly.openapi.diagnostic

import com.intellij.openapi.diagnostic.ErrorReportSubmitter
import com.intellij.openapi.diagnostic.IdeaLoggingEvent
import com.intellij.openapi.diagnostic.SubmittedReportInfo
import com.intellij.openapi.util.NlsActions
import com.intellij.util.Consumer
import io.github.asmflow.assembly.util.fail
import java.awt.Component

class AssemblyErrorReportSubmitter : ErrorReportSubmitter() {
    override fun getReportActionText(): @NlsActions.ActionText String = "Create AsmFlow Bug Report"

    override fun submit(
        events: Array<out IdeaLoggingEvent>,
        additionalInfo: String?,
        parentComponent: Component,
        consumer: Consumer<in SubmittedReportInfo>
    ): Boolean {
        if (events.isEmpty()) {
            consumer.fail()
            return false
        }

        TODO()
    }
}
