// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.asmflow.assembly.armv7.psi.impl.*;

public interface ARMv7TokenTypes {

  IElementType ADC_INSTRUCTION = new ARMv7ElementType("ADC_INSTRUCTION");
  IElementType CONDITION_CODES = new ARMv7ElementType("CONDITION_CODES");
  IElementType ITEM = new ARMv7ElementType("ITEM");
  IElementType LABEL = new ARMv7ElementType("LABEL");
  IElementType SET_CONDITION_FLAGS = new ARMv7ElementType("SET_CONDITION_FLAGS");

  IElementType ADC = new ARMv7TokenType("adc");
  IElementType AL = new ARMv7TokenType("al");
  IElementType CC = new ARMv7TokenType("cc");
  IElementType COLON = new ARMv7TokenType(":");
  IElementType COMMENT = new ARMv7TokenType("comment");
  IElementType CS = new ARMv7TokenType("cs");
  IElementType DOT = new ARMv7TokenType(".");
  IElementType EQ = new ARMv7TokenType("eq");
  IElementType GE = new ARMv7TokenType("ge");
  IElementType GT = new ARMv7TokenType("gt");
  IElementType HI = new ARMv7TokenType("hi");
  IElementType HS = new ARMv7TokenType("hs");
  IElementType IDENTIFIER = new ARMv7TokenType("identifier");
  IElementType LE = new ARMv7TokenType("le");
  IElementType LINE_FEED = new ARMv7TokenType("lf");
  IElementType LO = new ARMv7TokenType("lo");
  IElementType LS = new ARMv7TokenType("ls");
  IElementType LT = new ARMv7TokenType("lt");
  IElementType MI = new ARMv7TokenType("mi");
  IElementType NE = new ARMv7TokenType("ne");
  IElementType PL = new ARMv7TokenType("pl");
  IElementType S = new ARMv7TokenType("s");
  IElementType STRING = new ARMv7TokenType("string");
  IElementType VC = new ARMv7TokenType("vc");
  IElementType VS = new ARMv7TokenType("vs");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADC_INSTRUCTION) {
        return new ARMv7ADCInstructionImpl(node);
      }
      else if (type == CONDITION_CODES) {
        return new ARMv7ConditionCodesImpl(node);
      }
      else if (type == ITEM) {
        return new ARMv7ItemImpl(node);
      }
      else if (type == LABEL) {
        return new ARMv7LabelImpl(node);
      }
      else if (type == SET_CONDITION_FLAGS) {
        return new ARMv7SetConditionFlagsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
