package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class PopupMenuCompat {
  @Nullable
  public static View.OnTouchListener getDragToOpenListener(@NonNull Object paramObject) {
    return (Build.VERSION.SDK_INT >= 19) ? ((PopupMenu)paramObject).getDragToOpenListener() : null;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/PopupMenuCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */