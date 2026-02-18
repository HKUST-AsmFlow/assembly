package io.github.asmflow.assembly.armv7.psi.util

import com.intellij.psi.PsiElement
import io.github.asmflow.assembly.armv7.psi.ARMv7ElementFactory
import io.github.asmflow.assembly.armv7.psi.ARMv7Label
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes

object ARMv7PsiImplUtil {
    @JvmStatic
    fun getName(label: ARMv7Label): String = label.getLabelName()

    @JvmStatic
    fun getNameIdentifier(label: ARMv7Label): PsiElement? {
        val identNode = label.node.findChildByType(ARMv7TokenTypes.ID)!!
        return identNode.psi
    }

    @JvmStatic
    fun setName(label: ARMv7Label, name: String): ARMv7Label {
        val identNode = label.node.findChildByType(ARMv7TokenTypes.ID)!!
        val newLabel = ARMv7ElementFactory.createLabel(label.project, name)

        label.node.replaceChild(identNode, newLabel.node.findChildByType(ARMv7TokenTypes.ID)!!)
        return label
    }
}
