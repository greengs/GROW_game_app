package gnu.math;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class BaseUnit extends NamedUnit implements Externalizable {
  static int base_count = 0;
  
  private static final String unitName = "(name)";
  
  String dimension;
  
  int index;
  
  public BaseUnit() {
    this.index = Integer.MAX_VALUE;
    this.dims = Dimensions.Empty;
  }
  
  public BaseUnit(String paramString) {
    init();
  }
  
  public BaseUnit(String paramString1, String paramString2) {
    this.dimension = paramString2;
    init();
  }
  
  public static int compare(BaseUnit paramBaseUnit1, BaseUnit paramBaseUnit2) {
    int i = paramBaseUnit1.name.compareTo(paramBaseUnit2.name);
    if (i != 0)
      return i; 
    String str1 = paramBaseUnit1.dimension;
    String str2 = paramBaseUnit2.dimension;
    return (str1 == str2) ? 0 : ((str1 == null) ? -1 : ((str2 == null) ? 1 : str1.compareTo(str2)));
  }
  
  public static BaseUnit lookup(String paramString1, String paramString2) {
    String str = paramString1.intern();
    if (str == "(name)" && paramString2 == null)
      return Unit.Empty; 
    int i = str.hashCode();
    int j = table.length;
    for (NamedUnit namedUnit = table[(Integer.MAX_VALUE & i) % j]; namedUnit != null; namedUnit = namedUnit.chain) {
      if (namedUnit.name == str && namedUnit instanceof BaseUnit) {
        BaseUnit baseUnit2 = (BaseUnit)namedUnit;
        BaseUnit baseUnit1 = baseUnit2;
        if (baseUnit2.dimension != paramString2)
          continue; 
        return baseUnit1;
      } 
      continue;
    } 
    return null;
  }
  
  public static BaseUnit make(String paramString1, String paramString2) {
    BaseUnit baseUnit2 = lookup(paramString1, paramString2);
    BaseUnit baseUnit1 = baseUnit2;
    if (baseUnit2 == null)
      baseUnit1 = new BaseUnit(paramString1, paramString2); 
    return baseUnit1;
  }
  
  public String getDimension() {
    return this.dimension;
  }
  
  public int hashCode() {
    return this.name.hashCode();
  }
  
  protected void init() {
    this.base = this;
    this.scale = 1.0D;
    this.dims = new Dimensions(this);
    super.init();
    int i = base_count;
    base_count = i + 1;
    this.index = i;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = paramObjectInput.readUTF();
    this.dimension = (String)paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    BaseUnit baseUnit = lookup(this.name, this.dimension);
    if (baseUnit != null)
      return baseUnit; 
    init();
    return this;
  }
  
  public Unit unit() {
    return this;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(this.name);
    paramObjectOutput.writeObject(this.dimension);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/math/BaseUnit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */