package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class F64Vector extends SimpleVector implements Externalizable, Comparable {
  protected static double[] empty = new double[0];
  
  double[] data;
  
  public F64Vector() {
    this.data = empty;
  }
  
  public F64Vector(int paramInt) {
    this.data = new double[paramInt];
    this.size = paramInt;
  }
  
  public F64Vector(int paramInt, double paramDouble) {
    double[] arrayOfDouble = new double[paramInt];
    this.data = arrayOfDouble;
    this.size = paramInt;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfDouble[paramInt] = paramDouble;
        continue;
      } 
      break;
    } 
  }
  
  public F64Vector(Sequence paramSequence) {
    this.data = new double[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public F64Vector(double[] paramArrayOfdouble) {
    this.data = paramArrayOfdouble;
    this.size = paramArrayOfdouble.length;
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    while (true) {
      if (--paramInt2 >= 0) {
        this.data[paramInt1] = 0.0D;
        paramInt1++;
        continue;
      } 
      break;
    } 
  }
  
  public int compareTo(Object paramObject) {
    int i;
    paramObject = paramObject;
    double[] arrayOfDouble1 = this.data;
    double[] arrayOfDouble2 = ((F64Vector)paramObject).data;
    int k = this.size;
    int m = ((F64Vector)paramObject).size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    int j;
    for (j = 0; j < i; j++) {
      double d1 = arrayOfDouble1[j];
      double d2 = arrayOfDouble2[j];
      if (d1 != d2)
        return (d1 > d2) ? 1 : -1; 
    } 
    return k - m;
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeDouble(this.data[paramInt]);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      paramInt1 >>>= 1;
      while (true) {
        if (paramInt1 < paramInt2 >>> 1) {
          paramConsumer.writeDouble(this.data[paramInt1]);
          paramInt1++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public final double doubleAt(int paramInt) {
    if (paramInt >= this.size)
      throw new ArrayIndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  public final double doubleAtBuffer(int paramInt) {
    return this.data[paramInt];
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
    return 26;
  }
  
  public String getTag() {
    return "f64";
  }
  
  public final int intAtBuffer(int paramInt) {
    return (int)this.data[paramInt];
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    double[] arrayOfDouble = new double[j];
    for (int i = 0; i < j; i++)
      arrayOfDouble[i] = paramObjectInput.readDouble(); 
    this.data = arrayOfDouble;
    this.size = j;
  }
  
  public final Object setBuffer(int paramInt, Object paramObject) {
    Object object = Convert.toObject(this.data[paramInt]);
    this.data[paramInt] = Convert.toDouble(paramObject);
    return object;
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      double[] arrayOfDouble1 = new double[paramInt];
      double[] arrayOfDouble2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfDouble2, 0, arrayOfDouble1, 0, paramInt);
      this.data = arrayOfDouble1;
    } 
  }
  
  public final void setDoubleAt(int paramInt, double paramDouble) {
    if (paramInt > this.size)
      throw new IndexOutOfBoundsException(); 
    this.data[paramInt] = paramDouble;
  }
  
  public final void setDoubleAtBuffer(int paramInt, double paramDouble) {
    this.data[paramInt] = paramDouble;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeDouble(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/F64Vector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */