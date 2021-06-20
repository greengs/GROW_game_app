package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class BitVector extends SimpleVector implements Externalizable {
  protected static boolean[] empty = new boolean[0];
  
  boolean[] data;
  
  public BitVector() {
    this.data = empty;
  }
  
  public BitVector(int paramInt) {
    this.data = new boolean[paramInt];
    this.size = paramInt;
  }
  
  public BitVector(int paramInt, boolean paramBoolean) {
    boolean[] arrayOfBoolean = new boolean[paramInt];
    this.data = arrayOfBoolean;
    this.size = paramInt;
    if (paramBoolean)
      while (true) {
        if (--paramInt >= 0) {
          arrayOfBoolean[paramInt] = true;
          continue;
        } 
        break;
      }  
  }
  
  public BitVector(Sequence paramSequence) {
    this.data = new boolean[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public BitVector(boolean[] paramArrayOfboolean) {
    this.data = paramArrayOfboolean;
    this.size = paramArrayOfboolean.length;
  }
  
  public final boolean booleanAt(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  public final boolean booleanAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    while (true) {
      if (--paramInt2 >= 0) {
        this.data[paramInt1] = false;
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
    paramConsumer.writeBoolean(this.data[paramInt]);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      paramInt1 >>>= 1;
      while (true) {
        if (paramInt1 < paramInt2 >>> 1) {
          paramConsumer.writeBoolean(this.data[paramInt1]);
          paramInt1++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public final Object get(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return Convert.toObject(this.data[paramInt]);
  }
  
  protected Object getBuffer() {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt) {
    return Convert.toObject(this.data[paramInt]);
  }
  
  public int getBufferLength() {
    return this.data.length;
  }
  
  public int getElementKind() {
    return 27;
  }
  
  public String getTag() {
    return "b";
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    boolean[] arrayOfBoolean = new boolean[j];
    for (int i = 0; i < j; i++)
      arrayOfBoolean[i] = paramObjectInput.readBoolean(); 
    this.data = arrayOfBoolean;
    this.size = j;
  }
  
  public final void setBooleanAt(int paramInt, boolean paramBoolean) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    this.data[paramInt] = paramBoolean;
  }
  
  public final void setBooleanAtBuffer(int paramInt, boolean paramBoolean) {
    this.data[paramInt] = paramBoolean;
  }
  
  public Object setBuffer(int paramInt, Object paramObject) {
    boolean bool = this.data[paramInt];
    this.data[paramInt] = Convert.toBoolean(paramObject);
    return Convert.toObject(bool);
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      boolean[] arrayOfBoolean1 = new boolean[paramInt];
      boolean[] arrayOfBoolean2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfBoolean2, 0, arrayOfBoolean1, 0, paramInt);
      this.data = arrayOfBoolean1;
    } 
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeBoolean(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/BitVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */