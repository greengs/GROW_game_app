package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DComplex extends Complex implements Externalizable {
  double imag;
  
  double real;
  
  public DComplex() {}
  
  public DComplex(double paramDouble1, double paramDouble2) {
    this.real = paramDouble1;
    this.imag = paramDouble2;
  }
  
  public static DComplex div(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    if (Math.abs(paramDouble3) <= Math.abs(paramDouble4)) {
      double d1 = paramDouble3 / paramDouble4;
      paramDouble4 *= 1.0D + d1 * d1;
      paramDouble3 = paramDouble1 * d1 + paramDouble2;
      paramDouble2 = paramDouble2 * d1 - paramDouble1;
      paramDouble1 = paramDouble4;
      return new DComplex(paramDouble3 / paramDouble1, paramDouble2 / paramDouble1);
    } 
    double d = paramDouble4 / paramDouble3;
    paramDouble4 = paramDouble3 * (1.0D + d * d);
    paramDouble3 = paramDouble1 + paramDouble2 * d;
    paramDouble2 -= paramDouble1 * d;
    paramDouble1 = paramDouble4;
    return new DComplex(paramDouble3 / paramDouble1, paramDouble2 / paramDouble1);
  }
  
  public static Complex log(double paramDouble1, double paramDouble2) {
    return make(Math.log(Math.hypot(paramDouble1, paramDouble2)), Math.atan2(paramDouble2, paramDouble1));
  }
  
  public static DComplex power(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4) {
    double d = Math.log(Math.hypot(paramDouble1, paramDouble2));
    paramDouble1 = Math.atan2(paramDouble2, paramDouble1);
    return Complex.polar(Math.exp(d * paramDouble3 - paramDouble4 * paramDouble1), paramDouble4 * d + paramDouble3 * paramDouble1);
  }
  
  public static Complex sqrt(double paramDouble1, double paramDouble2) {
    double d = Math.hypot(paramDouble1, paramDouble2);
    if (d == 0.0D) {
      paramDouble1 = d;
      paramDouble2 = d;
      return new DComplex(paramDouble2, paramDouble1);
    } 
    if (paramDouble1 > 0.0D) {
      d = Math.sqrt(0.5D * (d + paramDouble1));
      paramDouble1 = paramDouble2 / d / 2.0D;
      paramDouble2 = d;
      return new DComplex(paramDouble2, paramDouble1);
    } 
    d = Math.sqrt(0.5D * (d - paramDouble1));
    paramDouble1 = d;
    if (paramDouble2 < 0.0D)
      paramDouble1 = -d; 
    paramDouble2 = paramDouble2 / paramDouble1 / 2.0D;
    return new DComplex(paramDouble2, paramDouble1);
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof Complex) {
      paramObject = paramObject;
      if (paramObject.dimensions() != Dimensions.Empty)
        throw new ArithmeticException("units mis-match"); 
      return new DComplex(this.real + paramInt * paramObject.reValue(), this.imag + paramInt * paramObject.imValue());
    } 
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof Complex) {
      paramObject = paramObject;
      return div(this.real, this.imag, paramObject.doubleValue(), paramObject.doubleImagValue());
    } 
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public double doubleImagValue() {
    return this.imag;
  }
  
  public double doubleValue() {
    return this.real;
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof Complex) {
      paramObject = paramObject;
      if (paramObject.unit() == Unit.Empty && Double.doubleToLongBits(this.real) == Double.doubleToLongBits(paramObject.reValue()) && Double.doubleToLongBits(this.imag) == Double.doubleToLongBits(paramObject.imValue()))
        return true; 
    } 
    return false;
  }
  
  public RealNum im() {
    return new DFloNum(this.imag);
  }
  
  public boolean isExact() {
    return false;
  }
  
  public Numeric mul(Object paramObject) {
    if (paramObject instanceof Complex) {
      paramObject = paramObject;
      if (paramObject.unit() == Unit.Empty) {
        double d1 = paramObject.reValue();
        double d2 = paramObject.imValue();
        return new DComplex(this.real * d1 - this.imag * d2, this.real * d2 + this.imag * d1);
      } 
      return Complex.times(this, (Complex)paramObject);
    } 
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public final Numeric neg() {
    return new DComplex(-this.real, -this.imag);
  }
  
  public RealNum re() {
    return new DFloNum(this.real);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.real = paramObjectInput.readDouble();
    this.imag = paramObjectInput.readDouble();
  }
  
  public Complex toExact() {
    return new CComplex(DFloNum.toExact(this.real), DFloNum.toExact(this.imag));
  }
  
  public String toString() {
    String str1;
    String str3;
    String str4;
    String str2 = "";
    if (this.real == Double.POSITIVE_INFINITY) {
      str2 = "#i";
      str4 = "1/0";
    } else if (this.real == Double.NEGATIVE_INFINITY) {
      str2 = "#i";
      str4 = "-1/0";
    } else if (Double.isNaN(this.real)) {
      str2 = "#i";
      str4 = "0/0";
    } else {
      str4 = Double.toString(this.real);
    } 
    if (Double.doubleToLongBits(this.imag) == 0L)
      return str2 + str4; 
    if (this.imag == Double.POSITIVE_INFINITY) {
      str3 = "#i";
      str1 = "+1/0i";
    } else if (this.imag == Double.NEGATIVE_INFINITY) {
      str3 = "#i";
      str1 = "-1/0i";
    } else if (Double.isNaN(this.imag)) {
      str3 = "#i";
      str1 = "+0/0i";
    } else {
      String str = Double.toString(this.imag) + "i";
      str1 = str;
      str3 = str2;
      if (str.charAt(0) != '-') {
        str1 = "+" + str;
        str3 = str2;
      } 
    } 
    StringBuilder stringBuilder = new StringBuilder();
    if (Double.doubleToLongBits(this.real) != 0L)
      str3 = str3 + str4; 
    return stringBuilder.append(str3).append(str1).toString();
  }
  
  public String toString(int paramInt) {
    return (paramInt == 10) ? toString() : ("#d" + toString());
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeDouble(this.real);
    paramObjectOutput.writeDouble(this.imag);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/DComplex.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */