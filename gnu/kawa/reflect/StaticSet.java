package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Field;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import java.lang.reflect.Field;

public class StaticSet extends Procedure1 implements Inlineable {
  ClassType ctype;
  
  Field field;
  
  String fname;
  
  Field reflectField;
  
  public StaticSet(ClassType paramClassType, String paramString, Type paramType, int paramInt) {
    this.ctype = paramClassType;
    this.fname = paramString;
    this.field = paramClassType.getField(paramString);
    if (this.field == null)
      this.field = paramClassType.addField(paramString, paramType, paramInt); 
  }
  
  StaticSet(Class paramClass, String paramString) {
    this.ctype = (ClassType)Type.make(paramClass);
    this.fname = paramString;
  }
  
  public Object apply1(Object paramObject) {
    if (this.reflectField == null) {
      Class clazz = this.ctype.getReflectClass();
      try {
        this.reflectField = clazz.getField(this.fname);
        try {
          this.reflectField.set(null, paramObject);
          return Values.empty;
        } catch (IllegalAccessException illegalAccessException) {
          throw new RuntimeException("illegal access for field " + this.fname);
        } 
      } catch (NoSuchFieldException noSuchFieldException) {
        throw new RuntimeException("no such field " + this.fname + " in " + clazz.getName());
      } 
    } 
    try {
      this.reflectField.set(null, noSuchFieldException);
      return Values.empty;
    } catch (IllegalAccessException illegalAccessException) {
      throw new RuntimeException("illegal access for field " + this.fname);
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    if (this.field == null) {
      this.field = this.ctype.getField(this.fname);
      if (this.field == null)
        this.field = this.ctype.addField(this.fname, Type.make(this.reflectField.getType()), this.reflectField.getModifiers()); 
    } 
    paramApplyExp.getArgs()[0].compile(paramCompilation, this.field.getType());
    paramCompilation.getCode().emitPutStatic(this.field);
    paramCompilation.compileConstant(Values.empty, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.voidType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/StaticSet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */