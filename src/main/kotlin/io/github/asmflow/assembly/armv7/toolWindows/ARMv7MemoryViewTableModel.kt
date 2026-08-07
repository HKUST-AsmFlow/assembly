package io.github.asmflow.assembly.armv7.toolWindows

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.table.AbstractTableModel

class ARMv7MemoryViewTableModel(
    initialMemory: ARMv7MemoryState
) : AbstractTableModel() {
    private var memory: ARMv7MemoryState = initialMemory


    override fun getColumnCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getRowCount(): Int {
        TODO("Not yet implemented")
    }

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        TODO("Not yet implemented")
    }

    fun updateMemoryData(newMemoryState: ARMv7MemoryState) {
        memory = newMemoryState

        fireTableDataChanged()
    }
}