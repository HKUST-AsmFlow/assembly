package io.github.asmflow.assembly.execution.configurations

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.execution.configurations.RunConfiguration
import com.intellij.openapi.components.BaseState
import com.intellij.openapi.project.Project
import org.jetbrains.annotations.NonNls

class AssemblyConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type) {
    override fun createTemplateConfiguration(project: Project): RunConfiguration =
        AssemblyRunConfiguration(project, this, "Run Assembly")

    override fun getId(): @NonNls String = type.id

    override fun getOptionsClass(): Class<out BaseState?> = AssemblyRunConfigurationOptions::class.java
}
