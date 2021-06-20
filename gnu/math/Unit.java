package gnu.math;

public abstract class Unit extends Quantity {
  public static BaseUnit Empty;
  
  public static double NON_COMBINABLE;
  
  public static final Unit cm;
  
  public static final NamedUnit date;
  
  public static final BaseUnit duration;
  
  public static final BaseUnit gram;
  
  public static final Unit hour;
  
  public static final Unit in;
  
  public static final BaseUnit meter;
  
  public static final Unit minute;
  
  public static final Unit mm;
  
  public static final NamedUnit month;
  
  public static final Unit pica;
  
  public static final Unit pt;
  
  public static final Unit radian;
  
  public static final NamedUnit second;
  
  static NamedUnit[] table = new NamedUnit[100];
  
  Unit base;
  
  Dimensions dims;
  
  double factor = 1.0D;
  
  MulUnit products;
  
  static {
    Empty = new BaseUnit();
    Dimensions.Empty.bases[0] = Empty;
    NON_COMBINABLE = 0.0D;
    meter = new BaseUnit("m", "Length");
    duration = new BaseUnit("duration", "Time");
    gram = new BaseUnit("g", "Mass");
    cm = define("cm", 0.01D, meter);
    mm = define("mm", 0.1D, cm);
    in = define("in", 0.0254D, meter);
    pt = define("pt", 3.527778E-4D, meter);
    pica = define("pica", 0.004233333D, meter);
    radian = define("rad", 1.0D, Empty);
    date = new NamedUnit("date", NON_COMBINABLE, duration);
    second = new NamedUnit("s", NON_COMBINABLE, duration);
    month = new NamedUnit("month", NON_COMBINABLE, duration);
    minute = define("min", 60.0D, second);
    hour = define("hour", 60.0D, minute);
  }
  
  public static Unit define(String paramString, double paramDouble, Unit paramUnit) {
    return new NamedUnit(paramString, paramDouble, paramUnit);
  }
  
  public static Unit define(String paramString, DQuantity paramDQuantity) {
    return new NamedUnit(paramString, paramDQuantity);
  }
  
  public static Unit divide(Unit paramUnit1, Unit paramUnit2) {
    return times(paramUnit1, 1, paramUnit2, -1);
  }
  
  public static NamedUnit lookup(String paramString) {
    return NamedUnit.lookup(paramString);
  }
  
  public static NamedUnit make(String paramString, Quantity paramQuantity) {
    return NamedUnit.make(paramString, paramQuantity);
  }
  
  public static Unit pow(Unit paramUnit, int paramInt) {
    return times(paramUnit, paramInt, Empty, 0);
  }
  
