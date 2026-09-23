package io.github.asmflow.assembly.armv7.toolWindows.register

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.EmulatorStateNotifier

class ARMv7RegisterViewToolWindowFactory : ToolWindowFactory {
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
            .subscribe(EmulatorStateNotifier.EMULATOR_STATE_TOPIC, object : EmulatorStateNotifier {
                override fun onRegisterStateChanged(registerState: ARMv7RegisterState) {
                    registerComponentManager.updateState(registerState)
                }
            })
    }

    companion object {
        const val TOOL_WINDOW_ID = "ARMv7 Register View"
    }
}
