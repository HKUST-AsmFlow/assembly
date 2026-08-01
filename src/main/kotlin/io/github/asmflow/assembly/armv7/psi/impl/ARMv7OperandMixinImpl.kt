package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand.Offset
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.util.functional.None
import io.github.asmflow.assembly.util.functional.toOption
import io.github.asmflow.assembly.util.unreachable
import kotlin.math.abs

abstract class ARMv7OperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Operand {
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

        fun parseRegisterNode(registerNode: ASTNode): ARMv7Register {
            if (registerNode.findChildByType(ARMv7TokenTypes.MINUS) != null) {
                throw AssemblySyntaxException("Signed base/index registers are not supported in memory addressing.")
            }

            val regToken = registerNode.findChildByType(ARMv7TokenTypes.REG)?.psi
                ?: throw AssemblySyntaxException("Invalid register operand.")
            return ARMv7Register.entries.find { regToken.textMatches(it.name.lowercase()) }
                ?: throw AssemblySyntaxException("Unknown register ${regToken.text}.")
        }

        fun parseRegisterWithShiftNode(registerWithShiftNode: ASTNode): ARMv7InstructionOperand.Register {
            val registerNode = registerWithShiftNode.findChildByType(ARMv7TokenTypes.REGISTER)
                ?: throw AssemblySyntaxException("Register offset expected.")
            val register = parseRegisterNode(registerNode)

            val shiftNode =
                registerWithShiftNode.findChildByType(ARMv7TokenTypes.SHIFT) ?: return ARMv7InstructionOperand.Register(
                    register,
                    None
                )

            val shiftTypePsi = shiftNode.findChildByType(ARMv7TokenTypes.SHIFT_TYPE)?.psi
                ?: throw AssemblySyntaxException("Shift type expected for register offset.")
            val shiftType = ARMv7ShiftType.entries.find { shiftTypePsi.textMatches(it.name.lowercase()) }
                ?: throw AssemblySyntaxException("Unknown shift type ${shiftTypePsi.text}.")

            val shiftBy = when {
                shiftNode.findChildByType(ARMv7TokenTypes.NUMBER) != null ->
                    ARMv7InstructionOperand.Number(parseNumericalNode(shiftNode.findChildByType(ARMv7TokenTypes.NUMBER)!!))
                shiftNode.findChildByType(ARMv7TokenTypes.REGISTER) != null ->
                    ARMv7InstructionOperand.Register(parseRegisterNode(shiftNode.findChildByType(ARMv7TokenTypes.REGISTER)!!), None)
                else -> throw AssemblySyntaxException("Invalid shift amount in register offset.")
            }

            return ARMv7InstructionOperand.Register(
                register = register,
                shift = ARMv7InstructionOperand.Register.Shift(shiftType, shiftBy).toOption(),
            )
        }

        fun toNumericalOffsetArgs(offsetNode: ASTNode): Pair<ARMv7Register, Offset.NumericalOffset> {
            val registerNode = offsetNode.findChildByType(ARMv7TokenTypes.REGISTER)
                ?: throw AssemblySyntaxException("Base register expected for memory addressing.")
            val register = parseRegisterNode(registerNode)

            val flexibleOffsetNode = offsetNode.findChildByType(ARMv7TokenTypes.FLEXIBLE_OFFSET)
            val numNode = flexibleOffsetNode?.findChildByType(ARMv7TokenTypes.NUMBER)
            val num = numNode?.let { parseNumericalNode(it) } ?: 0
            return Pair(register, Offset.NumericalOffset(abs(num)))
        }

        fun toRegisterOffsetArgs(offsetNode: ASTNode): Pair<ARMv7Register, Offset.RegisterOffset> {
            val registerNode = offsetNode.findChildByType(ARMv7TokenTypes.REGISTER)
                ?: throw AssemblySyntaxException("Base register expected for memory addressing.")
            val baseRegister = parseRegisterNode(registerNode)

            val flexibleOffsetNode = offsetNode.findChildByType(ARMv7TokenTypes.FLEXIBLE_OFFSET)
            val registerShiftNode = flexibleOffsetNode?.findChildByType(ARMv7TokenTypes.REGISTER_WITH_SHIFT)
                ?: throw AssemblySyntaxException("Register offset expected.")
            val indexRegister = parseRegisterWithShiftNode(registerShiftNode)
            return Pair(baseRegister, Offset.RegisterOffset(indexRegister))
        }

        fun parseAddressingMode(offsetNode: ASTNode): Triple<Boolean, Boolean, Boolean> {
            val preIndexed = offsetNode.elementType == ARMv7TokenTypes.PREINDEXED
            val writeBack = !preIndexed || offsetNode.findChildByType(ARMv7TokenTypes.BANG) != null

            val flexibleOffsetNode = offsetNode.findChildByType(ARMv7TokenTypes.FLEXIBLE_OFFSET)
            val numNode = flexibleOffsetNode?.findChildByType(ARMv7TokenTypes.NUMBER)
            if (numNode != null) {
                val signedOffset = parseNumericalNode(numNode)
                return Triple(preIndexed, signedOffset >= 0, writeBack)
            }

            val registerOffsetNode = flexibleOffsetNode
            val add = registerOffsetNode?.text?.trim()?.startsWith("-") != true
            return Triple(preIndexed, add, writeBack)
        }
    }

    override val operand: ARMv7InstructionOperand by lazy {
        when {
            label != null -> ARMv7InstructionOperand.Label(label = label!!.text)
            number != null -> number!!.operand
            registerWithShift != null -> registerWithShift!!.operand

            postindexed != null -> {
                val postindexedNode = postindexed!!.node
                val (preIndexed, add, writeBack) = parseAddressingMode(postindexedNode)
                val flexibleOffsetNode = postindexedNode.findChildByType(ARMv7TokenTypes.FLEXIBLE_OFFSET)
                val numericalOffset = flexibleOffsetNode == null ||
                    flexibleOffsetNode.findChildByType(ARMv7TokenTypes.NUMBER) != null
                val (reg, offset) = if (numericalOffset) {
                    toNumericalOffsetArgs(postindexedNode)
                } else {
                    toRegisterOffsetArgs(postindexedNode)
                }
                ARMv7InstructionOperand.RegisterWithOffset(
                    reg,
                    offset,
                    ARMv7InstructionOperand.AddressingFlags(preIndexed = preIndexed, add = add, writeBack = writeBack),
                )
            }

            preindexed != null -> {
                val preindexedNode = preindexed!!.node
                val (preIndexed, add, writeBack) = parseAddressingMode(preindexedNode)
                val flexibleOffsetNode = preindexedNode.findChildByType(ARMv7TokenTypes.FLEXIBLE_OFFSET)
                val numericalOffset = flexibleOffsetNode == null ||
                    flexibleOffsetNode.findChildByType(ARMv7TokenTypes.NUMBER) != null
                val (reg, offset) = if (numericalOffset) {
                    toNumericalOffsetArgs(preindexedNode)
                } else {
                    toRegisterOffsetArgs(preindexedNode)
                }
                ARMv7InstructionOperand.RegisterWithOffset(
                    reg,
                    offset,
                    ARMv7InstructionOperand.AddressingFlags(preIndexed = preIndexed, add = add, writeBack = writeBack),
                )
            }

            else -> unreachable()
        }
    }
}
