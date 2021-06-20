package androidx.appcompat.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.R;
import androidx.appcompat.graphics.drawable.DrawableWrapper;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.ViewPropertyAnimatorCompat;
import androidx.core.widget.ListViewAutoScrollHelper;
import java.lang.reflect.Field;

class DropDownListView extends ListView {
  public static final int INVALID_POSITION = -1;
  
  public static final int NO_POSITION = -1;
  
  private ViewPropertyAnimatorCompat mClickAnimation;
  
  private boolean mDrawsInPressedState;
  
  private boolean mHijackFocus;
  
  private Field mIsChildViewEnabled;
  
  private boolean mListSelectionHidden;
  
  private int mMotionPosition;
  
  ResolveHoverRunnable mResolveHoverRunnable;
  
  private ListViewAutoScrollHelper mScrollHelper;
  
  private int mSelectionBottomPadding = 0;
  
  private int mSelectionLeftPadding = 0;
  
  private int mSelectionRightPadding = 0;
  
  private int mSelectionTopPadding = 0;
  
  private GateKeeperDrawable mSelector;
  
  private final Rect mSelectorRect = new Rect();
  
  DropDownListView(Context paramContext, boolean paramBoolean) {
    super(paramContext, null, R.attr.dropDownListViewStyle);
    this.mHijackFocus = paramBoolean;
    setCacheColorHint(0);
    try {
      this.mIsChildViewEnabled = AbsListView.class.getDeclaredField("mIsChildViewEnabled");
      this.mIsChildViewEnabled.setAccessible(true);
      return;
    } catch (NoSuchFieldException noSuchFieldException) {
      noSuchFieldException.printStackTrace();
      return;
    } 
  }
  
  private void clearPressedItem() {
    this.mDrawsInPressedState = false;
    setPressed(false);
    drawableStateChanged();
    View view = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
    if (view != null)
      view.setPressed(false); 
    if (this.mClickAnimation != null) {
      this.mClickAnimation.cancel();
      this.mClickAnimation = null;
    } 
  }
  
  private void clickPressedItem(View paramView, int paramInt) {
    performItemClick(paramView, paramInt, getItemIdAtPosition(paramInt));
  }
  
  private void drawSelectorCompat(Canvas paramCanvas) {
    if (!this.mSelectorRect.isEmpty()) {
      Drawable drawable = getSelector();
      if (drawable != null) {
        drawable.setBounds(this.mSelectorRect);
        drawable.draw(paramCanvas);
      } 
    } 
  }
  
  private void positionSelectorCompat(int paramInt, View paramView) {
    Rect rect = this.mSelectorRect;
    rect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
    rect.left -= this.mSelectionLeftPadding;
    rect.top -= this.mSelectionTopPadding;
    rect.right += this.mSelectionRightPadding;
    rect.bottom += this.mSelectionBottomPadding;
    try {
      boolean bool = this.mIsChildViewEnabled.getBoolean(this);
      if (paramView.isEnabled() != bool) {
        Field field = this.mIsChildViewEnabled;
        if (!bool) {
          bool = true;
        } else {
          bool = false;
        } 
        field.set(this, Boolean.valueOf(bool));
        if (paramInt != -1)
          refreshDrawableState(); 
      } 
      return;
    } catch (IllegalAccessException illegalAccessException) {
      illegalAccessException.printStackTrace();
      return;
    } 
  }
  
  private void positionSelectorLikeFocusCompat(int paramInt, View paramView) {
    boolean bool1;
    boolean bool2 = true;
    Drawable drawable = getSelector();
    if (drawable != null && paramInt != -1) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    if (bool1)
      drawable.setVisible(false, false); 
    positionSelectorCompat(paramInt, paramView);
    if (bool1) {
      Rect rect = this.mSelectorRect;
      float f1 = rect.exactCenterX();
      float f2 = rect.exactCenterY();
      if (getVisibility() != 0)
        bool2 = false; 
      drawable.setVisible(bool2, false);
      DrawableCompat.setHotspot(drawable, f1, f2);
    } 
  }
  
  private void positionSelectorLikeTouchCompat(int paramInt, View paramView, float paramFloat1, float paramFloat2) {
    positionSelectorLikeFocusCompat(paramInt, paramView);
    Drawable drawable = getSelector();
    if (drawable != null && paramInt != -1)
      DrawableCompat.setHotspot(drawable, paramFloat1, paramFloat2); 
  }
  
