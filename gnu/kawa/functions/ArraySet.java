package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;

public class ArraySet extends ProcedureN {
  public static final ArraySet arraySet = new ArraySet();
  
  public static void arraySet(Array paramArray, Sequence paramSequence, Object paramObject) {
    int j = paramSequence.size();
    int[] arrayOfInt = new int[j];
    for (int i = 0; i < j; i++)
      arrayOfInt[i] = ((Number)paramSequence.get(i)).intValue(); 
    paramArray.set(arrayOfInt, paramObject);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    if (paramObject2 instanceof Sequence) {
      arraySet((Array)paramObject1, (Sequence)paramObject2, paramObject3);
      return Values.empty;
    } 
    return super.apply3(paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    Array array = (Array)paramArrayOfObject[0];
    if (paramArrayOfObject.length == 3) {
      Object object = paramArrayOfObject[1];
      if (object instanceof Sequence) {
        arraySet(array, (Sequence)object, paramArrayOfObject[2]);
        return Values.empty;
      } 
    } 
    int j = paramArrayOfObject.length - 2;
    int[] arrayOfInt = new int[j];
    int i = j;
    while (true) {
      if (--i >= 0) {
        arrayOfInt[i] = ((Number)paramArrayOfObject[i + 1]).intValue();
        continue;
      } 
      array.set(arrayOfInt, paramArrayOfObject[j + 1]);
      return Values.empty;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ArraySet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */