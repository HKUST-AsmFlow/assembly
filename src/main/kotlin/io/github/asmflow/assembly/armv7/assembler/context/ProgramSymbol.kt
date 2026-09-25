package io.github.asmflow.assembly.armv7.assembler.context

import io.github.asmflow.assembly.armv7.emulator.ARMv7AddressSpace

data class ProgramSymbol(val section: ProgramSection, val offset: Int) {
    fun absoluteAddress(): UInt = when (section) {
        ProgramSection.Text -> ARMv7AddressSpace.TEXT_BASE.addr + (offset * 4).toUInt()
        ProgramSection.Data -> ARMv7AddressSpace.DATA_BASE.addr + offset.toUInt()
    }
}
