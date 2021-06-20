package gnu.kawa.xml;

import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;
import gnu.text.Path;
import gnu.xml.XMLPrinter;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

public class WriteTo extends Procedure2 {
  public static final WriteTo writeTo = new WriteTo();
  
  public static final WriteTo writeToIfChanged = new WriteTo();
  
  boolean ifChanged;
  
  static {
    writeToIfChanged.ifChanged = true;
  }
  
  public static void writeTo(Object paramObject, Path paramPath, OutputStream paramOutputStream) throws Throwable {
    OutPort outPort = new OutPort(paramOutputStream, paramPath);
    XMLPrinter xMLPrinter = new XMLPrinter(outPort, false);
    if ("html".equals(paramPath.getExtension()))
      xMLPrinter.setStyle("html"); 
    Values.writeValues(paramObject, (Consumer)xMLPrinter);
    outPort.close();
  }
  
  public static void writeTo(Object paramObject1, Object paramObject2) throws Throwable {
    paramObject2 = Path.valueOf(paramObject2);
    writeTo(paramObject1, (Path)paramObject2, paramObject2.openOutputStream());
  }
  
  public static void writeToIfChanged(Object paramObject1, Object paramObject2) throws Throwable {
    paramObject2 = Path.valueOf(paramObject2);
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    writeTo(paramObject1, (Path)paramObject2, byteArrayOutputStream);
    paramObject1 = byteArrayOutputStream.toByteArray();
    try {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(paramObject2.openInputStream());
      int i = 0;
      while (true) {
        boolean bool;
        int j = bufferedInputStream.read();
        if (i == paramObject1.length) {
          bool = true;
        } else {
          bool = false;
        } 
        if (j < 0) {
          if (bool) {
            bufferedInputStream.close();
            return;
          } 
        } else if (!bool) {
          Object object = paramObject1[i];
          if (object == j) {
            i++;
            continue;
          } 
        } 
        bufferedInputStream.close();
        paramObject2 = new BufferedOutputStream(paramObject2.openOutputStream());
        paramObject2.write((byte[])paramObject1);
        paramObject2.close();
        return;
      } 
    } catch (Throwable throwable) {}
    paramObject2 = new BufferedOutputStream(paramObject2.openOutputStream());
    paramObject2.write((byte[])paramObject1);
    paramObject2.close();
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    if (this.ifChanged) {
      writeToIfChanged(paramObject1, paramObject2.toString());
      return Values.empty;
    } 
    writeTo(paramObject1, paramObject2.toString());
    return Values.empty;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/WriteTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */