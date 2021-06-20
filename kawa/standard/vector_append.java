package kawa.standard;

import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class vector_append extends ProcedureN {
  public static final vector_append vectorAppend = new vector_append("vector-append");
  
  public vector_append(String paramString) {
    super(paramString);
  }
  
  public static FVector apply$V(Object[] paramArrayOfObject) {
    int i = 0;
    int k = paramArrayOfObject.length;
    int j = k;
    while (true) {
      if (--j >= 0) {
        Object object = paramArrayOfObject[j];
        if (object instanceof FVector) {
          i += ((FVector)object).size();
          continue;
        } 
        int n = LList.listLength(object, false);
        if (n < 0)
          throw new WrongType(vectorAppend, j, object, "list or vector"); 
        i += n;
        continue;
      } 
      Object[] arrayOfObject = new Object[i];
      i = 0;
      int m = 0;
      label34: while (m < k) {
        Object object = paramArrayOfObject[m];
        if (object instanceof FVector) {
          object = object;
          int n = object.size();
          j = 0;
          while (j < n) {
            arrayOfObject[i] = object.get(j);
            j++;
            i++;
          } 
          j = i;
          continue;
        } 
        j = i;
        if (object instanceof gnu.lists.Pair)
          while (true) {
            j = i;
            if (object != LList.Empty) {
              object = object;
              arrayOfObject[i] = object.getCar();
              object = object.getCdr();
              i++;
              continue;
            } 
            m++;
            i = j;
            continue label34;
          }  
        continue;
      } 
      return new FVector(arrayOfObject);
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return apply$V(paramArrayOfObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/vector_append.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */