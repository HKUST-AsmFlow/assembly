package io.github.asmflow.assembly.armv7.toolWindows

import com.intellij.openapi.observable.properties.PropertyGraph
import com.intellij.ui.components.JBScrollPane
import com.intellij.ui.dsl.builder.Align
import com.intellij.ui.dsl.builder.panel
import com.intellij.ui.table.JBTable
import com.intellij.util.ui.JBUI
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import javax.swing.JPanel
import javax.swing.JTable

class ARMv7RegisterViewToolWindow {
    private val numberRepresentationProperty = PropertyGraph().property(NumberRepresentation.Hexadecimal)
    private var repr by numberRepresentationProperty
    private val tableModel =
        ARMv7RegisterViewTableModel(ARMv7RegisterState(), repr)
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
            row("Number representation: ") {
                segmentedButton(NumberRepresentation.entries) { text = it.toString() }.align(Align.FILL)
                    .bind(numberRepresentationProperty)
            }

            row {
                cell(scrollPane).align(Align.FILL)
            }.resizableRow()
        }

        contentPanel.border = JBUI.Borders.empty(JBUI.insets(5, 10))

        numberRepresentationProperty.afterChange {
            tableModel.setNumberRepresentation(it)
        }
    }

    fun getContent(): JPanel {
        return contentPanel
    }

    fun updateState(registers: ARMv7RegisterState) {
        tableModel.updateRegisterData(registers)
    }

    enum class NumberRepresentation {
        Decimal, Hexadecimal
    }
}
