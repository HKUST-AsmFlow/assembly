package io.github.asmflow.assembly.armv7.toolWindows.memory

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.table.AbstractTableModel

class ARMv7MemoryViewTableModel(
    initialMemory: ARMv7MemoryState
) : AbstractTableModel() {
    val wordsPerRow = 8
    val rows = 16

    private var memory: ARMv7MemoryState = initialMemory

    override fun getColumnCount(): Int = wordsPerRow + 1

    override fun getColumnName(column: Int): String? =
        when (column) {
            0 -> "Address"

            in 1..wordsPerRow ->
                "+%02X".format((column - 1) * 4)

            else -> null
        }

    override fun getRowCount(): Int = rows

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any {
        return when (columnIndex) {
            0 -> {
                val address = 0L + (rowIndex * wordsPerRow * 4).toLong()
                "0x%08X".format(address)
            }

            in 1..wordsPerRow -> {
                val rowAddress = 0L + (rowIndex * wordsPerRow * 4).toLong()
                val address = rowAddress + ((columnIndex - 1) * 4).toLong()
                "0x%08X".format(memory.getWord(address.toUInt()).toLong())
            }

            else -> throw IllegalArgumentException("Unexpected column index: $columnIndex")
        }
    }

    fun canMovePage(by: Int): Boolean = TODO()

    fun movePage(by: Int) {
        TODO()
    }

    fun updateMemoryData(newMemoryState: ARMv7MemoryState) {
        memory = newMemoryState

        fireTableDataChanged()
    }
}