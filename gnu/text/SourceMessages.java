package gnu.text;

import java.io.PrintStream;
import java.io.PrintWriter;

public class SourceMessages implements SourceLocator {
  public static boolean debugStackTraceOnError;
  
  public static boolean debugStackTraceOnWarning = false;
  
  int current_column;
  
  String current_filename;
  
  int current_line;
  
  private int errorCount = 0;
  
  SourceError firstError;
  
  SourceError lastError;
  
  SourceError lastPrevFilename = null;
  
  SourceLocator locator;
  
  public boolean sortMessages;
  
  static {
    debugStackTraceOnError = false;
  }
  
  public boolean checkErrors(PrintStream paramPrintStream, int paramInt) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.firstError != null) {
      printAll(paramPrintStream, paramInt);
      this.lastError = null;
      this.firstError = null;
      paramInt = this.errorCount;
      this.errorCount = 0;
      bool1 = bool2;
      if (paramInt > 0)
        bool1 = true; 
    } 
    return bool1;
  }
  
  public boolean checkErrors(PrintWriter paramPrintWriter, int paramInt) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (this.firstError != null) {
      printAll(paramPrintWriter, paramInt);
      this.lastError = null;
      this.firstError = null;
      paramInt = this.errorCount;
      this.errorCount = 0;
      bool1 = bool2;
      if (paramInt > 0)
        bool1 = true; 
    } 
    return bool1;
  }
  
  public void clear() {
    this.lastError = null;
    this.firstError = null;
    this.errorCount = 0;
  }
  
  public void clearErrors() {
    this.errorCount = 0;
  }
  
  public void error(char paramChar, SourceLocator paramSourceLocator, String paramString) {
    error(new SourceError(paramChar, paramSourceLocator, paramString));
  }
  
  public void error(char paramChar, SourceLocator paramSourceLocator, String paramString1, String paramString2) {
    paramSourceLocator = new SourceError(paramChar, paramSourceLocator, paramString1);
    ((SourceError)paramSourceLocator).code = paramString2;
    error((SourceError)paramSourceLocator);
  }
  
  public void error(char paramChar, String paramString) {
    error(new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString));
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2) {
    error(new SourceError(paramChar, paramString1, paramInt1, paramInt2, paramString2));
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3) {
    SourceError sourceError = new SourceError(paramChar, paramString1, paramInt1, paramInt2, paramString2);
    sourceError.code = paramString3;
    error(sourceError);
  }
  
  public void error(char paramChar, String paramString1, String paramString2) {
    SourceError sourceError = new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString1);
    sourceError.code = paramString2;
    error(sourceError);
  }
  
  public void error(char paramChar, String paramString, Throwable paramThrowable) {
    SourceError sourceError = new SourceError(paramChar, this.current_filename, this.current_line, this.current_column, paramString);
    sourceError.fakeException = paramThrowable;
    error(sourceError);
  }
  
  public void error(SourceError paramSourceError) {
    // Byte code:
    //   0: aload_1
    //   1: getfield severity : C
    //   4: bipush #102
    //   6: if_icmpne -> 168
    //   9: aload_0
    //   10: sipush #1000
    //   13: putfield errorCount : I
    //   16: getstatic gnu/text/SourceMessages.debugStackTraceOnError : Z
    //   19: ifeq -> 40
    //   22: aload_1
    //   23: getfield severity : C
    //   26: bipush #101
    //   28: if_icmpeq -> 55
    //   31: aload_1
    //   32: getfield severity : C
    //   35: bipush #102
    //   37: if_icmpeq -> 55
    //   40: getstatic gnu/text/SourceMessages.debugStackTraceOnWarning : Z
    //   43: ifeq -> 66
    //   46: aload_1
    //   47: getfield severity : C
    //   50: bipush #119
    //   52: if_icmpne -> 66
    //   55: aload_1
    //   56: new java/lang/Throwable
    //   59: dup
    //   60: invokespecial <init> : ()V
    //   63: putfield fakeException : Ljava/lang/Throwable;
    //   66: aload_0
    //   67: getfield lastError : Lgnu/text/SourceError;
    //   70: ifnull -> 108
    //   73: aload_0
    //   74: getfield lastError : Lgnu/text/SourceError;
    //   77: getfield filename : Ljava/lang/String;
    //   80: ifnull -> 108
    //   83: aload_0
    //   84: getfield lastError : Lgnu/text/SourceError;
    //   87: getfield filename : Ljava/lang/String;
    //   90: aload_1
    //   91: getfield filename : Ljava/lang/String;
    //   94: invokevirtual equals : (Ljava/lang/Object;)Z
    //   97: ifne -> 108
    //   100: aload_0
    //   101: aload_0
    //   102: getfield lastError : Lgnu/text/SourceError;
    //   105: putfield lastPrevFilename : Lgnu/text/SourceError;
    //   108: aload_0
    //   109: getfield lastPrevFilename : Lgnu/text/SourceError;
    //   112: astore_2
    //   113: aload_0
    //   114: getfield sortMessages : Z
    //   117: ifeq -> 129
    //   120: aload_1
    //   121: getfield severity : C
    //   124: bipush #102
    //   126: if_icmpne -> 192
    //   129: aload_0
    //   130: getfield lastError : Lgnu/text/SourceError;
    //   133: astore #4
    //   135: aload #4
    //   137: ifnonnull -> 286
    //   140: aload_1
    //   141: aload_0
    //   142: getfield firstError : Lgnu/text/SourceError;
    //   145: putfield next : Lgnu/text/SourceError;
    //   148: aload_0
    //   149: aload_1
    //   150: putfield firstError : Lgnu/text/SourceError;
    //   153: aload #4
    //   155: aload_0
    //   156: getfield lastError : Lgnu/text/SourceError;
    //   159: if_acmpne -> 167
    //   162: aload_0
    //   163: aload_1
    //   164: putfield lastError : Lgnu/text/SourceError;
    //   167: return
    //   168: aload_1
    //   169: getfield severity : C
    //   172: bipush #119
    //   174: if_icmpeq -> 16
    //   177: aload_0
    //   178: aload_0
    //   179: getfield errorCount : I
    //   182: iconst_1
    //   183: iadd
    //   184: putfield errorCount : I
    //   187: goto -> 16
    //   190: aload_3
    //   191: astore_2
    //   192: aload_2
    //   193: ifnonnull -> 278
    //   196: aload_0
    //   197: getfield firstError : Lgnu/text/SourceError;
    //   200: astore_3
    //   201: aload_2
    //   202: astore #4
    //   204: aload_3
    //   205: ifnull -> 135
    //   208: aload_1
    //   209: getfield line : I
    //   212: ifeq -> 190
    //   215: aload_3
    //   216: getfield line : I
    //   219: ifeq -> 190
    //   222: aload_2
    //   223: astore #4
    //   225: aload_1
    //   226: getfield line : I
    //   229: aload_3
    //   230: getfield line : I
    //   233: if_icmplt -> 135
    //   236: aload_1
    //   237: getfield line : I
    //   240: aload_3
    //   241: getfield line : I
    //   244: if_icmpne -> 190
    //   247: aload_1
    //   248: getfield column : I
    //   251: ifeq -> 190
    //   254: aload_3
    //   255: getfield column : I
    //   258: ifeq -> 190
    //   261: aload_1
    //   262: getfield column : I
    //   265: aload_3
    //   266: getfield column : I
    //   269: if_icmpge -> 190
    //   272: aload_2
    //   273: astore #4
    //   275: goto -> 135
    //   278: aload_2
    //   279: getfield next : Lgnu/text/SourceError;
    //   282: astore_3
    //   283: goto -> 201
    //   286: aload_1
    //   287: aload #4
    //   289: getfield next : Lgnu/text/SourceError;
    //   292: putfield next : Lgnu/text/SourceError;
    //   295: aload #4
    //   297: aload_1
    //   298: putfield next : Lgnu/text/SourceError;
    //   301: goto -> 153
  }
  
  public final int getColumnNumber() {
    return (this.locator == null) ? this.current_column : this.locator.getColumnNumber();
  }
  
  public int getErrorCount() {
    return this.errorCount;
  }
  
  public SourceError getErrors() {
    return this.firstError;
  }
  
  public final String getFileName() {
    return this.current_filename;
  }
  
  public final int getLineNumber() {
    return (this.locator == null) ? this.current_line : this.locator.getLineNumber();
  }
  
  public String getPublicId() {
    return (this.locator == null) ? null : this.locator.getPublicId();
  }
  
  public String getSystemId() {
    return (this.locator == null) ? this.current_filename : this.locator.getSystemId();
  }
  
  public boolean isStableSourceLocation() {
    return false;
  }
  
  public void printAll(PrintStream paramPrintStream, int paramInt) {
    SourceError sourceError = this.firstError;
    while (sourceError != null) {
      if (--paramInt >= 0) {
        sourceError.println(paramPrintStream);
        sourceError = sourceError.next;
      } 
    } 
  }
  
  public void printAll(PrintWriter paramPrintWriter, int paramInt) {
    SourceError sourceError = this.firstError;
    while (sourceError != null) {
      if (--paramInt >= 0) {
        sourceError.println(paramPrintWriter);
        sourceError = sourceError.next;
      } 
    } 
  }
  
  public boolean seenErrors() {
    return (this.errorCount > 0);
  }
  
  public boolean seenErrorsOrWarnings() {
    return (this.firstError != null);
  }
  
  public void setColumn(int paramInt) {
    this.current_column = paramInt;
  }
  
  public void setFile(String paramString) {
    this.current_filename = paramString;
  }
  
  public void setLine(int paramInt) {
    this.current_line = paramInt;
  }
  
  public void setLine(String paramString, int paramInt1, int paramInt2) {
    this.current_filename = paramString;
    this.current_line = paramInt1;
    this.current_column = paramInt2;
  }
  
  public final void setLocation(SourceLocator paramSourceLocator) {
    this.locator = null;
    this.current_line = paramSourceLocator.getLineNumber();
    this.current_column = paramSourceLocator.getColumnNumber();
    this.current_filename = paramSourceLocator.getFileName();
  }
  
  public final void setSourceLocator(SourceLocator paramSourceLocator) {
    SourceLocator sourceLocator = paramSourceLocator;
    if (paramSourceLocator == this)
      sourceLocator = null; 
    this.locator = sourceLocator;
  }
  
  public final SourceLocator swapSourceLocator(SourceLocator paramSourceLocator) {
    SourceLocator sourceLocator = this.locator;
    this.locator = paramSourceLocator;
    return sourceLocator;
  }
  
  public String toString(int paramInt) {
    if (this.firstError == null)
      return null; 
    StringBuffer stringBuffer = new StringBuffer();
    SourceError sourceError = this.firstError;
    while (sourceError != null) {
      if (--paramInt >= 0) {
        stringBuffer.append(sourceError);
        stringBuffer.append('\n');
        sourceError = sourceError.next;
      } 
    } 
    return stringBuffer.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/SourceMessages.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */