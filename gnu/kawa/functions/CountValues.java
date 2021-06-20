package gnu.kawa.functions;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure1;
import gnu.mapping.Values;
import gnu.math.IntNum;

public class CountValues extends Procedure1 {
  public static final CountValues countValues = new CountValues();
  
  public static int countValues(Object paramObject) {
    return (paramObject instanceof Values) ? ((Values)paramObject).size() : 1;
  }
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    consumer.writeInt(countValues(object));
  }
  
  public Object apply1(Object paramObject) {
    return IntNum.make(countValues(paramObject));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CountValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */