package com.google.appinventor.components.runtime;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.VisibleForTesting;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesLibraries;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.common.HtmlEntities;
import com.google.appinventor.components.runtime.collect.Lists;
import com.google.appinventor.components.runtime.collect.Maps;
import com.google.appinventor.components.runtime.errors.IllegalArgumentError;
import com.google.appinventor.components.runtime.repackaged.org.json.JSONException;
import com.google.appinventor.components.runtime.repackaged.org.json.XML;
import com.google.appinventor.components.runtime.util.AsynchUtil;
import com.google.appinventor.components.runtime.util.BulkPermissionRequest;
import com.google.appinventor.components.runtime.util.FileUtil;
import com.google.appinventor.components.runtime.util.GingerbreadUtil;
import com.google.appinventor.components.runtime.util.JsonUtil;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;
import com.google.appinventor.components.runtime.util.XmlParser;
import com.google.appinventor.components.runtime.util.YailDictionary;
import com.google.appinventor.components.runtime.util.YailList;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.json.JSONException;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

@DesignerComponent(category = ComponentCategory.CONNECTIVITY, description = "Non-visible component that provides functions for HTTP GET, POST, PUT, and DELETE requests.", iconName = "images/web.png", nonVisible = true, version = 7)
@SimpleObject
@UsesLibraries(libraries = "json.jar")
@UsesPermissions(permissionNames = "android.permission.INTERNET,android.permission.WRITE_EXTERNAL_STORAGE,android.permission.READ_EXTERNAL_STORAGE")
public class Web extends AndroidNonvisibleComponent implements Component {
  private static final String LOG_TAG = "Web";
  
  private static final Map<String, String> mimeTypeToExtension = Maps.newHashMap();
  
  private final Activity activity = null;
  
  private boolean allowCookies;
  
  private final CookieHandler cookieHandler;
  
  private boolean havePermission = false;
  
  private YailList requestHeaders = new YailList();
  
  private String responseFileName = "";
  
  private boolean saveResponse;
  
  private int timeout = 0;
  
  private String urlString = "";
  
  static {
    mimeTypeToExtension.put("application/pdf", "pdf");
    mimeTypeToExtension.put("application/zip", "zip");
    mimeTypeToExtension.put("audio/mpeg", "mpeg");
    mimeTypeToExtension.put("audio/mp3", "mp3");
    mimeTypeToExtension.put("audio/mp4", "mp4");
    mimeTypeToExtension.put("image/gif", "gif");
    mimeTypeToExtension.put("image/jpeg", "jpg");
    mimeTypeToExtension.put("image/png", "png");
    mimeTypeToExtension.put("image/tiff", "tiff");
    mimeTypeToExtension.put("text/plain", "txt");
    mimeTypeToExtension.put("text/html", "html");
    mimeTypeToExtension.put("text/xml", "xml");
  }
  
  protected Web() {
    super(null);
    this.cookieHandler = null;
  }
  
  public Web(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer.$form());
    if (SdkLevel.getLevel() >= 9) {
      CookieHandler cookieHandler = GingerbreadUtil.newCookieManager();
    } else {
      paramComponentContainer = null;
    } 
    this.cookieHandler = (CookieHandler)paramComponentContainer;
  }
  
  private CapturedProperties capturePropertyValues(String paramString) {
    try {
      return new CapturedProperties(this);
    } catch (MalformedURLException malformedURLException) {
      this.form.dispatchErrorOccurredEvent(this, paramString, 1109, new Object[] { this.urlString });
    } catch (InvalidRequestHeadersException invalidRequestHeadersException) {
      this.form.dispatchErrorOccurredEvent(this, paramString, invalidRequestHeadersException.errorNumber, new Object[] { Integer.valueOf(invalidRequestHeadersException.index) });
    } 
    return null;
  }
  
  private File createFile(String paramString1, String paramString2) throws IOException, FileUtil.FileException {
    if (!TextUtils.isEmpty(paramString1))
      return FileUtil.getExternalFile(this.form, paramString1); 
    int i = paramString2.indexOf(';');
    paramString1 = paramString2;
    if (i != -1)
      paramString1 = paramString2.substring(0, i); 
    paramString2 = mimeTypeToExtension.get(paramString1);
    paramString1 = paramString2;
    if (paramString2 == null)
      paramString1 = "tmp"; 
    return FileUtil.getDownloadFile(this.form, paramString1);
  }
  
  @Deprecated
  @VisibleForTesting
  static Object decodeJsonText(String paramString) throws IllegalArgumentException {
    return decodeJsonText(paramString, false);
  }
  
  @VisibleForTesting
  static Object decodeJsonText(String paramString, boolean paramBoolean) throws IllegalArgumentException {
    try {
      return JsonUtil.getObjectFromJson(paramString, paramBoolean);
    } catch (JSONException jSONException) {
      throw new IllegalArgumentException("jsonText is not a legal JSON value");
    } 
  }
  
  private static InputStream getConnectionStream(HttpURLConnection paramHttpURLConnection) throws SocketTimeoutException {
    try {
      return paramHttpURLConnection.getInputStream();
    } catch (SocketTimeoutException socketTimeoutException) {
      throw socketTimeoutException;
    } catch (IOException iOException) {
      return socketTimeoutException.getErrorStream();
    } 
  }
  
  private static String getResponseContent(HttpURLConnection paramHttpURLConnection) throws IOException {
    String str2 = paramHttpURLConnection.getContentEncoding();
    String str1 = str2;
    if (str2 == null)
      str1 = "UTF-8"; 
    InputStreamReader inputStreamReader = new InputStreamReader(getConnectionStream(paramHttpURLConnection), str1);
    try {
      StringBuilder stringBuilder;
      int i = paramHttpURLConnection.getContentLength();
      if (i != -1) {
        stringBuilder = new StringBuilder(i);
      } else {
        stringBuilder = new StringBuilder();
      } 
      char[] arrayOfChar = new char[1024];
      while (true) {
        i = inputStreamReader.read(arrayOfChar);
        if (i != -1) {
          stringBuilder.append(arrayOfChar, 0, i);
          continue;
        } 
        return stringBuilder.toString();
      } 
    } finally {
      inputStreamReader.close();
    } 
  }
  
  private static String getResponseType(HttpURLConnection paramHttpURLConnection) {
    String str = paramHttpURLConnection.getContentType();
    return (str != null) ? str : "";
  }
  
  private static HttpURLConnection openConnection(CapturedProperties paramCapturedProperties, String paramString) throws IOException, ClassCastException, ProtocolException {
    HttpURLConnection httpURLConnection = (HttpURLConnection)paramCapturedProperties.url.openConnection();
    httpURLConnection.setConnectTimeout(paramCapturedProperties.timeout);
    httpURLConnection.setReadTimeout(paramCapturedProperties.timeout);
    if (paramString.equals("PUT") || paramString.equals("DELETE"))
      httpURLConnection.setRequestMethod(paramString); 
    for (Map.Entry<String, List<String>> entry : paramCapturedProperties.requestHeaders.entrySet()) {
      String str = (String)entry.getKey();
      Iterator<String> iterator = ((List)entry.getValue()).iterator();
      while (iterator.hasNext())
        httpURLConnection.addRequestProperty(str, iterator.next()); 
    } 
    if (paramCapturedProperties.cookies != null)
      for (Map.Entry<String, List<String>> entry : paramCapturedProperties.cookies.entrySet()) {
        paramString = (String)entry.getKey();
        Iterator<String> iterator = ((List)entry.getValue()).iterator();
        while (iterator.hasNext())
          httpURLConnection.addRequestProperty(paramString, iterator.next()); 
      }  
    return httpURLConnection;
  }
  
  private void performRequest(CapturedProperties paramCapturedProperties, byte[] paramArrayOfbyte, String paramString1, String paramString2, String paramString3) {
    // Byte code:
    //   0: aload_0
    //   1: getfield saveResponse : Z
    //   4: istore #7
    //   6: aload_0
    //   7: getfield havePermission : Z
    //   10: ifne -> 67
    //   13: iconst_1
    //   14: istore #6
    //   16: iload #6
    //   18: iload #7
    //   20: iand
    //   21: ifeq -> 73
    //   24: aload_0
    //   25: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   28: new com/google/appinventor/components/runtime/Web$6
    //   31: dup
    //   32: aload_0
    //   33: aload_0
    //   34: ldc 'Web'
    //   36: iconst_2
    //   37: anewarray java/lang/String
    //   40: dup
    //   41: iconst_0
    //   42: ldc_w 'android.permission.READ_EXTERNAL_STORAGE'
    //   45: aastore
    //   46: dup
    //   47: iconst_1
    //   48: ldc_w 'android.permission.WRITE_EXTERNAL_STORAGE'
    //   51: aastore
    //   52: aload_0
    //   53: aload_1
    //   54: aload_2
    //   55: aload_3
    //   56: aload #4
    //   58: aload #5
    //   60: invokespecial <init> : (Lcom/google/appinventor/components/runtime/Web;Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;[Ljava/lang/String;Lcom/google/appinventor/components/runtime/Web;Lcom/google/appinventor/components/runtime/Web$CapturedProperties;[BLjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    //   63: invokevirtual askPermission : (Lcom/google/appinventor/components/runtime/util/BulkPermissionRequest;)V
    //   66: return
    //   67: iconst_0
    //   68: istore #6
    //   70: goto -> 16
    //   73: aload_1
    //   74: aload #4
    //   76: invokestatic openConnection : (Lcom/google/appinventor/components/runtime/Web$CapturedProperties;Ljava/lang/String;)Ljava/net/HttpURLConnection;
    //   79: astore #4
    //   81: aload #4
    //   83: ifnull -> 66
    //   86: aload_2
    //   87: ifnull -> 178
    //   90: aload #4
    //   92: aload_2
    //   93: invokestatic writeRequestData : (Ljava/net/HttpURLConnection;[B)V
    //   96: aload #4
    //   98: invokevirtual getResponseCode : ()I
    //   101: istore #6
    //   103: aload #4
    //   105: invokestatic getResponseType : (Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   108: astore #8
    //   110: aload_0
    //   111: aload #4
    //   113: invokespecial processResponseCookies : (Ljava/net/HttpURLConnection;)V
    //   116: aload_0
    //   117: getfield saveResponse : Z
    //   120: ifeq -> 248
    //   123: aload_0
    //   124: aload #4
    //   126: aload_1
    //   127: getfield responseFileName : Ljava/lang/String;
    //   130: aload #8
    //   132: invokespecial saveResponseContent : (Ljava/net/HttpURLConnection;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    //   135: astore #9
    //   137: aload_0
    //   138: getfield activity : Landroid/app/Activity;
    //   141: new com/google/appinventor/components/runtime/Web$7
    //   144: dup
    //   145: aload_0
    //   146: aload_1
    //   147: iload #6
    //   149: aload #8
    //   151: aload #9
    //   153: invokespecial <init> : (Lcom/google/appinventor/components/runtime/Web;Lcom/google/appinventor/components/runtime/Web$CapturedProperties;ILjava/lang/String;Ljava/lang/String;)V
    //   156: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
    //   159: aload #4
    //   161: invokevirtual disconnect : ()V
    //   164: return
    //   165: astore_1
    //   166: aload_0
    //   167: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   170: aload_0
    //   171: aload #5
    //   173: aload_1
    //   174: invokevirtual dispatchPermissionDeniedEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;Lcom/google/appinventor/components/runtime/errors/PermissionException;)V
    //   177: return
    //   178: aload_3
    //   179: ifnull -> 96
    //   182: aload_0
    //   183: aload #4
    //   185: aload_3
    //   186: invokespecial writeRequestFile : (Ljava/net/HttpURLConnection;Ljava/lang/String;)V
    //   189: goto -> 96
    //   192: astore #8
    //   194: aload_0
    //   195: getfield activity : Landroid/app/Activity;
    //   198: new com/google/appinventor/components/runtime/Web$9
    //   201: dup
    //   202: aload_0
    //   203: aload_1
    //   204: invokespecial <init> : (Lcom/google/appinventor/components/runtime/Web;Lcom/google/appinventor/components/runtime/Web$CapturedProperties;)V
    //   207: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
    //   210: new com/google/appinventor/components/runtime/errors/RequestTimeoutException
    //   213: dup
    //   214: invokespecial <init> : ()V
    //   217: athrow
    //   218: astore #8
    //   220: aload #4
    //   222: invokevirtual disconnect : ()V
    //   225: aload #8
    //   227: athrow
    //   228: astore_1
    //   229: aload_0
    //   230: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   233: aload_0
    //   234: aload #5
    //   236: aload_1
    //   237: invokevirtual getErrorMessageNumber : ()I
    //   240: iconst_0
    //   241: anewarray java/lang/Object
    //   244: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   247: return
    //   248: aload #4
    //   250: invokestatic getResponseContent : (Ljava/net/HttpURLConnection;)Ljava/lang/String;
    //   253: astore #9
    //   255: aload_0
    //   256: getfield activity : Landroid/app/Activity;
    //   259: new com/google/appinventor/components/runtime/Web$8
    //   262: dup
    //   263: aload_0
    //   264: aload_1
    //   265: iload #6
    //   267: aload #8
    //   269: aload #9
    //   271: invokespecial <init> : (Lcom/google/appinventor/components/runtime/Web;Lcom/google/appinventor/components/runtime/Web$CapturedProperties;ILjava/lang/String;Ljava/lang/String;)V
    //   274: invokevirtual runOnUiThread : (Ljava/lang/Runnable;)V
    //   277: goto -> 159
    //   280: astore_2
    //   281: aload_0
    //   282: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   285: aload_0
    //   286: aload #5
    //   288: sipush #1117
    //   291: iconst_1
    //   292: anewarray java/lang/Object
    //   295: dup
    //   296: iconst_0
    //   297: aload_1
    //   298: getfield urlString : Ljava/lang/String;
    //   301: aastore
    //   302: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   305: return
    //   306: astore #4
    //   308: aload #5
    //   310: ldc_w 'Get'
    //   313: invokevirtual equals : (Ljava/lang/Object;)Z
    //   316: ifeq -> 355
    //   319: sipush #1101
    //   322: istore #6
    //   324: iconst_1
    //   325: anewarray java/lang/String
    //   328: astore_2
    //   329: aload_2
    //   330: iconst_0
    //   331: aload_1
    //   332: getfield urlString : Ljava/lang/String;
    //   335: aastore
    //   336: aload_2
    //   337: astore_1
    //   338: aload_0
    //   339: getfield form : Lcom/google/appinventor/components/runtime/Form;
    //   342: aload_0
    //   343: aload #5
    //   345: iload #6
    //   347: aload_1
    //   348: checkcast [Ljava/lang/Object;
    //   351: invokevirtual dispatchErrorOccurredEvent : (Lcom/google/appinventor/components/runtime/Component;Ljava/lang/String;I[Ljava/lang/Object;)V
    //   354: return
    //   355: aload #5
    //   357: ldc_w 'Delete'
    //   360: invokevirtual equals : (Ljava/lang/Object;)Z
    //   363: ifeq -> 388
    //   366: sipush #1114
    //   369: istore #6
    //   371: iconst_1
    //   372: anewarray java/lang/String
    //   375: astore_2
    //   376: aload_2
    //   377: iconst_0
    //   378: aload_1
    //   379: getfield urlString : Ljava/lang/String;
    //   382: aastore
    //   383: aload_2
    //   384: astore_1
    //   385: goto -> 338
    //   388: aload #5
    //   390: ldc_w 'PostFile'
    //   393: invokevirtual equals : (Ljava/lang/Object;)Z
    //   396: ifne -> 410
    //   399: aload #5
    //   401: ldc_w 'PutFile'
    //   404: invokevirtual equals : (Ljava/lang/Object;)Z
    //   407: ifeq -> 436
    //   410: sipush #1104
    //   413: istore #6
    //   415: iconst_2
    //   416: anewarray java/lang/String
    //   419: astore_2
    //   420: aload_2
    //   421: iconst_0
    //   422: aload_3
    //   423: aastore
    //   424: aload_2
    //   425: iconst_1
    //   426: aload_1
    //   427: getfield urlString : Ljava/lang/String;
    //   430: aastore
    //   431: aload_2
    //   432: astore_1
    //   433: goto -> 338
    //   436: sipush #1103
    //   439: istore #6
    //   441: ldc ''
    //   443: astore #4
    //   445: aload #4
    //   447: astore_3
    //   448: aload_2
    //   449: ifnull -> 464
    //   452: new java/lang/String
    //   455: dup
    //   456: aload_2
    //   457: ldc_w 'UTF-8'
    //   460: invokespecial <init> : ([BLjava/lang/String;)V
    //   463: astore_3
    //   464: iconst_2
    //   465: anewarray java/lang/String
    //   468: astore_2
    //   469: aload_2
    //   470: iconst_0
    //   471: aload_3
    //   472: aastore
    //   473: aload_2
    //   474: iconst_1
    //   475: aload_1
    //   476: getfield urlString : Ljava/lang/String;
    //   479: aastore
    //   480: aload_2
    //   481: astore_1
    //   482: goto -> 338
    //   485: astore_2
    //   486: ldc 'Web'
    //   488: ldc_w 'UTF-8 is the default charset for Android but not available???'
    //   491: invokestatic e : (Ljava/lang/String;Ljava/lang/String;)I
    //   494: pop
    //   495: aload #4
    //   497: astore_3
    //   498: goto -> 464
    // Exception table:
    //   from	to	target	type
    //   73	81	165	com/google/appinventor/components/runtime/errors/PermissionException
    //   73	81	228	com/google/appinventor/components/runtime/util/FileUtil$FileException
    //   73	81	280	com/google/appinventor/components/runtime/errors/RequestTimeoutException
    //   73	81	306	java/lang/Exception
    //   90	96	192	java/net/SocketTimeoutException
    //   90	96	218	finally
    //   96	159	192	java/net/SocketTimeoutException
    //   96	159	218	finally
    //   159	164	165	com/google/appinventor/components/runtime/errors/PermissionException
    //   159	164	228	com/google/appinventor/components/runtime/util/FileUtil$FileException
    //   159	164	280	com/google/appinventor/components/runtime/errors/RequestTimeoutException
    //   159	164	306	java/lang/Exception
    //   182	189	192	java/net/SocketTimeoutException
    //   182	189	218	finally
    //   194	218	218	finally
    //   220	228	165	com/google/appinventor/components/runtime/errors/PermissionException
    //   220	228	228	com/google/appinventor/components/runtime/util/FileUtil$FileException
    //   220	228	280	com/google/appinventor/components/runtime/errors/RequestTimeoutException
    //   220	228	306	java/lang/Exception
    //   248	277	192	java/net/SocketTimeoutException
    //   248	277	218	finally
    //   452	464	485	java/io/UnsupportedEncodingException
  }
  
  private static Map<String, List<String>> processRequestHeaders(YailList paramYailList) throws InvalidRequestHeadersException {
    HashMap<Object, ArrayList<String>> hashMap = Maps.newHashMap();
    int i = 0;
    while (i < paramYailList.size()) {
      Object object = paramYailList.getObject(i);
      if (object instanceof YailList) {
        YailList yailList = (YailList)object;
        if (yailList.size() == 2) {
          object = yailList.getObject(0).toString();
          Object object1 = yailList.getObject(1);
          ArrayList<String> arrayList = Lists.newArrayList();
          if (object1 instanceof YailList) {
            object1 = object1;
            for (int j = 0; j < object1.size(); j++)
              arrayList.add(object1.getObject(j).toString()); 
          } else {
            arrayList.add(object1.toString());
          } 
          hashMap.put(object, arrayList);
          i++;
          continue;
        } 
        throw new InvalidRequestHeadersException(1111, i + 1);
      } 
      throw new InvalidRequestHeadersException(1110, i + 1);
    } 
    return (Map)hashMap;
  }
  
  private void processResponseCookies(HttpURLConnection paramHttpURLConnection) {
    if (this.allowCookies && this.cookieHandler != null)
      try {
        Map<String, List<String>> map = paramHttpURLConnection.getHeaderFields();
        this.cookieHandler.put(paramHttpURLConnection.getURL().toURI(), map);
        return;
      } catch (URISyntaxException uRISyntaxException) {
        return;
      } catch (IOException iOException) {
        return;
      }  
  }
  
  private void requestTextImpl(final String text, final String encoding, final String functionName, final String httpVerb) {
    final CapturedProperties webProps = capturePropertyValues(functionName);
    if (capturedProperties == null)
      return; 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            try {
              byte[] arrayOfByte;
              if (encoding == null || encoding.length() == 0) {
                arrayOfByte = text.getBytes("UTF-8");
              } else {
                arrayOfByte = text.getBytes(encoding);
              } 
              Web.this.performRequest(webProps, arrayOfByte, null, httpVerb, functionName);
              return;
            } catch (UnsupportedEncodingException unsupportedEncodingException) {
              Web.this.form.dispatchErrorOccurredEvent(Web.this, functionName, 1102, new Object[] { this.val$encoding });
              return;
            } 
          }
        });
  }
  
  private String saveResponseContent(HttpURLConnection paramHttpURLConnection, String paramString1, String paramString2) throws IOException {
    null = createFile(paramString1, paramString2);
    BufferedInputStream bufferedInputStream = new BufferedInputStream(getConnectionStream(paramHttpURLConnection), 4096);
    try {
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(null), 4096);
    } finally {
      bufferedInputStream.close();
    } 
  }
  
  private static void writeRequestData(HttpURLConnection paramHttpURLConnection, byte[] paramArrayOfbyte) throws IOException {
    paramHttpURLConnection.setDoOutput(true);
    paramHttpURLConnection.setFixedLengthStreamingMode(paramArrayOfbyte.length);
    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(paramHttpURLConnection.getOutputStream());
    try {
      bufferedOutputStream.write(paramArrayOfbyte, 0, paramArrayOfbyte.length);
      bufferedOutputStream.flush();
      return;
    } finally {
      bufferedOutputStream.close();
    } 
  }
  
  private void writeRequestFile(HttpURLConnection paramHttpURLConnection, String paramString) throws IOException {
    BufferedInputStream bufferedInputStream = new BufferedInputStream(MediaUtil.openMedia(this.form, paramString));
    try {
      paramHttpURLConnection.setDoOutput(true);
      paramHttpURLConnection.setChunkedStreamingMode(0);
      BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(paramHttpURLConnection.getOutputStream());
    } finally {
      bufferedInputStream.close();
    } 
  }
  
  @DesignerProperty(defaultValue = "false", editorType = "boolean")
  @SimpleProperty
  public void AllowCookies(boolean paramBoolean) {
    this.allowCookies = paramBoolean;
    if (paramBoolean && this.cookieHandler == null)
      this.form.dispatchErrorOccurredEvent(this, "AllowCookies", 4, new Object[0]); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the cookies from a response should be saved and used in subsequent requests. Cookies are only supported on Android version 2.3 or greater.")
  public boolean AllowCookies() {
    return this.allowCookies;
  }
  
  @SimpleFunction
  public String BuildRequestData(YailList paramYailList) {
    try {
      return buildRequestData(paramYailList);
    } catch (BuildRequestDataException buildRequestDataException) {
      this.form.dispatchErrorOccurredEvent(this, "BuildRequestData", buildRequestDataException.errorNumber, new Object[] { Integer.valueOf(buildRequestDataException.index) });
      return "";
    } 
  }
  
  @SimpleFunction(description = "Clears all cookies for this Web component.")
  public void ClearCookies() {
    if (this.cookieHandler != null) {
      GingerbreadUtil.clearCookies(this.cookieHandler);
      return;
    } 
    this.form.dispatchErrorOccurredEvent(this, "ClearCookies", 4, new Object[0]);
  }
  
  @SimpleFunction
  public void Delete() {
    final CapturedProperties webProps = capturePropertyValues("Delete");
    if (capturedProperties == null)
      return; 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Web.this.performRequest(webProps, null, null, "DELETE", "Delete");
          }
        });
  }
  
  @SimpleFunction
  public void Get() {
    final CapturedProperties webProps = capturePropertyValues("Get");
    if (capturedProperties == null)
      return; 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Web.this.performRequest(webProps, null, null, "GET", "Get");
          }
        });
  }
  
  @SimpleEvent
  public void GotFile(String paramString1, int paramInt, String paramString2, String paramString3) {
    EventDispatcher.dispatchEvent(this, "GotFile", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2, paramString3 });
  }
  
  @SimpleEvent
  public void GotText(String paramString1, int paramInt, String paramString2, String paramString3) {
    EventDispatcher.dispatchEvent(this, "GotText", new Object[] { paramString1, Integer.valueOf(paramInt), paramString2, paramString3 });
  }
  
  @SimpleFunction(description = "Decodes the given HTML text value. HTML character entities such as &amp;amp;, &amp;lt;, &amp;gt;, &amp;apos;, and &amp;quot; are changed to &amp;, &lt;, &gt;, &#39;, and &quot;. Entities such as &amp;#xhhhh, and &amp;#nnnn are changed to the appropriate characters.")
  public String HtmlTextDecode(String paramString) {
    try {
      return HtmlEntities.decodeHtmlText(paramString);
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "HtmlTextDecode", 1106, new Object[] { paramString });
      return "";
    } 
  }
  
  @SimpleFunction
  public String JsonObjectEncode(Object paramObject) {
    try {
      return JsonUtil.encodeJsonObject(paramObject);
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "JsonObjectEncode", 1118, new Object[] { paramObject });
      return "";
    } 
  }
  
  @SimpleFunction
  public Object JsonTextDecode(String paramString) {
    try {
      return decodeJsonText(paramString, false);
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "JsonTextDecode", 1105, new Object[] { paramString });
      return "";
    } 
  }
  
  @SimpleFunction
  public Object JsonTextDecodeWithDictionaries(String paramString) {
    try {
      return decodeJsonText(paramString, true);
    } catch (IllegalArgumentException illegalArgumentException) {
      this.form.dispatchErrorOccurredEvent(this, "JsonTextDecodeWithDictionaries", 1105, new Object[] { paramString });
      return "";
    } 
  }
  
  @SimpleFunction(description = "Performs an HTTP POST request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostFile(final String path) {
    final CapturedProperties webProps = capturePropertyValues("PostFile");
    if (capturedProperties == null)
      return; 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Web.this.performRequest(webProps, null, path, "POST", "PostFile");
          }
        });
  }
  
  @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostText(String paramString) {
    requestTextImpl(paramString, "UTF-8", "PostText", "POST");
  }
  
  @SimpleFunction(description = "Performs an HTTP POST request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PostTextWithEncoding(String paramString1, String paramString2) {
    requestTextImpl(paramString1, paramString2, "PostTextWithEncoding", "POST");
  }
  
  @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and data from the specified file.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutFile(final String path) {
    final CapturedProperties webProps = capturePropertyValues("PutFile");
    if (capturedProperties == null)
      return; 
    AsynchUtil.runAsynchronously(new Runnable() {
          public void run() {
            Web.this.performRequest(webProps, null, path, "PUT", "PutFile");
          }
        });
  }
  
  @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using UTF-8 encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The responseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutText(String paramString) {
    requestTextImpl(paramString, "UTF-8", "PutText", "PUT");
  }
  
  @SimpleFunction(description = "Performs an HTTP PUT request using the Url property and the specified text.<br>The characters of the text are encoded using the given encoding.<br>If the SaveResponse property is true, the response will be saved in a file and the GotFile event will be triggered. The ResponseFileName property can be used to specify the name of the file.<br>If the SaveResponse property is false, the GotText event will be triggered.")
  public void PutTextWithEncoding(String paramString1, String paramString2) {
    requestTextImpl(paramString1, paramString2, "PutTextWithEncoding", "PUT");
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The request headers, as a list of two-element sublists. The first element of each sublist represents the request header field name. The second element of each sublist represents the request header field values, either a single value or a list containing multiple values.")
  public YailList RequestHeaders() {
    return this.requestHeaders;
  }
  
  @SimpleProperty
  public void RequestHeaders(YailList paramYailList) {
    try {
      processRequestHeaders(paramYailList);
      this.requestHeaders = paramYailList;
      return;
    } catch (InvalidRequestHeadersException invalidRequestHeadersException) {
      this.form.dispatchErrorOccurredEvent(this, "RequestHeaders", invalidRequestHeadersException.errorNumber, new Object[] { Integer.valueOf(invalidRequestHeadersException.index) });
      return;
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The name of the file where the response should be saved. If SaveResponse is true and ResponseFileName is empty, then a new file name will be generated.")
  public String ResponseFileName() {
    return this.responseFileName;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void ResponseFileName(String paramString) {
    this.responseFileName = paramString;
  }
  
  @DesignerProperty(defaultValue = "false", editorType = "boolean")
  @SimpleProperty
  public void SaveResponse(boolean paramBoolean) {
    this.saveResponse = paramBoolean;
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Whether the response should be saved in a file.")
  public boolean SaveResponse() {
    return this.saveResponse;
  }
  
  @SimpleEvent
  public void TimedOut(String paramString) {
    EventDispatcher.dispatchEvent(this, "TimedOut", new Object[] { paramString });
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The number of milliseconds that a web request will wait for a response before giving up. If set to 0, then there is no time limit on how long the request will wait.")
  public int Timeout() {
    return this.timeout;
  }
  
  @DesignerProperty(defaultValue = "0", editorType = "non_negative_integer")
  @SimpleProperty
  public void Timeout(int paramInt) {
    if (paramInt < 0)
      throw new IllegalArgumentError("Web Timeout must be a non-negative integer."); 
    this.timeout = paramInt;
  }
  
  @SimpleFunction
  public String UriDecode(String paramString) {
    try {
      return URLDecoder.decode(paramString, "UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.e("Web", "UTF-8 is unsupported?", unsupportedEncodingException);
      return "";
    } 
  }
  
  @SimpleFunction
  public String UriEncode(String paramString) {
    try {
      return URLEncoder.encode(paramString, "UTF-8");
    } catch (UnsupportedEncodingException unsupportedEncodingException) {
      Log.e("Web", "UTF-8 is unsupported?", unsupportedEncodingException);
      return "";
    } 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "The URL for the web request.")
  public String Url() {
    return this.urlString;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void Url(String paramString) {
    this.urlString = paramString;
  }
  
  @SimpleFunction(description = "Decodes the given XML string to produce a dictionary structure. See the App Inventor documentation on \"Other topics, notes, and details\" for information.")
  public Object XMLTextDecode(String paramString) {
    try {
      return JsonTextDecode(XML.toJSONObject(paramString).toString());
    } catch (JSONException jSONException) {
      Log.e("Web", jSONException.getMessage());
      this.form.dispatchErrorOccurredEvent(this, "XMLTextDecode", 1105, new Object[] { jSONException.getMessage() });
      return YailList.makeEmptyList();
    } 
  }
  
  @SimpleFunction(description = "Decodes the given XML into a set of nested dictionaries that capture the structure and data contained in the XML. See the help for more details.")
  public Object XMLTextDecodeAsDictionary(String paramString) {
    try {
      XmlParser xmlParser = new XmlParser();
      SAXParser sAXParser = SAXParserFactory.newInstance().newSAXParser();
      InputSource inputSource = new InputSource(new StringReader(paramString));
      inputSource.setEncoding("UTF-8");
      sAXParser.parse(inputSource, (DefaultHandler)xmlParser);
      return xmlParser.getRoot();
    } catch (Exception exception) {
      Log.e("Web", exception.getMessage());
      this.form.dispatchErrorOccurredEvent(this, "XMLTextDecodeAsDictionary", 1105, new Object[] { exception.getMessage() });
      return new YailDictionary();
    } 
  }
  
  String buildRequestData(YailList paramYailList) throws BuildRequestDataException {
    StringBuilder stringBuilder = new StringBuilder();
    String str = "";
    int i = 0;
    while (i < paramYailList.size()) {
      Object object = paramYailList.getObject(i);
      if (object instanceof YailList) {
        YailList yailList = (YailList)object;
        if (yailList.size() == 2) {
          object = yailList.getObject(0).toString();
          String str1 = yailList.getObject(1).toString();
          stringBuilder.append(str).append(UriEncode((String)object)).append('=').append(UriEncode(str1));
          str = "&";
          i++;
          continue;
        } 
        throw new BuildRequestDataException(1113, i + 1);
      } 
      throw new BuildRequestDataException(1112, i + 1);
    } 
    return stringBuilder.toString();
  }
  
  static class BuildRequestDataException extends Exception {
    final int errorNumber;
    
    final int index;
    
    BuildRequestDataException(int param1Int1, int param1Int2) {
      this.errorNumber = param1Int1;
      this.index = param1Int2;
    }
  }
  
  private static class CapturedProperties {
    final boolean allowCookies;
    
    final Map<String, List<String>> cookies;
    
    final Map<String, List<String>> requestHeaders;
    
    final String responseFileName;
    
    final boolean saveResponse;
    
    final int timeout;
    
    final URL url;
    
    final String urlString;
    
    CapturedProperties(Web param1Web) throws MalformedURLException, Web.InvalidRequestHeadersException {
      this.urlString = param1Web.urlString;
      this.url = new URL(this.urlString);
      this.allowCookies = param1Web.allowCookies;
      this.saveResponse = param1Web.saveResponse;
      this.responseFileName = param1Web.responseFileName;
      this.timeout = param1Web.timeout;
      this.requestHeaders = Web.processRequestHeaders(param1Web.requestHeaders);
      Map<String, List<String>> map2 = null;
      Map<String, List<String>> map1 = map2;
      if (this.allowCookies) {
        map1 = map2;
        if (param1Web.cookieHandler != null)
          try {
            map1 = param1Web.cookieHandler.get(this.url.toURI(), this.requestHeaders);
          } catch (URISyntaxException uRISyntaxException) {
            map1 = map2;
          } catch (IOException iOException) {
            map1 = map2;
          }  
      } 
      this.cookies = map1;
    }
  }
  
  private static class InvalidRequestHeadersException extends Exception {
    final int errorNumber;
    
    final int index;
    
    InvalidRequestHeadersException(int param1Int1, int param1Int2) {
      this.errorNumber = param1Int1;
      this.index = param1Int2;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/Web.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */