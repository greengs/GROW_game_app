package androidx.core.view;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;

public interface TintableBackgroundView {
  @Nullable
  ColorStateList getSupportBackgroundTintList();
  
  @Nullable
  PorterDuff.Mode getSupportBackgroundTintMode();
  
  void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList);
  
  void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/TintableBackgroundView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */