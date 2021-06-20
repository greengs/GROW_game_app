package kawa.standard;

import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.ProcedureN;
import gnu.mapping.WrongType;

public class append extends ProcedureN {
  public static final append append = new append();
  
  static {
    append.setName("append");
  }
  
  public static Object append$V(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length;
    if (i == 0)
      return LList.Empty; 
    Object object = paramArrayOfObject[i - 1];
    i--;
    while (true) {
      i--;
      Object object1 = object;
      if (i >= 0) {
        Object object3 = paramArrayOfObject[i];
        Object object2 = null;
        object1 = null;
        while (object3 instanceof Pair) {
          Pair pair = (Pair)object3;
          object3 = new Pair(pair.getCar(), null);
          if (object2 == null) {
            object1 = object3;
          } else {
            object2.setCdr(object3);
          } 
          object2 = object3;
          object3 = pair.getCdr();
        } 
        if (object3 != LList.Empty)
          throw new WrongType(append, i + 1, paramArrayOfObject[i], "list"); 
        if (object2 != null) {
          object2.setCdr(object);
        } else {
          object1 = object;
        } 
        object = object1;
        continue;
      } 
      return object1;
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return append$V(paramArrayOfObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/append.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */