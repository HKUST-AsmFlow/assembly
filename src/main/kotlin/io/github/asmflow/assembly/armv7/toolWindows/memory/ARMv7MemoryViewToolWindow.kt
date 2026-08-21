package io.github.asmflow.assembly.armv7.toolWindows.memory

import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.JComponent
import javax.swing.JPanel
import javax.swing.JTable

class ARMv7MemoryViewToolWindow(val tableModel: ARMv7MemoryViewTableModel) {
    private val table = JBTable(tableModel)
    private val contentPanel: JPanel

    init {
        table.autoResizeMode = JTable.AUTO_RESIZE_LAST_COLUMN
        val scrollPane = JBScrollPane(table)
        val toolbar = createToolbar()

        contentPanel = panel {
            row {
                cell(toolbar).align(Align.FILL)
            }

            row {
                cell(scrollPane).align(Align.FILL)
            }.resizableRow()
        }

        contentPanel.border = JBUI.Borders.empty(JBUI.insets(5, 10))
    }

    private fun createToolbar(): JComponent {
        val group = DefaultActionGroup().apply {
            add(ARMv7MemoryViewToolWindowPreviousMemoryPageAction(tableModel))
            add(ARMv7MemoryViewToolWindowNextMemoryPageAction(tableModel))

            addSeparator()

            add(ARMv7MemoryViewToolWindowStartingAddressDropdownAction(tableModel))
        }

        val toolbar = ActionManager.getInstance().createActionToolbar(
            "ARMv7MemoryViewToolbar",
            group,
            true
        )

        toolbar.targetComponent = table
        return toolbar.component
    }

    fun getContent(): JPanel {
        return contentPanel
    }

    fun updateState(state: ARMv7MemoryState) {
        tableModel.updateMemoryData(state)
    }
}
