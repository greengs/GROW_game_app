package androidx.drawerlayout.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.WindowInsets;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import java.util.ArrayList;
import java.util.List;

public class DrawerLayout extends ViewGroup {
  private static final boolean ALLOW_EDGE_LOCK = false;
  
  static final boolean CAN_HIDE_DESCENDANTS;
  
  private static final boolean CHILDREN_DISALLOW_INTERCEPT = true;
  
  private static final int DEFAULT_SCRIM_COLOR = -1728053248;
  
  private static final int DRAWER_ELEVATION = 10;
  
  static final int[] LAYOUT_ATTRS;
  
  public static final int LOCK_MODE_LOCKED_CLOSED = 1;
  
  public static final int LOCK_MODE_LOCKED_OPEN = 2;
  
  public static final int LOCK_MODE_UNDEFINED = 3;
  
  public static final int LOCK_MODE_UNLOCKED = 0;
  
  private static final int MIN_DRAWER_MARGIN = 64;
  
  private static final int MIN_FLING_VELOCITY = 400;
  
  private static final int PEEK_DELAY = 160;
  
  private static final boolean SET_DRAWER_SHADOW_FROM_ELEVATION;
  
  public static final int STATE_DRAGGING = 1;
  
  public static final int STATE_IDLE = 0;
  
  public static final int STATE_SETTLING = 2;
  
  private static final String TAG = "DrawerLayout";
  
  private static final int[] THEME_ATTRS = new int[] { 16843828 };
  
  private static final float TOUCH_SLOP_SENSITIVITY = 1.0F;
  
  private final ChildAccessibilityDelegate mChildAccessibilityDelegate;
  
  private Rect mChildHitRect;
  
  private Matrix mChildInvertedMatrix;
  
  private boolean mChildrenCanceledTouch;
  
  private boolean mDisallowInterceptRequested;
  
  private boolean mDrawStatusBarBackground;
  
  private float mDrawerElevation;
  
  private int mDrawerState;
  
  private boolean mFirstLayout;
  
  private boolean mInLayout;
  
  private float mInitialMotionX;
  
  private float mInitialMotionY;
  
  private Object mLastInsets;
  
  private final ViewDragCallback mLeftCallback;
  
  private final ViewDragHelper mLeftDragger;
  
  @Nullable
  private DrawerListener mListener;
  
  private List<DrawerListener> mListeners;
  
  private int mLockModeEnd;
  
  private int mLockModeLeft;
  
  private int mLockModeRight;
  
  private int mLockModeStart;
  
  private int mMinDrawerMargin;
  
  private final ArrayList<View> mNonDrawerViews;
  
  private final ViewDragCallback mRightCallback;
  
  private final ViewDragHelper mRightDragger;
  
  private int mScrimColor;
  
  private float mScrimOpacity;
  
  private Paint mScrimPaint;
  
  private Drawable mShadowEnd;
  
  private Drawable mShadowLeft;
  
  private Drawable mShadowLeftResolved;
  
  private Drawable mShadowRight;
  
  private Drawable mShadowRightResolved;
  
  private Drawable mShadowStart;
  
  private Drawable mStatusBarBackground;
  
  private CharSequence mTitleLeft;
  
  private CharSequence mTitleRight;
  
  static {
    LAYOUT_ATTRS = new int[] { 16842931 };
    if (Build.VERSION.SDK_INT >= 19) {
      bool1 = true;
    } else {
      bool1 = false;
    } 
    CAN_HIDE_DESCENDANTS = bool1;
    if (Build.VERSION.SDK_INT >= 21) {
      bool1 = bool2;
    } else {
      bool1 = false;
    } 
    SET_DRAWER_SHADOW_FROM_ELEVATION = bool1;
  }
  
  public DrawerLayout(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public DrawerLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public DrawerLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new androidx/drawerlayout/widget/DrawerLayout$ChildAccessibilityDelegate
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: putfield mChildAccessibilityDelegate : Landroidx/drawerlayout/widget/DrawerLayout$ChildAccessibilityDelegate;
    //   18: aload_0
    //   19: ldc -1728053248
    //   21: putfield mScrimColor : I
    //   24: aload_0
    //   25: new android/graphics/Paint
    //   28: dup
    //   29: invokespecial <init> : ()V
    //   32: putfield mScrimPaint : Landroid/graphics/Paint;
    //   35: aload_0
    //   36: iconst_1
    //   37: putfield mFirstLayout : Z
    //   40: aload_0
    //   41: iconst_3
    //   42: putfield mLockModeLeft : I
    //   45: aload_0
    //   46: iconst_3
    //   47: putfield mLockModeRight : I
    //   50: aload_0
    //   51: iconst_3
    //   52: putfield mLockModeStart : I
    //   55: aload_0
    //   56: iconst_3
    //   57: putfield mLockModeEnd : I
    //   60: aload_0
    //   61: aconst_null
    //   62: putfield mShadowStart : Landroid/graphics/drawable/Drawable;
    //   65: aload_0
    //   66: aconst_null
    //   67: putfield mShadowEnd : Landroid/graphics/drawable/Drawable;
    //   70: aload_0
    //   71: aconst_null
    //   72: putfield mShadowLeft : Landroid/graphics/drawable/Drawable;
    //   75: aload_0
    //   76: aconst_null
    //   77: putfield mShadowRight : Landroid/graphics/drawable/Drawable;
    //   80: aload_0
    //   81: ldc 262144
    //   83: invokevirtual setDescendantFocusability : (I)V
    //   86: aload_0
    //   87: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   90: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   93: getfield density : F
    //   96: fstore #4
    //   98: aload_0
    //   99: ldc 64.0
    //   101: fload #4
    //   103: fmul
    //   104: ldc 0.5
    //   106: fadd
    //   107: f2i
    //   108: putfield mMinDrawerMargin : I
    //   111: ldc 400.0
    //   113: fload #4
    //   115: fmul
    //   116: fstore #5
    //   118: aload_0
    //   119: new androidx/drawerlayout/widget/DrawerLayout$ViewDragCallback
    //   122: dup
    //   123: aload_0
    //   124: iconst_3
    //   125: invokespecial <init> : (Landroidx/drawerlayout/widget/DrawerLayout;I)V
    //   128: putfield mLeftCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   131: aload_0
    //   132: new androidx/drawerlayout/widget/DrawerLayout$ViewDragCallback
    //   135: dup
    //   136: aload_0
    //   137: iconst_5
    //   138: invokespecial <init> : (Landroidx/drawerlayout/widget/DrawerLayout;I)V
    //   141: putfield mRightCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   144: aload_0
    //   145: aload_0
    //   146: fconst_1
    //   147: aload_0
    //   148: getfield mLeftCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   151: invokestatic create : (Landroid/view/ViewGroup;FLandroidx/customview/widget/ViewDragHelper$Callback;)Landroidx/customview/widget/ViewDragHelper;
    //   154: putfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   157: aload_0
    //   158: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   161: iconst_1
    //   162: invokevirtual setEdgeTrackingEnabled : (I)V
    //   165: aload_0
    //   166: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   169: fload #5
    //   171: invokevirtual setMinVelocity : (F)V
    //   174: aload_0
    //   175: getfield mLeftCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   178: aload_0
    //   179: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   182: invokevirtual setDragger : (Landroidx/customview/widget/ViewDragHelper;)V
    //   185: aload_0
    //   186: aload_0
    //   187: fconst_1
    //   188: aload_0
    //   189: getfield mRightCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   192: invokestatic create : (Landroid/view/ViewGroup;FLandroidx/customview/widget/ViewDragHelper$Callback;)Landroidx/customview/widget/ViewDragHelper;
    //   195: putfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   198: aload_0
    //   199: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   202: iconst_2
    //   203: invokevirtual setEdgeTrackingEnabled : (I)V
    //   206: aload_0
    //   207: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   210: fload #5
    //   212: invokevirtual setMinVelocity : (F)V
    //   215: aload_0
    //   216: getfield mRightCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   219: aload_0
    //   220: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   223: invokevirtual setDragger : (Landroidx/customview/widget/ViewDragHelper;)V
    //   226: aload_0
    //   227: iconst_1
    //   228: invokevirtual setFocusableInTouchMode : (Z)V
    //   231: aload_0
    //   232: iconst_1
    //   233: invokestatic setImportantForAccessibility : (Landroid/view/View;I)V
    //   236: aload_0
    //   237: new androidx/drawerlayout/widget/DrawerLayout$AccessibilityDelegate
    //   240: dup
    //   241: aload_0
    //   242: invokespecial <init> : (Landroidx/drawerlayout/widget/DrawerLayout;)V
    //   245: invokestatic setAccessibilityDelegate : (Landroid/view/View;Landroidx/core/view/AccessibilityDelegateCompat;)V
    //   248: aload_0
    //   249: iconst_0
    //   250: invokevirtual setMotionEventSplittingEnabled : (Z)V
    //   253: aload_0
    //   254: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   257: ifeq -> 308
    //   260: getstatic android/os/Build$VERSION.SDK_INT : I
    //   263: bipush #21
    //   265: if_icmplt -> 337
    //   268: aload_0
    //   269: new androidx/drawerlayout/widget/DrawerLayout$1
    //   272: dup
    //   273: aload_0
    //   274: invokespecial <init> : (Landroidx/drawerlayout/widget/DrawerLayout;)V
    //   277: invokevirtual setOnApplyWindowInsetsListener : (Landroid/view/View$OnApplyWindowInsetsListener;)V
    //   280: aload_0
    //   281: sipush #1280
    //   284: invokevirtual setSystemUiVisibility : (I)V
    //   287: aload_1
    //   288: getstatic androidx/drawerlayout/widget/DrawerLayout.THEME_ATTRS : [I
    //   291: invokevirtual obtainStyledAttributes : ([I)Landroid/content/res/TypedArray;
    //   294: astore_1
    //   295: aload_0
    //   296: aload_1
    //   297: iconst_0
    //   298: invokevirtual getDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   301: putfield mStatusBarBackground : Landroid/graphics/drawable/Drawable;
    //   304: aload_1
    //   305: invokevirtual recycle : ()V
    //   308: aload_0
    //   309: ldc_w 10.0
    //   312: fload #4
    //   314: fmul
    //   315: putfield mDrawerElevation : F
    //   318: aload_0
    //   319: new java/util/ArrayList
    //   322: dup
    //   323: invokespecial <init> : ()V
    //   326: putfield mNonDrawerViews : Ljava/util/ArrayList;
    //   329: return
    //   330: astore_2
    //   331: aload_1
    //   332: invokevirtual recycle : ()V
    //   335: aload_2
    //   336: athrow
    //   337: aload_0
    //   338: aconst_null
    //   339: putfield mStatusBarBackground : Landroid/graphics/drawable/Drawable;
    //   342: goto -> 308
    // Exception table:
    //   from	to	target	type
    //   295	304	330	finally
  }
  
  private boolean dispatchTransformedGenericPointerEvent(MotionEvent paramMotionEvent, View paramView) {
    if (!paramView.getMatrix().isIdentity()) {
      paramMotionEvent = getTransformedMotionEvent(paramMotionEvent, paramView);
      boolean bool1 = paramView.dispatchGenericMotionEvent(paramMotionEvent);
      paramMotionEvent.recycle();
      return bool1;
    } 
    float f1 = (getScrollX() - paramView.getLeft());
    float f2 = (getScrollY() - paramView.getTop());
    paramMotionEvent.offsetLocation(f1, f2);
    boolean bool = paramView.dispatchGenericMotionEvent(paramMotionEvent);
    paramMotionEvent.offsetLocation(-f1, -f2);
    return bool;
  }
  
  private MotionEvent getTransformedMotionEvent(MotionEvent paramMotionEvent, View paramView) {
    float f1 = (getScrollX() - paramView.getLeft());
    float f2 = (getScrollY() - paramView.getTop());
    paramMotionEvent = MotionEvent.obtain(paramMotionEvent);
    paramMotionEvent.offsetLocation(f1, f2);
    Matrix matrix = paramView.getMatrix();
    if (!matrix.isIdentity()) {
      if (this.mChildInvertedMatrix == null)
        this.mChildInvertedMatrix = new Matrix(); 
      matrix.invert(this.mChildInvertedMatrix);
      paramMotionEvent.transform(this.mChildInvertedMatrix);
    } 
    return paramMotionEvent;
  }
  
  static String gravityToString(int paramInt) {
    return ((paramInt & 0x3) == 3) ? "LEFT" : (((paramInt & 0x5) == 5) ? "RIGHT" : Integer.toHexString(paramInt));
  }
  
  private static boolean hasOpaqueBackground(View paramView) {
    boolean bool2 = false;
    Drawable drawable = paramView.getBackground();
    boolean bool1 = bool2;
    if (drawable != null) {
      bool1 = bool2;
      if (drawable.getOpacity() == -1)
        bool1 = true; 
    } 
    return bool1;
  }
  
  private boolean hasPeekingDrawer() {
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      if (((LayoutParams)getChildAt(i).getLayoutParams()).isPeeking)
        return true; 
    } 
    return false;
  }
  
  private boolean hasVisibleDrawer() {
    return (findVisibleDrawer() != null);
  }
  
  static boolean includeChildForAccessibility(View paramView) {
    return (ViewCompat.getImportantForAccessibility(paramView) != 4 && ViewCompat.getImportantForAccessibility(paramView) != 2);
  }
  
  private boolean isInBoundsOfChild(float paramFloat1, float paramFloat2, View paramView) {
    if (this.mChildHitRect == null)
      this.mChildHitRect = new Rect(); 
    paramView.getHitRect(this.mChildHitRect);
    return this.mChildHitRect.contains((int)paramFloat1, (int)paramFloat2);
  }
  
  private boolean mirror(Drawable paramDrawable, int paramInt) {
    if (paramDrawable == null || !DrawableCompat.isAutoMirrored(paramDrawable))
      return false; 
    DrawableCompat.setLayoutDirection(paramDrawable, paramInt);
    return true;
  }
  
  private Drawable resolveLeftShadow() {
    int i = ViewCompat.getLayoutDirection((View)this);
    if (i == 0) {
      if (this.mShadowStart != null) {
        mirror(this.mShadowStart, i);
        return this.mShadowStart;
      } 
    } else if (this.mShadowEnd != null) {
      mirror(this.mShadowEnd, i);
      return this.mShadowEnd;
    } 
    return this.mShadowLeft;
  }
  
  private Drawable resolveRightShadow() {
    int i = ViewCompat.getLayoutDirection((View)this);
    if (i == 0) {
      if (this.mShadowEnd != null) {
        mirror(this.mShadowEnd, i);
        return this.mShadowEnd;
      } 
    } else if (this.mShadowStart != null) {
      mirror(this.mShadowStart, i);
      return this.mShadowStart;
    } 
    return this.mShadowRight;
  }
  
  private void resolveShadowDrawables() {
    if (SET_DRAWER_SHADOW_FROM_ELEVATION)
      return; 
    this.mShadowLeftResolved = resolveLeftShadow();
    this.mShadowRightResolved = resolveRightShadow();
  }
  
  private void updateChildrenImportantForAccessibility(View paramView, boolean paramBoolean) {
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      if ((!paramBoolean && !isDrawerView(view)) || (paramBoolean && view == paramView)) {
        ViewCompat.setImportantForAccessibility(view, 1);
      } else {
        ViewCompat.setImportantForAccessibility(view, 4);
      } 
    } 
  }
  
  public void addDrawerListener(@NonNull DrawerListener paramDrawerListener) {
    if (paramDrawerListener == null)
      return; 
    if (this.mListeners == null)
      this.mListeners = new ArrayList<DrawerListener>(); 
    this.mListeners.add(paramDrawerListener);
  }
  
  public void addFocusables(ArrayList<View> paramArrayList, int paramInt1, int paramInt2) {
    if (getDescendantFocusability() == 393216)
      return; 
    int k = getChildCount();
    int j = 0;
    int i;
    for (i = 0; i < k; i++) {
      View view = getChildAt(i);
      if (isDrawerView(view)) {
        if (isDrawerOpen(view)) {
          j = 1;
          view.addFocusables(paramArrayList, paramInt1, paramInt2);
        } 
      } else {
        this.mNonDrawerViews.add(view);
      } 
    } 
    if (!j) {
      j = this.mNonDrawerViews.size();
      for (i = 0; i < j; i++) {
        View view = this.mNonDrawerViews.get(i);
        if (view.getVisibility() == 0)
          view.addFocusables(paramArrayList, paramInt1, paramInt2); 
      } 
    } 
    this.mNonDrawerViews.clear();
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams) {
    super.addView(paramView, paramInt, paramLayoutParams);
    if (findOpenDrawer() != null || isDrawerView(paramView)) {
      ViewCompat.setImportantForAccessibility(paramView, 4);
    } else {
      ViewCompat.setImportantForAccessibility(paramView, 1);
    } 
    if (!CAN_HIDE_DESCENDANTS)
      ViewCompat.setAccessibilityDelegate(paramView, this.mChildAccessibilityDelegate); 
  }
  
  void cancelChildViewTouch() {
    if (!this.mChildrenCanceledTouch) {
      long l = SystemClock.uptimeMillis();
      MotionEvent motionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
      int j = getChildCount();
      for (int i = 0; i < j; i++)
        getChildAt(i).dispatchTouchEvent(motionEvent); 
      motionEvent.recycle();
      this.mChildrenCanceledTouch = true;
    } 
  }
  
  boolean checkDrawerViewAbsoluteGravity(View paramView, int paramInt) {
    return ((getDrawerViewAbsoluteGravity(paramView) & paramInt) == paramInt);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams));
  }
  
  public void closeDrawer(int paramInt) {
    closeDrawer(paramInt, true);
  }
  
  public void closeDrawer(int paramInt, boolean paramBoolean) {
    View view = findDrawerWithGravity(paramInt);
    if (view == null)
      throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(paramInt)); 
    closeDrawer(view, paramBoolean);
  }
  
  public void closeDrawer(@NonNull View paramView) {
    closeDrawer(paramView, true);
  }
  
  public void closeDrawer(@NonNull View paramView, boolean paramBoolean) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a sliding drawer"); 
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (this.mFirstLayout) {
      layoutParams.onScreen = 0.0F;
      layoutParams.openState = 0;
    } else if (paramBoolean) {
      layoutParams.openState |= 0x4;
      if (checkDrawerViewAbsoluteGravity(paramView, 3)) {
        this.mLeftDragger.smoothSlideViewTo(paramView, -paramView.getWidth(), paramView.getTop());
      } else {
        this.mRightDragger.smoothSlideViewTo(paramView, getWidth(), paramView.getTop());
      } 
    } else {
      moveDrawerToOffset(paramView, 0.0F);
      updateDrawerState(layoutParams.gravity, 0, paramView);
      paramView.setVisibility(4);
    } 
    invalidate();
  }
  
  public void closeDrawers() {
    closeDrawers(false);
  }
  
  void closeDrawers(boolean paramBoolean) {
    boolean bool;
    byte b = 0;
    int j = getChildCount();
    int i = 0;
    while (i < j) {
      boolean bool1;
      View view = getChildAt(i);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      int k = b;
      if (isDrawerView(view))
        if (paramBoolean && !layoutParams.isPeeking) {
          k = b;
        } else {
          boolean bool2;
          k = view.getWidth();
          if (checkDrawerViewAbsoluteGravity(view, 3)) {
            bool2 = b | this.mLeftDragger.smoothSlideViewTo(view, -k, view.getTop());
          } else {
            bool2 |= this.mRightDragger.smoothSlideViewTo(view, getWidth(), view.getTop());
          } 
          layoutParams.isPeeking = false;
          bool1 = bool2;
        }  
      i++;
      bool = bool1;
    } 
    this.mLeftCallback.removeCallbacks();
    this.mRightCallback.removeCallbacks();
    if (bool)
      invalidate(); 
  }
  
  public void computeScroll() {
    int j = getChildCount();
    float f = 0.0F;
    for (int i = 0; i < j; i++)
      f = Math.max(f, ((LayoutParams)getChildAt(i).getLayoutParams()).onScreen); 
    this.mScrimOpacity = f;
    boolean bool1 = this.mLeftDragger.continueSettling(true);
    boolean bool2 = this.mRightDragger.continueSettling(true);
    if (bool1 || bool2)
      ViewCompat.postInvalidateOnAnimation((View)this); 
  }
  
  public boolean dispatchGenericMotionEvent(MotionEvent paramMotionEvent) {
    if ((paramMotionEvent.getSource() & 0x2) == 0 || paramMotionEvent.getAction() == 10 || this.mScrimOpacity <= 0.0F)
      return super.dispatchGenericMotionEvent(paramMotionEvent); 
    int i = getChildCount();
    if (i != 0) {
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      while (--i >= 0) {
        View view = getChildAt(i);
        if (isInBoundsOfChild(f1, f2, view) && !isContentView(view) && dispatchTransformedGenericPointerEvent(paramMotionEvent, view))
          return true; 
        i--;
      } 
    } 
    return false;
  }
  
  void dispatchOnDrawerClosed(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((layoutParams.openState & 0x1) == 1) {
      layoutParams.openState = 0;
      if (this.mListeners != null)
        for (int i = this.mListeners.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerClosed(paramView);  
      updateChildrenImportantForAccessibility(paramView, false);
      if (hasWindowFocus()) {
        paramView = getRootView();
        if (paramView != null)
          paramView.sendAccessibilityEvent(32); 
      } 
    } 
  }
  
  void dispatchOnDrawerOpened(View paramView) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if ((layoutParams.openState & 0x1) == 0) {
      layoutParams.openState = 1;
      if (this.mListeners != null)
        for (int i = this.mListeners.size() - 1; i >= 0; i--)
          ((DrawerListener)this.mListeners.get(i)).onDrawerOpened(paramView);  
      updateChildrenImportantForAccessibility(paramView, true);
      if (hasWindowFocus())
        sendAccessibilityEvent(32); 
    } 
  }
  
  void dispatchOnDrawerSlide(View paramView, float paramFloat) {
    if (this.mListeners != null)
      for (int i = this.mListeners.size() - 1; i >= 0; i--)
        ((DrawerListener)this.mListeners.get(i)).onDrawerSlide(paramView, paramFloat);  
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    int n = getHeight();
    boolean bool1 = isContentView(paramView);
    int i = 0;
    int m = 0;
    int j = getWidth();
    int i1 = paramCanvas.save();
    int k = j;
    if (bool1) {
      int i2 = getChildCount();
      k = 0;
      i = m;
      while (k < i2) {
        View view = getChildAt(k);
        m = i;
        int i3 = j;
        if (view != paramView) {
          m = i;
          i3 = j;
          if (view.getVisibility() == 0) {
            m = i;
            i3 = j;
            if (hasOpaqueBackground(view)) {
              m = i;
              i3 = j;
              if (isDrawerView(view))
                if (view.getHeight() < n) {
                  i3 = j;
                  m = i;
                } else if (checkDrawerViewAbsoluteGravity(view, 3)) {
                  int i4 = view.getRight();
                  m = i;
                  i3 = j;
                  if (i4 > i) {
                    m = i4;
                    i3 = j;
                  } 
                } else {
                  int i4 = view.getLeft();
                  m = i;
                  i3 = j;
                  if (i4 < j) {
                    i3 = i4;
                    m = i;
                  } 
                }  
            } 
          } 
        } 
        k++;
        i = m;
        j = i3;
      } 
      paramCanvas.clipRect(i, 0, j, getHeight());
      k = j;
    } 
    boolean bool2 = super.drawChild(paramCanvas, paramView, paramLong);
    paramCanvas.restoreToCount(i1);
    if (this.mScrimOpacity > 0.0F && bool1) {
      j = (int)(((this.mScrimColor & 0xFF000000) >>> 24) * this.mScrimOpacity);
      m = this.mScrimColor;
      this.mScrimPaint.setColor(j << 24 | m & 0xFFFFFF);
      paramCanvas.drawRect(i, 0.0F, k, getHeight(), this.mScrimPaint);
      return bool2;
    } 
    if (this.mShadowLeftResolved != null && checkDrawerViewAbsoluteGravity(paramView, 3)) {
      i = this.mShadowLeftResolved.getIntrinsicWidth();
      j = paramView.getRight();
      k = this.mLeftDragger.getEdgeSize();
      float f = Math.max(0.0F, Math.min(j / k, 1.0F));
      this.mShadowLeftResolved.setBounds(j, paramView.getTop(), j + i, paramView.getBottom());
      this.mShadowLeftResolved.setAlpha((int)(255.0F * f));
      this.mShadowLeftResolved.draw(paramCanvas);
      return bool2;
    } 
    if (this.mShadowRightResolved != null && checkDrawerViewAbsoluteGravity(paramView, 5)) {
      i = this.mShadowRightResolved.getIntrinsicWidth();
      j = paramView.getLeft();
      k = getWidth();
      m = this.mRightDragger.getEdgeSize();
      float f = Math.max(0.0F, Math.min((k - j) / m, 1.0F));
      this.mShadowRightResolved.setBounds(j - i, paramView.getTop(), j, paramView.getBottom());
      this.mShadowRightResolved.setAlpha((int)(255.0F * f));
      this.mShadowRightResolved.draw(paramCanvas);
      return bool2;
    } 
    return bool2;
  }
  
  View findDrawerWithGravity(int paramInt) {
    int i = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection((View)this));
    int j = getChildCount();
    for (paramInt = 0; paramInt < j; paramInt++) {
      View view = getChildAt(paramInt);
      if ((getDrawerViewAbsoluteGravity(view) & 0x7) == (i & 0x7))
        return view; 
    } 
    return null;
  }
  
  View findOpenDrawer() {
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      if ((((LayoutParams)view.getLayoutParams()).openState & 0x1) == 1)
        return view; 
    } 
    return null;
  }
  
  View findVisibleDrawer() {
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      if (isDrawerView(view) && isDrawerVisible(view))
        return view; 
    } 
    return null;
  }
  
  protected ViewGroup.LayoutParams generateDefaultLayoutParams() {
    return (ViewGroup.LayoutParams)new LayoutParams(-1, -1);
  }
  
  public ViewGroup.LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return (ViewGroup.LayoutParams)new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected ViewGroup.LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (ViewGroup.LayoutParams)((paramLayoutParams instanceof LayoutParams) ? new LayoutParams((LayoutParams)paramLayoutParams) : ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams)));
  }
  
  public float getDrawerElevation() {
    return SET_DRAWER_SHADOW_FROM_ELEVATION ? this.mDrawerElevation : 0.0F;
  }
  
  public int getDrawerLockMode(int paramInt) {
    int i = ViewCompat.getLayoutDirection((View)this);
    switch (paramInt) {
      default:
        return 0;
      case 3:
        if (this.mLockModeLeft != 3)
          return this.mLockModeLeft; 
        if (i == 0) {
          paramInt = this.mLockModeStart;
        } else {
          paramInt = this.mLockModeEnd;
        } 
        if (paramInt != 3)
          return paramInt; 
      case 5:
        if (this.mLockModeRight != 3)
          return this.mLockModeRight; 
        if (i == 0) {
          paramInt = this.mLockModeEnd;
        } else {
          paramInt = this.mLockModeStart;
        } 
        if (paramInt != 3)
          return paramInt; 
      case 8388611:
        if (this.mLockModeStart != 3)
          return this.mLockModeStart; 
        if (i == 0) {
          paramInt = this.mLockModeLeft;
        } else {
          paramInt = this.mLockModeRight;
        } 
        if (paramInt != 3)
          return paramInt; 
      case 8388613:
        break;
    } 
    if (this.mLockModeEnd != 3)
      return this.mLockModeEnd; 
    if (i == 0) {
      paramInt = this.mLockModeRight;
    } else {
      paramInt = this.mLockModeLeft;
    } 
    if (paramInt != 3)
      return paramInt; 
  }
  
  public int getDrawerLockMode(@NonNull View paramView) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a drawer"); 
    return getDrawerLockMode(((LayoutParams)paramView.getLayoutParams()).gravity);
  }
  
  @Nullable
  public CharSequence getDrawerTitle(int paramInt) {
    paramInt = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection((View)this));
    return (paramInt == 3) ? this.mTitleLeft : ((paramInt == 5) ? this.mTitleRight : null);
  }
  
  int getDrawerViewAbsoluteGravity(View paramView) {
    return GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection((View)this));
  }
  
  float getDrawerViewOffset(View paramView) {
    return ((LayoutParams)paramView.getLayoutParams()).onScreen;
  }
  
  @Nullable
  public Drawable getStatusBarBackgroundDrawable() {
    return this.mStatusBarBackground;
  }
  
  boolean isContentView(View paramView) {
    return (((LayoutParams)paramView.getLayoutParams()).gravity == 0);
  }
  
  public boolean isDrawerOpen(int paramInt) {
    View view = findDrawerWithGravity(paramInt);
    return (view != null) ? isDrawerOpen(view) : false;
  }
  
  public boolean isDrawerOpen(@NonNull View paramView) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a drawer"); 
    return ((((LayoutParams)paramView.getLayoutParams()).openState & 0x1) == 1);
  }
  
  boolean isDrawerView(View paramView) {
    int i = GravityCompat.getAbsoluteGravity(((LayoutParams)paramView.getLayoutParams()).gravity, ViewCompat.getLayoutDirection(paramView));
    return ((i & 0x3) != 0) ? true : (((i & 0x5) != 0));
  }
  
  public boolean isDrawerVisible(int paramInt) {
    View view = findDrawerWithGravity(paramInt);
    return (view != null) ? isDrawerVisible(view) : false;
  }
  
  public boolean isDrawerVisible(@NonNull View paramView) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a drawer"); 
    return (((LayoutParams)paramView.getLayoutParams()).onScreen > 0.0F);
  }
  
  void moveDrawerToOffset(View paramView, float paramFloat) {
    float f = getDrawerViewOffset(paramView);
    int i = paramView.getWidth();
    int j = (int)(i * f);
    i = (int)(i * paramFloat) - j;
    if (!checkDrawerViewAbsoluteGravity(paramView, 3))
      i = -i; 
    paramView.offsetLeftAndRight(i);
    setDrawerViewOffset(paramView, paramFloat);
  }
  
  protected void onAttachedToWindow() {
    super.onAttachedToWindow();
    this.mFirstLayout = true;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    this.mFirstLayout = true;
  }
  
  public void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
      boolean bool;
      if (Build.VERSION.SDK_INT >= 21) {
        if (this.mLastInsets != null) {
          bool = ((WindowInsets)this.mLastInsets).getSystemWindowInsetTop();
        } else {
          bool = false;
        } 
      } else {
        bool = false;
      } 
      if (bool) {
        this.mStatusBarBackground.setBounds(0, 0, getWidth(), bool);
        this.mStatusBarBackground.draw(paramCanvas);
      } 
    } 
  }
  
  public boolean onInterceptTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #7
    //   3: aload_1
    //   4: invokevirtual getActionMasked : ()I
    //   7: istore #4
    //   9: aload_0
    //   10: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   13: aload_1
    //   14: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   17: istore #8
    //   19: aload_0
    //   20: getfield mRightDragger : Landroidx/customview/widget/ViewDragHelper;
    //   23: aload_1
    //   24: invokevirtual shouldInterceptTouchEvent : (Landroid/view/MotionEvent;)Z
    //   27: istore #9
    //   29: iconst_0
    //   30: istore #6
    //   32: iconst_0
    //   33: istore #5
    //   35: iload #4
    //   37: tableswitch default -> 68, 0 -> 105, 1 -> 222, 2 -> 186, 3 -> 222
    //   68: iload #5
    //   70: istore #4
    //   72: iload #8
    //   74: iload #9
    //   76: ior
    //   77: ifne -> 99
    //   80: iload #4
    //   82: ifne -> 99
    //   85: aload_0
    //   86: invokespecial hasPeekingDrawer : ()Z
    //   89: ifne -> 99
    //   92: aload_0
    //   93: getfield mChildrenCanceledTouch : Z
    //   96: ifeq -> 102
    //   99: iconst_1
    //   100: istore #7
    //   102: iload #7
    //   104: ireturn
    //   105: aload_1
    //   106: invokevirtual getX : ()F
    //   109: fstore_2
    //   110: aload_1
    //   111: invokevirtual getY : ()F
    //   114: fstore_3
    //   115: aload_0
    //   116: fload_2
    //   117: putfield mInitialMotionX : F
    //   120: aload_0
    //   121: fload_3
    //   122: putfield mInitialMotionY : F
    //   125: iload #6
    //   127: istore #4
    //   129: aload_0
    //   130: getfield mScrimOpacity : F
    //   133: fconst_0
    //   134: fcmpl
    //   135: ifle -> 173
    //   138: aload_0
    //   139: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   142: fload_2
    //   143: f2i
    //   144: fload_3
    //   145: f2i
    //   146: invokevirtual findTopChildUnder : (II)Landroid/view/View;
    //   149: astore_1
    //   150: iload #6
    //   152: istore #4
    //   154: aload_1
    //   155: ifnull -> 173
    //   158: iload #6
    //   160: istore #4
    //   162: aload_0
    //   163: aload_1
    //   164: invokevirtual isContentView : (Landroid/view/View;)Z
    //   167: ifeq -> 173
    //   170: iconst_1
    //   171: istore #4
    //   173: aload_0
    //   174: iconst_0
    //   175: putfield mDisallowInterceptRequested : Z
    //   178: aload_0
    //   179: iconst_0
    //   180: putfield mChildrenCanceledTouch : Z
    //   183: goto -> 72
    //   186: iload #5
    //   188: istore #4
    //   190: aload_0
    //   191: getfield mLeftDragger : Landroidx/customview/widget/ViewDragHelper;
    //   194: iconst_3
    //   195: invokevirtual checkTouchSlop : (I)Z
    //   198: ifeq -> 72
    //   201: aload_0
    //   202: getfield mLeftCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   205: invokevirtual removeCallbacks : ()V
    //   208: aload_0
    //   209: getfield mRightCallback : Landroidx/drawerlayout/widget/DrawerLayout$ViewDragCallback;
    //   212: invokevirtual removeCallbacks : ()V
    //   215: iload #5
    //   217: istore #4
    //   219: goto -> 72
    //   222: aload_0
    //   223: iconst_1
    //   224: invokevirtual closeDrawers : (Z)V
    //   227: aload_0
    //   228: iconst_0
    //   229: putfield mDisallowInterceptRequested : Z
    //   232: aload_0
    //   233: iconst_0
    //   234: putfield mChildrenCanceledTouch : Z
    //   237: iload #5
    //   239: istore #4
    //   241: goto -> 72
  }
  
  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent) {
    if (paramInt == 4 && hasVisibleDrawer()) {
      paramKeyEvent.startTracking();
      return true;
    } 
    return super.onKeyDown(paramInt, paramKeyEvent);
  }
  
  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent) {
    View view;
    if (paramInt == 4) {
      view = findVisibleDrawer();
      if (view != null && getDrawerLockMode(view) == 0)
        closeDrawers(); 
      return (view != null);
    } 
    return super.onKeyUp(paramInt, (KeyEvent)view);
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: putfield mInLayout : Z
    //   5: iload #4
    //   7: iload_2
    //   8: isub
    //   9: istore #10
    //   11: aload_0
    //   12: invokevirtual getChildCount : ()I
    //   15: istore #11
    //   17: iconst_0
    //   18: istore #4
    //   20: iload #4
    //   22: iload #11
    //   24: if_icmpge -> 446
    //   27: aload_0
    //   28: iload #4
    //   30: invokevirtual getChildAt : (I)Landroid/view/View;
    //   33: astore #15
    //   35: aload #15
    //   37: invokevirtual getVisibility : ()I
    //   40: bipush #8
    //   42: if_icmpne -> 54
    //   45: iload #4
    //   47: iconst_1
    //   48: iadd
    //   49: istore #4
    //   51: goto -> 20
    //   54: aload #15
    //   56: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   59: checkcast androidx/drawerlayout/widget/DrawerLayout$LayoutParams
    //   62: astore #16
    //   64: aload_0
    //   65: aload #15
    //   67: invokevirtual isContentView : (Landroid/view/View;)Z
    //   70: ifeq -> 113
    //   73: aload #15
    //   75: aload #16
    //   77: getfield leftMargin : I
    //   80: aload #16
    //   82: getfield topMargin : I
    //   85: aload #16
    //   87: getfield leftMargin : I
    //   90: aload #15
    //   92: invokevirtual getMeasuredWidth : ()I
    //   95: iadd
    //   96: aload #16
    //   98: getfield topMargin : I
    //   101: aload #15
    //   103: invokevirtual getMeasuredHeight : ()I
    //   106: iadd
    //   107: invokevirtual layout : (IIII)V
    //   110: goto -> 45
    //   113: aload #15
    //   115: invokevirtual getMeasuredWidth : ()I
    //   118: istore #12
    //   120: aload #15
    //   122: invokevirtual getMeasuredHeight : ()I
    //   125: istore #13
    //   127: aload_0
    //   128: aload #15
    //   130: iconst_3
    //   131: invokevirtual checkDrawerViewAbsoluteGravity : (Landroid/view/View;I)Z
    //   134: ifeq -> 280
    //   137: iload #12
    //   139: ineg
    //   140: iload #12
    //   142: i2f
    //   143: aload #16
    //   145: getfield onScreen : F
    //   148: fmul
    //   149: f2i
    //   150: iadd
    //   151: istore #7
    //   153: iload #12
    //   155: iload #7
    //   157: iadd
    //   158: i2f
    //   159: iload #12
    //   161: i2f
    //   162: fdiv
    //   163: fstore #6
    //   165: fload #6
    //   167: aload #16
    //   169: getfield onScreen : F
    //   172: fcmpl
    //   173: ifeq -> 310
    //   176: iconst_1
    //   177: istore #8
    //   179: aload #16
    //   181: getfield gravity : I
    //   184: bipush #112
    //   186: iand
    //   187: lookupswitch default -> 212, 16 -> 356, 80 -> 316
    //   212: aload #15
    //   214: iload #7
    //   216: aload #16
    //   218: getfield topMargin : I
    //   221: iload #7
    //   223: iload #12
    //   225: iadd
    //   226: aload #16
    //   228: getfield topMargin : I
    //   231: iload #13
    //   233: iadd
    //   234: invokevirtual layout : (IIII)V
    //   237: iload #8
    //   239: ifeq -> 250
    //   242: aload_0
    //   243: aload #15
    //   245: fload #6
    //   247: invokevirtual setDrawerViewOffset : (Landroid/view/View;F)V
    //   250: aload #16
    //   252: getfield onScreen : F
    //   255: fconst_0
    //   256: fcmpl
    //   257: ifle -> 441
    //   260: iconst_0
    //   261: istore_2
    //   262: aload #15
    //   264: invokevirtual getVisibility : ()I
    //   267: iload_2
    //   268: if_icmpeq -> 45
    //   271: aload #15
    //   273: iload_2
    //   274: invokevirtual setVisibility : (I)V
    //   277: goto -> 45
    //   280: iload #10
    //   282: iload #12
    //   284: i2f
    //   285: aload #16
    //   287: getfield onScreen : F
    //   290: fmul
    //   291: f2i
    //   292: isub
    //   293: istore #7
    //   295: iload #10
    //   297: iload #7
    //   299: isub
    //   300: i2f
    //   301: iload #12
    //   303: i2f
    //   304: fdiv
    //   305: fstore #6
    //   307: goto -> 165
    //   310: iconst_0
    //   311: istore #8
    //   313: goto -> 179
    //   316: iload #5
    //   318: iload_3
    //   319: isub
    //   320: istore_2
    //   321: aload #15
    //   323: iload #7
    //   325: iload_2
    //   326: aload #16
    //   328: getfield bottomMargin : I
    //   331: isub
    //   332: aload #15
    //   334: invokevirtual getMeasuredHeight : ()I
    //   337: isub
    //   338: iload #7
    //   340: iload #12
    //   342: iadd
    //   343: iload_2
    //   344: aload #16
    //   346: getfield bottomMargin : I
    //   349: isub
    //   350: invokevirtual layout : (IIII)V
    //   353: goto -> 237
    //   356: iload #5
    //   358: iload_3
    //   359: isub
    //   360: istore #14
    //   362: iload #14
    //   364: iload #13
    //   366: isub
    //   367: iconst_2
    //   368: idiv
    //   369: istore #9
    //   371: iload #9
    //   373: aload #16
    //   375: getfield topMargin : I
    //   378: if_icmpge -> 407
    //   381: aload #16
    //   383: getfield topMargin : I
    //   386: istore_2
    //   387: aload #15
    //   389: iload #7
    //   391: iload_2
    //   392: iload #7
    //   394: iload #12
    //   396: iadd
    //   397: iload_2
    //   398: iload #13
    //   400: iadd
    //   401: invokevirtual layout : (IIII)V
    //   404: goto -> 237
    //   407: iload #9
    //   409: istore_2
    //   410: iload #9
    //   412: iload #13
    //   414: iadd
    //   415: iload #14
    //   417: aload #16
    //   419: getfield bottomMargin : I
    //   422: isub
    //   423: if_icmple -> 387
    //   426: iload #14
    //   428: aload #16
    //   430: getfield bottomMargin : I
    //   433: isub
    //   434: iload #13
    //   436: isub
    //   437: istore_2
    //   438: goto -> 387
    //   441: iconst_4
    //   442: istore_2
    //   443: goto -> 262
    //   446: aload_0
    //   447: iconst_0
    //   448: putfield mInLayout : Z
    //   451: aload_0
    //   452: iconst_0
    //   453: putfield mFirstLayout : Z
    //   456: return
  }
  
  @SuppressLint({"WrongConstant"})
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: iload_1
    //   1: invokestatic getMode : (I)I
    //   4: istore #8
    //   6: iload_2
    //   7: invokestatic getMode : (I)I
    //   10: istore #7
    //   12: iload_1
    //   13: invokestatic getSize : (I)I
    //   16: istore_3
    //   17: iload_2
    //   18: invokestatic getSize : (I)I
    //   21: istore #4
    //   23: iload #8
    //   25: ldc_w 1073741824
    //   28: if_icmpne -> 46
    //   31: iload #4
    //   33: istore #5
    //   35: iload_3
    //   36: istore #6
    //   38: iload #7
    //   40: ldc_w 1073741824
    //   43: if_icmpeq -> 76
    //   46: aload_0
    //   47: invokevirtual isInEditMode : ()Z
    //   50: ifeq -> 190
    //   53: iload #8
    //   55: ldc_w -2147483648
    //   58: if_icmpne -> 155
    //   61: iload #7
    //   63: ldc_w -2147483648
    //   66: if_icmpne -> 167
    //   69: iload_3
    //   70: istore #6
    //   72: iload #4
    //   74: istore #5
    //   76: aload_0
    //   77: iload #6
    //   79: iload #5
    //   81: invokevirtual setMeasuredDimension : (II)V
    //   84: aload_0
    //   85: getfield mLastInsets : Ljava/lang/Object;
    //   88: ifnull -> 201
    //   91: aload_0
    //   92: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   95: ifeq -> 201
    //   98: iconst_1
    //   99: istore #7
    //   101: aload_0
    //   102: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   105: istore #10
    //   107: iconst_0
    //   108: istore #4
    //   110: iconst_0
    //   111: istore_3
    //   112: aload_0
    //   113: invokevirtual getChildCount : ()I
    //   116: istore #11
    //   118: iconst_0
    //   119: istore #8
    //   121: iload #8
    //   123: iload #11
    //   125: if_icmpge -> 769
    //   128: aload_0
    //   129: iload #8
    //   131: invokevirtual getChildAt : (I)Landroid/view/View;
    //   134: astore #15
    //   136: aload #15
    //   138: invokevirtual getVisibility : ()I
    //   141: bipush #8
    //   143: if_icmpne -> 207
    //   146: iload #8
    //   148: iconst_1
    //   149: iadd
    //   150: istore #8
    //   152: goto -> 121
    //   155: iload #8
    //   157: ifne -> 61
    //   160: sipush #300
    //   163: istore_3
    //   164: goto -> 61
    //   167: iload #4
    //   169: istore #5
    //   171: iload_3
    //   172: istore #6
    //   174: iload #7
    //   176: ifne -> 76
    //   179: sipush #300
    //   182: istore #5
    //   184: iload_3
    //   185: istore #6
    //   187: goto -> 76
    //   190: new java/lang/IllegalArgumentException
    //   193: dup
    //   194: ldc_w 'DrawerLayout must be measured with MeasureSpec.EXACTLY.'
    //   197: invokespecial <init> : (Ljava/lang/String;)V
    //   200: athrow
    //   201: iconst_0
    //   202: istore #7
    //   204: goto -> 101
    //   207: aload #15
    //   209: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   212: checkcast androidx/drawerlayout/widget/DrawerLayout$LayoutParams
    //   215: astore #16
    //   217: iload #7
    //   219: ifeq -> 296
    //   222: aload #16
    //   224: getfield gravity : I
    //   227: iload #10
    //   229: invokestatic getAbsoluteGravity : (II)I
    //   232: istore #9
    //   234: aload #15
    //   236: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   239: ifeq -> 389
    //   242: getstatic android/os/Build$VERSION.SDK_INT : I
    //   245: bipush #21
    //   247: if_icmplt -> 296
    //   250: aload_0
    //   251: getfield mLastInsets : Ljava/lang/Object;
    //   254: checkcast android/view/WindowInsets
    //   257: astore #14
    //   259: iload #9
    //   261: iconst_3
    //   262: if_icmpne -> 353
    //   265: aload #14
    //   267: aload #14
    //   269: invokevirtual getSystemWindowInsetLeft : ()I
    //   272: aload #14
    //   274: invokevirtual getSystemWindowInsetTop : ()I
    //   277: iconst_0
    //   278: aload #14
    //   280: invokevirtual getSystemWindowInsetBottom : ()I
    //   283: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   286: astore #13
    //   288: aload #15
    //   290: aload #13
    //   292: invokevirtual dispatchApplyWindowInsets : (Landroid/view/WindowInsets;)Landroid/view/WindowInsets;
    //   295: pop
    //   296: aload_0
    //   297: aload #15
    //   299: invokevirtual isContentView : (Landroid/view/View;)Z
    //   302: ifeq -> 514
    //   305: aload #15
    //   307: iload #6
    //   309: aload #16
    //   311: getfield leftMargin : I
    //   314: isub
    //   315: aload #16
    //   317: getfield rightMargin : I
    //   320: isub
    //   321: ldc_w 1073741824
    //   324: invokestatic makeMeasureSpec : (II)I
    //   327: iload #5
    //   329: aload #16
    //   331: getfield topMargin : I
    //   334: isub
    //   335: aload #16
    //   337: getfield bottomMargin : I
    //   340: isub
    //   341: ldc_w 1073741824
    //   344: invokestatic makeMeasureSpec : (II)I
    //   347: invokevirtual measure : (II)V
    //   350: goto -> 146
    //   353: aload #14
    //   355: astore #13
    //   357: iload #9
    //   359: iconst_5
    //   360: if_icmpne -> 288
    //   363: aload #14
    //   365: iconst_0
    //   366: aload #14
    //   368: invokevirtual getSystemWindowInsetTop : ()I
    //   371: aload #14
    //   373: invokevirtual getSystemWindowInsetRight : ()I
    //   376: aload #14
    //   378: invokevirtual getSystemWindowInsetBottom : ()I
    //   381: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   384: astore #13
    //   386: goto -> 288
    //   389: getstatic android/os/Build$VERSION.SDK_INT : I
    //   392: bipush #21
    //   394: if_icmplt -> 296
    //   397: aload_0
    //   398: getfield mLastInsets : Ljava/lang/Object;
    //   401: checkcast android/view/WindowInsets
    //   404: astore #14
    //   406: iload #9
    //   408: iconst_3
    //   409: if_icmpne -> 478
    //   412: aload #14
    //   414: aload #14
    //   416: invokevirtual getSystemWindowInsetLeft : ()I
    //   419: aload #14
    //   421: invokevirtual getSystemWindowInsetTop : ()I
    //   424: iconst_0
    //   425: aload #14
    //   427: invokevirtual getSystemWindowInsetBottom : ()I
    //   430: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   433: astore #13
    //   435: aload #16
    //   437: aload #13
    //   439: invokevirtual getSystemWindowInsetLeft : ()I
    //   442: putfield leftMargin : I
    //   445: aload #16
    //   447: aload #13
    //   449: invokevirtual getSystemWindowInsetTop : ()I
    //   452: putfield topMargin : I
    //   455: aload #16
    //   457: aload #13
    //   459: invokevirtual getSystemWindowInsetRight : ()I
    //   462: putfield rightMargin : I
    //   465: aload #16
    //   467: aload #13
    //   469: invokevirtual getSystemWindowInsetBottom : ()I
    //   472: putfield bottomMargin : I
    //   475: goto -> 296
    //   478: aload #14
    //   480: astore #13
    //   482: iload #9
    //   484: iconst_5
    //   485: if_icmpne -> 435
    //   488: aload #14
    //   490: iconst_0
    //   491: aload #14
    //   493: invokevirtual getSystemWindowInsetTop : ()I
    //   496: aload #14
    //   498: invokevirtual getSystemWindowInsetRight : ()I
    //   501: aload #14
    //   503: invokevirtual getSystemWindowInsetBottom : ()I
    //   506: invokevirtual replaceSystemWindowInsets : (IIII)Landroid/view/WindowInsets;
    //   509: astore #13
    //   511: goto -> 435
    //   514: aload_0
    //   515: aload #15
    //   517: invokevirtual isDrawerView : (Landroid/view/View;)Z
    //   520: ifeq -> 717
    //   523: getstatic androidx/drawerlayout/widget/DrawerLayout.SET_DRAWER_SHADOW_FROM_ELEVATION : Z
    //   526: ifeq -> 551
    //   529: aload #15
    //   531: invokestatic getElevation : (Landroid/view/View;)F
    //   534: aload_0
    //   535: getfield mDrawerElevation : F
    //   538: fcmpl
    //   539: ifeq -> 551
    //   542: aload #15
    //   544: aload_0
    //   545: getfield mDrawerElevation : F
    //   548: invokestatic setElevation : (Landroid/view/View;F)V
    //   551: aload_0
    //   552: aload #15
    //   554: invokevirtual getDrawerViewAbsoluteGravity : (Landroid/view/View;)I
    //   557: bipush #7
    //   559: iand
    //   560: istore #12
    //   562: iload #12
    //   564: iconst_3
    //   565: if_icmpne -> 645
    //   568: iconst_1
    //   569: istore #9
    //   571: iload #9
    //   573: ifeq -> 581
    //   576: iload #4
    //   578: ifne -> 590
    //   581: iload #9
    //   583: ifne -> 651
    //   586: iload_3
    //   587: ifeq -> 651
    //   590: new java/lang/IllegalStateException
    //   593: dup
    //   594: new java/lang/StringBuilder
    //   597: dup
    //   598: invokespecial <init> : ()V
    //   601: ldc_w 'Child drawer has absolute gravity '
    //   604: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   607: iload #12
    //   609: invokestatic gravityToString : (I)Ljava/lang/String;
    //   612: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   615: ldc_w ' but this '
    //   618: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   621: ldc 'DrawerLayout'
    //   623: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   626: ldc_w ' already has a '
    //   629: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   632: ldc_w 'drawer view along that edge'
    //   635: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   638: invokevirtual toString : ()Ljava/lang/String;
    //   641: invokespecial <init> : (Ljava/lang/String;)V
    //   644: athrow
    //   645: iconst_0
    //   646: istore #9
    //   648: goto -> 571
    //   651: iload #9
    //   653: ifeq -> 712
    //   656: iconst_1
    //   657: istore #4
    //   659: aload #15
    //   661: iload_1
    //   662: aload_0
    //   663: getfield mMinDrawerMargin : I
    //   666: aload #16
    //   668: getfield leftMargin : I
    //   671: iadd
    //   672: aload #16
    //   674: getfield rightMargin : I
    //   677: iadd
    //   678: aload #16
    //   680: getfield width : I
    //   683: invokestatic getChildMeasureSpec : (III)I
    //   686: iload_2
    //   687: aload #16
    //   689: getfield topMargin : I
    //   692: aload #16
    //   694: getfield bottomMargin : I
    //   697: iadd
    //   698: aload #16
    //   700: getfield height : I
    //   703: invokestatic getChildMeasureSpec : (III)I
    //   706: invokevirtual measure : (II)V
    //   709: goto -> 146
    //   712: iconst_1
    //   713: istore_3
    //   714: goto -> 659
    //   717: new java/lang/IllegalStateException
    //   720: dup
    //   721: new java/lang/StringBuilder
    //   724: dup
    //   725: invokespecial <init> : ()V
    //   728: ldc_w 'Child '
    //   731: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   734: aload #15
    //   736: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   739: ldc_w ' at index '
    //   742: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   745: iload #8
    //   747: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   750: ldc_w ' does not have a valid layout_gravity - must be Gravity.LEFT, '
    //   753: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   756: ldc_w 'Gravity.RIGHT or Gravity.NO_GRAVITY'
    //   759: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   762: invokevirtual toString : ()Ljava/lang/String;
    //   765: invokespecial <init> : (Ljava/lang/String;)V
    //   768: athrow
    //   769: return
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    if (savedState.openDrawerGravity != 0) {
      View view = findDrawerWithGravity(savedState.openDrawerGravity);
      if (view != null)
        openDrawer(view); 
    } 
    if (savedState.lockModeLeft != 3)
      setDrawerLockMode(savedState.lockModeLeft, 3); 
    if (savedState.lockModeRight != 3)
      setDrawerLockMode(savedState.lockModeRight, 5); 
    if (savedState.lockModeStart != 3)
      setDrawerLockMode(savedState.lockModeStart, 8388611); 
    if (savedState.lockModeEnd != 3) {
      setDrawerLockMode(savedState.lockModeEnd, 8388613);
      return;
    } 
  }
  
  public void onRtlPropertiesChanged(int paramInt) {
    resolveShadowDrawables();
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    int j = getChildCount();
    for (int i = 0;; i++) {
      if (i < j) {
        boolean bool1;
        boolean bool2;
        LayoutParams layoutParams = (LayoutParams)getChildAt(i).getLayoutParams();
        if (layoutParams.openState == 1) {
          bool1 = true;
        } else {
          bool1 = false;
        } 
        if (layoutParams.openState == 2) {
          bool2 = true;
        } else {
          bool2 = false;
        } 
        if (bool1 || bool2) {
          savedState.openDrawerGravity = layoutParams.gravity;
          savedState.lockModeLeft = this.mLockModeLeft;
          savedState.lockModeRight = this.mLockModeRight;
          savedState.lockModeStart = this.mLockModeStart;
          savedState.lockModeEnd = this.mLockModeEnd;
          return (Parcelable)savedState;
        } 
      } else {
        savedState.lockModeLeft = this.mLockModeLeft;
        savedState.lockModeRight = this.mLockModeRight;
        savedState.lockModeStart = this.mLockModeStart;
        savedState.lockModeEnd = this.mLockModeEnd;
        return (Parcelable)savedState;
      } 
    } 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    View view;
    float f1;
    float f2;
    boolean bool1;
    boolean bool2;
    this.mLeftDragger.processTouchEvent(paramMotionEvent);
    this.mRightDragger.processTouchEvent(paramMotionEvent);
    switch (paramMotionEvent.getAction() & 0xFF) {
      default:
        return true;
      case 0:
        f1 = paramMotionEvent.getX();
        f2 = paramMotionEvent.getY();
        this.mInitialMotionX = f1;
        this.mInitialMotionY = f2;
        this.mDisallowInterceptRequested = false;
        this.mChildrenCanceledTouch = false;
        return true;
      case 1:
        f2 = paramMotionEvent.getX();
        f1 = paramMotionEvent.getY();
        bool2 = true;
        view = this.mLeftDragger.findTopChildUnder((int)f2, (int)f1);
        bool1 = bool2;
        if (view != null) {
          bool1 = bool2;
          if (isContentView(view)) {
            f2 -= this.mInitialMotionX;
            f1 -= this.mInitialMotionY;
            int i = this.mLeftDragger.getTouchSlop();
            bool1 = bool2;
            if (f2 * f2 + f1 * f1 < (i * i)) {
              view = findOpenDrawer();
              bool1 = bool2;
              if (view != null) {
                if (getDrawerLockMode(view) == 2) {
                  bool1 = true;
                  closeDrawers(bool1);
                  this.mDisallowInterceptRequested = false;
                  return true;
                } 
              } else {
                closeDrawers(bool1);
                this.mDisallowInterceptRequested = false;
                return true;
              } 
            } else {
              closeDrawers(bool1);
              this.mDisallowInterceptRequested = false;
              return true;
            } 
          } else {
            closeDrawers(bool1);
            this.mDisallowInterceptRequested = false;
            return true;
          } 
        } else {
          closeDrawers(bool1);
          this.mDisallowInterceptRequested = false;
          return true;
        } 
        bool1 = false;
        closeDrawers(bool1);
        this.mDisallowInterceptRequested = false;
        return true;
      case 3:
        break;
    } 
    closeDrawers(true);
    this.mDisallowInterceptRequested = false;
    this.mChildrenCanceledTouch = false;
    return true;
  }
  
  public void openDrawer(int paramInt) {
    openDrawer(paramInt, true);
  }
  
  public void openDrawer(int paramInt, boolean paramBoolean) {
    View view = findDrawerWithGravity(paramInt);
    if (view == null)
      throw new IllegalArgumentException("No drawer view found with gravity " + gravityToString(paramInt)); 
    openDrawer(view, paramBoolean);
  }
  
  public void openDrawer(@NonNull View paramView) {
    openDrawer(paramView, true);
  }
  
  public void openDrawer(@NonNull View paramView, boolean paramBoolean) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a sliding drawer"); 
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (this.mFirstLayout) {
      layoutParams.onScreen = 1.0F;
      layoutParams.openState = 1;
      updateChildrenImportantForAccessibility(paramView, true);
    } else if (paramBoolean) {
      layoutParams.openState |= 0x2;
      if (checkDrawerViewAbsoluteGravity(paramView, 3)) {
        this.mLeftDragger.smoothSlideViewTo(paramView, 0, paramView.getTop());
      } else {
        this.mRightDragger.smoothSlideViewTo(paramView, getWidth() - paramView.getWidth(), paramView.getTop());
      } 
    } else {
      moveDrawerToOffset(paramView, 1.0F);
      updateDrawerState(layoutParams.gravity, 0, paramView);
      paramView.setVisibility(0);
    } 
    invalidate();
  }
  
  public void removeDrawerListener(@NonNull DrawerListener paramDrawerListener) {
    if (paramDrawerListener != null && this.mListeners != null) {
      this.mListeners.remove(paramDrawerListener);
      return;
    } 
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    this.mDisallowInterceptRequested = paramBoolean;
    if (paramBoolean)
      closeDrawers(true); 
  }
  
  public void requestLayout() {
    if (!this.mInLayout)
      super.requestLayout(); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setChildInsets(Object paramObject, boolean paramBoolean) {
    this.mLastInsets = paramObject;
    this.mDrawStatusBarBackground = paramBoolean;
    if (!paramBoolean && getBackground() == null) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    } 
    setWillNotDraw(paramBoolean);
    requestLayout();
  }
  
  public void setDrawerElevation(float paramFloat) {
    this.mDrawerElevation = paramFloat;
    for (int i = 0; i < getChildCount(); i++) {
      View view = getChildAt(i);
      if (isDrawerView(view))
        ViewCompat.setElevation(view, this.mDrawerElevation); 
    } 
  }
  
  @Deprecated
  public void setDrawerListener(DrawerListener paramDrawerListener) {
    if (this.mListener != null)
      removeDrawerListener(this.mListener); 
    if (paramDrawerListener != null)
      addDrawerListener(paramDrawerListener); 
    this.mListener = paramDrawerListener;
  }
  
  public void setDrawerLockMode(int paramInt) {
    setDrawerLockMode(paramInt, 3);
    setDrawerLockMode(paramInt, 5);
  }
  
  public void setDrawerLockMode(int paramInt1, int paramInt2) {
    int i = GravityCompat.getAbsoluteGravity(paramInt2, ViewCompat.getLayoutDirection((View)this));
    switch (paramInt2) {
      default:
        if (paramInt1 != 0) {
          ViewDragHelper viewDragHelper;
          if (i == 3) {
            viewDragHelper = this.mLeftDragger;
          } else {
            viewDragHelper = this.mRightDragger;
          } 
          viewDragHelper.cancel();
        } 
        switch (paramInt1) {
          default:
            return;
          case 2:
            view = findDrawerWithGravity(i);
            if (view != null) {
              openDrawer(view);
              return;
            } 
          case 1:
            break;
        } 
        break;
      case 3:
        this.mLockModeLeft = paramInt1;
      case 5:
        this.mLockModeRight = paramInt1;
      case 8388611:
        this.mLockModeStart = paramInt1;
      case 8388613:
        this.mLockModeEnd = paramInt1;
    } 
    View view = findDrawerWithGravity(i);
    if (view != null) {
      closeDrawer(view);
      return;
    } 
  }
  
  public void setDrawerLockMode(int paramInt, @NonNull View paramView) {
    if (!isDrawerView(paramView))
      throw new IllegalArgumentException("View " + paramView + " is not a " + "drawer with appropriate layout_gravity"); 
    setDrawerLockMode(paramInt, ((LayoutParams)paramView.getLayoutParams()).gravity);
  }
  
  public void setDrawerShadow(@DrawableRes int paramInt1, int paramInt2) {
    setDrawerShadow(ContextCompat.getDrawable(getContext(), paramInt1), paramInt2);
  }
  
  public void setDrawerShadow(Drawable paramDrawable, int paramInt) {
    if (!SET_DRAWER_SHADOW_FROM_ELEVATION) {
      if ((paramInt & 0x800003) == 8388611) {
        this.mShadowStart = paramDrawable;
      } else if ((paramInt & 0x800005) == 8388613) {
        this.mShadowEnd = paramDrawable;
      } else if ((paramInt & 0x3) == 3) {
        this.mShadowLeft = paramDrawable;
      } else if ((paramInt & 0x5) == 5) {
        this.mShadowRight = paramDrawable;
      } else {
        return;
      } 
      resolveShadowDrawables();
      invalidate();
      return;
    } 
  }
  
  public void setDrawerTitle(int paramInt, @Nullable CharSequence paramCharSequence) {
    paramInt = GravityCompat.getAbsoluteGravity(paramInt, ViewCompat.getLayoutDirection((View)this));
    if (paramInt == 3) {
      this.mTitleLeft = paramCharSequence;
      return;
    } 
    if (paramInt == 5) {
      this.mTitleRight = paramCharSequence;
      return;
    } 
  }
  
  void setDrawerViewOffset(View paramView, float paramFloat) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (paramFloat == layoutParams.onScreen)
      return; 
    layoutParams.onScreen = paramFloat;
    dispatchOnDrawerSlide(paramView, paramFloat);
  }
  
  public void setScrimColor(@ColorInt int paramInt) {
    this.mScrimColor = paramInt;
    invalidate();
  }
  
  public void setStatusBarBackground(int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = ContextCompat.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    this.mStatusBarBackground = drawable;
    invalidate();
  }
  
  public void setStatusBarBackground(@Nullable Drawable paramDrawable) {
    this.mStatusBarBackground = paramDrawable;
    invalidate();
  }
  
  public void setStatusBarBackgroundColor(@ColorInt int paramInt) {
    this.mStatusBarBackground = (Drawable)new ColorDrawable(paramInt);
    invalidate();
  }
  
  void updateDrawerState(int paramInt1, int paramInt2, View paramView) {
    paramInt1 = this.mLeftDragger.getViewDragState();
    int i = this.mRightDragger.getViewDragState();
    if (paramInt1 == 1 || i == 1) {
      paramInt1 = 1;
    } else if (paramInt1 == 2 || i == 2) {
      paramInt1 = 2;
    } else {
      paramInt1 = 0;
    } 
    if (paramView != null && paramInt2 == 0) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      if (layoutParams.onScreen == 0.0F) {
        dispatchOnDrawerClosed(paramView);
      } else if (layoutParams.onScreen == 1.0F) {
        dispatchOnDrawerOpened(paramView);
      } 
    } 
    if (paramInt1 != this.mDrawerState) {
      this.mDrawerState = paramInt1;
      if (this.mListeners != null)
        for (paramInt2 = this.mListeners.size() - 1; paramInt2 >= 0; paramInt2--)
          ((DrawerListener)this.mListeners.get(paramInt2)).onDrawerStateChanged(paramInt1);  
    } 
  }
  
  static {
    boolean bool1;
    boolean bool2 = true;
  }
  
  class AccessibilityDelegate extends AccessibilityDelegateCompat {
    private final Rect mTmpRect = new Rect();
    
    private void addChildrenForAccessibility(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat, ViewGroup param1ViewGroup) {
      int j = param1ViewGroup.getChildCount();
      for (int i = 0; i < j; i++) {
        View view = param1ViewGroup.getChildAt(i);
        if (DrawerLayout.includeChildForAccessibility(view))
          param1AccessibilityNodeInfoCompat.addChild(view); 
      } 
    }
    
    private void copyNodeInfoNoChildren(AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat1, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat2) {
      Rect rect = this.mTmpRect;
      param1AccessibilityNodeInfoCompat2.getBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInParent(rect);
      param1AccessibilityNodeInfoCompat2.getBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setBoundsInScreen(rect);
      param1AccessibilityNodeInfoCompat1.setVisibleToUser(param1AccessibilityNodeInfoCompat2.isVisibleToUser());
      param1AccessibilityNodeInfoCompat1.setPackageName(param1AccessibilityNodeInfoCompat2.getPackageName());
      param1AccessibilityNodeInfoCompat1.setClassName(param1AccessibilityNodeInfoCompat2.getClassName());
      param1AccessibilityNodeInfoCompat1.setContentDescription(param1AccessibilityNodeInfoCompat2.getContentDescription());
      param1AccessibilityNodeInfoCompat1.setEnabled(param1AccessibilityNodeInfoCompat2.isEnabled());
      param1AccessibilityNodeInfoCompat1.setClickable(param1AccessibilityNodeInfoCompat2.isClickable());
      param1AccessibilityNodeInfoCompat1.setFocusable(param1AccessibilityNodeInfoCompat2.isFocusable());
      param1AccessibilityNodeInfoCompat1.setFocused(param1AccessibilityNodeInfoCompat2.isFocused());
      param1AccessibilityNodeInfoCompat1.setAccessibilityFocused(param1AccessibilityNodeInfoCompat2.isAccessibilityFocused());
      param1AccessibilityNodeInfoCompat1.setSelected(param1AccessibilityNodeInfoCompat2.isSelected());
      param1AccessibilityNodeInfoCompat1.setLongClickable(param1AccessibilityNodeInfoCompat2.isLongClickable());
      param1AccessibilityNodeInfoCompat1.addAction(param1AccessibilityNodeInfoCompat2.getActions());
    }
    
    public boolean dispatchPopulateAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      List<CharSequence> list;
      CharSequence charSequence;
      if (param1AccessibilityEvent.getEventType() == 32) {
        list = param1AccessibilityEvent.getText();
        View view = DrawerLayout.this.findVisibleDrawer();
        if (view != null) {
          int i = DrawerLayout.this.getDrawerViewAbsoluteGravity(view);
          charSequence = DrawerLayout.this.getDrawerTitle(i);
          if (charSequence != null)
            list.add(charSequence); 
        } 
        return true;
      } 
      return super.dispatchPopulateAccessibilityEvent((View)list, (AccessibilityEvent)charSequence);
    }
    
    public void onInitializeAccessibilityEvent(View param1View, AccessibilityEvent param1AccessibilityEvent) {
      super.onInitializeAccessibilityEvent(param1View, param1AccessibilityEvent);
      param1AccessibilityEvent.setClassName(DrawerLayout.class.getName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      if (DrawerLayout.CAN_HIDE_DESCENDANTS) {
        super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      } else {
        AccessibilityNodeInfoCompat accessibilityNodeInfoCompat = AccessibilityNodeInfoCompat.obtain(param1AccessibilityNodeInfoCompat);
        super.onInitializeAccessibilityNodeInfo(param1View, accessibilityNodeInfoCompat);
        param1AccessibilityNodeInfoCompat.setSource(param1View);
        ViewParent viewParent = ViewCompat.getParentForAccessibility(param1View);
        if (viewParent instanceof View)
          param1AccessibilityNodeInfoCompat.setParent((View)viewParent); 
        copyNodeInfoNoChildren(param1AccessibilityNodeInfoCompat, accessibilityNodeInfoCompat);
        accessibilityNodeInfoCompat.recycle();
        addChildrenForAccessibility(param1AccessibilityNodeInfoCompat, (ViewGroup)param1View);
      } 
      param1AccessibilityNodeInfoCompat.setClassName(DrawerLayout.class.getName());
      param1AccessibilityNodeInfoCompat.setFocusable(false);
      param1AccessibilityNodeInfoCompat.setFocused(false);
      param1AccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_FOCUS);
      param1AccessibilityNodeInfoCompat.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLEAR_FOCUS);
    }
    
    public boolean onRequestSendAccessibilityEvent(ViewGroup param1ViewGroup, View param1View, AccessibilityEvent param1AccessibilityEvent) {
      return (DrawerLayout.CAN_HIDE_DESCENDANTS || DrawerLayout.includeChildForAccessibility(param1View)) ? super.onRequestSendAccessibilityEvent(param1ViewGroup, param1View, param1AccessibilityEvent) : false;
    }
  }
  
  static final class ChildAccessibilityDelegate extends AccessibilityDelegateCompat {
    public void onInitializeAccessibilityNodeInfo(View param1View, AccessibilityNodeInfoCompat param1AccessibilityNodeInfoCompat) {
      super.onInitializeAccessibilityNodeInfo(param1View, param1AccessibilityNodeInfoCompat);
      if (!DrawerLayout.includeChildForAccessibility(param1View))
        param1AccessibilityNodeInfoCompat.setParent(null); 
    }
  }
  
  public static interface DrawerListener {
    void onDrawerClosed(@NonNull View param1View);
    
    void onDrawerOpened(@NonNull View param1View);
    
    void onDrawerSlide(@NonNull View param1View, float param1Float);
    
    void onDrawerStateChanged(int param1Int);
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    private static final int FLAG_IS_CLOSING = 4;
    
    private static final int FLAG_IS_OPENED = 1;
    
    private static final int FLAG_IS_OPENING = 2;
    
    public int gravity = 0;
    
    boolean isPeeking;
    
    float onScreen;
    
    int openState;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    public LayoutParams(int param1Int1, int param1Int2, int param1Int3) {
      this(param1Int1, param1Int2);
      this.gravity = param1Int3;
    }
    
    public LayoutParams(@NonNull Context param1Context, @Nullable AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, DrawerLayout.LAYOUT_ATTRS);
      this.gravity = typedArray.getInt(0, 0);
      typedArray.recycle();
    }
    
    public LayoutParams(@NonNull ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(@NonNull ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public LayoutParams(@NonNull LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.gravity = param1LayoutParams.gravity;
    }
  }
  
  protected static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public DrawerLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new DrawerLayout.SavedState(param2Parcel, null);
        }
        
        public DrawerLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new DrawerLayout.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public DrawerLayout.SavedState[] newArray(int param2Int) {
          return new DrawerLayout.SavedState[param2Int];
        }
      };
    
    int lockModeEnd;
    
    int lockModeLeft;
    
    int lockModeRight;
    
    int lockModeStart;
    
    int openDrawerGravity = 0;
    
    public SavedState(@NonNull Parcel param1Parcel, @Nullable ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      this.openDrawerGravity = param1Parcel.readInt();
      this.lockModeLeft = param1Parcel.readInt();
      this.lockModeRight = param1Parcel.readInt();
      this.lockModeStart = param1Parcel.readInt();
      this.lockModeEnd = param1Parcel.readInt();
    }
    
    public SavedState(@NonNull Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      super.writeToParcel(param1Parcel, param1Int);
      param1Parcel.writeInt(this.openDrawerGravity);
      param1Parcel.writeInt(this.lockModeLeft);
      param1Parcel.writeInt(this.lockModeRight);
      param1Parcel.writeInt(this.lockModeStart);
      param1Parcel.writeInt(this.lockModeEnd);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public DrawerLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new DrawerLayout.SavedState(param1Parcel, null);
    }
    
    public DrawerLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new DrawerLayout.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public DrawerLayout.SavedState[] newArray(int param1Int) {
      return new DrawerLayout.SavedState[param1Int];
    }
  }
  
  public static abstract class SimpleDrawerListener implements DrawerListener {
    public void onDrawerClosed(View param1View) {}
    
    public void onDrawerOpened(View param1View) {}
    
    public void onDrawerSlide(View param1View, float param1Float) {}
    
    public void onDrawerStateChanged(int param1Int) {}
  }
  
  private class ViewDragCallback extends ViewDragHelper.Callback {
    private final int mAbsGravity;
    
    private ViewDragHelper mDragger;
    
    private final Runnable mPeekRunnable = new Runnable() {
        public void run() {
          DrawerLayout.ViewDragCallback.this.peekDrawer();
        }
      };
    
    ViewDragCallback(int param1Int) {
      this.mAbsGravity = param1Int;
    }
    
    private void closeOtherDrawer() {
      byte b = 3;
      if (this.mAbsGravity == 3)
        b = 5; 
      View view = DrawerLayout.this.findDrawerWithGravity(b);
      if (view != null)
        DrawerLayout.this.closeDrawer(view); 
    }
    
    public int clampViewPositionHorizontal(View param1View, int param1Int1, int param1Int2) {
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, 3))
        return Math.max(-param1View.getWidth(), Math.min(param1Int1, 0)); 
      param1Int2 = DrawerLayout.this.getWidth();
      return Math.max(param1Int2 - param1View.getWidth(), Math.min(param1Int1, param1Int2));
    }
    
    public int clampViewPositionVertical(View param1View, int param1Int1, int param1Int2) {
      return param1View.getTop();
    }
    
    public int getViewHorizontalDragRange(View param1View) {
      return DrawerLayout.this.isDrawerView(param1View) ? param1View.getWidth() : 0;
    }
    
    public void onEdgeDragStarted(int param1Int1, int param1Int2) {
      View view;
      if ((param1Int1 & 0x1) == 1) {
        view = DrawerLayout.this.findDrawerWithGravity(3);
      } else {
        view = DrawerLayout.this.findDrawerWithGravity(5);
      } 
      if (view != null && DrawerLayout.this.getDrawerLockMode(view) == 0)
        this.mDragger.captureChildView(view, param1Int2); 
    }
    
    public boolean onEdgeLock(int param1Int) {
      return false;
    }
    
    public void onEdgeTouched(int param1Int1, int param1Int2) {
      DrawerLayout.this.postDelayed(this.mPeekRunnable, 160L);
    }
    
    public void onViewCaptured(View param1View, int param1Int) {
      ((DrawerLayout.LayoutParams)param1View.getLayoutParams()).isPeeking = false;
      closeOtherDrawer();
    }
    
    public void onViewDragStateChanged(int param1Int) {
      DrawerLayout.this.updateDrawerState(this.mAbsGravity, param1Int, this.mDragger.getCapturedView());
    }
    
    public void onViewPositionChanged(View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      float f;
      param1Int2 = param1View.getWidth();
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, 3)) {
        f = (param1Int2 + param1Int1) / param1Int2;
      } else {
        f = (DrawerLayout.this.getWidth() - param1Int1) / param1Int2;
      } 
      DrawerLayout.this.setDrawerViewOffset(param1View, f);
      if (f == 0.0F) {
        param1Int1 = 4;
      } else {
        param1Int1 = 0;
      } 
      param1View.setVisibility(param1Int1);
      DrawerLayout.this.invalidate();
    }
    
    public void onViewReleased(View param1View, float param1Float1, float param1Float2) {
      int i;
      param1Float2 = DrawerLayout.this.getDrawerViewOffset(param1View);
      int j = param1View.getWidth();
      if (DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, 3)) {
        if (param1Float1 > 0.0F || (param1Float1 == 0.0F && param1Float2 > 0.5F)) {
          i = 0;
        } else {
          i = -j;
        } 
      } else {
        i = DrawerLayout.this.getWidth();
        if (param1Float1 < 0.0F || (param1Float1 == 0.0F && param1Float2 > 0.5F))
          i -= j; 
      } 
      this.mDragger.settleCapturedViewAt(i, param1View.getTop());
      DrawerLayout.this.invalidate();
    }
    
    void peekDrawer() {
      boolean bool;
      View view;
      int i = 0;
      int j = this.mDragger.getEdgeSize();
      if (this.mAbsGravity == 3) {
        bool = true;
      } else {
        bool = false;
      } 
      if (bool) {
        view = DrawerLayout.this.findDrawerWithGravity(3);
        if (view != null)
          i = -view.getWidth(); 
        i += j;
      } else {
        view = DrawerLayout.this.findDrawerWithGravity(5);
        i = DrawerLayout.this.getWidth() - j;
      } 
      if (view != null && ((bool && view.getLeft() < i) || (!bool && view.getLeft() > i)) && DrawerLayout.this.getDrawerLockMode(view) == 0) {
        DrawerLayout.LayoutParams layoutParams = (DrawerLayout.LayoutParams)view.getLayoutParams();
        this.mDragger.smoothSlideViewTo(view, i, view.getTop());
        layoutParams.isPeeking = true;
        DrawerLayout.this.invalidate();
        closeOtherDrawer();
        DrawerLayout.this.cancelChildViewTouch();
      } 
    }
    
    public void removeCallbacks() {
      DrawerLayout.this.removeCallbacks(this.mPeekRunnable);
    }
    
    public void setDragger(ViewDragHelper param1ViewDragHelper) {
      this.mDragger = param1ViewDragHelper;
    }
    
    public boolean tryCaptureView(View param1View, int param1Int) {
      return (DrawerLayout.this.isDrawerView(param1View) && DrawerLayout.this.checkDrawerViewAbsoluteGravity(param1View, this.mAbsGravity) && DrawerLayout.this.getDrawerLockMode(param1View) == 0);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$1.peekDrawer();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/drawerlayout/widget/DrawerLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */