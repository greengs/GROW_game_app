package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.ObjectType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Language;
import gnu.expr.PrimProcedure;
import gnu.kawa.lispexpr.ClassNamespace;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Symbol;
import gnu.mapping.WrongType;

public class Invoke extends ProcedureN {
  public static final Invoke invoke = new Invoke("invoke", '*');
  
  public static final Invoke invokeSpecial;
  
  public static final Invoke invokeStatic = new Invoke("invoke-static", 'S');
  
  public static final Invoke make;
  
  char kind;
  
  Language language;
  
  static {
    invokeSpecial = new Invoke("invoke-special", 'P');
    make = new Invoke("make", 'N');
  }
  
  public Invoke(String paramString, char paramChar) {
    this(paramString, paramChar, Language.getDefaultLanguage());
  }
  
  public Invoke(String paramString, char paramChar, Language paramLanguage) {
    super(paramString);
    this.kind = paramChar;
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.reflect.CompileInvoke:validateApplyInvoke");
  }
  
  public static PrimProcedure getStaticMethod(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression) {
    // Byte code:
    //   0: ldc gnu/kawa/reflect/Invoke
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: aload_2
    //   6: invokestatic getStaticMethod : (Lgnu/bytecode/ClassType;Ljava/lang/String;[Lgnu/expr/Expression;)Lgnu/expr/PrimProcedure;
    //   9: astore_0
    //   10: ldc gnu/kawa/reflect/Invoke
    //   12: monitorexit
    //   13: aload_0
    //   14: areturn
    //   15: astore_0
    //   16: ldc gnu/kawa/reflect/Invoke
    //   18: monitorexit
    //   19: aload_0
    //   20: athrow
    // Exception table:
    //   from	to	target	type
    //   3	10	15	finally
  }
  
  public static Object invoke$V(Object[] paramArrayOfObject) throws Throwable {
    return invoke.applyN(paramArrayOfObject);
  }
  
  public static Object invokeStatic$V(Object[] paramArrayOfObject) throws Throwable {
    return invokeStatic.applyN(paramArrayOfObject);
  }
  
  public static Object make$V(Object[] paramArrayOfObject) throws Throwable {
    return make.applyN(paramArrayOfObject);
  }
  
  public static ApplyExp makeInvokeStatic(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression) {
    // Byte code:
    //   0: ldc gnu/kawa/reflect/Invoke
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: aload_2
    //   6: invokestatic getStaticMethod : (Lgnu/bytecode/ClassType;Ljava/lang/String;[Lgnu/expr/Expression;)Lgnu/expr/PrimProcedure;
    //   9: astore_3
    //   10: aload_3
    //   11: ifnonnull -> 59
    //   14: new java/lang/RuntimeException
    //   17: dup
    //   18: new java/lang/StringBuilder
    //   21: dup
    //   22: invokespecial <init> : ()V
    //   25: ldc 'missing or ambiguous method `'
    //   27: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   30: aload_1
    //   31: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   34: ldc '' in '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_0
    //   40: invokevirtual getName : ()Ljava/lang/String;
    //   43: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: invokevirtual toString : ()Ljava/lang/String;
    //   49: invokespecial <init> : (Ljava/lang/String;)V
    //   52: athrow
    //   53: astore_0
    //   54: ldc gnu/kawa/reflect/Invoke
    //   56: monitorexit
    //   57: aload_0
    //   58: athrow
    //   59: new gnu/expr/ApplyExp
    //   62: dup
    //   63: aload_3
    //   64: aload_2
    //   65: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   68: astore_0
    //   69: ldc gnu/kawa/reflect/Invoke
    //   71: monitorexit
    //   72: aload_0
    //   73: areturn
    // Exception table:
    //   from	to	target	type
    //   3	10	53	finally
    //   14	53	53	finally
    //   59	69	53	finally
  }
  
  private static ObjectType typeFrom(Object paramObject, Invoke paramInvoke) {
    Object object = paramObject;
    if (paramObject instanceof Class)
      object = Type.make((Class)paramObject); 
    if (object instanceof ObjectType)
      return (ObjectType)object; 
    if (object instanceof String || object instanceof gnu.lists.FString)
      return (ObjectType)ClassType.make(object.toString()); 
    if (object instanceof Symbol)
      return (ObjectType)ClassType.make(((Symbol)object).getName()); 
    if (object instanceof ClassNamespace)
      return (ObjectType)((ClassNamespace)object).getClassType(); 
    throw new WrongType(paramInvoke, 0, object, "class-specifier");
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Object[] arrayOfObject = paramCallContext.getArgs();
    if (this.kind == 'S' || this.kind == 'V' || this.kind == 's' || this.kind == '*') {
      int j = arrayOfObject.length;
      Procedure.checkArgCount((Procedure)this, j);
      Object object = arrayOfObject[0];
      if (this.kind == 'S' || this.kind == 's') {
        object = typeFrom(object, this);
      } else {
        object = Type.make(object.getClass());
      } 
      object = lookupMethods((ObjectType)object, arrayOfObject[1]);
      if (this.kind == 'S') {
        i = 2;
      } else {
        i = 1;
      } 
      Object[] arrayOfObject1 = new Object[j - i];
      int i = 0;
      if (this.kind == 'V' || this.kind == '*') {
        arrayOfObject1[0] = arrayOfObject[0];
        i = 0 + 1;
      } 
      System.arraycopy(arrayOfObject, 2, arrayOfObject1, i, j - 2);
      object.checkN(arrayOfObject1, paramCallContext);
      return;
    } 
    paramCallContext.writeValue(applyN(arrayOfObject));
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    // Byte code:
    //   0: aload_0
    //   1: getfield kind : C
    //   4: bipush #80
    //   6: if_icmpne -> 39
    //   9: new java/lang/RuntimeException
    //   12: dup
    //   13: new java/lang/StringBuilder
    //   16: dup
    //   17: invokespecial <init> : ()V
    //   20: aload_0
    //   21: invokevirtual getName : ()Ljava/lang/String;
    //   24: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: ldc ': invoke-special not allowed at run time'
    //   29: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   32: invokevirtual toString : ()Ljava/lang/String;
    //   35: invokespecial <init> : (Ljava/lang/String;)V
    //   38: athrow
    //   39: aload_1
    //   40: arraylength
    //   41: istore #5
    //   43: aload_0
    //   44: iload #5
    //   46: invokestatic checkArgCount : (Lgnu/mapping/Procedure;I)V
    //   49: aload_1
    //   50: iconst_0
    //   51: aaload
    //   52: astore #8
    //   54: aload_0
    //   55: getfield kind : C
    //   58: bipush #86
    //   60: if_icmpeq -> 149
    //   63: aload_0
    //   64: getfield kind : C
    //   67: bipush #42
    //   69: if_icmpeq -> 149
    //   72: aload #8
    //   74: aload_0
    //   75: invokestatic typeFrom : (Ljava/lang/Object;Lgnu/kawa/reflect/Invoke;)Lgnu/bytecode/ObjectType;
    //   78: astore #8
    //   80: aload_0
    //   81: getfield kind : C
    //   84: bipush #78
    //   86: if_icmpne -> 453
    //   89: aconst_null
    //   90: astore #10
    //   92: aload #8
    //   94: instanceof gnu/expr/TypeValue
    //   97: ifeq -> 165
    //   100: aload #8
    //   102: checkcast gnu/expr/TypeValue
    //   105: invokeinterface getConstructor : ()Lgnu/mapping/Procedure;
    //   110: astore #9
    //   112: aload #9
    //   114: ifnull -> 165
    //   117: iload #5
    //   119: iconst_1
    //   120: isub
    //   121: istore_2
    //   122: iload_2
    //   123: anewarray java/lang/Object
    //   126: astore #8
    //   128: aload_1
    //   129: iconst_1
    //   130: aload #8
    //   132: iconst_0
    //   133: iload_2
    //   134: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   137: aload #9
    //   139: aload #8
    //   141: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   144: astore #8
    //   146: aload #8
    //   148: areturn
    //   149: aload #8
    //   151: invokevirtual getClass : ()Ljava/lang/Class;
    //   154: invokestatic make : (Ljava/lang/Class;)Lgnu/bytecode/Type;
    //   157: checkcast gnu/bytecode/ObjectType
    //   160: astore #8
    //   162: goto -> 80
    //   165: aload #8
    //   167: astore #9
    //   169: aload #8
    //   171: instanceof gnu/expr/PairClassType
    //   174: ifeq -> 187
    //   177: aload #8
    //   179: checkcast gnu/expr/PairClassType
    //   182: getfield instanceType : Lgnu/bytecode/ClassType;
    //   185: astore #9
    //   187: aload #9
    //   189: astore #8
    //   191: aload #9
    //   193: instanceof gnu/bytecode/ArrayType
    //   196: ifeq -> 458
    //   199: aload #9
    //   201: checkcast gnu/bytecode/ArrayType
    //   204: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   207: astore #11
    //   209: aload_1
    //   210: arraylength
    //   211: iconst_1
    //   212: isub
    //   213: istore #7
    //   215: iload #7
    //   217: iconst_2
    //   218: if_icmplt -> 408
    //   221: aload_1
    //   222: iconst_1
    //   223: aaload
    //   224: instanceof gnu/expr/Keyword
    //   227: ifeq -> 408
    //   230: aload_1
    //   231: iconst_1
    //   232: aaload
    //   233: checkcast gnu/expr/Keyword
    //   236: invokevirtual getName : ()Ljava/lang/String;
    //   239: astore #8
    //   241: ldc 'length'
    //   243: aload #8
    //   245: invokevirtual equals : (Ljava/lang/Object;)Z
    //   248: ifne -> 261
    //   251: ldc 'size'
    //   253: aload #8
    //   255: invokevirtual equals : (Ljava/lang/Object;)Z
    //   258: ifeq -> 408
    //   261: aload_1
    //   262: iconst_2
    //   263: aaload
    //   264: checkcast java/lang/Number
    //   267: invokevirtual intValue : ()I
    //   270: istore_3
    //   271: iconst_3
    //   272: istore_2
    //   273: iconst_1
    //   274: istore #4
    //   276: aload #11
    //   278: invokevirtual getReflectClass : ()Ljava/lang/Class;
    //   281: iload_3
    //   282: invokestatic newInstance : (Ljava/lang/Class;I)Ljava/lang/Object;
    //   285: astore #9
    //   287: iconst_0
    //   288: istore_3
    //   289: aload #9
    //   291: astore #8
    //   293: iload_2
    //   294: iload #7
    //   296: if_icmpgt -> 146
    //   299: aload_1
    //   300: iload_2
    //   301: aaload
    //   302: astore #10
    //   304: aload #10
    //   306: astore #8
    //   308: iload_2
    //   309: istore #5
    //   311: iload_3
    //   312: istore #6
    //   314: iload #4
    //   316: ifeq -> 381
    //   319: aload #10
    //   321: astore #8
    //   323: iload_2
    //   324: istore #5
    //   326: iload_3
    //   327: istore #6
    //   329: aload #10
    //   331: instanceof gnu/expr/Keyword
    //   334: ifeq -> 381
    //   337: aload #10
    //   339: astore #8
    //   341: iload_2
    //   342: istore #5
    //   344: iload_3
    //   345: istore #6
    //   347: iload_2
    //   348: iload #7
    //   350: if_icmpge -> 381
    //   353: aload #10
    //   355: checkcast gnu/expr/Keyword
    //   358: invokevirtual getName : ()Ljava/lang/String;
    //   361: astore #8
    //   363: aload #8
    //   365: invokestatic parseInt : (Ljava/lang/String;)I
    //   368: istore #6
    //   370: iload_2
    //   371: iconst_1
    //   372: iadd
    //   373: istore #5
    //   375: aload_1
    //   376: iload #5
    //   378: aaload
    //   379: astore #8
    //   381: aload #9
    //   383: iload #6
    //   385: aload #11
    //   387: aload #8
    //   389: invokevirtual coerceFromObject : (Ljava/lang/Object;)Ljava/lang/Object;
    //   392: invokestatic set : (Ljava/lang/Object;ILjava/lang/Object;)V
    //   395: iload #6
    //   397: iconst_1
    //   398: iadd
    //   399: istore_3
    //   400: iload #5
    //   402: iconst_1
    //   403: iadd
    //   404: istore_2
    //   405: goto -> 289
    //   408: iload #7
    //   410: istore_3
    //   411: iconst_1
    //   412: istore_2
    //   413: iconst_0
    //   414: istore #4
    //   416: goto -> 276
    //   419: astore_1
    //   420: new java/lang/RuntimeException
    //   423: dup
    //   424: new java/lang/StringBuilder
    //   427: dup
    //   428: invokespecial <init> : ()V
    //   431: ldc 'non-integer keyword ''
    //   433: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   436: aload #8
    //   438: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   441: ldc '' in array constructor'
    //   443: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   446: invokevirtual toString : ()Ljava/lang/String;
    //   449: invokespecial <init> : (Ljava/lang/String;)V
    //   452: athrow
    //   453: aload_1
    //   454: iconst_1
    //   455: aaload
    //   456: astore #10
    //   458: aload_0
    //   459: aload #8
    //   461: aload #10
    //   463: invokevirtual lookupMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/Object;)Lgnu/mapping/MethodProc;
    //   466: astore #10
    //   468: aload_0
    //   469: getfield kind : C
    //   472: bipush #78
    //   474: if_icmpeq -> 562
    //   477: aload_0
    //   478: getfield kind : C
    //   481: bipush #83
    //   483: if_icmpeq -> 495
    //   486: aload_0
    //   487: getfield kind : C
    //   490: bipush #115
    //   492: if_icmpne -> 557
    //   495: iconst_2
    //   496: istore_2
    //   497: iload #5
    //   499: iload_2
    //   500: isub
    //   501: anewarray java/lang/Object
    //   504: astore #8
    //   506: iconst_0
    //   507: istore_2
    //   508: aload_0
    //   509: getfield kind : C
    //   512: bipush #86
    //   514: if_icmpeq -> 526
    //   517: aload_0
    //   518: getfield kind : C
    //   521: bipush #42
    //   523: if_icmpne -> 537
    //   526: aload #8
    //   528: iconst_0
    //   529: aload_1
    //   530: iconst_0
    //   531: aaload
    //   532: aastore
    //   533: iconst_0
    //   534: iconst_1
    //   535: iadd
    //   536: istore_2
    //   537: aload_1
    //   538: iconst_2
    //   539: aload #8
    //   541: iload_2
    //   542: iload #5
    //   544: iconst_2
    //   545: isub
    //   546: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   549: aload #10
    //   551: aload #8
    //   553: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   556: areturn
    //   557: iconst_1
    //   558: istore_2
    //   559: goto -> 497
    //   562: invokestatic getInstance : ()Lgnu/mapping/CallContext;
    //   565: astore #9
    //   567: iconst_0
    //   568: istore_2
    //   569: iload_2
    //   570: aload_1
    //   571: arraylength
    //   572: if_icmpge -> 591
    //   575: aload_1
    //   576: iload_2
    //   577: aaload
    //   578: instanceof gnu/expr/Keyword
    //   581: ifne -> 591
    //   584: iload_2
    //   585: iconst_1
    //   586: iadd
    //   587: istore_2
    //   588: goto -> 569
    //   591: iconst_m1
    //   592: istore #4
    //   594: iload_2
    //   595: aload_1
    //   596: arraylength
    //   597: if_icmpne -> 770
    //   600: aload #10
    //   602: aload_1
    //   603: aload #9
    //   605: invokevirtual matchN : ([Ljava/lang/Object;Lgnu/mapping/CallContext;)I
    //   608: istore_3
    //   609: iload_3
    //   610: ifne -> 619
    //   613: aload #9
    //   615: invokevirtual runUntilValue : ()Ljava/lang/Object;
    //   618: areturn
    //   619: aload #8
    //   621: checkcast gnu/bytecode/ClassType
    //   624: ldc_w 'valueOf'
    //   627: iconst_0
    //   628: aload_0
    //   629: getfield language : Lgnu/expr/Language;
    //   632: invokestatic apply : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/expr/Language;)Lgnu/mapping/MethodProc;
    //   635: astore #11
    //   637: aload #11
    //   639: ifnull -> 688
    //   642: iload #5
    //   644: iconst_1
    //   645: isub
    //   646: anewarray java/lang/Object
    //   649: astore #12
    //   651: aload_1
    //   652: iconst_1
    //   653: aload #12
    //   655: iconst_0
    //   656: iload #5
    //   658: iconst_1
    //   659: isub
    //   660: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   663: aload #11
    //   665: aload #12
    //   667: aload #9
    //   669: invokevirtual matchN : ([Ljava/lang/Object;Lgnu/mapping/CallContext;)I
    //   672: istore #4
    //   674: iload #4
    //   676: istore_3
    //   677: iload #4
    //   679: ifne -> 688
    //   682: aload #9
    //   684: invokevirtual runUntilValue : ()Ljava/lang/Object;
    //   687: areturn
    //   688: aload #10
    //   690: aload_1
    //   691: iconst_0
    //   692: aaload
    //   693: invokevirtual apply1 : (Ljava/lang/Object;)Ljava/lang/Object;
    //   696: astore #9
    //   698: iload_3
    //   699: istore #4
    //   701: iload_2
    //   702: istore_3
    //   703: iload_3
    //   704: iconst_1
    //   705: iadd
    //   706: aload_1
    //   707: arraylength
    //   708: if_icmpge -> 724
    //   711: aload_1
    //   712: iload_3
    //   713: aaload
    //   714: astore #11
    //   716: aload #11
    //   718: instanceof gnu/expr/Keyword
    //   721: ifne -> 797
    //   724: iload_2
    //   725: aload_1
    //   726: arraylength
    //   727: if_icmpne -> 732
    //   730: iconst_1
    //   731: istore_3
    //   732: iload_3
    //   733: aload_1
    //   734: arraylength
    //   735: if_icmpeq -> 855
    //   738: aload #8
    //   740: checkcast gnu/bytecode/ClassType
    //   743: ldc_w 'add'
    //   746: iconst_0
    //   747: aload_0
    //   748: getfield language : Lgnu/expr/Language;
    //   751: invokestatic apply : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/expr/Language;)Lgnu/mapping/MethodProc;
    //   754: astore #8
    //   756: aload #8
    //   758: ifnonnull -> 831
    //   761: iload #4
    //   763: aload #10
    //   765: aload_1
    //   766: invokestatic matchFailAsException : (ILgnu/mapping/Procedure;[Ljava/lang/Object;)Ljava/lang/RuntimeException;
    //   769: athrow
    //   770: iload_2
    //   771: anewarray java/lang/Object
    //   774: astore #9
    //   776: aload_1
    //   777: iconst_0
    //   778: aload #9
    //   780: iconst_0
    //   781: iload_2
    //   782: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   785: aload #10
    //   787: aload #9
    //   789: invokevirtual applyN : ([Ljava/lang/Object;)Ljava/lang/Object;
    //   792: astore #9
    //   794: goto -> 701
    //   797: aload #11
    //   799: checkcast gnu/expr/Keyword
    //   802: astore #11
    //   804: aload_1
    //   805: iload_3
    //   806: iconst_1
    //   807: iadd
    //   808: aaload
    //   809: astore #12
    //   811: iconst_0
    //   812: aload #9
    //   814: aload #11
    //   816: invokevirtual getName : ()Ljava/lang/String;
    //   819: aload #12
    //   821: invokestatic apply : (ZLjava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)V
    //   824: iload_3
    //   825: iconst_2
    //   826: iadd
    //   827: istore_3
    //   828: goto -> 703
    //   831: iload_3
    //   832: aload_1
    //   833: arraylength
    //   834: if_icmpge -> 855
    //   837: aload #8
    //   839: aload #9
    //   841: aload_1
    //   842: iload_3
    //   843: aaload
    //   844: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   847: pop
    //   848: iload_3
    //   849: iconst_1
    //   850: iadd
    //   851: istore_3
    //   852: goto -> 831
    //   855: aload #9
    //   857: areturn
    // Exception table:
    //   from	to	target	type
    //   363	370	419	java/lang/Throwable
  }
  
  protected MethodProc lookupMethods(ObjectType paramObjectType, Object paramObject) {
    byte b = 80;
    if (this.kind == 'N') {
      paramObject = "<init>";
    } else {
      if (paramObject instanceof String || paramObject instanceof gnu.lists.FString) {
        paramObject = paramObject.toString();
      } else if (paramObject instanceof Symbol) {
        paramObject = ((Symbol)paramObject).getName();
      } else {
        throw new WrongType(this, 1, null);
      } 
      paramObject = Compilation.mangleName((String)paramObject);
    } 
    if (this.kind != 'P')
      if (this.kind == '*' || this.kind == 'V') {
        b = 86;
      } else {
        b = 0;
      }  
    MethodProc methodProc = ClassMethods.apply(paramObjectType, (String)paramObject, b, this.language);
    if (methodProc == null)
      throw new RuntimeException(getName() + ": no method named `" + paramObject + "' in class " + paramObjectType.getName()); 
    return methodProc;
  }
  
  public int numArgs() {
    if (this.kind == 'N') {
      boolean bool = true;
      return bool | 0xFFFFF000;
    } 
    byte b = 2;
    return b | 0xFFFFF000;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/Invoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */