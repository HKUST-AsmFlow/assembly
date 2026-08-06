package io.github.asmflow.assembly.armv7.ranges

import io.github.asmflow.assembly.armv7.execution.ARMv7Register

class RegisterProgression(
    override val start: ARMv7Register,
    override val endInclusive: ARMv7Register
) : ClosedRange<ARMv7Register>, Iterable<ARMv7Register> {
    override fun iterator(): Iterator<ARMv7Register> = ARMv7Register.entries.iterator()

    companion object {
        operator fun ARMv7Register.rangeTo(other: ARMv7Register) = RegisterProgression(this, other)
    }
}
