package gnu.kawa.functions;

import gnu.text.PadFormat;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

class LispObjectFormat extends ReportFormat {
  ReportFormat base;
  
  int colInc;
  
  int minPad;
  
  int minWidth;
  
  int padChar;
  
  int where;
  
  public LispObjectFormat(ReportFormat paramReportFormat, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    this.base = paramReportFormat;
    this.minWidth = paramInt1;
    this.colInc = paramInt2;
    this.minPad = paramInt3;
    this.padChar = paramInt4;
    this.where = paramInt5;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int j = getParam(this.minWidth, 0, paramArrayOfObject, paramInt);
    int i = paramInt;
    if (this.minWidth == -1610612736)
      i = paramInt + 1; 
    int k = getParam(this.colInc, 1, paramArrayOfObject, i);
    paramInt = i;
    if (this.colInc == -1610612736)
      paramInt = i + 1; 
    int m = getParam(this.minPad, 0, paramArrayOfObject, paramInt);
    i = paramInt;
    if (this.minPad == -1610612736)
      i = paramInt + 1; 
    char c = getParam(this.padChar, ' ', paramArrayOfObject, i);
    paramInt = i;
    if (this.padChar == -1610612736)
      paramInt = i + 1; 
    return PadFormat.format((Format)this.base, paramArrayOfObject, paramInt, paramWriter, c, j, k, m, this.where, paramFieldPosition);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispObjectFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */