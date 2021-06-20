package gnu.kawa.reflect;

import gnu.bytecode.ClassType;
import gnu.bytecode.Member;
import gnu.bytecode.Method;
import gnu.bytecode.ObjectType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.ClassExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Keyword;
import gnu.expr.PrimProcedure;
import gnu.mapping.Procedure;

public class CompileInvoke {
  private static void append(PrimProcedure[] paramArrayOfPrimProcedure, int paramInt, StringBuffer paramStringBuffer) {
    for (int i = 0; i < paramInt; i++) {
      paramStringBuffer.append("\n  candidate: ");
      paramStringBuffer.append(paramArrayOfPrimProcedure[i]);
    } 
  }
  
  static Object[] checkKeywords(ObjectType paramObjectType, Expression[] paramArrayOfExpression, int paramInt, ClassType paramClassType) {
    int j = paramArrayOfExpression.length;
    int i;
    for (i = 0; i * 2 + paramInt + 1 < j && paramArrayOfExpression[i * 2 + paramInt].valueIfConstant() instanceof Keyword; i++);
    Object[] arrayOfObject = new Object[i];
    for (j = 0; j < i; j++) {
      Method method;
      String str1;
      String str2 = ((Keyword)paramArrayOfExpression[j * 2 + paramInt].valueIfConstant()).getName();
      Member member2 = SlotSet.lookupMember(paramObjectType, str2, paramClassType);
      Member member1 = member2;
      if (member2 == null)
        method = paramObjectType.getMethod(ClassExp.slotToMethodName("add", str2), SlotSet.type1Array); 
      if (method == null)
        str1 = str2; 
      arrayOfObject[j] = str1;
    } 
    return arrayOfObject;
  }
  
  private static String getMethodName(Expression[] paramArrayOfExpression, char paramChar) {
    if (paramChar == 'N')
      return "<init>"; 
    if (paramChar == 'P') {
      paramChar = '\002';
    } else {
      paramChar = '\001';
    } 
    return (paramArrayOfExpression.length >= paramChar + 1) ? ClassMethods.checkName(paramArrayOfExpression[paramChar], false) : null;
  }
  
  protected static PrimProcedure[] getMethods(ObjectType paramObjectType, String paramString, ClassType paramClassType, Invoke paramInvoke) {
    byte b = 80;
    char c = paramInvoke.kind;
    if (c != 'P') {
      if (c == '*' || c == 'V') {
        b = 86;
        return ClassMethods.getMethods(paramObjectType, paramString, b, paramClassType, paramInvoke.language);
      } 
      b = 0;
    } 
    return ClassMethods.getMethods(paramObjectType, paramString, b, paramClassType, paramInvoke.language);
  }
  
  public static PrimProcedure getStaticMethod(ClassType paramClassType, String paramString, Expression[] paramArrayOfExpression) {
    // Byte code:
    //   0: ldc gnu/kawa/reflect/CompileInvoke
    //   2: monitorenter
    //   3: aload_0
    //   4: aload_1
    //   5: aconst_null
    //   6: getstatic gnu/kawa/reflect/Invoke.invokeStatic : Lgnu/kawa/reflect/Invoke;
    //   9: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;Lgnu/kawa/reflect/Invoke;)[Lgnu/expr/PrimProcedure;
    //   12: astore_1
    //   13: aload_1
    //   14: aload_0
    //   15: aload_2
    //   16: aload_2
    //   17: arraylength
    //   18: iconst_0
    //   19: iconst_m1
    //   20: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;Lgnu/bytecode/ObjectType;[Lgnu/expr/Expression;III)J
    //   23: lstore #5
    //   25: lload #5
    //   27: bipush #32
    //   29: lshr
    //   30: l2i
    //   31: istore_3
    //   32: lload #5
    //   34: l2i
    //   35: istore #4
    //   37: aload_1
    //   38: ifnonnull -> 54
    //   41: iconst_m1
    //   42: istore_3
    //   43: iload_3
    //   44: ifge -> 67
    //   47: aconst_null
    //   48: astore_0
    //   49: ldc gnu/kawa/reflect/CompileInvoke
    //   51: monitorexit
    //   52: aload_0
    //   53: areturn
    //   54: iload_3
    //   55: ifle -> 80
    //   58: aload_1
    //   59: iload_3
    //   60: invokestatic mostSpecific : ([Lgnu/mapping/MethodProc;I)I
    //   63: istore_3
    //   64: goto -> 43
    //   67: aload_1
    //   68: iload_3
    //   69: aaload
    //   70: astore_0
    //   71: goto -> 49
    //   74: astore_0
    //   75: ldc gnu/kawa/reflect/CompileInvoke
    //   77: monitorexit
    //   78: aload_0
    //   79: athrow
    //   80: iload #4
    //   82: iconst_1
    //   83: if_icmpne -> 91
    //   86: iconst_0
    //   87: istore_3
    //   88: goto -> 43
    //   91: iconst_m1
    //   92: istore_3
    //   93: goto -> 43
    // Exception table:
    //   from	to	target	type
    //   3	25	74	finally
    //   58	64	74	finally
  }
  
  static int hasKeywordArgument(int paramInt, Expression[] paramArrayOfExpression) {
    while (paramInt < paramArrayOfExpression.length) {
      if (paramArrayOfExpression[paramInt].valueIfConstant() instanceof Keyword)
        return paramInt; 
      paramInt++;
    } 
    return paramArrayOfExpression.length;
  }
  
  private static long selectApplicable(PrimProcedure[] paramArrayOfPrimProcedure, ObjectType paramObjectType, Expression[] paramArrayOfExpression, int paramInt1, int paramInt2, int paramInt3) {
    Type[] arrayOfType = new Type[paramInt1];
    paramInt1 = 0;
    if (paramInt3 >= 0) {
      arrayOfType[0] = (Type)paramObjectType;
      paramInt1 = 0 + 1;
    } 
    while (paramInt2 < paramArrayOfExpression.length && paramInt1 < arrayOfType.length) {
      Type type;
      Expression expression = paramArrayOfExpression[paramInt2];
      paramObjectType = null;
      if (InlineCalls.checkIntValue(expression) != null) {
        PrimType primType = Type.intType;
      } else if (InlineCalls.checkLongValue(expression) != null) {
        PrimType primType = Type.longType;
      } else if (!false) {
        type = expression.getType();
      } 
      arrayOfType[paramInt1] = type;
      paramInt2++;
      paramInt1++;
    } 
    return ClassMethods.selectApplicable(paramArrayOfPrimProcedure, arrayOfType);
  }
  
  public static Expression validateApplyInvoke(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    // Byte code:
    //   0: aload_3
    //   1: checkcast gnu/kawa/reflect/Invoke
    //   4: astore #27
    //   6: aload #27
    //   8: getfield kind : C
    //   11: istore #4
    //   13: aload_1
    //   14: invokevirtual getCompilation : ()Lgnu/expr/Compilation;
    //   17: astore #26
    //   19: aload_0
    //   20: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   23: astore #25
    //   25: aload #25
    //   27: arraylength
    //   28: istore #14
    //   30: aload #26
    //   32: getfield mustCompile : Z
    //   35: ifeq -> 63
    //   38: iload #14
    //   40: ifeq -> 63
    //   43: iload #4
    //   45: bipush #86
    //   47: if_icmpeq -> 57
    //   50: iload #4
    //   52: bipush #42
    //   54: if_icmpne -> 70
    //   57: iload #14
    //   59: iconst_1
    //   60: if_icmpne -> 70
    //   63: aload_0
    //   64: aload_1
    //   65: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   68: aload_0
    //   69: areturn
    //   70: aload_1
    //   71: aload #25
    //   73: iconst_0
    //   74: aaload
    //   75: aconst_null
    //   76: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   79: astore #29
    //   81: aload #25
    //   83: iconst_0
    //   84: aload #29
    //   86: aastore
    //   87: iload #4
    //   89: bipush #86
    //   91: if_icmpeq -> 101
    //   94: iload #4
    //   96: bipush #42
    //   98: if_icmpne -> 374
    //   101: aload #29
    //   103: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   106: astore_3
    //   107: aload_3
    //   108: instanceof gnu/expr/PairClassType
    //   111: ifeq -> 388
    //   114: iload #4
    //   116: bipush #78
    //   118: if_icmpne -> 388
    //   121: aload_3
    //   122: checkcast gnu/expr/PairClassType
    //   125: getfield instanceType : Lgnu/bytecode/ClassType;
    //   128: astore #21
    //   130: aload #25
    //   132: iload #4
    //   134: invokestatic getMethodName : ([Lgnu/expr/Expression;C)Ljava/lang/String;
    //   137: astore #23
    //   139: iload #4
    //   141: bipush #86
    //   143: if_icmpeq -> 153
    //   146: iload #4
    //   148: bipush #42
    //   150: if_icmpne -> 410
    //   153: iload #14
    //   155: iconst_1
    //   156: isub
    //   157: istore #6
    //   159: iconst_2
    //   160: istore #5
    //   162: iconst_0
    //   163: istore #7
    //   165: iload #4
    //   167: bipush #78
    //   169: if_icmpne -> 807
    //   172: aload #21
    //   174: instanceof gnu/bytecode/ArrayType
    //   177: ifeq -> 807
    //   180: aload #21
    //   182: checkcast gnu/bytecode/ArrayType
    //   185: astore #21
    //   187: aload #21
    //   189: invokevirtual getComponentType : ()Lgnu/bytecode/Type;
    //   192: astore #20
    //   194: aconst_null
    //   195: astore_3
    //   196: iconst_0
    //   197: istore #5
    //   199: iload #5
    //   201: istore #7
    //   203: aload_3
    //   204: astore_2
    //   205: aload #25
    //   207: arraylength
    //   208: iconst_3
    //   209: if_icmplt -> 298
    //   212: iload #5
    //   214: istore #7
    //   216: aload_3
    //   217: astore_2
    //   218: aload #25
    //   220: iconst_1
    //   221: aaload
    //   222: instanceof gnu/expr/QuoteExp
    //   225: ifeq -> 298
    //   228: aload #25
    //   230: iconst_1
    //   231: aaload
    //   232: checkcast gnu/expr/QuoteExp
    //   235: invokevirtual getValue : ()Ljava/lang/Object;
    //   238: astore #22
    //   240: iload #5
    //   242: istore #7
    //   244: aload_3
    //   245: astore_2
    //   246: aload #22
    //   248: instanceof gnu/expr/Keyword
    //   251: ifeq -> 298
    //   254: aload #22
    //   256: checkcast gnu/expr/Keyword
    //   259: invokevirtual getName : ()Ljava/lang/String;
    //   262: astore #22
    //   264: ldc 'length'
    //   266: aload #22
    //   268: invokevirtual equals : (Ljava/lang/Object;)Z
    //   271: ifne -> 290
    //   274: iload #5
    //   276: istore #7
    //   278: aload_3
    //   279: astore_2
    //   280: ldc 'size'
    //   282: aload #22
    //   284: invokevirtual equals : (Ljava/lang/Object;)Z
    //   287: ifeq -> 298
    //   290: aload #25
    //   292: iconst_2
    //   293: aaload
    //   294: astore_2
    //   295: iconst_1
    //   296: istore #7
    //   298: aload_2
    //   299: astore_3
    //   300: aload_2
    //   301: ifnonnull -> 320
    //   304: new java/lang/Integer
    //   307: dup
    //   308: aload #25
    //   310: arraylength
    //   311: iconst_1
    //   312: isub
    //   313: invokespecial <init> : (I)V
    //   316: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   319: astore_3
    //   320: aload_1
    //   321: aload_3
    //   322: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   325: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   328: astore_2
    //   329: new gnu/expr/ApplyExp
    //   332: dup
    //   333: new gnu/kawa/reflect/ArrayNew
    //   336: dup
    //   337: aload #20
    //   339: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   342: iconst_1
    //   343: anewarray gnu/expr/Expression
    //   346: dup
    //   347: iconst_0
    //   348: aload_2
    //   349: aastore
    //   350: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   353: astore_2
    //   354: aload_2
    //   355: aload #21
    //   357: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   360: iload #7
    //   362: ifeq -> 488
    //   365: aload #25
    //   367: arraylength
    //   368: iconst_3
    //   369: if_icmpne -> 488
    //   372: aload_2
    //   373: areturn
    //   374: aload #27
    //   376: getfield language : Lgnu/expr/Language;
    //   379: aload #29
    //   381: invokevirtual getTypeFor : (Lgnu/expr/Expression;)Lgnu/bytecode/Type;
    //   384: astore_3
    //   385: goto -> 107
    //   388: aload_3
    //   389: instanceof gnu/bytecode/ObjectType
    //   392: ifeq -> 404
    //   395: aload_3
    //   396: checkcast gnu/bytecode/ObjectType
    //   399: astore #21
    //   401: goto -> 130
    //   404: aconst_null
    //   405: astore #21
    //   407: goto -> 130
    //   410: iload #4
    //   412: bipush #78
    //   414: if_icmpne -> 430
    //   417: iload #14
    //   419: istore #6
    //   421: iconst_0
    //   422: istore #5
    //   424: iconst_m1
    //   425: istore #7
    //   427: goto -> 165
    //   430: iload #4
    //   432: bipush #83
    //   434: if_icmpeq -> 444
    //   437: iload #4
    //   439: bipush #115
    //   441: if_icmpne -> 459
    //   444: iload #14
    //   446: iconst_2
    //   447: isub
    //   448: istore #6
    //   450: iconst_2
    //   451: istore #5
    //   453: iconst_m1
    //   454: istore #7
    //   456: goto -> 165
    //   459: iload #4
    //   461: bipush #80
    //   463: if_icmpne -> 481
    //   466: iload #14
    //   468: iconst_2
    //   469: isub
    //   470: istore #6
    //   472: iconst_3
    //   473: istore #5
    //   475: iconst_1
    //   476: istore #7
    //   478: goto -> 165
    //   481: aload_0
    //   482: aload_1
    //   483: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   486: aload_0
    //   487: areturn
    //   488: new gnu/expr/LetExp
    //   491: dup
    //   492: iconst_1
    //   493: anewarray gnu/expr/Expression
    //   496: dup
    //   497: iconst_0
    //   498: aload_2
    //   499: aastore
    //   500: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   503: astore #22
    //   505: aload #22
    //   507: aconst_null
    //   508: checkcast java/lang/String
    //   511: aload #21
    //   513: invokevirtual addDeclaration : (Ljava/lang/Object;Lgnu/bytecode/Type;)Lgnu/expr/Declaration;
    //   516: astore #21
    //   518: aload #21
    //   520: aload_2
    //   521: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   524: new gnu/expr/BeginExp
    //   527: dup
    //   528: invokespecial <init> : ()V
    //   531: astore #23
    //   533: iconst_0
    //   534: istore #6
    //   536: iload #7
    //   538: ifeq -> 743
    //   541: iconst_3
    //   542: istore #5
    //   544: iload #5
    //   546: aload #25
    //   548: arraylength
    //   549: if_icmpge -> 783
    //   552: aload #25
    //   554: iload #5
    //   556: aaload
    //   557: astore_3
    //   558: aload_3
    //   559: astore_2
    //   560: iload #5
    //   562: istore #8
    //   564: iload #6
    //   566: istore #9
    //   568: iload #7
    //   570: ifeq -> 664
    //   573: aload_3
    //   574: astore_2
    //   575: iload #5
    //   577: istore #8
    //   579: iload #6
    //   581: istore #9
    //   583: iload #5
    //   585: iconst_1
    //   586: iadd
    //   587: aload #25
    //   589: arraylength
    //   590: if_icmpge -> 664
    //   593: aload_3
    //   594: astore_2
    //   595: iload #5
    //   597: istore #8
    //   599: iload #6
    //   601: istore #9
    //   603: aload_3
    //   604: instanceof gnu/expr/QuoteExp
    //   607: ifeq -> 664
    //   610: aload_3
    //   611: checkcast gnu/expr/QuoteExp
    //   614: invokevirtual getValue : ()Ljava/lang/Object;
    //   617: astore #24
    //   619: aload_3
    //   620: astore_2
    //   621: iload #5
    //   623: istore #8
    //   625: iload #6
    //   627: istore #9
    //   629: aload #24
    //   631: instanceof gnu/expr/Keyword
    //   634: ifeq -> 664
    //   637: aload #24
    //   639: checkcast gnu/expr/Keyword
    //   642: invokevirtual getName : ()Ljava/lang/String;
    //   645: astore_2
    //   646: aload_2
    //   647: invokestatic parseInt : (Ljava/lang/String;)I
    //   650: istore #9
    //   652: iload #5
    //   654: iconst_1
    //   655: iadd
    //   656: istore #8
    //   658: aload #25
    //   660: iload #8
    //   662: aaload
    //   663: astore_2
    //   664: aload_1
    //   665: aload_2
    //   666: aload #20
    //   668: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   671: astore_2
    //   672: aload #23
    //   674: new gnu/expr/ApplyExp
    //   677: dup
    //   678: new gnu/kawa/reflect/ArraySet
    //   681: dup
    //   682: aload #20
    //   684: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   687: iconst_3
    //   688: anewarray gnu/expr/Expression
    //   691: dup
    //   692: iconst_0
    //   693: new gnu/expr/ReferenceExp
    //   696: dup
    //   697: aload #21
    //   699: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   702: aastore
    //   703: dup
    //   704: iconst_1
    //   705: new java/lang/Integer
    //   708: dup
    //   709: iload #9
    //   711: invokespecial <init> : (I)V
    //   714: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   717: aastore
    //   718: dup
    //   719: iconst_2
    //   720: aload_2
    //   721: aastore
    //   722: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   725: invokevirtual add : (Lgnu/expr/Expression;)V
    //   728: iload #9
    //   730: iconst_1
    //   731: iadd
    //   732: istore #6
    //   734: iload #8
    //   736: iconst_1
    //   737: iadd
    //   738: istore #5
    //   740: goto -> 544
    //   743: iconst_1
    //   744: istore #5
    //   746: goto -> 544
    //   749: astore_1
    //   750: aload #26
    //   752: bipush #101
    //   754: new java/lang/StringBuilder
    //   757: dup
    //   758: invokespecial <init> : ()V
    //   761: ldc 'non-integer keyword ''
    //   763: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   766: aload_2
    //   767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: ldc '' in array constructor'
    //   772: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   775: invokevirtual toString : ()Ljava/lang/String;
    //   778: invokevirtual error : (CLjava/lang/String;)V
    //   781: aload_0
    //   782: areturn
    //   783: aload #23
    //   785: new gnu/expr/ReferenceExp
    //   788: dup
    //   789: aload #21
    //   791: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   794: invokevirtual add : (Lgnu/expr/Expression;)V
    //   797: aload #22
    //   799: aload #23
    //   801: putfield body : Lgnu/expr/Expression;
    //   804: aload #22
    //   806: areturn
    //   807: aload #21
    //   809: ifnull -> 2721
    //   812: aload #23
    //   814: ifnull -> 2721
    //   817: aload #21
    //   819: instanceof gnu/expr/TypeValue
    //   822: ifeq -> 882
    //   825: iload #4
    //   827: bipush #78
    //   829: if_icmpne -> 882
    //   832: aload #21
    //   834: checkcast gnu/expr/TypeValue
    //   837: invokeinterface getConstructor : ()Lgnu/mapping/Procedure;
    //   842: astore_3
    //   843: aload_3
    //   844: ifnull -> 882
    //   847: iload #14
    //   849: iconst_1
    //   850: isub
    //   851: anewarray gnu/expr/Expression
    //   854: astore_0
    //   855: aload #25
    //   857: iconst_1
    //   858: aload_0
    //   859: iconst_0
    //   860: iload #14
    //   862: iconst_1
    //   863: isub
    //   864: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   867: aload_1
    //   868: new gnu/expr/ApplyExp
    //   871: dup
    //   872: aload_3
    //   873: aload_0
    //   874: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   877: aload_2
    //   878: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   881: areturn
    //   882: aload #26
    //   884: ifnonnull -> 1101
    //   887: aconst_null
    //   888: astore #22
    //   890: aload #21
    //   892: aload #23
    //   894: aload #22
    //   896: aload #27
    //   898: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;Lgnu/kawa/reflect/Invoke;)[Lgnu/expr/PrimProcedure;
    //   901: astore #24
    //   903: aload #24
    //   905: iload #6
    //   907: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;I)I
    //   910: istore #16
    //   912: iconst_m1
    //   913: istore #15
    //   915: iload #4
    //   917: bipush #78
    //   919: if_icmpne -> 1646
    //   922: iconst_1
    //   923: aload #25
    //   925: invokestatic hasKeywordArgument : (I[Lgnu/expr/Expression;)I
    //   928: istore #8
    //   930: iload #8
    //   932: aload #25
    //   934: arraylength
    //   935: if_icmplt -> 966
    //   938: iload #16
    //   940: ifgt -> 1646
    //   943: aload #24
    //   945: iconst_1
    //   946: anewarray gnu/bytecode/Type
    //   949: dup
    //   950: iconst_0
    //   951: getstatic gnu/expr/Compilation.typeClassType : Lgnu/bytecode/ClassType;
    //   954: aastore
    //   955: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;[Lgnu/bytecode/Type;)J
    //   958: bipush #32
    //   960: lshr
    //   961: lconst_1
    //   962: lcmp
    //   963: ifne -> 1646
    //   966: aload #21
    //   968: aload #25
    //   970: iload #8
    //   972: aload #22
    //   974: invokestatic checkKeywords : (Lgnu/bytecode/ObjectType;[Lgnu/expr/Expression;ILgnu/bytecode/ClassType;)[Ljava/lang/Object;
    //   977: astore #28
    //   979: aload #28
    //   981: arraylength
    //   982: iconst_2
    //   983: imul
    //   984: aload #25
    //   986: arraylength
    //   987: iload #8
    //   989: isub
    //   990: if_icmpeq -> 1015
    //   993: aload #21
    //   995: ldc 'add'
    //   997: bipush #86
    //   999: aconst_null
    //   1000: aload #27
    //   1002: getfield language : Lgnu/expr/Language;
    //   1005: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;CLgnu/bytecode/ClassType;Lgnu/expr/Language;)[Lgnu/expr/PrimProcedure;
    //   1008: iconst_2
    //   1009: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;I)I
    //   1012: ifle -> 1646
    //   1015: aconst_null
    //   1016: astore_3
    //   1017: iconst_0
    //   1018: istore #5
    //   1020: iload #5
    //   1022: aload #28
    //   1024: arraylength
    //   1025: if_icmpge -> 1174
    //   1028: aload_3
    //   1029: astore #20
    //   1031: aload #28
    //   1033: iload #5
    //   1035: aaload
    //   1036: instanceof java/lang/String
    //   1039: ifeq -> 1089
    //   1042: aload_3
    //   1043: ifnonnull -> 1163
    //   1046: new java/lang/StringBuffer
    //   1049: dup
    //   1050: invokespecial <init> : ()V
    //   1053: astore_3
    //   1054: aload_3
    //   1055: ldc_w 'no field or setter '
    //   1058: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1061: pop
    //   1062: aload_3
    //   1063: bipush #96
    //   1065: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1068: pop
    //   1069: aload_3
    //   1070: aload #28
    //   1072: iload #5
    //   1074: aaload
    //   1075: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuffer;
    //   1078: pop
    //   1079: aload_3
    //   1080: bipush #39
    //   1082: invokevirtual append : (C)Ljava/lang/StringBuffer;
    //   1085: pop
    //   1086: aload_3
    //   1087: astore #20
    //   1089: iload #5
    //   1091: iconst_1
    //   1092: iadd
    //   1093: istore #5
    //   1095: aload #20
    //   1097: astore_3
    //   1098: goto -> 1020
    //   1101: aload #26
    //   1103: getfield curClass : Lgnu/bytecode/ClassType;
    //   1106: ifnull -> 1119
    //   1109: aload #26
    //   1111: getfield curClass : Lgnu/bytecode/ClassType;
    //   1114: astore #22
    //   1116: goto -> 890
    //   1119: aload #26
    //   1121: getfield mainClass : Lgnu/bytecode/ClassType;
    //   1124: astore #22
    //   1126: goto -> 890
    //   1129: astore_1
    //   1130: aload #26
    //   1132: bipush #119
    //   1134: new java/lang/StringBuilder
    //   1137: dup
    //   1138: invokespecial <init> : ()V
    //   1141: ldc_w 'unknown class: '
    //   1144: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1147: aload #21
    //   1149: invokevirtual getName : ()Ljava/lang/String;
    //   1152: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1155: invokevirtual toString : ()Ljava/lang/String;
    //   1158: invokevirtual error : (CLjava/lang/String;)V
    //   1161: aload_0
    //   1162: areturn
    //   1163: aload_3
    //   1164: ldc_w ', '
    //   1167: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1170: pop
    //   1171: goto -> 1062
    //   1174: aload_3
    //   1175: ifnull -> 1209
    //   1178: aload_3
    //   1179: ldc_w ' in class '
    //   1182: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1185: pop
    //   1186: aload_3
    //   1187: aload #21
    //   1189: invokevirtual getName : ()Ljava/lang/String;
    //   1192: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   1195: pop
    //   1196: aload #26
    //   1198: bipush #119
    //   1200: aload_3
    //   1201: invokevirtual toString : ()Ljava/lang/String;
    //   1204: invokevirtual error : (CLjava/lang/String;)V
    //   1207: aload_0
    //   1208: areturn
    //   1209: iload #8
    //   1211: aload #25
    //   1213: arraylength
    //   1214: if_icmpge -> 1404
    //   1217: iload #8
    //   1219: anewarray gnu/expr/Expression
    //   1222: astore_3
    //   1223: aload #25
    //   1225: iconst_0
    //   1226: aload_3
    //   1227: iconst_0
    //   1228: iload #8
    //   1230: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   1233: aload_1
    //   1234: new gnu/expr/ApplyExp
    //   1237: dup
    //   1238: aload_0
    //   1239: invokevirtual getFunction : ()Lgnu/expr/Expression;
    //   1242: aload_3
    //   1243: invokespecial <init> : (Lgnu/expr/Expression;[Lgnu/expr/Expression;)V
    //   1246: aload #21
    //   1248: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   1251: checkcast gnu/expr/ApplyExp
    //   1254: astore_3
    //   1255: aload_3
    //   1256: aload #21
    //   1258: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   1261: aload_3
    //   1262: astore #20
    //   1264: aload #25
    //   1266: arraylength
    //   1267: ifle -> 1634
    //   1270: iconst_0
    //   1271: istore #5
    //   1273: aload_3
    //   1274: astore #20
    //   1276: iload #5
    //   1278: aload #28
    //   1280: arraylength
    //   1281: if_icmpge -> 1453
    //   1284: aload #28
    //   1286: iload #5
    //   1288: aaload
    //   1289: astore #23
    //   1291: aload #23
    //   1293: instanceof gnu/bytecode/Method
    //   1296: ifeq -> 1428
    //   1299: aload #23
    //   1301: checkcast gnu/bytecode/Method
    //   1304: invokevirtual getParameterTypes : ()[Lgnu/bytecode/Type;
    //   1307: iconst_0
    //   1308: aaload
    //   1309: astore_3
    //   1310: aload_3
    //   1311: astore #22
    //   1313: aload_3
    //   1314: ifnull -> 1328
    //   1317: aload #27
    //   1319: getfield language : Lgnu/expr/Language;
    //   1322: aload_3
    //   1323: invokevirtual getLangTypeFor : (Lgnu/bytecode/Type;)Lgnu/bytecode/Type;
    //   1326: astore #22
    //   1328: aload_1
    //   1329: aload #25
    //   1331: iload #5
    //   1333: iconst_2
    //   1334: imul
    //   1335: iload #8
    //   1337: iadd
    //   1338: iconst_1
    //   1339: iadd
    //   1340: aaload
    //   1341: aload #22
    //   1343: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   1346: astore_3
    //   1347: new gnu/expr/QuoteExp
    //   1350: dup
    //   1351: aload #23
    //   1353: invokespecial <init> : (Ljava/lang/Object;)V
    //   1356: astore #22
    //   1358: new gnu/expr/ApplyExp
    //   1361: dup
    //   1362: getstatic gnu/kawa/reflect/SlotSet.setFieldReturnObject : Lgnu/kawa/reflect/SlotSet;
    //   1365: iconst_3
    //   1366: anewarray gnu/expr/Expression
    //   1369: dup
    //   1370: iconst_0
    //   1371: aload #20
    //   1373: aastore
    //   1374: dup
    //   1375: iconst_1
    //   1376: aload #22
    //   1378: aastore
    //   1379: dup
    //   1380: iconst_2
    //   1381: aload_3
    //   1382: aastore
    //   1383: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1386: astore #20
    //   1388: aload #20
    //   1390: aload #21
    //   1392: invokevirtual setType : (Lgnu/bytecode/Type;)V
    //   1395: iload #5
    //   1397: iconst_1
    //   1398: iadd
    //   1399: istore #5
    //   1401: goto -> 1276
    //   1404: new gnu/expr/ApplyExp
    //   1407: dup
    //   1408: aload #24
    //   1410: iconst_0
    //   1411: aaload
    //   1412: iconst_1
    //   1413: anewarray gnu/expr/Expression
    //   1416: dup
    //   1417: iconst_0
    //   1418: aload #29
    //   1420: aastore
    //   1421: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1424: astore_3
    //   1425: goto -> 1255
    //   1428: aload #23
    //   1430: instanceof gnu/bytecode/Field
    //   1433: ifeq -> 1448
    //   1436: aload #23
    //   1438: checkcast gnu/bytecode/Field
    //   1441: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   1444: astore_3
    //   1445: goto -> 1310
    //   1448: aconst_null
    //   1449: astore_3
    //   1450: goto -> 1310
    //   1453: iload #8
    //   1455: aload #25
    //   1457: arraylength
    //   1458: if_icmpne -> 1602
    //   1461: iconst_1
    //   1462: istore #5
    //   1464: aload #20
    //   1466: astore_3
    //   1467: aload_3
    //   1468: astore #20
    //   1470: iload #5
    //   1472: aload #25
    //   1474: arraylength
    //   1475: if_icmpge -> 1634
    //   1478: new gnu/expr/LetExp
    //   1481: dup
    //   1482: iconst_1
    //   1483: anewarray gnu/expr/Expression
    //   1486: dup
    //   1487: iconst_0
    //   1488: aload_3
    //   1489: aastore
    //   1490: invokespecial <init> : ([Lgnu/expr/Expression;)V
    //   1493: astore #20
    //   1495: aload #20
    //   1497: aconst_null
    //   1498: checkcast java/lang/String
    //   1501: aload #21
    //   1503: invokevirtual addDeclaration : (Ljava/lang/Object;Lgnu/bytecode/Type;)Lgnu/expr/Declaration;
    //   1506: astore #21
    //   1508: aload #21
    //   1510: aload_3
    //   1511: invokevirtual noteValue : (Lgnu/expr/Expression;)V
    //   1514: new gnu/expr/BeginExp
    //   1517: dup
    //   1518: invokespecial <init> : ()V
    //   1521: astore_3
    //   1522: iload #5
    //   1524: aload #25
    //   1526: arraylength
    //   1527: if_icmpge -> 1615
    //   1530: new gnu/expr/ReferenceExp
    //   1533: dup
    //   1534: aload #21
    //   1536: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   1539: astore #22
    //   1541: ldc 'add'
    //   1543: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   1546: astore #23
    //   1548: aload #25
    //   1550: iload #5
    //   1552: aaload
    //   1553: astore #24
    //   1555: aload_3
    //   1556: aload_1
    //   1557: new gnu/expr/ApplyExp
    //   1560: dup
    //   1561: getstatic gnu/kawa/reflect/Invoke.invoke : Lgnu/kawa/reflect/Invoke;
    //   1564: iconst_3
    //   1565: anewarray gnu/expr/Expression
    //   1568: dup
    //   1569: iconst_0
    //   1570: aload #22
    //   1572: aastore
    //   1573: dup
    //   1574: iconst_1
    //   1575: aload #23
    //   1577: aastore
    //   1578: dup
    //   1579: iconst_2
    //   1580: aload #24
    //   1582: aastore
    //   1583: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   1586: aconst_null
    //   1587: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   1590: invokevirtual add : (Lgnu/expr/Expression;)V
    //   1593: iload #5
    //   1595: iconst_1
    //   1596: iadd
    //   1597: istore #5
    //   1599: goto -> 1522
    //   1602: aload #28
    //   1604: arraylength
    //   1605: iconst_2
    //   1606: imul
    //   1607: iload #8
    //   1609: iadd
    //   1610: istore #5
    //   1612: goto -> 1464
    //   1615: aload_3
    //   1616: new gnu/expr/ReferenceExp
    //   1619: dup
    //   1620: aload #21
    //   1622: invokespecial <init> : (Lgnu/expr/Declaration;)V
    //   1625: invokevirtual add : (Lgnu/expr/Expression;)V
    //   1628: aload #20
    //   1630: aload_3
    //   1631: putfield body : Lgnu/expr/Expression;
    //   1634: aload_1
    //   1635: aload #20
    //   1637: aload_0
    //   1638: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   1641: aload_2
    //   1642: invokevirtual checkType : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   1645: areturn
    //   1646: iload #16
    //   1648: iflt -> 2291
    //   1651: iconst_1
    //   1652: istore #8
    //   1654: iload #8
    //   1656: iload #14
    //   1658: if_icmpge -> 1922
    //   1661: aconst_null
    //   1662: astore_3
    //   1663: aconst_null
    //   1664: astore #20
    //   1666: iload #8
    //   1668: iload #14
    //   1670: iconst_1
    //   1671: isub
    //   1672: if_icmpne -> 1732
    //   1675: iconst_1
    //   1676: istore #9
    //   1678: iload #4
    //   1680: bipush #80
    //   1682: if_icmpne -> 1691
    //   1685: iload #8
    //   1687: iconst_2
    //   1688: if_icmpeq -> 1704
    //   1691: iload #4
    //   1693: bipush #78
    //   1695: if_icmpeq -> 1738
    //   1698: iload #8
    //   1700: iconst_1
    //   1701: if_icmpne -> 1738
    //   1704: aconst_null
    //   1705: astore #20
    //   1707: aload #25
    //   1709: iload #8
    //   1711: aload_1
    //   1712: aload #25
    //   1714: iload #8
    //   1716: aaload
    //   1717: aload #20
    //   1719: invokevirtual visit : (Lgnu/expr/Expression;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   1722: aastore
    //   1723: iload #8
    //   1725: iconst_1
    //   1726: iadd
    //   1727: istore #8
    //   1729: goto -> 1654
    //   1732: iconst_0
    //   1733: istore #9
    //   1735: goto -> 1678
    //   1738: iload #4
    //   1740: bipush #80
    //   1742: if_icmpne -> 1758
    //   1745: iload #8
    //   1747: iconst_1
    //   1748: if_icmpne -> 1758
    //   1751: aload #21
    //   1753: astore #20
    //   1755: goto -> 1707
    //   1758: iload #16
    //   1760: ifle -> 1707
    //   1763: iload #4
    //   1765: bipush #78
    //   1767: if_icmpne -> 1862
    //   1770: iconst_1
    //   1771: istore #10
    //   1773: iconst_0
    //   1774: istore #11
    //   1776: aload_3
    //   1777: astore #20
    //   1779: iload #11
    //   1781: iload #16
    //   1783: if_icmpge -> 1707
    //   1786: aload #24
    //   1788: iload #11
    //   1790: aaload
    //   1791: astore #20
    //   1793: iload #4
    //   1795: bipush #83
    //   1797: if_icmpeq -> 1869
    //   1800: aload #20
    //   1802: invokevirtual takesTarget : ()Z
    //   1805: ifeq -> 1869
    //   1808: iconst_1
    //   1809: istore #12
    //   1811: iload #8
    //   1813: iload #10
    //   1815: isub
    //   1816: iload #12
    //   1818: iadd
    //   1819: istore #12
    //   1821: iload #9
    //   1823: ifeq -> 1875
    //   1826: aload #20
    //   1828: invokevirtual takesVarArgs : ()Z
    //   1831: ifeq -> 1875
    //   1834: iload #12
    //   1836: aload #20
    //   1838: invokevirtual minArgs : ()I
    //   1841: if_icmpne -> 1875
    //   1844: aconst_null
    //   1845: astore_3
    //   1846: aload_3
    //   1847: astore #20
    //   1849: aload_3
    //   1850: ifnull -> 1707
    //   1853: iload #11
    //   1855: iconst_1
    //   1856: iadd
    //   1857: istore #11
    //   1859: goto -> 1776
    //   1862: iload #5
    //   1864: istore #10
    //   1866: goto -> 1773
    //   1869: iconst_0
    //   1870: istore #12
    //   1872: goto -> 1811
    //   1875: aload #20
    //   1877: iload #12
    //   1879: invokevirtual getParameterType : (I)Lgnu/bytecode/Type;
    //   1882: astore #20
    //   1884: iload #11
    //   1886: ifne -> 1895
    //   1889: aload #20
    //   1891: astore_3
    //   1892: goto -> 1846
    //   1895: aload #20
    //   1897: instanceof gnu/bytecode/PrimType
    //   1900: aload_3
    //   1901: instanceof gnu/bytecode/PrimType
    //   1904: if_icmpeq -> 1912
    //   1907: aconst_null
    //   1908: astore_3
    //   1909: goto -> 1846
    //   1912: aload_3
    //   1913: aload #20
    //   1915: invokestatic lowestCommonSuperType : (Lgnu/bytecode/Type;Lgnu/bytecode/Type;)Lgnu/bytecode/Type;
    //   1918: astore_3
    //   1919: goto -> 1846
    //   1922: aload #24
    //   1924: aload #21
    //   1926: aload #25
    //   1928: iload #6
    //   1930: iload #5
    //   1932: iload #7
    //   1934: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;Lgnu/bytecode/ObjectType;[Lgnu/expr/Expression;III)J
    //   1937: lstore #18
    //   1939: lload #18
    //   1941: bipush #32
    //   1943: lshr
    //   1944: l2i
    //   1945: istore #10
    //   1947: lload #18
    //   1949: l2i
    //   1950: istore #9
    //   1952: aload #24
    //   1954: arraylength
    //   1955: istore #17
    //   1957: aload #24
    //   1959: astore_3
    //   1960: iload #6
    //   1962: istore #12
    //   1964: iload #5
    //   1966: istore #8
    //   1968: iload #9
    //   1970: istore #13
    //   1972: iload #10
    //   1974: istore #11
    //   1976: iload #10
    //   1978: iload #9
    //   1980: iadd
    //   1981: ifne -> 2060
    //   1984: aload #24
    //   1986: astore_3
    //   1987: iload #6
    //   1989: istore #12
    //   1991: iload #5
    //   1993: istore #8
    //   1995: iload #9
    //   1997: istore #13
    //   1999: iload #10
    //   2001: istore #11
    //   2003: iload #4
    //   2005: bipush #78
    //   2007: if_icmpne -> 2060
    //   2010: aload #21
    //   2012: ldc_w 'valueOf'
    //   2015: aload #22
    //   2017: getstatic gnu/kawa/reflect/Invoke.invokeStatic : Lgnu/kawa/reflect/Invoke;
    //   2020: invokestatic getMethods : (Lgnu/bytecode/ObjectType;Ljava/lang/String;Lgnu/bytecode/ClassType;Lgnu/kawa/reflect/Invoke;)[Lgnu/expr/PrimProcedure;
    //   2023: astore_3
    //   2024: iconst_1
    //   2025: istore #8
    //   2027: iload #14
    //   2029: iconst_1
    //   2030: isub
    //   2031: istore #12
    //   2033: aload_3
    //   2034: aload #21
    //   2036: aload #25
    //   2038: iload #12
    //   2040: iconst_1
    //   2041: iconst_m1
    //   2042: invokestatic selectApplicable : ([Lgnu/expr/PrimProcedure;Lgnu/bytecode/ObjectType;[Lgnu/expr/Expression;III)J
    //   2045: lstore #18
    //   2047: lload #18
    //   2049: bipush #32
    //   2051: lshr
    //   2052: l2i
    //   2053: istore #11
    //   2055: lload #18
    //   2057: l2i
    //   2058: istore #13
    //   2060: iload #11
    //   2062: iload #13
    //   2064: iadd
    //   2065: ifne -> 2359
    //   2068: iload #4
    //   2070: bipush #80
    //   2072: if_icmpeq -> 2087
    //   2075: iload #15
    //   2077: istore #6
    //   2079: aload #26
    //   2081: invokevirtual warnInvokeUnknownMethod : ()Z
    //   2084: ifeq -> 2202
    //   2087: aload #23
    //   2089: astore #20
    //   2091: iload #4
    //   2093: bipush #78
    //   2095: if_icmpne -> 2121
    //   2098: new java/lang/StringBuilder
    //   2101: dup
    //   2102: invokespecial <init> : ()V
    //   2105: aload #23
    //   2107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2110: ldc_w '/valueOf'
    //   2113: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2116: invokevirtual toString : ()Ljava/lang/String;
    //   2119: astore #20
    //   2121: new java/lang/StringBuilder
    //   2124: dup
    //   2125: invokespecial <init> : ()V
    //   2128: astore #22
    //   2130: aload_3
    //   2131: arraylength
    //   2132: iload #17
    //   2134: iadd
    //   2135: ifne -> 2300
    //   2138: aload #22
    //   2140: ldc_w 'no accessible method ''
    //   2143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2146: pop
    //   2147: aload #22
    //   2149: aload #20
    //   2151: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2154: pop
    //   2155: aload #22
    //   2157: ldc_w '' in '
    //   2160: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2163: pop
    //   2164: aload #22
    //   2166: aload #21
    //   2168: invokevirtual getName : ()Ljava/lang/String;
    //   2171: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2174: pop
    //   2175: iload #4
    //   2177: bipush #80
    //   2179: if_icmpne -> 2352
    //   2182: bipush #101
    //   2184: istore #4
    //   2186: aload #26
    //   2188: iload #4
    //   2190: aload #22
    //   2192: invokevirtual toString : ()Ljava/lang/String;
    //   2195: invokevirtual error : (CLjava/lang/String;)V
    //   2198: iload #15
    //   2200: istore #6
    //   2202: iload #6
    //   2204: iflt -> 2721
    //   2207: iload #12
    //   2209: anewarray gnu/expr/Expression
    //   2212: astore #20
    //   2214: aload_3
    //   2215: iload #6
    //   2217: aaload
    //   2218: astore_3
    //   2219: aload_3
    //   2220: invokevirtual takesVarArgs : ()Z
    //   2223: pop
    //   2224: iconst_0
    //   2225: istore #5
    //   2227: iload #7
    //   2229: iflt -> 2246
    //   2232: aload #20
    //   2234: iconst_0
    //   2235: aload #25
    //   2237: iload #7
    //   2239: aaload
    //   2240: aastore
    //   2241: iconst_0
    //   2242: iconst_1
    //   2243: iadd
    //   2244: istore #5
    //   2246: iload #8
    //   2248: istore #6
    //   2250: iload #6
    //   2252: aload #25
    //   2254: arraylength
    //   2255: if_icmpge -> 2697
    //   2258: iload #5
    //   2260: aload #20
    //   2262: arraylength
    //   2263: if_icmpge -> 2697
    //   2266: aload #20
    //   2268: iload #5
    //   2270: aload #25
    //   2272: iload #6
    //   2274: aaload
    //   2275: aastore
    //   2276: iload #6
    //   2278: iconst_1
    //   2279: iadd
    //   2280: istore #6
    //   2282: iload #5
    //   2284: iconst_1
    //   2285: iadd
    //   2286: istore #5
    //   2288: goto -> 2250
    //   2291: iconst_0
    //   2292: istore #10
    //   2294: iconst_0
    //   2295: istore #9
    //   2297: goto -> 1952
    //   2300: iload #16
    //   2302: ldc_w -983040
    //   2305: if_icmpne -> 2320
    //   2308: aload #22
    //   2310: ldc_w 'too few arguments for method ''
    //   2313: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2316: pop
    //   2317: goto -> 2147
    //   2320: iload #16
    //   2322: ldc_w -917504
    //   2325: if_icmpne -> 2340
    //   2328: aload #22
    //   2330: ldc_w 'too many arguments for method ''
    //   2333: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2336: pop
    //   2337: goto -> 2147
    //   2340: aload #22
    //   2342: ldc_w 'no possibly applicable method ''
    //   2345: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   2348: pop
    //   2349: goto -> 2147
    //   2352: bipush #119
    //   2354: istore #4
    //   2356: goto -> 2186
    //   2359: iload #11
    //   2361: iconst_1
    //   2362: if_icmpeq -> 2376
    //   2365: iload #11
    //   2367: ifne -> 2382
    //   2370: iload #13
    //   2372: iconst_1
    //   2373: if_icmpne -> 2382
    //   2376: iconst_0
    //   2377: istore #6
    //   2379: goto -> 2202
    //   2382: iload #11
    //   2384: ifle -> 2587
    //   2387: aload_3
    //   2388: iload #11
    //   2390: invokestatic mostSpecific : ([Lgnu/mapping/MethodProc;I)I
    //   2393: istore #9
    //   2395: iload #9
    //   2397: istore #5
    //   2399: iload #9
    //   2401: ifge -> 2451
    //   2404: iload #9
    //   2406: istore #5
    //   2408: iload #4
    //   2410: bipush #83
    //   2412: if_icmpne -> 2451
    //   2415: iconst_0
    //   2416: istore #6
    //   2418: iload #9
    //   2420: istore #5
    //   2422: iload #6
    //   2424: iload #11
    //   2426: if_icmpge -> 2451
    //   2429: iload #9
    //   2431: istore #5
    //   2433: aload_3
    //   2434: iload #6
    //   2436: aaload
    //   2437: invokevirtual getStaticFlag : ()Z
    //   2440: ifeq -> 2567
    //   2443: iload #9
    //   2445: iflt -> 2563
    //   2448: iconst_m1
    //   2449: istore #5
    //   2451: iload #5
    //   2453: istore #6
    //   2455: iload #5
    //   2457: ifge -> 2202
    //   2460: iload #4
    //   2462: bipush #80
    //   2464: if_icmpeq -> 2479
    //   2467: iload #5
    //   2469: istore #6
    //   2471: aload #26
    //   2473: invokevirtual warnInvokeUnknownMethod : ()Z
    //   2476: ifeq -> 2202
    //   2479: new java/lang/StringBuffer
    //   2482: dup
    //   2483: invokespecial <init> : ()V
    //   2486: astore #20
    //   2488: aload #20
    //   2490: ldc_w 'more than one definitely applicable method `'
    //   2493: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2496: pop
    //   2497: aload #20
    //   2499: aload #23
    //   2501: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2504: pop
    //   2505: aload #20
    //   2507: ldc_w '' in '
    //   2510: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2513: pop
    //   2514: aload #20
    //   2516: aload #21
    //   2518: invokevirtual getName : ()Ljava/lang/String;
    //   2521: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2524: pop
    //   2525: aload_3
    //   2526: iload #11
    //   2528: aload #20
    //   2530: invokestatic append : ([Lgnu/expr/PrimProcedure;ILjava/lang/StringBuffer;)V
    //   2533: iload #4
    //   2535: bipush #80
    //   2537: if_icmpne -> 2580
    //   2540: bipush #101
    //   2542: istore #4
    //   2544: aload #26
    //   2546: iload #4
    //   2548: aload #20
    //   2550: invokevirtual toString : ()Ljava/lang/String;
    //   2553: invokevirtual error : (CLjava/lang/String;)V
    //   2556: iload #5
    //   2558: istore #6
    //   2560: goto -> 2202
    //   2563: iload #6
    //   2565: istore #5
    //   2567: iload #6
    //   2569: iconst_1
    //   2570: iadd
    //   2571: istore #6
    //   2573: iload #5
    //   2575: istore #9
    //   2577: goto -> 2418
    //   2580: bipush #119
    //   2582: istore #4
    //   2584: goto -> 2544
    //   2587: iload #4
    //   2589: bipush #80
    //   2591: if_icmpeq -> 2606
    //   2594: iload #15
    //   2596: istore #6
    //   2598: aload #26
    //   2600: invokevirtual warnInvokeUnknownMethod : ()Z
    //   2603: ifeq -> 2202
    //   2606: new java/lang/StringBuffer
    //   2609: dup
    //   2610: invokespecial <init> : ()V
    //   2613: astore #20
    //   2615: aload #20
    //   2617: ldc_w 'more than one possibly applicable method ''
    //   2620: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2623: pop
    //   2624: aload #20
    //   2626: aload #23
    //   2628: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2631: pop
    //   2632: aload #20
    //   2634: ldc_w '' in '
    //   2637: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2640: pop
    //   2641: aload #20
    //   2643: aload #21
    //   2645: invokevirtual getName : ()Ljava/lang/String;
    //   2648: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuffer;
    //   2651: pop
    //   2652: aload_3
    //   2653: iload #13
    //   2655: aload #20
    //   2657: invokestatic append : ([Lgnu/expr/PrimProcedure;ILjava/lang/StringBuffer;)V
    //   2660: iload #4
    //   2662: bipush #80
    //   2664: if_icmpne -> 2690
    //   2667: bipush #101
    //   2669: istore #4
    //   2671: aload #26
    //   2673: iload #4
    //   2675: aload #20
    //   2677: invokevirtual toString : ()Ljava/lang/String;
    //   2680: invokevirtual error : (CLjava/lang/String;)V
    //   2683: iload #15
    //   2685: istore #6
    //   2687: goto -> 2202
    //   2690: bipush #119
    //   2692: istore #4
    //   2694: goto -> 2671
    //   2697: new gnu/expr/ApplyExp
    //   2700: dup
    //   2701: aload_3
    //   2702: aload #20
    //   2704: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   2707: astore_3
    //   2708: aload_3
    //   2709: aload_0
    //   2710: invokevirtual setLine : (Lgnu/expr/Expression;)Lgnu/expr/Expression;
    //   2713: pop
    //   2714: aload_1
    //   2715: aload_3
    //   2716: aload_2
    //   2717: invokevirtual visitApplyOnly : (Lgnu/expr/ApplyExp;Lgnu/bytecode/Type;)Lgnu/expr/Expression;
    //   2720: areturn
    //   2721: aload_0
    //   2722: aload_1
    //   2723: invokevirtual visitArgs : (Lgnu/expr/InlineCalls;)V
    //   2726: aload_0
    //   2727: areturn
    // Exception table:
    //   from	to	target	type
    //   646	652	749	java/lang/Throwable
    //   890	912	1129	java/lang/Exception
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/reflect/CompileInvoke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */