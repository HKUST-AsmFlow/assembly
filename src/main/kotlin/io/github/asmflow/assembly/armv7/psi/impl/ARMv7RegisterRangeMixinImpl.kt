package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7RegisterRange
import io.github.asmflow.assembly.armv7.ranges.RegisterProgression.Companion.rangeTo

abstract class ARMv7RegisterRangeMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7RegisterRange {
    override val start = registerList.first().register
    override val end = registerList.last().register

    override val expanded = (start..end).toSortedSet()
}
