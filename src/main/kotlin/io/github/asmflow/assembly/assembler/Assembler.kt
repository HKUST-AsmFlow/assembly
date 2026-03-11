package io.github.asmflow.assembly.assembler

import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.psi.PsiFile

abstract class Assembler(val console: ConsoleView) {
    fun debug(message: String) {
        console.print(message, ConsoleViewContentType.LOG_DEBUG_OUTPUT)
    }

    abstract fun assemble(files: List<PsiFile>): AssemblerResult<List<AssembledInstruction>, List<AssemblerError>>
}
