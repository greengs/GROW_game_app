package kawa.lang;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;

public class GetFieldProc extends Procedure1 implements Inlineable {
  ClassType ctype;
  
  Field field;
  
  public GetFieldProc(ClassType paramClassType, String paramString) {
    this.ctype = paramClassType;
    this.field = Field.searchField(paramClassType.getFields(), paramString);
  }
  
  public GetFieldProc(ClassType paramClassType, String paramString, Type paramType, int paramInt) {
    this.ctype = paramClassType;
    this.field = paramClassType.getField(paramString);
    if (this.field == null)
      this.field = paramClassType.addField(paramString, paramType, paramInt); 
  }
  
  public GetFieldProc(Class paramClass, String paramString) {
    this((ClassType)Type.make(paramClass), paramString);
  }
  
  private Field getField() {
    return this.field;
  }
  
  public Object apply1(Object paramObject) {
    try {
      return this.field.getReflectField().get(paramObject);
    } catch (NoSuchFieldException noSuchFieldException) {
      throw new RuntimeException("no such field " + this.field.getSourceName() + " in " + this.ctype.getName());
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("illegal access for field " + this.field.getSourceName());
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    if (this.ctype.getReflectClass().getClassLoader() instanceof gnu.bytecode.ArrayClassLoader) {
      ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
      return;
    } 
    paramApplyExp.getArgs()[0].compile(paramCompilation, (Type)this.ctype);
    paramCompilation.getCode().emitGetField(this.field);
    paramTarget.compileFromStack(paramCompilation, this.field.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return getField().getType();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/GetFieldProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */