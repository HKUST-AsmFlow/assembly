package io.github.asmflow.assembly.armv7.editorActions

import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes

class ARMv7QuoteHandler : SimpleTokenSetQuoteHandler(ARMv7TokenTypes.STRING)
