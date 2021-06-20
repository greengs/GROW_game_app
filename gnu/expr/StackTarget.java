package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Type;
import gnu.mapping.Values;

public class StackTarget extends Target {
  Type type;
  
  public StackTarget(Type paramType) {
    this.type = paramType;
  }
  
  static boolean compileFromStack0(Compilation paramCompilation, Type paramType1, Type paramType2) {
    if (paramType2 == paramType1)
      return true; 
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramType1.isVoid()) {
      paramCompilation.compileConstant(Values.empty);
      ClassType classType = Type.pointer_type;
    } else {
      type = paramType1;
      if (paramType1 instanceof gnu.bytecode.PrimType) {
        type = paramType1;
        if (paramType2 instanceof gnu.bytecode.PrimType) {
          codeAttr.emitConvert(paramType1, paramType2);
          return true;
        } 
      } 
    } 
    if (type instanceof gnu.bytecode.ArrayType)
      return (paramType2 != Type.pointer_type && !"java.lang.Cloneable".equals(paramType2.getName())) ? (!CodeAttr.castNeeded(type.getImplementationType(), paramType2.getImplementationType())) : true; 
    paramType2.emitConvertFromPrimitive(type, codeAttr);
    Type type = codeAttr.topType();
    return !CodeAttr.castNeeded(type.getImplementationType(), paramType2.getImplementationType());
  }
  
  public static void convert(Compilation paramCompilation, Type paramType1, Type paramType2) {
    if (!compileFromStack0(paramCompilation, paramType1, paramType2))
      emitCoerceFromObject(paramType2, paramCompilation); 
  }
  
  protected static void emitCoerceFromObject(Type paramType, Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    if (paramType instanceof gnu.kawa.reflect.OccurrenceType) {
      paramCompilation.compileConstant(paramType, Target.pushObject);
      codeAttr.emitSwap();
      codeAttr.emitInvokeVirtual(ClassType.make("gnu.bytecode.Type").getDeclaredMethod("coerceFromObject", 1));
      return;
    } 
    paramCompilation.usedClass(paramType);
    paramType.emitCoerceFromObject(codeAttr);
  }
  
  public static Target getInstance(Type paramType) {
    return paramType.isVoid() ? Target.Ignore : ((paramType == Type.pointer_type) ? Target.pushObject : new StackTarget(paramType));
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType) {
    if (!compileFromStack0(paramCompilation, paramType))
      emitCoerceFromObject(this.type, paramCompilation); 
  }
  
  protected boolean compileFromStack0(Compilation paramCompilation, Type paramType) {
    return compileFromStack0(paramCompilation, paramType, this.type);
  }
  
  public Type getType() {
    return this.type;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/StackTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */