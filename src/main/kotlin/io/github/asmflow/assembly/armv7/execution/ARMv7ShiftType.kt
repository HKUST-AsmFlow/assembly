package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.assembler.AssemblySyntaxException

enum class ARMv7ShiftType(val code: Int, val description: String) {
    ASR(0b10, "Arithmetic shift right"),
    LSL(0b00, "Logical shift left"),
    LSR(0b01, "Logical shift right"),
    ROR(0b11, "Rotate right"),
    RRX(0b11, "Rotate right extended");

    val shifted = code shl 5

    companion object  {
        fun fromCodeAndImmediate(code: Int, immediate: Int): ARMv7ShiftType? {
            if (code == 0b11 && immediate == 0b00) return RRX
            if (code == 0b11) return ROR
            return entries.firstOrNull{it.code == code}
        }
        fun fromString(value: String): ARMv7ShiftType {
            try {
                val shift = ARMv7ShiftType.valueOf(value.uppercase())
                return shift
            }
            catch (e: IllegalArgumentException){
                throw AssemblySyntaxException("Invalid shift type: $value")
            }
        }
    }
}