package io.github.asmflow.assembly.armv7.psi

import io.github.asmflow.assembly.armv7.ARMv7File
import io.github.asmflow.assembly.armv7.ARMv7Language
import io.github.asmflow.assembly.psi.tree.AssemblyFileElementType
import org.jetbrains.annotations.NonNls

object ARMv7FileElementType : AssemblyFileElementType<ARMv7File>("ARMv7.FILE", ARMv7Language) {
    override fun getExternalId(): @NonNls String = "ARMv7.FILE"

    override fun getStubVersion(): Int = 1
}
