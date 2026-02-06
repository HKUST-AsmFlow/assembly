package io.github.asmflow.assembly.extapi.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider
import io.github.asmflow.assembly.lang.AssemblyLanguage

abstract class AssemblyFile(viewProvider: FileViewProvider, language: AssemblyLanguage) :
    PsiFileBase(viewProvider, language)