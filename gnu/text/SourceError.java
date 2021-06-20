package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceError implements SourceLocator {
  public String code;
  
  public int column;
  
  public Throwable fakeException;
  
  public String filename;
  
  public int line;
  
  public String message;
  
  public SourceError next;
  
  public char severity;
  
  public SourceError(char paramChar, SourceLocator paramSourceLocator, String paramString) {
    this(paramChar, paramSourceLocator.getFileName(), paramSourceLocator.getLineNumber(), paramSourceLocator.getColumnNumber(), paramString);
  }
  
  public SourceError(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2) {
    this.severity = paramChar;
    this.filename = paramString1;
    this.line = paramInt1;
    this.column = paramInt2;
    this.message = paramString2;
  }
  
  public SourceError(LineBufferedReader paramLineBufferedReader, char paramChar, String paramString) {
    this(paramChar, paramLineBufferedReader.getName(), paramLineBufferedReader.getLineNumber() + 1, paramLineBufferedReader.getColumnNumber(), paramString);
    if (this.column >= 0)
      this.column++; 
  }
  
  public int getColumnNumber() {
    return (this.column == 0) ? -1 : this.column;
  }
  
  public String getFileName() {
    return this.filename;
  }
  
  public int getLineNumber() {
    return (this.line == 0) ? -1 : this.line;
  }
  
  public String getPublicId() {
    return null;
  }
  
  public String getSystemId() {
    return this.filename;
  }
  
  public boolean isStableSourceLocation() {
    return true;
  }
  
  public void print(PrintWriter paramPrintWriter) {
    paramPrintWriter.print(this);
  }
  
  public void println(PrintStream paramPrintStream) {
    for (String str = toString();; str = str.substring(i + 1)) {
      int i = str.indexOf('\n');
      if (i < 0) {
        paramPrintStream.println(str);
        return;
      } 
      paramPrintStream.println(str.substring(0, i));
    } 
  }
  
  public void println(PrintWriter paramPrintWriter) {
    for (String str = toString();; str = str.substring(i + 1)) {
      int i = str.indexOf('\n');
      if (i < 0) {
        paramPrintWriter.println(str);
        return;
      } 
      paramPrintWriter.println(str.substring(0, i));
    } 
  }
  
  public String toString() {
    String str;
    StringBuffer stringBuffer = new StringBuffer();
    if (this.filename == null) {
      str = "<unknown>";
    } else {
      str = this.filename;
    } 
    stringBuffer.append(str);
    if (this.line > 0 || this.column > 0) {
      stringBuffer.append(':');
      stringBuffer.append(this.line);
      if (this.column > 0) {
        stringBuffer.append(':');
        stringBuffer.append(this.column);
      } 
    } 
    stringBuffer.append(": ");
    if (this.severity == 'w')
      stringBuffer.append("warning - "); 
    stringBuffer.append(this.message);
    if (this.code != null) {
      stringBuffer.append(" [");
      stringBuffer.append(this.code);
      stringBuffer.append("]");
    } 
    if (this.fakeException != null) {
      StackTraceElement[] arrayOfStackTraceElement = this.fakeException.getStackTrace();
      for (int i = 0; i < arrayOfStackTraceElement.length; i++) {
        stringBuffer.append("\n");
        stringBuffer.append("    ");
        stringBuffer.append(arrayOfStackTraceElement[i].toString());
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/SourceError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */