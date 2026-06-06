package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7BranchDecoder

class ARMv7BranchExecutor(private val registers: ARMv7RegisterState) {
    val decoder: ARMv7BranchDecoder = ARMv7BranchDecoder()
    fun execute(raw: Int) {
        val decoded = decoder.decode(raw)
        val currentPC = registers.getPC()
        val targetAddress = currentPC + decoded.byteOffset // byte offset already plans for PC + 8 illusion
        registers.setPC(targetAddress)

        if (decoded.isLink) {
            registers.setLR(currentPC - 4)
            // Return address is the next instruction
            // Since PC points two instructions forward
            // We need to use PC - 4
        }

    }
}