package io.github.asmflow.assembly.armv7.lexer;

import java.io.IOException;
import java.util.ArrayDeque;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;

%%

%{

private ArrayDeque<IElementType> queue = new ArrayDeque<>();

private void enqueue(IElementType t) {
    queue.add(t);
}

private void queueSetFlagsConditionCodes(String suffix) {
    if (suffix.startsWith("s"))
        enqueue(S);

    if (suffix.length() > 1) {
        String condition = suffix.substring(1);
        switch (condition) {
            case "eq":
                enqueue(EQ);
                break;
            case "ne":
                enqueue(NE);
                break;
            case "cs":
                enqueue(CS);
                break;
            case "hs":
                enqueue(HS);
                break;
            case "cc":
                enqueue(CC);
                break;
            case "lo":
                enqueue(LO);
                break;
            case "mi":
                enqueue(MI);
                break;
            case "pl":
                enqueue(PL);
                break;
            case "vs":
                enqueue(VS);
                break;
            case "vc":
                enqueue(VC);
                break;
            case "hi":
                enqueue(HI);
                break;
            case "ls":
                enqueue(LS);
                break;
            case "ge":
                enqueue(GE);
                break;
            case "lt":
                enqueue(LT);
                break;
            case "gt":
                enqueue(GT);
                break;
            case "le":
                enqueue(LE);
                break;
            case "al":
                enqueue(AL);
                break;
            default:
                enqueue(BAD_CHARACTER);
        }
    }
}

public IElementType advance() throws IOException {
    if (!queue.isEmpty()) return queue.pollFirst();
    return yyadvance();
}

%}

%public
%class ARMv7LexerImpl
%implements FlexLexer
%function yyadvance
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
    "." { return DOT; }

    "adc"({S}?{CONDITION_CODES}?) { queue.add(ADC); queueSetFlagsConditionCodes(yytext().toString().substring(3)); }

    {IDENTIFIER} { return IDENTIFIER; }

    {COMMENT} { return COMMENT; }
    {STRING} { return STRING; }
    {WHITE_SPACE}+ { return WHITE_SPACE; }
}

[^] { return BAD_CHARACTER; }
