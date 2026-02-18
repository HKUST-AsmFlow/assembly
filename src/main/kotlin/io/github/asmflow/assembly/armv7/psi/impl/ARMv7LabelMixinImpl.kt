package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes
import io.github.asmflow.assembly.armv7.psi.IARMv7LabelMixin

abstract class ARMv7LabelMixinImpl(node: ASTNode) : IARMv7LabelMixin, ARMv7NamedElementImpl(node) {
    override fun getLabelName(): String = node.findChildByType(ARMv7TokenTypes.ID)!!.text
}
