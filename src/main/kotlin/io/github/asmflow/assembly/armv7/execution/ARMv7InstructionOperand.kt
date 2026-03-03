package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.armv7.psi.ARMv7Shift
import io.github.asmflow.assembly.util.functional.Option

sealed class ARMv7InstructionOperand {
    data class Register(val kind: ARMv7Register, val shift: Option<ARMv7Shift>) : ARMv7InstructionOperand()
}
