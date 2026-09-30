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
        val bytes = directive.parameters.map { (it to (it as ARMv7DirectiveParameter.Number).value) }
        bytes.find { it.second !in -128..255 }?.let {
            errors.add(AssemblerError("(unsigned) byte value out of range", it.first.element))
            return
        }

        ctx.data.addAll(bytes.map { it.second.toByte() })
    }

    override fun size(directive: ARMv7Directive, ctx: AssemblerContext, errors: MutableList<AssemblerError>): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError("the byte directive is only valid in data segment", directive))
            return null
        }

        val parameters = directive.parameters
        if (parameters.isEmpty()) {
            errors.add(AssemblerError("no parameters passed to byte directive", directive))
            return null
        }
        parameters.find { it !is ARMv7DirectiveParameter.Number }?.let {
            errors.add(AssemblerError("invalid parameter type in byte directive", it.element))
            return null
        }

        return parameters.size
    }
}

object ARMv7HalfWordDataDirectiveHandler : ARMv7DirectiveHandler {
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
        if (parameters.isEmpty()) {
            errors.add(AssemblerError("no parameters passed to half word directive", directive))
            return null
        }
        parameters.find { it !is ARMv7DirectiveParameter.Number }?.let {
            errors.add(AssemblerError("invalid parameter type in half word directive", it.element))
            return null
        }

        TODO()
    }
}
