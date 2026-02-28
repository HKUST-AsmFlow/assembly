package io.github.asmflow.assembly.armv7.options

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.NlsContexts
import io.github.asmflow.assembly.AssemblyBundle
import io.github.asmflow.assembly.armv7.ARMv7FileType
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7SyntaxHighlighter
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7TextAttributes
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

class ARMv7ColorSettingsPage : ColorSettingsPage {
    val descriptors = arrayOf(
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.comment",
            ARMv7TextAttributes.ARMv7_COMMENT
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.directive",
            ARMv7TextAttributes.ARMv7_DIRECTIVE
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.instruction",
            ARMv7TextAttributes.ARMv7_INSTRUCTION
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.label",
            ARMv7TextAttributes.ARMv7_LABEL
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.number",
            ARMv7TextAttributes.ARMv7_NUMBER
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.register",
            ARMv7TextAttributes.ARMv7_REGISTERS
        ),
        createAttributesDescriptor(
            "armv7.color.settings.page.group.root", "armv7.attribute.descriptor.shifttype",
            ARMv7TextAttributes.ARMv7_SHIFT_TYPE
        )
    )

    override fun getIcon(): Icon = ARMv7FileType.icon

    override fun getHighlighter(): SyntaxHighlighter = ARMv7SyntaxHighlighter()

    override fun getDemoText(): @NonNls String = """
        |@ ARMv7 Assembly Example
        |
        |.<directive>global</directive> <label>main</label>
        |
        |<label>main</label>:
        |   <instruction>adcsal</instruction> r0, r1, r2, <shift_type>lsl</shift_type> <number>#1</number>
    """.trimMargin()

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey> = mapOf(
        "directive" to ARMv7TextAttributes.ARMv7_DIRECTIVE,
        "instruction" to ARMv7TextAttributes.ARMv7_INSTRUCTION,
        "label" to ARMv7TextAttributes.ARMv7_LABEL,
        "number" to ARMv7TextAttributes.ARMv7_NUMBER,
        "shift_type" to ARMv7TextAttributes.ARMv7_SHIFT_TYPE
    )

    override fun getAttributeDescriptors(): Array<out AttributesDescriptor> = descriptors

    override fun getColorDescriptors(): Array<out ColorDescriptor> = emptyArray()

    override fun getDisplayName(): @NlsContexts.ConfigurableName String = ARMv7FileType.displayName

    private fun createAttributesDescriptor(
        groupKey: String,
        typeKey: String,
        textAttributesKey: TextAttributesKey
    ): AttributesDescriptor {
        val displayName = AssemblyBundle.message(groupKey) + AssemblyBundle.message(typeKey)
        return AttributesDescriptor(displayName, textAttributesKey)
    }
}
