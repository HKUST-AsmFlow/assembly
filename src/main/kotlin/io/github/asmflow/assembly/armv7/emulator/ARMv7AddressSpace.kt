package io.github.asmflow.assembly.armv7.emulator

object ARMv7AddressSpace {
    const val TEXT_BASE = 0x00010000u
    const val DATA_BASE = 0x00800000u
    const val STACK_TOP = 0x80000000u
    const val STACK_BOTTOM = 0x7FFF0000u
}
