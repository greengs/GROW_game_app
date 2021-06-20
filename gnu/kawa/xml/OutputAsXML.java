package gnu.kawa.xml;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.Procedure1;
import gnu.xml.XMLPrinter;
import java.io.Writer;

public class OutputAsXML extends Procedure1 {
  public Object apply1(Object paramObject) {
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    XMLPrinter xMLPrinter = new XMLPrinter((Writer)charArrayOutPort);
    xMLPrinter.writeObject(paramObject);
    xMLPrinter.flush();
    return new FString(charArrayOutPort.toCharArray());
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/OutputAsXML.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */