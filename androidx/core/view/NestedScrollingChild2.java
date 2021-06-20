package androidx.core.view;

import androidx.annotation.Nullable;

public interface NestedScrollingChild2 extends NestedScrollingChild {
  boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfint1, @Nullable int[] paramArrayOfint2, int paramInt3);
  
  boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfint, int paramInt5);
  
  boolean hasNestedScrollingParent(int paramInt);
  
  boolean startNestedScroll(int paramInt1, int paramInt2);
  
  void stopNestedScroll(int paramInt);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/NestedScrollingChild2.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */