package io.github.asmflow.assembly.openapi.options

import com.intellij.openapi.options.SettingsEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.ComboBox
import com.intellij.openapi.ui.TextFieldWithBrowseButton
import com.intellij.ui.dsl.builder.Cell
import com.intellij.ui.dsl.builder.panel
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfiguration
import io.github.asmflow.assembly.execution.configurations.AssemblyRunConfigurationOptions.EmulatorFlavour
import javax.swing.JComponent

class AssemblyRunConfigurationOptionsSettingsEditor(private val project: Project) :
    SettingsEditor<AssemblyRunConfiguration>() {
    lateinit var flavourCombobox: Cell<ComboBox<String>>
    lateinit var scriptPathField: Cell<TextFieldWithBrowseButton>

    override fun applyEditorTo(options: AssemblyRunConfiguration) {
        options.setScriptPath(scriptPathField.component.text)
        options.setEmulatorFlavour(EmulatorFlavour.valueOf(flavourCombobox.component.selectedItem as String))
    }

    override fun createEditor(): JComponent = panel {
        row("Assembly Flavour: ") {
            flavourCombobox = comboBox(EmulatorFlavour.entries.map { it.name })
        }
        row("Assembly File: ") {
            scriptPathField = textFieldWithBrowseButton(
                "Select Assembly File",
                project,
                fileChosen = { file -> file.path }
            )
        }
    }

    override fun resetEditorFrom(options: AssemblyRunConfiguration) {
        flavourCombobox.component.selectedItem = options.getEmulatorFlavour().toString()
        scriptPathField.component.text = options.getScriptPath() ?: ""
    }
}
