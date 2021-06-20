package gnu.text;

import gnu.lists.Consumer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.FieldPosition;
import java.text.Format;
import java.text.ParsePosition;

public abstract class ReportFormat extends Format {
  public static final int PARAM_FROM_COUNT = -1342177280;
  
  public static final int PARAM_FROM_LIST = -1610612736;
  
  public static final int PARAM_UNSPECIFIED = -1073741824;
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    if (paramFormat instanceof ReportFormat)
      return ((ReportFormat)paramFormat).format(paramArrayOfObject, paramInt, paramWriter, paramFieldPosition); 
    StringBuffer stringBuffer = new StringBuffer();
    if (paramFormat instanceof java.text.MessageFormat) {
      paramInt = format(paramFormat, paramArrayOfObject, paramInt, stringBuffer, paramFieldPosition);
      int j = stringBuffer.length();
      arrayOfChar = new char[j];
      stringBuffer.getChars(0, j, arrayOfChar, 0);
      paramWriter.write(arrayOfChar);
      return paramInt;
    } 
    arrayOfChar.format(paramArrayOfObject[paramInt], stringBuffer, paramFieldPosition);
    paramInt++;
    int i = stringBuffer.length();
    char[] arrayOfChar = new char[i];
    stringBuffer.getChars(0, i, arrayOfChar, 0);
    paramWriter.write(arrayOfChar);
    return paramInt;
  }
  
  public static int format(Format paramFormat, Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    if (paramFormat instanceof ReportFormat)
      return ((ReportFormat)paramFormat).format(paramArrayOfObject, paramInt, paramStringBuffer, paramFieldPosition); 
    if (paramFormat instanceof java.text.MessageFormat) {
      int i = paramArrayOfObject.length - paramInt;
      if (paramInt > 0) {
        Object[] arrayOfObject = new Object[paramArrayOfObject.length - paramInt];
        System.arraycopy(paramArrayOfObject, paramInt, arrayOfObject, 0, arrayOfObject.length);
        paramArrayOfObject = arrayOfObject;
      } 
      paramFormat.format(paramArrayOfObject, paramStringBuffer, paramFieldPosition);
      return paramInt + i;
    } 
    Object object = paramArrayOfObject[paramInt];
    byte b = 1;
    paramFormat.format(object, paramStringBuffer, paramFieldPosition);
    return paramInt + b;
  }
  
  protected static char getParam(int paramInt1, char paramChar, Object[] paramArrayOfObject, int paramInt2) {
    return (char)getParam(paramInt1, paramChar, paramArrayOfObject, paramInt2);
  }
  
  protected static int getParam(int paramInt1, int paramInt2, Object[] paramArrayOfObject, int paramInt3) {
    if (paramInt1 == -1342177280)
      return paramArrayOfObject.length - paramInt3; 
    if (paramInt1 == -1610612736) {
      int j = paramInt2;
      return (paramArrayOfObject != null) ? getParam(paramArrayOfObject[paramInt3], paramInt2) : j;
    } 
    int i = paramInt2;
    return (paramInt1 != -1073741824) ? paramInt1 : i;
  }
  
  public static int getParam(Object paramObject, int paramInt) {
    return (paramObject instanceof Number) ? ((Number)paramObject).intValue() : ((paramObject instanceof Character) ? ((Character)paramObject).charValue() : ((paramObject instanceof Char) ? ((Char)paramObject).charValue() : paramInt));
  }
  
  public static int nextArg(int paramInt) {
    return 0xFFFFFF & paramInt;
  }
  
  public static void print(Writer paramWriter, String paramString) throws IOException {
    if (paramWriter instanceof PrintWriter) {
      ((PrintWriter)paramWriter).print(paramString);
      return;
    } 
    paramWriter.write(paramString.toCharArray());
  }
  
  public static void print(Object paramObject, Consumer paramConsumer) {
    if (paramObject instanceof Printable) {
      ((Printable)paramObject).print(paramConsumer);
      return;
    } 
    if (paramObject == null) {
      paramObject = "null";
    } else {
      paramObject = paramObject.toString();
    } 
    paramConsumer.write((String)paramObject);
  }
  
  public static int result(int paramInt1, int paramInt2) {
    return paramInt1 << 24 | paramInt2;
  }
  
  public static int resultCode(int paramInt) {
    return paramInt >>> 24;
  }
  
  public int format(Object paramObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException {
    return (paramObject instanceof Object[]) ? format((Object[])paramObject, paramInt, paramWriter, paramFieldPosition) : format(new Object[] { paramObject }, paramInt, paramWriter, paramFieldPosition);
  }
  
  public abstract int format(Object[] paramArrayOfObject, int paramInt, Writer paramWriter, FieldPosition paramFieldPosition) throws IOException;
  
  public int format(Object[] paramArrayOfObject, int paramInt, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    CharArrayWriter charArrayWriter = new CharArrayWriter();
    try {
      paramInt = format(paramArrayOfObject, paramInt, charArrayWriter, paramFieldPosition);
      if (paramInt < 0)
        return paramInt; 
    } catch (IOException iOException) {
      throw new Error("unexpected exception: " + iOException);
    } 
    paramStringBuffer.append(charArrayWriter.toCharArray());
    return paramInt;
  }
  
  public StringBuffer format(Object paramObject, StringBuffer paramStringBuffer, FieldPosition paramFieldPosition) {
    format((Object[])paramObject, 0, paramStringBuffer, paramFieldPosition);
    return paramStringBuffer;
  }
  
  public Object parseObject(String paramString, ParsePosition paramParsePosition) {
    throw new Error("ReportFormat.parseObject - not implemented");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/ReportFormat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */