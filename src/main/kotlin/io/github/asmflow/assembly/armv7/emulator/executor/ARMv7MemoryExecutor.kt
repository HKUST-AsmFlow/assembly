package io.github.asmflow.assembly.armv7.emulator.executor

import io.github.asmflow.assembly.armv7.emulator.ARMv7MemoryState
import io.github.asmflow.assembly.armv7.emulator.ARMv7RegisterState
import io.github.asmflow.assembly.armv7.emulator.decoder.ARMv7MemoryDecoder
import io.github.asmflow.assembly.armv7.emulator.decoder.DecodedMemoryInstruction
import io.github.asmflow.assembly.emulator.EmulationException

class ARMv7MemoryExecutor(private val registerState: ARMv7RegisterState, private val memoryState: ARMv7MemoryState) {
    private val decoder = ARMv7MemoryDecoder(registerState)

    fun execute(raw: Int) {
        val decoded = decoder.decode(raw)
        when (val mnemonic = decoded.instruction.mnemonic) {
            "ldr" -> execLdr(decoded)
            "str" -> execStr(decoded)
            "ldrb" -> execLdrb(decoded)
            "strb" -> execStrb(decoded)
            "ldrh" -> execLdrh(decoded)
            "strh" -> execStrh(decoded)
            "ldrsb" -> execLdrsb(decoded)
            "ldrsh" -> execLdrsh(decoded)
            else -> when {
                mnemonic.startsWith("ldm") -> execLdm(decoded)
                mnemonic.startsWith("stm") -> execStm(decoded)
                else -> throw EmulationException("Unsupported memory instruction: $mnemonic.")
            }
        }
    }

    private fun execLdr(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = memoryState.getWord(address.toUInt()).toInt()
                registerState.set(inst.transferType.rd, value)
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execStr(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = registerState.get(inst.transferType.rd)
                memoryState.setWord(address.toUInt(), value.toUInt())
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execLdrb(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = memoryState.getByte(address.toUInt()).toInt()
                registerState.set(inst.transferType.rd, value)
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execStrb(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = registerState.get(inst.transferType.rd)
                memoryState.setByte(address.toUInt(), (value and 0xFF).toUByte())
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execLdrh(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = memoryState.getHalf(address.toUInt()).toInt()
                registerState.set(inst.transferType.rd, value)
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execStrh(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = registerState.get(inst.transferType.rd)
                memoryState.setHalf(address.toUInt(), (value and 0xFF).toUShort())
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execLdrsb(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = memoryState.getByte(address.toUInt()).toByte().toInt()
                registerState.set(inst.transferType.rd, value)
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execLdrsh(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.SingleTransfer -> {
                val (address, writebackAddr) = calculateAddress(inst, inst.transferType)
                val value = memoryState.getHalf(address.toUInt()).toShort().toInt()
                registerState.set(inst.transferType.rd, value)
                if (inst.memoryBits.writeBack) {
                    registerState.set(inst.rn, writebackAddr)
                }
            }
            else -> throw EmulationException("Expected single transfer type for ${inst.instruction.mnemonic}; got multiple transfers.")
        }
    }

    private fun execLdm(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.MultiTransfer -> {

            }
            else -> throw EmulationException("Expected multiple transfer type for ${inst.instruction.mnemonic}; got single transfer.")
        }
    }

    private fun execStm(inst: DecodedMemoryInstruction) {
        when (inst.transferType) {
            is DecodedMemoryInstruction.TransferType.MultiTransfer -> {

            }
            else -> throw EmulationException("Expected multiple transfer type for ${inst.instruction.mnemonic}; got single transfer.")
        }
    }

    private fun calculateAddress(inst: DecodedMemoryInstruction, transfer: DecodedMemoryInstruction.TransferType.SingleTransfer): Pair<Int, Int> {
        val base = registerState.get(inst.rn)
        val offset = transfer.operand2.getValue()
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