package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class DQuantity extends Quantity implements Externalizable {
  double factor;
  
  Unit unt;
  
  public DQuantity(double paramDouble, Unit paramUnit) {
    this.factor = paramDouble;
    this.unt = paramUnit;
  }
  
  public static DQuantity add(DQuantity paramDQuantity1, DQuantity paramDQuantity2, double paramDouble) {
    if (paramDQuantity1.dimensions() != paramDQuantity2.dimensions())
      throw new ArithmeticException("units mis-match"); 
    double d = (paramDQuantity2.unit()).factor / (paramDQuantity1.unit()).factor;
    return new DQuantity(paramDQuantity1.factor + paramDouble * d * paramDQuantity2.factor, paramDQuantity1.unit());
  }
  
  public static DQuantity divide(DQuantity paramDQuantity1, DQuantity paramDQuantity2) {
    return new DQuantity(paramDQuantity1.factor / paramDQuantity2.factor, Unit.divide(paramDQuantity1.unit(), paramDQuantity2.unit()));
  }
  
  public static DQuantity times(DQuantity paramDQuantity1, DQuantity paramDQuantity2) {
    return new DQuantity(paramDQuantity1.factor * paramDQuantity2.factor, Unit.times(paramDQuantity1.unit(), paramDQuantity2.unit()));
  }
  
  public Numeric add(Object paramObject, int paramInt) {
    if (paramObject instanceof DQuantity)
      return add(this, (DQuantity)paramObject, paramInt); 
    if (dimensions() == Dimensions.Empty && paramObject instanceof RealNum)
      return new DQuantity(this.factor + paramInt * ((RealNum)paramObject).doubleValue(), unit()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).addReversed(this, paramInt);
  }
  
  public Numeric addReversed(Numeric paramNumeric, int paramInt) {
    if (dimensions() == Dimensions.Empty && paramNumeric instanceof RealNum)
      return new DFloNum(((RealNum)paramNumeric).doubleValue() + paramInt * this.factor); 
    throw new IllegalArgumentException();
  }
  
  public Numeric div(Object paramObject) {
    if (paramObject instanceof DQuantity) {
      paramObject = paramObject;
      return (Numeric)((dimensions() == paramObject.dimensions()) ? new DFloNum(this.factor * unit().doubleValue() / ((DQuantity)paramObject).factor * (paramObject.unit()).factor) : divide(this, (DQuantity)paramObject));
    } 
    if (paramObject instanceof RealNum)
      return new DQuantity(this.factor / ((RealNum)paramObject).doubleValue(), unit()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).divReversed(this);
  }
  
  public Numeric divReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof RealNum)
      return new DQuantity(((RealNum)paramNumeric).doubleValue() / this.factor, Unit.divide(Unit.Empty, unit())); 
    throw new IllegalArgumentException();
  }
  
  public final double doubleValue() {
    return this.factor * this.unt.factor;
  }
  
  public boolean isExact() {
    return false;
  }
  
  public boolean isZero() {
    return (this.factor == 0.0D);
  }
  
  public Numeric mul(Object paramObject) {
    if (paramObject instanceof DQuantity)
      return times(this, (DQuantity)paramObject); 
    if (paramObject instanceof RealNum)
      return new DQuantity(this.factor * ((RealNum)paramObject).doubleValue(), unit()); 
    if (!(paramObject instanceof Numeric))
      throw new IllegalArgumentException(); 
    return ((Numeric)paramObject).mulReversed(this);
  }
  
  public Numeric mulReversed(Numeric paramNumeric) {
    if (paramNumeric instanceof RealNum)
      return new DQuantity(((RealNum)paramNumeric).doubleValue() * this.factor, unit()); 
    throw new IllegalArgumentException();
  }
  
  public final Complex number() {
    return new DFloNum(this.factor);
  }
  
  public final RealNum re() {
    return new DFloNum(this.factor);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.factor = paramObjectInput.readDouble();
    this.unt = (Unit)paramObjectInput.readObject();
  }
  
  public final Unit unit() {
    return this.unt;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeDouble(this.factor);
    paramObjectOutput.writeObject(this.unt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/DQuantity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */