package io.github.asmflow.assembly.armv7.execution

enum class ARMv7Register(val id: Int?) {
    R0(0),
    R1(1),
    R2(2),
    R3(3),
    R4(4),
    R5(5),
    R6(6),
    R7(7),
    R8(8),
    R9(9),
    R10(10),
    R12(12),
    FP(11),
    SP(13),
    LR(14),
    PC(15),
    CPSR(null);

    fun getIDSafe(): Int {
        return requireNotNull(this.id) { "Register cannot be CPSR." }
    }
}
