package gnu.bytecode;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class ZipLoader extends ClassLoader {
  private Vector<Object> loadedClasses;
  
  int size;
  
  ZipFile zar;
  
  private String zipname;
  
  public ZipLoader(String paramString) throws IOException {
    this.zipname = paramString;
    this.zar = new ZipFile(paramString);
    this.size = 0;
    Enumeration<? extends ZipEntry> enumeration = this.zar.entries();
    while (enumeration.hasMoreElements()) {
      if (!((ZipEntry)enumeration.nextElement()).isDirectory())
        this.size++; 
    } 
    this.loadedClasses = new Vector(this.size);
  }
  
  public void close() throws IOException {
    if (this.zar != null)
      this.zar.close(); 
    this.zar = null;
  }
  
  public Class loadAllClasses() throws IOException {
    Class<?> clazz;
    Enumeration<? extends ZipEntry> enumeration = this.zar.entries();
    InputStream inputStream = null;
    while (enumeration.hasMoreElements()) {
      Class<?> clazz1;
      ZipEntry zipEntry = enumeration.nextElement();
      String str1 = zipEntry.getName().replace('/', '.');
      String str2 = str1.substring(0, str1.length() - "/class".length());
      int i = (int)zipEntry.getSize();
      InputStream inputStream1 = this.zar.getInputStream(zipEntry);
      byte[] arrayOfByte = new byte[i];
      (new DataInputStream(inputStream1)).readFully(arrayOfByte);
      Class<?> clazz2 = defineClass(str2, arrayOfByte, 0, i);
      inputStream1 = inputStream;
      if (inputStream == null)
        clazz1 = clazz2; 
      this.loadedClasses.addElement(str2);
      this.loadedClasses.addElement(clazz2);
      clazz = clazz1;
    } 
    close();
    return clazz;
  }
  
  public Class loadClass(String paramString, boolean paramBoolean) throws ClassNotFoundException {
    ZipEntry zipEntry;
    int i = this.loadedClasses.indexOf(paramString);
    if (i >= 0) {
      Class clazz = (Class)this.loadedClasses.elementAt(i + 1);
    } else if (this.zar == null && this.loadedClasses.size() == this.size * 2) {
      Class<?> clazz = Class.forName(paramString);
    } else {
      i = 0;
      String str = paramString.replace('.', '/') + ".class";
      if (this.zar == null)
        try {
          this.zar = new ZipFile(this.zipname);
          i = 1;
          ZipEntry zipEntry1 = this.zar.getEntry(str);
        } catch (IOException iOException) {
          throw new ClassNotFoundException("IOException while loading " + str + " from ziparchive \"" + paramString + "\": " + iOException.toString());
        }  
      zipEntry = this.zar.getEntry(str);
    } 
    if (paramBoolean)
      resolveClass((Class<?>)zipEntry); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/ZipLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */