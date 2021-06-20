package gnu.lists;

public class GeneralArray extends AbstractSequence implements Array {
  static final int[] zeros = new int[8];
  
  SimpleVector base;
  
  int[] dimensions;
  
  int[] lowBounds;
  
  int offset;
  
  boolean simple = true;
  
  int[] strides;
  
  public GeneralArray() {}
  
  public GeneralArray(int[] paramArrayOfint) {
    int i = 1;
    int j = paramArrayOfint.length;
    if (j <= zeros.length) {
      this.lowBounds = zeros;
    } else {
      this.lowBounds = new int[j];
    } 
    int[] arrayOfInt = new int[j];
    while (true) {
      if (--j >= 0) {
        arrayOfInt[j] = i;
        i *= paramArrayOfint[j];
        continue;
      } 
      this.base = new FVector(i);
      this.dimensions = paramArrayOfint;
      this.offset = 0;
      return;
    } 
  }
  
  public static Array makeSimple(int[] paramArrayOfint1, int[] paramArrayOfint2, SimpleVector paramSimpleVector) {
    int j = paramArrayOfint2.length;
    int[] arrayOfInt1 = paramArrayOfint1;
    if (paramArrayOfint1 == null) {
      paramArrayOfint1 = zeros;
      arrayOfInt1 = paramArrayOfint1;
      if (j > paramArrayOfint1.length)
        arrayOfInt1 = new int[j]; 
    } 
    if (j == 1 && arrayOfInt1[0] == 0)
      return paramSimpleVector; 
    GeneralArray generalArray = new GeneralArray();
    int[] arrayOfInt2 = new int[j];
    int i = 1;
    while (true) {
      if (--j >= 0) {
        arrayOfInt2[j] = i;
        i *= paramArrayOfint2[j];
        continue;
      } 
      generalArray.strides = arrayOfInt2;
      generalArray.dimensions = paramArrayOfint2;
      generalArray.lowBounds = arrayOfInt1;
      generalArray.base = paramSimpleVector;
      return generalArray;
    } 
  }
  
  public static void toString(Array paramArray, StringBuffer paramStringBuffer) {
    paramStringBuffer.append("#<array");
    int j = paramArray.rank();
    for (int i = 0; i < j; i++) {
      paramStringBuffer.append(' ');
      int k = paramArray.getLowBound(i);
      int m = paramArray.getSize(i);
      if (k != 0) {
        paramStringBuffer.append(k);
        paramStringBuffer.append(':');
      } 
      paramStringBuffer.append(k + m);
    } 
    paramStringBuffer.append('>');
  }
  
  public int createPos(int paramInt, boolean paramBoolean) {
    int i = this.offset;
    int j = this.dimensions.length;
    while (true) {
      int k = paramInt;
      if (--j >= 0) {
        int m = this.dimensions[j];
        paramInt = k / m;
        i += this.strides[j] * k % m;
        continue;
      } 
      if (paramBoolean) {
        paramInt = 1;
        return paramInt | i << 1;
      } 
      paramInt = 0;
      return paramInt | i << 1;
    } 
  }
  
  public Object get(int paramInt) {
    return getRowMajor(paramInt);
  }
  
  public Object get(int[] paramArrayOfint) {
    return this.base.get(getEffectiveIndex(paramArrayOfint));
  }
  
  public int getEffectiveIndex(int[] paramArrayOfint) {
    int i = this.offset;
    int j = this.dimensions.length;
    while (true) {
      if (--j >= 0) {
        int k = paramArrayOfint[j];
        int m = this.lowBounds[j];
        if (k >= m) {
          k -= m;
          if (k < this.dimensions[j]) {
            i += this.strides[j] * k;
            continue;
          } 
        } 
        throw new IndexOutOfBoundsException();
      } 
      return i;
    } 
  }
  
  public int getLowBound(int paramInt) {
    return this.lowBounds[paramInt];
  }
  
  public Object getRowMajor(int paramInt) {
    if (this.simple)
      return this.base.get(paramInt); 
    int i = this.offset;
    int j = this.dimensions.length;
    while (true) {
      int k = paramInt;
      if (--j >= 0) {
        int m = this.dimensions[j];
        paramInt = k / m;
        i += this.strides[j] * k % m;
        continue;
      } 
      return this.base.get(i);
    } 
  }
  
  public int getSize(int paramInt) {
    return this.dimensions[paramInt];
  }
  
  public int rank() {
    return this.dimensions.length;
  }
  
  public Object set(int[] paramArrayOfint, Object paramObject) {
    return this.base.set(getEffectiveIndex(paramArrayOfint), paramObject);
  }
  
  public int size() {
    int i = 1;
    int j = this.dimensions.length;
    while (true) {
      if (--j >= 0) {
        i *= this.dimensions[j];
        continue;
      } 
      return i;
    } 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    toString(this, stringBuffer);
    return stringBuffer.toString();
  }
  
  public Array transpose(int[] paramArrayOfint1, int[] paramArrayOfint2, int paramInt, int[] paramArrayOfint3) {
    if (paramArrayOfint2.length == 1 && paramArrayOfint1[0] == 0) {
      GeneralArray1 generalArray1 = new GeneralArray1();
      generalArray1.offset = paramInt;
      generalArray1.strides = paramArrayOfint3;
      generalArray1.dimensions = paramArrayOfint2;
      generalArray1.lowBounds = paramArrayOfint1;
      generalArray1.base = this.base;
      generalArray1.simple = false;
      return generalArray1;
    } 
    GeneralArray generalArray = new GeneralArray();
    generalArray.offset = paramInt;
    generalArray.strides = paramArrayOfint3;
    generalArray.dimensions = paramArrayOfint2;
    generalArray.lowBounds = paramArrayOfint1;
    generalArray.base = this.base;
    generalArray.simple = false;
    return generalArray;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/GeneralArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */