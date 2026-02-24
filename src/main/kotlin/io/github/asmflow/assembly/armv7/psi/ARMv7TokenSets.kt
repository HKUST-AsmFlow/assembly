package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.tree.TokenSet
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*

object ARMv7TokenSets {
    val COMMENTS = TokenSet.create(COMMENT)

    val DIRECTIVES = TokenSet.create(
        ALIGN,
        ASCII,
        ASCIZ,
        BALIGN,
        BALIGNL,
        BALIGNW,
        BYTE,
        COMM,
        DATA,
        DEF,
        DESC,
        DIM,
        DOUBLE,
        EJECT,
        ELSE,
        ENDEF,
        ENDIF,
        EQU,
        EQUIV,
        ERR,
        EXTERN,
        FILE,
        FILL,
        FLOAT,
        GLOBAL
    )

    val INSTRUCTIONS = TokenSet.create(
        ADC,
        ADD,
        ADR,
        AND,
        ASR,
        B,
        BFC,
        BFI,
        BIC,
        BKPT,
        BL,
        BX,
        BXJ,
        CBNZ,
        CBZ,
        CLREX,
        CLZ,
        CMN,
        CMP,
        CSDB,
        DBG,
        DMB,
        DSB,
        EOR,
        ERET,
        HVC,
        ISB,
        LDM,
        LDMFD,
        LDMIA,
        LDMDA,
        LDMFA,
        LDMDB,
        LDMEA,
        LDMIB,
        LDMED,
        LDR,
        LDRB,
        LDRBT,
        LDRD,
        LDREX,
        LDREXB,
        LDREXD,
        LDREXH,
        LDRH,
        LDRHT,
        LDRSB,
        LDRSBT
    )

    val NUMBERS = TokenSet.create(
        POUND,
        BINARY_NUMBER,
        DECIMAL_NUMBER,
        HEXADECIMAL_NUMBER,
        OCTAL_NUMBER
    )

    val REGISTERS = TokenSet.create(
        R0,
        R1,
        R2,
        R3,
        R4,
        R5,
        R6,
        R7,
        R8,
        R9,
        R10,
        R11,
        R12,
        SP,
        LR,
        PC,
        CPSR,
        SPSR
    )

    val SHIFT_TYPES = TokenSet.create(
        LSL,
        LSR,
        ASR,
        ROR,
        RRX
    )

    val STRINGS = TokenSet.create(STRING)
}
