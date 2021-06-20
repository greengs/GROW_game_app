package gnu.math;

public abstract class Complex extends Quantity {
  private static CComplex imMinusOne;
  
  private static CComplex imOne;
  
  public static Complex add(Complex paramComplex1, Complex paramComplex2, int paramInt) {
    return make(RealNum.add(paramComplex1.re(), paramComplex2.re(), paramInt), RealNum.add(paramComplex1.im(), paramComplex2.im(), paramInt));
  }
  
  public static int compare(Complex paramComplex1, Complex paramComplex2) {
    int i = paramComplex1.im().compare(paramComplex2.im());
    return (i != 0) ? i : paramComplex1.re().compare(paramComplex2.re());
  }
  
  public static Complex divide(Complex paramComplex1, Complex paramComplex2) {
    if (!paramComplex1.isExact() || !paramComplex2.isExact())
      return DComplex.div(paramComplex1.doubleRealValue(), paramComplex1.doubleImagValue(), paramComplex2.doubleRealValue(), paramComplex2.doubleImagValue()); 
    RealNum realNum1 = paramComplex1.re();
    paramComplex1 = paramComplex1.im();
    RealNum realNum2 = paramComplex2.re();
    RealNum realNum4 = paramComplex2.im();
    paramComplex2 = RealNum.add(RealNum.times(realNum2, realNum2), RealNum.times(realNum4, realNum4), 1);
    RealNum realNum3 = RealNum.add(RealNum.times(realNum1, realNum2), RealNum.times((RealNum)paramComplex1, realNum4), 1);
    paramComplex1 = RealNum.add(RealNum.times((RealNum)paramComplex1, realNum2), RealNum.times(realNum1, realNum4), -1);
    return make(RealNum.divide(realNum3, (RealNum)paramComplex2), RealNum.divide((RealNum)paramComplex1, (RealNum)paramComplex2));
  }
  
  public static boolean equals(Complex paramComplex1, Complex paramComplex2) {
    return (paramComplex1.re().equals(paramComplex2.re()) && paramComplex1.im().equals(paramComplex1.im()));
  }
  
  public static CComplex imMinusOne() {
    if (imMinusOne == null)
      imMinusOne = new CComplex(IntNum.zero(), IntNum.minusOne()); 
    return imMinusOne;
  }
  
  public static CComplex imOne() {
    if (imOne == null)
      imOne = new CComplex(IntNum.zero(), IntNum.one()); 
    return imOne;
  }
  
  public static Complex make(double paramDouble1, double paramDouble2) {
    return (Complex)((paramDouble2 == 0.0D) ? new DFloNum(paramDouble1) : new DComplex(paramDouble1, paramDouble2));
  }
  
  public static Complex make(RealNum paramRealNum1, RealNum paramRealNum2) {
    return (Complex)(paramRealNum2.isZero() ? paramRealNum1 : ((!paramRealNum1.isExact() || !paramRealNum2.isExact()) ? new DComplex(paramRealNum1.doubleValue(), paramRealNum2.doubleValue()) : new CComplex(paramRealNum1, paramRealNum2)));
  }
  
  public static Complex neg(Complex paramComplex) {
    return make(paramComplex.re().rneg(), paramComplex.im().rneg());
  }
  
  public static DComplex polar(double paramDouble1, double paramDouble2) {
    return new DComplex(Math.cos(paramDouble2) * paramDouble1, Math.sin(paramDouble2) * paramDouble1);
  }
  
  public static DComplex polar(RealNum paramRealNum1, RealNum paramRealNum2) {
    return polar(paramRealNum1.doubleValue(), paramRealNum2.doubleValue());
  }
  
