package io.github.asmflow.assembly.armv7.toolWindows.memory

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.armv7.emulator.EmulatorStateNotifier

class ARMv7MemoryViewToolWindowFactory : ToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Memory" }

        val memoryComponentManager = ARMv7MemoryViewToolWindow()
        val content = toolWindow.contentManager.factory.createContent(
            memoryComponentManager.getContent(),
            null,
            false
        )
        toolWindow.contentManager.addContent(content)

        project.messageBus.connect()
            .subscribe(EmulatorStateNotifier.EMULATOR_STATE_TOPIC, object : EmulatorStateNotifier {
                override fun onMemoryStateChanged(memoryState: ARMv7MemoryState) {
                    memoryComponentManager.updateState(memoryState)
                }
            })
    }

    companion object {
        const val TOOL_WINDOW_ID = "ARMv7 Memory"
    }
}
