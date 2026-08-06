package io.github.asmflow.assembly.armv7.emulator

import io.github.asmflow.assembly.emulator.EmulationException

class ARMv7MemoryState(text: List<Int>) {
    private val textWords: IntArray = text.toIntArray()
    private val dataMap: MutableMap<UInt, UByte> = mutableMapOf() // Simulate .data and stack using a hashmap

    private val textEnd: UInt =
        ARMv7AddressSpace.TEXT_BASE + (textWords.size * 4).toUInt()

    private fun inText(addr: UInt): Boolean =
        addr >= ARMv7AddressSpace.TEXT_BASE && addr < textEnd

    private fun inWritable(addr: UInt): Boolean =
        addr >= ARMv7AddressSpace.DATA_BASE && addr < ARMv7AddressSpace.STACK_TOP

    fun canFetch(pc: UInt): Boolean =
        pc % 4u == 0u && inText(pc) && inText(pc + 3u)

    private fun requireWritable(addr: UInt, size: Int) {
        if (!inWritable(addr) || !inWritable(addr + (size - 1).toUInt())) {
            throw EmulationException(
                "Write to non-writable address 0x${addr.toString(16).uppercase()}"
            )
        }
    }

    private fun textByte(addr: UInt): UByte {
        val offset = (addr - ARMv7AddressSpace.TEXT_BASE).toInt()
        val word = textWords[offset / 4].toUInt()
        return ((word shr ((offset % 4) * 8)) and 0xFFu).toUByte()
    }

    fun setByte(addr: UInt, value: UByte) {
        requireWritable(addr, 1)
        dataMap[addr] = value
    }

    fun setHalf(addr: UInt, value: UShort) {
        requireWritable(addr, 2)
        dataMap[addr] = (value and 0xFFu).toUByte()
        dataMap[addr + 1u] = (value.toInt() ushr 8).toUByte()
    }

    fun setWord(addr: UInt, value: UInt) {
        requireWritable(addr, 4)
        dataMap[addr] = (value and 0xFFu).toUByte()
        dataMap[addr + 1u] = (value shr 8 and 0xFFu).toUByte()
        dataMap[addr + 2u] = (value shr 16 and 0xFFu).toUByte()
        dataMap[addr + 3u] = (value shr 24 and 0xFFu).toUByte()
    }

    fun getByte(addr: UInt): UByte = when {
        inText(addr) -> textByte(addr)
        inWritable(addr) -> dataMap[addr] ?: 0u
        else -> 0u
    }

    fun getHalf(addr: UInt): UShort {
        val lo = getByte(addr).toUShort()
        val hi = getByte(addr + 1u).toUShort()
        return (hi.toInt() shl 8 or lo.toInt()).toUShort()
    }

    fun getWord(addr: UInt): UInt {
        val b0 = getByte(addr).toUInt()
        val b1 = getByte(addr + 1u).toUInt()
        val b2 = getByte(addr + 2u).toUInt()
        val b3 = getByte(addr + 3u).toUInt()
        return b0 or (b1 shl 8) or (b2 shl 16) or (b3 shl 24)
    }

    fun fetchInstruction(pc: UInt): Int {
        if (!canFetch(pc)) {
            throw EmulationException("Cannot fetch instruction at 0x${pc.toString(16).uppercase()}")
        }
        return textWords[((pc - ARMv7AddressSpace.TEXT_BASE) / 4u).toInt()]
    }
}
