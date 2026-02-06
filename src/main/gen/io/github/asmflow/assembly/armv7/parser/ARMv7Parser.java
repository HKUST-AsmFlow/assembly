// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;
import static io.github.asmflow.assembly.armv7.parser.ARMv7ParserUtil.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ARMv7Parser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return File(b, l + 1);
  }

  /* ********************************************************** */
  // AdcInstruction | AddInstruction
  static boolean ARMv7ArithmeticInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7ArithmeticInstructions")) return false;
    if (!nextTokenIs(b, "", ADC, ADD)) return false;
    boolean r;
    r = AdcInstruction(b, l + 1);
    if (!r) r = AddInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ARMv7ArithmeticInstructions
  static boolean ARMv7Instructions(PsiBuilder b, int l) {
    return ARMv7ArithmeticInstructions(b, l + 1);
  }

  /* ********************************************************** */
  // ADC SetConditionFlags? ConditionCodes? Registers (COMMA Shift)?
  public static boolean AdcInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction")) return false;
    if (!nextTokenIs(b, ADC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADC);
    r = r && AdcInstruction_1(b, l + 1);
    r = r && AdcInstruction_2(b, l + 1);
    r = r && Registers(b, l + 1);
    r = r && AdcInstruction_4(b, l + 1);
    exit_section_(b, m, ADC_INSTRUCTION, r);
    return r;
  }

  // SetConditionFlags?
  private static boolean AdcInstruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_1")) return false;
    SetConditionFlags(b, l + 1);
    return true;
  }

  // ConditionCodes?
  private static boolean AdcInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_2")) return false;
    ConditionCodes(b, l + 1);
    return true;
  }

  // (COMMA Shift)?
  private static boolean AdcInstruction_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_4")) return false;
    AdcInstruction_4_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean AdcInstruction_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ADD SetConditionFlags? ConditionCodes? Registers (COMMA Shift)?
  public static boolean AddInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction")) return false;
    if (!nextTokenIs(b, ADD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADD);
    r = r && AddInstruction_1(b, l + 1);
    r = r && AddInstruction_2(b, l + 1);
    r = r && Registers(b, l + 1);
    r = r && AddInstruction_4(b, l + 1);
    exit_section_(b, m, ADD_INSTRUCTION, r);
    return r;
  }

  // SetConditionFlags?
  private static boolean AddInstruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_1")) return false;
    SetConditionFlags(b, l + 1);
    return true;
  }

  // ConditionCodes?
  private static boolean AddInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_2")) return false;
    ConditionCodes(b, l + 1);
    return true;
  }

  // (COMMA Shift)?
  private static boolean AddInstruction_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_4")) return false;
    AddInstruction_4_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean AddInstruction_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !<<afterWhitespace>> (BINARY_NUMBER | DECIMAL_NUMBER | HEXADECIMAL_NUMBER | OCTAL_NUMBER)
  public static boolean Based(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Based")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BASED, "<based>");
    r = Based_0(b, l + 1);
    r = r && Based_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // !<<afterWhitespace>>
  private static boolean Based_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Based_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !afterWhitespace(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BINARY_NUMBER | DECIMAL_NUMBER | HEXADECIMAL_NUMBER | OCTAL_NUMBER
  private static boolean Based_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Based_1")) return false;
    boolean r;
    r = consumeToken(b, BINARY_NUMBER);
    if (!r) r = consumeToken(b, DECIMAL_NUMBER);
    if (!r) r = consumeToken(b, HEXADECIMAL_NUMBER);
    if (!r) r = consumeToken(b, OCTAL_NUMBER);
    return r;
  }

  /* ********************************************************** */
  // <<T>> (COMMA <<T>>)*
  static boolean CommaSeparatedList(PsiBuilder b, int l, Parser _T) {
    if (!recursion_guard_(b, l, "CommaSeparatedList")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = _T.parse(b, l);
    r = r && CommaSeparatedList_1(b, l + 1, _T);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA <<T>>)*
  private static boolean CommaSeparatedList_1(PsiBuilder b, int l, Parser _T) {
    if (!recursion_guard_(b, l, "CommaSeparatedList_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!CommaSeparatedList_1_0(b, l + 1, _T)) break;
      if (!empty_element_parsed_guard_(b, "CommaSeparatedList_1", c)) break;
    }
    return true;
  }

  // COMMA <<T>>
  private static boolean CommaSeparatedList_1_0(PsiBuilder b, int l, Parser _T) {
    if (!recursion_guard_(b, l, "CommaSeparatedList_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && _T.parse(b, l);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !<<afterWhitespace>> (EQ | NE | CS | HS | CC | LO | MI | PL | VS | VC | HI | LS | GE | LT | GT | LE | AL)
  public static boolean ConditionCodes(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConditionCodes")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONDITION_CODES, "<condition codes>");
    r = ConditionCodes_0(b, l + 1);
    r = r && ConditionCodes_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // !<<afterWhitespace>>
  private static boolean ConditionCodes_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConditionCodes_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !afterWhitespace(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // EQ | NE | CS | HS | CC | LO | MI | PL | VS | VC | HI | LS | GE | LT | GT | LE | AL
  private static boolean ConditionCodes_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ConditionCodes_1")) return false;
    boolean r;
    r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NE);
    if (!r) r = consumeToken(b, CS);
    if (!r) r = consumeToken(b, HS);
    if (!r) r = consumeToken(b, CC);
    if (!r) r = consumeToken(b, LO);
    if (!r) r = consumeToken(b, MI);
    if (!r) r = consumeToken(b, PL);
    if (!r) r = consumeToken(b, VS);
    if (!r) r = consumeToken(b, VC);
    if (!r) r = consumeToken(b, HI);
    if (!r) r = consumeToken(b, LS);
    if (!r) r = consumeToken(b, GE);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, GT);
    if (!r) r = consumeToken(b, LE);
    if (!r) r = consumeToken(b, AL);
    return r;
  }

  /* ********************************************************** */
  // Item*
  static boolean File(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "File")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Item(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "File", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // ARMv7Instructions
  static boolean Instruction(PsiBuilder b, int l) {
    return ARMv7Instructions(b, l + 1);
  }

  /* ********************************************************** */
  // Instruction | Label
  static boolean InstructionOrLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "InstructionOrLabel")) return false;
    boolean r;
    r = Instruction(b, l + 1);
    if (!r) r = Label(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LineFeed | InstructionOrLabel LineFeed?
  public static boolean Item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM, "<item>");
    r = LineFeed(b, l + 1);
    if (!r) r = Item_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // InstructionOrLabel LineFeed?
  private static boolean Item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = InstructionOrLabel(b, l + 1);
    r = r && Item_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LineFeed?
  private static boolean Item_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1_1")) return false;
    LineFeed(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER COLON
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, IDENTIFIER, COLON);
    exit_section_(b, m, LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // LINE_FEED+
  static boolean LineFeed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LineFeed")) return false;
    if (!nextTokenIsFast(b, LINE_FEED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokenFast(b, LINE_FEED);
    while (r) {
      int c = current_position_(b);
      if (!consumeTokenFast(b, LINE_FEED)) break;
      if (!empty_element_parsed_guard_(b, "LineFeed", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // POUND Based
  public static boolean Number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number")) return false;
    if (!nextTokenIs(b, POUND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POUND);
    r = r && Based(b, l + 1);
    exit_section_(b, m, NUMBER, r);
    return r;
  }

  /* ********************************************************** */
  // R0 | R1 | R2 | R3 | R4 | R5 | R6 | R7 | R8 | R9| R10 | R11 | R12 | SP | LR | PC | CPSR | SPSR
  public static boolean Register(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTER, "<register>");
    r = consumeToken(b, R0);
    if (!r) r = consumeToken(b, R1);
    if (!r) r = consumeToken(b, R2);
    if (!r) r = consumeToken(b, R3);
    if (!r) r = consumeToken(b, R4);
    if (!r) r = consumeToken(b, R5);
    if (!r) r = consumeToken(b, R6);
    if (!r) r = consumeToken(b, R7);
    if (!r) r = consumeToken(b, R8);
    if (!r) r = consumeToken(b, R9);
    if (!r) r = consumeToken(b, R10);
    if (!r) r = consumeToken(b, R11);
    if (!r) r = consumeToken(b, R12);
    if (!r) r = consumeToken(b, SP);
    if (!r) r = consumeToken(b, LR);
    if (!r) r = consumeToken(b, PC);
    if (!r) r = consumeToken(b, CPSR);
    if (!r) r = consumeToken(b, SPSR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // <<CommaSeparatedList Register>>
  public static boolean Registers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Registers")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTERS, "<registers>");
    r = CommaSeparatedList(b, l + 1, ARMv7Parser::Register);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // !<<afterWhitespace>> (S)
  public static boolean SetConditionFlags(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetConditionFlags")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SET_CONDITION_FLAGS, "<set condition flags>");
    r = SetConditionFlags_0(b, l + 1);
    r = r && consumeToken(b, S);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // !<<afterWhitespace>>
  private static boolean SetConditionFlags_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "SetConditionFlags_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !afterWhitespace(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ShiftType (Number | Register)
  public static boolean Shift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Shift")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHIFT, "<shift>");
    r = ShiftType(b, l + 1);
    r = r && Shift_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Number | Register
  private static boolean Shift_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Shift_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Register(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ASR | LSL | LSR | ROR | RRX
  public static boolean ShiftType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftType")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SHIFT_TYPE, "<shift type>");
    r = consumeToken(b, ASR);
    if (!r) r = consumeToken(b, LSL);
    if (!r) r = consumeToken(b, LSR);
    if (!r) r = consumeToken(b, ROR);
    if (!r) r = consumeToken(b, RRX);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
