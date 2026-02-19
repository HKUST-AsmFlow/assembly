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

public class ARMv7LdrbInstructionImpl extends ARMv7InstructionSuffixImpl implements ARMv7LdrbInstruction {

  public ARMv7LdrbInstructionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitLdrbInstruction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ARMv7Id getId() {
    return findChildByClass(ARMv7Id.class);
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
  public ARMv7PostindexOffset getPostindexOffset() {
    return findChildByClass(ARMv7PostindexOffset.class);
  }

  @Override
  @Nullable
  public ARMv7PreindexOffset getPreindexOffset() {
    return findChildByClass(ARMv7PreindexOffset.class);
  }

  @Override
  @NotNull
  public ARMv7Register getRegister() {
    return findNotNullChildByClass(ARMv7Register.class);
  }

}
