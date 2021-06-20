package gnu.mapping;

import gnu.lists.CharSeq;
import gnu.lists.FString;
import gnu.text.NullReader;
import gnu.text.Path;
import java.io.IOException;
import java.io.Reader;

public class CharArrayInPort extends InPort {
  static final Path stringPath = Path.valueOf("<string>");
  
  public CharArrayInPort(String paramString) {
    this(paramString.toCharArray());
  }
  
  public CharArrayInPort(char[] paramArrayOfchar) {
    this(paramArrayOfchar, paramArrayOfchar.length);
  }
  
  public CharArrayInPort(char[] paramArrayOfchar, int paramInt) {
    super((Reader)NullReader.nullReader, stringPath);
    try {
      setBuffer(paramArrayOfchar);
      this.limit = paramInt;
      return;
    } catch (IOException iOException) {
      throw new Error(iOException.toString());
    } 
  }
  
  public CharArrayInPort make(CharSequence paramCharSequence) {
    FString fString;
    if (paramCharSequence instanceof FString) {
      fString = (FString)paramCharSequence;
      return new CharArrayInPort(fString.data, fString.size);
    } 
    int i = fString.length();
    char[] arrayOfChar = new char[i];
    if (fString instanceof String) {
      ((String)fString).getChars(0, i, arrayOfChar, 0);
      return new CharArrayInPort(arrayOfChar, i);
    } 
    if (!(fString instanceof CharSeq)) {
      int j = i;
      while (true) {
        if (--j >= 0) {
          arrayOfChar[j] = fString.charAt(j);
          continue;
        } 
        return new CharArrayInPort(arrayOfChar, i);
      } 
    } 
    ((CharSeq)fString).getChars(0, i, arrayOfChar, 0);
    return new CharArrayInPort(arrayOfChar, i);
  }
  
  public int read() throws IOException {
    return (this.pos >= this.limit) ? -1 : super.read();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/CharArrayInPort.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */