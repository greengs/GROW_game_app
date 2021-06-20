package gnu.mapping;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;

public class Symbol implements EnvironmentKey, Comparable, Externalizable {
  public static final Symbol FUNCTION = makeUninterned("(function)");
  
  public static final Symbol PLIST = makeUninterned("(property-list)");
  
  protected String name;
  
  Namespace namespace;
  
  public Symbol() {}
  
  public Symbol(Namespace paramNamespace, String paramString) {
    this.name = paramString;
    this.namespace = paramNamespace;
  }
  
  public static boolean equals(Symbol paramSymbol1, Symbol paramSymbol2) {
    if (paramSymbol1 != paramSymbol2) {
      if (paramSymbol1 == null || paramSymbol2 == null)
        return false; 
      if (paramSymbol1.name == paramSymbol2.name) {
        Namespace namespace1 = paramSymbol1.namespace;
        Namespace namespace2 = paramSymbol2.namespace;
        if (namespace1 != null && namespace2 != null)
          return !(namespace1.name != namespace2.name); 
      } 
      return false;
    } 
    return true;
  }
  
  public static Symbol make(Object paramObject, String paramString) {
    if (paramObject instanceof String) {
      paramObject = Namespace.valueOf((String)paramObject);
    } else {
      paramObject = paramObject;
    } 
    return (paramObject == null || paramString == null) ? makeUninterned(paramString) : paramObject.getSymbol(paramString.intern());
  }
  
  public static Symbol make(String paramString1, String paramString2, String paramString3) {
    return Namespace.valueOf(paramString1, paramString3).getSymbol(paramString2.intern());
  }
  
  public static Symbol makeUninterned(String paramString) {
    return new Symbol(null, paramString);
  }
  
  public static Symbol makeWithUnknownNamespace(String paramString1, String paramString2) {
    return Namespace.makeUnknownNamespace(paramString2).getSymbol(paramString1.intern());
  }
  
  public static Symbol parse(String paramString) {
    int j;
    Object object1;
    Object object2;
    int k;
    int n = paramString.length();
    byte b1 = -1;
    byte b2 = -1;
    int m = 0;
    byte b = 0;
    boolean bool = false;
    int i = 0;
    while (true) {
      int i1;
      Object object4 = object1;
      k = b;
      Object object3 = object2;
      int i2 = b2;
      if (i < n) {
        i2 = paramString.charAt(i);
        if (i2 == 58 && !m) {
          j = i;
          k = i + 1;
          i2 = b2;
          object4 = object1;
        } else {
          i1 = m;
          Object object = object1;
          object3 = object2;
          if (i2 == 123) {
            object = object1;
            object3 = object2;
            if (object1 < null) {
              j = i;
              k = i;
            } 
            i1 = m + 1;
          } 
          m = i1;
          if (i2 == 125) {
            int i3 = i1 - 1;
            if (i3 == 0) {
              i2 = i;
              if (i < n && paramString.charAt(i + 1) == ':') {
                i += 2;
              } else {
                i++;
              } 
              i1 = k;
              k = i;
            } else {
              m = i3;
              if (i3 < 0) {
                i = j;
                i1 = k;
                k = i;
                i2 = b2;
              } else {
                continue;
              } 
            } 
          } else {
            continue;
          } 
        } 
      } 
      if (i1 >= 0 && i2 > 0) {
        String str2 = paramString.substring(i1 + 1, i2);
        if (j > 0) {
          String str = paramString.substring(0, j);
          return valueOf(paramString.substring(k), str2, str);
        } 
        String str1 = null;
        return valueOf(paramString.substring(k), str2, str1);
      } 
      break;
      i++;
      object1 = SYNTHETIC_LOCAL_VARIABLE_5;
      object2 = SYNTHETIC_LOCAL_VARIABLE_2;
    } 
    return (j > 0) ? makeWithUnknownNamespace(paramString.substring(k), paramString.substring(0, j)) : valueOf(paramString);
  }
  
  public static SimpleSymbol valueOf(String paramString) {
    return (SimpleSymbol)Namespace.EmptyNamespace.getSymbol(paramString.intern());
  }
  
  public static Symbol valueOf(String paramString, Object paramObject) {
    if (paramObject == null || paramObject == Boolean.FALSE)
      return makeUninterned(paramString); 
    if (paramObject instanceof Namespace) {
      paramObject = paramObject;
      return paramObject.getSymbol(paramString.intern());
    } 
    if (paramObject == Boolean.TRUE) {
      paramObject = Namespace.EmptyNamespace;
      return paramObject.getSymbol(paramString.intern());
    } 
    paramObject = Namespace.valueOf(((CharSequence)paramObject).toString());
    return paramObject.getSymbol(paramString.intern());
  }
  
  public static Symbol valueOf(String paramString1, String paramString2, String paramString3) {
    return Namespace.valueOf(paramString2, paramString3).getSymbol(paramString1.intern());
  }
  
  public int compareTo(Object paramObject) {
    paramObject = paramObject;
    if (getNamespaceURI() != paramObject.getNamespaceURI())
      throw new IllegalArgumentException("comparing Symbols in different namespaces"); 
    return getLocalName().compareTo(paramObject.getLocalName());
  }
  
  public final boolean equals(Object paramObject) {
    return (paramObject instanceof Symbol && equals(this, (Symbol)paramObject));
  }
  
  public final Object getKeyProperty() {
    return null;
  }
  
  public final Symbol getKeySymbol() {
    return this;
  }
  
  public final String getLocalName() {
    return this.name;
  }
  
  public final String getLocalPart() {
    return this.name;
  }
  
  public final String getName() {
    return this.name;
  }
  
  public final Namespace getNamespace() {
    return this.namespace;
  }
  
  public final String getNamespaceURI() {
    Namespace namespace = getNamespace();
    return (namespace == null) ? null : namespace.getName();
  }
  
  public final String getPrefix() {
    Namespace namespace = this.namespace;
    return (namespace == null) ? "" : namespace.prefix;
  }
  
  public final boolean hasEmptyNamespace() {
    Namespace namespace = getNamespace();
    if (namespace != null) {
      String str = namespace.getName();
      if (str != null && str.length() != 0)
        return false; 
    } 
    return true;
  }
  
  public int hashCode() {
    return (this.name == null) ? 0 : this.name.hashCode();
  }
  
  public boolean matches(EnvironmentKey paramEnvironmentKey) {
    return (equals(paramEnvironmentKey.getKeySymbol(), this) && paramEnvironmentKey.getKeyProperty() == null);
  }
  
  public boolean matches(Symbol paramSymbol, Object paramObject) {
    return (equals(paramSymbol, this) && paramObject == null);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.namespace = (Namespace)paramObjectInput.readObject();
    this.name = (String)paramObjectInput.readObject();
  }
  
  public Object readResolve() throws ObjectStreamException {
    return (this.namespace == null) ? this : make(this.namespace, getName());
  }
  
  public final void setNamespace(Namespace paramNamespace) {
    this.namespace = paramNamespace;
  }
  
  public String toString() {
    return toString('P');
  }
  
  public String toString(char paramChar) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   6: astore #4
    //   8: aload_0
    //   9: invokevirtual getPrefix : ()Ljava/lang/String;
    //   12: astore #6
    //   14: aload #4
    //   16: ifnull -> 157
    //   19: aload #4
    //   21: invokevirtual length : ()I
    //   24: ifle -> 157
    //   27: iconst_1
    //   28: istore_2
    //   29: aload #6
    //   31: ifnull -> 162
    //   34: aload #6
    //   36: invokevirtual length : ()I
    //   39: ifle -> 162
    //   42: aload_0
    //   43: invokevirtual getName : ()Ljava/lang/String;
    //   46: astore #5
    //   48: iload_2
    //   49: ifne -> 60
    //   52: aload #5
    //   54: astore #4
    //   56: iload_3
    //   57: ifeq -> 154
    //   60: new java/lang/StringBuilder
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: astore #4
    //   69: iload_3
    //   70: ifeq -> 91
    //   73: iload_1
    //   74: bipush #85
    //   76: if_icmpne -> 83
    //   79: iload_2
    //   80: ifne -> 91
    //   83: aload #4
    //   85: aload #6
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: iload_2
    //   92: ifeq -> 131
    //   95: iload_1
    //   96: bipush #80
    //   98: if_icmpne -> 105
    //   101: iload_3
    //   102: ifne -> 131
    //   105: aload #4
    //   107: bipush #123
    //   109: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload #4
    //   115: aload_0
    //   116: invokevirtual getNamespaceURI : ()Ljava/lang/String;
    //   119: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   122: pop
    //   123: aload #4
    //   125: bipush #125
    //   127: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   130: pop
    //   131: aload #4
    //   133: bipush #58
    //   135: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   138: pop
    //   139: aload #4
    //   141: aload #5
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: pop
    //   147: aload #4
    //   149: invokevirtual toString : ()Ljava/lang/String;
    //   152: astore #4
    //   154: aload #4
    //   156: areturn
    //   157: iconst_0
    //   158: istore_2
    //   159: goto -> 29
    //   162: iconst_0
    //   163: istore_3
    //   164: goto -> 42
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getNamespace());
    paramObjectOutput.writeObject(getName());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Symbol.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */