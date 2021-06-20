package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.errors.RuntimeError;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;

public class FileUtil {
  private static final String DIRECTORY_DOWNLOADS = "Downloads";
  
  private static final String DIRECTORY_PICTURES = "Pictures";
  
  private static final String DIRECTORY_RECORDINGS = "Recordings";
  
  private static final String DOCUMENT_DIRECTORY = "My Documents/";
  
  private static final String FILENAME_PREFIX = "app_inventor_";
  
  private static final String LOG_TAG = FileUtil.class.getSimpleName();
  
  public static void checkExternalStorageWriteable() throws FileException {
    String str = Environment.getExternalStorageState();
    if ("mounted".equals(str))
      return; 
    if ("mounted_ro".equals(str))
      throw new FileException(704); 
    throw new FileException(705);
  }
  
  private static void copy(InputStream paramInputStream, OutputStream paramOutputStream) throws IOException {
    paramOutputStream = new BufferedOutputStream(paramOutputStream, 4096);
    paramInputStream = new BufferedInputStream(paramInputStream, 4096);
    while (true) {
      int i = paramInputStream.read();
      if (i == -1) {
        paramOutputStream.flush();
        return;
      } 
      paramOutputStream.write(i);
    } 
  }
  
  public static String copyFile(String paramString1, String paramString2) throws IOException {
    FileInputStream fileInputStream = new FileInputStream(paramString1);
    try {
      paramString2 = writeStreamToFile(fileInputStream, paramString2);
      return paramString2;
    } finally {
      fileInputStream.close();
    } 
  }
  
  public static String downloadUrlToFile(String paramString1, String paramString2) throws IOException {
    InputStream inputStream = (new URL(paramString1)).openStream();
    try {
      paramString2 = writeStreamToFile(inputStream, paramString2);
      return paramString2;
    } finally {
      inputStream.close();
    } 
  }
  
  public static File getDownloadFile(Form paramForm, String paramString) throws IOException, FileException {
    return getFile(paramForm, "Downloads", paramString);
  }
  
  @Deprecated
  public static File getDownloadFile(String paramString) throws IOException, FileException {
    Log.w(LOG_TAG, "Calling deprecated function getDownloadFile", new IllegalAccessException());
    return getDownloadFile(Form.getActiveForm(), paramString);
  }
  
  public static File getExternalFile(Form paramForm, String paramString) throws IOException, FileException, SecurityException {
    checkExternalStorageWriteable();
    File file1 = new File(QUtil.getExternalStoragePath((Context)paramForm), paramString);
    File file2 = file1.getParentFile();
    if (paramForm != null)
      paramForm.assertPermission("android.permission.WRITE_EXTERNAL_STORAGE"); 
    if (!file2.exists() && !file2.mkdirs())
      throw new IOException("Unable to create directory " + file2.getAbsolutePath()); 
    if (file1.exists() && !file1.delete())
      throw new IOException("Cannot overwrite existing file " + file1.getAbsolutePath()); 
    return file1;
  }
  
  @Deprecated
  public static File getExternalFile(String paramString) throws IOException, FileException, SecurityException {
    return getExternalFile(Form.getActiveForm(), paramString);
  }
  
  private static File getFile(Form paramForm, String paramString1, String paramString2) throws IOException, FileException {
    return getExternalFile(paramForm, "My Documents/" + paramString1 + "/" + "app_inventor_" + System.currentTimeMillis() + "." + paramString2);
  }
  
  public static String getFileUrl(String paramString) {
    return (new File(paramString)).toURI().toString();
  }
  
  public static File getPictureFile(Form paramForm, String paramString) throws IOException, FileException {
    return getFile(paramForm, "Pictures", paramString);
  }
  
  @Deprecated
  public static File getPictureFile(String paramString) throws IOException, FileException {
    Log.w(LOG_TAG, "Calling deprecated function getPictureFile", new IllegalAccessException());
    return getPictureFile(Form.getActiveForm(), paramString);
  }
  
  public static File getRecordingFile(Form paramForm, String paramString) throws IOException, FileException {
    return getFile(paramForm, "Recordings", paramString);
  }
  
  @Deprecated
  public static File getRecordingFile(String paramString) throws IOException, FileException {
    return getRecordingFile(Form.getActiveForm(), paramString);
  }
  
  public static FileInputStream openFile(Form paramForm, File paramFile) throws IOException, PermissionException {
    return openFile(paramForm, paramFile.getAbsolutePath());
  }
  
  public static FileInputStream openFile(Form paramForm, String paramString) throws IOException, PermissionException {
    if (MediaUtil.isExternalFile((Context)paramForm, paramString))
      paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
    return new FileInputStream(paramString);
  }
  
  public static FileInputStream openFile(Form paramForm, URI paramURI) throws IOException, PermissionException {
    if (MediaUtil.isExternalFileUrl((Context)paramForm, paramURI.toString()))
      paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
    return new FileInputStream(new File(paramURI));
  }
  
  @Deprecated
  public static FileInputStream openFile(File paramFile) throws IOException, PermissionException {
    Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
    return openFile(Form.getActiveForm(), paramFile.getAbsolutePath());
  }
  
  @Deprecated
  public static FileInputStream openFile(String paramString) throws IOException, PermissionException {
    Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
    return openFile(Form.getActiveForm(), paramString);
  }
  
  @Deprecated
  public static FileInputStream openFile(URI paramURI) throws IOException, PermissionException {
    Log.w(LOG_TAG, "Calling deprecated function openFile", new IllegalAccessException());
    return openFile(Form.getActiveForm(), paramURI);
  }
  
  public static byte[] readFile(Form paramForm, String paramString) throws IOException {
    File file = new File(paramString);
    if (!file.isFile())
      throw new FileNotFoundException("Cannot find file: " + paramString); 
    FileInputStream fileInputStream = null;
    try {
      FileInputStream fileInputStream1 = openFile(paramForm, paramString);
      fileInputStream = fileInputStream1;
      int j = (int)file.length();
      fileInputStream = fileInputStream1;
      byte[] arrayOfByte = new byte[j];
      int i = 0;
      while (true) {
        fileInputStream = fileInputStream1;
        int m = fileInputStream1.read(arrayOfByte, i, j - i);
        int k = i;
        if (m > 0)
          k = i + m; 
        if (k != j) {
          i = k;
          if (m < 0)
            return arrayOfByte; 
          continue;
        } 
        return arrayOfByte;
      } 
    } finally {
      if (fileInputStream != null)
        fileInputStream.close(); 
    } 
  }
  
  @Deprecated
  public static byte[] readFile(String paramString) throws IOException {
    Log.w(LOG_TAG, "Calling deprecated function readFile", new IllegalAccessException());
    return readFile(Form.getActiveForm(), paramString);
  }
  
  public static String writeFile(byte[] paramArrayOfbyte, String paramString) throws IOException {
    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(paramArrayOfbyte);
    try {
      paramString = writeStreamToFile(byteArrayInputStream, paramString);
      return paramString;
    } finally {
      byteArrayInputStream.close();
    } 
  }
  
  public static String writeStreamToFile(InputStream paramInputStream, String paramString) throws IOException {
    File file = new File(paramString);
    file.getParentFile().mkdirs();
    FileOutputStream fileOutputStream = new FileOutputStream(file);
    try {
      copy(paramInputStream, fileOutputStream);
      return file.toURI().toString();
    } finally {
      fileOutputStream.flush();
      fileOutputStream.close();
    } 
  }
  
  public static class FileException extends RuntimeError {
    private final int msgNumber;
    
    public FileException(int param1Int) {
      this.msgNumber = param1Int;
    }
    
    public int getErrorMessageNumber() {
      return this.msgNumber;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/FileUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */