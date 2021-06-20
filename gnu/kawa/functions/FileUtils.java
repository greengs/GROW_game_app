package gnu.kawa.functions;

import java.io.File;
import java.io.IOException;

public class FileUtils {
  public static File createTempFile(String paramString) throws IOException {
    String str1 = paramString;
    if (paramString == null)
      str1 = "kawa~d.tmp"; 
    int i = str1.indexOf('~');
    File file = null;
    if (i < 0) {
      paramString = str1;
      str1 = ".tmp";
    } else {
      paramString = str1.substring(0, i);
      str1 = str1.substring(i + 2);
    } 
    i = paramString.indexOf(File.separatorChar);
    String str2 = paramString;
    if (i >= 0) {
      file = new File(paramString.substring(0, i));
      str2 = paramString.substring(i + 1);
    } 
    return File.createTempFile(str2, str1, file);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/FileUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */