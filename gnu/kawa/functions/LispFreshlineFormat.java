package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispFreshlineFormat extends ReportFormat {
  int count;
  
  public LispFreshlineFormat(int paramInt) {
    this.count = paramInt;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int j = getParam(this.count, 1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.count == -1610612736)
      i = paramInt + 1; 
    if (j > 0) {
      paramInt = j;
      if (paramWriter instanceof OutPort) {
        ((OutPort)paramWriter).freshLine();
        paramInt = j - 1;
      } 
      while (true) {
        if (--paramInt >= 0) {
          paramWriter.write(10);
          continue;
        } 
        break;
      } 
    } 
    return i;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispFreshlineFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */