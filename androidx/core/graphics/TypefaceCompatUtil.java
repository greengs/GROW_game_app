package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.os.CancellationSignal;
import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatUtil {
  private static final String CACHE_FILE_PREFIX = ".font";
  
  private static final String TAG = "TypefaceCompatUtil";
  
  public static void closeQuietly(Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
        return;
      } catch (IOException iOException) {
        return;
      }  
  }
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer copyToDirectBuffer(Context paramContext, Resources paramResources, int paramInt) {
    File file = getTempFile(paramContext);
    if (file == null)
      return null; 
    try {
      boolean bool = copyToFile(file, paramResources, paramInt);
      if (!bool)
        return null; 
      return mmap(file);
    } finally {
      file.delete();
    } 
  }
  
  public static boolean copyToFile(File paramFile, Resources paramResources, int paramInt) {
    InputStream inputStream = null;
    try {
      InputStream inputStream1 = paramResources.openRawResource(paramInt);
      inputStream = inputStream1;
      return copyToFile(paramFile, inputStream1);
    } finally {
      closeQuietly(inputStream);
    } 
  }
  
  public static boolean copyToFile(File paramFile, InputStream paramInputStream) {
    FileOutputStream fileOutputStream;
    byte[] arrayOfByte = null;
    File file2 = null;
    StrictMode.ThreadPolicy threadPolicy = StrictMode.allowThreadDiskWrites();
    try {
      InputStream inputStream;
      FileOutputStream fileOutputStream1 = new FileOutputStream(paramFile, false);
      try {
        arrayOfByte = new byte[1024];
        while (true)
          return true; 
      } catch (IOException null) {
        return false;
      } finally {
        paramInputStream = null;
        fileOutputStream = fileOutputStream1;
      } 
      closeQuietly(fileOutputStream);
      StrictMode.setThreadPolicy(threadPolicy);
      throw inputStream;
    } catch (IOException iOException) {
    
    } finally {
      closeQuietly(fileOutputStream);
      StrictMode.setThreadPolicy(threadPolicy);
    } 
    File file1 = paramFile;
    Log.e("TypefaceCompatUtil", "Error copying resource contents to temp file: " + iOException.getMessage());
    closeQuietly((Closeable)paramFile);
    StrictMode.setThreadPolicy(threadPolicy);
    return false;
  }
  
  @Nullable
  public static File getTempFile(Context paramContext) {
    String str = ".font" + Process.myPid() + "-" + Process.myTid() + "-";
    for (int i = 0; i < 100; i++) {
      File file = new File(paramContext.getCacheDir(), str + i);
      try {
        boolean bool = file.createNewFile();
        if (bool)
          return file; 
      } catch (IOException iOException) {}
    } 
    return null;
  }
  
  @Nullable
  @RequiresApi(19)
  public static ByteBuffer mmap(Context paramContext, CancellationSignal paramCancellationSignal, Uri paramUri) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   4: astore_0
    //   5: aload_0
    //   6: aload_2
    //   7: ldc 'r'
    //   9: aload_1
    //   10: invokevirtual openFileDescriptor : (Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   13: astore_2
    //   14: aload_2
    //   15: ifnonnull -> 53
    //   18: aconst_null
    //   19: astore_0
    //   20: aload_0
    //   21: astore_1
    //   22: aload_2
    //   23: ifnull -> 36
    //   26: iconst_0
    //   27: ifeq -> 47
    //   30: aload_2
    //   31: invokevirtual close : ()V
    //   34: aload_0
    //   35: astore_1
    //   36: aload_1
    //   37: areturn
    //   38: astore_0
    //   39: new java/lang/NullPointerException
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: athrow
    //   47: aload_2
    //   48: invokevirtual close : ()V
    //   51: aconst_null
    //   52: areturn
    //   53: new java/io/FileInputStream
    //   56: dup
    //   57: aload_2
    //   58: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   61: invokespecial <init> : (Ljava/io/FileDescriptor;)V
    //   64: astore #5
    //   66: aload #5
    //   68: invokevirtual getChannel : ()Ljava/nio/channels/FileChannel;
    //   71: astore_0
    //   72: aload_0
    //   73: invokevirtual size : ()J
    //   76: lstore_3
    //   77: aload_0
    //   78: getstatic java/nio/channels/FileChannel$MapMode.READ_ONLY : Ljava/nio/channels/FileChannel$MapMode;
    //   81: lconst_0
    //   82: lload_3
    //   83: invokevirtual map : (Ljava/nio/channels/FileChannel$MapMode;JJ)Ljava/nio/MappedByteBuffer;
    //   86: astore_0
    //   87: aload #5
    //   89: ifnull -> 101
    //   92: iconst_0
    //   93: ifeq -> 153
    //   96: aload #5
    //   98: invokevirtual close : ()V
    //   101: aload_0
    //   102: astore_1
    //   103: aload_2
    //   104: ifnull -> 36
    //   107: iconst_0
    //   108: ifeq -> 167
    //   111: aload_2
    //   112: invokevirtual close : ()V
    //   115: aload_0
    //   116: areturn
    //   117: astore_0
    //   118: new java/lang/NullPointerException
    //   121: dup
    //   122: invokespecial <init> : ()V
    //   125: athrow
    //   126: astore_0
    //   127: new java/lang/NullPointerException
    //   130: dup
    //   131: invokespecial <init> : ()V
    //   134: athrow
    //   135: astore_1
    //   136: aload_1
    //   137: athrow
    //   138: astore_0
    //   139: aload_2
    //   140: ifnull -> 151
    //   143: aload_1
    //   144: ifnull -> 221
    //   147: aload_2
    //   148: invokevirtual close : ()V
    //   151: aload_0
    //   152: athrow
    //   153: aload #5
    //   155: invokevirtual close : ()V
    //   158: goto -> 101
    //   161: astore_0
    //   162: aconst_null
    //   163: astore_1
    //   164: goto -> 139
    //   167: aload_2
    //   168: invokevirtual close : ()V
    //   171: aload_0
    //   172: areturn
    //   173: astore_1
    //   174: aload_1
    //   175: athrow
    //   176: astore_0
    //   177: aload #5
    //   179: ifnull -> 191
    //   182: aload_1
    //   183: ifnull -> 204
    //   186: aload #5
    //   188: invokevirtual close : ()V
    //   191: aload_0
    //   192: athrow
    //   193: astore #5
    //   195: aload_1
    //   196: aload #5
    //   198: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   201: goto -> 191
    //   204: aload #5
    //   206: invokevirtual close : ()V
    //   209: goto -> 191
    //   212: astore_2
    //   213: aload_1
    //   214: aload_2
    //   215: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   218: goto -> 151
    //   221: aload_2
    //   222: invokevirtual close : ()V
    //   225: goto -> 151
    //   228: astore_0
    //   229: aconst_null
    //   230: astore_1
    //   231: goto -> 177
    //   234: astore_0
    //   235: aconst_null
    //   236: areturn
    // Exception table:
    //   from	to	target	type
    //   5	14	234	java/io/IOException
    //   30	34	38	java/lang/Throwable
    //   30	34	234	java/io/IOException
    //   39	47	234	java/io/IOException
    //   47	51	234	java/io/IOException
    //   53	66	135	java/lang/Throwable
    //   53	66	161	finally
    //   66	87	173	java/lang/Throwable
    //   66	87	228	finally
    //   96	101	126	java/lang/Throwable
    //   96	101	161	finally
    //   111	115	117	java/lang/Throwable
    //   111	115	234	java/io/IOException
    //   118	126	234	java/io/IOException
    //   127	135	135	java/lang/Throwable
    //   127	135	161	finally
    //   136	138	138	finally
    //   147	151	212	java/lang/Throwable
    //   147	151	234	java/io/IOException
    //   151	153	234	java/io/IOException
    //   153	158	135	java/lang/Throwable
    //   153	158	161	finally
    //   167	171	234	java/io/IOException
    //   174	176	176	finally
    //   186	191	193	java/lang/Throwable
    //   186	191	161	finally
    //   191	193	135	java/lang/Throwable
    //   191	193	161	finally
    //   195	201	135	java/lang/Throwable
    //   195	201	161	finally
    //   204	209	135	java/lang/Throwable
    //   204	209	161	finally
    //   213	218	234	java/io/IOException
    //   221	225	234	java/io/IOException
  }
  
  @Nullable
  @RequiresApi(19)
  private static ByteBuffer mmap(File paramFile) {
    try {
      Object object;
      FileInputStream fileInputStream = new FileInputStream(paramFile);
      try {
        FileChannel fileChannel = fileInputStream.getChannel();
        long l = fileChannel.size();
        MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0L, l);
        if (fileInputStream != null) {
          if (false)
            try {
              return mappedByteBuffer;
            } catch (Throwable throwable1) {
              throw new NullPointerException();
            }  
        } else {
          return (ByteBuffer)throwable1;
        } 
        return (ByteBuffer)throwable1;
      } catch (Throwable null) {
        try {
          throw object;
        } finally {}
      } finally {
        paramFile = null;
      } 
      if (fileInputStream != null) {
        if (object != null) {
          try {
            fileInputStream.close();
          } catch (Throwable throwable) {}
          throw paramFile;
        } 
      } else {
        throw paramFile;
      } 
      throwable.close();
      throw paramFile;
    } catch (IOException iOException) {
      return null;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */