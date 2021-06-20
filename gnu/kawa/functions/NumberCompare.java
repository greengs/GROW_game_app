package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Language;
import gnu.expr.QuoteExp;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.math.IntNum;
import gnu.math.RatNum;

public class NumberCompare extends ProcedureN implements Inlineable {
  static final int RESULT_EQU = 0;
  
  static final int RESULT_GRT = 1;
  
  static final int RESULT_LSS = -1;
  
  static final int RESULT_NAN = -2;
  
  static final int RESULT_NEQ = -3;
  
  public static final int TRUE_IF_EQU = 8;
  
  public static final int TRUE_IF_GRT = 16;
  
  public static final int TRUE_IF_LSS = 4;
  
  public static final int TRUE_IF_NAN = 2;
  
  public static final int TRUE_IF_NEQ = 1;
  
  int flags;
  
  Language language;
  
  public static boolean $Eq(Object paramObject1, Object paramObject2) {
    return apply2(8, paramObject1, paramObject2);
  }
  
  public static boolean $Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    boolean bool = false;
    null = bool;
    if ($Eq(paramObject1, paramObject2)) {
      null = bool;
      if ($Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0) {
          null = bool;
          if ($Eq(paramObject3, paramArrayOfObject[0])) {
            null = bool;
            if (applyN(8, paramArrayOfObject))
              return true; 
          } 
          return null;
        } 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public static boolean $Gr(Object paramObject1, Object paramObject2) {
    return apply2(16, paramObject1, paramObject2);
  }
  
  public static boolean $Gr$Eq(Object paramObject1, Object paramObject2) {
    return apply2(24, paramObject1, paramObject2);
  }
  
  public static boolean $Gr$Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    boolean bool = false;
    null = bool;
    if ($Gr$Eq(paramObject1, paramObject2)) {
      null = bool;
      if ($Gr$Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0) {
          null = bool;
          if ($Gr$Eq(paramObject3, paramArrayOfObject[0])) {
            null = bool;
            if (applyN(24, paramArrayOfObject))
              return true; 
          } 
          return null;
        } 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public static boolean $Gr$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    boolean bool = false;
    null = bool;
    if ($Gr(paramObject1, paramObject2)) {
      null = bool;
      if ($Gr(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0) {
          null = bool;
          if ($Gr(paramObject3, paramArrayOfObject[0])) {
            null = bool;
            if (applyN(16, paramArrayOfObject))
              return true; 
          } 
          return null;
        } 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public static boolean $Ls(Object paramObject1, Object paramObject2) {
    return apply2(4, paramObject1, paramObject2);
  }
  
  public static boolean $Ls$Eq(Object paramObject1, Object paramObject2) {
    return apply2(12, paramObject1, paramObject2);
  }
  
  public static boolean $Ls$Eq$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    boolean bool = false;
    null = bool;
    if ($Ls$Eq(paramObject1, paramObject2)) {
      null = bool;
      if ($Ls$Eq(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0) {
          null = bool;
          if ($Ls$Eq(paramObject3, paramArrayOfObject[0])) {
            null = bool;
            if (applyN(12, paramArrayOfObject))
              return true; 
          } 
          return null;
        } 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public static boolean $Ls$V(Object paramObject1, Object paramObject2, Object paramObject3, Object[] paramArrayOfObject) {
    boolean bool = false;
    null = bool;
    if ($Ls(paramObject1, paramObject2)) {
      null = bool;
      if ($Ls(paramObject2, paramObject3)) {
        if (paramArrayOfObject.length != 0) {
          null = bool;
          if ($Ls(paramObject3, paramArrayOfObject[0])) {
            null = bool;
            if (applyN(4, paramArrayOfObject))
              return true; 
          } 
          return null;
        } 
      } else {
        return null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public static boolean apply2(int paramInt, Object paramObject1, Object paramObject2) {
    return ((1 << compare(paramObject1, paramObject2, true) + 3 & paramInt) != 0);
  }
  
  static boolean applyN(int paramInt, Object[] paramArrayOfObject) {
    for (int i = 0; i < paramArrayOfObject.length - 1; i++) {
      if (!apply2(paramInt, paramArrayOfObject[i], paramArrayOfObject[i + 1]))
        return false; 
    } 
    return true;
  }
  
  public static boolean applyWithPromotion(int paramInt, Object paramObject1, Object paramObject2) {
    return checkCompareCode(compare(paramObject1, paramObject2, false), paramInt);
  }
  
  public static boolean checkCompareCode(int paramInt1, int paramInt2) {
    return ((1 << paramInt1 + 3 & paramInt2) != 0);
  }
  
  static int classify(Expression paramExpression) {
    int k;
    int j = Arithmetic.classifyType(paramExpression.getType());
    int i = j;
    if (j == 4) {
      i = j;
      if (paramExpression instanceof QuoteExp) {
        Object object = ((QuoteExp)paramExpression).getValue();
        i = j;
        if (object instanceof IntNum) {
          k = ((IntNum)object).intLength();
          if (k < 32)
            return 1; 
        } else {
          return i;
        } 
      } else {
        return i;
      } 
    } else {
      return i;
    } 
    i = j;
    return (k < 64) ? 2 : i;
  }
  
  public static int compare(Object paramObject1, int paramInt1, Object paramObject2, int paramInt2, boolean paramBoolean) {
    int i;
    long l1;
    long l2;
    if (paramInt1 < 0 || paramInt2 < 0)
      return -3; 
    if (paramInt1 < paramInt2) {
      i = paramInt2;
    } else {
      i = paramInt1;
    } 
    switch (i) {
      default:
        return Arithmetic.asNumeric(paramObject1).compare(Arithmetic.asNumeric(paramObject2));
      case 1:
        paramInt1 = Arithmetic.asInt(paramObject1);
        paramInt2 = Arithmetic.asInt(paramObject2);
        return (paramInt1 < paramInt2) ? -1 : ((paramInt1 > paramInt2) ? 1 : 0);
      case 2:
        l1 = Arithmetic.asLong(paramObject1);
        l2 = Arithmetic.asLong(paramObject2);
        return (l1 < l2) ? -1 : ((l1 > l2) ? 1 : 0);
      case 3:
        return Arithmetic.asBigInteger(paramObject1).compareTo(Arithmetic.asBigInteger(paramObject2));
      case 4:
        return IntNum.compare(Arithmetic.asIntNum(paramObject1), Arithmetic.asIntNum(paramObject2));
      case 5:
        return Arithmetic.asBigDecimal(paramObject1).compareTo(Arithmetic.asBigDecimal(paramObject2));
      case 6:
        return RatNum.compare(Arithmetic.asRatNum(paramObject1), Arithmetic.asRatNum(paramObject2));
      case 7:
        if (!paramBoolean || (paramInt1 > 6 && paramInt2 > 6)) {
          float f1 = Arithmetic.asFloat(paramObject1);
          float f2 = Arithmetic.asFloat(paramObject2);
          return (f1 > f2) ? 1 : ((f1 < f2) ? -1 : ((f1 == f2) ? 0 : -2));
        } 
        break;
      case 8:
      case 9:
        break;
    } 
    if (!paramBoolean || (paramInt1 > 6 && paramInt2 > 6)) {
      double d1 = Arithmetic.asDouble(paramObject1);
      double d2 = Arithmetic.asDouble(paramObject2);
      return (d1 > d2) ? 1 : ((d1 < d2) ? -1 : ((d1 == d2) ? 0 : -2));
    } 
  }
  
  public static int compare(Object paramObject1, Object paramObject2, boolean paramBoolean) {
    return compare(paramObject1, Arithmetic.classifyValue(paramObject1), paramObject2, Arithmetic.classifyValue(paramObject2), paramBoolean);
  }
  
  public static NumberCompare make(Language paramLanguage, String paramString, int paramInt) {
    NumberCompare numberCompare = new NumberCompare();
    numberCompare.language = paramLanguage;
    numberCompare.setName(paramString);
    numberCompare.flags = paramInt;
    numberCompare.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileArith:validateApplyNumberCompare");
    return numberCompare;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return getLanguage().booleanObject(apply2(this.flags, paramObject1, paramObject2));
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return getLanguage().booleanObject(applyN(this.flags, paramArrayOfObject));
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getArgs : ()[Lgnu/expr/Expression;
    //   4: astore #13
    //   6: aload #13
    //   8: arraylength
    //   9: iconst_2
    //   10: if_icmpne -> 790
    //   13: aload #13
    //   15: iconst_0
    //   16: aaload
    //   17: astore #11
    //   19: aload #13
    //   21: iconst_1
    //   22: aaload
    //   23: astore #12
    //   25: aload #11
    //   27: invokestatic classify : (Lgnu/expr/Expression;)I
    //   30: istore #8
    //   32: aload #12
    //   34: invokestatic classify : (Lgnu/expr/Expression;)I
    //   37: istore #9
    //   39: aload_2
    //   40: invokevirtual getCode : ()Lgnu/bytecode/CodeAttr;
    //   43: astore #14
    //   45: iload #8
    //   47: ifle -> 790
    //   50: iload #9
    //   52: ifle -> 790
    //   55: iload #8
    //   57: bipush #10
    //   59: if_icmpgt -> 790
    //   62: iload #9
    //   64: bipush #10
    //   66: if_icmpgt -> 790
    //   69: iload #8
    //   71: bipush #6
    //   73: if_icmpne -> 83
    //   76: iload #9
    //   78: bipush #6
    //   80: if_icmpeq -> 790
    //   83: aload_3
    //   84: instanceof gnu/expr/ConditionalTarget
    //   87: ifne -> 103
    //   90: aload_1
    //   91: getstatic gnu/expr/QuoteExp.trueExp : Lgnu/expr/QuoteExp;
    //   94: getstatic gnu/expr/QuoteExp.falseExp : Lgnu/expr/QuoteExp;
    //   97: aload_2
    //   98: aload_3
    //   99: invokestatic compile : (Lgnu/expr/Expression;Lgnu/expr/Expression;Lgnu/expr/Expression;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   102: return
    //   103: aload_0
    //   104: getfield flags : I
    //   107: istore #5
    //   109: iload #5
    //   111: istore #4
    //   113: iload #5
    //   115: iconst_1
    //   116: if_icmpne -> 123
    //   119: bipush #20
    //   121: istore #4
    //   123: aload #11
    //   125: astore #10
    //   127: aload #12
    //   129: astore_1
    //   130: iload #8
    //   132: istore #7
    //   134: iload #9
    //   136: istore #6
    //   138: iload #4
    //   140: istore #5
    //   142: iload #8
    //   144: iconst_4
    //   145: if_icmpgt -> 281
    //   148: aload #11
    //   150: astore #10
    //   152: aload #12
    //   154: astore_1
    //   155: iload #8
    //   157: istore #7
    //   159: iload #9
    //   161: istore #6
    //   163: iload #4
    //   165: istore #5
    //   167: iload #9
    //   169: iconst_4
    //   170: if_icmpgt -> 281
    //   173: iload #8
    //   175: iconst_2
    //   176: if_icmpgt -> 204
    //   179: aload #11
    //   181: astore #10
    //   183: aload #12
    //   185: astore_1
    //   186: iload #8
    //   188: istore #7
    //   190: iload #9
    //   192: istore #6
    //   194: iload #4
    //   196: istore #5
    //   198: iload #9
    //   200: iconst_2
    //   201: if_icmple -> 281
    //   204: iconst_2
    //   205: anewarray gnu/bytecode/Type
    //   208: astore #15
    //   210: aload #15
    //   212: iconst_0
    //   213: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   216: aastore
    //   217: iload #9
    //   219: iconst_2
    //   220: if_icmpgt -> 566
    //   223: aload #15
    //   225: iconst_1
    //   226: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   229: aastore
    //   230: iload #4
    //   232: istore #5
    //   234: aload #13
    //   236: astore_1
    //   237: new gnu/expr/ApplyExp
    //   240: dup
    //   241: new gnu/expr/PrimProcedure
    //   244: dup
    //   245: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   248: ldc 'compare'
    //   250: aload #15
    //   252: invokevirtual getMethod : (Ljava/lang/String;[Lgnu/bytecode/Type;)Lgnu/bytecode/Method;
    //   255: invokespecial <init> : (Lgnu/bytecode/Method;)V
    //   258: aload_1
    //   259: invokespecial <init> : (Lgnu/mapping/Procedure;[Lgnu/expr/Expression;)V
    //   262: astore #10
    //   264: new gnu/expr/QuoteExp
    //   267: dup
    //   268: invokestatic zero : ()Lgnu/math/IntNum;
    //   271: invokespecial <init> : (Ljava/lang/Object;)V
    //   274: astore_1
    //   275: iconst_1
    //   276: istore #6
    //   278: iconst_1
    //   279: istore #7
    //   281: iload #7
    //   283: iconst_1
    //   284: if_icmpgt -> 687
    //   287: iload #6
    //   289: iconst_1
    //   290: if_icmpgt -> 687
    //   293: getstatic gnu/bytecode/Type.intType : Lgnu/bytecode/PrimType;
    //   296: astore #11
    //   298: new gnu/expr/StackTarget
    //   301: dup
    //   302: aload #11
    //   304: invokespecial <init> : (Lgnu/bytecode/Type;)V
    //   307: astore #12
    //   309: aload_3
    //   310: checkcast gnu/expr/ConditionalTarget
    //   313: astore #13
    //   315: aload #10
    //   317: astore #11
    //   319: aload_1
    //   320: astore_3
    //   321: iload #5
    //   323: istore #4
    //   325: aload #10
    //   327: instanceof gnu/expr/QuoteExp
    //   330: ifeq -> 397
    //   333: aload #10
    //   335: astore #11
    //   337: aload_1
    //   338: astore_3
    //   339: iload #5
    //   341: istore #4
    //   343: aload_1
    //   344: instanceof gnu/expr/QuoteExp
    //   347: ifne -> 397
    //   350: aload_1
    //   351: astore #11
    //   353: aload #10
    //   355: astore_3
    //   356: iload #5
    //   358: istore #4
    //   360: iload #5
    //   362: bipush #8
    //   364: if_icmpeq -> 397
    //   367: aload_1
    //   368: astore #11
    //   370: aload #10
    //   372: astore_3
    //   373: iload #5
    //   375: istore #4
    //   377: iload #5
    //   379: bipush #20
    //   381: if_icmpeq -> 397
    //   384: iload #5
    //   386: bipush #20
    //   388: ixor
    //   389: istore #4
    //   391: aload #10
    //   393: astore_3
    //   394: aload_1
    //   395: astore #11
    //   397: aload #13
    //   399: getfield trueBranchComesFirst : Z
    //   402: ifeq -> 715
    //   405: aload #13
    //   407: getfield ifFalse : Lgnu/bytecode/Label;
    //   410: astore_1
    //   411: iload #4
    //   413: istore #5
    //   415: aload #13
    //   417: getfield trueBranchComesFirst : Z
    //   420: ifeq -> 430
    //   423: iload #4
    //   425: bipush #28
    //   427: ixor
    //   428: istore #5
    //   430: iload #5
    //   432: lookupswitch default -> 492, 4 -> 740, 8 -> 732, 12 -> 764, 16 -> 724, 20 -> 748, 24 -> 756
    //   492: iconst_0
    //   493: istore #4
    //   495: aload #11
    //   497: aload_2
    //   498: aload #12
    //   500: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   503: iload #7
    //   505: iconst_1
    //   506: if_icmpgt -> 772
    //   509: iload #6
    //   511: iconst_1
    //   512: if_icmpgt -> 772
    //   515: aload_3
    //   516: instanceof gnu/expr/QuoteExp
    //   519: ifeq -> 772
    //   522: aload_3
    //   523: checkcast gnu/expr/QuoteExp
    //   526: invokevirtual getValue : ()Ljava/lang/Object;
    //   529: astore #10
    //   531: aload #10
    //   533: instanceof gnu/math/IntNum
    //   536: ifeq -> 772
    //   539: aload #10
    //   541: checkcast gnu/math/IntNum
    //   544: invokevirtual isZero : ()Z
    //   547: ifeq -> 772
    //   550: aload #14
    //   552: aload_1
    //   553: iload #4
    //   555: invokevirtual emitGotoIfCompare1 : (Lgnu/bytecode/Label;I)V
    //   558: aload #13
    //   560: aload #14
    //   562: invokevirtual emitGotoFirstBranch : (Lgnu/bytecode/CodeAttr;)V
    //   565: return
    //   566: iload #8
    //   568: iconst_2
    //   569: if_icmpgt -> 670
    //   572: aload #11
    //   574: instanceof gnu/expr/QuoteExp
    //   577: ifne -> 604
    //   580: aload #12
    //   582: instanceof gnu/expr/QuoteExp
    //   585: ifne -> 604
    //   588: aload #11
    //   590: instanceof gnu/expr/ReferenceExp
    //   593: ifne -> 604
    //   596: aload #12
    //   598: instanceof gnu/expr/ReferenceExp
    //   601: ifeq -> 670
    //   604: aload #15
    //   606: iconst_1
    //   607: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   610: aastore
    //   611: iconst_2
    //   612: anewarray gnu/expr/Expression
    //   615: astore #10
    //   617: aload #10
    //   619: iconst_0
    //   620: aload #12
    //   622: aastore
    //   623: aload #10
    //   625: iconst_1
    //   626: aload #11
    //   628: aastore
    //   629: aload #10
    //   631: astore_1
    //   632: iload #4
    //   634: istore #5
    //   636: iload #4
    //   638: bipush #8
    //   640: if_icmpeq -> 237
    //   643: aload #10
    //   645: astore_1
    //   646: iload #4
    //   648: istore #5
    //   650: iload #4
    //   652: bipush #20
    //   654: if_icmpeq -> 237
    //   657: iload #4
    //   659: bipush #20
    //   661: ixor
    //   662: istore #5
    //   664: aload #10
    //   666: astore_1
    //   667: goto -> 237
    //   670: aload #15
    //   672: iconst_1
    //   673: getstatic gnu/kawa/functions/Arithmetic.typeIntNum : Lgnu/kawa/lispexpr/LangObjType;
    //   676: aastore
    //   677: aload #13
    //   679: astore_1
    //   680: iload #4
    //   682: istore #5
    //   684: goto -> 237
    //   687: iload #7
    //   689: iconst_2
    //   690: if_icmpgt -> 707
    //   693: iload #6
    //   695: iconst_2
    //   696: if_icmpgt -> 707
    //   699: getstatic gnu/bytecode/Type.longType : Lgnu/bytecode/PrimType;
    //   702: astore #11
    //   704: goto -> 298
    //   707: getstatic gnu/bytecode/Type.doubleType : Lgnu/bytecode/PrimType;
    //   710: astore #11
    //   712: goto -> 298
    //   715: aload #13
    //   717: getfield ifTrue : Lgnu/bytecode/Label;
    //   720: astore_1
    //   721: goto -> 411
    //   724: sipush #157
    //   727: istore #4
    //   729: goto -> 495
    //   732: sipush #153
    //   735: istore #4
    //   737: goto -> 495
    //   740: sipush #155
    //   743: istore #4
    //   745: goto -> 495
    //   748: sipush #154
    //   751: istore #4
    //   753: goto -> 495
    //   756: sipush #156
    //   759: istore #4
    //   761: goto -> 495
    //   764: sipush #158
    //   767: istore #4
    //   769: goto -> 495
    //   772: aload_3
    //   773: aload_2
    //   774: aload #12
    //   776: invokevirtual compile : (Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   779: aload #14
    //   781: aload_1
    //   782: iload #4
    //   784: invokevirtual emitGotoIfCompare2 : (Lgnu/bytecode/Label;I)V
    //   787: goto -> 558
    //   790: aload_1
    //   791: aload_2
    //   792: aload_3
    //   793: invokestatic compile : (Lgnu/expr/ApplyExp;Lgnu/expr/Compilation;Lgnu/expr/Target;)V
    //   796: return
  }
  
  protected final Language getLanguage() {
    return this.language;
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.booleanType;
  }
  
  public int numArgs() {
    return -4094;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/NumberCompare.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */