// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ARMv7Visitor extends PsiElementVisitor {

  public void visitDirective(@NotNull ARMv7Directive o) {
    visitPsiElement(o);
  }

  public void visitDirectiveName(@NotNull ARMv7DirectiveName o) {
    visitPsiElement(o);
  }

  public void visitDirectiveParameter(@NotNull ARMv7DirectiveParameter o) {
    visitPsiElement(o);
  }

  public void visitDirectiveParameters(@NotNull ARMv7DirectiveParameters o) {
    visitPsiElement(o);
  }

  public void visitInstruction(@NotNull ARMv7Instruction o) {
    visitInstructionMixin(o);
  }

  public void visitLabel(@NotNull ARMv7Label o) {
    visitPsiElement(o);
  }

  public void visitLabelWithColon(@NotNull ARMv7LabelWithColon o) {
    visitPsiElement(o);
  }

  public void visitMnemonic(@NotNull ARMv7Mnemonic o) {
    visitPsiElement(o);
  }

  public void visitNumber(@NotNull ARMv7Number o) {
    visitPsiElement(o);
  }

  public void visitOffset(@NotNull ARMv7Offset o) {
    visitPsiElement(o);
  }

  public void visitOffsetVariant(@NotNull ARMv7OffsetVariant o) {
    visitPsiElement(o);
  }

  public void visitOperand(@NotNull ARMv7Operand o) {
    visitPsiElement(o);
  }

  public void visitOperands(@NotNull ARMv7Operands o) {
    visitPsiElement(o);
  }

  public void visitPostindexed(@NotNull ARMv7Postindexed o) {
    visitPsiElement(o);
  }

  public void visitPreindexed(@NotNull ARMv7Preindexed o) {
    visitPsiElement(o);
  }

  public void visitRegister(@NotNull ARMv7Register o) {
    visitPsiElement(o);
  }

  public void visitRegisterWithShift(@NotNull ARMv7RegisterWithShift o) {
    visitPsiElement(o);
  }

  public void visitShiftType(@NotNull ARMv7ShiftType o) {
    visitPsiElement(o);
  }

  public void visitInstructionMixin(@NotNull ARMv7InstructionMixin o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
