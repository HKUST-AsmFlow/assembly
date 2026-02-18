package io.github.asmflow.assembly.armv7.psi.util

import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import com.intellij.psi.search.FileTypeIndex
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.util.PsiTreeUtil
import io.github.asmflow.assembly.armv7.ARMv7FileType
import io.github.asmflow.assembly.armv7.psi.ARMv7Id

object ARMv7PsiTreeUtil {
    fun findLabels(project: Project, text: String): List<ARMv7Id> =
        FileTypeIndex.getFiles(ARMv7FileType, GlobalSearchScope.projectScope(project)).mapNotNull {
            PsiManager.getInstance(project).findFile(it)
        }.flatMap {
            PsiTreeUtil.findChildrenOfType(it, ARMv7Id::class.java)
        }.filter { it.text == text }
}
