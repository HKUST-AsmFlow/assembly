package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.assembler.AssemblerError
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive

interface ARMv7DirectiveHandler {
    fun emit(directive: ARMv7Directive, ctx: AssemblerContext, errors: MutableList<AssemblerError>)

    fun size(directive: ARMv7Directive, ctx: AssemblerContext, errors: MutableList<AssemblerError>): Int?
}
