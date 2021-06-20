package gnu.bytecode;

import java.io.DataOutputStream;
import java.io.IOException;

public abstract class CpoolEntry {
  int hash;
  
  public int index;
  
  CpoolEntry next;
  
  protected CpoolEntry() {}
  
  public CpoolEntry(ConstantPool paramConstantPool, int paramInt) {
    this.hash = paramInt;
    if (paramConstantPool.locked)
      throw new Error("adding new entry to locked contant pool"); 
    paramInt = paramConstantPool.count + 1;
    paramConstantPool.count = paramInt;
    this.index = paramInt;
    if (paramConstantPool.pool == null) {
      paramConstantPool.pool = new CpoolEntry[60];
    } else if (this.index >= paramConstantPool.pool.length) {
      int i = paramConstantPool.pool.length;
      CpoolEntry[] arrayOfCpoolEntry = new CpoolEntry[paramConstantPool.pool.length * 2];
      for (paramInt = 0; paramInt < i; paramInt++)
        arrayOfCpoolEntry[paramInt] = paramConstantPool.pool[paramInt]; 
      paramConstantPool.pool = arrayOfCpoolEntry;
    } 
    if (paramConstantPool.hashTab == null || this.index >= 0.6D * paramConstantPool.hashTab.length)
      paramConstantPool.rehash(); 
    paramConstantPool.pool[this.index] = this;
    add_hashed(paramConstantPool);
  }
  
  void add_hashed(ConstantPool paramConstantPool) {
    CpoolEntry[] arrayOfCpoolEntry = paramConstantPool.hashTab;
    int i = (this.hash & Integer.MAX_VALUE) % arrayOfCpoolEntry.length;
    this.next = arrayOfCpoolEntry[i];
    arrayOfCpoolEntry[i] = this;
  }
  
  public int getIndex() {
    return this.index;
  }
  
  public abstract int getTag();
  
  public int hashCode() {
    return this.hash;
  }
  
  public abstract void print(ClassTypeWriter paramClassTypeWriter, int paramInt);
  
  abstract void write(DataOutputStream paramDataOutputStream) throws IOException;
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/CpoolEntry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */