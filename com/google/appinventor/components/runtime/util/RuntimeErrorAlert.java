package com.google.appinventor.components.runtime.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public final class RuntimeErrorAlert {
  public static void alert(final Object context, String paramString1, String paramString2, String paramString3) {
    Log.i("RuntimeErrorAlert", "in alert");
    AlertDialog alertDialog = (new AlertDialog.Builder((Context)context)).create();
    alertDialog.setTitle(paramString2);
    alertDialog.setMessage(paramString1);
    alertDialog.setButton(paramString3, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface param1DialogInterface, int param1Int) {
            ((Activity)context).finish();
          }
        });
    if (paramString1 == null) {
      Log.e(RuntimeErrorAlert.class.getName(), "No error message available");
    } else {
      Log.e(RuntimeErrorAlert.class.getName(), paramString1);
    } 
    alertDialog.show();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/RuntimeErrorAlert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */