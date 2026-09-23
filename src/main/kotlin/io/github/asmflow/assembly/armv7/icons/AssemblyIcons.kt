package io.github.asmflow.assembly.armv7.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object AssemblyIcons {
    val ARMv7 = loadIcon("/armv7/icons/armv7.svg")

    private fun loadIcon(path: String): Icon = IconLoader.getIcon(path, AssemblyIcons.javaClass)
}
