package com.google.appinventor.components.runtime.util;

import android.os.Build;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import androidx.annotation.RequiresApi;
import com.google.appinventor.components.runtime.Form;
import com.google.appinventor.components.runtime.WebViewer;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.HashMap;

@RequiresApi(api = 11)
public class HoneycombWebViewClient extends FroyoWebViewClient<WebViewer> {
  private static final String ASSET_PREFIX = "file:///appinventor_asset/";
  
  private static final String TAG = HoneycombWebViewClient.class.getSimpleName();
  
  public HoneycombWebViewClient(boolean paramBoolean1, boolean paramBoolean2, Form paramForm, WebViewer paramWebViewer) {
    super(paramBoolean1, paramBoolean2, paramForm, paramWebViewer);
  }
  
  protected WebResourceResponse handleAppRequest(String paramString) {
    if (paramString.startsWith("file:///appinventor_asset/")) {
      paramString = paramString.substring("file:///appinventor_asset/".length());
    } else {
      paramString = paramString.substring(paramString.indexOf("//localhost/") + 12);
    } 
    try {
      Log.i(TAG, "webviewer requested path = " + paramString);
      InputStream inputStream = getForm().openAsset(paramString);
      HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
      hashMap.put("Access-Control-Allow-Origin", "localhost");
      String str = URLConnection.getFileNameMap().getContentTypeFor(paramString);
      paramString = "utf-8";
      Log.i(TAG, "Mime type = " + str);
      if (!str.startsWith("text/"))
        paramString = null; 
      return (Build.VERSION.SDK_INT >= 21) ? new WebResourceResponse(str, paramString, 200, "OK", hashMap, inputStream) : new WebResourceResponse(str, paramString, inputStream);
    } catch (IOException iOException) {
      ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream("404 Not Found".getBytes());
      return (Build.VERSION.SDK_INT >= 21) ? new WebResourceResponse("text/plain", "utf-8", 404, "Not Found", null, byteArrayInputStream) : new WebResourceResponse("text/plain", "utf-8", byteArrayInputStream);
    } 
  }
  
  @RequiresApi(api = 21)
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, WebResourceRequest paramWebResourceRequest) {
    Log.d(TAG, "scheme = " + paramWebResourceRequest.getUrl().getScheme());
    return (paramWebResourceRequest.getUrl().getAuthority().equals("localhost") || paramWebResourceRequest.getUrl().toString().startsWith("file:///appinventor_asset/")) ? handleAppRequest(paramWebResourceRequest.getUrl().toString()) : super.shouldInterceptRequest(paramWebView, paramWebResourceRequest);
  }
  
  public WebResourceResponse shouldInterceptRequest(WebView paramWebView, String paramString) {
    return (paramString.startsWith("http://localhost/") || paramString.startsWith("file:///appinventor_asset/")) ? handleAppRequest(paramString) : super.shouldInterceptRequest(paramWebView, paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/HoneycombWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */