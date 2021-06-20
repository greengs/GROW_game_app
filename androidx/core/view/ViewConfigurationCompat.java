package androidx.core.view;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewConfiguration;
import androidx.annotation.NonNull;
import java.lang.reflect.Method;

public final class ViewConfigurationCompat {
  private static final String TAG = "ViewConfigCompat";
  
  private static Method sGetScaledScrollFactorMethod;
  
  static {
    if (Build.VERSION.SDK_INT == 25)
      try {
        sGetScaledScrollFactorMethod = ViewConfiguration.class.getDeclaredMethod("getScaledScrollFactor", new Class[0]);
        return;
      } catch (Exception exception) {
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
        return;
      }  
  }
  
  private static float getLegacyScrollFactor(ViewConfiguration paramViewConfiguration, Context paramContext) {
    if (Build.VERSION.SDK_INT >= 25 && sGetScaledScrollFactorMethod != null)
      try {
        int i = ((Integer)sGetScaledScrollFactorMethod.invoke(paramViewConfiguration, new Object[0])).intValue();
        return i;
      } catch (Exception exception) {
        Log.i("ViewConfigCompat", "Could not find method getScaledScrollFactor() on ViewConfiguration");
      }  
    TypedValue typedValue = new TypedValue();
    return paramContext.getTheme().resolveAttribute(16842829, typedValue, true) ? typedValue.getDimension(paramContext.getResources().getDisplayMetrics()) : 0.0F;
  }
  
  public static float getScaledHorizontalScrollFactor(@NonNull ViewConfiguration paramViewConfiguration, @NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 26) ? paramViewConfiguration.getScaledHorizontalScrollFactor() : getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }
  
  public static int getScaledHoverSlop(ViewConfiguration paramViewConfiguration) {
    return (Build.VERSION.SDK_INT >= 28) ? paramViewConfiguration.getScaledHoverSlop() : (paramViewConfiguration.getScaledTouchSlop() / 2);
  }
  
  @Deprecated
  public static int getScaledPagingTouchSlop(ViewConfiguration paramViewConfiguration) {
    return paramViewConfiguration.getScaledPagingTouchSlop();
  }
  
  public static float getScaledVerticalScrollFactor(@NonNull ViewConfiguration paramViewConfiguration, @NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 26) ? paramViewConfiguration.getScaledVerticalScrollFactor() : getLegacyScrollFactor(paramViewConfiguration, paramContext);
  }
  
  @Deprecated
  public static boolean hasPermanentMenuKey(ViewConfiguration paramViewConfiguration) {
    return paramViewConfiguration.hasPermanentMenuKey();
  }
  
  public static boolean shouldShowMenuShortcutsWhenKeyboardPresent(ViewConfiguration paramViewConfiguration, @NonNull Context paramContext) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramViewConfiguration.shouldShowMenuShortcutsWhenKeyboardPresent(); 
    Resources resources = paramContext.getResources();
    int i = resources.getIdentifier("config_showMenuShortcutsWhenKeyboardPresent", "bool", "android");
    return (i != 0 && resources.getBoolean(i));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/ViewConfigurationCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */