package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class MakeCDATA extends MethodProc {
  public static final MakeCDATA makeCDATA = new MakeCDATA();
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    null = NodeConstructor.pushNodeContext(paramCallContext);
    try {
      StringBuffer stringBuffer = new StringBuffer();
      String str = Location.UNBOUND;
      while (true) {
        Object object = paramCallContext.getNextArg(str);
        if (object == str) {
          int i = stringBuffer.length();
          char[] arrayOfChar = new char[i];
          stringBuffer.getChars(0, i, arrayOfChar, 0);
          null.writeCDATA(arrayOfChar, 0, i);
          return;
        } 
        TextUtils.stringValue(object, stringBuffer);
      } 
    } finally {
      NodeConstructor.popNodeContext(consumer, paramCallContext);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/MakeCDATA.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */