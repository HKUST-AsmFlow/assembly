// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ARMv7Visitor extends PsiElementVisitor {

  public void visitAdcInstruction(@NotNull ARMv7AdcInstruction o) {
    visitPsiElement(o);
  }

  public void visitAddInstruction(@NotNull ARMv7AddInstruction o) {
    visitPsiElement(o);
  }

  public void visitAlignDirective(@NotNull ARMv7AlignDirective o) {
    visitPsiElement(o);
  }

  public void visitAsciiDirective(@NotNull ARMv7AsciiDirective o) {
    visitPsiElement(o);
  }

  public void visitBased(@NotNull ARMv7Based o) {
    visitPsiElement(o);
  }

  public void visitConditionCodes(@NotNull ARMv7ConditionCodes o) {
    visitPsiElement(o);
  }

  public void visitGlobalDirective(@NotNull ARMv7GlobalDirective o) {
    visitPsiElement(o);
  }

  public void visitItem(@NotNull ARMv7Item o) {
    visitPsiElement(o);
  }

  public void visitLabel(@NotNull ARMv7Label o) {
    visitPsiElement(o);
  }

  public void visitNumber(@NotNull ARMv7Number o) {
    visitPsiElement(o);
  }

  public void visitRegister(@NotNull ARMv7Register o) {
    visitPsiElement(o);
  }

  public void visitRegisters(@NotNull ARMv7Registers o) {
    visitPsiElement(o);
  }

  public void visitSetConditionFlags(@NotNull ARMv7SetConditionFlags o) {
    visitPsiElement(o);
  }

  public void visitShift(@NotNull ARMv7Shift o) {
    visitPsiElement(o);
  }

  public void visitShiftType(@NotNull ARMv7ShiftType o) {
    visitPsiElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
