package gnu.xml;

import gnu.mapping.Symbol;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class XName extends Symbol implements Externalizable {
  NamespaceBinding namespaceNodes;
  
  public XName() {}
  
  public XName(Symbol paramSymbol, NamespaceBinding paramNamespaceBinding) {
    super(paramSymbol.getNamespace(), paramSymbol.getName());
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public static int checkName(String paramString) {
    int k = paramString.length();
    if (k == 0)
      return -1; 
    int i = 2;
    int j = 0;
    while (true) {
      int m = i;
      if (j < k) {
        boolean bool;
        if (j == 0) {
          bool = true;
        } else {
          bool = false;
        } 
        m = j + 1;
        char c = paramString.charAt(j);
        int n = c;
        j = m;
        if (c >= '?') {
          n = c;
          j = m;
          if (c < '?') {
            n = c;
            j = m;
            if (m < k) {
              n = (c - 55296) * 1024 + paramString.charAt(m) - 56320 + 65536;
              j = m + 1;
            } 
          } 
        } 
        if (n == 58) {
          m = i;
          if (i == 2)
            m = 1; 
        } else {
          if (!isNamePart(n))
            return -1; 
          m = i;
          if (bool) {
            m = i;
            if (!isNameStart(n))
              m = 0; 
          } 
        } 
        i = m;
        continue;
      } 
      return m;
    } 
  }
  
  public static boolean isNCName(String paramString) {
    return (checkName(paramString) > 1);
  }
  
  public static boolean isName(String paramString) {
    return (checkName(paramString) > 0);
  }
  
  public static boolean isNamePart(int paramInt) {
    return (Character.isUnicodeIdentifierPart(paramInt) || paramInt == 45 || paramInt == 46);
  }
  
  public static boolean isNameStart(int paramInt) {
    return (Character.isUnicodeIdentifierStart(paramInt) || paramInt == 95);
  }
  
  public static boolean isNmToken(String paramString) {
    return (checkName(paramString) >= 0);
  }
  
  public final NamespaceBinding getNamespaceNodes() {
    return this.namespaceNodes;
  }
  
  String lookupNamespaceURI(String paramString) {
    for (NamespaceBinding namespaceBinding = this.namespaceNodes; namespaceBinding != null; namespaceBinding = namespaceBinding.next) {
      if (paramString == namespaceBinding.prefix)
        return namespaceBinding.uri; 
    } 
    return null;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    super.readExternal(paramObjectInput);
    this.namespaceNodes = (NamespaceBinding)paramObjectInput.readObject();
  }
  
  public final void setNamespaceNodes(NamespaceBinding paramNamespaceBinding) {
    this.namespaceNodes = paramNamespaceBinding;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    super.writeExternal(paramObjectOutput);
    paramObjectOutput.writeObject(this.namespaceNodes);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/XName.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */