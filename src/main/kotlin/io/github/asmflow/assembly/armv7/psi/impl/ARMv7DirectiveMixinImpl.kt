package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.assembler.directives.ARMv7DirectiveParameter
import io.github.asmflow.assembly.armv7.psi.ARMv7Directive
import io.github.asmflow.assembly.armv7.psi.ARMv7Parameter

abstract class ARMv7DirectiveMixinImpl(node: ASTNode) : ASTWrapperPsiElement(node), ARMv7Directive {
    override val parameters: List<ARMv7DirectiveParameter>
        get() = directiveParameterList?.parameterList.orEmpty().map { it.toParameter() }

    fun ARMv7Parameter.toParameter(): ARMv7DirectiveParameter = when {
        number != null -> ARMv7DirectiveParameter.Number(number!!.value, number!!)
        label != null -> ARMv7DirectiveParameter.Label(label!!.text, label!!)
        // todo: add string literal
        else -> error("invalid directive parameter")
    }
}
