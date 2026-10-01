package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.assembler.AssemblerError
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.assembler.context.ProgramSection
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive

object ARMv7StringDirectiveHandler : ARMv7DirectiveHandler {
    override fun size(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        errors: MutableList<AssemblerError>,
    ): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError(".ascii is only valid in the data section", directive))
            return null
        }

        val parameters = directive.parameters
        if (parameters.isEmpty()) {
            errors.add(AssemblerError("no parameters passed to .ascii", directive))
            return null
        }
        parameters.find { it !is ARMv7DirectiveParameter.StringLit }?.let {
            errors.add(AssemblerError("invalid parameter type in .ascii (expected string)", it.element))
            return null
        }

        return parameters.sumOf { (it as ARMv7DirectiveParameter.StringLit).value.length }
    }

    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>,
    ) {
        directive.parameters
            .map { (it as ARMv7DirectiveParameter.StringLit) }
            .forEach { p ->
                p.value.forEach { ch ->
                    val code = ch.code
                    if (code > 0xFF) {
                        errors.add(AssemblerError("non-byte character in .ascii string", p.element))
                        return
                    }
                    ctx.data.add(code.toByte())
                }
            }
    }
}

object ARMv7NullTerminatedStringDirectiveHandler : ARMv7DirectiveHandler {
    override fun size(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        errors: MutableList<AssemblerError>,
    ): Int? {
        if (ctx.section != ProgramSection.Data) {
            errors.add(AssemblerError(".asciz is only valid in the data section", directive))
            return null
        }

        val parameters = directive.parameters
        if (parameters.isEmpty()) {
            errors.add(AssemblerError("no parameters passed to .asciz", directive))
            return null
        }
        parameters.find { it !is ARMv7DirectiveParameter.StringLit }?.let {
            errors.add(AssemblerError("invalid parameter type in .asciz (expected string)", it.element))
            return null
        }

        return parameters.sumOf { (it as ARMv7DirectiveParameter.StringLit).value.length + 1 }
    }

    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>,
    ) {
        directive.parameters
            .map { (it as ARMv7DirectiveParameter.StringLit) }
            .forEach { p ->
                p.value.forEach { ch ->
                    val code = ch.code
                    if (code > 0xFF) {
                        errors.add(AssemblerError("non-byte character in .asciz string", p.element))
                        return
                    }
                    ctx.data.add(code.toByte())
                }
                ctx.data.add(0)
            }
    }
}
