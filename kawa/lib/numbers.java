package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.AddOp;
import gnu.kawa.functions.Arithmetic;
import gnu.kawa.functions.DivideOp;
import gnu.kawa.functions.Format;
import gnu.kawa.functions.GetNamedPart;
import gnu.kawa.functions.MultiplyOp;
import gnu.kawa.lispexpr.LangObjType;
import gnu.kawa.lispexpr.LispReader;
import gnu.lists.Consumer;
import gnu.lists.FString;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;
import gnu.math.BitOps;
import gnu.math.Complex;
import gnu.math.DComplex;
import gnu.math.Duration;
import gnu.math.IntNum;
import gnu.math.NamedUnit;
import gnu.math.Numeric;
import gnu.math.Quantity;
import gnu.math.RatNum;
import gnu.math.RealNum;
import gnu.math.Unit;
import kawa.standard.Scheme;

public class numbers extends ModuleBody {
  public static final numbers $instance;
  
  static final IntNum Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit10;
  
  static final SimpleSymbol Lit11;
  
  static final SimpleSymbol Lit12;
  
  static final SimpleSymbol Lit13;
  
  static final SimpleSymbol Lit14;
  
  static final SimpleSymbol Lit15;
  
  static final SimpleSymbol Lit16;
  
  static final SimpleSymbol Lit17;
  
  static final SimpleSymbol Lit18;
  
  static final SimpleSymbol Lit19;
  
  static final IntNum Lit2;
  
  static final SimpleSymbol Lit20;
  
  static final SimpleSymbol Lit21;
  
  static final SimpleSymbol Lit22;
  
  static final SimpleSymbol Lit23;
  
  static final SimpleSymbol Lit24;
  
  static final SimpleSymbol Lit25;
  
  static final SimpleSymbol Lit26;
  
  static final SimpleSymbol Lit27;
  
  static final SimpleSymbol Lit28;
  
  static final SimpleSymbol Lit29;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit30;
  
  static final SimpleSymbol Lit31;
  
  static final SimpleSymbol Lit32;
  
  static final SimpleSymbol Lit33;
  
  static final SimpleSymbol Lit34;
  
  static final SimpleSymbol Lit35;
  
  static final SimpleSymbol Lit36;
  
  static final SimpleSymbol Lit37;
  
  static final SimpleSymbol Lit38;
  
  static final SimpleSymbol Lit39;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit40;
  
  static final SimpleSymbol Lit41;
  
  static final SimpleSymbol Lit42;
  
  static final SimpleSymbol Lit43;
  
  static final SimpleSymbol Lit44;
  
  static final SimpleSymbol Lit45;
  
  static final SimpleSymbol Lit46;
  
  static final SimpleSymbol Lit47;
  
  static final SimpleSymbol Lit48;
  
  static final SimpleSymbol Lit49;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit50;
  
  static final SimpleSymbol Lit51;
  
  static final SimpleSymbol Lit52;
  
  static final SimpleSymbol Lit53;
  
  static final SimpleSymbol Lit54;
  
  static final SimpleSymbol Lit55;
  
  static final SimpleSymbol Lit56;
  
  static final SimpleSymbol Lit57;
  
  static final SimpleSymbol Lit58;
  
  static final SimpleSymbol Lit59;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit60;
  
  static final SimpleSymbol Lit61;
  
  static final SimpleSymbol Lit62;
  
  static final SimpleSymbol Lit63 = (SimpleSymbol)(new SimpleSymbol("duration")).readResolve();
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8;
  
  static final SimpleSymbol Lit9;
  
  public static final ModuleMethod abs;
  
  public static final ModuleMethod acos;
  
  public static final ModuleMethod angle;
  
  public static final ModuleMethod asin;
  
  public static final GenericProc atan;
  
  public static final ModuleMethod bitwise$Mnbit$Mncount;
  
  public static final ModuleMethod bitwise$Mnbit$Mnfield;
  
  public static final ModuleMethod bitwise$Mnbit$Mnset$Qu;
  
  public static final ModuleMethod bitwise$Mncopy$Mnbit;
  
  public static final ModuleMethod bitwise$Mncopy$Mnbit$Mnfield;
  
  public static final ModuleMethod bitwise$Mnfirst$Mnbit$Mnset;
  
  public static final ModuleMethod bitwise$Mnif;
  
  public static final ModuleMethod bitwise$Mnlength;
  
  public static final ModuleMethod bitwise$Mnreverse$Mnbit$Mnfield;
  
  public static final ModuleMethod bitwise$Mnrotate$Mnbit$Mnfield;
  
  public static final ModuleMethod ceiling;
  
  public static final ModuleMethod complex$Qu;
  
  public static final ModuleMethod cos;
  
  public static final ModuleMethod denominator;
  
  public static final ModuleMethod div$Mnand$Mnmod;
  
  public static final ModuleMethod div0$Mnand$Mnmod0;
  
  public static final ModuleMethod duration;
  
  public static final ModuleMethod exact;
  
  public static final ModuleMethod exact$Mn$Grinexact;
  
  public static final ModuleMethod exact$Qu;
  
  public static final ModuleMethod exp;
  
  public static final ModuleMethod floor;
  
  public static final ModuleMethod gcd;
  
  public static final ModuleMethod imag$Mnpart;
  
  public static final ModuleMethod inexact;
  
  public static final ModuleMethod inexact$Mn$Grexact;
  
  public static final ModuleMethod inexact$Qu;
  
  public static final ModuleMethod integer$Qu;
  
  static final ModuleMethod lambda$Fn1;
  
  static final ModuleMethod lambda$Fn2;
  
  static final ModuleMethod lambda$Fn3;
  
  static final ModuleMethod lambda$Fn4;
  
  public static final ModuleMethod lcm;
  
  public static final ModuleMethod log;
  
  public static final ModuleMethod logcount;
  
  public static final ModuleMethod logop;
  
  public static final ModuleMethod logtest;
  
  public static final ModuleMethod magnitude;
  
  public static final ModuleMethod make$Mnpolar;
  
  public static final ModuleMethod make$Mnquantity;
  
  public static final ModuleMethod make$Mnrectangular;
  
  public static final ModuleMethod max;
  
  public static final ModuleMethod min;
  
  public static final ModuleMethod negative$Qu;
  
  public static final ModuleMethod number$Mn$Grstring;
  
  public static final ModuleMethod number$Qu;
  
  public static final ModuleMethod numerator;
  
  public static final ModuleMethod positive$Qu;
  
  public static final ModuleMethod quantity$Mn$Grnumber;
  
  public static final ModuleMethod quantity$Mn$Grunit;
  
  public static final ModuleMethod quantity$Qu;
  
  public static final ModuleMethod rational$Qu;
  
  public static final ModuleMethod rationalize;
  
  public static final ModuleMethod real$Mnpart;
  
  public static final ModuleMethod real$Qu;
  
  public static final ModuleMethod round;
  
  public static final ModuleMethod sin;
  
  public static final GenericProc sqrt;
  
  public static final ModuleMethod string$Mn$Grnumber;
  
  public static final ModuleMethod tan;
  
  public static final ModuleMethod truncate;
  
  public static final ModuleMethod zero$Qu;
  
  static {
    Lit62 = (SimpleSymbol)(new SimpleSymbol("make-quantity")).readResolve();
    Lit61 = (SimpleSymbol)(new SimpleSymbol("quantity->unit")).readResolve();
    Lit60 = (SimpleSymbol)(new SimpleSymbol("quantity->number")).readResolve();
    Lit59 = (SimpleSymbol)(new SimpleSymbol("string->number")).readResolve();
    Lit58 = (SimpleSymbol)(new SimpleSymbol("number->string")).readResolve();
    Lit57 = (SimpleSymbol)(new SimpleSymbol("bitwise-reverse-bit-field")).readResolve();
    Lit56 = (SimpleSymbol)(new SimpleSymbol("bitwise-rotate-bit-field")).readResolve();
    Lit55 = (SimpleSymbol)(new SimpleSymbol("bitwise-first-bit-set")).readResolve();
    Lit54 = (SimpleSymbol)(new SimpleSymbol("bitwise-length")).readResolve();
    Lit53 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-count")).readResolve();
    Lit52 = (SimpleSymbol)(new SimpleSymbol("logcount")).readResolve();
    Lit51 = (SimpleSymbol)(new SimpleSymbol("logtest")).readResolve();
    Lit50 = (SimpleSymbol)(new SimpleSymbol("bitwise-if")).readResolve();
    Lit49 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-field")).readResolve();
    Lit48 = (SimpleSymbol)(new SimpleSymbol("bitwise-copy-bit-field")).readResolve();
    Lit47 = (SimpleSymbol)(new SimpleSymbol("bitwise-copy-bit")).readResolve();
    Lit46 = (SimpleSymbol)(new SimpleSymbol("bitwise-bit-set?")).readResolve();
    Lit45 = (SimpleSymbol)(new SimpleSymbol("logop")).readResolve();
    Lit44 = (SimpleSymbol)(new SimpleSymbol("inexact->exact")).readResolve();
    Lit43 = (SimpleSymbol)(new SimpleSymbol("exact->inexact")).readResolve();
    Lit42 = (SimpleSymbol)(new SimpleSymbol("exact")).readResolve();
    Lit41 = (SimpleSymbol)(new SimpleSymbol("inexact")).readResolve();
    Lit40 = (SimpleSymbol)(new SimpleSymbol("angle")).readResolve();
    Lit39 = (SimpleSymbol)(new SimpleSymbol("magnitude")).readResolve();
    Lit38 = (SimpleSymbol)(new SimpleSymbol("imag-part")).readResolve();
    Lit37 = (SimpleSymbol)(new SimpleSymbol("real-part")).readResolve();
    Lit36 = (SimpleSymbol)(new SimpleSymbol("make-polar")).readResolve();
    Lit35 = (SimpleSymbol)(new SimpleSymbol("make-rectangular")).readResolve();
    Lit34 = (SimpleSymbol)(new SimpleSymbol("acos")).readResolve();
    Lit33 = (SimpleSymbol)(new SimpleSymbol("asin")).readResolve();
    Lit32 = (SimpleSymbol)(new SimpleSymbol("tan")).readResolve();
    Lit31 = (SimpleSymbol)(new SimpleSymbol("cos")).readResolve();
    Lit30 = (SimpleSymbol)(new SimpleSymbol("sin")).readResolve();
    Lit29 = (SimpleSymbol)(new SimpleSymbol("log")).readResolve();
    Lit28 = (SimpleSymbol)(new SimpleSymbol("exp")).readResolve();
    Lit27 = (SimpleSymbol)(new SimpleSymbol("rationalize")).readResolve();
    Lit26 = (SimpleSymbol)(new SimpleSymbol("round")).readResolve();
    Lit25 = (SimpleSymbol)(new SimpleSymbol("truncate")).readResolve();
    Lit24 = (SimpleSymbol)(new SimpleSymbol("ceiling")).readResolve();
    Lit23 = (SimpleSymbol)(new SimpleSymbol("floor")).readResolve();
    Lit22 = (SimpleSymbol)(new SimpleSymbol("denominator")).readResolve();
    Lit21 = (SimpleSymbol)(new SimpleSymbol("numerator")).readResolve();
    Lit20 = (SimpleSymbol)(new SimpleSymbol("lcm")).readResolve();
    Lit19 = (SimpleSymbol)(new SimpleSymbol("gcd")).readResolve();
    Lit18 = (SimpleSymbol)(new SimpleSymbol("div0-and-mod0")).readResolve();
    Lit17 = (SimpleSymbol)(new SimpleSymbol("div-and-mod")).readResolve();
    Lit16 = (SimpleSymbol)(new SimpleSymbol("abs")).readResolve();
    Lit15 = (SimpleSymbol)(new SimpleSymbol("min")).readResolve();
    Lit14 = (SimpleSymbol)(new SimpleSymbol("max")).readResolve();
    Lit13 = (SimpleSymbol)(new SimpleSymbol("negative?")).readResolve();
    Lit12 = (SimpleSymbol)(new SimpleSymbol("positive?")).readResolve();
    Lit11 = (SimpleSymbol)(new SimpleSymbol("zero?")).readResolve();
    Lit10 = (SimpleSymbol)(new SimpleSymbol("inexact?")).readResolve();
    Lit9 = (SimpleSymbol)(new SimpleSymbol("exact?")).readResolve();
    Lit8 = (SimpleSymbol)(new SimpleSymbol("integer?")).readResolve();
    Lit7 = (SimpleSymbol)(new SimpleSymbol("rational?")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("real?")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("complex?")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("quantity?")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("number?")).readResolve();
    Lit2 = IntNum.make(1);
    Lit1 = (SimpleSymbol)(new SimpleSymbol("signum")).readResolve();
    Lit0 = IntNum.make(0);
    $instance = new numbers();
    numbers numbers1 = $instance;
    number$Qu = new ModuleMethod(numbers1, 1, Lit3, 4097);
    quantity$Qu = new ModuleMethod(numbers1, 2, Lit4, 4097);
    complex$Qu = new ModuleMethod(numbers1, 3, Lit5, 4097);
    real$Qu = new ModuleMethod(numbers1, 4, Lit6, 4097);
    rational$Qu = new ModuleMethod(numbers1, 5, Lit7, 4097);
    integer$Qu = new ModuleMethod(numbers1, 6, Lit8, 4097);
    exact$Qu = new ModuleMethod(numbers1, 7, Lit9, 4097);
    inexact$Qu = new ModuleMethod(numbers1, 8, Lit10, 4097);
    zero$Qu = new ModuleMethod(numbers1, 9, Lit11, 4097);
    positive$Qu = new ModuleMethod(numbers1, 10, Lit12, 4097);
    negative$Qu = new ModuleMethod(numbers1, 11, Lit13, 4097);
    max = new ModuleMethod(numbers1, 12, Lit14, -4096);
    min = new ModuleMethod(numbers1, 13, Lit15, -4096);
    abs = new ModuleMethod(numbers1, 14, Lit16, 4097);
    div$Mnand$Mnmod = new ModuleMethod(numbers1, 15, Lit17, 8194);
    div0$Mnand$Mnmod0 = new ModuleMethod(numbers1, 16, Lit18, 8194);
    gcd = new ModuleMethod(numbers1, 17, Lit19, -4096);
    lcm = new ModuleMethod(numbers1, 18, Lit20, -4096);
    numerator = new ModuleMethod(numbers1, 19, Lit21, 4097);
    denominator = new ModuleMethod(numbers1, 20, Lit22, 4097);
    floor = new ModuleMethod(numbers1, 21, Lit23, 4097);
    ceiling = new ModuleMethod(numbers1, 22, Lit24, 4097);
    truncate = new ModuleMethod(numbers1, 23, Lit25, 4097);
    round = new ModuleMethod(numbers1, 24, Lit26, 4097);
    rationalize = new ModuleMethod(numbers1, 25, Lit27, 8194);
    exp = new ModuleMethod(numbers1, 26, Lit28, 4097);
    log = new ModuleMethod(numbers1, 27, Lit29, 4097);
    sin = new ModuleMethod(numbers1, 28, Lit30, 4097);
    cos = new ModuleMethod(numbers1, 29, Lit31, 4097);
    tan = new ModuleMethod(numbers1, 30, Lit32, 4097);
    asin = new ModuleMethod(numbers1, 31, Lit33, 4097);
    acos = new ModuleMethod(numbers1, 32, Lit34, 4097);
    ModuleMethod moduleMethod = new ModuleMethod(numbers1, 33, null, 8194);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:146");
    lambda$Fn1 = moduleMethod;
    moduleMethod = new ModuleMethod(numbers1, 34, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:148");
    lambda$Fn2 = moduleMethod;
    moduleMethod = new ModuleMethod(numbers1, 35, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:152");
    lambda$Fn3 = moduleMethod;
    moduleMethod = new ModuleMethod(numbers1, 36, null, 4097);
    moduleMethod.setProperty("source-location", "/u2/home/jis/ai2-kawa/kawa/lib/numbers.scm:156");
    lambda$Fn4 = moduleMethod;
    make$Mnrectangular = new ModuleMethod(numbers1, 37, Lit35, 8194);
    make$Mnpolar = new ModuleMethod(numbers1, 38, Lit36, 8194);
    real$Mnpart = new ModuleMethod(numbers1, 39, Lit37, 4097);
    imag$Mnpart = new ModuleMethod(numbers1, 40, Lit38, 4097);
    magnitude = new ModuleMethod(numbers1, 41, Lit39, 4097);
    angle = new ModuleMethod(numbers1, 42, Lit40, 4097);
    inexact = new ModuleMethod(numbers1, 43, Lit41, 4097);
    exact = new ModuleMethod(numbers1, 44, Lit42, 4097);
    exact$Mn$Grinexact = new ModuleMethod(numbers1, 45, Lit43, 4097);
    inexact$Mn$Grexact = new ModuleMethod(numbers1, 46, Lit44, 4097);
    logop = new ModuleMethod(numbers1, 47, Lit45, 12291);
    bitwise$Mnbit$Mnset$Qu = new ModuleMethod(numbers1, 48, Lit46, 8194);
    bitwise$Mncopy$Mnbit = new ModuleMethod(numbers1, 49, Lit47, 12291);
    bitwise$Mncopy$Mnbit$Mnfield = new ModuleMethod(numbers1, 50, Lit48, 16388);
    bitwise$Mnbit$Mnfield = new ModuleMethod(numbers1, 51, Lit49, 12291);
    bitwise$Mnif = new ModuleMethod(numbers1, 52, Lit50, 12291);
    logtest = new ModuleMethod(numbers1, 53, Lit51, 8194);
    logcount = new ModuleMethod(numbers1, 54, Lit52, 4097);
    bitwise$Mnbit$Mncount = new ModuleMethod(numbers1, 55, Lit53, 4097);
    bitwise$Mnlength = new ModuleMethod(numbers1, 56, Lit54, 4097);
    bitwise$Mnfirst$Mnbit$Mnset = new ModuleMethod(numbers1, 57, Lit55, 4097);
    bitwise$Mnrotate$Mnbit$Mnfield = new ModuleMethod(numbers1, 58, Lit56, 16388);
    bitwise$Mnreverse$Mnbit$Mnfield = new ModuleMethod(numbers1, 59, Lit57, 12291);
    number$Mn$Grstring = new ModuleMethod(numbers1, 60, Lit58, 8193);
    string$Mn$Grnumber = new ModuleMethod(numbers1, 62, Lit59, 8193);
    quantity$Mn$Grnumber = new ModuleMethod(numbers1, 64, Lit60, 4097);
    quantity$Mn$Grunit = new ModuleMethod(numbers1, 65, Lit61, 4097);
    make$Mnquantity = new ModuleMethod(numbers1, 66, Lit62, 8194);
    duration = new ModuleMethod(numbers1, 67, Lit63, 4097);
    $instance.run();
  }
  
  public numbers() {
    ModuleInfo.register(this);
  }
  
  public static Number abs(Number paramNumber) {
    if (paramNumber instanceof Numeric)
      return (Number)((Numeric)paramNumber).abs(); 
    Number number = paramNumber;
    return (Scheme.numGEq.apply2(paramNumber, Lit0) == Boolean.FALSE) ? (Number)AddOp.$Mn.apply1(paramNumber) : number;
  }
  
  public static double acos(double paramDouble) {
    return Math.acos(paramDouble);
  }
  
  public static RealNum angle(Complex paramComplex) {
    return paramComplex.angle();
  }
  
  public static double asin(double paramDouble) {
    return Math.asin(paramDouble);
  }
  
  public static int bitwiseBitCount(IntNum paramIntNum) {
    return (IntNum.compare(paramIntNum, 0L) >= 0) ? BitOps.bitCount(paramIntNum) : (-1 - BitOps.bitCount(BitOps.not(paramIntNum)));
  }
  
  public static IntNum bitwiseBitField(IntNum paramIntNum, int paramInt1, int paramInt2) {
    return BitOps.extract(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseCopyBit(IntNum paramIntNum, int paramInt1, int paramInt2) {
    return BitOps.setBitValue(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseCopyBitField(IntNum paramIntNum1, int paramInt1, int paramInt2, IntNum paramIntNum2) {
    int i = IntNum.shift(-1, paramInt1);
    IntNum intNum = BitOps.not(IntNum.make(IntNum.shift(-1, paramInt2)));
    return bitwiseIf(BitOps.and(IntNum.make(i), intNum), IntNum.shift(paramIntNum2, paramInt1), paramIntNum1);
  }
  
  public static int bitwiseFirstBitSet(IntNum paramIntNum) {
    return BitOps.lowestBitSet(paramIntNum);
  }
  
  public static IntNum bitwiseIf(IntNum paramIntNum1, IntNum paramIntNum2, IntNum paramIntNum3) {
    return BitOps.ior(BitOps.and(paramIntNum1, paramIntNum2), BitOps.and(BitOps.not(paramIntNum1), paramIntNum3));
  }
  
  public static int bitwiseLength(IntNum paramIntNum) {
    return paramIntNum.intLength();
  }
  
  public static IntNum bitwiseReverseBitField(IntNum paramIntNum, int paramInt1, int paramInt2) {
    return BitOps.reverseBits(paramIntNum, paramInt1, paramInt2);
  }
  
  public static IntNum bitwiseRotateBitField(IntNum paramIntNum, int paramInt1, int paramInt2, int paramInt3) {
    int i = paramInt2 - paramInt1;
    IntNum intNum = paramIntNum;
    if (i > 0) {
      paramInt3 %= i;
      if (paramInt3 < 0)
        paramInt3 += i; 
      intNum = bitwiseBitField(paramIntNum, paramInt1, paramInt2);
      intNum = bitwiseCopyBitField(paramIntNum, paramInt1, paramInt2, BitOps.ior(IntNum.shift(intNum, paramInt3), IntNum.shift(intNum, paramInt3 - i)));
    } 
    return intNum;
  }
  
  public static RealNum ceiling(RealNum paramRealNum) {
    return paramRealNum.toInt(Numeric.CEILING);
  }
  
  public static double cos(double paramDouble) {
    return Math.cos(paramDouble);
  }
  
  public static IntNum denominator(RatNum paramRatNum) {
    return paramRatNum.denominator();
  }
  
  public static Object div0AndMod0(RealNum paramRealNum1, RealNum paramRealNum2) {
    Object object = DivideOp.div0.apply2(paramRealNum1, paramRealNum2);
    try {
      RealNum realNum = LangObjType.coerceRealNum(object);
      Object object1 = AddOp.$Mn.apply2(paramRealNum1, MultiplyOp.$St.apply2(realNum, paramRealNum2));
      try {
        paramRealNum2 = LangObjType.coerceRealNum(object1);
        return misc.values(new Object[] { realNum, paramRealNum2 });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "r", -2, object1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "q", -2, object);
    } 
  }
  
  public static Object divAndMod(RealNum paramRealNum1, RealNum paramRealNum2) {
    Object object = DivideOp.div.apply2(paramRealNum1, paramRealNum2);
    try {
      RealNum realNum = LangObjType.coerceRealNum(object);
      Object object1 = AddOp.$Mn.apply2(paramRealNum1, MultiplyOp.$St.apply2(realNum, paramRealNum2));
      try {
        paramRealNum2 = LangObjType.coerceRealNum(object1);
        return misc.values(new Object[] { realNum, paramRealNum2 });
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "r", -2, object1);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "q", -2, object);
    } 
  }
  
  public static Duration duration(Object paramObject) {
    if (paramObject == null) {
      paramObject = null;
      return Duration.parseDuration((String)paramObject);
    } 
    paramObject = paramObject.toString();
    return Duration.parseDuration((String)paramObject);
  }
  
  public static Number exact(Number paramNumber) {
    return Arithmetic.toExact(paramNumber);
  }
  
  public static Number exact$To$Inexact(Number paramNumber) {
    return Arithmetic.toInexact(paramNumber);
  }
  
  public static Complex exp(Complex paramComplex) {
    return paramComplex.exp();
  }
  
  public static RealNum floor(RealNum paramRealNum) {
    return paramRealNum.toInt(Numeric.FLOOR);
  }
  
  public static IntNum gcd(IntNum... paramVarArgs) {
    int j = paramVarArgs.length;
    if (j == 0)
      return Lit0; 
    IntNum intNum = paramVarArgs[0];
    int i = 1;
    while (true) {
      IntNum intNum1 = intNum;
      if (i < j) {
        intNum = IntNum.gcd(intNum, paramVarArgs[i]);
        i++;
        continue;
      } 
      return intNum1;
    } 
  }
  
  public static RealNum imagPart(Complex paramComplex) {
    return paramComplex.im();
  }
  
  public static Number inexact(Number paramNumber) {
    return Arithmetic.toInexact(paramNumber);
  }
  
  public static Number inexact$To$Exact(Number paramNumber) {
    return Arithmetic.toExact(paramNumber);
  }
  
  public static boolean isBitwiseBitSet(IntNum paramIntNum, int paramInt) {
    return BitOps.bitValue(paramIntNum, paramInt);
  }
  
  public static boolean isComplex(Object paramObject) {
    return paramObject instanceof Complex;
  }
  
  public static boolean isExact(Object paramObject) {
    boolean bool2 = paramObject instanceof Number;
    boolean bool1 = bool2;
    if (bool2)
      bool1 = Arithmetic.isExact((Number)paramObject); 
    return bool1;
  }
  
  public static boolean isInexact(Object paramObject) {
    throw new RuntimeException("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:659)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:629)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s2stmt(TypeTransformer.java:820)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:843)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\n");
  }
  
  public static boolean isInteger(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: instanceof gnu/math/IntNum
    //   4: istore_1
    //   5: iload_1
    //   6: ifeq -> 11
    //   9: iload_1
    //   10: ireturn
    //   11: aload_0
    //   12: instanceof gnu/math/DFloNum
    //   15: istore_1
    //   16: iload_1
    //   17: istore_2
    //   18: iload_1
    //   19: ifeq -> 44
    //   22: aload_0
    //   23: checkcast gnu/math/DFloNum
    //   26: astore_3
    //   27: aload_3
    //   28: invokevirtual doubleValue : ()D
    //   31: dconst_1
    //   32: invokestatic IEEEremainder : (DD)D
    //   35: dconst_0
    //   36: dcmpg
    //   37: ifne -> 99
    //   40: iconst_1
    //   41: istore_1
    //   42: iload_1
    //   43: istore_2
    //   44: iload_2
    //   45: istore_1
    //   46: iload_2
    //   47: ifne -> 9
    //   50: aload_0
    //   51: instanceof java/lang/Number
    //   54: istore_2
    //   55: iload_2
    //   56: istore_1
    //   57: iload_2
    //   58: ifeq -> 9
    //   61: aload_0
    //   62: instanceof java/lang/Long
    //   65: istore_2
    //   66: iload_2
    //   67: istore_1
    //   68: iload_2
    //   69: ifne -> 9
    //   72: aload_0
    //   73: instanceof java/lang/Integer
    //   76: istore_2
    //   77: iload_2
    //   78: istore_1
    //   79: iload_2
    //   80: ifne -> 9
    //   83: aload_0
    //   84: instanceof java/lang/Short
    //   87: istore_2
    //   88: iload_2
    //   89: istore_1
    //   90: iload_2
    //   91: ifne -> 9
    //   94: aload_0
    //   95: instanceof java/math/BigInteger
    //   98: ireturn
    //   99: iconst_0
    //   100: istore_1
    //   101: goto -> 42
    //   104: astore_3
    //   105: new gnu/mapping/WrongType
    //   108: dup
    //   109: aload_3
    //   110: ldc_w 'gnu.math.DFloNum.doubleValue()'
    //   113: iconst_1
    //   114: aload_0
    //   115: invokespecial <init> : (Ljava/lang/ClassCastException;Ljava/lang/String;ILjava/lang/Object;)V
    //   118: athrow
    // Exception table:
    //   from	to	target	type
    //   22	27	104	java/lang/ClassCastException
  }
  
  public static boolean isNegative(RealNum paramRealNum) {
    return paramRealNum.isNegative();
  }
  
  public static boolean isNumber(Object paramObject) {
    return paramObject instanceof Number;
  }
  
  public static boolean isPositive(RealNum paramRealNum) {
    return (paramRealNum.sign() > 0);
  }
  
  public static boolean isQuantity(Object paramObject) {
    return paramObject instanceof Quantity;
  }
  
  public static boolean isRational(Object paramObject) {
    return (RatNum.asRatNumOrNull(paramObject) != null);
  }
  
  public static boolean isReal(Object paramObject) {
    return (RealNum.asRealNumOrNull(paramObject) != null);
  }
  
  public static boolean isZero(Number paramNumber) {
    boolean bool = true;
    return (paramNumber instanceof Numeric) ? ((Numeric)paramNumber).isZero() : ((paramNumber instanceof java.math.BigInteger) ? ((Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2(paramNumber, Lit1)) == Boolean.FALSE) ? false : bool) : ((paramNumber instanceof java.math.BigDecimal) ? ((Scheme.numEqu.apply2(Lit0, GetNamedPart.getNamedPart.apply2(paramNumber, Lit1)) == Boolean.FALSE) ? false : bool) : ((paramNumber.doubleValue() != 0.0D) ? false : bool)));
  }
  
  static double lambda1(double paramDouble1, double paramDouble2) {
    return Math.atan2(paramDouble1, paramDouble2);
  }
  
  static double lambda2(double paramDouble) {
    return Math.atan(paramDouble);
  }
  
  static Quantity lambda3(Quantity paramQuantity) {
    return Quantity.make(paramQuantity.number().sqrt(), paramQuantity.unit().sqrt());
  }
  
  static double lambda4(double paramDouble) {
    return Math.sqrt(paramDouble);
  }
  
  public static IntNum lcm(IntNum... paramVarArgs) {
    int j = paramVarArgs.length;
    if (j == 0)
      return Lit2; 
    IntNum intNum = IntNum.abs(paramVarArgs[0]);
    int i = 1;
    while (true) {
      IntNum intNum1 = intNum;
      if (i < j) {
        intNum = IntNum.lcm(intNum, paramVarArgs[i]);
        i++;
        continue;
      } 
      return intNum1;
    } 
  }
  
  public static Complex log(Complex paramComplex) {
    return paramComplex.log();
  }
  
  public static int logcount(IntNum paramIntNum) {
    if (IntNum.compare(paramIntNum, 0L) < 0)
      paramIntNum = BitOps.not(paramIntNum); 
    return BitOps.bitCount(paramIntNum);
  }
  
  public static IntNum logop(int paramInt, IntNum paramIntNum1, IntNum paramIntNum2) {
    return BitOps.bitOp(paramInt, paramIntNum1, paramIntNum2);
  }
  
  public static boolean logtest(IntNum paramIntNum1, IntNum paramIntNum2) {
    return BitOps.test(paramIntNum1, paramIntNum2);
  }
  
  public static Number magnitude(Number paramNumber) {
    return abs(paramNumber);
  }
  
  public static DComplex makePolar(double paramDouble1, double paramDouble2) {
    return Complex.polar(paramDouble1, paramDouble2);
  }
  
  public static Quantity makeQuantity(Object paramObject1, Object paramObject2) {
    String str;
    if (paramObject2 instanceof Unit)
      try {
        Unit unit = (Unit)paramObject2;
        if (unit == null)
          throw (Throwable)new IllegalArgumentException(Format.formatToString(0, new Object[] { "unknown unit: ~s", paramObject2 }).toString()); 
        try {
          paramObject2 = paramObject1;
          return Quantity.make((Complex)paramObject2, unit);
        } catch (ClassCastException classCastException2) {
          throw new WrongType(classCastException2, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, paramObject1);
        } 
      } catch (ClassCastException classCastException1) {
        throw new WrongType(classCastException1, "u", -2, classCastException2);
      }  
    if (classCastException2 == null) {
      str = null;
    } else {
      str = classCastException2.toString();
    } 
    NamedUnit namedUnit = Unit.lookup(str);
    if (namedUnit == null)
      throw (Throwable)new IllegalArgumentException(Format.formatToString(0, new Object[] { "unknown unit: ~s", classCastException2 }).toString()); 
    try {
      Complex complex = (Complex)classCastException1;
      return Quantity.make(complex, (Unit)namedUnit);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "gnu.math.Quantity.make(gnu.math.Complex,gnu.math.Unit)", 1, classCastException1);
    } 
  }
  
  public static Complex makeRectangular(RealNum paramRealNum1, RealNum paramRealNum2) {
    return Complex.make(paramRealNum1, paramRealNum2);
  }
  
  public static Object max(Object... paramVarArgs) {
    RealNum realNum;
    int i = paramVarArgs.length;
    Object object = paramVarArgs[0];
    try {
      realNum = LangObjType.coerceRealNum(object);
      int j = 1;
      while (j < i) {
        object = paramVarArgs[j];
        try {
          RealNum realNum1 = LangObjType.coerceRealNum(object);
          realNum = realNum.max(realNum1);
          j++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.math.RealNum.max(real)", 2, object);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "result", -2, object);
    } 
    return realNum;
  }
  
  public static Object min(Object... paramVarArgs) {
    RealNum realNum;
    int i = paramVarArgs.length;
    Object object = paramVarArgs[0];
    try {
      realNum = LangObjType.coerceRealNum(object);
      int j = 0;
      while (j < i) {
        object = paramVarArgs[j];
        try {
          RealNum realNum1 = LangObjType.coerceRealNum(object);
          realNum = realNum.min(realNum1);
          j++;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "gnu.math.RealNum.min(real)", 2, object);
        } 
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "result", -2, object);
    } 
    return realNum;
  }
  
  public static CharSequence number$To$String(Number paramNumber) {
    return number$To$String(paramNumber, 10);
  }
  
  public static CharSequence number$To$String(Number paramNumber, int paramInt) {
    return (CharSequence)new FString(Arithmetic.toString(paramNumber, paramInt));
  }
  
  public static IntNum numerator(RatNum paramRatNum) {
    return paramRatNum.numerator();
  }
  
  public static Complex quantity$To$Number(Quantity paramQuantity) {
    paramQuantity.unit();
    return (paramQuantity.doubleValue() == 1.0D) ? paramQuantity.number() : Complex.make(paramQuantity.reValue(), paramQuantity.imValue());
  }
  
  public static Unit quantity$To$Unit(Quantity paramQuantity) {
    return paramQuantity.unit();
  }
  
  public static RealNum rationalize(RealNum paramRealNum1, RealNum paramRealNum2) {
    return RatNum.rationalize(LangObjType.coerceRealNum(paramRealNum1.sub(paramRealNum2)), LangObjType.coerceRealNum(paramRealNum1.add(paramRealNum2)));
  }
  
  public static RealNum realPart(Complex paramComplex) {
    return paramComplex.re();
  }
  
  public static RealNum round(RealNum paramRealNum) {
    return paramRealNum.toInt(Numeric.ROUND);
  }
  
  public static double sin(double paramDouble) {
    return Math.sin(paramDouble);
  }
  
  public static Object string$To$Number(CharSequence paramCharSequence) {
    return string$To$Number(paramCharSequence, 10);
  }
  
  public static Object string$To$Number(CharSequence paramCharSequence, int paramInt) {
    Object object = LispReader.parseNumber(paramCharSequence, paramInt);
    return (object instanceof Numeric) ? object : Boolean.FALSE;
  }
  
  public static double tan(double paramDouble) {
    return Math.tan(paramDouble);
  }
  
  public static RealNum truncate(RealNum paramRealNum) {
    return paramRealNum.toInt(Numeric.TRUNCATE);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isNumber(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        return isQuantity(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 3:
        return isComplex(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 4:
        return isReal(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 5:
        return isRational(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 6:
        return isInteger(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 7:
        return isExact(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 8:
        return isInexact(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 9:
        try {
          Number number = (Number)paramObject;
          return isZero(number) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "zero?", 1, paramObject);
        } 
      case 10:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return isPositive(realNum) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "positive?", 1, paramObject);
        } 
      case 11:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return isNegative(realNum) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "negative?", 1, paramObject);
        } 
      case 14:
        try {
          Number number = (Number)paramObject;
          return abs(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "abs", 1, paramObject);
        } 
      case 19:
        try {
          RatNum ratNum = LangObjType.coerceRatNum(paramObject);
          return numerator(ratNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "numerator", 1, paramObject);
        } 
      case 20:
        try {
          RatNum ratNum = LangObjType.coerceRatNum(paramObject);
          return denominator(ratNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "denominator", 1, paramObject);
        } 
      case 21:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return floor(realNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "floor", 1, paramObject);
        } 
      case 22:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return ceiling(realNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "ceiling", 1, paramObject);
        } 
      case 23:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return truncate(realNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "truncate", 1, paramObject);
        } 
      case 24:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject);
          return round(realNum);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "round", 1, paramObject);
        } 
      case 26:
        try {
          Complex complex = (Complex)paramObject;
          return exp(complex);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "exp", 1, paramObject);
        } 
      case 27:
        try {
          Complex complex = (Complex)paramObject;
          return log(complex);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "log", 1, paramObject);
        } 
      case 28:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(sin(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "sin", 1, paramObject);
        } 
      case 29:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(cos(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "cos", 1, paramObject);
        } 
      case 30:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(tan(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "tan", 1, paramObject);
        } 
      case 31:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(asin(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "asin", 1, paramObject);
        } 
      case 32:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(acos(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "acos", 1, paramObject);
        } 
      case 34:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(lambda2(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, paramObject);
        } 
      case 35:
        try {
          Quantity quantity = (Quantity)paramObject;
          return lambda3(quantity);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, paramObject);
        } 
      case 36:
        try {
          double d = ((Number)paramObject).doubleValue();
          return Double.valueOf(lambda4(d));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, paramObject);
        } 
      case 39:
        try {
          Complex complex = (Complex)paramObject;
          return realPart(complex);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "real-part", 1, paramObject);
        } 
      case 40:
        try {
          Complex complex = (Complex)paramObject;
          return imagPart(complex);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "imag-part", 1, paramObject);
        } 
      case 41:
        try {
          Number number = (Number)paramObject;
          return magnitude(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "magnitude", 1, paramObject);
        } 
      case 42:
        try {
          Complex complex = (Complex)paramObject;
          return angle(complex);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "angle", 1, paramObject);
        } 
      case 43:
        try {
          Number number = (Number)paramObject;
          return inexact(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "inexact", 1, paramObject);
        } 
      case 44:
        try {
          Number number = (Number)paramObject;
          return exact(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "exact", 1, paramObject);
        } 
      case 45:
        try {
          Number number = (Number)paramObject;
          return exact$To$Inexact(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "exact->inexact", 1, paramObject);
        } 
      case 46:
        try {
          Number number = (Number)paramObject;
          return inexact$To$Exact(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "inexact->exact", 1, paramObject);
        } 
      case 54:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject);
          return Integer.valueOf(logcount(intNum));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "logcount", 1, paramObject);
        } 
      case 55:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject);
          return Integer.valueOf(bitwiseBitCount(intNum));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-bit-count", 1, paramObject);
        } 
      case 56:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject);
          return Integer.valueOf(bitwiseLength(intNum));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-length", 1, paramObject);
        } 
      case 57:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject);
          return Integer.valueOf(bitwiseFirstBitSet(intNum));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-first-bit-set", 1, paramObject);
        } 
      case 60:
        try {
          Number number = (Number)paramObject;
          return number$To$String(number);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "number->string", 1, paramObject);
        } 
      case 62:
        try {
          CharSequence charSequence = (CharSequence)paramObject;
          return string$To$Number(charSequence);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string->number", 1, paramObject);
        } 
      case 64:
        try {
          Quantity quantity = (Quantity)paramObject;
          return quantity$To$Number(quantity);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "quantity->number", 1, paramObject);
        } 
      case 65:
        try {
          Quantity quantity = (Quantity)paramObject;
          return quantity$To$Unit(quantity);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "quantity->unit", 1, paramObject);
        } 
      case 67:
        break;
    } 
    return duration(paramObject);
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 15:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceRealNum(paramObject2);
            return divAndMod(realNum, (RealNum)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "div-and-mod", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "div-and-mod", 1, paramObject1);
        } 
      case 16:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceRealNum(paramObject2);
            return div0AndMod0(realNum, (RealNum)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "div0-and-mod0", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "div0-and-mod0", 1, paramObject1);
        } 
      case 25:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceRealNum(paramObject2);
            return rationalize(realNum, (RealNum)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "rationalize", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "rationalize", 1, paramObject1);
        } 
      case 33:
        try {
          double d = ((Number)paramObject1).doubleValue();
          try {
            double d1 = ((Number)paramObject2).doubleValue();
            return Double.valueOf(lambda1(d, d1));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "lambda", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lambda", 1, paramObject1);
        } 
      case 37:
        try {
          RealNum realNum = LangObjType.coerceRealNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceRealNum(paramObject2);
            return makeRectangular(realNum, (RealNum)paramObject1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-rectangular", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-rectangular", 1, paramObject1);
        } 
      case 38:
        try {
          double d = ((Number)paramObject1).doubleValue();
          try {
            double d1 = ((Number)paramObject2).doubleValue();
            return makePolar(d, d1);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "make-polar", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-polar", 1, paramObject1);
        } 
      case 48:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            int i = ((Number)paramObject2).intValue();
            return isBitwiseBitSet(intNum, i) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-bit-set?", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-bit-set?", 1, paramObject1);
        } 
      case 53:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceIntNum(paramObject2);
            return logtest(intNum, (IntNum)paramObject1) ? Boolean.TRUE : Boolean.FALSE;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "logtest", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "logtest", 1, paramObject1);
        } 
      case 60:
        try {
          Number number = (Number)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return number$To$String(number, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "number->string", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "number->string", 1, paramObject1);
        } 
      case 62:
        try {
          CharSequence charSequence = (CharSequence)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return string$To$Number(charSequence, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "string->number", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "string->number", 1, paramObject1);
        } 
      case 66:
        break;
    } 
    return makeQuantity(paramObject1, paramObject2);
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply3(paramModuleMethod, paramObject1, paramObject2, paramObject3);
      case 47:
        try {
          int i = ((Number)paramObject1).intValue();
          try {
            IntNum intNum = LangObjType.coerceIntNum(paramObject2);
            try {
              paramObject1 = LangObjType.coerceIntNum(paramObject3);
              return logop(i, intNum, (IntNum)paramObject1);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "logop", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "logop", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "logop", 1, paramObject1);
        } 
      case 49:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              return bitwiseCopyBit(intNum, i, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "bitwise-copy-bit", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-copy-bit", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-copy-bit", 1, paramObject1);
        } 
      case 51:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              return bitwiseBitField(intNum, i, j);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "bitwise-bit-field", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-bit-field", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-bit-field", 1, paramObject1);
        } 
      case 52:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            paramObject1 = LangObjType.coerceIntNum(paramObject2);
            try {
              paramObject2 = LangObjType.coerceIntNum(paramObject3);
              return bitwiseIf(intNum, (IntNum)paramObject1, (IntNum)paramObject2);
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "bitwise-if", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-if", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-if", 1, paramObject1);
        } 
      case 59:
        break;
    } 
    try {
      IntNum intNum = LangObjType.coerceIntNum(paramObject1);
      try {
        int i = ((Number)paramObject2).intValue();
        try {
          int j = ((Number)paramObject3).intValue();
          return bitwiseReverseBitField(intNum, i, j);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-reverse-bit-field", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "bitwise-reverse-bit-field", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "bitwise-reverse-bit-field", 1, paramObject1);
    } 
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 50:
        try {
          IntNum intNum = LangObjType.coerceIntNum(paramObject1);
          try {
            int i = ((Number)paramObject2).intValue();
            try {
              int j = ((Number)paramObject3).intValue();
              try {
                paramObject1 = LangObjType.coerceIntNum(paramObject4);
                return bitwiseCopyBitField(intNum, i, j, (IntNum)paramObject1);
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "bitwise-copy-bit-field", 4, paramObject4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "bitwise-copy-bit-field", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-copy-bit-field", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-copy-bit-field", 1, paramObject1);
        } 
      case 58:
        break;
    } 
    try {
      IntNum intNum = LangObjType.coerceIntNum(paramObject1);
      try {
        int i = ((Number)paramObject2).intValue();
        try {
          int j = ((Number)paramObject3).intValue();
          try {
            int k = ((Number)paramObject4).intValue();
            return bitwiseRotateBitField(intNum, i, j, k);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "bitwise-rotate-bit-field", 4, paramObject4);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "bitwise-rotate-bit-field", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "bitwise-rotate-bit-field", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "bitwise-rotate-bit-field", 1, paramObject1);
    } 
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 12:
        return max(paramArrayOfObject);
      case 13:
        return min(paramArrayOfObject);
      case 17:
        i = paramArrayOfObject.length;
        arrayOfIntNum = new IntNum[i];
        while (true) {
          if (--i < 0)
            return gcd(arrayOfIntNum); 
          Object object = paramArrayOfObject[i];
          try {
            IntNum intNum = LangObjType.coerceIntNum(object);
            arrayOfIntNum[i] = intNum;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "gcd", 0, object);
          } 
        } 
      case 18:
        break;
    } 
    int i = classCastException.length;
    IntNum[] arrayOfIntNum = new IntNum[i];
    while (true) {
      if (--i < 0)
        return lcm(arrayOfIntNum); 
      ClassCastException classCastException1 = classCastException[i];
      try {
        IntNum intNum = LangObjType.coerceIntNum(classCastException1);
        arrayOfIntNum[i] = intNum;
      } catch (ClassCastException classCastException2) {
        throw new WrongType(classCastException2, "lcm", 0, classCastException1);
      } 
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 67:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 65:
        if (!(paramObject instanceof Quantity))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 64:
        if (!(paramObject instanceof Quantity))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 62:
        if (paramObject instanceof CharSequence) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 60:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 57:
        if (IntNum.asIntNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 56:
        if (IntNum.asIntNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 55:
        if (IntNum.asIntNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 54:
        if (IntNum.asIntNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 46:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 45:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 44:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 43:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 42:
        if (!(paramObject instanceof Complex))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 41:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 40:
        if (!(paramObject instanceof Complex))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 39:
        if (!(paramObject instanceof Complex))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 36:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 35:
        if (!(paramObject instanceof Quantity))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 34:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 32:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 31:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 30:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 29:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 28:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 27:
        if (!(paramObject instanceof Complex))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 26:
        if (!(paramObject instanceof Complex))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 24:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 23:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 22:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 21:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 20:
        if (RatNum.asRatNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 19:
        if (RatNum.asRatNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 14:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 11:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 10:
        if (RealNum.asRealNumOrNull(paramObject) != null) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 9:
        if (!(paramObject instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 8:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 7:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 6:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 5:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 4:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 66:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 62:
        if (paramObject1 instanceof CharSequence) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 60:
        if (!(paramObject1 instanceof Number))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 53:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (IntNum.asIntNumOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 48:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 38:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 37:
        if (RealNum.asRealNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (RealNum.asRealNumOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 33:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 25:
        if (RealNum.asRealNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (RealNum.asRealNumOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 16:
        if (RealNum.asRealNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (RealNum.asRealNumOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            paramCallContext.proc = (Procedure)paramModuleMethod;
            paramCallContext.pc = 2;
            return 0;
          } 
        } else {
          return -786431;
        } 
        return -786430;
      case 15:
        break;
    } 
    if (RealNum.asRealNumOrNull(paramObject1) != null) {
      paramCallContext.value1 = paramObject1;
      if (RealNum.asRealNumOrNull(paramObject2) != null) {
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      } 
    } else {
      return -786431;
    } 
    return -786430;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
      case 59:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786431;
      case 52:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          if (IntNum.asIntNumOrNull(paramObject2) != null) {
            paramCallContext.value2 = paramObject2;
            if (IntNum.asIntNumOrNull(paramObject3) != null) {
              paramCallContext.value3 = paramObject3;
              paramCallContext.proc = (Procedure)paramModuleMethod;
              paramCallContext.pc = 3;
              return 0;
            } 
          } else {
            return -786430;
          } 
        } else {
          return -786431;
        } 
        return -786429;
      case 51:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786431;
      case 49:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 3;
          return 0;
        } 
        return -786431;
      case 47:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    if (IntNum.asIntNumOrNull(paramObject2) != null) {
      paramCallContext.value2 = paramObject2;
      if (IntNum.asIntNumOrNull(paramObject3) != null) {
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } 
    } else {
      return -786430;
    } 
    return -786429;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 58:
        if (IntNum.asIntNumOrNull(paramObject1) != null) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.value3 = paramObject3;
          paramCallContext.value4 = paramObject4;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 4;
          return 0;
        } 
        return -786431;
      case 50:
        break;
    } 
    if (IntNum.asIntNumOrNull(paramObject1) != null) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      if (IntNum.asIntNumOrNull(paramObject4) != null) {
        paramCallContext.value4 = paramObject4;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 4;
        return 0;
      } 
    } else {
      return -786431;
    } 
    return -786428;
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 18:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 17:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 13:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 12:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    atan = new GenericProc("atan");
    atan.setProperties(new Object[] { lambda$Fn1, lambda$Fn2 });
    sqrt = new GenericProc("sqrt");
    sqrt.setProperties(new Object[] { lambda$Fn3, lambda$Fn4 });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/numbers.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */