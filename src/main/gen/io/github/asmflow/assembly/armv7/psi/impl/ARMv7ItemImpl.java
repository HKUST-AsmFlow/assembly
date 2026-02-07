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
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.asmflow.assembly.armv7.psi.*;

public class ARMv7ItemImpl extends ASTWrapperPsiElement implements ARMv7Item {

  public ARMv7ItemImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitItem(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7AdcInstruction getAdcInstruction() {
    return findChildByClass(ARMv7AdcInstruction.class);
  }

  @Override
  @Nullable
  public ARMv7AddInstruction getAddInstruction() {
    return findChildByClass(ARMv7AddInstruction.class);
  }

  @Override
  @Nullable
  public ARMv7AlignDirective getAlignDirective() {
    return findChildByClass(ARMv7AlignDirective.class);
  }

  @Override
  @Nullable
  public ARMv7AsciiDirective getAsciiDirective() {
    return findChildByClass(ARMv7AsciiDirective.class);
  }

  @Override
  @Nullable
  public ARMv7GlobalDirective getGlobalDirective() {
    return findChildByClass(ARMv7GlobalDirective.class);
  }

  @Override
  @Nullable
  public ARMv7Label getLabel() {
    return findChildByClass(ARMv7Label.class);
  }

}
