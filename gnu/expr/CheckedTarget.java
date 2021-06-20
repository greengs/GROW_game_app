package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Label;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;

public class CheckedTarget extends StackTarget {
  static Method initWrongTypeProcMethod;
  
  static Method initWrongTypeStringMethod;
  
  static ClassType typeClassCastException;
  
  static ClassType typeWrongType;
  
  int argno;
  
  LambdaExp proc;
  
  String procname;
  
  public CheckedTarget(Type paramType) {
    super(paramType);
    this.argno = -4;
  }
  
  public CheckedTarget(Type paramType, LambdaExp paramLambdaExp, int paramInt) {
    super(paramType);
    this.proc = paramLambdaExp;
    this.procname = paramLambdaExp.getName();
    this.argno = paramInt;
  }
  
  public CheckedTarget(Type paramType, String paramString, int paramInt) {
    super(paramType);
    this.procname = paramString;
    this.argno = paramInt;
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, int paramInt, Type paramType) {
    emitCheckedCoerce(paramCompilation, paramLambdaExp, paramLambdaExp.getName(), paramInt, paramType, (Variable)null);
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, int paramInt, Type paramType, Variable paramVariable) {
    emitCheckedCoerce(paramCompilation, paramLambdaExp, paramLambdaExp.getName(), paramInt, paramType, paramVariable);
  }
  
  static void emitCheckedCoerce(Compilation paramCompilation, LambdaExp paramLambdaExp, String paramString, int paramInt, Type paramType, Variable paramVariable) {
    Method method;
    Object object;
    CodeAttr codeAttr = paramCompilation.getCode();
    boolean bool = codeAttr.isInTry();
    initWrongType();
    Label label2 = new Label(codeAttr);
    if (paramVariable == null && paramType != Type.toStringType) {
      object = codeAttr.pushScope();
      paramVariable = codeAttr.addLocal((Type)Type.objectType);
      codeAttr.emitDup(1);
      codeAttr.emitStore(paramVariable);
    } else {
      object = null;
    } 
    int i = codeAttr.getPC();
    label2.define(codeAttr);
    emitCoerceFromObject(paramType, paramCompilation);
    if (codeAttr.getPC() == i || paramType == Type.toStringType) {
      if (object != null)
        codeAttr.popScope(); 
      return;
    } 
    Label label3 = new Label(codeAttr);
    label3.define(codeAttr);
    Label label1 = new Label(codeAttr);
    label1.setTypes(codeAttr);
    if (bool)
      codeAttr.emitGoto(label1); 
    i = 0;
    codeAttr.setUnreachable();
    if (!bool)
      i = codeAttr.beginFragment(label1); 
    codeAttr.addHandler(label2, label3, typeClassCastException);
    int k = 0;
    int j = k;
    if (paramLambdaExp != null) {
      j = k;
      if (paramLambdaExp.isClassGenerated()) {
        j = k;
        if (!paramCompilation.method.getStaticFlag()) {
          j = k;
          if (paramCompilation.method.getDeclaringClass() == paramLambdaExp.getCompiledClassType(paramCompilation))
            j = 1; 
        } 
      } 
    } 
    k = paramCompilation.getLineNumber();
    if (k > 0)
      codeAttr.putLineNumber(k); 
    codeAttr.emitNew(typeWrongType);
    codeAttr.emitDupX();
    codeAttr.emitSwap();
    if (j != 0) {
      codeAttr.emitPushThis();
    } else {
      String str = paramString;
      if (paramString == null) {
        str = paramString;
        if (paramInt != -4)
          str = "lambda"; 
      } 
      codeAttr.emitPushString(str);
    } 
    codeAttr.emitPushInt(paramInt);
    codeAttr.emitLoad(paramVariable);
    if (j != 0) {
      method = initWrongTypeProcMethod;
    } else {
      method = initWrongTypeStringMethod;
    } 
    codeAttr.emitInvokeSpecial(method);
    if (object != null)
      codeAttr.popScope(); 
    codeAttr.emitThrow();
    if (bool) {
      label1.define(codeAttr);
      return;
    } 
    codeAttr.endFragment(i);
  }
  
  public static void emitCheckedCoerce(Compilation paramCompilation, String paramString, int paramInt, Type paramType) {
    emitCheckedCoerce(paramCompilation, (LambdaExp)null, paramString, paramInt, paramType, (Variable)null);
  }
  
  public static Target getInstance(Type paramType) {
    return (paramType == Type.objectType) ? Target.pushObject : new CheckedTarget(paramType);
  }
  
  public static Target getInstance(Type paramType, LambdaExp paramLambdaExp, int paramInt) {
    return (paramType == Type.objectType) ? Target.pushObject : new CheckedTarget(paramType, paramLambdaExp, paramInt);
  }
  
  public static Target getInstance(Type paramType, String paramString, int paramInt) {
    return (paramType == Type.objectType) ? Target.pushObject : new CheckedTarget(paramType, paramString, paramInt);
  }
  
  public static Target getInstance(Declaration paramDeclaration) {
    return getInstance(paramDeclaration.getType(), paramDeclaration.getName(), -2);
  }
  
  private static void initWrongType() {
    if (typeClassCastException == null)
      typeClassCastException = ClassType.make("java.lang.ClassCastException"); 
    if (typeWrongType == null) {
      typeWrongType = ClassType.make("gnu.mapping.WrongType");
      ClassType classType1 = typeClassCastException;
      ClassType classType2 = Compilation.javaStringType;
      PrimType primType1 = Type.intType;
      ClassType classType3 = Type.objectType;
      ClassType classType4 = typeWrongType;
      PrimType primType2 = Type.voidType;
      initWrongTypeStringMethod = classType4.addMethod("<init>", 1, new Type[] { (Type)classType1, (Type)classType2, (Type)primType1, (Type)classType3 }, (Type)primType2);
      classType1 = typeClassCastException;
      classType2 = Compilation.typeProcedure;
      primType1 = Type.intType;
      classType3 = Type.objectType;
      classType4 = typeWrongType;
      primType2 = Type.voidType;
      initWrongTypeProcMethod = classType4.addMethod("<init>", 1, new Type[] { (Type)classType1, (Type)classType2, (Type)primType1, (Type)classType3 }, (Type)primType2);
    } 
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType) {
    if (!compileFromStack0(paramCompilation, paramType))
      emitCheckedCoerce(paramCompilation, this.proc, this.procname, this.argno, this.type, (Variable)null); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/CheckedTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */