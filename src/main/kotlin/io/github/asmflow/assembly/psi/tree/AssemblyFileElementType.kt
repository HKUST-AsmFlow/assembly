package io.github.asmflow.assembly.psi.tree

import com.intellij.psi.PsiFile
import com.intellij.psi.stubs.PsiFileStub
import com.intellij.psi.tree.ILightStubFileElementType
import com.intellij.lang.Language

abstract class AssemblyFileElementType<F : PsiFile>(debugName: String, language: Language) :
    ILightStubFileElementType<PsiFileStub<F>>(debugName, language)
