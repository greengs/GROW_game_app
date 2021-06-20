package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import java.io.File;

public class QUtil {
  public static File getExternalStorageDir(Context paramContext) {
    return getExternalStorageDir(paramContext, false);
  }
  
  public static File getExternalStorageDir(Context paramContext, boolean paramBoolean) {
    return getExternalStorageDir(paramContext, paramBoolean, false);
  }
  
  public static File getExternalStorageDir(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {
    return (Build.VERSION.SDK_INT < 8) ? Environment.getExternalStorageDirectory() : (((!paramBoolean2 && Build.VERSION.SDK_INT >= 29) || paramBoolean1) ? paramContext.getExternalFilesDir(null) : Environment.getExternalStorageDirectory());
  }
  
  public static String getExternalStoragePath(Context paramContext) {
    return getExternalStoragePath(paramContext, false, false);
  }
  
  public static String getExternalStoragePath(Context paramContext, boolean paramBoolean) {
    return getExternalStoragePath(paramContext, paramBoolean, false);
  }
  
  public static String getExternalStoragePath(Context paramContext, boolean paramBoolean1, boolean paramBoolean2) {
    return getExternalStorageDir(paramContext, paramBoolean1, paramBoolean2).getAbsolutePath();
  }
  
  public static String getReplAssetPath(Context paramContext) {
    return getReplAssetPath(paramContext, false);
  }
  
  public static String getReplAssetPath(Context paramContext, boolean paramBoolean) {
    return (Build.VERSION.SDK_INT >= 29) ? (getExternalStoragePath(paramContext, paramBoolean) + "/assets/") : (getExternalStoragePath(paramContext, paramBoolean) + "/AppInventor/assets/");
  }
  
  public static String getReplDataPath(Context paramContext) {
    return getReplDataPath(paramContext, false);
  }
  
  public static String getReplDataPath(Context paramContext, boolean paramBoolean) {
    return (Build.VERSION.SDK_INT >= 29) ? (getExternalStoragePath(paramContext, paramBoolean) + "/data/") : (getExternalStoragePath(paramContext, paramBoolean) + "/AppInventor/data/");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/QUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */