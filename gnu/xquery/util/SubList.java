package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.lists.Sequence;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class SubList extends MethodProc {
  public static final SubList subList = new SubList();
  
  public static void subList(Object paramObject, double paramDouble1, double paramDouble2, Consumer paramConsumer) {
    if (paramObject instanceof gnu.mapping.Values) {
      paramObject = paramObject;
      int j = 0;
      int i = 0;
      while (true) {
        if (++j < paramDouble1) {
          int n = paramObject.nextDataIndex(i);
          i = n;
          if (n < 0)
            continue; 
          continue;
        } 
        int m = i;
        int k = j;
        j = i;
        while (true) {
          if (k < paramDouble2) {
            j = paramObject.nextDataIndex(j);
            if (j >= 0) {
              m = j;
              k++;
              continue;
            } 
          } 
          paramObject.consumeIRange(i, m, paramConsumer);
          return;
        } 
        break;
      } 
    } 
    if (paramDouble1 <= 1.0D && paramDouble2 >= 2.0D) {
      paramConsumer.writeObject(paramObject);
      return;
    } 
  }
  
  public void apply(CallContext paramCallContext) {
    double d1;
    Consumer consumer = paramCallContext.consumer;
    Object object1 = paramCallContext.getNextArg();
    double d2 = Math.round(StringUtils.asDouble(paramCallContext.getNextArg()));
    Object object2 = Sequence.eofValue;
    Object object3 = paramCallContext.getNextArg(object2);
    paramCallContext.lastArg();
    if (object3 != object2) {
      d1 = Math.round(StringUtils.asDouble(object3));
    } else {
      d1 = Double.POSITIVE_INFINITY;
    } 
    subList(object1, d2, d2 + d1, consumer);
  }
  
  public int numArgs() {
    return 12290;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/SubList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */