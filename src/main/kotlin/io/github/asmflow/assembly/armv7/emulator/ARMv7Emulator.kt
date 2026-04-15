package io.github.asmflow.assembly.armv7.emulator

import io.github.asmflow.assembly.emulator.Emulator

class ARMv7Emulator(val text: List<Int>) : Emulator {
    val registers = ARMv7RegisterState()
    override val name = "armv7"
    override var currentIdx = 0 // Assume .text starts at 0
    override fun forward() {
        currentIdx++
        registers.incrementPC()

        // TODO implement proper memory,
        // for now, directly access the text[i] for the ith instruction


    }

    override fun backward() {
        TODO("Not yet implemented")
    }


}