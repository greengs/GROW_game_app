package com.google.appinventor.components.runtime.multidex;

import android.app.Application;
import android.content.Context;

public class MultiDexApplication extends Application {
  public static boolean installed = false;
  
  protected void attachBaseContext(Context paramContext) {
    super.attachBaseContext(paramContext);
    MultiDex.install((Context)this, true);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/multidex/MultiDexApplication.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */