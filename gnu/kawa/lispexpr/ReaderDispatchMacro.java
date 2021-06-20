package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.math.IntNum;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderDispatchMacro extends ReaderMisc {
  Procedure procedure;
  
  public ReaderDispatchMacro(Procedure paramProcedure) {
    super(5);
    this.procedure = paramProcedure;
  }
  
  public Procedure getProcedure() {
    return this.procedure;
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    LineBufferedReader lineBufferedReader = paramLexer.getPort();
    try {
      return this.procedure.apply3(lineBufferedReader, Char.make(paramInt1), IntNum.make(paramInt2));
    } catch (IOException iOException) {
      throw iOException;
    } catch (SyntaxException syntaxException) {
      throw syntaxException;
    } catch (Throwable throwable) {
      syntaxException.fatal("reader macro '" + this.procedure + "' threw: " + throwable);
      return null;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderDispatchMacro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */