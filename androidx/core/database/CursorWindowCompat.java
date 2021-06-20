package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class CursorWindowCompat {
  @NonNull
  public static CursorWindow create(@Nullable String paramString, long paramLong) {
    return (Build.VERSION.SDK_INT >= 28) ? new CursorWindow(paramString, paramLong) : ((Build.VERSION.SDK_INT >= 15) ? new CursorWindow(paramString) : new CursorWindow(false));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/database/CursorWindowCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */