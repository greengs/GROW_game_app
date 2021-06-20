package gnu.expr;

import gnu.bytecode.Type;

public abstract class Target {
  public static final Target Ignore = new IgnoreTarget();
  
  public static final Target pushObject = new StackTarget((Type)Type.pointer_type);
  
  public static Target pushValue(Type paramType) {
    return paramType.isVoid() ? Ignore : StackTarget.getInstance(paramType);
  }
  
  public abstract void compileFromStack(Compilation paramCompilation, Type paramType);
  
  public abstract Type getType();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/Target.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */