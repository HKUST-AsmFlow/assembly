package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.EmulationException
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

    /**
     * Executes BX / BLX (register). Only ARM state is supported, so a target with bit 0 set
     * (which would switch to Thumb) is rejected rather than silently aligned.
     */
    fun executeRegister(raw: Int) {
        val decoded = decoder.decodeRegister(raw)
        val currentPC = registers.getPC()
        // Read Rm before writing LR, since `blx lr` is legal
        val target = registers.get(decoded.rm)

        if ((target and 1) == 1) {
            throw EmulationException(
                "Branch to Thumb address 0x${target.toUInt().toString(16)} is not supported (ARM state only)"
            )
        }

        if (decoded.isLink) {
            registers.setLR(currentPC - 4)
        }
        registers.setPC(target)
    }
}