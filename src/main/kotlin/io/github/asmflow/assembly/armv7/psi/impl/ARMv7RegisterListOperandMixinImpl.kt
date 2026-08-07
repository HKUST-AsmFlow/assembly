package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7RegisterList

abstract class ARMv7RegisterListOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7RegisterList {
    override val operand = ARMv7InstructionOperand.RegisterList(
        registerList.map { setOf(it.register) }
            .plus(registerRangeList.map { it.expanded })
            .fold(emptySet<ARMv7Register>()) { set, reg -> set.union(reg) }.toSortedSet()
    )
}
