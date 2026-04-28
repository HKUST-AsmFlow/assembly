package io.github.asmflow.assembly.emulator

interface Emulator {
    val name: String
    var currentIdx: Int

    fun forward()
    fun backward()

    fun inBounds(): Boolean
}