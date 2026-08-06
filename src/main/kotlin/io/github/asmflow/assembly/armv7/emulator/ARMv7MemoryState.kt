package io.github.asmflow.assembly.armv7.emulator

class ARMv7MemoryState {
    private val memory: MutableMap<UInt, UByte> = mutableMapOf()

    fun setByte(addr: UInt, value: UByte) {
        memory[addr] = value
    }

    fun setHalf(addr: UInt, value: UShort) {
        memory[addr] = (value and 0xFFu).toUByte()
        memory[addr + 1u] = (value.toInt() ushr 8).toUByte()
    }

    fun setWord(addr: UInt, value: UInt) {
        memory[addr] = (value and 0xFFu).toUByte()
        memory[addr + 1u] = (value shr 8 and 0xFFu).toUByte()
        memory[addr + 2u] = (value shr 16 and 0xFFu).toUByte()
        memory[addr + 3u] = (value shr 24 and 0xFFu).toUByte()
    }

    fun getByte(addr: UInt): UByte = memory[addr] ?: 0.toUByte()

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
}