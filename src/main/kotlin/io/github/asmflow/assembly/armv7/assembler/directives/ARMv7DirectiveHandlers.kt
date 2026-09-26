package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.util.functional.toOption

object ARMv7DirectiveHandlers {
    private val handlers = mapOf(
        "data" to ARMv7DataSectionSwitchHandler,
        "text" to ARMv7TextSectionSwitchHandler
    )

    fun get(name: String) = handlers[name].toOption()
}
