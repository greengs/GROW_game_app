package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import androidx.core.content.FileProvider;
import com.google.appinventor.components.runtime.Form;
import java.io.File;

public final class NougatUtil {
  private static final String LOG_TAG = NougatUtil.class.getSimpleName();
  
  public static Uri getPackageUri(Form paramForm, File paramFile) {
    if (Build.VERSION.SDK_INT < 24)
      return Uri.fromFile(paramFile); 
    String str = paramForm.$context().getPackageName();
    Log.d(LOG_TAG, "packageName = " + str);
    return FileProvider.getUriForFile((Context)paramForm.$context(), str + ".provider", paramFile);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/NougatUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */