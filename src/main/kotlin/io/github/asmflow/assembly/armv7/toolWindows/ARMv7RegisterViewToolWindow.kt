package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.ui.dsl.builder.panel
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import javax.swing.JPanel
import javax.swing.JTable

object ARMv7RegisterViewToolWindow {
    fun makeContent(registers: ARMv7RegisterState): JPanel = panel {
        row {
            cell(JTable(ARMv7RegisterViewTableModel(registers)))
        }
    }
}
