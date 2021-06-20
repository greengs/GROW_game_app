package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListPopupWindow;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class ListPopupWindowCompat {
  @Nullable
  public static View.OnTouchListener createDragToOpenListener(@NonNull ListPopupWindow paramListPopupWindow, @NonNull View paramView) {
    return (Build.VERSION.SDK_INT >= 19) ? paramListPopupWindow.createDragToOpenListener(paramView) : null;
  }
  
  @Deprecated
  public static View.OnTouchListener createDragToOpenListener(Object paramObject, View paramView) {
    return createDragToOpenListener((ListPopupWindow)paramObject, paramView);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/ListPopupWindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */