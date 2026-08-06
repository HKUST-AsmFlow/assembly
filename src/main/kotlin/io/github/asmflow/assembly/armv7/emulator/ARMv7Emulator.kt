package io.github.asmflow.assembly.armv7.emulator

import com.intellij.openapi.project.Project
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7ConditionCodeDecoder
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7BranchExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7DataProcessingExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7MemoryExecutor
import io.github.asmflow.assembly.emulator.Emulator
import io.github.asmflow.assembly.util.messages.EmulatorStateNotifier

class ARMv7Emulator(val project: Project, val text: List<Int>) : Emulator {
    val publisher: EmulatorStateNotifier = project.messageBus.syncPublisher(EmulatorStateNotifier.EMULATOR_STATE_TOPIC)

    val registers = ARMv7RegisterState().apply {
        setPC(ARMv7AddressSpace.TEXT_BASE.toInt())
        setSP(ARMv7AddressSpace.STACK_TOP.toInt())
    }
    val memory = ARMv7MemoryState(text)
    override val name = "armv7"

    override val currentIdx: Int
        get() = ((registers.getPC().toUInt() - ARMv7AddressSpace.TEXT_BASE) / 4u).toInt()

    override fun forward() {
        val currentPC = registers.getPC()
        val instruction = memory.fetchInstruction(currentPC.toUInt())
        if (ARMv7ConditionCodeDecoder.shouldExecute(instruction, registers)) {
            registers.setPC(currentPC + 8)

            when ((instruction ushr 26) and 0b11) {
                0b00 -> ARMv7DataProcessingExecutor(registers).execute(instruction)
                0b01 -> ARMv7MemoryExecutor(registers, memory).execute(instruction)
                0b10 -> ARMv7BranchExecutor(registers).execute(instruction)
            }

            if (registers.getPC() == currentPC + 8) {
                registers.setPC(currentPC + 4)
            }
        } else {
            registers.setPC(currentPC + 4)
        }

        publisher.onRegisterStateChanged(registers)
    }

    override fun backward() {
        TODO("Not yet implemented")
    }

    override fun inBounds(): Boolean = memory.canFetch(registers.getPC().toUInt())
}
