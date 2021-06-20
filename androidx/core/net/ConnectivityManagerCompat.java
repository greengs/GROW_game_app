package androidx.core.net;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresPermission;
import androidx.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ConnectivityManagerCompat {
  public static final int RESTRICT_BACKGROUND_STATUS_DISABLED = 1;
  
  public static final int RESTRICT_BACKGROUND_STATUS_ENABLED = 3;
  
  public static final int RESTRICT_BACKGROUND_STATUS_WHITELISTED = 2;
  
  @Nullable
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static NetworkInfo getNetworkInfoFromBroadcast(@NonNull ConnectivityManager paramConnectivityManager, @NonNull Intent paramIntent) {
    NetworkInfo networkInfo = (NetworkInfo)paramIntent.getParcelableExtra("networkInfo");
    return (networkInfo != null) ? paramConnectivityManager.getNetworkInfo(networkInfo.getType()) : null;
  }
  
  public static int getRestrictBackgroundStatus(@NonNull ConnectivityManager paramConnectivityManager) {
    return (Build.VERSION.SDK_INT >= 24) ? paramConnectivityManager.getRestrictBackgroundStatus() : 3;
  }
  
  @RequiresPermission("android.permission.ACCESS_NETWORK_STATE")
  public static boolean isActiveNetworkMetered(@NonNull ConnectivityManager paramConnectivityManager) {
    boolean bool2 = true;
    if (Build.VERSION.SDK_INT >= 16)
      boolean bool = paramConnectivityManager.isActiveNetworkMetered(); 
    NetworkInfo networkInfo = paramConnectivityManager.getActiveNetworkInfo();
    boolean bool1 = bool2;
    if (networkInfo != null) {
      bool1 = bool2;
      switch (networkInfo.getType()) {
        case 0:
        case 2:
        case 3:
        case 4:
        case 5:
        case 6:
          return bool1;
        default:
          return true;
        case 1:
        case 7:
        case 9:
          break;
      } 
      return false;
    } 
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface RestrictBackgroundStatus {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/net/ConnectivityManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */