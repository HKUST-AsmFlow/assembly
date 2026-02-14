package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.configurations.RunConfigurationOptions

class AssemblyRunConfigurationOptions : RunConfigurationOptions() {
    private val emulatorFlavour = enum(EmulatorFlavour.ARMv7)
        .provideDelegate(this, "emulator-flavour")

    fun getEmulatorFlavour() = emulatorFlavour.getValue(this)

    fun setEmulatorFlavour(newFlavour: EmulatorFlavour) {
        emulatorFlavour.setValue(this, newFlavour)
    }

    enum class EmulatorFlavour {
        ARMv7
    }
}