  private void setPressedItem(View paramView, int paramInt, float paramFloat1, float paramFloat2) {
    this.mDrawsInPressedState = true;
    if (Build.VERSION.SDK_INT >= 21)
      drawableHotspotChanged(paramFloat1, paramFloat2); 
    if (!isPressed())
      setPressed(true); 
    layoutChildren();
    if (this.mMotionPosition != -1) {
      View view = getChildAt(this.mMotionPosition - getFirstVisiblePosition());
      if (view != null && view != paramView && view.isPressed())
        view.setPressed(false); 
    } 
    this.mMotionPosition = paramInt;
    float f1 = paramView.getLeft();
    float f2 = paramView.getTop();
    if (Build.VERSION.SDK_INT >= 21)
      paramView.drawableHotspotChanged(paramFloat1 - f1, paramFloat2 - f2); 
    if (!paramView.isPressed())
      paramView.setPressed(true); 
    positionSelectorLikeTouchCompat(paramInt, paramView, paramFloat1, paramFloat2);
    setSelectorEnabled(false);
    refreshDrawableState();
  }
  
  private void setSelectorEnabled(boolean paramBoolean) {
    if (this.mSelector != null)
      this.mSelector.setEnabled(paramBoolean); 
  }
  
  private boolean touchModeDrawsInPressedStateCompat() {
    return this.mDrawsInPressedState;
  }
  
  private void updateSelectorStateCompat() {
    Drawable drawable = getSelector();
    if (drawable != null && touchModeDrawsInPressedStateCompat() && isPressed())
      drawable.setState(getDrawableState()); 
  }
  
  protected void dispatchDraw(Canvas paramCanvas) {
    drawSelectorCompat(paramCanvas);
    super.dispatchDraw(paramCanvas);
  }
  
  protected void drawableStateChanged() {
    if (this.mResolveHoverRunnable != null)
      return; 
    super.drawableStateChanged();
    setSelectorEnabled(true);
    updateSelectorStateCompat();
  }
  
  public boolean hasFocus() {
    return (this.mHijackFocus || super.hasFocus());
  }
  
  public boolean hasWindowFocus() {
    return (this.mHijackFocus || super.hasWindowFocus());
  }
  
  public boolean isFocused() {
    return (this.mHijackFocus || super.isFocused());
  }
  
  public boolean isInTouchMode() {
    return ((this.mHijackFocus && this.mListSelectionHidden) || super.isInTouchMode());
  }
  
  public int lookForSelectablePosition(int paramInt, boolean paramBoolean) {
    ListAdapter listAdapter = getAdapter();
    if (listAdapter != null && !isInTouchMode()) {
      int i = listAdapter.getCount();
      if (!getAdapter().areAllItemsEnabled()) {
        int j;
        if (paramBoolean) {
          paramInt = Math.max(0, paramInt);
          while (true) {
            j = paramInt;
            if (paramInt < i) {
              j = paramInt;
              if (!listAdapter.isEnabled(paramInt)) {
                paramInt++;
                continue;
              } 
            } 
            break;
          } 
        } else {
          paramInt = Math.min(paramInt, i - 1);
          while (true) {
            j = paramInt;
            if (paramInt >= 0) {
              j = paramInt;
              if (!listAdapter.isEnabled(paramInt)) {
                paramInt--;
                continue;
              } 
            } 
            break;
          } 
        } 
        return (j >= 0 && j < i) ? j : -1;
      } 
      if (paramInt >= 0 && paramInt < i)
        return paramInt; 
    } 
    return -1;
  }
  
  public int measureHeightOfChildrenCompat(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    paramInt2 = getListPaddingTop();
    paramInt3 = getListPaddingBottom();
    getListPaddingLeft();
    getListPaddingRight();
    int i = getDividerHeight();
    Drawable drawable = getDivider();
    ListAdapter listAdapter = getAdapter();
    if (listAdapter == null) {
      paramInt2 += paramInt3;
      return paramInt2;
    } 
    paramInt3 = paramInt2 + paramInt3;
    if (i <= 0 || drawable == null)
      i = 0; 
    paramInt2 = 0;
    drawable = null;
    int k = 0;
    int m = listAdapter.getCount();
    int j = 0;
    while (j < m) {
      int i1 = listAdapter.getItemViewType(j);
      int n = k;
      if (i1 != k) {
        drawable = null;
        n = i1;
      } 
      View view2 = listAdapter.getView(j, (View)drawable, (ViewGroup)this);
      ViewGroup.LayoutParams layoutParams2 = view2.getLayoutParams();
      ViewGroup.LayoutParams layoutParams1 = layoutParams2;
      if (layoutParams2 == null) {
        layoutParams1 = generateDefaultLayoutParams();
        view2.setLayoutParams(layoutParams1);
      } 
      if (layoutParams1.height > 0) {
        k = View.MeasureSpec.makeMeasureSpec(layoutParams1.height, 1073741824);
      } else {
        k = View.MeasureSpec.makeMeasureSpec(0, 0);
      } 
      view2.measure(paramInt1, k);
      view2.forceLayout();
      k = paramInt3;
      if (j > 0)
        k = paramInt3 + i; 
      paramInt3 = k + view2.getMeasuredHeight();
      if (paramInt3 >= paramInt4)
        return (paramInt5 < 0 || j <= paramInt5 || paramInt2 <= 0 || paramInt3 == paramInt4) ? paramInt4 : paramInt2; 
      k = paramInt2;
      if (paramInt5 >= 0) {
        k = paramInt2;
        if (j >= paramInt5)
          k = paramInt3; 
      } 
      j++;
      View view1 = view2;
      paramInt2 = k;
      k = n;
    } 
    return paramInt3;
  }
  
