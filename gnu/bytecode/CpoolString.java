package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolString extends CpoolEntry {
  CpoolUtf8 str;
  
  CpoolString() {}
  
  CpoolString(ConstantPool paramConstantPool, int paramInt, CpoolUtf8 paramCpoolUtf8) {
    super(paramConstantPool, paramInt);
    this.str = paramCpoolUtf8;
  }
  
  static final int hashCode(CpoolUtf8 paramCpoolUtf8) {
    return paramCpoolUtf8.hashCode() ^ 0xF30F;
  }
  
  public final CpoolUtf8 getString() {
    return this.str;
  }
  
  public int getTag() {
    return 8;
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = hashCode(this.str); 
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt) {
    if (paramInt > 0) {
      paramClassTypeWriter.print("String ");
      if (paramInt == 2)
        paramClassTypeWriter.printOptionalIndex(this.str); 
    } 
    paramClassTypeWriter.printConstantTersely(this.str.index, 1);
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(8);
    paramDataOutputStream.writeShort(this.str.index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */