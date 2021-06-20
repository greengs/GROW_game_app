package gnu.kawa.lispexpr;

import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderParens extends ReadTableEntry {
  private static ReaderParens instance;
  
  char close;
  
  Object command;
  
  int kind;
  
  char open;
  
  public ReaderParens(char paramChar1, char paramChar2, int paramInt, Object paramObject) {
    this.open = paramChar1;
    this.close = paramChar2;
    this.kind = paramInt;
    this.command = paramObject;
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2) {
    return getInstance(paramChar1, paramChar2, 5);
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2, int paramInt) {
    if (paramChar1 == '(' && paramChar2 == ')' && paramInt == 5) {
      if (instance == null)
        instance = new ReaderParens(paramChar1, paramChar2, paramInt, null); 
      return instance;
    } 
    return new ReaderParens(paramChar1, paramChar2, paramInt, null);
  }
  
  public static ReaderParens getInstance(char paramChar1, char paramChar2, int paramInt, Object paramObject) {
    return (paramObject == null) ? getInstance(paramChar1, paramChar2, paramInt) : new ReaderParens(paramChar1, paramChar2, paramInt, paramObject);
  }
  
  public static Object readList(LispReader paramLispReader, int paramInt1, int paramInt2, int paramInt3) throws IOException, SyntaxException {
    LineBufferedReader lineBufferedReader = paramLispReader.getPort();
    if (paramInt3 == 93) {
      c = '[';
    } else {
      c = '(';
    } 
    char c = paramLispReader.pushNesting(c);
    int i = lineBufferedReader.getLineNumber();
    int j = lineBufferedReader.getColumnNumber();
    ReadTableEntry readTableEntry = null;
    try {
      Object object = paramLispReader.makeNil();
      paramInt2 = 0;
      paramInt1 = 0;
      ReadTable readTable = ReadTable.getCurrent();
      while (true) {
        Object object1;
        ReadTableEntry readTableEntry1;
        int i1 = lineBufferedReader.getLineNumber();
        int k = lineBufferedReader.getColumnNumber();
        int n = lineBufferedReader.read();
        if (n == paramInt3)
          return object; 
        if (n < 0)
          paramLispReader.eofError("unexpected EOF in list starting here", i + 1, j); 
        if (n == 46) {
          m = lineBufferedReader.peek();
          readTableEntry1 = readTable.lookup(m);
          n = readTableEntry1.getKind();
          if (n == 1 || n == 5 || n == 0) {
            lineBufferedReader.skip();
            k++;
            if (m == paramInt3) {
              paramLispReader.error("unexpected '" + (char)paramInt3 + "' after '.'");
              return object;
            } 
            if (m < 0)
              paramLispReader.eofError("unexpected EOF in list starting here", i + 1, j); 
            if (paramInt2 != 0) {
              paramLispReader.error("multiple '.' in list");
              paramInt1 = 0;
              object = paramLispReader.makeNil();
              readTableEntry = null;
            } 
          } else {
            n = 46;
            readTableEntry1 = ReadTableEntry.getConstituentInstance();
            m = paramInt2;
            paramInt2 = n;
            ReadTableEntry readTableEntry3 = readTableEntry1;
            readTableEntry1 = readTableEntry;
          } 
        } else {
          ReadTableEntry readTableEntry3 = readTable.lookup(n);
          readTableEntry1 = readTableEntry;
          m = paramInt2;
          paramInt2 = n;
          object1 = paramLispReader.readValues(paramInt2, readTableEntry3, readTable);
        } 
        n = 1;
        paramInt2 = m;
        int m = n;
        ReadTableEntry readTableEntry2 = readTableEntry1;
        Object object2 = object1;
      } 
    } finally {
      paramLispReader.popNesting(c);
    } 
  }
  
  public int getKind() {
    return this.kind;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    Object object2 = readList((LispReader)paramLexer, paramInt1, paramInt2, this.close);
    Object object1 = object2;
    if (this.command != null) {
      object1 = paramLexer.getPort();
      paramInt1 = object1.getLineNumber();
      paramInt2 = object1.getColumnNumber();
      object1 = ((LispReader)paramLexer).makePair(this.command, paramInt1, paramInt2);
      ((LispReader)paramLexer).setCdr(object1, object2);
    } 
    return object1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderParens.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */