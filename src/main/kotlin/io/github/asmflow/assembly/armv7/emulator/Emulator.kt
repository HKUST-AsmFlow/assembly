package io.github.asmflow.assembly.armv7.emulator

interface Emulator {
    val name: String
    val currentIdx: Int

    fun forward()
    fun backward()
    fun inBounds(): Boolean
}
