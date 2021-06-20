package gnu.xquery.util;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1or2;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class ArithOp extends Procedure1or2 implements Inlineable {
  static final BigInteger TEN = BigInteger.valueOf(10L);
  
  public static final ArithOp add = new ArithOp("+", '+', 2);
  
  public static final ArithOp div;
  
  public static final ArithOp idiv;
  
  public static final ArithOp minus;
  
  public static final ArithOp mod;
  
  public static final ArithOp mul;
  
  public static final ArithOp plus;
  
  public static final ArithOp sub = new ArithOp("-", '-', 2);
  
  char op;
  
  static {
    mul = new ArithOp("*", '*', 2);
    div = new ArithOp("div", 'd', 2);
    idiv = new ArithOp("idiv", 'i', 2);
    mod = new ArithOp("mod", 'm', 2);
    plus = new ArithOp("+", 'P', 1);
    minus = new ArithOp("-", 'M', 1);
  }
  
  ArithOp(String paramString, char paramChar, int paramInt) {
    super(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.xquery.util.CompileMisc:validateArithOp");
    this.op = paramChar;
  }
  
  public static BigDecimal div(BigDecimal paramBigDecimal1, BigDecimal paramBigDecimal2) {
    return paramBigDecimal1.divide(paramBigDecimal2, MathContext.DECIMAL128);
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    // Byte code:
    //   0: aload_1
    //   1: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   4: if_acmpeq -> 11
    //   7: aload_1
    //   8: ifnonnull -> 13
    //   11: aload_1
    //   12: areturn
    //   13: aload_1
    //   14: instanceof gnu/kawa/xml/KNode
    //   17: ifne -> 29
    //   20: aload_1
    //   21: astore_2
    //   22: aload_1
    //   23: instanceof gnu/kawa/xml/UntypedAtomic
    //   26: ifeq -> 40
    //   29: getstatic gnu/kawa/xml/XDataType.doubleType : Lgnu/kawa/xml/XDataType;
    //   32: aload_1
    //   33: invokestatic stringValue : (Ljava/lang/Object;)Ljava/lang/String;
    //   36: invokevirtual valueOf : (Ljava/lang/String;)Ljava/lang/Object;
    //   39: astore_2
    //   40: aload_0
    //   41: getfield op : C
    //   44: tableswitch default -> 76, 77 -> 97, 78 -> 76, 79 -> 76, 80 -> 88
    //   76: new java/lang/UnsupportedOperationException
    //   79: dup
    //   80: aload_0
    //   81: invokevirtual getName : ()Ljava/lang/String;
    //   84: invokespecial <init> : (Ljava/lang/String;)V
    //   87: athrow
    //   88: iconst_1
    //   89: invokestatic zero : ()Lgnu/math/IntNum;
    //   92: aload_2
    //   93: invokestatic apply2 : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   96: areturn
    //   97: aload_2
    //   98: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   101: tableswitch default -> 124, 7 -> 139, 8 -> 148
    //   124: aload_2
    //   125: instanceof gnu/math/Numeric
    //   128: ifeq -> 157
    //   131: aload_2
    //   132: checkcast gnu/math/Numeric
    //   135: invokevirtual neg : ()Lgnu/math/Numeric;
    //   138: areturn
    //   139: aload_2
    //   140: invokestatic asFloat : (Ljava/lang/Object;)F
    //   143: fneg
    //   144: invokestatic makeFloat : (F)Ljava/lang/Float;
    //   147: areturn
    //   148: aload_2
    //   149: invokestatic asDouble : (Ljava/lang/Object;)D
    //   152: dneg
    //   153: invokestatic makeDouble : (D)Ljava/lang/Double;
    //   156: areturn
    //   157: iconst_m1
    //   158: invokestatic zero : ()Lgnu/math/IntNum;
    //   161: aload_2
    //   162: invokestatic apply2 : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   165: areturn
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    // Byte code:
    //   0: aload_1
    //   1: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   4: if_acmpeq -> 11
    //   7: aload_1
    //   8: ifnonnull -> 13
    //   11: aload_1
    //   12: areturn
    //   13: aload_2
    //   14: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   17: if_acmpeq -> 24
    //   20: aload_2
    //   21: ifnonnull -> 26
    //   24: aload_2
    //   25: areturn
    //   26: aload_1
    //   27: instanceof gnu/kawa/xml/KNode
    //   30: ifne -> 43
    //   33: aload_1
    //   34: astore #12
    //   36: aload_1
    //   37: instanceof gnu/kawa/xml/UntypedAtomic
    //   40: ifeq -> 55
    //   43: getstatic gnu/kawa/xml/XDataType.doubleType : Lgnu/kawa/xml/XDataType;
    //   46: aload_1
    //   47: invokestatic stringValue : (Ljava/lang/Object;)Ljava/lang/String;
    //   50: invokevirtual valueOf : (Ljava/lang/String;)Ljava/lang/Object;
    //   53: astore #12
    //   55: aload_2
    //   56: instanceof gnu/kawa/xml/KNode
    //   59: ifne -> 71
    //   62: aload_2
    //   63: astore_1
    //   64: aload_2
    //   65: instanceof gnu/kawa/xml/UntypedAtomic
    //   68: ifeq -> 82
    //   71: getstatic gnu/kawa/xml/XDataType.doubleType : Lgnu/kawa/xml/XDataType;
    //   74: aload_2
    //   75: invokestatic stringValue : (Ljava/lang/Object;)Ljava/lang/String;
    //   78: invokevirtual valueOf : (Ljava/lang/String;)Ljava/lang/Object;
    //   81: astore_1
    //   82: aload_0
    //   83: getfield op : C
    //   86: tableswitch default -> 116, 42 -> 208, 43 -> 192, 44 -> 116, 45 -> 200
    //   116: aload #12
    //   118: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   121: istore #6
    //   123: aload_1
    //   124: invokestatic classifyValue : (Ljava/lang/Object;)I
    //   127: istore #7
    //   129: iload #6
    //   131: iload #7
    //   133: if_icmpge -> 218
    //   136: iload #7
    //   138: istore #5
    //   140: aload_0
    //   141: getfield op : C
    //   144: lookupswitch default -> 180, 100 -> 225, 105 -> 485, 109 -> 601
    //   180: new java/lang/UnsupportedOperationException
    //   183: dup
    //   184: aload_0
    //   185: invokevirtual getName : ()Ljava/lang/String;
    //   188: invokespecial <init> : (Ljava/lang/String;)V
    //   191: athrow
    //   192: iconst_1
    //   193: aload #12
    //   195: aload_1
    //   196: invokestatic apply2 : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   199: areturn
    //   200: iconst_m1
    //   201: aload #12
    //   203: aload_1
    //   204: invokestatic apply2 : (ILjava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   207: areturn
    //   208: getstatic gnu/kawa/functions/MultiplyOp.$St : Lgnu/kawa/functions/MultiplyOp;
    //   211: aload #12
    //   213: aload_1
    //   214: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   217: areturn
    //   218: iload #6
    //   220: istore #5
    //   222: goto -> 140
    //   225: iload #6
    //   227: iflt -> 180
    //   230: iload #7
    //   232: iflt -> 180
    //   235: iload #5
    //   237: bipush #6
    //   239: if_icmpgt -> 267
    //   242: getstatic gnu/kawa/xml/XDataType.decimalType : Lgnu/kawa/xml/XDataType;
    //   245: aload #12
    //   247: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   250: checkcast java/math/BigDecimal
    //   253: getstatic gnu/kawa/xml/XDataType.decimalType : Lgnu/kawa/xml/XDataType;
    //   256: aload_1
    //   257: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   260: checkcast java/math/BigDecimal
    //   263: invokestatic div : (Ljava/math/BigDecimal;Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   266: areturn
    //   267: iload #5
    //   269: bipush #7
    //   271: if_icmpne -> 298
    //   274: new java/lang/Float
    //   277: dup
    //   278: aload #12
    //   280: checkcast java/lang/Number
    //   283: invokevirtual floatValue : ()F
    //   286: aload_1
    //   287: checkcast java/lang/Number
    //   290: invokevirtual floatValue : ()F
    //   293: fdiv
    //   294: invokespecial <init> : (F)V
    //   297: areturn
    //   298: iload #5
    //   300: bipush #8
    //   302: if_icmpne -> 329
    //   305: new java/lang/Double
    //   308: dup
    //   309: aload #12
    //   311: checkcast java/lang/Number
    //   314: invokevirtual doubleValue : ()D
    //   317: aload_1
    //   318: checkcast java/lang/Number
    //   321: invokevirtual doubleValue : ()D
    //   324: ddiv
    //   325: invokespecial <init> : (D)V
    //   328: areturn
    //   329: aload #12
    //   331: instanceof gnu/math/Duration
    //   334: ifeq -> 467
    //   337: aload_1
    //   338: instanceof gnu/math/Duration
    //   341: ifeq -> 467
    //   344: aload #12
    //   346: checkcast gnu/math/Duration
    //   349: astore_2
    //   350: aload_1
    //   351: checkcast gnu/math/Duration
    //   354: astore_1
    //   355: aload_2
    //   356: invokevirtual unit : ()Lgnu/math/Unit;
    //   359: getstatic gnu/math/Unit.second : Lgnu/math/NamedUnit;
    //   362: if_acmpne -> 417
    //   365: aload_1
    //   366: invokevirtual unit : ()Lgnu/math/Unit;
    //   369: getstatic gnu/math/Unit.second : Lgnu/math/NamedUnit;
    //   372: if_acmpne -> 417
    //   375: aload_2
    //   376: invokevirtual getTotalSeconds : ()J
    //   379: lstore #8
    //   381: aload_1
    //   382: invokevirtual getTotalSeconds : ()J
    //   385: lstore #10
    //   387: aload_2
    //   388: invokevirtual getNanoSecondsOnly : ()I
    //   391: istore #5
    //   393: aload_1
    //   394: invokevirtual getNanoSecondsOnly : ()I
    //   397: istore #6
    //   399: lload #8
    //   401: iload #5
    //   403: invokestatic secondsBigDecimalFromDuration : (JI)Ljava/math/BigDecimal;
    //   406: lload #10
    //   408: iload #6
    //   410: invokestatic secondsBigDecimalFromDuration : (JI)Ljava/math/BigDecimal;
    //   413: invokestatic div : (Ljava/math/BigDecimal;Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   416: areturn
    //   417: aload_2
    //   418: invokevirtual unit : ()Lgnu/math/Unit;
    //   421: getstatic gnu/math/Unit.month : Lgnu/math/NamedUnit;
    //   424: if_acmpne -> 457
    //   427: aload_1
    //   428: invokevirtual unit : ()Lgnu/math/Unit;
    //   431: getstatic gnu/math/Unit.month : Lgnu/math/NamedUnit;
    //   434: if_acmpne -> 457
    //   437: aload_2
    //   438: invokevirtual getTotalMonths : ()I
    //   441: i2l
    //   442: invokestatic valueOf : (J)Ljava/math/BigDecimal;
    //   445: aload_1
    //   446: invokevirtual getTotalMonths : ()I
    //   449: i2l
    //   450: invokestatic valueOf : (J)Ljava/math/BigDecimal;
    //   453: invokestatic div : (Ljava/math/BigDecimal;Ljava/math/BigDecimal;)Ljava/math/BigDecimal;
    //   456: areturn
    //   457: new java/lang/ArithmeticException
    //   460: dup
    //   461: ldc 'divide of incompatible durations'
    //   463: invokespecial <init> : (Ljava/lang/String;)V
    //   466: athrow
    //   467: iload #5
    //   469: iflt -> 485
    //   472: aload #12
    //   474: invokestatic asNumeric : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   477: aload_1
    //   478: invokestatic asNumeric : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   481: invokevirtual div : (Ljava/lang/Object;)Lgnu/math/Numeric;
    //   484: areturn
    //   485: iload #6
    //   487: iflt -> 180
    //   490: iload #7
    //   492: iflt -> 180
    //   495: iload #5
    //   497: iconst_4
    //   498: if_icmpgt -> 514
    //   501: aload #12
    //   503: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   506: aload_1
    //   507: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   510: invokestatic quotient : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   513: areturn
    //   514: iload #5
    //   516: bipush #6
    //   518: if_icmpgt -> 551
    //   521: getstatic gnu/kawa/xml/XDataType.decimalType : Lgnu/kawa/xml/XDataType;
    //   524: aload #12
    //   526: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   529: checkcast java/math/BigDecimal
    //   532: getstatic gnu/kawa/xml/XDataType.decimalType : Lgnu/kawa/xml/XDataType;
    //   535: aload_1
    //   536: invokevirtual cast : (Ljava/lang/Object;)Ljava/lang/Object;
    //   539: checkcast java/math/BigDecimal
    //   542: iconst_0
    //   543: iconst_1
    //   544: invokevirtual divide : (Ljava/math/BigDecimal;II)Ljava/math/BigDecimal;
    //   547: invokestatic asIntNum : (Ljava/math/BigDecimal;)Lgnu/math/IntNum;
    //   550: areturn
    //   551: iload #5
    //   553: bipush #7
    //   555: if_icmpgt -> 580
    //   558: aload #12
    //   560: checkcast java/lang/Number
    //   563: invokevirtual floatValue : ()F
    //   566: aload_1
    //   567: checkcast java/lang/Number
    //   570: invokevirtual floatValue : ()F
    //   573: fdiv
    //   574: f2d
    //   575: iconst_3
    //   576: invokestatic toExactInt : (DI)Lgnu/math/IntNum;
    //   579: areturn
    //   580: aload #12
    //   582: checkcast java/lang/Number
    //   585: invokevirtual doubleValue : ()D
    //   588: aload_1
    //   589: checkcast java/lang/Number
    //   592: invokevirtual doubleValue : ()D
    //   595: ddiv
    //   596: iconst_3
    //   597: invokestatic toExactInt : (DI)Lgnu/math/IntNum;
    //   600: areturn
    //   601: iload #6
    //   603: iflt -> 180
    //   606: iload #7
    //   608: iflt -> 180
    //   611: iload #5
    //   613: iconst_4
    //   614: if_icmpgt -> 630
    //   617: aload #12
    //   619: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   622: aload_1
    //   623: invokestatic asIntNum : (Ljava/lang/Object;)Lgnu/math/IntNum;
    //   626: invokestatic remainder : (Lgnu/math/IntNum;Lgnu/math/IntNum;)Lgnu/math/IntNum;
    //   629: areturn
    //   630: iload #5
    //   632: bipush #6
    //   634: if_icmpgt -> 662
    //   637: getstatic gnu/xquery/util/ArithOp.sub : Lgnu/xquery/util/ArithOp;
    //   640: aload #12
    //   642: getstatic gnu/xquery/util/ArithOp.mul : Lgnu/xquery/util/ArithOp;
    //   645: getstatic gnu/xquery/util/ArithOp.idiv : Lgnu/xquery/util/ArithOp;
    //   648: aload #12
    //   650: aload_1
    //   651: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   654: aload_1
    //   655: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   658: invokevirtual apply2 : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   661: areturn
    //   662: iload #5
    //   664: bipush #7
    //   666: if_icmpgt -> 683
    //   669: aload #12
    //   671: invokestatic asFloat : (Ljava/lang/Object;)F
    //   674: aload_1
    //   675: invokestatic asFloat : (Ljava/lang/Object;)F
    //   678: frem
    //   679: invokestatic makeFloat : (F)Ljava/lang/Float;
    //   682: areturn
    //   683: iload #5
    //   685: bipush #9
    //   687: if_icmpgt -> 180
    //   690: aload #12
    //   692: invokestatic asDouble : (Ljava/lang/Object;)D
    //   695: aload_1
    //   696: invokestatic asDouble : (Ljava/lang/Object;)D
    //   699: drem
    //   700: dstore_3
    //   701: iload #5
    //   703: bipush #9
    //   705: if_icmpne -> 713
    //   708: dload_3
    //   709: invokestatic make : (D)Lgnu/math/DFloNum;
    //   712: areturn
    //   713: dload_3
    //   714: invokestatic makeDouble : (D)Ljava/lang/Double;
    //   717: areturn
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    ApplyExp.compile(paramApplyExp, paramCompilation, paramTarget);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/ArithOp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */