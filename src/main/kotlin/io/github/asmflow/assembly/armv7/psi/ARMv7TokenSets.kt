package io.github.asmflow.assembly.armv7.psi

import com.intellij.psi.tree.TokenSet
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*

object ARMv7TokenSets {
    val COMMENTS = TokenSet.create(COMMENT)

    val STRINGS = TokenSet.create(STRING)
}
