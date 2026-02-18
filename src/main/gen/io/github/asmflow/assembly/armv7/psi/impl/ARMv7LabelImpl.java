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
import io.github.asmflow.assembly.armv7.psi.util.ARMv7PsiImplUtil;

public class ARMv7LabelImpl extends ARMv7LabelMixinImpl implements ARMv7Label {

  public ARMv7LabelImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitLabel(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ARMv7Id getId() {
    return findNotNullChildByClass(ARMv7Id.class);
  }

  @Override
  public @NotNull String getName() {
    return ARMv7PsiImplUtil.getName(this);
  }

  @Override
  public @Nullable PsiElement getNameIdentifier() {
    return ARMv7PsiImplUtil.getNameIdentifier(this);
  }

  @Override
  public @NotNull ARMv7Label setName(@NotNull String name) {
    return ARMv7PsiImplUtil.setName(this, name);
  }

}
