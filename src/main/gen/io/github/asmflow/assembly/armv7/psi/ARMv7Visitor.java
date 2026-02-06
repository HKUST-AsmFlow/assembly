// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ARMv7Visitor extends PsiElementVisitor {

  public void visitADCInstruction(@NotNull ARMv7ADCInstruction o) {
    visitPsiElement(o);
  }

  public void visitItem(@NotNull ARMv7Item o) {
    visitPsiElement(o);
  }

  public void visitLabel(@NotNull ARMv7Label o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
