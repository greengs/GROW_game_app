package gnu.expr;

import gnu.bytecode.Type;

public class IgnoreTarget extends Target {
  public void compileFromStack(Compilation paramCompilation, Type paramType) {
    if (!paramType.isVoid())
      paramCompilation.getCode().emitPop(1); 
  }
  
  public Type getType() {
    return (Type)Type.voidType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/IgnoreTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */