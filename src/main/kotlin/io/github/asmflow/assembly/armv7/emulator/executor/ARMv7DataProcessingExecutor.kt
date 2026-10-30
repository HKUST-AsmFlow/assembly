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
            "add" -> execArith(decoded) { rn, op2, _ -> Triple(rn, op2, false) }
            "adc" -> execArith(decoded) { rn, op2, c -> Triple(rn, op2, c) }
            "sub" -> execArith(decoded) { rn, op2, _ -> Triple(rn, op2.inv(), true) }
            "sbc" -> execArith(decoded) { rn, op2, c -> Triple(rn, op2.inv(), c) }
            "rsb" -> execArith(decoded) { rn, op2, _ -> Triple(rn.inv(), op2, true) }
            "rsc" -> execArith(decoded) { rn, op2, c -> Triple(rn.inv(), op2, c) }
            "cmp" -> execArith(decoded, writeResult = false) { rn, op2, _ -> Triple(rn, op2.inv(), true) }
            "cmn" -> execArith(decoded, writeResult = false) { rn, op2, _ -> Triple(rn, op2, false) }
            "and" -> execLogical(decoded) { op1, op2 -> op1 and op2 }
            "eor" -> execLogical(decoded) { op1, op2 -> op1 xor op2 }
            "orr" -> execLogical(decoded) { op1, op2 -> op1 or op2 }
            "bic" -> execLogical(decoded) { op1, op2 -> op1 and op2.inv() }
            "tst" -> execLogical(decoded, writeResult = false) { op1, op2 -> op1 and op2 }
            "teq" -> execLogical(decoded, writeResult = false) { op1, op2 -> op1 xor op2 }
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

    /**
     * Arithmetic operations, all expressed through the spec's AddWithCarry: [operands] maps
     * (Rn, operand2, C) to (x, y, carryIn), e.g. SUB is Rn + NOT(op2) + 1. N and Z come from the
     * result; C and V come from AddWithCarry. CMP / CMN only set the flags.
     */
    private fun execArith(
        inst: DecodedDataProcessingInstruction,
        writeResult: Boolean = true,
        operands: (Int, Int, Boolean) -> Triple<Int, Int, Boolean>
    ) {
        if (writeResult && inst.setFlags && inst.rd == 15) {
            throw EmulationException("${inst.instruction.mnemonic.uppercase()}S to PC (exception return) is not supported")
        }

        val cpsr = registers.getCPSR()
        // Read both operands before writing Rd, which may also be Rn or Rm
        val (x, y, carryIn) = operands(registers.get(inst.rn), inst.operand2.getValue(), cpsr.C)
        val (result, carryOut, overflow) = addWithCarry(x, y, carryIn)

        if (writeResult) registers.set(inst.rd, result)
        if (inst.setFlags) {
            cpsr.N = result < 0
            cpsr.Z = result == 0
            cpsr.C = carryOut
            cpsr.V = overflow
        }
    }

    /**
     * The spec's AddWithCarry(x, y, carry_in): returns (result, carry_out, overflow).
     */
    private fun addWithCarry(x: Int, y: Int, carryIn: Boolean): Triple<Int, Boolean, Boolean> {
        val unsignedSum = (x.toLong() and 0xFFFFFFFFL) + (y.toLong() and 0xFFFFFFFFL) + (if (carryIn) 1 else 0)
        val signedSum = x.toLong() + y.toLong() + (if (carryIn) 1 else 0)
        val result = unsignedSum.toInt()
        return Triple(result, unsignedSum != (result.toLong() and 0xFFFFFFFFL), signedSum != result.toLong())
    }

    /**
     * Logical operations (AND, EOR, ORR, BIC, MOV, MVN). With S set, N and Z come from the result,
     * C is the barrel shifter's carry-out, and V is left unchanged. MOV / MVN ignore Rn.
     * TST / TEQ only set the flags.
     */
    private fun execLogical(
        inst: DecodedDataProcessingInstruction,
        writeResult: Boolean = true,
        operation: (Int, Int) -> Int
    ) {
        if (writeResult && inst.setFlags && inst.rd == 15) {
            throw EmulationException("${inst.instruction.mnemonic.uppercase()}S to PC (exception return) is not supported")
        }

        val op1 = registers.get(inst.rn)
        // Evaluate the shifter once, before writing Rd, which may also be Rm or Rs
        val shifted = inst.operand2.evaluate()

        val result = operation(op1, shifted.result)
        if (writeResult) registers.set(inst.rd, result)
        if (inst.setFlags) {
            val cpsr = registers.getCPSR()
            cpsr.N = result < 0
            cpsr.Z = result == 0
            cpsr.C = shifted.carryOut
        }
    }
}