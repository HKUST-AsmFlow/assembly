package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactory
import io.github.asmflow.assembly.openapi.wm.AssemblyToolWindowFactoryCompanion
import io.github.asmflow.assembly.util.messages.EmulatorStateNotifier

class ARMv7RegisterViewToolWindowFactory : AssemblyToolWindowFactory {
    override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
        toolWindow.setStripeShortTitleProvider { "ARMv7 Registers" }
        project.messageBus.connect().subscribe(EmulatorStateNotifier.EMULATOR_STATE_TOPIC, object : EmulatorStateNotifier {
            override fun onRegisterStateChanged(registerState: ARMv7RegisterState) {
                val content = toolWindow.contentManager.factory.createContent(
                    ARMv7RegisterViewToolWindow.makeContent(registerState),
                    null,
                    false
                )
                toolWindow.contentManager.addContent(content)
            }
        })
    }

    object Companion : AssemblyToolWindowFactoryCompanion {
        override val toolWindowId = "ARMv7 Register View"
    }
}
