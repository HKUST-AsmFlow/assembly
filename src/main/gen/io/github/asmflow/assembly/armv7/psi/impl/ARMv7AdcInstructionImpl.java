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

public class ARMv7AdcInstructionImpl extends ASTWrapperPsiElement implements ARMv7AdcInstruction {

  public ARMv7AdcInstructionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitAdcInstruction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7ConditionCodes getConditionCodes() {
    return findChildByClass(ARMv7ConditionCodes.class);
  }

  @Override
  @Nullable
  public ARMv7Number getNumber() {
    return findChildByClass(ARMv7Number.class);
  }

  @Override
  @NotNull
  public ARMv7Registers getRegisters() {
    return findNotNullChildByClass(ARMv7Registers.class);
  }

  @Override
  @Nullable
  public ARMv7SetConditionFlags getSetConditionFlags() {
    return findChildByClass(ARMv7SetConditionFlags.class);
  }

  @Override
  @Nullable
  public ARMv7Shift getShift() {
    return findChildByClass(ARMv7Shift.class);
  }

}
