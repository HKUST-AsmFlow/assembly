package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.emulation.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.psi.IARMv7InstructionSuffix

open class ARMv7InstructionSuffixImpl(node: ASTNode) : IARMv7InstructionSuffix, ASTWrapperPsiElement(node) {
    val flagsAndConditionCode = firstChild.text.substringAfter(node.elementType.toString().substringBefore('_').lowercase())

    override fun conditionCode(): ARMv7InstructionConditionCode? {
        val substr = if (setsFlags())
            flagsAndConditionCode.substring(1)
        else
            flagsAndConditionCode

        return try {
            ARMv7InstructionConditionCode.valueOf(substr.uppercase())
        } catch (_: IllegalArgumentException) {
            null
        }
    }

    override fun setsFlags(): Boolean = flagsAndConditionCode.startsWith("s")
}
