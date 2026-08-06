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
  "{" { return LBRACE; }
  "[" { return LBRACKET; }
  "-" { return MINUS; }
  "+" { return PLUS; }
  "#" { return POUND; }
  "}" { return RBRACE; }
  "]" { return RBRACKET; }

  "r0" { return REG; }
  "r1" { return REG; }
  "r2" { return REG; }
  "r3" { return REG; }
  "r4" { return REG; }
  "r5" { return REG; }
  "r6" { return REG; }
  "r7" { return REG; }
  "r8" { return REG; }
  "r9" { return REG; }
  "r10" { return REG; }
  "r11" { return REG; }
  "r12" { return REG; }
  "sp" { return REG; }
  "lr" { return REG; }
  "pc" { return REG; }
  "cpsr" { return REG; }
  "spsr" { return REG; }

  {BINARY_NUMBER} { return BINARY_NUMBER; }
  {DECIMAL_NUMBER} { return DECIMAL_NUMBER; }
  {HEXADECIMAL_NUMBER} { return HEXADECIMAL_NUMBER; }
  {OCTAL_NUMBER} { return OCTAL_NUMBER; }

  {IDENTIFIER} { return IDENTIFIER; }
  {STRING} { return STRING; }
}

[^] { return BAD_CHARACTER; }
