package gnu.text;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class LineBufferedReader extends Reader {
  public static final int BUFFER_SIZE = 8192;
  
  private static final int CONVERT_CR = 1;
  
  private static final int DONT_KEEP_FULL_LINES = 8;
  
  private static final int PREV_WAS_CR = 4;
  
  private static final int USER_BUFFER = 2;
  
  public char[] buffer;
  
  private int flags;
  
  int highestPos;
  
  protected Reader in;
  
  public int limit;
  
  protected int lineNumber;
  
  private int lineStartPos;
  
  protected int markPos;
  
  Path path;
  
  public int pos;
  
  protected int readAheadLimit = 0;
  
  public char readState = '\n';
  
  public LineBufferedReader(InputStream paramInputStream) {
    this.in = new InputStreamReader(paramInputStream);
  }
  
  public LineBufferedReader(Reader paramReader) {
    this.in = paramReader;
  }
  
  private void clearMark() {
    Object object;
    int i = 0;
    this.readAheadLimit = 0;
    if (this.lineStartPos >= 0)
      i = this.lineStartPos; 
    while (true) {
      int j = object + 1;
      if (j >= this.pos)
        return; 
      char c = this.buffer[j - 1];
      if (c != '\n') {
        int k = j;
        if (c == '\r') {
          if (getConvertCR()) {
            k = j;
            if (this.buffer[j] != '\n')
              continue; 
            continue;
          } 
          continue;
        } 
        continue;
      } 
      continue;
      this.lineNumber++;
      this.lineStartPos = SYNTHETIC_LOCAL_VARIABLE_2;
      object = SYNTHETIC_LOCAL_VARIABLE_2;
    } 
  }
  
  static int countLines(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: iload_1
    //   6: iload_2
    //   7: if_icmpge -> 58
    //   10: aload_0
    //   11: iload_1
    //   12: caload
    //   13: istore #5
    //   15: iload #5
    //   17: bipush #10
    //   19: if_icmpne -> 29
    //   22: iload #4
    //   24: bipush #13
    //   26: if_icmpne -> 39
    //   29: iload_3
    //   30: istore #4
    //   32: iload #5
    //   34: bipush #13
    //   36: if_icmpne -> 44
    //   39: iload_3
    //   40: iconst_1
    //   41: iadd
    //   42: istore #4
    //   44: iload_1
    //   45: iconst_1
    //   46: iadd
    //   47: istore_1
    //   48: iload #4
    //   50: istore_3
    //   51: iload #5
    //   53: istore #4
    //   55: goto -> 5
    //   58: iload_3
    //   59: ireturn
  }
  
  private void reserve(char[] paramArrayOfchar, int paramInt) throws IOException {
    // Byte code:
    //   0: iload_2
    //   1: aload_0
    //   2: getfield limit : I
    //   5: iadd
    //   6: istore #4
    //   8: iload #4
    //   10: aload_1
    //   11: arraylength
    //   12: if_icmpgt -> 44
    //   15: iconst_0
    //   16: istore_2
    //   17: aload_0
    //   18: getfield limit : I
    //   21: ifle -> 38
    //   24: aload_0
    //   25: getfield buffer : [C
    //   28: iload_2
    //   29: aload_1
    //   30: iconst_0
    //   31: aload_0
    //   32: getfield limit : I
    //   35: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   38: aload_0
    //   39: aload_1
    //   40: putfield buffer : [C
    //   43: return
    //   44: aload_0
    //   45: getfield pos : I
    //   48: istore_2
    //   49: iload_2
    //   50: istore_3
    //   51: aload_0
    //   52: getfield readAheadLimit : I
    //   55: ifle -> 114
    //   58: iload_2
    //   59: istore_3
    //   60: aload_0
    //   61: getfield markPos : I
    //   64: aload_0
    //   65: getfield pos : I
    //   68: if_icmpge -> 114
    //   71: aload_0
    //   72: getfield pos : I
    //   75: aload_0
    //   76: getfield markPos : I
    //   79: isub
    //   80: aload_0
    //   81: getfield readAheadLimit : I
    //   84: if_icmpgt -> 108
    //   87: aload_0
    //   88: getfield flags : I
    //   91: iconst_2
    //   92: iand
    //   93: ifeq -> 211
    //   96: iload #4
    //   98: aload_0
    //   99: getfield markPos : I
    //   102: isub
    //   103: aload_1
    //   104: arraylength
    //   105: if_icmple -> 211
    //   108: aload_0
    //   109: invokespecial clearMark : ()V
    //   112: iload_2
    //   113: istore_3
    //   114: iload #4
    //   116: aload_1
    //   117: arraylength
    //   118: isub
    //   119: istore #4
    //   121: iload #4
    //   123: iload_3
    //   124: if_icmpgt -> 219
    //   127: iload_3
    //   128: istore_2
    //   129: aload_1
    //   130: astore #5
    //   132: iload_3
    //   133: aload_0
    //   134: getfield lineStartPos : I
    //   137: if_icmple -> 155
    //   140: aload_0
    //   141: getfield flags : I
    //   144: bipush #8
    //   146: iand
    //   147: ifeq -> 219
    //   150: aload_1
    //   151: astore #5
    //   153: iload_3
    //   154: istore_2
    //   155: aload_0
    //   156: aload_0
    //   157: getfield lineStartPos : I
    //   160: iload_2
    //   161: isub
    //   162: putfield lineStartPos : I
    //   165: aload_0
    //   166: aload_0
    //   167: getfield limit : I
    //   170: iload_2
    //   171: isub
    //   172: putfield limit : I
    //   175: aload_0
    //   176: aload_0
    //   177: getfield markPos : I
    //   180: iload_2
    //   181: isub
    //   182: putfield markPos : I
    //   185: aload_0
    //   186: aload_0
    //   187: getfield pos : I
    //   190: iload_2
    //   191: isub
    //   192: putfield pos : I
    //   195: aload_0
    //   196: aload_0
    //   197: getfield highestPos : I
    //   200: iload_2
    //   201: isub
    //   202: putfield highestPos : I
    //   205: aload #5
    //   207: astore_1
    //   208: goto -> 17
    //   211: aload_0
    //   212: getfield markPos : I
    //   215: istore_3
    //   216: goto -> 114
    //   219: iload #4
    //   221: aload_0
    //   222: getfield lineStartPos : I
    //   225: if_icmpgt -> 247
    //   228: iload_3
    //   229: aload_0
    //   230: getfield lineStartPos : I
    //   233: if_icmple -> 247
    //   236: aload_0
    //   237: getfield lineStartPos : I
    //   240: istore_2
    //   241: aload_1
    //   242: astore #5
    //   244: goto -> 155
    //   247: aload_0
    //   248: getfield flags : I
    //   251: iconst_2
    //   252: iand
    //   253: ifeq -> 271
    //   256: iload_3
    //   257: iload_3
    //   258: iload #4
    //   260: isub
    //   261: iconst_2
    //   262: ishr
    //   263: isub
    //   264: istore_2
    //   265: aload_1
    //   266: astore #5
    //   268: goto -> 155
    //   271: iload_3
    //   272: istore_2
    //   273: aload_0
    //   274: getfield lineStartPos : I
    //   277: iflt -> 285
    //   280: aload_0
    //   281: getfield lineStartPos : I
    //   284: istore_2
    //   285: aload_1
    //   286: arraylength
    //   287: iconst_2
    //   288: imul
    //   289: newarray char
    //   291: astore #5
    //   293: goto -> 155
  }
  
  public void close() throws IOException {
    this.in.close();
  }
  
  public int fill(int paramInt) throws IOException {
    return this.in.read(this.buffer, this.pos, paramInt);
  }
  
  public int getColumnNumber() {
    int i = 0;
    if (this.pos > 0) {
      char c = this.buffer[this.pos - 1];
      if (c == '\n' || c == '\r')
        return 0; 
    } 
    if (this.readAheadLimit <= 0)
      return this.pos - this.lineStartPos; 
    if (this.lineStartPos >= 0)
      i = this.lineStartPos; 
    int k = i;
    int j = i;
    while (k < this.pos) {
      char[] arrayOfChar = this.buffer;
      i = k + 1;
      k = arrayOfChar[k];
      if (k == 10 || k == 13)
        j = i; 
      k = i;
    } 
    j = this.pos - j;
    i = j;
    if (this.lineStartPos < 0)
      i = j - this.lineStartPos; 
    return i;
  }
  
  public final boolean getConvertCR() {
    return ((this.flags & 0x1) != 0);
  }
  
  public int getLineNumber() {
    int i = this.lineNumber;
    if (this.readAheadLimit == 0) {
      int j = i;
      if (this.pos > 0) {
        j = i;
        if (this.pos > this.lineStartPos) {
          char c = this.buffer[this.pos - 1];
          if (c != '\n') {
            j = i;
            return (c == '\r') ? (i + 1) : j;
          } 
        } else {
          return j;
        } 
      } else {
        return j;
      } 
    } else {
      char[] arrayOfChar = this.buffer;
      if (this.lineStartPos < 0) {
        boolean bool = false;
        return i + countLines(arrayOfChar, bool, this.pos);
      } 
      int j = this.lineStartPos;
      return i + countLines(arrayOfChar, j, this.pos);
    } 
    return i + 1;
  }
  
  public String getName() {
    return (this.path == null) ? null : this.path.toString();
  }
  
  public Path getPath() {
    return this.path;
  }
  
  public char getReadState() {
    return this.readState;
  }
  
  public void incrLineNumber(int paramInt1, int paramInt2) {
    this.lineNumber += paramInt1;
    this.lineStartPos = paramInt2;
  }
  
  public void lineStart(boolean paramBoolean) throws IOException {}
  
  public void mark(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield readAheadLimit : I
    //   6: ifle -> 13
    //   9: aload_0
    //   10: invokespecial clearMark : ()V
    //   13: aload_0
    //   14: iload_1
    //   15: putfield readAheadLimit : I
    //   18: aload_0
    //   19: aload_0
    //   20: getfield pos : I
    //   23: putfield markPos : I
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: astore_2
    //   30: aload_0
    //   31: monitorexit
    //   32: aload_2
    //   33: athrow
    // Exception table:
    //   from	to	target	type
    //   2	13	29	finally
    //   13	26	29	finally
  }
  
  public boolean markSupported() {
    return true;
  }
  
  public int peek() throws IOException {
    if (this.pos < this.limit && this.pos > 0) {
      char c = this.buffer[this.pos - 1];
      if (c != '\n' && c != '\r') {
        char c1 = this.buffer[this.pos];
        c = c1;
        if (c1 == '\r') {
          c = c1;
          if (getConvertCR())
            c = '\n'; 
        } 
        return c;
      } 
    } 
    int i = read();
    if (i >= 0)
      unread_quick(); 
    return i;
  }
  
  public int read() throws IOException {
    boolean bool;
    if (this.pos > 0) {
      bool = this.buffer[this.pos - 1];
    } else if ((this.flags & 0x4) != 0) {
      bool = true;
    } else if (this.lineStartPos >= 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (bool == 13 || bool == 10) {
      boolean bool1;
      if (this.lineStartPos < this.pos && (this.readAheadLimit == 0 || this.pos <= this.markPos)) {
        this.lineStartPos = this.pos;
        this.lineNumber++;
      } 
      if (this.pos < this.highestPos) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      if (bool != 10 || ((this.pos <= 1) ? ((this.flags & 0x4) == 0) : (this.buffer[this.pos - 2] != '\r')))
        lineStart(bool1); 
      if (!bool1)
        this.highestPos = this.pos + 1; 
    } 
    if (this.pos >= this.limit) {
      if (this.buffer == null) {
        this.buffer = new char[8192];
      } else if (this.limit == this.buffer.length) {
        reserve(this.buffer, 1);
      } 
      if (this.pos == 0)
        if (bool == 13) {
          this.flags |= 0x4;
        } else {
          this.flags &= 0xFFFFFFFB;
        }  
      int j = fill(this.buffer.length - this.pos);
      if (j <= 0)
        return -1; 
      this.limit += j;
    } 
    char[] arrayOfChar = this.buffer;
    int i = this.pos;
    this.pos = i + 1;
    char c = arrayOfChar[i];
    if (c == '\n') {
      i = c;
      if (bool == 13) {
        if (this.lineStartPos == this.pos - 1) {
          this.lineNumber--;
          this.lineStartPos--;
        } 
        i = c;
        if (getConvertCR())
          return read(); 
      } 
      return i;
    } 
    i = c;
    if (c == '\r') {
      i = c;
      if (getConvertCR())
        return 10; 
    } 
    return i;
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) throws IOException {
    int i;
    if (this.pos >= this.limit) {
      i = 0;
    } else if (this.pos > 0) {
      i = this.buffer[this.pos - 1];
    } else if ((this.flags & 0x4) != 0 || this.lineStartPos >= 0) {
      i = 10;
    } else {
      i = 0;
    } 
    int j = paramInt2;
    int k = paramInt1;
    paramInt1 = j;
    j = k;
    label44: while (paramInt1 > 0) {
      if (this.pos >= this.limit || i == 10 || i == 13) {
        if (this.pos >= this.limit && paramInt1 < paramInt2)
          return paramInt2 - paramInt1; 
        i = read();
        if (i < 0) {
          paramInt1 = paramInt2 - paramInt1;
          return (paramInt1 <= 0) ? -1 : paramInt1;
        } 
        paramArrayOfchar[j] = (char)i;
        paramInt1--;
        j++;
        continue;
      } 
      int i2 = this.pos;
      int i3 = this.limit;
      k = i;
      int n = i3;
      int m = j;
      int i1 = i2;
      if (paramInt1 < i3 - i2) {
        n = i2 + paramInt1;
        i1 = i2;
        m = j;
        k = i;
      } 
      while (true) {
        i = k;
        if (i1 < n) {
          k = this.buffer[i1];
          i = k;
          if (k != 10)
            if (k == 13) {
              i = k;
            } else {
              paramArrayOfchar[m] = (char)k;
              i1++;
              m++;
              continue;
            }  
        } 
        paramInt1 -= i1 - this.pos;
        this.pos = i1;
        j = m;
        continue label44;
      } 
    } 
    return paramInt2;
  }
  
  public String readLine() throws IOException {
    int i = read();
    if (i < 0)
      return null; 
    if (i == 13 || i == 10)
      return ""; 
    i = this.pos - 1;
    while (this.pos < this.limit) {
      char[] arrayOfChar = this.buffer;
      int j = this.pos;
      this.pos = j + 1;
      j = arrayOfChar[j];
      if (j == 13 || j == 10) {
        int k = this.pos;
        if (j != 10 && !getConvertCR()) {
          if (this.pos >= this.limit) {
            this.pos--;
            break;
          } 
          if (this.buffer[this.pos] == '\n')
            this.pos++; 
        } 
        return new String(this.buffer, i, k - 1 - i);
      } 
    } 
    StringBuffer stringBuffer = new StringBuffer(100);
    stringBuffer.append(this.buffer, i, this.pos - i);
    readLine(stringBuffer, 'I');
    return stringBuffer.toString();
  }
  
  public void readLine(StringBuffer paramStringBuffer, char paramChar) throws IOException {
    label33: while (true) {
      if (read() < 0)
        return; 
      int i = this.pos - 1;
      this.pos = i;
      while (this.pos < this.limit) {
        char[] arrayOfChar = this.buffer;
        int j = this.pos;
        this.pos = j + 1;
        j = arrayOfChar[j];
        if (j == 13 || j == 10) {
          paramStringBuffer.append(this.buffer, i, this.pos - 1 - i);
          if (paramChar == 'P') {
            this.pos--;
            return;
          } 
          if (getConvertCR() || j == 10) {
            if (paramChar != 'I') {
              paramStringBuffer.append('\n');
              return;
            } 
            continue label33;
          } 
          if (paramChar != 'I')
            paramStringBuffer.append('\r'); 
          i = read();
          if (i == 10) {
            if (paramChar != 'I') {
              paramStringBuffer.append('\n');
              return;
            } 
            continue label33;
          } 
          if (i >= 0) {
            unread_quick();
            return;
          } 
          continue label33;
        } 
      } 
      paramStringBuffer.append(this.buffer, i, this.pos - i);
    } 
  }
  
  public boolean ready() throws IOException {
    return (this.pos < this.limit || this.in.ready());
  }
  
  public void reset() throws IOException {
    if (this.readAheadLimit <= 0)
      throw new IOException("mark invalid"); 
    if (this.pos > this.highestPos)
      this.highestPos = this.pos; 
    this.pos = this.markPos;
    this.readAheadLimit = 0;
  }
  
  public void setBuffer(char[] paramArrayOfchar) throws IOException {
    if (paramArrayOfchar == null) {
      if (this.buffer != null) {
        paramArrayOfchar = new char[this.buffer.length];
        System.arraycopy(this.buffer, 0, paramArrayOfchar, 0, this.buffer.length);
        this.buffer = paramArrayOfchar;
      } 
      this.flags &= 0xFFFFFFFD;
      return;
    } 
    if (this.limit - this.pos > paramArrayOfchar.length)
      throw new IOException("setBuffer - too short"); 
    this.flags |= 0x2;
    reserve(paramArrayOfchar, 0);
  }
  
  public final void setConvertCR(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x1;
      return;
    } 
    this.flags &= 0xFFFFFFFE;
  }
  
  public void setKeepFullLines(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags &= 0xFFFFFFF7;
      return;
    } 
    this.flags |= 0x8;
  }
  
  public void setLineNumber(int paramInt) {
    this.lineNumber += paramInt - getLineNumber();
  }
  
  public void setName(Object paramObject) {
    setPath(Path.valueOf(paramObject));
  }
  
  public void setPath(Path paramPath) {
    this.path = paramPath;
  }
  
  public int skip(int paramInt) throws IOException {
    int i;
    if (paramInt < 0) {
      for (i = -paramInt; i > 0 && this.pos > 0; i--)
        unread(); 
      return paramInt + i;
    } 
    int j = paramInt;
    if (this.pos >= this.limit) {
      i = 0;
    } else if (this.pos > 0) {
      i = this.buffer[this.pos - 1];
    } else if ((this.flags & 0x4) != 0 || this.lineStartPos >= 0) {
      i = 10;
    } else {
      i = 0;
    } 
    label47: while (true) {
      int k = paramInt;
      if (j > 0) {
        if (i == 10 || i == 13 || this.pos >= this.limit) {
          i = read();
          if (i < 0)
            return paramInt - j; 
          j--;
          continue;
        } 
        int i1 = this.pos;
        int i2 = this.limit;
        k = i;
        int n = i2;
        int m = i1;
        if (j < i2 - i1) {
          n = i1 + j;
          m = i1;
          k = i;
        } 
        while (true) {
          i = k;
          if (m < n) {
            k = this.buffer[m];
            i = k;
            if (k != 10)
              if (k == 13) {
                i = k;
              } else {
                m++;
                continue;
              }  
          } 
          j -= m - this.pos;
          this.pos = m;
          continue label47;
        } 
        break;
      } 
      return k;
    } 
  }
  
  public void skip() throws IOException {
    read();
  }
  
  public void skipRestOfLine() throws IOException {
    while (true) {
      int i = read();
      if (i >= 0) {
        if (i == 13) {
          i = read();
          if (i >= 0 && i != 10) {
            unread();
            return;
          } 
          return;
        } 
        if (i == 10)
          break; 
        continue;
      } 
      return;
    } 
  }
  
  public final void skip_quick() throws IOException {
    this.pos++;
  }
  
  public void unread() throws IOException {
    // Byte code:
    //   0: aload_0
    //   1: getfield pos : I
    //   4: ifne -> 17
    //   7: new java/io/IOException
    //   10: dup
    //   11: ldc 'unread too much'
    //   13: invokespecial <init> : (Ljava/lang/String;)V
    //   16: athrow
    //   17: aload_0
    //   18: aload_0
    //   19: getfield pos : I
    //   22: iconst_1
    //   23: isub
    //   24: putfield pos : I
    //   27: aload_0
    //   28: getfield buffer : [C
    //   31: aload_0
    //   32: getfield pos : I
    //   35: caload
    //   36: istore_1
    //   37: iload_1
    //   38: bipush #10
    //   40: if_icmpeq -> 49
    //   43: iload_1
    //   44: bipush #13
    //   46: if_icmpne -> 165
    //   49: aload_0
    //   50: getfield pos : I
    //   53: ifle -> 95
    //   56: iload_1
    //   57: bipush #10
    //   59: if_icmpne -> 95
    //   62: aload_0
    //   63: invokevirtual getConvertCR : ()Z
    //   66: ifeq -> 95
    //   69: aload_0
    //   70: getfield buffer : [C
    //   73: aload_0
    //   74: getfield pos : I
    //   77: iconst_1
    //   78: isub
    //   79: caload
    //   80: bipush #13
    //   82: if_icmpne -> 95
    //   85: aload_0
    //   86: aload_0
    //   87: getfield pos : I
    //   90: iconst_1
    //   91: isub
    //   92: putfield pos : I
    //   95: aload_0
    //   96: getfield pos : I
    //   99: aload_0
    //   100: getfield lineStartPos : I
    //   103: if_icmpge -> 165
    //   106: aload_0
    //   107: aload_0
    //   108: getfield lineNumber : I
    //   111: iconst_1
    //   112: isub
    //   113: putfield lineNumber : I
    //   116: aload_0
    //   117: getfield pos : I
    //   120: istore_1
    //   121: iload_1
    //   122: istore_2
    //   123: iload_1
    //   124: ifle -> 160
    //   127: aload_0
    //   128: getfield buffer : [C
    //   131: astore #4
    //   133: iload_1
    //   134: iconst_1
    //   135: isub
    //   136: istore_2
    //   137: aload #4
    //   139: iload_2
    //   140: caload
    //   141: istore_3
    //   142: iload_3
    //   143: bipush #13
    //   145: if_icmpeq -> 156
    //   148: iload_2
    //   149: istore_1
    //   150: iload_3
    //   151: bipush #10
    //   153: if_icmpne -> 121
    //   156: iload_2
    //   157: iconst_1
    //   158: iadd
    //   159: istore_2
    //   160: aload_0
    //   161: iload_2
    //   162: putfield lineStartPos : I
    //   165: return
  }
  
  public void unread_quick() {
    this.pos--;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/LineBufferedReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */