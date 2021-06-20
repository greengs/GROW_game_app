package gnu.kawa.functions;

import gnu.math.IntNum;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispPluralFormat extends ReportFormat {
  boolean backup;
  
  boolean y;
  
  public static LispPluralFormat getInstance(boolean paramBoolean1, boolean paramBoolean2) {
    LispPluralFormat lispPluralFormat = new LispPluralFormat();
    lispPluralFormat.backup = paramBoolean1;
    lispPluralFormat.y = paramBoolean2;
    return lispPluralFormat;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    int i = paramInt;
    if (this.backup)
      i = paramInt - 1; 
    int j = i + 1;
    if (paramArrayOfObject[i] != IntNum.one()) {
      paramInt = 1;
    } else {
      paramInt = 0;
    } 
    if (this.y) {
      String str;
      if (paramInt != 0) {
        str = "ies";
      } else {
        str = "y";
      } 
      print(paramWriter, str);
      return j;
    } 
    if (paramInt != 0) {
      paramWriter.write(115);
      return j;
    } 
    return j;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispPluralFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */