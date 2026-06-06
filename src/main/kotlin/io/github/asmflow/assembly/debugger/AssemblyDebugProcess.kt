package io.github.asmflow.assembly.debugger

import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.breakpoints.XBreakpointHandler
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider
import com.intellij.xdebugger.frame.XSuspendContext

class AssemblyDebugProcess(debugSession: XDebugSession) : XDebugProcess(debugSession) {
    override fun getEditorsProvider(): XDebuggerEditorsProvider = TODO("Not yet implemented")

    override fun getBreakpointHandlers(): Array<out XBreakpointHandler<*>?> = arrayOf()

    override fun resume(context: XSuspendContext?) {}

    override fun startStepOver(context: XSuspendContext?) {}

    override fun startStepInto(context: XSuspendContext?) {}

    override fun stop() {}
}
