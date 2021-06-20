package gnu.text;

import java.io.IOException;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public class CompoundFormat extends ReportFormat {
  protected Format[] formats;
  
  protected int length;
  
  public CompoundFormat(Format[] paramArrayOfFormat) {
    this.formats = paramArrayOfFormat;
    this.length = paramArrayOfFormat.length;
  }
  
  public CompoundFormat(Format[] paramArrayOfFormat, int paramInt) {
    this.formats = paramArrayOfFormat;
    this.length = paramInt;
  }
  
  public int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    for (int i = 0; i < this.length; i++) {
      Format format = this.formats[i];
      if (format instanceof ReportFormat) {
        int j = ((ReportFormat)format).format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition);
        paramInt = j;
        if (j < 0)
          return j; 
      } else if (paramInt >= paramArrayOfObject.length) {
        paramWriter.write("#<missing format argument>");
      } else {
        StringBuffer stringBuffer = new StringBuffer();
        format.format(paramArrayOfObject[paramInt], stringBuffer, paramFieldPosition);
        paramWriter.write(stringBuffer.toString());
        paramInt++;
      } 
    } 
    return paramInt;
  }
  
  public final int format(Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    int i;
    for (i = 0; i < this.length; i++) {
      Format format = this.formats[i];
      if (format instanceof ReportFormat) {
        int j = ((ReportFormat)format).format(paramArrayOfObject, paramInt, paramStringBuffer, paramFieldPosition);
        paramInt = j;
        if (j < 0)
          return j; 
      } else {
        format.format(paramArrayOfObject[paramInt], paramStringBuffer, paramFieldPosition);
        paramInt++;
      } 
    } 
    return paramInt;
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition) {
    throw new Error("CompoundFormat.parseObject - not implemented");
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append("CompoundFormat[");
    for (int i = 0; i < this.length; i++) {
      if (i > 0)
        stringBuffer.append(", "); 
      stringBuffer.append(this.formats[i]);
    } 
    stringBuffer.append("]");
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/CompoundFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */