package io.github.asmflow.assembly.armv7.emulator

/**
 * The outside world as seen by emulated syscalls.
 */
interface ARMv7SyscallHandler {
    /**
     * Receives text written by the program to file descriptor [fd] (1 = stdout, 2 = stderr).
     */
    fun write(fd: Int, text: String)

    object None : ARMv7SyscallHandler {
        override fun write(fd: Int, text: String) = Unit
    }
}
