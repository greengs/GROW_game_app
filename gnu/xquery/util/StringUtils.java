package gnu.xquery.util;

import gnu.kawa.xml.KNode;
import gnu.kawa.xml.XIntegerType;
import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import gnu.math.IntNum;
import gnu.text.URIPath;
import gnu.xml.TextUtils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils {
  private static String ERROR_VALUE = "<error>";
  
  private static void appendCodepoint(Object paramObject, StringBuffer paramStringBuffer) {
    int j = ((IntNum)XIntegerType.integerType.cast(paramObject)).intValue();
    if (j <= 0 || (j > 55295 && (j < 57344 || (j > 65533 && j < 65536) || j > 1114111)))
      throw new IllegalArgumentException("codepoints-to-string: " + j + " is not a valid XML character [FOCH0001]"); 
    int i = j;
    if (j >= 65536) {
      paramStringBuffer.append((char)((j - 65536 >> 10) + 55296));
      i = (j & 0x3FF) + 56320;
    } 
    paramStringBuffer.append((char)i);
  }
  
  static double asDouble(Object paramObject) {
    Object object = paramObject;
    if (!(paramObject instanceof Number))
      object = NumberValue.numberValue(paramObject); 
    return ((Number)object).doubleValue();
  }
  
  public static Object codepointEqual(Object paramObject1, Object paramObject2) {
    paramObject1 = coerceToString(paramObject1, "codepoint-equal", 1, null);
    paramObject2 = coerceToString(paramObject2, "codepoint-equal", 2, null);
    return (paramObject1 == null || paramObject2 == null) ? Values.empty : (paramObject1.equals(paramObject2) ? Boolean.TRUE : Boolean.FALSE);
  }
  
  public static String codepointsToString(Object paramObject) {
    if (paramObject == null)
      return ""; 
    StringBuffer stringBuffer = new StringBuffer();
    if (paramObject instanceof Values) {
      paramObject = paramObject;
      int i = paramObject.startPos();
      while (true) {
        i = paramObject.nextPos(i);
        if (i != 0) {
          appendCodepoint(paramObject.getPosPrevious(i), stringBuffer);
          continue;
        } 
        break;
      } 
    } else {
      appendCodepoint(paramObject, stringBuffer);
    } 
    return stringBuffer.toString();
  }
  
  static String coerceToString(Object paramObject, String paramString1, int paramInt, String paramString2) {
    Object object = paramObject;
    if (paramObject instanceof KNode)
      object = KNode.atomicValue(paramObject); 
    if ((object == Values.empty || object == null) && paramString2 != ERROR_VALUE)
      return paramString2; 
    if (object instanceof gnu.kawa.xml.UntypedAtomic || object instanceof CharSequence || object instanceof java.net.URI || object instanceof gnu.text.Path)
      return object.toString(); 
    if (paramString2 == ERROR_VALUE) {
      paramObject = "xs:string";
      throw new WrongType(paramString1, paramInt, object, paramObject);
    } 
    paramObject = "xs:string?";
    throw new WrongType(paramString1, paramInt, object, paramObject);
  }
  
  public static Object compare(Object paramObject1, Object paramObject2, NamedCollator paramNamedCollator) {
    if (paramObject1 == Values.empty || paramObject1 == null || paramObject2 == Values.empty || paramObject2 == null)
      return Values.empty; 
    NamedCollator namedCollator = paramNamedCollator;
    if (paramNamedCollator == null)
      namedCollator = NamedCollator.codepointCollation; 
    int i = namedCollator.compare(paramObject1.toString(), paramObject2.toString());
    return (i < 0) ? IntNum.minusOne() : ((i > 0) ? IntNum.one() : IntNum.zero());
  }
  
  public static String concat$V(Object paramObject1, Object paramObject2, Object[] paramArrayOfObject) {
    paramObject1 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(paramObject1, "concat", 1));
    paramObject2 = TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(paramObject2, "concat", 2));
    paramObject1 = new StringBuilder((String)paramObject1);
    paramObject1.append((String)paramObject2);
    int j = paramArrayOfObject.length;
    for (int i = 0; i < j; i++)
      paramObject1.append(TextUtils.stringValue(SequenceUtils.coerceToZeroOrOne(paramArrayOfObject[i], "concat", i + 2))); 
    return paramObject1.toString();
  }
  
  public static Object contains(Object paramObject1, Object paramObject2) {
    return (coerceToString(paramObject1, "contains", 1, "").indexOf(coerceToString(paramObject2, "contains", 2, "")) < 0) ? Boolean.FALSE : Boolean.TRUE;
  }
  
  public static String encodeForUri(Object paramObject) {
    return URIPath.encodeForUri(coerceToString(paramObject, "encode-for-uri", 1, ""), 'U');
  }
  
  public static Object endsWith(Object paramObject1, Object paramObject2) {
    return coerceToString(paramObject1, "ends-with", 1, "").endsWith(coerceToString(paramObject2, "ends-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public static String escapeHtmlUri(Object paramObject) {
    return URIPath.encodeForUri(coerceToString(paramObject, "escape-html-uri", 1, ""), 'H');
  }
  
  public static String iriToUri(Object paramObject) {
    return URIPath.encodeForUri(coerceToString(paramObject, "iri-to-uru", 1, ""), 'I');
  }
  
  public static Object lowerCase(Object paramObject) {
    return coerceToString(paramObject, "lower-case", 1, "").toLowerCase();
  }
  
  public static Pattern makePattern(String paramString1, String paramString2) {
    int i = 0;
    int j = paramString2.length();
    while (true) {
      String str;
      int k = j - 1;
      if (k >= 0) {
        int m;
        int n;
        StringBuffer stringBuffer;
        switch (paramString2.charAt(k)) {
          default:
            throw new IllegalArgumentException("unknown 'replace' flag");
          case 'i':
            i |= 0x42;
            j = k;
            continue;
          case 's':
            i |= 0x20;
            j = k;
            continue;
          case 'x':
            stringBuffer = new StringBuffer();
            n = paramString1.length();
            m = 0;
            for (j = 0; j < n; j = i1) {
              char c;
              int i1;
              int i2 = j + 1;
              char c1 = paramString1.charAt(j);
              if (c1 == '\\' && i2 < n) {
                stringBuffer.append(c1);
                c = paramString1.charAt(i2);
                i1 = i2 + 1;
                j = m;
              } else if (c1 == '[') {
                j = m + 1;
                i1 = i2;
                c = c1;
              } else if (c1 == ']') {
                j = m - 1;
                i1 = i2;
                c = c1;
              } else {
                j = m;
                i1 = i2;
                c = c1;
                if (m == 0) {
                  j = m;
                  i1 = i2;
                  c = c1;
                  if (Character.isWhitespace(c1)) {
                    j = i2;
                    continue;
                  } 
                } 
              } 
              stringBuffer.append(c);
              m = j;
            } 
            paramString1 = stringBuffer.toString();
            j = k;
            continue;
          case 'm':
            break;
        } 
        i |= 0x8;
        j = k;
        continue;
      } 
      paramString2 = paramString1;
      if (paramString1.indexOf("{Is") >= 0) {
        StringBuffer stringBuffer = new StringBuffer();
        int m = paramString1.length();
        for (j = 0; j < m; j = n) {
          int n = j + 1;
          char c = paramString1.charAt(j);
          if (c == '\\' && n + 4 < m) {
            stringBuffer.append(c);
            j = n + 1;
            c = paramString1.charAt(n);
            stringBuffer.append(c);
            if ((c == 'p' || c == 'P') && paramString1.charAt(j) == '{' && paramString1.charAt(j + 1) == 'I' && paramString1.charAt(j + 2) == 's') {
              stringBuffer.append('{');
              stringBuffer.append('I');
              stringBuffer.append('n');
              j += 3;
            } 
            continue;
          } 
          stringBuffer.append(c);
        } 
        str = stringBuffer.toString();
      } 
      return Pattern.compile(str, i);
    } 
  }
  
  public static boolean matches(Object paramObject, String paramString) {
    return matches(paramObject, paramString, "");
  }
  
  public static boolean matches(Object paramObject, String paramString1, String paramString2) {
    paramObject = coerceToString(paramObject, "matches", 1, "");
    return makePattern(paramString1, paramString2).matcher((CharSequence)paramObject).find();
  }
  
  public static String normalizeSpace(Object paramObject) {
    String str = coerceToString(paramObject, "normalize-space", 1, "");
    int k = str.length();
    paramObject = null;
    int i = 0;
    for (int j = 0; j < k; j++) {
      char c = str.charAt(j);
      if (Character.isWhitespace(c)) {
        Object object = paramObject;
        if (paramObject == null) {
          object = paramObject;
          if (!i) {
            object = paramObject;
            if (j > 0)
              object = new StringBuffer(str.substring(0, j)); 
          } 
        } 
        i++;
        paramObject = object;
      } else {
        Object object = paramObject;
        int m = i;
        if (i > 0) {
          if (paramObject != null) {
            paramObject.append(' ');
          } else if (i > 1 || j == 1 || str.charAt(j - 1) != ' ') {
            paramObject = new StringBuffer();
          } 
          m = 0;
          object = paramObject;
        } 
        paramObject = object;
        i = m;
        if (object != null) {
          object.append(c);
          paramObject = object;
          i = m;
        } 
      } 
    } 
    if (paramObject != null)
      return paramObject.toString(); 
    paramObject = str;
    return (String)((i > 0) ? "" : paramObject);
  }
  
  public static Object normalizeUnicode(Object paramObject) {
    return normalizeUnicode(paramObject, "NFC");
  }
  
  public static Object normalizeUnicode(Object paramObject, String paramString) {
    paramObject = coerceToString(paramObject, "normalize-unicode", 1, "");
    if ("".equals(paramString.trim().toUpperCase()))
      return paramObject; 
    throw new UnsupportedOperationException("normalize-unicode: unicode string normalization not available");
  }
  
  public static String replace(Object paramObject, String paramString1, String paramString2) {
    return replace(paramObject, paramString1, paramString2, "");
  }
  
  public static String replace(Object paramObject, String paramString1, String paramString2, String paramString3) {
    paramObject = coerceToString(paramObject, "replace", 1, "");
    int j = paramString2.length();
    int i = 0;
    while (i < j) {
      int k = i + 1;
      char c = paramString2.charAt(i);
      i = k;
      if (c == '\\') {
        if (k < c) {
          i = paramString2.charAt(k);
          if (i == 92 || i == 36) {
            i = k + 1;
            continue;
          } 
        } 
        throw new IllegalArgumentException("invalid replacement string [FORX0004]");
      } 
    } 
    return makePattern(paramString1, paramString3).matcher((CharSequence)paramObject).replaceAll(paramString2);
  }
  
  public static Object startsWith(Object paramObject1, Object paramObject2) {
    return coerceToString(paramObject1, "starts-with", 1, "").startsWith(coerceToString(paramObject2, "starts-with", 2, "")) ? Boolean.TRUE : Boolean.FALSE;
  }
  
  public static Object stringJoin(Object paramObject1, Object paramObject2) {
    StringBuffer stringBuffer = new StringBuffer();
    paramObject2 = coerceToString(paramObject2, "string-join", 2, ERROR_VALUE);
    int j = paramObject2.length();
    int i = 0;
    boolean bool = false;
    while (true) {
      int k = Values.nextIndex(paramObject1, i);
      if (k >= 0) {
        Object object = Values.nextValue(paramObject1, i);
        if (object != Values.empty) {
          if (bool && j > 0)
            stringBuffer.append((String)paramObject2); 
          stringBuffer.append(TextUtils.stringValue(object));
          bool = true;
          i = k;
        } 
        continue;
      } 
      return stringBuffer.toString();
    } 
  }
  
  public static Object stringLength(Object paramObject) {
    paramObject = coerceToString(paramObject, "string-length", 1, "");
    int k = paramObject.length();
    int j = 0;
    int i = 0;
    while (i < k) {
      int m = i + 1;
      char c = paramObject.charAt(i);
      i = m;
      if (c >= '?') {
        i = m;
        if (c < '?') {
          i = m;
          if (m < k)
            i = m + 1; 
        } 
      } 
      j++;
    } 
    return IntNum.make(j);
  }
  
  public static Object stringPad(Object paramObject1, Object paramObject2) {
    int j = ((Number)NumberValue.numberValue(paramObject2)).intValue();
    if (j <= 0) {
      if (j == 0)
        return ""; 
      throw new IndexOutOfBoundsException("Invalid string-pad count");
    } 
    paramObject1 = coerceToString(paramObject1, "string-pad", 1, "");
    paramObject2 = new StringBuffer(j * paramObject1.length());
    for (int i = 0; i < j; i++)
      paramObject2.append((String)paramObject1); 
    return paramObject2.toString();
  }
  
  public static void stringToCodepoints$X(Object paramObject, CallContext paramCallContext) {
    paramObject = coerceToString(paramObject, "string-to-codepoints", 1, "");
    int j = paramObject.length();
    Consumer consumer = paramCallContext.consumer;
    int i = 0;
    while (i < j) {
      int m = i + 1;
      char c = paramObject.charAt(i);
      int k = c;
      i = m;
      if (c >= '?') {
        k = c;
        i = m;
        if (c < '?') {
          k = c;
          i = m;
          if (m < j) {
            k = (c - 55296) * 1024 + paramObject.charAt(m) - 56320 + 65536;
            i = m + 1;
          } 
        } 
      } 
      consumer.writeInt(k);
    } 
  }
  
  public static Object substring(Object paramObject1, Object paramObject2) {
    double d = asDouble(paramObject2);
    if (Double.isNaN(d))
      return ""; 
    int j = (int)(d - 0.5D);
    int i = j;
    if (j < 0)
      i = 0; 
    paramObject1 = coerceToString(paramObject1, "substring", 1, "");
    int k = paramObject1.length();
    j = 0;
    while (true) {
      int m = i - 1;
      if (m >= 0) {
        if (j >= k)
          return ""; 
        int n = j + 1;
        j = paramObject1.charAt(j);
        i = n;
        if (j >= 55296) {
          i = n;
          if (j < 56320) {
            i = n;
            if (n < k)
              i = n + 1; 
          } 
        } 
        j = i;
        i = m;
        continue;
      } 
      return paramObject1.substring(j);
    } 
  }
  
  public static Object substring(Object paramObject1, Object paramObject2, Object paramObject3) {
    paramObject1 = coerceToString(paramObject1, "substring", 1, "");
    int m = paramObject1.length();
    double d2 = Math.floor(asDouble(paramObject2) - 0.5D);
    double d3 = d2 + Math.floor(asDouble(paramObject3) + 0.5D);
    double d1 = d2;
    if (d2 <= 0.0D)
      d1 = 0.0D; 
    d2 = d3;
    if (d3 > m)
      d2 = m; 
    if (d2 <= d1)
      return ""; 
    int j = (int)d1;
    int k = (int)d2 - j;
    int i = 0;
    while (true) {
      if (--j >= 0) {
        if (i >= m)
          return ""; 
        int n = i + 1;
        char c = paramObject1.charAt(i);
        i = n;
        if (c >= '?') {
          i = n;
          if (c < '?') {
            i = n;
            if (n < m)
              i = n + 1; 
          } 
        } 
        continue;
      } 
      j = i;
      while (true) {
        if (--k >= 0) {
          if (j >= m)
            return ""; 
          int n = j + 1;
          char c = paramObject1.charAt(j);
          j = n;
          if (c >= '?') {
            j = n;
            if (c < '?') {
              j = n;
              if (n < m)
                j = n + 1; 
            } 
          } 
          continue;
        } 
        return paramObject1.substring(i, j);
      } 
      break;
    } 
  }
  
  public static Object substringAfter(Object paramObject1, Object paramObject2) {
    paramObject1 = coerceToString(paramObject1, "substring-after", 1, "");
    paramObject2 = coerceToString(paramObject2, "substring-after", 2, "");
    int i = paramObject2.length();
    if (i == 0)
      return paramObject1; 
    int j = paramObject1.indexOf((String)paramObject2);
    return (j >= 0) ? paramObject1.substring(j + i) : "";
  }
  
  public static Object substringBefore(Object paramObject1, Object paramObject2) {
    paramObject1 = coerceToString(paramObject1, "substring-before", 1, "");
    paramObject2 = coerceToString(paramObject2, "substring-before", 2, "");
    if (paramObject2.length() == 0)
      return ""; 
    int i = paramObject1.indexOf((String)paramObject2);
    return (i >= 0) ? paramObject1.substring(0, i) : "";
  }
  
  public static void tokenize$X(Object paramObject, String paramString, CallContext paramCallContext) {
    tokenize$X(paramObject, paramString, "", paramCallContext);
  }
  
  public static void tokenize$X(Object paramObject, String paramString1, String paramString2, CallContext paramCallContext) {
    paramObject = coerceToString(paramObject, "tokenize", 1, "");
    Consumer consumer = paramCallContext.consumer;
    Matcher matcher = makePattern(paramString1, paramString2).matcher((CharSequence)paramObject);
    if (paramObject.length() == 0)
      return; 
    int i = 0;
    while (true) {
      if (!matcher.find()) {
        consumer.writeObject(paramObject.substring(i));
        return;
      } 
      int k = matcher.start();
      consumer.writeObject(paramObject.substring(i, k));
      int j = matcher.end();
      i = j;
      if (j == k)
        throw new IllegalArgumentException("pattern matches empty string"); 
    } 
  }
  
  public static Object translate(Object paramObject1, Object paramObject2, Object paramObject3) {
    String str = coerceToString(paramObject1, "translate", 1, "");
    paramObject2 = KNode.atomicValue(paramObject2);
    if (!(paramObject2 instanceof gnu.kawa.xml.UntypedAtomic) && !(paramObject2 instanceof String))
      throw new WrongType("translate", 2, paramObject1, "xs:string"); 
    paramObject2 = paramObject2.toString();
    int j = paramObject2.length();
    paramObject3 = KNode.atomicValue(paramObject3);
    if (!(paramObject3 instanceof gnu.kawa.xml.UntypedAtomic) && !(paramObject3 instanceof String))
      throw new WrongType("translate", 3, paramObject1, "xs:string"); 
    paramObject1 = paramObject3.toString();
    if (j == 0)
      return str; 
    int k = str.length();
    paramObject3 = new StringBuffer(k);
    int m = paramObject1.length();
    int i = 0;
    label57: while (i < k) {
      int n = i + 1;
      char c = str.charAt(i);
      char c2 = Character.MIN_VALUE;
      char c1 = c2;
      i = n;
      if (c >= '?') {
        c1 = c2;
        i = n;
        if (c < '?') {
          c1 = c2;
          i = n;
          if (n < k) {
            c1 = str.charAt(n);
            i = n + 1;
          } 
        } 
      } 
      n = 0;
      int i1 = 0;
      while (true) {
        if (i1 < j) {
          int i3 = i1 + 1;
          char c4 = paramObject2.charAt(i1);
          char c3 = Character.MIN_VALUE;
          int i2 = c3;
          i1 = i3;
          if (c4 >= '?') {
            i2 = c3;
            i1 = i3;
            if (c4 < '?') {
              i2 = c3;
              i1 = i3;
              if (i3 < j) {
                i2 = paramObject2.charAt(i3);
                i1 = i3 + 1;
              } 
            } 
          } 
          if (c4 == c && i2 == c1) {
            i2 = 0;
            i1 = n;
            while (i2 < m) {
              i3 = i2 + 1;
              c = paramObject1.charAt(i2);
              c2 = Character.MIN_VALUE;
              c1 = c2;
              n = i3;
              if (c >= '?') {
                c1 = c2;
                n = i3;
                if (c < '?') {
                  c1 = c2;
                  n = i3;
                  if (i3 < m) {
                    c1 = paramObject1.charAt(i3);
                    n = i3 + 1;
                  } 
                } 
              } 
              if (i1 == 0) {
                paramObject3.append(c);
                if (c1 != '\000')
                  paramObject3.append(c1); 
                break;
              } 
              i1--;
              i2 = n;
            } 
            continue label57;
          } 
          n++;
          continue;
        } 
        continue;
      } 
    } 
    return paramObject3.toString();
  }
  
  public static Object upperCase(Object paramObject) {
    return coerceToString(paramObject, "upper-case", 1, "").toUpperCase();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/StringUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */