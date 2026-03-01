package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.armv7.psi.ARMv7Instruction
import io.github.asmflow.assembly.util.functional.None
import io.github.asmflow.assembly.util.functional.Option
import io.github.asmflow.assembly.util.functional.Some
import io.github.asmflow.assembly.util.functional.resultOfException
import kotlin.properties.Delegates

abstract class ARMv7InstructionMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Instruction {
    override lateinit var baseMnemonic: String
    override var setsFlags by Delegates.notNull<Boolean>()
    override lateinit var conditionCode: ARMv7InstructionConditionCode

    init {
        val parts = partitionMnemonic()

        baseMnemonic = parts.first
        setsFlags = parts.second
        conditionCode = parts.third.unwrapOr(ARMv7InstructionConditionCode.AL)
    }

    private fun partitionMnemonic(): Triple<String, Boolean, Option<ARMv7InstructionConditionCode>> {
        var mnemonic = mnemonic.text
        val conditionCodes = ARMv7InstructionConditionCode.entries.map { it.toString().lowercase() }

        val conditionCode = if (conditionCodes.any { mnemonic.endsWith(it) }) {
            val value = Some(mnemonic.substring(mnemonic.length - 2))
            mnemonic = mnemonic.substring(0, mnemonic.length - 2)

            value
        } else
            None

        val setsFlags = mnemonic.endsWith('s')
        val base: String = if (setsFlags)
            mnemonic.substring(0, mnemonic.length - 1)
        else
            mnemonic.substring(0, mnemonic.length)

        val conditionCodeEnum = when (conditionCode) {
            is Some -> resultOfException {
                ARMv7InstructionConditionCode.valueOf(
                    conditionCode.unwrap().uppercase()
                )
            }.ok()

            is None -> None
        }

        return Triple(base, setsFlags, conditionCodeEnum)
    }
}
