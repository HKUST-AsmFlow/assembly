package io.github.asmflow.assembly.armv7.emulator

object ARMv7AddressSpace {
    const val TEXT_BASE = 0x00400000u
    const val DATA_BASE = 0x10000000u
    const val STACK_TOP = 0x7FFFFFFCu
    const val KERNEL_BASE = 0x80000000u
}
