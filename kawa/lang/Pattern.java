package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.expr.Compilation;
import gnu.text.Printable;

public abstract class Pattern implements Printable {
  private static Type[] matchArgs;
  
  public static final Method matchPatternMethod;
  
  public static ClassType typePattern = ClassType.make("kawa.lang.Pattern");
  
  static {
    matchArgs = new Type[] { (Type)Type.pointer_type, (Type)Compilation.objArrayType, (Type)Type.intType };
    matchPatternMethod = typePattern.addMethod("match", matchArgs, (Type)Type.booleanType, 1);
  }
  
  public abstract boolean match(Object paramObject, Object[] paramArrayOfObject, int paramInt);
  
  public Object[] match(Object paramObject) {
    Object[] arrayOfObject = new Object[varCount()];
    return match(paramObject, arrayOfObject, 0) ? arrayOfObject : null;
  }
  
  public abstract int varCount();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Pattern.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */