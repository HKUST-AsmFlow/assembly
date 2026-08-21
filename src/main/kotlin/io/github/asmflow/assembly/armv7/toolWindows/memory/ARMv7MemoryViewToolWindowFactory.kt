package io.github.asmflow.assembly.armv7.toolWindows.memory

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactory
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactoryCompanion
import io.github.asmflow.assembly.util.messages.EmulatorStateListener

class ARMv7MemoryViewToolWindowFactory : AssemblyToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Memory" }

        val memoryComponentManager = ARMv7MemoryViewToolWindow(ARMv7MemoryViewTableModel(ARMv7MemoryState(listOf())))
        val content = toolWindow.contentManager.factory.createContent(
            memoryComponentManager.getContent(),
            null,
            false
        )
        toolWindow.contentManager.addContent(content)

        project.messageBus.connect()
            .subscribe(EmulatorStateListener.EMULATOR_STATE_TOPIC, object : EmulatorStateListener {
                override fun onMemoryStateChanged(memoryState: ARMv7MemoryState) {
                    memoryComponentManager.updateState(memoryState)
                }
            })
    }

    object Companion : AssemblyToolWindowFactoryCompanion {
        override val toolWindowId = "ARMv7 Memory"
    }
}
