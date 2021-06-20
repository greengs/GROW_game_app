package com.google.appinventor.components.runtime.util;

import android.util.Log;
import java.io.Closeable;
import java.io.IOException;

public final class IOUtils {
  public static void closeQuietly(String paramString, Closeable paramCloseable) {
    if (paramCloseable != null)
      try {
        paramCloseable.close();
        return;
      } catch (IOException iOException) {
        Log.w(paramString, "Failed to close resource", iOException);
        return;
      }  
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/IOUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */