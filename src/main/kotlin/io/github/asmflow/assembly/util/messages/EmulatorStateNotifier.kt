package io.github.asmflow.assembly.util.messages

import com.intellij.util.messages.Topic
import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState

interface EmulatorStateNotifier {
    fun onMemoryStateChanged(memoryState: ARMv7MemoryState) {}

    fun onRegisterStateChanged(registerState: ARMv7RegisterState) {}

    companion object {
        @Topic.ProjectLevel
        val EMULATOR_STATE_TOPIC = Topic.create("EmulatorStateNotifier", EmulatorStateNotifier::class.java)
    }
}
