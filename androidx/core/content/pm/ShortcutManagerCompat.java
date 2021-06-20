package androidx.core.content.pm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.ResolveInfo;
import android.content.pm.ShortcutManager;
import android.os.Build;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.core.content.ContextCompat;
import java.util.Iterator;

public class ShortcutManagerCompat {
  @VisibleForTesting
  static final String ACTION_INSTALL_SHORTCUT = "com.android.launcher.action.INSTALL_SHORTCUT";
  
  @VisibleForTesting
  static final String INSTALL_SHORTCUT_PERMISSION = "com.android.launcher.permission.INSTALL_SHORTCUT";
  
  @NonNull
  public static Intent createShortcutResultIntent(@NonNull Context paramContext, @NonNull ShortcutInfoCompat paramShortcutInfoCompat) {
    Intent intent2 = null;
    if (Build.VERSION.SDK_INT >= 26)
      intent2 = ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).createShortcutResultIntent(paramShortcutInfoCompat.toShortcutInfo()); 
    Intent intent1 = intent2;
    if (intent2 == null)
      intent1 = new Intent(); 
    return paramShortcutInfoCompat.addToIntent(intent1);
  }
  
  public static boolean isRequestPinShortcutSupported(@NonNull Context paramContext) {
    boolean bool2 = false;
    if (Build.VERSION.SDK_INT >= 26)
      return ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).isRequestPinShortcutSupported(); 
    boolean bool1 = bool2;
    if (ContextCompat.checkSelfPermission(paramContext, "com.android.launcher.permission.INSTALL_SHORTCUT") == 0) {
      Iterator iterator = paramContext.getPackageManager().queryBroadcastReceivers(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"), 0).iterator();
      while (true) {
        bool1 = bool2;
        if (iterator.hasNext()) {
          String str = ((ResolveInfo)iterator.next()).activityInfo.permission;
          if (TextUtils.isEmpty(str) || "com.android.launcher.permission.INSTALL_SHORTCUT".equals(str))
            break; 
          continue;
        } 
        return bool1;
      } 
      return true;
    } 
    return bool1;
  }
  
  public static boolean requestPinShortcut(@NonNull Context paramContext, @NonNull ShortcutInfoCompat paramShortcutInfoCompat, @Nullable final IntentSender callback) {
    if (Build.VERSION.SDK_INT >= 26)
      return ((ShortcutManager)paramContext.getSystemService(ShortcutManager.class)).requestPinShortcut(paramShortcutInfoCompat.toShortcutInfo(), callback); 
    if (!isRequestPinShortcutSupported(paramContext))
      return false; 
    Intent intent = paramShortcutInfoCompat.addToIntent(new Intent("com.android.launcher.action.INSTALL_SHORTCUT"));
    if (callback == null) {
      paramContext.sendBroadcast(intent);
      return true;
    } 
    paramContext.sendOrderedBroadcast(intent, null, new BroadcastReceiver() {
          public void onReceive(Context param1Context, Intent param1Intent) {
            try {
              callback.sendIntent(param1Context, 0, null, null, null);
              return;
            } catch (android.content.IntentSender.SendIntentException sendIntentException) {
              return;
            } 
          }
        }null, -1, null, null);
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/pm/ShortcutManagerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */