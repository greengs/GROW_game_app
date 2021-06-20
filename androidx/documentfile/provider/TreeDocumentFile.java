package androidx.documentfile.provider;

import android.content.Context;
import android.net.Uri;
import android.provider.DocumentsContract;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

@RequiresApi(21)
class TreeDocumentFile extends DocumentFile {
  private Context mContext;
  
  private Uri mUri;
  
  TreeDocumentFile(@Nullable DocumentFile paramDocumentFile, Context paramContext, Uri paramUri) {
    super(paramDocumentFile);
    this.mContext = paramContext;
    this.mUri = paramUri;
  }
  
  private static void closeQuietly(@Nullable AutoCloseable paramAutoCloseable) {
    if (paramAutoCloseable != null)
      try {
        paramAutoCloseable.close();
        return;
      } catch (RuntimeException runtimeException) {
        throw runtimeException;
      } catch (Exception exception) {
        return;
      }  
  }
  
  @Nullable
  private static Uri createFile(Context paramContext, Uri paramUri, String paramString1, String paramString2) {
    try {
      return DocumentsContract.createDocument(paramContext.getContentResolver(), paramUri, paramString1, paramString2);
    } catch (Exception exception) {
      return null;
    } 
  }
  
  public boolean canRead() {
    return DocumentsContractApi19.canRead(this.mContext, this.mUri);
  }
  
  public boolean canWrite() {
    return DocumentsContractApi19.canWrite(this.mContext, this.mUri);
  }
  
  @Nullable
  public DocumentFile createDirectory(String paramString) {
    Uri uri = createFile(this.mContext, this.mUri, "vnd.android.document/directory", paramString);
    return (uri != null) ? new TreeDocumentFile(this, this.mContext, uri) : null;
  }
  
  @Nullable
  public DocumentFile createFile(String paramString1, String paramString2) {
    Uri uri = createFile(this.mContext, this.mUri, paramString1, paramString2);
    return (uri != null) ? new TreeDocumentFile(this, this.mContext, uri) : null;
  }
  
  public boolean delete() {
    try {
      return DocumentsContract.deleteDocument(this.mContext.getContentResolver(), this.mUri);
    } catch (Exception exception) {
      return false;
    } 
  }
  
  public boolean exists() {
    return DocumentsContractApi19.exists(this.mContext, this.mUri);
  }
  
  @Nullable
  public String getName() {
    return DocumentsContractApi19.getName(this.mContext, this.mUri);
  }
  
  @Nullable
  public String getType() {
    return DocumentsContractApi19.getType(this.mContext, this.mUri);
  }
  
  public Uri getUri() {
    return this.mUri;
  }
  
  public boolean isDirectory() {
    return DocumentsContractApi19.isDirectory(this.mContext, this.mUri);
  }
  
  public boolean isFile() {
    return DocumentsContractApi19.isFile(this.mContext, this.mUri);
  }
  
  public boolean isVirtual() {
    return DocumentsContractApi19.isVirtual(this.mContext, this.mUri);
  }
  
  public long lastModified() {
    return DocumentsContractApi19.lastModified(this.mContext, this.mUri);
  }
  
  public long length() {
    return DocumentsContractApi19.length(this.mContext, this.mUri);
  }
  
