// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import io.github.asmflow.assembly.armv7.psi.impl.*;

public interface ARMv7TokenTypes {

  IElementType ADC_INSTRUCTION = new ARMv7ElementType("ADC_INSTRUCTION");
  IElementType ADD_INSTRUCTION = new ARMv7ElementType("ADD_INSTRUCTION");
  IElementType ADR_INSTRUCTION = new ARMv7ElementType("ADR_INSTRUCTION");
  IElementType ALIGN_DIRECTIVE = new ARMv7ElementType("ALIGN_DIRECTIVE");
  IElementType AND_INSTRUCTION = new ARMv7ElementType("AND_INSTRUCTION");
  IElementType ASCII_DIRECTIVE = new ARMv7ElementType("ASCII_DIRECTIVE");
  IElementType ASR_INSTRUCTION = new ARMv7ElementType("ASR_INSTRUCTION");
  IElementType BASED = new ARMv7ElementType("BASED");
  IElementType BFC_INSTRUCTION = new ARMv7ElementType("BFC_INSTRUCTION");
  IElementType BFI_INSTRUCTION = new ARMv7ElementType("BFI_INSTRUCTION");
  IElementType BIC_INSTRUCTION = new ARMv7ElementType("BIC_INSTRUCTION");
  IElementType BKPT_INSTRUCTION = new ARMv7ElementType("BKPT_INSTRUCTION");
  IElementType BL_INSTRUCTION = new ARMv7ElementType("BL_INSTRUCTION");
  IElementType BXJ_INSTRUCTION = new ARMv7ElementType("BXJ_INSTRUCTION");
  IElementType BX_INSTRUCTION = new ARMv7ElementType("BX_INSTRUCTION");
  IElementType B_INSTRUCTION = new ARMv7ElementType("B_INSTRUCTION");
  IElementType CBNZ_INSTRUCTION = new ARMv7ElementType("CBNZ_INSTRUCTION");
  IElementType CBZ_INSTRUCTION = new ARMv7ElementType("CBZ_INSTRUCTION");
  IElementType CLREX_INSTRUCTION = new ARMv7ElementType("CLREX_INSTRUCTION");
  IElementType CLZ_INSTRUCTION = new ARMv7ElementType("CLZ_INSTRUCTION");
  IElementType CMN_INSTRUCTION = new ARMv7ElementType("CMN_INSTRUCTION");
  IElementType CMP_INSTRUCTION = new ARMv7ElementType("CMP_INSTRUCTION");
  IElementType GLOBAL_DIRECTIVE = new ARMv7ElementType("GLOBAL_DIRECTIVE");
  IElementType LABEL = new ARMv7ElementType("LABEL");
  IElementType NUMBER = new ARMv7ElementType("NUMBER");
  IElementType NUMBERS = new ARMv7ElementType("NUMBERS");
  IElementType REGISTER = new ARMv7ElementType("REGISTER");
  IElementType REGISTERS = new ARMv7ElementType("REGISTERS");
  IElementType SHIFT = new ARMv7ElementType("SHIFT");
  IElementType SHIFT_TYPE = new ARMv7ElementType("SHIFT_TYPE");

