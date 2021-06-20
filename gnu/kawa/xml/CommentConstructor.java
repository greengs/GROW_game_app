package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Location;
import gnu.mapping.MethodProc;
import gnu.xml.TextUtils;
import gnu.xml.XMLFilter;

public class CommentConstructor extends MethodProc {
  public static final CommentConstructor commentConstructor = new CommentConstructor();
  
  public void apply(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    null = NodeConstructor.pushNodeContext(paramCallContext);
    try {
      StringBuffer stringBuffer = new StringBuffer();
      String str = Location.UNBOUND;
      int i = 1;
      int j = 0;
      while (true) {
        boolean bool;
        Object object = paramCallContext.getNextArg(str);
        if (object == str) {
          i = stringBuffer.length();
          char[] arrayOfChar = new char[i];
          stringBuffer.getChars(0, i, arrayOfChar, 0);
          null.writeComment(arrayOfChar, 0, i);
          return;
        } 
        if (object instanceof gnu.mapping.Values) {
          object = object;
          bool = false;
          while (true) {
            int k = object.nextPos(bool);
            bool = i;
            if (k != 0) {
              if (i == 0)
                stringBuffer.append(' '); 
              i = 0;
              TextUtils.stringValue(object.getPosPrevious(k), stringBuffer);
              bool = k;
              continue;
            } 
            break;
          } 
        } else {
          if (i == 0)
            stringBuffer.append(' '); 
          bool = false;
          TextUtils.stringValue(object, stringBuffer);
        } 
        j++;
        i = bool;
      } 
    } finally {
      NodeConstructor.popNodeContext(consumer, paramCallContext);
    } 
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/CommentConstructor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */