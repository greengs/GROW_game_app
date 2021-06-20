package androidx.core.app;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public final class NavUtils {
  public static final String PARENT_ACTIVITY = "android.support.PARENT_ACTIVITY";
  
  private static final String TAG = "NavUtils";
  
  @Nullable
  public static Intent getParentActivityIntent(@NonNull Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 16) {
      Intent intent = paramActivity.getParentActivityIntent();
      if (intent != null)
        return intent; 
    } 
    String str = getParentActivityName(paramActivity);
    if (str == null)
      return null; 
    ComponentName componentName = new ComponentName((Context)paramActivity, str);
    try {
      if (getParentActivityName((Context)paramActivity, componentName) == null) {
        Intent intent = Intent.makeMainActivity(componentName);
      } else {
        Intent intent = (new Intent()).setComponent(componentName);
      } 
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      Log.e("NavUtils", "getParentActivityIntent: bad parentActivityName '" + str + "' in manifest");
      return null;
    } 
    return (Intent)nameNotFoundException;
  }
  
  @Nullable
  public static Intent getParentActivityIntent(@NonNull Context paramContext, @NonNull ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    String str = getParentActivityName(paramContext, paramComponentName);
    if (str == null)
      return null; 
    paramComponentName = new ComponentName(paramComponentName.getPackageName(), str);
    return (getParentActivityName(paramContext, paramComponentName) == null) ? Intent.makeMainActivity(paramComponentName) : (new Intent()).setComponent(paramComponentName);
  }
  
  @Nullable
  public static Intent getParentActivityIntent(@NonNull Context paramContext, @NonNull Class<?> paramClass) throws PackageManager.NameNotFoundException {
    String str = getParentActivityName(paramContext, new ComponentName(paramContext, paramClass));
    if (str == null)
      return null; 
    ComponentName componentName = new ComponentName(paramContext, str);
    return (getParentActivityName(paramContext, componentName) == null) ? Intent.makeMainActivity(componentName) : (new Intent()).setComponent(componentName);
  }
  
  @Nullable
  public static String getParentActivityName(@NonNull Activity paramActivity) {
    try {
      return getParentActivityName((Context)paramActivity, paramActivity.getComponentName());
    } catch (android.content.pm.PackageManager.NameNotFoundException nameNotFoundException) {
      throw new IllegalArgumentException(nameNotFoundException);
    } 
  }
  
  @Nullable
  public static String getParentActivityName(@NonNull Context paramContext, @NonNull ComponentName paramComponentName) throws PackageManager.NameNotFoundException {
    ActivityInfo activityInfo = paramContext.getPackageManager().getActivityInfo(paramComponentName, 128);
    if (Build.VERSION.SDK_INT >= 16) {
      String str = activityInfo.parentActivityName;
      if (str != null)
        return str; 
    } 
    if (activityInfo.metaData == null)
      return null; 
    String str2 = activityInfo.metaData.getString("android.support.PARENT_ACTIVITY");
    if (str2 == null)
      return null; 
    String str1 = str2;
    if (str2.charAt(0) == '.')
      str1 = paramContext.getPackageName() + str2; 
    return str1;
  }
  
  public static void navigateUpFromSameTask(@NonNull Activity paramActivity) {
    Intent intent = getParentActivityIntent(paramActivity);
    if (intent == null)
      throw new IllegalArgumentException("Activity " + paramActivity.getClass().getSimpleName() + " does not have a parent activity name specified." + " (Did you forget to add the android.support.PARENT_ACTIVITY <meta-data> " + " element in your manifest?)"); 
    navigateUpTo(paramActivity, intent);
  }
  
  public static void navigateUpTo(@NonNull Activity paramActivity, @NonNull Intent paramIntent) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.navigateUpTo(paramIntent);
      return;
    } 
    paramIntent.addFlags(67108864);
    paramActivity.startActivity(paramIntent);
    paramActivity.finish();
  }
  
  public static boolean shouldUpRecreateTask(@NonNull Activity paramActivity, @NonNull Intent paramIntent) {
    if (Build.VERSION.SDK_INT >= 16)
      return paramActivity.shouldUpRecreateTask(paramIntent); 
    String str = paramActivity.getIntent().getAction();
    return (str != null && !str.equals("android.intent.action.MAIN"));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/NavUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */