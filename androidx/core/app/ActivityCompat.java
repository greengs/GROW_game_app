package androidx.core.app;

import android.app.Activity;
import android.app.SharedElementCallback;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.view.DragEvent;
import android.view.View;
import androidx.annotation.IdRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.view.DragAndDropPermissionsCompat;
import java.util.List;
import java.util.Map;

public class ActivityCompat extends ContextCompat {
  private static PermissionCompatDelegate sDelegate;
  
  public static void finishAffinity(@NonNull Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.finishAffinity();
      return;
    } 
    paramActivity.finish();
  }
  
  public static void finishAfterTransition(@NonNull Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21) {
      paramActivity.finishAfterTransition();
      return;
    } 
    paramActivity.finish();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static PermissionCompatDelegate getPermissionCompatDelegate() {
    return sDelegate;
  }
  
  @Nullable
  public static Uri getReferrer(@NonNull Activity paramActivity) {
    String str;
    if (Build.VERSION.SDK_INT >= 22)
      return paramActivity.getReferrer(); 
    Intent intent = paramActivity.getIntent();
    Uri uri2 = (Uri)intent.getParcelableExtra("android.intent.extra.REFERRER");
    Uri uri1 = uri2;
    if (uri2 == null) {
      str = intent.getStringExtra("android.intent.extra.REFERRER_NAME");
      return (str != null) ? Uri.parse(str) : null;
    } 
    return (Uri)str;
  }
  
  @Deprecated
  public static boolean invalidateOptionsMenu(Activity paramActivity) {
    paramActivity.invalidateOptionsMenu();
    return true;
  }
  
  public static void postponeEnterTransition(@NonNull Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21)
      paramActivity.postponeEnterTransition(); 
  }
  
  @Nullable
  public static DragAndDropPermissionsCompat requestDragAndDropPermissions(Activity paramActivity, DragEvent paramDragEvent) {
    return DragAndDropPermissionsCompat.request(paramActivity, paramDragEvent);
  }
  
  public static void requestPermissions(@NonNull final Activity activity, @NonNull final String[] permissions, @IntRange(from = 0L) final int requestCode) {
    if (sDelegate == null || !sDelegate.requestPermissions(activity, permissions, requestCode)) {
      if (Build.VERSION.SDK_INT >= 23) {
        if (activity instanceof RequestPermissionsRequestCodeValidator)
          ((RequestPermissionsRequestCodeValidator)activity).validateRequestPermissionsRequestCode(requestCode); 
        activity.requestPermissions(permissions, requestCode);
        return;
      } 
      if (activity instanceof OnRequestPermissionsResultCallback) {
        (new Handler(Looper.getMainLooper())).post(new Runnable() {
              public void run() {
                int[] arrayOfInt = new int[permissions.length];
                PackageManager packageManager = activity.getPackageManager();
                String str = activity.getPackageName();
                int j = permissions.length;
                for (int i = 0; i < j; i++)
                  arrayOfInt[i] = packageManager.checkPermission(permissions[i], str); 
                ((ActivityCompat.OnRequestPermissionsResultCallback)activity).onRequestPermissionsResult(requestCode, permissions, arrayOfInt);
              }
            });
        return;
      } 
    } 
  }
  
  @NonNull
  public static <T extends View> T requireViewById(@NonNull Activity paramActivity, @IdRes int paramInt) {
    if (Build.VERSION.SDK_INT >= 28)
      return (T)paramActivity.requireViewById(paramInt); 
    View view2 = paramActivity.findViewById(paramInt);
    View view1 = view2;
    if (view2 == null)
      throw new IllegalArgumentException("ID does not reference a View inside this Activity"); 
    return (T)view1;
  }
  
  public static void setEnterSharedElementCallback(@NonNull Activity paramActivity, @Nullable SharedElementCallback paramSharedElementCallback) {
    if (Build.VERSION.SDK_INT >= 21) {
      if (paramSharedElementCallback != null) {
        SharedElementCallback21Impl sharedElementCallback21Impl = new SharedElementCallback21Impl(paramSharedElementCallback);
      } else {
        paramSharedElementCallback = null;
      } 
      paramActivity.setEnterSharedElementCallback((SharedElementCallback)paramSharedElementCallback);
    } 
  }
  
  public static void setExitSharedElementCallback(@NonNull Activity paramActivity, @Nullable SharedElementCallback paramSharedElementCallback) {
    if (Build.VERSION.SDK_INT >= 21) {
      if (paramSharedElementCallback != null) {
        SharedElementCallback21Impl sharedElementCallback21Impl = new SharedElementCallback21Impl(paramSharedElementCallback);
      } else {
        paramSharedElementCallback = null;
      } 
      paramActivity.setExitSharedElementCallback((SharedElementCallback)paramSharedElementCallback);
    } 
  }
  
  public static void setPermissionCompatDelegate(@Nullable PermissionCompatDelegate paramPermissionCompatDelegate) {
    sDelegate = paramPermissionCompatDelegate;
  }
  
  public static boolean shouldShowRequestPermissionRationale(@NonNull Activity paramActivity, @NonNull String paramString) {
    return (Build.VERSION.SDK_INT >= 23) ? paramActivity.shouldShowRequestPermissionRationale(paramString) : false;
  }
  
  public static void startActivityForResult(@NonNull Activity paramActivity, @NonNull Intent paramIntent, int paramInt, @Nullable Bundle paramBundle) {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.startActivityForResult(paramIntent, paramInt, paramBundle);
      return;
    } 
    paramActivity.startActivityForResult(paramIntent, paramInt);
  }
  
  public static void startIntentSenderForResult(@NonNull Activity paramActivity, @NonNull IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4, @Nullable Bundle paramBundle) throws IntentSender.SendIntentException {
    if (Build.VERSION.SDK_INT >= 16) {
      paramActivity.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4, paramBundle);
      return;
    } 
    paramActivity.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
  
  public static void startPostponedEnterTransition(@NonNull Activity paramActivity) {
    if (Build.VERSION.SDK_INT >= 21)
      paramActivity.startPostponedEnterTransition(); 
  }
  
  public static interface OnRequestPermissionsResultCallback {
    void onRequestPermissionsResult(int param1Int, @NonNull String[] param1ArrayOfString, @NonNull int[] param1ArrayOfint);
  }
  
  public static interface PermissionCompatDelegate {
    boolean onActivityResult(@NonNull Activity param1Activity, @IntRange(from = 0L) int param1Int1, int param1Int2, @Nullable Intent param1Intent);
    
    boolean requestPermissions(@NonNull Activity param1Activity, @NonNull String[] param1ArrayOfString, @IntRange(from = 0L) int param1Int);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static interface RequestPermissionsRequestCodeValidator {
    void validateRequestPermissionsRequestCode(int param1Int);
  }
  
  @RequiresApi(21)
  private static class SharedElementCallback21Impl extends SharedElementCallback {
    private final SharedElementCallback mCallback;
    
    SharedElementCallback21Impl(SharedElementCallback param1SharedElementCallback) {
      this.mCallback = param1SharedElementCallback;
    }
    
    public Parcelable onCaptureSharedElementSnapshot(View param1View, Matrix param1Matrix, RectF param1RectF) {
      return this.mCallback.onCaptureSharedElementSnapshot(param1View, param1Matrix, param1RectF);
    }
    
    public View onCreateSnapshotView(Context param1Context, Parcelable param1Parcelable) {
      return this.mCallback.onCreateSnapshotView(param1Context, param1Parcelable);
    }
    
    public void onMapSharedElements(List<String> param1List, Map<String, View> param1Map) {
      this.mCallback.onMapSharedElements(param1List, param1Map);
    }
    
    public void onRejectSharedElements(List<View> param1List) {
      this.mCallback.onRejectSharedElements(param1List);
    }
    
    public void onSharedElementEnd(List<String> param1List, List<View> param1List1, List<View> param1List2) {
      this.mCallback.onSharedElementEnd(param1List, param1List1, param1List2);
    }
    
    public void onSharedElementStart(List<String> param1List, List<View> param1List1, List<View> param1List2) {
      this.mCallback.onSharedElementStart(param1List, param1List1, param1List2);
    }
    
    @RequiresApi(23)
    public void onSharedElementsArrived(List<String> param1List, List<View> param1List1, final SharedElementCallback.OnSharedElementsReadyListener listener) {
      this.mCallback.onSharedElementsArrived(param1List, param1List1, new SharedElementCallback.OnSharedElementsReadyListener() {
            public void onSharedElementsReady() {
              listener.onSharedElementsReady();
            }
          });
    }
  }
  
  class null implements SharedElementCallback.OnSharedElementsReadyListener {
    public void onSharedElementsReady() {
      listener.onSharedElementsReady();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/ActivityCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */