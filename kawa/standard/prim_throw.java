package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;

public class prim_throw extends Procedure1 implements Inlineable {
  private static ClassType javaThrowableType;
  
  public static final prim_throw primitiveThrow = new prim_throw();
  
  public static void throw_it(Object paramObject) throws Throwable {
    throw (Throwable)paramObject;
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    throw_it(paramObject);
    return Values.empty;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    CodeAttr codeAttr = paramCompilation.getCode();
    paramApplyExp.getArgs()[0].compile(paramCompilation, Target.pushObject);
    if (javaThrowableType == null)
      javaThrowableType = new ClassType("java.lang.Throwable"); 
    codeAttr.emitCheckcast((Type)javaThrowableType);
    codeAttr.emitThrow();
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.neverReturnsType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/prim_throw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */