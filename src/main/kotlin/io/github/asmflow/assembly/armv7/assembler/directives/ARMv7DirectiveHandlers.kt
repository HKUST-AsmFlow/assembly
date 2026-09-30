package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.util.functional.toOption

object ARMv7DirectiveHandlers {
    private val handlers = mapOf(
        // size 0
        "data" to ARMv7DataSectionSwitchHandler,
        "text" to ARMv7TextSectionSwitchHandler,

        // size 1 * n
        "byte" to ARMv7ByteDataDirectiveHandler,

        // size 2 * n
        "half" to ARMv7HalfWordDataDirectiveHandler,
        "short" to ARMv7HalfWordDataDirectiveHandler,
    )

    fun get(name: String) = handlers[name].toOption()
}
