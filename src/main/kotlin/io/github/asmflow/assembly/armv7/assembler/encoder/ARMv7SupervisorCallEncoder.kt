package io.github.asmflow.assembly.armv7.assembler.encoder

import io.github.asmflow.assembly.armv7.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

/**
 * Object providing support for encoding the supervisor call instruction.
 *
 * `SVC` (called `SWI` before UAL) is encoding A1 in the ARM, section A8.8.229:
 *
 * ```
 *  31   28 27 26 25 24 23                                    0
 * +-------+-----------+----------------------------------------+
 * | cond  | 1  1  1  1|                imm24                    |
 * +-------+-----------+----------------------------------------+
 * ```
 *
 * The immediate is not used by the processor itself -- it is left in the instruction for the
 * handler to read back. AsmFlow follows the ARM Linux EABI convention of writing `svc #0` and
 * passing the service number in a register, so the immediate is conventionally zero, but any
 * 24-bit value assembles.
 */
object ARMv7SupervisorCallEncoder : ARMv7InstructionEncoder {
    private const val SVC_SIGNATURE = 0b1111 shl 24
    private const val IMM24_MAX = 0xFFFFFF

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        if (instruction.baseMnemonic != "svc") {
            throw AssemblySyntaxException(
                "Invalid mnemonic ${instruction.baseMnemonic} for supervisor call instruction."
            )
        }

        if (operands.size != 1) {
            throw AssemblySyntaxException(
                "Invalid syntax for SVC, needed 1 argument, received ${operands.size}."
            )
        }

        val immediate = operands[0].operand as? ARMv7InstructionOperand.Number
            ?: throw AssemblySyntaxException("SVC requires an immediate operand, for example `svc #0`.")

        if (immediate.value !in 0..IMM24_MAX) {
            throw AssemblySyntaxException(
                "SVC immediate ${immediate.value} does not fit in 24 bits (it must be between 0 and $IMM24_MAX)."
            )
        }

        return listOf((instruction.conditionCode.code shl 28) or SVC_SIGNATURE or immediate.value)
    }
}
