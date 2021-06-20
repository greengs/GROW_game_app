package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public abstract class ByteVector extends SimpleVector implements Externalizable, Comparable {
  protected static byte[] empty = new byte[0];
  
  byte[] data;
  
  public final byte byteAt(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  public final byte byteAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    while (true) {
      if (--paramInt2 >= 0) {
        this.data[paramInt1] = 0;
        paramInt1++;
        continue;
      } 
      break;
    } 
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeInt(intAtBuffer(paramInt));
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      int i = paramInt1 >>> 1;
      int j = paramInt2 >>> 1;
      paramInt1 = j;
      paramInt2 = i;
      if (j > this.size) {
        paramInt1 = this.size;
        paramInt2 = i;
      } 
      while (true) {
        if (paramInt2 < paramInt1) {
          paramConsumer.writeInt(intAtBuffer(paramInt2));
          paramInt2++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  protected Object getBuffer() {
    return this.data;
  }
  
  public int getBufferLength() {
    return this.data.length;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    byte[] arrayOfByte = new byte[j];
    for (int i = 0; i < j; i++)
      arrayOfByte[i] = paramObjectInput.readByte(); 
    this.data = arrayOfByte;
    this.size = j;
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      byte[] arrayOfByte1 = new byte[paramInt];
      byte[] arrayOfByte2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfByte2, 0, arrayOfByte1, 0, paramInt);
      this.data = arrayOfByte1;
    } 
  }
  
  public final void setByteAt(int paramInt, byte paramByte) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    this.data[paramInt] = paramByte;
  }
  
  public final void setByteAtBuffer(int paramInt, byte paramByte) {
    this.data[paramInt] = paramByte;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeByte(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/ByteVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */