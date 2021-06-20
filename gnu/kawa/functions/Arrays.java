package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.FVector;
import gnu.lists.GeneralArray;
import gnu.lists.SimpleVector;
import gnu.mapping.Procedure;
import gnu.math.IntNum;

public class Arrays {
  static final int[] shapeStrides = new int[] { 2, 1 };
  
  static final int[] zeros2 = new int[2];
  
  public static int effectiveIndex(Array paramArray, Procedure paramProcedure, Object[] paramArrayOfObject, int[] paramArrayOfint) throws Throwable {
    Object object = paramProcedure.applyN(paramArrayOfObject);
    if (object instanceof gnu.mapping.Values) {
      object = object;
      int j = 0;
      int i = 0;
      while (true) {
        j = object.nextPos(j);
        if (j != 0) {
          paramArrayOfint[i] = ((Number)object.getPosPrevious(j)).intValue();
          i++;
          continue;
        } 
        break;
      } 
    } else {
      paramArrayOfint[0] = ((Number)object).intValue();
    } 
    return paramArray.getEffectiveIndex(paramArrayOfint);
  }
  
  public static Array make(Array paramArray, Object paramObject) {
    int k = paramArray.getSize(0);
    int[] arrayOfInt2 = new int[k];
    int[] arrayOfInt1 = null;
    int i = 1;
    int j = k;
    while (true) {
      if (--j >= 0) {
        int m = ((Number)paramArray.getRowMajor(j * 2)).intValue();
        int n = ((Number)paramArray.getRowMajor(j * 2 + 1)).intValue() - m;
        arrayOfInt2[j] = n;
        int[] arrayOfInt = arrayOfInt1;
        if (m != 0) {
          arrayOfInt = arrayOfInt1;
          if (arrayOfInt1 == null)
            arrayOfInt = new int[k]; 
          arrayOfInt[j] = m;
        } 
        i *= n;
        arrayOfInt1 = arrayOfInt;
        continue;
      } 
      return GeneralArray.makeSimple(arrayOfInt1, arrayOfInt2, (SimpleVector)new FVector(i, paramObject));
    } 
  }
  
  public static Array makeSimple(Array paramArray, SimpleVector paramSimpleVector) {
    int j = paramArray.getSize(0);
    int[] arrayOfInt2 = new int[j];
    int[] arrayOfInt1 = null;
    int i = j;
    while (true) {
      int k = i - 1;
      if (k >= 0) {
        int m = ((Number)paramArray.getRowMajor(k * 2)).intValue();
        arrayOfInt2[k] = ((Number)paramArray.getRowMajor(k * 2 + 1)).intValue() - m;
        i = k;
        if (m != 0) {
          int[] arrayOfInt = arrayOfInt1;
          if (arrayOfInt1 == null)
            arrayOfInt = new int[j]; 
          arrayOfInt[k] = m;
          i = k;
          arrayOfInt1 = arrayOfInt;
        } 
        continue;
      } 
      return GeneralArray.makeSimple(arrayOfInt1, arrayOfInt2, paramSimpleVector);
    } 
  }
  
  public static Array shape(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    if ((i & 0x1) != 0)
      throw new RuntimeException("shape: not an even number of arguments"); 
    FVector fVector = new FVector(paramArrayOfObject);
    int[] arrayOfInt1 = zeros2;
    int[] arrayOfInt2 = shapeStrides;
    return fVector.transpose(arrayOfInt1, new int[] { i >> 1, 2 }, 0, arrayOfInt2);
  }
  
  public static Array shareArray(Array paramArray1, Array paramArray2, Procedure paramProcedure) throws Throwable {
    int i = paramArray2.getSize(0);
    Object[] arrayOfObject = new Object[i];
    int[] arrayOfInt1 = new int[i];
    int[] arrayOfInt2 = new int[i];
    int j = 0;
    int k = i;
    while (true) {
      int m = k - 1;
      if (m >= 0) {
        Object object = paramArray2.getRowMajor(m * 2);
        arrayOfObject[m] = object;
        k = ((Number)object).intValue();
        arrayOfInt2[m] = k;
        int n = ((Number)paramArray2.getRowMajor(m * 2 + 1)).intValue() - k;
        arrayOfInt1[m] = n;
        k = m;
        if (n <= 0) {
          j = 1;
          k = m;
        } 
        continue;
      } 
      k = paramArray1.rank();
      int[] arrayOfInt3 = new int[i];
      if (j) {
        i = 0;
        continue;
      } 
      int[] arrayOfInt4 = new int[k];
      j = effectiveIndex(paramArray1, paramProcedure, arrayOfObject, arrayOfInt4);
      while (true) {
        k = i - 1;
        i = j;
        if (k >= 0) {
          i = arrayOfInt1[k];
          m = arrayOfInt2[k];
          if (i <= 1) {
            arrayOfInt3[k] = 0;
            i = k;
            continue;
          } 
          Object object = arrayOfObject[k];
          arrayOfObject[k] = IntNum.make(m + 1);
          arrayOfInt3[k] = effectiveIndex(paramArray1, paramProcedure, arrayOfObject, arrayOfInt4) - j;
          arrayOfObject[k] = object;
          i = k;
          continue;
        } 
        return paramArray1.transpose(arrayOfInt2, arrayOfInt1, i, arrayOfInt3);
      } 
      break;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Arrays.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */