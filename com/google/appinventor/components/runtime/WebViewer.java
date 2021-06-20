package com.google.appinventor.components.runtime;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.annotations.DesignerProperty;
import com.google.appinventor.components.annotations.PropertyCategory;
import com.google.appinventor.components.annotations.SimpleEvent;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.annotations.SimpleObject;
import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.UsesPermissions;
import com.google.appinventor.components.common.ComponentCategory;
import com.google.appinventor.components.runtime.util.EclairUtil;
import com.google.appinventor.components.runtime.util.FroyoWebViewClient;
import com.google.appinventor.components.runtime.util.HoneycombWebViewClient;
import com.google.appinventor.components.runtime.util.MediaUtil;
import com.google.appinventor.components.runtime.util.SdkLevel;

@DesignerComponent(category = ComponentCategory.USERINTERFACE, description = "Component for viewing Web pages.  The Home URL can be specified in the Designer or in the Blocks Editor.  The view can be set to follow links when they are tapped, and users can fill in Web forms. Warning: This is not a full browser.  For example, pressing the phone's hardware Back key will exit the app, rather than move back in the browser history.<p />You can use the WebViewer.WebViewString property to communicate between your app and Javascript code running in the Webviewer page. In the app, you get and set WebViewString.  In the WebViewer, you include Javascript that references the window.AppInventor object, using the methoods </em getWebViewString()</em> and <em>setWebViewString(text)</em>.  <p />For example, if the WebViewer opens to a page that contains the Javascript command <br /> <em>document.write(\"The answer is\" + window.AppInventor.getWebViewString());</em> <br />and if you set WebView.WebVewString to \"hello\", then the web page will show </br ><em>The answer is hello</em>.  <br />And if the Web page contains Javascript that executes the command <br /><em>window.AppInventor.setWebViewString(\"hello from Javascript\")</em>, <br />then the value of the WebViewString property will be <br /><em>hello from Javascript</em>. ", version = 10)
@SimpleObject
@UsesPermissions(permissionNames = "android.permission.INTERNET")
public final class WebViewer extends AndroidViewComponent {
  private boolean followLinks = true;
  
  private boolean havePermission = false;
  
  private String homeUrl;
  
  private boolean ignoreSslErrors = false;
  
  private boolean prompt = true;
  
  private final WebView webview;
  
  WebViewInterface wvInterface;
  
  public WebViewer(ComponentContainer paramComponentContainer) {
    super(paramComponentContainer);
    this.webview = new WebView((Context)paramComponentContainer.$context());
    resetWebViewClient();
    this.webview.getSettings().setJavaScriptEnabled(true);
    this.webview.setFocusable(true);
    this.wvInterface = new WebViewInterface();
    this.webview.addJavascriptInterface(this.wvInterface, "AppInventor");
    this.webview.getSettings().setBuiltInZoomControls(true);
    if (SdkLevel.getLevel() >= 5)
      EclairUtil.setupWebViewGeoLoc(this, this.webview, paramComponentContainer.$context()); 
    paramComponentContainer.$add(this);
    this.webview.setOnTouchListener(new View.OnTouchListener() {
          public boolean onTouch(View param1View, MotionEvent param1MotionEvent) {
            switch (param1MotionEvent.getAction()) {
              default:
                return false;
              case 0:
              case 1:
                break;
            } 
            if (!param1View.hasFocus())
              param1View.requestFocus(); 
          }
        });
    HomeUrl("");
    Width(-2);
    Height(-2);
  }
  
  private void loadUrl(final String caller, final String url) {
    if (!this.havePermission && MediaUtil.isExternalFileUrl((Context)this.container.$form(), url)) {
      this.container.$form().askPermission("android.permission.READ_EXTERNAL_STORAGE", new PermissionResultHandler() {
            public void HandlePermissionResponse(String param1String, boolean param1Boolean) {
              if (param1Boolean) {
                WebViewer.access$202(WebViewer.this, true);
                WebViewer.this.webview.loadUrl(url);
                return;
              } 
              WebViewer.this.container.$form().dispatchPermissionDeniedEvent(WebViewer.this, caller, "android.permission.READ_EXTERNAL_STORAGE");
            }
          });
      return;
    } 
    this.webview.loadUrl(url);
  }
  
  private void resetWebViewClient() {
    if (SdkLevel.getLevel() >= 11) {
      this.webview.setWebViewClient((WebViewClient)new HoneycombWebViewClient(this.followLinks, this.ignoreSslErrors, this.container.$form(), this));
      return;
    } 
    if (SdkLevel.getLevel() >= 8) {
      this.webview.setWebViewClient((WebViewClient)new FroyoWebViewClient(this.followLinks, this.ignoreSslErrors, this.container.$form(), this));
      return;
    } 
    this.webview.setWebViewClient(new WebViewerClient());
  }
  
  @SimpleEvent(description = "When a page is about to load this event is run.")
  public void BeforePageLoad(String paramString) {
    EventDispatcher.dispatchEvent(this, "BeforePageLoad", new Object[] { paramString });
  }
  
  @SimpleFunction(description = "Returns true if the WebViewer can go back in the history list.")
  public boolean CanGoBack() {
    return this.webview.canGoBack();
  }
  
  @SimpleFunction(description = "Returns true if the WebViewer can go forward in the history list.")
  public boolean CanGoForward() {
    return this.webview.canGoForward();
  }
  
  @SimpleFunction(description = "Clear WebView caches.")
  public void ClearCaches() {
    this.webview.clearCache(true);
  }
  
  @SimpleFunction(description = "Clear WebView cookies.")
  public void ClearCookies() {
    CookieManager cookieManager = CookieManager.getInstance();
    if (SdkLevel.getLevel() >= 21) {
      cookieManager.removeAllCookies(null);
      return;
    } 
    cookieManager.removeAllCookie();
  }
  
  @SimpleFunction(description = "Clear stored location permissions.")
  public void ClearLocations() {
    if (SdkLevel.getLevel() >= 5)
      EclairUtil.clearWebViewGeoLoc(); 
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Title of the page currently viewed")
  public String CurrentPageTitle() {
    return (this.webview.getTitle() == null) ? "" : this.webview.getTitle();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URL of the page currently viewed.   This could be different from the Home URL if new pages were visited by following links.")
  public String CurrentUrl() {
    return (this.webview.getUrl() == null) ? "" : this.webview.getUrl();
  }
  
  @SimpleEvent(description = "When an error occurs this event is run.")
  public void ErrorOccurred(int paramInt, String paramString1, String paramString2) {
    EventDispatcher.dispatchEvent(this, "ErrorOccurred", new Object[] { Integer.valueOf(paramInt), paramString1, paramString2 });
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty
  public void FollowLinks(boolean paramBoolean) {
    this.followLinks = paramBoolean;
    resetWebViewClient();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determines whether to follow links when they are tapped in the WebViewer.  If you follow links, you can use GoBack and GoForward to navigate the browser history. ")
  public boolean FollowLinks() {
    return this.followLinks;
  }
  
  @SimpleFunction(description = "Go back to the previous page in the history list.  Does nothing if there is no previous page.")
  public void GoBack() {
    if (this.webview.canGoBack())
      this.webview.goBack(); 
  }
  
  @SimpleFunction(description = "Go forward to the next page in the history list.   Does nothing if there is no next page.")
  public void GoForward() {
    if (this.webview.canGoForward())
      this.webview.goForward(); 
  }
  
  @SimpleFunction(description = "Loads the home URL page.  This happens automatically when the home URL is changed.")
  public void GoHome() {
    loadUrl("GoHome", this.homeUrl);
  }
  
  @SimpleFunction(description = "Load the page at the given URL.")
  public void GoToUrl(String paramString) {
    loadUrl("GoToUrl", paramString);
  }
  
  @SimpleProperty
  public void Height(int paramInt) {
    int i = paramInt;
    if (paramInt == -1)
      i = -2; 
    super.Height(i);
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "URL of the page the WebViewer should initially open to.  Setting this will load the page.")
  public String HomeUrl() {
    return this.homeUrl;
  }
  
  @DesignerProperty(defaultValue = "", editorType = "string")
  @SimpleProperty
  public void HomeUrl(String paramString) {
    this.homeUrl = paramString;
    this.webview.clearHistory();
    loadUrl("HomeUrl", this.homeUrl);
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty
  public void IgnoreSslErrors(boolean paramBoolean) {
    this.ignoreSslErrors = paramBoolean;
    resetWebViewClient();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Determine whether or not to ignore SSL errors. Set to true to ignore errors. Use this to accept self signed certificates from websites.")
  public boolean IgnoreSslErrors() {
    return this.ignoreSslErrors;
  }
  
  @SimpleEvent(description = "When a page is finished loading this event is run.")
  public void PageLoaded(String paramString) {
    EventDispatcher.dispatchEvent(this, "PageLoaded", new Object[] { paramString });
  }
  
  @DesignerProperty(defaultValue = "True", editorType = "boolean")
  @SimpleProperty(userVisible = true)
  public void PromptforPermission(boolean paramBoolean) {
    this.prompt = paramBoolean;
  }
  
  @SimpleProperty(description = "If True, then prompt the user of the WebView to give permission to access the geolocation API. If False, then assume permission is granted.")
  public boolean PromptforPermission() {
    return this.prompt;
  }
  
  @SimpleFunction(description = "Reload the current page.")
  public void Reload() {
    this.webview.reload();
  }
  
  @SimpleFunction(description = "Run JavaScript in the current page.")
  public void RunJavaScript(String paramString) {
    this.webview.loadUrl("javascript:(function(){" + paramString + "})()");
  }
  
  @SimpleFunction(description = "Stop loading a page.")
  public void StopLoading() {
    this.webview.stopLoading();
  }
  
  @DesignerProperty(defaultValue = "False", editorType = "boolean")
  @SimpleProperty(description = "Whether or not to give the application permission to use the Javascript geolocation API. This property is available only in the designer.", userVisible = false)
  public void UsesLocation(boolean paramBoolean) {}
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR, description = "Gets the WebView's String, which is viewable through Javascript in the WebView as the window.AppInventor object")
  public String WebViewString() {
    return this.wvInterface.getWebViewString();
  }
  
  @SimpleProperty(category = PropertyCategory.BEHAVIOR)
  public void WebViewString(String paramString) {
    this.wvInterface.setWebViewStringFromBlocks(paramString);
  }
  
  @SimpleEvent(description = "When the JavaScript calls AppInventor.setWebViewString this event is run.")
  public void WebViewStringChange(String paramString) {
    EventDispatcher.dispatchEvent(this, "WebViewStringChange", new Object[] { paramString });
  }
  
  @SimpleProperty
  public void Width(int paramInt) {
    int i = paramInt;
    if (paramInt == -1)
      i = -2; 
    super.Width(i);
  }
  
  public View getView() {
    return (View)this.webview;
  }
  
  public class WebViewInterface {
    String webViewString = " ";
    
    @JavascriptInterface
    public String getWebViewString() {
      return this.webViewString;
    }
    
    @JavascriptInterface
    public void setWebViewString(final String newString) {
      this.webViewString = newString;
      WebViewer.this.container.$form().runOnUiThread(new Runnable() {
            public void run() {
              WebViewer.this.WebViewStringChange(newString);
            }
          });
    }
    
    public void setWebViewStringFromBlocks(String param1String) {
      this.webViewString = param1String;
    }
  }
  
  class null implements Runnable {
    public void run() {
      WebViewer.this.WebViewStringChange(newString);
    }
  }
  
  private class WebViewerClient extends WebViewClient {
    private WebViewerClient() {}
    
    public void onPageFinished(WebView param1WebView, String param1String) {
      WebViewer.this.PageLoaded(param1String);
    }
    
    public void onPageStarted(WebView param1WebView, String param1String, Bitmap param1Bitmap) {
      WebViewer.this.BeforePageLoad(param1String);
    }
    
    public void onReceivedError(WebView param1WebView, final int errorCode, final String description, final String failingUrl) {
      WebViewer.this.container.$form().runOnUiThread(new Runnable() {
            public void run() {
              WebViewer.this.ErrorOccurred(errorCode, description, failingUrl);
            }
          });
    }
    
    public boolean shouldOverrideUrlLoading(WebView param1WebView, String param1String) {
      return !WebViewer.this.followLinks;
    }
  }
  
  class null implements Runnable {
    public void run() {
      WebViewer.this.ErrorOccurred(errorCode, description, failingUrl);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/WebViewer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */