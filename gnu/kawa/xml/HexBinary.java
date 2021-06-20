package gnu.kawa.xml;

public class HexBinary extends BinaryObject {
  public HexBinary(byte[] paramArrayOfbyte) {
    this.data = paramArrayOfbyte;
  }
  
  static char forHexDigit(int paramInt) {
    return (paramInt < 10) ? (char)(paramInt + 48) : (char)(paramInt - 10 + 65);
  }
  
  static byte[] parseHexBinary(String paramString) {
    paramString = paramString.trim();
    int i = paramString.length();
    if ((i & 0x1) != 0)
      throw new IllegalArgumentException("hexBinary string length not a multiple of 2"); 
    int k = i >> 1;
    byte[] arrayOfByte = new byte[k];
    for (int j = 0; j < k; j++) {
      int m = Character.digit(paramString.charAt(j * 2), 16);
      int n = Character.digit(paramString.charAt(j * 2 + 1), 16);
      i = -1;
      if (m < 0) {
        i = j * 2;
      } else if (n < 0) {
        i = j * 2 + 1;
      } 
      if (i >= 0)
        throw new IllegalArgumentException("invalid hexBinary character at position " + i); 
      arrayOfByte[j] = (byte)(m * 16 + n);
    } 
    return arrayOfByte;
  }
  
  static HexBinary valueOf(String paramString) {
    return new HexBinary(parseHexBinary(paramString));
  }
  
  public String toString() {
    return toString(new StringBuffer()).toString();
  }
  
  public StringBuffer toString(StringBuffer paramStringBuffer) {
    for (byte b : this.data) {
      paramStringBuffer.append(forHexDigit(b >> 4 & 0xF));
      paramStringBuffer.append(forHexDigit(b & 0xF));
    } 
    return paramStringBuffer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/HexBinary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */