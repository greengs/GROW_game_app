package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import java.lang.ref.WeakReference;

class TintResources extends ResourcesWrapper {
  private final WeakReference<Context> mContextRef;
  
  public TintResources(@NonNull Context paramContext, @NonNull Resources paramResources) {
    super(paramResources);
    this.mContextRef = new WeakReference<Context>(paramContext);
  }
  
  public Drawable getDrawable(int paramInt) throws Resources.NotFoundException {
    Drawable drawable = super.getDrawable(paramInt);
    Context context = this.mContextRef.get();
    if (drawable != null && context != null) {
      AppCompatDrawableManager.get();
      AppCompatDrawableManager.tintDrawableUsingColorFilter(context, paramInt, drawable);
    } 
    return drawable;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/TintResources.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */