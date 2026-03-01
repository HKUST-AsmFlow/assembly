package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.process.NopProcessHandler
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.psi.PsiManager
import io.github.asmflow.assembly.armv7.ARMv7File
import io.github.asmflow.assembly.armv7.assembler.ARMv7Assembler
import io.github.asmflow.assembly.execution.AssemblyExecutionResult
import java.nio.file.Paths

class AssemblyRunProfileState(
    private val environment: ExecutionEnvironment,
    private val config: AssemblyRunConfiguration
) : RunProfileState {
    val consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(environment.project)

    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        val path = config.getScriptPath()
        val scriptVirtualFile = VirtualFileManager.getInstance().findFileByNioPath(Paths.get(path!!))!!
        val psiFile = PsiManager.getInstance(environment.project).findFile(scriptVirtualFile) ?: throw RuntimeException(
            "Cannot find PSI file"
        )

        val console = consoleBuilder.console
        console.print("Assembling ${scriptVirtualFile.name}...", ConsoleViewContentType.NORMAL_OUTPUT)

        when (config.getEmulatorFlavour()) {
            AssemblyRunConfigurationOptions.EmulatorFlavour.ARMv7 -> {
                val assembler = ARMv7Assembler()
                assembler.assemble(listOf(psiFile))
            }
        }

        val processHandler = NopProcessHandler()
        processHandler.startNotify()
        processHandler.destroyProcess()

        return AssemblyExecutionResult(console, processHandler)
    }
}
