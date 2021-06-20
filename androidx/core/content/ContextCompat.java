package androidx.core.content;

import android.accessibilityservice.AccessibilityService;
import android.accounts.AccountManager;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.AppOpsManager;
import android.app.DownloadManager;
import android.app.KeyguardManager;
import android.app.NotificationManager;
import android.app.SearchManager;
import android.app.UiModeManager;
import android.app.WallpaperManager;
import android.app.admin.DevicePolicyManager;
import android.app.job.JobScheduler;
import android.app.usage.UsageStatsManager;
import android.appwidget.AppWidgetManager;
import android.bluetooth.BluetoothManager;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.RestrictionsManager;
import android.content.pm.LauncherApps;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.hardware.ConsumerIrManager;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraManager;
import android.hardware.display.DisplayManager;
import android.hardware.input.InputManager;
import android.hardware.usb.UsbManager;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.MediaRouter;
import android.media.projection.MediaProjectionManager;
import android.media.session.MediaSessionManager;
import android.media.tv.TvInputManager;
import android.net.ConnectivityManager;
import android.net.nsd.NsdManager;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pManager;
import android.nfc.NfcManager;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.os.PowerManager;
import android.os.Process;
import android.os.UserManager;
import android.os.Vibrator;
import android.os.storage.StorageManager;
import android.print.PrintManager;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.WindowManager;
import android.view.accessibility.CaptioningManager;
import android.view.inputmethod.InputMethodManager;
import android.view.textservice.TextServicesManager;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.File;
import java.util.HashMap;

public class ContextCompat {
  private static final String TAG = "ContextCompat";
  
  private static final Object sLock = new Object();
  
  private static TypedValue sTempValue;
  
  private static File buildPath(File paramFile, String... paramVarArgs) {
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      String str = paramVarArgs[i];
      if (paramFile == null) {
        paramFile = new File(str);
      } else if (str != null) {
        paramFile = new File(paramFile, str);
      } 
    } 
    return paramFile;
  }
  
  public static int checkSelfPermission(@NonNull Context paramContext, @NonNull String paramString) {
    if (paramString == null)
      throw new IllegalArgumentException("permission is null"); 
    return paramContext.checkPermission(paramString, Process.myPid(), Process.myUid());
  }
  
  @Nullable
  public static Context createDeviceProtectedStorageContext(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 24) ? paramContext.createDeviceProtectedStorageContext() : null;
  }
  
  private static File createFilesDir(File paramFile) {
    // Byte code:
    //   0: ldc androidx/core/content/ContextCompat
    //   2: monitorenter
    //   3: aload_0
    //   4: astore_2
    //   5: aload_0
    //   6: invokevirtual exists : ()Z
    //   9: ifne -> 32
    //   12: aload_0
    //   13: astore_2
    //   14: aload_0
    //   15: invokevirtual mkdirs : ()Z
    //   18: ifne -> 32
    //   21: aload_0
    //   22: invokevirtual exists : ()Z
    //   25: istore_1
    //   26: iload_1
    //   27: ifeq -> 37
    //   30: aload_0
    //   31: astore_2
    //   32: ldc androidx/core/content/ContextCompat
    //   34: monitorexit
    //   35: aload_2
    //   36: areturn
    //   37: ldc 'ContextCompat'
    //   39: new java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial <init> : ()V
    //   46: ldc 'Unable to create files subdir '
    //   48: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   51: aload_0
    //   52: invokevirtual getPath : ()Ljava/lang/String;
    //   55: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: invokevirtual toString : ()Ljava/lang/String;
    //   61: invokestatic w : (Ljava/lang/String;Ljava/lang/String;)I
    //   64: pop
    //   65: aconst_null
    //   66: astore_2
    //   67: goto -> 32
    //   70: astore_0
    //   71: ldc androidx/core/content/ContextCompat
    //   73: monitorexit
    //   74: aload_0
    //   75: athrow
    // Exception table:
    //   from	to	target	type
    //   5	12	70	finally
    //   14	26	70	finally
    //   37	65	70	finally
  }
  
  public static File getCodeCacheDir(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 21) ? paramContext.getCodeCacheDir() : createFilesDir(new File((paramContext.getApplicationInfo()).dataDir, "code_cache"));
  }
  
  @ColorInt
  public static int getColor(@NonNull Context paramContext, @ColorRes int paramInt) {
    return (Build.VERSION.SDK_INT >= 23) ? paramContext.getColor(paramInt) : paramContext.getResources().getColor(paramInt);
  }
  
  @Nullable
  public static ColorStateList getColorStateList(@NonNull Context paramContext, @ColorRes int paramInt) {
    return (Build.VERSION.SDK_INT >= 23) ? paramContext.getColorStateList(paramInt) : paramContext.getResources().getColorStateList(paramInt);
  }
  
  @Nullable
  public static File getDataDir(@NonNull Context paramContext) {
    if (Build.VERSION.SDK_INT >= 24)
      return paramContext.getDataDir(); 
    String str = (paramContext.getApplicationInfo()).dataDir;
    return (str != null) ? new File(str) : null;
  }
  
  @Nullable
  public static Drawable getDrawable(@NonNull Context paramContext, @DrawableRes int paramInt) {
    if (Build.VERSION.SDK_INT >= 21)
      return paramContext.getDrawable(paramInt); 
    if (Build.VERSION.SDK_INT >= 16)
      return paramContext.getResources().getDrawable(paramInt); 
    synchronized (sLock) {
      if (sTempValue == null)
        sTempValue = new TypedValue(); 
      paramContext.getResources().getValue(paramInt, sTempValue, true);
      paramInt = sTempValue.resourceId;
      return paramContext.getResources().getDrawable(paramInt);
    } 
  }
  
  @NonNull
  public static File[] getExternalCacheDirs(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 19) ? paramContext.getExternalCacheDirs() : new File[] { paramContext.getExternalCacheDir() };
  }
  
  @NonNull
  public static File[] getExternalFilesDirs(@NonNull Context paramContext, @Nullable String paramString) {
    return (Build.VERSION.SDK_INT >= 19) ? paramContext.getExternalFilesDirs(paramString) : new File[] { paramContext.getExternalFilesDir(paramString) };
  }
  
  @Nullable
  public static File getNoBackupFilesDir(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 21) ? paramContext.getNoBackupFilesDir() : createFilesDir(new File((paramContext.getApplicationInfo()).dataDir, "no_backup"));
  }
  
  @NonNull
  public static File[] getObbDirs(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 19) ? paramContext.getObbDirs() : new File[] { paramContext.getObbDir() };
  }
  
  @Nullable
  public static <T> T getSystemService(@NonNull Context paramContext, @NonNull Class<T> paramClass) {
    if (Build.VERSION.SDK_INT >= 23)
      return (T)paramContext.getSystemService(paramClass); 
    String str = getSystemServiceName(paramContext, paramClass);
    return (T)((str != null) ? paramContext.getSystemService(str) : null);
  }
  
  @Nullable
  public static String getSystemServiceName(@NonNull Context paramContext, @NonNull Class<?> paramClass) {
    return (Build.VERSION.SDK_INT >= 23) ? paramContext.getSystemServiceName(paramClass) : LegacyServiceMapHolder.SERVICES.get(paramClass);
  }
  
  public static boolean isDeviceProtectedStorage(@NonNull Context paramContext) {
    return (Build.VERSION.SDK_INT >= 24) ? paramContext.isDeviceProtectedStorage() : false;
  }
  
  public static boolean startActivities(@NonNull Context paramContext, @NonNull Intent[] paramArrayOfIntent) {
    return startActivities(paramContext, paramArrayOfIntent, null);
  }
  
  public static boolean startActivities(@NonNull Context paramContext, @NonNull Intent[] paramArrayOfIntent, @Nullable Bundle paramBundle) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramContext.startActivities(paramArrayOfIntent, paramBundle);
      return true;
    } 
    paramContext.startActivities(paramArrayOfIntent);
    return true;
  }
  
  public static void startActivity(@NonNull Context paramContext, @NonNull Intent paramIntent, @Nullable Bundle paramBundle) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramContext.startActivity(paramIntent, paramBundle);
      return;
    } 
    paramContext.startActivity(paramIntent);
  }
  
  public static void startForegroundService(@NonNull Context paramContext, @NonNull Intent paramIntent) {
    if (Build.VERSION.SDK_INT >= 26) {
      paramContext.startForegroundService(paramIntent);
      return;
    } 
    paramContext.startService(paramIntent);
  }
  
  private static final class LegacyServiceMapHolder {
    static final HashMap<Class<?>, String> SERVICES = new HashMap<Class<?>, String>();
    
    static {
      if (Build.VERSION.SDK_INT > 22) {
        SERVICES.put(SubscriptionManager.class, "telephony_subscription_service");
        SERVICES.put(UsageStatsManager.class, "usagestats");
      } 
      if (Build.VERSION.SDK_INT > 21) {
        SERVICES.put(AppWidgetManager.class, "appwidget");
        SERVICES.put(BatteryManager.class, "batterymanager");
        SERVICES.put(CameraManager.class, "camera");
        SERVICES.put(JobScheduler.class, "jobscheduler");
        SERVICES.put(LauncherApps.class, "launcherapps");
        SERVICES.put(MediaProjectionManager.class, "media_projection");
        SERVICES.put(MediaSessionManager.class, "media_session");
        SERVICES.put(RestrictionsManager.class, "restrictions");
        SERVICES.put(TelecomManager.class, "telecom");
        SERVICES.put(TvInputManager.class, "tv_input");
      } 
      if (Build.VERSION.SDK_INT > 19) {
        SERVICES.put(AppOpsManager.class, "appops");
        SERVICES.put(CaptioningManager.class, "captioning");
        SERVICES.put(ConsumerIrManager.class, "consumer_ir");
        SERVICES.put(PrintManager.class, "print");
      } 
      if (Build.VERSION.SDK_INT > 18)
        SERVICES.put(BluetoothManager.class, "bluetooth"); 
      if (Build.VERSION.SDK_INT > 17) {
        SERVICES.put(DisplayManager.class, "display");
        SERVICES.put(UserManager.class, "user");
      } 
      if (Build.VERSION.SDK_INT > 16) {
        SERVICES.put(InputManager.class, "input");
        SERVICES.put(MediaRouter.class, "media_router");
        SERVICES.put(NsdManager.class, "servicediscovery");
      } 
      SERVICES.put(AccessibilityService.class, "accessibility");
      SERVICES.put(AccountManager.class, "account");
      SERVICES.put(ActivityManager.class, "activity");
      SERVICES.put(AlarmManager.class, "alarm");
      SERVICES.put(AudioManager.class, "audio");
      SERVICES.put(ClipboardManager.class, "clipboard");
      SERVICES.put(ConnectivityManager.class, "connectivity");
      SERVICES.put(DevicePolicyManager.class, "device_policy");
      SERVICES.put(DownloadManager.class, "download");
      SERVICES.put(DropBoxManager.class, "dropbox");
      SERVICES.put(InputMethodManager.class, "input_method");
      SERVICES.put(KeyguardManager.class, "keyguard");
      SERVICES.put(LayoutInflater.class, "layout_inflater");
      SERVICES.put(LocationManager.class, "location");
      SERVICES.put(NfcManager.class, "nfc");
      SERVICES.put(NotificationManager.class, "notification");
      SERVICES.put(PowerManager.class, "power");
      SERVICES.put(SearchManager.class, "search");
      SERVICES.put(SensorManager.class, "sensor");
      SERVICES.put(StorageManager.class, "storage");
      SERVICES.put(TelephonyManager.class, "phone");
      SERVICES.put(TextServicesManager.class, "textservices");
      SERVICES.put(UiModeManager.class, "uimode");
      SERVICES.put(UsbManager.class, "usb");
      SERVICES.put(Vibrator.class, "vibrator");
      SERVICES.put(WallpaperManager.class, "wallpaper");
      SERVICES.put(WifiP2pManager.class, "wifip2p");
      SERVICES.put(WifiManager.class, "wifi");
      SERVICES.put(WindowManager.class, "window");
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/ContextCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */