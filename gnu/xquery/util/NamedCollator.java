package gnu.xquery.util;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.text.CollationKey;
import java.text.Collator;

public class NamedCollator extends Collator implements Externalizable {
  public static final String UNICODE_CODEPOINT_COLLATION = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
  
  public static final NamedCollator codepointCollation = new NamedCollator();
  
  Collator collator;
  
  String name;
  
  static {
    codepointCollation.name = "http://www.w3.org/2005/xpath-functions/collation/codepoint";
  }
  
  public static int codepointCompare(String paramString1, String paramString2) {
    int k = paramString1.length();
    int m = paramString2.length();
    int j = 0;
    int i = 0;
    while (true) {
      if (i == k)
        return (j == m) ? 0 : -1; 
      if (j == m)
        return 1; 
      int i1 = i + 1;
      char c1 = paramString1.charAt(i);
      int n = c1;
      i = i1;
      if (c1 >= '?') {
        n = c1;
        i = i1;
        if (c1 < '?') {
          n = c1;
          i = i1;
          if (i1 < k) {
            n = (c1 - 55296) * 1024 + paramString1.charAt(i1) - 56320 + 65536;
            i = i1 + 1;
          } 
        } 
      } 
      int i2 = j + 1;
      char c2 = paramString2.charAt(j);
      i1 = c2;
      j = i2;
      if (c2 >= '?') {
        i1 = c2;
        j = i2;
        if (c2 < '?') {
          i1 = c2;
          j = i2;
          if (i2 < m) {
            i1 = (c2 - 55296) * 1024 + paramString2.charAt(i2) - 56320 + 65536;
            j = i2 + 1;
          } 
        } 
      } 
      if (n != i1)
        return (n < i1) ? -1 : 1; 
    } 
  }
  
  public static NamedCollator find(String paramString) {
    return make(paramString);
  }
  
  public static NamedCollator make(String paramString) {
    NamedCollator namedCollator = new NamedCollator();
    namedCollator.name = paramString;
    namedCollator.resolve();
    return namedCollator;
  }
  
  public int compare(String paramString1, String paramString2) {
    return (this.collator != null) ? this.collator.compare(paramString1, paramString2) : codepointCompare(paramString1, paramString2);
  }
  
  public CollationKey getCollationKey(String paramString) {
    return this.collator.getCollationKey(paramString);
  }
  
  public String getName() {
    return this.name;
  }
  
  public int hashCode() {
    return (this.collator != null) ? this.collator.hashCode() : 0;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.name = paramObjectInput.readUTF();
    resolve();
  }
  
  public void resolve() {
    if (this.name != null && !this.name.equals("http://www.w3.org/2005/xpath-functions/collation/codepoint"))
      throw new RuntimeException("unknown collation: " + this.name); 
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(this.name);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/NamedCollator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */