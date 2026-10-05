package io.github.asmflow.assembly.armv7.assembler.encoder

import io.github.asmflow.assembly.armv7.assembler.AssemblySyntaxException
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.emulator.ARMv7AddressSpace
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand

/**
 * Encoder for branch instructions.
 *
 * Supports the resolution of 'b' and 'bl' instructions to their correct binary representation
 * by using the input symbol table.
 */
class ARMv7BranchEncoder(val symbols: Map<String, UInt>) : ARMv7InstructionEncoder {
    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        if (instruction.baseMnemonic == "b" || instruction.baseMnemonic == "bl") {
            if (operands.size != 1) throw AssemblySyntaxException("Invalid syntax for branch mnemonic, needed 1 argument, recieved ${operands.size}.")
            val targetLabel = operands[0].label ?: throw AssemblySyntaxException("Invalid first operand, needed label")
            val target =
                symbols[targetLabel.text] ?: throw AssemblySyntaxException("Label ${targetLabel.text} does not exist")

            val addr = ARMv7AddressSpace.TEXT_BASE.addr + (addrCounter * 4).toUInt()
            val pc = addr + 8u

            val byteDisplacement = target.toInt() - pc.toInt()
            if (byteDisplacement % 4 != 0) {
                throw AssemblySyntaxException("Branch target is not aligned on a word boundary")
            }

            val wordDisplacement = byteDisplacement / 4

            if (wordDisplacement < -(1 shl 23) || wordDisplacement > (1 shl 23) - 1) {
                throw AssemblySyntaxException("Branch target out of range")
            }

            val imm24 = wordDisplacement and 0xFFFFFF  // Clip the top 8 bits they will be sign extended later
            return listOf((instruction.conditionCode.code shl 28) or (ARMv7InstructionDatabase.getOpcode(instruction.baseMnemonic) shl 24) or imm24)
        }

        throw AssemblySyntaxException("Invalid mnemonic ${instruction.baseMnemonic} for branch type instruction.")
    }
}