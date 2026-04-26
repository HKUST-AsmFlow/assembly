package io.github.asmflow.assembly.armv7.emulator.decoder

import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.execution.ARMv7InstructionConditionCode
import io.github.asmflow.assembly.emulator.EmulationException

object ARMv7ConditionCodeDecoder {
    fun shouldExecute(instruction: Int, registers: ARMv7RegisterState): Boolean{
        val condBits = (instruction ushr 28) and 0xF
        val code = ARMv7InstructionConditionCode.fromCode(condBits) ?: throw EmulationException("Invalid condition code: $condBits")
        val cpsr = registers.getCPSR()
        return code.checkCondition(cpsr)
    }
}