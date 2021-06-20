package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.xml.Nodes;
import gnu.kawa.xml.TreeScanner;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class RelativeStep extends MethodProc implements Inlineable {
  public static final RelativeStep relativeStep = new RelativeStep();
  
  RelativeStep() {
    setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateApplyRelativeStep");
  }
  
  public static TreeScanner extractStep(Expression paramExpression) {
    while (true) {
      if (!(paramExpression instanceof ApplyExp))
        return null; 
      ApplyExp applyExp = (ApplyExp)paramExpression;
      Expression expression = applyExp.getFunction();
      if (expression instanceof QuoteExp) {
        Object object = ((QuoteExp)expression).getValue();
        if (object instanceof TreeScanner)
          return (TreeScanner)object; 
        if (object instanceof ValuesFilter) {
          Expression expression1 = applyExp.getArgs()[0];
          continue;
        } 
      } 
      break;
    } 
    return null;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Nodes nodes;
    Object object = paramCallContext.getNextArg();
    Procedure procedure = (Procedure)paramCallContext.getNextArg();
    Consumer consumer = paramCallContext.consumer;
    if (object instanceof Nodes) {
      nodes = (Nodes)object;
    } else {
      nodes = new Nodes();
      Values.writeValues(object, (Consumer)nodes);
    } 
    int k = nodes.size();
    int j = 0;
    object = IntNum.make(k);
    RelativeStepFilter relativeStepFilter = new RelativeStepFilter(consumer);
    for (int i = 1; i <= k; i++) {
      j = nodes.nextPos(j);
      procedure.check3(nodes.getPosPrevious(j), IntNum.make(i), object, paramCallContext);
      Values.writeValues(paramCallContext.runUntilValue(), (Consumer)relativeStepFilter);
    } 
    relativeStepFilter.finish();
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   4: astore #6
    //   6: aload #6
    //   8: iconst_0
    //   9: aaload
    //   10: astore #10
    //   12: aload #6
    //   14: iconst_1
    //   15: aaload
    //   16: astore #11
    //   18: aload_3
    //   19: instanceof gnu/expr/IgnoreTarget
    //   22: ifeq -> 40
    //   25: aload #10
    //   27: aload_2
    //   28: aload_3
    //   29: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   32: aload #11
    //   34: aload_2
    //   35: aload_3
    //   36: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   39: return
    //   40: aload_1
    //   41: invokevirtual getTypeRaw : ()Lgnu/bytecode/Type;
    //   44: astore #7
    //   46: aload #7
    //   48: astore #6
    //   50: aload #7
    //   52: ifnonnull -> 60
    //   55: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   58: astore #6
    //   60: aload #6
    //   62: invokestatic itemPrimeType : (Lgnu/bytecode/Type;)Lgnu/bytecode/Type;
    //   65: astore #6
    //   67: getstatic gnu/kawa/xml/NodeType.anyNodeTest : Lgnu/kawa/xml/NodeType;
    //   70: aload #6
    //   72: invokevirtual compare : (Lgnu/bytecode/Type;)I
    //   75: istore #4
    //   77: iload #4
    //   79: iflt -> 189
    //   82: bipush #78
    //   84: istore #4
    //   86: aload #11
    //   88: invokestatic extractStep : (Lgnu/expr/Expression;)Lgnu/kawa/xml/TreeScanner;
    //   91: astore #6
    //   93: iload #4
    //   95: istore #5
    //   97: aload #6
    //   99: ifnull -> 175
    //   102: aload #10
    //   104: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   107: astore #7
    //   109: aload #6
    //   111: instanceof gnu/kawa/xml/ChildAxis
    //   114: ifne -> 137
    //   117: aload #6
    //   119: instanceof gnu/kawa/xml/AttributeAxis
    //   122: ifne -> 137
    //   125: iload #4
    //   127: istore #5
    //   129: aload #6
    //   131: instanceof gnu/kawa/xml/SelfAxis
    //   134: ifeq -> 175
    //   137: aload #7
    //   139: instanceof gnu/kawa/xml/NodeSetType
    //   142: ifne -> 171
    //   145: iload #4
    //   147: istore #5
    //   149: iload #4
    //   151: bipush #78
    //   153: if_icmpne -> 175
    //   156: iload #4
    //   158: istore #5
    //   160: aload #10
    //   162: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   165: invokestatic itemCountIsZeroOrOne : (Lgnu/bytecode/Type;)Z
    //   168: ifeq -> 175
    //   171: bipush #83
    //   173: istore #5
    //   175: aload_3
    //   176: instanceof gnu/expr/ConsumerTarget
    //   179: ifne -> 210
    //   182: aload_1
    //   183: aload_2
    //   184: aload_3
    //   185: invokestatic compileUsingConsumer : (Lgnu/expr/Expression;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   188: return
    //   189: iload #4
    //   191: bipush #-3
    //   193: if_icmpne -> 203
    //   196: bipush #65
    //   198: istore #4
    //   200: goto -> 86
    //   203: bipush #32
    //   205: istore #4
    //   207: goto -> 86
    //   210: aload_2
    //   211: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   214: astore #12
    //   216: aload #12
    //   218: invokevirtual pushScope : ()Lgnu/bytecode/Scope;
    //   221: astore #7
    //   223: iload #5
    //   225: bipush #65
    //   227: if_icmpeq -> 237
    //   230: iload #5
    //   232: bipush #83
    //   234: if_icmpne -> 301
    //   237: aconst_null
    //   238: astore_1
    //   239: aconst_null
    //   240: astore #6
    //   242: aconst_null
    //   243: astore #7
    //   245: aload #11
    //   247: checkcast gnu/expr/LambdaExp
    //   250: aload #10
    //   252: iconst_1
    //   253: aconst_null
    //   254: aload_2
    //   255: aload_3
    //   256: invokestatic compileInlined : (Lgnu/expr/LambdaExp;Lgnu/expr/Expression;ILgnu/bytecode/Method;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   259: iload #5
    //   261: bipush #78
    //   263: if_icmpne -> 426
    //   266: aload #12
    //   268: aload #6
    //   270: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   273: aload #12
    //   275: aload #7
    //   277: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   280: aload #12
    //   282: getstatic gnu/expr/Compilation.typeValues : Lgnu/bytecode/ClassType;
    //   285: ldc 'writeValues'
    //   287: iconst_2
    //   288: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   291: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   294: aload #12
    //   296: invokevirtual popScope : ()Lgnu/bytecode/Scope;
    //   299: pop
    //   300: return
    //   301: iload #5
    //   303: bipush #78
    //   305: if_icmpne -> 408
    //   308: ldc 'gnu.kawa.xml.SortedNodes'
    //   310: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   313: astore_1
    //   314: aload_1
    //   315: ldc '<init>'
    //   317: iconst_0
    //   318: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   321: astore #6
    //   323: aload #7
    //   325: aload #12
    //   327: aload_1
    //   328: aconst_null
    //   329: invokevirtual addVariable : (Lgnu/bytecode/CodeAttr;Lgnu/bytecode/Type;Ljava/lang/String;)Lgnu/bytecode/Variable;
    //   332: astore #7
    //   334: new gnu/expr/ConsumerTarget
    //   337: dup
    //   338: aload #7
    //   340: invokespecial <init> : (Lgnu/bytecode/Variable;)V
    //   343: astore #9
    //   345: aload #12
    //   347: aload_1
    //   348: invokevirtual emitNew : (Lgnu/bytecode/ClassType;)V
    //   351: aload #12
    //   353: aload_1
    //   354: invokevirtual emitDup : (Lgnu/bytecode/Type;)V
    //   357: aload_3
    //   358: checkcast gnu/expr/ConsumerTarget
    //   361: invokevirtual getConsumerVariable : ()Lgnu/bytecode/Variable;
    //   364: astore #8
    //   366: iload #5
    //   368: bipush #78
    //   370: if_icmpeq -> 380
    //   373: aload #12
    //   375: aload #8
    //   377: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   380: aload #12
    //   382: aload #6
    //   384: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   387: aload #12
    //   389: aload #7
    //   391: invokevirtual emitStore : (Lgnu/bytecode/Variable;)V
    //   394: aload #9
    //   396: astore_3
    //   397: aload #7
    //   399: astore #6
    //   401: aload #8
    //   403: astore #7
    //   405: goto -> 245
    //   408: ldc 'gnu.xquery.util.RelativeStepFilter'
    //   410: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   413: astore_1
    //   414: aload_1
    //   415: ldc '<init>'
    //   417: iconst_1
    //   418: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   421: astore #6
    //   423: goto -> 323
    //   426: iload #5
    //   428: bipush #32
    //   430: if_icmpne -> 294
    //   433: aload #12
    //   435: aload #6
    //   437: invokevirtual emitLoad : (Lgnu/bytecode/Variable;)V
    //   440: aload #12
    //   442: aload_1
    //   443: ldc 'finish'
    //   445: iconst_0
    //   446: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   449: invokevirtual emitInvoke : (Lgnu/bytecode/Method;)V
    //   452: goto -> 294
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/RelativeStep.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */