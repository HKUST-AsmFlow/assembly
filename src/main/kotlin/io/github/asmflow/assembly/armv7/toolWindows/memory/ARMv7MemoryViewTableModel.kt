package io.github.asmflow.assembly.armv7.toolWindows.memory

import io.github.asmflow.assembly.armv7.emulator.ARMv7AddressSpace
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import javax.swing.table.AbstractTableModel

class ARMv7MemoryViewTableModel(
    initialMemory: ARMv7MemoryState
) : AbstractTableModel() {
    val wordsPerRow = 8
    val rows = 16

    private var baseAddress = ARMv7AddressSpace.DATA_BASE.addr
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
                val address = (baseAddress + (rowIndex * wordsPerRow * 4).toUInt()).toLong()
                "0x%08X".format(address)
            }

            in 1..wordsPerRow -> {
                val rowAddress = (baseAddress + (rowIndex * wordsPerRow * 4).toUInt()).toLong()
                val address = rowAddress + ((columnIndex - 1) * 4).toLong()
                "0x%08X".format(memory.getWord(address.toUInt()).toLong())
            }

            else -> throw IllegalArgumentException("Unexpected column index: $columnIndex")
        }
    }

    override fun isCellEditable(rowIndex: Int, columnIndex: Int): Boolean = columnIndex > 0

    override fun setValueAt(aValue: Any?, rowIndex: Int, columnIndex: Int) {
        super.setValueAt(aValue, rowIndex, columnIndex)
    }

    fun canMovePage(by: Int): Boolean = when {
        by < 0 ->
            baseAddress > ARMv7AddressSpace.TEXT_BASE.addr

        by > 0 ->
            baseAddress < ARMv7AddressSpace.KERNEL_BASE.addr

        else ->
            false
    }

    fun movePage(by: Int) {
        when {
            by < 0 -> baseAddress -= 0x200u
            by > 0 -> baseAddress += 0x200u
        }

        fireTableDataChanged()
    }

    fun updateMemoryData(newMemoryState: ARMv7MemoryState) {
        memory = newMemoryState

        fireTableDataChanged()
    }

    fun setBaseAddress(addr: UInt) {
        baseAddress = addr
        fireTableDataChanged()
    }
}