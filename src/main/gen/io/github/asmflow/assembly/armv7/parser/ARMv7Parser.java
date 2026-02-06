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
  // ADC SetConditionFlags? ConditionCodes?
  public static boolean ADCInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ADCInstruction")) return false;
    if (!nextTokenIs(b, ADC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADC);
    r = r && ADCInstruction_1(b, l + 1);
    r = r && ADCInstruction_2(b, l + 1);
    exit_section_(b, m, ADC_INSTRUCTION, r);
    return r;
  }

  // SetConditionFlags?
  private static boolean ADCInstruction_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ADCInstruction_1")) return false;
    SetConditionFlags(b, l + 1);
    return true;
  }

  // ConditionCodes?
  private static boolean ADCInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ADCInstruction_2")) return false;
    ConditionCodes(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // ADCInstruction
  static boolean ARMv7ArithmeticInstructions(PsiBuilder b, int l) {
    return ADCInstruction(b, l + 1);
  }

  /* ********************************************************** */
  // ARMv7ArithmeticInstructions
  static boolean ARMv7Instructions(PsiBuilder b, int l) {
    return ARMv7ArithmeticInstructions(b, l + 1);
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
    if (!nextTokenIs(b, "", ADC, IDENTIFIER)) return false;
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

}
