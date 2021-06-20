package com.google.appinventor.components.runtime.util;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.runtime.Component;
import com.google.appinventor.components.runtime.EventDispatcher;
import com.google.appinventor.components.runtime.Form;

public class FroyoWebViewClient<T extends Component> extends WebViewClient {
  private final T component;
  
  private final boolean followLinks;
  
  private final Form form;
  
  private final boolean ignoreErrors;
  
  public FroyoWebViewClient(boolean paramBoolean1, boolean paramBoolean2, Form paramForm, T paramT) {
    this.followLinks = paramBoolean1;
    this.ignoreErrors = paramBoolean2;
    this.form = paramForm;
    this.component = paramT;
  }
  
  public T getComponent() {
    return this.component;
  }
  
  public Form getForm() {
    return this.form;
  }
  
  public void onPageFinished(WebView paramWebView, String paramString) {
    EventDispatcher.dispatchEvent((Component)this.component, "PageLoaded", new Object[] { paramString });
  }
  
  public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap) {
    EventDispatcher.dispatchEvent((Component)this.component, "BeforePageLoad", new Object[] { paramString });
  }
  
  public void onReceivedError(WebView paramWebView, final int errorCode, final String description, final String failingUrl) {
    this.form.runOnUiThread(new Runnable() {
          public void run() {
            EventDispatcher.dispatchEvent((Component)FroyoWebViewClient.this.component, "ErrorOccurred", new Object[] { Integer.valueOf(this.val$errorCode), this.val$description, this.val$failingUrl });
          }
        });
  }
  
  public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError) {
    if (this.ignoreErrors) {
      paramSslErrorHandler.proceed();
      return;
    } 
    paramSslErrorHandler.cancel();
    this.form.dispatchErrorOccurredEvent((Component)this.component, "WebView", 2501, new Object[0]);
  }
  
  public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString) {
    return !this.followLinks;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/FroyoWebViewClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */