package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class S64Vector extends SimpleVector implements Externalizable, Comparable {
  protected static long[] empty = new long[0];
  
  long[] data;
  
  public S64Vector() {
    this.data = empty;
  }
  
  public S64Vector(int paramInt) {
    this.data = new long[paramInt];
    this.size = paramInt;
  }
  
  public S64Vector(int paramInt, long paramLong) {
    long[] arrayOfLong = new long[paramInt];
    this.data = arrayOfLong;
    this.size = paramInt;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfLong[paramInt] = paramLong;
        continue;
      } 
      break;
    } 
  }
  
  public S64Vector(Sequence paramSequence) {
    this.data = new long[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public S64Vector(long[] paramArrayOflong) {
    this.data = paramArrayOflong;
    this.size = paramArrayOflong.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    while (true) {
      if (--paramInt2 >= 0) {
        this.data[paramInt1] = 0L;
        paramInt1++;
        continue;
      } 
      break;
    } 
  }
  
  public int compareTo(Object paramObject) {
    return compareToLong(this, (S64Vector)paramObject);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeLong(this.data[paramInt]);
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
          paramConsumer.writeLong(this.data[paramInt2]);
          paramInt2++;
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
    return 24;
  }
  
  public String getTag() {
    return "s64";
  }
  
  public final int intAtBuffer(int paramInt) {
    return (int)this.data[paramInt];
  }
  
  public final long longAt(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  public final long longAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    long[] arrayOfLong = new long[j];
    for (int i = 0; i < j; i++)
      arrayOfLong[i] = paramObjectInput.readLong(); 
    this.data = arrayOfLong;
    this.size = j;
  }
  
  public Object setBuffer(int paramInt, Object paramObject) {
    long l = this.data[paramInt];
    this.data[paramInt] = Convert.toLong(paramObject);
    return Convert.toObject(l);
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      long[] arrayOfLong1 = new long[paramInt];
      long[] arrayOfLong2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfLong2, 0, arrayOfLong1, 0, paramInt);
      this.data = arrayOfLong1;
    } 
  }
  
  public final void setLongAt(int paramInt, long paramLong) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    this.data[paramInt] = paramLong;
  }
  
  public final void setLongAtBuffer(int paramInt, long paramLong) {
    this.data[paramInt] = paramLong;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeLong(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/S64Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */