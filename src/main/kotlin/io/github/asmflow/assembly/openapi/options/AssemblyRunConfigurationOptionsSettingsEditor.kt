package io.github.asmflow.assembly.openapi.options

import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.ui.ComboBox
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.panel
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfiguration
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfigurationOptions.EmulatorFlavour as AssemblyEmulatorFlavour
import javax.swing.JComponent

class AssemblyRunConfigurationOptionsSettingsEditor : SettingsEditor<AssemblyRunConfiguration>() {
    lateinit var flavourCombobox: Cell<ComboBox<String>>

    override fun applyEditorTo(options: AssemblyRunConfiguration) {
        options.setEmulatorFlavour(AssemblyEmulatorFlavour.valueOf(flavourCombobox.component.selectedItem as String))
    }

    override fun createEditor(): JComponent = panel {
        row("Assembly Flavour: ") {
            flavourCombobox = comboBox(AssemblyEmulatorFlavour.entries.map { it.name })
        }
    }

    override fun resetEditorFrom(options: AssemblyRunConfiguration) {
        flavourCombobox.component.selectedItem = options.getEmulatorFlavour().toString()
    }
}
