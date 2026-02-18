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

public class ARMv7AndInstructionImpl extends ARMv7InstructionSuffixImpl implements ARMv7AndInstruction {

  public ARMv7AndInstructionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitAndInstruction(this);
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
  public ARMv7Registers getRegisters() {
    return findNotNullChildByClass(ARMv7Registers.class);
  }

  @Override
  @Nullable
  public ARMv7Shift getShift() {
    return findChildByClass(ARMv7Shift.class);
  }

}
