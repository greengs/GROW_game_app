package gnu.text;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;

public class Lexer extends Reader {
  protected boolean interactive;
  
  SourceMessages messages = null;
  
  protected int nesting;
  
  protected LineBufferedReader port;
  
  private int saveTokenBufferLength = -1;
  
  public char[] tokenBuffer = new char[100];
  
  public int tokenBufferLength = 0;
  
  public Lexer(LineBufferedReader paramLineBufferedReader) {
    this.port = paramLineBufferedReader;
  }
  
  public Lexer(LineBufferedReader paramLineBufferedReader, SourceMessages paramSourceMessages) {
    this.port = paramLineBufferedReader;
    this.messages = paramSourceMessages;
  }
  
  public static long readDigitsInBuffer(LineBufferedReader paramLineBufferedReader, int paramInt) {
    long l1 = 0L;
    int i = 0;
    long l2 = Long.MAX_VALUE / paramInt;
    int j = paramLineBufferedReader.pos;
    int k = j;
    if (j >= paramLineBufferedReader.limit)
      return 0L; 
    while (true) {
      j = Character.digit(paramLineBufferedReader.buffer[k], paramInt);
      if (j >= 0) {
        long l;
        if (l1 > l2) {
          i = 1;
          l = l1;
        } else {
          l = paramInt * l1 + j;
        } 
        j = i;
        if (l < 0L)
          j = 1; 
        int m = k + 1;
        k = m;
        i = j;
        l1 = l;
        if (m >= paramLineBufferedReader.limit) {
          k = m;
          i = j;
          l1 = l;
        } else {
          continue;
        } 
      } 
      paramLineBufferedReader.pos = k;
      return (i != 0) ? -1L : l1;
    } 
  }
  
  public boolean checkErrors(PrintWriter paramPrintWriter, int paramInt) {
    return (this.messages != null && this.messages.checkErrors(paramPrintWriter, paramInt));
  }
  
  public boolean checkNext(char paramChar) throws IOException {
    int i = this.port.read();
    if (i == paramChar)
      return true; 
    if (i >= 0)
      this.port.unread_quick(); 
    return false;
  }
  
  public void clearErrors() {
    if (this.messages != null)
      this.messages.clearErrors(); 
  }
  
  public void close() throws IOException {
    this.port.close();
  }
  
  public void eofError(String paramString) throws SyntaxException {
    fatal(paramString);
  }
  
  public void eofError(String paramString, int paramInt1, int paramInt2) throws SyntaxException {
    error('f', this.port.getName(), paramInt1, paramInt2, paramString);
    throw new SyntaxException(this.messages);
  }
  
  public void error(char paramChar, String paramString) {
    int j = this.port.getLineNumber();
    int i = this.port.getColumnNumber();
    String str = this.port.getName();
    if (i >= 0) {
      i++;
    } else {
      i = 0;
    } 
    error(paramChar, str, j + 1, i, paramString);
  }
  
  public void error(char paramChar, String paramString1, int paramInt1, int paramInt2, String paramString2) {
    if (this.messages == null)
      this.messages = new SourceMessages(); 
    this.messages.error(paramChar, paramString1, paramInt1, paramInt2, paramString2);
  }
  
  public void error(String paramString) {
    error('e', paramString);
  }
  
  public void fatal(String paramString) throws SyntaxException {
    error('f', paramString);
    throw new SyntaxException(this.messages);
  }
  
  public int getColumnNumber() {
    return this.port.getColumnNumber();
  }
  
  public SourceError getErrors() {
    return (this.messages == null) ? null : this.messages.getErrors();
  }
  
  public int getLineNumber() {
    return this.port.getLineNumber();
  }
  
  public SourceMessages getMessages() {
    return this.messages;
  }
  
  public String getName() {
    return this.port.getName();
  }
  
  public final LineBufferedReader getPort() {
    return this.port;
  }
  
  public boolean isInteractive() {
    return this.interactive;
  }
  
  public void mark() throws IOException {
    if (this.saveTokenBufferLength >= 0)
      throw new Error("internal error: recursive call to mark not allowed"); 
    this.port.mark(2147483647);
    this.saveTokenBufferLength = this.tokenBufferLength;
  }
  
  public int peek() throws IOException {
    return this.port.peek();
  }
  
  public void popNesting(char paramChar) {
    (getPort()).readState = paramChar;
    this.nesting--;
  }
  
  public char pushNesting(char paramChar) {
    this.nesting++;
    LineBufferedReader lineBufferedReader = getPort();
    char c = lineBufferedReader.readState;
    lineBufferedReader.readState = paramChar;
    return c;
  }
  
  public int read() throws IOException {
    return this.port.read();
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    return this.port.read(paramArrayOfchar, paramInt1, paramInt2);
  }
  
  public boolean readDelimited(String paramString) throws IOException, SyntaxException {
    this.tokenBufferLength = 0;
    int i = paramString.length();
    char c = paramString.charAt(i - 1);
    while (true) {
      int j = read();
      if (j < 0)
        return false; 
      if (j == c) {
        int m = this.tokenBufferLength;
        int k = i - 1;
        int n = m - k;
        if (n >= 0)
          do {
            if (k == 0) {
              this.tokenBufferLength = n;
              return true;
            } 
            m = k - 1;
            k = m;
          } while (this.tokenBuffer[n + m] == paramString.charAt(m)); 
      } 
      tokenBufferAppend((char)j);
    } 
  }
  
  public int readOptionalExponent() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual read : ()I
    //   4: istore #4
    //   6: iconst_0
    //   7: istore_3
    //   8: iconst_0
    //   9: istore #5
    //   11: iload #4
    //   13: bipush #43
    //   15: if_icmpeq -> 25
    //   18: iload #4
    //   20: bipush #45
    //   22: if_icmpne -> 100
    //   25: aload_0
    //   26: invokevirtual read : ()I
    //   29: istore_1
    //   30: iload_1
    //   31: iflt -> 46
    //   34: iload_1
    //   35: i2c
    //   36: bipush #10
    //   38: invokestatic digit : (CI)I
    //   41: istore_2
    //   42: iload_2
    //   43: ifge -> 109
    //   46: iload #4
    //   48: ifeq -> 57
    //   51: aload_0
    //   52: ldc 'exponent sign not followed by digit'
    //   54: invokevirtual error : (Ljava/lang/String;)V
    //   57: iconst_1
    //   58: istore_2
    //   59: iload_1
    //   60: istore #6
    //   62: iload #6
    //   64: iflt -> 73
    //   67: aload_0
    //   68: iload #6
    //   70: invokevirtual unread : (I)V
    //   73: iload_2
    //   74: istore_1
    //   75: iload #4
    //   77: bipush #45
    //   79: if_icmpne -> 85
    //   82: iload_2
    //   83: ineg
    //   84: istore_1
    //   85: iload #5
    //   87: ifeq -> 159
    //   90: iload #4
    //   92: bipush #45
    //   94: if_icmpne -> 156
    //   97: ldc -2147483648
    //   99: ireturn
    //   100: iload #4
    //   102: istore_1
    //   103: iconst_0
    //   104: istore #4
    //   106: goto -> 30
    //   109: iload_2
    //   110: istore_1
    //   111: aload_0
    //   112: invokevirtual read : ()I
    //   115: istore #6
    //   117: iload #6
    //   119: i2c
    //   120: bipush #10
    //   122: invokestatic digit : (CI)I
    //   125: istore #7
    //   127: iload_3
    //   128: istore #5
    //   130: iload_1
    //   131: istore_2
    //   132: iload #7
    //   134: iflt -> 62
    //   137: iload_1
    //   138: ldc 214748363
    //   140: if_icmple -> 145
    //   143: iconst_1
    //   144: istore_3
    //   145: iload_1
    //   146: bipush #10
    //   148: imul
    //   149: iload #7
    //   151: iadd
    //   152: istore_1
    //   153: goto -> 111
    //   156: ldc 2147483647
    //   158: ireturn
    //   159: iload_1
    //   160: ireturn
  }
  
  public int readUnicodeChar() throws IOException {
    int j = this.port.read();
    int i = j;
    if (j >= 55296) {
      i = j;
      if (j < 56319) {
        int k = this.port.read();
        i = j;
        if (k >= 56320) {
          i = j;
          if (k <= 57343)
            i = (j - 55296 << 10) + j - 56320 + 65536; 
        } 
      } 
    } 
    return i;
  }
  
  public void reset() throws IOException {
    if (this.saveTokenBufferLength < 0)
      throw new Error("internal error: reset called without prior mark"); 
    this.port.reset();
    this.saveTokenBufferLength = -1;
  }
  
  public boolean seenErrors() {
    return (this.messages != null && this.messages.seenErrors());
  }
  
  public void setInteractive(boolean paramBoolean) {
    this.interactive = paramBoolean;
  }
  
  public void setMessages(SourceMessages paramSourceMessages) {
    this.messages = paramSourceMessages;
  }
  
  public void skip() throws IOException {
    this.port.skip();
  }
  
  protected void skip_quick() throws IOException {
    this.port.skip_quick();
  }
  
  public void tokenBufferAppend(int paramInt) {
    int i = paramInt;
    if (paramInt >= 65536) {
      tokenBufferAppend((paramInt - 65536 >> 10) + 55296);
      i = (paramInt & 0x3FF) + 56320;
    } 
    paramInt = this.tokenBufferLength;
    char[] arrayOfChar2 = this.tokenBuffer;
    char[] arrayOfChar1 = arrayOfChar2;
    if (paramInt == this.tokenBuffer.length) {
      this.tokenBuffer = new char[paramInt * 2];
      System.arraycopy(arrayOfChar2, 0, this.tokenBuffer, 0, paramInt);
      arrayOfChar1 = this.tokenBuffer;
    } 
    arrayOfChar1[paramInt] = (char)i;
    this.tokenBufferLength = paramInt + 1;
  }
  
  public String tokenBufferString() {
    return new String(this.tokenBuffer, 0, this.tokenBufferLength);
  }
  
  protected void unread() throws IOException {
    this.port.unread();
  }
  
  public void unread(int paramInt) throws IOException {
    if (paramInt >= 0)
      this.port.unread(); 
  }
  
  protected void unread_quick() throws IOException {
    this.port.unread_quick();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/Lexer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */