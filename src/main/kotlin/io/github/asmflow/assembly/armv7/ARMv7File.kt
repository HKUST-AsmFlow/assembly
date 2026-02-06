package io.github.asmflow.assembly.armv7

import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import io.github.asmflow.assembly.extapi.psi.AssemblyFile

class ARMv7File(viewProvider: FileViewProvider) : AssemblyFile(viewProvider, ARMv7Language) {
    override fun getFileType(): FileType = ARMv7FileType
}
