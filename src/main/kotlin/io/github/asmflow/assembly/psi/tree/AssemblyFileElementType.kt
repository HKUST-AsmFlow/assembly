package io.github.asmflow.assembly.psi.tree

import com.intellij.psi.PsiFile
import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.ILightStubFileElementType
import io.github.asmflow.assembly.lang.AssemblyLanguage

abstract class AssemblyFileElementType<F : PsiFile>(debugName: String, language: AssemblyLanguage) :
    ILightStubFileElementType<PsiFileStub<F>>(debugName, language)
