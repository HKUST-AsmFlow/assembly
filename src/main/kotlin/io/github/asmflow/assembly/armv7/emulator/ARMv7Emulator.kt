package io.github.asmflow.assembly.armv7.emulator

import com.intellij.openapi.project.Project
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7ConditionCodeDecoder
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7BranchExecutor
import io.github.asmflow.assembly.armv7.emulator.executor.ARMv7DataProcessingExecutor
import io.github.asmflow.assembly.emulator.EmulationException
import io.github.asmflow.assembly.emulator.Emulator
import io.github.asmflow.assembly.util.messages.EmulatorStateNotifier

class ARMv7Emulator(val project: Project, val text: List<Int>) : Emulator {
    val publisher: EmulatorStateNotifier = project.messageBus.syncPublisher(EmulatorStateNotifier.EMULATOR_STATE_TOPIC)

    val registers = ARMv7RegisterState()
    override val name = "armv7"
    override var currentIdx = registers.getPC() / 4 // just 0x000.. (start of .text)
    override fun forward() {
        // for now, directly access the text[i] for the ith instruction
        val currentPC = registers.getPC()
        currentIdx = currentPC / 4

        if (currentIdx >= text.size || currentIdx < 0){
            throw EmulationException("Index $currentIdx with PC ${registers.getPC()} out of bounds of .text region of ${text.size} words.")
        }

        val instruction = text[currentIdx]
        if (ARMv7ConditionCodeDecoder.shouldExecute(instruction, registers)){

            // Simulate PC + 8 "illusion"
            registers.setPC(currentPC + 8)

            // Execute
            when((instruction ushr 26) and 0b11){
                0b00 -> ARMv7DataProcessingExecutor(registers).execute(instruction)
                0b10 -> ARMv7BranchExecutor(registers).execute(instruction)
            }

            // Pipeline flush check
            val pcAfterExecution = registers.getPC()

            if (pcAfterExecution == currentPC + 8){
                // PC not modified by instruction, continue pipeline as usual
                registers.setPC(currentPC + 4)
            }
            else {
                // The PC was modified.
                // Do nothing, the PC already
                // has the relevant value set by the instruction
            }
        }
        else {
            // Instruction skipped.
            // Increment PC as usual
            registers.setPC(currentPC + 4)
        }

        publisher.onRegisterStateChanged(registers)
    }

    override fun backward() {
        TODO("Not yet implemented")
    }

    override fun inBounds(): Boolean {
        currentIdx = registers.getPC() / 4
        return currentIdx < text.size && currentIdx >= 0
    }
}