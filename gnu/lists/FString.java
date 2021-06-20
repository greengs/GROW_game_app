package gnu.lists;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.Writer;

public class FString extends SimpleVector implements Comparable, Appendable, CharSeq, Externalizable, Consumable {
  protected static char[] empty = new char[0];
  
  public char[] data;
  
  public FString() {
    this.data = empty;
  }
  
  public FString(int paramInt) {
    this.size = paramInt;
    this.data = new char[paramInt];
  }
  
  public FString(int paramInt, char paramChar) {
    char[] arrayOfChar = new char[paramInt];
    this.data = arrayOfChar;
    this.size = paramInt;
    while (true) {
      if (--paramInt >= 0) {
        arrayOfChar[paramInt] = paramChar;
        continue;
      } 
      break;
    } 
  }
  
  public FString(CharSeq paramCharSeq) {
    this(paramCharSeq, 0, paramCharSeq.size());
  }
  
  public FString(CharSeq paramCharSeq, int paramInt1, int paramInt2) {
    char[] arrayOfChar = new char[paramInt2];
    paramCharSeq.getChars(paramInt1, paramInt1 + paramInt2, arrayOfChar, 0);
    this.data = arrayOfChar;
    this.size = paramInt2;
  }
  
  public FString(Sequence paramSequence) {
    this.data = new char[paramSequence.size()];
    addAll(paramSequence);
  }
  
  public FString(CharSequence paramCharSequence) {
    this(paramCharSequence, 0, paramCharSequence.length());
  }
  
  public FString(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    char[] arrayOfChar = new char[paramInt2];
    int i = paramInt2;
    while (true) {
      if (--i >= 0) {
        arrayOfChar[i] = paramCharSequence.charAt(paramInt1 + i);
        continue;
      } 
      this.data = arrayOfChar;
      this.size = paramInt2;
      return;
    } 
  }
  
  public FString(String paramString) {
    this.data = paramString.toCharArray();
    this.size = this.data.length;
  }
  
  public FString(StringBuffer paramStringBuffer) {
    this(paramStringBuffer, 0, paramStringBuffer.length());
  }
  
  public FString(StringBuffer paramStringBuffer, int paramInt1, int paramInt2) {
    this.size = paramInt2;
    this.data = new char[paramInt2];
    if (paramInt2 > 0)
      paramStringBuffer.getChars(paramInt1, paramInt1 + paramInt2, this.data, 0); 
  }
  
  public FString(char[] paramArrayOfchar) {
    this.size = paramArrayOfchar.length;
    this.data = paramArrayOfchar;
  }
  
  public FString(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    this.size = paramInt2;
    this.data = new char[paramInt2];
    System.arraycopy(paramArrayOfchar, paramInt1, this.data, 0, paramInt2);
  }
  
  public boolean addAll(FString paramFString) {
    boolean bool = false;
    int i = this.size + paramFString.size;
    if (this.data.length < i)
      setBufferLength(i); 
    System.arraycopy(paramFString.data, 0, this.data, this.size, paramFString.size);
    this.size = i;
    if (paramFString.size > 0)
      bool = true; 
    return bool;
  }
  
  public boolean addAll(CharSequence paramCharSequence) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #5
    //   3: aload_1
    //   4: invokeinterface length : ()I
    //   9: istore_3
    //   10: aload_0
    //   11: getfield size : I
    //   14: iload_3
    //   15: iadd
    //   16: istore #4
    //   18: aload_0
    //   19: getfield data : [C
    //   22: arraylength
    //   23: iload #4
    //   25: if_icmpge -> 34
    //   28: aload_0
    //   29: iload #4
    //   31: invokevirtual setBufferLength : (I)V
    //   34: aload_1
    //   35: instanceof gnu/lists/FString
    //   38: ifeq -> 77
    //   41: aload_1
    //   42: checkcast gnu/lists/FString
    //   45: getfield data : [C
    //   48: iconst_0
    //   49: aload_0
    //   50: getfield data : [C
    //   53: aload_0
    //   54: getfield size : I
    //   57: iload_3
    //   58: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   61: aload_0
    //   62: iload #4
    //   64: putfield size : I
    //   67: iload_3
    //   68: ifle -> 74
    //   71: iconst_1
    //   72: istore #5
    //   74: iload #5
    //   76: ireturn
    //   77: aload_1
    //   78: instanceof java/lang/String
    //   81: ifeq -> 104
    //   84: aload_1
    //   85: checkcast java/lang/String
    //   88: iconst_0
    //   89: iload_3
    //   90: aload_0
    //   91: getfield data : [C
    //   94: aload_0
    //   95: getfield size : I
    //   98: invokevirtual getChars : (II[CI)V
    //   101: goto -> 61
    //   104: aload_1
    //   105: instanceof gnu/lists/CharSeq
    //   108: ifeq -> 133
    //   111: aload_1
    //   112: checkcast gnu/lists/CharSeq
    //   115: iconst_0
    //   116: iload_3
    //   117: aload_0
    //   118: getfield data : [C
    //   121: aload_0
    //   122: getfield size : I
    //   125: invokeinterface getChars : (II[CI)V
    //   130: goto -> 61
    //   133: iload_3
    //   134: istore_2
    //   135: iload_2
    //   136: iconst_1
    //   137: isub
    //   138: istore_2
    //   139: iload_2
    //   140: iflt -> 61
    //   143: aload_0
    //   144: getfield data : [C
    //   147: aload_0
    //   148: getfield size : I
    //   151: iload_2
    //   152: iadd
    //   153: aload_1
    //   154: iload_2
    //   155: invokeinterface charAt : (I)C
    //   160: castore
    //   161: goto -> 135
  }
  
  public void addAllStrings(Object[] paramArrayOfObject, int paramInt) {
    int j = this.size;
    for (int i = paramInt; i < paramArrayOfObject.length; i++)
      j += ((CharSequence)paramArrayOfObject[i]).length(); 
    if (this.data.length < j)
      setBufferLength(j); 
    while (paramInt < paramArrayOfObject.length) {
      addAll((CharSequence)paramArrayOfObject[paramInt]);
      paramInt++;
    } 
  }
  
  public FString append(char paramChar) {
    int i = this.size;
    if (i >= this.data.length)
      ensureBufferLength(i + 1); 
    this.data[i] = paramChar;
    this.size = i + 1;
    return this;
  }
  
  public FString append(CharSequence paramCharSequence) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    return append(charSequence, 0, charSequence.length());
  }
  
  public FString append(CharSequence paramCharSequence, int paramInt1, int paramInt2) {
    CharSequence charSequence = paramCharSequence;
    if (paramCharSequence == null)
      charSequence = "null"; 
    int i = paramInt2 - paramInt1;
    int j = this.size;
    if (j + i > this.data.length)
      ensureBufferLength(j + i); 
    char[] arrayOfChar = this.data;
    if (charSequence instanceof String) {
      ((String)charSequence).getChars(paramInt1, paramInt2, arrayOfChar, j);
      this.size = j;
      return this;
    } 
    if (charSequence instanceof CharSeq) {
      ((CharSeq)charSequence).getChars(paramInt1, paramInt2, arrayOfChar, j);
      this.size = j;
      return this;
    } 
    i = paramInt1;
    paramInt1 = j;
    while (true) {
      if (i < paramInt2) {
        arrayOfChar[paramInt1] = charSequence.charAt(i);
        i++;
        paramInt1++;
        continue;
      } 
      this.size = j;
      return this;
    } 
  }
  
  public final char charAt(int paramInt) {
    if (paramInt >= this.size)
      throw new StringIndexOutOfBoundsException(paramInt); 
    return this.data[paramInt];
  }
  
  public final char charAtBuffer(int paramInt) {
    return this.data[paramInt];
  }
  
  protected void clearBuffer(int paramInt1, int paramInt2) {
    char[] arrayOfChar = this.data;
    while (true) {
      if (--paramInt2 >= 0) {
        arrayOfChar[paramInt1] = Character.MIN_VALUE;
        paramInt1++;
        continue;
      } 
      break;
    } 
  }
  
  public int compareTo(Object paramObject) {
    int i;
    paramObject = paramObject;
    char[] arrayOfChar1 = this.data;
    char[] arrayOfChar2 = ((FString)paramObject).data;
    int k = this.size;
    int m = ((FString)paramObject).size;
    if (k > m) {
      i = m;
    } else {
      i = k;
    } 
    for (int j = 0; j < i; j++) {
      int n = arrayOfChar1[j] - arrayOfChar2[j];
      if (n != 0)
        return n; 
    } 
    return k - m;
  }
  
  public void consume(Consumer paramConsumer) {
    paramConsumer.write(this.data, 0, this.data.length);
  }
  
  public boolean consumeNext(int paramInt, Consumer paramConsumer) {
    paramInt >>>= 1;
    if (paramInt >= this.size)
      return false; 
    paramConsumer.write(this.data[paramInt]);
    return true;
  }
  
  public void consumePosRange(int paramInt1, int paramInt2, Consumer paramConsumer) {
    if (!paramConsumer.ignoring()) {
      int i = paramInt1 >>> 1;
      paramInt2 >>>= 1;
      paramInt1 = paramInt2;
      if (paramInt2 > this.size)
        paramInt1 = this.size; 
      if (paramInt1 > i) {
        paramConsumer.write(this.data, i, paramInt1 - i);
        return;
      } 
    } 
  }
  
  public FString copy(int paramInt1, int paramInt2) {
    char[] arrayOfChar1 = new char[paramInt2 - paramInt1];
    char[] arrayOfChar2 = this.data;
    for (int i = paramInt1; i < paramInt2; i++)
      arrayOfChar1[i - paramInt1] = arrayOfChar2[i]; 
    return new FString(arrayOfChar1);
  }
  
  public void ensureBufferLength(int paramInt) {
    if (paramInt > this.data.length) {
      int i;
      if (paramInt < 60) {
        i = 120;
      } else {
        i = paramInt * 2;
      } 
      char[] arrayOfChar = new char[i];
      System.arraycopy(this.data, 0, arrayOfChar, 0, paramInt);
      this.data = arrayOfChar;
    } 
  }
  
  public boolean equals(Object paramObject) {
    if (paramObject != null && paramObject instanceof FString) {
      paramObject = ((FString)paramObject).data;
      int i = this.size;
      if (paramObject != null && paramObject.length == i) {
        char[] arrayOfChar = this.data;
        while (true) {
          int j = i - 1;
          if (j >= 0) {
            i = j;
            if (arrayOfChar[j] != paramObject[j])
              return false; 
            continue;
          } 
          return true;
        } 
      } 
    } 
    return false;
  }
  
  public final void fill(char paramChar) {
    char[] arrayOfChar = this.data;
    int i = this.size;
    while (true) {
      if (--i >= 0) {
        arrayOfChar[i] = paramChar;
        continue;
      } 
      break;
    } 
  }
  
  public void fill(int paramInt1, int paramInt2, char paramChar) {
    if (paramInt1 < 0 || paramInt2 > this.size)
      throw new IndexOutOfBoundsException(); 
    char[] arrayOfChar = this.data;
    while (paramInt1 < paramInt2) {
      arrayOfChar[paramInt1] = paramChar;
      paramInt1++;
    } 
  }
  
  public final Object get(int paramInt) {
    if (paramInt >= this.size)
      throw new ArrayIndexOutOfBoundsException(); 
    return Convert.toObject(this.data[paramInt]);
  }
  
  protected Object getBuffer() {
    return this.data;
  }
  
  public final Object getBuffer(int paramInt) {
    return Convert.toObject(this.data[paramInt]);
  }
  
  public int getBufferLength() {
    return this.data.length;
  }
  
  public void getChars(int paramInt1, int paramInt2, StringBuffer paramStringBuffer) {
    if (paramInt1 < 0 || paramInt1 > paramInt2)
      throw new StringIndexOutOfBoundsException(paramInt1); 
    if (paramInt2 > this.size)
      throw new StringIndexOutOfBoundsException(paramInt2); 
    if (paramInt1 < paramInt2)
      paramStringBuffer.append(this.data, paramInt1, paramInt2 - paramInt1); 
  }
  
  public void getChars(int paramInt1, int paramInt2, char[] paramArrayOfchar, int paramInt3) {
    if (paramInt1 < 0 || paramInt1 > paramInt2)
      throw new StringIndexOutOfBoundsException(paramInt1); 
    if (paramInt2 > this.size)
      throw new StringIndexOutOfBoundsException(paramInt2); 
    if (paramInt3 + paramInt2 - paramInt1 > paramArrayOfchar.length)
      throw new StringIndexOutOfBoundsException(paramInt3); 
    if (paramInt1 < paramInt2)
      System.arraycopy(this.data, paramInt1, paramArrayOfchar, paramInt3, paramInt2 - paramInt1); 
  }
  
  public void getChars(StringBuffer paramStringBuffer) {
    paramStringBuffer.append(this.data, 0, this.size);
  }
  
  public int getElementKind() {
    return 29;
  }
  
  public int hashCode() {
    char[] arrayOfChar = this.data;
    int k = this.size;
    int j = 0;
    for (int i = 0; i < k; i++)
      j = j * 31 + arrayOfChar[i]; 
    return j;
  }
  
  public int length() {
    return this.size;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    int j = paramObjectInput.readInt();
    char[] arrayOfChar = new char[j];
    for (int i = 0; i < j; i++)
      arrayOfChar[i] = paramObjectInput.readChar(); 
    this.data = arrayOfChar;
    this.size = j;
  }
  
  public void replace(int paramInt, String paramString) {
    paramString.getChars(0, paramString.length(), this.data, paramInt);
  }
  
  public void replace(int paramInt1, char[] paramArrayOfchar, int paramInt2, int paramInt3) {
    System.arraycopy(paramArrayOfchar, paramInt2, this.data, paramInt1, paramInt3);
  }
  
  public final Object setBuffer(int paramInt, Object paramObject) {
    Object object = Convert.toObject(this.data[paramInt]);
    this.data[paramInt] = Convert.toChar(paramObject);
    return object;
  }
  
  public void setBufferLength(int paramInt) {
    int i = this.data.length;
    if (i != paramInt) {
      char[] arrayOfChar1 = new char[paramInt];
      char[] arrayOfChar2 = this.data;
      if (i < paramInt)
        paramInt = i; 
      System.arraycopy(arrayOfChar2, 0, arrayOfChar1, 0, paramInt);
      this.data = arrayOfChar1;
    } 
  }
  
  public void setCharAt(int paramInt, char paramChar) {
    if (paramInt < 0 || paramInt >= this.size)
      throw new StringIndexOutOfBoundsException(paramInt); 
    this.data[paramInt] = paramChar;
  }
  
  public void setCharAtBuffer(int paramInt, char paramChar) {
    this.data[paramInt] = paramChar;
  }
  
  public void shift(int paramInt1, int paramInt2, int paramInt3) {
    System.arraycopy(this.data, paramInt1, this.data, paramInt2, paramInt3);
  }
  
  public CharSequence subSequence(int paramInt1, int paramInt2) {
    return new FString(this.data, paramInt1, paramInt2 - paramInt1);
  }
  
  public String substring(int paramInt1, int paramInt2) {
    return new String(this.data, paramInt1, paramInt2 - paramInt1);
  }
  
  public char[] toCharArray() {
    int i = this.data.length;
    int j = this.size;
    if (j == i)
      return this.data; 
    char[] arrayOfChar = new char[j];
    System.arraycopy(this.data, 0, arrayOfChar, 0, j);
    return arrayOfChar;
  }
  
  public String toString() {
    return new String(this.data, 0, this.size);
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    int j = this.size;
    paramObjectOutput.writeInt(j);
    char[] arrayOfChar = this.data;
    for (int i = 0; i < j; i++)
      paramObjectOutput.writeChar(arrayOfChar[i]); 
  }
  
  public void writeTo(int paramInt1, int paramInt2, Appendable paramAppendable) throws IOException {
    if (paramAppendable instanceof Writer)
      try {
        ((Writer)paramAppendable).write(this.data, paramInt1, paramInt2);
        return;
      } catch (IOException iOException) {
        throw new RuntimeException(iOException);
      }  
    iOException.append(this, paramInt1, paramInt1 + paramInt2);
  }
  
  public void writeTo(Appendable paramAppendable) throws IOException {
    writeTo(0, this.size, paramAppendable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/lists/FString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */