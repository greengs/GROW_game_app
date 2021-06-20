package gnu.kawa.functions;

import gnu.bytecode.ClassType;
import gnu.bytecode.PrimType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.Inlineable;
import gnu.expr.PrimProcedure;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LangPrimType;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class CompileArith implements Inlineable {
  public static CompileArith $Mn;
  
  public static CompileArith $Pl = new CompileArith(AddOp.$Pl, 1);
  
  int op;
  
  Procedure proc;
  
  static {
    $Mn = new CompileArith(AddOp.$Mn, 2);
  }
  
  CompileArith(Object paramObject, int paramInt) {
    this.proc = (Procedure)paramObject;
    this.op = paramInt;
  }
  
  static int adjustReturnKind(int paramInt1, int paramInt2) {
    if (paramInt2 >= 4 && paramInt2 <= 7 && paramInt1 > 0) {
      switch (paramInt2) {
        default:
          return paramInt1;
        case 4:
          if (paramInt1 <= 4)
            return 6; 
        case 5:
          if (paramInt1 <= 10 && paramInt1 != 7)
            return 8; 
        case 7:
          break;
      } 
      if (paramInt1 <= 10)
        return 4; 
    } 
  }
  
  public static boolean appropriateIntConstant(Expression[] paramArrayOfExpression, int paramInt, InlineCalls paramInlineCalls) {
    QuoteExp quoteExp = paramInlineCalls.fixIntValue(paramArrayOfExpression[paramInt]);
    if (quoteExp != null) {
      paramArrayOfExpression[paramInt] = (Expression)quoteExp;
      return true;
    } 
    return false;
  }
  
  public static boolean appropriateLongConstant(Expression[] paramArrayOfExpression, int paramInt, InlineCalls paramInlineCalls) {
    QuoteExp quoteExp = paramInlineCalls.fixLongValue(paramArrayOfExpression[paramInt]);
    if (quoteExp != null) {
      paramArrayOfExpression[paramInt] = (Expression)quoteExp;
      return true;
    } 
    return false;
  }
  
  public static CompileArith forBitwise(Object paramObject) {
    return new CompileArith(paramObject, ((BitwiseOp)paramObject).op);
  }
  
  public static CompileArith forDiv(Object paramObject) {
    return new CompileArith(paramObject, ((DivideOp)paramObject).op);
  }
  
  public static CompileArith forMul(Object paramObject) {
    return new CompileArith(paramObject, 3);
  }
  
  public static int getReturnKind(int paramInt1, int paramInt2, int paramInt3) {
    if (paramInt3 >= 9 && paramInt3 <= 12)
      return paramInt1; 
    if (paramInt1 > 0) {
      paramInt3 = paramInt2;
      if (paramInt1 > paramInt2) {
        paramInt3 = paramInt2;
        if (paramInt2 > 0)
          return paramInt1; 
      } 
      return paramInt3;
    } 
    return paramInt1;
  }
  
  public static Expression pairwise(Procedure paramProcedure, Expression paramExpression, Expression[] paramArrayOfExpression, InlineCalls paramInlineCalls) {
    int j = paramArrayOfExpression.length;
    Expression expression = paramArrayOfExpression[0];
    int i;
    for (i = 1; i < j; i++) {
      ApplyExp applyExp = new ApplyExp(paramExpression, new Expression[] { expression, paramArrayOfExpression[i] });
      Expression expression1 = paramInlineCalls.maybeInline(applyExp, null, paramProcedure);
      if (expression1 != null)
        expression = expression1; 
    } 
    return expression;
  }
  
  public static Expression validateApplyAdd(AddOp paramAddOp, ApplyExp paramApplyExp, InlineCalls paramInlineCalls) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    ApplyExp applyExp = paramApplyExp;
    if (arrayOfExpression.length == 1) {
      applyExp = paramApplyExp;
      if (paramAddOp.plusOrMinus < 0) {
        Type type = arrayOfExpression[0].getType();
        applyExp = paramApplyExp;
        if (type instanceof PrimType) {
          PrimType primType;
          char c = type.getSignature().charAt(0);
          applyExp = null;
          byte b2 = 0;
          byte b1 = b2;
          ApplyExp applyExp1 = applyExp;
          if (c != 'V') {
            b1 = b2;
            applyExp1 = applyExp;
            if (c != 'Z')
              if (c == 'C') {
                applyExp1 = applyExp;
                b1 = b2;
              } else if (c == 'D') {
                b1 = 119;
                primType = LangPrimType.doubleType;
              } else if (c == 'F') {
                b1 = 118;
                primType = LangPrimType.floatType;
              } else if (c == 'J') {
                b1 = 117;
                primType = LangPrimType.longType;
              } else {
                b1 = 116;
                primType = LangPrimType.intType;
              }  
          } 
          applyExp = paramApplyExp;
          if (primType != null)
            applyExp = new ApplyExp((Procedure)PrimProcedure.makeBuiltinUnary(b1, (Type)primType), arrayOfExpression); 
        } 
      } 
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateApplyArithOp(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    int j = ((ArithOp)paramProcedure).op;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length > 2)
      return pairwise(paramProcedure, paramApplyExp.getFunction(), arrayOfExpression, paramInlineCalls); 
    Expression expression = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
    if (expression != paramApplyExp)
      return expression; 
    int i = 0;
    if (arrayOfExpression.length == 2 || arrayOfExpression.length == 1) {
      int k = Arithmetic.classifyType(arrayOfExpression[0].getType());
      if (arrayOfExpression.length == 2 && (j < 9 || j > 12)) {
        int n = Arithmetic.classifyType(arrayOfExpression[1].getType());
        int m = getReturnKind(k, n, j);
        i = m;
        if (m == 4)
          if (k == 1 && appropriateIntConstant(arrayOfExpression, 1, paramInlineCalls)) {
            i = 1;
          } else if (n == 1 && appropriateIntConstant(arrayOfExpression, 0, paramInlineCalls)) {
            i = 1;
          } else if (k == 2 && appropriateLongConstant(arrayOfExpression, 1, paramInlineCalls)) {
            i = 2;
          } else {
            i = m;
            if (n == 2) {
              i = m;
              if (appropriateLongConstant(arrayOfExpression, 0, paramInlineCalls))
                i = 2; 
            } 
          }  
      } else {
        i = k;
      } 
      i = adjustReturnKind(i, j);
      paramApplyExp.setType(Arithmetic.kindType(i));
    } 
    ApplyExp applyExp = paramApplyExp;
    if ((paramInlineCalls.getCompilation()).mustCompile) {
      switch (j) {
        default:
          return (Expression)paramApplyExp;
        case 1:
        case 2:
          return validateApplyAdd((AddOp)paramProcedure, paramApplyExp, paramInlineCalls);
        case 4:
        case 5:
        case 6:
        case 7:
        case 8:
          return validateApplyDiv((DivideOp)paramProcedure, paramApplyExp, paramInlineCalls);
        case 16:
          break;
      } 
      applyExp = paramApplyExp;
      if (i > 0)
        return validateApplyNot(paramApplyExp, i, paramInlineCalls); 
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateApplyDiv(DivideOp paramDivideOp, ApplyExp paramApplyExp, InlineCalls paramInlineCalls) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    ApplyExp applyExp = paramApplyExp;
    if (arrayOfExpression.length == 1) {
      QuoteExp quoteExp = QuoteExp.getInstance(IntNum.one());
      Expression expression = arrayOfExpression[0];
      applyExp = new ApplyExp(paramApplyExp.getFunction(), new Expression[] { (Expression)quoteExp, expression });
    } 
    return (Expression)applyExp;
  }
  
  public static Expression validateApplyNot(ApplyExp paramApplyExp, int paramInt, InlineCalls paramInlineCalls) {
    QuoteExp quoteExp1;
    ApplyExp applyExp = paramApplyExp;
    if (paramApplyExp.getArgCount() == 1) {
      Expression expression = paramApplyExp.getArg(0);
      if (paramInt == 1 || paramInt == 2) {
        quoteExp1 = QuoteExp.getInstance(IntNum.minusOne());
        return paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)BitwiseOp.xor, new Expression[] { expression, (Expression)quoteExp1 }), null);
      } 
    } else {
      return (Expression)applyExp;
    } 
    if (paramInt == 4) {
      String str = "gnu.math.BitOps";
    } else if (paramInt == 3) {
      String str = "java.meth.BigInteger";
    } else {
      paramInlineCalls = null;
    } 
    QuoteExp quoteExp2 = quoteExp1;
    return (Expression)((paramInlineCalls != null) ? new ApplyExp(ClassType.make((String)paramInlineCalls).getDeclaredMethod("not", 1), quoteExp1.getArgs()) : quoteExp2);
  }
  
  public static Expression validateApplyNumberCompare(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression expression = paramApplyExp.inlineIfConstant(paramProcedure, paramInlineCalls);
    return (Expression)((expression != paramApplyExp) ? expression : paramApplyExp);
  }
  
  public static Expression validateApplyNumberPredicate(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    int i = ((NumberPredicate)paramProcedure).op;
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    arrayOfExpression[0] = paramInlineCalls.visit(arrayOfExpression[0], (Type)LangObjType.integerType);
    paramApplyExp.setType((Type)Type.booleanType);
    return (Expression)paramApplyExp;
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   4: astore #11
    //   6: aload #11
    //   8: arraylength
    //   9: istore #6
    //   11: iload #6
    //   13: ifne -> 32
    //   16: aload_2
    //   17: aload_0
    //   18: getfield proc : Lgnu/mapping/Procedure;
    //   21: checkcast gnu/kawa/functions/ArithOp
    //   24: invokevirtual defaultResult : ()Ljava/lang/Object;
    //   27: aload_3
    //   28: invokevirtual compileConstant : (Ljava/lang/Object;Lgnu/expr/Target;)V
    //   31: return
    //   32: iload #6
    //   34: iconst_1
    //   35: if_icmpeq -> 45
    //   38: aload_3
    //   39: instanceof gnu/expr/IgnoreTarget
    //   42: ifeq -> 52
    //   45: aload_1
    //   46: aload_2
    //   47: aload_3
    //   48: invokestatic compile : (Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   51: return
    //   52: aload #11
    //   54: iconst_0
    //   55: aaload
    //   56: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   59: invokestatic classifyType : (Lgnu/bytecode/Type;)I
    //   62: istore #7
    //   64: aload #11
    //   66: iconst_1
    //   67: aaload
    //   68: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   71: invokestatic classifyType : (Lgnu/bytecode/Type;)I
    //   74: istore #8
    //   76: iload #7
    //   78: iload #8
    //   80: aload_0
    //   81: getfield op : I
    //   84: invokestatic getReturnKind : (III)I
    //   87: istore #5
    //   89: iload #5
    //   91: invokestatic kindType : (I)Lgnu/bytecode/Type;
    //   94: astore #9
    //   96: iload #5
    //   98: ifeq -> 107
    //   101: iload #6
    //   103: iconst_2
    //   104: if_icmpeq -> 114
    //   107: aload_1
    //   108: aload_2
    //   109: aload_3
    //   110: invokestatic compile : (Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   113: return
    //   114: aload_3
    //   115: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   118: invokestatic classifyType : (Lgnu/bytecode/Type;)I
    //   121: istore #4
    //   123: iload #4
    //   125: iconst_1
    //   126: if_icmpeq -> 135
    //   129: iload #4
    //   131: iconst_2
    //   132: if_icmpne -> 372
    //   135: iload #5
    //   137: iconst_1
    //   138: if_icmplt -> 372
    //   141: iload #5
    //   143: iconst_4
    //   144: if_icmpgt -> 372
    //   147: iload #4
    //   149: istore #5
    //   151: iload #4
    //   153: iconst_1
    //   154: if_icmpne -> 360
    //   157: getstatic gnu/kawa/lispexpr/LangPrimType.intType : Lgnu/bytecode/PrimType;
    //   160: astore #9
    //   162: iload #5
    //   164: istore #4
    //   166: iload #4
    //   168: istore #5
    //   170: aload_0
    //   171: getfield op : I
    //   174: iconst_4
    //   175: if_icmplt -> 241
    //   178: iload #4
    //   180: istore #5
    //   182: aload_0
    //   183: getfield op : I
    //   186: bipush #8
    //   188: if_icmpgt -> 241
    //   191: aload_0
    //   192: getfield proc : Lgnu/mapping/Procedure;
    //   195: checkcast gnu/kawa/functions/DivideOp
    //   198: astore #10
    //   200: aload #10
    //   202: getfield op : I
    //   205: iconst_4
    //   206: if_icmpne -> 482
    //   209: iload #4
    //   211: istore #5
    //   213: iload #4
    //   215: iconst_4
    //   216: if_icmple -> 241
    //   219: iload #4
    //   221: istore #5
    //   223: iload #4
    //   225: bipush #6
    //   227: if_icmpge -> 241
    //   230: iload #4
    //   232: bipush #9
    //   234: if_icmpgt -> 482
    //   237: iload #4
    //   239: istore #5
    //   241: aload_0
    //   242: getfield op : I
    //   245: iconst_4
    //   246: if_icmpne -> 665
    //   249: iload #5
    //   251: bipush #10
    //   253: if_icmpgt -> 665
    //   256: iload #5
    //   258: bipush #8
    //   260: if_icmpeq -> 665
    //   263: iload #5
    //   265: bipush #7
    //   267: if_icmpeq -> 665
    //   270: iload #5
    //   272: bipush #6
    //   274: if_icmpeq -> 283
    //   277: iload #5
    //   279: iconst_4
    //   280: if_icmple -> 646
    //   283: iload #5
    //   285: bipush #6
    //   287: if_icmpne -> 639
    //   290: getstatic gnu/kawa/functions/Arithmetic.typeRatNum : Lgnu/kawa/lispexpr/LangObjType;
    //   293: astore_1
    //   294: aload_1
    //   295: astore #9
    //   297: aload_1
    //   298: ldc_w 'divide'
    //   301: iconst_2
    //   302: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   305: astore #10
    //   307: aload #9
    //   309: astore_1
    //   310: aload #10
    //   312: astore #9
    //   314: aload_1
    //   315: invokestatic getInstance : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   318: astore #10
    //   320: aload #11
    //   322: iconst_0
    //   323: aaload
    //   324: aload_2
    //   325: aload #10
    //   327: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   330: aload #11
    //   332: iconst_1
    //   333: aaload
    //   334: aload_2
    //   335: aload #10
    //   337: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   340: aload_2
    //   341: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   344: aload #9
    //   346: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   349: aload_1
    //   350: astore #10
    //   352: aload_3
    //   353: aload_2
    //   354: aload #10
    //   356: invokevirtual compileFromStack : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   359: return
    //   360: getstatic gnu/kawa/lispexpr/LangPrimType.longType : Lgnu/bytecode/PrimType;
    //   363: astore #9
    //   365: iload #5
    //   367: istore #4
    //   369: goto -> 166
    //   372: iload #4
    //   374: bipush #8
    //   376: if_icmpeq -> 386
    //   379: iload #4
    //   381: bipush #7
    //   383: if_icmpne -> 430
    //   386: iload #5
    //   388: iconst_2
    //   389: if_icmple -> 430
    //   392: iload #5
    //   394: bipush #10
    //   396: if_icmpgt -> 430
    //   399: iload #4
    //   401: istore #5
    //   403: iload #4
    //   405: bipush #7
    //   407: if_icmpne -> 422
    //   410: getstatic gnu/kawa/lispexpr/LangPrimType.floatType : Lgnu/bytecode/PrimType;
    //   413: astore #9
    //   415: iload #5
    //   417: istore #4
    //   419: goto -> 166
    //   422: getstatic gnu/kawa/lispexpr/LangPrimType.doubleType : Lgnu/bytecode/PrimType;
    //   425: astore #9
    //   427: goto -> 415
    //   430: iload #5
    //   432: bipush #7
    //   434: if_icmpne -> 449
    //   437: getstatic gnu/kawa/lispexpr/LangPrimType.floatType : Lgnu/bytecode/PrimType;
    //   440: astore #9
    //   442: iload #5
    //   444: istore #4
    //   446: goto -> 166
    //   449: iload #5
    //   451: bipush #8
    //   453: if_icmpeq -> 463
    //   456: iload #5
    //   458: bipush #9
    //   460: if_icmpne -> 475
    //   463: bipush #8
    //   465: istore #4
    //   467: getstatic gnu/kawa/lispexpr/LangPrimType.doubleType : Lgnu/bytecode/PrimType;
    //   470: astore #9
    //   472: goto -> 166
    //   475: iload #5
    //   477: istore #4
    //   479: goto -> 166
    //   482: aload #10
    //   484: getfield op : I
    //   487: iconst_5
    //   488: if_icmpne -> 505
    //   491: iload #4
    //   493: bipush #10
    //   495: if_icmpgt -> 505
    //   498: iload #4
    //   500: bipush #7
    //   502: if_icmpne -> 521
    //   505: aload #10
    //   507: getfield op : I
    //   510: iconst_4
    //   511: if_icmpne -> 528
    //   514: iload #4
    //   516: bipush #10
    //   518: if_icmpne -> 528
    //   521: bipush #8
    //   523: istore #5
    //   525: goto -> 241
    //   528: aload #10
    //   530: getfield op : I
    //   533: bipush #7
    //   535: if_icmpeq -> 554
    //   538: aload #10
    //   540: getfield op : I
    //   543: bipush #6
    //   545: if_icmpne -> 599
    //   548: iload #4
    //   550: iconst_4
    //   551: if_icmpgt -> 599
    //   554: iload #4
    //   556: istore #5
    //   558: aload #10
    //   560: invokevirtual getRoundingMode : ()I
    //   563: iconst_3
    //   564: if_icmpeq -> 241
    //   567: iload #4
    //   569: istore #5
    //   571: iload #4
    //   573: iconst_4
    //   574: if_icmpeq -> 241
    //   577: iload #4
    //   579: istore #5
    //   581: iload #4
    //   583: bipush #7
    //   585: if_icmpeq -> 241
    //   588: iload #4
    //   590: istore #5
    //   592: iload #4
    //   594: bipush #8
    //   596: if_icmpeq -> 241
    //   599: aload #10
    //   601: getfield op : I
    //   604: bipush #8
    //   606: if_icmpne -> 632
    //   609: iload #4
    //   611: istore #5
    //   613: aload #10
    //   615: invokevirtual getRoundingMode : ()I
    //   618: iconst_3
    //   619: if_icmpeq -> 241
    //   622: iload #4
    //   624: istore #5
    //   626: iload #4
    //   628: iconst_4
    //   629: if_icmpeq -> 241
    //   632: aload_1
    //   633: aload_2
    //   634: aload_3
    //   635: invokestatic compile : (Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   638: return
    //   639: getstatic gnu/kawa/functions/Arithmetic.typeRealNum : Lgnu/kawa/lispexpr/LangObjType;
    //   642: astore_1
    //   643: goto -> 294
    //   646: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   649: astore_1
    //   650: getstatic gnu/kawa/functions/Arithmetic.typeRatNum : Lgnu/kawa/lispexpr/LangObjType;
    //   653: ldc_w 'make'
    //   656: iconst_2
    //   657: invokevirtual getDeclaredMethod : (Ljava/lang/String;I)Lgnu/bytecode/Method;
    //   660: astore #9
    //   662: goto -> 314
    //   665: iload #5
    //   667: iconst_4
    //   668: if_icmpne -> 783
    //   671: aload_0
    //   672: getfield op : I
    //   675: iconst_1
    //   676: if_icmpeq -> 758
    //   679: aload_0
    //   680: getfield op : I
    //   683: iconst_3
    //   684: if_icmpeq -> 758
    //   687: aload_0
    //   688: getfield op : I
    //   691: iconst_2
    //   692: if_icmpeq -> 758
    //   695: aload_0
    //   696: getfield op : I
    //   699: bipush #13
    //   701: if_icmpeq -> 758
    //   704: aload_0
    //   705: getfield op : I
    //   708: bipush #14
    //   710: if_icmpeq -> 758
    //   713: aload_0
    //   714: getfield op : I
    //   717: bipush #15
    //   719: if_icmpeq -> 758
    //   722: aload_0
    //   723: getfield op : I
    //   726: bipush #7
    //   728: if_icmpeq -> 758
    //   731: aload_0
    //   732: getfield op : I
    //   735: bipush #8
    //   737: if_icmpeq -> 758
    //   740: aload_0
    //   741: getfield op : I
    //   744: bipush #9
    //   746: if_icmplt -> 783
    //   749: aload_0
    //   750: getfield op : I
    //   753: bipush #11
    //   755: if_icmpgt -> 783
    //   758: aload_0
    //   759: aload #11
    //   761: iconst_0
    //   762: aaload
    //   763: aload #11
    //   765: iconst_1
    //   766: aaload
    //   767: iload #7
    //   769: iload #8
    //   771: aload_2
    //   772: invokevirtual compileIntNum : (Lgnu/expr/Expression;Lgnu/expr/Expression;IILgnu/expr/Compilation;)Z
    //   775: pop
    //   776: aload #9
    //   778: astore #10
    //   780: goto -> 352
    //   783: iload #5
    //   785: iconst_1
    //   786: if_icmpeq -> 827
    //   789: iload #5
    //   791: iconst_2
    //   792: if_icmpeq -> 827
    //   795: iload #5
    //   797: bipush #7
    //   799: if_icmpeq -> 809
    //   802: iload #5
    //   804: bipush #8
    //   806: if_icmpne -> 1041
    //   809: aload_0
    //   810: getfield op : I
    //   813: bipush #8
    //   815: if_icmple -> 827
    //   818: aload_0
    //   819: getfield op : I
    //   822: bipush #13
    //   824: if_icmplt -> 1041
    //   827: aload #9
    //   829: invokestatic getInstance : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   832: astore_1
    //   833: aload_2
    //   834: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   837: astore #12
    //   839: iconst_0
    //   840: istore #4
    //   842: aload #9
    //   844: astore #10
    //   846: iload #4
    //   848: iload #6
    //   850: if_icmpge -> 352
    //   853: aload_1
    //   854: astore #10
    //   856: iload #4
    //   858: iconst_1
    //   859: if_icmpne -> 894
    //   862: aload_1
    //   863: astore #10
    //   865: aload_0
    //   866: getfield op : I
    //   869: bipush #9
    //   871: if_icmplt -> 894
    //   874: aload_1
    //   875: astore #10
    //   877: aload_0
    //   878: getfield op : I
    //   881: bipush #12
    //   883: if_icmpgt -> 894
    //   886: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   889: invokestatic getInstance : (Lgnu/bytecode/Type;)Lgnu/expr/Target;
    //   892: astore #10
    //   894: aload #11
    //   896: iload #4
    //   898: aaload
    //   899: aload_2
    //   900: aload #10
    //   902: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   905: iload #4
    //   907: ifne -> 922
    //   910: iload #4
    //   912: iconst_1
    //   913: iadd
    //   914: istore #4
    //   916: aload #10
    //   918: astore_1
    //   919: goto -> 842
    //   922: iload #5
    //   924: tableswitch default -> 972, 1 -> 975, 2 -> 975, 3 -> 972, 4 -> 972, 5 -> 972, 6 -> 972, 7 -> 975, 8 -> 975
    //   972: goto -> 910
    //   975: aload_0
    //   976: getfield op : I
    //   979: bipush #9
    //   981: if_icmpne -> 1021
    //   984: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   987: astore_1
    //   988: aload #12
    //   990: ldc_w 'gnu.math.IntNum'
    //   993: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   996: ldc_w 'shift'
    //   999: iconst_2
    //   1000: anewarray gnu/bytecode/Type
    //   1003: dup
    //   1004: iconst_0
    //   1005: aload #9
    //   1007: aastore
    //   1008: dup
    //   1009: iconst_1
    //   1010: aload_1
    //   1011: aastore
    //   1012: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   1015: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   1018: goto -> 910
    //   1021: aload #12
    //   1023: aload_0
    //   1024: invokevirtual primitiveOpcode : ()I
    //   1027: aload #9
    //   1029: invokevirtual getImplementationType : ()Lgnu/bytecode/Type;
    //   1032: checkcast gnu/bytecode/PrimType
    //   1035: invokevirtual emitBinop : (ILgnu/bytecode/Type;)V
    //   1038: goto -> 910
    //   1041: aload_1
    //   1042: aload_2
    //   1043: aload_3
    //   1044: invokestatic compile : (Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   1047: return
  }
  
  public boolean compileIntNum(Expression paramExpression1, Expression paramExpression2, int paramInt1, int paramInt2, Compilation paramCompilation) {
    // Byte code:
    //   0: aload_0
    //   1: getfield op : I
    //   4: iconst_2
    //   5: if_icmpne -> 138
    //   8: aload_2
    //   9: instanceof gnu/expr/QuoteExp
    //   12: ifeq -> 138
    //   15: aload_2
    //   16: invokevirtual valueIfConstant : ()Ljava/lang/Object;
    //   19: astore #10
    //   21: iload #4
    //   23: iconst_2
    //   24: if_icmpgt -> 91
    //   27: aload #10
    //   29: checkcast java/lang/Number
    //   32: invokevirtual longValue : ()J
    //   35: lstore #7
    //   37: lload #7
    //   39: ldc2_w -2147483648
    //   42: lcmp
    //   43: ifle -> 85
    //   46: lload #7
    //   48: ldc2_w 2147483647
    //   51: lcmp
    //   52: ifgt -> 85
    //   55: iconst_1
    //   56: istore #9
    //   58: iload #9
    //   60: ifeq -> 138
    //   63: getstatic gnu/kawa/functions/CompileArith.$Pl : Lgnu/kawa/functions/CompileArith;
    //   66: aload_1
    //   67: lload #7
    //   69: lneg
    //   70: l2i
    //   71: invokestatic valueOf : (I)Ljava/lang/Integer;
    //   74: invokestatic getInstance : (Ljava/lang/Object;)Lgnu/expr/QuoteExp;
    //   77: iload_3
    //   78: iconst_1
    //   79: aload #5
    //   81: invokevirtual compileIntNum : (Lgnu/expr/Expression;Lgnu/expr/Expression;IILgnu/expr/Compilation;)Z
    //   84: ireturn
    //   85: iconst_0
    //   86: istore #9
    //   88: goto -> 58
    //   91: aload #10
    //   93: instanceof gnu/math/IntNum
    //   96: ifeq -> 129
    //   99: aload #10
    //   101: checkcast gnu/math/IntNum
    //   104: astore #10
    //   106: aload #10
    //   108: invokevirtual longValue : ()J
    //   111: lstore #7
    //   113: aload #10
    //   115: ldc2_w -2147483647
    //   118: ldc2_w 2147483647
    //   121: invokevirtual inRange : (JJ)Z
    //   124: istore #9
    //   126: goto -> 58
    //   129: iconst_0
    //   130: istore #9
    //   132: lconst_0
    //   133: lstore #7
    //   135: goto -> 58
    //   138: aload_0
    //   139: getfield op : I
    //   142: iconst_1
    //   143: if_icmpeq -> 154
    //   146: aload_0
    //   147: getfield op : I
    //   150: iconst_3
    //   151: if_icmpne -> 230
    //   154: iconst_1
    //   155: istore #6
    //   157: iload #6
    //   159: ifeq -> 424
    //   162: iload_3
    //   163: istore #6
    //   165: aload_1
    //   166: invokestatic checkIntValue : (Lgnu/expr/Expression;)Ljava/lang/Integer;
    //   169: ifnull -> 175
    //   172: iconst_1
    //   173: istore #6
    //   175: aload_2
    //   176: invokestatic checkIntValue : (Lgnu/expr/Expression;)Ljava/lang/Integer;
    //   179: ifnull -> 185
    //   182: iconst_1
    //   183: istore #4
    //   185: iload #6
    //   187: iconst_1
    //   188: if_icmpne -> 236
    //   191: iload #4
    //   193: iconst_1
    //   194: if_icmpeq -> 236
    //   197: iconst_1
    //   198: istore_3
    //   199: iload_3
    //   200: ifeq -> 241
    //   203: aload_1
    //   204: invokevirtual side_effects : ()Z
    //   207: ifeq -> 217
    //   210: aload_2
    //   211: invokevirtual side_effects : ()Z
    //   214: ifne -> 241
    //   217: aload_0
    //   218: aload_2
    //   219: aload_1
    //   220: iload #4
    //   222: iload #6
    //   224: aload #5
    //   226: invokevirtual compileIntNum : (Lgnu/expr/Expression;Lgnu/expr/Expression;IILgnu/expr/Compilation;)Z
    //   229: ireturn
    //   230: iconst_0
    //   231: istore #6
    //   233: goto -> 157
    //   236: iconst_0
    //   237: istore_3
    //   238: goto -> 199
    //   241: iload #6
    //   243: iconst_1
    //   244: if_icmpne -> 408
    //   247: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   250: astore #11
    //   252: iload #4
    //   254: iconst_1
    //   255: if_icmpne -> 416
    //   258: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   261: astore #10
    //   263: aload_1
    //   264: aload #5
    //   266: aload #11
    //   268: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   271: aload_2
    //   272: aload #5
    //   274: aload #10
    //   276: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/bytecode/Type;)V
    //   279: aload #5
    //   281: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   284: astore #15
    //   286: aload #10
    //   288: astore #12
    //   290: iload_3
    //   291: ifeq -> 309
    //   294: aload #15
    //   296: invokevirtual emitSwap : ()V
    //   299: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   302: astore #11
    //   304: getstatic gnu/kawa/lispexpr/LangPrimType.intType : Lgnu/bytecode/PrimType;
    //   307: astore #12
    //   309: aconst_null
    //   310: astore_1
    //   311: aconst_null
    //   312: astore_2
    //   313: aconst_null
    //   314: astore #13
    //   316: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   319: astore #14
    //   321: aload_0
    //   322: getfield op : I
    //   325: tableswitch default -> 400, 1 -> 471, 2 -> 522, 3 -> 536, 4 -> 592, 5 -> 592, 6 -> 592, 7 -> 592, 8 -> 592, 9 -> 749, 10 -> 715, 11 -> 715, 12 -> 400, 13 -> 550, 14 -> 554, 15 -> 564
    //   400: new java/lang/Error
    //   403: dup
    //   404: invokespecial <init> : ()V
    //   407: athrow
    //   408: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   411: astore #11
    //   413: goto -> 252
    //   416: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   419: astore #10
    //   421: goto -> 263
    //   424: aload_0
    //   425: getfield op : I
    //   428: bipush #9
    //   430: if_icmplt -> 457
    //   433: aload_0
    //   434: getfield op : I
    //   437: bipush #12
    //   439: if_icmpgt -> 457
    //   442: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   445: astore #11
    //   447: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   450: astore #10
    //   452: iconst_0
    //   453: istore_3
    //   454: goto -> 263
    //   457: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   460: astore #10
    //   462: aload #10
    //   464: astore #11
    //   466: iconst_0
    //   467: istore_3
    //   468: goto -> 263
    //   471: ldc_w 'add'
    //   474: astore_1
    //   475: aload #14
    //   477: astore #5
    //   479: aload #13
    //   481: astore_2
    //   482: aload_2
    //   483: astore #10
    //   485: aload_2
    //   486: ifnonnull -> 507
    //   489: iconst_2
    //   490: anewarray gnu/bytecode/Type
    //   493: astore #10
    //   495: aload #10
    //   497: iconst_0
    //   498: aload #11
    //   500: aastore
    //   501: aload #10
    //   503: iconst_1
    //   504: aload #12
    //   506: aastore
    //   507: aload #15
    //   509: aload #5
    //   511: aload_1
    //   512: aload #10
    //   514: invokevirtual getMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   517: invokevirtual emitInvokeStatic : (Lgnu/bytecode/Method;)V
    //   520: iconst_1
    //   521: ireturn
    //   522: ldc_w 'sub'
    //   525: astore_1
    //   526: aload #13
    //   528: astore_2
    //   529: aload #14
    //   531: astore #5
    //   533: goto -> 482
    //   536: ldc_w 'times'
    //   539: astore_1
    //   540: aload #13
    //   542: astore_2
    //   543: aload #14
    //   545: astore #5
    //   547: goto -> 482
    //   550: ldc_w 'and'
    //   553: astore_2
    //   554: aload_2
    //   555: astore_1
    //   556: aload_2
    //   557: ifnonnull -> 564
    //   560: ldc_w 'ior'
    //   563: astore_1
    //   564: aload_1
    //   565: astore #10
    //   567: aload_1
    //   568: ifnonnull -> 576
    //   571: ldc_w 'xor'
    //   574: astore #10
    //   576: ldc 'gnu.math.BitOps'
    //   578: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   581: astore #5
    //   583: aload #13
    //   585: astore_2
    //   586: aload #10
    //   588: astore_1
    //   589: goto -> 482
    //   592: aload_0
    //   593: getfield op : I
    //   596: bipush #8
    //   598: if_icmpne -> 647
    //   601: ldc_w 'remainder'
    //   604: astore #10
    //   606: aload_0
    //   607: getfield proc : Lgnu/mapping/Procedure;
    //   610: checkcast gnu/kawa/functions/DivideOp
    //   613: astore #16
    //   615: aload_0
    //   616: getfield op : I
    //   619: bipush #8
    //   621: if_icmpne -> 655
    //   624: aload #16
    //   626: getfield rounding_mode : I
    //   629: iconst_1
    //   630: if_icmpne -> 655
    //   633: ldc_w 'modulo'
    //   636: astore_1
    //   637: aload #13
    //   639: astore_2
    //   640: aload #14
    //   642: astore #5
    //   644: goto -> 482
    //   647: ldc_w 'quotient'
    //   650: astore #10
    //   652: goto -> 606
    //   655: aload #13
    //   657: astore_2
    //   658: aload #14
    //   660: astore #5
    //   662: aload #10
    //   664: astore_1
    //   665: aload #16
    //   667: getfield rounding_mode : I
    //   670: iconst_3
    //   671: if_icmpeq -> 482
    //   674: aload #15
    //   676: aload #16
    //   678: getfield rounding_mode : I
    //   681: invokevirtual emitPushInt : (I)V
    //   684: iconst_3
    //   685: anewarray gnu/bytecode/Type
    //   688: astore_2
    //   689: aload_2
    //   690: iconst_0
    //   691: aload #11
    //   693: aastore
    //   694: aload_2
    //   695: iconst_1
    //   696: aload #12
    //   698: aastore
    //   699: aload_2
    //   700: iconst_2
    //   701: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   704: aastore
    //   705: aload #14
    //   707: astore #5
    //   709: aload #10
    //   711: astore_1
    //   712: goto -> 482
    //   715: aload_0
    //   716: getfield op : I
    //   719: bipush #10
    //   721: if_icmpne -> 742
    //   724: ldc_w 'shiftLeft'
    //   727: astore_1
    //   728: ldc_w 'gnu.kawa.functions.BitwiseOp'
    //   731: invokestatic make : (Ljava/lang/String;)Lgnu/bytecode/ClassType;
    //   734: astore #5
    //   736: aload #13
    //   738: astore_2
    //   739: goto -> 482
    //   742: ldc_w 'shiftRight'
    //   745: astore_1
    //   746: goto -> 728
    //   749: ldc_w 'shift'
    //   752: astore_1
    //   753: aload #13
    //   755: astore_2
    //   756: aload #14
    //   758: astore #5
    //   760: goto -> 482
  }
  
  public int getReturnKind(Expression[] paramArrayOfExpression) {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: istore #6
    //   4: iload #6
    //   6: ifne -> 15
    //   9: iconst_4
    //   10: istore #4
    //   12: iload #4
    //   14: ireturn
    //   15: getstatic gnu/bytecode/Type.pointer_type : Lgnu/bytecode/ClassType;
    //   18: astore #7
    //   20: iconst_0
    //   21: istore_2
    //   22: iconst_0
    //   23: istore_3
    //   24: iload_2
    //   25: istore #4
    //   27: iload_3
    //   28: iload #6
    //   30: if_icmpge -> 12
    //   33: aload_1
    //   34: iload_3
    //   35: aaload
    //   36: invokevirtual getType : ()Lgnu/bytecode/Type;
    //   39: invokestatic classifyType : (Lgnu/bytecode/Type;)I
    //   42: istore #5
    //   44: iload_3
    //   45: ifeq -> 62
    //   48: iload #5
    //   50: ifeq -> 62
    //   53: iload_2
    //   54: istore #4
    //   56: iload #5
    //   58: iload_2
    //   59: if_icmple -> 66
    //   62: iload #5
    //   64: istore #4
    //   66: iload_3
    //   67: iconst_1
    //   68: iadd
    //   69: istore_3
    //   70: iload #4
    //   72: istore_2
    //   73: goto -> 24
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return Arithmetic.kindType(adjustReturnKind(getReturnKind(paramArrayOfExpression), this.op));
  }
  
  public int primitiveOpcode() {
    switch (this.op) {
      default:
        return -1;
      case 1:
        return 96;
      case 2:
        return 100;
      case 3:
        return 104;
      case 4:
      case 5:
      case 6:
      case 7:
        return 108;
      case 8:
        return 112;
      case 10:
        return 120;
      case 11:
        return 122;
      case 12:
        return 124;
      case 13:
        return 126;
      case 14:
        return 128;
      case 15:
        break;
    } 
    return 130;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CompileArith.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */