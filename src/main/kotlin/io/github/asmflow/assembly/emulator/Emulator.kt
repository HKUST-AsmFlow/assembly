package io.github.asmflow.assembly.emulator

interface Emulator {
    val name: String
    val currentIdx: Int

    fun forward()
    fun backward()
    fun inBounds(): Boolean
}
