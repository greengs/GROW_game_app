package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispPrettyFormat extends ReportFormat {
  Format body;
  
  boolean perLine;
  
  String prefix;
  
  boolean seenAt;
  
  Format[] segments;
  
  String suffix;
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    OutPort outPort;
    String str3 = this.prefix;
    String str1 = this.suffix;
    if (paramWriter instanceof OutPort) {
      outPort = (OutPort)paramWriter;
    } else {
      outPort = null;
    } 
    String str2 = str1;
    try {
      String str;
      if (this.seenAt) {
        if (outPort != null) {
          str2 = str1;
          outPort.startLogicalBlock(str3, this.perLine, this.suffix);
        } 
        str2 = str1;
        paramInt = ReportFormat.format(this.body, paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
        str = str1;
      } else {
        String str4 = str[paramInt];
        str2 = str1;
        Object[] arrayOfObject = LispFormat.asArray(str4);
        str = str1;
        if (arrayOfObject == null) {
          str = "";
          str3 = "";
        } 
        if (outPort != null) {
          str2 = str;
          outPort.startLogicalBlock(str3, this.perLine, this.suffix);
        } 
        if (arrayOfObject == null) {
          str2 = str;
          ObjectFormat.format(str4, paramWriter, -1, true);
        } else {
          str2 = str;
          ReportFormat.format(this.body, arrayOfObject, 0, paramWriter, paramFieldPosition);
        } 
        paramInt++;
      } 
      return paramInt;
    } finally {
      if (outPort != null)
        outPort.endLogicalBlock(str2); 
    } 
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("LispPrettyFormat[");
    stringBuffer.append("prefix: \"");
    stringBuffer.append(this.prefix);
    stringBuffer.append("\", suffix: \"");
    stringBuffer.append(this.suffix);
    stringBuffer.append("\", body: ");
    stringBuffer.append(this.body);
    stringBuffer.append("]");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispPrettyFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */