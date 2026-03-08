package io.github.asmflow.assembly.util

import com.intellij.openapi.diagnostic.SubmittedReportInfo
import com.intellij.util.Consumer

fun Consumer<in SubmittedReportInfo>.fail() =
    consume(SubmittedReportInfo(null, null, SubmittedReportInfo.SubmissionStatus.FAILED))
