package io.github.asmflow.assembly.armv7.emulator.decoder

import io.github.asmflow.assembly.armv7.database.ARMv7InstructionDatabase
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.execution.ARMv7ShiftType
import io.github.asmflow.assembly.emulator.EmulationException

data class MemoryControlBits(val preIdx: Boolean, val add: Boolean, val writeBack: Boolean)
sealed class MemoryOperand2 {
    abstract fun getValue(): Int
    data class Immediate(val imm12: Int): MemoryOperand2() {
        override fun getValue(): Int = imm12
    }
    // Same as data processing!
    data class Register(
        val rm: Int,
        val shiftType: ARMv7ShiftType,
        val shiftImm: Int,
        private val registers: ARMv7RegisterState
    ) : MemoryOperand2() {
        override fun getValue(): Int {
            val result = shiftType.shift(registers.get(rm), shiftImm, registers.getCPSR().C)
            return result.result
        }
    }
}

data class DecodedMemoryInstruction(
    val instruction: ARMv7InstructionDatabase.Instruction,
    val memoryBits: MemoryControlBits,
    val rn: Int,
    val transferType: TransferType,
) {
    sealed class TransferType {
        data class SingleTransfer(val rd: Int, val operand2: MemoryOperand2) : TransferType()
        data class MultiTransfer(val registerMask: Int) : TransferType()
    }
}

class ARMv7MemoryDecoder(private val registers: ARMv7RegisterState) {
    fun extractMemoryBits(instruction: Int, isSingle: Boolean = true): MemoryControlBits {
        val preIdx = ((instruction shr 24) and 1) != 0
        val add = ((instruction shr 23) and 1) != 0
        val w = ((instruction shr 21) and 1) != 0
        // ARM: wback = (P == '0') || (W == '1') for single transfers. Post-index encodes P=0,W=0.
        // For multi-transfer, the single w bit is sufficient.
        val writeBack = if (isSingle) !preIdx || w else w
        return MemoryControlBits(preIdx, add, writeBack)
    }

    fun decode(raw: Int): DecodedMemoryInstruction = when ((raw ushr 25) and 0b111) {
        // LDM / STM
        0b100 -> decodeBlockTransfer(raw)
        // Assume all delegated here are memory ops; we exclude MUL / SWP outside prior
        // Halfword / signed byte variants
        0b000 -> decodeMiscTransfer(raw)
        // LDR / STR / LDRB / STRB
        else -> decodeSingleTransfer(raw)
    }

    private fun decodeBlockTransfer(raw: Int): DecodedMemoryInstruction {
        val preIdx = ((raw shr 24) and 1) != 0
        val isIncrement = ((raw shr 23) and 1) != 0
        val isLoad = ((raw shr 20) and 1) != 0

        val mnemonic = toMultiMnemonic(preIdx, isIncrement, isLoad)
        val instruction = ARMv7InstructionDatabase.get(mnemonic)
            .unwrapOrThrow { EmulationException("Unknown memory instruction: $mnemonic") }

        val rn = (raw shr 16) and 0xF
        val memoryBits = extractMemoryBits(raw, false)
        val multiMask = raw and 0xFFFF
        return DecodedMemoryInstruction(
            instruction = instruction,
            memoryBits = memoryBits,
            rn = rn,
            DecodedMemoryInstruction.TransferType.MultiTransfer(multiMask)
        )
    }

    private fun decodeMiscTransfer(raw: Int): DecodedMemoryInstruction {
        val isImmediate = ((raw shr 22) and 1) != 0
        val isLoad = ((raw shr 20) and 1) != 0

        val mnemonic = toMiscMnemonic((raw shr 5) and 0b11, isLoad)
        val instruction = ARMv7InstructionDatabase.get(mnemonic)
            .unwrapOrThrow { EmulationException("Unknown memory instruction: $mnemonic") }

        val memoryBits = extractMemoryBits(raw)
        val rn = (raw shr 16) and 0xF
        val rt = (raw shr 12) and 0xF

        val operand2 = if (isImmediate) {
            MemoryOperand2.Immediate(
                (((raw shr 8) and 0xF) shl 4) // imm4H
                        or (raw and 0xF) // imm4L
            )
        } else {
            MemoryOperand2.Register(
                rm = raw and 0xF,
                ARMv7ShiftType.LSL, 0, // Implies no shift possible
                registers = registers
            )
        }

        return DecodedMemoryInstruction(
            instruction = instruction,
            memoryBits = memoryBits,
            rn = rn,
            DecodedMemoryInstruction.TransferType.SingleTransfer(rt, operand2)
        )
    }

    private fun decodeSingleTransfer(raw: Int): DecodedMemoryInstruction {
        // A1 word LDR/STR: [27:25]=010 immediate, [27:25]=011 register -> I/bit25=0 means imm
        val isImmediate = ((raw shr 25) and 1) == 0
        val isByte = ((raw shr 22) and 1) != 0
        val isLoad = ((raw shr 20) and 1) != 0
        
        val mnemonic = toSingleMnemonic(isByte, isLoad)
        val instruction = ARMv7InstructionDatabase.get(mnemonic)
            .unwrapOrThrow { EmulationException("Unknown memory instruction: $mnemonic") }
        
        val memoryBits = extractMemoryBits(raw)
        val rn = (raw shr 16) and 0xF
        val rt = (raw shr 12) and 0xF
        
        val operand2 = if (isImmediate) MemoryOperand2.Immediate(raw and 0xFFF) else decodeRegisterOperand2(raw and 0xFFF)
        
        return DecodedMemoryInstruction(
            instruction = instruction,
            memoryBits = memoryBits,
            rn = rn,
            DecodedMemoryInstruction.TransferType.SingleTransfer(rt, operand2)
        )
    }
    
    private fun toSingleMnemonic(isByte: Boolean, isLoad: Boolean): String = when {
        !isByte && isLoad -> "LDR"
        !isByte && !isLoad -> "STR"
        isByte && isLoad -> "LDRB"
        isByte && !isLoad -> "STRB" // For clarity; should always be true at this point
        else -> throw EmulationException("Invalid memory instruction encoding.")
    }

    private fun toMultiMnemonic(preIndex: Boolean, isIncrement: Boolean, isLoad: Boolean): String {
        val base = if (isLoad) "ldm" else "stm"
        return base + when {
            preIndex && isIncrement -> "ib"
            !preIndex && isIncrement -> ""
            preIndex && !isIncrement -> "db"
            !preIndex && !isIncrement -> "da" // For clarity
            else -> throw EmulationException("Invalid memory instruction encoding.")
        }
    }

    private fun toMiscMnemonic(opType: Int, isLoad: Boolean): String = when {
        opType == 0b01 && isLoad -> "ldrh"
        opType == 0b01 && !isLoad -> "strh"
        opType == 0b10 && isLoad -> "ldrsb"
        opType == 0b11 && isLoad -> "ldrsh"
        else -> throw EmulationException("Invalid memory instruction encoding.")
    }

    private fun decodeRegisterOperand2(op2: Int): MemoryOperand2.Register {
        val bit4 = (op2 shr 4) and 1
        if (bit4 == 1) {
            throw EmulationException("RSR form not supported for memory instructions (bit4=1)")
        }
        
        val rm = op2 and 0xF
        val shiftTypeCode = (op2 shr 5) and 0b11
        val shiftImm = (op2 shr 7) and 0x1F
        val shiftType = ARMv7ShiftType.fromCodeAndImmediate(shiftTypeCode, shiftImm)
            ?: throw EmulationException("Invalid shift type code=$shiftTypeCode shiftImm=$shiftImm")
        
        return MemoryOperand2.Register(
            rm = rm,
            shiftType = shiftType,
            shiftImm = shiftImm,
            registers = registers
        )
    }
}