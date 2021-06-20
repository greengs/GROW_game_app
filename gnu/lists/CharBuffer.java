package gnu.lists;

import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;

public class CharBuffer extends StableVector implements CharSeq, Serializable {
  private FString string;
  
  protected CharBuffer() {}
  
  public CharBuffer(int paramInt) {
    this(new FString(paramInt));
  }
  
  public CharBuffer(FString paramFString) {
    super(paramFString);
    this.string = paramFString;
  }
  
  public char charAt(int paramInt) {
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    return this.string.charAt(i);
  }
  
  public void consume(int paramInt1, int paramInt2, Consumer paramConsumer) {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < this.gapStart) {
      j = this.gapStart - paramInt1;
      i = j;
      if (j > paramInt2)
        i = paramInt2; 
      paramConsumer.write(arrayOfChar, paramInt1, i);
      i = paramInt2 - i;
      j = paramInt1 + i;
    } 
    if (i > 0)
      paramConsumer.write(arrayOfChar, j + this.gapEnd - this.gapStart, i); 
  }
  
  public void delete(int paramInt1, int paramInt2) {
    paramInt1 = createPos(paramInt1, false);
    removePos(paramInt1, paramInt2);
    releasePos(paramInt1);
  }
  
  public void dump() {
    int i = 0;
    System.err.println("Buffer Content dump.  size:" + size() + "  buffer:" + (getArray()).length);
    System.err.print("before gap: \"");
    System.err.print(new String(getArray(), 0, this.gapStart));
    System.err.println("\" (gapStart:" + this.gapStart + " gapEnd:" + this.gapEnd + ')');
    System.err.print("after gap: \"");
    System.err.print(new String(getArray(), this.gapEnd, (getArray()).length - this.gapEnd));
    System.err.println("\"");
    if (this.positions != null)
      i = this.positions.length; 
    System.err.println("Positions (size: " + i + " free:" + this.free + "):");
    boolean[] arrayOfBoolean = null;
    if (this.free != -2) {
      boolean[] arrayOfBoolean1 = new boolean[this.positions.length];
      int k = this.free;
      while (true) {
        arrayOfBoolean = arrayOfBoolean1;
        if (k >= 0) {
          arrayOfBoolean1[k] = true;
          k = this.positions[k];
          continue;
        } 
        break;
      } 
    } 
    for (int j = 0; j < i; j++) {
      int k = this.positions[j];
      if ((this.free == -2) ? (k != -2) : !arrayOfBoolean[j])
        System.err.println("position#" + j + ": " + (k >> 1) + " isAfter:" + (k & 0x1)); 
    } 
  }
  
  public final void fill(char paramChar) {
    char[] arrayOfChar = this.string.data;
    int i = arrayOfChar.length;
    while (true) {
      if (--i >= this.gapEnd) {
        arrayOfChar[i] = paramChar;
        continue;
      } 
      i = this.gapStart;
      while (true) {
        if (--i >= 0) {
          arrayOfChar[i] = paramChar;
          continue;
        } 
        break;
      } 
      return;
    } 
  }
  
  public void fill(int paramInt1, int paramInt2, char paramChar) {
    char[] arrayOfChar = this.string.data;
    int i = paramInt1;
    if (this.gapStart < paramInt2) {
      paramInt1 = this.gapStart;
    } else {
      paramInt1 = paramInt2;
    } 
    while (i < paramInt1) {
      arrayOfChar[i] = paramChar;
      i++;
    } 
    for (i = paramInt1 + this.gapEnd - this.gapStart; i < paramInt1 + paramInt2; i++)
      arrayOfChar[i] = paramChar; 
  }
  
  public char[] getArray() {
    return (char[])this.base.getBuffer();
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3) {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt3;
    if (paramInt1 < this.gapStart) {
      if (paramInt2 < this.gapStart) {
        i = paramInt2;
      } else {
        i = this.gapStart;
      } 
      int k = i - paramInt1;
      j = paramInt1;
      i = paramInt3;
      if (k > 0) {
        System.arraycopy(arrayOfChar, paramInt1, paramArrayOfchar, paramInt3, k);
        j = paramInt1 + k;
        i = paramInt3 + k;
      } 
    } 
    paramInt1 = this.gapEnd - this.gapStart;
    paramInt3 = j + paramInt1;
    paramInt1 = paramInt2 + paramInt1 - paramInt3;
    if (paramInt1 > 0)
      System.arraycopy(arrayOfChar, paramInt3, paramArrayOfchar, i, paramInt1); 
  }
  
  public int indexOf(int paramInt1, int paramInt2) {
    char c;
    boolean bool;
    if (paramInt1 >= 65536) {
      c = (char)((paramInt1 - 65536 >> 10) + 55296);
      bool = (char)((paramInt1 & 0x3FF) + 56320);
    } else {
      c = (char)paramInt1;
      bool = false;
    } 
    char[] arrayOfChar = getArray();
    int i = paramInt2;
    int j = this.gapStart;
    paramInt1 = i;
    paramInt2 = j;
    if (i >= j) {
      paramInt1 = i + this.gapEnd - this.gapStart;
      paramInt2 = arrayOfChar.length;
    } 
    while (true) {
      j = paramInt1;
      i = paramInt2;
      if (paramInt1 == paramInt2) {
        i = arrayOfChar.length;
        if (paramInt1 < i) {
          j = this.gapEnd;
        } else {
          return -1;
        } 
      } 
      if (arrayOfChar[j] == c && (!bool || ((j + 1 < i) ? (arrayOfChar[j + 1] == bool) : (this.gapEnd < arrayOfChar.length && arrayOfChar[this.gapEnd] == bool)))) {
        if (j > this.gapStart)
          return j - this.gapEnd - this.gapStart; 
      } else {
        paramInt1 = j + 1;
        paramInt2 = i;
        continue;
      } 
      return j;
    } 
  }
  
  public void insert(int paramInt, String paramString, boolean paramBoolean) {
    int i = paramString.length();
    gapReserve(paramInt, i);
    paramString.getChars(0, i, this.string.data, paramInt);
    this.gapStart += i;
  }
  
  public int lastIndexOf(int paramInt1, int paramInt2) {
    char c;
    if (paramInt1 >= 65536) {
      char c1 = (char)((paramInt1 - 65536 >> 10) + 55296);
      c = (char)((paramInt1 & 0x3FF) + 56320);
      paramInt1 = c1;
    } else {
      c = Character.MIN_VALUE;
      char c1 = (char)paramInt1;
      paramInt1 = c;
      c = c1;
    } 
    while (true) {
      int i = paramInt2 - 1;
      if (i >= 0) {
        paramInt2 = i;
        if (charAt(i) == c) {
          if (paramInt1 == 0)
            return i; 
          paramInt2 = i;
          if (i > 0) {
            paramInt2 = i;
            if (charAt(i - 1) == paramInt1)
              return i - 1; 
          } 
        } 
        continue;
      } 
      return -1;
    } 
  }
  
  public int length() {
    return size();
  }
  
  public void setCharAt(int paramInt, char paramChar) {
    int i = paramInt;
    if (paramInt >= this.gapStart)
      i = paramInt + this.gapEnd - this.gapStart; 
    this.string.setCharAt(i, paramChar);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    int i = size();
    if (paramInt1 < 0 || paramInt2 < paramInt1 || paramInt2 > i)
      throw new IndexOutOfBoundsException(); 
    return new SubCharSeq(this, this.base.createPos(paramInt1, false), this.base.createPos(paramInt2, true));
  }
  
  public String substring(int paramInt1, int paramInt2) {
    int i = size();
    if (paramInt1 < 0 || paramInt2 < paramInt1 || paramInt2 > i)
      throw new IndexOutOfBoundsException(); 
    paramInt2 -= paramInt1;
    paramInt1 = getSegment(paramInt1, paramInt2);
    return new String(getArray(), paramInt1, paramInt2);
  }
  
  public String toString() {
    int i = size();
    int j = getSegment(0, i);
    return new String(getArray(), j, i);
  }
  
  public void writeTo(int paramInt1, int paramInt2, Writer paramWriter) throws IOException {
    char[] arrayOfChar = this.string.data;
    int j = paramInt1;
    int i = paramInt2;
    if (paramInt1 < this.gapStart) {
      j = this.gapStart - paramInt1;
      i = j;
      if (j > paramInt2)
        i = paramInt2; 
      paramWriter.write(arrayOfChar, paramInt1, i);
      i = paramInt2 - i;
      j = paramInt1 + i;
    } 
    if (i > 0)
      paramWriter.write(arrayOfChar, j + this.gapEnd - this.gapStart, i); 
  }
  
  public void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable) throws IOException {
    if (paramAppendable instanceof Writer) {
      writeTo(paramInt1, paramInt2, (Writer)paramAppendable);
      return;
    } 
    paramAppendable.append(this, paramInt1, paramInt1 + paramInt2);
  }
  
  public void writeTo(Writer paramWriter) throws IOException {
    char[] arrayOfChar = this.string.data;
    paramWriter.write(arrayOfChar, 0, this.gapStart);
    paramWriter.write(arrayOfChar, this.gapEnd, arrayOfChar.length - this.gapEnd);
  }
  
  public void writeTo(Appendable paramAppendable) throws IOException {
    writeTo(0, size(), paramAppendable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/CharBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */