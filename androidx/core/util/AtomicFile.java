package androidx.core.util;

import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class AtomicFile {
  private final File mBackupName;
  
  private final File mBaseName;
  
  public AtomicFile(@NonNull File paramFile) {
    this.mBaseName = paramFile;
    this.mBackupName = new File(paramFile.getPath() + ".bak");
  }
  
  private static boolean sync(@NonNull FileOutputStream paramFileOutputStream) {
    try {
      paramFileOutputStream.getFD().sync();
      return true;
    } catch (IOException iOException) {
      return false;
    } 
  }
  
  public void delete() {
    this.mBaseName.delete();
    this.mBackupName.delete();
  }
  
  public void failWrite(@Nullable FileOutputStream paramFileOutputStream) {
    if (paramFileOutputStream != null) {
      sync(paramFileOutputStream);
      try {
        paramFileOutputStream.close();
        this.mBaseName.delete();
        this.mBackupName.renameTo(this.mBaseName);
        return;
      } catch (IOException iOException) {
        Log.w("AtomicFile", "failWrite: Got exception:", iOException);
        return;
      } 
    } 
  }
  
  public void finishWrite(@Nullable FileOutputStream paramFileOutputStream) {
    if (paramFileOutputStream != null) {
      sync(paramFileOutputStream);
      try {
        paramFileOutputStream.close();
        this.mBackupName.delete();
        return;
      } catch (IOException iOException) {
        Log.w("AtomicFile", "finishWrite: Got exception:", iOException);
        return;
      } 
    } 
  }
  
  @NonNull
  public File getBaseFile() {
    return this.mBaseName;
  }
  
  @NonNull
  public FileInputStream openRead() throws FileNotFoundException {
    if (this.mBackupName.exists()) {
      this.mBaseName.delete();
      this.mBackupName.renameTo(this.mBaseName);
    } 
    return new FileInputStream(this.mBaseName);
  }
  
  @NonNull
  public byte[] readFully() throws IOException {
    FileInputStream fileInputStream = openRead();
    int i = 0;
    try {
      byte[] arrayOfByte = new byte[fileInputStream.available()];
      while (true) {
        int j = fileInputStream.read(arrayOfByte, i, arrayOfByte.length - i);
        if (j <= 0)
          return arrayOfByte; 
        j = i + j;
        int k = fileInputStream.available();
        i = j;
        if (k > arrayOfByte.length - j) {
          byte[] arrayOfByte1 = new byte[j + k];
          System.arraycopy(arrayOfByte, 0, arrayOfByte1, 0, j);
          arrayOfByte = arrayOfByte1;
          i = j;
        } 
      } 
    } finally {
      fileInputStream.close();
    } 
  }
  
  @NonNull
  public FileOutputStream startWrite() throws IOException {
    if (this.mBaseName.exists())
      if (!this.mBackupName.exists()) {
        if (!this.mBaseName.renameTo(this.mBackupName))
          Log.w("AtomicFile", "Couldn't rename file " + this.mBaseName + " to backup file " + this.mBackupName); 
      } else {
        this.mBaseName.delete();
      }  
    try {
      return new FileOutputStream(this.mBaseName);
    } catch (FileNotFoundException fileNotFoundException) {
      if (!this.mBaseName.getParentFile().mkdirs())
        throw new IOException("Couldn't create directory " + this.mBaseName); 
      try {
        return new FileOutputStream(this.mBaseName);
      } catch (FileNotFoundException fileNotFoundException1) {
        throw new IOException("Couldn't create " + this.mBaseName);
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/util/AtomicFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */