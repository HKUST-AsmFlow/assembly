package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register
import io.github.asmflow.assembly.assembler.AssemblySyntaxException

object ARMv7BranchExchangeEncoder : ARMv7InstructionEncoder {
    private const val BX_OPCODE = 0x012FFF10

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        if (instruction.baseMnemonic != "bx") {
            throw AssemblySyntaxException("Invalid mnemonic ${instruction.baseMnemonic} for branch exchange instruction.")
        }

        if (operands.size != 1) {
            throw AssemblySyntaxException("Invalid syntax for BX, needed 1 argument, received ${operands.size}.")
        }

        val target = operands[0].operand
        val register = target as? ARMv7InstructionOperand.Register
            ?: throw AssemblySyntaxException("BX requires a register operand.")

        return listOf((instruction.conditionCode.code shl 28) or BX_OPCODE or register.register.getIDSafe())
    }
}
