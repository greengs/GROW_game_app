package gnu.text;

import gnu.mapping.WrappedException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class URLPath extends URIPath {
  final URL url;
  
  URLPath(URL paramURL) {
    super(toUri(paramURL));
    this.url = paramURL;
  }
  
  public static URLPath classResourcePath(Class paramClass) {
    URL uRL;
    try {
      URL uRL1 = ResourceStreamHandler.makeURL(paramClass);
      uRL = uRL1;
      return valueOf(uRL);
    } catch (SecurityException securityException) {
      String str = uRL.getName().replace('.', '/') + ".class";
      uRL = uRL.getClassLoader().getResource(str);
      return valueOf(uRL);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static int getContentLength(URL paramURL) {
    try {
      return paramURL.openConnection().getContentLength();
    } catch (Throwable throwable) {
      return -1;
    } 
  }
  
  public static long getLastModified(URL paramURL) {
    try {
      return paramURL.openConnection().getLastModified();
    } catch (Throwable throwable) {
      return 0L;
    } 
  }
  
  public static InputStream openInputStream(URL paramURL) throws IOException {
    return paramURL.openConnection().getInputStream();
  }
  
  public static OutputStream openOutputStream(URL paramURL) throws IOException {
    String str = paramURL.toString();
    if (str.startsWith("file:"))
      try {
        return new FileOutputStream(new File(new URI(str)));
      } catch (Throwable throwable) {} 
    URLConnection uRLConnection = paramURL.openConnection();
    uRLConnection.setDoInput(false);
    uRLConnection.setDoOutput(true);
    return uRLConnection.getOutputStream();
  }
  
  public static URI toUri(URL paramURL) {
    try {
      return paramURL.toURI();
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static URLPath valueOf(URL paramURL) {
    return new URLPath(paramURL);
  }
  
  public long getContentLength() {
    return getContentLength(this.url);
  }
  
  public long getLastModified() {
    return getLastModified(this.url);
  }
  
  public boolean isAbsolute() {
    return true;
  }
  
  public InputStream openInputStream() throws IOException {
    return openInputStream(this.url);
  }
  
  public OutputStream openOutputStream() throws IOException {
    return openOutputStream(this.url);
  }
  
  public Path resolve(String paramString) {
    try {
      return valueOf(new URL(this.url, paramString));
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public String toURIString() {
    return this.url.toString();
  }
  
  public URL toURL() {
    return this.url;
  }
  
  public URI toUri() {
    return toUri(this.url);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/URLPath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */