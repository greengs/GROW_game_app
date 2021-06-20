package gnu.xquery.util;

import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class ItemAt extends Procedure2 {
  public static final ItemAt itemAt = new ItemAt();
  
  public static Object itemAt(Object paramObject, int paramInt) {
    if (paramObject instanceof Values) {
      paramObject = paramObject;
      return paramObject.isEmpty() ? Values.empty : paramObject.get(paramInt - 1);
    } 
    if (paramInt != 1)
      throw new IndexOutOfBoundsException(); 
    return paramObject;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    return itemAt(paramObject1, ((Number)paramObject2).intValue());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/ItemAt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */