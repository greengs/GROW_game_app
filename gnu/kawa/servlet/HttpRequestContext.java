package gnu.kawa.servlet;

import gnu.mapping.InPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Map;

public abstract class HttpRequestContext {
  public static final int HTTP_NOT_FOUND = 404;
  
  public static final int HTTP_OK = 200;
  
  static final int STATUS_SENT = -999;
  
  public static int importServletDefinitions;
  
  protected static final ThreadLocal<HttpRequestContext> instance = new ThreadLocal<HttpRequestContext>();
  
  ServletPrinter consumer;
  
  String localPath = "";
  
  String scriptPath = "";
  
  public int statusCode = 200;
  
  public String statusReasonPhrase = null;
  
  public static HttpRequestContext getInstance() {
    HttpRequestContext httpRequestContext = instance.get();
    if (httpRequestContext == null)
      throw new UnsupportedOperationException("can only be called by http-server"); 
    return httpRequestContext;
  }
  
  public static HttpRequestContext getInstance(String paramString) {
    HttpRequestContext httpRequestContext = instance.get();
    if (httpRequestContext == null)
      throw new UnsupportedOperationException(paramString + " can only be called within http-server"); 
    return httpRequestContext;
  }
  
  public static void setInstance(HttpRequestContext paramHttpRequestContext) {
    instance.set(paramHttpRequestContext);
  }
  
  public abstract Object getAttribute(String paramString);
  
  public ServletPrinter getConsumer() throws IOException {
    if (this.consumer == null)
      this.consumer = new ServletPrinter(this, 8192); 
    return this.consumer;
  }
  
  public abstract String getContextPath();
  
  public InetAddress getLocalHost() {
    try {
      return InetAddress.getLocalHost();
    } catch (Throwable throwable) {
      throw new RuntimeException(throwable);
    } 
  }
  
  public String getLocalIPAddress() {
    return getLocalHost().getHostAddress();
  }
  
  public String getLocalPath() {
    return this.localPath;
  }
  
  public abstract int getLocalPort();
  
  public InetSocketAddress getLocalSocketAddress() {
    return new InetSocketAddress(getLocalHost(), getLocalPort());
  }
  
  public abstract String getPathTranslated();
  
  public abstract String getQueryString();
  
  public abstract InetAddress getRemoteHost();
  
  public abstract String getRemoteIPAddress();
  
  public abstract int getRemotePort();
  
  public InetSocketAddress getRemoteSocketAddress() {
    return new InetSocketAddress(getRemoteHost(), getRemotePort());
  }
  
  public String getRequestBodyChars() throws IOException {
    InputStreamReader inputStreamReader = new InputStreamReader(getRequestStream());
    int j = 1024;
    char[] arrayOfChar = new char[1024];
    int i = 0;
    while (true) {
      int m = j - i;
      char[] arrayOfChar1 = arrayOfChar;
      int k = j;
      if (m <= 0) {
        arrayOfChar1 = new char[j * 2];
        System.arraycopy(arrayOfChar, 0, arrayOfChar1, 0, j);
        k = j + j;
      } 
      j = inputStreamReader.read(arrayOfChar1, i, m);
      if (j < 0) {
        inputStreamReader.close();
        return new String(arrayOfChar1, 0, i);
      } 
      i += j;
      arrayOfChar = arrayOfChar1;
      j = k;
    } 
  }
  
  public abstract String getRequestHeader(String paramString);
  
  public abstract List<String> getRequestHeaders(String paramString);
  
  public abstract Map<String, List<String>> getRequestHeaders();
  
  public abstract String getRequestMethod();
  
  public String getRequestParameter(String paramString) {
    List<String> list = getRequestParameters().get(paramString);
    return (list == null || list.isEmpty()) ? null : list.get(0);
  }
  
  public abstract Map<String, List<String>> getRequestParameters();
  
  public String getRequestPath() {
    return getRequestURI().getPath();
  }
  
  public InPort getRequestPort() {
    return new InPort(getRequestStream());
  }
  
  public String getRequestScheme() {
    return "http";
  }
  
  public abstract InputStream getRequestStream();
  
  public abstract URI getRequestURI();
  
  public StringBuffer getRequestURLBuffer() {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(getRequestScheme());
    stringBuffer.append("://");
    String str = getRequestHeader("Host");
    if (str != null) {
      stringBuffer.append(str);
      stringBuffer.append(getRequestPath());
      return stringBuffer;
    } 
    stringBuffer.append(getLocalIPAddress());
    stringBuffer.append(':');
    stringBuffer.append(getLocalPort());
    stringBuffer.append(getRequestPath());
    return stringBuffer;
  }
  
  public abstract URL getResourceURL(String paramString);
  
  public abstract OutputStream getResponseStream();
  
  public String getScriptPath() {
    return this.scriptPath;
  }
  
  public abstract void log(String paramString);
  
  public abstract void log(String paramString, Throwable paramThrowable);
  
  protected String normalizeToContext(String paramString) {
    if (paramString.length() > 0 && paramString.charAt(0) == '/') {
      paramString = paramString.substring(1);
    } else {
      paramString = getScriptPath() + paramString;
    } 
    String str = paramString;
    if (paramString.indexOf("..") >= 0) {
      paramString = URI.create(paramString).normalize().toString();
      str = paramString;
      if (paramString.startsWith("../"))
        return null; 
    } 
    return str;
  }
  
  public abstract boolean reset(boolean paramBoolean);
  
  public void sendNotFound(String paramString) throws IOException {
    byte[] arrayOfByte = ("The requested URL " + paramString + " was not found on this server.\r\n").getBytes();
    sendResponseHeaders(404, null, arrayOfByte.length);
    OutputStream outputStream = getResponseStream();
    try {
      outputStream.write(arrayOfByte);
      return;
    } catch (IOException iOException) {
      throw new RuntimeException(iOException);
    } 
  }
  
  public abstract void sendResponseHeaders(int paramInt, String paramString, long paramLong) throws IOException;
  
  public abstract void setAttribute(String paramString, Object paramObject);
  
  public void setContentType(String paramString) {
    setResponseHeader("Content-Type", paramString);
  }
  
  public abstract void setResponseHeader(String paramString1, String paramString2);
  
  public void setScriptAndLocalPath(String paramString1, String paramString2) {
    this.scriptPath = paramString1;
    this.localPath = paramString2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/servlet/HttpRequestContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */