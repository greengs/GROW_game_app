package gnu.math;

public abstract class Numeric extends Number {
  public static final int CEILING = 2;
  
  public static final int FLOOR = 1;
  
  public static final int NONNEG_MOD = 5;
  
  public static final int ROUND = 4;
  
  public static final int TRUNCATE = 3;
  
  public static Numeric asNumericOrNull(Object paramObject) {
    return (Numeric)((paramObject instanceof Numeric) ? paramObject : ((paramObject instanceof java.math.BigInteger || paramObject instanceof Long || paramObject instanceof Short || paramObject instanceof Byte || paramObject instanceof Integer) ? IntNum.asIntNumOrNull(paramObject) : ((paramObject instanceof java.math.BigDecimal) ? RatNum.asRatNumOrNull(paramObject) : ((paramObject instanceof Float || paramObject instanceof Double) ? new DFloNum(((Number)paramObject).doubleValue()) : null))));
  }
  
  public abstract Numeric abs();
  
  public final Numeric add(Object paramObject) {
    return add(paramObject, 1);
  }
  
  public abstract Numeric add(Object paramObject, int paramInt);
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    throw new IllegalArgumentException();
  }
  
  public int compare(Object paramObject) {
    return -3;
  }
  
  public int compareReversed(Numeric paramNumeric) {
    throw new IllegalArgumentException();
  }
  
  public abstract Numeric div(Object paramObject);
  
  public Numeric divReversed(Numeric paramNumeric) {
    throw new IllegalArgumentException();
  }
  
  public Numeric div_inv() {
    return IntNum.one().div(this);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject != null && paramObject instanceof Numeric && compare(paramObject) == 0);
  }
  
  public float floatValue() {
    return (float)doubleValue();
  }
  
  public boolean geq(Object paramObject) {
    return (compare(paramObject) >= 0);
  }
  
  public boolean grt(Object paramObject) {
    return (compare(paramObject) > 0);
  }
  
  public int intValue() {
    return (int)longValue();
  }
  
  public abstract boolean isExact();
  
  public abstract boolean isZero();
  
  public long longValue() {
    return (long)doubleValue();
  }
  
  public abstract Numeric mul(Object paramObject);
  
  public Numeric mulReversed(Numeric paramNumeric) {
    throw new IllegalArgumentException();
  }
  
  public Numeric mul_ident() {
    return IntNum.one();
  }
  
  public abstract Numeric neg();
  
  public Numeric power(IntNum paramIntNum) {
    if (paramIntNum.isNegative())
      return power(IntNum.neg(paramIntNum)).div_inv(); 
    Numeric numeric = this;
    IntNum intNum2 = null;
    IntNum intNum1 = paramIntNum;
    while (true) {
      Numeric numeric1;
      paramIntNum = intNum2;
      if (intNum1.isOdd())
        if (intNum2 == null) {
          numeric1 = numeric;
        } else {
          numeric1 = intNum2.mul(numeric);
        }  
      intNum1 = IntNum.shift(intNum1, -1);
      if (intNum1.isZero()) {
        numeric = numeric1;
        return (numeric1 == null) ? mul_ident() : numeric;
      } 
      numeric = numeric.mul(numeric);
      Numeric numeric2 = numeric1;
    } 
  }
  
  public final Numeric sub(Object paramObject) {
    return add(paramObject, -1);
  }
  
  public Numeric toExact() {
    return this;
  }
  
  public Numeric toInexact() {
    return this;
  }
  
  public String toString() {
    return toString(10);
  }
  
  public abstract String toString(int paramInt);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/Numeric.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */