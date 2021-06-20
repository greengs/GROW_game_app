package gnu.kawa.functions;

import gnu.mapping.OutPort;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispTabulateFormat extends ReportFormat {
  int colinc;
  
  int colnum;
  
  int padChar;
  
  boolean relative;
  
  public LispTabulateFormat(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean) {
    this.colnum = paramInt1;
    this.colinc = paramInt2;
    this.relative = paramBoolean;
    this.padChar = paramInt3;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int j = getParam(this.colnum, 1, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.colnum == -1610612736)
      i = paramInt + 1; 
    int k = getParam(this.colinc, 1, paramArrayOfObject, i);
    paramInt = i;
    if (this.colinc == -1610612736)
      paramInt = i + 1; 
    char c = getParam(this.padChar, ' ', paramArrayOfObject, paramInt);
    i = paramInt;
    if (this.padChar == -1610612736)
      i = paramInt + 1; 
    paramInt = -1;
    if (paramWriter instanceof OutPort)
      paramInt = ((OutPort)paramWriter).getColumnNumber(); 
    if (paramInt >= 0) {
      if (!this.relative) {
        if (paramInt < j) {
          paramInt = j - paramInt;
        } else if (k <= 0) {
          paramInt = 0;
        } else {
          paramInt = k - (paramInt - j) % k;
        } 
      } else {
        paramInt = j + k - (paramInt + j) % k;
      } 
    } else if (this.relative) {
      paramInt = j;
    } else {
      paramInt = 2;
    } 
    while (true) {
      if (--paramInt >= 0) {
        paramWriter.write(c);
        continue;
      } 
      return i;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispTabulateFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */