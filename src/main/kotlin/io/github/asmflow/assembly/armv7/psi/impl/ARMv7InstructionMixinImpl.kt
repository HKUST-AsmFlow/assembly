package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.util.functional.*

abstract class ARMv7InstructionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Instruction {
    override val baseMnemonic = partitionMnemonic().first
    override val setsFlags = partitionMnemonic().second
    override val conditionCode = partitionMnemonic().third.unwrapOr(ARMv7InstructionConditionCode.AL)

    private fun partitionMnemonic(): Triple<String, Boolean, Option<ARMv7InstructionConditionCode>> {
        var mnemonic = mnemonic.text
        val conditionCodes = ARMv7InstructionConditionCode.entries.map { it.display() }

        val conditionCode = if (conditionCodes.any { mnemonic.endsWith(it) }) {
            val value = Some(mnemonic.substring(mnemonic.length - 2))
            mnemonic = mnemonic.substring(0, mnemonic.length - 2)

            value
        } else
            None

        val setsFlags = mnemonic.endsWith('s')
        val base = if (setsFlags)
            mnemonic.substring(0, mnemonic.length - 1)
        else
            mnemonic.substring(0, mnemonic.length)

        val conditionCodeEnum = conditionCode.map { ARMv7InstructionConditionCode.fromString(it).toOption() }
        return Triple(base, setsFlags, conditionCodeEnum.flatten())
    }
}
