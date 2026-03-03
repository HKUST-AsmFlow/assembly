package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.util.functional.toOption

abstract class ARMv7InstructionOperandMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Operand {
    override lateinit var operand: ARMv7InstructionOperand

    init {
        registerWithShift.toOption().map {
            // todo: extract register
            ARMv7InstructionOperand.Register(kind = ARMv7Register.R1, it.shift.toOption())
        }.alsoIfSome {
            operand = it
        }
    }
}
