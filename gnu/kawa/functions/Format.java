package gnu.kawa.functions;

import gnu.lists.FString;
import gnu.mapping.CharArrayOutPort;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.text.ReportFormat;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.text.MessageFormat;

public class Format extends ProcedureN {
  public static final Format format = new Format();
  
  static {
    format.setName("format");
    format.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyFormat");
  }
  
  static Object[] drop2(Object[] paramArrayOfObject) {
    int i = paramArrayOfObject.length - 2;
    Object[] arrayOfObject = new Object[i];
    System.arraycopy(paramArrayOfObject, 2, arrayOfObject, 0, i);
    return arrayOfObject;
  }
  
  public static Object format(Object... paramVarArgs) {
    Object object = paramVarArgs[0];
    if (object == Boolean.TRUE) {
      format((Writer)OutPort.outDefault(), paramVarArgs, 1);
      return Values.empty;
    } 
    if (object == Boolean.FALSE)
      return formatToString(1, paramVarArgs); 
    if (object instanceof MessageFormat || object instanceof CharSequence || object instanceof ReportFormat)
      return formatToString(0, paramVarArgs); 
    if (object instanceof Writer) {
      format((Writer)object, paramVarArgs, 1);
      return Values.empty;
    } 
    if (object instanceof OutputStream) {
      formatToOutputStream((OutputStream)object, paramVarArgs[1], drop2(paramVarArgs));
      return Values.empty;
    } 
    throw new RuntimeException("bad first argument to format");
  }
  
  public static void format(Writer paramWriter, Object[] paramArrayOfObject, int paramInt) {
    int i = paramInt + 1;
    Object object = paramArrayOfObject[paramInt];
    Object[] arrayOfObject = new Object[paramArrayOfObject.length - i];
    System.arraycopy(paramArrayOfObject, i, arrayOfObject, 0, arrayOfObject.length);
    formatToWriter(paramWriter, object, arrayOfObject);
  }
  
  public static FString formatToFString(char paramChar, Object paramObject, Object[] paramArrayOfObject) {
    ReportFormat reportFormat = ParseFormat.asFormat(paramObject, paramChar);
    paramObject = new CharArrayOutPort();
    try {
      reportFormat.format(paramArrayOfObject, 0, (Writer)paramObject, null);
      char[] arrayOfChar = paramObject.toCharArray();
      paramObject.close();
      return new FString(arrayOfChar);
    } catch (IOException iOException) {
      throw new RuntimeException("Error in format: " + iOException);
    } 
  }
  
  public static void formatToOutputStream(OutputStream paramOutputStream, Object paramObject, Object... paramVarArgs) {
    OutPort outPort = new OutPort(paramOutputStream);
    format(new Object[] { outPort, paramObject, paramVarArgs });
    outPort.closeThis();
  }
  
  public static String formatToString(int paramInt, Object... paramVarArgs) {
    CharArrayOutPort charArrayOutPort = new CharArrayOutPort();
    format((Writer)charArrayOutPort, paramVarArgs, paramInt);
    String str = charArrayOutPort.toString();
    charArrayOutPort.close();
    return str;
  }
  
  public static void formatToWriter(Writer paramWriter, Object paramObject, Object... paramVarArgs) {
    OutPort outPort;
    Writer writer = paramWriter;
    if (paramWriter == null)
      outPort = OutPort.outDefault(); 
    try {
      if (paramObject instanceof MessageFormat) {
        outPort.write(((MessageFormat)paramObject).format(paramVarArgs));
        return;
      } 
      Object object = paramObject;
      if (!(paramObject instanceof ReportFormat))
        object = ParseFormat.parseFormat.apply1(paramObject); 
      ((ReportFormat)object).format(paramVarArgs, 0, (Writer)outPort, null);
      return;
    } catch (IOException iOException) {
      throw new RuntimeException("Error in format: " + iOException);
    } 
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return format(paramArrayOfObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Format.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */