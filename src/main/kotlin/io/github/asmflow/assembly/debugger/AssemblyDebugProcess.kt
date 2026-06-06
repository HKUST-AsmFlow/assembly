package io.github.asmflow.assembly.debugger

import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.breakpoints.XBreakpointHandler
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider
import com.intellij.xdebugger.frame.XSuspendContext
import io.github.asmflow.assembly.debugger.evaluation.AssemblyDebugEditorsProvider

class AssemblyDebugProcess(debugSession: XDebugSession) : XDebugProcess(debugSession) {
    override fun getEditorsProvider(): XDebuggerEditorsProvider = AssemblyDebugEditorsProvider()

    override fun getBreakpointHandlers(): Array<out XBreakpointHandler<*>?> = arrayOf()

    override fun resume(context: XSuspendContext?) {}

    override fun startStepOver(context: XSuspendContext?) {}

    override fun startStepInto(context: XSuspendContext?) {}

    override fun stop() {}
}
