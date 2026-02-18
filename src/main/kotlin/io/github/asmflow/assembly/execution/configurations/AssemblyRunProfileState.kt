package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import io.github.asmflow.assembly.armv7.assembler.Armv7Assembler
import io.github.asmflow.assembly.execution.AssemblyExecutionResult

class AssemblyRunProfileState(private val environment: ExecutionEnvironment, private val config: AssemblyRunConfiguration) : RunProfileState {
    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        println("DEBUG: Run file")
        val path = config.getScriptPath()
        println(path)
        when(config.getEmulatorFlavour()){
            AssemblyRunConfigurationOptions.EmulatorFlavour.ARMv7 -> {
                // val assembler = Armv7Assembler(file = file)
                // assembler.assemble()
            }
        }
        return AssemblyExecutionResult()
    }
}
