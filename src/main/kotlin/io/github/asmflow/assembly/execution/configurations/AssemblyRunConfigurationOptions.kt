package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.configurations.LocatableRunConfigurationOptions

class AssemblyRunConfigurationOptions : LocatableRunConfigurationOptions() {
    private val emulatorFlavour = enum(EmulatorFlavour.ARMv7)
        .provideDelegate(this, "emulator-flavour")
    private val scriptPath = string("").provideDelegate(this, "script-path")

    fun getEmulatorFlavour() = emulatorFlavour.getValue(this)

    fun setEmulatorFlavour(newFlavour: EmulatorFlavour) {
        emulatorFlavour.setValue(this, newFlavour)
    }

    fun getScriptPath() = scriptPath.getValue(this)

    fun setScriptPath(newPath: String) {
        scriptPath.setValue(this, newPath)
    }

    enum class EmulatorFlavour {
        ARMv7
    }
}
