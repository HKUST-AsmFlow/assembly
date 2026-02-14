package io.github.asmflow.assembly.execution

import com.intellij.execution.configurations.ConfigurationTypeBase
import com.intellij.icons.AllIcons
import com.intellij.openapi.util.NotNullLazyValue

open class AssemblyRunConfigurationType : ConfigurationTypeBase(
    "AssemblyRunConfigurationType", "Run Assembly",
    "Run Assembly files",
    NotNullLazyValue.createValue { AllIcons.Nodes.Console }
) {

    init {
        addFactory(TODO())
    }
}
