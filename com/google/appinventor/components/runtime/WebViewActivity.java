package com.google.appinventor.components.runtime;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public final class WebViewActivity extends AppInventorCompatActivity {
  public void onCreate(Bundle paramBundle) {
    super.onCreate(paramBundle);
    WebView webView = new WebView((Context)this);
    webView.getSettings().setJavaScriptEnabled(true);
    webView.setWebViewClient(new WebViewClient() {
          public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
            Intent intent;
            Log.i("WebView", "Handling url " + param1String);
            Uri uri = Uri.parse(param1String);
            if (uri.getScheme().equals("appinventor")) {
              intent = new Intent();
              intent.setData(uri);
              WebViewActivity.this.setResult(-1, intent);
              WebViewActivity.this.finish();
              return true;
            } 
            intent.loadUrl(param1String);
            return true;
          }
        });
    setContentView((View)webView);
    Intent intent = getIntent();
    if (intent != null && intent.getData() != null) {
      Uri uri = intent.getData();
      String str1 = uri.getScheme();
      String str2 = uri.getHost();
      Log.i("WebView", "Got intent with URI: " + uri + ", scheme=" + str1 + ", host=" + str2);
      webView.loadUrl(uri.toString());
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/WebViewActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */