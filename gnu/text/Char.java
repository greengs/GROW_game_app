package gnu.text;

import gnu.lists.Consumer;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Char implements Comparable, Externalizable {
  static Char[] ascii;
  
  static char[] charNameValues;
  
  static String[] charNames;
  
  static CharMap hashTable = new CharMap();
  
  int value;
  
  static {
    ascii = new Char[128];
    int i = 128;
    while (true) {
      if (--i >= 0) {
        ascii[i] = new Char(i);
        continue;
      } 
      charNameValues = new char[] { 
          ' ', '\t', '\n', '\n', '\r', '\f', '\b', '\033', '', '', 
          '', '\007', '\007', '\013', Character.MIN_VALUE };
      charNames = new String[] { 
          "space", "tab", "newline", "linefeed", "return", "page", "backspace", "esc", "delete", "del", 
          "rubout", "alarm", "bel", "vtab", "nul" };
      return;
    } 
  }
  
  public Char() {}
  
  Char(int paramInt) {
    this.value = paramInt;
  }
  
  public static Char make(int paramInt) {
    if (paramInt < 128)
      return ascii[paramInt]; 
    synchronized (hashTable) {
      return hashTable.get(paramInt);
    } 
  }
  
  public static int nameToChar(String paramString) {
    int i = charNames.length;
    while (true) {
      int j = i - 1;
      if (j >= 0) {
        i = j;
        if (charNames[j].equals(paramString))
          return charNameValues[j]; 
        continue;
      } 
      i = charNames.length;
      while (true) {
        j = i - 1;
        if (j >= 0) {
          i = j;
          if (charNames[j].equalsIgnoreCase(paramString))
            return charNameValues[j]; 
          continue;
        } 
        int k = paramString.length();
        if (k > 1) {
          if (paramString.charAt(0) == 'u') {
            i = 0;
            j = 1;
            while (true) {
              int m = i;
              if (j != k) {
                m = Character.digit(paramString.charAt(j), 16);
                if (m < 0) {
                  if (k == 3 && paramString.charAt(1) == '-') {
                    i = paramString.charAt(0);
                    if (i == 99 || i == 67)
                      return paramString.charAt(2) & 0x1F; 
                  } 
                  break;
                } 
                i = (i << 4) + m;
                j++;
                continue;
              } 
              return m;
            } 
            return -1;
          } 
          continue;
        } 
        continue;
      } 
      break;
    } 
  }
  
  public static void print(int paramInt, Consumer paramConsumer) {
    if (paramInt >= 65536) {
      paramConsumer.write((char)((paramInt - 65536 >> 10) + 55296));
      paramConsumer.write((char)((paramInt & 0x3FF) + 56320));
      return;
    } 
    paramConsumer.write((char)paramInt);
  }
  
  public static String toScmReadableString(int paramInt) {
    StringBuffer stringBuffer = new StringBuffer(20);
    stringBuffer.append("#\\");
    for (int i = 0; i < charNameValues.length; i++) {
      if ((char)paramInt == charNameValues[i]) {
        stringBuffer.append(charNames[i]);
        return stringBuffer.toString();
      } 
    } 
    if (paramInt < 32 || paramInt > 127) {
      stringBuffer.append('x');
      stringBuffer.append(Integer.toString(paramInt, 16));
      return stringBuffer.toString();
    } 
    stringBuffer.append((char)paramInt);
    return stringBuffer.toString();
  }
  
  public final char charValue() {
    return (char)this.value;
  }
  
  public int compareTo(Object paramObject) {
    return this.value - ((Char)paramObject).value;
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject != null && paramObject instanceof Char && ((Char)paramObject).intValue() == this.value);
  }
  
  public int hashCode() {
    return this.value;
  }
  
  public final int intValue() {
    return this.value;
  }
  
  public void print(Consumer paramConsumer) {
    print(this.value, paramConsumer);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.value = paramObjectInput.readChar();
    if (this.value >= 55296 && this.value < 56319) {
      char c = paramObjectInput.readChar();
      if (c >= '?' && c <= '?')
        this.value = (this.value - 55296 << 10) + c - 56320 + 65536; 
    } 
  }
  
  public Object readResolve() throws ObjectStreamException {
    return make(this.value);
  }
  
  public String toString() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append('\'');
    if (this.value >= 32 && this.value < 127 && this.value != 39) {
      stringBuffer.append((char)this.value);
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
    stringBuffer.append('\\');
    if (this.value == 39) {
      stringBuffer.append('\'');
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
    if (this.value == 10) {
      stringBuffer.append('n');
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
    if (this.value == 13) {
      stringBuffer.append('r');
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
    if (this.value == 9) {
      stringBuffer.append('t');
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
    if (this.value < 256) {
      String str1 = Integer.toOctalString(this.value);
      int j = 3 - str1.length();
      while (true) {
        if (--j >= 0) {
          stringBuffer.append('0');
          continue;
        } 
        stringBuffer.append(str1);
        stringBuffer.append('\'');
        return stringBuffer.toString();
      } 
    } 
    stringBuffer.append('u');
    String str = Integer.toHexString(this.value);
    int i = 4 - str.length();
    while (true) {
      if (--i >= 0) {
        stringBuffer.append('0');
        continue;
      } 
      stringBuffer.append(str);
      stringBuffer.append('\'');
      return stringBuffer.toString();
    } 
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    if (this.value > 55296)
      if (this.value > 65535) {
        paramObjectOutput.writeChar((this.value - 65536 >> 10) + 55296);
        this.value = (this.value & 0x3FF) + 56320;
      } else if (this.value <= 56319) {
        paramObjectOutput.writeChar(this.value);
        this.value = 0;
      }  
    paramObjectOutput.writeChar(this.value);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/Char.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */