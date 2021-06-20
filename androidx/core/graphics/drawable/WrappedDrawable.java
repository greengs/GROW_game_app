package androidx.core.graphics.drawable;

import android.graphics.drawable.Drawable;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public interface WrappedDrawable {
  Drawable getWrappedDrawable();
  
  void setWrappedDrawable(Drawable paramDrawable);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/drawable/WrappedDrawable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */