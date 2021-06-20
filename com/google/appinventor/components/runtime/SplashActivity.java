package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebStorage;
import android.webkit.WebView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.appinventor.components.runtime.util.SdkLevel;

public final class SplashActivity extends AppInventorCompatActivity {
  Handler handler;
  
  WebView webview;
  
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    JavaInterface javaInterface = new JavaInterface((Context)this);
    this.handler = new Handler();
    this.webview = new WebView((Context)this);
    WebSettings webSettings = this.webview.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webSettings.setDatabaseEnabled(true);
    webSettings.setDomStorageEnabled(true);
    webSettings.setDatabasePath(getApplicationContext().getDir("database", 0).getPath());
    this.webview.setWebChromeClient(new WebChromeClient() {
          public void onExceededDatabaseQuota(String param1String1, String param1String2, long param1Long1, long param1Long2, long param1Long3, WebStorage.QuotaUpdater param1QuotaUpdater) {
            param1QuotaUpdater.updateQuota(5242880L);
          }
        });
    setContentView((View)this.webview);
    this.webview.addJavascriptInterface(javaInterface, "Android");
    this.webview.loadUrl("file:///android_asset/splash.html");
  }
  
  public void onRequestPermissionsResult(int paramInt, String[] paramArrayOfString, int[] paramArrayOfint) {
    for (paramInt = 0; paramInt < paramArrayOfString.length; paramInt++) {
      String str = paramArrayOfString[paramInt];
      int i = paramArrayOfint[paramInt];
      boolean bool = false;
      if (i == 0)
        bool = true; 
      this.webview.loadUrl("javascript:permresult('" + str + "'," + bool + ")");
    } 
  }
  
  public class JavaInterface {
    Context mContext;
    
    public JavaInterface(Context param1Context) {
      this.mContext = param1Context;
    }
    
    @JavascriptInterface
    public void askPermission(String param1String) {
      ActivityCompat.requestPermissions(SplashActivity.this, new String[] { param1String }, 1);
    }
    
    @JavascriptInterface
    public void finished() {
      SplashActivity.this.handler.post(new Runnable() {
            public void run() {
              SplashActivity.this.webview.destroy();
              SplashActivity.this.finish();
            }
          });
    }
    
    @JavascriptInterface
    public String getVersion() {
      try {
        null = this.mContext.getPackageName();
        return (this.mContext.getPackageManager().getPackageInfo(null, 0)).versionName;
      } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
        return "Unknown";
      } 
    }
    
    @JavascriptInterface
    public boolean hasPermission(String param1String) {
      return !(SdkLevel.getLevel() >= 23 && ContextCompat.checkSelfPermission(this.mContext, param1String) != 0);
    }
  }
  
  class null implements Runnable {
    public void run() {
      SplashActivity.this.webview.destroy();
      SplashActivity.this.finish();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/SplashActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */