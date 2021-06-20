package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttr extends Attribute {
  Object value;
  
  int value_index;
  
  public ConstantValueAttr(int paramInt) {
    super("ConstantValue");
    this.value_index = paramInt;
  }
  
  public ConstantValueAttr(Object paramObject) {
    super("ConstantValue");
    this.value = paramObject;
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
    if (this.value_index == 0) {
      CpoolValue2 cpoolValue2;
      ConstantPool constantPool = paramClassType.getConstants();
      paramClassType = null;
      if (this.value instanceof String) {
        CpoolString cpoolString = constantPool.addString((String)this.value);
      } else if (this.value instanceof Integer) {
        CpoolValue1 cpoolValue1 = constantPool.addInt(((Integer)this.value).intValue());
      } else if (this.value instanceof Long) {
        cpoolValue2 = constantPool.addLong(((Long)this.value).longValue());
      } else if (this.value instanceof Float) {
        CpoolValue1 cpoolValue1 = constantPool.addFloat(((Float)this.value).floatValue());
      } else if (this.value instanceof Long) {
        cpoolValue2 = constantPool.addDouble(((Double)this.value).doubleValue());
      } 
      this.value_index = cpoolValue2.getIndex();
    } 
  }
  
  public final int getLength() {
    return 2;
  }
  
  public Object getValue(ConstantPool paramConstantPool) {
    if (this.value != null)
      return this.value; 
    CpoolEntry cpoolEntry = paramConstantPool.getPoolEntry(this.value_index);
    switch (cpoolEntry.getTag()) {
      default:
        return this.value;
      case 8:
        this.value = ((CpoolString)cpoolEntry).getString().getString();
      case 3:
        this.value = new Integer(((CpoolValue1)cpoolEntry).value);
      case 5:
        this.value = new Long(((CpoolValue2)cpoolEntry).value);
      case 4:
        this.value = new Float(Float.intBitsToFloat(((CpoolValue1)cpoolEntry).value));
      case 6:
        break;
    } 
    this.value = new Double(Double.longBitsToDouble(((CpoolValue2)cpoolEntry).value));
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", value: ");
    if (this.value_index == 0) {
      Object object = getValue(paramClassTypeWriter.ctype.constants);
      if (object instanceof String) {
        paramClassTypeWriter.printQuotedString((String)object);
      } else {
        paramClassTypeWriter.print(object);
      } 
    } else {
      paramClassTypeWriter.printOptionalIndex(this.value_index);
      paramClassTypeWriter.ctype.constants.getPoolEntry(this.value_index).print(paramClassTypeWriter, 1);
    } 
    paramClassTypeWriter.println();
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeShort(this.value_index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ConstantValueAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */