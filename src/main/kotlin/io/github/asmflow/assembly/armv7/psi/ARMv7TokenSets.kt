package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.tree.TokenSet
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*

object ARMv7TokenSets {
    val COMMENTS = TokenSet.create(COMMENT)

    val DIRECTIVES = TokenSet.create(DIRECTIVE)

    val INSTRUCTIONS = TokenSet.create(MNEMONIC)

    val NUMBERS = TokenSet.create(NUMBER)

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

    val SHIFT_TYPES = TokenSet.create(SHIFT_TYPE)

    val STRINGS = TokenSet.create(STRING)
}
