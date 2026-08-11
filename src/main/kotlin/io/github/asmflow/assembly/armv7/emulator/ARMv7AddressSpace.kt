package io.github.asmflow.assembly.armv7.emulator

enum class ARMv7AddressSpace(val addr: UInt) {
    TEXT_BASE(0x00400000u),
    DATA_BASE(0x10000000u),
    STACK_TOP(0x7FFFFFFCu),
    KERNEL_BASE(0x80000000u)
}
