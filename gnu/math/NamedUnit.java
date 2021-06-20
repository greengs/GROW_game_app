package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class NamedUnit extends Unit implements Externalizable {
  Unit base;
  
  NamedUnit chain;
  
  String name;
  
  double scale;
  
  public NamedUnit() {}
  
  public NamedUnit(String paramString, double paramDouble, Unit paramUnit) {
    this.name = paramString;
    this.base = paramUnit;
    this.scale = paramDouble;
    init();
  }
  
  public NamedUnit(String paramString, DQuantity paramDQuantity) {
    this.name = paramString.intern();
    this.scale = paramDQuantity.factor;
    this.base = paramDQuantity.unt;
    init();
  }
  
  public static NamedUnit lookup(String paramString) {
    String str = paramString.intern();
    int i = str.hashCode();
    int j = table.length;
    for (NamedUnit namedUnit = table[(Integer.MAX_VALUE & i) % j]; namedUnit != null; namedUnit = namedUnit.chain) {
      if (namedUnit.name == str)
        return namedUnit; 
    } 
    return null;
  }
  
  public static NamedUnit lookup(String paramString, double paramDouble, Unit paramUnit) {
    String str = paramString.intern();
    int i = str.hashCode();
    int j = table.length;
    for (NamedUnit namedUnit = table[(Integer.MAX_VALUE & i) % j]; namedUnit != null; namedUnit = namedUnit.chain) {
      if (namedUnit.name == str && namedUnit.scale == paramDouble && namedUnit.base == paramUnit)
        return namedUnit; 
    } 
    return null;
  }
  
  public static NamedUnit make(String paramString, double paramDouble, Unit paramUnit) {
    NamedUnit namedUnit2 = lookup(paramString, paramDouble, paramUnit);
    NamedUnit namedUnit1 = namedUnit2;
    if (namedUnit2 == null)
      namedUnit1 = new NamedUnit(paramString, paramDouble, paramUnit); 
    return namedUnit1;
  }
  
  public static NamedUnit make(String paramString, Quantity paramQuantity) {
    double d;
    if (paramQuantity instanceof DQuantity) {
      d = ((DQuantity)paramQuantity).factor;
    } else {
      if (paramQuantity.imValue() != 0.0D)
        throw new ArithmeticException("defining " + paramString + " using complex value"); 
      d = paramQuantity.re().doubleValue();
    } 
    Unit unit = paramQuantity.unit();
    NamedUnit namedUnit = lookup(paramString, d, unit);
    paramQuantity = namedUnit;
    if (namedUnit == null)
      paramQuantity = new NamedUnit(paramString, d, unit); 
    return (NamedUnit)paramQuantity;
  }
  
  public String getName() {
    return this.name;
  }
  
  protected void init() {
    this.factor = this.scale * this.base.factor;
    this.dims = this.base.dims;
    this.name = this.name.intern();
    int i = (Integer.MAX_VALUE & this.name.hashCode()) % table.length;
    this.chain = table[i];
    table[i] = this;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = paramObjectInput.readUTF();
    this.scale = paramObjectInput.readDouble();
    this.base = (Unit)paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    NamedUnit namedUnit = lookup(this.name, this.scale, this.base);
    if (namedUnit != null)
      return namedUnit; 
    init();
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(this.name);
    paramObjectOutput.writeDouble(this.scale);
    paramObjectOutput.writeObject(this.base);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/NamedUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */