package com.google.appinventor.components.runtime.util;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;

public class PaintUtil {
  public static void changePaint(Paint paramPaint, int paramInt) {
    paramPaint.setColor(0xFFFFFF & paramInt);
    paramPaint.setAlpha(paramInt >> 24 & 0xFF);
    paramPaint.setXfermode(null);
  }
  
  public static void changePaintTransparent(Paint paramPaint) {
    paramPaint.setAlpha(0);
    paramPaint.setXfermode((Xfermode)new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
  }
  
  public static int hexStringToInt(String paramString) {
    String str = paramString;
    if (paramString.startsWith("#x") || paramString.startsWith("&H"))
      str = paramString.substring(2); 
    long l2 = Long.parseLong(str, 16);
    long l1 = l2;
    if (l2 > 2147483647L)
      l1 = l2 + 0L; 
    return (int)l1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/PaintUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */