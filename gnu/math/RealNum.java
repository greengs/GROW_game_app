package gnu.math;

import java.math.BigDecimal;

public abstract class RealNum extends Complex implements Comparable {
  public static RealNum add(RealNum paramRealNum1, RealNum paramRealNum2, int paramInt) {
    return (RealNum)paramRealNum1.add(paramRealNum2, paramInt);
  }
  
  public static RealNum asRealNumOrNull(Object paramObject) {
    return (RealNum)((paramObject instanceof RealNum) ? paramObject : ((paramObject instanceof Float || paramObject instanceof Double) ? new DFloNum(((Number)paramObject).doubleValue()) : RatNum.asRatNumOrNull(paramObject)));
  }
  
  public static RealNum divide(RealNum paramRealNum1, RealNum paramRealNum2) {
    return (RealNum)paramRealNum1.div(paramRealNum2);
  }
  
  public static RealNum times(RealNum paramRealNum1, RealNum paramRealNum2) {
    return (RealNum)paramRealNum1.mul(paramRealNum2);
  }
  
  public static IntNum toExactInt(double paramDouble) {
    boolean bool;
    if (Double.isInfinite(paramDouble) || Double.isNaN(paramDouble))
      throw new ArithmeticException("cannot convert " + paramDouble + " to exact integer"); 
    long l = Double.doubleToLongBits(paramDouble);
    if (l < 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    int i = (int)(l >> 52L) & 0x7FF;
    l &= 0xFFFFFFFFFFFFFL;
    if (i == 0) {
      l <<= 1L;
    } else {
      l |= 0x10000000000000L;
    } 
    if (i <= 1075) {
      i = 1075 - i;
      if (i > 53)
        return IntNum.zero(); 
      l >>= i;
      if (bool)
        l = -l; 
      return IntNum.make(l);
    } 
    if (bool)
      l = -l; 
    return IntNum.shift(IntNum.make(l), i - 1075);
  }
  
  public static IntNum toExactInt(double paramDouble, int paramInt) {
    return toExactInt(toInt(paramDouble, paramInt));
  }
  
  public static double toInt(double paramDouble, int paramInt) {
    switch (paramInt) {
      default:
        return paramDouble;
      case 1:
        return Math.floor(paramDouble);
      case 2:
        return Math.ceil(paramDouble);
      case 3:
        return (paramDouble < 0.0D) ? Math.ceil(paramDouble) : Math.floor(paramDouble);
      case 4:
        break;
    } 
    return Math.rint(paramDouble);
  }
  
  public static IntNum toScaledInt(double paramDouble, int paramInt) {
    return toScaledInt(DFloNum.toExact(paramDouble), paramInt);
  }
  
  public static IntNum toScaledInt(RatNum paramRatNum, int paramInt) {
    RatNum ratNum = paramRatNum;
    if (paramInt != 0) {
      int i;
      ratNum = IntNum.ten();
      if (paramInt < 0) {
        i = -paramInt;
      } else {
        i = paramInt;
      } 
      IntNum intNum = IntNum.power((IntNum)ratNum, i);
      ratNum = paramRatNum.numerator();
      paramRatNum = paramRatNum.denominator();
      if (paramInt >= 0) {
        ratNum = IntNum.times((IntNum)ratNum, intNum);
      } else {
        paramRatNum = IntNum.times((IntNum)paramRatNum, intNum);
      } 
      ratNum = RatNum.make((IntNum)ratNum, (IntNum)paramRatNum);
    } 
    return ratNum.toExactInt(4);
  }
  
  public static String toStringDecimal(String paramString) {
    int i = paramString.indexOf('E');
    if (i >= 0) {
      int j = paramString.length();
      char c = paramString.charAt(j - 1);
      if (c != 'y' && c != 'N') {
        int k;
        int m;
        StringBuffer stringBuffer = new StringBuffer(j + 10);
        if (paramString.charAt(0) == '-') {
          m = 1;
        } else {
          m = 0;
        } 
        if (paramString.charAt(i + 1) != '-')
          throw new Error("not implemented: toStringDecimal given non-negative exponent: " + paramString); 
        c = Character.MIN_VALUE;
        int n;
        for (n = i + 2; n < j; n++)
          k = c * 10 + paramString.charAt(n) - 48; 
        if (m)
          stringBuffer.append('-'); 
        stringBuffer.append("0.");
        while (true) {
          if (--k > 0) {
            stringBuffer.append('0');
            continue;
          } 
          for (k = 0;; k = m) {
            m = k + 1;
            char c1 = paramString.charAt(k);
            if (c1 != 'E') {
              if (c1 != '-') {
                k = 1;
              } else {
                k = 0;
              } 
              if (c1 != '.') {
                n = 1;
              } else {
                n = 0;
              } 
              if ((n & k) != 0 && (c1 != '0' || m < i)) {
                stringBuffer.append(c1);
                k = m;
                continue;
              } 
            } else {
              return stringBuffer.toString();
            } 
          } 
          break;
        } 
      } 
    } 
    return paramString;
  }
  
  public static int toStringScientific(String paramString, StringBuffer paramStringBuffer) {
    if (paramString.charAt(0) == '-') {
      j = 1;
    } else {
      j = 0;
    } 
    if (j)
      paramStringBuffer.append('-'); 
    if (j) {
      i = 1;
    } else {
      i = 0;
    } 
    int m = paramString.length();
    if (paramString.charAt(i) == '0') {
      int n = i;
      while (true) {
        if (n == m) {
          paramStringBuffer.append("0");
          i = 0;
        } else {
          j = n + 1;
          char c = paramString.charAt(n);
          if (c >= '0' && c <= '9' && (c != '0' || j == m)) {
            paramStringBuffer.append(c);
            paramStringBuffer.append('.');
            if (c == '0') {
              i = 0;
            } else {
              i = i - j + 2;
            } 
            if (j == m) {
              paramStringBuffer.append('0');
            } else {
              n = j;
              while (true) {
                j = i;
                int i1 = n;
                if (n < m) {
                  paramStringBuffer.append(paramString.charAt(n));
                  n++;
                  continue;
                } 
                break;
              } 
              i = j;
            } 
          } else {
            n = j;
            continue;
          } 
        } 
        n = paramStringBuffer.length();
        j = -1;
        while (true) {
          char c = paramStringBuffer.charAt(--n);
          if (c == '0') {
            j = n;
            continue;
          } 
          if (c == '.')
            j = n + 2; 
          if (j >= 0)
            paramStringBuffer.setLength(j); 
          return i;
        } 
        break;
      } 
    } 
    if (j != 0) {
      j = 2;
    } else {
      j = 1;
    } 
    int k = m - j - m + paramString.indexOf('.');
    int j = i + 1;
    paramStringBuffer.append(paramString.charAt(i));
    paramStringBuffer.append('.');
    int i = j;
    while (true) {
      j = k;
      int n = i;
      if (i < m) {
        char c = paramString.charAt(i);
        if (c != '.')
          paramStringBuffer.append(c); 
        i++;
        continue;
      } 
      break;
    } 
    i = j;
  }
  
  public static String toStringScientific(double paramDouble) {
    return toStringScientific(Double.toString(paramDouble));
  }
  
  public static String toStringScientific(float paramFloat) {
    return toStringScientific(Float.toString(paramFloat));
  }
  
  public static String toStringScientific(String paramString) {
    if (paramString.indexOf('E') < 0) {
      int i = paramString.length();
      char c = paramString.charAt(i - 1);
      if (c != 'y' && c != 'N') {
        StringBuffer stringBuffer = new StringBuffer(i + 10);
        i = toStringScientific(paramString, stringBuffer);
        stringBuffer.append('E');
        stringBuffer.append(i);
        return stringBuffer.toString();
      } 
    } 
    return paramString;
  }
  
  public Numeric abs() {
    Numeric numeric = this;
    if (isNegative())
      numeric = neg(); 
    return numeric;
  }
  
  public abstract Numeric add(Object paramObject, int paramInt);
  
  public BigDecimal asBigDecimal() {
    return new BigDecimal(doubleValue());
  }
  
  public int compareTo(Object paramObject) {
    return compare(paramObject);
  }
  
  public abstract Numeric div(Object paramObject);
  
  public Complex exp() {
    return new DFloNum(Math.exp(doubleValue()));
  }
  
  public final RealNum im() {
    return IntNum.zero();
  }
  
  public abstract boolean isNegative();
  
  public boolean isZero() {
    return (sign() == 0);
  }
  
  public Complex log() {
    double d = doubleValue();
    return (d < 0.0D) ? DComplex.log(d, 0.0D) : new DFloNum(Math.log(d));
  }
  
  public RealNum max(RealNum paramRealNum) {
    boolean bool;
    if (isExact() && paramRealNum.isExact()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (grt(paramRealNum))
      paramRealNum = this; 
    RealNum realNum = paramRealNum;
    if (!bool) {
      realNum = paramRealNum;
      if (paramRealNum.isExact())
        realNum = new DFloNum(paramRealNum.doubleValue()); 
    } 
    return realNum;
  }
  
  public RealNum min(RealNum paramRealNum) {
    boolean bool;
    if (isExact() && paramRealNum.isExact()) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!grt(paramRealNum))
      paramRealNum = this; 
    RealNum realNum = paramRealNum;
    if (!bool) {
      realNum = paramRealNum;
      if (paramRealNum.isExact())
        realNum = new DFloNum(paramRealNum.doubleValue()); 
    } 
    return realNum;
  }
  
  public abstract Numeric mul(Object paramObject);
  
  public final RealNum re() {
    return this;
  }
  
  public RealNum rneg() {
    return (RealNum)neg();
  }
  
  public abstract int sign();
  
  public final Complex sin() {
    return new DFloNum(Math.sin(doubleValue()));
  }
  
  public final Complex sqrt() {
    double d = doubleValue();
    return (d >= 0.0D) ? new DFloNum(Math.sqrt(d)) : DComplex.sqrt(d, 0.0D);
  }
  
  public RatNum toExact() {
    return DFloNum.toExact(doubleValue());
  }
  
  public IntNum toExactInt(int paramInt) {
    return toExactInt(doubleValue(), paramInt);
  }
  
  public RealNum toInexact() {
    RealNum realNum = this;
    if (isExact())
      realNum = new DFloNum(doubleValue()); 
    return realNum;
  }
  
  public RealNum toInt(int paramInt) {
    return new DFloNum(toInt(doubleValue(), paramInt));
  }
  
  public IntNum toScaledInt(int paramInt) {
    return toScaledInt(toExact(), paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/RealNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */