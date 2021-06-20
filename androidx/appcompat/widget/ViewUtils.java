package androidx.appcompat.widget;

import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.core.view.ViewCompat;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ViewUtils {
  private static final String TAG = "ViewUtils";
  
  private static Method sComputeFitSystemWindowsMethod;
  
  static {
    if (Build.VERSION.SDK_INT >= 18)
      try {
        sComputeFitSystemWindowsMethod = View.class.getDeclaredMethod("computeFitSystemWindows", new Class[] { Rect.class, Rect.class });
        if (!sComputeFitSystemWindowsMethod.isAccessible())
          sComputeFitSystemWindowsMethod.setAccessible(true); 
        return;
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.d("ViewUtils", "Could not find method computeFitSystemWindows. Oh well.");
        return;
      }  
  }
  
  public static void computeFitSystemWindows(View paramView, Rect paramRect1, Rect paramRect2) {
    if (sComputeFitSystemWindowsMethod != null)
      try {
        sComputeFitSystemWindowsMethod.invoke(paramView, new Object[] { paramRect1, paramRect2 });
        return;
      } catch (Exception exception) {
        Log.d("ViewUtils", "Could not invoke computeFitSystemWindows", exception);
        return;
      }  
  }
  
  public static boolean isLayoutRtl(View paramView) {
    return (ViewCompat.getLayoutDirection(paramView) == 1);
  }
  
  public static void makeOptionalFitsSystemWindows(View paramView) {
    if (Build.VERSION.SDK_INT >= 16)
      try {
        Method method = paramView.getClass().getMethod("makeOptionalFitsSystemWindows", new Class[0]);
        if (!method.isAccessible())
          method.setAccessible(true); 
        method.invoke(paramView, new Object[0]);
        return;
      } catch (NoSuchMethodException noSuchMethodException) {
        Log.d("ViewUtils", "Could not find method makeOptionalFitsSystemWindows. Oh well...");
        return;
      } catch (InvocationTargetException invocationTargetException) {
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", invocationTargetException);
        return;
      } catch (IllegalAccessException illegalAccessException) {
        Log.d("ViewUtils", "Could not invoke makeOptionalFitsSystemWindows", illegalAccessException);
        return;
      }  
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/ViewUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */