package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunConfigurationBase
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import io.github.asmflow.assembly.openapi.options.AssemblyRunConfigurationOptionsSettingsEditor
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfigurationOptions.EmulatorFlavour as AssemblyEmulatorFlavour

class AssemblyRunConfiguration(project: Project, factory: ConfigurationFactory, name: String) :
    RunConfigurationBase<AssemblyRunConfigurationOptions>(project, factory, name) {
    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> =
        AssemblyRunConfigurationOptionsSettingsEditor()

    override fun getOptions(): AssemblyRunConfigurationOptions = super.options as AssemblyRunConfigurationOptions

    override fun getState(executor: Executor, executionEnvironment: ExecutionEnvironment): RunProfileState =
        AssemblyRunProfileState()

    fun getEmulatorFlavour() = options.getEmulatorFlavour()

    fun setEmulatorFlavour(newFlavour: AssemblyEmulatorFlavour) = options.setEmulatorFlavour(newFlavour)
}
