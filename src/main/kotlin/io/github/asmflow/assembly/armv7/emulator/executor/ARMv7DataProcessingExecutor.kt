package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7DataProcessingDecoder
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.EmulationException
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
            "and" -> execLogical(decoded) { op1, op2 -> op1 and op2 }
            "sub" -> execSub(decoded)
            "cmp" -> execCmp(decoded)
            "eor" -> execLogical(decoded) { op1, op2 -> op1 xor op2 }
            "orr" -> execLogical(decoded) { op1, op2 -> op1 or op2 }
            "bic" -> execLogical(decoded) { op1, op2 -> op1 and op2.inv() }
            "mov" -> execLogical(decoded) { _, op2 -> op2 }
            "mvn" -> execLogical(decoded) { _, op2 -> op2.inv() }
            "movw" -> registers.set(decoded.rd, decoded.operand2.getValue())
            "movt" -> {
                val low = registers.get(decoded.rd) and 0xFFFF
                registers.set(decoded.rd, low or (decoded.operand2.getValue() shl 16))
            }
            // Fail loudly: a silently skipped instruction is how missing MOV went unnoticed
            else -> throw EmulationException("Data-processing instruction ${decoded.instruction.mnemonic} is not executable yet")
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

    /**
     * Logical operations (AND, EOR, ORR, BIC, MOV, MVN). With S set, N and Z come from the result,
     * C is the barrel shifter's carry-out, and V is left unchanged. MOV / MVN ignore Rn.
     */
    private fun execLogical(inst: DecodedDataProcessingInstruction, operation: (Int, Int) -> Int) {
        if (inst.setFlags && inst.rd == 15) {
            throw EmulationException("${inst.instruction.mnemonic.uppercase()}S to PC (exception return) is not supported")
        }

        val op1 = registers.get(inst.rn)
        // Evaluate the shifter once, before writing Rd, which may also be Rm or Rs
        val shifted = inst.operand2.evaluate()

        val result = operation(op1, shifted.result)
        registers.set(inst.rd, result)
        if (inst.setFlags) {
            val cpsr = registers.getCPSR()
            cpsr.N = result < 0
            cpsr.Z = result == 0
            cpsr.C = shifted.carryOut
        }
    }

    private fun execCmp(inst: DecodedDataProcessingInstruction) {
        val op1 = registers.get(inst.rn)
        val op2 = inst.operand2.getValue()

        val result = op1 - op2
        val cpsr = registers.getCPSR()
        cpsr.N = result < 0
        cpsr.Z = result == 0

        val unsignedOp1 = op1.toLong() and 0xFFFFFFFFL
        val unsignedOp2 = op2.toLong() and 0xFFFFFFFFL
        cpsr.C = unsignedOp1 >= unsignedOp2

        cpsr.V = ((op1 xor op2) < 0) && ((op1 xor result) < 0)
    }

    private fun execSub(inst: DecodedDataProcessingInstruction) {
        val op1 = registers.get(inst.rn)
        val op2 = inst.operand2.getValue()

        val result = op1 - op2
        registers.set(inst.rd, result)

        if (inst.setFlags) {
            val cpsr = registers.getCPSR()
            cpsr.N = result < 0
            cpsr.Z = result == 0

            val unsignedOp1 = op1.toLong() and 0xFFFFFFFFL
            val unsignedOp2 = op2.toLong() and 0xFFFFFFFFL
            cpsr.C = unsignedOp1 >= unsignedOp2

            cpsr.V = ((op1 xor op2) < 0) && ((op1 xor result) < 0)
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