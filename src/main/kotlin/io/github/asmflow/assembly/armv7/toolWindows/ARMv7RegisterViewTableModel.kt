package io.github.asmflow.assembly.armv7.toolWindows

import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import javax.swing.table.AbstractTableModel

class ARMv7RegisterViewTableModel(val registers: ARMv7RegisterState) : AbstractTableModel() {
    override fun getColumnCount(): Int = 2

    override fun getRowCount(): Int = 16

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any =
        when (columnIndex) {
            0 -> "R$rowIndex"
            1 -> registers.get(rowIndex)
            else -> throw IllegalArgumentException("Unexpected column index: $columnIndex")
        }
}
