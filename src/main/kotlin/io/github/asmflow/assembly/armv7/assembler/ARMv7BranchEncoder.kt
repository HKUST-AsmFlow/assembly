package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.assembler.AssemblySyntaxException

/**
 * Encoder for branch instructions.
 *
 * Supports the resolution of 'b' and 'bl' instructions to their correct binary representation
 * by using the input symbol table.
 */
class ARMv7BranchEncoder(val symbols: HashMap<String, Int>) : ARMv7InstructionEncoder {
    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        if (instruction.baseMnemonic == "b" || instruction.baseMnemonic == "bl") {
            if (operands.size != 1) throw AssemblySyntaxException("Invalid syntax for branch mnemonic, needed 1 argument, recieved ${operands.size}.")
            val targetLabel = operands[0].label ?: throw AssemblySyntaxException("Invalid first operand, needed label")
            val targetLocation =
                symbols[targetLabel.text] ?: throw AssemblySyntaxException("Label ${targetLabel.text} does not exist")
            val imm24 =
                (targetLocation - (addrCounter + 2)) and 0xFFFFFF // Clip the the top 8 bits they will be sign extended later
            // No shifting and PC + 2 instead of PC + 8, we are working already in terms
            // of instructions, not bytes

            // TODO detect if location is too far and throw an error, right now it just silently fails
            return listOf((instruction.conditionCode.code shl 28) or (ARMv7InstructionDatabase.getOpcode(instruction.baseMnemonic) shl 24) or imm24)

        }
        throw AssemblySyntaxException("Invalid mnemonic ${instruction.baseMnemonic} for branch type instruction.")
    }
}