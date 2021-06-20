package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface TintableImageSourceView {
  @Nullable
  ColorStateList getSupportImageTintList();
  
  @Nullable
  PorterDuff.Mode getSupportImageTintMode();
  
  void setSupportImageTintList(@Nullable ColorStateList paramColorStateList);
  
  void setSupportImageTintMode(@Nullable PorterDuff.Mode paramMode);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/TintableImageSourceView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */