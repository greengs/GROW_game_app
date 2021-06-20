package androidx.core.graphics;

import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;

public final class BitmapCompat {
  public static int getAllocationByteCount(@NonNull Bitmap paramBitmap) {
    return (Build.VERSION.SDK_INT >= 19) ? paramBitmap.getAllocationByteCount() : paramBitmap.getByteCount();
  }
  
  public static boolean hasMipMap(@NonNull Bitmap paramBitmap) {
    return (Build.VERSION.SDK_INT >= 18) ? paramBitmap.hasMipMap() : false;
  }
  
  public static void setHasMipMap(@NonNull Bitmap paramBitmap, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 18)
      paramBitmap.setHasMipMap(paramBoolean); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/BitmapCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */