package io.github.asmflow.assembly.armv7.lexer;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;

%%

%public
%class ARMv7LexerImpl
%implements FlexLexer
%function advance
%type IElementType

CRLF = [\r\n]

COMMENT = ;.*
DIRECTIVE = [.]{IDENTIFIER}
IDENTIFIER = [a-zA-Z_]*

BINARY_NUMBER = b[0|1]+
DECIMAL_NUMBER = [\d]+
HEXADECIMAL_NUMBER = x[0-9a-fA-F]+
OCTAL_NUMBER = o[0-7]+

%state AFTER_IDENTIFIER
%state IN_INSTRUCTION

%%

<YYINITIAL> {
    {CRLF} { yybegin(YYINITIAL); return LINE_FEED; }
}

<YYINITIAL> {
    {COMMENT} { return COMMENT; }
    {IDENTIFIER} { yybegin(AFTER_IDENTIFIER); return IDENTIFIER; }
}

<AFTER_IDENTIFIER> {
    ":" { yybegin(IN_INSTRUCTION); return COLON; }
}

<IN_INSTRUCTION> {
    "adc" { return ADC; }
}

[^] { return BAD_CHARACTER; }