  public DocumentFile[] listFiles() {
    // Byte code:
    //   0: aload_0
    //   1: getfield mContext : Landroid/content/Context;
    //   4: invokevirtual getContentResolver : ()Landroid/content/ContentResolver;
    //   7: astore #4
    //   9: aload_0
    //   10: getfield mUri : Landroid/net/Uri;
    //   13: aload_0
    //   14: getfield mUri : Landroid/net/Uri;
    //   17: invokestatic getDocumentId : (Landroid/net/Uri;)Ljava/lang/String;
    //   20: invokestatic buildChildDocumentsUriUsingTree : (Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   23: astore #6
    //   25: new java/util/ArrayList
    //   28: dup
    //   29: invokespecial <init> : ()V
    //   32: astore #5
    //   34: aconst_null
    //   35: astore_3
    //   36: aconst_null
    //   37: astore_2
    //   38: aload #4
    //   40: aload #6
    //   42: iconst_1
    //   43: anewarray java/lang/String
    //   46: dup
    //   47: iconst_0
    //   48: ldc 'document_id'
    //   50: aastore
    //   51: aconst_null
    //   52: aconst_null
    //   53: aconst_null
    //   54: invokevirtual query : (Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   57: astore #4
    //   59: aload #4
    //   61: astore_2
    //   62: aload #4
    //   64: astore_3
    //   65: aload #4
    //   67: invokeinterface moveToNext : ()Z
    //   72: ifeq -> 205
    //   75: aload #4
    //   77: astore_2
    //   78: aload #4
    //   80: astore_3
    //   81: aload #4
    //   83: iconst_0
    //   84: invokeinterface getString : (I)Ljava/lang/String;
    //   89: astore #6
    //   91: aload #4
    //   93: astore_2
    //   94: aload #4
    //   96: astore_3
    //   97: aload #5
    //   99: aload_0
    //   100: getfield mUri : Landroid/net/Uri;
    //   103: aload #6
    //   105: invokestatic buildDocumentUriUsingTree : (Landroid/net/Uri;Ljava/lang/String;)Landroid/net/Uri;
    //   108: invokevirtual add : (Ljava/lang/Object;)Z
    //   111: pop
    //   112: goto -> 59
    //   115: astore #4
    //   117: aload_2
    //   118: astore_3
    //   119: ldc 'DocumentFile'
    //   121: new java/lang/StringBuilder
    //   124: dup
    //   125: invokespecial <init> : ()V
    //   128: ldc 'Failed query: '
    //   130: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   133: aload #4
    //   135: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   138: invokevirtual toString : ()Ljava/lang/String;
    //   141: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   144: pop
    //   145: aload_2
    //   146: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   149: aload #5
    //   151: aload #5
    //   153: invokevirtual size : ()I
    //   156: anewarray android/net/Uri
    //   159: invokevirtual toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
    //   162: checkcast [Landroid/net/Uri;
    //   165: astore_2
    //   166: aload_2
    //   167: arraylength
    //   168: anewarray androidx/documentfile/provider/DocumentFile
    //   171: astore_3
    //   172: iconst_0
    //   173: istore_1
    //   174: iload_1
    //   175: aload_2
    //   176: arraylength
    //   177: if_icmpge -> 220
    //   180: aload_3
    //   181: iload_1
    //   182: new androidx/documentfile/provider/TreeDocumentFile
    //   185: dup
    //   186: aload_0
    //   187: aload_0
    //   188: getfield mContext : Landroid/content/Context;
    //   191: aload_2
    //   192: iload_1
    //   193: aaload
    //   194: invokespecial <init> : (Landroidx/documentfile/provider/DocumentFile;Landroid/content/Context;Landroid/net/Uri;)V
    //   197: aastore
    //   198: iload_1
    //   199: iconst_1
    //   200: iadd
    //   201: istore_1
    //   202: goto -> 174
    //   205: aload #4
    //   207: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   210: goto -> 149
    //   213: astore_2
    //   214: aload_3
    //   215: invokestatic closeQuietly : (Ljava/lang/AutoCloseable;)V
    //   218: aload_2
    //   219: athrow
    //   220: aload_3
    //   221: areturn
    // Exception table:
    //   from	to	target	type
    //   38	59	115	java/lang/Exception
    //   38	59	213	finally
    //   65	75	115	java/lang/Exception
    //   65	75	213	finally
    //   81	91	115	java/lang/Exception
    //   81	91	213	finally
    //   97	112	115	java/lang/Exception
    //   97	112	213	finally
    //   119	145	213	finally
  }
  
  public boolean renameTo(String paramString) {
    boolean bool = false;
    try {
      Uri uri = DocumentsContract.renameDocument(this.mContext.getContentResolver(), this.mUri, paramString);
      if (uri != null) {
        this.mUri = uri;
        bool = true;
      } 
      return bool;
    } catch (Exception exception) {
      return false;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/documentfile/provider/TreeDocumentFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */