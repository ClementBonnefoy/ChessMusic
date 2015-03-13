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

complexnote = {number}[\+\-]*{letter}{number}

scale = "ionian" | "dorian" | "myxolydian" | "phrygian" | "aeolian" | "locrian" | "lydian"

identifiant = [A-Za-z_][A-Za-z_0-9]*



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

"let" { return symbol(sym.LET); }
"ens" { return symbol(sym.ENS); }
"begin" { return symbol(sym.BEGIN); }
"end" { return symbol(sym.END); }
"tempo" { return symbol(sym.TEMPO); }
"play" { return symbol(sym.PLAY); }
"chord" { return symbol(sym.CHORD); }
"sequence" { return symbol(sym.SEQUENCE); }
"rest" { return symbol(sym.REST); }
"scale" { return symbol(sym.SCALE); }

{number}      { return symbol(sym.NUMBER, new Integer(yytext())); }

{complexnote} { System.out.println("complex note: "+yytext());
        return symbol(sym.COMPLEXNOTE, yytext()); }

{letter}      { return symbol(sym.LETTER, yytext()); }

{scale}     { return symbol(sym.SCALENAME, yytext()); }

{identifiant}       { return symbol(sym.IDENTIFIANT, yytext());}

{WhiteSpace}       {}

}

[^]                    { throw new Error("Illegal character <"+yytext()+">"); }