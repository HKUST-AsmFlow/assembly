package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionOperand
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.assembler.AssemblySyntaxException

class ARMv7MemoryAccessEncoder(val symbols: HashMap<String, Int>) : ARMv7InstructionEncoder {
    fun processLdr(instruction: ARMv7InstructionMixin, operands: List<ARMv7Operand>): Int {
        if (operands.size != 2) {
            throw AssemblySyntaxException("Inncorrect number of operands for LDR instruction: " +
                    "${operands.size} supplied but 2 expected.")
        }
        val (rd, rt) = operands.map { it.operand }
        return ((instruction.conditionCode.code shl 28) or (0b01 shl 26))
    }

    fun processStr(instruction: ARMv7InstructionMixin,): Int {
        return ((instruction.conditionCode.code shl 28) or 0)
    }

    fun processLdm(instruction: ARMv7InstructionMixin,): Int {
        return ((instruction.conditionCode.code shl 28) or 0)
    }

    fun processStm(instruction: ARMv7InstructionMixin,): Int {
        return ((instruction.conditionCode.code shl 28) or 0)
    }

    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>,
        addrCounter: Int
    ): List<Int> {
        TODO("Not yet implemented")
    }
}