  protected void onDetachedFromWindow() {
    this.mResolveHoverRunnable = null;
    super.onDetachedFromWindow();
  }
  
  public boolean onForwardedEvent(MotionEvent paramMotionEvent, int paramInt) {
    // Byte code:
    //   0: iconst_1
    //   1: istore #7
    //   3: iconst_1
    //   4: istore #8
    //   6: iconst_0
    //   7: istore_3
    //   8: aload_1
    //   9: invokevirtual getActionMasked : ()I
    //   12: istore #4
    //   14: iload #4
    //   16: tableswitch default -> 44, 1 -> 117, 2 -> 120, 3 -> 109
    //   44: iload #8
    //   46: istore #7
    //   48: iload_3
    //   49: istore_2
    //   50: iload #7
    //   52: ifeq -> 59
    //   55: iload_2
    //   56: ifeq -> 63
    //   59: aload_0
    //   60: invokespecial clearPressedItem : ()V
    //   63: iload #7
    //   65: ifeq -> 235
    //   68: aload_0
    //   69: getfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   72: ifnonnull -> 87
    //   75: aload_0
    //   76: new androidx/core/widget/ListViewAutoScrollHelper
    //   79: dup
    //   80: aload_0
    //   81: invokespecial <init> : (Landroid/widget/ListView;)V
    //   84: putfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   87: aload_0
    //   88: getfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   91: iconst_1
    //   92: invokevirtual setEnabled : (Z)Landroidx/core/widget/AutoScrollHelper;
    //   95: pop
    //   96: aload_0
    //   97: getfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   100: aload_0
    //   101: aload_1
    //   102: invokevirtual onTouch : (Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   105: pop
    //   106: iload #7
    //   108: ireturn
    //   109: iconst_0
    //   110: istore #7
    //   112: iload_3
    //   113: istore_2
    //   114: goto -> 50
    //   117: iconst_0
    //   118: istore #7
    //   120: aload_1
    //   121: iload_2
    //   122: invokevirtual findPointerIndex : (I)I
    //   125: istore #5
    //   127: iload #5
    //   129: ifge -> 140
    //   132: iconst_0
    //   133: istore #7
    //   135: iload_3
    //   136: istore_2
    //   137: goto -> 50
    //   140: aload_1
    //   141: iload #5
    //   143: invokevirtual getX : (I)F
    //   146: f2i
    //   147: istore_2
    //   148: aload_1
    //   149: iload #5
    //   151: invokevirtual getY : (I)F
    //   154: f2i
    //   155: istore #6
    //   157: aload_0
    //   158: iload_2
    //   159: iload #6
    //   161: invokevirtual pointToPosition : (II)I
    //   164: istore #5
    //   166: iload #5
    //   168: iconst_m1
    //   169: if_icmpne -> 177
    //   172: iconst_1
    //   173: istore_2
    //   174: goto -> 50
    //   177: aload_0
    //   178: iload #5
    //   180: aload_0
    //   181: invokevirtual getFirstVisiblePosition : ()I
    //   184: isub
    //   185: invokevirtual getChildAt : (I)Landroid/view/View;
    //   188: astore #9
    //   190: aload_0
    //   191: aload #9
    //   193: iload #5
    //   195: iload_2
    //   196: i2f
    //   197: iload #6
    //   199: i2f
    //   200: invokespecial setPressedItem : (Landroid/view/View;IFF)V
    //   203: iconst_1
    //   204: istore #8
    //   206: iload_3
    //   207: istore_2
    //   208: iload #8
    //   210: istore #7
    //   212: iload #4
    //   214: iconst_1
    //   215: if_icmpne -> 50
    //   218: aload_0
    //   219: aload #9
    //   221: iload #5
    //   223: invokespecial clickPressedItem : (Landroid/view/View;I)V
    //   226: iload_3
    //   227: istore_2
    //   228: iload #8
    //   230: istore #7
    //   232: goto -> 50
    //   235: aload_0
    //   236: getfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   239: ifnull -> 106
    //   242: aload_0
    //   243: getfield mScrollHelper : Landroidx/core/widget/ListViewAutoScrollHelper;
    //   246: iconst_0
    //   247: invokevirtual setEnabled : (Z)Landroidx/core/widget/AutoScrollHelper;
    //   250: pop
    //   251: iload #7
    //   253: ireturn
  }
  
  public boolean onHoverEvent(@NonNull MotionEvent paramMotionEvent) {
    if (Build.VERSION.SDK_INT < 26)
      return super.onHoverEvent(paramMotionEvent); 
    int i = paramMotionEvent.getActionMasked();
    if (i == 10 && this.mResolveHoverRunnable == null) {
      this.mResolveHoverRunnable = new ResolveHoverRunnable();
      this.mResolveHoverRunnable.post();
    } 
    boolean bool = super.onHoverEvent(paramMotionEvent);
    if (i == 9 || i == 7) {
      i = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      boolean bool1 = bool;
      if (i != -1) {
        bool1 = bool;
        if (i != getSelectedItemPosition()) {
          View view = getChildAt(i - getFirstVisiblePosition());
          if (view.isEnabled())
            setSelectionFromTop(i, view.getTop() - getTop()); 
          updateSelectorStateCompat();
          return bool;
        } 
      } 
      return bool1;
    } 
    setSelection(-1);
    return bool;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    switch (paramMotionEvent.getAction()) {
      default:
        if (this.mResolveHoverRunnable != null)
          this.mResolveHoverRunnable.cancel(); 
        return super.onTouchEvent(paramMotionEvent);
      case 0:
        break;
    } 
    this.mMotionPosition = pointToPosition((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
  }
  
  void setListSelectionHidden(boolean paramBoolean) {
    this.mListSelectionHidden = paramBoolean;
  }
  
  public void setSelector(Drawable paramDrawable) {
    GateKeeperDrawable gateKeeperDrawable;
    if (paramDrawable != null) {
      gateKeeperDrawable = new GateKeeperDrawable(paramDrawable);
    } else {
      gateKeeperDrawable = null;
    } 
    this.mSelector = gateKeeperDrawable;
    super.setSelector((Drawable)this.mSelector);
    Rect rect = new Rect();
    if (paramDrawable != null)
      paramDrawable.getPadding(rect); 
    this.mSelectionLeftPadding = rect.left;
    this.mSelectionTopPadding = rect.top;
    this.mSelectionRightPadding = rect.right;
    this.mSelectionBottomPadding = rect.bottom;
  }
  
  private static class GateKeeperDrawable extends DrawableWrapper {
    private boolean mEnabled = true;
    
    GateKeeperDrawable(Drawable param1Drawable) {
      super(param1Drawable);
    }
    
    public void draw(Canvas param1Canvas) {
      if (this.mEnabled)
        super.draw(param1Canvas); 
    }
    
    void setEnabled(boolean param1Boolean) {
      this.mEnabled = param1Boolean;
    }
    
    public void setHotspot(float param1Float1, float param1Float2) {
      if (this.mEnabled)
        super.setHotspot(param1Float1, param1Float2); 
    }
    
    public void setHotspotBounds(int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      if (this.mEnabled)
        super.setHotspotBounds(param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    public boolean setState(int[] param1ArrayOfint) {
      return this.mEnabled ? super.setState(param1ArrayOfint) : false;
    }
    
    public boolean setVisible(boolean param1Boolean1, boolean param1Boolean2) {
      return this.mEnabled ? super.setVisible(param1Boolean1, param1Boolean2) : false;
    }
  }
  
  private class ResolveHoverRunnable implements Runnable {
    public void cancel() {
      DropDownListView.this.mResolveHoverRunnable = null;
      DropDownListView.this.removeCallbacks(this);
    }
    
    public void post() {
      DropDownListView.this.post(this);
    }
    
    public void run() {
      DropDownListView.this.mResolveHoverRunnable = null;
      DropDownListView.this.drawableStateChanged();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/DropDownListView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */