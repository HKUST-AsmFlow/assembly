package io.github.asmflow.assembly.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

object AssemblyIcons {
    val ARMV7 = loadIcon("/icons/armv7.svg")

    private fun loadIcon(path: String): Icon = IconLoader.getIcon(path, AssemblyIcons.javaClass)
}
