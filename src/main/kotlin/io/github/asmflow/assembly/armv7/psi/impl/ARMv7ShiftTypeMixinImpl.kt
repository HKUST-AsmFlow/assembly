package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7ShiftType as PsiARMv7ShiftType

abstract class ARMv7ShiftTypeMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), PsiARMv7ShiftType {
    override val shiftType: ARMv7ShiftType
        get() = ARMv7ShiftType.entries.firstOrNull { text.equals(it.name, true) } ?: error("Unknown shift type $text")
}
