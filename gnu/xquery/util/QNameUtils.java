package gnu.xquery.util;

import gnu.kawa.xml.KElement;
import gnu.kawa.xml.KNode;
import gnu.kawa.xml.XStringType;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.text.Path;
import gnu.text.URIPath;
import gnu.xml.NamespaceBinding;
import gnu.xml.TextUtils;
import gnu.xml.XName;
import java.net.URISyntaxException;

public class QNameUtils {
  public static Object localNameFromQName(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return paramObject; 
    if (!(paramObject instanceof Symbol))
      throw new WrongType("local-name-from-QName", 1, paramObject, "xs:QName"); 
    return XStringType.makeNCName(((Symbol)paramObject).getName());
  }
  
  public static String lookupPrefix(String paramString, NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    while (true) {
      String str1;
      NamespaceBinding namespaceBinding;
      String str2;
      if (paramNamespaceBinding1 == null) {
        str1 = paramNamespaceBinding2.resolve(paramString);
      } else if (str1.getPrefix() == paramString) {
        str1 = str1.getUri();
      } else {
        namespaceBinding = str1.getNext();
        continue;
      } 
      paramNamespaceBinding2 = namespaceBinding;
      if (namespaceBinding == null) {
        paramNamespaceBinding2 = namespaceBinding;
        if (paramString == null)
          str2 = ""; 
      } 
      return str2;
    } 
  }
  
  public static Symbol makeQName(Object paramObject, String paramString) {
    // Byte code:
    //   0: aload_0
    //   1: ifnull -> 13
    //   4: aload_0
    //   5: astore_3
    //   6: aload_0
    //   7: getstatic gnu/mapping/Values.empty : Lgnu/mapping/Values;
    //   10: if_acmpne -> 16
    //   13: ldc ''
    //   15: astore_3
    //   16: aload_1
    //   17: bipush #58
    //   19: invokevirtual indexOf : (I)I
    //   22: istore_2
    //   23: aload_3
    //   24: checkcast java/lang/String
    //   27: astore #4
    //   29: iload_2
    //   30: ifge -> 88
    //   33: aload_1
    //   34: astore_0
    //   35: ldc ''
    //   37: astore_3
    //   38: aload_0
    //   39: invokestatic validNCName : (Ljava/lang/String;)Z
    //   42: ifeq -> 56
    //   45: iload_2
    //   46: iflt -> 109
    //   49: aload_3
    //   50: invokestatic validNCName : (Ljava/lang/String;)Z
    //   53: ifne -> 109
    //   56: new java/lang/IllegalArgumentException
    //   59: dup
    //   60: new java/lang/StringBuilder
    //   63: dup
    //   64: invokespecial <init> : ()V
    //   67: ldc 'invalid QName syntax ''
    //   69: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: aload_1
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc '''
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual toString : ()Ljava/lang/String;
    //   84: invokespecial <init> : (Ljava/lang/String;)V
    //   87: athrow
    //   88: aload_1
    //   89: iload_2
    //   90: iconst_1
    //   91: iadd
    //   92: invokevirtual substring : (I)Ljava/lang/String;
    //   95: astore_0
    //   96: aload_1
    //   97: iconst_0
    //   98: iload_2
    //   99: invokevirtual substring : (II)Ljava/lang/String;
    //   102: invokevirtual intern : ()Ljava/lang/String;
    //   105: astore_3
    //   106: goto -> 38
    //   109: iload_2
    //   110: iflt -> 153
    //   113: aload #4
    //   115: invokevirtual length : ()I
    //   118: ifne -> 153
    //   121: new java/lang/IllegalArgumentException
    //   124: dup
    //   125: new java/lang/StringBuilder
    //   128: dup
    //   129: invokespecial <init> : ()V
    //   132: ldc 'empty uri for ''
    //   134: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   137: aload_1
    //   138: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   141: ldc '''
    //   143: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: invokevirtual toString : ()Ljava/lang/String;
    //   149: invokespecial <init> : (Ljava/lang/String;)V
    //   152: athrow
    //   153: aload #4
    //   155: aload_0
    //   156: aload_3
    //   157: invokestatic make : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   160: areturn
  }
  
  public static Object namespaceURIForPrefix(Object paramObject1, Object paramObject2) {
    KNode kNode = KNode.coerce(paramObject2);
    if (kNode == null)
      throw new WrongType("namespace-uri-for-prefix", 2, paramObject2, "node()"); 
    if (paramObject1 == null || paramObject1 == Values.empty) {
      paramObject1 = null;
    } else {
      if (!(paramObject1 instanceof String) && !(paramObject1 instanceof gnu.kawa.xml.UntypedAtomic))
        throw new WrongType("namespace-uri-for-prefix", 1, paramObject2, "xs:string"); 
      paramObject2 = paramObject1.toString().intern();
      paramObject1 = paramObject2;
      if (paramObject2 == "")
        paramObject1 = null; 
    } 
    paramObject2 = kNode.lookupNamespaceURI((String)paramObject1);
    paramObject1 = paramObject2;
    if (paramObject2 == null)
      paramObject1 = Values.empty; 
    return paramObject1;
  }
  
  public static Object namespaceURIFromQName(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return paramObject; 
    try {
      return URIPath.makeURI(((Symbol)paramObject).getNamespaceURI());
    } catch (ClassCastException classCastException) {
      throw new WrongType("namespace-uri", 1, paramObject, "xs:QName");
    } 
  }
  
  public static Object prefixFromQName(Object paramObject) {
    if (paramObject == Values.empty || paramObject == null)
      return paramObject; 
    if (paramObject instanceof Symbol) {
      paramObject = ((Symbol)paramObject).getPrefix();
      return (paramObject == null || paramObject.length() == 0) ? Values.empty : XStringType.makeNCName((String)paramObject);
    } 
    throw new WrongType("prefix-from-QName", 1, paramObject, "xs:QName");
  }
  
  public static String resolvePrefix(String paramString, NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    String str = lookupPrefix(paramString, paramNamespaceBinding1, paramNamespaceBinding2);
    if (str == null)
      throw new RuntimeException("unknown namespace prefix '" + paramString + "'"); 
    return str;
  }
  
  public static Object resolveQName(Object paramObject, NamespaceBinding paramNamespaceBinding1, NamespaceBinding paramNamespaceBinding2) {
    String str2;
    paramObject = KNode.atomicValue(paramObject);
    if (paramObject instanceof Symbol)
      return paramObject; 
    if (paramObject instanceof Values || (!(paramObject instanceof String) && !(paramObject instanceof gnu.kawa.xml.UntypedAtomic)))
      throw new RuntimeException("bad argument to QName"); 
    String str3 = TextUtils.replaceWhitespace(paramObject.toString(), true);
    int i = str3.indexOf(':');
    if (i < 0) {
      str2 = str3;
      paramObject = null;
    } else {
      paramObject = str3.substring(0, i).intern();
      str2 = str3.substring(i + 1);
    } 
    if (!validNCName(str2) || (paramObject != null && !validNCName((String)paramObject)))
      throw new RuntimeException("invalid QName syntax '" + str3 + "'"); 
    String str1 = resolvePrefix((String)paramObject, paramNamespaceBinding1, paramNamespaceBinding2);
    Object object = paramObject;
    if (paramObject == null)
      object = ""; 
    return Symbol.make(str1, str2, (String)object);
  }
  
  public static Object resolveQNameUsingElement(Object paramObject, KElement paramKElement) {
    String str2;
    paramObject = KNode.atomicValue(paramObject);
    if (paramObject == Values.empty || paramObject == null)
      return paramObject; 
    if (paramObject instanceof Values || (!(paramObject instanceof String) && !(paramObject instanceof gnu.kawa.xml.UntypedAtomic)))
      throw new RuntimeException("bad argument to QName"); 
    String str3 = TextUtils.replaceWhitespace(paramObject.toString(), true);
    int i = str3.indexOf(':');
    if (i < 0) {
      paramObject = null;
      str2 = str3;
    } else {
      paramObject = str3.substring(0, i).intern();
      str2 = str3.substring(i + 1);
    } 
    String str4 = paramKElement.lookupNamespaceURI((String)paramObject);
    String str1 = str4;
    if (str4 == null)
      if (paramObject == null) {
        str1 = "";
      } else {
        throw new RuntimeException("unknown namespace for '" + str3 + "'");
      }  
    if (!validNCName(str2) || (paramObject != null && !validNCName((String)paramObject)))
      throw new RuntimeException("invalid QName syntax '" + str3 + "'"); 
    Object object = paramObject;
    if (paramObject == null)
      object = ""; 
    return Symbol.make(str1, str2, (String)object);
  }
  
  public static Object resolveURI(Object paramObject1, Object paramObject2) throws URISyntaxException {
    Object object = paramObject1;
    if (paramObject1 instanceof KNode)
      object = KNode.atomicValue(paramObject1); 
    paramObject1 = paramObject2;
    if (paramObject2 instanceof KNode)
      paramObject1 = KNode.atomicValue(paramObject2); 
    if (object == Values.empty || object == null)
      return object; 
    paramObject2 = object;
    if (object instanceof gnu.kawa.xml.UntypedAtomic)
      paramObject2 = object.toString(); 
    object = paramObject1;
    if (paramObject1 instanceof gnu.kawa.xml.UntypedAtomic)
      object = paramObject1.toString(); 
    if (object instanceof Path) {
      paramObject1 = object;
    } else {
      paramObject1 = URIPath.makeURI(object);
    } 
    return (paramObject2 instanceof Path) ? paramObject1.resolve((Path)paramObject2) : paramObject1.resolve(paramObject2.toString());
  }
  
  public static boolean validNCName(String paramString) {
    return XName.isName(paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/QNameUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */