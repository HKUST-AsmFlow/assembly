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

public class ARMv7OperandImpl extends ASTWrapperPsiElement implements ARMv7Operand {

  public ARMv7OperandImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitOperand(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7Label getLabel() {
    return findChildByClass(ARMv7Label.class);
  }

  @Override
  @Nullable
  public ARMv7Number getNumber() {
    return findChildByClass(ARMv7Number.class);
  }

  @Override
  @Nullable
  public ARMv7Offset getOffset() {
    return findChildByClass(ARMv7Offset.class);
  }

  @Override
  @Nullable
  public ARMv7OffsetVariant getOffsetVariant() {
    return findChildByClass(ARMv7OffsetVariant.class);
  }

  @Override
  @Nullable
  public ARMv7Postindexed getPostindexed() {
    return findChildByClass(ARMv7Postindexed.class);
  }

  @Override
  @Nullable
  public ARMv7Preindexed getPreindexed() {
    return findChildByClass(ARMv7Preindexed.class);
  }

  @Override
  @Nullable
  public ARMv7Register getRegister() {
    return findChildByClass(ARMv7Register.class);
  }

  @Override
  @Nullable
  public ARMv7RegisterWithShift getRegisterWithShift() {
    return findChildByClass(ARMv7RegisterWithShift.class);
  }

}
