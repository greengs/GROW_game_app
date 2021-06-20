package androidx.core.database.sqlite;

import android.database.sqlite.SQLiteCursor;
import android.os.Build;
import androidx.annotation.NonNull;

public final class SQLiteCursorCompat {
  public static void setFillWindowForwardOnly(@NonNull SQLiteCursor paramSQLiteCursor, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 28)
      paramSQLiteCursor.setFillWindowForwardOnly(paramBoolean); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/database/sqlite/SQLiteCursorCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */