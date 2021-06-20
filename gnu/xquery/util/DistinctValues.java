package gnu.xquery.util;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;

public class DistinctValues {
  public static void distinctValues$X(Object paramObject, NamedCollator paramNamedCollator, CallContext paramCallContext) {
    Values.writeValues(paramObject, (Consumer)new DistinctValuesConsumer(paramNamedCollator, paramCallContext.consumer));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/DistinctValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */