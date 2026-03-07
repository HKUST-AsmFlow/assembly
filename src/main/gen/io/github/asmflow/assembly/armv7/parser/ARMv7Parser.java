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
  // DOT DirectiveName DirectiveParameters?
  public static boolean Directive(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Directive")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE, null);
    r = consumeToken(b, DOT);
    r = r && DirectiveName(b, l + 1);
    p = r; // pin = 2
    r = r && Directive_2(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
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
  // DirectiveParameter (COMMA DirectiveParameter)*
  public static boolean DirectiveParameters(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveParameters")) return false;
    if (!nextTokenIs(b, "<directive parameters>", IDENTIFIER, STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DIRECTIVE_PARAMETERS, "<directive parameters>");
    r = DirectiveParameter(b, l + 1);
    r = r && DirectiveParameters_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA DirectiveParameter)*
  private static boolean DirectiveParameters_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveParameters_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!DirectiveParameters_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "DirectiveParameters_1", c)) break;
    }
    return true;
  }

  // COMMA DirectiveParameter
  private static boolean DirectiveParameters_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveParameters_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && DirectiveParameter(b, l + 1);
    exit_section_(b, m, null, r);
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
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, INSTRUCTION, null);
    r = Mnemonic(b, l + 1);
    p = r; // pin = 1
    r = r && Instruction_1(b, l + 1);
    exit_section_(b, l, m, r, p, null);
    return r || p;
  }

  // Operands?
  private static boolean Instruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction_1")) return false;
    Operands(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // (LabelWithColon | Instruction | Directive) LINE_FEED | LINE_FEED
  static boolean Item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Item_0(b, l + 1);
    if (!r) r = consumeToken(b, LINE_FEED);
    exit_section_(b, m, null, r);
    return r;
  }

  // (LabelWithColon | Instruction | Directive) LINE_FEED
  private static boolean Item_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Item_0_0(b, l + 1);
    r = r && consumeToken(b, LINE_FEED);
    exit_section_(b, m, null, r);
    return r;
  }

  // LabelWithColon | Instruction | Directive
  private static boolean Item_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_0_0")) return false;
    boolean r;
    r = LabelWithColon(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Directive(b, l + 1);
    return r;
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
  // POUND Sign? Based
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

  // Sign?
  private static boolean Number_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number_1")) return false;
    Sign(b, l + 1);
    return true;
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
  // Operand (COMMA Operand)*
  public static boolean Operands(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operands")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPERANDS, "<operands>");
    r = Operand(b, l + 1);
    r = r && Operands_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA Operand)*
  private static boolean Operands_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operands_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!Operands_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "Operands_1", c)) break;
    }
    return true;
  }

  // COMMA Operand
  private static boolean Operands_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Operands_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Operand(b, l + 1);
    exit_section_(b, m, null, r);
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
  // REGISTER
  static boolean Register(PsiBuilder b, int l) {
    return consumeToken(b, REGISTER);
  }

  /* ********************************************************** */
  // RegisterWithShift
  //   | Postindexed
  //   | Offset
  //   | OffsetVariant
  //   | Preindexed
  static boolean RegisterOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterOperand")) return false;
    if (!nextTokenIs(b, "", LBRACKET, REGISTER)) return false;
    boolean r;
    r = RegisterWithShift(b, l + 1);
    if (!r) r = Postindexed(b, l + 1);
    if (!r) r = Offset(b, l + 1);
    if (!r) r = OffsetVariant(b, l + 1);
    if (!r) r = Preindexed(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Register (COMMA Shift)?
  public static boolean RegisterWithShift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift")) return false;
    if (!nextTokenIs(b, REGISTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Register(b, l + 1);
    r = r && RegisterWithShift_1(b, l + 1);
    exit_section_(b, m, REGISTER_WITH_SHIFT, r);
    return r;
  }

  // (COMMA Shift)?
  private static boolean RegisterWithShift_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift_1")) return false;
    RegisterWithShift_1_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean RegisterWithShift_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ShiftType (Number | Register)
  public static boolean Shift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Shift")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = ShiftType(b, l + 1);
    r = r && Shift_1(b, l + 1);
    exit_section_(b, m, SHIFT, r);
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

  /* ********************************************************** */
  // PLUS | MINUS
  public static boolean Sign(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Sign")) return false;
    if (!nextTokenIs(b, "<sign>", MINUS, PLUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, SIGN, "<sign>");
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

}
