package io.github.asmflow.assembly.armv7.emulator

import com.intellij.openapi.project.Project
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7ConditionCodeDecoder
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7BranchExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7DataProcessingExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7MemoryExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7SupervisorCallExecutor

class ARMv7Emulator(
    val project: Project,
    val text: List<Int>,
    private val host: ARMv7SyscallHandler = ARMv7SyscallHandler.None,
) : Emulator {
    val publisher: EmulatorStateNotifier = project.messageBus.syncPublisher(EmulatorStateNotifier.EMULATOR_STATE_TOPIC)

    val registers = ARMv7RegisterState().apply {
        setPC(ARMv7AddressSpace.TEXT_BASE.addr.toInt())
        setSP(ARMv7AddressSpace.STACK_TOP.addr.toInt())
    }
    val memory = ARMv7MemoryState(text)
    override val name = "armv7"

    /**
     * The exit code passed to the `exit` syscall, or null while the program is still running.
     */
    var exitCode: Int? = null
        private set

    override val currentIdx: Int
        get() = ((registers.getPC().toUInt() - ARMv7AddressSpace.TEXT_BASE.addr) / 4u).toInt()

    override fun forward() {
        val currentPC = registers.getPC()
        val instruction = memory.fetchInstruction(currentPC.toUInt())
        if (ARMv7ConditionCodeDecoder.shouldExecute(instruction, registers)) {
            registers.setPC(currentPC + 8)

            val router = (instruction ushr 25) and 0b111
            when (router) {
                0b101 -> ARMv7BranchExecutor(registers).execute(instruction)
                // 0b010 -> Immediate-type
                // 0b011 -> Register-type
                // 0b100 -> LDM / STM
                0b010, 0b011, 0b100 -> ARMv7MemoryExecutor(registers, memory).execute(instruction)
                0b000 -> {
                    // Annoyingly, MUL / SWP (from data) overlaps into the memory space
                    // We use this ugly expression to check for it
                    if (((instruction ushr 7) and 1) == 1
                        && (((instruction ushr 4) and 1) == 1)
                        && (((instruction ushr 5) and 0b11) != 0))
                        ARMv7MemoryExecutor(registers, memory).execute(instruction)
                    else ARMv7DataProcessingExecutor(registers).execute(instruction)
                }
                0b001 -> ARMv7DataProcessingExecutor(registers).execute(instruction)
                0b111 -> ARMv7SupervisorCallExecutor(registers, memory, host) { exitCode = it }.execute(instruction)
            }

            if (registers.getPC() == currentPC + 8) {
                registers.setPC(currentPC + 4)
            }
        } else {
            registers.setPC(currentPC + 4)
        }

        publisher.onRegisterStateChanged(registers)
        publisher.onMemoryStateChanged(memory)
    }

    override fun backward() {
        TODO("Not yet implemented")
    }

    override fun inBounds(): Boolean = exitCode == null && memory.canFetch(registers.getPC().toUInt())
}
