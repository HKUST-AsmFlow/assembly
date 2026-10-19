package io.github.asmflow.assembly.armv7.assembler.encoder

import io.github.asmflow.assembly.armv7.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.execution.ARMv7Register

/**
 * Encodes BX and BLX (register). Only ARM state is supported, so BLX (immediate), which always
 * switches to Thumb from ARM state, is rejected.
 */
object ARMv7BranchExchangeEncoder : ARMv7InstructionEncoder {
    private const val BX_OPCODE = 0x012FFF10
    private const val BLX_OPCODE = 0x012FFF30

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        val mnemonic = instruction.baseMnemonic
        val opcode = when (mnemonic) {
            "bx" -> BX_OPCODE
            "blx" -> BLX_OPCODE
            else -> throw AssemblySyntaxException("Invalid mnemonic $mnemonic for branch exchange instruction.")
        }

        if (operands.size != 1) {
            throw AssemblySyntaxException("Invalid syntax for ${mnemonic.uppercase()}, needed 1 argument, received ${operands.size}.")
        }

        val target = operands[0].operand
        if (mnemonic == "blx" && target !is ARMv7InstructionOperand.Register) {
            throw AssemblySyntaxException("BLX with a label switches to Thumb state, which is not supported. Use BL.")
        }

        val register = target as? ARMv7InstructionOperand.Register
            ?: throw AssemblySyntaxException("${mnemonic.uppercase()} requires a register operand.")

        if (register.shift.isSome()) {
            throw AssemblySyntaxException("${mnemonic.uppercase()} does not accept a shifted register.")
        }
        if (mnemonic == "blx" && register.register == ARMv7Register.PC) {
            throw AssemblySyntaxException("BLX pc is UNPREDICTABLE.")
        }

        return listOf((instruction.conditionCode.code shl 28) or opcode or register.register.getIDSafe())
    }
}
