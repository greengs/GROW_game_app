package com.google.appinventor.components.runtime.util;

import android.graphics.Bitmap;
import android.view.View;

public class DonutUtil {
  public static void buildDrawingCache(View paramView, boolean paramBoolean) {
    paramView.buildDrawingCache(paramBoolean);
  }
  
  public static Bitmap getDrawingCache(View paramView, boolean paramBoolean) {
    return paramView.getDrawingCache(paramBoolean);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/DonutUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */