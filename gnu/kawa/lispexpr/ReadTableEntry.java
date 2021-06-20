package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public abstract class ReadTableEntry {
  public static final ReadTableEntry brace;
  
  public static final ReadTableEntry constituent;
  
  public static final ReadTableEntry illegal = new ReaderMisc(0);
  
  public static final ReadTableEntry multipleEscape;
  
  public static final ReadTableEntry singleEscape;
  
  public static final ReadTableEntry whitespace = new ReaderMisc(1);
  
  static {
    singleEscape = new ReaderMisc(3);
    multipleEscape = new ReaderMisc(4);
    constituent = new ReaderMisc(2);
    brace = new ReaderMisc(2);
  }
  
  public static ReadTableEntry getConstituentInstance() {
    return constituent;
  }
  
  public static ReadTableEntry getDigitInstance() {
    return constituent;
  }
  
  public static ReadTableEntry getIllegalInstance() {
    return illegal;
  }
  
  public static ReadTableEntry getMultipleEscapeInstance() {
    return multipleEscape;
  }
  
  public static ReadTableEntry getSingleEscapeInstance() {
    return singleEscape;
  }
  
  public static ReadTableEntry getWhitespaceInstance() {
    return whitespace;
  }
  
  public int getKind() {
    return 5;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    throw new Error("invalid character");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReadTableEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */