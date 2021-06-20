package gnu.text;

import gnu.mapping.WrappedException;
import gnu.mapping.WrongType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FilePath extends Path implements Comparable<FilePath> {
  final File file;
  
  final String path;
  
  private FilePath(File paramFile) {
    this.file = paramFile;
    this.path = paramFile.toString();
  }
  
  private FilePath(File paramFile, String paramString) {
    this.file = paramFile;
    this.path = paramString;
  }
  
  public static FilePath coerceToFilePathOrNull(Object paramObject) {
    if (paramObject instanceof FilePath)
      return (FilePath)paramObject; 
    if (paramObject instanceof URIPath)
      return valueOf(new File(((URIPath)paramObject).uri)); 
    if (paramObject instanceof URI)
      return valueOf(new File((URI)paramObject)); 
    if (paramObject instanceof File)
      return valueOf((File)paramObject); 
    if (paramObject instanceof gnu.lists.FString) {
      paramObject = paramObject.toString();
      return valueOf((String)paramObject);
    } 
    if (paramObject instanceof String) {
      paramObject = paramObject;
      return valueOf((String)paramObject);
    } 
    return null;
  }
  
  public static FilePath makeFilePath(Object paramObject) {
    FilePath filePath = coerceToFilePathOrNull(paramObject);
    if (filePath == null)
      throw new WrongType((String)null, -4, paramObject, "filepath"); 
    return filePath;
  }
  
  private static URI toUri(File paramFile) {
    try {
      if (paramFile.isAbsolute())
        return paramFile.toURI(); 
      String str2 = paramFile.toString();
      char c = File.separatorChar;
      String str1 = str2;
      if (c != '/')
        str1 = str2.replace(c, '/'); 
      return new URI(null, null, str1, null);
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public static FilePath valueOf(File paramFile) {
    return new FilePath(paramFile);
  }
  
  public static FilePath valueOf(String paramString) {
    return new FilePath(new File(paramString), paramString);
  }
  
  public int compareTo(FilePath paramFilePath) {
    return this.file.compareTo(paramFilePath.file);
  }
  
  public boolean delete() {
    return toFile().delete();
  }
  
  public boolean equals(Object paramObject) {
    return (paramObject instanceof FilePath && this.file.equals(((FilePath)paramObject).file));
  }
  
  public boolean exists() {
    return this.file.exists();
  }
  
  public Path getCanonical() {
    try {
      File file = this.file.getCanonicalFile();
      FilePath filePath = this;
      if (!file.equals(this.file))
        filePath = valueOf(file); 
      return filePath;
    } catch (Throwable throwable) {
      return this;
    } 
  }
  
  public long getContentLength() {
    long l2 = this.file.length();
    long l1 = l2;
    if (l2 == 0L) {
      l1 = l2;
      if (!this.file.exists())
        l1 = -1L; 
    } 
    return l1;
  }
  
  public String getLast() {
    return this.file.getName();
  }
  
  public long getLastModified() {
    return this.file.lastModified();
  }
  
  public FilePath getParent() {
    File file = this.file.getParentFile();
    return (file == null) ? null : valueOf(file);
  }
  
  public String getPath() {
    return this.file.getPath();
  }
  
  public String getScheme() {
    return isAbsolute() ? "file" : null;
  }
  
  public int hashCode() {
    return this.file.hashCode();
  }
  
  public boolean isAbsolute() {
    return (this == Path.userDirPath || this.file.isAbsolute());
  }
  
  public boolean isDirectory() {
    if (!this.file.isDirectory()) {
      if (!this.file.exists()) {
        int i = this.path.length();
        if (i > 0) {
          i = this.path.charAt(i - 1);
          return !(i != 47 && i != File.separatorChar);
        } 
      } 
      return false;
    } 
    return true;
  }
  
  public InputStream openInputStream() throws IOException {
    return new FileInputStream(this.file);
  }
  
  public OutputStream openOutputStream() throws IOException {
    return new FileOutputStream(this.file);
  }
  
  public Path resolve(String paramString) {
    if (Path.uriSchemeSpecified(paramString))
      return URLPath.valueOf(paramString); 
    File file2 = new File(paramString);
    if (file2.isAbsolute())
      return valueOf(file2); 
    char c = File.separatorChar;
    String str = paramString;
    if (c != '/')
      str = paramString.replace('/', c); 
    if (this == Path.userDirPath) {
      file1 = new File(System.getProperty("user.dir"), str);
      return valueOf(file1);
    } 
    if (isDirectory()) {
      file1 = this.file;
    } else {
      file1 = this.file.getParentFile();
    } 
    File file1 = new File(file1, str);
    return valueOf(file1);
  }
  
  public File toFile() {
    return this.file;
  }
  
  public String toString() {
    return this.path;
  }
  
  public URL toURL() {
    if (this == Path.userDirPath)
      return resolve("").toURL(); 
    if (!isAbsolute())
      return getAbsolute().toURL(); 
    try {
      return this.file.toURI().toURL();
    } catch (Throwable throwable) {
      throw WrappedException.wrapIfNeeded(throwable);
    } 
  }
  
  public URI toUri() {
    return (this == Path.userDirPath) ? resolve("").toURI() : toUri(this.file);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/FilePath.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */