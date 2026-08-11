package io.github.asmflow.assembly.armv7.toolWindows.memory

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.Presentation
import com.intellij.openapi.actionSystem.ex.CustomComponentAction
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.dsl.builder.panel
import io.github.asmflow.assembly.armv7.emulator.ARMv7AddressSpace
import javax.swing.JComponent

class ARMv7MemoryViewToolWindowStartingAddressDropdownAction(model: ARMv7MemoryViewTableModel) : AnAction(), CustomComponentAction {
    val comboBox = ComboBox(ARMv7AddressSpace.entries.toTypedArray())

    init {
        comboBox.addActionListener {
            val location = comboBox.selectedItem as? ARMv7AddressSpace ?: return@addActionListener
            model.setBaseAddress(location.addr)
        }
    }

    override fun actionPerformed(p0: AnActionEvent) {}

    override fun createCustomComponent(presentation: Presentation, place: String): JComponent = panel {
        row("  Starting address: ") { cell(comboBox) }
    }

    override fun getActionUpdateThread() = ActionUpdateThread.EDT
}