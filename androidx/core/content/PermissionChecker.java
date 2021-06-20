package androidx.core.content;

import android.content.Context;
import android.os.Binder;
import android.os.Process;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.app.AppOpsManagerCompat;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class PermissionChecker {
  public static final int PERMISSION_DENIED = -1;
  
  public static final int PERMISSION_DENIED_APP_OP = -2;
  
  public static final int PERMISSION_GRANTED = 0;
  
  public static int checkCallingOrSelfPermission(@NonNull Context paramContext, @NonNull String paramString) {
    if (Binder.getCallingPid() == Process.myPid()) {
      String str1 = paramContext.getPackageName();
      return checkPermission(paramContext, paramString, Binder.getCallingPid(), Binder.getCallingUid(), str1);
    } 
    String str = null;
    return checkPermission(paramContext, paramString, Binder.getCallingPid(), Binder.getCallingUid(), str);
  }
  
  public static int checkCallingPermission(@NonNull Context paramContext, @NonNull String paramString1, @Nullable String paramString2) {
    return (Binder.getCallingPid() == Process.myPid()) ? -1 : checkPermission(paramContext, paramString1, Binder.getCallingPid(), Binder.getCallingUid(), paramString2);
  }
  
  public static int checkPermission(@NonNull Context paramContext, @NonNull String paramString1, int paramInt1, int paramInt2, @Nullable String paramString2) {
    if (paramContext.checkPermission(paramString1, paramInt1, paramInt2) != -1) {
      String str1;
      String str2 = AppOpsManagerCompat.permissionToOp(paramString1);
      if (str2 == null)
        return 0; 
      paramString1 = paramString2;
      if (paramString2 == null) {
        String[] arrayOfString = paramContext.getPackageManager().getPackagesForUid(paramInt2);
        if (arrayOfString != null && arrayOfString.length > 0) {
          str1 = arrayOfString[0];
        } else {
          return -1;
        } 
      } 
      return (AppOpsManagerCompat.noteProxyOpNoThrow(paramContext, str2, str1) != 0) ? -2 : 0;
    } 
    return -1;
  }
  
  public static int checkSelfPermission(@NonNull Context paramContext, @NonNull String paramString) {
    return checkPermission(paramContext, paramString, Process.myPid(), Process.myUid(), paramContext.getPackageName());
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface PermissionResult {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/PermissionChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */