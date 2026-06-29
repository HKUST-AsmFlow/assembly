package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7DataProcessingDecoder
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.decoder.DecodedDataProcessingInstruction

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
            "sub" -> execSub(decoded)
            "cmp" -> execCmp(decoded)
            "eor" -> execEor(decoded)
            "orr" -> execOrr(decoded)
            "bic" -> execBic(decoded)
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

    private fun execBic(inst: DecodedDataProcessingInstruction) {}

    private fun execCmp(inst: DecodedDataProcessingInstruction) {}

    private fun execEor(inst: DecodedDataProcessingInstruction) {}

    private fun execSub(inst: DecodedDataProcessingInstruction) {}

    private fun execOrr(inst: DecodedDataProcessingInstruction) {}

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