  public static Complex power(Complex paramComplex1, Complex paramComplex2) {
    if (paramComplex2 instanceof IntNum)
      return (Complex)paramComplex1.power((IntNum)paramComplex2); 
    double d1 = paramComplex1.doubleRealValue();
    double d2 = paramComplex1.doubleImagValue();
    double d3 = paramComplex2.doubleRealValue();
    double d4 = paramComplex2.doubleImagValue();
    return (Complex)((d2 == 0.0D && d4 == 0.0D && (d1 >= 0.0D || Double.isInfinite(d1) || Double.isNaN(d1))) ? new DFloNum(Math.pow(d1, d3)) : DComplex.power(d1, d2, d3, d4));
  }
  
  public static Complex times(Complex paramComplex1, Complex paramComplex2) {
    RealNum realNum1 = paramComplex1.re();
    paramComplex1 = paramComplex1.im();
    RealNum realNum2 = paramComplex2.re();
    paramComplex2 = paramComplex2.im();
    return make(RealNum.add(RealNum.times(realNum1, realNum2), RealNum.times((RealNum)paramComplex1, (RealNum)paramComplex2), -1), RealNum.add(RealNum.times(realNum1, (RealNum)paramComplex2), RealNum.times((RealNum)paramComplex1, realNum2), 1));
  }
  
  public Numeric abs() {
    return new DFloNum(Math.hypot(doubleRealValue(), doubleImagValue()));
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    return (paramObject instanceof Complex) ? add(this, (Complex)paramObject, paramInt) : ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    if (paramNumeric instanceof Complex)
      return add((Complex)paramNumeric, this, paramInt); 
    throw new IllegalArgumentException();
  }
  
  public RealNum angle() {
    return new DFloNum(Math.atan2(doubleImagValue(), doubleRealValue()));
  }
  
  public int compare(Object paramObject) {
    return !(paramObject instanceof Complex) ? ((Numeric)paramObject).compareReversed(this) : compare(this, (Complex)paramObject);
  }
  
  public Numeric div(Object paramObject) {
    return (paramObject instanceof Complex) ? divide(this, (Complex)paramObject) : ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof Complex)
      return divide((Complex)paramNumeric, this); 
    throw new IllegalArgumentException();
  }
  
  public double doubleImagValue() {
    return im().doubleValue();
  }
  
  public final double doubleRealValue() {
    return doubleValue();
  }
  
  public double doubleValue() {
    return re().doubleValue();
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject == null || !(paramObject instanceof Complex)) ? false : equals(this, (Complex)paramObject);
  }
  
  public Complex exp() {
    return polar(Math.exp(doubleRealValue()), doubleImagValue());
  }
  
  public boolean isExact() {
    return (re().isExact() && im().isExact());
  }
  
  public boolean isZero() {
    return (re().isZero() && im().isZero());
  }
  
  public Complex log() {
    return DComplex.log(doubleRealValue(), doubleImagValue());
  }
  
  public long longValue() {
    return re().longValue();
  }
  
  public Numeric mul(Object paramObject) {
    return (paramObject instanceof Complex) ? times(this, (Complex)paramObject) : ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof Complex)
      return times((Complex)paramNumeric, this); 
    throw new IllegalArgumentException();
  }
  
  public Numeric neg() {
    return neg(this);
  }
  
  public Complex number() {
    return this;
  }
  
  public Complex sqrt() {
    return DComplex.sqrt(doubleRealValue(), doubleImagValue());
  }
  
  public Complex toExact() {
    RealNum realNum1 = re();
    RealNum realNum2 = im();
    RatNum ratNum1 = realNum1.toExact();
    RatNum ratNum2 = realNum2.toExact();
    return (ratNum1 == realNum1 && ratNum2 == realNum2) ? this : new CComplex(ratNum1, ratNum2);
  }
  
  public Complex toInexact() {
    return isExact() ? this : new DComplex(re().doubleValue(), im().doubleValue());
  }
  
  public String toString(int paramInt) {
    if (im().isZero())
      return re().toString(paramInt); 
    String str2 = im().toString(paramInt) + "i";
    String str1 = str2;
    if (str2.charAt(0) != '-')
      str1 = "+" + str2; 
    str2 = str1;
    return !re().isZero() ? (re().toString(paramInt) + str1) : str2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/Complex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */