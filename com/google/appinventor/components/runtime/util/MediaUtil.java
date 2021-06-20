package com.google.appinventor.components.runtime.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Build;
import android.provider.Contacts;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.VideoView;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import com.google.appinventor.components.runtime.errors.PermissionException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MediaUtil {
  private static final String LOG_TAG = "MediaUtil";
  
  private static ConcurrentHashMap<String, String> pathCache;
  
  private static final Map<String, File> tempFileMap = new HashMap<String, File>();
  
  static {
    pathCache = new ConcurrentHashMap<String, String>(2);
  }
  
  private static File cacheMediaTempFile(Form paramForm, String paramString, MediaSource paramMediaSource) throws IOException {
    File file2 = tempFileMap.get(paramString);
    if (file2 != null) {
      File file = file2;
      if (!file2.exists()) {
        Log.i("MediaUtil", "Copying media " + paramString + " to temp file...");
        file = copyMediaToTempFile(paramForm, paramString, paramMediaSource);
        Log.i("MediaUtil", "Finished copying media " + paramString + " to temp file " + file.getAbsolutePath());
        tempFileMap.put(paramString, file);
        return file;
      } 
      return file;
    } 
    Log.i("MediaUtil", "Copying media " + paramString + " to temp file...");
    File file1 = copyMediaToTempFile(paramForm, paramString, paramMediaSource);
    Log.i("MediaUtil", "Finished copying media " + paramString + " to temp file " + file1.getAbsolutePath());
    tempFileMap.put(paramString, file1);
    return file1;
  }
  
  public static File copyMediaToTempFile(Form paramForm, String paramString) throws IOException {
    return copyMediaToTempFile(paramForm, paramString, determineMediaSource(paramForm, paramString));
  }
  
  private static File copyMediaToTempFile(Form paramForm, String paramString, MediaSource paramMediaSource) throws IOException {
    InputStream inputStream = openMedia(paramForm, paramString, paramMediaSource);
    paramForm = null;
    try {
      File file = File.createTempFile("AI_Media_", null);
      null = file;
      file.deleteOnExit();
      null = file;
      FileUtil.writeStreamToFile(inputStream, file.getAbsolutePath());
      return file;
    } catch (IOException iOException) {
    
    } finally {
      inputStream.close();
    } 
    Log.e("MediaUtil", "Could not copy media " + paramString + " to temp file.");
    throw iOException;
  }
  
  private static Bitmap decodeStream(InputStream paramInputStream, Rect paramRect, BitmapFactory.Options paramOptions) {
    return BitmapFactory.decodeStream(new FlushedInputStream(paramInputStream), paramRect, paramOptions);
  }
  
  @SuppressLint({"SdCardPath"})
  private static MediaSource determineMediaSource(Form paramForm, String paramString) {
    if (paramString.startsWith(QUtil.getExternalStoragePath((Context)paramForm)) || paramString.startsWith("/sdcard/"))
      return MediaSource.SDCARD; 
    if (paramString.startsWith("content://contacts/"))
      return MediaSource.CONTACT_URI; 
    if (paramString.startsWith("content://"))
      return MediaSource.CONTENT_URI; 
    try {
      new URL(paramString);
      return paramString.startsWith("file:") ? MediaSource.FILE_URL : MediaSource.URL;
    } catch (MalformedURLException malformedURLException) {
      return (paramForm instanceof ReplForm) ? (((ReplForm)paramForm).isAssetsLoaded() ? MediaSource.REPL_ASSET : MediaSource.ASSET) : MediaSource.ASSET;
    } 
  }
  
  static String fileUrlToFilePath(String paramString) throws IOException {
    try {
      return (new File((new URL(paramString)).toURI())).getAbsolutePath();
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new IOException("Unable to determine file path of file url " + paramString);
    } catch (Exception exception) {
      throw new IOException("Unable to determine file path of file url " + paramString);
    } 
  }
  
  private static String findCaseinsensitivePath(Form paramForm, String paramString) throws IOException {
    if (!pathCache.containsKey(paramString)) {
      String str = findCaseinsensitivePathWithoutCache(paramForm, paramString);
      if (str == null)
        return null; 
      pathCache.put(paramString, str);
    } 
    return pathCache.get(paramString);
  }
  
  private static String findCaseinsensitivePathWithoutCache(Form paramForm, String paramString) throws IOException {
    String[] arrayOfString = paramForm.getAssets().list("");
    int j = Array.getLength(arrayOfString);
    for (int i = 0; i < j; i++) {
      String str = arrayOfString[i];
      if (str.equalsIgnoreCase(paramString))
        return str; 
    } 
    return null;
  }
  
  private static AssetFileDescriptor getAssetsIgnoreCaseAfd(Form paramForm, String paramString) throws IOException {
    try {
      return paramForm.getAssets().openFd(paramString);
    } catch (IOException iOException) {
      paramString = findCaseinsensitivePath(paramForm, paramString);
      if (paramString == null)
        throw iOException; 
      return paramForm.getAssets().openFd(paramString);
    } 
  }
  
  private static InputStream getAssetsIgnoreCaseInputStream(Form paramForm, String paramString) throws IOException {
    try {
      return paramForm.getAssets().open(paramString);
    } catch (IOException iOException) {
      paramString = findCaseinsensitivePath(paramForm, paramString);
      if (paramString == null)
        throw iOException; 
      return paramForm.getAssets().open(paramString);
    } 
  }
  
  public static BitmapDrawable getBitmapDrawable(Form paramForm, String paramString) throws IOException {
    BitmapDrawable bitmapDrawable3 = null;
    BitmapDrawable bitmapDrawable2 = bitmapDrawable3;
    if (paramString != null) {
      if (paramString.length() == 0)
        return bitmapDrawable3; 
    } else {
      return bitmapDrawable2;
    } 
    final Synchronizer<BitmapDrawable> syncer = new Synchronizer();
    getBitmapDrawableAsync(paramForm, paramString, new AsyncCallbackPair<BitmapDrawable>() {
          public void onFailure(String param1String) {
            syncer.error(param1String);
          }
          
          public void onSuccess(BitmapDrawable param1BitmapDrawable) {
            syncer.wakeup(param1BitmapDrawable);
          }
        });
    synchronizer.waitfor();
    BitmapDrawable bitmapDrawable1 = synchronizer.getResult();
    bitmapDrawable2 = bitmapDrawable1;
    if (bitmapDrawable1 == null) {
      String str = synchronizer.getError();
      if (str.startsWith("PERMISSION_DENIED:"))
        throw new PermissionException(str.split(":")[1]); 
      throw new IOException(str);
    } 
    return bitmapDrawable2;
  }
  
  public static void getBitmapDrawableAsync(final Form form, final String mediaPath, final AsyncCallbackPair<BitmapDrawable> continuation) {
    if (mediaPath == null || mediaPath.length() == 0) {
      continuation.onSuccess(null);
      return;
    } 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            // Byte code:
            //   0: ldc 'MediaUtil'
            //   2: new java/lang/StringBuilder
            //   5: dup
            //   6: invokespecial <init> : ()V
            //   9: ldc 'mediaPath = '
            //   11: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   14: aload_0
            //   15: getfield val$mediaPath : Ljava/lang/String;
            //   18: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   21: invokevirtual toString : ()Ljava/lang/String;
            //   24: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
            //   27: pop
            //   28: aconst_null
            //   29: astore #5
            //   31: aconst_null
            //   32: astore_3
            //   33: aconst_null
            //   34: astore #4
            //   36: new java/io/ByteArrayOutputStream
            //   39: dup
            //   40: invokespecial <init> : ()V
            //   43: astore #7
            //   45: sipush #4096
            //   48: newarray byte
            //   50: astore #8
            //   52: aload_0
            //   53: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   56: aload_0
            //   57: getfield val$mediaPath : Ljava/lang/String;
            //   60: aload_0
            //   61: getfield val$mediaSource : Lcom/google/appinventor/components/runtime/util/MediaUtil$MediaSource;
            //   64: invokestatic access$100 : (Lcom/google/appinventor/components/runtime/Form;Ljava/lang/String;Lcom/google/appinventor/components/runtime/util/MediaUtil$MediaSource;)Ljava/io/InputStream;
            //   67: astore #6
            //   69: aload #6
            //   71: astore #4
            //   73: aload #6
            //   75: astore #5
            //   77: aload #6
            //   79: astore_3
            //   80: aload #6
            //   82: aload #8
            //   84: invokevirtual read : ([B)I
            //   87: istore_1
            //   88: iload_1
            //   89: ifle -> 168
            //   92: aload #6
            //   94: astore #4
            //   96: aload #6
            //   98: astore #5
            //   100: aload #6
            //   102: astore_3
            //   103: aload #7
            //   105: aload #8
            //   107: iconst_0
            //   108: iload_1
            //   109: invokevirtual write : ([BII)V
            //   112: goto -> 69
            //   115: astore #5
            //   117: aload #4
            //   119: astore_3
            //   120: aload_0
            //   121: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   124: new java/lang/StringBuilder
            //   127: dup
            //   128: invokespecial <init> : ()V
            //   131: ldc 'PERMISSION_DENIED:'
            //   133: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   136: aload #5
            //   138: invokevirtual getPermissionNeeded : ()Ljava/lang/String;
            //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   144: invokevirtual toString : ()Ljava/lang/String;
            //   147: invokeinterface onFailure : (Ljava/lang/String;)V
            //   152: aload #4
            //   154: ifnull -> 162
            //   157: aload #4
            //   159: invokevirtual close : ()V
            //   162: aload #7
            //   164: invokevirtual close : ()V
            //   167: return
            //   168: aload #6
            //   170: astore #4
            //   172: aload #6
            //   174: astore #5
            //   176: aload #6
            //   178: astore_3
            //   179: aload #7
            //   181: invokevirtual toByteArray : ()[B
            //   184: astore #8
            //   186: aload #6
            //   188: ifnull -> 196
            //   191: aload #6
            //   193: invokevirtual close : ()V
            //   196: aload #7
            //   198: invokevirtual close : ()V
            //   201: new java/io/ByteArrayInputStream
            //   204: dup
            //   205: aload #8
            //   207: invokespecial <init> : ([B)V
            //   210: astore_3
            //   211: aload #8
            //   213: arraylength
            //   214: istore_1
            //   215: aload_3
            //   216: iload_1
            //   217: invokevirtual mark : (I)V
            //   220: aload_0
            //   221: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   224: aload_3
            //   225: aload_0
            //   226: getfield val$mediaPath : Ljava/lang/String;
            //   229: invokestatic access$200 : (Lcom/google/appinventor/components/runtime/Form;Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/BitmapFactory$Options;
            //   232: astore #4
            //   234: aload_3
            //   235: invokevirtual reset : ()V
            //   238: new android/graphics/drawable/BitmapDrawable
            //   241: dup
            //   242: aload_0
            //   243: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   246: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   249: aload_3
            //   250: aconst_null
            //   251: aload #4
            //   253: invokestatic access$300 : (Ljava/io/InputStream;Landroid/graphics/Rect;Landroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
            //   256: invokespecial <init> : (Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
            //   259: astore #5
            //   261: aload #5
            //   263: aload_0
            //   264: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   267: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   270: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
            //   273: invokevirtual setTargetDensity : (Landroid/util/DisplayMetrics;)V
            //   276: aload #4
            //   278: getfield inSampleSize : I
            //   281: iconst_1
            //   282: if_icmpne -> 297
            //   285: aload_0
            //   286: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   289: invokevirtual deviceDensity : ()F
            //   292: fconst_1
            //   293: fcmpl
            //   294: ifne -> 534
            //   297: aload_0
            //   298: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   301: aload #5
            //   303: invokeinterface onSuccess : (Ljava/lang/Object;)V
            //   308: aload_3
            //   309: ifnull -> 167
            //   312: aload_3
            //   313: invokevirtual close : ()V
            //   316: return
            //   317: astore_3
            //   318: ldc 'MediaUtil'
            //   320: ldc 'Unexpected error on close'
            //   322: aload_3
            //   323: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   326: pop
            //   327: return
            //   328: astore_3
            //   329: ldc 'MediaUtil'
            //   331: ldc 'Unexpected error on close'
            //   333: aload_3
            //   334: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   337: pop
            //   338: goto -> 196
            //   341: astore_3
            //   342: ldc 'MediaUtil'
            //   344: ldc 'Unexpected error on close'
            //   346: aload_3
            //   347: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   350: pop
            //   351: goto -> 162
            //   354: astore #4
            //   356: aload #5
            //   358: astore_3
            //   359: aload_0
            //   360: getfield val$mediaSource : Lcom/google/appinventor/components/runtime/util/MediaUtil$MediaSource;
            //   363: getstatic com/google/appinventor/components/runtime/util/MediaUtil$MediaSource.CONTACT_URI : Lcom/google/appinventor/components/runtime/util/MediaUtil$MediaSource;
            //   366: if_acmpne -> 444
            //   369: aload #5
            //   371: astore_3
            //   372: new android/graphics/drawable/BitmapDrawable
            //   375: dup
            //   376: aload_0
            //   377: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   380: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   383: aload_0
            //   384: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   387: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   390: ldc 17301606
            //   392: aconst_null
            //   393: invokestatic decodeResource : (Landroid/content/res/Resources;ILandroid/graphics/BitmapFactory$Options;)Landroid/graphics/Bitmap;
            //   396: invokespecial <init> : (Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
            //   399: astore #4
            //   401: aload #5
            //   403: astore_3
            //   404: aload_0
            //   405: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   408: aload #4
            //   410: invokeinterface onSuccess : (Ljava/lang/Object;)V
            //   415: aload #5
            //   417: ifnull -> 425
            //   420: aload #5
            //   422: invokevirtual close : ()V
            //   425: aload #7
            //   427: invokevirtual close : ()V
            //   430: return
            //   431: astore_3
            //   432: ldc 'MediaUtil'
            //   434: ldc 'Unexpected error on close'
            //   436: aload_3
            //   437: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   440: pop
            //   441: goto -> 425
            //   444: aload #5
            //   446: astore_3
            //   447: ldc 'MediaUtil'
            //   449: ldc 'IOException reading file.'
            //   451: aload #4
            //   453: invokestatic d : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   456: pop
            //   457: aload #5
            //   459: astore_3
            //   460: aload_0
            //   461: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   464: aload #4
            //   466: invokevirtual getMessage : ()Ljava/lang/String;
            //   469: invokeinterface onFailure : (Ljava/lang/String;)V
            //   474: aload #5
            //   476: ifnull -> 484
            //   479: aload #5
            //   481: invokevirtual close : ()V
            //   484: aload #7
            //   486: invokevirtual close : ()V
            //   489: return
            //   490: astore_3
            //   491: ldc 'MediaUtil'
            //   493: ldc 'Unexpected error on close'
            //   495: aload_3
            //   496: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   499: pop
            //   500: goto -> 484
            //   503: astore #4
            //   505: aload_3
            //   506: ifnull -> 513
            //   509: aload_3
            //   510: invokevirtual close : ()V
            //   513: aload #7
            //   515: invokevirtual close : ()V
            //   518: aload #4
            //   520: athrow
            //   521: astore_3
            //   522: ldc 'MediaUtil'
            //   524: ldc 'Unexpected error on close'
            //   526: aload_3
            //   527: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   530: pop
            //   531: goto -> 513
            //   534: aload_0
            //   535: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   538: invokevirtual deviceDensity : ()F
            //   541: aload #5
            //   543: invokevirtual getIntrinsicWidth : ()I
            //   546: i2f
            //   547: fmul
            //   548: f2i
            //   549: istore_1
            //   550: aload_0
            //   551: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   554: invokevirtual deviceDensity : ()F
            //   557: aload #5
            //   559: invokevirtual getIntrinsicHeight : ()I
            //   562: i2f
            //   563: fmul
            //   564: f2i
            //   565: istore_2
            //   566: ldc 'MediaUtil'
            //   568: new java/lang/StringBuilder
            //   571: dup
            //   572: invokespecial <init> : ()V
            //   575: ldc 'form.deviceDensity() = '
            //   577: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   580: aload_0
            //   581: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   584: invokevirtual deviceDensity : ()F
            //   587: invokevirtual append : (F)Ljava/lang/StringBuilder;
            //   590: invokevirtual toString : ()Ljava/lang/String;
            //   593: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
            //   596: pop
            //   597: ldc 'MediaUtil'
            //   599: new java/lang/StringBuilder
            //   602: dup
            //   603: invokespecial <init> : ()V
            //   606: ldc 'originalBitmapDrawable.getIntrinsicWidth() = '
            //   608: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   611: aload #5
            //   613: invokevirtual getIntrinsicWidth : ()I
            //   616: invokevirtual append : (I)Ljava/lang/StringBuilder;
            //   619: invokevirtual toString : ()Ljava/lang/String;
            //   622: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
            //   625: pop
            //   626: ldc 'MediaUtil'
            //   628: new java/lang/StringBuilder
            //   631: dup
            //   632: invokespecial <init> : ()V
            //   635: ldc 'originalBitmapDrawable.getIntrinsicHeight() = '
            //   637: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   640: aload #5
            //   642: invokevirtual getIntrinsicHeight : ()I
            //   645: invokevirtual append : (I)Ljava/lang/StringBuilder;
            //   648: invokevirtual toString : ()Ljava/lang/String;
            //   651: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
            //   654: pop
            //   655: aload #5
            //   657: invokevirtual getBitmap : ()Landroid/graphics/Bitmap;
            //   660: iload_1
            //   661: iload_2
            //   662: iconst_0
            //   663: invokestatic createScaledBitmap : (Landroid/graphics/Bitmap;IIZ)Landroid/graphics/Bitmap;
            //   666: astore #4
            //   668: new android/graphics/drawable/BitmapDrawable
            //   671: dup
            //   672: aload_0
            //   673: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   676: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   679: aload #4
            //   681: invokespecial <init> : (Landroid/content/res/Resources;Landroid/graphics/Bitmap;)V
            //   684: astore #4
            //   686: aload #4
            //   688: aload_0
            //   689: getfield val$form : Lcom/google/appinventor/components/runtime/Form;
            //   692: invokevirtual getResources : ()Landroid/content/res/Resources;
            //   695: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
            //   698: invokevirtual setTargetDensity : (Landroid/util/DisplayMetrics;)V
            //   701: invokestatic gc : ()V
            //   704: aload_0
            //   705: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   708: aload #4
            //   710: invokeinterface onSuccess : (Ljava/lang/Object;)V
            //   715: aload_3
            //   716: ifnull -> 167
            //   719: aload_3
            //   720: invokevirtual close : ()V
            //   723: return
            //   724: astore_3
            //   725: ldc 'MediaUtil'
            //   727: ldc 'Unexpected error on close'
            //   729: aload_3
            //   730: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   733: pop
            //   734: return
            //   735: astore #4
            //   737: ldc 'MediaUtil'
            //   739: ldc 'Exception while loading media.'
            //   741: aload #4
            //   743: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   746: pop
            //   747: aload_0
            //   748: getfield val$continuation : Lcom/google/appinventor/components/runtime/util/AsyncCallbackPair;
            //   751: aload #4
            //   753: invokevirtual getMessage : ()Ljava/lang/String;
            //   756: invokeinterface onFailure : (Ljava/lang/String;)V
            //   761: aload_3
            //   762: ifnull -> 167
            //   765: aload_3
            //   766: invokevirtual close : ()V
            //   769: return
            //   770: astore_3
            //   771: ldc 'MediaUtil'
            //   773: ldc 'Unexpected error on close'
            //   775: aload_3
            //   776: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   779: pop
            //   780: return
            //   781: astore #4
            //   783: aload_3
            //   784: ifnull -> 791
            //   787: aload_3
            //   788: invokevirtual close : ()V
            //   791: aload #4
            //   793: athrow
            //   794: astore_3
            //   795: ldc 'MediaUtil'
            //   797: ldc 'Unexpected error on close'
            //   799: aload_3
            //   800: invokestatic w : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   803: pop
            //   804: goto -> 791
            //   807: astore_3
            //   808: goto -> 201
            //   811: astore_3
            //   812: goto -> 167
            //   815: astore_3
            //   816: goto -> 430
            //   819: astore_3
            //   820: goto -> 489
            //   823: astore_3
            //   824: goto -> 518
            // Exception table:
            //   from	to	target	type
            //   52	69	115	com/google/appinventor/components/runtime/errors/PermissionException
            //   52	69	354	java/io/IOException
            //   52	69	503	finally
            //   80	88	115	com/google/appinventor/components/runtime/errors/PermissionException
            //   80	88	354	java/io/IOException
            //   80	88	503	finally
            //   103	112	115	com/google/appinventor/components/runtime/errors/PermissionException
            //   103	112	354	java/io/IOException
            //   103	112	503	finally
            //   120	152	503	finally
            //   157	162	341	java/io/IOException
            //   162	167	811	java/io/IOException
            //   179	186	115	com/google/appinventor/components/runtime/errors/PermissionException
            //   179	186	354	java/io/IOException
            //   179	186	503	finally
            //   191	196	328	java/io/IOException
            //   196	201	807	java/io/IOException
            //   215	297	735	java/lang/Exception
            //   215	297	781	finally
            //   297	308	735	java/lang/Exception
            //   297	308	781	finally
            //   312	316	317	java/io/IOException
            //   359	369	503	finally
            //   372	401	503	finally
            //   404	415	503	finally
            //   420	425	431	java/io/IOException
            //   425	430	815	java/io/IOException
            //   447	457	503	finally
            //   460	474	503	finally
            //   479	484	490	java/io/IOException
            //   484	489	819	java/io/IOException
            //   509	513	521	java/io/IOException
            //   513	518	823	java/io/IOException
            //   534	715	735	java/lang/Exception
            //   534	715	781	finally
            //   719	723	724	java/io/IOException
            //   737	761	781	finally
            //   765	769	770	java/io/IOException
            //   787	791	794	java/io/IOException
          }
        });
  }
  
  private static BitmapFactory.Options getBitmapOptions(Form paramForm, InputStream paramInputStream, String paramString) {
    int i;
    int j;
    BitmapFactory.Options options2 = new BitmapFactory.Options();
    options2.inJustDecodeBounds = true;
    decodeStream(paramInputStream, null, options2);
    int m = options2.outWidth;
    int n = options2.outHeight;
    Display display = ((WindowManager)paramForm.getSystemService("window")).getDefaultDisplay();
    if (Form.getCompatibilityMode()) {
      j = 720;
      i = 840;
    } else {
      j = (int)(display.getWidth() / paramForm.deviceDensity());
      i = (int)(display.getHeight() / paramForm.deviceDensity());
    } 
    int k;
    for (k = 1; m / k > j && n / k > i; k *= 2);
    BitmapFactory.Options options1 = new BitmapFactory.Options();
    Log.d("MediaUtil", "getBitmapOptions: sampleSize = " + k + " mediaPath = " + paramString + " maxWidth = " + j + " maxHeight = " + i + " display width = " + display.getWidth() + " display height = " + display.getHeight());
    options1.inSampleSize = k;
    return options1;
  }
  
  @SuppressLint({"SdCardPath"})
  public static boolean isExternalFile(Context paramContext, String paramString) {
    return (Build.VERSION.SDK_INT < 29 && (paramString.startsWith(QUtil.getExternalStoragePath(paramContext)) || paramString.startsWith("/sdcard/") || isExternalFileUrl(paramContext, paramString)));
  }
  
  @Deprecated
  @SuppressLint({"SdCardPath"})
  public static boolean isExternalFile(String paramString) {
    Log.w("MediaUtil", "Calling deprecated version of isExternalFile", new IllegalAccessException());
    return (paramString.startsWith(QUtil.getExternalStoragePath((Context)Form.getActiveForm())) || paramString.startsWith("/sdcard/") || isExternalFileUrl((Context)Form.getActiveForm(), paramString));
  }
  
  @SuppressLint({"SdCardPath"})
  public static boolean isExternalFileUrl(Context paramContext, String paramString) {
    return (Build.VERSION.SDK_INT < 29 && (paramString.startsWith("file://" + QUtil.getExternalStorageDir(paramContext)) || paramString.startsWith("file:///sdcard")));
  }
  
  @Deprecated
  @SuppressLint({"SdCardPath"})
  public static boolean isExternalFileUrl(String paramString) {
    Log.w("MediaUtil", "Calling deprecated version of isExternalFileUrl", new IllegalAccessException());
    return (paramString.startsWith("file://" + QUtil.getExternalStoragePath((Context)Form.getActiveForm())) || paramString.startsWith("file:///sdcard/"));
  }
  
  public static void loadMediaPlayer(MediaPlayer paramMediaPlayer, Form paramForm, String paramString) throws IOException {
    AssetFileDescriptor assetFileDescriptor;
    MediaSource mediaSource = determineMediaSource(paramForm, paramString);
    switch (mediaSource) {
      default:
        throw new IOException("Unable to load audio or video " + paramString + ".");
      case ASSET:
        assetFileDescriptor = getAssetsIgnoreCaseAfd(paramForm, paramString);
        try {
          paramMediaPlayer.setDataSource(assetFileDescriptor.getFileDescriptor(), assetFileDescriptor.getStartOffset(), assetFileDescriptor.getLength());
          return;
        } finally {
          assetFileDescriptor.close();
        } 
      case REPL_ASSET:
        assetFileDescriptor.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        paramMediaPlayer.setDataSource(assetFileDescriptor.getAssetPath(paramString));
        return;
      case SDCARD:
        assetFileDescriptor.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        paramMediaPlayer.setDataSource(paramString);
        return;
      case FILE_URL:
        if (isExternalFileUrl((Context)assetFileDescriptor, paramString))
          assetFileDescriptor.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
        paramMediaPlayer.setDataSource(fileUrlToFilePath(paramString));
        return;
      case URL:
        paramMediaPlayer.setDataSource(paramString);
        return;
      case CONTENT_URI:
        paramMediaPlayer.setDataSource((Context)assetFileDescriptor, Uri.parse(paramString));
        return;
      case CONTACT_URI:
        break;
    } 
    throw new IOException("Unable to load audio or video for contact " + paramString + ".");
  }
  
  public static int loadSoundPool(SoundPool paramSoundPool, Form paramForm, String paramString) throws IOException {
    MediaSource mediaSource = determineMediaSource(paramForm, paramString);
    switch (mediaSource) {
      default:
        throw new IOException("Unable to load audio " + paramString + ".");
      case ASSET:
        return paramSoundPool.load(getAssetsIgnoreCaseAfd(paramForm, paramString), 1);
      case REPL_ASSET:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        return paramSoundPool.load(QUtil.getReplAssetPath((Context)paramForm) + paramString, 1);
      case SDCARD:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        return paramSoundPool.load(paramString, 1);
      case FILE_URL:
        if (isExternalFileUrl((Context)paramForm, paramString))
          paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
        return paramSoundPool.load(fileUrlToFilePath(paramString), 1);
      case URL:
      case CONTENT_URI:
        return paramSoundPool.load(cacheMediaTempFile(paramForm, paramString, mediaSource).getAbsolutePath(), 1);
      case CONTACT_URI:
        break;
    } 
    throw new IOException("Unable to load audio for contact " + paramString + ".");
  }
  
  public static void loadVideoView(VideoView paramVideoView, Form paramForm, String paramString) throws IOException {
    MediaSource mediaSource = determineMediaSource(paramForm, paramString);
    switch (mediaSource) {
      default:
        throw new IOException("Unable to load video " + paramString + ".");
      case ASSET:
      case URL:
        paramVideoView.setVideoPath(cacheMediaTempFile(paramForm, paramString, mediaSource).getAbsolutePath());
        return;
      case REPL_ASSET:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        paramVideoView.setVideoPath(paramForm.getAssetPath(paramString));
        return;
      case SDCARD:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        paramVideoView.setVideoPath(paramString);
        return;
      case FILE_URL:
        if (isExternalFileUrl((Context)paramForm, paramString))
          paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
        paramVideoView.setVideoPath(fileUrlToFilePath(paramString));
        return;
      case CONTENT_URI:
        paramVideoView.setVideoURI(Uri.parse(paramString));
        return;
      case CONTACT_URI:
        break;
    } 
    throw new IOException("Unable to load video for contact " + paramString + ".");
  }
  
  public static InputStream openMedia(Form paramForm, String paramString) throws IOException {
    return openMedia(paramForm, paramString, determineMediaSource(paramForm, paramString));
  }
  
  private static InputStream openMedia(Form paramForm, String paramString, MediaSource paramMediaSource) throws IOException {
    InputStream inputStream1;
    switch (paramMediaSource) {
      default:
        throw new IOException("Unable to open media " + paramString + ".");
      case ASSET:
        return getAssetsIgnoreCaseInputStream(paramForm, paramString);
      case REPL_ASSET:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        return new FileInputStream(new File(URI.create(paramForm.getAssetPath(paramString))));
      case SDCARD:
        paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE");
        return new FileInputStream(paramString);
      case FILE_URL:
        if (isExternalFileUrl((Context)paramForm, paramString))
          paramForm.assertPermission("android.permission.READ_EXTERNAL_STORAGE"); 
      case URL:
        return (new URL(paramString)).openStream();
      case CONTENT_URI:
        return paramForm.getContentResolver().openInputStream(Uri.parse(paramString));
      case CONTACT_URI:
        break;
    } 
    if (SdkLevel.getLevel() >= 12) {
      inputStream1 = HoneycombMR1Util.openContactPhotoInputStreamHelper(paramForm.getContentResolver(), Uri.parse(paramString));
    } else {
      inputStream1 = Contacts.People.openContactPhotoInputStream(inputStream1.getContentResolver(), Uri.parse(paramString));
    } 
    InputStream inputStream2 = inputStream1;
    if (inputStream1 == null)
      throw new IOException("Unable to open contact photo " + paramString + "."); 
    return inputStream2;
  }
  
  private static class FlushedInputStream extends FilterInputStream {
    public FlushedInputStream(InputStream param1InputStream) {
      super(param1InputStream);
    }
    
    public long skip(long param1Long) throws IOException {
      for (long l = 0L;; l += l1) {
        long l1;
        if (l < param1Long) {
          long l2 = this.in.skip(param1Long - l);
          l1 = l2;
          if (l2 == 0L) {
            if (read() < 0)
              return l; 
            l1 = 1L;
          } 
        } else {
          return l;
        } 
      } 
    }
  }
  
  private enum MediaSource {
    ASSET, CONTACT_URI, CONTENT_URI, FILE_URL, REPL_ASSET, SDCARD, URL;
    
    static {
      CONTENT_URI = new MediaSource("CONTENT_URI", 5);
      CONTACT_URI = new MediaSource("CONTACT_URI", 6);
      $VALUES = new MediaSource[] { ASSET, REPL_ASSET, SDCARD, FILE_URL, URL, CONTENT_URI, CONTACT_URI };
    }
  }
  
  private static class Synchronizer<T> {
    private String error;
    
    private volatile boolean finished = false;
    
    private T result;
    
    private Synchronizer() {}
    
    public void error(String param1String) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield finished : Z
      //   7: aload_0
      //   8: aload_1
      //   9: putfield error : Ljava/lang/String;
      //   12: aload_0
      //   13: invokevirtual notifyAll : ()V
      //   16: aload_0
      //   17: monitorexit
      //   18: return
      //   19: astore_1
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_1
      //   23: athrow
      // Exception table:
      //   from	to	target	type
      //   2	16	19	finally
    }
    
    public String getError() {
      return this.error;
    }
    
    public T getResult() {
      return this.result;
    }
    
    public void waitfor() {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield finished : Z
      //   6: istore_1
      //   7: iload_1
      //   8: ifne -> 22
      //   11: aload_0
      //   12: invokevirtual wait : ()V
      //   15: goto -> 2
      //   18: astore_2
      //   19: goto -> 2
      //   22: aload_0
      //   23: monitorexit
      //   24: return
      //   25: astore_2
      //   26: aload_0
      //   27: monitorexit
      //   28: aload_2
      //   29: athrow
      // Exception table:
      //   from	to	target	type
      //   2	7	25	finally
      //   11	15	18	java/lang/InterruptedException
      //   11	15	25	finally
    }
    
    public void wakeup(T param1T) {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: iconst_1
      //   4: putfield finished : Z
      //   7: aload_0
      //   8: aload_1
      //   9: putfield result : Ljava/lang/Object;
      //   12: aload_0
      //   13: invokevirtual notifyAll : ()V
      //   16: aload_0
      //   17: monitorexit
      //   18: return
      //   19: astore_1
      //   20: aload_0
      //   21: monitorexit
      //   22: aload_1
      //   23: athrow
      // Exception table:
      //   from	to	target	type
      //   2	16	19	finally
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/MediaUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */