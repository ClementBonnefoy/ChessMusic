/* The following code was generated by JFlex 1.6.0 */

package sml.compiler;

import java_cup.runtime.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.0
 * from the specification file <tt>sml.flex</tt>
 */
public class Lexer implements java_cup.runtime.Scanner {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\7\0\1\16"+
    "\1\17\1\13\1\15\1\23\1\11\1\7\1\12\1\4\11\5\1\0"+
    "\1\14\1\0\1\22\1\0\1\24\1\0\32\10\4\0\1\10\1\0"+
    "\1\41\1\32\1\43\1\35\1\26\1\6\1\33\1\44\1\34\2\10"+
    "\1\25\1\36\1\30\1\40\1\37\1\46\1\45\1\31\1\27\1\47"+
    "\2\10\1\50\1\42\1\10\1\20\1\0\1\21\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uff92\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\2\3\1\4\1\1\1\5\1\6"+
    "\1\1\1\7\1\10\1\11\1\12\1\13\1\14\1\15"+
    "\1\16\1\17\1\5\1\4\2\5\1\4\1\5\1\4"+
    "\2\5\2\4\1\5\4\0\22\5\3\20\3\0\1\21"+
    "\1\5\1\22\1\23\14\5\4\0\10\5\1\24\3\5"+
    "\2\0\1\5\1\25\2\5\1\26\1\27\2\5\1\30"+
    "\1\5\1\20\1\31\2\5\1\32\1\33\3\5\1\34"+
    "\1\35";

  private static int [] zzUnpackAction() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\51\0\122\0\51\0\173\0\244\0\315\0\366"+
    "\0\315\0\51\0\u011f\0\51\0\51\0\51\0\51\0\51"+
    "\0\51\0\51\0\51\0\51\0\u0148\0\u0171\0\u019a\0\u01c3"+
    "\0\u01ec\0\u0215\0\u023e\0\u0267\0\u0290\0\u02b9\0\u02e2\0\u030b"+
    "\0\u0334\0\173\0\u035d\0\u0386\0\u03af\0\u03d8\0\u0401\0\u042a"+
    "\0\u0453\0\u047c\0\u04a5\0\u04ce\0\u04f7\0\u0520\0\u0549\0\u0572"+
    "\0\u059b\0\u05c4\0\u05ed\0\u0616\0\u063f\0\u0668\0\u0691\0\u06ba"+
    "\0\u06e3\0\u070c\0\u0735\0\u075e\0\315\0\u0787\0\315\0\315"+
    "\0\u07b0\0\u07d9\0\u0802\0\u082b\0\u0854\0\u087d\0\u08a6\0\u08cf"+
    "\0\u08f8\0\u0921\0\u094a\0\u0973\0\u099c\0\u09c5\0\u09ee\0\u0a17"+
    "\0\u0a40\0\u0a69\0\u0a92\0\u0abb\0\u0ae4\0\u0b0d\0\u0b36\0\u0b5f"+
    "\0\315\0\u0b88\0\u0bb1\0\u0bda\0\u0c03\0\u0c2c\0\u0c55\0\315"+
    "\0\u0c7e\0\u0ca7\0\315\0\315\0\u0cd0\0\u0cf9\0\315\0\u0d22"+
    "\0\51\0\315\0\u0d4b\0\u0d74\0\315\0\315\0\u0d9d\0\u0dc6"+
    "\0\u0def\0\315\0\315";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\2\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\11"+
    "\1\30\1\31\1\7\1\32\1\33\1\34\1\35\1\11"+
    "\1\36\1\11\1\37\1\11\1\40\3\11\53\0\1\4"+
    "\54\0\1\41\2\0\1\42\3\0\1\42\10\0\1\41"+
    "\3\0\2\41\1\0\1\41\3\0\1\41\1\0\1\41"+
    "\11\0\2\6\1\41\2\0\1\42\3\0\1\42\10\0"+
    "\1\41\3\0\2\41\1\0\1\41\3\0\1\41\1\0"+
    "\1\41\11\0\3\11\1\0\1\11\14\0\24\11\7\0"+
    "\1\43\54\0\1\44\41\0\3\11\1\0\1\11\14\0"+
    "\1\11\1\45\11\11\1\46\1\11\1\47\6\11\4\0"+
    "\3\11\1\0\1\11\14\0\3\11\1\50\20\11\4\0"+
    "\3\11\1\0\1\11\14\0\1\11\1\51\16\11\1\52"+
    "\3\11\4\0\3\11\1\0\1\11\14\0\1\11\1\53"+
    "\14\11\1\54\5\11\4\0\3\11\1\0\1\11\14\0"+
    "\1\11\1\55\22\11\4\0\3\11\1\0\1\11\14\0"+
    "\3\11\1\56\7\11\1\57\10\11\4\0\3\11\1\0"+
    "\1\11\14\0\13\11\1\60\10\11\4\0\3\11\1\0"+
    "\1\11\14\0\15\11\1\61\6\11\4\0\3\11\1\0"+
    "\1\11\14\0\1\62\16\11\1\63\4\11\4\0\3\11"+
    "\1\0\1\11\14\0\1\11\1\64\22\11\4\0\3\11"+
    "\1\0\1\11\14\0\17\11\1\65\4\11\4\0\3\11"+
    "\1\0\1\11\14\0\1\11\1\66\22\11\4\0\1\67"+
    "\1\70\1\71\1\0\1\71\14\0\24\71\7\0\1\72"+
    "\41\0\13\73\1\74\35\73\4\0\3\11\1\0\1\11"+
    "\14\0\2\11\1\75\21\11\4\0\3\11\1\0\1\11"+
    "\14\0\16\11\1\60\5\11\4\0\3\11\1\0\1\11"+
    "\14\0\10\11\1\76\13\11\4\0\3\11\1\0\1\11"+
    "\14\0\4\11\1\77\3\11\1\100\13\11\4\0\3\11"+
    "\1\0\1\11\14\0\11\11\1\101\12\11\4\0\3\11"+
    "\1\0\1\11\14\0\14\11\1\102\7\11\4\0\3\11"+
    "\1\0\1\11\14\0\21\11\1\103\2\11\4\0\3\11"+
    "\1\0\1\11\14\0\14\11\1\104\7\11\4\0\3\11"+
    "\1\0\1\11\14\0\6\11\1\105\15\11\4\0\3\11"+
    "\1\0\1\11\14\0\4\11\1\106\17\11\4\0\3\11"+
    "\1\0\1\11\14\0\3\11\1\76\20\11\4\0\3\11"+
    "\1\0\1\11\14\0\20\11\1\76\3\11\4\0\3\11"+
    "\1\0\1\11\14\0\23\11\1\107\4\0\3\11\1\0"+
    "\1\11\14\0\14\11\1\110\7\11\4\0\3\11\1\0"+
    "\1\11\14\0\20\11\1\111\3\11\4\0\3\11\1\0"+
    "\1\11\14\0\13\11\1\112\10\11\4\0\3\11\1\0"+
    "\1\11\14\0\13\11\1\113\10\11\4\0\3\11\1\0"+
    "\1\11\14\0\12\11\1\114\11\11\7\0\1\115\45\0"+
    "\2\70\1\0\1\115\45\0\3\71\1\0\1\71\14\0"+
    "\24\71\4\0\1\116\1\117\43\0\13\73\1\120\35\73"+
    "\12\0\1\4\1\74\41\0\3\11\1\0\1\11\14\0"+
    "\7\11\1\121\14\11\4\0\3\11\1\0\1\11\14\0"+
    "\12\11\1\122\11\11\4\0\3\11\1\0\1\11\14\0"+
    "\3\11\1\123\20\11\4\0\3\11\1\0\1\11\14\0"+
    "\22\11\1\124\1\11\4\0\3\11\1\0\1\11\14\0"+
    "\1\125\23\11\4\0\3\11\1\0\1\11\14\0\7\11"+
    "\1\126\14\11\4\0\3\11\1\0\1\11\14\0\2\11"+
    "\1\127\21\11\4\0\3\11\1\0\1\11\14\0\13\11"+
    "\1\130\10\11\4\0\3\11\1\0\1\11\14\0\15\11"+
    "\1\131\6\11\4\0\3\11\1\0\1\11\14\0\15\11"+
    "\1\132\6\11\4\0\3\11\1\0\1\11\14\0\1\76"+
    "\23\11\4\0\3\11\1\0\1\11\14\0\20\11\1\133"+
    "\3\11\4\0\3\11\1\0\1\11\14\0\1\11\1\134"+
    "\22\11\7\0\1\135\47\0\1\136\2\0\1\116\3\0"+
    "\1\116\10\0\1\136\3\0\2\136\1\0\1\136\3\0"+
    "\1\136\1\0\1\136\11\0\2\117\1\136\2\0\1\116"+
    "\3\0\1\116\10\0\1\136\3\0\2\136\1\0\1\136"+
    "\3\0\1\136\1\0\1\136\5\0\12\73\1\4\1\120"+
    "\35\73\4\0\3\11\1\0\1\11\14\0\14\11\1\137"+
    "\7\11\4\0\3\11\1\0\1\11\14\0\13\11\1\140"+
    "\10\11\4\0\3\11\1\0\1\11\14\0\4\11\1\141"+
    "\17\11\4\0\3\11\1\0\1\11\14\0\1\11\1\142"+
    "\22\11\4\0\3\11\1\0\1\11\14\0\1\11\1\143"+
    "\22\11\4\0\3\11\1\0\1\11\14\0\3\11\1\144"+
    "\20\11\4\0\3\11\1\0\1\11\14\0\20\11\1\145"+
    "\3\11\4\0\3\11\1\0\1\11\14\0\1\146\23\11"+
    "\4\0\3\11\1\0\1\11\14\0\6\11\1\76\15\11"+
    "\4\0\3\11\1\0\1\11\14\0\10\11\1\147\13\11"+
    "\4\0\3\11\1\0\1\11\14\0\14\11\1\150\7\11"+
    "\7\0\1\151\45\0\1\67\1\70\47\0\3\11\1\0"+
    "\1\11\14\0\3\11\1\152\20\11\4\0\3\11\1\0"+
    "\1\11\14\0\12\11\1\153\11\11\4\0\3\11\1\0"+
    "\1\11\14\0\3\11\1\154\20\11\4\0\3\11\1\0"+
    "\1\11\14\0\22\11\1\155\1\11\4\0\3\11\1\0"+
    "\1\11\14\0\15\11\1\47\6\11\4\0\3\11\1\0"+
    "\1\11\14\0\2\11\1\156\21\11\4\0\3\11\1\0"+
    "\1\11\14\0\13\11\1\157\10\11\4\0\3\11\1\0"+
    "\1\11\14\0\16\11\1\160\5\11\4\0\3\11\1\0"+
    "\1\11\14\0\4\11\1\161\17\11\4\0\3\11\1\0"+
    "\1\11\14\0\1\11\1\162\22\11\4\0\3\11\1\0"+
    "\1\11\14\0\1\11\1\163\22\11";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3608];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\5\1\1\11\1\1\11\11"+
    "\14\1\4\0\25\1\3\0\20\1\4\0\14\1\2\0"+
    "\12\1\1\11\12\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[115];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */

private Symbol symbol(int type) {
return new Symbol(type, yyline, yycolumn);
}

private Symbol symbol(int type, Object value) {
return new Symbol(type, yyline, yycolumn, value);
}


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public Lexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 148) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;           
    int totalRead = 0;
    while (totalRead < requested) {
      int numRead = zzReader.read(zzBuffer, zzEndRead + totalRead, requested - totalRead);
      if (numRead == -1) {
        break;
      }
      totalRead += numRead;
    }

    if (totalRead > 0) {
      zzEndRead += totalRead;
      if (totalRead == requested) { /* possibly more input available */
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      return false;
    }

    // totalRead = 0: End of stream
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 1: 
          { /*throw new Error("Illegal character <"+yytext()+">");*/
          }
        case 30: break;
        case 2: 
          { 
          }
        case 31: break;
        case 3: 
          { return symbol(sym.NUMBER, new Integer(yytext()));
          }
        case 32: break;
        case 4: 
          { return symbol(sym.LETTER, yytext());
          }
        case 33: break;
        case 5: 
          { return symbol(sym.STRING, yytext());
          }
        case 34: break;
        case 6: 
          { return symbol(sym.MINUS);
          }
        case 35: break;
        case 7: 
          { return symbol(sym.SEMI);
          }
        case 36: break;
        case 8: 
          { return symbol(sym.PLUS);
          }
        case 37: break;
        case 9: 
          { return symbol(sym.LPAREN);
          }
        case 38: break;
        case 10: 
          { return symbol(sym.RPAREN);
          }
        case 39: break;
        case 11: 
          { return symbol(sym.LACCOL);
          }
        case 40: break;
        case 12: 
          { return symbol(sym.RACCOL);
          }
        case 41: break;
        case 13: 
          { return symbol(sym.EQUAL);
          }
        case 42: break;
        case 14: 
          { return symbol(sym.COMA);
          }
        case 43: break;
        case 15: 
          { return symbol(sym.REST);
          }
        case 44: break;
        case 16: 
          { return symbol(sym.COMPLEXNOTE, yytext());
          }
        case 45: break;
        case 17: 
          { return symbol(sym.LET);
          }
        case 46: break;
        case 18: 
          { return symbol(sym.ENS);
          }
        case 47: break;
        case 19: 
          { return symbol(sym.END);
          }
        case 48: break;
        case 20: 
          { return symbol(sym.PLAY);
          }
        case 49: break;
        case 21: 
          { return symbol(sym.TEMPO);
          }
        case 50: break;
        case 22: 
          { return symbol(sym.SCALE);
          }
        case 51: break;
        case 23: 
          { return symbol(sym.BEGIN);
          }
        case 52: break;
        case 24: 
          { return symbol(sym.CHORD);
          }
        case 53: break;
        case 25: 
          { return symbol(sym.SCALENAME, yytext());
          }
        case 54: break;
        case 26: 
          { return symbol(sym.INSTRU);
          }
        case 55: break;
        case 27: 
          { return symbol(sym.REPEAT);
          }
        case 56: break;
        case 28: 
          { return symbol(sym.SEQUENCE);
          }
        case 57: break;
        case 29: 
          { return symbol(sym.TRANSPOSE);
          }
        case 58: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            zzDoEOF();
              { return new java_cup.runtime.Symbol(sym.EOF); }
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
