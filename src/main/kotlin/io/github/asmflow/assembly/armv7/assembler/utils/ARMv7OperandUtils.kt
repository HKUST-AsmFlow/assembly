package io.github.asmflow.assembly.armv7.assembler.utils

import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

object ARMv7OperandUtils {
    fun ARMv7Operand?.isShiftlessRegister(): Boolean {
        this ?: return false
        registerWithShift ?: return false
        registerWithShift?.shiftType ?: return true
        return false
    }

    fun ARMv7Operand?.isNumber(): Boolean {
        this ?: return false
        number ?: return false
        return true
    }

}
