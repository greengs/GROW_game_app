package gnu.kawa.functions;

import gnu.lists.AbstractFormat;
import gnu.lists.Consumer;
import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.ParsePosition;
import kawa.standard.Scheme;

public class ObjectFormat extends ReportFormat {
  private static ObjectFormat plainFormat;
  
  private static ObjectFormat readableFormat;
  
  int maxChars;
  
  boolean readable;
  
  public ObjectFormat(boolean paramBoolean) {
    this.readable = paramBoolean;
    this.maxChars = -1073741824;
  }
  
  public ObjectFormat(boolean paramBoolean, int paramInt) {
    this.readable = paramBoolean;
    this.maxChars = paramInt;
  }
  
  public static int format(Object[] paramArrayOfObject, int paramInt1, Writer paramWriter, int paramInt2, boolean paramBoolean) throws IOException {
    if (paramInt1 >= paramArrayOfObject.length) {
      str = "#<missing format argument>";
      paramInt1--;
      paramBoolean = false;
      paramInt2 = -1;
      format(str, paramWriter, paramInt2, paramBoolean);
      return paramInt1 + 1;
    } 
    String str = str[paramInt1];
    format(str, paramWriter, paramInt2, paramBoolean);
    return paramInt1 + 1;
  }
  
  public static boolean format(Object paramObject, Writer paramWriter, int paramInt, boolean paramBoolean) throws IOException {
    OutPort outPort1;
    if (paramInt < 0 && paramWriter instanceof OutPort) {
      print(paramObject, (OutPort)paramWriter, paramBoolean);
      return true;
    } 
    if (paramInt < 0 && paramWriter instanceof CharArrayWriter) {
      outPort1 = new OutPort(paramWriter);
      print(paramObject, outPort1, paramBoolean);
      outPort1.close();
      return true;
    } 
    CharArrayWriter charArrayWriter = new CharArrayWriter();
    OutPort outPort2 = new OutPort(charArrayWriter);
    print(paramObject, outPort2, paramBoolean);
    outPort2.close();
    int i = charArrayWriter.size();
    if (paramInt < 0 || i <= paramInt) {
      charArrayWriter.writeTo((Writer)outPort1);
      return true;
    } 
    outPort1.write(charArrayWriter.toCharArray(), 0, paramInt);
    return false;
  }
  
  public static ObjectFormat getInstance(boolean paramBoolean) {
    if (paramBoolean) {
      if (readableFormat == null)
        readableFormat = new ObjectFormat(true); 
      return readableFormat;
    } 
    if (plainFormat == null)
      plainFormat = new ObjectFormat(false); 
    return plainFormat;
  }
  
  private static void print(Object paramObject, OutPort paramOutPort, boolean paramBoolean) {
    boolean bool = paramOutPort.printReadable;
    AbstractFormat abstractFormat = paramOutPort.objectFormat;
    try {
      AbstractFormat abstractFormat1;
      paramOutPort.printReadable = paramBoolean;
      if (paramBoolean) {
        abstractFormat1 = Scheme.writeFormat;
      } else {
        abstractFormat1 = Scheme.displayFormat;
      } 
      paramOutPort.objectFormat = abstractFormat1;
      abstractFormat1.writeObject(paramObject, (Consumer)paramOutPort);
      return;
    } finally {
      paramOutPort.printReadable = bool;
      paramOutPort.objectFormat = abstractFormat;
    } 
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int j = getParam(this.maxChars, -1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.maxChars == -1610612736)
      i = paramInt + 1; 
    return format(paramArrayOfObject, i, paramWriter, j, this.readable);
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition) {
    throw new RuntimeException("ObjectFormat.parseObject - not implemented");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/ObjectFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */