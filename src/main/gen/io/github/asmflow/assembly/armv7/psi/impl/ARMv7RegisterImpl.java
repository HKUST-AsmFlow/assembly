// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi.impl;

import java.util.List;

import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;

import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;

import io.github.asmflow.assembly.armv7.psi.*;

public class ARMv7RegisterImpl extends ARMv7RegisterSuffixImpl implements ARMv7Register {

    public ARMv7RegisterImpl(@NotNull ASTNode node) {
        super(node);
    }

    public void accept(@NotNull ARMv7Visitor visitor) {
        visitor.visitRegister(this);
    }

    @Override
    public void accept(@NotNull PsiElementVisitor visitor) {
        if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor) visitor);
        else super.accept(visitor);
    }

}
