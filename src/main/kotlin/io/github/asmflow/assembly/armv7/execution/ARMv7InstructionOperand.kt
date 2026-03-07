package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.armv7.psi.ARMv7Shift
import io.github.asmflow.assembly.util.functional.Option

sealed class ARMv7InstructionOperand {
    data class Label(val label: String): ARMv7InstructionOperand()
    data class Number(val value: Int) : ARMv7InstructionOperand()
    data class Register(val register: ARMv7Register, val shift: Option<ARMv7Shift>) : ARMv7InstructionOperand()
    data class RegisterWithOffset(val register: ARMv7Register, val offset: Offset) : ARMv7InstructionOperand()

    sealed class Offset {
        data class Numerical(val amount: Int) : ARMv7InstructionOperand()
    }
}
