package androidx.coordinatorlayout.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.FloatRange;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.R;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Pools;
import androidx.core.view.GravityCompat;
import androidx.core.view.NestedScrollingParent2;
import androidx.core.view.NestedScrollingParentHelper;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorLayout extends ViewGroup implements NestedScrollingParent2 {
  static final Class<?>[] CONSTRUCTOR_PARAMS;
  
  static final int EVENT_NESTED_SCROLL = 1;
  
  static final int EVENT_PRE_DRAW = 0;
  
  static final int EVENT_VIEW_REMOVED = 2;
  
  static final String TAG = "CoordinatorLayout";
  
  static final Comparator<View> TOP_SORTED_CHILDREN_COMPARATOR;
  
  private static final int TYPE_ON_INTERCEPT = 0;
  
  private static final int TYPE_ON_TOUCH = 1;
  
  static final String WIDGET_PACKAGE_NAME;
  
  static final ThreadLocal<Map<String, Constructor<Behavior>>> sConstructors;
  
  private static final Pools.Pool<Rect> sRectPool;
  
  private OnApplyWindowInsetsListener mApplyWindowInsetsListener;
  
  private View mBehaviorTouchView;
  
  private final DirectedAcyclicGraph<View> mChildDag;
  
  private final List<View> mDependencySortedChildren;
  
  private boolean mDisallowInterceptReset;
  
  private boolean mDrawStatusBarBackground;
  
  private boolean mIsAttachedToWindow;
  
  private int[] mKeylines;
  
  private WindowInsetsCompat mLastInsets;
  
  private boolean mNeedsPreDrawListener;
  
  private final NestedScrollingParentHelper mNestedScrollingParentHelper;
  
  private View mNestedScrollingTarget;
  
  ViewGroup.OnHierarchyChangeListener mOnHierarchyChangeListener;
  
  private OnPreDrawListener mOnPreDrawListener;
  
  private Paint mScrimPaint;
  
  private Drawable mStatusBarBackground;
  
  private final List<View> mTempDependenciesList;
  
  private final int[] mTempIntPair;
  
  private final List<View> mTempList1;
  
  static {
    Package package_ = CoordinatorLayout.class.getPackage();
    if (package_ != null) {
      String str = package_.getName();
    } else {
      package_ = null;
    } 
    WIDGET_PACKAGE_NAME = (String)package_;
    if (Build.VERSION.SDK_INT >= 21) {
      TOP_SORTED_CHILDREN_COMPARATOR = new ViewElevationComparator();
    } else {
      TOP_SORTED_CHILDREN_COMPARATOR = null;
    } 
    CONSTRUCTOR_PARAMS = new Class[] { Context.class, AttributeSet.class };
    sConstructors = new ThreadLocal<Map<String, Constructor<Behavior>>>();
    sRectPool = (Pools.Pool<Rect>)new Pools.SynchronizedPool(12);
  }
  
  public CoordinatorLayout(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public CoordinatorLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.coordinatorLayoutStyle);
  }
  
  public CoordinatorLayout(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @AttrRes int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray typedArray;
    this.mDependencySortedChildren = new ArrayList<View>();
    this.mChildDag = new DirectedAcyclicGraph<View>();
    this.mTempList1 = new ArrayList<View>();
    this.mTempDependenciesList = new ArrayList<View>();
    this.mTempIntPair = new int[2];
    this.mNestedScrollingParentHelper = new NestedScrollingParentHelper(this);
    if (paramInt == 0) {
      typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.CoordinatorLayout, 0, R.style.Widget_Support_CoordinatorLayout);
    } else {
      typedArray = paramContext.obtainStyledAttributes((AttributeSet)typedArray, R.styleable.CoordinatorLayout, paramInt, 0);
    } 
    paramInt = typedArray.getResourceId(R.styleable.CoordinatorLayout_keylines, 0);
    if (paramInt != 0) {
      Resources resources = paramContext.getResources();
      this.mKeylines = resources.getIntArray(paramInt);
      float f = (resources.getDisplayMetrics()).density;
      int i = this.mKeylines.length;
      for (paramInt = 0; paramInt < i; paramInt++)
        this.mKeylines[paramInt] = (int)(this.mKeylines[paramInt] * f); 
    } 
    this.mStatusBarBackground = typedArray.getDrawable(R.styleable.CoordinatorLayout_statusBarBackground);
    typedArray.recycle();
    setupForInsets();
    super.setOnHierarchyChangeListener(new HierarchyChangeListener());
  }
  
  @NonNull
  private static Rect acquireTempRect() {
    Rect rect2 = (Rect)sRectPool.acquire();
    Rect rect1 = rect2;
    if (rect2 == null)
      rect1 = new Rect(); 
    return rect1;
  }
  
  private static int clamp(int paramInt1, int paramInt2, int paramInt3) {
    return (paramInt1 < paramInt2) ? paramInt2 : ((paramInt1 > paramInt3) ? paramInt3 : paramInt1);
  }
  
  private void constrainChildRect(LayoutParams paramLayoutParams, Rect paramRect, int paramInt1, int paramInt2) {
    int j = getWidth();
    int i = getHeight();
    j = Math.max(getPaddingLeft() + paramLayoutParams.leftMargin, Math.min(paramRect.left, j - getPaddingRight() - paramInt1 - paramLayoutParams.rightMargin));
    i = Math.max(getPaddingTop() + paramLayoutParams.topMargin, Math.min(paramRect.top, i - getPaddingBottom() - paramInt2 - paramLayoutParams.bottomMargin));
    paramRect.set(j, i, j + paramInt1, i + paramInt2);
  }
  
  private WindowInsetsCompat dispatchApplyWindowInsetsToBehaviors(WindowInsetsCompat paramWindowInsetsCompat) {
    if (paramWindowInsetsCompat.isConsumed())
      return paramWindowInsetsCompat; 
    int i = 0;
    int j = getChildCount();
    while (true) {
      WindowInsetsCompat windowInsetsCompat = paramWindowInsetsCompat;
      if (i < j) {
        View view = getChildAt(i);
        windowInsetsCompat = paramWindowInsetsCompat;
        if (ViewCompat.getFitsSystemWindows(view)) {
          Behavior<View> behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
          windowInsetsCompat = paramWindowInsetsCompat;
          if (behavior != null) {
            paramWindowInsetsCompat = behavior.onApplyWindowInsets(this, view, paramWindowInsetsCompat);
            windowInsetsCompat = paramWindowInsetsCompat;
            if (paramWindowInsetsCompat.isConsumed())
              return paramWindowInsetsCompat; 
          } 
        } 
      } else {
        return windowInsetsCompat;
      } 
      i++;
      paramWindowInsetsCompat = windowInsetsCompat;
    } 
  }
  
  private void getDesiredAnchoredChildRectWithoutConstraints(View paramView, int paramInt1, Rect paramRect1, Rect paramRect2, LayoutParams paramLayoutParams, int paramInt2, int paramInt3) {
    // Byte code:
    //   0: aload #5
    //   2: getfield gravity : I
    //   5: invokestatic resolveAnchoredChildGravity : (I)I
    //   8: iload_2
    //   9: invokestatic getAbsoluteGravity : (II)I
    //   12: istore #10
    //   14: aload #5
    //   16: getfield anchorGravity : I
    //   19: invokestatic resolveGravity : (I)I
    //   22: iload_2
    //   23: invokestatic getAbsoluteGravity : (II)I
    //   26: istore #8
    //   28: iload #8
    //   30: bipush #7
    //   32: iand
    //   33: lookupswitch default -> 60, 1 -> 208, 5 -> 200
    //   60: aload_3
    //   61: getfield left : I
    //   64: istore_2
    //   65: iload #8
    //   67: bipush #112
    //   69: iand
    //   70: lookupswitch default -> 96, 16 -> 232, 80 -> 223
    //   96: aload_3
    //   97: getfield top : I
    //   100: istore #8
    //   102: iload_2
    //   103: istore #9
    //   105: iload #10
    //   107: bipush #7
    //   109: iand
    //   110: lookupswitch default -> 136, 1 -> 248, 5 -> 142
    //   136: iload_2
    //   137: iload #6
    //   139: isub
    //   140: istore #9
    //   142: iload #8
    //   144: istore_2
    //   145: iload #10
    //   147: bipush #112
    //   149: iand
    //   150: lookupswitch default -> 176, 16 -> 259, 80 -> 182
    //   176: iload #8
    //   178: iload #7
    //   180: isub
    //   181: istore_2
    //   182: aload #4
    //   184: iload #9
    //   186: iload_2
    //   187: iload #9
    //   189: iload #6
    //   191: iadd
    //   192: iload_2
    //   193: iload #7
    //   195: iadd
    //   196: invokevirtual set : (IIII)V
    //   199: return
    //   200: aload_3
    //   201: getfield right : I
    //   204: istore_2
    //   205: goto -> 65
    //   208: aload_3
    //   209: getfield left : I
    //   212: aload_3
    //   213: invokevirtual width : ()I
    //   216: iconst_2
    //   217: idiv
    //   218: iadd
    //   219: istore_2
    //   220: goto -> 65
    //   223: aload_3
    //   224: getfield bottom : I
    //   227: istore #8
    //   229: goto -> 102
    //   232: aload_3
    //   233: getfield top : I
    //   236: aload_3
    //   237: invokevirtual height : ()I
    //   240: iconst_2
    //   241: idiv
    //   242: iadd
    //   243: istore #8
    //   245: goto -> 102
    //   248: iload_2
    //   249: iload #6
    //   251: iconst_2
    //   252: idiv
    //   253: isub
    //   254: istore #9
    //   256: goto -> 142
    //   259: iload #8
    //   261: iload #7
    //   263: iconst_2
    //   264: idiv
    //   265: isub
    //   266: istore_2
    //   267: goto -> 182
  }
  
  private int getKeyline(int paramInt) {
    if (this.mKeylines == null) {
      Log.e("CoordinatorLayout", "No keylines defined for " + this + " - attempted index lookup " + paramInt);
      return 0;
    } 
    if (paramInt < 0 || paramInt >= this.mKeylines.length) {
      Log.e("CoordinatorLayout", "Keyline index " + paramInt + " out of range for " + this);
      return 0;
    } 
    return this.mKeylines[paramInt];
  }
  
  private void getTopSortedChildren(List<View> paramList) {
    paramList.clear();
    boolean bool = isChildrenDrawingOrderEnabled();
    int j = getChildCount();
    for (int i = j - 1; i >= 0; i--) {
      int k;
      if (bool) {
        k = getChildDrawingOrder(j, i);
      } else {
        k = i;
      } 
      paramList.add(getChildAt(k));
    } 
    if (TOP_SORTED_CHILDREN_COMPARATOR != null)
      Collections.sort(paramList, TOP_SORTED_CHILDREN_COMPARATOR); 
  }
  
  private boolean hasDependencies(View paramView) {
    return this.mChildDag.hasOutgoingEdges(paramView);
  }
  
  private void layoutChild(View paramView, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    Rect rect1 = acquireTempRect();
    rect1.set(getPaddingLeft() + layoutParams.leftMargin, getPaddingTop() + layoutParams.topMargin, getWidth() - getPaddingRight() - layoutParams.rightMargin, getHeight() - getPaddingBottom() - layoutParams.bottomMargin);
    if (this.mLastInsets != null && ViewCompat.getFitsSystemWindows((View)this) && !ViewCompat.getFitsSystemWindows(paramView)) {
      rect1.left += this.mLastInsets.getSystemWindowInsetLeft();
      rect1.top += this.mLastInsets.getSystemWindowInsetTop();
      rect1.right -= this.mLastInsets.getSystemWindowInsetRight();
      rect1.bottom -= this.mLastInsets.getSystemWindowInsetBottom();
    } 
    Rect rect2 = acquireTempRect();
    GravityCompat.apply(resolveGravity(layoutParams.gravity), paramView.getMeasuredWidth(), paramView.getMeasuredHeight(), rect1, rect2, paramInt);
    paramView.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
    releaseTempRect(rect1);
    releaseTempRect(rect2);
  }
  
  private void layoutChildWithAnchor(View paramView1, View paramView2, int paramInt) {
    Rect rect1 = acquireTempRect();
    Rect rect2 = acquireTempRect();
    try {
      getDescendantRect(paramView2, rect1);
      getDesiredAnchoredChildRect(paramView1, paramInt, rect1, rect2);
      paramView1.layout(rect2.left, rect2.top, rect2.right, rect2.bottom);
      return;
    } finally {
      releaseTempRect(rect1);
      releaseTempRect(rect2);
    } 
  }
  
  private void layoutChildWithKeyline(View paramView, int paramInt1, int paramInt2) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i1 = GravityCompat.getAbsoluteGravity(resolveKeylineGravity(layoutParams.gravity), paramInt2);
    int n = getWidth();
    int m = getHeight();
    int j = paramView.getMeasuredWidth();
    int k = paramView.getMeasuredHeight();
    int i = paramInt1;
    if (paramInt2 == 1)
      i = n - paramInt1; 
    paramInt1 = getKeyline(i) - j;
    paramInt2 = 0;
    switch (i1 & 0x7) {
      default:
        switch (i1 & 0x70) {
          default:
            paramInt1 = Math.max(getPaddingLeft() + layoutParams.leftMargin, Math.min(paramInt1, n - getPaddingRight() - j - layoutParams.rightMargin));
            paramInt2 = Math.max(getPaddingTop() + layoutParams.topMargin, Math.min(paramInt2, m - getPaddingBottom() - k - layoutParams.bottomMargin));
            paramView.layout(paramInt1, paramInt2, paramInt1 + j, paramInt2 + k);
            return;
          case 80:
            paramInt2 = 0 + k;
          case 16:
            break;
        } 
        break;
      case 5:
        paramInt1 += j;
      case 1:
        paramInt1 += j / 2;
    } 
    paramInt2 = 0 + k / 2;
  }
  
  private void offsetChildByInset(View paramView, Rect paramRect, int paramInt) {
    if (ViewCompat.isLaidOut(paramView) && paramView.getWidth() > 0 && paramView.getHeight() > 0) {
      LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
      Behavior<View> behavior = layoutParams.getBehavior();
      Rect rect1 = acquireTempRect();
      Rect rect2 = acquireTempRect();
      rect2.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
      if (behavior != null && behavior.getInsetDodgeRect(this, paramView, rect1)) {
        if (!rect2.contains(rect1))
          throw new IllegalArgumentException("Rect should be within the child's bounds. Rect:" + rect1.toShortString() + " | Bounds:" + rect2.toShortString()); 
      } else {
        rect1.set(rect2);
      } 
      releaseTempRect(rect2);
      if (rect1.isEmpty()) {
        releaseTempRect(rect1);
        return;
      } 
      int j = GravityCompat.getAbsoluteGravity(layoutParams.dodgeInsetEdges, paramInt);
      int i = 0;
      paramInt = i;
      if ((j & 0x30) == 48) {
        int k = rect1.top - layoutParams.topMargin - layoutParams.mInsetOffsetY;
        paramInt = i;
        if (k < paramRect.top) {
          setInsetOffsetY(paramView, paramRect.top - k);
          paramInt = 1;
        } 
      } 
      i = paramInt;
      if ((j & 0x50) == 80) {
        int k = getHeight() - rect1.bottom - layoutParams.bottomMargin + layoutParams.mInsetOffsetY;
        i = paramInt;
        if (k < paramRect.bottom) {
          setInsetOffsetY(paramView, k - paramRect.bottom);
          i = 1;
        } 
      } 
      if (i == 0)
        setInsetOffsetY(paramView, 0); 
      i = 0;
      paramInt = i;
      if ((j & 0x3) == 3) {
        int k = rect1.left - layoutParams.leftMargin - layoutParams.mInsetOffsetX;
        paramInt = i;
        if (k < paramRect.left) {
          setInsetOffsetX(paramView, paramRect.left - k);
          paramInt = 1;
        } 
      } 
      i = paramInt;
      if ((j & 0x5) == 5) {
        j = getWidth() - rect1.right - layoutParams.rightMargin + layoutParams.mInsetOffsetX;
        i = paramInt;
        if (j < paramRect.right) {
          setInsetOffsetX(paramView, j - paramRect.right);
          i = 1;
        } 
      } 
      if (i == 0)
        setInsetOffsetX(paramView, 0); 
      releaseTempRect(rect1);
      return;
    } 
  }
  
  static Behavior parseBehavior(Context paramContext, AttributeSet paramAttributeSet, String paramString) {
    if (TextUtils.isEmpty(paramString))
      return null; 
    if (paramString.startsWith(".")) {
      paramString = paramContext.getPackageName() + paramString;
    } else if (paramString.indexOf('.') < 0 && !TextUtils.isEmpty(WIDGET_PACKAGE_NAME)) {
      paramString = WIDGET_PACKAGE_NAME + '.' + paramString;
    } 
    try {
      Map<Object, Object> map2 = (Map)sConstructors.get();
      Map<Object, Object> map1 = map2;
      if (map2 == null) {
        map1 = new HashMap<Object, Object>();
        sConstructors.set(map1);
      } 
      Constructor<?> constructor2 = (Constructor)map1.get(paramString);
      Constructor<?> constructor1 = constructor2;
      if (constructor2 == null) {
        constructor1 = paramContext.getClassLoader().loadClass(paramString).getConstructor(CONSTRUCTOR_PARAMS);
        constructor1.setAccessible(true);
        map1.put(paramString, constructor1);
      } 
      return (Behavior)constructor1.newInstance(new Object[] { paramContext, paramAttributeSet });
    } catch (Exception exception) {
      throw new RuntimeException("Could not inflate Behavior subclass " + paramString, exception);
    } 
  }
  
  private boolean performIntercept(MotionEvent paramMotionEvent, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #8
    //   3: iconst_0
    //   4: istore_3
    //   5: aconst_null
    //   6: astore #13
    //   8: aload_1
    //   9: invokevirtual getActionMasked : ()I
    //   12: istore #6
    //   14: aload_0
    //   15: getfield mTempList1 : Ljava/util/List;
    //   18: astore #15
    //   20: aload_0
    //   21: aload #15
    //   23: invokespecial getTopSortedChildren : (Ljava/util/List;)V
    //   26: aload #15
    //   28: invokeinterface size : ()I
    //   33: istore #7
    //   35: iconst_0
    //   36: istore #4
    //   38: iload #8
    //   40: istore #9
    //   42: iload #4
    //   44: iload #7
    //   46: if_icmpge -> 351
    //   49: aload #15
    //   51: iload #4
    //   53: invokeinterface get : (I)Ljava/lang/Object;
    //   58: checkcast android/view/View
    //   61: astore #16
    //   63: aload #16
    //   65: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   68: checkcast androidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams
    //   71: astore #14
    //   73: aload #14
    //   75: invokevirtual getBehavior : ()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;
    //   78: astore #17
    //   80: iload #8
    //   82: ifne -> 89
    //   85: iload_3
    //   86: ifeq -> 229
    //   89: iload #6
    //   91: ifeq -> 229
    //   94: aload #13
    //   96: astore #14
    //   98: iload #8
    //   100: istore #10
    //   102: iload_3
    //   103: istore #5
    //   105: aload #17
    //   107: ifnull -> 167
    //   110: aload #13
    //   112: astore #14
    //   114: aload #13
    //   116: ifnonnull -> 137
    //   119: invokestatic uptimeMillis : ()J
    //   122: lstore #11
    //   124: lload #11
    //   126: lload #11
    //   128: iconst_3
    //   129: fconst_0
    //   130: fconst_0
    //   131: iconst_0
    //   132: invokestatic obtain : (JJIFFI)Landroid/view/MotionEvent;
    //   135: astore #14
    //   137: iload_2
    //   138: tableswitch default -> 160, 0 -> 187, 1 -> 208
    //   160: iload_3
    //   161: istore #5
    //   163: iload #8
    //   165: istore #10
    //   167: iload #4
    //   169: iconst_1
    //   170: iadd
    //   171: istore #4
    //   173: aload #14
    //   175: astore #13
    //   177: iload #10
    //   179: istore #8
    //   181: iload #5
    //   183: istore_3
    //   184: goto -> 38
    //   187: aload #17
    //   189: aload_0
    //   190: aload #16
    //   192: aload #14
    //   194: invokevirtual onInterceptTouchEvent : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   197: pop
    //   198: iload #8
    //   200: istore #10
    //   202: iload_3
    //   203: istore #5
    //   205: goto -> 167
    //   208: aload #17
    //   210: aload_0
    //   211: aload #16
    //   213: aload #14
    //   215: invokevirtual onTouchEvent : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   218: pop
    //   219: iload #8
    //   221: istore #10
    //   223: iload_3
    //   224: istore #5
    //   226: goto -> 167
    //   229: iload #8
    //   231: istore #9
    //   233: iload #8
    //   235: ifne -> 291
    //   238: iload #8
    //   240: istore #9
    //   242: aload #17
    //   244: ifnull -> 291
    //   247: iload_2
    //   248: tableswitch default -> 272, 0 -> 361, 1 -> 375
    //   272: iload #8
    //   274: istore #9
    //   276: iload #8
    //   278: ifeq -> 291
    //   281: aload_0
    //   282: aload #16
    //   284: putfield mBehaviorTouchView : Landroid/view/View;
    //   287: iload #8
    //   289: istore #9
    //   291: aload #14
    //   293: invokevirtual didBlockInteraction : ()Z
    //   296: istore #10
    //   298: aload #14
    //   300: aload_0
    //   301: aload #16
    //   303: invokevirtual isBlockingInteractionBelow : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;)Z
    //   306: istore #8
    //   308: iload #8
    //   310: ifeq -> 389
    //   313: iload #10
    //   315: ifne -> 389
    //   318: iconst_1
    //   319: istore_3
    //   320: aload #13
    //   322: astore #14
    //   324: iload #9
    //   326: istore #10
    //   328: iload_3
    //   329: istore #5
    //   331: iload #8
    //   333: ifeq -> 167
    //   336: aload #13
    //   338: astore #14
    //   340: iload #9
    //   342: istore #10
    //   344: iload_3
    //   345: istore #5
    //   347: iload_3
    //   348: ifne -> 167
    //   351: aload #15
    //   353: invokeinterface clear : ()V
    //   358: iload #9
    //   360: ireturn
    //   361: aload #17
    //   363: aload_0
    //   364: aload #16
    //   366: aload_1
    //   367: invokevirtual onInterceptTouchEvent : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   370: istore #8
    //   372: goto -> 272
    //   375: aload #17
    //   377: aload_0
    //   378: aload #16
    //   380: aload_1
    //   381: invokevirtual onTouchEvent : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   384: istore #8
    //   386: goto -> 272
    //   389: iconst_0
    //   390: istore_3
    //   391: goto -> 320
  }
  
  private void prepareChildren() {
    this.mDependencySortedChildren.clear();
    this.mChildDag.clear();
    int i = 0;
    int j = getChildCount();
    while (i < j) {
      View view = getChildAt(i);
      LayoutParams layoutParams = getResolvedLayoutParams(view);
      layoutParams.findAnchorView(this, view);
      this.mChildDag.addNode(view);
      for (int k = 0; k < j; k++) {
        if (k != i) {
          View view1 = getChildAt(k);
          if (layoutParams.dependsOn(this, view, view1)) {
            if (!this.mChildDag.contains(view1))
              this.mChildDag.addNode(view1); 
            this.mChildDag.addEdge(view1, view);
          } 
        } 
      } 
      i++;
    } 
    this.mDependencySortedChildren.addAll(this.mChildDag.getSortedList());
    Collections.reverse(this.mDependencySortedChildren);
  }
  
  private static void releaseTempRect(@NonNull Rect paramRect) {
    paramRect.setEmpty();
    sRectPool.release(paramRect);
  }
  
  private void resetTouchBehaviors(boolean paramBoolean) {
    int j = getChildCount();
    int i;
    for (i = 0; i < j; i++) {
      View view = getChildAt(i);
      Behavior<View> behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
      if (behavior != null) {
        long l = SystemClock.uptimeMillis();
        MotionEvent motionEvent = MotionEvent.obtain(l, l, 3, 0.0F, 0.0F, 0);
        if (paramBoolean) {
          behavior.onInterceptTouchEvent(this, view, motionEvent);
        } else {
          behavior.onTouchEvent(this, view, motionEvent);
        } 
        motionEvent.recycle();
      } 
    } 
    for (i = 0; i < j; i++)
      ((LayoutParams)getChildAt(i).getLayoutParams()).resetTouchBehaviorTracking(); 
    this.mBehaviorTouchView = null;
    this.mDisallowInterceptReset = false;
  }
  
  private static int resolveAnchoredChildGravity(int paramInt) {
    int i = paramInt;
    if (paramInt == 0)
      i = 17; 
    return i;
  }
  
  private static int resolveGravity(int paramInt) {
    int i = paramInt;
    if ((paramInt & 0x7) == 0)
      i = paramInt | 0x800003; 
    paramInt = i;
    if ((i & 0x70) == 0)
      paramInt = i | 0x30; 
    return paramInt;
  }
  
  private static int resolveKeylineGravity(int paramInt) {
    int i = paramInt;
    if (paramInt == 0)
      i = 8388661; 
    return i;
  }
  
  private void setInsetOffsetX(View paramView, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (layoutParams.mInsetOffsetX != paramInt) {
      ViewCompat.offsetLeftAndRight(paramView, paramInt - layoutParams.mInsetOffsetX);
      layoutParams.mInsetOffsetX = paramInt;
    } 
  }
  
  private void setInsetOffsetY(View paramView, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (layoutParams.mInsetOffsetY != paramInt) {
      ViewCompat.offsetTopAndBottom(paramView, paramInt - layoutParams.mInsetOffsetY);
      layoutParams.mInsetOffsetY = paramInt;
    } 
  }
  
  private void setupForInsets() {
    if (Build.VERSION.SDK_INT < 21)
      return; 
    if (ViewCompat.getFitsSystemWindows((View)this)) {
      if (this.mApplyWindowInsetsListener == null)
        this.mApplyWindowInsetsListener = new OnApplyWindowInsetsListener() {
            public WindowInsetsCompat onApplyWindowInsets(View param1View, WindowInsetsCompat param1WindowInsetsCompat) {
              return CoordinatorLayout.this.setWindowInsets(param1WindowInsetsCompat);
            }
          }; 
      ViewCompat.setOnApplyWindowInsetsListener((View)this, this.mApplyWindowInsetsListener);
      setSystemUiVisibility(1280);
      return;
    } 
    ViewCompat.setOnApplyWindowInsetsListener((View)this, null);
  }
  
  void addPreDrawListener() {
    if (this.mIsAttachedToWindow) {
      if (this.mOnPreDrawListener == null)
        this.mOnPreDrawListener = new OnPreDrawListener(); 
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    } 
    this.mNeedsPreDrawListener = true;
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof LayoutParams && super.checkLayoutParams(paramLayoutParams));
  }
  
  public void dispatchDependentViewsChanged(@NonNull View paramView) {
    List<View> list = this.mChildDag.getIncomingEdges(paramView);
    if (list != null && !list.isEmpty())
      for (int i = 0; i < list.size(); i++) {
        View view = list.get(i);
        Behavior<View> behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
        if (behavior != null)
          behavior.onDependentViewChanged(this, view, paramView); 
      }  
  }
  
  public boolean doViewsOverlap(@NonNull View paramView1, @NonNull View paramView2) {
    boolean bool = true;
    if (paramView1.getVisibility() == 0 && paramView2.getVisibility() == 0) {
      boolean bool1;
      Rect rect2 = acquireTempRect();
      if (paramView1.getParent() != this) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      getChildRect(paramView1, bool1, rect2);
      Rect rect1 = acquireTempRect();
      if (paramView2.getParent() != this) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      getChildRect(paramView2, bool1, rect1);
      try {
        if (rect2.left <= rect1.right && rect2.top <= rect1.bottom && rect2.right >= rect1.left) {
          int i = rect2.bottom;
          int j = rect1.top;
          if (i >= j) {
            bool1 = bool;
            return bool1;
          } 
        } 
        bool1 = false;
        return bool1;
      } finally {
        releaseTempRect(rect2);
        releaseTempRect(rect1);
      } 
    } 
    return false;
  }
  
  protected boolean drawChild(Canvas paramCanvas, View paramView, long paramLong) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (layoutParams.mBehavior != null) {
      float f = layoutParams.mBehavior.getScrimOpacity(this, paramView);
      if (f > 0.0F) {
        if (this.mScrimPaint == null)
          this.mScrimPaint = new Paint(); 
        this.mScrimPaint.setColor(layoutParams.mBehavior.getScrimColor(this, paramView));
        this.mScrimPaint.setAlpha(clamp(Math.round(255.0F * f), 0, 255));
        int i = paramCanvas.save();
        if (paramView.isOpaque())
          paramCanvas.clipRect(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom(), Region.Op.DIFFERENCE); 
        paramCanvas.drawRect(getPaddingLeft(), getPaddingTop(), (getWidth() - getPaddingRight()), (getHeight() - getPaddingBottom()), this.mScrimPaint);
        paramCanvas.restoreToCount(i);
      } 
    } 
    return super.drawChild(paramCanvas, paramView, paramLong);
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    byte b = 0;
    Drawable drawable = this.mStatusBarBackground;
    int i = b;
    if (drawable != null) {
      i = b;
      if (drawable.isStateful())
        i = false | drawable.setState(arrayOfInt); 
    } 
    if (i != 0)
      invalidate(); 
  }
  
  void ensurePreDrawListener() {
    boolean bool = false;
    int j = getChildCount();
    int i = 0;
    while (true) {
      boolean bool1 = bool;
      if (i < j)
        if (hasDependencies(getChildAt(i))) {
          bool1 = true;
        } else {
          i++;
          continue;
        }  
      if (bool1 != this.mNeedsPreDrawListener) {
        if (bool1) {
          addPreDrawListener();
          return;
        } 
      } else {
        return;
      } 
      removePreDrawListener();
      return;
    } 
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return new LayoutParams(-2, -2);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return (paramLayoutParams instanceof LayoutParams) ? new LayoutParams((LayoutParams)paramLayoutParams) : ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams) ? new LayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams) : new LayoutParams(paramLayoutParams));
  }
  
  void getChildRect(View paramView, boolean paramBoolean, Rect paramRect) {
    if (paramView.isLayoutRequested() || paramView.getVisibility() == 8) {
      paramRect.setEmpty();
      return;
    } 
    if (paramBoolean) {
      getDescendantRect(paramView, paramRect);
      return;
    } 
    paramRect.set(paramView.getLeft(), paramView.getTop(), paramView.getRight(), paramView.getBottom());
  }
  
  @NonNull
  public List<View> getDependencies(@NonNull View paramView) {
    List<View> list = this.mChildDag.getOutgoingEdges(paramView);
    this.mTempDependenciesList.clear();
    if (list != null)
      this.mTempDependenciesList.addAll(list); 
    return this.mTempDependenciesList;
  }
  
  @VisibleForTesting
  final List<View> getDependencySortedChildren() {
    prepareChildren();
    return Collections.unmodifiableList(this.mDependencySortedChildren);
  }
  
  @NonNull
  public List<View> getDependents(@NonNull View paramView) {
    List<? extends View> list = this.mChildDag.getIncomingEdges(paramView);
    this.mTempDependenciesList.clear();
    if (list != null)
      this.mTempDependenciesList.addAll(list); 
    return this.mTempDependenciesList;
  }
  
  void getDescendantRect(View paramView, Rect paramRect) {
    ViewGroupUtils.getDescendantRect(this, paramView, paramRect);
  }
  
  void getDesiredAnchoredChildRect(View paramView, int paramInt, Rect paramRect1, Rect paramRect2) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    int i = paramView.getMeasuredWidth();
    int j = paramView.getMeasuredHeight();
    getDesiredAnchoredChildRectWithoutConstraints(paramView, paramInt, paramRect1, paramRect2, layoutParams, i, j);
    constrainChildRect(layoutParams, paramRect2, i, j);
  }
  
  void getLastChildRect(View paramView, Rect paramRect) {
    paramRect.set(((LayoutParams)paramView.getLayoutParams()).getLastChildRect());
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public final WindowInsetsCompat getLastWindowInsets() {
    return this.mLastInsets;
  }
  
  public int getNestedScrollAxes() {
    return this.mNestedScrollingParentHelper.getNestedScrollAxes();
  }
  
  LayoutParams getResolvedLayoutParams(View paramView) {
    DefaultBehavior defaultBehavior;
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (!layoutParams.mBehaviorResolved) {
      if (paramView instanceof AttachedBehavior) {
        behavior = ((AttachedBehavior)paramView).getBehavior();
        if (behavior == null)
          Log.e("CoordinatorLayout", "Attached behavior class is null"); 
        layoutParams.setBehavior(behavior);
        layoutParams.mBehaviorResolved = true;
        return layoutParams;
      } 
    } else {
      return layoutParams;
    } 
    Class<?> clazz = behavior.getClass();
    Behavior behavior = null;
    while (true) {
      Behavior behavior1 = behavior;
      if (clazz != null) {
        DefaultBehavior defaultBehavior1 = clazz.<DefaultBehavior>getAnnotation(DefaultBehavior.class);
        defaultBehavior = defaultBehavior1;
        if (defaultBehavior1 == null) {
          clazz = clazz.getSuperclass();
          continue;
        } 
      } 
      break;
    } 
    if (defaultBehavior != null)
      try {
        layoutParams.setBehavior(defaultBehavior.value().getDeclaredConstructor(new Class[0]).newInstance(new Object[0]));
      } catch (Exception exception) {
        Log.e("CoordinatorLayout", "Default behavior class " + defaultBehavior.value().getName() + " could not be instantiated. Did you forget" + " a default constructor?", exception);
      }  
    layoutParams.mBehaviorResolved = true;
    return layoutParams;
  }
  
  @Nullable
  public Drawable getStatusBarBackground() {
    return this.mStatusBarBackground;
  }
  
  protected int getSuggestedMinimumHeight() {
    return Math.max(super.getSuggestedMinimumHeight(), getPaddingTop() + getPaddingBottom());
  }
  
  protected int getSuggestedMinimumWidth() {
    return Math.max(super.getSuggestedMinimumWidth(), getPaddingLeft() + getPaddingRight());
  }
  
  public boolean isPointInChildBounds(@NonNull View paramView, int paramInt1, int paramInt2) {
    Rect rect = acquireTempRect();
    getDescendantRect(paramView, rect);
    try {
      return rect.contains(paramInt1, paramInt2);
    } finally {
      releaseTempRect(rect);
    } 
  }
  
  void offsetChildToAnchor(View paramView, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aload_1
    //   3: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   6: checkcast androidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams
    //   9: astore #6
    //   11: aload #6
    //   13: getfield mAnchorView : Landroid/view/View;
    //   16: ifnull -> 212
    //   19: invokestatic acquireTempRect : ()Landroid/graphics/Rect;
    //   22: astore #7
    //   24: invokestatic acquireTempRect : ()Landroid/graphics/Rect;
    //   27: astore #8
    //   29: invokestatic acquireTempRect : ()Landroid/graphics/Rect;
    //   32: astore #9
    //   34: aload_0
    //   35: aload #6
    //   37: getfield mAnchorView : Landroid/view/View;
    //   40: aload #7
    //   42: invokevirtual getDescendantRect : (Landroid/view/View;Landroid/graphics/Rect;)V
    //   45: aload_0
    //   46: aload_1
    //   47: iconst_0
    //   48: aload #8
    //   50: invokevirtual getChildRect : (Landroid/view/View;ZLandroid/graphics/Rect;)V
    //   53: aload_1
    //   54: invokevirtual getMeasuredWidth : ()I
    //   57: istore #4
    //   59: aload_1
    //   60: invokevirtual getMeasuredHeight : ()I
    //   63: istore #5
    //   65: aload_0
    //   66: aload_1
    //   67: iload_2
    //   68: aload #7
    //   70: aload #9
    //   72: aload #6
    //   74: iload #4
    //   76: iload #5
    //   78: invokespecial getDesiredAnchoredChildRectWithoutConstraints : (Landroid/view/View;ILandroid/graphics/Rect;Landroid/graphics/Rect;Landroidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams;II)V
    //   81: aload #9
    //   83: getfield left : I
    //   86: aload #8
    //   88: getfield left : I
    //   91: if_icmpne -> 109
    //   94: iload_3
    //   95: istore_2
    //   96: aload #9
    //   98: getfield top : I
    //   101: aload #8
    //   103: getfield top : I
    //   106: if_icmpeq -> 111
    //   109: iconst_1
    //   110: istore_2
    //   111: aload_0
    //   112: aload #6
    //   114: aload #9
    //   116: iload #4
    //   118: iload #5
    //   120: invokespecial constrainChildRect : (Landroidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams;Landroid/graphics/Rect;II)V
    //   123: aload #9
    //   125: getfield left : I
    //   128: aload #8
    //   130: getfield left : I
    //   133: isub
    //   134: istore_3
    //   135: aload #9
    //   137: getfield top : I
    //   140: aload #8
    //   142: getfield top : I
    //   145: isub
    //   146: istore #4
    //   148: iload_3
    //   149: ifeq -> 157
    //   152: aload_1
    //   153: iload_3
    //   154: invokestatic offsetLeftAndRight : (Landroid/view/View;I)V
    //   157: iload #4
    //   159: ifeq -> 168
    //   162: aload_1
    //   163: iload #4
    //   165: invokestatic offsetTopAndBottom : (Landroid/view/View;I)V
    //   168: iload_2
    //   169: ifeq -> 197
    //   172: aload #6
    //   174: invokevirtual getBehavior : ()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;
    //   177: astore #10
    //   179: aload #10
    //   181: ifnull -> 197
    //   184: aload #10
    //   186: aload_0
    //   187: aload_1
    //   188: aload #6
    //   190: getfield mAnchorView : Landroid/view/View;
    //   193: invokevirtual onDependentViewChanged : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/View;)Z
    //   196: pop
    //   197: aload #7
    //   199: invokestatic releaseTempRect : (Landroid/graphics/Rect;)V
    //   202: aload #8
    //   204: invokestatic releaseTempRect : (Landroid/graphics/Rect;)V
    //   207: aload #9
    //   209: invokestatic releaseTempRect : (Landroid/graphics/Rect;)V
    //   212: return
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    resetTouchBehaviors(false);
    if (this.mNeedsPreDrawListener) {
      if (this.mOnPreDrawListener == null)
        this.mOnPreDrawListener = new OnPreDrawListener(); 
      getViewTreeObserver().addOnPreDrawListener(this.mOnPreDrawListener);
    } 
    if (this.mLastInsets == null && ViewCompat.getFitsSystemWindows((View)this))
      ViewCompat.requestApplyInsets((View)this); 
    this.mIsAttachedToWindow = true;
  }
  
  final void onChildViewsChanged(int paramInt) {
    int j = ViewCompat.getLayoutDirection((View)this);
    int k = this.mDependencySortedChildren.size();
    Rect rect1 = acquireTempRect();
    Rect rect2 = acquireTempRect();
    Rect rect3 = acquireTempRect();
    int i = 0;
    label57: while (i < k) {
      View view = this.mDependencySortedChildren.get(i);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (paramInt == 0 && view.getVisibility() == 8)
        continue; 
      int m;
      for (m = 0; m < i; m++) {
        View view1 = this.mDependencySortedChildren.get(m);
        if (layoutParams.mAnchorDirectChild == view1)
          offsetChildToAnchor(view, j); 
      } 
      getChildRect(view, true, rect2);
      if (layoutParams.insetEdge != 0 && !rect2.isEmpty()) {
        m = GravityCompat.getAbsoluteGravity(layoutParams.insetEdge, j);
        switch (m & 0x70) {
          default:
            switch (m & 0x7) {
              default:
                if (layoutParams.dodgeInsetEdges != 0 && view.getVisibility() == 0)
                  offsetChildByInset(view, rect1, j); 
                if (paramInt != 2) {
                  getLastChildRect(view, rect3);
                  if (!rect3.equals(rect2)) {
                    recordLastChildRect(view, rect2);
                  } else {
                    continue;
                  } 
                } 
                m = i + 1;
                while (true) {
                  if (m < k) {
                    View view1 = this.mDependencySortedChildren.get(m);
                    LayoutParams layoutParams1 = (LayoutParams)view1.getLayoutParams();
                    Behavior<View> behavior = layoutParams1.getBehavior();
                    if (behavior != null && behavior.layoutDependsOn(this, view1, view))
                      if (paramInt == 0 && layoutParams1.getChangedAfterNestedScroll()) {
                        layoutParams1.resetChangedAfterNestedScroll();
                      } else {
                        boolean bool;
                        switch (paramInt) {
                          case 2:
                            behavior.onDependentViewRemoved(this, view1, view);
                            bool = true;
                            if (paramInt == 1)
                              layoutParams1.setChangedAfterNestedScroll(bool); 
                            m++;
                            break;
                        } 
                        continue;
                      }  
                  } else {
                    i++;
                    continue label57;
                  } 
                  m++;
                  break;
                } 
              case 3:
                rect1.left = Math.max(rect1.left, rect2.right);
              case 5:
                break;
            } 
            break;
          case 48:
            rect1.top = Math.max(rect1.top, rect2.bottom);
          case 80:
            rect1.bottom = Math.max(rect1.bottom, getHeight() - rect2.top);
        } 
        rect1.right = Math.max(rect1.right, getWidth() - rect2.left);
      } 
    } 
    releaseTempRect(rect1);
    releaseTempRect(rect2);
    releaseTempRect(rect3);
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    resetTouchBehaviors(false);
    if (this.mNeedsPreDrawListener && this.mOnPreDrawListener != null)
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener); 
    if (this.mNestedScrollingTarget != null)
      onStopNestedScroll(this.mNestedScrollingTarget); 
    this.mIsAttachedToWindow = false;
  }
  
  public void onDraw(Canvas paramCanvas) {
    super.onDraw(paramCanvas);
    if (this.mDrawStatusBarBackground && this.mStatusBarBackground != null) {
      boolean bool;
      if (this.mLastInsets != null) {
        bool = this.mLastInsets.getSystemWindowInsetTop();
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
    int i = paramMotionEvent.getActionMasked();
    if (i == 0)
      resetTouchBehaviors(true); 
    boolean bool = performIntercept(paramMotionEvent, 0);
    if (i == 1 || i == 3)
      resetTouchBehaviors(true); 
    return bool;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramInt2 = ViewCompat.getLayoutDirection((View)this);
    paramInt3 = this.mDependencySortedChildren.size();
    for (paramInt1 = 0; paramInt1 < paramInt3; paramInt1++) {
      View view = this.mDependencySortedChildren.get(paramInt1);
      if (view.getVisibility() != 8) {
        Behavior<View> behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
        if (behavior == null || !behavior.onLayoutChild(this, view, paramInt2))
          onLayoutChild(view, paramInt2); 
      } 
    } 
  }
  
  public void onLayoutChild(@NonNull View paramView, int paramInt) {
    LayoutParams layoutParams = (LayoutParams)paramView.getLayoutParams();
    if (layoutParams.checkAnchorChanged())
      throw new IllegalStateException("An anchor may not be changed after CoordinatorLayout measurement begins before layout is complete."); 
    if (layoutParams.mAnchorView != null) {
      layoutChildWithAnchor(paramView, layoutParams.mAnchorView, paramInt);
      return;
    } 
    if (layoutParams.keyline >= 0) {
      layoutChildWithKeyline(paramView, layoutParams.keyline, paramInt);
      return;
    } 
    layoutChild(paramView, paramInt);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial prepareChildren : ()V
    //   4: aload_0
    //   5: invokevirtual ensurePreDrawListener : ()V
    //   8: aload_0
    //   9: invokevirtual getPaddingLeft : ()I
    //   12: istore #14
    //   14: aload_0
    //   15: invokevirtual getPaddingTop : ()I
    //   18: istore #15
    //   20: aload_0
    //   21: invokevirtual getPaddingRight : ()I
    //   24: istore #16
    //   26: aload_0
    //   27: invokevirtual getPaddingBottom : ()I
    //   30: istore #17
    //   32: aload_0
    //   33: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   36: istore #18
    //   38: iload #18
    //   40: iconst_1
    //   41: if_icmpne -> 159
    //   44: iconst_1
    //   45: istore #4
    //   47: iload_1
    //   48: invokestatic getMode : (I)I
    //   51: istore #19
    //   53: iload_1
    //   54: invokestatic getSize : (I)I
    //   57: istore #20
    //   59: iload_2
    //   60: invokestatic getMode : (I)I
    //   63: istore #21
    //   65: iload_2
    //   66: invokestatic getSize : (I)I
    //   69: istore #22
    //   71: aload_0
    //   72: invokevirtual getSuggestedMinimumWidth : ()I
    //   75: istore #9
    //   77: aload_0
    //   78: invokevirtual getSuggestedMinimumHeight : ()I
    //   81: istore #8
    //   83: iconst_0
    //   84: istore #7
    //   86: aload_0
    //   87: getfield mLastInsets : Landroidx/core/view/WindowInsetsCompat;
    //   90: ifnull -> 165
    //   93: aload_0
    //   94: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   97: ifeq -> 165
    //   100: iconst_1
    //   101: istore #5
    //   103: aload_0
    //   104: getfield mDependencySortedChildren : Ljava/util/List;
    //   107: invokeinterface size : ()I
    //   112: istore #23
    //   114: iconst_0
    //   115: istore #6
    //   117: iload #6
    //   119: iload #23
    //   121: if_icmpge -> 525
    //   124: aload_0
    //   125: getfield mDependencySortedChildren : Ljava/util/List;
    //   128: iload #6
    //   130: invokeinterface get : (I)Ljava/lang/Object;
    //   135: checkcast android/view/View
    //   138: astore #24
    //   140: aload #24
    //   142: invokevirtual getVisibility : ()I
    //   145: bipush #8
    //   147: if_icmpne -> 171
    //   150: iload #6
    //   152: iconst_1
    //   153: iadd
    //   154: istore #6
    //   156: goto -> 117
    //   159: iconst_0
    //   160: istore #4
    //   162: goto -> 47
    //   165: iconst_0
    //   166: istore #5
    //   168: goto -> 103
    //   171: aload #24
    //   173: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   176: checkcast androidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams
    //   179: astore #25
    //   181: iconst_0
    //   182: istore #10
    //   184: iload #10
    //   186: istore_3
    //   187: aload #25
    //   189: getfield keyline : I
    //   192: iflt -> 267
    //   195: iload #10
    //   197: istore_3
    //   198: iload #19
    //   200: ifeq -> 267
    //   203: aload_0
    //   204: aload #25
    //   206: getfield keyline : I
    //   209: invokespecial getKeyline : (I)I
    //   212: istore #11
    //   214: aload #25
    //   216: getfield gravity : I
    //   219: invokestatic resolveKeylineGravity : (I)I
    //   222: iload #18
    //   224: invokestatic getAbsoluteGravity : (II)I
    //   227: bipush #7
    //   229: iand
    //   230: istore #12
    //   232: iload #12
    //   234: iconst_3
    //   235: if_icmpne -> 243
    //   238: iload #4
    //   240: ifeq -> 254
    //   243: iload #12
    //   245: iconst_5
    //   246: if_icmpne -> 484
    //   249: iload #4
    //   251: ifeq -> 484
    //   254: iconst_0
    //   255: iload #20
    //   257: iload #16
    //   259: isub
    //   260: iload #11
    //   262: isub
    //   263: invokestatic max : (II)I
    //   266: istore_3
    //   267: iload_1
    //   268: istore #11
    //   270: iload_2
    //   271: istore #12
    //   273: iload #11
    //   275: istore #13
    //   277: iload #12
    //   279: istore #10
    //   281: iload #5
    //   283: ifeq -> 368
    //   286: iload #11
    //   288: istore #13
    //   290: iload #12
    //   292: istore #10
    //   294: aload #24
    //   296: invokestatic getFitsSystemWindows : (Landroid/view/View;)Z
    //   299: ifne -> 368
    //   302: aload_0
    //   303: getfield mLastInsets : Landroidx/core/view/WindowInsetsCompat;
    //   306: invokevirtual getSystemWindowInsetLeft : ()I
    //   309: istore #12
    //   311: aload_0
    //   312: getfield mLastInsets : Landroidx/core/view/WindowInsetsCompat;
    //   315: invokevirtual getSystemWindowInsetRight : ()I
    //   318: istore #13
    //   320: aload_0
    //   321: getfield mLastInsets : Landroidx/core/view/WindowInsetsCompat;
    //   324: invokevirtual getSystemWindowInsetTop : ()I
    //   327: istore #10
    //   329: aload_0
    //   330: getfield mLastInsets : Landroidx/core/view/WindowInsetsCompat;
    //   333: invokevirtual getSystemWindowInsetBottom : ()I
    //   336: istore #11
    //   338: iload #20
    //   340: iload #12
    //   342: iload #13
    //   344: iadd
    //   345: isub
    //   346: iload #19
    //   348: invokestatic makeMeasureSpec : (II)I
    //   351: istore #13
    //   353: iload #22
    //   355: iload #10
    //   357: iload #11
    //   359: iadd
    //   360: isub
    //   361: iload #21
    //   363: invokestatic makeMeasureSpec : (II)I
    //   366: istore #10
    //   368: aload #25
    //   370: invokevirtual getBehavior : ()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;
    //   373: astore #26
    //   375: aload #26
    //   377: ifnull -> 397
    //   380: aload #26
    //   382: aload_0
    //   383: aload #24
    //   385: iload #13
    //   387: iload_3
    //   388: iload #10
    //   390: iconst_0
    //   391: invokevirtual onMeasureChild : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;IIII)Z
    //   394: ifne -> 409
    //   397: aload_0
    //   398: aload #24
    //   400: iload #13
    //   402: iload_3
    //   403: iload #10
    //   405: iconst_0
    //   406: invokevirtual onMeasureChild : (Landroid/view/View;IIII)V
    //   409: iload #9
    //   411: aload #24
    //   413: invokevirtual getMeasuredWidth : ()I
    //   416: iload #14
    //   418: iload #16
    //   420: iadd
    //   421: iadd
    //   422: aload #25
    //   424: getfield leftMargin : I
    //   427: iadd
    //   428: aload #25
    //   430: getfield rightMargin : I
    //   433: iadd
    //   434: invokestatic max : (II)I
    //   437: istore #9
    //   439: iload #8
    //   441: aload #24
    //   443: invokevirtual getMeasuredHeight : ()I
    //   446: iload #15
    //   448: iload #17
    //   450: iadd
    //   451: iadd
    //   452: aload #25
    //   454: getfield topMargin : I
    //   457: iadd
    //   458: aload #25
    //   460: getfield bottomMargin : I
    //   463: iadd
    //   464: invokestatic max : (II)I
    //   467: istore #8
    //   469: iload #7
    //   471: aload #24
    //   473: invokevirtual getMeasuredState : ()I
    //   476: invokestatic combineMeasuredStates : (II)I
    //   479: istore #7
    //   481: goto -> 150
    //   484: iload #12
    //   486: iconst_5
    //   487: if_icmpne -> 495
    //   490: iload #4
    //   492: ifeq -> 512
    //   495: iload #10
    //   497: istore_3
    //   498: iload #12
    //   500: iconst_3
    //   501: if_icmpne -> 267
    //   504: iload #10
    //   506: istore_3
    //   507: iload #4
    //   509: ifeq -> 267
    //   512: iconst_0
    //   513: iload #11
    //   515: iload #14
    //   517: isub
    //   518: invokestatic max : (II)I
    //   521: istore_3
    //   522: goto -> 267
    //   525: aload_0
    //   526: iload #9
    //   528: iload_1
    //   529: ldc_w -16777216
    //   532: iload #7
    //   534: iand
    //   535: invokestatic resolveSizeAndState : (III)I
    //   538: iload #8
    //   540: iload_2
    //   541: iload #7
    //   543: bipush #16
    //   545: ishl
    //   546: invokestatic resolveSizeAndState : (III)I
    //   549: invokevirtual setMeasuredDimension : (II)V
    //   552: return
  }
  
  public void onMeasureChild(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    measureChildWithMargins(paramView, paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public boolean onNestedFling(View paramView, float paramFloat1, float paramFloat2, boolean paramBoolean) {
    boolean bool = false;
    int j = getChildCount();
    int i = 0;
    while (i < j) {
      boolean bool1;
      View view = getChildAt(i);
      if (view.getVisibility() == 8) {
        bool1 = bool;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        bool1 = bool;
        if (layoutParams.isNestedScrollAccepted(0)) {
          Behavior<View> behavior = layoutParams.getBehavior();
          bool1 = bool;
          if (behavior != null)
            bool1 = bool | behavior.onNestedFling(this, view, paramView, paramFloat1, paramFloat2, paramBoolean); 
        } 
      } 
      i++;
      bool = bool1;
    } 
    if (bool)
      onChildViewsChanged(1); 
    return bool;
  }
  
  public boolean onNestedPreFling(View paramView, float paramFloat1, float paramFloat2) {
    boolean bool = false;
    int j = getChildCount();
    int i = 0;
    while (i < j) {
      boolean bool1;
      View view = getChildAt(i);
      if (view.getVisibility() == 8) {
        bool1 = bool;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        bool1 = bool;
        if (layoutParams.isNestedScrollAccepted(0)) {
          Behavior<View> behavior = layoutParams.getBehavior();
          bool1 = bool;
          if (behavior != null)
            bool1 = bool | behavior.onNestedPreFling(this, view, paramView, paramFloat1, paramFloat2); 
        } 
      } 
      i++;
      bool = bool1;
    } 
    return bool;
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfint) {
    onNestedPreScroll(paramView, paramInt1, paramInt2, paramArrayOfint, 0);
  }
  
  public void onNestedPreScroll(View paramView, int paramInt1, int paramInt2, int[] paramArrayOfint, int paramInt3) {
    int i = 0;
    int j = 0;
    boolean bool = false;
    int m = getChildCount();
    int k = 0;
    while (k < m) {
      boolean bool1;
      int n;
      int i1;
      View view = getChildAt(k);
      if (view.getVisibility() == 8) {
        i1 = j;
        n = i;
        bool1 = bool;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        bool1 = bool;
        n = i;
        i1 = j;
        if (layoutParams.isNestedScrollAccepted(paramInt3)) {
          Behavior<View> behavior = layoutParams.getBehavior();
          bool1 = bool;
          n = i;
          i1 = j;
          if (behavior != null) {
            int[] arrayOfInt = this.mTempIntPair;
            this.mTempIntPair[1] = 0;
            arrayOfInt[0] = 0;
            behavior.onNestedPreScroll(this, view, paramView, paramInt1, paramInt2, this.mTempIntPair, paramInt3);
            if (paramInt1 > 0) {
              i = Math.max(i, this.mTempIntPair[0]);
            } else {
              i = Math.min(i, this.mTempIntPair[0]);
            } 
            if (paramInt2 > 0) {
              j = Math.max(j, this.mTempIntPair[1]);
            } else {
              j = Math.min(j, this.mTempIntPair[1]);
            } 
            bool1 = true;
            n = i;
            i1 = j;
          } 
        } 
      } 
      k++;
      bool = bool1;
      i = n;
      j = i1;
    } 
    paramArrayOfint[0] = i;
    paramArrayOfint[1] = j;
    if (bool)
      onChildViewsChanged(1); 
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    onNestedScroll(paramView, paramInt1, paramInt2, paramInt3, paramInt4, 0);
  }
  
  public void onNestedScroll(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    int j = getChildCount();
    boolean bool = false;
    int i = 0;
    while (i < j) {
      boolean bool1;
      View view = getChildAt(i);
      if (view.getVisibility() == 8) {
        bool1 = bool;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        bool1 = bool;
        if (layoutParams.isNestedScrollAccepted(paramInt5)) {
          Behavior<View> behavior = layoutParams.getBehavior();
          bool1 = bool;
          if (behavior != null) {
            behavior.onNestedScroll(this, view, paramView, paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
            bool1 = true;
          } 
        } 
      } 
      i++;
      bool = bool1;
    } 
    if (bool)
      onChildViewsChanged(1); 
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt) {
    onNestedScrollAccepted(paramView1, paramView2, paramInt, 0);
  }
  
  public void onNestedScrollAccepted(View paramView1, View paramView2, int paramInt1, int paramInt2) {
    this.mNestedScrollingParentHelper.onNestedScrollAccepted(paramView1, paramView2, paramInt1, paramInt2);
    this.mNestedScrollingTarget = paramView2;
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (layoutParams.isNestedScrollAccepted(paramInt2)) {
        Behavior<View> behavior = layoutParams.getBehavior();
        if (behavior != null)
          behavior.onNestedScrollAccepted(this, view, paramView1, paramView2, paramInt1, paramInt2); 
      } 
    } 
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable) {
    if (!(paramParcelable instanceof SavedState)) {
      super.onRestoreInstanceState(paramParcelable);
      return;
    } 
    SavedState savedState = (SavedState)paramParcelable;
    super.onRestoreInstanceState(savedState.getSuperState());
    SparseArray<Parcelable> sparseArray = savedState.behaviorStates;
    int i = 0;
    int j = getChildCount();
    while (true) {
      if (i < j) {
        View view = getChildAt(i);
        int k = view.getId();
        Behavior<View> behavior = getResolvedLayoutParams(view).getBehavior();
        if (k != -1 && behavior != null) {
          Parcelable parcelable = (Parcelable)sparseArray.get(k);
          if (parcelable != null)
            behavior.onRestoreInstanceState(this, view, parcelable); 
        } 
        i++;
        continue;
      } 
      return;
    } 
  }
  
  protected Parcelable onSaveInstanceState() {
    SavedState savedState = new SavedState(super.onSaveInstanceState());
    SparseArray<Parcelable> sparseArray = new SparseArray();
    int i = 0;
    int j = getChildCount();
    while (i < j) {
      View view = getChildAt(i);
      int k = view.getId();
      Behavior<View> behavior = ((LayoutParams)view.getLayoutParams()).getBehavior();
      if (k != -1 && behavior != null) {
        Parcelable parcelable = behavior.onSaveInstanceState(this, view);
        if (parcelable != null)
          sparseArray.append(k, parcelable); 
      } 
      i++;
    } 
    savedState.behaviorStates = sparseArray;
    return (Parcelable)savedState;
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt) {
    return onStartNestedScroll(paramView1, paramView2, paramInt, 0);
  }
  
  public boolean onStartNestedScroll(View paramView1, View paramView2, int paramInt1, int paramInt2) {
    boolean bool = false;
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        Behavior<View> behavior = layoutParams.getBehavior();
        if (behavior != null) {
          boolean bool1 = behavior.onStartNestedScroll(this, view, paramView1, paramView2, paramInt1, paramInt2);
          bool |= bool1;
          layoutParams.setNestedScrollAccepted(paramInt2, bool1);
        } else {
          layoutParams.setNestedScrollAccepted(paramInt2, false);
        } 
      } 
    } 
    return bool;
  }
  
  public void onStopNestedScroll(View paramView) {
    onStopNestedScroll(paramView, 0);
  }
  
  public void onStopNestedScroll(View paramView, int paramInt) {
    this.mNestedScrollingParentHelper.onStopNestedScroll(paramView, paramInt);
    int j = getChildCount();
    for (int i = 0; i < j; i++) {
      View view = getChildAt(i);
      LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
      if (layoutParams.isNestedScrollAccepted(paramInt)) {
        Behavior<View> behavior = layoutParams.getBehavior();
        if (behavior != null)
          behavior.onStopNestedScroll(this, view, paramView, paramInt); 
        layoutParams.resetNestedScroll(paramInt);
        layoutParams.resetChangedAfterNestedScroll();
      } 
    } 
    this.mNestedScrollingTarget = null;
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #6
    //   3: iconst_0
    //   4: istore #4
    //   6: aconst_null
    //   7: astore #9
    //   9: aconst_null
    //   10: astore #10
    //   12: aload_1
    //   13: invokevirtual getActionMasked : ()I
    //   16: istore_2
    //   17: aload_0
    //   18: getfield mBehaviorTouchView : Landroid/view/View;
    //   21: ifnonnull -> 44
    //   24: aload_0
    //   25: aload_1
    //   26: iconst_1
    //   27: invokespecial performIntercept : (Landroid/view/MotionEvent;I)Z
    //   30: istore #4
    //   32: iload #4
    //   34: istore #5
    //   36: iload #6
    //   38: istore_3
    //   39: iload #4
    //   41: ifeq -> 87
    //   44: aload_0
    //   45: getfield mBehaviorTouchView : Landroid/view/View;
    //   48: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   51: checkcast androidx/coordinatorlayout/widget/CoordinatorLayout$LayoutParams
    //   54: invokevirtual getBehavior : ()Landroidx/coordinatorlayout/widget/CoordinatorLayout$Behavior;
    //   57: astore #11
    //   59: iload #4
    //   61: istore #5
    //   63: iload #6
    //   65: istore_3
    //   66: aload #11
    //   68: ifnull -> 87
    //   71: aload #11
    //   73: aload_0
    //   74: aload_0
    //   75: getfield mBehaviorTouchView : Landroid/view/View;
    //   78: aload_1
    //   79: invokevirtual onTouchEvent : (Landroidx/coordinatorlayout/widget/CoordinatorLayout;Landroid/view/View;Landroid/view/MotionEvent;)Z
    //   82: istore_3
    //   83: iload #4
    //   85: istore #5
    //   87: aload_0
    //   88: getfield mBehaviorTouchView : Landroid/view/View;
    //   91: ifnonnull -> 141
    //   94: iload_3
    //   95: aload_0
    //   96: aload_1
    //   97: invokespecial onTouchEvent : (Landroid/view/MotionEvent;)Z
    //   100: ior
    //   101: istore #4
    //   103: aload #10
    //   105: astore_1
    //   106: iload #4
    //   108: ifne -> 115
    //   111: iload_2
    //   112: ifne -> 115
    //   115: aload_1
    //   116: ifnull -> 123
    //   119: aload_1
    //   120: invokevirtual recycle : ()V
    //   123: iload_2
    //   124: iconst_1
    //   125: if_icmpeq -> 133
    //   128: iload_2
    //   129: iconst_3
    //   130: if_icmpne -> 138
    //   133: aload_0
    //   134: iconst_0
    //   135: invokespecial resetTouchBehaviors : (Z)V
    //   138: iload #4
    //   140: ireturn
    //   141: aload #10
    //   143: astore_1
    //   144: iload_3
    //   145: istore #4
    //   147: iload #5
    //   149: ifeq -> 106
    //   152: aload #9
    //   154: astore_1
    //   155: iconst_0
    //   156: ifne -> 176
    //   159: invokestatic uptimeMillis : ()J
    //   162: lstore #7
    //   164: lload #7
    //   166: lload #7
    //   168: iconst_3
    //   169: fconst_0
    //   170: fconst_0
    //   171: iconst_0
    //   172: invokestatic obtain : (JJIFFI)Landroid/view/MotionEvent;
    //   175: astore_1
    //   176: aload_0
    //   177: aload_1
    //   178: invokespecial onTouchEvent : (Landroid/view/MotionEvent;)Z
    //   181: pop
    //   182: iload_3
    //   183: istore #4
    //   185: goto -> 106
  }
  
  void recordLastChildRect(View paramView, Rect paramRect) {
    ((LayoutParams)paramView.getLayoutParams()).setLastChildRect(paramRect);
  }
  
  void removePreDrawListener() {
    if (this.mIsAttachedToWindow && this.mOnPreDrawListener != null)
      getViewTreeObserver().removeOnPreDrawListener(this.mOnPreDrawListener); 
    this.mNeedsPreDrawListener = false;
  }
  
  public boolean requestChildRectangleOnScreen(View paramView, Rect paramRect, boolean paramBoolean) {
    Behavior<View> behavior = ((LayoutParams)paramView.getLayoutParams()).getBehavior();
    return (behavior != null && behavior.onRequestChildRectangleOnScreen(this, paramView, paramRect, paramBoolean)) ? true : super.requestChildRectangleOnScreen(paramView, paramRect, paramBoolean);
  }
  
  public void requestDisallowInterceptTouchEvent(boolean paramBoolean) {
    super.requestDisallowInterceptTouchEvent(paramBoolean);
    if (paramBoolean && !this.mDisallowInterceptReset) {
      resetTouchBehaviors(false);
      this.mDisallowInterceptReset = true;
    } 
  }
  
  public void setFitsSystemWindows(boolean paramBoolean) {
    super.setFitsSystemWindows(paramBoolean);
    setupForInsets();
  }
  
  public void setOnHierarchyChangeListener(ViewGroup.OnHierarchyChangeListener paramOnHierarchyChangeListener) {
    this.mOnHierarchyChangeListener = paramOnHierarchyChangeListener;
  }
  
  public void setStatusBarBackground(@Nullable Drawable paramDrawable) {
    Drawable drawable = null;
    if (this.mStatusBarBackground != paramDrawable) {
      if (this.mStatusBarBackground != null)
        this.mStatusBarBackground.setCallback(null); 
      if (paramDrawable != null)
        drawable = paramDrawable.mutate(); 
      this.mStatusBarBackground = drawable;
      if (this.mStatusBarBackground != null) {
        boolean bool;
        if (this.mStatusBarBackground.isStateful())
          this.mStatusBarBackground.setState(getDrawableState()); 
        DrawableCompat.setLayoutDirection(this.mStatusBarBackground, ViewCompat.getLayoutDirection((View)this));
        paramDrawable = this.mStatusBarBackground;
        if (getVisibility() == 0) {
          bool = true;
        } else {
          bool = false;
        } 
        paramDrawable.setVisible(bool, false);
        this.mStatusBarBackground.setCallback((Drawable.Callback)this);
      } 
      ViewCompat.postInvalidateOnAnimation((View)this);
    } 
  }
  
  public void setStatusBarBackgroundColor(@ColorInt int paramInt) {
    setStatusBarBackground((Drawable)new ColorDrawable(paramInt));
  }
  
  public void setStatusBarBackgroundResource(@DrawableRes int paramInt) {
    Drawable drawable;
    if (paramInt != 0) {
      drawable = ContextCompat.getDrawable(getContext(), paramInt);
    } else {
      drawable = null;
    } 
    setStatusBarBackground(drawable);
  }
  
  public void setVisibility(int paramInt) {
    boolean bool;
    super.setVisibility(paramInt);
    if (paramInt == 0) {
      bool = true;
    } else {
      bool = false;
    } 
    if (this.mStatusBarBackground != null && this.mStatusBarBackground.isVisible() != bool)
      this.mStatusBarBackground.setVisible(bool, false); 
  }
  
  final WindowInsetsCompat setWindowInsets(WindowInsetsCompat paramWindowInsetsCompat) {
    boolean bool = true;
    WindowInsetsCompat windowInsetsCompat = paramWindowInsetsCompat;
    if (!ObjectsCompat.equals(this.mLastInsets, paramWindowInsetsCompat)) {
      boolean bool1;
      this.mLastInsets = paramWindowInsetsCompat;
      if (paramWindowInsetsCompat != null && paramWindowInsetsCompat.getSystemWindowInsetTop() > 0) {
        bool1 = true;
      } else {
        bool1 = false;
      } 
      this.mDrawStatusBarBackground = bool1;
      if (!this.mDrawStatusBarBackground && getBackground() == null) {
        bool1 = bool;
      } else {
        bool1 = false;
      } 
      setWillNotDraw(bool1);
      windowInsetsCompat = dispatchApplyWindowInsetsToBehaviors(paramWindowInsetsCompat);
      requestLayout();
    } 
    return windowInsetsCompat;
  }
  
  protected boolean verifyDrawable(Drawable paramDrawable) {
    return (super.verifyDrawable(paramDrawable) || paramDrawable == this.mStatusBarBackground);
  }
  
  public static interface AttachedBehavior {
    @NonNull
    CoordinatorLayout.Behavior getBehavior();
  }
  
  public static abstract class Behavior<V extends View> {
    public Behavior() {}
    
    public Behavior(Context param1Context, AttributeSet param1AttributeSet) {}
    
    @Nullable
    public static Object getTag(@NonNull View param1View) {
      return ((CoordinatorLayout.LayoutParams)param1View.getLayoutParams()).mBehaviorTag;
    }
    
    public static void setTag(@NonNull View param1View, @Nullable Object param1Object) {
      ((CoordinatorLayout.LayoutParams)param1View.getLayoutParams()).mBehaviorTag = param1Object;
    }
    
    public boolean blocksInteractionBelow(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V) {
      return (getScrimOpacity(param1CoordinatorLayout, param1V) > 0.0F);
    }
    
    public boolean getInsetDodgeRect(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull Rect param1Rect) {
      return false;
    }
    
    @ColorInt
    public int getScrimColor(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V) {
      return -16777216;
    }
    
    @FloatRange(from = 0.0D, to = 1.0D)
    public float getScrimOpacity(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V) {
      return 0.0F;
    }
    
    public boolean layoutDependsOn(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View) {
      return false;
    }
    
    @NonNull
    public WindowInsetsCompat onApplyWindowInsets(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull WindowInsetsCompat param1WindowInsetsCompat) {
      return param1WindowInsetsCompat;
    }
    
    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams param1LayoutParams) {}
    
    public boolean onDependentViewChanged(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View) {
      return false;
    }
    
    public void onDependentViewRemoved(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View) {}
    
    public void onDetachedFromLayoutParams() {}
    
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull MotionEvent param1MotionEvent) {
      return false;
    }
    
    public boolean onLayoutChild(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, int param1Int) {
      return false;
    }
    
    public boolean onMeasureChild(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {
      return false;
    }
    
    public boolean onNestedFling(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, float param1Float1, float param1Float2, boolean param1Boolean) {
      return false;
    }
    
    public boolean onNestedPreFling(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, float param1Float1, float param1Float2) {
      return false;
    }
    
    @Deprecated
    public void onNestedPreScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, int param1Int1, int param1Int2, @NonNull int[] param1ArrayOfint) {}
    
    public void onNestedPreScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, int param1Int1, int param1Int2, @NonNull int[] param1ArrayOfint, int param1Int3) {
      if (param1Int3 == 0)
        onNestedPreScroll(param1CoordinatorLayout, param1V, param1View, param1Int1, param1Int2, param1ArrayOfint); 
    }
    
    @Deprecated
    public void onNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4) {}
    
    public void onNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) {
      if (param1Int5 == 0)
        onNestedScroll(param1CoordinatorLayout, param1V, param1View, param1Int1, param1Int2, param1Int3, param1Int4); 
    }
    
    @Deprecated
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View1, @NonNull View param1View2, int param1Int) {}
    
    public void onNestedScrollAccepted(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View1, @NonNull View param1View2, int param1Int1, int param1Int2) {
      if (param1Int2 == 0)
        onNestedScrollAccepted(param1CoordinatorLayout, param1V, param1View1, param1View2, param1Int1); 
    }
    
    public boolean onRequestChildRectangleOnScreen(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull Rect param1Rect, boolean param1Boolean) {
      return false;
    }
    
    public void onRestoreInstanceState(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull Parcelable param1Parcelable) {}
    
    @Nullable
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V) {
      return (Parcelable)View.BaseSavedState.EMPTY_STATE;
    }
    
    @Deprecated
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View1, @NonNull View param1View2, int param1Int) {
      return false;
    }
    
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View1, @NonNull View param1View2, int param1Int1, int param1Int2) {
      return (param1Int2 == 0) ? onStartNestedScroll(param1CoordinatorLayout, param1V, param1View1, param1View2, param1Int1) : false;
    }
    
    @Deprecated
    public void onStopNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View) {}
    
    public void onStopNestedScroll(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull View param1View, int param1Int) {
      if (param1Int == 0)
        onStopNestedScroll(param1CoordinatorLayout, param1V, param1View); 
    }
    
    public boolean onTouchEvent(@NonNull CoordinatorLayout param1CoordinatorLayout, @NonNull V param1V, @NonNull MotionEvent param1MotionEvent) {
      return false;
    }
  }
  
  @Deprecated
  @Retention(RetentionPolicy.RUNTIME)
  public static @interface DefaultBehavior {
    Class<? extends CoordinatorLayout.Behavior> value();
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DispatchChangeEvent {}
  
  private class HierarchyChangeListener implements ViewGroup.OnHierarchyChangeListener {
    public void onChildViewAdded(View param1View1, View param1View2) {
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null)
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewAdded(param1View1, param1View2); 
    }
    
    public void onChildViewRemoved(View param1View1, View param1View2) {
      CoordinatorLayout.this.onChildViewsChanged(2);
      if (CoordinatorLayout.this.mOnHierarchyChangeListener != null)
        CoordinatorLayout.this.mOnHierarchyChangeListener.onChildViewRemoved(param1View1, param1View2); 
    }
  }
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int anchorGravity = 0;
    
    public int dodgeInsetEdges = 0;
    
    public int gravity = 0;
    
    public int insetEdge = 0;
    
    public int keyline = -1;
    
    View mAnchorDirectChild;
    
    int mAnchorId = -1;
    
    View mAnchorView;
    
    CoordinatorLayout.Behavior mBehavior;
    
    boolean mBehaviorResolved = false;
    
    Object mBehaviorTag;
    
    private boolean mDidAcceptNestedScrollNonTouch;
    
    private boolean mDidAcceptNestedScrollTouch;
    
    private boolean mDidBlockInteraction;
    
    private boolean mDidChangeAfterNestedScroll;
    
    int mInsetOffsetX;
    
    int mInsetOffsetY;
    
    final Rect mLastChildRect = new Rect();
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
    }
    
    LayoutParams(@NonNull Context param1Context, @Nullable AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.CoordinatorLayout_Layout);
      this.gravity = typedArray.getInteger(R.styleable.CoordinatorLayout_Layout_android_layout_gravity, 0);
      this.mAnchorId = typedArray.getResourceId(R.styleable.CoordinatorLayout_Layout_layout_anchor, -1);
      this.anchorGravity = typedArray.getInteger(R.styleable.CoordinatorLayout_Layout_layout_anchorGravity, 0);
      this.keyline = typedArray.getInteger(R.styleable.CoordinatorLayout_Layout_layout_keyline, -1);
      this.insetEdge = typedArray.getInt(R.styleable.CoordinatorLayout_Layout_layout_insetEdge, 0);
      this.dodgeInsetEdges = typedArray.getInt(R.styleable.CoordinatorLayout_Layout_layout_dodgeInsetEdges, 0);
      this.mBehaviorResolved = typedArray.hasValue(R.styleable.CoordinatorLayout_Layout_layout_behavior);
      if (this.mBehaviorResolved)
        this.mBehavior = CoordinatorLayout.parseBehavior(param1Context, param1AttributeSet, typedArray.getString(R.styleable.CoordinatorLayout_Layout_layout_behavior)); 
      typedArray.recycle();
      if (this.mBehavior != null)
        this.mBehavior.onAttachedToLayoutParams(this); 
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    private void resolveAnchorView(View param1View, CoordinatorLayout param1CoordinatorLayout) {
      this.mAnchorView = param1CoordinatorLayout.findViewById(this.mAnchorId);
      if (this.mAnchorView != null) {
        if (this.mAnchorView == param1CoordinatorLayout) {
          if (param1CoordinatorLayout.isInEditMode()) {
            this.mAnchorDirectChild = null;
            this.mAnchorView = null;
            return;
          } 
          throw new IllegalStateException("View can not be anchored to the the parent CoordinatorLayout");
        } 
        View view = this.mAnchorView;
        for (ViewParent viewParent = this.mAnchorView.getParent(); viewParent != param1CoordinatorLayout && viewParent != null; viewParent = viewParent.getParent()) {
          if (viewParent == param1View) {
            if (param1CoordinatorLayout.isInEditMode()) {
              this.mAnchorDirectChild = null;
              this.mAnchorView = null;
              return;
            } 
            throw new IllegalStateException("Anchor must not be a descendant of the anchored view");
          } 
          if (viewParent instanceof View)
            view = (View)viewParent; 
        } 
        this.mAnchorDirectChild = view;
        return;
      } 
      if (param1CoordinatorLayout.isInEditMode()) {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return;
      } 
      throw new IllegalStateException("Could not find CoordinatorLayout descendant view with id " + param1CoordinatorLayout.getResources().getResourceName(this.mAnchorId) + " to anchor view " + param1View);
    }
    
    private boolean shouldDodge(View param1View, int param1Int) {
      int i = GravityCompat.getAbsoluteGravity(((LayoutParams)param1View.getLayoutParams()).insetEdge, param1Int);
      return (i != 0 && (GravityCompat.getAbsoluteGravity(this.dodgeInsetEdges, param1Int) & i) == i);
    }
    
    private boolean verifyAnchorView(View param1View, CoordinatorLayout param1CoordinatorLayout) {
      if (this.mAnchorView.getId() != this.mAnchorId)
        return false; 
      View view = this.mAnchorView;
      for (ViewParent viewParent = this.mAnchorView.getParent(); viewParent != param1CoordinatorLayout; viewParent = viewParent.getParent()) {
        if (viewParent == null || viewParent == param1View) {
          this.mAnchorDirectChild = null;
          this.mAnchorView = null;
          return false;
        } 
        if (viewParent instanceof View)
          view = (View)viewParent; 
      } 
      this.mAnchorDirectChild = view;
      return true;
    }
    
    boolean checkAnchorChanged() {
      return (this.mAnchorView == null && this.mAnchorId != -1);
    }
    
    boolean dependsOn(CoordinatorLayout param1CoordinatorLayout, View param1View1, View param1View2) {
      return (param1View2 == this.mAnchorDirectChild || shouldDodge(param1View2, ViewCompat.getLayoutDirection((View)param1CoordinatorLayout)) || (this.mBehavior != null && this.mBehavior.layoutDependsOn(param1CoordinatorLayout, param1View1, param1View2)));
    }
    
    boolean didBlockInteraction() {
      if (this.mBehavior == null)
        this.mDidBlockInteraction = false; 
      return this.mDidBlockInteraction;
    }
    
    View findAnchorView(CoordinatorLayout param1CoordinatorLayout, View param1View) {
      if (this.mAnchorId == -1) {
        this.mAnchorDirectChild = null;
        this.mAnchorView = null;
        return null;
      } 
      if (this.mAnchorView == null || !verifyAnchorView(param1View, param1CoordinatorLayout))
        resolveAnchorView(param1View, param1CoordinatorLayout); 
      return this.mAnchorView;
    }
    
    @IdRes
    public int getAnchorId() {
      return this.mAnchorId;
    }
    
    @Nullable
    public CoordinatorLayout.Behavior getBehavior() {
      return this.mBehavior;
    }
    
    boolean getChangedAfterNestedScroll() {
      return this.mDidChangeAfterNestedScroll;
    }
    
    Rect getLastChildRect() {
      return this.mLastChildRect;
    }
    
    void invalidateAnchor() {
      this.mAnchorDirectChild = null;
      this.mAnchorView = null;
    }
    
    boolean isBlockingInteractionBelow(CoordinatorLayout param1CoordinatorLayout, View param1View) {
      if (this.mDidBlockInteraction)
        return true; 
      boolean bool2 = this.mDidBlockInteraction;
      if (this.mBehavior != null) {
        boolean bool = this.mBehavior.blocksInteractionBelow(param1CoordinatorLayout, param1View);
        bool |= bool2;
        this.mDidBlockInteraction = bool;
        return bool;
      } 
      boolean bool1 = false;
      bool1 |= bool2;
      this.mDidBlockInteraction = bool1;
      return bool1;
    }
    
    boolean isNestedScrollAccepted(int param1Int) {
      switch (param1Int) {
        default:
          return false;
        case 0:
          return this.mDidAcceptNestedScrollTouch;
        case 1:
          break;
      } 
      return this.mDidAcceptNestedScrollNonTouch;
    }
    
    void resetChangedAfterNestedScroll() {
      this.mDidChangeAfterNestedScroll = false;
    }
    
    void resetNestedScroll(int param1Int) {
      setNestedScrollAccepted(param1Int, false);
    }
    
    void resetTouchBehaviorTracking() {
      this.mDidBlockInteraction = false;
    }
    
    public void setAnchorId(@IdRes int param1Int) {
      invalidateAnchor();
      this.mAnchorId = param1Int;
    }
    
    public void setBehavior(@Nullable CoordinatorLayout.Behavior param1Behavior) {
      if (this.mBehavior != param1Behavior) {
        if (this.mBehavior != null)
          this.mBehavior.onDetachedFromLayoutParams(); 
        this.mBehavior = param1Behavior;
        this.mBehaviorTag = null;
        this.mBehaviorResolved = true;
        if (param1Behavior != null)
          param1Behavior.onAttachedToLayoutParams(this); 
      } 
    }
    
    void setChangedAfterNestedScroll(boolean param1Boolean) {
      this.mDidChangeAfterNestedScroll = param1Boolean;
    }
    
    void setLastChildRect(Rect param1Rect) {
      this.mLastChildRect.set(param1Rect);
    }
    
    void setNestedScrollAccepted(int param1Int, boolean param1Boolean) {
      switch (param1Int) {
        default:
          return;
        case 0:
          this.mDidAcceptNestedScrollTouch = param1Boolean;
          return;
        case 1:
          break;
      } 
      this.mDidAcceptNestedScrollNonTouch = param1Boolean;
    }
  }
  
  class OnPreDrawListener implements ViewTreeObserver.OnPreDrawListener {
    public boolean onPreDraw() {
      CoordinatorLayout.this.onChildViewsChanged(0);
      return true;
    }
  }
  
  protected static class SavedState extends AbsSavedState {
    public static final Parcelable.Creator<SavedState> CREATOR = (Parcelable.Creator<SavedState>)new Parcelable.ClassLoaderCreator<SavedState>() {
        public CoordinatorLayout.SavedState createFromParcel(Parcel param2Parcel) {
          return new CoordinatorLayout.SavedState(param2Parcel, null);
        }
        
        public CoordinatorLayout.SavedState createFromParcel(Parcel param2Parcel, ClassLoader param2ClassLoader) {
          return new CoordinatorLayout.SavedState(param2Parcel, param2ClassLoader);
        }
        
        public CoordinatorLayout.SavedState[] newArray(int param2Int) {
          return new CoordinatorLayout.SavedState[param2Int];
        }
      };
    
    SparseArray<Parcelable> behaviorStates;
    
    public SavedState(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      super(param1Parcel, param1ClassLoader);
      int j = param1Parcel.readInt();
      int[] arrayOfInt = new int[j];
      param1Parcel.readIntArray(arrayOfInt);
      Parcelable[] arrayOfParcelable = param1Parcel.readParcelableArray(param1ClassLoader);
      this.behaviorStates = new SparseArray(j);
      for (int i = 0; i < j; i++)
        this.behaviorStates.append(arrayOfInt[i], arrayOfParcelable[i]); 
    }
    
    public SavedState(Parcelable param1Parcelable) {
      super(param1Parcelable);
    }
    
    public void writeToParcel(Parcel param1Parcel, int param1Int) {
      byte b;
      super.writeToParcel(param1Parcel, param1Int);
      if (this.behaviorStates != null) {
        b = this.behaviorStates.size();
      } else {
        b = 0;
      } 
      param1Parcel.writeInt(b);
      int[] arrayOfInt = new int[b];
      Parcelable[] arrayOfParcelable = new Parcelable[b];
      int i;
      for (i = 0; i < b; i++) {
        arrayOfInt[i] = this.behaviorStates.keyAt(i);
        arrayOfParcelable[i] = (Parcelable)this.behaviorStates.valueAt(i);
      } 
      param1Parcel.writeIntArray(arrayOfInt);
      param1Parcel.writeParcelableArray(arrayOfParcelable, param1Int);
    }
  }
  
  static final class null implements Parcelable.ClassLoaderCreator<SavedState> {
    public CoordinatorLayout.SavedState createFromParcel(Parcel param1Parcel) {
      return new CoordinatorLayout.SavedState(param1Parcel, null);
    }
    
    public CoordinatorLayout.SavedState createFromParcel(Parcel param1Parcel, ClassLoader param1ClassLoader) {
      return new CoordinatorLayout.SavedState(param1Parcel, param1ClassLoader);
    }
    
    public CoordinatorLayout.SavedState[] newArray(int param1Int) {
      return new CoordinatorLayout.SavedState[param1Int];
    }
  }
  
  static class ViewElevationComparator implements Comparator<View> {
    public int compare(View param1View1, View param1View2) {
      float f1 = ViewCompat.getZ(param1View1);
      float f2 = ViewCompat.getZ(param1View2);
      return (f1 > f2) ? -1 : ((f1 < f2) ? 1 : 0);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/coordinatorlayout/widget/CoordinatorLayout.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */