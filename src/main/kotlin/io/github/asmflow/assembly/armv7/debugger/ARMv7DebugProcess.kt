package io.github.asmflow.assembly.armv7.debugger

import com.intellij.xdebugger.XDebugProcess
import com.intellij.xdebugger.XDebugSession
import com.intellij.xdebugger.breakpoints.XBreakpointHandler
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider
import com.intellij.xdebugger.frame.XSuspendContext
import io.github.asmflow.assembly.armv7.debugger.breakpoints.ARMv7BreakpointHandler
import io.github.asmflow.assembly.armv7.debugger.evaluation.ARMv7DebugEditorsProvider

class ARMv7DebugProcess(debugSession: XDebugSession) : XDebugProcess(debugSession) {
    private val debugEditorsProvider = ARMv7DebugEditorsProvider()

    override fun getEditorsProvider(): XDebuggerEditorsProvider = debugEditorsProvider

    override fun getBreakpointHandlers(): Array<out XBreakpointHandler<*>?> = arrayOf(
        ARMv7BreakpointHandler()
    )

    override fun resume(context: XSuspendContext?) {}

    override fun startStepOver(context: XSuspendContext?) {}

    override fun startStepInto(context: XSuspendContext?) {}

    override fun stop() {}
}
