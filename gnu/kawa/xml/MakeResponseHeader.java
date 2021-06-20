package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;

public class MakeResponseHeader extends MethodProc {
  public static MakeResponseHeader makeResponseHeader = new MakeResponseHeader();
  
  public void apply(CallContext paramCallContext) {
    String str = paramCallContext.getNextArg().toString();
    Object object = paramCallContext.getNextArg();
    paramCallContext.lastArg();
    Consumer consumer = paramCallContext.consumer;
    consumer.startAttribute(str);
    consumer.write(object.toString());
    consumer.endAttribute();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeResponseHeader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */