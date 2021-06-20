package androidx.core.widget;

import android.os.Build;
import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;

public final class ListViewCompat {
  public static boolean canScrollList(@NonNull ListView paramListView, int paramInt) {
    boolean bool2 = false;
    if (Build.VERSION.SDK_INT >= 19)
      return paramListView.canScrollList(paramInt); 
    int i = paramListView.getChildCount();
    boolean bool1 = bool2;
    if (i != 0) {
      int j = paramListView.getFirstVisiblePosition();
      if (paramInt > 0) {
        paramInt = paramListView.getChildAt(i - 1).getBottom();
        if (j + i >= paramListView.getCount()) {
          bool1 = bool2;
          return (paramInt > paramListView.getHeight() - paramListView.getListPaddingBottom()) ? true : bool1;
        } 
        return true;
      } 
      paramInt = paramListView.getChildAt(0).getTop();
      if (j <= 0) {
        bool1 = bool2;
        return (paramInt < paramListView.getListPaddingTop()) ? true : bool1;
      } 
      return true;
    } 
    return bool1;
  }
  
  public static void scrollListBy(@NonNull ListView paramListView, int paramInt) {
    if (Build.VERSION.SDK_INT >= 19) {
      paramListView.scrollListBy(paramInt);
      return;
    } 
    int i = paramListView.getFirstVisiblePosition();
    if (i != -1) {
      View view = paramListView.getChildAt(0);
      if (view != null) {
        paramListView.setSelectionFromTop(i, view.getTop() - paramInt);
        return;
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/ListViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */