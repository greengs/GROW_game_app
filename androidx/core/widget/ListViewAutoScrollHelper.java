package androidx.core.widget;

import android.view.View;
import android.widget.ListView;
import androidx.annotation.NonNull;

public class ListViewAutoScrollHelper extends AutoScrollHelper {
  private final ListView mTarget;
  
  public ListViewAutoScrollHelper(@NonNull ListView paramListView) {
    super((View)paramListView);
    this.mTarget = paramListView;
  }
  
  public boolean canTargetScrollHorizontally(int paramInt) {
    return false;
  }
  
  public boolean canTargetScrollVertically(int paramInt) {
    ListView listView = this.mTarget;
    int i = listView.getCount();
    if (i != 0) {
      int j = listView.getChildCount();
      int k = listView.getFirstVisiblePosition();
      if (paramInt > 0)
        return (k + j < i || listView.getChildAt(j - 1).getBottom() > listView.getHeight()); 
      if (paramInt < 0)
        return !(k <= 0 && listView.getChildAt(0).getTop() >= 0); 
    } 
    return false;
  }
  
  public void scrollTargetBy(int paramInt1, int paramInt2) {
    ListViewCompat.scrollListBy(this.mTarget, paramInt2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/ListViewAutoScrollHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */