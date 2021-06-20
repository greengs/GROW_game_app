package gnu.kawa.lispexpr;

import gnu.lists.PairWithPosition;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderQuote extends ReadTableEntry {
  Object magicSymbol;
  
  Object magicSymbol2;
  
  char next;
  
  public ReaderQuote(Object paramObject) {
    this.magicSymbol = paramObject;
  }
  
  public ReaderQuote(Object paramObject1, char paramChar, Object paramObject2) {
    this.next = paramChar;
    this.magicSymbol = paramObject1;
    this.magicSymbol2 = paramObject2;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    LispReader lispReader = (LispReader)paramLexer;
    String str = lispReader.getName();
    paramInt1 = lispReader.getLineNumber();
    paramInt2 = lispReader.getColumnNumber();
    Object object2 = this.magicSymbol;
    Object object1 = object2;
    if (this.next != '\000') {
      i = lispReader.read();
      if (i == this.next) {
        object1 = this.magicSymbol2;
        i = lispReader.getLineNumber();
        int k = lispReader.getColumnNumber();
        return PairWithPosition.make(object1, PairWithPosition.make(lispReader.readObject(), lispReader.makeNil(), str, i + 1, k + 1), str, paramInt1 + 1, paramInt2 + 1);
      } 
    } else {
      i = lispReader.getLineNumber();
      int k = lispReader.getColumnNumber();
      return PairWithPosition.make(object1, PairWithPosition.make(lispReader.readObject(), lispReader.makeNil(), str, i + 1, k + 1), str, paramInt1 + 1, paramInt2 + 1);
    } 
    object1 = object2;
    if (i >= 0) {
      lispReader.unread(i);
      object1 = object2;
    } 
    int i = lispReader.getLineNumber();
    int j = lispReader.getColumnNumber();
    return PairWithPosition.make(object1, PairWithPosition.make(lispReader.readObject(), lispReader.makeNil(), str, i + 1, j + 1), str, paramInt1 + 1, paramInt2 + 1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderQuote.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */