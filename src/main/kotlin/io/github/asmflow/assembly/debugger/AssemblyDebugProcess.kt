package io.github.asmflow.assembly.debugger

import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider

class AssemblyDebugProcess(debugSession: XDebugSession) : XDebugProcess(debugSession) {
    override fun getEditorsProvider(): XDebuggerEditorsProvider = TODO("Not yet implemented")
}
