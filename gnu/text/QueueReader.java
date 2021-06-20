package gnu.text;

import java.io.IOException;
import java.io.Reader;

public class QueueReader extends Reader implements Appendable {
  boolean EOFseen;
  
  char[] buffer;
  
  int limit;
  
  int mark;
  
  int pos;
  
  int readAheadLimit;
  
  public QueueReader append(char paramChar) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: invokevirtual reserveSpace : (I)V
    //   7: aload_0
    //   8: getfield buffer : [C
    //   11: astore_3
    //   12: aload_0
    //   13: getfield limit : I
    //   16: istore_2
    //   17: aload_0
    //   18: iload_2
    //   19: iconst_1
    //   20: iadd
    //   21: putfield limit : I
    //   24: aload_3
    //   25: iload_2
    //   26: iload_1
    //   27: castore
    //   28: aload_0
    //   29: invokevirtual notifyAll : ()V
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_0
    //   35: areturn
    //   36: astore_3
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_3
    //   40: athrow
    // Exception table:
    //   from	to	target	type
    //   2	24	36	finally
    //   28	32	36	finally
  }
  
  public QueueReader append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    return append(charSequence, 0, charSequence.length());
  }
  
  public QueueReader append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: astore #7
    //   5: aload_1
    //   6: ifnonnull -> 13
    //   9: ldc 'null'
    //   11: astore #7
    //   13: iload_3
    //   14: iload_2
    //   15: isub
    //   16: istore #6
    //   18: aload_0
    //   19: iload #6
    //   21: invokevirtual reserveSpace : (I)V
    //   24: aload_0
    //   25: getfield limit : I
    //   28: istore #5
    //   30: aload_0
    //   31: getfield buffer : [C
    //   34: astore_1
    //   35: aload #7
    //   37: instanceof java/lang/String
    //   40: ifeq -> 73
    //   43: aload #7
    //   45: checkcast java/lang/String
    //   48: iload_2
    //   49: iload_3
    //   50: aload_1
    //   51: iload #5
    //   53: invokevirtual getChars : (II[CI)V
    //   56: aload_0
    //   57: iload #5
    //   59: iload #6
    //   61: iadd
    //   62: putfield limit : I
    //   65: aload_0
    //   66: invokevirtual notifyAll : ()V
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_0
    //   72: areturn
    //   73: aload #7
    //   75: instanceof gnu/lists/CharSeq
    //   78: ifeq -> 104
    //   81: aload #7
    //   83: checkcast gnu/lists/CharSeq
    //   86: iload_2
    //   87: iload_3
    //   88: aload_1
    //   89: iload #5
    //   91: invokeinterface getChars : (II[CI)V
    //   96: goto -> 56
    //   99: astore_1
    //   100: aload_0
    //   101: monitorexit
    //   102: aload_1
    //   103: athrow
    //   104: iload_2
    //   105: istore #4
    //   107: iload #5
    //   109: istore_2
    //   110: iload #4
    //   112: iload_3
    //   113: if_icmpge -> 56
    //   116: aload_1
    //   117: iload_2
    //   118: aload #7
    //   120: iload #4
    //   122: invokeinterface charAt : (I)C
    //   127: castore
    //   128: iload #4
    //   130: iconst_1
    //   131: iadd
    //   132: istore #4
    //   134: iload_2
    //   135: iconst_1
    //   136: iadd
    //   137: istore_2
    //   138: goto -> 110
    // Exception table:
    //   from	to	target	type
    //   18	56	99	finally
    //   56	69	99	finally
    //   73	96	99	finally
    //   116	128	99	finally
  }
  
  public void append(char[] paramArrayOfchar) {
    append(paramArrayOfchar, 0, paramArrayOfchar.length);
  }
  
  public void append(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_3
    //   4: invokevirtual reserveSpace : (I)V
    //   7: aload_1
    //   8: iload_2
    //   9: aload_0
    //   10: getfield buffer : [C
    //   13: aload_0
    //   14: getfield limit : I
    //   17: iload_3
    //   18: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   21: aload_0
    //   22: aload_0
    //   23: getfield limit : I
    //   26: iload_3
    //   27: iadd
    //   28: putfield limit : I
    //   31: aload_0
    //   32: invokevirtual notifyAll : ()V
    //   35: aload_0
    //   36: monitorexit
    //   37: return
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: athrow
    // Exception table:
    //   from	to	target	type
    //   2	35	38	finally
  }
  
  public void appendEOF() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield EOFseen : Z
    //   7: aload_0
    //   8: monitorexit
    //   9: return
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	10	finally
  }
  
  public void checkAvailable() {}
  
  public void close() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_0
    //   4: putfield pos : I
    //   7: aload_0
    //   8: iconst_0
    //   9: putfield limit : I
    //   12: aload_0
    //   13: iconst_0
    //   14: putfield mark : I
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield EOFseen : Z
    //   22: aload_0
    //   23: aconst_null
    //   24: putfield buffer : [C
    //   27: aload_0
    //   28: monitorexit
    //   29: return
    //   30: astore_1
    //   31: aload_0
    //   32: monitorexit
    //   33: aload_1
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   2	27	30	finally
  }
  
  public void mark(int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iload_1
    //   4: putfield readAheadLimit : I
    //   7: aload_0
    //   8: aload_0
    //   9: getfield pos : I
    //   12: putfield mark : I
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_2
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_2
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  public boolean markSupported() {
    return true;
  }
  
  public int read() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield pos : I
    //   6: aload_0
    //   7: getfield limit : I
    //   10: if_icmplt -> 43
    //   13: aload_0
    //   14: getfield EOFseen : Z
    //   17: istore_2
    //   18: iload_2
    //   19: ifeq -> 28
    //   22: iconst_m1
    //   23: istore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_1
    //   27: ireturn
    //   28: aload_0
    //   29: invokevirtual checkAvailable : ()V
    //   32: aload_0
    //   33: invokevirtual wait : ()V
    //   36: goto -> 2
    //   39: astore_3
    //   40: goto -> 2
    //   43: aload_0
    //   44: getfield buffer : [C
    //   47: astore_3
    //   48: aload_0
    //   49: getfield pos : I
    //   52: istore_1
    //   53: aload_0
    //   54: iload_1
    //   55: iconst_1
    //   56: iadd
    //   57: putfield pos : I
    //   60: aload_3
    //   61: iload_1
    //   62: caload
    //   63: istore_1
    //   64: goto -> 24
    //   67: astore_3
    //   68: aload_0
    //   69: monitorexit
    //   70: aload_3
    //   71: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	67	finally
    //   28	32	67	finally
    //   32	36	39	java/lang/InterruptedException
    //   32	36	67	finally
    //   43	60	67	finally
  }
  
  public int read(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: iload_3
    //   3: ifne -> 20
    //   6: iconst_0
    //   7: istore_2
    //   8: aload_0
    //   9: monitorexit
    //   10: iload_2
    //   11: ireturn
    //   12: aload_0
    //   13: invokevirtual checkAvailable : ()V
    //   16: aload_0
    //   17: invokevirtual wait : ()V
    //   20: aload_0
    //   21: getfield pos : I
    //   24: aload_0
    //   25: getfield limit : I
    //   28: if_icmplt -> 43
    //   31: aload_0
    //   32: getfield EOFseen : Z
    //   35: ifeq -> 12
    //   38: iconst_m1
    //   39: istore_2
    //   40: goto -> 8
    //   43: aload_0
    //   44: getfield limit : I
    //   47: aload_0
    //   48: getfield pos : I
    //   51: isub
    //   52: istore #5
    //   54: iload_3
    //   55: istore #4
    //   57: iload_3
    //   58: iload #5
    //   60: if_icmple -> 67
    //   63: iload #5
    //   65: istore #4
    //   67: aload_0
    //   68: getfield buffer : [C
    //   71: aload_0
    //   72: getfield pos : I
    //   75: aload_1
    //   76: iload_2
    //   77: iload #4
    //   79: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   82: aload_0
    //   83: aload_0
    //   84: getfield pos : I
    //   87: iload #4
    //   89: iadd
    //   90: putfield pos : I
    //   93: iload #4
    //   95: istore_2
    //   96: goto -> 8
    //   99: astore #6
    //   101: goto -> 20
    //   104: astore_1
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: athrow
    // Exception table:
    //   from	to	target	type
    //   12	16	104	finally
    //   16	20	99	java/lang/InterruptedException
    //   16	20	104	finally
    //   20	38	104	finally
    //   43	54	104	finally
    //   67	93	104	finally
  }
  
  public boolean ready() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield pos : I
    //   6: aload_0
    //   7: getfield limit : I
    //   10: if_icmplt -> 22
    //   13: aload_0
    //   14: getfield EOFseen : Z
    //   17: istore_1
    //   18: iload_1
    //   19: ifeq -> 28
    //   22: iconst_1
    //   23: istore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: iload_1
    //   27: ireturn
    //   28: iconst_0
    //   29: istore_1
    //   30: goto -> 24
    //   33: astore_2
    //   34: aload_0
    //   35: monitorexit
    //   36: aload_2
    //   37: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	33	finally
  }
  
  protected void reserveSpace(int paramInt) {
    if (this.buffer == null) {
      this.buffer = new char[paramInt + 100];
      return;
    } 
    if (this.buffer.length < this.limit + paramInt) {
      resize(paramInt);
      return;
    } 
  }
  
  public void reset() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield readAheadLimit : I
    //   6: ifle -> 17
    //   9: aload_0
    //   10: aload_0
    //   11: getfield mark : I
    //   14: putfield pos : I
    //   17: aload_0
    //   18: monitorexit
    //   19: return
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	20	finally
  }
  
  void resize(int paramInt) {
    char[] arrayOfChar;
    int i = this.limit - this.pos;
    if (this.readAheadLimit > 0 && this.pos - this.mark <= this.readAheadLimit) {
      i = this.limit - this.mark;
    } else {
      this.mark = this.pos;
    } 
    if (this.buffer.length < i + paramInt) {
      arrayOfChar = new char[i * 2 + paramInt];
    } else {
      arrayOfChar = this.buffer;
    } 
    System.arraycopy(this.buffer, this.mark, arrayOfChar, 0, i);
    this.buffer = arrayOfChar;
    this.pos -= this.mark;
    this.mark = 0;
    this.limit = i;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/QueueReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */