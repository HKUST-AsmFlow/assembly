package io.github.asmflow.assembly.armv7.options

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.util.NlsContexts
import io.github.asmflow.assembly.armv7.ARMv7FileType
import io.github.asmflow.assembly.armv7.editor.colors.ARMv7SyntaxHighlighter
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

class ARMv7ColorSettingsPage : ColorSettingsPage {
    override fun getIcon(): Icon = ARMv7FileType.icon

    override fun getHighlighter(): SyntaxHighlighter = ARMv7SyntaxHighlighter()

    override fun getDemoText(): @NonNls String = TODO("Not yet implemented")

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String?, TextAttributesKey?> = emptyMap()

    override fun getAttributeDescriptors(): Array<out AttributesDescriptor?> = TODO("Not yet implemented")

    override fun getColorDescriptors(): Array<out ColorDescriptor?> = emptyArray()

    override fun getDisplayName(): @NlsContexts.ConfigurableName String = ARMv7FileType.displayName
}
