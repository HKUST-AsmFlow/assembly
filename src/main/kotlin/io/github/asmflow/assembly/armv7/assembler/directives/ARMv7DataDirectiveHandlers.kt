package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.assembler.AssemblerError
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.assembler.context.ProgramSection
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive

object ARMv7ByteDataDirectiveHandler : ARMv7DirectiveHandler {
    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>
    ) {
    }

    override fun size(directive: ARMv7Directive, ctx: AssemblerContext, errors: MutableList<AssemblerError>): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError("the byte directive is only valid in data segment", directive))
            return null
        }

        val parameters = directive.parameters
        parameters.find { it !is ARMv7DirectiveParameter.Number }?.let {
            errors.add(AssemblerError("invalid parameter type in byte directive", it.element))
            return null
        }

        return parameters.size
    }
}
