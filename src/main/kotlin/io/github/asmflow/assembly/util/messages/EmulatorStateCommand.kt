package io.github.asmflow.assembly.util.messages

import com.intellij.util.messages.Topic

interface EmulatorStateCommand {
    fun writeMemory()

    fun writeRegister()

    companion object {
        @Topic.ProjectLevel
        val EMULATOR_COMMAND_TOPIC = Topic.create("EmulatorStateCommand", EmulatorStateCommand::class.java)
    }
}
