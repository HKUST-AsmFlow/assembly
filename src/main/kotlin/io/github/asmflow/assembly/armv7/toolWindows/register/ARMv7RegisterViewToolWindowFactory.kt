package io.github.asmflow.assembly.armv7.toolWindows.register

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactory
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactoryCompanion
import io.github.asmflow.assembly.util.messages.EmulatorStateListener

class ARMv7RegisterViewToolWindowFactory : AssemblyToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Registers" }

        val registerComponentManager = ARMv7RegisterViewToolWindow()
        val content = toolWindow.contentManager.factory.createContent(
            registerComponentManager.getContent(),
            null,
            false
        )
        toolWindow.contentManager.addContent(content)

        project.messageBus.connect()
            .subscribe(EmulatorStateListener.EMULATOR_STATE_TOPIC, object : EmulatorStateListener {
                override fun onRegisterStateChanged(registerState: ARMv7RegisterState) {
                    registerComponentManager.updateState(registerState)
                }
            })
    }

    object Companion : AssemblyToolWindowFactoryCompanion {
        override val toolWindowId = "ARMv7 Register View"
    }
}