  static Unit times(Unit paramUnit1, int paramInt1, Unit paramUnit2, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: istore #5
    //   3: aload_2
    //   4: astore #6
    //   6: iload_3
    //   7: istore #4
    //   9: aload_0
    //   10: aload_2
    //   11: if_acmpne -> 27
    //   14: iload_1
    //   15: iload_3
    //   16: iadd
    //   17: istore #5
    //   19: getstatic gnu/math/Unit.Empty : Lgnu/math/BaseUnit;
    //   22: astore #6
    //   24: iconst_0
    //   25: istore #4
    //   27: iload #5
    //   29: ifeq -> 48
    //   32: aload_0
    //   33: astore #7
    //   35: aload #6
    //   37: astore_2
    //   38: iload #4
    //   40: istore_1
    //   41: aload_0
    //   42: getstatic gnu/math/Unit.Empty : Lgnu/math/BaseUnit;
    //   45: if_acmpne -> 62
    //   48: getstatic gnu/math/Unit.Empty : Lgnu/math/BaseUnit;
    //   51: astore_2
    //   52: iconst_0
    //   53: istore_1
    //   54: iload #4
    //   56: istore #5
    //   58: aload #6
    //   60: astore #7
    //   62: iload_1
    //   63: ifeq -> 73
    //   66: aload_2
    //   67: getstatic gnu/math/Unit.Empty : Lgnu/math/BaseUnit;
    //   70: if_acmpne -> 91
    //   73: iload #5
    //   75: iconst_1
    //   76: if_icmpne -> 82
    //   79: aload #7
    //   81: areturn
    //   82: iload #5
    //   84: ifne -> 91
    //   87: getstatic gnu/math/Unit.Empty : Lgnu/math/BaseUnit;
    //   90: areturn
    //   91: aload #7
    //   93: instanceof gnu/math/MulUnit
    //   96: ifeq -> 316
    //   99: aload #7
    //   101: checkcast gnu/math/MulUnit
    //   104: astore_0
    //   105: aload_0
    //   106: getfield unit1 : Lgnu/math/Unit;
    //   109: aload_2
    //   110: if_acmpne -> 138
    //   113: aload_2
    //   114: aload_0
    //   115: getfield power1 : I
    //   118: iload #5
    //   120: imul
    //   121: iload_1
    //   122: iadd
    //   123: aload_0
    //   124: getfield unit2 : Lgnu/math/Unit;
    //   127: aload_0
    //   128: getfield power2 : I
    //   131: iload #5
    //   133: imul
    //   134: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   137: areturn
    //   138: aload_0
    //   139: getfield unit2 : Lgnu/math/Unit;
    //   142: aload_2
    //   143: if_acmpne -> 171
    //   146: aload_0
    //   147: getfield unit1 : Lgnu/math/Unit;
    //   150: aload_0
    //   151: getfield power1 : I
    //   154: iload #5
    //   156: imul
    //   157: aload_2
    //   158: aload_0
    //   159: getfield power2 : I
    //   162: iload #5
    //   164: imul
    //   165: iload_1
    //   166: iadd
    //   167: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   170: areturn
    //   171: aload_2
    //   172: instanceof gnu/math/MulUnit
    //   175: ifeq -> 316
    //   178: aload_2
    //   179: checkcast gnu/math/MulUnit
    //   182: astore #6
    //   184: aload_0
    //   185: getfield unit1 : Lgnu/math/Unit;
    //   188: aload #6
    //   190: getfield unit1 : Lgnu/math/Unit;
    //   193: if_acmpne -> 250
    //   196: aload_0
    //   197: getfield unit2 : Lgnu/math/Unit;
    //   200: aload #6
    //   202: getfield unit2 : Lgnu/math/Unit;
    //   205: if_acmpne -> 250
    //   208: aload_0
    //   209: getfield unit1 : Lgnu/math/Unit;
    //   212: aload_0
    //   213: getfield power1 : I
    //   216: iload #5
    //   218: imul
    //   219: aload #6
    //   221: getfield power1 : I
    //   224: iload_1
    //   225: imul
    //   226: iadd
    //   227: aload_0
    //   228: getfield unit2 : Lgnu/math/Unit;
    //   231: aload_0
    //   232: getfield power2 : I
    //   235: iload #5
    //   237: imul
    //   238: aload #6
    //   240: getfield power2 : I
    //   243: iload_1
    //   244: imul
    //   245: iadd
    //   246: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   249: areturn
    //   250: aload_0
    //   251: getfield unit1 : Lgnu/math/Unit;
    //   254: aload #6
    //   256: getfield unit2 : Lgnu/math/Unit;
    //   259: if_acmpne -> 316
    //   262: aload_0
    //   263: getfield unit2 : Lgnu/math/Unit;
    //   266: aload #6
    //   268: getfield unit1 : Lgnu/math/Unit;
    //   271: if_acmpne -> 316
    //   274: aload_0
    //   275: getfield unit1 : Lgnu/math/Unit;
    //   278: aload_0
    //   279: getfield power1 : I
    //   282: iload #5
    //   284: imul
    //   285: aload #6
    //   287: getfield power2 : I
    //   290: iload_1
    //   291: imul
    //   292: iadd
    //   293: aload_0
    //   294: getfield unit2 : Lgnu/math/Unit;
    //   297: aload_0
    //   298: getfield power2 : I
    //   301: iload #5
    //   303: imul
    //   304: aload #6
    //   306: getfield power1 : I
    //   309: iload_1
    //   310: imul
    //   311: iadd
    //   312: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   315: areturn
    //   316: aload_2
    //   317: instanceof gnu/math/MulUnit
    //   320: ifeq -> 396
    //   323: aload_2
    //   324: checkcast gnu/math/MulUnit
    //   327: astore_0
    //   328: aload_0
    //   329: getfield unit1 : Lgnu/math/Unit;
    //   332: aload #7
    //   334: if_acmpne -> 362
    //   337: aload #7
    //   339: aload_0
    //   340: getfield power1 : I
    //   343: iload_1
    //   344: imul
    //   345: iload #5
    //   347: iadd
    //   348: aload_0
    //   349: getfield unit2 : Lgnu/math/Unit;
    //   352: aload_0
    //   353: getfield power2 : I
    //   356: iload_1
    //   357: imul
    //   358: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   361: areturn
    //   362: aload_0
    //   363: getfield unit2 : Lgnu/math/Unit;
    //   366: aload #7
    //   368: if_acmpne -> 396
    //   371: aload_0
    //   372: getfield unit1 : Lgnu/math/Unit;
    //   375: aload_0
    //   376: getfield power1 : I
    //   379: iload_1
    //   380: imul
    //   381: aload #7
    //   383: aload_0
    //   384: getfield power2 : I
    //   387: iload_1
    //   388: imul
    //   389: iload #5
    //   391: iadd
    //   392: invokestatic times : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/Unit;
    //   395: areturn
    //   396: aload #7
    //   398: iload #5
    //   400: aload_2
    //   401: iload_1
    //   402: invokestatic make : (Lgnu/math/Unit;ILgnu/math/Unit;I)Lgnu/math/MulUnit;
    //   405: areturn
  }
  
  public static Unit times(Unit paramUnit1, Unit paramUnit2) {
    return times(paramUnit1, 1, paramUnit2, 1);
  }
  
  public final Dimensions dimensions() {
    return this.dims;
  }
  
  public final double doubleValue() {
    return this.factor;
  }
  
  public String getName() {
    return null;
  }
  
  public int hashCode() {
    return this.dims.hashCode();
  }
  
  public boolean isExact() {
    return false;
  }
  
  public final boolean isZero() {
    return false;
  }
  
  public Complex number() {
    return DFloNum.one();
  }
  
  public Numeric power(IntNum paramIntNum) {
    if (paramIntNum.words != null)
      throw new ArithmeticException("Unit raised to bignum power"); 
    return pow(this, paramIntNum.ival);
  }
  
  public Unit sqrt() {
    if (this == Empty)
      return this; 
    throw new RuntimeException("unimplemented Unit.sqrt");
  }
  
  public String toString() {
    String str = getName();
    return (str != null) ? str : ((this == Empty) ? "unit" : (Double.toString(this.factor) + "<unnamed unit>"));
  }
  
  public String toString(double paramDouble) {
    String str = Double.toString(paramDouble);
    return (this == Empty) ? str : (str + toString());
  }
  
  public String toString(RealNum paramRealNum) {
    return toString(paramRealNum.doubleValue());
  }
  
  public Unit unit() {
    return this;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/Unit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */