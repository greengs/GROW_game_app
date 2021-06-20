package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.Iterator;

public class IteratorItems extends MethodProc {
  public static IteratorItems iteratorItems = new IteratorItems();
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    Iterator iterator = (Iterator)object;
    while (iterator.hasNext())
      Values.writeValues(iterator.next(), consumer); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/IteratorItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */