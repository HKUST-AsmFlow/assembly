package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.assembler.AssemblerError
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.assembler.context.ProgramSection
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive

object ARMv7AlignDirectiveHandler : ARMv7DirectiveHandler {
    override fun size(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        errors: MutableList<AssemblerError>,
    ): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError(".align is only valid in the data section", directive))
            return null
        }

        val parameters = directive.parameters
        if (parameters.size != 1) {
            errors.add(AssemblerError(".align expects exactly one parameter", directive))
            return null
        }
        val p = parameters[0]
        if (p !is ARMv7DirectiveParameter.Number) {
            errors.add(AssemblerError("invalid parameter type in .align (expected #number)", p.element))
            return null
        }

        if (p.value !in 0..30) {
            errors.add(AssemblerError(".align exponent out of range (expected 0..30)", p.element))
            return null
        }

        val align = 1 shl p.value
        return alignPad(ctx.dataOffsetBytes, align)
    }

    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>,
    ) {
        val n = (directive.parameters[0] as ARMv7DirectiveParameter.Number).value
        val align = 1 shl n
        val pad = alignPad(ctx.data.size, align)
        repeat(pad) { ctx.data.add(0) }
    }
}

object ARMv7SpaceDirectiveHandler : ARMv7DirectiveHandler {
    override fun size(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        errors: MutableList<AssemblerError>,
    ): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError(".space is only valid in the data section", directive))
            return null
        }

        val parameters = directive.parameters
        if (parameters.size != 1) {
            errors.add(AssemblerError(".space expects exactly one parameter", directive))
            return null
        }
        val p = parameters[0]
        if (p !is ARMv7DirectiveParameter.Number) {
            errors.add(AssemblerError("invalid parameter type in .space (expected #number)", p.element))
            return null
        }
        if (p.value < 0) {
            errors.add(AssemblerError(".space size must be non-negative", p.element))
            return null
        }

        return p.value
    }

    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>,
    ) {
        val n = (directive.parameters[0] as ARMv7DirectiveParameter.Number).value
        repeat(n) { ctx.data.add(0) }
    }
}
