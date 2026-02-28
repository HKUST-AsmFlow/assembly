package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizerUtil
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfigurationOptions.EmulatorFlavour
import io.github.asmflow.assembly.openapi.options.AssemblyRunConfigurationOptionsSettingsEditor
import org.jdom.Element

class AssemblyRunConfiguration(project: Project, factory: ConfigurationFactory, name: String) :
    LocatableConfigurationBase<AssemblyRunConfigurationOptions>(project, factory, name) {
    override fun getConfigurationEditor(): SettingsEditor<out RunConfiguration?> =
        AssemblyRunConfigurationOptionsSettingsEditor(project)

    override fun getOptions(): AssemblyRunConfigurationOptions = super.options as AssemblyRunConfigurationOptions

    override fun getState(executor: Executor, executionEnvironment: ExecutionEnvironment): RunProfileState =
        AssemblyRunProfileState(executionEnvironment, this)

    override fun readExternal(element: Element) {
        super.readExternal(element)

        setEmulatorFlavour(
            EmulatorFlavour.valueOf(
                JDOMExternalizerUtil.readField(
                    element,
                    "emulatorFlavour"
                )!!
            )
        )
        setScriptPath(JDOMExternalizerUtil.readField(element, "scriptPath")!!)
    }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)

        JDOMExternalizerUtil.writeField(element, "emulatorFlavour", getEmulatorFlavour().name)
        JDOMExternalizerUtil.writeField(element, "scriptPath", getScriptPath())
    }

    fun getEmulatorFlavour() = options.getEmulatorFlavour()

    fun setEmulatorFlavour(newFlavour: EmulatorFlavour) = options.setEmulatorFlavour(newFlavour)

    fun getScriptPath() = options.getScriptPath()

    fun setScriptPath(path: String) = options.setScriptPath(path)
}
