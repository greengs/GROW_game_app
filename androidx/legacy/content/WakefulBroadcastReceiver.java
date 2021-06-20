package androidx.legacy.content;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;
import android.util.SparseArray;

@Deprecated
public abstract class WakefulBroadcastReceiver extends BroadcastReceiver {
  private static final String EXTRA_WAKE_LOCK_ID = "androidx.contentpager.content.wakelockid";
  
  private static int mNextId;
  
  private static final SparseArray<PowerManager.WakeLock> sActiveWakeLocks = new SparseArray();
  
  static {
    mNextId = 1;
  }
  
  public static boolean completeWakefulIntent(Intent paramIntent) {
    int i = paramIntent.getIntExtra("androidx.contentpager.content.wakelockid", 0);
    if (i == 0)
      return false; 
    synchronized (sActiveWakeLocks) {
      PowerManager.WakeLock wakeLock = (PowerManager.WakeLock)sActiveWakeLocks.get(i);
      if (wakeLock != null) {
        wakeLock.release();
        sActiveWakeLocks.remove(i);
        return true;
      } 
      Log.w("WakefulBroadcastReceiv.", "No active wake lock id #" + i);
      return true;
    } 
  }
  
  public static ComponentName startWakefulService(Context paramContext, Intent paramIntent) {
    synchronized (sActiveWakeLocks) {
      int i = mNextId;
      mNextId++;
      if (mNextId <= 0)
        mNextId = 1; 
      paramIntent.putExtra("androidx.contentpager.content.wakelockid", i);
      ComponentName componentName = paramContext.startService(paramIntent);
      if (componentName == null)
        return null; 
      PowerManager.WakeLock wakeLock = ((PowerManager)paramContext.getSystemService("power")).newWakeLock(1, "androidx.core:wake:" + componentName.flattenToShortString());
      wakeLock.setReferenceCounted(false);
      wakeLock.acquire(60000L);
      sActiveWakeLocks.put(i, wakeLock);
      return componentName;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/legacy/content/WakefulBroadcastReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */