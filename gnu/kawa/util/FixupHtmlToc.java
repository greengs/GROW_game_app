package gnu.kawa.util;

import java.io.File;

public class FixupHtmlToc {
  static FileInfo[] argFiles;
  
  public static void main(String[] paramArrayOfString) {
    try {
      argFiles = new FileInfo[paramArrayOfString.length];
      for (int j = 0; j < paramArrayOfString.length; j++) {
        FileInfo fileInfo = FileInfo.find(new File(paramArrayOfString[j]));
        fileInfo.writeNeeded = true;
        argFiles[j] = fileInfo;
      } 
    } catch (Throwable throwable) {
      System.err.println("caught " + throwable);
      throwable.printStackTrace();
      return;
    } 
    int i;
    for (i = 0; i < throwable.length; i++) {
      argFiles[i].scan();
      argFiles[i].write();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/util/FixupHtmlToc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */