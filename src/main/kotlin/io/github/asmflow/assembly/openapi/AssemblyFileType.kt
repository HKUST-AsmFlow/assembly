package io.github.asmflow.assembly.openapi

import com.intellij.openapi.fileTypes.LanguageFileType
import io.github.asmflow.assembly.lang.AssemblyLanguage

abstract class AssemblyFileType(language: AssemblyLanguage) : LanguageFileType(language)
