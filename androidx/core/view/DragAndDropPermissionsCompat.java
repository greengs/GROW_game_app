package androidx.core.view;

import android.app.Activity;
import android.os.Build;
import android.view.DragAndDropPermissions;
import android.view.DragEvent;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

public final class DragAndDropPermissionsCompat {
  private Object mDragAndDropPermissions;
  
  private DragAndDropPermissionsCompat(Object paramObject) {
    this.mDragAndDropPermissions = paramObject;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static DragAndDropPermissionsCompat request(Activity paramActivity, DragEvent paramDragEvent) {
    if (Build.VERSION.SDK_INT >= 24) {
      DragAndDropPermissions dragAndDropPermissions = paramActivity.requestDragAndDropPermissions(paramDragEvent);
      if (dragAndDropPermissions != null)
        return new DragAndDropPermissionsCompat(dragAndDropPermissions); 
    } 
    return null;
  }
  
  public void release() {
    if (Build.VERSION.SDK_INT >= 24)
      ((DragAndDropPermissions)this.mDragAndDropPermissions).release(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/DragAndDropPermissionsCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */