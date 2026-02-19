package io.github.asmflow.assembly.execution

import com.intellij.execution.ExecutionResult
import com.intellij.execution.process.ProcessHandler
import com.intellij.execution.ui.ConsoleView
import com.intellij.execution.ui.ExecutionConsole
import com.intellij.openapi.actionSystem.AnAction

class AssemblyExecutionResult(val console: ConsoleView, val proc: ProcessHandler) : ExecutionResult {
    override fun getActions(): Array<out AnAction> = emptyArray()

    override fun getExecutionConsole(): ExecutionConsole = console

    override fun getProcessHandler(): ProcessHandler = proc
}
