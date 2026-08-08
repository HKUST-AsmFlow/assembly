package io.github.asmflow.assembly.armv7.toolWindows

import io.github.asmflow.assembly.armv7.assembler.ARMv7DataProcessingEncoder.toInt
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import javax.swing.table.AbstractTableModel

class ARMv7RegisterViewTableModel(
    initialRegisters: ARMv7RegisterState,
    var base: ARMv7ViewNumberRepresentation
) : AbstractTableModel() {
    private var registers: ARMv7RegisterState = initialRegisters

    fun setNumberRepresentation(repr: ARMv7ViewNumberRepresentation) {
        if (base == repr) return

        base = repr
        fireTableDataChanged()
    }

    override fun getColumnName(column: Int): String? = when (column) {
        0 -> "Register"
        1 -> "Value"
        else -> null
    }

    override fun getColumnCount(): Int = 2

    override fun getRowCount(): Int = 20

    override fun getValueAt(rowIndex: Int, columnIndex: Int): Any =
        when (columnIndex) {
            0 -> when (rowIndex) {
                13 -> "R13 (SP)"
                14 -> "R14 (LR)"
                15 -> "R15 (PC)"
                16 -> "N (CPSR)"
                17 -> "Z (CPSR)"
                18 -> "C (CPSR)"
                19 -> "V (CPSR)"
                else -> "R$rowIndex"
            }

            1 -> {
                when (rowIndex) {
                    16 -> registers.getCPSR().N.toInt()
                    17 -> registers.getCPSR().Z.toInt()
                    18 -> registers.getCPSR().C.toInt()
                    19 -> registers.getCPSR().V.toInt()
                    else -> when (base) {
                        ARMv7ViewNumberRepresentation.Hexadecimal -> "0x%08X".format(
                            registers.get(
                                rowIndex
                            )
                        )

                        ARMv7ViewNumberRepresentation.Decimal -> registers.get(rowIndex)
                    }
                }
            }

            else -> throw IllegalArgumentException("Unexpected column index: $columnIndex")
        }

    fun updateRegisterData(newRegisters: ARMv7RegisterState) {
        registers = newRegisters

        fireTableDataChanged()
    }
}