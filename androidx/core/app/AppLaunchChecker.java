package androidx.core.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.annotation.NonNull;

public class AppLaunchChecker {
  private static final String KEY_STARTED_FROM_LAUNCHER = "startedFromLauncher";
  
  private static final String SHARED_PREFS_NAME = "android.support.AppLaunchChecker";
  
  public static boolean hasStartedFromLauncher(@NonNull Context paramContext) {
    return paramContext.getSharedPreferences("android.support.AppLaunchChecker", 0).getBoolean("startedFromLauncher", false);
  }
  
  public static void onActivityCreate(@NonNull Activity paramActivity) {
    SharedPreferences sharedPreferences = paramActivity.getSharedPreferences("android.support.AppLaunchChecker", 0);
    if (!sharedPreferences.getBoolean("startedFromLauncher", false)) {
      Intent intent = paramActivity.getIntent();
      if (intent != null && "android.intent.action.MAIN".equals(intent.getAction()) && (intent.hasCategory("android.intent.category.LAUNCHER") || intent.hasCategory("android.intent.category.LEANBACK_LAUNCHER"))) {
        sharedPreferences.edit().putBoolean("startedFromLauncher", true).apply();
        return;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/AppLaunchChecker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */