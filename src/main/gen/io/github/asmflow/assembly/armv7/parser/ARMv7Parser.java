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
  // !<<afterWhitespace>> (BINARY_NUMBER | DECIMAL_NUMBER | HEXADECIMAL_NUMBER | OCTAL_NUMBER)
  static boolean Based(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Based")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Based_0(b, l + 1);
    r = r && Based_1(b, l + 1);
    exit_section_(b, m, null, r);
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
  // DOT DirectiveName DirectiveParameters?
  public static boolean Directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && DirectiveName(b, l + 1);
    r = r && Directive_2(b, l + 1);
    exit_section_(b, m, DIRECTIVE, r);
    return r;
  }

  // DirectiveParameters?
  private static boolean Directive_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive_2")) return false;
    DirectiveParameters(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean DirectiveName(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveName")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, DIRECTIVE_NAME, r);
    return r;
  }

  /* ********************************************************** */
  // Label | STRING
  public static boolean DirectiveParameter(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveParameter")) return false;
    if (!nextTokenIs(b, "<directive parameter>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_PARAMETER, "<directive parameter>");
    r = Label(b, l + 1);
    if (!r) r = consumeToken(b, STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // <<CommaSeparatedList DirectiveParameter>>
  public static boolean DirectiveParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveParameters")) return false;
    if (!nextTokenIs(b, "<directive parameters>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_PARAMETERS, "<directive parameters>");
    r = CommaSeparatedList(b, l + 1, ARMv7Parser::DirectiveParameter);
    exit_section_(b, l, m, r, false, null);
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
  // Mnemonic Operands?
  public static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Mnemonic(b, l + 1);
    r = r && Instruction_1(b, l + 1);
    exit_section_(b, m, INSTRUCTION, r);
    return r;
  }

  // Operands?
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    Operands(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // LineFeed | (LabelWithColon | Directive | Instruction) LineFeed?
  static boolean Item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LineFeed(b, l + 1);
    if (!r) r = Item_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LabelWithColon | Directive | Instruction) LineFeed?
  private static boolean Item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Item_1_0(b, l + 1);
    r = r && Item_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // LabelWithColon | Directive | Instruction
  private static boolean Item_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1_0")) return false;
    boolean r;
    r = LabelWithColon(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    return r;
  }

  // LineFeed?
  private static boolean Item_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1_1")) return false;
    LineFeed(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // Label COLON
  public static boolean LabelWithColon(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LabelWithColon")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Label(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, LABEL_WITH_COLON, r);
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
  // IDENTIFIER
  public static boolean Mnemonic(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Mnemonic")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, MNEMONIC, r);
    return r;
  }

  /* ********************************************************** */
  // POUND (PLUS | MINUS)? Based
  public static boolean Number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number")) return false;
    if (!nextTokenIs(b, POUND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POUND);
    r = r && Number_1(b, l + 1);
    r = r && Based(b, l + 1);
    exit_section_(b, m, NUMBER, r);
    return r;
  }

  // (PLUS | MINUS)?
  private static boolean Number_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number_1")) return false;
    Number_1_0(b, l + 1);
    return true;
  }

  // PLUS | MINUS
  private static boolean Number_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number_1_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register (COMMA Number)? RBRACKET
  public static boolean Offset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Offset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && Offset_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, OFFSET, r);
    return r;
  }

  // (COMMA Number)?
  private static boolean Offset_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Offset_2")) return false;
    Offset_2_0(b, l + 1);
    return true;
  }

  // COMMA Number
  private static boolean Offset_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Offset_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register COMMA RegisterWithShift RBRACKET BANG?
  public static boolean OffsetVariant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OffsetVariant")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && RegisterWithShift(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && OffsetVariant_5(b, l + 1);
    exit_section_(b, m, OFFSET_VARIANT, r);
    return r;
  }

  // BANG?
  private static boolean OffsetVariant_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OffsetVariant_5")) return false;
    consumeToken(b, BANG);
    return true;
  }

  /* ********************************************************** */
  // RegisterOperand | Label | Number
  public static boolean Operand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operand")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERAND, "<operand>");
    r = RegisterOperand(b, l + 1);
    if (!r) r = Label(b, l + 1);
    if (!r) r = Number(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // <<CommaSeparatedList Operand>>
  public static boolean Operands(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operands")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERANDS, "<operands>");
    r = CommaSeparatedList(b, l + 1, ARMv7Parser::Operand);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register RBRACKET COMMA Number
  public static boolean Postindexed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Postindexed")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, RBRACKET, COMMA);
    r = r && Number(b, l + 1);
    exit_section_(b, m, POSTINDEXED, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register COMMA Number RBRACKET BANG
  public static boolean Preindexed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preindexed")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Number(b, l + 1);
    r = r && consumeTokens(b, 0, RBRACKET, BANG);
    exit_section_(b, m, PREINDEXED, r);
    return r;
  }

  /* ********************************************************** */
  // R0 | R1 | R2 | R3 | R4 | R5 | R6 | R7 | R8 | R9 | R10 | R11 | R12 | SP | LR | PC | CPSR | SPSR
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
  // RegisterWithShift
  //   | Postindexed
  //   | Offset
  //   | OffsetVariant
  //   | Preindexed
  static boolean RegisterOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterOperand")) return false;
    boolean r;
    r = RegisterWithShift(b, l + 1);
    if (!r) r = Postindexed(b, l + 1);
    if (!r) r = Offset(b, l + 1);
    if (!r) r = OffsetVariant(b, l + 1);
    if (!r) r = Preindexed(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Register (COMMA ShiftType (Number | Register))?
  public static boolean RegisterWithShift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTER_WITH_SHIFT, "<register with shift>");
    r = Register(b, l + 1);
    r = r && RegisterWithShift_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA ShiftType (Number | Register))?
  private static boolean RegisterWithShift_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift_1")) return false;
    RegisterWithShift_1_0(b, l + 1);
    return true;
  }

  // COMMA ShiftType (Number | Register)
  private static boolean RegisterWithShift_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && ShiftType(b, l + 1);
    r = r && RegisterWithShift_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Register
  private static boolean RegisterWithShift_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift_1_0_2")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Register(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean ShiftType(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ShiftType")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, SHIFT_TYPE, r);
    return r;
  }

}
