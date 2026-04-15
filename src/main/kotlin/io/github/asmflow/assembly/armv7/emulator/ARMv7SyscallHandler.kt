package io.github.asmflow.assembly.armv7.emulator

interface ARMv7SyscallHandler {
    fun handle(imm24: Int, )
}