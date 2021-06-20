package gnu.bytecode;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZipArchive {
  public static long copy(InputStream paramInputStream, OutputStream paramOutputStream, byte[] paramArrayOfbyte) throws IOException {
    long l;
    for (l = 0L;; l += i) {
      int i = paramInputStream.read(paramArrayOfbyte);
      if (i <= 0)
        return l; 
      paramOutputStream.write(paramArrayOfbyte, 0, i);
    } 
  }
  
  public static void copy(InputStream paramInputStream, String paramString, byte[] paramArrayOfbyte) throws IOException {
    File file = new File(paramString);
    String str = file.getParent();
    if (str != null) {
      File file1 = new File(str);
      if (!file1.exists())
        System.err.println("mkdirs:" + file1.mkdirs()); 
    } 
    if (paramString.charAt(paramString.length() - 1) != '/') {
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
      copy(paramInputStream, bufferedOutputStream, paramArrayOfbyte);
      bufferedOutputStream.close();
    } 
  }
  
  public static void main(String[] paramArrayOfString) throws IOException {
    ZipEntry zipEntry;
    if (paramArrayOfString.length < 2)
      usage(); 
    String str1 = paramArrayOfString[0];
    String str2 = paramArrayOfString[1];
    try {
      if (str1.equals("t") || str1.equals("p") || str1.equals("x")) {
        ZipInputStream zipInputStream;
        PrintStream printStream = System.out;
        byte[] arrayOfByte = new byte[1024];
        if (paramArrayOfString.length == 2) {
          zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(str2)));
          while (true) {
            String str;
            zipEntry = zipInputStream.getNextEntry();
            if (zipEntry != null) {
              str = zipEntry.getName();
              if (str1.equals("t")) {
                printStream.print(str);
                printStream.print(" size: ");
                printStream.println(zipEntry.getSize());
                continue;
              } 
            } else {
              return;
            } 
            if (str1.equals("p")) {
              copy(zipInputStream, printStream, arrayOfByte);
              continue;
            } 
            copy(zipInputStream, str, arrayOfByte);
          } 
        } 
        ZipFile zipFile = new ZipFile((String)zipEntry);
        int i = 2;
        while (true) {
          if (i < zipInputStream.length) {
            ZipInputStream zipInputStream1 = zipInputStream[i];
            ZipEntry zipEntry1 = zipFile.getEntry((String)zipInputStream1);
            if (zipEntry1 == null) {
              System.err.println("zipfile " + zipEntry + ":" + zipInputStream[i] + " - not found");
              System.exit(-1);
            } else if (str1.equals("t")) {
              printStream.print((String)zipInputStream1);
              printStream.print(" size: ");
              printStream.println(zipEntry1.getSize());
            } else if (str1.equals("p")) {
              copy(zipFile.getInputStream(zipEntry1), printStream, arrayOfByte);
            } else {
              copy(zipFile.getInputStream(zipEntry1), (String)zipInputStream1, arrayOfByte);
            } 
            i++;
            continue;
          } 
          return;
        } 
      } 
    } catch (IOException iOException) {
      System.err.println("I/O Exception:  " + iOException);
      return;
    } 
    if (str1.equals("q")) {
      ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream((String)zipEntry));
      for (int i = 2; i < iOException.length; i++) {
        File file = new File((String)iOException[i]);
        if (!file.exists())
          throw new IOException(iOException[i] + " - not found"); 
        if (!file.canRead())
          throw new IOException(iOException[i] + " - not readable"); 
        int j = (int)file.length();
        FileInputStream fileInputStream = new FileInputStream(file);
        byte[] arrayOfByte = new byte[j];
        if (fileInputStream.read(arrayOfByte) != j)
          throw new IOException(iOException[i] + " - read error"); 
        fileInputStream.close();
        ZipEntry zipEntry1 = new ZipEntry((String)iOException[i]);
        zipEntry1.setSize(j);
        zipEntry1.setTime(file.lastModified());
        zipOutputStream.putNextEntry(zipEntry1);
        zipOutputStream.write(arrayOfByte, 0, j);
      } 
      zipOutputStream.close();
      return;
    } 
    usage();
  }
  
  private static void usage() {
    System.err.println("zipfile [ptxq] archive [file ...]");
    System.exit(-1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ZipArchive.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */