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

public class ARMv7RegisterWithShiftImpl extends ASTWrapperPsiElement implements ARMv7RegisterWithShift {

  public ARMv7RegisterWithShiftImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitRegisterWithShift(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7Number getNumber() {
    return findChildByClass(ARMv7Number.class);
  }

  @Override
  @NotNull
  public List<ARMv7Register> getRegisterList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ARMv7Register.class);
  }

  @Override
  @Nullable
  public ARMv7ShiftType getShiftType() {
    return findChildByClass(ARMv7ShiftType.class);
  }

}
