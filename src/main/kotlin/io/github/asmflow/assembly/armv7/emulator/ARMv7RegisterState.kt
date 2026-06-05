package io.github.asmflow.assembly.armv7.emulator

import io.github.asmflow.assembly.emulator.EmulationException

class ARMv7RegisterState {
    private val registerFile = IntArray(16)
    private val CPSR = ARMv7CPSR()

    fun set(id: Int, value: Int) {
        if (id < 0 || id >= registerFile.size) throw EmulationException("Invalid register id: $id")
        registerFile[id] = value
    }

    fun get(id: Int): Int {
        if (id < 0 || id >= registerFile.size) throw EmulationException("Invalid register id: $id")
        return registerFile[id]
    }

    fun getSP(): Int = get(13)
    fun setSP(value: Int) = set(13, value)

    fun getLR(): Int = get(14)
    fun setLR(value: Int) = set(14, value)

    fun getPC(): Int = get(15)
    fun setPC(value: Int) = set(15, value)
    fun incrementPC() = setPC(getPC() + 4)

    fun getCPSR(): ARMv7CPSR = CPSR
}
