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
  // AdcInstruction
  //     | AddInstruction
  //     | AdrInstruction
  //     | AndInstruction
  //     | AsrInstruction
  //     | BfcInstruction
  //     | BfiInstruction
  //     | BicInstruction
  static boolean ARMv7ArithmeticInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7ArithmeticInstructions")) return false;
    boolean r;
    r = AdcInstruction(b, l + 1);
    if (!r) r = AddInstruction(b, l + 1);
    if (!r) r = AdrInstruction(b, l + 1);
    if (!r) r = AndInstruction(b, l + 1);
    if (!r) r = AsrInstruction(b, l + 1);
    if (!r) r = BfcInstruction(b, l + 1);
    if (!r) r = BfiInstruction(b, l + 1);
    if (!r) r = BicInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BInstruction
  static boolean ARMv7BranchInstructions(PsiBuilder b, int l) {
    return BInstruction(b, l + 1);
  }

  /* ********************************************************** */
  // AlignDirective | AsciiDirective | GlobalDirective
  static boolean ARMv7Directives(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7Directives")) return false;
    boolean r;
    r = AlignDirective(b, l + 1);
    if (!r) r = AsciiDirective(b, l + 1);
    if (!r) r = GlobalDirective(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ADC Registers (COMMA (Number | Shift))?
  public static boolean AdcInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction")) return false;
    if (!nextTokenIs(b, ADC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADC);
    r = r && Registers(b, l + 1);
    r = r && AdcInstruction_2(b, l + 1);
    exit_section_(b, m, ADC_INSTRUCTION, r);
    return r;
  }

  // (COMMA (Number | Shift))?
  private static boolean AdcInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_2")) return false;
    AdcInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA (Number | Shift)
  private static boolean AdcInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && AdcInstruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Shift
  private static boolean AdcInstruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdcInstruction_2_0_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Shift(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ADD Registers (COMMA (Number | Shift))?
  public static boolean AddInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction")) return false;
    if (!nextTokenIs(b, ADD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADD);
    r = r && Registers(b, l + 1);
    r = r && AddInstruction_2(b, l + 1);
    exit_section_(b, m, ADD_INSTRUCTION, r);
    return r;
  }

  // (COMMA (Number | Shift))?
  private static boolean AddInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_2")) return false;
    AddInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA (Number | Shift)
  private static boolean AddInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && AddInstruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Shift
  private static boolean AddInstruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AddInstruction_2_0_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Shift(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ADR Register COMMA IDENTIFIER
  public static boolean AdrInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdrInstruction")) return false;
    if (!nextTokenIs(b, ADR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADR);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, IDENTIFIER);
    exit_section_(b, m, ADR_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // ALIGN <<CommaSeparatedList Based>>
  public static boolean AlignDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AlignDirective")) return false;
    if (!nextTokenIs(b, ALIGN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ALIGN);
    r = r && CommaSeparatedList(b, l + 1, ARMv7Parser::Based);
    exit_section_(b, m, ALIGN_DIRECTIVE, r);
    return r;
  }

  /* ********************************************************** */
  // AND Registers (COMMA (Number | Shift))?
  public static boolean AndInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndInstruction")) return false;
    if (!nextTokenIs(b, AND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, AND);
    r = r && Registers(b, l + 1);
    r = r && AndInstruction_2(b, l + 1);
    exit_section_(b, m, AND_INSTRUCTION, r);
    return r;
  }

  // (COMMA (Number | Shift))?
  private static boolean AndInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndInstruction_2")) return false;
    AndInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA (Number | Shift)
  private static boolean AndInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && AndInstruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Shift
  private static boolean AndInstruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AndInstruction_2_0_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Shift(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ASCII <<CommaSeparatedList STRING>>
  public static boolean AsciiDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsciiDirective")) return false;
    if (!nextTokenIs(b, ASCII)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASCII);
    r = r && CommaSeparatedList(b, l + 1, STRING_parser_);
    exit_section_(b, m, ASCII_DIRECTIVE, r);
    return r;
  }

  /* ********************************************************** */
  // ASR Registers (COMMA Number)?
  public static boolean AsrInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsrInstruction")) return false;
    if (!nextTokenIs(b, ASR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASR);
    r = r && Registers(b, l + 1);
    r = r && AsrInstruction_2(b, l + 1);
    exit_section_(b, m, ASR_INSTRUCTION, r);
    return r;
  }

  // (COMMA Number)?
  private static boolean AsrInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsrInstruction_2")) return false;
    AsrInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA Number
  private static boolean AsrInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsrInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // DOT ARMv7Directives
  static boolean AssemblerDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AssemblerDirective")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && ARMv7Directives(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // B IDENTIFIER
  public static boolean BInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BInstruction")) return false;
    if (!nextTokenIs(b, B)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, B, IDENTIFIER);
    exit_section_(b, m, B_INSTRUCTION, r);
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
  // BFC Register COMMA Numbers
  public static boolean BfcInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BfcInstruction")) return false;
    if (!nextTokenIs(b, BFC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BFC);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Numbers(b, l + 1);
    exit_section_(b, m, BFC_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // BFI Registers COMMA Numbers
  public static boolean BfiInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BfiInstruction")) return false;
    if (!nextTokenIs(b, BFI)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BFI);
    r = r && Registers(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Numbers(b, l + 1);
    exit_section_(b, m, BFI_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // BIC Registers (COMMA (Number | Shift))?
  public static boolean BicInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BicInstruction")) return false;
    if (!nextTokenIs(b, BIC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BIC);
    r = r && Registers(b, l + 1);
    r = r && BicInstruction_2(b, l + 1);
    exit_section_(b, m, BIC_INSTRUCTION, r);
    return r;
  }

  // (COMMA (Number | Shift))?
  private static boolean BicInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BicInstruction_2")) return false;
    BicInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA (Number | Shift)
  private static boolean BicInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BicInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && BicInstruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Shift
  private static boolean BicInstruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BicInstruction_2_0_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Shift(b, l + 1);
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
  // AssemblerDirective | Instruction | Label
  static boolean DirectiveOrInstructionOrLabel(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DirectiveOrInstructionOrLabel")) return false;
    boolean r;
    r = AssemblerDirective(b, l + 1);
    if (!r) r = Instruction(b, l + 1);
    if (!r) r = Label(b, l + 1);
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
  // GLOBAL IDENTIFIER
  public static boolean GlobalDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalDirective")) return false;
    if (!nextTokenIs(b, GLOBAL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, GLOBAL, IDENTIFIER);
    exit_section_(b, m, GLOBAL_DIRECTIVE, r);
    return r;
  }

  /* ********************************************************** */
  // ARMv7ArithmeticInstructions | ARMv7BranchInstructions
  static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    r = ARMv7ArithmeticInstructions(b, l + 1);
    if (!r) r = ARMv7BranchInstructions(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LineFeed | DirectiveOrInstructionOrLabel LineFeed?
  public static boolean Item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ITEM, "<item>");
    r = LineFeed(b, l + 1);
    if (!r) r = Item_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // DirectiveOrInstructionOrLabel LineFeed?
  private static boolean Item_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = DirectiveOrInstructionOrLabel(b, l + 1);
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
  // <<CommaSeparatedList Number>>
  public static boolean Numbers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Numbers")) return false;
    if (!nextTokenIs(b, POUND)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = CommaSeparatedList(b, l + 1, ARMv7Parser::Number);
    exit_section_(b, m, NUMBERS, r);
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

  static final Parser STRING_parser_ = (b, l) -> consumeToken(b, STRING);
}
