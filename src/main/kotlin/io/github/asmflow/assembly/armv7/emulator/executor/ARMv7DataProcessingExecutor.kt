package io.github.asmflow.assembly.armv7.emulator.executor

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
        fun execute(raw: Int) {
            val decoded = ARMv7DataProcessingDecoder.decode(raw)
            if (!decoded.condition.checkCondition(registers.getCPSR())) {
                return // conditional execution failed: instruction is skipped
            }
            when (decoded.instruction.mnemonic) {
                "add" -> execAdd(decoded, withCarry = false)
                "adc" -> execAdd(decoded, withCarry = true)
               "and" -> execAnd(decoded)
            }
        }
        private fun execAdd(inst: DecodedDataProcessingInstruction, withCarry: Boolean) {
            val op1 = registers.get(inst.rn)
            val op2 = resolveOperand2Value(inst.operand2)
            val carryIn = if (withCarry && registers.getCPSR().C) 1 else 0
            val result = op1 + op2 + carryIn
            registers.set(inst.rd, result)
            if (inst.setFlags) {
                updateAddFlags(op1, op2, carryIn, result)
            }
        }
        private fun execAnd(inst: DecodedDataProcessingInstruction) {
            val op1 = registers.get(inst.rn)
            val op2 = resolveOperand2Value(inst.operand2)
            val result = op1 and op2
            registers.set(inst.rd, result)
            if (inst.setFlags) {
                val cpsr = registers.getCPSR()
                cpsr.N = result < 0
                cpsr.Z = result == 0
                // C for logical ops with shifted register can come from shifter carry-out.
                // For this basic demo, leave C unchanged.
                // V unchanged.
            }
        }
        private fun resolveOperand2Value(op2: DecodedOperand2): Int {
            return when (op2) {
                is DecodedOperand2.Immediate -> op2.imm32
                is DecodedOperand2.Register -> {
                    val rmVal = registers.get(op2.rm)
                    applyImmediateShift(rmVal, op2.shiftType, op2.shiftImm)
                }
            }
        }
        private fun applyImmediateShift(value: Int, type: ARMv7ShiftType, amount: Int): Int {
            return when (type) {
                ARMv7ShiftType.LSL -> if (amount == 0) value else value shl amount
                ARMv7ShiftType.LSR -> {
                    val a = if (amount == 0) 32 else amount
                    value ushr a
                }
                ARMv7ShiftType.ASR -> {
                    val a = if (amount == 0) 32 else amount
                    value shr a
                }
                ARMv7ShiftType.ROR -> {
                    val a = if (amount == 0) 1 else amount // amount 0 with code=11 is RRX in ARM
                    Integer.rotateRight(value, a)
                }
                ARMv7ShiftType.RRX -> {
                    // Basic implementation; if you want exact carry behavior wire CPSR.C in/out.
                    val cIn = if (registers.getCPSR().C) 1 else 0
                    (value ushr 1) or (cIn shl 31)
                }
            }
        }
        private fun updateAddFlags(op1: Int, op2: Int, carryIn: Int, result: Int) {
            val cpsr = registers.getCPSR()
            cpsr.N = result < 0
            cpsr.Z = result == 0
            val sumUnsigned = op1.toUInt().toULong() + op2.toUInt().toULong() + carryIn.toULong()
            cpsr.C = sumUnsigned > 0xFFFF_FFFFu
            // signed overflow for addition
            val op2WithCarry = op2 + carryIn
            cpsr.V = (((op1 xor result) and (op2WithCarry xor result)) < 0)
        }


}