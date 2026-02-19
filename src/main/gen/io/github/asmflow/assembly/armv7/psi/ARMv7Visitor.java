// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class ARMv7Visitor extends PsiElementVisitor {

  public void visitAdcInstruction(@NotNull ARMv7AdcInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitAddInstruction(@NotNull ARMv7AddInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitAdrInstruction(@NotNull ARMv7AdrInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitAlignDirective(@NotNull ARMv7AlignDirective o) {
    visitPsiElement(o);
  }

  public void visitAndInstruction(@NotNull ARMv7AndInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitAsciiDirective(@NotNull ARMv7AsciiDirective o) {
    visitPsiElement(o);
  }

  public void visitAsrInstruction(@NotNull ARMv7AsrInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBInstruction(@NotNull ARMv7BInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBased(@NotNull ARMv7Based o) {
    visitPsiElement(o);
  }

  public void visitBfcInstruction(@NotNull ARMv7BfcInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBfiInstruction(@NotNull ARMv7BfiInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBicInstruction(@NotNull ARMv7BicInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBkptInstruction(@NotNull ARMv7BkptInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBlInstruction(@NotNull ARMv7BlInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBxInstruction(@NotNull ARMv7BxInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitBxjInstruction(@NotNull ARMv7BxjInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitCbnzInstruction(@NotNull ARMv7CbnzInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitCbzInstruction(@NotNull ARMv7CbzInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitClrexInstruction(@NotNull ARMv7ClrexInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitClzInstruction(@NotNull ARMv7ClzInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitCmnInstruction(@NotNull ARMv7CmnInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitCmpInstruction(@NotNull ARMv7CmpInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitCsdbInstruction(@NotNull ARMv7CsdbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitDbgInstruction(@NotNull ARMv7DbgInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitDmbInstruction(@NotNull ARMv7DmbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitDsbInstruction(@NotNull ARMv7DsbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitEorInstruction(@NotNull ARMv7EorInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitEretInstruction(@NotNull ARMv7EretInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitGlobalDirective(@NotNull ARMv7GlobalDirective o) {
    visitIARMv7GlobalDirectiveMixin(o);
  }

  public void visitHvcInstruction(@NotNull ARMv7HvcInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitId(@NotNull ARMv7Id o) {
    visitPsiElement(o);
  }

  public void visitIsbInstruction(@NotNull ARMv7IsbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLabel(@NotNull ARMv7Label o) {
    visitIARMv7LabelMixin(o);
  }

  public void visitLdmInstruction(@NotNull ARMv7LdmInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmdaInstruction(@NotNull ARMv7LdmdaInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmdbInstruction(@NotNull ARMv7LdmdbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmeaPseudoInstruction(@NotNull ARMv7LdmeaPseudoInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmedPseudoInstruction(@NotNull ARMv7LdmedPseudoInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmfaPseudoInstruction(@NotNull ARMv7LdmfaPseudoInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmfdPseudoInstruction(@NotNull ARMv7LdmfdPseudoInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmiaPseudoInstruction(@NotNull ARMv7LdmiaPseudoInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdmibInstruction(@NotNull ARMv7LdmibInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrInstruction(@NotNull ARMv7LdrInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrbInstruction(@NotNull ARMv7LdrbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrbtInstruction(@NotNull ARMv7LdrbtInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrdInstruction(@NotNull ARMv7LdrdInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrexInstruction(@NotNull ARMv7LdrexInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrexbInstruction(@NotNull ARMv7LdrexbInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrexdInstruction(@NotNull ARMv7LdrexdInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrexhInstruction(@NotNull ARMv7LdrexhInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitLdrhInstruction(@NotNull ARMv7LdrhInstruction o) {
    visitIARMv7InstructionSuffix(o);
  }

  public void visitNoOffset(@NotNull ARMv7NoOffset o) {
    visitPsiElement(o);
  }

  public void visitNumber(@NotNull ARMv7Number o) {
    visitPsiElement(o);
  }

  public void visitNumbers(@NotNull ARMv7Numbers o) {
    visitPsiElement(o);
  }

  public void visitPCWithImmediateOffset(@NotNull ARMv7PCWithImmediateOffset o) {
    visitPsiElement(o);
  }

  public void visitPostindexOffset(@NotNull ARMv7PostindexOffset o) {
    visitPsiElement(o);
  }

  public void visitPreindexOffset(@NotNull ARMv7PreindexOffset o) {
    visitPsiElement(o);
  }

  public void visitRegister(@NotNull ARMv7Register o) {
    visitIARMv7RegisterSuffix(o);
  }

  public void visitRegisters(@NotNull ARMv7Registers o) {
    visitPsiElement(o);
  }

  public void visitShift(@NotNull ARMv7Shift o) {
    visitPsiElement(o);
  }

  public void visitShiftType(@NotNull ARMv7ShiftType o) {
    visitPsiElement(o);
  }

  public void visitIARMv7GlobalDirectiveMixin(@NotNull IARMv7GlobalDirectiveMixin o) {
    visitElement(o);
  }

  public void visitIARMv7InstructionSuffix(@NotNull IARMv7InstructionSuffix o) {
    visitElement(o);
  }

  public void visitIARMv7LabelMixin(@NotNull IARMv7LabelMixin o) {
    visitElement(o);
  }

  public void visitIARMv7RegisterSuffix(@NotNull IARMv7RegisterSuffix o) {
    visitElement(o);
  }

  public void visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
  }

}
