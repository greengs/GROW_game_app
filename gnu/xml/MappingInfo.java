package gnu.xml;

import gnu.mapping.Symbol;

final class MappingInfo {
  int index = -1;
  
  String local;
  
  NamespaceBinding namespaces;
  
  MappingInfo nextInBucket;
  
  String prefix;
  
  Symbol qname;
  
  int tagHash;
  
  XName type;
  
  String uri;
  
  static boolean equals(String paramString, StringBuffer paramStringBuffer) {
    int i = paramStringBuffer.length();
    if (paramString.length() == i) {
      int j = 0;
      while (j < i) {
        if (paramStringBuffer.charAt(j) == paramString.charAt(j)) {
          j++;
          continue;
        } 
        return false;
      } 
      return true;
    } 
    return false;
  }
  
  static boolean equals(String paramString, char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (paramString.length() == paramInt2) {
      int i = 0;
      while (i < paramInt2) {
        if (paramArrayOfchar[paramInt1 + i] == paramString.charAt(i)) {
          i++;
          continue;
        } 
        return false;
      } 
      return true;
    } 
    return false;
  }
  
  static int hash(String paramString1, String paramString2) {
    int j = paramString2.hashCode();
    int i = j;
    if (paramString1 != null)
      i = j ^ paramString1.hashCode(); 
    return i;
  }
  
  static int hash(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    int j = 0;
    int k = 0;
    int m = -1;
    for (int i = 0; i < paramInt2; i++) {
      char c = paramArrayOfchar[paramInt1 + i];
      if (c == ':' && m < 0) {
        m = i;
        c = Character.MIN_VALUE;
        k = j;
        j = c;
      } else {
        j = j * 31 + c;
      } 
    } 
    return k ^ j;
  }
  
  boolean match(char[] paramArrayOfchar, int paramInt1, int paramInt2) {
    if (this.prefix != null) {
      int i = this.local.length();
      int j = this.prefix.length();
      return (paramInt2 == j + 1 + i && paramArrayOfchar[j] == ':' && equals(this.prefix, paramArrayOfchar, paramInt1, j) && equals(this.local, paramArrayOfchar, paramInt1 + j + 1, i));
    } 
    return equals(this.local, paramArrayOfchar, paramInt1, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/MappingInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */