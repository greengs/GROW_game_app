package androidx.core.os;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class HandlerCompat {
  public static boolean postDelayed(@NonNull Handler paramHandler, @NonNull Runnable paramRunnable, @Nullable Object paramObject, long paramLong) {
    if (Build.VERSION.SDK_INT >= 28)
      return paramHandler.postDelayed(paramRunnable, paramObject, paramLong); 
    Message message = Message.obtain(paramHandler, paramRunnable);
    message.obj = paramObject;
    return paramHandler.sendMessageDelayed(message, paramLong);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/HandlerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */