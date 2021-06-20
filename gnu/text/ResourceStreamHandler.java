package gnu.text;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class ResourceStreamHandler extends URLStreamHandler {
  public static final String CLASS_RESOURCE_URI_PREFIX = "class-resource:/";
  
  public static final int CLASS_RESOURCE_URI_PREFIX_LENGTH = 16;
  
  ClassLoader cloader;
  
  public ResourceStreamHandler(ClassLoader paramClassLoader) {
    this.cloader = paramClassLoader;
  }
  
  public static URL makeURL(Class paramClass) throws MalformedURLException {
    String str2 = paramClass.getName();
    int i = str2.lastIndexOf('.');
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("class-resource:/");
    String str1 = str2;
    if (i >= 0) {
      stringBuilder.append(str2.substring(0, i));
      stringBuilder.append('/');
      str1 = str2.substring(i + 1);
    } 
    stringBuilder.append(str1);
    return new URL(null, stringBuilder.toString(), new ResourceStreamHandler(paramClass.getClassLoader()));
  }
  
  public URLConnection openConnection(URL paramURL) throws IOException {
    String str3 = paramURL.toString();
    String str2 = str3.substring(16);
    int i = str2.indexOf('/');
    String str1 = str2;
    if (i > 0)
      str1 = str2.substring(0, i).replace('.', '/') + str2.substring(i); 
    URL uRL = this.cloader.getResource(str1);
    if (uRL == null)
      throw new FileNotFoundException(str3); 
    return uRL.openConnection();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/ResourceStreamHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */