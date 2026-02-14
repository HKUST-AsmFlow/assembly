package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ProgramRunner
import io.github.asmflow.assembly.execution.AssemblyExecutionResult

class AssemblyRunProfileState : RunProfileState {
    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult = AssemblyExecutionResult()
}
