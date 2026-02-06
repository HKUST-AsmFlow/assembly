package io.github.asmflow.assembly.psi.tree

import com.intellij.psi.tree.IElementType
import io.github.asmflow.assembly.lang.AssemblyLanguage

open class AssemblyTokenType(debugName: String, language: AssemblyLanguage) : IElementType(debugName, language)
