package io.github.asmflow.assembly.armv7.lexer;

import java.io.IOException;
import java.util.ArrayDeque;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;

%%

%public
%class ARMv7LexerImpl
%implements FlexLexer
%function advance
%type IElementType

CRLF = \r | \n | \r\n
WHITE_SPACE = [\s]+

COMMENT = @.*
DIRECTIVE = [.]{IDENTIFIER}
IDENTIFIER = [a-zA-Z_]+

BINARY_NUMBER = b[0|1]+
DECIMAL_NUMBER = [\d]+
HEXADECIMAL_NUMBER = x[0-9a-fA-F]+
OCTAL_NUMBER = o[0-7]+

EXCLAMATION_POINT = "!"
S = "s"
CONDITION_CODES = "eq"|"ne"|"cs"|"hs"|"cc"|"lo"|"mi"|"pl"|"vs"|"vc"|"hi"|"ls"|"ge"|"lt"|"gt"|"le"|"al"

STRING = \"([^\\\"\r\n]|\\[^\r\n])*\"?

%%

<YYINITIAL> {
  {CRLF} { return LINE_FEED; }

  ":" { return COLON; }
  "," { return COMMA; }
  "." { return DOT; }
  "#" { return POUND; }

  "align" { return ALIGN; }
  "ascii" { return ASCII; }
  "asciz" { return ASCIZ; }
  "balign" { return BALIGN; }
  "balignl" { return BALIGNL; }
  "balignw" { return BALIGNW; }
  "byte" { return BYTE; }
  "comm" { return COMM; }
  "data" { return DATA; }
  "def" { return DEF; }
  "desc" { return DESC; }
  "dim" { return DIM; }
  "double" { return DOUBLE; }
  "eject" { return EJECT; }
  "else" { return ELSE; }
  "endef" { return ENDEF; }
  "endif" { return ENDIF; }
  "equ" { return EQU; }
  "equiv" { return EQUIV; }
  "err" { return ERR; }
  "extern" { return EXTERN; }
  "file" { return FILE; }
  "fill" { return FILL; }
  "float" { return FLOAT; }
  "global" { return GLOBAL; }

  "r0"({EXCLAMATION_POINT}?) { return R0; }
  "r1"({EXCLAMATION_POINT}?) { return R1; }
  "r2"({EXCLAMATION_POINT}?) { return R2; }
  "r3"({EXCLAMATION_POINT}?) { return R3; }
  "r4"({EXCLAMATION_POINT}?) { return R4; }
  "r5"({EXCLAMATION_POINT}?) { return R5; }
  "r6"({EXCLAMATION_POINT}?) { return R6; }
  "r7"({EXCLAMATION_POINT}?) { return R7; }
  "r8"({EXCLAMATION_POINT}?) { return R8; }
  "r9"({EXCLAMATION_POINT}?) { return R9; }
  "r10"({EXCLAMATION_POINT}?) { return R10; }
  "r11"({EXCLAMATION_POINT}?) { return R11; }
  "r12"({EXCLAMATION_POINT}?) { return R12; }
  "sp"({EXCLAMATION_POINT}?) { return SP; }
  "lr"({EXCLAMATION_POINT}?) { return LR; }
  "pc"({EXCLAMATION_POINT}?) { return PC; }
  "cpsr"({EXCLAMATION_POINT}?) { return CPSR; }
  "spsr"({EXCLAMATION_POINT}?) { return SPSR; }

  "lsl" { return LSL; }
  "lsr" { return LSR; }
  "asr" { return ASR; }
  "ror" { return ROR; }
  "rrx" { return RRX; }

  "adc"({S}?{CONDITION_CODES}?) { return ADC; }
  "add"({S}?{CONDITION_CODES}?) { return ADD; }
  "adr"({CONDITION_CODES}?) { return ADR; }
  "and"({S}?{CONDITION_CODES}?) { return AND; }
  "asr"({S}?{CONDITION_CODES}?) { return ASR; }
  "b"({CONDITION_CODES}?) { return B; }
  "bfc"({CONDITION_CODES}?) { return BFC; }
  "bfi"({CONDITION_CODES}?) { return BFI; }
  "bic"({S}?{CONDITION_CODES}?) { return BIC; }
  "bkpt" { return BKPT; }
  "bl"({CONDITION_CODES}?) { return BL; }
  "bx"({CONDITION_CODES}?) { return BX; }
  "bxj"({CONDITION_CODES}?) { return BXJ; }
  "cbnz" { return CBNZ; }
  "cbz" { return CBZ; }
  "clrex" { return CLREX; }
  "clz"({CONDITION_CODES}?) { return CLZ; }
  "cmn"({CONDITION_CODES}?) { return CMN; }
  "cmp"({CONDITION_CODES}?) { return CMP; }
  "csdb"({CONDITION_CODES}?) { return CSDB; }
  "dbg"({CONDITION_CODES}?) { return DBG; }
  "dmb"({CONDITION_CODES}?) { return DMB; }
  "dsb"({CONDITION_CODES}?) { return DSB; }
  "eor"({S}?{CONDITION_CODES}?) { return EOR; }
  "eret"({CONDITION_CODES}?) { return ERET; }
  "hvc" { return HVC; }
  "isb"({CONDITION_CODES}?) { return ISB; }
  "ldm"({CONDITION_CODES}?) { return LDM; }

  {BINARY_NUMBER} { return BINARY_NUMBER; }
  {DECIMAL_NUMBER} { return DECIMAL_NUMBER; }
  {HEXADECIMAL_NUMBER} { return HEXADECIMAL_NUMBER; }
  {OCTAL_NUMBER} { return OCTAL_NUMBER; }

  {IDENTIFIER} { return IDENTIFIER; }

  {COMMENT} { return COMMENT; }
  {STRING} { return STRING; }
  {WHITE_SPACE}+ { return WHITE_SPACE; }
}

[^] { return BAD_CHARACTER; }
