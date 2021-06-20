package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;

public interface TintableCompoundButton {
  @Nullable
  ColorStateList getSupportButtonTintList();
  
  @Nullable
  PorterDuff.Mode getSupportButtonTintMode();
  
  void setSupportButtonTintList(@Nullable ColorStateList paramColorStateList);
  
  void setSupportButtonTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/TintableCompoundButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */