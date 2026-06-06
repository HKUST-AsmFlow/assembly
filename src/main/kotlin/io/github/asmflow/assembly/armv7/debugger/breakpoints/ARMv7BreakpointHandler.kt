package io.github.asmflow.assembly.armv7.debugger.breakpoints

import com.intellij.xdebugger.breakpoints.XBreakpointHandler
import com.intellij.xdebugger.breakpoints.XBreakpointProperties
import com.intellij.xdebugger.breakpoints.XLineBreakpoint

class ARMv7BreakpointHandler : XBreakpointHandler<XLineBreakpoint<XBreakpointProperties<*>>>(
    ARMv7BreakpointType::class.java
) {
    override fun registerBreakpoint(breakpoint: XLineBreakpoint<XBreakpointProperties<*>>) {}

    override fun unregisterBreakpoint(breakpoint: XLineBreakpoint<XBreakpointProperties<*>>, temporary: Boolean) {}
}
