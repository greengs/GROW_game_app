package com.google.appinventor.components.runtime;

import android.content.Context;
import android.util.Log;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.errors.PermissionException;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.QUtil;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

@DesignerComponent(category = ComponentCategory.STORAGE, description = "Non-visible component for storing and retrieving files. Use this component to write or read files on your device. The default behaviour is to write files to the private data directory associated with your App. The Companion is special cased to write files to /sdcard/AppInventor/data to facilitate debugging. If the file path starts with a slash (/), then the file is created relative to /sdcard. For example writing a file to /myFile.txt will write the file in /sdcard/myFile.txt.", iconName = "images/file.png", nonVisible = true, version = 3)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.WRITE_EXTERNAL_STORAGE, android.permission.READ_EXTERNAL_STORAGE")
public class File extends AndroidNonvisibleComponent implements Component {
  private static final int BUFFER_LENGTH = 4096;
  
  private static final String LOG_TAG = "FileComponent";
  
  private boolean legacy = false;
  
  public File(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    LegacyMode(false);
  }
  
  private String AbsoluteFileName(String paramString, boolean paramBoolean) {
    java.io.File file;
    if (paramString.startsWith("/"))
      return QUtil.getExternalStoragePath((Context)this.form, false, paramBoolean) + paramString; 
    if (this.form.isRepl()) {
      file = new java.io.File(QUtil.getReplDataPath((Context)this.form, false));
    } else {
      file = this.form.getFilesDir();
    } 
    if (!file.exists())
      file.mkdirs(); 
    return file.getPath() + "/" + paramString;
  }
  
  private void AsyncRead(InputStream paramInputStream, String paramString) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: aconst_null
    //   4: astore #4
    //   6: aconst_null
    //   7: astore #6
    //   9: new java/io/InputStreamReader
    //   12: dup
    //   13: aload_1
    //   14: invokespecial <init> : (Ljava/io/InputStream;)V
    //   17: astore_1
    //   18: new java/io/StringWriter
    //   21: dup
    //   22: invokespecial <init> : ()V
    //   25: astore #4
    //   27: sipush #4096
    //   30: newarray char
    //   32: astore #5
    //   34: aload_1
    //   35: aload #5
    //   37: iconst_0
    //   38: sipush #4096
    //   41: invokevirtual read : ([CII)I
    //   44: istore_3
    //   45: iload_3
    //   46: ifle -> 109
    //   49: aload #4
    //   51: aload #5
    //   53: iconst_0
    //   54: iload_3
    //   55: invokevirtual write : ([CII)V
    //   58: goto -> 34
    //   61: astore #5
    //   63: aload_1
    //   64: astore #4
    //   66: ldc 'FileComponent'
    //   68: ldc 'FileNotFoundException'
    //   70: aload #5
    //   72: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   75: pop
    //   76: aload_1
    //   77: astore #4
    //   79: aload_0
    //   80: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   83: aload_0
    //   84: ldc 'ReadFrom'
    //   86: sipush #2101
    //   89: iconst_1
    //   90: anewarray java/lang/Object
    //   93: dup
    //   94: iconst_0
    //   95: aload_2
    //   96: aastore
    //   97: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   100: aload_1
    //   101: ifnull -> 108
    //   104: aload_1
    //   105: invokevirtual close : ()V
    //   108: return
    //   109: aload_0
    //   110: aload #4
    //   112: invokevirtual toString : ()Ljava/lang/String;
    //   115: invokespecial normalizeNewLines : (Ljava/lang/String;)Ljava/lang/String;
    //   118: astore #4
    //   120: aload_0
    //   121: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   124: new com/google/appinventor/components/runtime/File$5
    //   127: dup
    //   128: aload_0
    //   129: aload #4
    //   131: invokespecial <init> : (Lcom/google/appinventor/components/runtime/File;Ljava/lang/String;)V
    //   134: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
    //   137: aload_1
    //   138: ifnull -> 246
    //   141: aload_1
    //   142: invokevirtual close : ()V
    //   145: return
    //   146: astore_1
    //   147: return
    //   148: astore #4
    //   150: aload #5
    //   152: astore_1
    //   153: aload #4
    //   155: astore #5
    //   157: aload_1
    //   158: astore #4
    //   160: ldc 'FileComponent'
    //   162: ldc 'IOException'
    //   164: aload #5
    //   166: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   169: pop
    //   170: aload_1
    //   171: astore #4
    //   173: aload_0
    //   174: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   177: aload_0
    //   178: ldc 'ReadFrom'
    //   180: sipush #2102
    //   183: iconst_1
    //   184: anewarray java/lang/Object
    //   187: dup
    //   188: iconst_0
    //   189: aload_2
    //   190: aastore
    //   191: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   194: aload_1
    //   195: ifnull -> 108
    //   198: aload_1
    //   199: invokevirtual close : ()V
    //   202: return
    //   203: astore_1
    //   204: return
    //   205: astore_1
    //   206: aload #4
    //   208: ifnull -> 216
    //   211: aload #4
    //   213: invokevirtual close : ()V
    //   216: aload_1
    //   217: athrow
    //   218: astore_1
    //   219: return
    //   220: astore_2
    //   221: goto -> 216
    //   224: astore_2
    //   225: aload_1
    //   226: astore #4
    //   228: aload_2
    //   229: astore_1
    //   230: goto -> 206
    //   233: astore #5
    //   235: goto -> 157
    //   238: astore #5
    //   240: aload #6
    //   242: astore_1
    //   243: goto -> 63
    //   246: return
    // Exception table:
    //   from	to	target	type
    //   9	18	238	java/io/FileNotFoundException
    //   9	18	148	java/io/IOException
    //   9	18	205	finally
    //   18	34	61	java/io/FileNotFoundException
    //   18	34	233	java/io/IOException
    //   18	34	224	finally
    //   34	45	61	java/io/FileNotFoundException
    //   34	45	233	java/io/IOException
    //   34	45	224	finally
    //   49	58	61	java/io/FileNotFoundException
    //   49	58	233	java/io/IOException
    //   49	58	224	finally
    //   66	76	205	finally
    //   79	100	205	finally
    //   104	108	218	java/io/IOException
    //   109	137	61	java/io/FileNotFoundException
    //   109	137	233	java/io/IOException
    //   109	137	224	finally
    //   141	145	146	java/io/IOException
    //   160	170	205	finally
    //   173	194	205	finally
    //   198	202	203	java/io/IOException
    //   211	216	220	java/io/IOException
  }
  
  private void Write(final String filename, final String text, final boolean append) {
    if (filename.startsWith("//")) {
      if (append) {
        this.form.dispatchErrorOccurredEvent(this, "AppendTo", 2106, new Object[] { filename });
        return;
      } 
      this.form.dispatchErrorOccurredEvent(this, "SaveFile", 2106, new Object[] { filename });
      return;
    } 
    final Runnable operation = new Runnable() {
        public void run() {
          String str = File.this.AbsoluteFileName(filename, legacy);
          if (MediaUtil.isExternalFile((Context)File.this.form, str))
            File.this.form.assertPermission("android.permission.WRITE_EXTERNAL_STORAGE"); 
          java.io.File file = new java.io.File(str);
          if (!file.exists()) {
            try {
              file.createNewFile();
              try {
                FileOutputStream fileOutputStream = new FileOutputStream(file, append);
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
                outputStreamWriter.write(text);
                outputStreamWriter.flush();
                outputStreamWriter.close();
                fileOutputStream.close();
                File.this.form.runOnUiThread(new Runnable() {
                      public void run() {
                        File.this.AfterFileSaved(filename);
                      }
                    });
                return;
              } catch (IOException null) {}
            } catch (IOException iOException) {
              if (append) {
                File.this.form.dispatchErrorOccurredEvent(File.this, "AppendTo", 2103, new Object[] { str });
                return;
              } 
              File.this.form.dispatchErrorOccurredEvent(File.this, "SaveFile", 2103, new Object[] { str });
              return;
            } 
            if (append) {
              File.this.form.dispatchErrorOccurredEvent(File.this, "AppendTo", 2104, new Object[] { str });
              return;
            } 
            File.this.form.dispatchErrorOccurredEvent(File.this, "SaveFile", 2104, new Object[] { str });
            return;
          } 
          try {
            FileOutputStream fileOutputStream = new FileOutputStream((java.io.File)iOException, append);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            outputStreamWriter.write(text);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            fileOutputStream.close();
            File.this.form.runOnUiThread(new Runnable() {
                  public void run() {
                    File.this.AfterFileSaved(filename);
                  }
                });
            return;
          } catch (IOException iOException1) {}
        }
      };
    this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
          public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
            String str;
            if (param1Boolean) {
              AsynchUtil.runAsynchronously(operation);
              return;
            } 
            Form form = File.this.form;
            File file = File.this;
            if (append) {
              str = "AppendTo";
            } else {
              str = "SaveFile";
            } 
            form.dispatchPermissionDeniedEvent(file, str, param1String);
          }
        });
  }
  
  private String normalizeNewLines(String paramString) {
    return paramString.replaceAll("\r\n", "\n");
  }
  
  @SimpleEvent(description = "Event indicating that the contents of the file have been written.")
  public void AfterFileSaved(String paramString) {
    EventDispatcher.dispatchEvent(this, "AfterFileSaved", new Object[] { paramString });
  }
  
  @SimpleFunction(description = "Appends text to the end of a file storage, creating the file if it does not exist. See the help text under SaveFile for information about where files are written.")
  public void AppendToFile(String paramString1, String paramString2) {
    if (paramString2.startsWith("/"))
      FileUtil.checkExternalStorageWriteable(); 
    Write(paramString2, paramString1, true);
  }
  
  @SimpleFunction(description = "Deletes a file from storage. Prefix the filename with / to delete a specific file in the SD card, for instance /myFile.txt. will delete the file /sdcard/myFile.txt. If the file does not begin with a /, then the file located in the programs private storage will be deleted. Starting the file with // is an error because assets files cannot be deleted.")
  public void Delete(final String fileName) {
    final boolean legacy = this.legacy;
    this.form.askPermission("android.permission.WRITE_EXTERNAL_STORAGE", new PermissionResultHandler() {
          public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
            if (param1Boolean) {
              if (fileName.startsWith("//")) {
                File.this.form.dispatchErrorOccurredEvent(File.this, "DeleteFile", 2105, new Object[] { this.val$fileName });
                return;
              } 
              param1String = File.this.AbsoluteFileName(fileName, legacy);
              if (MediaUtil.isExternalFile((Context)File.this.form, fileName) && File.this.form.isDeniedPermission("android.permission.WRITE_EXTERNAL_STORAGE"))
                File.this.form.dispatchPermissionDeniedEvent(File.this, "Delete", new PermissionException("android.permission.WRITE_EXTERNAL_STORAGE")); 
              (new java.io.File(param1String)).delete();
              return;
            } 
            File.this.form.dispatchPermissionDeniedEvent(File.this, "Delete", param1String);
          }
        });
  }
  
  @SimpleEvent(description = "Event indicating that the contents from the file have been read.")
  public void GotText(String paramString) {
    EventDispatcher.dispatchEvent(this, "GotText", new Object[] { paramString });
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public void LegacyMode(boolean paramBoolean) {
    this.legacy = paramBoolean;
  }
  
  @SimpleProperty(description = "Allows app to access files from the root of the external storage directory (legacy mode).")
  public boolean LegacyMode() {
    return this.legacy;
  }
  
  @SimpleFunction(description = "Reads text from a file in storage. Prefix the filename with / to read from a specific file on the SD card. for instance /myFile.txt will read the file /sdcard/myFile.txt. To read assets packaged with an application (also works for the Companion) start the filename with // (two slashes). If a filename does not start with a slash, it will be read from the applications private storage (for packaged apps) and from /sdcard/AppInventor/data for the Companion.")
  public void ReadFrom(final String fileName) {
    final boolean legacy = this.legacy;
    this.form.askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
          public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
            if (param1Boolean)
              try {
                final InputStream asyncInputStream;
                if (fileName.startsWith("//")) {
                  inputStream = File.this.form.openAsset(fileName.substring(2));
                } else {
                  param1String = File.this.AbsoluteFileName(fileName, legacy);
                  Log.d("FileComponent", "filepath = " + param1String);
                  inputStream = FileUtil.openFile(File.this.form, param1String);
                } 
                AsynchUtil.runAsynchronously(new Runnable() {
                      public void run() {
                        File.this.AsyncRead(asyncInputStream, fileName);
                      }
                    });
                return;
              } catch (PermissionException permissionException) {
                File.this.form.dispatchPermissionDeniedEvent(File.this, "ReadFrom", permissionException);
                return;
              } catch (FileNotFoundException null) {
                Log.e("FileComponent", "FileNotFoundException", iOException);
                File.this.form.dispatchErrorOccurredEvent(File.this, "ReadFrom", 2101, new Object[] { this.val$fileName });
                return;
              } catch (IOException iOException) {
                Log.e("FileComponent", "IOException", iOException);
                File.this.form.dispatchErrorOccurredEvent(File.this, "ReadFrom", 2101, new Object[] { this.val$fileName });
                return;
              }  
            File.this.form.dispatchPermissionDeniedEvent(File.this, "ReadFrom", (String)iOException);
          }
        });
  }
  
  @SimpleFunction(description = "Saves text to a file. If the filename begins with a slash (/) the file is written to the sdcard. For example writing to /myFile.txt will write the file to /sdcard/myFile.txt. If the filename does not start with a slash, it will be written in the programs private data directory where it will not be accessible to other programs on the phone. There is a special exception for the AI Companion where these files are written to /sdcard/AppInventor/data to facilitate debugging. Note that this block will overwrite a file if it already exists.\n\nIf you want to add content to a file use the append block.")
  public void SaveFile(String paramString1, String paramString2) {
    if (paramString2.startsWith("/"))
      FileUtil.checkExternalStorageWriteable(); 
    Write(paramString2, paramString1, false);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/File.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */