package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.tree.IElementType
import io.github.asmflow.assembly.armv7.ARMv7AssemblyLanguage
import org.jetbrains.annotations.NotNull

class ARMv7ElementType(debugName: @NotNull String) : IElementType(debugName, ARMv7AssemblyLanguage)
