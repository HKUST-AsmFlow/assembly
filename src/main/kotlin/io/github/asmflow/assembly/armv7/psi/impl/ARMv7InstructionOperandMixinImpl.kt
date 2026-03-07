package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.util.functional.toOption
import io.github.asmflow.assembly.util.unreachable

abstract class ARMv7InstructionOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Operand {
    override val operand: ARMv7InstructionOperand by lazy {
        when {
            label != null -> {
                ARMv7InstructionOperand.Label(label = label!!.text)
            }

            number != null -> {
                val numberNode = number!!.node
                val numberPsi = numberNode.findChildByType(ARMv7TokenTypes.BINARY_NUMBER) ?: numberNode.findChildByType(
                    ARMv7TokenTypes.DECIMAL_NUMBER
                ) ?: numberNode.findChildByType(ARMv7TokenTypes.HEXADECIMAL_NUMBER) ?: numberNode.findChildByType(
                    ARMv7TokenTypes.OCTAL_NUMBER
                )!!

                val radix = when (numberPsi.elementType) {
                    ARMv7TokenTypes.BINARY_NUMBER -> 2
                    ARMv7TokenTypes.DECIMAL_NUMBER -> 10
                    ARMv7TokenTypes.HEXADECIMAL_NUMBER -> 16
                    ARMv7TokenTypes.OCTAL_NUMBER -> 8
                    else -> unreachable()
                }

                ARMv7InstructionOperand.Number(value = numberPsi.text.toInt(radix))
            }

            offset != null -> {
                TODO("")
            }

            offsetVariant != null -> {
                TODO("")
            }

            postindexed != null -> {
                TODO("")
            }

            preindexed != null -> {
                TODO("")
            }

            registerWithShift != null -> {
                val registerPsi =
                    registerWithShift!!.node.findChildByType(ARMv7TokenTypes.REGISTER).toOption().unwrap().psi
                val register =
                    ARMv7Register.entries.find { registerPsi.textMatches(it.name.lowercase()) }.toOption().unwrap()

                ARMv7InstructionOperand.Register(
                    register = register,
                    shift = registerWithShift!!.shift.toOption()  // ← assuming element is registerWithShift?
                )
            }

            else -> unreachable()
        }
    }
}
