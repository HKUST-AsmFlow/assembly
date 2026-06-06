package io.github.asmflow.assembly.armv7.emulator.executor

import com.jetbrains.rhizomedb.register
import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.emulator.ARMv7DataProcessingDecoder
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.DecodedDataProcessingInstruction
import io.github.asmflow.assembly.armv7.emulator.DecodedOperand2
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Shift

class ARMv7DataProcessingExecutor(private val registers: ARMv7RegisterState) {
    /**
     * Executor that:
     * 1) checks condition
     * 2) decodes data-processing instruction
     * 3) delegates by opcode to execution helpers
     *
     * Assumes ARMv7RegisterState exposes CPSR and has get/set register APIs.
     */
    val decoder: ARMv7DataProcessingDecoder = ARMv7DataProcessingDecoder(registers)

    fun execute(raw: Int) {
        val decoded = decoder.decode(raw)
        when (decoded.instruction.mnemonic) {
            "add" -> execAdd(decoded, withCarry = false)
            "adc" -> execAdd(decoded, withCarry = true)
            "and" -> execAnd(decoded)
        }
    }

    private fun execAdd(inst: DecodedDataProcessingInstruction, withCarry: Boolean) {
        val op1 = registers.get(inst.rn)
        val op2 = inst.operand2.getValue()
        val carryIn = if (withCarry && registers.getCPSR().C) 1 else 0
        val result = op1 + op2 + carryIn
        registers.set(inst.rd, result)
        if (inst.setFlags) {
            updateAddFlags(op1, op2, carryIn, result)
        }
    }

    private fun execAnd(inst: DecodedDataProcessingInstruction) {
        val op1 = registers.get(inst.rn)
        val op2 = inst.operand2.getValue()
        val result = op1 and op2
        registers.set(inst.rd, result)
        if (inst.setFlags) {
            val cpsr = registers.getCPSR()
            cpsr.N = result < 0
            cpsr.Z = result == 0
            cpsr.C = inst.operand2.getCarryOut()
        }
    }

    private fun updateAddFlags(op1: Int, op2: Int, carryIn: Int, result: Int) {
        val cpsr = registers.getCPSR()
        cpsr.N = result < 0
        cpsr.Z = result == 0
        val sumUnsigned = op1.toUInt().toULong() + op2.toUInt().toULong() + carryIn.toULong()
        cpsr.C = sumUnsigned > 0xFFFF_FFFFu
        // signed overflow for addition
        cpsr.V = (((op1 xor result) and (op2 xor result)) < 0)
    }

}