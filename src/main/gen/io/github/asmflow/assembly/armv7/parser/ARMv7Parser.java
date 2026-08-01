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
  // BINARY_NUMBER
  public static boolean BinaryLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BinaryLiteral")) return false;
    if (!nextTokenIs(b, BINARY_NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BINARY_NUMBER);
    exit_section_(b, m, BINARY_LITERAL, r);
    return r;
  }

  /* ********************************************************** */
  // DECIMAL_NUMBER
  public static boolean DecimalLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DecimalLiteral")) return false;
    if (!nextTokenIs(b, DECIMAL_NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DECIMAL_NUMBER);
    exit_section_(b, m, DECIMAL_LITERAL, r);
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
  // Number | Sign? RegisterWithShift
  public static boolean FlexibleOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlexibleOffset")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FLEXIBLE_OFFSET, "<flexible offset>");
    r = Number(b, l + 1);
    if (!r) r = FlexibleOffset_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // Sign? RegisterWithShift
  private static boolean FlexibleOffset_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlexibleOffset_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = FlexibleOffset_1_0(b, l + 1);
    r = r && RegisterWithShift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Sign?
  private static boolean FlexibleOffset_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "FlexibleOffset_1_0")) return false;
    Sign(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // HEXADECIMAL_NUMBER
  public static boolean HexadecimalLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HexadecimalLiteral")) return false;
    if (!nextTokenIs(b, HEXADECIMAL_NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEXADECIMAL_NUMBER);
    exit_section_(b, m, HEXADECIMAL_LITERAL, r);
    return r;
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
  // POUND Sign? NumberLiteral
  public static boolean Number(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Number")) return false;
    if (!nextTokenIs(b, POUND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, POUND);
    r = r && Number_1(b, l + 1);
    r = r && NumberLiteral(b, l + 1);
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
  // !<<afterWhitespace>> (BinaryLiteral | DecimalLiteral | HexadecimalLiteral | OctalLiteral)
  static boolean NumberLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = NumberLiteral_0(b, l + 1);
    r = r && NumberLiteral_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // !<<afterWhitespace>>
  private static boolean NumberLiteral_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral_0")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !afterWhitespace(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // BinaryLiteral | DecimalLiteral | HexadecimalLiteral | OctalLiteral
  private static boolean NumberLiteral_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NumberLiteral_1")) return false;
    boolean r;
    r = BinaryLiteral(b, l + 1);
    if (!r) r = DecimalLiteral(b, l + 1);
    if (!r) r = HexadecimalLiteral(b, l + 1);
    if (!r) r = OctalLiteral(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // OCTAL_NUMBER
  public static boolean OctalLiteral(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "OctalLiteral")) return false;
    if (!nextTokenIs(b, OCTAL_NUMBER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OCTAL_NUMBER);
    exit_section_(b, m, OCTAL_LITERAL, r);
    return r;
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
  // LBRACKET Register RBRACKET COMMA FlexibleOffset
  public static boolean Postindexed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Postindexed")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, RBRACKET, COMMA);
    r = r && FlexibleOffset(b, l + 1);
    exit_section_(b, m, POSTINDEXED, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register (COMMA FlexibleOffset)? RBRACKET BANG?
  public static boolean Preindexed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preindexed")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && Preindexed_2(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && Preindexed_4(b, l + 1);
    exit_section_(b, m, PREINDEXED, r);
    return r;
  }

  // (COMMA FlexibleOffset)?
  private static boolean Preindexed_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preindexed_2")) return false;
    Preindexed_2_0(b, l + 1);
    return true;
  }

  // COMMA FlexibleOffset
  private static boolean Preindexed_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preindexed_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && FlexibleOffset(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BANG?
  private static boolean Preindexed_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Preindexed_4")) return false;
    consumeToken(b, BANG);
    return true;
  }

  /* ********************************************************** */
  // MINUS? REG
  public static boolean Register(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register")) return false;
    if (!nextTokenIs(b, "<register>", MINUS, REG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTER, "<register>");
    r = Register_0(b, l + 1);
    r = r && consumeToken(b, REG);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // MINUS?
  private static boolean Register_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register_0")) return false;
    consumeToken(b, MINUS);
    return true;
  }

  /* ********************************************************** */
  // Postindexed
  //   | Preindexed
  //   | RegisterWithShift
  static boolean RegisterOperand(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterOperand")) return false;
    boolean r;
    r = Postindexed(b, l + 1);
    if (!r) r = Preindexed(b, l + 1);
    if (!r) r = RegisterWithShift(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // Register (COMMA Shift)?
  public static boolean RegisterWithShift(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithShift")) return false;
    if (!nextTokenIs(b, "<register with shift>", MINUS, REG)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTER_WITH_SHIFT, "<register with shift>");
    r = Register(b, l + 1);
    r = r && RegisterWithShift_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
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
