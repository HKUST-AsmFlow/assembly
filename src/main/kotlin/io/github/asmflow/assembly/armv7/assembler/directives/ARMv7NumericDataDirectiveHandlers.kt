package io.github.asmflow.assembly.armv7.assembler.directives

import io.github.asmflow.assembly.armv7.assembler.AssemblerError
import io.github.asmflow.assembly.armv7.assembler.context.AssemblerContext
import io.github.asmflow.assembly.armv7.assembler.context.ProgramSection
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive

fun MutableList<Byte>.writeHalfLE(value: Int) {
    add((value and 0xFF).toByte())
    add(((value ushr 8) and 0xFF).toByte())
}

fun MutableList<Byte>.writeWordLE(value: Int) {
    add((value and 0xFF).toByte())
    add(((value ushr 8) and 0xFF).toByte())
    add(((value ushr 16) and 0xFF).toByte())
    add(((value ushr 24) and 0xFF).toByte())
}

object ARMv7ByteDataDirectiveHandler : ARMv7DirectiveHandler {
    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>
    ) {
        val bytes = directive.parameters.map { (it as ARMv7DirectiveParameter.Number).value }
        bytes.withIndex().find { it.value !in -128..255 }?.let { (i, _) ->
            errors.add(AssemblerError("(unsigned) byte value out of range", directive.parameters[i].element))
            return
        }

        ctx.data.addAll(bytes.map { it.toByte() })
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
        val values = directive.parameters.map {
            (it as ARMv7DirectiveParameter.Number).value
        }
        values.withIndex().find { it.value !in -32768..65535 }?.let { (i, _) ->
            errors.add(
                AssemblerError(
                    "halfword value out of range (expected -32768..65535)",
                    directive.parameters[i].element,
                )
            )
            return
        }

        val pad = alignPad(ctx.data.size, 2)
        repeat(pad) { ctx.data.add(0) }

        values.forEach { ctx.data.writeHalfLE(it) }
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

        val pad = alignPad(ctx.dataOffsetBytes, 2)
        return pad + 2 * parameters.size
    }
}

object ARMv7WordDataDirectiveHandler : ARMv7DirectiveHandler {
    override fun emit(
        directive: ARMv7Directive,
        ctx: AssemblerContext,
        symbols: Map<String, UInt>,
        errors: MutableList<AssemblerError>
    ) {
        val values = directive.parameters.map {
            (it as ARMv7DirectiveParameter.Number).value
        }
        values.withIndex().find { it.value !in -Int.MIN_VALUE..UInt.MAX_VALUE.toLong() }?.let { (i, _) ->
            errors.add(
                AssemblerError(
                    "word value out of range (expected -2147483648..4294967295)",
                    directive.parameters[i].element,
                )
            )
            return
        }

        val pad = alignPad(ctx.data.size, 4)
        repeat(pad) { ctx.data.add(0) }

        values.forEach { ctx.data.writeWordLE(it) }
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
            errors.add(AssemblerError("invalid parameter type in word directive", it.element))
            return null
        }

        val pad = alignPad(ctx.dataOffsetBytes, 4)
        return pad + 4 * parameters.size
    }
}
