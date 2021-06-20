package com.google.appinventor.components.runtime.util;

import android.os.Build;
import android.widget.TextView;

public final class IceCreamSandwichUtil {
  public static void setAllCaps(TextView paramTextView, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 14)
      paramTextView.setAllCaps(paramBoolean); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/IceCreamSandwichUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */