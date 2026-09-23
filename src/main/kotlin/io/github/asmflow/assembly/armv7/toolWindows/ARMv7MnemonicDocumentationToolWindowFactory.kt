package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory

class ARMv7MnemonicDocumentationToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Mnemonics" }

        val content = toolWindow.contentManager.factory.createContent(
            ARMv7DocumentationToolWindow.makeContent(),
            null,
            false
        )
        toolWindow.contentManager.addContent(content)
    }

    companion object {
        const val TOOL_WINDOW_ID = "ARMv7 Mnemonics Documentation"
    }
}
