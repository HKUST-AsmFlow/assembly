package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7FlexibleOffset
import io.github.asmflow.assembly.util.functional.toOption
import io.github.asmflow.assembly.util.unreachable
import kotlin.math.abs

abstract class ARMv7FlexibleOffsetMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7FlexibleOffset {
    override val add: Boolean
        get() = when {
            // #-imm: sign lives inside Number (POUND Sign? NumberLiteral)
            number != null -> (number!!.sign?.multiplier ?: 1) > 0
            // -Rm: sign is a FlexibleOffset child
            else -> (sign?.multiplier ?: 1) > 0
        }

    override val offset: ARMv7InstructionOperand.Offset
        get() = when {
            number != null -> ARMv7InstructionOperand.Offset.NumericalOffset(abs(number!!.value))
            registerWithShift != null -> ARMv7InstructionOperand.Offset.RegisterOffset(
                ARMv7InstructionOperand.Register(
                    registerWithShift!!.register.register,
                    registerWithShift!!.shift?.shift.toOption()
                )
            )
            else -> unreachable()
        }
}
