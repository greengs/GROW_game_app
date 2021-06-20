package gnu.text;

import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public abstract class Path {
  public static Path defaultPath;
  
  private static ThreadLocal<Path> pathLocation;
  
  public static final FilePath userDirPath = FilePath.valueOf(new File("."));
  
  static {
    defaultPath = userDirPath;
    pathLocation = new ThreadLocal<Path>();
  }
  
  public static Path coerceToPathOrNull(Object paramObject) {
    if (paramObject instanceof Path)
      return (Path)paramObject; 
    if (paramObject instanceof URL)
      return URLPath.valueOf((URL)paramObject); 
    if (paramObject instanceof URI)
      return URIPath.valueOf((URI)paramObject); 
    if (paramObject instanceof File)
      return FilePath.valueOf((File)paramObject); 
    if (paramObject instanceof gnu.lists.FString) {
      paramObject = paramObject.toString();
    } else {
      if (!(paramObject instanceof String))
        return null; 
      paramObject = paramObject;
    } 
    return (Path)(uriSchemeSpecified((String)paramObject) ? URIPath.valueOf((String)paramObject) : FilePath.valueOf((String)paramObject));
  }
  
  public static Path currentPath() {
    Path path = pathLocation.get();
    return (path != null) ? path : defaultPath;
  }
  
  public static InputStream openInputStream(Object paramObject) throws IOException {
    return valueOf(paramObject).openInputStream();
  }
  
  public static String relativize(String paramString1, String paramString2) throws URISyntaxException, IOException {
    // Byte code:
    //   0: new java/net/URI
    //   3: dup
    //   4: aload_1
    //   5: invokespecial <init> : (Ljava/lang/String;)V
    //   8: invokevirtual normalize : ()Ljava/net/URI;
    //   11: invokevirtual toString : ()Ljava/lang/String;
    //   14: astore #9
    //   16: aload_0
    //   17: invokestatic valueOf : (Ljava/lang/String;)Lgnu/text/URIPath;
    //   20: invokevirtual toURI : ()Ljava/net/URI;
    //   23: invokevirtual normalize : ()Ljava/net/URI;
    //   26: invokevirtual toString : ()Ljava/lang/String;
    //   29: astore #8
    //   31: aload #9
    //   33: invokevirtual length : ()I
    //   36: istore #5
    //   38: aload #8
    //   40: invokevirtual length : ()I
    //   43: istore #6
    //   45: iconst_0
    //   46: istore_2
    //   47: iconst_0
    //   48: istore #4
    //   50: iconst_0
    //   51: istore_3
    //   52: iload_2
    //   53: iload #5
    //   55: if_icmpge -> 83
    //   58: iload_2
    //   59: iload #6
    //   61: if_icmpge -> 83
    //   64: aload #9
    //   66: iload_2
    //   67: invokevirtual charAt : (I)C
    //   70: istore #7
    //   72: iload #7
    //   74: aload #8
    //   76: iload_2
    //   77: invokevirtual charAt : (I)C
    //   80: if_icmpeq -> 187
    //   83: aload_0
    //   84: astore_1
    //   85: iload_3
    //   86: ifle -> 226
    //   89: iload #4
    //   91: iload_3
    //   92: iconst_2
    //   93: iadd
    //   94: if_icmpgt -> 120
    //   97: iload #5
    //   99: iload_3
    //   100: iconst_2
    //   101: iadd
    //   102: if_icmple -> 120
    //   105: aload_0
    //   106: astore_1
    //   107: aload #9
    //   109: iload_3
    //   110: iconst_2
    //   111: iadd
    //   112: invokevirtual charAt : (I)C
    //   115: bipush #47
    //   117: if_icmpeq -> 226
    //   120: aload #9
    //   122: iload #4
    //   124: iconst_1
    //   125: iadd
    //   126: invokevirtual substring : (I)Ljava/lang/String;
    //   129: astore_0
    //   130: aload #8
    //   132: iload #4
    //   134: iconst_1
    //   135: iadd
    //   136: invokevirtual substring : (I)Ljava/lang/String;
    //   139: astore_1
    //   140: new java/lang/StringBuilder
    //   143: dup
    //   144: invokespecial <init> : ()V
    //   147: astore #8
    //   149: aload_0
    //   150: invokevirtual length : ()I
    //   153: istore_2
    //   154: iload_2
    //   155: iconst_1
    //   156: isub
    //   157: istore_3
    //   158: iload_3
    //   159: iflt -> 213
    //   162: iload_3
    //   163: istore_2
    //   164: aload_0
    //   165: iload_3
    //   166: invokevirtual charAt : (I)C
    //   169: bipush #47
    //   171: if_icmpne -> 154
    //   174: aload #8
    //   176: ldc '../'
    //   178: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   181: pop
    //   182: iload_3
    //   183: istore_2
    //   184: goto -> 154
    //   187: iload #7
    //   189: bipush #47
    //   191: if_icmpne -> 197
    //   194: iload_2
    //   195: istore #4
    //   197: iload #7
    //   199: bipush #58
    //   201: if_icmpne -> 206
    //   204: iload_2
    //   205: istore_3
    //   206: iload_2
    //   207: iconst_1
    //   208: iadd
    //   209: istore_2
    //   210: goto -> 52
    //   213: aload #8
    //   215: aload_1
    //   216: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   219: pop
    //   220: aload #8
    //   222: invokevirtual toString : ()Ljava/lang/String;
    //   225: astore_1
    //   226: aload_1
    //   227: areturn
  }
  
  public static void setCurrentPath(Path paramPath) {
    pathLocation.set(paramPath);
  }
  
  public static URL toURL(String paramString) {
    String str = paramString;
    try {
      if (!uriSchemeSpecified(paramString)) {
        Path path = currentPath().resolve(paramString);
        if (path.isAbsolute())
          return path.toURL(); 
        str = path.toString();
      } 
      return new URL(str);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static int uriSchemeLength(String paramString) {
    int j = paramString.length();
    for (int i = 0; i < j; i++) {
      char c = paramString.charAt(i);
      if (c == ':')
        return i; 
      if ((i == 0) ? !Character.isLetter(c) : (!Character.isLetterOrDigit(c) && c != '+' && c != '-' && c != '.'))
        return -1; 
    } 
    return -1;
  }
  
  public static boolean uriSchemeSpecified(String paramString) {
    // Byte code:
    //   0: iconst_1
    //   1: istore_2
    //   2: iconst_0
    //   3: istore_3
    //   4: aload_0
    //   5: invokestatic uriSchemeLength : (Ljava/lang/String;)I
    //   8: istore_1
    //   9: iload_1
    //   10: iconst_1
    //   11: if_icmpne -> 60
    //   14: getstatic java/io/File.separatorChar : C
    //   17: bipush #92
    //   19: if_icmpne -> 60
    //   22: aload_0
    //   23: iconst_0
    //   24: invokevirtual charAt : (I)C
    //   27: istore_1
    //   28: iload_1
    //   29: bipush #97
    //   31: if_icmplt -> 42
    //   34: iload_3
    //   35: istore_2
    //   36: iload_1
    //   37: bipush #122
    //   39: if_icmple -> 58
    //   42: iload_1
    //   43: bipush #65
    //   45: if_icmplt -> 56
    //   48: iload_3
    //   49: istore_2
    //   50: iload_1
    //   51: bipush #90
    //   53: if_icmple -> 58
    //   56: iconst_1
    //   57: istore_2
    //   58: iload_2
    //   59: ireturn
    //   60: iload_1
    //   61: ifle -> 66
    //   64: iload_2
    //   65: ireturn
    //   66: iconst_0
    //   67: istore_2
    //   68: goto -> 64
  }
  
  public static Path valueOf(Object paramObject) {
    Path path = coerceToPathOrNull(paramObject);
    if (path == null)
      throw new WrongType((String)null, -4, paramObject, "path"); 
    return path;
  }
  
  public boolean delete() {
    return false;
  }
  
  public boolean exists() {
    return (getLastModified() != 0L);
  }
  
  public Path getAbsolute() {
    return (this == userDirPath) ? resolve("") : currentPath().resolve(this);
  }
  
  public String getAuthority() {
    return null;
  }
  
  public Path getCanonical() {
    return getAbsolute();
  }
  
  public CharSequence getCharContent(boolean paramBoolean) throws IOException {
    throw new UnsupportedOperationException();
  }
  
  public long getContentLength() {
    return -1L;
  }
  
  public Path getDirectory() {
    return isDirectory() ? this : resolve("");
  }
  
  public String getExtension() {
    String str = getPath();
    if (str != null) {
      int i = str.length();
      while (true) {
        int j = i - 1;
        if (j > 0) {
          char c = str.charAt(j);
          boolean bool = false;
          i = c;
          if (c == '.') {
            i = str.charAt(j - 1);
            bool = true;
          } 
          if (i != 47 && (!(this instanceof FilePath) || i != File.separatorChar)) {
            i = j;
            if (bool)
              return str.substring(j + 1); 
            continue;
          } 
        } 
        return null;
      } 
    } 
    return null;
  }
  
  public String getFragment() {
    return null;
  }
  
  public String getHost() {
    return null;
  }
  
  public String getLast() {
    String str = getPath();
    if (str == null)
      return null; 
    int i = str.length();
    int k = i;
    int j = i;
    while (true) {
      int m = j - 1;
      if (m <= 0)
        return ""; 
      char c = str.charAt(m);
      if (c != '/') {
        j = m;
        if (this instanceof FilePath) {
          j = m;
          if (c == File.separatorChar) {
            if (m + 1 == i) {
              k = m;
              j = m;
              continue;
            } 
            return str.substring(m + 1, k);
          } 
        } 
        continue;
      } 
      if (m + 1 == i) {
        k = m;
        j = m;
        continue;
      } 
      return str.substring(m + 1, k);
    } 
  }
  
  public abstract long getLastModified();
  
  public String getName() {
    return toString();
  }
  
  public Path getParent() {
    if (isDirectory()) {
      String str1 = "..";
      return resolve(str1);
    } 
    String str = "";
    return resolve(str);
  }
  
  public abstract String getPath();
  
  public int getPort() {
    return -1;
  }
  
  public String getQuery() {
    return null;
  }
  
  public abstract String getScheme();
  
  public String getUserInfo() {
    return null;
  }
  
  public abstract boolean isAbsolute();
  
  public boolean isDirectory() {
    String str = toString();
    int i = str.length();
    if (i > 0) {
      i = str.charAt(i - 1);
      if (i == 47 || i == File.separatorChar)
        return true; 
    } 
    return false;
  }
  
  public abstract InputStream openInputStream() throws IOException;
  
  public abstract OutputStream openOutputStream() throws IOException;
  
  public Reader openReader(boolean paramBoolean) throws IOException {
    throw new UnsupportedOperationException();
  }
  
  public Writer openWriter() throws IOException {
    return new OutputStreamWriter(openOutputStream());
  }
  
  public Path resolve(Path paramPath) {
    return paramPath.isAbsolute() ? paramPath : resolve(paramPath.toString());
  }
  
  public abstract Path resolve(String paramString);
  
  public final URI toURI() {
    return toUri();
  }
  
  public String toURIString() {
    return toUri().toString();
  }
  
  public abstract URL toURL();
  
  public abstract URI toUri();
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/Path.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */