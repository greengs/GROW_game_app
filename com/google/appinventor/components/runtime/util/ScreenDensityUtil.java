package com.google.appinventor.components.runtime.util;

import android.content.Context;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class ScreenDensityUtil {
  public static final int DEFAULT_NORMAL_SHORT_DIMENSION = 320;
  
  private static final String LOG_TAG = "ScreenDensityUtil";
  
  public static final float MAXIMUM_ASPECT_RATIO = 1.7791667F;
  
  public static float computeCompatibleScaling(Context paramContext) {
    DisplayMetrics displayMetrics = paramContext.getResources().getDisplayMetrics();
    Point point = new Point();
    getRawScreenDim(paramContext, point);
    int i = point.x;
    int j = point.y;
    if (i < j) {
      n = i;
      m = j;
    } else {
      n = j;
      m = i;
    } 
    int k = (int)(320.0F * displayMetrics.density + 0.5F);
    float f2 = m / n;
    float f1 = f2;
    if (f2 > 1.7791667F)
      f1 = 1.7791667F; 
    int m = (int)(k * f1 + 0.5F);
    if (i < j) {
      n = k;
      k = m;
      return Math.max(1.0F, Math.min(Math.min(i / n, j / k), 1.7791667F));
    } 
    int n = m;
    return Math.max(1.0F, Math.min(Math.min(i / n, j / k), 1.7791667F));
  }
  
  private static void getRawScreenDim(Context paramContext, Point paramPoint) {
    DisplayMetrics displayMetrics = new DisplayMetrics();
    Display display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    int i = SdkLevel.getLevel();
    if (i >= 24) {
      display.getMetrics(displayMetrics);
      paramPoint.x = displayMetrics.widthPixels;
      paramPoint.y = displayMetrics.heightPixels;
      return;
    } 
    if (i >= 17) {
      JellybeanUtil.getRealSize(display, paramPoint);
      return;
    } 
    if (i > 10) {
      try {
        Method method1 = Display.class.getMethod("getRawHeight", new Class[0]);
        Method method2 = Display.class.getMethod("getRawWidth", new Class[0]);
        try {
          paramPoint.x = ((Integer)method2.invoke(display, new Object[0])).intValue();
          paramPoint.y = ((Integer)method1.invoke(display, new Object[0])).intValue();
          return;
        } catch (IllegalArgumentException illegalArgumentException) {
          Log.e("ScreenDensityUtil", "Error reading raw screen size", illegalArgumentException);
          return;
        } catch (IllegalAccessException illegalAccessException) {
          Log.e("ScreenDensityUtil", "Error reading raw screen size", illegalAccessException);
          return;
        } catch (InvocationTargetException invocationTargetException) {}
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.e("ScreenDensityUtil", "Error reading raw screen size", noSuchMethodException);
        return;
      } 
      Log.e("ScreenDensityUtil", "Error reading raw screen size", noSuchMethodException);
      return;
    } 
    paramPoint.x = noSuchMethodException.getWidth();
    paramPoint.y = noSuchMethodException.getHeight();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/ScreenDensityUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */