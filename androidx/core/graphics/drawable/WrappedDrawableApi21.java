package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Outline;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Method;

@RequiresApi(21)
class WrappedDrawableApi21 extends WrappedDrawableApi14 {
  private static final String TAG = "WrappedDrawableApi21";
  
  private static Method sIsProjectedDrawableMethod;
  
  WrappedDrawableApi21(Drawable paramDrawable) {
    super(paramDrawable);
    findAndCacheIsProjectedDrawableMethod();
  }
  
  WrappedDrawableApi21(WrappedDrawableApi14.DrawableWrapperState paramDrawableWrapperState, Resources paramResources) {
    super(paramDrawableWrapperState, paramResources);
    findAndCacheIsProjectedDrawableMethod();
  }
  
  private void findAndCacheIsProjectedDrawableMethod() {
    if (sIsProjectedDrawableMethod == null)
      try {
        sIsProjectedDrawableMethod = Drawable.class.getDeclaredMethod("isProjected", new Class[0]);
        return;
      } catch (Exception exception) {
        Log.w("WrappedDrawableApi21", "Failed to retrieve Drawable#isProjected() method", exception);
        return;
      }  
  }
  
  @NonNull
  public Rect getDirtyBounds() {
    return this.mDrawable.getDirtyBounds();
  }
  
  public void getOutline(@NonNull Outline paramOutline) {
    this.mDrawable.getOutline(paramOutline);
  }
  
  protected boolean isCompatTintEnabled() {
    boolean bool = false;
    null = bool;
    if (Build.VERSION.SDK_INT == 21) {
      Drawable drawable = this.mDrawable;
      if (!(drawable instanceof android.graphics.drawable.GradientDrawable) && !(drawable instanceof android.graphics.drawable.DrawableContainer) && !(drawable instanceof android.graphics.drawable.InsetDrawable)) {
        null = bool;
        return (drawable instanceof android.graphics.drawable.RippleDrawable) ? true : null;
      } 
    } else {
      return null;
    } 
    return true;
  }
  
  public boolean isProjected() {
    if (this.mDrawable != null && sIsProjectedDrawableMethod != null)
      try {
        return ((Boolean)sIsProjectedDrawableMethod.invoke(this.mDrawable, new Object[0])).booleanValue();
      } catch (Exception exception) {
        Log.w("WrappedDrawableApi21", "Error calling Drawable#isProjected() method", exception);
      }  
    return false;
  }
  
  @NonNull
  WrappedDrawableApi14.DrawableWrapperState mutateConstantState() {
    return new DrawableWrapperStateLollipop(this.mState, null);
  }
  
  public void setHotspot(float paramFloat1, float paramFloat2) {
    this.mDrawable.setHotspot(paramFloat1, paramFloat2);
  }
  
  public void setHotspotBounds(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mDrawable.setHotspotBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean setState(@NonNull int[] paramArrayOfint) {
    if (super.setState(paramArrayOfint)) {
      invalidateSelf();
      return true;
    } 
    return false;
  }
  
  public void setTint(int paramInt) {
    if (isCompatTintEnabled()) {
      super.setTint(paramInt);
      return;
    } 
    this.mDrawable.setTint(paramInt);
  }
  
  public void setTintList(ColorStateList paramColorStateList) {
    if (isCompatTintEnabled()) {
      super.setTintList(paramColorStateList);
      return;
    } 
    this.mDrawable.setTintList(paramColorStateList);
  }
  
  public void setTintMode(PorterDuff.Mode paramMode) {
    if (isCompatTintEnabled()) {
      super.setTintMode(paramMode);
      return;
    } 
    this.mDrawable.setTintMode(paramMode);
  }
  
  private static class DrawableWrapperStateLollipop extends WrappedDrawableApi14.DrawableWrapperState {
    DrawableWrapperStateLollipop(@Nullable WrappedDrawableApi14.DrawableWrapperState param1DrawableWrapperState, @Nullable Resources param1Resources) {
      super(param1DrawableWrapperState, param1Resources);
    }
    
    @NonNull
    public Drawable newDrawable(@Nullable Resources param1Resources) {
      return new WrappedDrawableApi21(this, param1Resources);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/drawable/WrappedDrawableApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */