package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConditionalTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.Target;
import gnu.expr.TypeValue;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;

public class InstanceOf extends Procedure2 implements Inlineable {
  static Method instanceMethod;
  
  static ClassType typeType;
  
  protected Language language;
  
  public InstanceOf(Language paramLanguage) {
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileReflect:validateApplyInstanceOf");
  }
  
  public InstanceOf(Language paramLanguage, String paramString) {
    this(paramLanguage);
    setName(paramString);
  }
  
  public static void emitIsInstance(TypeValue paramTypeValue, Variable paramVariable, Compilation paramCompilation, Target paramTarget) {
    ConditionalTarget conditionalTarget;
    CodeAttr codeAttr = paramCompilation.getCode();
    paramTypeValue.emitTestIf(null, null, paramCompilation);
    paramTypeValue = null;
    if (paramTarget instanceof ConditionalTarget) {
      conditionalTarget = (ConditionalTarget)paramTarget;
      codeAttr.emitGoto(conditionalTarget.ifTrue);
    } else {
      codeAttr.emitPushInt(1);
    } 
    codeAttr.emitElse();
    if (conditionalTarget != null) {
      codeAttr.emitGoto(conditionalTarget.ifFalse);
    } else {
      codeAttr.emitPushInt(0);
    } 
    codeAttr.emitFi();
    if (conditionalTarget == null)
      paramTarget.compileFromStack(paramCompilation, paramCompilation.getLanguage().getTypeFor(boolean.class)); 
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    Type type = this.language.asType(paramObject2);
    paramObject2 = type;
    if (type instanceof PrimType)
      paramObject2 = ((PrimType)type).boxedType(); 
    return this.language.booleanObject(paramObject2.isInstance(paramObject1));
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   4: astore #5
    //   6: aload_2
    //   7: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   10: astore #6
    //   12: aconst_null
    //   13: astore_1
    //   14: aload #5
    //   16: iconst_1
    //   17: aaload
    //   18: astore #4
    //   20: aload #4
    //   22: instanceof gnu/expr/QuoteExp
    //   25: ifeq -> 134
    //   28: aload_0
    //   29: getfield language : Lgnu/expr/Language;
    //   32: aload #4
    //   34: checkcast gnu/expr/QuoteExp
    //   37: invokevirtual getValue : ()Ljava/lang/Object;
    //   40: invokevirtual asType : (Ljava/lang/Object;)Lgnu/bytecode/Type;
    //   43: astore #4
    //   45: aload #4
    //   47: astore_1
    //   48: aload_1
    //   49: ifnull -> 176
    //   52: aload_1
    //   53: astore #4
    //   55: aload_1
    //   56: instanceof gnu/bytecode/PrimType
    //   59: ifeq -> 71
    //   62: aload_1
    //   63: checkcast gnu/bytecode/PrimType
    //   66: invokevirtual boxedType : ()Lgnu/bytecode/ClassType;
    //   69: astore #4
    //   71: aload #5
    //   73: iconst_0
    //   74: aaload
    //   75: aload_2
    //   76: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   79: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   82: aload #4
    //   84: instanceof gnu/expr/TypeValue
    //   87: ifeq -> 147
    //   90: aload #4
    //   92: checkcast gnu/expr/TypeValue
    //   95: aconst_null
    //   96: aload_2
    //   97: aload_3
    //   98: invokeinterface emitIsInstance : (Lgnu/bytecode/Variable;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   103: return
    //   104: astore #4
    //   106: aload_2
    //   107: bipush #119
    //   109: new java/lang/StringBuilder
    //   112: dup
    //   113: invokespecial <init> : ()V
    //   116: ldc 'unknown type spec: '
    //   118: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   121: aconst_null
    //   122: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   125: invokevirtual toString : ()Ljava/lang/String;
    //   128: invokevirtual error : (CLjava/lang/String;)V
    //   131: goto -> 48
    //   134: aload_0
    //   135: getfield language : Lgnu/expr/Language;
    //   138: aload #4
    //   140: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   143: astore_1
    //   144: goto -> 48
    //   147: aload #4
    //   149: aload #6
    //   151: invokevirtual emitIsInstance : (Lgnu/bytecode/CodeAttr;)V
    //   154: aload_2
    //   155: aload #4
    //   157: invokevirtual usedClass : (Lgnu/bytecode/Type;)V
    //   160: aload_3
    //   161: aload_2
    //   162: aload_0
    //   163: getfield language : Lgnu/expr/Language;
    //   166: getstatic java/lang/Boolean.TYPE : Ljava/lang/Class;
    //   169: invokevirtual getTypeFor : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   172: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   175: return
    //   176: getstatic gnu/kawa/reflect/InstanceOf.typeType : Lgnu/bytecode/ClassType;
    //   179: ifnonnull -> 208
    //   182: ldc 'gnu.bytecode.Type'
    //   184: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   187: putstatic gnu/kawa/reflect/InstanceOf.typeType : Lgnu/bytecode/ClassType;
    //   190: getstatic gnu/kawa/reflect/InstanceOf.typeType : Lgnu/bytecode/ClassType;
    //   193: ldc 'isInstance'
    //   195: getstatic gnu/expr/Compilation.apply1args : [Lgnu/bytecode/Type;
    //   198: getstatic gnu/bytecode/Type.boolean_type : Lgnu/bytecode/PrimType;
    //   201: iconst_1
    //   202: invokevirtual addMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;Lgnu/bytecode/Type;I)Lgnu/bytecode/Method;
    //   205: putstatic gnu/kawa/reflect/InstanceOf.instanceMethod : Lgnu/bytecode/Method;
    //   208: aload #5
    //   210: iconst_1
    //   211: aaload
    //   212: aload_2
    //   213: getstatic gnu/kawa/reflect/InstanceOf.typeType : Lgnu/bytecode/ClassType;
    //   216: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   219: aload #5
    //   221: iconst_0
    //   222: aaload
    //   223: aload_2
    //   224: getstatic gnu/expr/Target.pushObject : Lgnu/expr/Target;
    //   227: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   230: aload #6
    //   232: getstatic gnu/kawa/reflect/InstanceOf.instanceMethod : Lgnu/bytecode/Method;
    //   235: invokevirtual emitInvokeVirtual : (Lgnu/bytecode/Method;)V
    //   238: goto -> 160
    // Exception table:
    //   from	to	target	type
    //   28	45	104	java/lang/Exception
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return this.language.getTypeFor(boolean.class);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/InstanceOf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */