package gnu.kawa.functions;

import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispRepositionFormat extends ReportFormat {
  boolean absolute;
  
  boolean backwards;
  
  int count;
  
  public LispRepositionFormat(int paramInt, boolean paramBoolean1, boolean paramBoolean2) {
    this.count = paramInt;
    this.backwards = paramBoolean1;
    this.absolute = paramBoolean2;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int j = this.count;
    if (this.absolute) {
      i = 0;
    } else {
      i = 1;
    } 
    int i = getParam(j, i, paramArrayOfObject, paramInt);
    j = i;
    if (!this.absolute) {
      j = i;
      if (this.backwards)
        j = -i; 
      j += paramInt;
    } 
    return (j < 0) ? 0 : ((j > paramArrayOfObject.length) ? paramArrayOfObject.length : j);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispRepositionFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */