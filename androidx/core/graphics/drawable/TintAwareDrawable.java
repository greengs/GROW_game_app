package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface TintAwareDrawable {
  void setTint(@ColorInt int paramInt);
  
  void setTintList(ColorStateList paramColorStateList);
  
  void setTintMode(PorterDuff.Mode paramMode);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/drawable/TintAwareDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */