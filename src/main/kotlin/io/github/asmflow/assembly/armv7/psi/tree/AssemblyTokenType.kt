package io.github.asmflow.assembly.armv7.psi.tree

import com.intellij.psi.tree.IElementType
import com.intellij.lang.Language

open class AssemblyTokenType(debugName: String, language: Language) : IElementType(debugName, language)
