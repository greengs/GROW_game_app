package androidx.core.os;

import android.os.Build;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.IOException;

public final class EnvironmentCompat {
  public static final String MEDIA_UNKNOWN = "unknown";
  
  private static final String TAG = "EnvironmentCompat";
  
  public static String getStorageState(File paramFile) {
    if (Build.VERSION.SDK_INT >= 19)
      return Environment.getStorageState(paramFile); 
    try {
      if (paramFile.getCanonicalPath().startsWith(Environment.getExternalStorageDirectory().getCanonicalPath()))
        return Environment.getExternalStorageState(); 
    } catch (IOException iOException) {
      Log.w("EnvironmentCompat", "Failed to resolve canonical path: " + iOException);
    } 
    return "unknown";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/EnvironmentCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */