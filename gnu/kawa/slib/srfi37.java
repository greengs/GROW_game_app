package gnu.kawa.slib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Apply;
import gnu.kawa.functions.IsEqual;
import gnu.lists.Consumer;
import gnu.lists.LList;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.Char;
import kawa.lib.lists;
import kawa.lib.misc;
import kawa.lib.strings;
import kawa.standard.Scheme;
import kawa.standard.call_with_values;

public class srfi37 extends ModuleBody {
  public static final srfi37 $instance;
  
  static final IntNum Lit0;
  
  static final Char Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11 = (SimpleSymbol)(new SimpleSymbol("args-fold")).readResolve();
  
  static final Char Lit2;
  
  static final IntNum Lit3;
  
  static final IntNum Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod args$Mnfold;
  
  public static final ModuleMethod option;
  
  public static final ModuleMethod option$Mnnames;
  
  public static final ModuleMethod option$Mnoptional$Mnarg$Qu;
  
  public static final ModuleMethod option$Mnprocessor;
  
  public static final ModuleMethod option$Mnrequired$Mnarg$Qu;
  
  static final Class option$Mntype;
  
  public static final ModuleMethod option$Qu;
  
  static {
    Lit10 = (SimpleSymbol)(new SimpleSymbol("option-processor")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("option-optional-arg?")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("option-required-arg?")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("option-names")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("option")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("option?")).readResolve();
    Lit4 = IntNum.make(0);
    Lit3 = IntNum.make(3);
    Lit2 = Char.make(61);
    Lit1 = Char.make(45);
    Lit0 = IntNum.make(1);
    $instance = new srfi37();
    option$Mntype = Mntype.class;
    srfi37 srfi371 = $instance;
    option$Qu = new ModuleMethod(srfi371, 25, Lit5, 4097);
    option = new ModuleMethod(srfi371, 26, Lit6, 16388);
    option$Mnnames = new ModuleMethod(srfi371, 27, Lit7, 4097);
    option$Mnrequired$Mnarg$Qu = new ModuleMethod(srfi371, 28, Lit8, 4097);
    option$Mnoptional$Mnarg$Qu = new ModuleMethod(srfi371, 29, Lit9, 4097);
    option$Mnprocessor = new ModuleMethod(srfi371, 30, Lit10, 4097);
    args$Mnfold = new ModuleMethod(srfi371, 31, Lit11, -4092);
    $instance.run();
  }
  
  public srfi37() {
    ModuleInfo.register(this);
  }
  
  public static Object argsFold$V(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, Object[] paramArrayOfObject) {
    frame frame = new frame();
    frame.options = paramObject2;
    frame.unrecognized$Mnoption$Mnproc = paramObject3;
    frame.operand$Mnproc = paramObject4;
    return frame.lambda5scanArgs(paramObject1, LList.makeList(paramArrayOfObject, 0));
  }
  
  public static boolean isOption(Object paramObject) {
    return paramObject instanceof Mntype;
  }
  
  public static Object isOptionOptionalArg(Mntype paramMntype) {
    return paramMntype.optional$Mnarg$Qu;
  }
  
  public static Object isOptionRequiredArg(Mntype paramMntype) {
    return paramMntype.required$Mnarg$Qu;
  }
  
  public static Mntype option(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    Mntype mntype = new Mntype();
    mntype.names = paramObject1;
    mntype.required$Mnarg$Qu = paramObject2;
    mntype.optional$Mnarg$Qu = paramObject3;
    mntype.processor = paramObject4;
    return mntype;
  }
  
  public static Object optionNames(Mntype paramMntype) {
    return paramMntype.names;
  }
  
  public static Object optionProcessor(Mntype paramMntype) {
    return paramMntype.processor;
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 25:
        return isOption(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 27:
        try {
          Mntype mntype = (Mntype)paramObject;
          return optionNames(mntype);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-names", 1, paramObject);
        } 
      case 28:
        try {
          Mntype mntype = (Mntype)paramObject;
          return isOptionRequiredArg(mntype);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-required-arg?", 1, paramObject);
        } 
      case 29:
        try {
          Mntype mntype = (Mntype)paramObject;
          return isOptionOptionalArg(mntype);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-optional-arg?", 1, paramObject);
        } 
      case 30:
        break;
    } 
    try {
      Mntype mntype = (Mntype)paramObject;
      return optionProcessor(mntype);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "option-processor", 1, paramObject);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    return (paramModuleMethod.selector == 26) ? option(paramObject1, paramObject2, paramObject3, paramObject4) : super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    Object object;
    if (paramModuleMethod.selector == 31) {
      object = paramArrayOfObject[0];
      Object object1 = paramArrayOfObject[1];
      Object object2 = paramArrayOfObject[2];
      Object object3 = paramArrayOfObject[3];
      int i = paramArrayOfObject.length - 4;
      Object[] arrayOfObject = new Object[i];
      while (true) {
        if (--i < 0)
          return argsFold$V(object, object1, object2, object3, arrayOfObject); 
        arrayOfObject[i] = paramArrayOfObject[i + 4];
      } 
    } 
    return super.applyN((ModuleMethod)object, paramArrayOfObject);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 30:
        if (paramObject instanceof Mntype) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 29:
        if (paramObject instanceof Mntype) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 28:
        if (paramObject instanceof Mntype) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 27:
        if (paramObject instanceof Mntype) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 25:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 26) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 4;
      return 0;
    } 
    return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 31) {
      paramCallContext.values = paramArrayOfObject;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 5;
      return 0;
    } 
    return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
  
  public class Mntype {
    public Object names;
    
    public Object optional$Mnarg$Qu;
    
    public Object processor;
    
    public Object required$Mnarg$Qu;
  }
  
  public class frame extends ModuleBody {
    Object operand$Mnproc;
    
    Object options;
    
    Object unrecognized$Mnoption$Mnproc;
    
    public static Object lambda1find(Object param1Object1, Object param1Object2) {
      return lists.isNull(param1Object1) ? Boolean.FALSE : ((Scheme.applyToArgs.apply2(param1Object2, lists.car.apply1(param1Object1)) != Boolean.FALSE) ? lists.car.apply1(param1Object1) : lambda1find(lists.cdr.apply1(param1Object1), param1Object2));
    }
    
    public Object lambda2findOption(Object param1Object) {
      frame0 frame0 = new frame0();
      frame0.staticLink = this;
      frame0.name = param1Object;
      return lambda1find(this.options, frame0.lambda$Fn1);
    }
    
    public Object lambda3scanShortOptions(Object param1Object1, Object param1Object2, Object param1Object3, Object param1Object4) {
      // Byte code:
      //   0: new gnu/kawa/slib/srfi37$frame1
      //   3: dup
      //   4: invokespecial <init> : ()V
      //   7: astore #7
      //   9: aload #7
      //   11: aload_0
      //   12: putfield staticLink : Lgnu/kawa/slib/srfi37$frame;
      //   15: aload #7
      //   17: aload_1
      //   18: putfield index : Ljava/lang/Object;
      //   21: aload #7
      //   23: aload_2
      //   24: putfield shorts : Ljava/lang/Object;
      //   27: aload #7
      //   29: aload_3
      //   30: putfield args : Ljava/lang/Object;
      //   33: aload #7
      //   35: aload #4
      //   37: putfield seeds : Ljava/lang/Object;
      //   40: getstatic kawa/standard/Scheme.numEqu : Lgnu/kawa/functions/NumberCompare;
      //   43: astore_2
      //   44: aload #7
      //   46: getfield index : Ljava/lang/Object;
      //   49: astore_3
      //   50: aload #7
      //   52: getfield shorts : Ljava/lang/Object;
      //   55: astore_1
      //   56: aload_1
      //   57: checkcast java/lang/CharSequence
      //   60: astore #4
      //   62: aload_2
      //   63: aload_3
      //   64: aload #4
      //   66: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   69: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   72: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   75: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   78: if_acmpeq -> 96
      //   81: aload_0
      //   82: aload #7
      //   84: getfield args : Ljava/lang/Object;
      //   87: aload #7
      //   89: getfield seeds : Ljava/lang/Object;
      //   92: invokevirtual lambda5scanArgs : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   95: areturn
      //   96: aload #7
      //   98: getfield shorts : Ljava/lang/Object;
      //   101: astore_1
      //   102: aload_1
      //   103: checkcast java/lang/CharSequence
      //   106: astore_2
      //   107: aload #7
      //   109: getfield index : Ljava/lang/Object;
      //   112: astore_1
      //   113: aload_1
      //   114: checkcast java/lang/Number
      //   117: invokevirtual intValue : ()I
      //   120: istore #5
      //   122: aload #7
      //   124: aload_2
      //   125: iload #5
      //   127: invokestatic stringRef : (Ljava/lang/CharSequence;I)C
      //   130: putfield name : C
      //   133: aload_0
      //   134: aload #7
      //   136: getfield name : C
      //   139: invokestatic make : (I)Lgnu/text/Char;
      //   142: invokevirtual lambda2findOption : (Ljava/lang/Object;)Ljava/lang/Object;
      //   145: astore_1
      //   146: aload_1
      //   147: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   150: if_acmpeq -> 262
      //   153: aload #7
      //   155: aload_1
      //   156: putfield option : Ljava/lang/Object;
      //   159: getstatic kawa/standard/Scheme.numLss : Lgnu/kawa/functions/NumberCompare;
      //   162: astore_2
      //   163: getstatic gnu/kawa/functions/AddOp.$Pl : Lgnu/kawa/functions/AddOp;
      //   166: aload #7
      //   168: getfield index : Ljava/lang/Object;
      //   171: getstatic gnu/kawa/slib/srfi37.Lit0 : Lgnu/math/IntNum;
      //   174: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   177: astore_3
      //   178: aload #7
      //   180: getfield shorts : Ljava/lang/Object;
      //   183: astore_1
      //   184: aload_1
      //   185: checkcast java/lang/CharSequence
      //   188: astore #4
      //   190: aload_2
      //   191: aload_3
      //   192: aload #4
      //   194: invokestatic stringLength : (Ljava/lang/CharSequence;)I
      //   197: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   200: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
      //   203: astore_1
      //   204: aload_1
      //   205: checkcast java/lang/Boolean
      //   208: invokevirtual booleanValue : ()Z
      //   211: istore #6
      //   213: iload #6
      //   215: ifeq -> 359
      //   218: aload #7
      //   220: getfield option : Ljava/lang/Object;
      //   223: astore_1
      //   224: aload_1
      //   225: checkcast gnu/kawa/slib/option$Mntype
      //   228: astore_2
      //   229: aload_2
      //   230: invokestatic isOptionRequiredArg : (Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   233: astore_1
      //   234: aload_1
      //   235: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   238: if_acmpeq -> 290
      //   241: aload_1
      //   242: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   245: if_acmpeq -> 311
      //   248: aload #7
      //   250: getfield lambda$Fn3 : Lgnu/expr/ModuleMethod;
      //   253: aload #7
      //   255: getfield lambda$Fn4 : Lgnu/expr/ModuleMethod;
      //   258: invokestatic callWithValues : (Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   261: areturn
      //   262: aload #7
      //   264: getfield name : C
      //   267: invokestatic make : (I)Lgnu/text/Char;
      //   270: invokestatic list1 : (Ljava/lang/Object;)Lgnu/lists/Pair;
      //   273: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   276: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   279: aload_0
      //   280: getfield unrecognized$Mnoption$Mnproc : Ljava/lang/Object;
      //   283: invokestatic option : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lgnu/kawa/slib/option$Mntype;
      //   286: astore_1
      //   287: goto -> 153
      //   290: aload #7
      //   292: getfield option : Ljava/lang/Object;
      //   295: astore_1
      //   296: aload_1
      //   297: checkcast gnu/kawa/slib/option$Mntype
      //   300: astore_2
      //   301: aload_2
      //   302: invokestatic isOptionOptionalArg : (Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   305: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   308: if_acmpne -> 248
      //   311: aload #7
      //   313: getfield option : Ljava/lang/Object;
      //   316: astore_1
      //   317: aload_1
      //   318: checkcast gnu/kawa/slib/option$Mntype
      //   321: astore_2
      //   322: aload_2
      //   323: invokestatic isOptionRequiredArg : (Lgnu/kawa/slib/option$Mntype;)Ljava/lang/Object;
      //   326: astore_1
      //   327: aload_1
      //   328: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   331: if_acmpeq -> 367
      //   334: aload #7
      //   336: getfield args : Ljava/lang/Object;
      //   339: invokestatic isPair : (Ljava/lang/Object;)Z
      //   342: ifeq -> 374
      //   345: aload #7
      //   347: getfield lambda$Fn5 : Lgnu/expr/ModuleMethod;
      //   350: aload #7
      //   352: getfield lambda$Fn6 : Lgnu/expr/ModuleMethod;
      //   355: invokestatic callWithValues : (Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   358: areturn
      //   359: iload #6
      //   361: ifeq -> 311
      //   364: goto -> 248
      //   367: aload_1
      //   368: getstatic java/lang/Boolean.FALSE : Ljava/lang/Boolean;
      //   371: if_acmpne -> 345
      //   374: aload #7
      //   376: getfield lambda$Fn7 : Lgnu/expr/ModuleMethod;
      //   379: aload #7
      //   381: getfield lambda$Fn8 : Lgnu/expr/ModuleMethod;
      //   384: invokestatic callWithValues : (Lgnu/mapping/Procedure;Lgnu/mapping/Procedure;)Ljava/lang/Object;
      //   387: areturn
      //   388: astore_2
      //   389: new gnu/mapping/WrongType
      //   392: dup
      //   393: aload_2
      //   394: ldc 'string-length'
      //   396: iconst_1
      //   397: aload_1
      //   398: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   401: athrow
      //   402: astore_2
      //   403: new gnu/mapping/WrongType
      //   406: dup
      //   407: aload_2
      //   408: ldc 'string-ref'
      //   410: iconst_1
      //   411: aload_1
      //   412: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   415: athrow
      //   416: astore_2
      //   417: new gnu/mapping/WrongType
      //   420: dup
      //   421: aload_2
      //   422: ldc 'string-ref'
      //   424: iconst_2
      //   425: aload_1
      //   426: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   429: athrow
      //   430: astore_2
      //   431: new gnu/mapping/WrongType
      //   434: dup
      //   435: aload_2
      //   436: ldc 'string-length'
      //   438: iconst_1
      //   439: aload_1
      //   440: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   443: athrow
      //   444: astore_2
      //   445: new gnu/mapping/WrongType
      //   448: dup
      //   449: aload_2
      //   450: ldc 'x'
      //   452: bipush #-2
      //   454: aload_1
      //   455: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   458: athrow
      //   459: astore_2
      //   460: new gnu/mapping/WrongType
      //   463: dup
      //   464: aload_2
      //   465: ldc 'option-required-arg?'
      //   467: iconst_0
      //   468: aload_1
      //   469: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   472: athrow
      //   473: astore_2
      //   474: new gnu/mapping/WrongType
      //   477: dup
      //   478: aload_2
      //   479: ldc 'option-optional-arg?'
      //   481: iconst_0
      //   482: aload_1
      //   483: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   486: athrow
      //   487: astore_2
      //   488: new gnu/mapping/WrongType
      //   491: dup
      //   492: aload_2
      //   493: ldc 'option-required-arg?'
      //   495: iconst_0
      //   496: aload_1
      //   497: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
      //   500: athrow
      // Exception table:
      //   from	to	target	type
      //   56	62	388	java/lang/ClassCastException
      //   102	107	402	java/lang/ClassCastException
      //   113	122	416	java/lang/ClassCastException
      //   184	190	430	java/lang/ClassCastException
      //   204	213	444	java/lang/ClassCastException
      //   224	229	459	java/lang/ClassCastException
      //   296	301	473	java/lang/ClassCastException
      //   317	322	487	java/lang/ClassCastException
    }
    
    public Object lambda4scanOperands(Object param1Object1, Object param1Object2) {
      frame2 frame2 = new frame2();
      frame2.staticLink = this;
      frame2.operands = param1Object1;
      frame2.seeds = param1Object2;
      return lists.isNull(frame2.operands) ? Scheme.apply.apply2(misc.values, frame2.seeds) : call_with_values.callWithValues((Procedure)frame2.lambda$Fn9, (Procedure)frame2.lambda$Fn10);
    }
    
    public Object lambda5scanArgs(Object param1Object1, Object param1Object2) {
      throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
    }
    
    public class frame0 extends ModuleBody {
      final ModuleMethod lambda$Fn1;
      
      final ModuleMethod lambda$Fn2;
      
      Object name;
      
      srfi37.frame staticLink;
      
      public frame0() {
        ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:75");
        this.lambda$Fn2 = moduleMethod;
        moduleMethod = new ModuleMethod(this, 2, null, 4097);
        moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:72");
        this.lambda$Fn1 = moduleMethod;
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply1(param2ModuleMethod, param2Object);
          case 1:
            return lambda7(param2Object) ? Boolean.TRUE : Boolean.FALSE;
          case 2:
            break;
        } 
        return lambda6(param2Object);
      }
      
      Object lambda6(Object param2Object) {
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)param2Object;
          return srfi37.frame.lambda1find(srfi37.optionNames(mntype), this.lambda$Fn2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-names", 0, param2Object);
        } 
      }
      
      boolean lambda7(Object param2Object) {
        return IsEqual.apply(this.name, param2Object);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match1(param2ModuleMethod, param2Object, param2CallContext);
          case 2:
            param2CallContext.value1 = param2Object;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 1;
            return 0;
          case 1:
            break;
        } 
        param2CallContext.value1 = param2Object;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 1;
        return 0;
      }
    }
    
    public class frame1 extends ModuleBody {
      Object args;
      
      Object index;
      
      final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
      
      final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, null, -4096);
      
      final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
      
      final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, null, -4096);
      
      final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
      
      final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, null, -4096);
      
      char name;
      
      Object option;
      
      Object seeds;
      
      Object shorts;
      
      srfi37.frame staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply0(param2ModuleMethod);
          case 3:
            return lambda8();
          case 5:
            return lambda10();
          case 7:
            break;
        } 
        return lambda12();
      }
      
      public Object applyN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.applyN(param2ModuleMethod, param2ArrayOfObject);
          case 4:
            return lambda9$V(param2ArrayOfObject);
          case 6:
            return lambda11$V(param2ArrayOfObject);
          case 8:
            break;
        } 
        return lambda13$V(param2ArrayOfObject);
      }
      
      Object lambda10() {
        Apply apply = Scheme.apply;
        Object object = this.option;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, Char.make(this.name), lists.car.apply1(this.args), this.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda11$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), lList);
      }
      
      Object lambda12() {
        Apply apply = Scheme.apply;
        Object object = this.option;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, Char.make(this.name), Boolean.FALSE, this.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda13$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, lList);
      }
      
      Object lambda8() {
        Apply apply = Scheme.apply;
        Object object = this.option;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          object = srfi37.optionProcessor(mntype);
          Object object1 = this.option;
          Char char_ = Char.make(this.name);
          Object object2 = this.shorts;
          try {
            CharSequence charSequence = (CharSequence)object2;
            object2 = AddOp.$Pl.apply2(this.index, srfi37.Lit0);
            try {
              int i = ((Number)object2).intValue();
              object2 = this.shorts;
              try {
                CharSequence charSequence1 = (CharSequence)object2;
                return apply.applyN(new Object[] { object, object1, char_, strings.substring(charSequence, i, strings.stringLength(charSequence1)), this.seeds });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-length", 1, object2);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 2, object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 1, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda9$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda5scanArgs(this.args, lList);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match0(param2ModuleMethod, param2CallContext);
          case 7:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 5:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 3:
            break;
        } 
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 0;
        return 0;
      }
      
      public int matchN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.matchN(param2ModuleMethod, param2ArrayOfObject, param2CallContext);
          case 8:
            param2CallContext.values = param2ArrayOfObject;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 5;
            return 0;
          case 6:
            param2CallContext.values = param2ArrayOfObject;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 5;
            return 0;
          case 4:
            break;
        } 
        param2CallContext.values = param2ArrayOfObject;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 5;
        return 0;
      }
    }
    
    public class frame2 extends ModuleBody {
      final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, null, -4096);
      
      final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
      
      Object operands;
      
      Object seeds;
      
      srfi37.frame staticLink;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 9) ? lambda14() : super.apply0(param2ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject) {
        return (param2ModuleMethod.selector == 10) ? lambda15$V(param2ArrayOfObject) : super.applyN(param2ModuleMethod, param2ArrayOfObject);
      }
      
      Object lambda14() {
        return Scheme.apply.apply3(this.staticLink.operand$Mnproc, lists.car.apply1(this.operands), this.seeds);
      }
      
      Object lambda15$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda4scanOperands(lists.cdr.apply1(this.operands), lList);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 9) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int matchN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 10) {
          param2CallContext.values = param2ArrayOfObject;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param2ModuleMethod, param2ArrayOfObject, param2CallContext);
      }
    }
    
    public class frame3 extends ModuleBody {
      Object arg;
      
      Object args;
      
      final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, null, 0);
      
      final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, null, 4097);
      
      final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, null, 0);
      
      final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, null, -4096);
      
      final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, null, 0);
      
      final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, -4096);
      
      final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, null, 0);
      
      final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, null, -4096);
      
      CharSequence name;
      
      Object option;
      
      Object seeds;
      
      srfi37.frame staticLink;
      
      Object temp;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.apply0(param2ModuleMethod);
          case 17:
            return lambda16();
          case 19:
            return lambda24();
          case 21:
            return lambda26();
          case 23:
            break;
        } 
        return lambda28();
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 18) ? lambda17(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      public Object applyN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.applyN(param2ModuleMethod, param2ArrayOfObject);
          case 20:
            return lambda25$V(param2ArrayOfObject);
          case 22:
            return lambda27$V(param2ArrayOfObject);
          case 24:
            break;
        } 
        return lambda29$V(param2ArrayOfObject);
      }
      
      CharSequence lambda16() {
        Object object = this.arg;
        try {
          CharSequence charSequence = (CharSequence)object;
          object = this.temp;
          try {
            int i = ((Number)object).intValue();
            return strings.substring(charSequence, 2, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 3, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 1, object);
        } 
      }
      
      Object lambda17(Object param2Object) {
        frame4 frame4 = new frame4();
        frame4.staticLink = this;
        frame4.x = param2Object;
        return call_with_values.callWithValues((Procedure)frame4.lambda$Fn13, (Procedure)frame4.lambda$Fn14);
      }
      
      Object lambda24() {
        Apply apply = Scheme.apply;
        Object object = this.option;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, this.name, lists.car.apply1(this.args), this.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda25$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), lList);
      }
      
      Object lambda26() {
        Apply apply = Scheme.apply;
        Object object = this.option;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, this.name, Boolean.FALSE, this.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda27$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda5scanArgs(this.args, lList);
      }
      
      Object lambda28() {
        return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
      }
      
      Object lambda29$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.lambda5scanArgs(this.args, lList);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.match0(param2ModuleMethod, param2CallContext);
          case 23:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 21:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 19:
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 0;
            return 0;
          case 17:
            break;
        } 
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 0;
        return 0;
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 18) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
      
      public int matchN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject, CallContext param2CallContext) {
        switch (param2ModuleMethod.selector) {
          default:
            return super.matchN(param2ModuleMethod, param2ArrayOfObject, param2CallContext);
          case 24:
            param2CallContext.values = param2ArrayOfObject;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 5;
            return 0;
          case 22:
            param2CallContext.values = param2ArrayOfObject;
            param2CallContext.proc = (Procedure)param2ModuleMethod;
            param2CallContext.pc = 5;
            return 0;
          case 20:
            break;
        } 
        param2CallContext.values = param2ArrayOfObject;
        param2CallContext.proc = (Procedure)param2ModuleMethod;
        param2CallContext.pc = 5;
        return 0;
      }
      
      public class frame4 extends ModuleBody {
        final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
        
        final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
        
        srfi37.frame.frame3 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 15) ? lambda18() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 16) ? lambda19(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        CharSequence lambda18() {
          Object object = this.staticLink.arg;
          try {
            CharSequence charSequence = (CharSequence)object;
            object = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
            try {
              int i = ((Number)object).intValue();
              object = this.staticLink.arg;
              try {
                CharSequence charSequence1 = (CharSequence)object;
                return strings.substring(charSequence, i, strings.stringLength(charSequence1));
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "string-length", 1, object);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "substring", 2, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 1, object);
          } 
        }
        
        Object lambda19(Object param3Object) {
          frame5 frame5 = new frame5();
          frame5.staticLink = this;
          frame5.x = param3Object;
          return call_with_values.callWithValues((Procedure)frame5.lambda$Fn15, (Procedure)frame5.lambda$Fn16);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 15) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 16) {
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param3ModuleMethod, param3Object, param3CallContext);
        }
        
        public class frame5 extends ModuleBody {
          final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
          
          final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
          
          srfi37.frame.frame3.frame4 staticLink;
          
          Object x;
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 13) ? lambda20() : super.apply0(param4ModuleMethod);
          }
          
          public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
            return (param4ModuleMethod.selector == 14) ? lambda21(param4Object) : super.apply1(param4ModuleMethod, param4Object);
          }
          
          Object lambda20() {
            Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
            return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
          }
          
          Object lambda21(Object param4Object) {
            frame6 frame6 = new frame6();
            frame6.staticLink = this;
            frame6.x = param4Object;
            return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 13) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 14) {
              param4CallContext.value1 = param4Object;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 1;
              return 0;
            } 
            return super.match1(param4ModuleMethod, param4Object, param4CallContext);
          }
          
          public class frame6 extends ModuleBody {
            final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
            
            final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
            
            srfi37.frame.frame3.frame4.frame5 staticLink;
            
            Object x;
            
            public Object apply0(ModuleMethod param5ModuleMethod) {
              return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
            }
            
            public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
              return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
            }
            
            Object lambda22() {
              Apply apply = Scheme.apply;
              Object object = this.x;
              try {
                srfi37.Mntype mntype = (srfi37.Mntype)object;
                return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "option-processor", 0, object);
              } 
            }
            
            Object lambda23$V(Object[] param5ArrayOfObject) {
              LList lList = LList.makeList(param5ArrayOfObject, 0);
              return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
            }
            
            public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
              if (param5ModuleMethod.selector == 11) {
                param5CallContext.proc = (Procedure)param5ModuleMethod;
                param5CallContext.pc = 0;
                return 0;
              } 
              return super.match0(param5ModuleMethod, param5CallContext);
            }
            
            public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
              if (param5ModuleMethod.selector == 12) {
                param5CallContext.values = param5ArrayOfObject;
                param5CallContext.proc = (Procedure)param5ModuleMethod;
                param5CallContext.pc = 5;
                return 0;
              } 
              return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
            }
          }
        }
        
        public class frame6 extends ModuleBody {
          final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
          
          final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
          
          srfi37.frame.frame3.frame4.frame5 staticLink;
          
          Object x;
          
          public Object apply0(ModuleMethod param4ModuleMethod) {
            return (param4ModuleMethod.selector == 11) ? lambda22() : super.apply0(param4ModuleMethod);
          }
          
          public Object applyN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject) {
            return (param4ModuleMethod.selector == 12) ? lambda23$V(param4ArrayOfObject) : super.applyN(param4ModuleMethod, param4ArrayOfObject);
          }
          
          Object lambda22() {
            Apply apply = Scheme.apply;
            Object object = this.x;
            try {
              srfi37.Mntype mntype = (srfi37.Mntype)object;
              return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "option-processor", 0, object);
            } 
          }
          
          Object lambda23$V(Object[] param4ArrayOfObject) {
            LList lList = LList.makeList(param4ArrayOfObject, 0);
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
          }
          
          public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 11) {
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param4ModuleMethod, param4CallContext);
          }
          
          public int matchN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject, CallContext param4CallContext) {
            if (param4ModuleMethod.selector == 12) {
              param4CallContext.values = param4ArrayOfObject;
              param4CallContext.proc = (Procedure)param4ModuleMethod;
              param4CallContext.pc = 5;
              return 0;
            } 
            return super.matchN(param4ModuleMethod, param4ArrayOfObject, param4CallContext);
          }
        }
      }
      
      public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
        
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
        
        srfi37.frame.frame3.frame4 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 13) ? lambda20() : super.apply0(param3ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
          return (param3ModuleMethod.selector == 14) ? lambda21(param3Object) : super.apply1(param3ModuleMethod, param3Object);
        }
        
        Object lambda20() {
          Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
          return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
        
        Object lambda21(Object param3Object) {
          frame6 frame6 = new frame6();
          frame6.staticLink = this;
          frame6.x = param3Object;
          return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 13) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 14) {
            param3CallContext.value1 = param3Object;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param3ModuleMethod, param3Object, param3CallContext);
        }
        
        public class frame6 extends ModuleBody {
          final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
          
          final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
          
          srfi37.frame.frame3.frame4.frame5 staticLink;
          
          Object x;
          
          public Object apply0(ModuleMethod param5ModuleMethod) {
            return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
          }
          
          public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
            return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
          }
          
          Object lambda22() {
            Apply apply = Scheme.apply;
            Object object = this.x;
            try {
              srfi37.Mntype mntype = (srfi37.Mntype)object;
              return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "option-processor", 0, object);
            } 
          }
          
          Object lambda23$V(Object[] param5ArrayOfObject) {
            LList lList = LList.makeList(param5ArrayOfObject, 0);
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
          }
          
          public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 11) {
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param5ModuleMethod, param5CallContext);
          }
          
          public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 12) {
              param5CallContext.values = param5ArrayOfObject;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 5;
              return 0;
            } 
            return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
          }
        }
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param3ModuleMethod) {
          return (param3ModuleMethod.selector == 11) ? lambda22() : super.apply0(param3ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param3ModuleMethod, Object[] param3ArrayOfObject) {
          return (param3ModuleMethod.selector == 12) ? lambda23$V(param3ArrayOfObject) : super.applyN(param3ModuleMethod, param3ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param3ArrayOfObject) {
          LList lList = LList.makeList(param3ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 11) {
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param3ModuleMethod, param3CallContext);
        }
        
        public int matchN(ModuleMethod param3ModuleMethod, Object[] param3ArrayOfObject, CallContext param3CallContext) {
          if (param3ModuleMethod.selector == 12) {
            param3CallContext.values = param3ArrayOfObject;
            param3CallContext.proc = (Procedure)param3ModuleMethod;
            param3CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param3ModuleMethod, param3ArrayOfObject, param3CallContext);
        }
      }
    }
    
    public class frame4 extends ModuleBody {
      final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
      
      final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
      
      srfi37.frame.frame3 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 15) ? lambda18() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 16) ? lambda19(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      CharSequence lambda18() {
        Object object = this.staticLink.arg;
        try {
          CharSequence charSequence = (CharSequence)object;
          object = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
          try {
            int i = ((Number)object).intValue();
            object = this.staticLink.arg;
            try {
              CharSequence charSequence1 = (CharSequence)object;
              return strings.substring(charSequence, i, strings.stringLength(charSequence1));
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 2, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 1, object);
        } 
      }
      
      Object lambda19(Object param2Object) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.x = param2Object;
        return call_with_values.callWithValues((Procedure)frame5.lambda$Fn15, (Procedure)frame5.lambda$Fn16);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 15) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 16) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
      
      public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
        
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
        
        srfi37.frame.frame3.frame4 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 13) ? lambda20() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          return (param4ModuleMethod.selector == 14) ? lambda21(param4Object) : super.apply1(param4ModuleMethod, param4Object);
        }
        
        Object lambda20() {
          Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
          return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
        
        Object lambda21(Object param4Object) {
          frame6 frame6 = new frame6();
          frame6.staticLink = this;
          frame6.x = param4Object;
          return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 13) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 14) {
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param4ModuleMethod, param4Object, param4CallContext);
        }
        
        public class frame6 extends ModuleBody {
          final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
          
          final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
          
          srfi37.frame.frame3.frame4.frame5 staticLink;
          
          Object x;
          
          public Object apply0(ModuleMethod param5ModuleMethod) {
            return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
          }
          
          public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
            return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
          }
          
          Object lambda22() {
            Apply apply = Scheme.apply;
            Object object = this.x;
            try {
              srfi37.Mntype mntype = (srfi37.Mntype)object;
              return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "option-processor", 0, object);
            } 
          }
          
          Object lambda23$V(Object[] param5ArrayOfObject) {
            LList lList = LList.makeList(param5ArrayOfObject, 0);
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
          }
          
          public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 11) {
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param5ModuleMethod, param5CallContext);
          }
          
          public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 12) {
              param5CallContext.values = param5ArrayOfObject;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 5;
              return 0;
            } 
            return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
          }
        }
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 11) ? lambda22() : super.apply0(param4ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject) {
          return (param4ModuleMethod.selector == 12) ? lambda23$V(param4ArrayOfObject) : super.applyN(param4ModuleMethod, param4ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param4ArrayOfObject) {
          LList lList = LList.makeList(param4ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 11) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int matchN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 12) {
            param4CallContext.values = param4ArrayOfObject;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param4ModuleMethod, param4ArrayOfObject, param4CallContext);
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
      
      final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
      
      srfi37.frame.frame3.frame4 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 13) ? lambda20() : super.apply0(param2ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param2ModuleMethod, Object param2Object) {
        return (param2ModuleMethod.selector == 14) ? lambda21(param2Object) : super.apply1(param2ModuleMethod, param2Object);
      }
      
      Object lambda20() {
        Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
        return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
      }
      
      Object lambda21(Object param2Object) {
        frame6 frame6 = new frame6();
        frame6.staticLink = this;
        frame6.x = param2Object;
        return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 13) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int match1(ModuleMethod param2ModuleMethod, Object param2Object, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 14) {
          param2CallContext.value1 = param2Object;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param2ModuleMethod, param2Object, param2CallContext);
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param5ModuleMethod) {
          return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
          return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param5ArrayOfObject) {
          LList lList = LList.makeList(param5ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 11) {
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param5ModuleMethod, param5CallContext);
        }
        
        public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 12) {
            param5CallContext.values = param5ArrayOfObject;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
        }
      }
    }
    
    public class frame6 extends ModuleBody {
      final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
      
      final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
      
      srfi37.frame.frame3.frame4.frame5 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param2ModuleMethod) {
        return (param2ModuleMethod.selector == 11) ? lambda22() : super.apply0(param2ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject) {
        return (param2ModuleMethod.selector == 12) ? lambda23$V(param2ArrayOfObject) : super.applyN(param2ModuleMethod, param2ArrayOfObject);
      }
      
      Object lambda22() {
        Apply apply = Scheme.apply;
        Object object = this.x;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda23$V(Object[] param2ArrayOfObject) {
        LList lList = LList.makeList(param2ArrayOfObject, 0);
        return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
      }
      
      public int match0(ModuleMethod param2ModuleMethod, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 11) {
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param2ModuleMethod, param2CallContext);
      }
      
      public int matchN(ModuleMethod param2ModuleMethod, Object[] param2ArrayOfObject, CallContext param2CallContext) {
        if (param2ModuleMethod.selector == 12) {
          param2CallContext.values = param2ArrayOfObject;
          param2CallContext.proc = (Procedure)param2ModuleMethod;
          param2CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param2ModuleMethod, param2ArrayOfObject, param2CallContext);
      }
    }
  }
  
  public class frame0 extends ModuleBody {
    final ModuleMethod lambda$Fn1;
    
    final ModuleMethod lambda$Fn2;
    
    Object name;
    
    srfi37.frame staticLink;
    
    public frame0() {
      ModuleMethod moduleMethod = new ModuleMethod(this, 1, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:75");
      this.lambda$Fn2 = moduleMethod;
      moduleMethod = new ModuleMethod(this, 2, null, 4097);
      moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/gnu/kawa/slib/srfi37.scm:72");
      this.lambda$Fn1 = moduleMethod;
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply1(param1ModuleMethod, param1Object);
        case 1:
          return lambda7(param1Object) ? Boolean.TRUE : Boolean.FALSE;
        case 2:
          break;
      } 
      return lambda6(param1Object);
    }
    
    Object lambda6(Object param1Object) {
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)param1Object;
        return srfi37.frame.lambda1find(srfi37.optionNames(mntype), this.lambda$Fn2);
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-names", 0, param1Object);
      } 
    }
    
    boolean lambda7(Object param1Object) {
      return IsEqual.apply(this.name, param1Object);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match1(param1ModuleMethod, param1Object, param1CallContext);
        case 2:
          param1CallContext.value1 = param1Object;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 1;
          return 0;
        case 1:
          break;
      } 
      param1CallContext.value1 = param1Object;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 1;
      return 0;
    }
  }
  
  public class frame1 extends ModuleBody {
    Object args;
    
    Object index;
    
    final ModuleMethod lambda$Fn3 = new ModuleMethod(this, 3, null, 0);
    
    final ModuleMethod lambda$Fn4 = new ModuleMethod(this, 4, null, -4096);
    
    final ModuleMethod lambda$Fn5 = new ModuleMethod(this, 5, null, 0);
    
    final ModuleMethod lambda$Fn6 = new ModuleMethod(this, 6, null, -4096);
    
    final ModuleMethod lambda$Fn7 = new ModuleMethod(this, 7, null, 0);
    
    final ModuleMethod lambda$Fn8 = new ModuleMethod(this, 8, null, -4096);
    
    char name;
    
    Object option;
    
    Object seeds;
    
    Object shorts;
    
    srfi37.frame staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 3:
          return lambda8();
        case 5:
          return lambda10();
        case 7:
          break;
      } 
      return lambda12();
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.applyN(param1ModuleMethod, param1ArrayOfObject);
        case 4:
          return lambda9$V(param1ArrayOfObject);
        case 6:
          return lambda11$V(param1ArrayOfObject);
        case 8:
          break;
      } 
      return lambda13$V(param1ArrayOfObject);
    }
    
    Object lambda10() {
      Apply apply = Scheme.apply;
      Object object = this.option;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, Char.make(this.name), lists.car.apply1(this.args), this.seeds });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda11$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), lList);
    }
    
    Object lambda12() {
      Apply apply = Scheme.apply;
      Object object = this.option;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, Char.make(this.name), Boolean.FALSE, this.seeds });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda13$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda3scanShortOptions(AddOp.$Pl.apply2(this.index, srfi37.Lit0), this.shorts, this.args, lList);
    }
    
    Object lambda8() {
      Apply apply = Scheme.apply;
      Object object = this.option;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        object = srfi37.optionProcessor(mntype);
        Object object1 = this.option;
        Char char_ = Char.make(this.name);
        Object object2 = this.shorts;
        try {
          CharSequence charSequence = (CharSequence)object2;
          object2 = AddOp.$Pl.apply2(this.index, srfi37.Lit0);
          try {
            int i = ((Number)object2).intValue();
            object2 = this.shorts;
            try {
              CharSequence charSequence1 = (CharSequence)object2;
              return apply.applyN(new Object[] { object, object1, char_, strings.substring(charSequence, i, strings.stringLength(charSequence1)), this.seeds });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object2);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 2, object2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 1, object2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda9$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, lList);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 7:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 5:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 3:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
        case 8:
          param1CallContext.values = param1ArrayOfObject;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 5;
          return 0;
        case 6:
          param1CallContext.values = param1ArrayOfObject;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 5;
          return 0;
        case 4:
          break;
      } 
      param1CallContext.values = param1ArrayOfObject;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 5;
      return 0;
    }
  }
  
  public class frame2 extends ModuleBody {
    final ModuleMethod lambda$Fn10 = new ModuleMethod(this, 10, null, -4096);
    
    final ModuleMethod lambda$Fn9 = new ModuleMethod(this, 9, null, 0);
    
    Object operands;
    
    Object seeds;
    
    srfi37.frame staticLink;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 9) ? lambda14() : super.apply0(param1ModuleMethod);
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      return (param1ModuleMethod.selector == 10) ? lambda15$V(param1ArrayOfObject) : super.applyN(param1ModuleMethod, param1ArrayOfObject);
    }
    
    Object lambda14() {
      return Scheme.apply.apply3(this.staticLink.operand$Mnproc, lists.car.apply1(this.operands), this.seeds);
    }
    
    Object lambda15$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda4scanOperands(lists.cdr.apply1(this.operands), lList);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 9) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 10) {
        param1CallContext.values = param1ArrayOfObject;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 5;
        return 0;
      } 
      return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
    }
  }
  
  public class frame3 extends ModuleBody {
    Object arg;
    
    Object args;
    
    final ModuleMethod lambda$Fn11 = new ModuleMethod(this, 17, null, 0);
    
    final ModuleMethod lambda$Fn12 = new ModuleMethod(this, 18, null, 4097);
    
    final ModuleMethod lambda$Fn19 = new ModuleMethod(this, 19, null, 0);
    
    final ModuleMethod lambda$Fn20 = new ModuleMethod(this, 20, null, -4096);
    
    final ModuleMethod lambda$Fn21 = new ModuleMethod(this, 21, null, 0);
    
    final ModuleMethod lambda$Fn22 = new ModuleMethod(this, 22, null, -4096);
    
    final ModuleMethod lambda$Fn23 = new ModuleMethod(this, 23, null, 0);
    
    final ModuleMethod lambda$Fn24 = new ModuleMethod(this, 24, null, -4096);
    
    CharSequence name;
    
    Object option;
    
    Object seeds;
    
    srfi37.frame staticLink;
    
    Object temp;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.apply0(param1ModuleMethod);
        case 17:
          return lambda16();
        case 19:
          return lambda24();
        case 21:
          return lambda26();
        case 23:
          break;
      } 
      return lambda28();
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 18) ? lambda17(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.applyN(param1ModuleMethod, param1ArrayOfObject);
        case 20:
          return lambda25$V(param1ArrayOfObject);
        case 22:
          return lambda27$V(param1ArrayOfObject);
        case 24:
          break;
      } 
      return lambda29$V(param1ArrayOfObject);
    }
    
    CharSequence lambda16() {
      Object object = this.arg;
      try {
        CharSequence charSequence = (CharSequence)object;
        object = this.temp;
        try {
          int i = ((Number)object).intValue();
          return strings.substring(charSequence, 2, i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 3, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "substring", 1, object);
      } 
    }
    
    Object lambda17(Object param1Object) {
      frame4 frame4 = new frame4();
      frame4.staticLink = this;
      frame4.x = param1Object;
      return call_with_values.callWithValues((Procedure)frame4.lambda$Fn13, (Procedure)frame4.lambda$Fn14);
    }
    
    Object lambda24() {
      Apply apply = Scheme.apply;
      Object object = this.option;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, this.name, lists.car.apply1(this.args), this.seeds });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda25$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(lists.cdr.apply1(this.args), lList);
    }
    
    Object lambda26() {
      Apply apply = Scheme.apply;
      Object object = this.option;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.option, this.name, Boolean.FALSE, this.seeds });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda27$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, lList);
    }
    
    Object lambda28() {
      return Scheme.apply.apply3(this.staticLink.operand$Mnproc, this.arg, this.seeds);
    }
    
    Object lambda29$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.lambda5scanArgs(this.args, lList);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.match0(param1ModuleMethod, param1CallContext);
        case 23:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 21:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 19:
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 0;
          return 0;
        case 17:
          break;
      } 
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 0;
      return 0;
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 18) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      switch (param1ModuleMethod.selector) {
        default:
          return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
        case 24:
          param1CallContext.values = param1ArrayOfObject;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 5;
          return 0;
        case 22:
          param1CallContext.values = param1ArrayOfObject;
          param1CallContext.proc = (Procedure)param1ModuleMethod;
          param1CallContext.pc = 5;
          return 0;
        case 20:
          break;
      } 
      param1CallContext.values = param1ArrayOfObject;
      param1CallContext.proc = (Procedure)param1ModuleMethod;
      param1CallContext.pc = 5;
      return 0;
    }
    
    public class frame4 extends ModuleBody {
      final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
      
      final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
      
      srfi37.frame.frame3 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 15) ? lambda18() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 16) ? lambda19(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      CharSequence lambda18() {
        Object object = this.staticLink.arg;
        try {
          CharSequence charSequence = (CharSequence)object;
          object = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
          try {
            int i = ((Number)object).intValue();
            object = this.staticLink.arg;
            try {
              CharSequence charSequence1 = (CharSequence)object;
              return strings.substring(charSequence, i, strings.stringLength(charSequence1));
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "string-length", 1, object);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "substring", 2, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 1, object);
        } 
      }
      
      Object lambda19(Object param3Object) {
        frame5 frame5 = new frame5();
        frame5.staticLink = this;
        frame5.x = param3Object;
        return call_with_values.callWithValues((Procedure)frame5.lambda$Fn15, (Procedure)frame5.lambda$Fn16);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 15) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 16) {
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param3ModuleMethod, param3Object, param3CallContext);
      }
      
      public class frame5 extends ModuleBody {
        final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
        
        final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
        
        srfi37.frame.frame3.frame4 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 13) ? lambda20() : super.apply0(param4ModuleMethod);
        }
        
        public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
          return (param4ModuleMethod.selector == 14) ? lambda21(param4Object) : super.apply1(param4ModuleMethod, param4Object);
        }
        
        Object lambda20() {
          Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
          return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
        }
        
        Object lambda21(Object param4Object) {
          frame6 frame6 = new frame6();
          frame6.staticLink = this;
          frame6.x = param4Object;
          return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 13) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 14) {
            param4CallContext.value1 = param4Object;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 1;
            return 0;
          } 
          return super.match1(param4ModuleMethod, param4Object, param4CallContext);
        }
        
        public class frame6 extends ModuleBody {
          final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
          
          final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
          
          srfi37.frame.frame3.frame4.frame5 staticLink;
          
          Object x;
          
          public Object apply0(ModuleMethod param5ModuleMethod) {
            return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
          }
          
          public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
            return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
          }
          
          Object lambda22() {
            Apply apply = Scheme.apply;
            Object object = this.x;
            try {
              srfi37.Mntype mntype = (srfi37.Mntype)object;
              return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "option-processor", 0, object);
            } 
          }
          
          Object lambda23$V(Object[] param5ArrayOfObject) {
            LList lList = LList.makeList(param5ArrayOfObject, 0);
            return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
          }
          
          public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 11) {
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 0;
              return 0;
            } 
            return super.match0(param5ModuleMethod, param5CallContext);
          }
          
          public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
            if (param5ModuleMethod.selector == 12) {
              param5CallContext.values = param5ArrayOfObject;
              param5CallContext.proc = (Procedure)param5ModuleMethod;
              param5CallContext.pc = 5;
              return 0;
            } 
            return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
          }
        }
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param4ModuleMethod) {
          return (param4ModuleMethod.selector == 11) ? lambda22() : super.apply0(param4ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject) {
          return (param4ModuleMethod.selector == 12) ? lambda23$V(param4ArrayOfObject) : super.applyN(param4ModuleMethod, param4ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param4ArrayOfObject) {
          LList lList = LList.makeList(param4ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 11) {
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param4ModuleMethod, param4CallContext);
        }
        
        public int matchN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject, CallContext param4CallContext) {
          if (param4ModuleMethod.selector == 12) {
            param4CallContext.values = param4ArrayOfObject;
            param4CallContext.proc = (Procedure)param4ModuleMethod;
            param4CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param4ModuleMethod, param4ArrayOfObject, param4CallContext);
        }
      }
    }
    
    public class frame5 extends ModuleBody {
      final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
      
      final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
      
      srfi37.frame.frame3.frame4 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 13) ? lambda20() : super.apply0(param3ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param3ModuleMethod, Object param3Object) {
        return (param3ModuleMethod.selector == 14) ? lambda21(param3Object) : super.apply1(param3ModuleMethod, param3Object);
      }
      
      Object lambda20() {
        Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
        return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
      }
      
      Object lambda21(Object param3Object) {
        frame6 frame6 = new frame6();
        frame6.staticLink = this;
        frame6.x = param3Object;
        return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 13) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int match1(ModuleMethod param3ModuleMethod, Object param3Object, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 14) {
          param3CallContext.value1 = param3Object;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param3ModuleMethod, param3Object, param3CallContext);
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param5ModuleMethod) {
          return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
          return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param5ArrayOfObject) {
          LList lList = LList.makeList(param5ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 11) {
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param5ModuleMethod, param5CallContext);
        }
        
        public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 12) {
            param5CallContext.values = param5ArrayOfObject;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
        }
      }
    }
    
    public class frame6 extends ModuleBody {
      final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
      
      final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
      
      srfi37.frame.frame3.frame4.frame5 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param3ModuleMethod) {
        return (param3ModuleMethod.selector == 11) ? lambda22() : super.apply0(param3ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param3ModuleMethod, Object[] param3ArrayOfObject) {
        return (param3ModuleMethod.selector == 12) ? lambda23$V(param3ArrayOfObject) : super.applyN(param3ModuleMethod, param3ArrayOfObject);
      }
      
      Object lambda22() {
        Apply apply = Scheme.apply;
        Object object = this.x;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda23$V(Object[] param3ArrayOfObject) {
        LList lList = LList.makeList(param3ArrayOfObject, 0);
        return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
      }
      
      public int match0(ModuleMethod param3ModuleMethod, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 11) {
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param3ModuleMethod, param3CallContext);
      }
      
      public int matchN(ModuleMethod param3ModuleMethod, Object[] param3ArrayOfObject, CallContext param3CallContext) {
        if (param3ModuleMethod.selector == 12) {
          param3CallContext.values = param3ArrayOfObject;
          param3CallContext.proc = (Procedure)param3ModuleMethod;
          param3CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param3ModuleMethod, param3ArrayOfObject, param3CallContext);
      }
    }
  }
  
  public class frame4 extends ModuleBody {
    final ModuleMethod lambda$Fn13 = new ModuleMethod(this, 15, null, 0);
    
    final ModuleMethod lambda$Fn14 = new ModuleMethod(this, 16, null, 4097);
    
    srfi37.frame.frame3 staticLink;
    
    Object x;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 15) ? lambda18() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 16) ? lambda19(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    CharSequence lambda18() {
      Object object = this.staticLink.arg;
      try {
        CharSequence charSequence = (CharSequence)object;
        object = AddOp.$Pl.apply2(this.staticLink.temp, srfi37.Lit0);
        try {
          int i = ((Number)object).intValue();
          object = this.staticLink.arg;
          try {
            CharSequence charSequence1 = (CharSequence)object;
            return strings.substring(charSequence, i, strings.stringLength(charSequence1));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string-length", 1, object);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "substring", 2, object);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "substring", 1, object);
      } 
    }
    
    Object lambda19(Object param1Object) {
      frame5 frame5 = new frame5();
      frame5.staticLink = this;
      frame5.x = param1Object;
      return call_with_values.callWithValues((Procedure)frame5.lambda$Fn15, (Procedure)frame5.lambda$Fn16);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 15) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 16) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame5 extends ModuleBody {
      final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
      
      final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
      
      srfi37.frame.frame3.frame4 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 13) ? lambda20() : super.apply0(param4ModuleMethod);
      }
      
      public Object apply1(ModuleMethod param4ModuleMethod, Object param4Object) {
        return (param4ModuleMethod.selector == 14) ? lambda21(param4Object) : super.apply1(param4ModuleMethod, param4Object);
      }
      
      Object lambda20() {
        Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
        return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
      }
      
      Object lambda21(Object param4Object) {
        frame6 frame6 = new frame6();
        frame6.staticLink = this;
        frame6.x = param4Object;
        return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 13) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int match1(ModuleMethod param4ModuleMethod, Object param4Object, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 14) {
          param4CallContext.value1 = param4Object;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 1;
          return 0;
        } 
        return super.match1(param4ModuleMethod, param4Object, param4CallContext);
      }
      
      public class frame6 extends ModuleBody {
        final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
        
        final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
        
        srfi37.frame.frame3.frame4.frame5 staticLink;
        
        Object x;
        
        public Object apply0(ModuleMethod param5ModuleMethod) {
          return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
        }
        
        public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
          return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
        }
        
        Object lambda22() {
          Apply apply = Scheme.apply;
          Object object = this.x;
          try {
            srfi37.Mntype mntype = (srfi37.Mntype)object;
            return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "option-processor", 0, object);
          } 
        }
        
        Object lambda23$V(Object[] param5ArrayOfObject) {
          LList lList = LList.makeList(param5ArrayOfObject, 0);
          return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
        }
        
        public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 11) {
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 0;
            return 0;
          } 
          return super.match0(param5ModuleMethod, param5CallContext);
        }
        
        public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
          if (param5ModuleMethod.selector == 12) {
            param5CallContext.values = param5ArrayOfObject;
            param5CallContext.proc = (Procedure)param5ModuleMethod;
            param5CallContext.pc = 5;
            return 0;
          } 
          return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
        }
      }
    }
    
    public class frame6 extends ModuleBody {
      final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
      
      final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
      
      srfi37.frame.frame3.frame4.frame5 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param4ModuleMethod) {
        return (param4ModuleMethod.selector == 11) ? lambda22() : super.apply0(param4ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject) {
        return (param4ModuleMethod.selector == 12) ? lambda23$V(param4ArrayOfObject) : super.applyN(param4ModuleMethod, param4ArrayOfObject);
      }
      
      Object lambda22() {
        Apply apply = Scheme.apply;
        Object object = this.x;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda23$V(Object[] param4ArrayOfObject) {
        LList lList = LList.makeList(param4ArrayOfObject, 0);
        return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
      }
      
      public int match0(ModuleMethod param4ModuleMethod, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 11) {
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param4ModuleMethod, param4CallContext);
      }
      
      public int matchN(ModuleMethod param4ModuleMethod, Object[] param4ArrayOfObject, CallContext param4CallContext) {
        if (param4ModuleMethod.selector == 12) {
          param4CallContext.values = param4ArrayOfObject;
          param4CallContext.proc = (Procedure)param4ModuleMethod;
          param4CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param4ModuleMethod, param4ArrayOfObject, param4CallContext);
      }
    }
  }
  
  public class frame5 extends ModuleBody {
    final ModuleMethod lambda$Fn15 = new ModuleMethod(this, 13, null, 0);
    
    final ModuleMethod lambda$Fn16 = new ModuleMethod(this, 14, null, 4097);
    
    srfi37.frame.frame3.frame4 staticLink;
    
    Object x;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 13) ? lambda20() : super.apply0(param1ModuleMethod);
    }
    
    public Object apply1(ModuleMethod param1ModuleMethod, Object param1Object) {
      return (param1ModuleMethod.selector == 14) ? lambda21(param1Object) : super.apply1(param1ModuleMethod, param1Object);
    }
    
    Object lambda20() {
      Object object = this.staticLink.staticLink.staticLink.lambda2findOption(this.staticLink.x);
      return (object != Boolean.FALSE) ? object : srfi37.option(LList.list1(this.staticLink.x), Boolean.TRUE, Boolean.FALSE, this.staticLink.staticLink.staticLink.unrecognized$Mnoption$Mnproc);
    }
    
    Object lambda21(Object param1Object) {
      frame6 frame6 = new frame6();
      frame6.staticLink = this;
      frame6.x = param1Object;
      return call_with_values.callWithValues((Procedure)frame6.lambda$Fn17, (Procedure)frame6.lambda$Fn18);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 13) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int match1(ModuleMethod param1ModuleMethod, Object param1Object, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 14) {
        param1CallContext.value1 = param1Object;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 1;
        return 0;
      } 
      return super.match1(param1ModuleMethod, param1Object, param1CallContext);
    }
    
    public class frame6 extends ModuleBody {
      final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
      
      final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
      
      srfi37.frame.frame3.frame4.frame5 staticLink;
      
      Object x;
      
      public Object apply0(ModuleMethod param5ModuleMethod) {
        return (param5ModuleMethod.selector == 11) ? lambda22() : super.apply0(param5ModuleMethod);
      }
      
      public Object applyN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject) {
        return (param5ModuleMethod.selector == 12) ? lambda23$V(param5ArrayOfObject) : super.applyN(param5ModuleMethod, param5ArrayOfObject);
      }
      
      Object lambda22() {
        Apply apply = Scheme.apply;
        Object object = this.x;
        try {
          srfi37.Mntype mntype = (srfi37.Mntype)object;
          return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "option-processor", 0, object);
        } 
      }
      
      Object lambda23$V(Object[] param5ArrayOfObject) {
        LList lList = LList.makeList(param5ArrayOfObject, 0);
        return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
      }
      
      public int match0(ModuleMethod param5ModuleMethod, CallContext param5CallContext) {
        if (param5ModuleMethod.selector == 11) {
          param5CallContext.proc = (Procedure)param5ModuleMethod;
          param5CallContext.pc = 0;
          return 0;
        } 
        return super.match0(param5ModuleMethod, param5CallContext);
      }
      
      public int matchN(ModuleMethod param5ModuleMethod, Object[] param5ArrayOfObject, CallContext param5CallContext) {
        if (param5ModuleMethod.selector == 12) {
          param5CallContext.values = param5ArrayOfObject;
          param5CallContext.proc = (Procedure)param5ModuleMethod;
          param5CallContext.pc = 5;
          return 0;
        } 
        return super.matchN(param5ModuleMethod, param5ArrayOfObject, param5CallContext);
      }
    }
  }
  
  public class frame6 extends ModuleBody {
    final ModuleMethod lambda$Fn17 = new ModuleMethod(this, 11, null, 0);
    
    final ModuleMethod lambda$Fn18 = new ModuleMethod(this, 12, null, -4096);
    
    srfi37.frame.frame3.frame4.frame5 staticLink;
    
    Object x;
    
    public Object apply0(ModuleMethod param1ModuleMethod) {
      return (param1ModuleMethod.selector == 11) ? lambda22() : super.apply0(param1ModuleMethod);
    }
    
    public Object applyN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject) {
      return (param1ModuleMethod.selector == 12) ? lambda23$V(param1ArrayOfObject) : super.applyN(param1ModuleMethod, param1ArrayOfObject);
    }
    
    Object lambda22() {
      Apply apply = Scheme.apply;
      Object object = this.x;
      try {
        srfi37.Mntype mntype = (srfi37.Mntype)object;
        return apply.applyN(new Object[] { srfi37.optionProcessor(mntype), this.x, this.staticLink.staticLink.x, this.staticLink.x, this.staticLink.staticLink.staticLink.seeds });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "option-processor", 0, object);
      } 
    }
    
    Object lambda23$V(Object[] param1ArrayOfObject) {
      LList lList = LList.makeList(param1ArrayOfObject, 0);
      return this.staticLink.staticLink.staticLink.staticLink.lambda5scanArgs(this.staticLink.staticLink.staticLink.args, lList);
    }
    
    public int match0(ModuleMethod param1ModuleMethod, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 11) {
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 0;
        return 0;
      } 
      return super.match0(param1ModuleMethod, param1CallContext);
    }
    
    public int matchN(ModuleMethod param1ModuleMethod, Object[] param1ArrayOfObject, CallContext param1CallContext) {
      if (param1ModuleMethod.selector == 12) {
        param1CallContext.values = param1ArrayOfObject;
        param1CallContext.proc = (Procedure)param1ModuleMethod;
        param1CallContext.pc = 5;
        return 0;
      } 
      return super.matchN(param1ModuleMethod, param1ArrayOfObject, param1CallContext);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/slib/srfi37.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */