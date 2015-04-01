package sml.compiler;

import java_cup.runtime.*;

%%

%class Lexer
%public

%line
%column

%cup


%{

private Symbol symbol(int type) {
return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value) {
return new Symbol(type, yyline, yycolumn, value);
}
%}



LineTerminator = \r|\n|\r\n

WhiteSpace     = {LineTerminator} | [ \t\f]

number = 0 | [1-9][0-9]*

letter = [a-g]

string = [A-Za-z_][A-Za-z_0-9]*

complexnote = {number}[\+\-]*{letter}{number} |
{number}[\+\-]*{letter}{string}

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment}

TraditionalComment = "/*" [^*] ~"*/" | "/*" "*"+ "/"
EndOfLineComment = "//" [^]* {LineTerminator}?


%%


<YYINITIAL> {




";"                { return symbol(sym.SEMI); }
"+"                { return symbol(sym.PLUS); }
"-"                { return symbol(sym.MINUS); }
"("                { return symbol(sym.LPAREN); }
")"                { return symbol(sym.RPAREN); }
"{"                { return symbol(sym.LACCOL); }
"}"                { return symbol(sym.RACCOL); }
"="                { return symbol(sym.EQUAL); }
","                { return symbol(sym.COMA); }
"\?"                { return symbol(sym.REST); }

"let" { return symbol(sym.LET); }
"ens" { return symbol(sym.ENS); }
"begin" { return symbol(sym.BEGIN); }
"end" { return symbol(sym.END); }
"tempo" { return symbol(sym.TEMPO); }
"play" { return symbol(sym.PLAY); }
"chord" { return symbol(sym.CHORD); }
"sequence" { return symbol(sym.SEQUENCE); }
"scale" { return symbol(sym.SCALE); }
"instru"  { return symbol(sym.INSTRU); }

"ionian" { return symbol(sym.SCALENAME, yytext()); }
"dorian" { return symbol(sym.SCALENAME, yytext()); }
"myxolydian" { return symbol(sym.SCALENAME, yytext()); }
"phrygian" { return symbol(sym.SCALENAME, yytext()); }
"lydian" { return symbol(sym.SCALENAME, yytext()); }
"aeolian" { return symbol(sym.SCALENAME, yytext()); }
"locrian" { return symbol(sym.SCALENAME, yytext()); }

{number}      { return symbol(sym.NUMBER, new Integer(yytext())); }

{complexnote} { return symbol(sym.COMPLEXNOTE, yytext()); }

{letter}      { return symbol(sym.LETTER, yytext()); }

{string}       { return symbol(sym.STRING, yytext());}

{Comment} { System.out.println("commentaire"); }

{WhiteSpace}       {}

}


[^]                    { throw new Error("Illegal character <"+yytext()+">"); }
