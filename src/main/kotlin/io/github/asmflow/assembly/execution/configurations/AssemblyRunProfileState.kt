package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ConsoleViewContentType
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.wm.ToolWindowManager
import com.intellij.psi.PsiManager
import io.github.asmflow.assembly.armv7.assembler.ARMv7Assembler
import io.github.asmflow.assembly.armv7.emulator.ARMv7Emulator
import io.github.asmflow.assembly.armv7.toolWindows.register.ARMv7RegisterViewToolWindowFactory
import io.github.asmflow.assembly.assembler.AssemblerError
import io.github.asmflow.assembly.assembler.AssemblerResult
import io.github.asmflow.assembly.execution.AssemblyExecutionResult
import io.github.asmflow.assembly.execution.process.AssemblyEmulatorProcessHandler
import io.github.asmflow.assembly.util.functional.toOption
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

        val toolWindow = ToolWindowManager.getInstance(environment.project).getToolWindow(
            ARMv7RegisterViewToolWindowFactory.Companion.toolWindowId
        ).toOption()
        toolWindow.isSomeThen {
            it.show()
        }

        val console = consoleBuilder.console
        console.print("Assembling ${scriptVirtualFile.name}...\n", ConsoleViewContentType.NORMAL_OUTPUT)

        val processHandler = AssemblyEmulatorProcessHandler {
            when (config.getEmulatorFlavour()) {
                AssemblyRunConfigurationOptions.EmulatorFlavour.ARMv7 -> {
                    val assembler = ARMv7Assembler(console)
                    val result = ApplicationManager.getApplication().runReadAction<AssemblerResult<List<Int>, List<AssemblerError>>> {
                        return@runReadAction assembler.assemble(listOf(psiFile))
                    }

                    if (!result.isErr()) {
                        val emulator = ARMv7Emulator(environment.project, result.unwrap())
                        while (emulator.inBounds() && !isProcessTerminating) {
                            emulator.forward()
                        }
                    }
                }
            }
        }
        console.attachToProcess(processHandler)
        processHandler.startNotify()

        return AssemblyExecutionResult(console, processHandler)
    }
}
