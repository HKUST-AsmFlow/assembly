package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import javax.swing.JPanel
import javax.swing.JTable

class ARMv7RegisterViewToolWindow {
    private val tableModel = ARMv7RegisterViewTableModel(ARMv7RegisterState())
    private val table = JBTable(tableModel)
    private val contentPanel: JPanel

    init {
        table.autoResizeMode = JTable.AUTO_RESIZE_LAST_COLUMN

        val columnModel = table.columnModel
        val firstColumn = columnModel.getColumn(0)
        firstColumn.preferredWidth = 150
        firstColumn.minWidth = 100
        firstColumn.maxWidth = 250

        val secondColumn = columnModel.getColumn(1)
        secondColumn.preferredWidth = 400

        val scrollPane = JBScrollPane(table)

        contentPanel = panel {
            row {
                cell(scrollPane).align(Align.FILL)
            }.resizableRow()
        }
    }

    fun getContent(): JPanel {
        return contentPanel
    }

    fun updateState(registers: ARMv7RegisterState) {
        tableModel.updateRegisterData(registers)
    }
}
