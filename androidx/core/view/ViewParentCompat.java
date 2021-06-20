package androidx.core.view;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.view.accessibility.AccessibilityEvent;

public final class ViewParentCompat {
  private static final String TAG = "ViewParentCompat";
  
  public static void notifySubtreeAccessibilityStateChanged(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt) {
    if (Build.VERSION.SDK_INT >= 19)
      paramViewParent.notifySubtreeAccessibilityStateChanged(paramView1, paramView2, paramInt); 
  }
  
  public static boolean onNestedFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        return paramViewParent.onNestedFling(paramView, paramFloat1, paramFloat2, paramBoolean);
      } catch (AbstractMethodError abstractMethodError) {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedFling", abstractMethodError);
      } 
      return false;
    } 
    return (paramViewParent instanceof NestedScrollingParent) ? ((NestedScrollingParent)paramViewParent).onNestedFling((View)abstractMethodError, paramFloat1, paramFloat2, paramBoolean) : false;
  }
  
  public static boolean onNestedPreFling(ViewParent paramViewParent, View paramView, float paramFloat1, float paramFloat2) {
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        return paramViewParent.onNestedPreFling(paramView, paramFloat1, paramFloat2);
      } catch (AbstractMethodError abstractMethodError) {
        Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreFling", abstractMethodError);
      } 
      return false;
    } 
    return (paramViewParent instanceof NestedScrollingParent) ? ((NestedScrollingParent)paramViewParent).onNestedPreFling((View)abstractMethodError, paramFloat1, paramFloat2) : false;
  }
  
  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfint) {
    onNestedPreScroll(paramViewParent, paramView, paramInt1, paramInt2, paramArrayOfint, 0);
  }
  
  public static void onNestedPreScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int[] paramArrayOfint, int paramInt3) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfint, paramInt3);
      return;
    } 
    if (paramInt3 == 0) {
      if (Build.VERSION.SDK_INT >= 21)
        try {
          paramViewParent.onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfint);
          return;
        } catch (AbstractMethodError abstractMethodError) {
          Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedPreScroll", abstractMethodError);
          return;
        }  
      if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedPreScroll((View)abstractMethodError, paramInt1, paramInt2, paramArrayOfint);
        return;
      } 
    } 
  }
  
  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    onNestedScroll(paramViewParent, paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }
  
  public static void onNestedScroll(ViewParent paramViewParent, View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
      return;
    } 
    if (paramInt5 == 0) {
      if (Build.VERSION.SDK_INT >= 21)
        try {
          paramViewParent.onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
          return;
        } catch (AbstractMethodError abstractMethodError) {
          Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScroll", abstractMethodError);
          return;
        }  
      if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedScroll((View)abstractMethodError, paramInt1, paramInt2, paramInt3, paramInt4);
        return;
      } 
    } 
  }
  
  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt) {
    onNestedScrollAccepted(paramViewParent, paramView1, paramView2, paramInt, 0);
  }
  
  public static void onNestedScrollAccepted(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
      return;
    } 
    if (paramInt2 == 0) {
      if (Build.VERSION.SDK_INT >= 21)
        try {
          paramViewParent.onNestedScrollAccepted(paramView1, paramView2, paramInt1);
          return;
        } catch (AbstractMethodError abstractMethodError) {
          Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onNestedScrollAccepted", abstractMethodError);
          return;
        }  
      if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onNestedScrollAccepted((View)abstractMethodError, paramView2, paramInt1);
        return;
      } 
    } 
  }
  
  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt) {
    return onStartNestedScroll(paramViewParent, paramView1, paramView2, paramInt, 0);
  }
  
  public static boolean onStartNestedScroll(ViewParent paramViewParent, View paramView1, View paramView2, int paramInt1, int paramInt2) {
    if (paramViewParent instanceof NestedScrollingParent2)
      return ((NestedScrollingParent2)paramViewParent).onStartNestedScroll(paramView1, paramView2, paramInt1, paramInt2); 
    if (paramInt2 == 0) {
      if (Build.VERSION.SDK_INT >= 21) {
        try {
          return paramViewParent.onStartNestedScroll(paramView1, paramView2, paramInt1);
        } catch (AbstractMethodError abstractMethodError) {
          Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStartNestedScroll", abstractMethodError);
        } 
        return false;
      } 
    } else {
      return false;
    } 
    return (paramViewParent instanceof NestedScrollingParent) ? ((NestedScrollingParent)paramViewParent).onStartNestedScroll((View)abstractMethodError, paramView2, paramInt1) : false;
  }
  
  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView) {
    onStopNestedScroll(paramViewParent, paramView, 0);
  }
  
  public static void onStopNestedScroll(ViewParent paramViewParent, View paramView, int paramInt) {
    if (paramViewParent instanceof NestedScrollingParent2) {
      ((NestedScrollingParent2)paramViewParent).onStopNestedScroll(paramView, paramInt);
      return;
    } 
    if (paramInt == 0) {
      if (Build.VERSION.SDK_INT >= 21)
        try {
          paramViewParent.onStopNestedScroll(paramView);
          return;
        } catch (AbstractMethodError abstractMethodError) {
          Log.e("ViewParentCompat", "ViewParent " + paramViewParent + " does not implement interface " + "method onStopNestedScroll", abstractMethodError);
          return;
        }  
      if (paramViewParent instanceof NestedScrollingParent) {
        ((NestedScrollingParent)paramViewParent).onStopNestedScroll((View)abstractMethodError);
        return;
      } 
    } 
  }
  
  @Deprecated
  public static boolean requestSendAccessibilityEvent(ViewParent paramViewParent, View paramView, AccessibilityEvent paramAccessibilityEvent) {
    return paramViewParent.requestSendAccessibilityEvent(paramView, paramAccessibilityEvent);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/view/ViewParentCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */