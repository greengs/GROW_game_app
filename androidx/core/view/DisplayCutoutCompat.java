package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import java.util.List;

public final class DisplayCutoutCompat {
  private final Object mDisplayCutout;
  
  public DisplayCutoutCompat(Rect paramRect, List<Rect> paramList) {
    this(paramRect);
  }
  
  private DisplayCutoutCompat(Object paramObject) {
    this.mDisplayCutout = paramObject;
  }
  
  static DisplayCutoutCompat wrap(Object paramObject) {
    return (paramObject == null) ? null : new DisplayCutoutCompat(paramObject);
  }
  
  public boolean equals(Object paramObject) {
    if (this != paramObject) {
      if (paramObject == null || getClass() != paramObject.getClass())
        return false; 
      paramObject = paramObject;
      return (this.mDisplayCutout == null) ? (!(((DisplayCutoutCompat)paramObject).mDisplayCutout != null)) : this.mDisplayCutout.equals(((DisplayCutoutCompat)paramObject).mDisplayCutout);
    } 
    return true;
  }
  
  public List<Rect> getBoundingRects() {
    return (Build.VERSION.SDK_INT >= 28) ? ((DisplayCutout)this.mDisplayCutout).getBoundingRects() : null;
  }
  
  public int getSafeInsetBottom() {
    return (Build.VERSION.SDK_INT >= 28) ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetBottom() : 0;
  }
  
  public int getSafeInsetLeft() {
    return (Build.VERSION.SDK_INT >= 28) ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetLeft() : 0;
  }
  
  public int getSafeInsetRight() {
    return (Build.VERSION.SDK_INT >= 28) ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetRight() : 0;
  }
  
  public int getSafeInsetTop() {
    return (Build.VERSION.SDK_INT >= 28) ? ((DisplayCutout)this.mDisplayCutout).getSafeInsetTop() : 0;
  }
  
  public int hashCode() {
    return (this.mDisplayCutout == null) ? 0 : this.mDisplayCutout.hashCode();
  }
  
  public String toString() {
    return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/DisplayCutoutCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */