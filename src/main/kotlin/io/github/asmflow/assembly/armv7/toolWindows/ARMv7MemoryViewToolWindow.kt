package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.JPanel
import javax.swing.JTable

class ARMv7MemoryViewToolWindow {
    // TODO: text.
    private val tableModel =
        ARMv7MemoryViewTableModel(ARMv7MemoryState(emptyList()))
    private val table = JBTable(tableModel)
    private val contentPanel: JPanel

    init {
        table.autoResizeMode = JTable.AUTO_RESIZE_LAST_COLUMN
        val scrollPane = JBScrollPane(table)

        contentPanel = panel {
            row {
                cell(scrollPane).align(Align.FILL)
            }.resizableRow()
        }

        contentPanel.border = JBUI.Borders.empty(JBUI.insets(5, 10))
    }

    fun getContent(): JPanel {
        return contentPanel
    }

    fun updateState(state: ARMv7MemoryState) {
        tableModel.updateMemoryData(state)
    }
}
