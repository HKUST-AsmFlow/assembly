// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.asmflow.assembly.armv7.psi.impl.*;

public interface ARMv7TokenTypes {

  IElementType ADC_INSTRUCTION = new ARMv7ElementType("ADC_INSTRUCTION");
  IElementType BASED = new ARMv7ElementType("BASED");
  IElementType CONDITION_CODES = new ARMv7ElementType("CONDITION_CODES");
  IElementType ITEM = new ARMv7ElementType("ITEM");
  IElementType LABEL = new ARMv7ElementType("LABEL");
  IElementType NUMBER = new ARMv7ElementType("NUMBER");
  IElementType REGISTER = new ARMv7ElementType("REGISTER");
  IElementType REGISTERS = new ARMv7ElementType("REGISTERS");
  IElementType SET_CONDITION_FLAGS = new ARMv7ElementType("SET_CONDITION_FLAGS");
  IElementType SHIFT = new ARMv7ElementType("SHIFT");
  IElementType SHIFT_TYPE = new ARMv7ElementType("SHIFT_TYPE");

  IElementType ADC = new ARMv7TokenType("adc");
  IElementType AL = new ARMv7TokenType("al");
  IElementType ASR = new ARMv7TokenType("asr");
  IElementType BINARY_NUMBER = new ARMv7TokenType("binary number");
  IElementType CC = new ARMv7TokenType("cc");
  IElementType COLON = new ARMv7TokenType(":");
  IElementType COMMA = new ARMv7TokenType(",");
  IElementType COMMENT = new ARMv7TokenType("comment");
  IElementType CPSR = new ARMv7TokenType("CPSR");
  IElementType CS = new ARMv7TokenType("cs");
  IElementType DECIMAL_NUMBER = new ARMv7TokenType("decimal number");
  IElementType DOT = new ARMv7TokenType(".");
  IElementType EQ = new ARMv7TokenType("eq");
  IElementType GE = new ARMv7TokenType("ge");
  IElementType GT = new ARMv7TokenType("gt");
  IElementType HEXADECIMAL_NUMBER = new ARMv7TokenType("hexadecimal number");
  IElementType HI = new ARMv7TokenType("hi");
  IElementType HS = new ARMv7TokenType("hs");
  IElementType IDENTIFIER = new ARMv7TokenType("identifier");
  IElementType LE = new ARMv7TokenType("le");
  IElementType LINE_FEED = new ARMv7TokenType("lf");
  IElementType LO = new ARMv7TokenType("lo");
  IElementType LR = new ARMv7TokenType("lr");
  IElementType LS = new ARMv7TokenType("ls");
  IElementType LSL = new ARMv7TokenType("lsl");
  IElementType LSR = new ARMv7TokenType("lsr");
  IElementType LT = new ARMv7TokenType("lt");
  IElementType MI = new ARMv7TokenType("mi");
  IElementType NE = new ARMv7TokenType("ne");
  IElementType OCTAL_NUMBER = new ARMv7TokenType("octal number");
  IElementType PC = new ARMv7TokenType("PC");
  IElementType PL = new ARMv7TokenType("pl");
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
  IElementType ROR = new ARMv7TokenType("ror");
  IElementType RRX = new ARMv7TokenType("rrx");
  IElementType S = new ARMv7TokenType("s");
  IElementType SP = new ARMv7TokenType("sp");
  IElementType SPSR = new ARMv7TokenType("SPSR");
  IElementType STRING = new ARMv7TokenType("string");
  IElementType VC = new ARMv7TokenType("vc");
  IElementType VS = new ARMv7TokenType("vs");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADC_INSTRUCTION) {
        return new ARMv7ADCInstructionImpl(node);
      }
      else if (type == BASED) {
        return new ARMv7BasedImpl(node);
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
      else if (type == NUMBER) {
        return new ARMv7NumberImpl(node);
      }
      else if (type == REGISTER) {
        return new ARMv7RegisterImpl(node);
      }
      else if (type == REGISTERS) {
        return new ARMv7RegistersImpl(node);
      }
      else if (type == SET_CONDITION_FLAGS) {
        return new ARMv7SetConditionFlagsImpl(node);
      }
      else if (type == SHIFT) {
        return new ARMv7ShiftImpl(node);
      }
      else if (type == SHIFT_TYPE) {
        return new ARMv7ShiftTypeImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
