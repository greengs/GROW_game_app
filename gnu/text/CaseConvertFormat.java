package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;

public class CaseConvertFormat extends ReportFormat {
  Format baseFormat;
  
  char code;
  
  public CaseConvertFormat(Format paramFormat, char paramChar) {
    this.baseFormat = paramFormat;
    this.code = paramChar;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    StringBuffer stringBuffer = new StringBuffer(100);
    int i = format(this.baseFormat, paramArrayOfObject, paramInt, stringBuffer, paramFieldPosition);
    int j = stringBuffer.length();
    char c = ' ';
    paramInt = 0;
    while (paramInt < j) {
      char c1 = stringBuffer.charAt(paramInt);
      if (this.code == 'U') {
        c = Character.toUpperCase(c1);
      } else if ((this.code == 'T' && paramInt == 0) || (this.code == 'C' && !Character.isLetterOrDigit(c))) {
        c = Character.toTitleCase(c1);
      } else {
        c = Character.toLowerCase(c1);
      } 
      c1 = c;
      paramWriter.write(c);
      paramInt++;
      c = c1;
    } 
    return i;
  }
  
  public Format getBaseFormat() {
    return this.baseFormat;
  }
  
  public void setBaseFormat(Format paramFormat) {
    this.baseFormat = paramFormat;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/CaseConvertFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */