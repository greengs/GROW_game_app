package gnu.math;

import java.math.BigDecimal;

public abstract class RatNum extends RealNum {
  public static final IntNum ten_exp_9 = IntNum.make(1000000000);
  
  public static RatNum add(RatNum paramRatNum1, RatNum paramRatNum2, int paramInt) {
    IntNum intNum1 = paramRatNum1.numerator();
    paramRatNum1 = paramRatNum1.denominator();
    IntNum intNum2 = paramRatNum2.numerator();
    paramRatNum2 = paramRatNum2.denominator();
    return IntNum.equals((IntNum)paramRatNum1, (IntNum)paramRatNum2) ? make(IntNum.add(intNum1, intNum2, paramInt), (IntNum)paramRatNum1) : make(IntNum.add(IntNum.times((IntNum)paramRatNum2, intNum1), IntNum.times(intNum2, (IntNum)paramRatNum1), paramInt), IntNum.times((IntNum)paramRatNum1, (IntNum)paramRatNum2));
  }
  
  public static RatNum asRatNumOrNull(Object paramObject) {
    return (paramObject instanceof RatNum) ? (RatNum)paramObject : ((paramObject instanceof BigDecimal) ? valueOf((BigDecimal)paramObject) : IntNum.asIntNumOrNull(paramObject));
  }
  
  public static int compare(RatNum paramRatNum1, RatNum paramRatNum2) {
    return IntNum.compare(IntNum.times(paramRatNum1.numerator(), paramRatNum2.denominator()), IntNum.times(paramRatNum2.numerator(), paramRatNum1.denominator()));
  }
  
  public static RatNum divide(RatNum paramRatNum1, RatNum paramRatNum2) {
    return make(IntNum.times(paramRatNum1.numerator(), paramRatNum2.denominator()), IntNum.times(paramRatNum1.denominator(), paramRatNum2.numerator()));
  }
  
  public static boolean equals(RatNum paramRatNum1, RatNum paramRatNum2) {
    return (IntNum.equals(paramRatNum1.numerator(), paramRatNum2.numerator()) && IntNum.equals(paramRatNum1.denominator(), paramRatNum2.denominator()));
  }
  
  public static RatNum infinity(int paramInt) {
    return new IntFraction(IntNum.make(paramInt), IntNum.zero());
  }
  
  public static RatNum make(IntNum paramIntNum1, IntNum paramIntNum2) {
    IntNum intNum2 = IntNum.gcd(paramIntNum1, paramIntNum2);
    IntNum intNum1 = intNum2;
    if (paramIntNum2.isNegative())
      intNum1 = IntNum.neg(intNum2); 
    IntNum intNum3 = paramIntNum1;
    intNum2 = paramIntNum2;
    if (!intNum1.isOne()) {
      intNum3 = IntNum.quotient(paramIntNum1, intNum1);
      intNum2 = IntNum.quotient(paramIntNum2, intNum1);
    } 
    return (RatNum)(intNum2.isOne() ? intNum3 : new IntFraction(intNum3, intNum2));
  }
  
  public static RatNum neg(RatNum paramRatNum) {
    IntNum intNum = paramRatNum.numerator();
    paramRatNum = paramRatNum.denominator();
    return make(IntNum.neg(intNum), (IntNum)paramRatNum);
  }
  
  public static RealNum rationalize(RealNum paramRealNum1, RealNum paramRealNum2) {
    if (paramRealNum1.grt(paramRealNum2))
      return simplest_rational2(paramRealNum2, paramRealNum1); 
    RealNum realNum = paramRealNum1;
    return paramRealNum2.grt(paramRealNum1) ? ((paramRealNum1.sign() > 0) ? simplest_rational2(paramRealNum1, paramRealNum2) : (paramRealNum2.isNegative() ? (RealNum)simplest_rational2((RealNum)paramRealNum2.neg(), (RealNum)paramRealNum1.neg()).neg() : IntNum.zero())) : realNum;
  }
  
  private static RealNum simplest_rational2(RealNum paramRealNum1, RealNum paramRealNum2) {
    RealNum realNum1 = paramRealNum1.toInt(1);
    RealNum realNum2 = paramRealNum2.toInt(1);
    if (!paramRealNum1.grt(realNum1))
      return realNum1; 
    if (realNum1.equals(realNum2)) {
      paramRealNum2 = (RealNum)IntNum.one().div(paramRealNum2.sub(realNum2));
      paramRealNum1 = (RealNum)IntNum.one().div(paramRealNum1.sub(realNum1));
      return (RealNum)realNum1.add(IntNum.one().div(simplest_rational2(paramRealNum2, paramRealNum1)), 1);
    } 
    return (RealNum)realNum1.add(IntNum.one(), 1);
  }
  
  public static RatNum times(RatNum paramRatNum1, RatNum paramRatNum2) {
    return make(IntNum.times(paramRatNum1.numerator(), paramRatNum2.numerator()), IntNum.times(paramRatNum1.denominator(), paramRatNum2.denominator()));
  }
  
  public static RatNum valueOf(BigDecimal paramBigDecimal) {
    int i;
    RatNum ratNum = IntNum.valueOf(paramBigDecimal.unscaledValue().toString(), 10);
    int j = paramBigDecimal.scale();
    IntNum intNum = (IntNum)ratNum;
    while (true) {
      i = j;
      ratNum = intNum;
      if (j >= 9) {
        RatNum ratNum1 = divide(intNum, ten_exp_9);
        j -= 9;
        continue;
      } 
      break;
    } 
    while (i <= -9) {
      ratNum = times(ratNum, ten_exp_9);
      i += 9;
    } 
    if (i > 0) {
      j = i;
    } else {
      j = -i;
    } 
    switch (j) {
      default:
        return ratNum;
      case 1:
        intNum = IntNum.make(10);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 2:
        intNum = IntNum.make(100);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 3:
        intNum = IntNum.make(1000);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 4:
        intNum = IntNum.make(10000);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 5:
        intNum = IntNum.make(100000);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 6:
        intNum = IntNum.make(1000000);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 7:
        intNum = IntNum.make(10000000);
        return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
      case 8:
        break;
    } 
    intNum = IntNum.make(100000000);
    return (i > 0) ? divide(ratNum, intNum) : times(ratNum, intNum);
  }
  
  public abstract IntNum denominator();
  
  public boolean equals(Object paramObject) {
    return (paramObject == null || !(paramObject instanceof RatNum)) ? false : equals(this, (RatNum)paramObject);
  }
  
  public boolean isExact() {
    return true;
  }
  
  public boolean isZero() {
    return numerator().isZero();
  }
  
  public abstract IntNum numerator();
  
  public Numeric power(IntNum paramIntNum) {
    boolean bool1;
    boolean bool2;
    if (paramIntNum.isNegative()) {
      bool1 = true;
      paramIntNum = IntNum.neg(paramIntNum);
    } else {
      bool1 = false;
    } 
    if (paramIntNum.words == null) {
      IntNum intNum = IntNum.power(numerator(), paramIntNum.ival);
      paramIntNum = IntNum.power(denominator(), paramIntNum.ival);
      return bool1 ? make(paramIntNum, intNum) : make(intNum, paramIntNum);
    } 
    double d1 = doubleValue();
    if (d1 < 0.0D && paramIntNum.isOdd()) {
      bool2 = true;
    } else {
      bool2 = false;
    } 
    double d2 = Math.pow(d1, paramIntNum.doubleValue());
    d1 = d2;
    if (bool1)
      d1 = 1.0D / d2; 
    d2 = d1;
    if (bool2)
      d2 = -d1; 
    return new DFloNum(d2);
  }
  
  public final RatNum rneg() {
    return neg(this);
  }
  
  public final RatNum toExact() {
    return this;
  }
  
  public IntNum toExactInt(int paramInt) {
    return IntNum.quotient(numerator(), denominator(), paramInt);
  }
  
  public RealNum toInt(int paramInt) {
    return IntNum.quotient(numerator(), denominator(), paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/RatNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */