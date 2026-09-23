package io.github.asmflow.assembly.armv7.emulator

import com.intellij.util.messages.Topic

interface EmulatorStateNotifier {
    fun onMemoryStateChanged(memoryState: ARMv7MemoryState) {}

    fun onRegisterStateChanged(registerState: ARMv7RegisterState) {}

    companion object {
        @Topic.ProjectLevel
        val EMULATOR_STATE_TOPIC = Topic.create("EmulatorStateNotifier", EmulatorStateNotifier::class.java)
    }
}
