package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DFloNum extends RealNum implements Externalizable {
  private static final DFloNum one = new DFloNum(1.0D);
  
  double value;
  
  public DFloNum() {}
  
  public DFloNum(double paramDouble) {
    this.value = paramDouble;
  }
  
  public DFloNum(String paramString) throws NumberFormatException {
    this.value = Double.valueOf(paramString).doubleValue();
    if (this.value == 0.0D && paramString.charAt(0) == '-')
      this.value = 0.0D; 
  }
  
  public static DFloNum asDFloNumOrNull(Object paramObject) {
    return (paramObject instanceof DFloNum) ? (DFloNum)paramObject : ((paramObject instanceof RealNum || paramObject instanceof Number) ? new DFloNum(((Number)paramObject).doubleValue()) : null);
  }
  
  public static int compare(double paramDouble1, double paramDouble2) {
    return (paramDouble1 > paramDouble2) ? 1 : ((paramDouble1 < paramDouble2) ? -1 : ((paramDouble1 == paramDouble2) ? 0 : -2));
  }
  
  public static int compare(IntNum paramIntNum1, IntNum paramIntNum2, double paramDouble) {
    boolean bool;
    int i = 1;
    if (Double.isNaN(paramDouble))
      return -2; 
    if (Double.isInfinite(paramDouble)) {
      if (paramDouble >= 0.0D)
        i = -1; 
      bool = i;
      if (paramIntNum2.isZero()) {
        if (paramIntNum1.isZero())
          return -2; 
        i >>= 1;
        bool = i;
        if (!paramIntNum1.isNegative())
          return i ^ 0xFFFFFFFF; 
      } 
      return bool;
    } 
    long l1 = Double.doubleToLongBits(paramDouble);
    if (l1 < 0L) {
      bool = true;
    } else {
      bool = false;
    } 
    i = (int)(l1 >> 52L) & 0x7FF;
    l1 &= 0xFFFFFFFFFFFFFL;
    if (i == 0) {
      l1 <<= 1L;
    } else {
      l1 |= 0x10000000000000L;
    } 
    long l2 = l1;
    if (bool)
      l2 = -l1; 
    IntNum intNum1 = IntNum.make(l2);
    if (i >= 1075) {
      IntNum intNum = IntNum.shift(intNum1, i - 1075);
      intNum1 = paramIntNum1;
      paramIntNum1 = intNum;
      return IntNum.compare(intNum1, IntNum.times(paramIntNum1, paramIntNum2));
    } 
    IntNum intNum2 = IntNum.shift(paramIntNum1, 1075 - i);
    paramIntNum1 = intNum1;
    intNum1 = intNum2;
    return IntNum.compare(intNum1, IntNum.times(paramIntNum1, paramIntNum2));
  }
  
  public static DFloNum make(double paramDouble) {
    return new DFloNum(paramDouble);
  }
  
  public static final DFloNum one() {
    return one;
  }
  
  public static RatNum toExact(double paramDouble) {
    byte b = 1;
    if (Double.isInfinite(paramDouble)) {
      if (paramDouble < 0.0D)
        b = -1; 
      return RatNum.infinity(b);
    } 
    if (Double.isNaN(paramDouble))
      throw new ArithmeticException("cannot convert NaN to exact rational"); 
    long l1 = Double.doubleToLongBits(paramDouble);
    if (l1 < 0L) {
      b = 1;
    } else {
      b = 0;
    } 
    int i = (int)(l1 >> 52L) & 0x7FF;
    l1 &= 0xFFFFFFFFFFFFFL;
    if (i == 0) {
      l1 <<= 1L;
    } else {
      l1 |= 0x10000000000000L;
    } 
    long l2 = l1;
    if (b != 0)
      l2 = -l1; 
    IntNum intNum = IntNum.make(l2);
    return (i >= 1075) ? IntNum.shift(intNum, i - 1075) : RatNum.make(intNum, IntNum.shift(IntNum.one(), 1075 - i));
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof RealNum)
      return new DFloNum(this.value + paramInt * ((RealNum)paramObject).doubleValue()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    if (paramNumeric instanceof RealNum)
      return new DFloNum(((RealNum)paramNumeric).doubleValue() + paramInt * this.value); 
    throw new IllegalArgumentException();
  }
  
  public int compare(Object paramObject) {
    if (paramObject instanceof RatNum) {
      paramObject = paramObject;
      int i = compare(paramObject.numerator(), paramObject.denominator(), this.value);
      return (i < -1) ? i : -i;
    } 
    return compare(this.value, ((RealNum)paramObject).doubleValue());
  }
  
  public int compareReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof RatNum) {
      paramNumeric = paramNumeric;
      return compare(paramNumeric.numerator(), paramNumeric.denominator(), this.value);
    } 
    return compare(((RealNum)paramNumeric).doubleValue(), this.value);
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof RealNum)
      return new DFloNum(this.value / ((RealNum)paramObject).doubleValue()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof RealNum)
      return new DFloNum(((RealNum)paramNumeric).doubleValue() / this.value); 
    throw new IllegalArgumentException();
  }
  
  public final double doubleValue() {
    return this.value;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject != null && paramObject instanceof DFloNum && Double.doubleToLongBits(((DFloNum)paramObject).value) == Double.doubleToLongBits(this.value));
  }
  
  public int hashCode() {
    return (int)this.value;
  }
  
  public boolean isExact() {
    return false;
  }
  
  public boolean isNegative() {
    return (this.value < 0.0D);
  }
  
  public boolean isZero() {
    return (this.value == 0.0D);
  }
  
  public long longValue() {
    return (long)this.value;
  }
  
  public Numeric mul(Object paramObject) {
    if (paramObject instanceof RealNum)
      return new DFloNum(this.value * ((RealNum)paramObject).doubleValue()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof RealNum)
      return new DFloNum(((RealNum)paramNumeric).doubleValue() * this.value); 
    throw new IllegalArgumentException();
  }
  
  public Numeric neg() {
    return new DFloNum(-this.value);
  }
  
  public Numeric power(IntNum paramIntNum) {
    return new DFloNum(Math.pow(doubleValue(), paramIntNum.doubleValue()));
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.value = paramObjectInput.readDouble();
  }
  
  public int sign() {
    return (this.value > 0.0D) ? 1 : ((this.value < 0.0D) ? -1 : ((this.value == 0.0D) ? 0 : -2));
  }
  
  public String toString() {
    return (this.value == Double.POSITIVE_INFINITY) ? "+inf.0" : ((this.value == Double.NEGATIVE_INFINITY) ? "-inf.0" : (Double.isNaN(this.value) ? "+nan.0" : Double.toString(this.value)));
  }
  
  public String toString(int paramInt) {
    return (paramInt == 10) ? toString() : ("#d" + toString());
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeDouble(this.value);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/DFloNum.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */