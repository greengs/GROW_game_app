package gnu.expr;

import gnu.bytecode.Field;

public abstract class Initializer {
  public Field field;
  
  Initializer next;
  
  public static Initializer reverse(Initializer paramInitializer) {
    Initializer initializer = null;
    while (paramInitializer != null) {
      Initializer initializer1 = paramInitializer.next;
      paramInitializer.next = initializer;
      initializer = paramInitializer;
      paramInitializer = initializer1;
    } 
    return initializer;
  }
  
  public abstract void emit(Compilation paramCompilation);
  
  public void reportError(String paramString, Compilation paramCompilation) {
    paramCompilation.error('e', paramString + "field " + this.field);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Initializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */