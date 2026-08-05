package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7Shift
import io.github.asmflow.assembly.util.functional.None
import io.github.asmflow.assembly.util.unreachable

abstract class ARMv7ShiftMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Shift {
    override val shift: ARMv7InstructionOperand.Register.Shift
        get() = ARMv7InstructionOperand.Register.Shift(
            shiftType.shiftType,
            when {
                number != null -> number!!.operand
                register != null -> ARMv7InstructionOperand.Register(register!!.register, None)
                else -> unreachable()
            }
        )
}
