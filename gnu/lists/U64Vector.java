package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class U64Vector extends SimpleVector implements Externalizable, Comparable {
  long[] data;
  
  public U64Vector() {
    this.data = S64Vector.empty;
  }
  
  public U64Vector(int paramInt) {
    this.data = new long[paramInt];
    this.size = paramInt;
  }
  
  public U64Vector(int paramInt, long paramLong) {
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
  
  public U64Vector(Sequence paramSequence) {
    this.data = new long[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public U64Vector(long[] paramArrayOflong) {
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
    int i;
    paramObject = paramObject;
    long[] arrayOfLong1 = this.data;
    long[] arrayOfLong2 = ((U64Vector)paramObject).data;
    int k = this.size;
    int m = ((U64Vector)paramObject).size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    for (int j = 0; j < i; j++) {
      long l1 = arrayOfLong1[j];
      long l2 = arrayOfLong2[j];
      if (l1 != l2)
        return ((Long.MIN_VALUE ^ l1) > (Long.MIN_VALUE ^ l2)) ? 1 : -1; 
    } 
    return k - m;
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
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  protected Object getBuffer() {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt) {
    return Convert.toObjectUnsigned(this.data[paramInt]);
  }
  
  public int getBufferLength() {
    return this.data.length;
  }
  
  public int getElementKind() {
    return 23;
  }
  
  public String getTag() {
    return "u64";
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
    this.data[paramInt] = Convert.toLongUnsigned(paramObject);
    return Convert.toObjectUnsigned(l);
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/U64Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */