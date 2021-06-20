package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public class ExceptionsAttr extends Attribute {
  short[] exception_table;
  
  ClassType[] exceptions;
  
  public ExceptionsAttr(Method paramMethod) {
    super("Exceptions");
    addToFrontOf(paramMethod);
  }
  
  public void assignConstants(ClassType paramClassType) {
    super.assignConstants(paramClassType);
    ConstantPool constantPool = paramClassType.getConstants();
    int i = this.exceptions.length;
    this.exception_table = new short[i];
    while (--i >= 0) {
      this.exception_table[i] = (short)(constantPool.addClass(this.exceptions[i])).index;
      i--;
    } 
  }
  
  public final ClassType[] getExceptions() {
    return this.exceptions;
  }
  
  public final int getLength() {
    if (this.exceptions == null) {
      byte b = 0;
      return b * 2 + 2;
    } 
    int i = this.exceptions.length;
    return i * 2 + 2;
  }
  
  public void print(ClassTypeWriter paramClassTypeWriter) {
    paramClassTypeWriter.print("Attribute \"");
    paramClassTypeWriter.print(getName());
    paramClassTypeWriter.print("\", length:");
    paramClassTypeWriter.print(getLength());
    paramClassTypeWriter.print(", count: ");
    int j = this.exceptions.length;
    paramClassTypeWriter.println(j);
    for (int i = 0; i < j; i++) {
      int k = this.exception_table[i] & 0xFFFF;
      paramClassTypeWriter.print("  ");
      paramClassTypeWriter.printOptionalIndex(k);
      paramClassTypeWriter.printConstantTersely(k, 7);
      paramClassTypeWriter.println();
    } 
  }
  
  public void setExceptions(ClassType[] paramArrayOfClassType) {
    this.exceptions = paramArrayOfClassType;
  }
  
  public void setExceptions(short[] paramArrayOfshort, ClassType paramClassType) {
    this.exception_table = paramArrayOfshort;
    this.exceptions = new ClassType[paramArrayOfshort.length];
    ConstantPool constantPool = paramClassType.getConstants();
    for (int i = paramArrayOfshort.length - 1; i >= 0; i--)
      this.exceptions[i] = (ClassType)((CpoolClass)constantPool.getPoolEntry(paramArrayOfshort[i])).getClassType(); 
  }
  
  public void write(DataOutputStream paramDataOutputStream) throws IOException {
    int j = this.exceptions.length;
    paramDataOutputStream.writeShort(j);
    for (int i = 0; i < j; i++)
      paramDataOutputStream.writeShort(this.exception_table[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ExceptionsAttr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */