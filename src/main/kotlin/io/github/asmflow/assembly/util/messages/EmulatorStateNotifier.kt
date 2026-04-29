package io.github.asmflow.assembly.util.messages

import com.intellij.util.messages.Topic

interface EmulatorStateNotifier {
    companion object {
        @Topic.ProjectLevel
        val EMULATOR_STATE_TOPIC = Topic.create("EmulatorStateNotifier", EmulatorStateNotifier::class.java)
    }
}
