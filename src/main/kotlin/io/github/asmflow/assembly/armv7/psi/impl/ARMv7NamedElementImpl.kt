package io.github.asmflow.assembly.armv7.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import io.github.asmflow.assembly.armv7.psi.ARMv7NamedElement

abstract class ARMv7NamedElementImpl(node: ASTNode) : ARMv7NamedElement, ASTWrapperPsiElement(node)
