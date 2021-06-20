package gnu.kawa.lispexpr;

import gnu.expr.QuoteExp;
import gnu.lists.FVector;
import gnu.mapping.InPort;
import gnu.mapping.Values;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;
import java.util.Vector;

public class ReaderVector extends ReadTableEntry {
  char close;
  
  public ReaderVector(char paramChar) {
    this.close = paramChar;
  }
  
  public static FVector readVector(LispReader paramLispReader, LineBufferedReader paramLineBufferedReader, int paramInt, char paramChar) throws IOException, SyntaxException {
    char c = ' ';
    if (paramLineBufferedReader instanceof InPort) {
      char c1 = ((InPort)paramLineBufferedReader).readState;
      InPort inPort = (InPort)paramLineBufferedReader;
      if (paramChar == ']') {
        c = '[';
      } else {
        c = '(';
      } 
      inPort.readState = c;
      c = c1;
    } 
    try {
      Vector<Object> vector = new Vector();
      ReadTable readTable = ReadTable.getCurrent();
      while (true) {
        FVector fVector;
        paramInt = paramLispReader.read();
        if (paramInt < 0)
          paramLispReader.eofError("unexpected EOF in vector"); 
        if (paramInt == paramChar) {
          Object[] arrayOfObject = new Object[vector.size()];
          vector.copyInto(arrayOfObject);
          fVector = new FVector(arrayOfObject);
          return fVector;
        } 
        Object object2 = fVector.readValues(paramInt, readTable);
        if (object2 instanceof Values) {
          Object[] arrayOfObject = ((Values)object2).getValues();
          int i = arrayOfObject.length;
          for (paramInt = 0; paramInt < i; paramInt++)
            vector.addElement(arrayOfObject[paramInt]); 
          continue;
        } 
        Object object1 = object2;
        if (object2 == QuoteExp.voidExp)
          object1 = Values.empty; 
        vector.addElement(object1);
      } 
    } finally {
      if (paramLineBufferedReader instanceof InPort)
        ((InPort)paramLineBufferedReader).readState = c; 
    } 
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    return readVector((LispReader)paramLexer, paramLexer.getPort(), paramInt2, this.close);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */