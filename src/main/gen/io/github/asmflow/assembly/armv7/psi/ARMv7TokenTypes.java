// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.asmflow.assembly.armv7.psi.impl.*;

public interface ARMv7TokenTypes {

  IElementType DIRECTIVE = new ARMv7ElementType("DIRECTIVE");
  IElementType DIRECTIVE_PARAMETER = new ARMv7ElementType("DIRECTIVE_PARAMETER");
  IElementType DIRECTIVE_PARAMETERS = new ARMv7ElementType("DIRECTIVE_PARAMETERS");
  IElementType INSTRUCTION = new ARMv7ElementType("INSTRUCTION");
  IElementType LABEL = new ARMv7ElementType("LABEL");
  IElementType LABEL_WITH_COLON = new ARMv7ElementType("LABEL_WITH_COLON");
  IElementType MNEMONIC = new ARMv7ElementType("MNEMONIC");
  IElementType NUMBER = new ARMv7ElementType("NUMBER");
  IElementType OFFSET = new ARMv7ElementType("OFFSET");
  IElementType OFFSET_VARIANT = new ARMv7ElementType("OFFSET_VARIANT");
  IElementType OPERAND = new ARMv7ElementType("OPERAND");
  IElementType OPERANDS = new ARMv7ElementType("OPERANDS");
  IElementType POSTINDEXED = new ARMv7ElementType("POSTINDEXED");
  IElementType PREINDEXED = new ARMv7ElementType("PREINDEXED");
  IElementType REGISTER = new ARMv7ElementType("REGISTER");
  IElementType REGISTER_WITH_SHIFT = new ARMv7ElementType("REGISTER_WITH_SHIFT");
  IElementType SHIFT_TYPE = new ARMv7ElementType("SHIFT_TYPE");

  IElementType BANG = new ARMv7TokenType("!");
  IElementType BINARY_NUMBER = new ARMv7TokenType("binary number");
  IElementType COLON = new ARMv7TokenType(":");
  IElementType COMMA = new ARMv7TokenType(",");
  IElementType COMMENT = new ARMv7TokenType("comment");
  IElementType CPSR = new ARMv7TokenType("CPSR");
  IElementType DECIMAL_NUMBER = new ARMv7TokenType("decimal number");
  IElementType DOT = new ARMv7TokenType(".");
  IElementType HEXADECIMAL_NUMBER = new ARMv7TokenType("hexadecimal number");
  IElementType IDENTIFIER = new ARMv7TokenType("identifier");
  IElementType LBRACKET = new ARMv7TokenType("[");
  IElementType LINE_FEED = new ARMv7TokenType("lf");
  IElementType LR = new ARMv7TokenType("lr");
  IElementType MINUS = new ARMv7TokenType("-");
  IElementType OCTAL_NUMBER = new ARMv7TokenType("octal number");
  IElementType PC = new ARMv7TokenType("PC");
  IElementType PLUS = new ARMv7TokenType("+");
  IElementType POUND = new ARMv7TokenType("#");
  IElementType R0 = new ARMv7TokenType("r0");
  IElementType R1 = new ARMv7TokenType("r1");
  IElementType R10 = new ARMv7TokenType("r10");
  IElementType R11 = new ARMv7TokenType("r11");
  IElementType R12 = new ARMv7TokenType("r12");
  IElementType R2 = new ARMv7TokenType("r2");
  IElementType R3 = new ARMv7TokenType("r3");
  IElementType R4 = new ARMv7TokenType("r4");
  IElementType R5 = new ARMv7TokenType("r5");
  IElementType R6 = new ARMv7TokenType("r6");
  IElementType R7 = new ARMv7TokenType("r7");
  IElementType R8 = new ARMv7TokenType("r8");
  IElementType R9 = new ARMv7TokenType("r9");
  IElementType RBRACKET = new ARMv7TokenType("]");
  IElementType SP = new ARMv7TokenType("sp");
  IElementType SPSR = new ARMv7TokenType("SPSR");
  IElementType STRING = new ARMv7TokenType("string");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == DIRECTIVE) {
        return new ARMv7DirectiveImpl(node);
      }
      else if (type == DIRECTIVE_PARAMETER) {
        return new ARMv7DirectiveParameterImpl(node);
      }
      else if (type == DIRECTIVE_PARAMETERS) {
        return new ARMv7DirectiveParametersImpl(node);
      }
      else if (type == INSTRUCTION) {
        return new ARMv7InstructionImpl(node);
      }
      else if (type == LABEL) {
        return new ARMv7LabelImpl(node);
      }
      else if (type == LABEL_WITH_COLON) {
        return new ARMv7LabelWithColonImpl(node);
      }
      else if (type == MNEMONIC) {
        return new ARMv7MnemonicImpl(node);
      }
      else if (type == NUMBER) {
        return new ARMv7NumberImpl(node);
      }
      else if (type == OFFSET) {
        return new ARMv7OffsetImpl(node);
      }
      else if (type == OFFSET_VARIANT) {
        return new ARMv7OffsetVariantImpl(node);
      }
      else if (type == OPERAND) {
        return new ARMv7OperandImpl(node);
      }
      else if (type == OPERANDS) {
        return new ARMv7OperandsImpl(node);
      }
      else if (type == POSTINDEXED) {
        return new ARMv7PostindexedImpl(node);
      }
      else if (type == PREINDEXED) {
        return new ARMv7PreindexedImpl(node);
      }
      else if (type == REGISTER) {
        return new ARMv7RegisterImpl(node);
      }
      else if (type == REGISTER_WITH_SHIFT) {
        return new ARMv7RegisterWithShiftImpl(node);
      }
      else if (type == SHIFT_TYPE) {
        return new ARMv7ShiftTypeImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
