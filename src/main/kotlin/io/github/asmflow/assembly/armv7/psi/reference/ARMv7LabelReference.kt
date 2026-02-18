package io.github.asmflow.assembly.armv7.psi.reference

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiElementResolveResult
import com.intellij.psi.PsiPolyVariantReferenceBase
import com.intellij.psi.ResolveResult
import io.github.asmflow.assembly.armv7.psi.ARMv7Label
import io.github.asmflow.assembly.armv7.psi.util.ARMv7PsiTreeUtil

class ARMv7LabelReference(val label: ARMv7Label) : PsiPolyVariantReferenceBase<PsiElement>(label) {
    override fun multiResolve(incomplete: Boolean): Array<out ResolveResult> {
        val project = label.project

        return PsiElementResolveResult.createResults(ARMv7PsiTreeUtil.findLabels(project, label.getLabelName()))
    }
}
