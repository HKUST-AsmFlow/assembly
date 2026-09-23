package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.ARMv7SyscallHandler
import io.github.asmflow.assembly.armv7.emulator.EmulationException

/**
 * Executes `SVC` following the Linux ARM EABI convention: the syscall number is in R7,
 * arguments are in R0-R2, and the result is returned in R0. The instruction's immediate is ignored.
 */
class ARMv7SupervisorCallExecutor(
    private val registers: ARMv7RegisterState,
    private val memory: ARMv7MemoryState,
    private val host: ARMv7SyscallHandler,
    private val onExit: (exitCode: Int) -> Unit,
) {
    fun execute(raw: Int) {
        // Bits [27:25] = 111 also covers coprocessor instructions; only bit 24 set is SVC.
        if (((raw ushr 24) and 0xF) != 0xF) {
            throw EmulationException("Coprocessor instructions are not supported.")
        }

        when (val number = registers.get(SYSCALL_NUMBER_REGISTER)) {
            SYS_EXIT -> onExit(registers.get(0) and 0xFF)
            SYS_WRITE -> write()
            else -> throw EmulationException("Unsupported syscall $number")
        }
    }

    private fun write() {
        val fd = registers.get(0)
        val buffer = registers.get(1)
        val length = registers.get(2)

        if (length < 0 || length > MAX_WRITE_LENGTH) {
            throw EmulationException("Invalid write length $length (maximum is $MAX_WRITE_LENGTH bytes).")
        }

        if (fd != STDOUT && fd != STDERR) {
            registers.set(0, -EBADF)
            return
        }

        val bytes = ByteArray(length) { memory.getByte((buffer + it).toUInt()).toByte() }
        host.write(fd, String(bytes, Charsets.UTF_8))
        registers.set(0, length)
    }

    private companion object {
        const val SYSCALL_NUMBER_REGISTER = 7

        const val SYS_EXIT = 1
        const val SYS_WRITE = 4

        const val STDOUT = 1
        const val STDERR = 2
        const val EBADF = 9

        const val MAX_WRITE_LENGTH = 1 shl 20
    }
}
