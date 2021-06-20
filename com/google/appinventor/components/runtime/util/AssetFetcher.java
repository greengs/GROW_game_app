package com.google.appinventor.components.runtime.util;

import android.util.Log;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.ReplForm;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONException;

public class AssetFetcher {
  private static final String LOG_TAG = AssetFetcher.class.getSimpleName();
  
  private static ExecutorService background = Executors.newSingleThreadExecutor();
  
  private static volatile boolean inError = false;
  
  private static final Object semaphore = new Object();
  
  public static void fetchAssets(final String cookieValue, final String projectId, final String uri, final String asset) {
    background.submit(new Runnable() {
          public void run() {
            if (AssetFetcher.getFile(uri + "/ode/download/file/" + projectId + "/" + asset, cookieValue, asset, 0) != null)
              RetValManager.assetTransferred(asset); 
          }
        });
  }
  
  private static File getFile(String paramString1, String paramString2, String paramString3, int paramInt) {
    // Byte code:
    //   0: invokestatic getActiveForm : ()Lcom/google/appinventor/components/runtime/Form;
    //   3: astore #8
    //   5: iload_3
    //   6: iconst_1
    //   7: if_icmple -> 52
    //   10: getstatic com/google/appinventor/components/runtime/util/AssetFetcher.semaphore : Ljava/lang/Object;
    //   13: astore_1
    //   14: aload_1
    //   15: monitorenter
    //   16: getstatic com/google/appinventor/components/runtime/util/AssetFetcher.inError : Z
    //   19: ifeq -> 26
    //   22: aload_1
    //   23: monitorexit
    //   24: aconst_null
    //   25: areturn
    //   26: iconst_1
    //   27: putstatic com/google/appinventor/components/runtime/util/AssetFetcher.inError : Z
    //   30: aload #8
    //   32: new com/google/appinventor/components/runtime/util/AssetFetcher$2
    //   35: dup
    //   36: aload_0
    //   37: invokespecial <init> : (Ljava/lang/String;)V
    //   40: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
    //   43: aload_1
    //   44: monitorexit
    //   45: aconst_null
    //   46: areturn
    //   47: astore_0
    //   48: aload_1
    //   49: monitorexit
    //   50: aload_0
    //   51: athrow
    //   52: iconst_0
    //   53: istore #4
    //   55: aconst_null
    //   56: astore #6
    //   58: new java/net/URL
    //   61: dup
    //   62: aload_0
    //   63: invokespecial <init> : (Ljava/lang/String;)V
    //   66: invokevirtual openConnection : ()Ljava/net/URLConnection;
    //   69: checkcast java/net/HttpURLConnection
    //   72: astore #7
    //   74: aload #7
    //   76: ifnull -> 398
    //   79: aload #7
    //   81: ldc 'GET'
    //   83: invokevirtual setRequestMethod : (Ljava/lang/String;)V
    //   86: aload #7
    //   88: ldc 'Cookie'
    //   90: new java/lang/StringBuilder
    //   93: dup
    //   94: invokespecial <init> : ()V
    //   97: ldc 'AppInventor = '
    //   99: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   102: aload_1
    //   103: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   106: invokevirtual toString : ()Ljava/lang/String;
    //   109: invokevirtual addRequestProperty : (Ljava/lang/String;Ljava/lang/String;)V
    //   112: aload #7
    //   114: invokevirtual getResponseCode : ()I
    //   117: istore #5
    //   119: getstatic com/google/appinventor/components/runtime/util/AssetFetcher.LOG_TAG : Ljava/lang/String;
    //   122: new java/lang/StringBuilder
    //   125: dup
    //   126: invokespecial <init> : ()V
    //   129: ldc 'asset = '
    //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   134: aload_2
    //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   138: ldc ' responseCode = '
    //   140: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   143: iload #5
    //   145: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   148: invokevirtual toString : ()Ljava/lang/String;
    //   151: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   154: pop
    //   155: new java/io/File
    //   158: dup
    //   159: aload #8
    //   161: invokestatic getReplAssetPath : (Landroid/content/Context;)Ljava/lang/String;
    //   164: aload_2
    //   165: ldc 'assets/'
    //   167: invokevirtual length : ()I
    //   170: invokevirtual substring : (I)Ljava/lang/String;
    //   173: invokespecial <init> : (Ljava/lang/String;Ljava/lang/String;)V
    //   176: astore #6
    //   178: aload #6
    //   180: invokevirtual getParentFile : ()Ljava/io/File;
    //   183: astore #8
    //   185: aload #8
    //   187: invokevirtual exists : ()Z
    //   190: ifne -> 269
    //   193: aload #8
    //   195: invokevirtual mkdirs : ()Z
    //   198: ifne -> 269
    //   201: new java/io/IOException
    //   204: dup
    //   205: new java/lang/StringBuilder
    //   208: dup
    //   209: invokespecial <init> : ()V
    //   212: ldc 'Unable to create assets directory '
    //   214: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   217: aload #8
    //   219: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   222: invokevirtual toString : ()Ljava/lang/String;
    //   225: invokespecial <init> : (Ljava/lang/String;)V
    //   228: athrow
    //   229: astore #6
    //   231: getstatic com/google/appinventor/components/runtime/util/AssetFetcher.LOG_TAG : Ljava/lang/String;
    //   234: new java/lang/StringBuilder
    //   237: dup
    //   238: invokespecial <init> : ()V
    //   241: ldc 'Exception while fetching '
    //   243: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   246: aload_0
    //   247: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: invokevirtual toString : ()Ljava/lang/String;
    //   253: aload #6
    //   255: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   258: pop
    //   259: aload_0
    //   260: aload_1
    //   261: aload_2
    //   262: iload_3
    //   263: iconst_1
    //   264: iadd
    //   265: invokestatic getFile : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Ljava/io/File;
    //   268: areturn
    //   269: new java/io/BufferedInputStream
    //   272: dup
    //   273: aload #7
    //   275: invokevirtual getInputStream : ()Ljava/io/InputStream;
    //   278: sipush #4096
    //   281: invokespecial <init> : (Ljava/io/InputStream;I)V
    //   284: astore #9
    //   286: new java/io/BufferedOutputStream
    //   289: dup
    //   290: new java/io/FileOutputStream
    //   293: dup
    //   294: aload #6
    //   296: invokespecial <init> : (Ljava/io/File;)V
    //   299: sipush #4096
    //   302: invokespecial <init> : (Ljava/io/OutputStream;I)V
    //   305: astore #8
    //   307: aload #9
    //   309: invokevirtual read : ()I
    //   312: istore #5
    //   314: iload #5
    //   316: iconst_m1
    //   317: if_icmpne -> 354
    //   320: aload #8
    //   322: invokevirtual flush : ()V
    //   325: aload #8
    //   327: invokevirtual close : ()V
    //   330: aload #7
    //   332: invokevirtual disconnect : ()V
    //   335: iload #4
    //   337: ifeq -> 409
    //   340: aload_0
    //   341: aload_1
    //   342: aload_2
    //   343: iload_3
    //   344: iconst_1
    //   345: iadd
    //   346: invokestatic getFile : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;I)Ljava/io/File;
    //   349: astore #6
    //   351: aload #6
    //   353: areturn
    //   354: aload #8
    //   356: iload #5
    //   358: invokevirtual write : (I)V
    //   361: goto -> 307
    //   364: astore #9
    //   366: getstatic com/google/appinventor/components/runtime/util/AssetFetcher.LOG_TAG : Ljava/lang/String;
    //   369: ldc 'copying assets'
    //   371: aload #9
    //   373: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   376: pop
    //   377: iconst_1
    //   378: istore #4
    //   380: aload #8
    //   382: invokevirtual close : ()V
    //   385: goto -> 330
    //   388: astore #6
    //   390: aload #8
    //   392: invokevirtual close : ()V
    //   395: aload #6
    //   397: athrow
    //   398: iconst_1
    //   399: istore #4
    //   401: goto -> 335
    //   404: astore #6
    //   406: goto -> 231
    //   409: aload #6
    //   411: areturn
    // Exception table:
    //   from	to	target	type
    //   16	24	47	finally
    //   26	45	47	finally
    //   48	50	47	finally
    //   58	74	404	java/lang/Exception
    //   79	178	404	java/lang/Exception
    //   178	229	229	java/lang/Exception
    //   269	307	229	java/lang/Exception
    //   307	314	364	java/io/IOException
    //   307	314	388	finally
    //   320	325	364	java/io/IOException
    //   320	325	388	finally
    //   325	330	229	java/lang/Exception
    //   330	335	229	java/lang/Exception
    //   340	351	404	java/lang/Exception
    //   354	361	364	java/io/IOException
    //   354	361	388	finally
    //   366	377	388	finally
    //   380	385	229	java/lang/Exception
    //   390	398	229	java/lang/Exception
  }
  
  public static void loadExtensions(String paramString) {
    ReplForm replForm;
    ArrayList<String> arrayList;
    Log.d(LOG_TAG, "loadExtensions called jsonString = " + paramString);
    try {
      replForm = (ReplForm)Form.getActiveForm();
      JSONArray jSONArray = new JSONArray(paramString);
      arrayList = new ArrayList();
      if (jSONArray.length() == 0) {
        Log.d(LOG_TAG, "loadExtensions: No Extensions");
        RetValManager.extensionsLoaded();
        return;
      } 
    } catch (JSONException jSONException) {
      Log.e(LOG_TAG, "JSON Exception parsing extension string", (Throwable)jSONException);
      return;
    } 
    int i = 0;
    while (i < jSONException.length()) {
      String str = jSONException.optString(i);
      if (str != null) {
        Log.d(LOG_TAG, "loadExtensions, extensionName = " + str);
        arrayList.add(str);
        i++;
        continue;
      } 
      Log.e(LOG_TAG, "extensionName was null");
      return;
    } 
    try {
      replForm.loadComponents(arrayList);
      RetValManager.extensionsLoaded();
      return;
    } catch (Exception exception) {
      Log.e(LOG_TAG, "Error in form.loadComponents", exception);
      return;
    } 
  }
  
  public static void upgradeCompanion(String paramString1, String paramString2) {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AssetFetcher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */