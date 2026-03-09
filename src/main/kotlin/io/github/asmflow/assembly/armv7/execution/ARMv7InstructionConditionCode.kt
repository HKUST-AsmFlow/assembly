package io.github.asmflow.assembly.armv7.execution

import io.github.asmflow.assembly.armv7.emulator.ARMv7CPSR

enum class ARMv7InstructionConditionCode(val code: Int, val description: String, val checkCondition: (ARMv7CPSR) ->  Boolean) {
    EQ(0b0000, "Equal", { it.Z }),
    NE(0b0001, "Not equal", { !it.Z }),
    CS(0b0010, "Carry set", { it.C }),
    HS(0b0010, "Unsigned higher or same", { it.C }), // Synonym for CS
    CC(0b0011, "Carry clear", { !it.C }),
    LO(0b0011, "Unsigned lower", { !it.C }),         // Synonym for CC
    MI(0b0100, "Minus, negative", { it.N }),
    PL(0b0101, "Plus, positive or zero", { !it.N }),
    VS(0b0110, "Overflow", { it.V }),
    VC(0b0111, "No overflow", { !it.V }),
    HI(0b1000, "Unsigned higher", { it.C && !it.Z }),
    LS(0b1001, "Unsigned lower or same", { !it.C || it.Z }),
    GE(0b1010, "Signed greater than or equal", { it.N == it.V }),
    LT(0b1011, "Signed less than", { it.N != it.V }),
    GT(0b1100, "Signed greater than", { !it.Z && (it.N == it.V) }),
    LE(0b1101, "Signed less than or equal", { it.Z || (it.N != it.V) }),
    AL(0b1110, "Always (Unconditional)", { true });

    val shifted = code shl 28

    companion object {
        fun fromCode(code: Int): ARMv7InstructionConditionCode? {
            return entries.firstOrNull{it.code == code}
        }
    }
}
