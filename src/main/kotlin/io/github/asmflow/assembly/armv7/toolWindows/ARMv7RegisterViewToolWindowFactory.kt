package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactory
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactoryCompanion

class ARMv7RegisterViewToolWindowFactory : AssemblyToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Registers" }

        val content = toolWindow.contentManager.factory.createContent(
            ARMv7RegisterViewToolWindow.makeContent(),
            null,
            false
        )
        toolWindow.contentManager.addContent(content)
    }

    object Companion : AssemblyToolWindowFactoryCompanion {
        override val toolWindowId = "ARMv7 Register View"
    }
}
