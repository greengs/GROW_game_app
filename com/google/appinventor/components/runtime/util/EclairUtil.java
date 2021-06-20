package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.webkit.GeolocationPermissions;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.EditText;
import com.google.appinventor.components.runtime.WebViewer;

public class EclairUtil {
  public static void clearWebViewGeoLoc() {
    GeolocationPermissions.getInstance().clearAll();
  }
  
  public static void disableSuggestions(EditText paramEditText) {
    paramEditText.setInputType(paramEditText.getInputType() | 0x80000);
  }
  
  public static String getInstallerPackageName(String paramString, Activity paramActivity) {
    return paramActivity.getPackageManager().getInstallerPackageName(paramString);
  }
  
  public static void overridePendingTransitions(Activity paramActivity, int paramInt1, int paramInt2) {
    paramActivity.overridePendingTransition(paramInt1, paramInt2);
  }
  
  public static void setupWebViewGeoLoc(final WebViewer caller, WebView paramWebView, final Activity activity) {
    paramWebView.getSettings().setGeolocationDatabasePath(activity.getFilesDir().getAbsolutePath());
    paramWebView.getSettings().setDatabaseEnabled(true);
    paramWebView.setWebChromeClient(new WebChromeClient() {
          public void onGeolocationPermissionsShowPrompt(final String theOrigin, final GeolocationPermissions.Callback theCallback) {
            if (!caller.PromptforPermission()) {
              theCallback.invoke(theOrigin, true, true);
              return;
            } 
            AlertDialog alertDialog = (new AlertDialog.Builder((Context)activity)).create();
            alertDialog.setTitle("Permission Request");
            String str = theOrigin;
            if (theOrigin.equals("file://"))
              str = "This Application"; 
            alertDialog.setMessage(str + " would like to access your location.");
            alertDialog.setButton(-1, "Allow", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    theCallback.invoke(theOrigin, true, true);
                  }
                });
            alertDialog.setButton(-2, "Refuse", new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface param2DialogInterface, int param2Int) {
                    theCallback.invoke(theOrigin, false, true);
                  }
                });
            alertDialog.show();
          }
        });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/EclairUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */