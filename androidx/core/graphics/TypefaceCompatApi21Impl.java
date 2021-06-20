package androidx.core.graphics;

import android.content.Context;
import android.graphics.Typeface;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.system.ErrnoException;
import android.system.Os;
import android.system.OsConstants;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.provider.FontsContractCompat;
import java.io.File;

@RequiresApi(21)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi21Impl extends TypefaceCompatBaseImpl {
  private static final String TAG = "TypefaceCompatApi21Impl";
  
  private File getFile(ParcelFileDescriptor paramParcelFileDescriptor) {
    try {
      String str = Os.readlink("/proc/self/fd/" + paramParcelFileDescriptor.getFd());
      return OsConstants.S_ISREG((Os.stat(str)).st_mode) ? new File(str) : null;
    } catch (ErrnoException errnoException) {
      return null;
    } 
  }
  
  public Typeface createFromFontInfo(Context paramContext, CancellationSignal paramCancellationSignal, @NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    // Byte code:
    //   0: aload_3
    //   1: arraylength
    //   2: iconst_1
    //   3: if_icmpge -> 10
    //   6: aconst_null
    //   7: astore_1
    //   8: aload_1
    //   9: areturn
    //   10: aload_0
    //   11: aload_3
    //   12: iload #4
    //   14: invokevirtual findBestInfo : ([Landroidx/core/provider/FontsContractCompat$FontInfo;I)Landroidx/core/provider/FontsContractCompat$FontInfo;
    //   17: astore_3
    //   18: aload_1
    //   19: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   22: astore #5
    //   24: aload #5
    //   26: aload_3
    //   27: invokevirtual getUri : ()Landroid/net/Uri;
    //   30: ldc 'r'
    //   32: aload_2
    //   33: invokevirtual openFileDescriptor : (Landroid/net/Uri;Ljava/lang/String;Landroid/os/CancellationSignal;)Landroid/os/ParcelFileDescriptor;
    //   36: astore_3
    //   37: aload_0
    //   38: aload_3
    //   39: invokespecial getFile : (Landroid/os/ParcelFileDescriptor;)Ljava/io/File;
    //   42: astore_2
    //   43: aload_2
    //   44: ifnull -> 54
    //   47: aload_2
    //   48: invokevirtual canRead : ()Z
    //   51: ifne -> 209
    //   54: new java/io/FileInputStream
    //   57: dup
    //   58: aload_3
    //   59: invokevirtual getFileDescriptor : ()Ljava/io/FileDescriptor;
    //   62: invokespecial <init> : (Ljava/io/FileDescriptor;)V
    //   65: astore #5
    //   67: aconst_null
    //   68: astore_2
    //   69: aload_0
    //   70: aload_1
    //   71: aload #5
    //   73: invokespecial createFromInputStream : (Landroid/content/Context;Ljava/io/InputStream;)Landroid/graphics/Typeface;
    //   76: astore_1
    //   77: aload_1
    //   78: astore_2
    //   79: aload #5
    //   81: ifnull -> 93
    //   84: iconst_0
    //   85: ifeq -> 148
    //   88: aload #5
    //   90: invokevirtual close : ()V
    //   93: aload_2
    //   94: astore_1
    //   95: aload_3
    //   96: ifnull -> 8
    //   99: iconst_0
    //   100: ifeq -> 162
    //   103: aload_3
    //   104: invokevirtual close : ()V
    //   107: aload_2
    //   108: areturn
    //   109: astore_1
    //   110: new java/lang/NullPointerException
    //   113: dup
    //   114: invokespecial <init> : ()V
    //   117: athrow
    //   118: astore_1
    //   119: aconst_null
    //   120: areturn
    //   121: astore_1
    //   122: new java/lang/NullPointerException
    //   125: dup
    //   126: invokespecial <init> : ()V
    //   129: athrow
    //   130: astore_1
    //   131: aload_1
    //   132: athrow
    //   133: astore_2
    //   134: aload_3
    //   135: ifnull -> 146
    //   138: aload_1
    //   139: ifnull -> 254
    //   142: aload_3
    //   143: invokevirtual close : ()V
    //   146: aload_2
    //   147: athrow
    //   148: aload #5
    //   150: invokevirtual close : ()V
    //   153: goto -> 93
    //   156: astore_2
    //   157: aconst_null
    //   158: astore_1
    //   159: goto -> 134
    //   162: aload_3
    //   163: invokevirtual close : ()V
    //   166: aload_2
    //   167: areturn
    //   168: astore_1
    //   169: aload_1
    //   170: astore_2
    //   171: aload_1
    //   172: athrow
    //   173: astore_1
    //   174: aload #5
    //   176: ifnull -> 188
    //   179: aload_2
    //   180: ifnull -> 201
    //   183: aload #5
    //   185: invokevirtual close : ()V
    //   188: aload_1
    //   189: athrow
    //   190: astore #5
    //   192: aload_2
    //   193: aload #5
    //   195: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   198: goto -> 188
    //   201: aload #5
    //   203: invokevirtual close : ()V
    //   206: goto -> 188
    //   209: aload_2
    //   210: invokestatic createFromFile : (Ljava/io/File;)Landroid/graphics/Typeface;
    //   213: astore_2
    //   214: aload_2
    //   215: astore_1
    //   216: aload_3
    //   217: ifnull -> 8
    //   220: iconst_0
    //   221: ifeq -> 239
    //   224: aload_3
    //   225: invokevirtual close : ()V
    //   228: aload_2
    //   229: areturn
    //   230: astore_1
    //   231: new java/lang/NullPointerException
    //   234: dup
    //   235: invokespecial <init> : ()V
    //   238: athrow
    //   239: aload_3
    //   240: invokevirtual close : ()V
    //   243: aload_2
    //   244: areturn
    //   245: astore_3
    //   246: aload_1
    //   247: aload_3
    //   248: invokevirtual addSuppressed : (Ljava/lang/Throwable;)V
    //   251: goto -> 146
    //   254: aload_3
    //   255: invokevirtual close : ()V
    //   258: goto -> 146
    // Exception table:
    //   from	to	target	type
    //   24	37	118	java/io/IOException
    //   37	43	130	java/lang/Throwable
    //   37	43	156	finally
    //   47	54	130	java/lang/Throwable
    //   47	54	156	finally
    //   54	67	130	java/lang/Throwable
    //   54	67	156	finally
    //   69	77	168	java/lang/Throwable
    //   69	77	173	finally
    //   88	93	121	java/lang/Throwable
    //   88	93	156	finally
    //   103	107	109	java/lang/Throwable
    //   103	107	118	java/io/IOException
    //   110	118	118	java/io/IOException
    //   122	130	130	java/lang/Throwable
    //   122	130	156	finally
    //   131	133	133	finally
    //   142	146	245	java/lang/Throwable
    //   142	146	118	java/io/IOException
    //   146	148	118	java/io/IOException
    //   148	153	130	java/lang/Throwable
    //   148	153	156	finally
    //   162	166	118	java/io/IOException
    //   171	173	173	finally
    //   183	188	190	java/lang/Throwable
    //   183	188	156	finally
    //   188	190	130	java/lang/Throwable
    //   188	190	156	finally
    //   192	198	130	java/lang/Throwable
    //   192	198	156	finally
    //   201	206	130	java/lang/Throwable
    //   201	206	156	finally
    //   209	214	130	java/lang/Throwable
    //   209	214	156	finally
    //   224	228	230	java/lang/Throwable
    //   224	228	118	java/io/IOException
    //   231	239	118	java/io/IOException
    //   239	243	118	java/io/IOException
    //   246	251	118	java/io/IOException
    //   254	258	118	java/io/IOException
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompatApi21Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */