package androidx.core.view;

import android.view.View;
import android.view.ViewParent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class NestedScrollingChildHelper {
  private boolean mIsNestedScrollingEnabled;
  
  private ViewParent mNestedScrollingParentNonTouch;
  
  private ViewParent mNestedScrollingParentTouch;
  
  private int[] mTempNestedScrollConsumed;
  
  private final View mView;
  
  public NestedScrollingChildHelper(@NonNull View paramView) {
    this.mView = paramView;
  }
  
  private ViewParent getNestedScrollingParentForType(int paramInt) {
    switch (paramInt) {
      default:
        return null;
      case 0:
        return this.mNestedScrollingParentTouch;
      case 1:
        break;
    } 
    return this.mNestedScrollingParentNonTouch;
  }
  
  private void setNestedScrollingParentForType(int paramInt, ViewParent paramViewParent) {
    switch (paramInt) {
      default:
        return;
      case 0:
        this.mNestedScrollingParentTouch = paramViewParent;
        return;
      case 1:
        break;
    } 
    this.mNestedScrollingParentNonTouch = paramViewParent;
  }
  
  public boolean dispatchNestedFling(float paramFloat1, float paramFloat2, boolean paramBoolean) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (isNestedScrollingEnabled()) {
      ViewParent viewParent = getNestedScrollingParentForType(0);
      bool1 = bool2;
      if (viewParent != null)
        bool1 = ViewParentCompat.onNestedFling(viewParent, this.mView, paramFloat1, paramFloat2, paramBoolean); 
    } 
    return bool1;
  }
  
  public boolean dispatchNestedPreFling(float paramFloat1, float paramFloat2) {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (isNestedScrollingEnabled()) {
      ViewParent viewParent = getNestedScrollingParentForType(0);
      bool1 = bool2;
      if (viewParent != null)
        bool1 = ViewParentCompat.onNestedPreFling(viewParent, this.mView, paramFloat1, paramFloat2); 
    } 
    return bool1;
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfint1, @Nullable int[] paramArrayOfint2) {
    return dispatchNestedPreScroll(paramInt1, paramInt2, paramArrayOfint1, paramArrayOfint2, 0);
  }
  
  public boolean dispatchNestedPreScroll(int paramInt1, int paramInt2, @Nullable int[] paramArrayOfint1, @Nullable int[] paramArrayOfint2, int paramInt3) {
    if (isNestedScrollingEnabled()) {
      ViewParent viewParent = getNestedScrollingParentForType(paramInt3);
      if (viewParent != null) {
        if (paramInt1 != 0 || paramInt2 != 0) {
          int i = 0;
          int j = 0;
          if (paramArrayOfint2 != null) {
            this.mView.getLocationInWindow(paramArrayOfint2);
            i = paramArrayOfint2[0];
            j = paramArrayOfint2[1];
          } 
          int[] arrayOfInt = paramArrayOfint1;
          if (paramArrayOfint1 == null) {
            if (this.mTempNestedScrollConsumed == null)
              this.mTempNestedScrollConsumed = new int[2]; 
            arrayOfInt = this.mTempNestedScrollConsumed;
          } 
          arrayOfInt[0] = 0;
          arrayOfInt[1] = 0;
          ViewParentCompat.onNestedPreScroll(viewParent, this.mView, paramInt1, paramInt2, arrayOfInt, paramInt3);
          if (paramArrayOfint2 != null) {
            this.mView.getLocationInWindow(paramArrayOfint2);
            paramArrayOfint2[0] = paramArrayOfint2[0] - i;
            paramArrayOfint2[1] = paramArrayOfint2[1] - j;
          } 
          return (arrayOfInt[0] != 0 || arrayOfInt[1] != 0);
        } 
        if (paramArrayOfint2 != null) {
          paramArrayOfint2[0] = 0;
          paramArrayOfint2[1] = 0;
          return false;
        } 
      } 
    } 
    return false;
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfint) {
    return dispatchNestedScroll(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfint, 0);
  }
  
  public boolean dispatchNestedScroll(int paramInt1, int paramInt2, int paramInt3, int paramInt4, @Nullable int[] paramArrayOfint, int paramInt5) {
    if (isNestedScrollingEnabled()) {
      ViewParent viewParent = getNestedScrollingParentForType(paramInt5);
      if (viewParent == null)
        return false; 
      if (paramInt1 != 0 || paramInt2 != 0 || paramInt3 != 0 || paramInt4 != 0) {
        int i = 0;
        int j = 0;
        if (paramArrayOfint != null) {
          this.mView.getLocationInWindow(paramArrayOfint);
          i = paramArrayOfint[0];
          j = paramArrayOfint[1];
        } 
        ViewParentCompat.onNestedScroll(viewParent, this.mView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
        if (paramArrayOfint != null) {
          this.mView.getLocationInWindow(paramArrayOfint);
          paramArrayOfint[0] = paramArrayOfint[0] - i;
          paramArrayOfint[1] = paramArrayOfint[1] - j;
        } 
        return true;
      } 
      if (paramArrayOfint != null) {
        paramArrayOfint[0] = 0;
        paramArrayOfint[1] = 0;
      } 
    } 
    return false;
  }
  
  public boolean hasNestedScrollingParent() {
    return hasNestedScrollingParent(0);
  }
  
  public boolean hasNestedScrollingParent(int paramInt) {
    return (getNestedScrollingParentForType(paramInt) != null);
  }
  
  public boolean isNestedScrollingEnabled() {
    return this.mIsNestedScrollingEnabled;
  }
  
  public void onDetachedFromWindow() {
    ViewCompat.stopNestedScroll(this.mView);
  }
  
  public void onStopNestedScroll(@NonNull View paramView) {
    ViewCompat.stopNestedScroll(this.mView);
  }
  
  public void setNestedScrollingEnabled(boolean paramBoolean) {
    if (this.mIsNestedScrollingEnabled)
      ViewCompat.stopNestedScroll(this.mView); 
    this.mIsNestedScrollingEnabled = paramBoolean;
  }
  
  public boolean startNestedScroll(int paramInt) {
    return startNestedScroll(paramInt, 0);
  }
  
  public boolean startNestedScroll(int paramInt1, int paramInt2) {
    if (hasNestedScrollingParent(paramInt2))
      return true; 
    if (isNestedScrollingEnabled()) {
      ViewParent viewParent = this.mView.getParent();
      View view = this.mView;
      while (viewParent != null) {
        if (ViewParentCompat.onStartNestedScroll(viewParent, view, this.mView, paramInt1, paramInt2)) {
          setNestedScrollingParentForType(paramInt2, viewParent);
          ViewParentCompat.onNestedScrollAccepted(viewParent, view, this.mView, paramInt1, paramInt2);
          return true;
        } 
        if (viewParent instanceof View)
          view = (View)viewParent; 
        viewParent = viewParent.getParent();
      } 
    } 
    return false;
  }
  
  public void stopNestedScroll() {
    stopNestedScroll(0);
  }
  
  public void stopNestedScroll(int paramInt) {
    ViewParent viewParent = getNestedScrollingParentForType(paramInt);
    if (viewParent != null) {
      ViewParentCompat.onStopNestedScroll(viewParent, this.mView, paramInt);
      setNestedScrollingParentForType(paramInt, null);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/NestedScrollingChildHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */