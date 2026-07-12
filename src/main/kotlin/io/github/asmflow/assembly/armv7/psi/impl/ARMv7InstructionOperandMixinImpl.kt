package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand.Offset
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.util.functional.None
import io.github.asmflow.assembly.util.functional.toOption
import io.github.asmflow.assembly.util.unreachable

abstract class ARMv7InstructionOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Operand {
    companion object {
        fun stripRadixPrefix(raw: String): String {
            val s = raw.trim()
            return when {
                s.startsWith("0x", ignoreCase = true) -> s.drop(2)
                s.startsWith("0b", ignoreCase = true) -> s.drop(2)
                s.startsWith("0o", ignoreCase = true) -> s.drop(2)
                s.startsWith("x", ignoreCase = true) -> s.drop(1)
                s.startsWith("b", ignoreCase = true) -> s.drop(1)
                s.startsWith("o", ignoreCase = true) -> s.drop(1)
                else -> s
            }
        }
        fun parseNumericalNode(numberNode: ASTNode): Int {
            val numberPsi = numberNode.findChildByType(ARMv7TokenTypes.BINARY_NUMBER) ?: numberNode.findChildByType(
                ARMv7TokenTypes.DECIMAL_NUMBER
            ) ?: numberNode.findChildByType(ARMv7TokenTypes.HEXADECIMAL_NUMBER) ?: numberNode.findChildByType(
                ARMv7TokenTypes.OCTAL_NUMBER
            )!!
            val isNegative = numberNode.findChildByType(ARMv7TokenTypes.MINUS) != null

            val radix = when (numberPsi.elementType) {
                ARMv7TokenTypes.BINARY_NUMBER -> 2
                ARMv7TokenTypes.DECIMAL_NUMBER -> 10
                ARMv7TokenTypes.HEXADECIMAL_NUMBER -> 16
                ARMv7TokenTypes.OCTAL_NUMBER -> 8
                else -> unreachable()
            }

            return stripRadixPrefix(numberPsi.text).toInt(radix) * (if (isNegative) -1 else 1)
        }

        fun toNumericalOffsetArgs(offsetNode: ASTNode): Pair<ARMv7Register, Offset.NumericalOffset> {
            val registerPsi = offsetNode.findChildByType(ARMv7TokenTypes.REGISTER)!!.psi
            val register = ARMv7Register.entries.find { registerPsi.textMatches(it.name.lowercase()) }!!

            val numNode = offsetNode.findChildByType(ARMv7TokenTypes.NUMBER)
            val num = numNode?.let { parseNumericalNode(it) } ?: 0
            return Pair(register, Offset.NumericalOffset(num))
        }

        fun toRegisterOffsetArgs(offsetNode: ASTNode): Pair<ARMv7Register, Offset.RegisterOffset> {
            val registerPsi = offsetNode.findChildByType(ARMv7TokenTypes.REGISTER)!!.psi
            val register = ARMv7Register.entries.find { registerPsi.textMatches(it.name.lowercase()) }!!

            val registerShift = offsetNode.findChildByType(ARMv7TokenTypes.REGISTER_WITH_SHIFT)!!.psi
            // TODO
            return Pair(register, Offset.RegisterOffset(ARMv7InstructionOperand.Register(register, None)))
        }
    }

    override val operand: ARMv7InstructionOperand by lazy {
        when {
            label != null -> {
                ARMv7InstructionOperand.Label(label = label!!.text)
            }

            number != null -> {
                val numberNode = number!!.node
                val num = parseNumericalNode(numberNode)

                ARMv7InstructionOperand.Number(num)
            }

            offset != null -> {
                val (reg, num) = toNumericalOffsetArgs(offset!!.node)
                ARMv7InstructionOperand.RegisterWithOffset(reg, num,
                    ARMv7InstructionOperand.AddressingFlags(preIndexed = false, postIndexed = false))
            }

            offsetVariant != null -> {
                // Deal with this later
                TODO("Offset variants are not yet supported!")
            }

            postindexed != null -> {
                val (reg, num) = toNumericalOffsetArgs(postindexed!!.node)
                ARMv7InstructionOperand.RegisterWithOffset(reg, num,
                    ARMv7InstructionOperand.AddressingFlags(preIndexed = false, postIndexed = true))
            }

            preindexed != null -> {
                val (reg, num) = toNumericalOffsetArgs(preindexed!!.node)
                ARMv7InstructionOperand.RegisterWithOffset(reg, num,
                    ARMv7InstructionOperand.AddressingFlags(preIndexed = true, postIndexed = false))
            }

            registerWithShift != null -> {
                val registerPsi =
                    registerWithShift!!.node.findChildByType(ARMv7TokenTypes.REGISTER)!!.psi
                val register =
                    ARMv7Register.entries.find { registerPsi.textMatches(it.name.lowercase()) }!!
                val pair = registerWithShift!!.shift.toOption().map { psi ->
                    val shiftType =
                        ARMv7ShiftType.entries.find { psi.shiftType.textMatches(it.name.lowercase()) }!!
                    val shiftBy = when {
                        psi.register != null ->
                            ARMv7InstructionOperand.Register(
                                ARMv7Register.entries.find { psi.register!!.textMatches(it.name.lowercase()) }!!, None
                            )
                        psi.number != null -> ARMv7InstructionOperand.Number(parseNumericalNode(psi.number!!.node))
                        else -> unreachable()
                    }
                    Pair(shiftType, shiftBy)
                }

                ARMv7InstructionOperand.Register(
                    register = register,
                    shift = pair.map { ARMv7InstructionOperand.Register.Shift(it.first, it.second) }
                )
            }

            else -> unreachable()
        }
    }
}
