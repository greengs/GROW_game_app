package gnu.kawa.lispexpr;

import gnu.kawa.util.RangeTable;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatch extends ReadTableEntry {
  int kind;
  
  RangeTable table;
  
  public ReaderDispatch() {
    this.table = new RangeTable();
    this.kind = 5;
  }
  
  public ReaderDispatch(boolean paramBoolean) {
    byte b;
    this.table = new RangeTable();
    if (paramBoolean) {
      b = 6;
    } else {
      b = 5;
    } 
    this.kind = b;
  }
  
  public static ReaderDispatch create(ReadTable paramReadTable) {
    ReaderDispatch readerDispatch = new ReaderDispatch();
    ReaderDispatchMisc readerDispatchMisc = ReaderDispatchMisc.getInstance();
    readerDispatch.set(58, readerDispatchMisc);
    readerDispatch.set(66, readerDispatchMisc);
    readerDispatch.set(68, readerDispatchMisc);
    readerDispatch.set(69, readerDispatchMisc);
    readerDispatch.set(70, readerDispatchMisc);
    readerDispatch.set(73, readerDispatchMisc);
    readerDispatch.set(79, readerDispatchMisc);
    readerDispatch.set(82, readerDispatchMisc);
    readerDispatch.set(83, readerDispatchMisc);
    readerDispatch.set(84, readerDispatchMisc);
    readerDispatch.set(85, readerDispatchMisc);
    readerDispatch.set(88, readerDispatchMisc);
    readerDispatch.set(124, readerDispatchMisc);
    readerDispatch.set(59, readerDispatchMisc);
    readerDispatch.set(33, readerDispatchMisc);
    readerDispatch.set(92, readerDispatchMisc);
    readerDispatch.set(61, readerDispatchMisc);
    readerDispatch.set(35, readerDispatchMisc);
    readerDispatch.set(47, readerDispatchMisc);
    readerDispatch.set(39, new ReaderQuote(paramReadTable.makeSymbol("function")));
    readerDispatch.set(40, new ReaderVector(')'));
    readerDispatch.set(60, new ReaderXmlElement());
    return readerDispatch;
  }
  
  public int getKind() {
    return this.kind;
  }
  
  public ReadTableEntry lookup(int paramInt) {
    return (ReadTableEntry)this.table.lookup(paramInt, null);
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    paramInt1 = -1;
    while (true) {
      paramInt2 = paramLexer.read();
      if (paramInt2 < 0)
        paramLexer.eofError("unexpected EOF after " + (char)paramInt2); 
      if (paramInt2 <= 65536) {
        int i = Character.digit((char)paramInt2, 10);
        if (i < 0) {
          paramInt2 = Character.toUpperCase((char)paramInt2);
        } else {
          if (paramInt1 < 0) {
            paramInt1 = i;
            continue;
          } 
          paramInt1 = paramInt1 * 10 + i;
          continue;
        } 
      } 
      ReadTableEntry readTableEntry = (ReadTableEntry)this.table.lookup(paramInt2, null);
      if (readTableEntry == null) {
        paramLexer.error('e', paramLexer.getName(), paramLexer.getLineNumber() + 1, paramLexer.getColumnNumber(), "invalid dispatch character '" + (char)paramInt2 + '\'');
        return Values.empty;
      } 
      return readTableEntry.read(paramLexer, paramInt2, paramInt1);
    } 
  }
  
  public void set(int paramInt, Object paramObject) {
    this.table.set(paramInt, paramInt, paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */