package androidx.core.view;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;

public class NestedScrollingParentHelper {
  private int mNestedScrollAxes;
  
  private final ViewGroup mViewGroup;
  
  public NestedScrollingParentHelper(@NonNull ViewGroup paramViewGroup) {
    this.mViewGroup = paramViewGroup;
  }
  
  public int getNestedScrollAxes() {
    return this.mNestedScrollAxes;
  }
  
  public void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt) {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }
  
  public void onNestedScrollAccepted(@NonNull View paramView1, @NonNull View paramView2, int paramInt1, int paramInt2) {
    this.mNestedScrollAxes = paramInt1;
  }
  
  public void onStopNestedScroll(@NonNull View paramView) {
    onStopNestedScroll(paramView, 0);
  }
  
  public void onStopNestedScroll(@NonNull View paramView, int paramInt) {
    this.mNestedScrollAxes = 0;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/NestedScrollingParentHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */