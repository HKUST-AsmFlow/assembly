package io.github.asmflow.assembly.lang

import com.intellij.lang.Language
import org.jetbrains.annotations.NotNull

abstract class AssemblyLanguage(@NotNull id: String) : Language(id)
