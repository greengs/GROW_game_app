package gnu.kawa.functions;

import gnu.math.DFloNum;
import gnu.math.IntNum;
import gnu.math.Numeric;
import gnu.text.Char;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;

class LispEscapeFormat extends ReportFormat {
  public static final int ESCAPE_ALL = 242;
  
  public static final int ESCAPE_NORMAL = 241;
  
  public static final LispEscapeFormat alwaysTerminate = new LispEscapeFormat(0, -1073741824);
  
  boolean escapeAll;
  
  int param1;
  
  int param2;
  
  int param3;
  
  public LispEscapeFormat(int paramInt1, int paramInt2) {
    this.param1 = paramInt1;
    this.param2 = paramInt2;
    this.param3 = -1073741824;
  }
  
  public LispEscapeFormat(int paramInt1, int paramInt2, int paramInt3) {
    this.param1 = paramInt1;
    this.param2 = paramInt2;
    this.param3 = paramInt3;
  }
  
  static Numeric getParam(int paramInt1, Object[] paramArrayOfObject, int paramInt2) {
    if (paramInt1 == -1342177280)
      return (Numeric)IntNum.make(paramArrayOfObject.length - paramInt2); 
    if (paramInt1 == -1610612736) {
      Object object = paramArrayOfObject[paramInt2];
      return (Numeric)((object instanceof Numeric) ? object : ((object instanceof Number) ? ((object instanceof Float || object instanceof Double) ? new DFloNum(((Number)object).doubleValue()) : IntNum.make(((Number)object).longValue())) : ((object instanceof Char) ? new IntNum(((Char)object).intValue()) : ((object instanceof Character) ? new IntNum(((Character)object).charValue()) : new DFloNum(Double.NaN)))));
    } 
    return (Numeric)IntNum.make(paramInt1);
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    boolean bool2 = true;
    boolean bool = true;
    boolean bool1 = false;
    if (this.param1 == -1073741824) {
      if (paramInt != paramArrayOfObject.length)
        bool = false; 
    } else if (this.param2 == -1073741824 && this.param1 == 0) {
      bool = true;
    } else {
      Numeric numeric = getParam(this.param1, paramArrayOfObject, paramInt);
      int i = paramInt;
      if (this.param1 == -1610612736)
        i = paramInt + 1; 
      if (this.param2 == -1073741824) {
        bool = numeric.isZero();
        paramInt = i;
      } else {
        Numeric numeric1 = getParam(this.param2, paramArrayOfObject, i);
        paramInt = i;
        if (this.param2 == -1610612736)
          paramInt = i + 1; 
        if (this.param3 == -1073741824) {
          bool = numeric.equals(numeric1);
        } else {
          Numeric numeric2 = getParam(this.param3, paramArrayOfObject, paramInt);
          i = paramInt;
          if (this.param3 == -1610612736)
            i = paramInt + 1; 
          if (numeric1.geq(numeric) && numeric2.geq(numeric1)) {
            bool = bool2;
          } else {
            bool = false;
          } 
          paramInt = i;
        } 
      } 
    } 
    if (!bool) {
      boolean bool3 = bool1;
      return result(bool3, paramInt);
    } 
    if (this.escapeAll) {
      char c1 = 'ò';
      return result(c1, paramInt);
    } 
    char c = 'ñ';
    return result(c, paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/LispEscapeFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */