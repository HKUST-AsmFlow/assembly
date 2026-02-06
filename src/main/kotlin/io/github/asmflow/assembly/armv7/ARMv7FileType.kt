package io.github.asmflow.assembly.armv7

import com.intellij.openapi.util.NlsSafe
import io.github.asmflow.assembly.icons.AssemblyIcons
import io.github.asmflow.assembly.openapi.AssemblyFileType
import org.jetbrains.annotations.NonNls
import javax.swing.Icon

object ARMv7FileType : AssemblyFileType(ARMv7Language) {
    override fun getDescription(): String = "ARMv7 Assembly file"

    override fun getDefaultExtension(): @NlsSafe String = ".arm"

    override fun getIcon(): Icon = AssemblyIcons.ARMV7

    override fun getName(): @NonNls String = ARMv7Language.displayName
}
