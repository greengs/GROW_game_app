package androidx.core.app;

import android.app.Service;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ServiceCompat {
  public static final int START_STICKY = 1;
  
  public static final int STOP_FOREGROUND_DETACH = 2;
  
  public static final int STOP_FOREGROUND_REMOVE = 1;
  
  public static void stopForeground(@NonNull Service paramService, int paramInt) {
    boolean bool;
    if (Build.VERSION.SDK_INT >= 24) {
      paramService.stopForeground(paramInt);
      return;
    } 
    if ((paramInt & 0x1) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    paramService.stopForeground(bool);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface StopForegroundFlags {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/ServiceCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */