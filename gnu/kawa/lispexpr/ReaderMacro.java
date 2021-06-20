package gnu.kawa.lispexpr;

import gnu.mapping.Procedure;
import gnu.text.Char;
import gnu.text.Lexer;
import gnu.text.LineBufferedReader;
import gnu.text.SyntaxException;
import java.io.IOException;

public class ReaderMacro extends ReaderMisc {
  Procedure procedure;
  
  public ReaderMacro(Procedure paramProcedure) {
    super(5);
    this.procedure = paramProcedure;
  }
  
  public ReaderMacro(Procedure paramProcedure, boolean paramBoolean) {
    super(b);
    byte b;
    this.procedure = paramProcedure;
  }
  
  public Procedure getProcedure() {
    return this.procedure;
  }
  
  public boolean isNonTerminating() {
    return (this.kind == 6);
  }
  
  public Object read(Lexer paramLexer, int paramInt1, int paramInt2) throws IOException, SyntaxException {
    LineBufferedReader lineBufferedReader = paramLexer.getPort();
    try {
      return this.procedure.apply2(lineBufferedReader, Char.make(paramInt1));
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ReaderMacro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */