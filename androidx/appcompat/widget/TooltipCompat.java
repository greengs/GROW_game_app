package androidx.appcompat.widget;

import android.os.Build;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TooltipCompat {
  public static void setTooltipText(@NonNull View paramView, @Nullable CharSequence paramCharSequence) {
    if (Build.VERSION.SDK_INT >= 26) {
      paramView.setTooltipText(paramCharSequence);
      return;
    } 
    TooltipCompatHandler.setTooltipText(paramView, paramCharSequence);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/TooltipCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */