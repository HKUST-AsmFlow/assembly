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

public class ARMv7LdrInstructionImpl extends ARMv7InstructionSuffixImpl implements ARMv7LdrInstruction {

  public ARMv7LdrInstructionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitLdrInstruction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7NoOffset getNoOffset() {
    return findChildByClass(ARMv7NoOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PCWithImmediateOffset getPCWithImmediateOffset() {
    return findChildByClass(ARMv7PCWithImmediateOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PostindexImmediateOffset getPostindexImmediateOffset() {
    return findChildByClass(ARMv7PostindexImmediateOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PostindexRegisterOffset getPostindexRegisterOffset() {
    return findChildByClass(ARMv7PostindexRegisterOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PreindexImmediateOffset getPreindexImmediateOffset() {
    return findChildByClass(ARMv7PreindexImmediateOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PreindexRegisterOffset getPreindexRegisterOffset() {
    return findChildByClass(ARMv7PreindexRegisterOffset.class);
  }

  @Override
  @NotNull
  public ARMv7Register getRegister() {
    return findNotNullChildByClass(ARMv7Register.class);
  }

}
