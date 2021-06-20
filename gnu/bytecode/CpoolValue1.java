package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolValue1 extends CpoolEntry {
  int tag;
  
  int value;
  
  CpoolValue1(int paramInt) {
    this.tag = paramInt;
  }
  
  CpoolValue1(ConstantPool paramConstantPool, int paramInt1, int paramInt2, int paramInt3) {
    super(paramConstantPool, paramInt2);
    this.tag = paramInt1;
    this.value = paramInt3;
  }
  
  static int hashCode(int paramInt) {
    return paramInt;
  }
  
  public int getTag() {
    return this.tag;
  }
  
  public final int getValue() {
    return this.value;
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = this.value; 
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt) {
    if (this.tag == 3) {
      if (paramInt > 0)
        paramClassTypeWriter.print("Integer "); 
      paramClassTypeWriter.print(this.value);
      if (paramInt > 1 && this.value != 0) {
        paramClassTypeWriter.print("=0x");
        paramClassTypeWriter.print(Integer.toHexString(this.value));
      } 
      return;
    } 
    if (paramInt > 0)
      paramClassTypeWriter.print("Float "); 
    paramClassTypeWriter.print(Float.intBitsToFloat(this.value));
    if (paramInt > 1) {
      paramClassTypeWriter.print("=0x");
      paramClassTypeWriter.print(Integer.toHexString(this.value));
      return;
    } 
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(this.tag);
    paramDataOutputStream.writeInt(this.value);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolValue1.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */