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

COMMENT = ;.*
DIRECTIVE = [.]{IDENTIFIER}
IDENTIFIER = [a-zA-Z_]+

BINARY_NUMBER = b[0|1]+
DECIMAL_NUMBER = [\d]+
HEXADECIMAL_NUMBER = x[0-9a-fA-F]+
OCTAL_NUMBER = o[0-7]+

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

  "r0" { return R0; }
  "r1" { return R1; }
  "r2" { return R2; }
  "r3" { return R3; }
  "r4" { return R4; }
  "r5" { return R5; }
  "r6" { return R6; }
  "r7" { return R7; }
  "r8" { return R8; }
  "r9" { return R9; }
  "r10" { return R10; }
  "r11" { return R11; }
  "r12" { return R12; }
  "sp" { return SP; }
  "lr" { return LR; }
  "pc" { return PC; }
  "cpsr" { return CPSR; }
  "spsr" { return SPSR; }

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
