package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolValue2 extends CpoolEntry {
  int tag;
  
  long value;
  
  CpoolValue2(int paramInt) {
    this.tag = paramInt;
  }
  
  CpoolValue2(ConstantPool paramConstantPool, int paramInt1, int paramInt2, long paramLong) {
    super(paramConstantPool, paramInt2);
    this.tag = paramInt1;
    this.value = paramLong;
    paramConstantPool.count++;
  }
  
  static int hashCode(long paramLong) {
    return (int)paramLong;
  }
  
  public int getTag() {
    return this.tag;
  }
  
  public final long getValue() {
    return this.value;
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = hashCode(this.value); 
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt) {
    if (this.tag == 5) {
      if (paramInt > 0)
        paramClassTypeWriter.print("Long "); 
      paramClassTypeWriter.print(this.value);
      if (paramInt > 1 && this.value != 0L) {
        paramClassTypeWriter.print("=0x");
        paramClassTypeWriter.print(Long.toHexString(this.value));
      } 
      return;
    } 
    if (paramInt > 0)
      paramClassTypeWriter.print("Double "); 
    paramClassTypeWriter.print(Double.longBitsToDouble(this.value));
    if (paramInt > 1) {
      paramClassTypeWriter.print("=0x");
      paramClassTypeWriter.print(Long.toHexString(this.value));
      return;
    } 
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(this.tag);
    paramDataOutputStream.writeLong(this.value);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolValue2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */