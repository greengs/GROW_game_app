package gnu.text;

import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URIPath extends Path implements Comparable<URIPath> {
  final URI uri;
  
  URIPath(URI paramURI) {
    this.uri = paramURI;
  }
  
  public static URIPath coerceToURIPathOrNull(Object paramObject) {
    if (paramObject instanceof URIPath)
      return (URIPath)paramObject; 
    if (paramObject instanceof URL)
      return URLPath.valueOf((URL)paramObject); 
    if (paramObject instanceof URI)
      return valueOf((URI)paramObject); 
    if (paramObject instanceof File || paramObject instanceof Path || paramObject instanceof gnu.lists.FString) {
      paramObject = paramObject.toString();
      return valueOf((String)paramObject);
    } 
    if (paramObject instanceof String) {
      paramObject = paramObject;
      return valueOf((String)paramObject);
    } 
    return null;
  }
  
  public static String encodeForUri(String paramString, char paramChar) {
    StringBuffer stringBuffer = new StringBuffer();
    int j = paramString.length();
    int i = 0;
    label79: while (i < j) {
      int k = i + 1;
      char c = paramString.charAt(i);
      int m = c;
      i = k;
      if (c >= '?') {
        m = c;
        i = k;
        if (c < '?') {
          m = c;
          i = k;
          if (k < j) {
            m = (c - 55296) * 1024 + paramString.charAt(k) - 56320 + 65536;
            i = k + 1;
          } 
        } 
      } 
      if ((paramChar == 'H') ? (m >= 32 && m <= 126) : ((m >= 97 && m <= 122) || (m >= 65 && m <= 90) || (m >= 48 && m <= 57) || m == 45 || m == 95 || m == 46 || m == 126 || (paramChar == 'I' && (m == 59 || m == 47 || m == 63 || m == 58 || m == 42 || m == 39 || m == 40 || m == 41 || m == 64 || m == 38 || m == 61 || m == 43 || m == 36 || m == 44 || m == 91 || m == 93 || m == 35 || m == 33 || m == 37)))) {
        stringBuffer.append((char)m);
        continue;
      } 
      int n = stringBuffer.length();
      c = Character.MIN_VALUE;
      if (m >= 128 && m >= 2048 && m < 65536);
      while (true) {
        if (c == '\000') {
          k = 7;
        } else {
          k = 6 - c;
        } 
        if (m < 1 << k) {
          k = m;
          m = k;
          if (c > '\000')
            m = k | 65408 >> c & 0xFF; 
          k = 0;
        } else {
          k = m & 0x3F | 0x80;
          int i3 = m >> 6;
          m = k;
          k = i3;
        } 
        int i2 = c + 1;
        c = Character.MIN_VALUE;
        while (c <= '\001') {
          int i4 = m & 0xF;
          if (i4 <= 9) {
            i4 += 48;
          } else {
            i4 = i4 - 10 + 65;
          } 
          stringBuffer.insert(n, (char)i4);
          m >>= 4;
          int i3 = c + 1;
        } 
        stringBuffer.insert(n, '%');
        m = k;
        int i1 = i2;
        if (k == 0)
          continue label79; 
      } 
    } 
    return stringBuffer.toString();
  }
  
  public static URIPath makeURI(Object paramObject) {
    URIPath uRIPath = coerceToURIPathOrNull(paramObject);
    if (uRIPath == null)
      throw new WrongType((String)null, -4, paramObject, "URI"); 
    return uRIPath;
  }
  
  public static URIPath valueOf(String paramString) {
    try {
      return new URIStringPath(new URI(encodeForUri(paramString, 'I')), paramString);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static URIPath valueOf(URI paramURI) {
    return new URIPath(paramURI);
  }
  
  public int compareTo(URIPath paramURIPath) {
    return this.uri.compareTo(paramURIPath.uri);
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof URIPath && this.uri.equals(((URIPath)paramObject).uri));
  }
  
  public boolean exists() {
    boolean bool = true;
    try {
      URLConnection uRLConnection = toURL().openConnection();
      if (uRLConnection instanceof HttpURLConnection) {
        if (((HttpURLConnection)uRLConnection).getResponseCode() == 200)
          return true; 
      } else {
        long l = uRLConnection.getLastModified();
        return (l == 0L) ? false : bool;
      } 
    } catch (Throwable throwable) {
      return false;
    } 
    return false;
  }
  
  public String getAuthority() {
    return this.uri.getAuthority();
  }
  
  public Path getCanonical() {
    if (isAbsolute()) {
      URI uRI = this.uri.normalize();
      return (uRI == this.uri) ? this : valueOf(uRI);
    } 
    return getAbsolute().getCanonical();
  }
  
  public long getContentLength() {
    return URLPath.getContentLength(toURL());
  }
  
  public String getFragment() {
    return this.uri.getFragment();
  }
  
  public String getHost() {
    return this.uri.getHost();
  }
  
  public long getLastModified() {
    return URLPath.getLastModified(toURL());
  }
  
  public String getPath() {
    return this.uri.getPath();
  }
  
  public int getPort() {
    return this.uri.getPort();
  }
  
  public String getQuery() {
    return this.uri.getQuery();
  }
  
  public String getScheme() {
    return this.uri.getScheme();
  }
  
  public String getUserInfo() {
    return this.uri.getUserInfo();
  }
  
  public int hashCode() {
    return this.uri.hashCode();
  }
  
  public boolean isAbsolute() {
    return this.uri.isAbsolute();
  }
  
  public InputStream openInputStream() throws IOException {
    return URLPath.openInputStream(toURL());
  }
  
  public OutputStream openOutputStream() throws IOException {
    return URLPath.openOutputStream(toURL());
  }
  
  public Path resolve(String paramString) {
    if (Path.uriSchemeSpecified(paramString))
      return valueOf(paramString); 
    char c = File.separatorChar;
    String str = paramString;
    if (c != '/') {
      if (paramString.length() >= 2 && ((paramString.charAt(1) == ':' && Character.isLetter(paramString.charAt(0))) || (paramString.charAt(0) == c && paramString.charAt(1) == c)))
        return FilePath.valueOf(new File(paramString)); 
      str = paramString.replace(c, '/');
    } 
    try {
      URI uRI = this.uri.resolve(new URI(null, str, null));
      return valueOf(uRI);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public String toString() {
    return toURIString();
  }
  
  public String toURIString() {
    return this.uri.toString();
  }
  
  public URL toURL() {
    return Path.toURL(this.uri.toString());
  }
  
  public URI toUri() {
    return this.uri;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/URIPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */