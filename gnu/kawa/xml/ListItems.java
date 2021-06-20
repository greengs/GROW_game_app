package gnu.kawa.xml;

import gnu.lists.AbstractSequence;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import java.util.Iterator;
import java.util.List;

public class ListItems extends MethodProc {
  public static ListItems listItems = new ListItems();
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    List list = (List)object;
    if (object instanceof AbstractSequence) {
      ((AbstractSequence)object).consumePosRange(0, -1, consumer);
      return;
    } 
    Iterator iterator = list.iterator();
    while (true) {
      if (iterator.hasNext()) {
        Values.writeValues(iterator.next(), consumer);
        continue;
      } 
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/ListItems.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */