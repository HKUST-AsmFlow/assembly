// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ARMv7Visitor extends PsiElementVisitor {

  public void visitBinaryLiteral(@NotNull ARMv7BinaryLiteral o) {
    visitNumberMixin(o);
  }

  public void visitDecimalLiteral(@NotNull ARMv7DecimalLiteral o) {
    visitNumberMixin(o);
  }

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

  public void visitFlexibleOffset(@NotNull ARMv7FlexibleOffset o) {
    visitFlexibleOffsetMixin(o);
  }

  public void visitHexadecimalLiteral(@NotNull ARMv7HexadecimalLiteral o) {
    visitNumberMixin(o);
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
    visitNumberMixin(o);
    // visitOperandMixin(o);
  }

  public void visitOctalLiteral(@NotNull ARMv7OctalLiteral o) {
    visitNumberMixin(o);
  }

  public void visitOperand(@NotNull ARMv7Operand o) {
    visitOperandMixin(o);
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
    visitRegisterMixin(o);
  }

  public void visitRegisterWithShift(@NotNull ARMv7RegisterWithShift o) {
    visitOperandMixin(o);
  }

  public void visitShift(@NotNull ARMv7Shift o) {
    visitShiftMixin(o);
  }

  public void visitShiftType(@NotNull ARMv7ShiftType o) {
    visitShiftTypeMixin(o);
  }

  public void visitSign(@NotNull ARMv7Sign o) {
    visitSignMixin(o);
  }

  public void visitFlexibleOffsetMixin(@NotNull ARMv7FlexibleOffsetMixin o) {
    visitPsiElement(o);
  }

  public void visitInstructionMixin(@NotNull ARMv7InstructionMixin o) {
    visitPsiElement(o);
  }

  public void visitNumberMixin(@NotNull ARMv7NumberMixin o) {
    visitPsiElement(o);
  }

  public void visitOperandMixin(@NotNull ARMv7OperandMixin o) {
    visitPsiElement(o);
  }

  public void visitRegisterMixin(@NotNull ARMv7RegisterMixin o) {
    visitPsiElement(o);
  }

  public void visitShiftMixin(@NotNull ARMv7ShiftMixin o) {
    visitPsiElement(o);
  }

  public void visitShiftTypeMixin(@NotNull ARMv7ShiftTypeMixin o) {
    visitPsiElement(o);
  }

  public void visitSignMixin(@NotNull ARMv7SignMixin o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
