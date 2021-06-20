package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class CpoolClass extends CpoolEntry {
  ObjectType clas;
  
  CpoolUtf8 name;
  
  CpoolClass() {}
  
  CpoolClass(ConstantPool paramConstantPool, int paramInt, CpoolUtf8 paramCpoolUtf8) {
    super(paramConstantPool, paramInt);
    this.name = paramCpoolUtf8;
  }
  
  static final int hashCode(CpoolUtf8 paramCpoolUtf8) {
    return paramCpoolUtf8.hashCode() ^ 0xF0F;
  }
  
  public final String getClassName() {
    return this.name.string.replace('/', '.');
  }
  
  public final ObjectType getClassType() {
    ObjectType objectType2 = this.clas;
    if (objectType2 != null)
      return objectType2; 
    String str = this.name.string;
    if (str.charAt(0) == '[') {
      objectType1 = (ObjectType)Type.signatureToType(str);
      this.clas = objectType1;
      return objectType1;
    } 
    ObjectType objectType1 = ClassType.make(objectType1.replace('/', '.'));
    this.clas = objectType1;
    return objectType1;
  }
  
  public final CpoolUtf8 getName() {
    return this.name;
  }
  
  public final String getStringName() {
    return this.name.string;
  }
  
  public int getTag() {
    return 7;
  }
  
  public int hashCode() {
    if (this.hash == 0)
      this.hash = hashCode(this.name); 
    return this.hash;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter, int paramInt) {
    if (paramInt == 1) {
      paramClassTypeWriter.print("Class ");
    } else if (paramInt > 1) {
      paramClassTypeWriter.print("Class name: ");
      paramClassTypeWriter.printOptionalIndex(this.name);
    } 
    String str = this.name.string;
    paramInt = str.length();
    if (paramInt > 1 && str.charAt(0) == '[') {
      Type.printSignature(str, 0, paramInt, paramClassTypeWriter);
      return;
    } 
    paramClassTypeWriter.print(str.replace('/', '.'));
  }
  
  void write(DataOutputStream paramDataOutputStream) throws IOException {
    paramDataOutputStream.writeByte(7);
    paramDataOutputStream.writeShort(this.name.index);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolClass.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */