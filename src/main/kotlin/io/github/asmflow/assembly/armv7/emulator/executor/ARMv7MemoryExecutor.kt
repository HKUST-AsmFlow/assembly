package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7MemoryDecoder
import io.github.asmflow.assembly.armv7.emulator.decoder.DecodedMemoryInstruction

class ARMv7MemoryExecutor(private val registerState: ARMv7RegisterState, private val memoryState: ARMv7MemoryState) {
    private val decoder = ARMv7MemoryDecoder(registerState)

    fun execute(raw: Int) {
        val decoded = decoder.decode(raw)
        when (decoded.instruction.mnemonic) {
            "ldr" -> execLdr(decoded)
            "str" -> execStr(decoded)
            "ldrb" -> execLdrb(decoded)
            "strb" -> execStrb(decoded)
        }
    }

    private fun execLdr(inst: DecodedMemoryInstruction) {
        val (address, writebackAddr) = calculateAddress(inst)
        val value = memoryState.getWord(address.toUInt()).toInt()
        registerState.set(inst.rd, value)
        if (inst.memoryBits.writeBack) {
            registerState.set(inst.rn, writebackAddr)
        }
    }

    private fun execStr(inst: DecodedMemoryInstruction) {
        val (address, writebackAddr) = calculateAddress(inst)
        val value = registerState.get(inst.rd)
        memoryState.setWord(address.toUInt(), value.toUInt())
        if (inst.memoryBits.writeBack) {
            registerState.set(inst.rn, writebackAddr)
        }
    }

    private fun execLdrb(inst: DecodedMemoryInstruction) {
        val (address, writebackAddr) = calculateAddress(inst)
        val value = memoryState.getByte(address.toUInt()).toInt()
        registerState.set(inst.rd, value)
        if (inst.memoryBits.writeBack) {
            registerState.set(inst.rn, writebackAddr)
        }
    }

    private fun execStrb(inst: DecodedMemoryInstruction) {
        val (address, writebackAddr) = calculateAddress(inst)
        val value = registerState.get(inst.rd)
        memoryState.setByte(address.toUInt(), (value and 0xFF).toUByte())
        if (inst.memoryBits.writeBack) {
            registerState.set(inst.rn, writebackAddr)
        }
    }

    private fun calculateAddress(inst: DecodedMemoryInstruction): Pair<Int, Int> {
        val base = registerState.get(inst.rn)
        val offset = inst.operand2.getValue()
        val signedOffset = if (inst.memoryBits.add) offset else -offset

        return when {
            // Offset mode: P=1, W=0
            inst.memoryBits.preIdx && !inst.memoryBits.writeBack -> Pair(base + signedOffset, base)
            // Pre-indexed: P=1, W=1
            inst.memoryBits.preIdx && inst.memoryBits.writeBack -> Pair(base + signedOffset, base + signedOffset)
            // Post-indexed: P=0, W=1
            !inst.memoryBits.preIdx && inst.memoryBits.writeBack -> Pair(base, base + signedOffset)
            else -> Pair(base, base)
        }
    }
}