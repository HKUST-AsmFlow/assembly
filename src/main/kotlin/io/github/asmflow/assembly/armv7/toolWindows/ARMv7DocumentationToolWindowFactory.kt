package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactory
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactoryCompanion

class ARMv7DocumentationToolWindowFactory : AssemblyToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.stripeTitle = "ARMv7 Docs"
    }

    object Companion : AssemblyToolWindowFactoryCompanion {
        override val toolWindowId = "ARMv7 Documentation"
    }
}
