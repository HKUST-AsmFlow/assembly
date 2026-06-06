package io.github.asmflow.assembly.execution.runners

import com.intellij.execution.configurations.RunProfile
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.configurations.RunnerSettings
import com.intellij.execution.executors.DefaultDebugExecutor
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.GenericProgramRunner
import com.intellij.execution.ui.RunContentDescriptor
import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugProcessStarter
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.XDebuggerManager
import io.github.asmflow.assembly.debugger.AssemblyDebugProcess
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfiguration

class AssemblyDebugProgramRunner : GenericProgramRunner<RunnerSettings>() {
    override fun canRun(executorId: String, profile: RunProfile): Boolean =
        DefaultDebugExecutor.EXECUTOR_ID == executorId && profile is AssemblyRunConfiguration

    override fun getRunnerId(): String = "AssemblyDebugProgramRunner"

    override fun doExecute(state: RunProfileState, environment: ExecutionEnvironment): RunContentDescriptor {
        val session = XDebuggerManager.getInstance(environment.project)
            .startSession(environment, object : XDebugProcessStarter() {
                override fun start(session: XDebugSession): XDebugProcess = AssemblyDebugProcess(session)
            })

        return session.runContentDescriptor
    }
}
