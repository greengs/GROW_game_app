package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class CQuantity extends Quantity implements Externalizable {
  Complex num;
  
  Unit unt;
  
  public CQuantity(Complex paramComplex, Unit paramUnit) {
    this.num = paramComplex;
    this.unt = paramUnit;
  }
  
  public CQuantity(RealNum paramRealNum1, RealNum paramRealNum2, Unit paramUnit) {
    this.num = new CComplex(paramRealNum1, paramRealNum2);
    this.unt = paramUnit;
  }
  
  public boolean isExact() {
    return this.num.isExact();
  }
  
  public boolean isZero() {
    return this.num.isZero();
  }
  
  public Complex number() {
    return this.num;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.num = (Complex)paramObjectInput.readObject();
    this.unt = (Unit)paramObjectInput.readObject();
  }
  
  public Unit unit() {
    return this.unt;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.num);
    paramObjectOutput.writeObject(this.unt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/CQuantity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */