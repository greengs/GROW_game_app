package androidx.core.widget;

import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ImageViewCompat {
  @Nullable
  public static ColorStateList getImageTintList(@NonNull ImageView paramImageView) {
    return (Build.VERSION.SDK_INT >= 21) ? paramImageView.getImageTintList() : ((paramImageView instanceof TintableImageSourceView) ? ((TintableImageSourceView)paramImageView).getSupportImageTintList() : null);
  }
  
  @Nullable
  public static PorterDuff.Mode getImageTintMode(@NonNull ImageView paramImageView) {
    return (Build.VERSION.SDK_INT >= 21) ? paramImageView.getImageTintMode() : ((paramImageView instanceof TintableImageSourceView) ? ((TintableImageSourceView)paramImageView).getSupportImageTintMode() : null);
  }
  
  public static void setImageTintList(@NonNull ImageView paramImageView, @Nullable ColorStateList paramColorStateList) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= 21) {
      paramImageView.setImageTintList(paramColorStateList);
      if (Build.VERSION.SDK_INT == 21) {
        boolean bool;
        drawable = paramImageView.getDrawable();
        if (paramImageView.getImageTintList() != null && paramImageView.getImageTintMode() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (drawable != null && bool) {
          if (drawable.isStateful())
            drawable.setState(paramImageView.getDrawableState()); 
          paramImageView.setImageDrawable(drawable);
        } 
      } 
      return;
    } 
    if (paramImageView instanceof TintableImageSourceView) {
      ((TintableImageSourceView)paramImageView).setSupportImageTintList((ColorStateList)drawable);
      return;
    } 
  }
  
  public static void setImageTintMode(@NonNull ImageView paramImageView, @Nullable PorterDuff.Mode paramMode) {
    Drawable drawable;
    if (Build.VERSION.SDK_INT >= 21) {
      paramImageView.setImageTintMode(paramMode);
      if (Build.VERSION.SDK_INT == 21) {
        boolean bool;
        drawable = paramImageView.getDrawable();
        if (paramImageView.getImageTintList() != null && paramImageView.getImageTintMode() != null) {
          bool = true;
        } else {
          bool = false;
        } 
        if (drawable != null && bool) {
          if (drawable.isStateful())
            drawable.setState(paramImageView.getDrawableState()); 
          paramImageView.setImageDrawable(drawable);
        } 
      } 
      return;
    } 
    if (paramImageView instanceof TintableImageSourceView) {
      ((TintableImageSourceView)paramImageView).setSupportImageTintMode((PorterDuff.Mode)drawable);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/ImageViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */