package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class S16Vector extends SimpleVector implements Externalizable, Comparable {
  protected static short[] empty = new short[0];
  
  short[] data;
  
  public S16Vector() {
    this.data = empty;
  }
  
  public S16Vector(int paramInt) {
    this.data = new short[paramInt];
    this.size = paramInt;
  }
  
  public S16Vector(int paramInt, short paramShort) {
    short[] arrayOfShort = new short[paramInt];
    this.data = arrayOfShort;
    this.size = paramInt;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfShort[paramInt] = paramShort;
        continue;
      } 
      break;
    } 
  }
  
  public S16Vector(Sequence paramSequence) {
    this.data = new short[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public S16Vector(short[] paramArrayOfshort) {
    this.data = paramArrayOfshort;
    this.size = paramArrayOfshort.length;
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
  
  public int compareTo(Object paramObject) {
    return compareToInt(this, (S16Vector)paramObject);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeInt(this.data[paramInt]);
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
          paramConsumer.writeInt(this.data[paramInt2]);
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
    return 20;
  }
  
  public String getTag() {
    return "s16";
  }
  
  public final int intAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    short[] arrayOfShort = new short[j];
    for (int i = 0; i < j; i++)
      arrayOfShort[i] = paramObjectInput.readShort(); 
    this.data = arrayOfShort;
    this.size = j;
  }
  
  public Object setBuffer(int paramInt, Object paramObject) {
    short s = this.data[paramInt];
    this.data[paramInt] = Convert.toShort(paramObject);
    return Convert.toObject(s);
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      short[] arrayOfShort1 = new short[paramInt];
      short[] arrayOfShort2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfShort2, 0, arrayOfShort1, 0, paramInt);
      this.data = arrayOfShort1;
    } 
  }
  
  public final void setShortAt(int paramInt, short paramShort) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    this.data[paramInt] = paramShort;
  }
  
  public final void setShortAtBuffer(int paramInt, short paramShort) {
    this.data[paramInt] = paramShort;
  }
  
  public final short shortAt(int paramInt) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  public final short shortAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeShort(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/S16Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */