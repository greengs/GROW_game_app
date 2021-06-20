package kawa.lang;

import gnu.lists.LList;
import gnu.lists.PairWithPosition;
import gnu.mapping.CallContext;
import gnu.mapping.Environment;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import gnu.text.SourceLocator;
import gnu.text.SourceMessages;

public class Eval extends Procedure1or2 {
  public static final Eval eval = new Eval();
  
  static final String evalFunctionName = "atEvalLevel$";
  
  static {
    eval.setName("eval");
  }
  
  public static Object eval(Object paramObject, Environment paramEnvironment) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      eval(paramObject, paramEnvironment, callContext);
      return callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public static void eval(Object paramObject, Environment paramEnvironment, CallContext paramCallContext) throws Throwable {
    if (paramObject instanceof PairWithPosition) {
      paramObject = new PairWithPosition((SourceLocator)paramObject, paramObject, LList.Empty);
    } else {
      paramObject = new PairWithPosition(paramObject, LList.Empty);
      paramObject.setFile("<eval>");
    } 
    evalBody(paramObject, paramEnvironment, new SourceMessages(), paramCallContext);
  }
  
  public static Object evalBody(Object paramObject, Environment paramEnvironment, SourceMessages paramSourceMessages) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    int i = callContext.startFromContext();
    try {
      evalBody(paramObject, paramEnvironment, paramSourceMessages, callContext);
      return callContext.getFromContext(i);
    } catch (Throwable throwable) {
      callContext.cleanupFromContext(i);
      throw throwable;
    } 
  }
  
  public static void evalBody(Object paramObject, Environment paramEnvironment, SourceMessages paramSourceMessages, CallContext paramCallContext) throws Throwable {
    // Byte code:
    //   0: invokestatic getDefaultLanguage : ()Lgnu/expr/Language;
    //   3: astore #6
    //   5: invokestatic getCurrent : ()Lgnu/mapping/Environment;
    //   8: astore #5
    //   10: aload_1
    //   11: aload #5
    //   13: if_acmpeq -> 20
    //   16: aload_1
    //   17: invokestatic setCurrent : (Lgnu/mapping/Environment;)V
    //   20: new kawa/lang/Translator
    //   23: dup
    //   24: aload #6
    //   26: aload_2
    //   27: aload_1
    //   28: aload #6
    //   30: invokestatic getInstance : (Lgnu/mapping/Environment;Lgnu/expr/Language;)Lgnu/expr/NameLookup;
    //   33: invokespecial <init> : (Lgnu/expr/Language;Lgnu/text/SourceMessages;Lgnu/expr/NameLookup;)V
    //   36: astore #6
    //   38: aload #6
    //   40: iconst_1
    //   41: putfield immediate : Z
    //   44: aload #6
    //   46: iconst_3
    //   47: invokevirtual setState : (I)V
    //   50: aload #6
    //   52: iconst_1
    //   53: invokevirtual setSharedModuleDefs : (Z)V
    //   56: aload #6
    //   58: aconst_null
    //   59: checkcast java/lang/String
    //   62: invokevirtual pushNewModule : (Ljava/lang/String;)Lgnu/expr/ModuleExp;
    //   65: astore #7
    //   67: aload #6
    //   69: invokestatic setSaveCurrent : (Lgnu/expr/Compilation;)Lgnu/expr/Compilation;
    //   72: astore #8
    //   74: aload #6
    //   76: getfield formStack : Ljava/util/Stack;
    //   79: invokevirtual size : ()I
    //   82: istore #4
    //   84: aload #6
    //   86: aload_0
    //   87: aload #7
    //   89: iconst_0
    //   90: invokevirtual scanBody : (Ljava/lang/Object;Lgnu/expr/ScopeExp;Z)Lgnu/lists/LList;
    //   93: pop
    //   94: aload #6
    //   96: iload #4
    //   98: putfield firstForm : I
    //   101: aload #6
    //   103: aload #7
    //   105: invokevirtual finishModule : (Lgnu/expr/ModuleExp;)V
    //   108: aload #8
    //   110: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   113: aload_0
    //   114: instanceof gnu/lists/PairWithPosition
    //   117: ifeq -> 132
    //   120: aload #7
    //   122: aload_0
    //   123: checkcast gnu/lists/PairWithPosition
    //   126: invokevirtual getFileName : ()Ljava/lang/String;
    //   129: invokevirtual setFile : (Ljava/lang/String;)V
    //   132: new java/lang/StringBuilder
    //   135: dup
    //   136: invokespecial <init> : ()V
    //   139: ldc 'atEvalLevel$'
    //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   144: astore_0
    //   145: getstatic gnu/expr/ModuleExp.interactiveCounter : I
    //   148: iconst_1
    //   149: iadd
    //   150: istore #4
    //   152: iload #4
    //   154: putstatic gnu/expr/ModuleExp.interactiveCounter : I
    //   157: aload #7
    //   159: aload_0
    //   160: iload #4
    //   162: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   165: invokevirtual toString : ()Ljava/lang/String;
    //   168: invokevirtual setName : (Ljava/lang/String;)V
    //   171: aload_1
    //   172: aload_3
    //   173: aload #6
    //   175: aconst_null
    //   176: aconst_null
    //   177: invokestatic evalModule : (Lgnu/mapping/Environment;Lgnu/mapping/CallContext;Lgnu/expr/Compilation;Ljava/net/URL;Lgnu/mapping/OutPort;)Z
    //   180: pop
    //   181: aload_2
    //   182: invokevirtual seenErrors : ()Z
    //   185: ifeq -> 242
    //   188: new java/lang/RuntimeException
    //   191: dup
    //   192: new java/lang/StringBuilder
    //   195: dup
    //   196: invokespecial <init> : ()V
    //   199: ldc 'invalid syntax in eval form:\\n'
    //   201: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   204: aload_2
    //   205: bipush #20
    //   207: invokevirtual toString : (I)Ljava/lang/String;
    //   210: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   213: invokevirtual toString : ()Ljava/lang/String;
    //   216: invokespecial <init> : (Ljava/lang/String;)V
    //   219: athrow
    //   220: astore_0
    //   221: aload_1
    //   222: aload #5
    //   224: if_acmpeq -> 232
    //   227: aload #5
    //   229: invokestatic setCurrent : (Lgnu/mapping/Environment;)V
    //   232: aload_0
    //   233: athrow
    //   234: astore_0
    //   235: aload #8
    //   237: invokestatic restoreCurrent : (Lgnu/expr/Compilation;)V
    //   240: aload_0
    //   241: athrow
    //   242: aload_1
    //   243: aload #5
    //   245: if_acmpeq -> 253
    //   248: aload #5
    //   250: invokestatic setCurrent : (Lgnu/mapping/Environment;)V
    //   253: return
    // Exception table:
    //   from	to	target	type
    //   16	20	220	finally
    //   20	74	220	finally
    //   74	108	234	finally
    //   108	132	220	finally
    //   132	220	220	finally
    //   235	242	220	finally
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Procedure.checkArgCount((Procedure)this, paramCallContext.count);
    Object object = paramCallContext.getNextArg();
    Environment environment2 = (Environment)paramCallContext.getNextArg(null);
    Environment environment1 = environment2;
    if (environment2 == null)
      environment1 = Environment.user(); 
    paramCallContext.lastArg();
    eval(object, environment1, paramCallContext);
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    return eval(paramObject, Environment.user());
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return eval(paramObject1, (Environment)paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Eval.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */