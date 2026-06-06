package io.github.asmflow.assembly.armv7.debugger.breakpoints

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.xdebugger.breakpoints.XBreakpointProperties
import com.intellij.xdebugger.breakpoints.XLineBreakpointType
import io.github.asmflow.assembly.armv7.ARMv7FileType

class ARMv7BreakpointType : XLineBreakpointType<XBreakpointProperties<*>>(
    "ARMv7_LINE_BKPT",
    "ARMv7 Line Breakpoint"
) {
    override fun createBreakpointProperties(file: VirtualFile, line: Int): XBreakpointProperties<*>? = null

    override fun canPutAt(file: VirtualFile, line: Int, project: Project): Boolean =
        file.fileType is ARMv7FileType
}
