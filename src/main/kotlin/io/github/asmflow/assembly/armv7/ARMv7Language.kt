package io.github.asmflow.assembly.armv7

import com.intellij.openapi.util.NlsSafe
import com.intellij.lang.Language

@Suppress("JavaIoSerializableObjectMustHaveReadResolve")
object ARMv7Language : Language("ARMv7_ASM") {
    override fun getDisplayName(): @NlsSafe String = "ARMv7 Assembly"

    override fun isCaseSensitive(): Boolean = true
}
