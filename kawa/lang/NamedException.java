package kawa.lang;

import gnu.mapping.Procedure;
import gnu.mapping.Symbol;

public class NamedException extends RuntimeException {
  Object[] args;
  
  Symbol name;
  
  public NamedException(Symbol paramSymbol, Object[] paramArrayOfObject) {
    this.name = paramSymbol;
    this.args = paramArrayOfObject;
  }
  
  public Object applyHandler(Object paramObject, Procedure paramProcedure) throws Throwable {
    checkMatch(paramObject);
    return paramProcedure.applyN(this.args);
  }
  
  public void checkMatch(Object paramObject) {
    if (paramObject != this.name && paramObject != Boolean.TRUE)
      throw this; 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("#<ERROR");
    byte b = 0;
    int j = this.args.length;
    int i = b;
    if (j > 1) {
      i = b;
      if (this.args[0] == "misc-error")
        i = 0 + 1; 
    } 
    while (i < j) {
      stringBuffer.append(' ');
      stringBuffer.append(this.args[i]);
      i++;
    } 
    stringBuffer.append(">");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/NamedException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */