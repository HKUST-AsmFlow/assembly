package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.tree.TokenSet
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*

object ARMv7TokenSets {
    val COMMENTS = TokenSet.create(COMMENT)

    val DIRECTIVES = TokenSet.create(DIRECTIVE)

    val INSTRUCTIONS = TokenSet.create(MNEMONIC)

    val NUMBERS = TokenSet.create(NUMBER)

    val REGISTERS = TokenSet.create(REGISTER)

    val SHIFT_TYPES = TokenSet.create(SHIFT_TYPE)

    val STRINGS = TokenSet.create(STRING)
}
