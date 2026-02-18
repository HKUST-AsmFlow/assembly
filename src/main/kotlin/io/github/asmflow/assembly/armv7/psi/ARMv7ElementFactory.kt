package io.github.asmflow.assembly.armv7.psi

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFileFactory
import io.github.asmflow.assembly.armv7.ARMv7File
import io.github.asmflow.assembly.armv7.ARMv7FileType

object ARMv7ElementFactory {
    fun createFile(project: Project, text: String): ARMv7File = PsiFileFactory.getInstance(project)
        .createFileFromText("psi.arm", ARMv7FileType, text) as ARMv7File

    fun createLabel(project: Project, label: String): ARMv7Label = createFile(project, "$label:")
        .findChildrenByClass(ARMv7Label::class.java)[0]
}
