package io.github.asmflow.assembly.armv7

import com.intellij.openapi.fileTypes.FileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.psi.FileViewProvider

class ARMv7File(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ARMv7Language) {
    override fun getFileType(): FileType = ARMv7FileType
}
