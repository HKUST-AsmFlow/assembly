package io.github.asmflow.assembly.armv7.assembler

import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7OperandUtils.isNumber
import io.github.asmflow.assembly.armv7.assembler.utils.ARMv7OperandUtils.isShiftlessRegister
import io.github.asmflow.assembly.armv7.psi.ARMv7InstructionMixin
import io.github.asmflow.assembly.armv7.psi.ARMv7Operand
import io.github.asmflow.assembly.assembler.AssemblySyntaxException


object Armv7DataProcessingEncoder: Armv7InstructionEncoder {


    override fun encode(
        instruction: ARMv7InstructionMixin,
        operands: List<ARMv7Operand>
    ): Int {
        val cond = instruction.conditionCode
        val sBit = if (instruction.setsFlags) 1 else 0

        // For now, make the following assumptions:
        // 1. All data processing instructions have [27:26] = 00 (by definition)
        // 2. All instructions can be executed conditionally, so always parse the instruction codes into bits [31: 28]
        // 3. The S suffix encoded as [20] is either supported (use the sBit variable), or not supported (forced as either 0 or 1)
        // 4. Ignore certain instructions such as MUL, LDRH, BX, MOVW etc. that use [27:26] = 00 but do not have the standard format for now.
        // 5. All instructions require at least one register. For instructions that require two, they are written as Rd, Rn and encoded Rn-Rd till bit 12.
        // Otherwise, for instructions that require only one register, either Rn or Rd must be encoded as 0b0000.
        // 6. Bits [11: 0] are the complicated part. For now, we assume each instruction has one of the following 3 encodings:
        // a) The bottom 12 bits are an immediate. The I bit must be set to 1 and, it is split into an 8 bit immediate and 4 bit rotate right multipler.
        // b) Register shift encoding: Bottom 12 bits specify another register, type of shift, and 5 bit immediate for shift amount
        // c) Register shifted register: Bottom 12 bits specify a register, which is shifted by the amount specified in another register, according to a certain type.
        operands ?: throw AssemblySyntaxException("No operands in instruction ${instruction.text}")
        if (operands.size !in 1..3) throw AssemblySyntaxException("Too few or too little operands in instruction ${instruction.text}") // Register with shift is encoded as one operand
        if (!operands[0].isShiftlessRegister()) throw AssemblySyntaxException("First operand ${operands[0].text} in instruction ${instruction.text} must be a register without shift")

        operands[0].isShiftlessRegister()
        // Check for case a: Instruction typically written as MNEMONIC{S}{<c>} {<Rd>,} <Rn>, #<const>
        if (operands.size == 3 && operands[1].isShiftlessRegister() && operands[2].isNumber()) {
            //throw Exception("Case 1")
        }
        if (operands.size == 2 && operands[1].isNumber()){
            //throw Exception("Case 2")
        }
        //throw Exception("Case 3")

        return 0

    }
}