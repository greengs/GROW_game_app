package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure0;
import java.lang.reflect.Field;

public class StaticGet extends Procedure0 implements Inlineable {
  ClassType ctype;
  
  Field field;
  
  String fname;
  
  Field reflectField;
  
  public StaticGet(ClassType paramClassType, String paramString, Type paramType, int paramInt) {
    this.ctype = paramClassType;
    this.fname = paramString;
    this.field = paramClassType.getField(paramString);
    if (this.field == null)
      this.field = paramClassType.addField(paramString, paramType, paramInt); 
  }
  
  StaticGet(Class paramClass, String paramString) {
    this.ctype = (ClassType)Type.make(paramClass);
    this.fname = paramString;
  }
  
  private Field getField() {
    if (this.field == null) {
      this.field = this.ctype.getField(this.fname);
      if (this.field == null)
        this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers()); 
    } 
    return this.field;
  }
  
  public Object apply0() {
    if (this.reflectField == null) {
      Class clazz = this.ctype.getReflectClass();
      try {
        this.reflectField = clazz.getField(this.fname);
        try {
          return this.reflectField.get(null);
        } catch (IllegalAccessException illegalAccessException) {
          throw new RuntimeException("illegal access for field " + this.fname);
        } 
      } catch (NoSuchFieldException noSuchFieldException) {
        throw new RuntimeException("no such field " + this.fname + " in " + illegalAccessException.getName());
      } 
    } 
    try {
      return this.reflectField.get(null);
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("illegal access for field " + this.fname);
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    getField();
    paramCompilation.getCode().emitGetStatic(this.field);
    paramTarget.compileFromStack(paramCompilation, this.field.getType());
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return getField().getType();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/StaticGet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */