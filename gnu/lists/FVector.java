package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;

public class FVector extends SimpleVector implements Externalizable, Consumable, Comparable {
  protected static Object[] empty = new Object[0];
  
  public Object[] data;
  
  public FVector() {
    this.data = empty;
  }
  
  public FVector(int paramInt) {
    this.size = paramInt;
    this.data = new Object[paramInt];
  }
  
  public FVector(int paramInt, Object paramObject) {
    Object[] arrayOfObject = new Object[paramInt];
    if (paramObject != null)
      for (int i = 0; i < paramInt; i++)
        arrayOfObject[i] = paramObject;  
    this.data = arrayOfObject;
    this.size = paramInt;
  }
  
  public FVector(List paramList) {
    this.data = new Object[paramList.size()];
    addAll(paramList);
  }
  
  public FVector(Object[] paramArrayOfObject) {
    this.size = paramArrayOfObject.length;
    this.data = paramArrayOfObject;
  }
  
  public static FVector make(Object... paramVarArgs) {
    return new FVector(paramVarArgs);
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    Object[] arrayOfObject = this.data;
    while (true) {
      if (--paramInt2 >= 0) {
        arrayOfObject[paramInt1] = null;
        paramInt1++;
        continue;
      } 
      break;
    } 
  }
  
  public int compareTo(Object paramObject) {
    int i;
    paramObject = paramObject;
    Object[] arrayOfObject1 = this.data;
    Object[] arrayOfObject2 = ((FVector)paramObject).data;
    int k = this.size;
    int m = ((FVector)paramObject).size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    for (int j = 0; j < i; j++) {
      int n = ((Comparable<Comparable>)arrayOfObject1[j]).compareTo((Comparable)arrayOfObject2[j]);
      if (n != 0)
        return n; 
    } 
    return k - m;
  }
  
  public void consume(Consumer paramConsumer) {
    paramConsumer.startElement("#vector");
    int j = this.size;
    for (int i = 0; i < j; i++)
      paramConsumer.writeObject(this.data[i]); 
    paramConsumer.endElement();
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.writeObject(this.data[paramInt]);
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
          paramConsumer.writeObject(this.data[paramInt2]);
          paramInt2++;
          continue;
        } 
        return;
      } 
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof FVector) {
      FVector fVector = (FVector)paramObject;
      int i = this.size;
      if (fVector.data != null && fVector.size == i) {
        paramObject = this.data;
        Object[] arrayOfObject = fVector.data;
        int j = 0;
        while (j < i) {
          if (paramObject[j].equals(arrayOfObject[j])) {
            j++;
            continue;
          } 
          return false;
        } 
        return true;
      } 
    } 
    return false;
  }
  
  public final Object get(int paramInt) {
    if (paramInt >= this.size)
      throw new ArrayIndexOutOfBoundsException(); 
    return this.data[paramInt];
  }
  
  protected Object getBuffer() {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  public int getBufferLength() {
    return this.data.length;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    Object[] arrayOfObject = new Object[j];
    for (int i = 0; i < j; i++)
      arrayOfObject[i] = paramObjectInput.readObject(); 
    this.size = j;
    this.data = arrayOfObject;
  }
  
  public final void setAll(Object paramObject) {
    Object[] arrayOfObject = this.data;
    int i = this.size;
    while (true) {
      if (--i >= 0) {
        arrayOfObject[i] = paramObject;
        continue;
      } 
      break;
    } 
  }
  
  public final Object setBuffer(int paramInt, Object paramObject) {
    Object object = this.data[paramInt];
    this.data[paramInt] = paramObject;
    return object;
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      Object[] arrayOfObject1 = new Object[paramInt];
      Object[] arrayOfObject2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfObject2, 0, arrayOfObject1, 0, paramInt);
      this.data = arrayOfObject1;
    } 
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(this.data, paramInt1, this.data, paramInt2, paramInt3);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeObject(this.data[i]); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/FVector.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */