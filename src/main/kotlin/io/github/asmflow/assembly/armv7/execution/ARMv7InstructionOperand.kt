package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.util.functional.Option

sealed class ARMv7InstructionOperand {
    data class Label(val label: String) : ARMv7InstructionOperand()
    data class Number(val value: Int) : ARMv7InstructionOperand()
    data class Register(val register: ARMv7Register, val shift: Option<Shift>) : ARMv7InstructionOperand() {
        data class Shift(val shiftType: ARMv7ShiftType, val shiftBy: ARMv7InstructionOperand)
    }

    data class RegisterWithOffset(val register: ARMv7Register, val offset: Offset, val flags: AddressingFlags) : ARMv7InstructionOperand()

    data class AddressingFlags(
        val preIndexed: Boolean,
        val add: Boolean,
        val writeBack: Boolean,
    )

    sealed class Offset {
        data class NumericalOffset(val amount: Int) : Offset()
        data class RegisterOffset(val register: Register) : Offset()

        companion object {
            val ZERO = NumericalOffset(0)
        }
    }
}
