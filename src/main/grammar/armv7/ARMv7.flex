package io.github.asmflow.assembly.armv7.lexer;

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

CRLF = \R
WHITE_SPACE = [ \t]+

COMMENT = @.*
DIRECTIVE = [.]{IDENTIFIER}
IDENTIFIER = [a-zA-Z_]+

BINARY_NUMBER = b[0|1]+
DECIMAL_NUMBER = [\d]+
HEXADECIMAL_NUMBER = x[0-9a-fA-F]+
OCTAL_NUMBER = o[0-7]+

STRING = \"([^\\\"\r\n]|\\[^\r\n])*\"?

%%

<YYINITIAL> {
  {CRLF} { return LINE_FEED; }
  {WHITE_SPACE}+ { return WHITE_SPACE; }
  {COMMENT} { return COMMENT; }

  "!" { return BANG; }
  ":" { return COLON; }
  "," { return COMMA; }
  "." { return DOT; }
  "[" { return LBRACKET; }
  "-" { return MINUS; }
  "+" { return PLUS; }
  "#" { return POUND; }
  "]" { return RBRACKET; }

  "r0" { return REGISTER; }
  "r1" { return REGISTER; }
  "r2" { return REGISTER; }
  "r3" { return REGISTER; }
  "r4" { return REGISTER; }
  "r5" { return REGISTER; }
  "r6" { return REGISTER; }
  "r7" { return REGISTER; }
  "r8" { return REGISTER; }
  "r9" { return REGISTER; }
  "r10" { return REGISTER; }
  "r11" { return REGISTER; }
  "r12" { return REGISTER; }
  "sp" { return REGISTER; }
  "lr" { return REGISTER; }
  "pc" { return REGISTER; }
  "cpsr" { return REGISTER; }
  "spsr" { return REGISTER; }

  {BINARY_NUMBER} { return BINARY_NUMBER; }
  {DECIMAL_NUMBER} { return DECIMAL_NUMBER; }
  {HEXADECIMAL_NUMBER} { return HEXADECIMAL_NUMBER; }
  {OCTAL_NUMBER} { return OCTAL_NUMBER; }

  {IDENTIFIER} { return IDENTIFIER; }
  {STRING} { return STRING; }
}

[^] { return BAD_CHARACTER; }
