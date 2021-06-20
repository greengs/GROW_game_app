package gnu.kawa.functions;

import gnu.lists.Array;
import gnu.lists.Sequence;
import gnu.mapping.ProcedureN;

public class ArrayRef extends ProcedureN {
  public static final ArrayRef arrayRef = new ArrayRef();
  
  public static Object arrayRef(Array paramArray, Sequence paramSequence) {
    int j = paramSequence.size();
    int[] arrayOfInt = new int[j];
    for (int i = 0; i < j; i++)
      arrayOfInt[i] = ((Number)paramSequence.get(i)).intValue(); 
    return paramArray.get(arrayOfInt);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return (paramObject2 instanceof Sequence) ? arrayRef((Array)paramObject1, (Sequence)paramObject2) : super.apply2(paramObject1, paramObject2);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    Array array = (Array)paramArrayOfObject[0];
    if (paramArrayOfObject.length == 2) {
      Object object = paramArrayOfObject[1];
      if (object instanceof Sequence)
        return arrayRef(array, (Sequence)object); 
    } 
    int[] arrayOfInt = new int[paramArrayOfObject.length - 1];
    int i = paramArrayOfObject.length - 1;
    while (true) {
      if (--i >= 0) {
        arrayOfInt[i] = ((Number)paramArrayOfObject[i + 1]).intValue();
        continue;
      } 
      return array.get(arrayOfInt);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ArrayRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */