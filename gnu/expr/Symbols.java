package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.SimpleSymbol;

public class Symbols {
  private static int gensym_counter;
  
  static int generateInt() {
    // Byte code:
    //   0: ldc gnu/expr/Symbols
    //   2: monitorenter
    //   3: getstatic gnu/expr/Symbols.gensym_counter : I
    //   6: iconst_1
    //   7: iadd
    //   8: istore_0
    //   9: iload_0
    //   10: putstatic gnu/expr/Symbols.gensym_counter : I
    //   13: ldc gnu/expr/Symbols
    //   15: monitorexit
    //   16: iload_0
    //   17: ireturn
    //   18: astore_1
    //   19: ldc gnu/expr/Symbols
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	18	finally
  }
  
  public static final SimpleSymbol gentemp() {
    return SimpleSymbol.valueOf("GS." + Integer.toString(generateInt()));
  }
  
  public static final String intern(String paramString) {
    return make(paramString);
  }
  
  public static String make(String paramString) {
    return paramString.intern();
  }
  
  public static void print(String paramString, Consumer paramConsumer) {
    int i;
    if (paramConsumer instanceof OutPort && ((OutPort)paramConsumer).printReadable) {
      i = 1;
    } else {
      i = 0;
    } 
    if (i) {
      int j = paramString.length();
      for (i = 0; i < j; i++) {
        char c = paramString.charAt(i);
        if (!Character.isLowerCase(c) && c != '!' && c != '$' && c != '%' && c != '&' && c != '*' && c != '/' && c != ':' && c != '<' && c != '=' && c != '>' && c != '?' && c != '~' && c != '_' && c != '^' && ((c != '+' && c != '-') || (i <= 0 && j != 1)) && (!Character.isDigit(c) || i <= 0) && (c != '.' || (i != 0 && paramString.charAt(i - 1) != '.')))
          paramConsumer.write(92); 
        paramConsumer.write(c);
      } 
    } else {
      paramConsumer.write(paramString);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Symbols.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */