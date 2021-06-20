package gnu.kawa.lispexpr;

import gnu.lists.Sequence;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderIgnoreRestOfLine extends ReadTableEntry {
  static ReaderIgnoreRestOfLine instance = new ReaderIgnoreRestOfLine();
  
  public static ReaderIgnoreRestOfLine getInstance() {
    return instance;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    do {
      paramInt1 = paramLexer.read();
      if (paramInt1 < 0)
        return Sequence.eofValue; 
    } while (paramInt1 != 10 && paramInt1 != 13);
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderIgnoreRestOfLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */