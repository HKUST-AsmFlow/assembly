package io.github.asmflow.assembly.armv7.toolWindows

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.table.AbstractTableModel

class ARMv7MemoryViewTableModel(
    initialMemory: ARMv7MemoryState
) : AbstractTableModel() {
    val wordsPerRow = 8
    val rows = 256

    private var memory: ARMv7MemoryState = initialMemory

    override fun getColumnCount(): Int = wordsPerRow + 1

    override fun getRowCount(): Int = rows

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return when (columnIndex) {
            0 -> {
                val address = 0u + (rowIndex * wordsPerRow * 4).toUInt()
                "0x%08X".format(address)
            }

            in 1..wordsPerRow -> {
                val rowAddress = 0u + (rowIndex * wordsPerRow * 4).toUInt()
                val address = rowAddress + ((columnIndex - 1) * 4).toUInt()
                "0x%08X".format(memory.getWord(address))
            }

            else -> throw IllegalArgumentException("Unexpected column index: $columnIndex")
        }
    }

    fun updateMemoryData(newMemoryState: ARMv7MemoryState) {
        memory = newMemoryState

        fireTableDataChanged()
    }
}