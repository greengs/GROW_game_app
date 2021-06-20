package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class IntFraction extends RatNum implements Externalizable {
  IntNum den;
  
  IntNum num;
  
  IntFraction() {}
  
  public IntFraction(IntNum paramIntNum1, IntNum paramIntNum2) {
    this.num = paramIntNum1;
    this.den = paramIntNum2;
  }
  
  public static IntFraction neg(IntFraction paramIntFraction) {
    return new IntFraction(IntNum.neg(paramIntFraction.numerator()), paramIntFraction.denominator());
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof RatNum)
      return RatNum.add(this, (RatNum)paramObject, paramInt); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    if (!(paramNumeric instanceof RatNum))
      throw new IllegalArgumentException(); 
    return RatNum.add((RatNum)paramNumeric, this, paramInt);
  }
  
  public final int compare(Object paramObject) {
    return (paramObject instanceof RatNum) ? RatNum.compare(this, (RatNum)paramObject) : ((RealNum)paramObject).compareReversed(this);
  }
  
  public int compareReversed(Numeric paramNumeric) {
    return RatNum.compare((RatNum)paramNumeric, this);
  }
  
  public final IntNum denominator() {
    return this.den;
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof RatNum)
      return RatNum.divide(this, (RatNum)paramObject); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric) {
    if (!(paramNumeric instanceof RatNum))
      throw new IllegalArgumentException(); 
    return RatNum.divide((RatNum)paramNumeric, this);
  }
  
  public double doubleValue() {
    boolean bool1 = this.num.isNegative();
    if (this.den.isZero())
      return bool1 ? Double.NEGATIVE_INFINITY : (this.num.isZero() ? Double.NaN : Double.POSITIVE_INFINITY); 
    IntNum intNum2 = this.num;
    IntNum intNum1 = intNum2;
    if (bool1)
      intNum1 = IntNum.neg(intNum2); 
    int j = intNum1.intLength();
    int k = this.den.intLength();
    int i = 0;
    intNum2 = intNum1;
    if (j < k + 54) {
      i = k + 54 - j;
      intNum2 = IntNum.shift(intNum1, i);
      i = -i;
    } 
    IntNum intNum3 = new IntNum();
    intNum1 = new IntNum();
    IntNum.divide(intNum2, this.den, intNum3, intNum1, 3);
    intNum2 = intNum3.canonicalize();
    if (!intNum1.canonicalize().isZero()) {
      boolean bool2 = true;
      return intNum2.roundToDouble(i, bool1, bool2);
    } 
    boolean bool = false;
    return intNum2.roundToDouble(i, bool1, bool);
  }
  
  public final boolean isNegative() {
    return this.num.isNegative();
  }
  
  public long longValue() {
    return toExactInt(4).longValue();
  }
  
  public Numeric mul(Object paramObject) {
    if (paramObject instanceof RatNum)
      return RatNum.times(this, (RatNum)paramObject); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric) {
    if (!(paramNumeric instanceof RatNum))
      throw new IllegalArgumentException(); 
    return RatNum.times((RatNum)paramNumeric, this);
  }
  
  public Numeric neg() {
    return neg(this);
  }
  
  public final IntNum numerator() {
    return this.num;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.num = (IntNum)paramObjectInput.readObject();
    this.den = (IntNum)paramObjectInput.readObject();
  }
  
  public final int sign() {
    return this.num.sign();
  }
  
  public String toString(int paramInt) {
    return this.num.toString(paramInt) + '/' + this.den.toString(paramInt);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.num);
    paramObjectOutput.writeObject(this.den);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/IntFraction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */