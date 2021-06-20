package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;

class LispNewlineFormat extends ReportFormat {
  static final String line_separator = System.getProperty("line.separator", "\n");
  
  int count;
  
  int kind;
  
  public static LispNewlineFormat getInstance(int paramInt1, int paramInt2) {
    LispNewlineFormat lispNewlineFormat = new LispNewlineFormat();
    lispNewlineFormat.count = paramInt1;
    lispNewlineFormat.kind = paramInt2;
    return lispNewlineFormat;
  }
  
  public static void printNewline(int paramInt, Writer paramWriter) throws IOException {
    if (paramWriter instanceof OutPort && paramInt != 76) {
      ((OutPort)paramWriter).writeBreak(paramInt);
      return;
    } 
    if (paramWriter instanceof PrintWriter) {
      ((PrintWriter)paramWriter).println();
      return;
    } 
    paramWriter.write(line_separator);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int k = getParam(this.count, 1, paramArrayOfObject, paramInt);
    int j = k;
    int i = paramInt;
    if (this.count == -1610612736) {
      i = paramInt + 1;
      j = k;
    } 
    while (true) {
      if (--j >= 0) {
        printNewline(this.kind, paramWriter);
        continue;
      } 
      return i;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispNewlineFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */