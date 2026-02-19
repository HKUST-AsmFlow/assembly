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

public class ARMv7LdmedPseudoInstructionImpl extends ARMv7InstructionSuffixImpl implements ARMv7LdmedPseudoInstruction {

  public ARMv7LdmedPseudoInstructionImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitLdmedPseudoInstruction(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public ARMv7Register getRegister() {
    return findNotNullChildByClass(ARMv7Register.class);
  }

  @Override
  @NotNull
  public ARMv7Registers getRegisters() {
    return findNotNullChildByClass(ARMv7Registers.class);
  }

}
