package com.google.appinventor.components.runtime.util;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.google.appinventor.components.runtime.ReplForm;
import gnu.expr.Language;
import gnu.expr.ModuleExp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import kawa.standard.Scheme;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AppInvHTTPD extends NanoHTTPD {
  private static final String LOG_TAG = "AppInvHTTPD";
  
  private static final String MIME_JSON = "application/json";
  
  private static final int YAV_SKEW_BACKWARD = 4;
  
  private static final int YAV_SKEW_FORWARD = 1;
  
  private static byte[] hmacKey;
  
  private static int seq;
  
  private final Handler androidUIHandler = new Handler();
  
  private ReplForm form;
  
  private File rootDir;
  
  private Language scheme;
  
  private boolean secure;
  
  public AppInvHTTPD(int paramInt, File paramFile, boolean paramBoolean, ReplForm paramReplForm) throws IOException {
    super(paramInt, paramFile);
    this.rootDir = paramFile;
    this.scheme = Scheme.getInstance("scheme");
    this.form = paramReplForm;
    this.secure = paramBoolean;
    ModuleExp.mustNeverCompile();
  }
  
  private NanoHTTPD.Response addHeaders(NanoHTTPD.Response paramResponse) {
    paramResponse.addHeader("Access-Control-Allow-Origin", "*");
    paramResponse.addHeader("Access-Control-Allow-Headers", "origin, content-type");
    paramResponse.addHeader("Access-Control-Allow-Methods", "POST,OPTIONS,GET,HEAD,PUT");
    paramResponse.addHeader("Allow", "POST,OPTIONS,GET,HEAD,PUT");
    return paramResponse;
  }
  
  private void adoptMainThreadClassLoader() {
    ClassLoader classLoader = Looper.getMainLooper().getThread().getContextClassLoader();
    Thread thread = Thread.currentThread();
    if (thread.getContextClassLoader() != classLoader)
      thread.setContextClassLoader(classLoader); 
  }
  
  private boolean copyFile(File paramFile1, File paramFile2) {
    try {
      FileInputStream fileInputStream = new FileInputStream(paramFile1);
      FileOutputStream fileOutputStream = new FileOutputStream(paramFile2);
      byte[] arrayOfByte = new byte[32768];
      while (true) {
        int i = fileInputStream.read(arrayOfByte);
        if (i > 0) {
          fileOutputStream.write(arrayOfByte, 0, i);
          continue;
        } 
        fileInputStream.close();
        fileOutputStream.close();
        return false;
      } 
    } catch (IOException iOException) {
      iOException.printStackTrace();
      return true;
    } 
  }
  
  private NanoHTTPD.Response error(String paramString) {
    JSONObject jSONObject = new JSONObject();
    try {
      jSONObject.put("status", "BAD");
      jSONObject.put("message", paramString);
    } catch (JSONException jSONException) {
      Log.wtf("AppInvHTTPD", "Unable to write basic JSON content", (Throwable)jSONException);
    } 
    return addHeaders(new NanoHTTPD.Response(this, "200 OK", "application/json", jSONObject.toString()));
  }
  
  private NanoHTTPD.Response error(Throwable paramThrowable) {
    return error(paramThrowable.toString());
  }
  
  private NanoHTTPD.Response json(String paramString) {
    return addHeaders(new NanoHTTPD.Response(this, "200 OK", "application/json", paramString));
  }
  
  private NanoHTTPD.Response message(String paramString) {
    return addHeaders(new NanoHTTPD.Response(this, "200 OK", "text/plain", paramString));
  }
  
  private NanoHTTPD.Response processLoadExtensionsRequest(Properties paramProperties) {
    try {
      JSONArray jSONArray = new JSONArray(paramProperties.getProperty("extensions", "[]"));
      ArrayList<String> arrayList = new ArrayList();
      int i = 0;
      while (i < jSONArray.length()) {
        String str = jSONArray.optString(i);
        if (str != null) {
          arrayList.add(str);
          i++;
          continue;
        } 
        return error("Invalid JSON content at index " + i);
      } 
      try {
        this.form.loadComponents(arrayList);
        return message("OK");
      } catch (Exception exception) {
        return error(exception);
      } 
    } catch (JSONException jSONException) {
      return error((Throwable)jSONException);
    } 
  }
  
  public static void setHmacKey(String paramString) {
    hmacKey = paramString.getBytes();
    seq = 1;
  }
  
  public void resetSeq() {
    seq = 1;
  }
  
  public NanoHTTPD.Response serve(String paramString1, String paramString2, Properties paramProperties1, Properties paramProperties2, Properties paramProperties3, Socket paramSocket) {
    // Byte code:
    //   0: ldc 'AppInvHTTPD'
    //   2: new java/lang/StringBuilder
    //   5: dup
    //   6: invokespecial <init> : ()V
    //   9: aload_2
    //   10: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   13: ldc_w ' ''
    //   16: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   19: aload_1
    //   20: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: ldc_w '' '
    //   26: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: invokevirtual toString : ()Ljava/lang/String;
    //   32: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   35: pop
    //   36: aload_0
    //   37: getfield secure : Z
    //   40: ifeq -> 171
    //   43: aload #6
    //   45: invokevirtual getInetAddress : ()Ljava/net/InetAddress;
    //   48: invokevirtual getHostAddress : ()Ljava/lang/String;
    //   51: astore #6
    //   53: aload #6
    //   55: ldc_w '127.0.0.1'
    //   58: invokevirtual equals : (Ljava/lang/Object;)Z
    //   61: ifne -> 171
    //   64: ldc 'AppInvHTTPD'
    //   66: new java/lang/StringBuilder
    //   69: dup
    //   70: invokespecial <init> : ()V
    //   73: ldc_w 'Debug: hostAddress = '
    //   76: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   79: aload #6
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: ldc_w ' while in secure mode, closing connection.'
    //   87: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: invokevirtual toString : ()Ljava/lang/String;
    //   93: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   96: pop
    //   97: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   100: dup
    //   101: aload_0
    //   102: ldc '200 OK'
    //   104: ldc 'application/json'
    //   106: new java/lang/StringBuilder
    //   109: dup
    //   110: invokespecial <init> : ()V
    //   113: ldc_w '{"status" : "BAD", "message" : "Security Error: Invalid Source Location '
    //   116: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   119: aload #6
    //   121: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   124: ldc_w '"}'
    //   127: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   130: invokevirtual toString : ()Ljava/lang/String;
    //   133: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   136: astore_2
    //   137: aload_2
    //   138: ldc 'Access-Control-Allow-Origin'
    //   140: ldc '*'
    //   142: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   145: aload_2
    //   146: ldc 'Access-Control-Allow-Headers'
    //   148: ldc 'origin, content-type'
    //   150: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   153: aload_2
    //   154: ldc 'Access-Control-Allow-Methods'
    //   156: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   158: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   161: aload_2
    //   162: ldc 'Allow'
    //   164: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   166: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   169: aload_2
    //   170: areturn
    //   171: aload_2
    //   172: ldc_w 'OPTIONS'
    //   175: invokevirtual equals : (Ljava/lang/Object;)Z
    //   178: ifeq -> 303
    //   181: aload_3
    //   182: invokevirtual propertyNames : ()Ljava/util/Enumeration;
    //   185: astore_1
    //   186: aload_1
    //   187: invokeinterface hasMoreElements : ()Z
    //   192: ifeq -> 254
    //   195: aload_1
    //   196: invokeinterface nextElement : ()Ljava/lang/Object;
    //   201: checkcast java/lang/String
    //   204: astore_2
    //   205: ldc 'AppInvHTTPD'
    //   207: new java/lang/StringBuilder
    //   210: dup
    //   211: invokespecial <init> : ()V
    //   214: ldc_w '  HDR: ''
    //   217: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   220: aload_2
    //   221: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   224: ldc_w '' = ''
    //   227: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   230: aload_3
    //   231: aload_2
    //   232: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   238: ldc_w '''
    //   241: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   244: invokevirtual toString : ()Ljava/lang/String;
    //   247: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   250: pop
    //   251: goto -> 186
    //   254: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   257: dup
    //   258: aload_0
    //   259: ldc '200 OK'
    //   261: ldc 'text/plain'
    //   263: ldc 'OK'
    //   265: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   268: astore_1
    //   269: aload_1
    //   270: ldc 'Access-Control-Allow-Origin'
    //   272: ldc '*'
    //   274: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   277: aload_1
    //   278: ldc 'Access-Control-Allow-Headers'
    //   280: ldc 'origin, content-type'
    //   282: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   285: aload_1
    //   286: ldc 'Access-Control-Allow-Methods'
    //   288: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   290: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   293: aload_1
    //   294: ldc 'Allow'
    //   296: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   298: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   301: aload_1
    //   302: areturn
    //   303: aload_1
    //   304: ldc_w '/_newblocks'
    //   307: invokevirtual equals : (Ljava/lang/Object;)Z
    //   310: ifeq -> 1090
    //   313: aload_0
    //   314: invokespecial adoptMainThreadClassLoader : ()V
    //   317: aload #4
    //   319: ldc_w 'seq'
    //   322: ldc_w '0'
    //   325: invokevirtual getProperty : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   328: astore_3
    //   329: aload_3
    //   330: invokestatic parseInt : (Ljava/lang/String;)I
    //   333: istore #8
    //   335: aload #4
    //   337: ldc_w 'blockid'
    //   340: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   343: astore_2
    //   344: aload #4
    //   346: ldc_w 'code'
    //   349: invokevirtual getProperty : (Ljava/lang/String;)Ljava/lang/String;
    //   352: astore_1
    //   353: aload #4
    //   355: ldc_w 'mac'
    //   358: ldc_w 'no key provided'
    //   361: invokevirtual getProperty : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   364: astore #4
    //   366: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.hmacKey : [B
    //   369: ifnull -> 986
    //   372: ldc_w 'HmacSHA1'
    //   375: invokestatic getInstance : (Ljava/lang/String;)Ljavax/crypto/Mac;
    //   378: astore #5
    //   380: aload #5
    //   382: new javax/crypto/spec/SecretKeySpec
    //   385: dup
    //   386: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.hmacKey : [B
    //   389: ldc_w 'RAW'
    //   392: invokespecial <init> : ([BLjava/lang/String;)V
    //   395: invokevirtual init : (Ljava/security/Key;)V
    //   398: aload #5
    //   400: new java/lang/StringBuilder
    //   403: dup
    //   404: invokespecial <init> : ()V
    //   407: aload_1
    //   408: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   411: aload_3
    //   412: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   415: aload_2
    //   416: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   419: invokevirtual toString : ()Ljava/lang/String;
    //   422: invokevirtual getBytes : ()[B
    //   425: invokevirtual doFinal : ([B)[B
    //   428: astore #5
    //   430: new java/lang/StringBuffer
    //   433: dup
    //   434: aload #5
    //   436: arraylength
    //   437: iconst_2
    //   438: imul
    //   439: invokespecial <init> : (I)V
    //   442: astore #6
    //   444: new java/util/Formatter
    //   447: dup
    //   448: aload #6
    //   450: invokespecial <init> : (Ljava/lang/Appendable;)V
    //   453: astore #10
    //   455: aload #5
    //   457: arraylength
    //   458: istore #9
    //   460: iconst_0
    //   461: istore #7
    //   463: iload #7
    //   465: iload #9
    //   467: if_icmpge -> 503
    //   470: aload #10
    //   472: ldc_w '%02x'
    //   475: iconst_1
    //   476: anewarray java/lang/Object
    //   479: dup
    //   480: iconst_0
    //   481: aload #5
    //   483: iload #7
    //   485: baload
    //   486: invokestatic valueOf : (B)Ljava/lang/Byte;
    //   489: aastore
    //   490: invokevirtual format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/util/Formatter;
    //   493: pop
    //   494: iload #7
    //   496: iconst_1
    //   497: iadd
    //   498: istore #7
    //   500: goto -> 463
    //   503: aload #6
    //   505: invokevirtual toString : ()Ljava/lang/String;
    //   508: astore #5
    //   510: ldc 'AppInvHTTPD'
    //   512: new java/lang/StringBuilder
    //   515: dup
    //   516: invokespecial <init> : ()V
    //   519: ldc_w 'Incoming Mac = '
    //   522: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   525: aload #4
    //   527: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   530: invokevirtual toString : ()Ljava/lang/String;
    //   533: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   536: pop
    //   537: ldc 'AppInvHTTPD'
    //   539: new java/lang/StringBuilder
    //   542: dup
    //   543: invokespecial <init> : ()V
    //   546: ldc_w 'Computed Mac = '
    //   549: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   552: aload #5
    //   554: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   557: invokevirtual toString : ()Ljava/lang/String;
    //   560: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   563: pop
    //   564: ldc 'AppInvHTTPD'
    //   566: new java/lang/StringBuilder
    //   569: dup
    //   570: invokespecial <init> : ()V
    //   573: ldc_w 'Incoming seq = '
    //   576: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   579: aload_3
    //   580: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   583: invokevirtual toString : ()Ljava/lang/String;
    //   586: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   589: pop
    //   590: ldc 'AppInvHTTPD'
    //   592: new java/lang/StringBuilder
    //   595: dup
    //   596: invokespecial <init> : ()V
    //   599: ldc_w 'Computed seq = '
    //   602: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   605: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   608: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   611: invokevirtual toString : ()Ljava/lang/String;
    //   614: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   617: pop
    //   618: ldc 'AppInvHTTPD'
    //   620: new java/lang/StringBuilder
    //   623: dup
    //   624: invokespecial <init> : ()V
    //   627: ldc_w 'blockid = '
    //   630: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   633: aload_2
    //   634: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   637: invokevirtual toString : ()Ljava/lang/String;
    //   640: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   643: pop
    //   644: aload #4
    //   646: aload #5
    //   648: invokevirtual equals : (Ljava/lang/Object;)Z
    //   651: ifne -> 758
    //   654: ldc 'AppInvHTTPD'
    //   656: ldc_w 'Hmac does not match'
    //   659: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   662: pop
    //   663: aload_0
    //   664: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   667: aload_0
    //   668: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   671: ldc 'AppInvHTTPD'
    //   673: sipush #1801
    //   676: iconst_1
    //   677: anewarray java/lang/Object
    //   680: dup
    //   681: iconst_0
    //   682: ldc_w 'Invalid HMAC'
    //   685: aastore
    //   686: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   689: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   692: dup
    //   693: aload_0
    //   694: ldc '200 OK'
    //   696: ldc 'application/json'
    //   698: ldc_w '{"status" : "BAD", "message" : "Security Error: Invalid MAC"}'
    //   701: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   704: areturn
    //   705: astore_1
    //   706: ldc 'AppInvHTTPD'
    //   708: ldc_w 'Error working with hmac'
    //   711: aload_1
    //   712: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   715: pop
    //   716: aload_0
    //   717: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   720: aload_0
    //   721: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   724: ldc 'AppInvHTTPD'
    //   726: sipush #1801
    //   729: iconst_1
    //   730: anewarray java/lang/Object
    //   733: dup
    //   734: iconst_0
    //   735: ldc_w 'Exception working on HMAC'
    //   738: aastore
    //   739: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   742: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   745: dup
    //   746: aload_0
    //   747: ldc '200 OK'
    //   749: ldc 'text/plain'
    //   751: ldc_w 'NOT'
    //   754: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   757: areturn
    //   758: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   761: iload #8
    //   763: if_icmpeq -> 827
    //   766: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   769: iload #8
    //   771: iconst_1
    //   772: iadd
    //   773: if_icmpeq -> 827
    //   776: ldc 'AppInvHTTPD'
    //   778: ldc_w 'Seq does not match'
    //   781: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   784: pop
    //   785: aload_0
    //   786: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   789: aload_0
    //   790: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   793: ldc 'AppInvHTTPD'
    //   795: sipush #1801
    //   798: iconst_1
    //   799: anewarray java/lang/Object
    //   802: dup
    //   803: iconst_0
    //   804: ldc_w 'Invalid Seq'
    //   807: aastore
    //   808: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   811: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   814: dup
    //   815: aload_0
    //   816: ldc '200 OK'
    //   818: ldc 'application/json'
    //   820: ldc_w '{"status" : "BAD", "message" : "Security Error: Invalid Seq"}'
    //   823: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   826: areturn
    //   827: getstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   830: iload #8
    //   832: iconst_1
    //   833: iadd
    //   834: if_icmpne -> 846
    //   837: ldc 'AppInvHTTPD'
    //   839: ldc_w 'Seq Fixup Invoked'
    //   842: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   845: pop
    //   846: iload #8
    //   848: iconst_1
    //   849: iadd
    //   850: putstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   853: new java/lang/StringBuilder
    //   856: dup
    //   857: invokespecial <init> : ()V
    //   860: ldc_w '(begin (require <com.google.youngandroid.runtime>) (process-repl-input '
    //   863: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   866: aload_2
    //   867: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   870: ldc_w ' (begin '
    //   873: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   876: aload_1
    //   877: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   880: ldc_w ' )))'
    //   883: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   886: invokevirtual toString : ()Ljava/lang/String;
    //   889: astore_3
    //   890: ldc 'AppInvHTTPD'
    //   892: new java/lang/StringBuilder
    //   895: dup
    //   896: invokespecial <init> : ()V
    //   899: ldc_w 'To Eval: '
    //   902: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   905: aload_3
    //   906: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   909: invokevirtual toString : ()Ljava/lang/String;
    //   912: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   915: pop
    //   916: aload_1
    //   917: ldc_w '#f'
    //   920: invokevirtual equals : (Ljava/lang/Object;)Z
    //   923: ifeq -> 1037
    //   926: ldc 'AppInvHTTPD'
    //   928: ldc_w 'Skipping evaluation of #f'
    //   931: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   934: pop
    //   935: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   938: dup
    //   939: aload_0
    //   940: ldc '200 OK'
    //   942: ldc 'application/json'
    //   944: iconst_0
    //   945: invokestatic fetch : (Z)Ljava/lang/String;
    //   948: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   951: astore_1
    //   952: aload_1
    //   953: ldc 'Access-Control-Allow-Origin'
    //   955: ldc '*'
    //   957: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   960: aload_1
    //   961: ldc 'Access-Control-Allow-Headers'
    //   963: ldc 'origin, content-type'
    //   965: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   968: aload_1
    //   969: ldc 'Access-Control-Allow-Methods'
    //   971: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   973: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   976: aload_1
    //   977: ldc 'Allow'
    //   979: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   981: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   984: aload_1
    //   985: areturn
    //   986: ldc 'AppInvHTTPD'
    //   988: ldc_w 'No HMAC Key'
    //   991: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   994: pop
    //   995: aload_0
    //   996: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   999: aload_0
    //   1000: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   1003: ldc 'AppInvHTTPD'
    //   1005: sipush #1801
    //   1008: iconst_1
    //   1009: anewarray java/lang/Object
    //   1012: dup
    //   1013: iconst_0
    //   1014: ldc_w 'No HMAC Key'
    //   1017: aastore
    //   1018: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   1021: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1024: dup
    //   1025: aload_0
    //   1026: ldc '200 OK'
    //   1028: ldc 'application/json'
    //   1030: ldc_w '{"status" : "BAD", "message" : "Security Error: No HMAC Key"}'
    //   1033: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1036: areturn
    //   1037: aload_0
    //   1038: getfield scheme : Lgnu/expr/Language;
    //   1041: aload_3
    //   1042: invokevirtual eval : (Ljava/lang/String;)Ljava/lang/Object;
    //   1045: pop
    //   1046: goto -> 935
    //   1049: astore_1
    //   1050: ldc 'AppInvHTTPD'
    //   1052: ldc_w 'newblocks: Scheme Failure'
    //   1055: aload_1
    //   1056: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   1059: pop
    //   1060: aload_2
    //   1061: ldc 'BAD'
    //   1063: aload_1
    //   1064: invokevirtual toString : ()Ljava/lang/String;
    //   1067: invokestatic appendReturnValue : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1070: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1073: dup
    //   1074: aload_0
    //   1075: ldc '200 OK'
    //   1077: ldc 'application/json'
    //   1079: iconst_0
    //   1080: invokestatic fetch : (Z)Ljava/lang/String;
    //   1083: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1086: astore_1
    //   1087: goto -> 952
    //   1090: aload_1
    //   1091: ldc_w '/_values'
    //   1094: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1097: ifeq -> 1151
    //   1100: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1103: dup
    //   1104: aload_0
    //   1105: ldc '200 OK'
    //   1107: ldc 'application/json'
    //   1109: iconst_1
    //   1110: invokestatic fetch : (Z)Ljava/lang/String;
    //   1113: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1116: astore_1
    //   1117: aload_1
    //   1118: ldc 'Access-Control-Allow-Origin'
    //   1120: ldc '*'
    //   1122: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1125: aload_1
    //   1126: ldc 'Access-Control-Allow-Headers'
    //   1128: ldc 'origin, content-type'
    //   1130: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1133: aload_1
    //   1134: ldc 'Access-Control-Allow-Methods'
    //   1136: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1138: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1141: aload_1
    //   1142: ldc 'Allow'
    //   1144: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1146: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1149: aload_1
    //   1150: areturn
    //   1151: aload_1
    //   1152: ldc_w '/_getversion'
    //   1155: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1158: ifeq -> 1382
    //   1161: aload_0
    //   1162: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   1165: invokevirtual getPackageName : ()Ljava/lang/String;
    //   1168: astore_3
    //   1169: aload_0
    //   1170: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   1173: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
    //   1176: aload_3
    //   1177: iconst_0
    //   1178: invokevirtual getPackageInfo : (Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   1181: astore_2
    //   1182: invokestatic getLevel : ()I
    //   1185: iconst_5
    //   1186: if_icmplt -> 1351
    //   1189: ldc_w 'edu.mit.appinventor.aicompanion3'
    //   1192: aload_0
    //   1193: getfield form : Lcom/google/appinventor/components/runtime/ReplForm;
    //   1196: invokestatic getInstallerPackageName : (Ljava/lang/String;Landroid/app/Activity;)Ljava/lang/String;
    //   1199: astore_1
    //   1200: aload_2
    //   1201: getfield versionName : Ljava/lang/String;
    //   1204: astore #4
    //   1206: aload_1
    //   1207: astore_2
    //   1208: aload_1
    //   1209: ifnonnull -> 1216
    //   1212: ldc_w 'Not Known'
    //   1215: astore_2
    //   1216: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1219: dup
    //   1220: aload_0
    //   1221: ldc '200 OK'
    //   1223: ldc 'application/json'
    //   1225: new java/lang/StringBuilder
    //   1228: dup
    //   1229: invokespecial <init> : ()V
    //   1232: ldc_w '{"version" : "'
    //   1235: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1238: aload #4
    //   1240: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1243: ldc_w '", "fingerprint" : "'
    //   1246: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1249: getstatic android/os/Build.FINGERPRINT : Ljava/lang/String;
    //   1252: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1255: ldc_w '", "installer" : "'
    //   1258: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1261: aload_2
    //   1262: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1265: ldc_w '", "package" : "'
    //   1268: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1271: aload_3
    //   1272: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1275: ldc_w '", "fqcn" : true }'
    //   1278: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1281: invokevirtual toString : ()Ljava/lang/String;
    //   1284: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1287: astore_1
    //   1288: aload_1
    //   1289: ldc 'Access-Control-Allow-Origin'
    //   1291: ldc '*'
    //   1293: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1296: aload_1
    //   1297: ldc 'Access-Control-Allow-Headers'
    //   1299: ldc 'origin, content-type'
    //   1301: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1304: aload_1
    //   1305: ldc 'Access-Control-Allow-Methods'
    //   1307: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1309: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1312: aload_1
    //   1313: ldc 'Allow'
    //   1315: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1317: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1320: aload_1
    //   1321: astore_2
    //   1322: aload_0
    //   1323: getfield secure : Z
    //   1326: ifeq -> 169
    //   1329: iconst_1
    //   1330: putstatic com/google/appinventor/components/runtime/util/AppInvHTTPD.seq : I
    //   1333: aload_0
    //   1334: getfield androidUIHandler : Landroid/os/Handler;
    //   1337: new com/google/appinventor/components/runtime/util/AppInvHTTPD$1
    //   1340: dup
    //   1341: aload_0
    //   1342: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/AppInvHTTPD;)V
    //   1345: invokevirtual post : (Ljava/lang/Runnable;)Z
    //   1348: pop
    //   1349: aload_1
    //   1350: areturn
    //   1351: ldc_w 'Not Known'
    //   1354: astore_1
    //   1355: goto -> 1200
    //   1358: astore_1
    //   1359: aload_1
    //   1360: invokevirtual printStackTrace : ()V
    //   1363: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1366: dup
    //   1367: aload_0
    //   1368: ldc '200 OK'
    //   1370: ldc 'application/json'
    //   1372: ldc_w '{"verison" : "Unknown"'
    //   1375: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1378: astore_1
    //   1379: goto -> 1288
    //   1382: aload_1
    //   1383: ldc_w '/_extensions'
    //   1386: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1389: ifeq -> 1399
    //   1392: aload_0
    //   1393: aload #4
    //   1395: invokespecial processLoadExtensionsRequest : (Ljava/util/Properties;)Lcom/google/appinventor/components/runtime/util/NanoHTTPD$Response;
    //   1398: areturn
    //   1399: aload_2
    //   1400: ldc_w 'PUT'
    //   1403: invokevirtual equals : (Ljava/lang/Object;)Z
    //   1406: ifeq -> 1745
    //   1409: iconst_0
    //   1410: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1413: astore_2
    //   1414: aload #5
    //   1416: ldc_w 'content'
    //   1419: aconst_null
    //   1420: invokevirtual getProperty : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1423: astore_1
    //   1424: aload_1
    //   1425: ifnull -> 1679
    //   1428: new java/io/File
    //   1431: dup
    //   1432: aload_1
    //   1433: invokespecial <init> : (Ljava/lang/String;)V
    //   1436: astore #5
    //   1438: aload #4
    //   1440: ldc_w 'filename'
    //   1443: aconst_null
    //   1444: invokevirtual getProperty : (Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   1447: astore_3
    //   1448: aload_3
    //   1449: astore_1
    //   1450: aload_3
    //   1451: ifnull -> 1514
    //   1454: aload_3
    //   1455: ldc_w '..'
    //   1458: invokevirtual startsWith : (Ljava/lang/String;)Z
    //   1461: ifne -> 1486
    //   1464: aload_3
    //   1465: ldc_w '..'
    //   1468: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   1471: ifne -> 1486
    //   1474: aload_3
    //   1475: astore_1
    //   1476: aload_3
    //   1477: ldc_w '../'
    //   1480: invokevirtual indexOf : (Ljava/lang/String;)I
    //   1483: iflt -> 1514
    //   1486: ldc 'AppInvHTTPD'
    //   1488: new java/lang/StringBuilder
    //   1491: dup
    //   1492: invokespecial <init> : ()V
    //   1495: ldc_w ' Ignoring invalid filename: '
    //   1498: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1501: aload_3
    //   1502: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1505: invokevirtual toString : ()Ljava/lang/String;
    //   1508: invokestatic d : (Ljava/lang/String;Ljava/lang/String;)I
    //   1511: pop
    //   1512: aconst_null
    //   1513: astore_1
    //   1514: aload_1
    //   1515: ifnull -> 1656
    //   1518: new java/io/File
    //   1521: dup
    //   1522: new java/lang/StringBuilder
    //   1525: dup
    //   1526: invokespecial <init> : ()V
    //   1529: aload_0
    //   1530: getfield rootDir : Ljava/io/File;
    //   1533: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   1536: ldc_w '/'
    //   1539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1542: aload_1
    //   1543: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1546: invokevirtual toString : ()Ljava/lang/String;
    //   1549: invokespecial <init> : (Ljava/lang/String;)V
    //   1552: astore_3
    //   1553: aload_3
    //   1554: invokevirtual getParentFile : ()Ljava/io/File;
    //   1557: astore_1
    //   1558: aload_1
    //   1559: invokevirtual exists : ()Z
    //   1562: ifne -> 1570
    //   1565: aload_1
    //   1566: invokevirtual mkdirs : ()Z
    //   1569: pop
    //   1570: aload_2
    //   1571: astore_1
    //   1572: aload #5
    //   1574: aload_3
    //   1575: invokevirtual renameTo : (Ljava/io/File;)Z
    //   1578: ifne -> 1598
    //   1581: aload_0
    //   1582: aload #5
    //   1584: aload_3
    //   1585: invokespecial copyFile : (Ljava/io/File;Ljava/io/File;)Z
    //   1588: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1591: astore_1
    //   1592: aload #5
    //   1594: invokevirtual delete : ()Z
    //   1597: pop
    //   1598: aload_1
    //   1599: invokevirtual booleanValue : ()Z
    //   1602: ifeq -> 1696
    //   1605: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1608: dup
    //   1609: aload_0
    //   1610: ldc_w '500 Internal Server Error'
    //   1613: ldc 'text/plain'
    //   1615: ldc_w 'NOTOK'
    //   1618: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1621: astore_1
    //   1622: aload_1
    //   1623: ldc 'Access-Control-Allow-Origin'
    //   1625: ldc '*'
    //   1627: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1630: aload_1
    //   1631: ldc 'Access-Control-Allow-Headers'
    //   1633: ldc 'origin, content-type'
    //   1635: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1638: aload_1
    //   1639: ldc 'Access-Control-Allow-Methods'
    //   1641: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1643: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1646: aload_1
    //   1647: ldc 'Allow'
    //   1649: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1651: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1654: aload_1
    //   1655: areturn
    //   1656: aload #5
    //   1658: invokevirtual delete : ()Z
    //   1661: pop
    //   1662: ldc 'AppInvHTTPD'
    //   1664: ldc_w 'Received content without a file name!'
    //   1667: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1670: pop
    //   1671: iconst_1
    //   1672: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1675: astore_1
    //   1676: goto -> 1598
    //   1679: ldc 'AppInvHTTPD'
    //   1681: ldc_w 'Received PUT without content.'
    //   1684: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   1687: pop
    //   1688: iconst_1
    //   1689: invokestatic valueOf : (Z)Ljava/lang/Boolean;
    //   1692: astore_1
    //   1693: goto -> 1598
    //   1696: new com/google/appinventor/components/runtime/util/NanoHTTPD$Response
    //   1699: dup
    //   1700: aload_0
    //   1701: ldc '200 OK'
    //   1703: ldc 'text/plain'
    //   1705: ldc 'OK'
    //   1707: invokespecial <init> : (Lcom/google/appinventor/components/runtime/util/NanoHTTPD;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   1710: astore_1
    //   1711: aload_1
    //   1712: ldc 'Access-Control-Allow-Origin'
    //   1714: ldc '*'
    //   1716: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1719: aload_1
    //   1720: ldc 'Access-Control-Allow-Headers'
    //   1722: ldc 'origin, content-type'
    //   1724: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1727: aload_1
    //   1728: ldc 'Access-Control-Allow-Methods'
    //   1730: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1732: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1735: aload_1
    //   1736: ldc 'Allow'
    //   1738: ldc 'POST,OPTIONS,GET,HEAD,PUT'
    //   1740: invokevirtual addHeader : (Ljava/lang/String;Ljava/lang/String;)V
    //   1743: aload_1
    //   1744: areturn
    //   1745: aload_0
    //   1746: aload_1
    //   1747: aload_3
    //   1748: aload_0
    //   1749: getfield rootDir : Ljava/io/File;
    //   1752: iconst_1
    //   1753: invokevirtual serveFile : (Ljava/lang/String;Ljava/util/Properties;Ljava/io/File;Z)Lcom/google/appinventor/components/runtime/util/NanoHTTPD$Response;
    //   1756: areturn
    // Exception table:
    //   from	to	target	type
    //   372	460	705	java/lang/Exception
    //   470	494	705	java/lang/Exception
    //   503	510	705	java/lang/Exception
    //   916	935	1049	java/lang/Throwable
    //   935	952	1049	java/lang/Throwable
    //   1037	1046	1049	java/lang/Throwable
    //   1161	1200	1358	android/content/pm/PackageManager$NameNotFoundException
    //   1200	1206	1358	android/content/pm/PackageManager$NameNotFoundException
    //   1216	1288	1358	android/content/pm/PackageManager$NameNotFoundException
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/AppInvHTTPD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */