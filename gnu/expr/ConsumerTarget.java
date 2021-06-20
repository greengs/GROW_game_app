package gnu.expr;

import gnu.bytecode.ClassType;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Method;
import gnu.bytecode.Scope;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.reflect.OccurrenceType;

public class ConsumerTarget extends Target {
  Variable consumer;
  
  boolean isContextTarget;
  
  public ConsumerTarget(Variable paramVariable) {
    this.consumer = paramVariable;
  }
  
  public static void compileUsingConsumer(Expression paramExpression, Compilation paramCompilation, Target paramTarget) {
    if (paramTarget instanceof ConsumerTarget || paramTarget instanceof IgnoreTarget) {
      paramExpression.compile(paramCompilation, paramTarget);
      return;
    } 
    ClassType classType = Compilation.typeValues;
    compileUsingConsumer(paramExpression, paramCompilation, paramTarget, classType.getDeclaredMethod("make", 0), classType.getDeclaredMethod("canonicalize", 0));
  }
  
  public static void compileUsingConsumer(Expression paramExpression, Compilation paramCompilation, Target paramTarget, Method paramMethod1, Method paramMethod2) {
    ClassType classType;
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    Scope scope = codeAttr.pushScope();
    if (paramMethod1.getName() == "<init>") {
      ClassType classType2 = paramMethod1.getDeclaringClass();
      ClassType classType1 = classType2;
      codeAttr.emitNew(classType2);
      codeAttr.emitDup((Type)classType1);
      codeAttr.emitInvoke(paramMethod1);
      classType = classType1;
    } else {
      Type type1 = classType.getReturnType();
      codeAttr.emitInvokeStatic((Method)classType);
      type = type1;
    } 
    Variable variable = scope.addVariable(codeAttr, type, null);
    ConsumerTarget consumerTarget = new ConsumerTarget(variable);
    codeAttr.emitStore(variable);
    paramExpression.compile(paramCompilation, consumerTarget);
    codeAttr.emitLoad(variable);
    if (paramMethod2 != null)
      codeAttr.emitInvoke(paramMethod2); 
    codeAttr.popScope();
    if (paramMethod2 != null)
      type = paramMethod2.getReturnType(); 
    paramTarget.compileFromStack(paramCompilation, type);
  }
  
  public static Target makeContextTarget(Compilation paramCompilation) {
    CodeAttr codeAttr = paramCompilation.getCode();
    paramCompilation.loadCallContext();
    codeAttr.emitGetField(Compilation.typeCallContext.getDeclaredField("consumer"));
    Variable variable = codeAttr.getCurrentScope().addVariable(codeAttr, (Type)Compilation.typeConsumer, "$result");
    codeAttr.emitStore(variable);
    ConsumerTarget consumerTarget = new ConsumerTarget(variable);
    consumerTarget.isContextTarget = true;
    return consumerTarget;
  }
  
  public void compileFromStack(Compilation paramCompilation, Type paramType) {
    compileFromStack(paramCompilation, paramType, -1);
  }
  
  void compileFromStack(Compilation paramCompilation, Type paramType, int paramInt) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   4: astore #8
    //   6: aconst_null
    //   7: astore #6
    //   9: aconst_null
    //   10: astore #7
    //   12: aconst_null
    //   13: astore_1
    //   14: iconst_0
    //   15: istore #4
    //   17: aload_2
    //   18: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   21: astore #9
    //   23: aload #9
    //   25: instanceof gnu/bytecode/PrimType
    //   28: ifeq -> 260
    //   31: aload #9
    //   33: invokevirtual getSignature : ()Ljava/lang/String;
    //   36: iconst_0
    //   37: invokevirtual charAt : (I)C
    //   40: istore #5
    //   42: iload #5
    //   44: lookupswitch default -> 128, 66 -> 194, 67 -> 240, 68 -> 227, 70 -> 217, 73 -> 194, 74 -> 204, 83 -> 194, 86 -> 193, 90 -> 250
    //   128: aload #6
    //   130: astore_2
    //   131: iload_3
    //   132: iflt -> 321
    //   135: aload #7
    //   137: astore #6
    //   139: iconst_0
    //   140: ifne -> 168
    //   143: aload #7
    //   145: astore #6
    //   147: aload_2
    //   148: ifnull -> 168
    //   151: getstatic gnu/expr/Compilation.typeConsumer : Lgnu/bytecode/ClassType;
    //   154: aload_2
    //   155: iconst_1
    //   156: anewarray gnu/bytecode/Type
    //   159: dup
    //   160: iconst_0
    //   161: aload_1
    //   162: aastore
    //   163: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   166: astore #6
    //   168: aload #6
    //   170: ifnull -> 180
    //   173: aload #8
    //   175: aload #6
    //   177: invokevirtual emitInvokeInterface : (Lgnu/bytecode/Method;)V
    //   180: iload #5
    //   182: bipush #67
    //   184: if_icmpne -> 193
    //   187: aload #8
    //   189: iconst_1
    //   190: invokevirtual emitPop : (I)V
    //   193: return
    //   194: ldc 'writeInt'
    //   196: astore_2
    //   197: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   200: astore_1
    //   201: goto -> 131
    //   204: ldc 'writeLong'
    //   206: astore_2
    //   207: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   210: astore_1
    //   211: iconst_1
    //   212: istore #4
    //   214: goto -> 131
    //   217: ldc 'writeFloat'
    //   219: astore_2
    //   220: getstatic gnu/bytecode/Type.floatType : Lgnu/bytecode/PrimType;
    //   223: astore_1
    //   224: goto -> 131
    //   227: ldc 'writeDouble'
    //   229: astore_2
    //   230: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   233: astore_1
    //   234: iconst_1
    //   235: istore #4
    //   237: goto -> 131
    //   240: ldc 'append'
    //   242: astore_2
    //   243: getstatic gnu/bytecode/Type.charType : Lgnu/bytecode/PrimType;
    //   246: astore_1
    //   247: goto -> 131
    //   250: ldc 'writeBoolean'
    //   252: astore_2
    //   253: getstatic gnu/bytecode/Type.booleanType : Lgnu/bytecode/PrimType;
    //   256: astore_1
    //   257: goto -> 131
    //   260: iconst_0
    //   261: istore #5
    //   263: iload_3
    //   264: iconst_1
    //   265: if_icmpeq -> 276
    //   268: aload #9
    //   270: invokestatic itemCountIsOne : (Lgnu/bytecode/Type;)Z
    //   273: ifeq -> 286
    //   276: ldc 'writeObject'
    //   278: astore_2
    //   279: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   282: astore_1
    //   283: goto -> 131
    //   286: getstatic gnu/expr/Compilation.typeValues : Lgnu/bytecode/ClassType;
    //   289: ldc 'writeValues'
    //   291: iconst_2
    //   292: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   295: astore_1
    //   296: aload #8
    //   298: aload_0
    //   299: getfield consumer : Lgnu/bytecode/Variable;
    //   302: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   305: iload_3
    //   306: ifne -> 314
    //   309: aload #8
    //   311: invokevirtual emitSwap : ()V
    //   314: aload #8
    //   316: aload_1
    //   317: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   320: return
    //   321: iload #4
    //   323: ifeq -> 373
    //   326: aload #8
    //   328: invokevirtual pushScope : ()Lgnu/bytecode/Scope;
    //   331: pop
    //   332: aload #8
    //   334: aload #9
    //   336: invokevirtual addLocal : (Lgnu/bytecode/Type;)Lgnu/bytecode/Variable;
    //   339: astore #6
    //   341: aload #8
    //   343: aload #6
    //   345: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   348: aload #8
    //   350: aload_0
    //   351: getfield consumer : Lgnu/bytecode/Variable;
    //   354: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   357: aload #8
    //   359: aload #6
    //   361: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   364: aload #8
    //   366: invokevirtual popScope : ()Lgnu/bytecode/Scope;
    //   369: pop
    //   370: goto -> 135
    //   373: aload #8
    //   375: aload_0
    //   376: getfield consumer : Lgnu/bytecode/Variable;
    //   379: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   382: aload #8
    //   384: invokevirtual emitSwap : ()V
    //   387: goto -> 135
  }
  
  public boolean compileWrite(Expression paramExpression, Compilation paramCompilation) {
    Type type = paramExpression.getType().getImplementationType();
    if ((type instanceof gnu.bytecode.PrimType && !type.isVoid()) || OccurrenceType.itemCountIsOne(type)) {
      paramCompilation.getCode().emitLoad(this.consumer);
      paramExpression.compile(paramCompilation, StackTarget.getInstance(type));
      compileFromStack(paramCompilation, type, 1);
      return true;
    } 
    return false;
  }
  
  public Variable getConsumerVariable() {
    return this.consumer;
  }
  
  public Type getType() {
    return (Type)Compilation.scmSequenceType;
  }
  
  public final boolean isContextTarget() {
    return this.isContextTarget;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ConsumerTarget.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */