package gnu.expr;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassFileInput;
import gnu.bytecode.ClassType;
import gnu.bytecode.ClassTypeWriter;
import gnu.bytecode.CodeAttr;
import gnu.bytecode.Field;
import gnu.bytecode.Method;
import gnu.bytecode.Type;
import gnu.bytecode.Variable;
import gnu.kawa.lispexpr.LangObjType;
import gnu.lists.Consumer;
import gnu.lists.ConsumerWriter;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongArguments;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URL;

public class PrimProcedure extends MethodProc implements Inlineable {
  private static ClassLoader systemClassLoader = PrimProcedure.class.getClassLoader();
  
  Type[] argTypes;
  
  Member member;
  
  Method method;
  
  char mode;
  
  int op_code;
  
  Type retType;
  
  boolean sideEffectFree;
  
  LambdaExp source;
  
  public PrimProcedure(int paramInt, ClassType paramClassType, String paramString, Type paramType, Type[] paramArrayOfType) {
    boolean bool;
    this.op_code = paramInt;
    if (paramInt == 184) {
      bool = true;
    } else {
      bool = false;
    } 
    this.method = paramClassType.addMethod(paramString, bool, paramArrayOfType, paramType);
    this.retType = paramType;
    this.argTypes = paramArrayOfType;
    if (paramInt != 184)
      b = 86; 
    this.mode = b;
  }
  
  public PrimProcedure(int paramInt, Type paramType, Type[] paramArrayOfType) {
    this.op_code = paramInt;
    this.retType = paramType;
    this.argTypes = paramArrayOfType;
  }
  
  public PrimProcedure(Method paramMethod) {
    ClassType classType;
    Type type;
    init(paramMethod);
    if (paramMethod.getName().endsWith("$X")) {
      classType = Type.objectType;
    } else {
      type = classType.getReturnType();
    } 
    this.retType = type;
  }
  
  public PrimProcedure(Method paramMethod, char paramChar, Language paramLanguage) {
    this.mode = paramChar;
    init(paramMethod);
    Type[] arrayOfType = this.argTypes;
    int j = arrayOfType.length;
    this.argTypes = null;
    int i = j;
    while (true) {
      int k = i - 1;
      if (k >= 0) {
        Type type1 = arrayOfType[k];
        Type type2 = paramLanguage.getLangTypeFor(type1);
        i = k;
        if (type1 != type2) {
          if (this.argTypes == null) {
            this.argTypes = new Type[j];
            System.arraycopy(arrayOfType, 0, this.argTypes, 0, j);
          } 
          this.argTypes[k] = type2;
          i = k;
        } 
        continue;
      } 
      if (this.argTypes == null)
        this.argTypes = arrayOfType; 
      if (isConstructor()) {
        this.retType = (Type)paramMethod.getDeclaringClass();
        return;
      } 
      if (paramMethod.getName().endsWith("$X")) {
        this.retType = (Type)Type.objectType;
        return;
      } 
      this.retType = paramLanguage.getLangTypeFor(paramMethod.getReturnType());
      if (this.retType == Type.toStringType) {
        this.retType = (Type)Type.javalangStringType;
        return;
      } 
      return;
    } 
  }
  
  public PrimProcedure(Method paramMethod, LambdaExp paramLambdaExp) {
    this(paramMethod);
    this.retType = paramLambdaExp.getReturnType();
    this.source = paramLambdaExp;
  }
  
  public PrimProcedure(Method paramMethod, Language paramLanguage) {
    this(paramMethod, false, paramLanguage);
  }
  
  public PrimProcedure(String paramString1, String paramString2, int paramInt) {
    this(ClassType.make(paramString1).getDeclaredMethod(paramString2, paramInt));
  }
  
  public PrimProcedure(Method paramMethod, Language paramLanguage) {
    this(((ClassType)paramLanguage.getTypeFor(paramMethod.getDeclaringClass())).getMethod(paramMethod), paramLanguage);
  }
  
  private void compileArgs(Expression[] paramArrayOfExpression, int paramInt, Type paramType, Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual takesVarArgs : ()Z
    //   4: istore #14
    //   6: aload_0
    //   7: invokevirtual getName : ()Ljava/lang/String;
    //   10: astore #18
    //   12: aconst_null
    //   13: astore #17
    //   15: aload #4
    //   17: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   20: astore #19
    //   22: aload_3
    //   23: getstatic gnu/bytecode/Type.voidType : Lgnu/bytecode/PrimType;
    //   26: if_acmpne -> 400
    //   29: iconst_1
    //   30: istore #6
    //   32: aload_0
    //   33: getfield argTypes : [Lgnu/bytecode/Type;
    //   36: arraylength
    //   37: iload #6
    //   39: isub
    //   40: istore #5
    //   42: iload #5
    //   44: istore #7
    //   46: aload_0
    //   47: invokevirtual takesContext : ()Z
    //   50: ifeq -> 59
    //   53: iload #5
    //   55: iconst_1
    //   56: isub
    //   57: istore #7
    //   59: aload_1
    //   60: arraylength
    //   61: iload_2
    //   62: isub
    //   63: istore #12
    //   65: aload_3
    //   66: ifnull -> 74
    //   69: iload #6
    //   71: ifeq -> 406
    //   74: iconst_1
    //   75: istore #8
    //   77: iconst_0
    //   78: istore #11
    //   80: iconst_0
    //   81: istore #10
    //   83: iload #11
    //   85: istore #5
    //   87: iload #14
    //   89: istore #13
    //   91: iload #14
    //   93: ifeq -> 270
    //   96: iload #11
    //   98: istore #5
    //   100: iload #14
    //   102: istore #13
    //   104: aload_0
    //   105: getfield method : Lgnu/bytecode/Method;
    //   108: invokevirtual getModifiers : ()I
    //   111: sipush #128
    //   114: iand
    //   115: ifeq -> 270
    //   118: iload #11
    //   120: istore #5
    //   122: iload #14
    //   124: istore #13
    //   126: iload #12
    //   128: ifle -> 270
    //   131: iload #11
    //   133: istore #5
    //   135: iload #14
    //   137: istore #13
    //   139: aload_0
    //   140: getfield argTypes : [Lgnu/bytecode/Type;
    //   143: arraylength
    //   144: ifle -> 270
    //   147: iload #8
    //   149: ifeq -> 412
    //   152: iconst_0
    //   153: istore #9
    //   155: iload #11
    //   157: istore #5
    //   159: iload #14
    //   161: istore #13
    //   163: iload #12
    //   165: iload #9
    //   167: iload #7
    //   169: iadd
    //   170: if_icmpne -> 270
    //   173: aload_1
    //   174: aload_1
    //   175: arraylength
    //   176: iconst_1
    //   177: isub
    //   178: aaload
    //   179: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   182: astore #15
    //   184: aload_0
    //   185: getfield argTypes : [Lgnu/bytecode/Type;
    //   188: aload_0
    //   189: getfield argTypes : [Lgnu/bytecode/Type;
    //   192: arraylength
    //   193: iconst_1
    //   194: isub
    //   195: aaload
    //   196: astore #16
    //   198: iload #11
    //   200: istore #5
    //   202: iload #14
    //   204: istore #13
    //   206: aload #15
    //   208: instanceof gnu/bytecode/ObjectType
    //   211: ifeq -> 270
    //   214: iload #11
    //   216: istore #5
    //   218: iload #14
    //   220: istore #13
    //   222: aload #16
    //   224: instanceof gnu/bytecode/ArrayType
    //   227: ifeq -> 270
    //   230: iload #11
    //   232: istore #5
    //   234: iload #14
    //   236: istore #13
    //   238: aload #16
    //   240: checkcast gnu/bytecode/ArrayType
    //   243: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   246: instanceof gnu/bytecode/ArrayType
    //   249: ifne -> 270
    //   252: iload #10
    //   254: istore #5
    //   256: aload #15
    //   258: instanceof gnu/bytecode/ArrayType
    //   261: ifne -> 267
    //   264: iconst_1
    //   265: istore #5
    //   267: iconst_0
    //   268: istore #13
    //   270: iload #13
    //   272: ifeq -> 424
    //   275: iload #8
    //   277: ifeq -> 418
    //   280: iconst_1
    //   281: istore #9
    //   283: iload #7
    //   285: iload #9
    //   287: isub
    //   288: istore #9
    //   290: aload_0
    //   291: getfield source : Lgnu/expr/LambdaExp;
    //   294: ifnonnull -> 433
    //   297: aconst_null
    //   298: astore #16
    //   300: aload #16
    //   302: astore #15
    //   304: aload #16
    //   306: ifnull -> 328
    //   309: aload #16
    //   311: astore #15
    //   313: aload #16
    //   315: invokevirtual isThisParameter : ()Z
    //   318: ifeq -> 328
    //   321: aload #16
    //   323: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   326: astore #15
    //   328: iconst_0
    //   329: istore #10
    //   331: aload #17
    //   333: astore #16
    //   335: aload #15
    //   337: astore #17
    //   339: aload #16
    //   341: astore #15
    //   343: iload #13
    //   345: ifeq -> 474
    //   348: aload #16
    //   350: astore #15
    //   352: iload #10
    //   354: iload #9
    //   356: if_icmpne -> 474
    //   359: aload_0
    //   360: getfield argTypes : [Lgnu/bytecode/Type;
    //   363: iload #7
    //   365: iconst_1
    //   366: isub
    //   367: iload #6
    //   369: iadd
    //   370: aaload
    //   371: astore #15
    //   373: aload #15
    //   375: getstatic gnu/expr/Compilation.scmListType : Lgnu/bytecode/ClassType;
    //   378: if_acmpeq -> 389
    //   381: aload #15
    //   383: getstatic gnu/kawa/lispexpr/LangObjType.listType : Lgnu/kawa/lispexpr/LangObjType;
    //   386: if_acmpne -> 445
    //   389: aload_1
    //   390: iload_2
    //   391: iload #10
    //   393: iadd
    //   394: aload #4
    //   396: invokestatic compile : ([Lgnu/expr/Expression;ILgnu/expr/Compilation;)V
    //   399: return
    //   400: iconst_0
    //   401: istore #6
    //   403: goto -> 32
    //   406: iconst_0
    //   407: istore #8
    //   409: goto -> 77
    //   412: iconst_1
    //   413: istore #9
    //   415: goto -> 155
    //   418: iconst_0
    //   419: istore #9
    //   421: goto -> 283
    //   424: aload_1
    //   425: arraylength
    //   426: iload_2
    //   427: isub
    //   428: istore #9
    //   430: goto -> 290
    //   433: aload_0
    //   434: getfield source : Lgnu/expr/LambdaExp;
    //   437: invokevirtual firstDecl : ()Lgnu/expr/Declaration;
    //   440: astore #16
    //   442: goto -> 300
    //   445: aload #19
    //   447: aload_1
    //   448: arraylength
    //   449: iload_2
    //   450: isub
    //   451: iload #9
    //   453: isub
    //   454: invokevirtual emitPushInt : (I)V
    //   457: aload #15
    //   459: checkcast gnu/bytecode/ArrayType
    //   462: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   465: astore #15
    //   467: aload #19
    //   469: aload #15
    //   471: invokevirtual emitNewArray : (Lgnu/bytecode/Type;)V
    //   474: iload #10
    //   476: iload #12
    //   478: if_icmpge -> 399
    //   481: iload #5
    //   483: ifeq -> 735
    //   486: iload #10
    //   488: iconst_1
    //   489: iadd
    //   490: iload #12
    //   492: if_icmpne -> 735
    //   495: iconst_1
    //   496: istore #11
    //   498: iload #10
    //   500: iload #9
    //   502: if_icmplt -> 741
    //   505: aload #19
    //   507: iconst_1
    //   508: invokevirtual emitDup : (I)V
    //   511: aload #19
    //   513: iload #10
    //   515: iload #9
    //   517: isub
    //   518: invokevirtual emitPushInt : (I)V
    //   521: aload #4
    //   523: aload #15
    //   525: invokevirtual usedClass : (Lgnu/bytecode/Type;)V
    //   528: iload #11
    //   530: ifeq -> 811
    //   533: getstatic gnu/bytecode/Type.objectType : Lgnu/bytecode/ClassType;
    //   536: astore #16
    //   538: aload_0
    //   539: getfield source : Lgnu/expr/LambdaExp;
    //   542: ifnonnull -> 818
    //   545: aload #16
    //   547: aload #18
    //   549: iload #10
    //   551: iconst_1
    //   552: iadd
    //   553: invokestatic getInstance : (Lgnu/bytecode/Type;Ljava/lang/String;I)Lgnu/expr/Target;
    //   556: astore #16
    //   558: aload_1
    //   559: iload_2
    //   560: iload #10
    //   562: iadd
    //   563: aaload
    //   564: aload #4
    //   566: aload #16
    //   568: aload_1
    //   569: iload_2
    //   570: iload #10
    //   572: iadd
    //   573: aaload
    //   574: invokevirtual compileNotePosition : (Lgnu/expr/Compilation;Lgnu/expr/Target;Lgnu/expr/Expression;)V
    //   577: iload #11
    //   579: ifeq -> 674
    //   582: aload #15
    //   584: checkcast gnu/bytecode/ArrayType
    //   587: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   590: astore #16
    //   592: aload #19
    //   594: invokevirtual emitDup : ()V
    //   597: aload #19
    //   599: aload #15
    //   601: invokevirtual emitInstanceof : (Lgnu/bytecode/Type;)V
    //   604: aload #19
    //   606: invokevirtual emitIfIntNotZero : ()V
    //   609: aload #19
    //   611: aload #15
    //   613: invokevirtual emitCheckcast : (Lgnu/bytecode/Type;)V
    //   616: aload #19
    //   618: invokevirtual emitElse : ()V
    //   621: aload #19
    //   623: iconst_1
    //   624: invokevirtual emitPushInt : (I)V
    //   627: aload #19
    //   629: aload #16
    //   631: invokevirtual emitNewArray : (Lgnu/bytecode/Type;)V
    //   634: aload #19
    //   636: invokevirtual emitDupX : ()V
    //   639: aload #19
    //   641: invokevirtual emitSwap : ()V
    //   644: aload #19
    //   646: iconst_0
    //   647: invokevirtual emitPushInt : (I)V
    //   650: aload #19
    //   652: invokevirtual emitSwap : ()V
    //   655: aload #16
    //   657: aload #19
    //   659: invokevirtual emitCoerceFromObject : (Lgnu/bytecode/CodeAttr;)V
    //   662: aload #19
    //   664: aload #15
    //   666: invokevirtual emitArrayStore : (Lgnu/bytecode/Type;)V
    //   669: aload #19
    //   671: invokevirtual emitFi : ()V
    //   674: iload #10
    //   676: iload #9
    //   678: if_icmplt -> 688
    //   681: aload #19
    //   683: aload #15
    //   685: invokevirtual emitArrayStore : (Lgnu/bytecode/Type;)V
    //   688: aload #17
    //   690: astore #16
    //   692: aload #17
    //   694: ifnull -> 718
    //   697: iload #8
    //   699: ifne -> 711
    //   702: aload #17
    //   704: astore #16
    //   706: iload #10
    //   708: ifle -> 718
    //   711: aload #17
    //   713: invokevirtual nextDecl : ()Lgnu/expr/Declaration;
    //   716: astore #16
    //   718: iload #10
    //   720: iconst_1
    //   721: iadd
    //   722: istore #10
    //   724: aload #16
    //   726: astore #17
    //   728: aload #15
    //   730: astore #16
    //   732: goto -> 339
    //   735: iconst_0
    //   736: istore #11
    //   738: goto -> 498
    //   741: aload #17
    //   743: ifnull -> 766
    //   746: iload #8
    //   748: ifne -> 756
    //   751: iload #10
    //   753: ifle -> 766
    //   756: aload #17
    //   758: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   761: astore #15
    //   763: goto -> 521
    //   766: iload #8
    //   768: ifeq -> 786
    //   771: aload_0
    //   772: getfield argTypes : [Lgnu/bytecode/Type;
    //   775: iload #10
    //   777: iload #6
    //   779: iadd
    //   780: aaload
    //   781: astore #15
    //   783: goto -> 763
    //   786: iload #10
    //   788: ifne -> 797
    //   791: aload_3
    //   792: astore #15
    //   794: goto -> 763
    //   797: aload_0
    //   798: getfield argTypes : [Lgnu/bytecode/Type;
    //   801: iload #10
    //   803: iconst_1
    //   804: isub
    //   805: aaload
    //   806: astore #15
    //   808: goto -> 763
    //   811: aload #15
    //   813: astore #16
    //   815: goto -> 538
    //   818: aload #16
    //   820: aload_0
    //   821: getfield source : Lgnu/expr/LambdaExp;
    //   824: iload #10
    //   826: invokestatic getInstance : (Lgnu/bytecode/Type;Lgnu/expr/LambdaExp;I)Lgnu/expr/Target;
    //   829: astore #16
    //   831: goto -> 558
  }
  
  public static void compileInvoke(Compilation paramCompilation, Method paramMethod, Target paramTarget, boolean paramBoolean, int paramInt, Type paramType) {
    ClassType classType;
    CodeAttr codeAttr = paramCompilation.getCode();
    paramCompilation.usedClass((Type)paramMethod.getDeclaringClass());
    paramCompilation.usedClass(paramMethod.getReturnType());
    if (!takesContext(paramMethod)) {
      codeAttr.emitInvokeMethod(paramMethod, paramInt);
    } else {
      if (paramTarget instanceof IgnoreTarget || (paramTarget instanceof ConsumerTarget && ((ConsumerTarget)paramTarget).isContextTarget())) {
        Field field;
        Variable variable1;
        paramType = null;
        ClassType classType1 = null;
        paramCompilation.loadCallContext();
        if (paramTarget instanceof IgnoreTarget) {
          classType1 = Compilation.typeCallContext;
          field = classType1.getDeclaredField("consumer");
          codeAttr.pushScope();
          variable1 = codeAttr.addLocal((Type)classType1);
          codeAttr.emitDup();
          codeAttr.emitGetField(field);
          codeAttr.emitStore(variable1);
          codeAttr.emitDup();
          codeAttr.emitGetStatic(ClassType.make("gnu.lists.VoidConsumer").getDeclaredField("instance"));
          codeAttr.emitPutField(field);
        } 
        codeAttr.emitInvokeMethod(paramMethod, paramInt);
        if (paramBoolean) {
          paramCompilation.loadCallContext();
          codeAttr.emitInvoke(Compilation.typeCallContext.getDeclaredMethod("runUntilDone", 0));
        } 
        if (paramTarget instanceof IgnoreTarget) {
          paramCompilation.loadCallContext();
          codeAttr.emitLoad(variable1);
          codeAttr.emitPutField(field);
          codeAttr.popScope();
          return;
        } 
        return;
      } 
      paramCompilation.loadCallContext();
      classType = Type.objectType;
      codeAttr.pushScope();
      Variable variable = codeAttr.addLocal((Type)Type.intType);
      paramCompilation.loadCallContext();
      codeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("startFromContext", 0));
      codeAttr.emitStore(variable);
      codeAttr.emitWithCleanupStart();
      codeAttr.emitInvokeMethod(paramMethod, paramInt);
      codeAttr.emitWithCleanupCatch(null);
      paramCompilation.loadCallContext();
      codeAttr.emitLoad(variable);
      codeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("cleanupFromContext", 1));
      codeAttr.emitWithCleanupDone();
      paramCompilation.loadCallContext();
      codeAttr.emitLoad(variable);
      codeAttr.emitInvokeVirtual(Compilation.typeCallContext.getDeclaredMethod("getFromContext", 1));
      codeAttr.popScope();
    } 
    paramTarget.compileFromStack(paramCompilation, (Type)classType);
  }
  
  public static void disassemble(Procedure paramProcedure, ClassTypeWriter paramClassTypeWriter) throws Exception {
    MethodProc methodProc;
    if (paramProcedure instanceof GenericProc) {
      String str;
      GenericProc genericProc = (GenericProc)paramProcedure;
      int j = genericProc.getMethodCount();
      paramClassTypeWriter.print("Generic procedure with ");
      paramClassTypeWriter.print(j);
      if (j == 1) {
        str = " method.";
      } else {
        str = "methods.";
      } 
      paramClassTypeWriter.println(str);
      for (int i = 0; i < j; i++) {
        methodProc = genericProc.getMethod(i);
        if (methodProc != null) {
          paramClassTypeWriter.println();
          disassemble((Procedure)methodProc, paramClassTypeWriter);
        } 
      } 
    } else {
      String str1;
      Class<?> clazz1;
      String str3 = null;
      Class<?> clazz2 = methodProc.getClass();
      if (methodProc instanceof ModuleMethod) {
        clazz1 = ((ModuleMethod)methodProc).module.getClass();
        str1 = str3;
      } else {
        clazz1 = clazz2;
        str1 = str3;
        if (methodProc instanceof PrimProcedure) {
          Method method1 = ((PrimProcedure)methodProc).method;
          clazz1 = clazz2;
          str1 = str3;
          if (method1 != null) {
            clazz1 = method1.getDeclaringClass().getReflectClass();
            str1 = method1.getName();
          } 
        } 
      } 
      ClassLoader classLoader = clazz1.getClassLoader();
      String str2 = clazz1.getName();
      String str4 = str2.replace('.', '/') + ".class";
      ClassType classType = new ClassType();
      InputStream inputStream = classLoader.getResourceAsStream(str4);
      if (inputStream == null)
        throw new RuntimeException("missing resource " + str4); 
      new ClassFileInput(classType, inputStream);
      paramClassTypeWriter.setClass(classType);
      URL uRL = classLoader.getResource(str4);
      paramClassTypeWriter.print("In class ");
      paramClassTypeWriter.print(str2);
      if (uRL != null) {
        paramClassTypeWriter.print(" at ");
        paramClassTypeWriter.print(uRL);
      } 
      paramClassTypeWriter.println();
      str2 = str1;
      if (str1 == null) {
        String str = methodProc.getName();
        if (str == null) {
          paramClassTypeWriter.println("Anonymous function - unknown method.");
          return;
        } 
        str2 = Compilation.mangleName(str);
      } 
      for (Method method = classType.getMethods(); method != null; method = method.getNext()) {
        if (method.getName().equals(str2))
          paramClassTypeWriter.printMethod(method); 
      } 
      paramClassTypeWriter.flush();
      return;
    } 
  }
  
  public static void disassemble(Procedure paramProcedure, Writer paramWriter) throws Exception {
    disassemble(paramProcedure, new ClassTypeWriter(null, paramWriter, 0));
  }
  
  public static void disassemble$X(Procedure paramProcedure, CallContext paramCallContext) throws Exception {
    Writer writer;
    ConsumerWriter consumerWriter;
    Consumer consumer = paramCallContext.consumer;
    if (consumer instanceof Writer) {
      writer = (Writer)consumer;
    } else {
      consumerWriter = new ConsumerWriter((Consumer)writer);
    } 
    disassemble(paramProcedure, (Writer)consumerWriter);
  }
  
  public static PrimProcedure getMethodFor(ClassType paramClassType, String paramString, Declaration paramDeclaration, Type[] paramArrayOfType, Language paramLanguage) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #16
    //   3: aconst_null
    //   4: astore #15
    //   6: iconst_m1
    //   7: istore #8
    //   9: iconst_0
    //   10: istore #5
    //   12: aload_1
    //   13: ifnonnull -> 18
    //   16: aconst_null
    //   17: areturn
    //   18: aload #16
    //   20: astore #14
    //   22: aload_1
    //   23: invokestatic mangleName : (Ljava/lang/String;)Ljava/lang/String;
    //   26: astore #18
    //   28: aload #16
    //   30: astore #14
    //   32: new java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial <init> : ()V
    //   39: aload #18
    //   41: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: ldc_w '$V'
    //   47: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   50: invokevirtual toString : ()Ljava/lang/String;
    //   53: astore #19
    //   55: aload #16
    //   57: astore #14
    //   59: new java/lang/StringBuilder
    //   62: dup
    //   63: invokespecial <init> : ()V
    //   66: aload #18
    //   68: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: ldc_w '$V$X'
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: invokevirtual toString : ()Ljava/lang/String;
    //   80: astore #20
    //   82: aload #16
    //   84: astore #14
    //   86: new java/lang/StringBuilder
    //   89: dup
    //   90: invokespecial <init> : ()V
    //   93: aload #18
    //   95: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   98: ldc '$X'
    //   100: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   103: invokevirtual toString : ()Ljava/lang/String;
    //   106: astore #21
    //   108: iconst_1
    //   109: istore #6
    //   111: aload #16
    //   113: astore #14
    //   115: aload_0
    //   116: invokevirtual getDeclaredMethods : ()Lgnu/bytecode/Method;
    //   119: astore #17
    //   121: aload #15
    //   123: astore_0
    //   124: aload_0
    //   125: astore #14
    //   127: aload #17
    //   129: ifnull -> 486
    //   132: aload_0
    //   133: astore #14
    //   135: aload #17
    //   137: invokevirtual getModifiers : ()I
    //   140: bipush #9
    //   142: iand
    //   143: bipush #9
    //   145: if_icmpeq -> 221
    //   148: iload #6
    //   150: istore #13
    //   152: aload_0
    //   153: astore #16
    //   155: iload #8
    //   157: istore #9
    //   159: iload #5
    //   161: istore #11
    //   163: aload_2
    //   164: ifnull -> 192
    //   167: aload_0
    //   168: astore #14
    //   170: aload_2
    //   171: getfield base : Lgnu/expr/Declaration;
    //   174: ifnonnull -> 221
    //   177: iload #5
    //   179: istore #11
    //   181: iload #8
    //   183: istore #9
    //   185: aload_0
    //   186: astore #16
    //   188: iload #6
    //   190: istore #13
    //   192: aload #16
    //   194: astore #14
    //   196: aload #17
    //   198: invokevirtual getNext : ()Lgnu/bytecode/Method;
    //   201: astore #17
    //   203: iload #13
    //   205: istore #6
    //   207: aload #16
    //   209: astore_0
    //   210: iload #9
    //   212: istore #8
    //   214: iload #11
    //   216: istore #5
    //   218: goto -> 124
    //   221: aload_0
    //   222: astore #14
    //   224: aload #17
    //   226: invokevirtual getName : ()Ljava/lang/String;
    //   229: astore #15
    //   231: aload_0
    //   232: astore #14
    //   234: aload #15
    //   236: aload #18
    //   238: invokevirtual equals : (Ljava/lang/Object;)Z
    //   241: ifne -> 489
    //   244: aload_0
    //   245: astore #14
    //   247: aload #15
    //   249: aload #19
    //   251: invokevirtual equals : (Ljava/lang/Object;)Z
    //   254: ifne -> 489
    //   257: aload_0
    //   258: astore #14
    //   260: aload #15
    //   262: aload #21
    //   264: invokevirtual equals : (Ljava/lang/Object;)Z
    //   267: ifne -> 489
    //   270: aload_0
    //   271: astore #14
    //   273: aload #15
    //   275: aload #20
    //   277: invokevirtual equals : (Ljava/lang/Object;)Z
    //   280: ifeq -> 379
    //   283: goto -> 489
    //   286: aload #15
    //   288: astore #14
    //   290: new gnu/expr/PrimProcedure
    //   293: dup
    //   294: aload #17
    //   296: aload #4
    //   298: invokespecial <init> : (Lgnu/bytecode/Method;Lgnu/expr/Language;)V
    //   301: astore_0
    //   302: aload #15
    //   304: astore #14
    //   306: aload_0
    //   307: aload_1
    //   308: invokevirtual setName : (Ljava/lang/String;)V
    //   311: aload #15
    //   313: astore #14
    //   315: aload_0
    //   316: aload_3
    //   317: invokevirtual isApplicable : ([Lgnu/bytecode/Type;)I
    //   320: istore #5
    //   322: iload #6
    //   324: istore #13
    //   326: aload #15
    //   328: astore #16
    //   330: iload #10
    //   332: istore #9
    //   334: iload #12
    //   336: istore #11
    //   338: iload #5
    //   340: iflt -> 192
    //   343: iload #6
    //   345: istore #13
    //   347: aload #15
    //   349: astore #16
    //   351: iload #10
    //   353: istore #9
    //   355: iload #12
    //   357: istore #11
    //   359: iload #5
    //   361: iload #10
    //   363: if_icmplt -> 192
    //   366: iload #5
    //   368: iload #10
    //   370: if_icmple -> 445
    //   373: aload_0
    //   374: astore #16
    //   376: goto -> 547
    //   379: iload #6
    //   381: istore #13
    //   383: aload_0
    //   384: astore #16
    //   386: iload #8
    //   388: istore #9
    //   390: iload #5
    //   392: istore #11
    //   394: iload #6
    //   396: ifeq -> 192
    //   399: aload_0
    //   400: astore #14
    //   402: aload #15
    //   404: ldc_w 'apply'
    //   407: invokevirtual equals : (Ljava/lang/Object;)Z
    //   410: ifne -> 562
    //   413: iload #6
    //   415: istore #13
    //   417: aload_0
    //   418: astore #16
    //   420: iload #8
    //   422: istore #9
    //   424: iload #5
    //   426: istore #11
    //   428: aload_0
    //   429: astore #14
    //   431: aload #15
    //   433: ldc_w 'apply$V'
    //   436: invokevirtual equals : (Ljava/lang/Object;)Z
    //   439: ifeq -> 192
    //   442: goto -> 562
    //   445: aload #15
    //   447: astore #16
    //   449: aload #15
    //   451: ifnull -> 547
    //   454: aload #15
    //   456: astore #14
    //   458: aload #15
    //   460: aload_0
    //   461: invokestatic mostSpecific : (Lgnu/mapping/MethodProc;Lgnu/mapping/MethodProc;)Lgnu/mapping/MethodProc;
    //   464: checkcast gnu/expr/PrimProcedure
    //   467: astore_0
    //   468: aload_0
    //   469: astore #16
    //   471: aload_0
    //   472: ifnonnull -> 547
    //   475: aload_0
    //   476: astore #16
    //   478: iload #10
    //   480: ifle -> 547
    //   483: aconst_null
    //   484: areturn
    //   485: astore_0
    //   486: aload #14
    //   488: areturn
    //   489: iconst_0
    //   490: istore #7
    //   492: aload_0
    //   493: astore #15
    //   495: iload #8
    //   497: istore #10
    //   499: iload #5
    //   501: istore #12
    //   503: iload #7
    //   505: ifne -> 286
    //   508: iconst_0
    //   509: istore #9
    //   511: iload #9
    //   513: istore #6
    //   515: aload_0
    //   516: astore #15
    //   518: iload #8
    //   520: istore #10
    //   522: iload #5
    //   524: istore #12
    //   526: iload #5
    //   528: ifeq -> 286
    //   531: aconst_null
    //   532: astore #15
    //   534: iconst_m1
    //   535: istore #10
    //   537: iconst_0
    //   538: istore #12
    //   540: iload #9
    //   542: istore #6
    //   544: goto -> 286
    //   547: iload #5
    //   549: istore #9
    //   551: iload #6
    //   553: istore #13
    //   555: iload #7
    //   557: istore #11
    //   559: goto -> 192
    //   562: iconst_1
    //   563: istore #7
    //   565: goto -> 492
    // Exception table:
    //   from	to	target	type
    //   22	28	485	java/lang/SecurityException
    //   32	55	485	java/lang/SecurityException
    //   59	82	485	java/lang/SecurityException
    //   86	108	485	java/lang/SecurityException
    //   115	121	485	java/lang/SecurityException
    //   135	148	485	java/lang/SecurityException
    //   170	177	485	java/lang/SecurityException
    //   196	203	485	java/lang/SecurityException
    //   224	231	485	java/lang/SecurityException
    //   234	244	485	java/lang/SecurityException
    //   247	257	485	java/lang/SecurityException
    //   260	270	485	java/lang/SecurityException
    //   273	283	485	java/lang/SecurityException
    //   290	302	485	java/lang/SecurityException
    //   306	311	485	java/lang/SecurityException
    //   315	322	485	java/lang/SecurityException
    //   402	413	485	java/lang/SecurityException
    //   431	442	485	java/lang/SecurityException
    //   458	468	485	java/lang/SecurityException
  }
  
  public static PrimProcedure getMethodFor(ClassType paramClassType, String paramString, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage) {
    int i = paramArrayOfExpression.length;
    Type[] arrayOfType = new Type[i];
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = paramArrayOfExpression[i].getType();
        continue;
      } 
      return getMethodFor(paramClassType, paramString, paramDeclaration, arrayOfType, paramLanguage);
    } 
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Declaration paramDeclaration, Type[] paramArrayOfType, Language paramLanguage) {
    // Byte code:
    //   0: aload_0
    //   1: astore #6
    //   3: aload_0
    //   4: instanceof gnu/expr/GenericProc
    //   7: ifeq -> 89
    //   10: aload_0
    //   11: checkcast gnu/expr/GenericProc
    //   14: astore #7
    //   16: aload #7
    //   18: getfield methods : [Lgnu/mapping/MethodProc;
    //   21: astore #6
    //   23: aconst_null
    //   24: astore_0
    //   25: aload #7
    //   27: getfield count : I
    //   30: istore #4
    //   32: iload #4
    //   34: iconst_1
    //   35: isub
    //   36: istore #5
    //   38: iload #5
    //   40: iflt -> 80
    //   43: iload #5
    //   45: istore #4
    //   47: aload #6
    //   49: iload #5
    //   51: aaload
    //   52: aload_2
    //   53: invokevirtual isApplicable : ([Lgnu/bytecode/Type;)I
    //   56: iflt -> 32
    //   59: aload_0
    //   60: ifnull -> 67
    //   63: aconst_null
    //   64: astore_0
    //   65: aload_0
    //   66: areturn
    //   67: aload #6
    //   69: iload #5
    //   71: aaload
    //   72: astore_0
    //   73: iload #5
    //   75: istore #4
    //   77: goto -> 32
    //   80: aload_0
    //   81: astore #6
    //   83: aload_0
    //   84: ifnonnull -> 89
    //   87: aconst_null
    //   88: areturn
    //   89: aload #6
    //   91: instanceof gnu/expr/PrimProcedure
    //   94: ifeq -> 116
    //   97: aload #6
    //   99: checkcast gnu/expr/PrimProcedure
    //   102: astore #7
    //   104: aload #7
    //   106: astore_0
    //   107: aload #7
    //   109: aload_2
    //   110: invokevirtual isApplicable : ([Lgnu/bytecode/Type;)I
    //   113: ifge -> 65
    //   116: aload #6
    //   118: invokestatic getProcedureClass : (Ljava/lang/Object;)Ljava/lang/Class;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnonnull -> 128
    //   126: aconst_null
    //   127: areturn
    //   128: aload_0
    //   129: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   132: checkcast gnu/bytecode/ClassType
    //   135: aload #6
    //   137: invokevirtual getName : ()Ljava/lang/String;
    //   140: aload_1
    //   141: aload_2
    //   142: aload_3
    //   143: invokestatic getMethodFor : (Lgnu/bytecode/ClassType;Ljava/lang/String;Lgnu/expr/Declaration;[Lgnu/bytecode/Type;Lgnu/expr/Language;)Lgnu/expr/PrimProcedure;
    //   146: areturn
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage) {
    int i = paramArrayOfExpression.length;
    Type[] arrayOfType = new Type[i];
    while (true) {
      if (--i >= 0) {
        arrayOfType[i] = paramArrayOfExpression[i].getType();
        continue;
      } 
      return getMethodFor(paramProcedure, paramDeclaration, arrayOfType, paramLanguage);
    } 
  }
  
  public static PrimProcedure getMethodFor(Procedure paramProcedure, Expression[] paramArrayOfExpression) {
    return getMethodFor(paramProcedure, (Declaration)null, paramArrayOfExpression, Language.getDefaultLanguage());
  }
  
  public static PrimProcedure getMethodFor(Class paramClass, String paramString, Declaration paramDeclaration, Expression[] paramArrayOfExpression, Language paramLanguage) {
    return getMethodFor((ClassType)Type.make(paramClass), paramString, paramDeclaration, paramArrayOfExpression, paramLanguage);
  }
  
  public static Class getProcedureClass(Object<?> paramObject) {
    if (paramObject instanceof ModuleMethod) {
      paramObject = (Object<?>)((ModuleMethod)paramObject).module.getClass();
    } else {
      paramObject = (Object<?>)paramObject.getClass();
    } 
    try {
      ClassLoader classLoader1 = paramObject.getClassLoader();
      ClassLoader classLoader2 = systemClassLoader;
      if (classLoader1 == classLoader2)
        return (Class)paramObject; 
    } catch (SecurityException securityException) {}
    return null;
  }
  
  private void init(Method paramMethod) {
    this.method = paramMethod;
    if ((paramMethod.getModifiers() & 0x8) != 0) {
      this.op_code = 184;
    } else {
      ClassType classType = paramMethod.getDeclaringClass();
      if (this.mode == 'P') {
        this.op_code = 183;
      } else {
        this.mode = 'V';
        if ("<init>".equals(paramMethod.getName())) {
          this.op_code = 183;
        } else if ((classType.getModifiers() & 0x200) != 0) {
          this.op_code = 185;
        } else {
          this.op_code = 182;
        } 
      } 
    } 
    Type[] arrayOfType2 = paramMethod.getParameterTypes();
    Type[] arrayOfType1 = arrayOfType2;
    if (isConstructor()) {
      arrayOfType1 = arrayOfType2;
      if (paramMethod.getDeclaringClass().hasOuterLink()) {
        int i = arrayOfType2.length - 1;
        arrayOfType1 = new Type[i];
        System.arraycopy(arrayOfType2, 1, arrayOfType1, 0, i);
      } 
    } 
    this.argTypes = arrayOfType1;
  }
  
  public static PrimProcedure makeBuiltinBinary(int paramInt, Type paramType) {
    return new PrimProcedure(paramInt, paramType, new Type[] { paramType, paramType });
  }
  
  public static PrimProcedure makeBuiltinUnary(int paramInt, Type paramType) {
    return new PrimProcedure(paramInt, paramType, new Type[] { paramType });
  }
  
  public static boolean takesContext(Method paramMethod) {
    return paramMethod.getName().endsWith("$X");
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: getfield argTypes : [Lgnu/bytecode/Type;
    //   4: arraylength
    //   5: istore #4
    //   7: aload_0
    //   8: invokevirtual isConstructor : ()Z
    //   11: istore #5
    //   13: iload #5
    //   15: ifeq -> 102
    //   18: aload_0
    //   19: getfield method : Lgnu/bytecode/Method;
    //   22: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   25: invokevirtual hasOuterLink : ()Z
    //   28: ifeq -> 102
    //   31: iconst_1
    //   32: istore_2
    //   33: aload_0
    //   34: getfield member : Ljava/lang/reflect/Member;
    //   37: ifnonnull -> 154
    //   40: aload_0
    //   41: getfield method : Lgnu/bytecode/Method;
    //   44: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   47: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   50: astore #6
    //   52: iload_2
    //   53: ifeq -> 107
    //   56: iconst_1
    //   57: istore_3
    //   58: iload_3
    //   59: iload #4
    //   61: iadd
    //   62: anewarray java/lang/Class
    //   65: astore #7
    //   67: iload #4
    //   69: istore_3
    //   70: goto -> 358
    //   73: aload #7
    //   75: iload_3
    //   76: iload #4
    //   78: iadd
    //   79: aload_0
    //   80: getfield argTypes : [Lgnu/bytecode/Type;
    //   83: iload #4
    //   85: aaload
    //   86: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   89: aastore
    //   90: iload #4
    //   92: istore_3
    //   93: goto -> 358
    //   96: astore_1
    //   97: aload_1
    //   98: invokevirtual getTargetException : ()Ljava/lang/Throwable;
    //   101: athrow
    //   102: iconst_0
    //   103: istore_2
    //   104: goto -> 33
    //   107: iconst_0
    //   108: istore_3
    //   109: goto -> 58
    //   112: iconst_0
    //   113: istore_3
    //   114: goto -> 73
    //   117: iload_2
    //   118: ifeq -> 138
    //   121: aload #7
    //   123: iconst_0
    //   124: aload_0
    //   125: getfield method : Lgnu/bytecode/Method;
    //   128: invokevirtual getDeclaringClass : ()Lgnu/bytecode/ClassType;
    //   131: invokevirtual getOuterLinkType : ()Lgnu/bytecode/ClassType;
    //   134: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   137: aastore
    //   138: iload #5
    //   140: ifeq -> 244
    //   143: aload_0
    //   144: aload #6
    //   146: aload #7
    //   148: invokevirtual getConstructor : ([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   151: putfield member : Ljava/lang/reflect/Member;
    //   154: iload #5
    //   156: ifeq -> 275
    //   159: aload_1
    //   160: getfield values : [Ljava/lang/Object;
    //   163: astore #7
    //   165: aload #7
    //   167: astore #6
    //   169: iload_2
    //   170: ifeq -> 211
    //   173: aload #7
    //   175: arraylength
    //   176: iconst_1
    //   177: iadd
    //   178: istore_2
    //   179: iload_2
    //   180: anewarray java/lang/Object
    //   183: astore #6
    //   185: aload #7
    //   187: iconst_0
    //   188: aload #6
    //   190: iconst_1
    //   191: iload_2
    //   192: iconst_1
    //   193: isub
    //   194: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   197: aload #6
    //   199: iconst_0
    //   200: aload_1
    //   201: getfield value1 : Ljava/lang/Object;
    //   204: checkcast gnu/expr/PairClassType
    //   207: getfield staticLink : Ljava/lang/Object;
    //   210: aastore
    //   211: aload_0
    //   212: getfield member : Ljava/lang/reflect/Member;
    //   215: checkcast java/lang/reflect/Constructor
    //   218: aload #6
    //   220: invokevirtual newInstance : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   223: astore #6
    //   225: aload_0
    //   226: invokevirtual takesContext : ()Z
    //   229: ifne -> 377
    //   232: aload_1
    //   233: getfield consumer : Lgnu/lists/Consumer;
    //   236: aload #6
    //   238: invokeinterface writeObject : (Ljava/lang/Object;)V
    //   243: return
    //   244: aload_0
    //   245: getfield method : Lgnu/bytecode/Method;
    //   248: getstatic gnu/bytecode/Type.clone_method : Lgnu/bytecode/Method;
    //   251: if_acmpeq -> 154
    //   254: aload_0
    //   255: aload #6
    //   257: aload_0
    //   258: getfield method : Lgnu/bytecode/Method;
    //   261: invokevirtual getName : ()Ljava/lang/String;
    //   264: aload #7
    //   266: invokevirtual getMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   269: putfield member : Ljava/lang/reflect/Member;
    //   272: goto -> 154
    //   275: aload_0
    //   276: getfield method : Lgnu/bytecode/Method;
    //   279: getstatic gnu/bytecode/Type.clone_method : Lgnu/bytecode/Method;
    //   282: if_acmpne -> 328
    //   285: aload_1
    //   286: getfield value1 : Ljava/lang/Object;
    //   289: astore #7
    //   291: aload #7
    //   293: invokevirtual getClass : ()Ljava/lang/Class;
    //   296: invokevirtual getComponentType : ()Ljava/lang/Class;
    //   299: astore #6
    //   301: aload #7
    //   303: invokestatic getLength : (Ljava/lang/Object;)I
    //   306: istore_2
    //   307: aload #6
    //   309: iload_2
    //   310: invokestatic newInstance : (Ljava/lang/Class;I)Ljava/lang/Object;
    //   313: astore #6
    //   315: aload #7
    //   317: iconst_0
    //   318: aload #6
    //   320: iconst_0
    //   321: iload_2
    //   322: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   325: goto -> 225
    //   328: aload_0
    //   329: getfield retType : Lgnu/bytecode/Type;
    //   332: aload_0
    //   333: getfield member : Ljava/lang/reflect/Member;
    //   336: checkcast java/lang/reflect/Method
    //   339: aload_1
    //   340: getfield value1 : Ljava/lang/Object;
    //   343: aload_1
    //   344: getfield values : [Ljava/lang/Object;
    //   347: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   350: invokevirtual coerceToObject : (Ljava/lang/Object;)Ljava/lang/Object;
    //   353: astore #6
    //   355: goto -> 225
    //   358: iload_3
    //   359: iconst_1
    //   360: isub
    //   361: istore #4
    //   363: iload #4
    //   365: iflt -> 117
    //   368: iload_2
    //   369: ifeq -> 112
    //   372: iconst_1
    //   373: istore_3
    //   374: goto -> 73
    //   377: return
    // Exception table:
    //   from	to	target	type
    //   33	52	96	java/lang/reflect/InvocationTargetException
    //   58	67	96	java/lang/reflect/InvocationTargetException
    //   73	90	96	java/lang/reflect/InvocationTargetException
    //   121	138	96	java/lang/reflect/InvocationTargetException
    //   143	154	96	java/lang/reflect/InvocationTargetException
    //   159	165	96	java/lang/reflect/InvocationTargetException
    //   173	211	96	java/lang/reflect/InvocationTargetException
    //   211	225	96	java/lang/reflect/InvocationTargetException
    //   225	243	96	java/lang/reflect/InvocationTargetException
    //   244	272	96	java/lang/reflect/InvocationTargetException
    //   275	325	96	java/lang/reflect/InvocationTargetException
    //   328	355	96	java/lang/reflect/InvocationTargetException
  }
  
  void compile(Type paramType, ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ClassType classType1;
    boolean bool1;
    ClassType classType2;
    ClassType classType3 = null;
    Type type1 = null;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    CodeAttr codeAttr = paramCompilation.getCode();
    Type type2 = this.retType;
    boolean bool2 = false;
    if (isConstructor()) {
      if (this.method == null) {
        paramType = type1;
      } else {
        classType1 = this.method.getDeclaringClass();
      } 
      if (classType1.hasOuterLink())
        ClassExp.loadSuperStaticLink(arrayOfExpression[0], classType1, paramCompilation); 
      type1 = null;
      bool1 = true;
    } else if (opcode() == 183 && this.mode == 'P' && "<init>".equals(this.method.getName())) {
      if (this.method != null)
        classType3 = this.method.getDeclaringClass(); 
      bool1 = bool2;
      classType2 = classType1;
      if (classType3.hasOuterLink()) {
        codeAttr.emitPushThis();
        codeAttr.emitLoad(codeAttr.getCurrentScope().getVariable(1));
        classType2 = null;
        bool1 = true;
      } 
    } else {
      bool1 = bool2;
      classType2 = classType1;
      if (takesTarget()) {
        bool1 = bool2;
        classType2 = classType1;
        if (this.method.getStaticFlag()) {
          bool1 = true;
          classType2 = classType1;
        } 
      } 
    } 
    compileArgs(arrayOfExpression, bool1, (Type)classType2, paramCompilation);
    if (this.method == null) {
      codeAttr.emitPrimop(opcode(), arrayOfExpression.length, this.retType);
      paramTarget.compileFromStack(paramCompilation, type2);
      return;
    } 
    compileInvoke(paramCompilation, this.method, paramTarget, paramApplyExp.isTailCall(), this.op_code, type2);
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Type type;
    CodeAttr codeAttr = paramCompilation.getCode();
    if (this.method == null) {
      type = null;
    } else {
      type = (Type)this.method.getDeclaringClass();
    } 
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (isConstructor()) {
      Declaration declaration;
      if (paramApplyExp.getFlag(8)) {
        int j = arrayOfExpression.length;
        paramCompilation.letStart();
        type = (Type)new Expression[j];
        type[0] = arrayOfExpression[0];
        int i;
        for (i = 1; i < j; i++) {
          Expression expression = arrayOfExpression[i];
          declaration = paramCompilation.letVariable(null, expression.getType(), expression);
          declaration.setCanRead(true);
          type[i] = new ReferenceExp(declaration);
        } 
        paramCompilation.letEnter();
        paramCompilation.letDone(new ApplyExp(paramApplyExp.func, (Expression[])type)).compile(paramCompilation, paramTarget);
        return;
      } 
      declaration.emitNew((ClassType)type);
      declaration.emitDup(type);
    } 
    String str = WrongArguments.checkArgCount((Procedure)this, arrayOfExpression.length);
    if (str != null)
      paramCompilation.error('e', str); 
    if (getStaticFlag())
      type = null; 
    compile(type, paramApplyExp, paramCompilation, paramTarget);
  }
  
  public Method getMethod() {
    return this.method;
  }
  
  public String getName() {
    String str = super.getName();
    if (str != null)
      return str; 
    str = getVerboseName();
    setName(str);
    return str;
  }
  
  public Type getParameterType(int paramInt) {
    int i = paramInt;
    if (takesTarget()) {
      if (paramInt == 0)
        return (Type)(isConstructor() ? Type.objectType : this.method.getDeclaringClass()); 
      i = paramInt - 1;
    } 
    paramInt = this.argTypes.length;
    if (i < paramInt - 1)
      return this.argTypes[i]; 
    boolean bool = takesVarArgs();
    if (i < paramInt && !bool)
      return this.argTypes[i]; 
    Type type = this.argTypes[paramInt - 1];
    return (Type)((type instanceof ArrayType) ? ((ArrayType)type).getComponentType() : Type.objectType);
  }
  
  public final Type[] getParameterTypes() {
    return this.argTypes;
  }
  
  public Type getReturnType() {
    return this.retType;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return this.retType;
  }
  
  public final boolean getStaticFlag() {
    return (this.method == null || this.method.getStaticFlag() || isConstructor());
  }
  
  public String getVerboseName() {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (this.method == null) {
      stringBuffer.append("<op ");
      stringBuffer.append(this.op_code);
      stringBuffer.append('>');
    } else {
      stringBuffer.append(this.method.getDeclaringClass().getName());
      stringBuffer.append('.');
      stringBuffer.append(this.method.getName());
    } 
    stringBuffer.append('(');
    for (int i = 0; i < this.argTypes.length; i++) {
      if (i > 0)
        stringBuffer.append(','); 
      stringBuffer.append(this.argTypes[i].getName());
    } 
    stringBuffer.append(')');
    return stringBuffer.toString();
  }
  
  public int isApplicable(Type[] paramArrayOfType) {
    int j = super.isApplicable(paramArrayOfType);
    int k = paramArrayOfType.length;
    int i = j;
    if (j == -1) {
      i = j;
      if (this.method != null) {
        i = j;
        if ((this.method.getModifiers() & 0x80) != 0) {
          i = j;
          if (k > 0) {
            i = j;
            if (paramArrayOfType[k - 1] instanceof ArrayType) {
              Type[] arrayOfType = new Type[k];
              System.arraycopy(paramArrayOfType, 0, arrayOfType, 0, k - 1);
              arrayOfType[k - 1] = ((ArrayType)paramArrayOfType[k - 1]).getComponentType();
              i = super.isApplicable(arrayOfType);
            } 
          } 
        } 
      } 
    } 
    return i;
  }
  
  public final boolean isConstructor() {
    return (opcode() == 183 && this.mode != 'P');
  }
  
  public boolean isSideEffectFree() {
    return this.sideEffectFree;
  }
  
  public boolean isSpecial() {
    return (this.mode == 'P');
  }
  
  public int match0(CallContext paramCallContext) {
    return matchN(ProcedureN.noArgs, paramCallContext);
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    return matchN(new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    boolean bool;
    int m = paramArrayOfObject.length;
    boolean bool1 = takesVarArgs();
    int k = minArgs();
    if (m < k)
      return 0xFFF10000 | k; 
    if (!bool1 && m > k)
      return 0xFFF20000 | k; 
    int j = this.argTypes.length;
    Type type = null;
    Object[] arrayOfObject1 = null;
    if (takesTarget() || isConstructor()) {
      bool = true;
    } else {
      bool = false;
    } 
    boolean bool2 = takesContext();
    Object[] arrayOfObject3 = new Object[j];
    int i = j;
    if (bool2) {
      i = j - 1;
      arrayOfObject3[i] = paramCallContext;
    } 
    Object[] arrayOfObject2 = arrayOfObject1;
    if (bool1) {
      ClassType classType;
      type = this.argTypes[i - 1];
      if (type == Compilation.scmListType || type == LangObjType.listType) {
        arrayOfObject3[i - 1] = LList.makeList(paramArrayOfObject, k);
        classType = Type.objectType;
        arrayOfObject2 = arrayOfObject1;
      } else {
        Type type1 = ((ArrayType)classType).getComponentType();
        arrayOfObject2 = (Object[])Array.newInstance(type1.getReflectClass(), m - k);
        arrayOfObject3[i - 1] = arrayOfObject2;
      } 
    } 
    if (isConstructor()) {
      Object object = paramArrayOfObject[0];
    } else {
      if (bool) {
        try {
          Object object = this.method.getDeclaringClass().coerceFromObject(paramArrayOfObject[0]);
          i = bool;
        } catch (ClassCastException classCastException) {
          return -786431;
        } 
      } else {
        arrayOfObject1 = null;
        i = bool;
      } 
      paramCallContext.value1 = arrayOfObject1;
      paramCallContext.values = arrayOfObject3;
      paramCallContext.proc = (Procedure)this;
      return 0;
    } 
    i = bool;
  }
  
  public int numArgs() {
    int j = this.argTypes.length;
    int i = j;
    if (takesTarget())
      i = j + 1; 
    j = i;
    if (takesContext())
      j = i - 1; 
    return takesVarArgs() ? (j - 1 - 4096) : ((j << 12) + j);
  }
  
  public final int opcode() {
    return this.op_code;
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print("#<primitive procedure ");
    paramPrintWriter.print(toString());
    paramPrintWriter.print('>');
  }
  
  public void setReturnType(Type paramType) {
    this.retType = paramType;
  }
  
  public void setSideEffectFree() {
    this.sideEffectFree = true;
  }
  
  public boolean takesContext() {
    return (this.method != null && takesContext(this.method));
  }
  
  public boolean takesTarget() {
    return (this.mode != '\000');
  }
  
  public boolean takesVarArgs() {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.method != null) {
      if ((this.method.getModifiers() & 0x80) != 0)
        return true; 
    } else {
      return bool1;
    } 
    String str = this.method.getName();
    if (!str.endsWith("$V")) {
      bool1 = bool2;
      return str.endsWith("$V$X") ? true : bool1;
    } 
    return true;
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer(100);
    if (this.retType == null) {
      String str1 = "<unknown>";
      stringBuffer.append(str1);
      stringBuffer.append(' ');
      stringBuffer.append(getVerboseName());
      return stringBuffer.toString();
    } 
    String str = this.retType.getName();
    stringBuffer.append(str);
    stringBuffer.append(' ');
    stringBuffer.append(getVerboseName());
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/PrimProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */