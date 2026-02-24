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
  //     | ClzInstruction
  //     | EorInstruction
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
    if (!r) r = ClzInstruction(b, l + 1);
    if (!r) r = EorInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BInstruction
  //     | BlInstruction
  //     | BxInstruction
  //     | BxjInstruction
  //     | CbnzInstruction
  //     | CbzInstruction
  static boolean ARMv7BranchInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7BranchInstructions")) return false;
    boolean r;
    r = BInstruction(b, l + 1);
    if (!r) r = BlInstruction(b, l + 1);
    if (!r) r = BxInstruction(b, l + 1);
    if (!r) r = BxjInstruction(b, l + 1);
    if (!r) r = CbnzInstruction(b, l + 1);
    if (!r) r = CbzInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // CmnInstruction
  //     | CmpInstruction
  static boolean ARMv7ComparisonInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7ComparisonInstructions")) return false;
    if (!nextTokenIs(b, "", CMN, CMP)) return false;
    boolean r;
    r = CmnInstruction(b, l + 1);
    if (!r) r = CmpInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BkptInstruction
  //     | DbgInstruction
  static boolean ARMv7DebugInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7DebugInstructions")) return false;
    if (!nextTokenIs(b, "", BKPT, DBG)) return false;
    boolean r;
    r = BkptInstruction(b, l + 1);
    if (!r) r = DbgInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // AlignDirective | AsciiDirective | GlobalDirective
  static boolean ARMv7Directives(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7Directives")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    r = AlignDirective(b, l + 1);
    if (!r) r = AsciiDirective(b, l + 1);
    if (!r) r = GlobalDirective(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LdmInstruction
  //     | LdmfdPseudoInstruction
  //     | LdmiaPseudoInstruction
  //     | LdmdaInstruction
  //     | LdmfaPseudoInstruction
  //     | LdmdbInstruction
  //     | LdmeaPseudoInstruction
  //     | LdmibInstruction
  //     | LdmedPseudoInstruction
  //     | LdrInstruction
  //     | LdrbInstruction
  //     | LdrbtInstruction
  //     | LdrdInstruction
  //     | LdrexInstruction
  //     | LdrexbInstruction
  //     | LdrexdInstruction
  //     | LdrexhInstruction
  //     | LdrhInstruction
  //     | LdrhtInstruction
  //     | LdrsbInstruction
  //     | LdrsbtInstruction
  //     | LdrshInstruction
  static boolean ARMv7LoadInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7LoadInstructions")) return false;
    boolean r;
    r = LdmInstruction(b, l + 1);
    if (!r) r = LdmfdPseudoInstruction(b, l + 1);
    if (!r) r = LdmiaPseudoInstruction(b, l + 1);
    if (!r) r = LdmdaInstruction(b, l + 1);
    if (!r) r = LdmfaPseudoInstruction(b, l + 1);
    if (!r) r = LdmdbInstruction(b, l + 1);
    if (!r) r = LdmeaPseudoInstruction(b, l + 1);
    if (!r) r = LdmibInstruction(b, l + 1);
    if (!r) r = LdmedPseudoInstruction(b, l + 1);
    if (!r) r = LdrInstruction(b, l + 1);
    if (!r) r = LdrbInstruction(b, l + 1);
    if (!r) r = LdrbtInstruction(b, l + 1);
    if (!r) r = LdrdInstruction(b, l + 1);
    if (!r) r = LdrexInstruction(b, l + 1);
    if (!r) r = LdrexbInstruction(b, l + 1);
    if (!r) r = LdrexdInstruction(b, l + 1);
    if (!r) r = LdrexhInstruction(b, l + 1);
    if (!r) r = LdrhInstruction(b, l + 1);
    if (!r) r = LdrhtInstruction(b, l + 1);
    if (!r) r = LdrsbInstruction(b, l + 1);
    if (!r) r = LdrsbtInstruction(b, l + 1);
    if (!r) r = LdrshInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ClrexInstruction
  static boolean ARMv7LockInstructions(PsiBuilder b, int l) {
    return ClrexInstruction(b, l + 1);
  }

  /* ********************************************************** */
  // CsdbInstruction
  //     | DmbInstruction
  //     | DsbInstruction
  //     | IsbInstruction
  static boolean ARMv7SpeculativeInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARMv7SpeculativeInstructions")) return false;
    boolean r;
    r = CsdbInstruction(b, l + 1);
    if (!r) r = DmbInstruction(b, l + 1);
    if (!r) r = DsbInstruction(b, l + 1);
    if (!r) r = IsbInstruction(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // EretInstruction
  //     | HvcInstruction
  static boolean ARmv7SystemInstructions(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ARmv7SystemInstructions")) return false;
    if (!nextTokenIs(b, "", ERET, HVC)) return false;
    boolean r;
    r = EretInstruction(b, l + 1);
    if (!r) r = HvcInstruction(b, l + 1);
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
  // ADR Register COMMA Id
  public static boolean AdrInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AdrInstruction")) return false;
    if (!nextTokenIs(b, ADR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ADR);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Id(b, l + 1);
    exit_section_(b, m, ADR_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // DOT ALIGN <<CommaSeparatedList Based>>
  public static boolean AlignDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AlignDirective")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, ALIGN);
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
  // DOT ASCII <<CommaSeparatedList STRING>>
  public static boolean AsciiDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "AsciiDirective")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, ASCII);
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
  // ARMv7Directives
  static boolean AssemblerDirective(PsiBuilder b, int l) {
    return ARMv7Directives(b, l + 1);
  }

  /* ********************************************************** */
  // B Id
  public static boolean BInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BInstruction")) return false;
    if (!nextTokenIs(b, B)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, B);
    r = r && Id(b, l + 1);
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
  // BKPT Number
  public static boolean BkptInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BkptInstruction")) return false;
    if (!nextTokenIs(b, BKPT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BKPT);
    r = r && Number(b, l + 1);
    exit_section_(b, m, BKPT_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // BL Id
  public static boolean BlInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BlInstruction")) return false;
    if (!nextTokenIs(b, BL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BL);
    r = r && Id(b, l + 1);
    exit_section_(b, m, BL_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // BX Register
  public static boolean BxInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BxInstruction")) return false;
    if (!nextTokenIs(b, BX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BX);
    r = r && Register(b, l + 1);
    exit_section_(b, m, BX_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // BXJ Register
  public static boolean BxjInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "BxjInstruction")) return false;
    if (!nextTokenIs(b, BXJ)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BXJ);
    r = r && Register(b, l + 1);
    exit_section_(b, m, BXJ_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // CBNZ Register COMMA Id
  public static boolean CbnzInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CbnzInstruction")) return false;
    if (!nextTokenIs(b, CBNZ)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CBNZ);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Id(b, l + 1);
    exit_section_(b, m, CBNZ_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // CBZ Register COMMA Id
  public static boolean CbzInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CbzInstruction")) return false;
    if (!nextTokenIs(b, CBZ)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CBZ);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Id(b, l + 1);
    exit_section_(b, m, CBZ_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // CLREX
  public static boolean ClrexInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClrexInstruction")) return false;
    if (!nextTokenIs(b, CLREX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLREX);
    exit_section_(b, m, CLREX_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // CLZ Register COMMA Register
  public static boolean ClzInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ClzInstruction")) return false;
    if (!nextTokenIs(b, CLZ)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CLZ);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Register(b, l + 1);
    exit_section_(b, m, CLZ_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // CMN Register COMMA (Number | Register (COMMA Shift)?)
  public static boolean CmnInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmnInstruction")) return false;
    if (!nextTokenIs(b, CMN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CMN);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && CmnInstruction_3(b, l + 1);
    exit_section_(b, m, CMN_INSTRUCTION, r);
    return r;
  }

  // Number | Register (COMMA Shift)?
  private static boolean CmnInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmnInstruction_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Number(b, l + 1);
    if (!r) r = CmnInstruction_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Register (COMMA Shift)?
  private static boolean CmnInstruction_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmnInstruction_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Register(b, l + 1);
    r = r && CmnInstruction_3_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Shift)?
  private static boolean CmnInstruction_3_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmnInstruction_3_1_1")) return false;
    CmnInstruction_3_1_1_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean CmnInstruction_3_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmnInstruction_3_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // CMP Register COMMA (Number | Register (COMMA Shift)?)
  public static boolean CmpInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpInstruction")) return false;
    if (!nextTokenIs(b, CMP)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CMP);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && CmpInstruction_3(b, l + 1);
    exit_section_(b, m, CMP_INSTRUCTION, r);
    return r;
  }

  // Number | Register (COMMA Shift)?
  private static boolean CmpInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpInstruction_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Number(b, l + 1);
    if (!r) r = CmpInstruction_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Register (COMMA Shift)?
  private static boolean CmpInstruction_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpInstruction_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Register(b, l + 1);
    r = r && CmpInstruction_3_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA Shift)?
  private static boolean CmpInstruction_3_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpInstruction_3_1_1")) return false;
    CmpInstruction_3_1_1_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean CmpInstruction_3_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CmpInstruction_3_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
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
  // CSDB
  public static boolean CsdbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "CsdbInstruction")) return false;
    if (!nextTokenIs(b, CSDB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, CSDB);
    exit_section_(b, m, CSDB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // DBG Number
  public static boolean DbgInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DbgInstruction")) return false;
    if (!nextTokenIs(b, DBG)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DBG);
    r = r && Number(b, l + 1);
    exit_section_(b, m, DBG_INSTRUCTION, r);
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
  // DMB Id
  public static boolean DmbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DmbInstruction")) return false;
    if (!nextTokenIs(b, DMB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DMB);
    r = r && Id(b, l + 1);
    exit_section_(b, m, DMB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // DSB Id
  public static boolean DsbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "DsbInstruction")) return false;
    if (!nextTokenIs(b, DSB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DSB);
    r = r && Id(b, l + 1);
    exit_section_(b, m, DSB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // EOR Registers (COMMA (Number | Shift))?
  public static boolean EorInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EorInstruction")) return false;
    if (!nextTokenIs(b, EOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EOR);
    r = r && Registers(b, l + 1);
    r = r && EorInstruction_2(b, l + 1);
    exit_section_(b, m, EOR_INSTRUCTION, r);
    return r;
  }

  // (COMMA (Number | Shift))?
  private static boolean EorInstruction_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EorInstruction_2")) return false;
    EorInstruction_2_0(b, l + 1);
    return true;
  }

  // COMMA (Number | Shift)
  private static boolean EorInstruction_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EorInstruction_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && EorInstruction_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // Number | Shift
  private static boolean EorInstruction_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EorInstruction_2_0_1")) return false;
    boolean r;
    r = Number(b, l + 1);
    if (!r) r = Shift(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ERET
  public static boolean EretInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "EretInstruction")) return false;
    if (!nextTokenIs(b, ERET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ERET);
    exit_section_(b, m, ERET_INSTRUCTION, r);
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
  // DOT GLOBAL Id
  public static boolean GlobalDirective(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "GlobalDirective")) return false;
    if (!nextTokenIs(b, DOT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, DOT, GLOBAL);
    r = r && Id(b, l + 1);
    exit_section_(b, m, GLOBAL_DIRECTIVE, r);
    return r;
  }

  /* ********************************************************** */
  // HVC Number
  public static boolean HvcInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "HvcInstruction")) return false;
    if (!nextTokenIs(b, HVC)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HVC);
    r = r && Number(b, l + 1);
    exit_section_(b, m, HVC_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  public static boolean Id(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Id")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    exit_section_(b, m, ID, r);
    return r;
  }

  /* ********************************************************** */
  // ARMv7ArithmeticInstructions
  //     | ARMv7BranchInstructions
  //     | ARMv7ComparisonInstructions
  //     | ARMv7DebugInstructions
  //     | ARMv7LoadInstructions
  //     | ARMv7LockInstructions
  //     | ARMv7SpeculativeInstructions
  //     | ARmv7SystemInstructions
  static boolean Instruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Instruction")) return false;
    boolean r;
    r = ARMv7ArithmeticInstructions(b, l + 1);
    if (!r) r = ARMv7BranchInstructions(b, l + 1);
    if (!r) r = ARMv7ComparisonInstructions(b, l + 1);
    if (!r) r = ARMv7DebugInstructions(b, l + 1);
    if (!r) r = ARMv7LoadInstructions(b, l + 1);
    if (!r) r = ARMv7LockInstructions(b, l + 1);
    if (!r) r = ARMv7SpeculativeInstructions(b, l + 1);
    if (!r) r = ARmv7SystemInstructions(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ISB Id
  public static boolean IsbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "IsbInstruction")) return false;
    if (!nextTokenIs(b, ISB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ISB);
    r = r && Id(b, l + 1);
    exit_section_(b, m, ISB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LineFeed | DirectiveOrInstructionOrLabel LineFeed?
  static boolean Item(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Item")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = LineFeed(b, l + 1);
    if (!r) r = Item_1(b, l + 1);
    exit_section_(b, m, null, r);
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
  // Id COLON
  public static boolean Label(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Label")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Id(b, l + 1);
    r = r && consumeToken(b, COLON);
    exit_section_(b, m, LABEL, r);
    return r;
  }

  /* ********************************************************** */
  // LDM Register COMMA Registers
  public static boolean LdmInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmInstruction")) return false;
    if (!nextTokenIs(b, LDM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDM);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDM_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMDA Register COMMA Registers
  public static boolean LdmdaInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmdaInstruction")) return false;
    if (!nextTokenIs(b, LDMDA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMDA);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMDA_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMDB Register COMMA Registers
  public static boolean LdmdbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmdbInstruction")) return false;
    if (!nextTokenIs(b, LDMDB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMDB);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMDB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMEA Register COMMA Registers
  public static boolean LdmeaPseudoInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmeaPseudoInstruction")) return false;
    if (!nextTokenIs(b, LDMEA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMEA);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMEA_PSEUDO_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMED Register COMMA Registers
  public static boolean LdmedPseudoInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmedPseudoInstruction")) return false;
    if (!nextTokenIs(b, LDMED)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMED);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMED_PSEUDO_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMFA Register COMMA Registers
  public static boolean LdmfaPseudoInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmfaPseudoInstruction")) return false;
    if (!nextTokenIs(b, LDMFA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMFA);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMFA_PSEUDO_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMFD Register COMMA Registers
  public static boolean LdmfdPseudoInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmfdPseudoInstruction")) return false;
    if (!nextTokenIs(b, LDMFD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMFD);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMFD_PSEUDO_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMIA Register COMMA Registers
  public static boolean LdmiaPseudoInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmiaPseudoInstruction")) return false;
    if (!nextTokenIs(b, LDMIA)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMIA);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMIA_PSEUDO_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDMIB Register COMMA Registers
  public static boolean LdmibInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdmibInstruction")) return false;
    if (!nextTokenIs(b, LDMIB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDMIB);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && Registers(b, l + 1);
    exit_section_(b, m, LDMIB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDR Register COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrInstruction")) return false;
    if (!nextTokenIs(b, LDR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDR);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrInstruction_3(b, l + 1);
    exit_section_(b, m, LDR_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LDRB Register COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrbInstruction")) return false;
    if (!nextTokenIs(b, LDRB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRB);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrbInstruction_3(b, l + 1);
    exit_section_(b, m, LDRB_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrbInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrbInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LDRBT Register COMMA PostindexOffset
  public static boolean LdrbtInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrbtInstruction")) return false;
    if (!nextTokenIs(b, LDRBT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRBT);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && PostindexOffset(b, l + 1);
    exit_section_(b, m, LDRBT_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDRD Registers COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrdInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrdInstruction")) return false;
    if (!nextTokenIs(b, LDRD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRD);
    r = r && Registers(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrdInstruction_3(b, l + 1);
    exit_section_(b, m, LDRD_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrdInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrdInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LDREX Register COMMA LBRACKET Register (COMMA Number)? RBRACKET
  public static boolean LdrexInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexInstruction")) return false;
    if (!nextTokenIs(b, LDREX)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDREX);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && LdrexInstruction_5(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, LDREX_INSTRUCTION, r);
    return r;
  }

  // (COMMA Number)?
  private static boolean LdrexInstruction_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexInstruction_5")) return false;
    LdrexInstruction_5_0(b, l + 1);
    return true;
  }

  // COMMA Number
  private static boolean LdrexInstruction_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexInstruction_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Number(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LDREXB Register COMMA LBRACKET Register RBRACKET
  public static boolean LdrexbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexbInstruction")) return false;
    if (!nextTokenIs(b, LDREXB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDREXB);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, LDREXB_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDREXD Registers COMMA LBRACKET Register RBRACKET
  public static boolean LdrexdInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexdInstruction")) return false;
    if (!nextTokenIs(b, LDREXD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDREXD);
    r = r && Registers(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, LDREXD_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDREXH Register COMMA LBRACKET Register RBRACKET
  public static boolean LdrexhInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrexhInstruction")) return false;
    if (!nextTokenIs(b, LDREXH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDREXH);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, COMMA, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, LDREXH_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDRH Register COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrhInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrhInstruction")) return false;
    if (!nextTokenIs(b, LDRH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRH);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrhInstruction_3(b, l + 1);
    exit_section_(b, m, LDRH_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrhInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrhInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LDRHT Register COMMA (RegisterWithOffset)
  public static boolean LdrhtInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrhtInstruction")) return false;
    if (!nextTokenIs(b, LDRHT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRHT);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrhtInstruction_3(b, l + 1);
    exit_section_(b, m, LDRHT_INSTRUCTION, r);
    return r;
  }

  // (RegisterWithOffset)
  private static boolean LdrhtInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrhtInstruction_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = RegisterWithOffset(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LDRSB Register COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrsbInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrsbInstruction")) return false;
    if (!nextTokenIs(b, LDRSB)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRSB);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrsbInstruction_3(b, l + 1);
    exit_section_(b, m, LDRSB_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrsbInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrsbInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // LDRSBT Register COMMA RegisterWithOffset
  public static boolean LdrsbtInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrsbtInstruction")) return false;
    if (!nextTokenIs(b, LDRSBT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRSBT);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && RegisterWithOffset(b, l + 1);
    exit_section_(b, m, LDRSBT_INSTRUCTION, r);
    return r;
  }

  /* ********************************************************** */
  // LDRSH Register COMMA (PCWithImmediateOffset | RegisterWithOffset | Id)
  public static boolean LdrshInstruction(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrshInstruction")) return false;
    if (!nextTokenIs(b, LDRSH)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LDRSH);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && LdrshInstruction_3(b, l + 1);
    exit_section_(b, m, LDRSH_INSTRUCTION, r);
    return r;
  }

  // PCWithImmediateOffset | RegisterWithOffset | Id
  private static boolean LdrshInstruction_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "LdrshInstruction_3")) return false;
    boolean r;
    r = PCWithImmediateOffset(b, l + 1);
    if (!r) r = RegisterWithOffset(b, l + 1);
    if (!r) r = Id(b, l + 1);
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
  // LBRACKET Register RBRACKET
  public static boolean NoOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "NoOffset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, NO_OFFSET, r);
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
  // LBRACKET PC COMMA Number RBRACKET
  public static boolean PCWithImmediateOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PCWithImmediateOffset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, LBRACKET, PC, COMMA);
    r = r && Number(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    exit_section_(b, m, PC_WITH_IMMEDIATE_OFFSET, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register RBRACKET COMMA (Number | (PLUS | MINUS)? Register (COMMA Shift)?)
  public static boolean PostindexOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeTokens(b, 0, RBRACKET, COMMA);
    r = r && PostindexOffset_4(b, l + 1);
    exit_section_(b, m, POSTINDEX_OFFSET, r);
    return r;
  }

  // Number | (PLUS | MINUS)? Register (COMMA Shift)?
  private static boolean PostindexOffset_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Number(b, l + 1);
    if (!r) r = PostindexOffset_4_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS | MINUS)? Register (COMMA Shift)?
  private static boolean PostindexOffset_4_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PostindexOffset_4_1_0(b, l + 1);
    r = r && Register(b, l + 1);
    r = r && PostindexOffset_4_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS | MINUS)?
  private static boolean PostindexOffset_4_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4_1_0")) return false;
    PostindexOffset_4_1_0_0(b, l + 1);
    return true;
  }

  // PLUS | MINUS
  private static boolean PostindexOffset_4_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    return r;
  }

  // (COMMA Shift)?
  private static boolean PostindexOffset_4_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4_1_2")) return false;
    PostindexOffset_4_1_2_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean PostindexOffset_4_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PostindexOffset_4_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // LBRACKET Register COMMA (Number | (PLUS | MINUS)? Register (COMMA Shift)?) RBRACKET BANG?
  public static boolean PreindexOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LBRACKET);
    r = r && Register(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && PreindexOffset_3(b, l + 1);
    r = r && consumeToken(b, RBRACKET);
    r = r && PreindexOffset_5(b, l + 1);
    exit_section_(b, m, PREINDEX_OFFSET, r);
    return r;
  }

  // Number | (PLUS | MINUS)? Register (COMMA Shift)?
  private static boolean PreindexOffset_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = Number(b, l + 1);
    if (!r) r = PreindexOffset_3_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS | MINUS)? Register (COMMA Shift)?
  private static boolean PreindexOffset_3_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = PreindexOffset_3_1_0(b, l + 1);
    r = r && Register(b, l + 1);
    r = r && PreindexOffset_3_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PLUS | MINUS)?
  private static boolean PreindexOffset_3_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3_1_0")) return false;
    PreindexOffset_3_1_0_0(b, l + 1);
    return true;
  }

  // PLUS | MINUS
  private static boolean PreindexOffset_3_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3_1_0_0")) return false;
    boolean r;
    r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    return r;
  }

  // (COMMA Shift)?
  private static boolean PreindexOffset_3_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3_1_2")) return false;
    PreindexOffset_3_1_2_0(b, l + 1);
    return true;
  }

  // COMMA Shift
  private static boolean PreindexOffset_3_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_3_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && Shift(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BANG?
  private static boolean PreindexOffset_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "PreindexOffset_5")) return false;
    consumeToken(b, BANG);
    return true;
  }

  /* ********************************************************** */
  // (R0 | R1 | R2 | R3 | R4 | R5 | R6 | R7 | R8 | R9| R10 | R11 | R12 | SP | LR | PC | CPSR | SPSR) BANG?
  public static boolean Register(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REGISTER, "<register>");
    r = Register_0(b, l + 1);
    r = r && Register_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // R0 | R1 | R2 | R3 | R4 | R5 | R6 | R7 | R8 | R9| R10 | R11 | R12 | SP | LR | PC | CPSR | SPSR
  private static boolean Register_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register_0")) return false;
    boolean r;
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
    return r;
  }

  // BANG?
  private static boolean Register_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "Register_1")) return false;
    consumeToken(b, BANG);
    return true;
  }

  /* ********************************************************** */
  // PostindexOffset
  //     | NoOffset
  //     | PreindexOffset
  static boolean RegisterWithOffset(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "RegisterWithOffset")) return false;
    if (!nextTokenIs(b, LBRACKET)) return false;
    boolean r;
    r = PostindexOffset(b, l + 1);
    if (!r) r = NoOffset(b, l + 1);
    if (!r) r = PreindexOffset(b, l + 1);
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
