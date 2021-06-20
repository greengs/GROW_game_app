package gnu.xml;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public final class NamespaceBinding implements Externalizable {
  public static final String XML_NAMESPACE = "http://www.w3.org/XML/1998/namespace";
  
  public static final NamespaceBinding predefinedXML = new NamespaceBinding("xml", "http://www.w3.org/XML/1998/namespace", null);
  
  int depth;
  
  NamespaceBinding next;
  
  String prefix;
  
  String uri;
  
  public NamespaceBinding(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding) {
    this.prefix = paramString1;
    this.uri = paramString2;
    setNext(paramNamespaceBinding);
  }
  
  public static NamespaceBinding commonAncestor(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    NamespaceBinding namespaceBinding2 = paramNamespaceBinding1;
    NamespaceBinding namespaceBinding1 = paramNamespaceBinding2;
    if (paramNamespaceBinding1.depth > paramNamespaceBinding2.depth) {
      namespaceBinding1 = paramNamespaceBinding1;
      namespaceBinding2 = paramNamespaceBinding2;
    } 
    while (true) {
      paramNamespaceBinding1 = namespaceBinding2;
      paramNamespaceBinding2 = namespaceBinding1;
      if (namespaceBinding1.depth > namespaceBinding2.depth) {
        namespaceBinding1 = namespaceBinding1.next;
        continue;
      } 
      break;
    } 
    while (paramNamespaceBinding1 != paramNamespaceBinding2) {
      paramNamespaceBinding1 = paramNamespaceBinding1.next;
      paramNamespaceBinding2 = paramNamespaceBinding2.next;
    } 
    return paramNamespaceBinding1;
  }
  
  public static NamespaceBinding maybeAdd(String paramString1, String paramString2, NamespaceBinding paramNamespaceBinding) {
    NamespaceBinding namespaceBinding = paramNamespaceBinding;
    if (paramNamespaceBinding == null) {
      if (paramString2 == null)
        return paramNamespaceBinding; 
      namespaceBinding = predefinedXML;
    } 
    String str = namespaceBinding.resolve(paramString1);
    return ((str == null) ? (paramString2 == null) : str.equals(paramString2)) ? namespaceBinding : new NamespaceBinding(paramString1, paramString2, namespaceBinding);
  }
  
  public static final NamespaceBinding nconc(NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    if (paramNamespaceBinding1 == null)
      return paramNamespaceBinding2; 
    paramNamespaceBinding1.setNext(nconc(paramNamespaceBinding1.next, paramNamespaceBinding2));
    return paramNamespaceBinding1;
  }
  
  public int count(NamespaceBinding paramNamespaceBinding) {
    int i = 0;
    for (NamespaceBinding namespaceBinding = this; namespaceBinding != paramNamespaceBinding; namespaceBinding = namespaceBinding.next)
      i++; 
    return i;
  }
  
  public final NamespaceBinding getNext() {
    return this.next;
  }
  
  public final String getPrefix() {
    return this.prefix;
  }
  
  public final String getUri() {
    return this.uri;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.prefix = paramObjectInput.readUTF();
    this.uri = paramObjectInput.readUTF();
    this.next = (NamespaceBinding)paramObjectInput.readObject();
  }
  
  public String resolve(String paramString) {
    for (NamespaceBinding namespaceBinding = this; namespaceBinding != null; namespaceBinding = namespaceBinding.next) {
      if (namespaceBinding.prefix == paramString)
        return namespaceBinding.uri; 
    } 
    return null;
  }
  
  public String resolve(String paramString, NamespaceBinding paramNamespaceBinding) {
    for (NamespaceBinding namespaceBinding = this; namespaceBinding != paramNamespaceBinding; namespaceBinding = namespaceBinding.next) {
      if (namespaceBinding.prefix == paramString)
        return namespaceBinding.uri; 
    } 
    return null;
  }
  
  public NamespaceBinding reversePrefix(NamespaceBinding paramNamespaceBinding) {
    int i;
    NamespaceBinding namespaceBinding2 = paramNamespaceBinding;
    NamespaceBinding namespaceBinding1 = this;
    if (paramNamespaceBinding == null) {
      i = -1;
    } else {
      i = paramNamespaceBinding.depth;
    } 
    while (namespaceBinding1 != paramNamespaceBinding) {
      NamespaceBinding namespaceBinding = namespaceBinding1.next;
      namespaceBinding1.next = namespaceBinding2;
      namespaceBinding2 = namespaceBinding1;
      namespaceBinding1.depth = ++i;
      namespaceBinding1 = namespaceBinding;
    } 
    return namespaceBinding2;
  }
  
  public final void setNext(NamespaceBinding paramNamespaceBinding) {
    int i;
    this.next = paramNamespaceBinding;
    if (paramNamespaceBinding == null) {
      i = 0;
    } else {
      i = paramNamespaceBinding.depth + 1;
    } 
    this.depth = i;
  }
  
  public final void setPrefix(String paramString) {
    this.prefix = paramString;
  }
  
  public final void setUri(String paramString) {
    this.uri = paramString;
  }
  
  public String toString() {
    return "Namespace{" + this.prefix + "=" + this.uri + ", depth:" + this.depth + "}";
  }
  
  public String toStringAll() {
    StringBuffer stringBuffer = new StringBuffer("Namespaces{");
    for (NamespaceBinding namespaceBinding = this; namespaceBinding != null; namespaceBinding = namespaceBinding.next) {
      String str;
      stringBuffer.append(namespaceBinding.prefix);
      stringBuffer.append("=\"");
      stringBuffer.append(namespaceBinding.uri);
      if (namespaceBinding == null) {
        str = "\"";
      } else {
        str = "\", ";
      } 
      stringBuffer.append(str);
    } 
    stringBuffer.append('}');
    return stringBuffer.toString();
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeUTF(this.prefix);
    paramObjectOutput.writeUTF(this.uri);
    paramObjectOutput.writeObject(this.next);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xml/NamespaceBinding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */