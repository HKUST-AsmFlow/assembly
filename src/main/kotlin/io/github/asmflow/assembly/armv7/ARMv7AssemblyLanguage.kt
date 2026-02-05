package io.github.asmflow.assembly.armv7

import com.intellij.openapi.util.NlsSafe
import io.github.asmflow.assembly.lang.AssemblyLanguage

@Suppress("JavaIoSerializableObjectMustHaveReadResolve")
object ARMv7AssemblyLanguage : AssemblyLanguage("ARMv7_ASM") {
    override fun getDisplayName(): @NlsSafe String = "ARMv7 Assembly"

    override fun isCaseSensitive(): Boolean = true
}