  IElementType ADC = new ARMv7TokenType("adc");
  IElementType ADD = new ARMv7TokenType("add");
  IElementType ADR = new ARMv7TokenType("adr");
  IElementType AL = new ARMv7TokenType("al");
  IElementType ALIGN = new ARMv7TokenType("align");
  IElementType AND = new ARMv7TokenType("and");
  IElementType ASCII = new ARMv7TokenType("ascii");
  IElementType ASCIZ = new ARMv7TokenType("asciz");
  IElementType ASR = new ARMv7TokenType("asr");
  IElementType B = new ARMv7TokenType("b");
  IElementType BALIGN = new ARMv7TokenType("balign");
  IElementType BALIGNL = new ARMv7TokenType("balignl");
  IElementType BALIGNW = new ARMv7TokenType("balignw");
  IElementType BFC = new ARMv7TokenType("bfc");
  IElementType BFI = new ARMv7TokenType("bfi");
  IElementType BIC = new ARMv7TokenType("bic");
  IElementType BINARY_NUMBER = new ARMv7TokenType("binary number");
  IElementType BKPT = new ARMv7TokenType("bkpt");
  IElementType BL = new ARMv7TokenType("bl");
  IElementType BX = new ARMv7TokenType("bx");
  IElementType BXJ = new ARMv7TokenType("bxj");
  IElementType BYTE = new ARMv7TokenType("byte");
  IElementType CBNZ = new ARMv7TokenType("cbnz");
  IElementType CBZ = new ARMv7TokenType("cbz");
  IElementType CC = new ARMv7TokenType("cc");
  IElementType CLREX = new ARMv7TokenType("clrex");
  IElementType CLZ = new ARMv7TokenType("clz");
  IElementType CMN = new ARMv7TokenType("cmn");
  IElementType CMP = new ARMv7TokenType("cmp");
  IElementType COLON = new ARMv7TokenType(":");
  IElementType COMM = new ARMv7TokenType("comm");
  IElementType COMMA = new ARMv7TokenType(",");
  IElementType COMMENT = new ARMv7TokenType("comment");
  IElementType CPSR = new ARMv7TokenType("CPSR");
  IElementType CS = new ARMv7TokenType("cs");
  IElementType DATA = new ARMv7TokenType("data");
  IElementType DECIMAL_NUMBER = new ARMv7TokenType("decimal number");
  IElementType DEF = new ARMv7TokenType("def");
  IElementType DESC = new ARMv7TokenType("desc");
  IElementType DIM = new ARMv7TokenType("dim");
  IElementType DOT = new ARMv7TokenType(".");
  IElementType DOUBLE = new ARMv7TokenType("double");
  IElementType EJECT = new ARMv7TokenType("eject");
  IElementType ELSE = new ARMv7TokenType("else");
  IElementType ENDEF = new ARMv7TokenType("endef");
  IElementType ENDIF = new ARMv7TokenType("endif");
  IElementType EQ = new ARMv7TokenType("eq");
  IElementType EQU = new ARMv7TokenType("equ");
  IElementType EQUIV = new ARMv7TokenType("equiv");
  IElementType ERR = new ARMv7TokenType("err");
  IElementType EXTERN = new ARMv7TokenType("extern");
  IElementType FILE = new ARMv7TokenType("file");
  IElementType FILL = new ARMv7TokenType("fill");
  IElementType FLOAT = new ARMv7TokenType("float");
  IElementType GE = new ARMv7TokenType("ge");
  IElementType GLOBAL = new ARMv7TokenType("global");
  IElementType GT = new ARMv7TokenType("gt");
  IElementType HEXADECIMAL_NUMBER = new ARMv7TokenType("hexadecimal number");
  IElementType HI = new ARMv7TokenType("hi");
  IElementType HS = new ARMv7TokenType("hs");
  IElementType HWORD = new ARMv7TokenType("hword");
  IElementType IDENT = new ARMv7TokenType("ident");
  IElementType IDENTIFIER = new ARMv7TokenType("identifier");
  IElementType IF = new ARMv7TokenType("if");
  IElementType INCLUDE = new ARMv7TokenType("include");
  IElementType INT = new ARMv7TokenType("int");
  IElementType IRP = new ARMv7TokenType("irp");
  IElementType IRPC = new ARMv7TokenType("irpc");
  IElementType LCOMM = new ARMv7TokenType("lcomm");
  IElementType LE = new ARMv7TokenType("le");
  IElementType LFLAGS = new ARMv7TokenType("lflags");
  IElementType LINE_FEED = new ARMv7TokenType("lf");
  IElementType LINKONCE = new ARMv7TokenType("linkonce");
  IElementType LIST = new ARMv7TokenType("list");
  IElementType LN = new ARMv7TokenType("ln");
  IElementType LO = new ARMv7TokenType("lo");
  IElementType LONG = new ARMv7TokenType("long");
  IElementType LR = new ARMv7TokenType("lr");
  IElementType LS = new ARMv7TokenType("ls");
  IElementType LSL = new ARMv7TokenType("lsl");
  IElementType LSR = new ARMv7TokenType("lsr");
  IElementType LT = new ARMv7TokenType("lt");
  IElementType MACRO = new ARMv7TokenType("macro");
  IElementType MI = new ARMv7TokenType("mi");
  IElementType NE = new ARMv7TokenType("ne");
  IElementType NOLIST = new ARMv7TokenType("nolist");
  IElementType OCTA = new ARMv7TokenType("octa");
  IElementType OCTAL_NUMBER = new ARMv7TokenType("octal number");
  IElementType ORG = new ARMv7TokenType("org");
  IElementType P2ALIGN = new ARMv7TokenType("p2align");
  IElementType P2ALIGNL = new ARMv7TokenType("p2alignl");
  IElementType P2ALIGNW = new ARMv7TokenType("p2alignw");
  IElementType PC = new ARMv7TokenType("PC");
  IElementType PL = new ARMv7TokenType("pl");
  IElementType POUND = new ARMv7TokenType("#");
  IElementType PSIZE = new ARMv7TokenType("psize");
  IElementType QUAD = new ARMv7TokenType("quad");
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
  IElementType REPT = new ARMv7TokenType("rept");
  IElementType ROR = new ARMv7TokenType("ror");
  IElementType RRX = new ARMv7TokenType("rrx");
  IElementType S = new ARMv7TokenType("s");
  IElementType SBTTL = new ARMv7TokenType("sbttl");
  IElementType SCL = new ARMv7TokenType("scl");
  IElementType SECTION = new ARMv7TokenType("section");
  IElementType SET = new ARMv7TokenType("set");
  IElementType SHORT = new ARMv7TokenType("short");
  IElementType SINGLE = new ARMv7TokenType("single");
  IElementType SIZE = new ARMv7TokenType("size");
  IElementType SKIP = new ARMv7TokenType("skip");
  IElementType SLEB128 = new ARMv7TokenType("sleb128");
  IElementType SP = new ARMv7TokenType("sp");
  IElementType SPACE = new ARMv7TokenType("space");
  IElementType SPSR = new ARMv7TokenType("SPSR");
  IElementType STABD = new ARMv7TokenType("stabd");
  IElementType STABN = new ARMv7TokenType("stabn");
  IElementType STABS = new ARMv7TokenType("stabs");
  IElementType STRING = new ARMv7TokenType("string");
  IElementType SYMVER = new ARMv7TokenType("symver");
  IElementType TAG = new ARMv7TokenType("tag");
  IElementType TEXT = new ARMv7TokenType("text");
  IElementType TITLE = new ARMv7TokenType("title");
  IElementType TYPE = new ARMv7TokenType("type");
  IElementType ULEB128 = new ARMv7TokenType("uleb128");
  IElementType VAR = new ARMv7TokenType("var");
  IElementType VC = new ARMv7TokenType("vc");
  IElementType VS = new ARMv7TokenType("vs");
  IElementType WORD = new ARMv7TokenType("word");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADC_INSTRUCTION) {
        return new ARMv7AdcInstructionImpl(node);
      }
      else if (type == ADD_INSTRUCTION) {
        return new ARMv7AddInstructionImpl(node);
      }
      else if (type == ADR_INSTRUCTION) {
        return new ARMv7AdrInstructionImpl(node);
      }
      else if (type == ALIGN_DIRECTIVE) {
        return new ARMv7AlignDirectiveImpl(node);
      }
      else if (type == AND_INSTRUCTION) {
        return new ARMv7AndInstructionImpl(node);
      }
      else if (type == ASCII_DIRECTIVE) {
        return new ARMv7AsciiDirectiveImpl(node);
      }
      else if (type == ASR_INSTRUCTION) {
        return new ARMv7AsrInstructionImpl(node);
      }
      else if (type == BASED) {
        return new ARMv7BasedImpl(node);
      }
      else if (type == BFC_INSTRUCTION) {
        return new ARMv7BfcInstructionImpl(node);
      }
      else if (type == BFI_INSTRUCTION) {
        return new ARMv7BfiInstructionImpl(node);
      }
      else if (type == BIC_INSTRUCTION) {
        return new ARMv7BicInstructionImpl(node);
      }
      else if (type == BKPT_INSTRUCTION) {
        return new ARMv7BkptInstructionImpl(node);
      }
      else if (type == BL_INSTRUCTION) {
        return new ARMv7BlInstructionImpl(node);
      }
      else if (type == BXJ_INSTRUCTION) {
        return new ARMv7BxjInstructionImpl(node);
      }
      else if (type == BX_INSTRUCTION) {
        return new ARMv7BxInstructionImpl(node);
      }
      else if (type == B_INSTRUCTION) {
        return new ARMv7BInstructionImpl(node);
      }
      else if (type == CBNZ_INSTRUCTION) {
        return new ARMv7CbnzInstructionImpl(node);
      }
      else if (type == CBZ_INSTRUCTION) {
        return new ARMv7CbzInstructionImpl(node);
      }
      else if (type == CLREX_INSTRUCTION) {
        return new ARMv7ClrexInstructionImpl(node);
      }
      else if (type == CLZ_INSTRUCTION) {
        return new ARMv7ClzInstructionImpl(node);
      }
      else if (type == CMN_INSTRUCTION) {
        return new ARMv7CmnInstructionImpl(node);
      }
      else if (type == CMP_INSTRUCTION) {
        return new ARMv7CmpInstructionImpl(node);
      }
      else if (type == GLOBAL_DIRECTIVE) {
        return new ARMv7GlobalDirectiveImpl(node);
      }
      else if (type == LABEL) {
        return new ARMv7LabelImpl(node);
      }
      else if (type == NUMBER) {
        return new ARMv7NumberImpl(node);
      }
      else if (type == NUMBERS) {
        return new ARMv7NumbersImpl(node);
      }
      else if (type == REGISTER) {
        return new ARMv7RegisterImpl(node);
      }
      else if (type == REGISTERS) {
        return new ARMv7RegistersImpl(node);
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
