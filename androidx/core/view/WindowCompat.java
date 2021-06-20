package androidx.core.view;

import android.os.Build;
import android.view.View;
import android.view.Window;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;

public final class WindowCompat {
  public static final int FEATURE_ACTION_BAR = 8;
  
  public static final int FEATURE_ACTION_BAR_OVERLAY = 9;
  
  public static final int FEATURE_ACTION_MODE_OVERLAY = 10;
  
  @NonNull
  public static <T extends View> T requireViewById(@NonNull Window paramWindow, @IdRes int paramInt) {
    if (Build.VERSION.SDK_INT >= 28)
      return (T)paramWindow.requireViewById(paramInt); 
    View view2 = paramWindow.findViewById(paramInt);
    View view1 = view2;
    if (view2 == null)
      throw new IllegalArgumentException("ID does not reference a View inside this Window"); 
    return (T)view1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/WindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */