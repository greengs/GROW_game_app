package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class LinearLayoutCompat extends ViewGroup {
  public static final int HORIZONTAL = 0;
  
  private static final int INDEX_BOTTOM = 2;
  
  private static final int INDEX_CENTER_VERTICAL = 0;
  
  private static final int INDEX_FILL = 3;
  
  private static final int INDEX_TOP = 1;
  
  public static final int SHOW_DIVIDER_BEGINNING = 1;
  
  public static final int SHOW_DIVIDER_END = 4;
  
  public static final int SHOW_DIVIDER_MIDDLE = 2;
  
  public static final int SHOW_DIVIDER_NONE = 0;
  
  public static final int VERTICAL = 1;
  
  private static final int VERTICAL_GRAVITY_COUNT = 4;
  
  private boolean mBaselineAligned = true;
  
  private int mBaselineAlignedChildIndex = -1;
  
  private int mBaselineChildTop = 0;
  
  private Drawable mDivider;
  
  private int mDividerHeight;
  
  private int mDividerPadding;
  
  private int mDividerWidth;
  
  private int mGravity = 8388659;
  
  private int[] mMaxAscent;
  
  private int[] mMaxDescent;
  
  private int mOrientation;
  
  private int mShowDividers;
  
  private int mTotalLength;
  
  private boolean mUseLargestChild;
  
  private float mWeightSum;
  
  public LinearLayoutCompat(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public LinearLayoutCompat(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.LinearLayoutCompat, paramInt, 0);
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_orientation, -1);
    if (paramInt >= 0)
      setOrientation(paramInt); 
    paramInt = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_gravity, -1);
    if (paramInt >= 0)
      setGravity(paramInt); 
    boolean bool = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_android_baselineAligned, true);
    if (!bool)
      setBaselineAligned(bool); 
    this.mWeightSum = tintTypedArray.getFloat(R.styleable.LinearLayoutCompat_android_weightSum, -1.0F);
    this.mBaselineAlignedChildIndex = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_android_baselineAlignedChildIndex, -1);
    this.mUseLargestChild = tintTypedArray.getBoolean(R.styleable.LinearLayoutCompat_measureWithLargestChild, false);
    setDividerDrawable(tintTypedArray.getDrawable(R.styleable.LinearLayoutCompat_divider));
    this.mShowDividers = tintTypedArray.getInt(R.styleable.LinearLayoutCompat_showDividers, 0);
    this.mDividerPadding = tintTypedArray.getDimensionPixelSize(R.styleable.LinearLayoutCompat_dividerPadding, 0);
    tintTypedArray.recycle();
  }
  
  private void forceUniformHeight(int paramInt1, int paramInt2) {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824);
    for (int i = 0; i < paramInt1; i++) {
      View view = getVirtualChildAt(i);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.height == -1) {
          int k = layoutParams.width;
          layoutParams.width = view.getMeasuredWidth();
          measureChildWithMargins(view, paramInt2, 0, j, 0);
          layoutParams.width = k;
        } 
      } 
    } 
  }
  
  private void forceUniformWidth(int paramInt1, int paramInt2) {
    int j = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 1073741824);
    for (int i = 0; i < paramInt1; i++) {
      View view = getVirtualChildAt(i);
      if (view.getVisibility() != 8) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (layoutParams.width == -1) {
          int k = layoutParams.height;
          layoutParams.height = view.getMeasuredHeight();
          measureChildWithMargins(view, j, 0, paramInt2, 0);
          layoutParams.height = k;
        } 
      } 
    } 
  }
  
  private void setChildFrame(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    paramView.layout(paramInt1, paramInt2, paramInt1 + paramInt3, paramInt2 + paramInt4);
  }
  
  protected boolean checkLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return paramLayoutParams instanceof LayoutParams;
  }
  
  void drawDividersHorizontal(Canvas paramCanvas) {
    int j = getVirtualChildCount();
    boolean bool = ViewUtils.isLayoutRtl((View)this);
    int i;
    for (i = 0; i < j; i++) {
      View view = getVirtualChildAt(i);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
        int k;
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          k = view.getRight() + layoutParams.rightMargin;
        } else {
          k = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } 
        drawVerticalDivider(paramCanvas, k);
      } 
    } 
    if (hasDividerBeforeChildAt(j)) {
      View view = getVirtualChildAt(j - 1);
      if (view == null) {
        if (bool) {
          i = getPaddingLeft();
        } else {
          i = getWidth() - getPaddingRight() - this.mDividerWidth;
        } 
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        if (bool) {
          i = view.getLeft() - layoutParams.leftMargin - this.mDividerWidth;
        } else {
          i = view.getRight() + layoutParams.rightMargin;
        } 
      } 
      drawVerticalDivider(paramCanvas, i);
    } 
  }
  
  void drawDividersVertical(Canvas paramCanvas) {
    int j = getVirtualChildCount();
    int i;
    for (i = 0; i < j; i++) {
      View view = getVirtualChildAt(i);
      if (view != null && view.getVisibility() != 8 && hasDividerBeforeChildAt(i)) {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        drawHorizontalDivider(paramCanvas, view.getTop() - layoutParams.topMargin - this.mDividerHeight);
      } 
    } 
    if (hasDividerBeforeChildAt(j)) {
      View view = getVirtualChildAt(j - 1);
      if (view == null) {
        i = getHeight() - getPaddingBottom() - this.mDividerHeight;
      } else {
        LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        i = view.getBottom() + layoutParams.bottomMargin;
      } 
      drawHorizontalDivider(paramCanvas, i);
    } 
  }
  
  void drawHorizontalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(getPaddingLeft() + this.mDividerPadding, paramInt, getWidth() - getPaddingRight() - this.mDividerPadding, this.mDividerHeight + paramInt);
    this.mDivider.draw(paramCanvas);
  }
  
  void drawVerticalDivider(Canvas paramCanvas, int paramInt) {
    this.mDivider.setBounds(paramInt, getPaddingTop() + this.mDividerPadding, this.mDividerWidth + paramInt, getHeight() - getPaddingBottom() - this.mDividerPadding);
    this.mDivider.draw(paramCanvas);
  }
  
  protected LayoutParams generateDefaultLayoutParams() {
    return (this.mOrientation == 0) ? new LayoutParams(-2, -2) : ((this.mOrientation == 1) ? new LayoutParams(-1, -2) : null);
  }
  
  public LayoutParams generateLayoutParams(AttributeSet paramAttributeSet) {
    return new LayoutParams(getContext(), paramAttributeSet);
  }
  
  protected LayoutParams generateLayoutParams(ViewGroup.LayoutParams paramLayoutParams) {
    return new LayoutParams(paramLayoutParams);
  }
  
  public int getBaseline() {
    int i = -1;
    if (this.mBaselineAlignedChildIndex < 0)
      return super.getBaseline(); 
    if (getChildCount() <= this.mBaselineAlignedChildIndex)
      throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout set to an index that is out of bounds."); 
    View view = getChildAt(this.mBaselineAlignedChildIndex);
    int k = view.getBaseline();
    if (k == -1) {
      if (this.mBaselineAlignedChildIndex != 0)
        throw new RuntimeException("mBaselineAlignedChildIndex of LinearLayout points to a View that doesn't know how to get its baseline."); 
      return i;
    } 
    int j = this.mBaselineChildTop;
    i = j;
    if (this.mOrientation == 1) {
      int m = this.mGravity & 0x70;
      i = j;
      if (m != 48) {
        switch (m) {
          default:
            i = j;
            return ((LayoutParams)view.getLayoutParams()).topMargin + i + k;
          case 80:
            i = getBottom() - getTop() - getPaddingBottom() - this.mTotalLength;
            return ((LayoutParams)view.getLayoutParams()).topMargin + i + k;
          case 16:
            break;
        } 
      } else {
        return ((LayoutParams)view.getLayoutParams()).topMargin + i + k;
      } 
    } else {
      return ((LayoutParams)view.getLayoutParams()).topMargin + i + k;
    } 
    i = j + (getBottom() - getTop() - getPaddingTop() - getPaddingBottom() - this.mTotalLength) / 2;
    return ((LayoutParams)view.getLayoutParams()).topMargin + i + k;
  }
  
  public int getBaselineAlignedChildIndex() {
    return this.mBaselineAlignedChildIndex;
  }
  
  int getChildrenSkipCount(View paramView, int paramInt) {
    return 0;
  }
  
  public Drawable getDividerDrawable() {
    return this.mDivider;
  }
  
  public int getDividerPadding() {
    return this.mDividerPadding;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public int getDividerWidth() {
    return this.mDividerWidth;
  }
  
  public int getGravity() {
    return this.mGravity;
  }
  
  int getLocationOffset(View paramView) {
    return 0;
  }
  
  int getNextLocationOffset(View paramView) {
    return 0;
  }
  
  public int getOrientation() {
    return this.mOrientation;
  }
  
  public int getShowDividers() {
    return this.mShowDividers;
  }
  
  View getVirtualChildAt(int paramInt) {
    return getChildAt(paramInt);
  }
  
  int getVirtualChildCount() {
    return getChildCount();
  }
  
  public float getWeightSum() {
    return this.mWeightSum;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY})
  protected boolean hasDividerBeforeChildAt(int paramInt) {
    if (paramInt == 0)
      return !((this.mShowDividers & 0x1) == 0); 
    if (paramInt == getChildCount())
      return !((this.mShowDividers & 0x4) == 0); 
    if ((this.mShowDividers & 0x2) != 0) {
      boolean bool = false;
      paramInt--;
      while (true) {
        boolean bool1 = bool;
        if (paramInt >= 0) {
          if (getChildAt(paramInt).getVisibility() != 8)
            return true; 
        } else {
          return bool1;
        } 
        paramInt--;
      } 
    } 
    return false;
  }
  
  public boolean isBaselineAligned() {
    return this.mBaselineAligned;
  }
  
  public boolean isMeasureWithLargestChildEnabled() {
    return this.mUseLargestChild;
  }
  
  void layoutHorizontal(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic isLayoutRtl : (Landroid/view/View;)Z
    //   4: istore #17
    //   6: aload_0
    //   7: invokevirtual getPaddingTop : ()I
    //   10: istore #7
    //   12: iload #4
    //   14: iload_2
    //   15: isub
    //   16: istore #9
    //   18: aload_0
    //   19: invokevirtual getPaddingBottom : ()I
    //   22: istore #10
    //   24: aload_0
    //   25: invokevirtual getPaddingBottom : ()I
    //   28: istore #11
    //   30: aload_0
    //   31: invokevirtual getVirtualChildCount : ()I
    //   34: istore #12
    //   36: aload_0
    //   37: getfield mGravity : I
    //   40: istore_2
    //   41: aload_0
    //   42: getfield mGravity : I
    //   45: istore #13
    //   47: aload_0
    //   48: getfield mBaselineAligned : Z
    //   51: istore #18
    //   53: aload_0
    //   54: getfield mMaxAscent : [I
    //   57: astore #19
    //   59: aload_0
    //   60: getfield mMaxDescent : [I
    //   63: astore #20
    //   65: iload_2
    //   66: ldc_w 8388615
    //   69: iand
    //   70: aload_0
    //   71: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   74: invokestatic getAbsoluteGravity : (II)I
    //   77: lookupswitch default -> 104, 1 -> 200, 5 -> 183
    //   104: aload_0
    //   105: invokevirtual getPaddingLeft : ()I
    //   108: istore_1
    //   109: iconst_0
    //   110: istore #5
    //   112: iconst_1
    //   113: istore #4
    //   115: iload #17
    //   117: ifeq -> 129
    //   120: iload #12
    //   122: iconst_1
    //   123: isub
    //   124: istore #5
    //   126: iconst_m1
    //   127: istore #4
    //   129: iconst_0
    //   130: istore_2
    //   131: iload_1
    //   132: istore_3
    //   133: iload_2
    //   134: iload #12
    //   136: if_icmpge -> 544
    //   139: iload #5
    //   141: iload #4
    //   143: iload_2
    //   144: imul
    //   145: iadd
    //   146: istore #14
    //   148: aload_0
    //   149: iload #14
    //   151: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   154: astore #21
    //   156: aload #21
    //   158: ifnonnull -> 219
    //   161: iload_3
    //   162: aload_0
    //   163: iload #14
    //   165: invokevirtual measureNullChild : (I)I
    //   168: iadd
    //   169: istore_1
    //   170: iload_2
    //   171: istore #6
    //   173: iload #6
    //   175: iconst_1
    //   176: iadd
    //   177: istore_2
    //   178: iload_1
    //   179: istore_3
    //   180: goto -> 133
    //   183: aload_0
    //   184: invokevirtual getPaddingLeft : ()I
    //   187: iload_3
    //   188: iadd
    //   189: iload_1
    //   190: isub
    //   191: aload_0
    //   192: getfield mTotalLength : I
    //   195: isub
    //   196: istore_1
    //   197: goto -> 109
    //   200: aload_0
    //   201: invokevirtual getPaddingLeft : ()I
    //   204: iload_3
    //   205: iload_1
    //   206: isub
    //   207: aload_0
    //   208: getfield mTotalLength : I
    //   211: isub
    //   212: iconst_2
    //   213: idiv
    //   214: iadd
    //   215: istore_1
    //   216: goto -> 109
    //   219: iload_3
    //   220: istore_1
    //   221: iload_2
    //   222: istore #6
    //   224: aload #21
    //   226: invokevirtual getVisibility : ()I
    //   229: bipush #8
    //   231: if_icmpeq -> 173
    //   234: aload #21
    //   236: invokevirtual getMeasuredWidth : ()I
    //   239: istore #15
    //   241: aload #21
    //   243: invokevirtual getMeasuredHeight : ()I
    //   246: istore #16
    //   248: iconst_m1
    //   249: istore_1
    //   250: aload #21
    //   252: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   255: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   258: astore #22
    //   260: iload_1
    //   261: istore #6
    //   263: iload #18
    //   265: ifeq -> 287
    //   268: iload_1
    //   269: istore #6
    //   271: aload #22
    //   273: getfield height : I
    //   276: iconst_m1
    //   277: if_icmpeq -> 287
    //   280: aload #21
    //   282: invokevirtual getBaseline : ()I
    //   285: istore #6
    //   287: aload #22
    //   289: getfield gravity : I
    //   292: istore #8
    //   294: iload #8
    //   296: istore_1
    //   297: iload #8
    //   299: ifge -> 308
    //   302: iload #13
    //   304: bipush #112
    //   306: iand
    //   307: istore_1
    //   308: iload_1
    //   309: bipush #112
    //   311: iand
    //   312: lookupswitch default -> 348, 16 -> 465, 48 -> 432, 80 -> 497
    //   348: iload #7
    //   350: istore_1
    //   351: iload_3
    //   352: istore #6
    //   354: aload_0
    //   355: iload #14
    //   357: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   360: ifeq -> 371
    //   363: iload_3
    //   364: aload_0
    //   365: getfield mDividerWidth : I
    //   368: iadd
    //   369: istore #6
    //   371: iload #6
    //   373: aload #22
    //   375: getfield leftMargin : I
    //   378: iadd
    //   379: istore_3
    //   380: aload_0
    //   381: aload #21
    //   383: iload_3
    //   384: aload_0
    //   385: aload #21
    //   387: invokevirtual getLocationOffset : (Landroid/view/View;)I
    //   390: iadd
    //   391: iload_1
    //   392: iload #15
    //   394: iload #16
    //   396: invokespecial setChildFrame : (Landroid/view/View;IIII)V
    //   399: iload_3
    //   400: aload #22
    //   402: getfield rightMargin : I
    //   405: iload #15
    //   407: iadd
    //   408: aload_0
    //   409: aload #21
    //   411: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   414: iadd
    //   415: iadd
    //   416: istore_1
    //   417: iload_2
    //   418: aload_0
    //   419: aload #21
    //   421: iload #14
    //   423: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   426: iadd
    //   427: istore #6
    //   429: goto -> 173
    //   432: iload #7
    //   434: aload #22
    //   436: getfield topMargin : I
    //   439: iadd
    //   440: istore #8
    //   442: iload #8
    //   444: istore_1
    //   445: iload #6
    //   447: iconst_m1
    //   448: if_icmpeq -> 351
    //   451: iload #8
    //   453: aload #19
    //   455: iconst_1
    //   456: iaload
    //   457: iload #6
    //   459: isub
    //   460: iadd
    //   461: istore_1
    //   462: goto -> 351
    //   465: iload #9
    //   467: iload #7
    //   469: isub
    //   470: iload #11
    //   472: isub
    //   473: iload #16
    //   475: isub
    //   476: iconst_2
    //   477: idiv
    //   478: iload #7
    //   480: iadd
    //   481: aload #22
    //   483: getfield topMargin : I
    //   486: iadd
    //   487: aload #22
    //   489: getfield bottomMargin : I
    //   492: isub
    //   493: istore_1
    //   494: goto -> 351
    //   497: iload #9
    //   499: iload #10
    //   501: isub
    //   502: iload #16
    //   504: isub
    //   505: aload #22
    //   507: getfield bottomMargin : I
    //   510: isub
    //   511: istore #8
    //   513: iload #8
    //   515: istore_1
    //   516: iload #6
    //   518: iconst_m1
    //   519: if_icmpeq -> 351
    //   522: aload #21
    //   524: invokevirtual getMeasuredHeight : ()I
    //   527: istore_1
    //   528: iload #8
    //   530: aload #20
    //   532: iconst_2
    //   533: iaload
    //   534: iload_1
    //   535: iload #6
    //   537: isub
    //   538: isub
    //   539: isub
    //   540: istore_1
    //   541: goto -> 351
    //   544: return
  }
  
  void layoutVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    // Byte code:
    //   0: aload_0
    //   1: invokevirtual getPaddingLeft : ()I
    //   4: istore #5
    //   6: iload_3
    //   7: iload_1
    //   8: isub
    //   9: istore #6
    //   11: aload_0
    //   12: invokevirtual getPaddingRight : ()I
    //   15: istore #7
    //   17: aload_0
    //   18: invokevirtual getPaddingRight : ()I
    //   21: istore #8
    //   23: aload_0
    //   24: invokevirtual getVirtualChildCount : ()I
    //   27: istore #9
    //   29: aload_0
    //   30: getfield mGravity : I
    //   33: istore_1
    //   34: aload_0
    //   35: getfield mGravity : I
    //   38: istore #10
    //   40: iload_1
    //   41: bipush #112
    //   43: iand
    //   44: lookupswitch default -> 72, 16 -> 136, 80 -> 118
    //   72: aload_0
    //   73: invokevirtual getPaddingTop : ()I
    //   76: istore_1
    //   77: iconst_0
    //   78: istore_2
    //   79: iload_2
    //   80: iload #9
    //   82: if_icmpge -> 394
    //   85: aload_0
    //   86: iload_2
    //   87: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   90: astore #13
    //   92: aload #13
    //   94: ifnonnull -> 156
    //   97: iload_1
    //   98: aload_0
    //   99: iload_2
    //   100: invokevirtual measureNullChild : (I)I
    //   103: iadd
    //   104: istore_3
    //   105: iload_2
    //   106: istore #4
    //   108: iload #4
    //   110: iconst_1
    //   111: iadd
    //   112: istore_2
    //   113: iload_3
    //   114: istore_1
    //   115: goto -> 79
    //   118: aload_0
    //   119: invokevirtual getPaddingTop : ()I
    //   122: iload #4
    //   124: iadd
    //   125: iload_2
    //   126: isub
    //   127: aload_0
    //   128: getfield mTotalLength : I
    //   131: isub
    //   132: istore_1
    //   133: goto -> 77
    //   136: aload_0
    //   137: invokevirtual getPaddingTop : ()I
    //   140: iload #4
    //   142: iload_2
    //   143: isub
    //   144: aload_0
    //   145: getfield mTotalLength : I
    //   148: isub
    //   149: iconst_2
    //   150: idiv
    //   151: iadd
    //   152: istore_1
    //   153: goto -> 77
    //   156: iload_1
    //   157: istore_3
    //   158: iload_2
    //   159: istore #4
    //   161: aload #13
    //   163: invokevirtual getVisibility : ()I
    //   166: bipush #8
    //   168: if_icmpeq -> 108
    //   171: aload #13
    //   173: invokevirtual getMeasuredWidth : ()I
    //   176: istore #11
    //   178: aload #13
    //   180: invokevirtual getMeasuredHeight : ()I
    //   183: istore #12
    //   185: aload #13
    //   187: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   190: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   193: astore #14
    //   195: aload #14
    //   197: getfield gravity : I
    //   200: istore #4
    //   202: iload #4
    //   204: istore_3
    //   205: iload #4
    //   207: ifge -> 217
    //   210: iload #10
    //   212: ldc_w 8388615
    //   215: iand
    //   216: istore_3
    //   217: iload_3
    //   218: aload_0
    //   219: invokestatic getLayoutDirection : (Landroid/view/View;)I
    //   222: invokestatic getAbsoluteGravity : (II)I
    //   225: bipush #7
    //   227: iand
    //   228: lookupswitch default -> 256, 1 -> 344, 5 -> 376
    //   256: iload #5
    //   258: aload #14
    //   260: getfield leftMargin : I
    //   263: iadd
    //   264: istore_3
    //   265: iload_1
    //   266: istore #4
    //   268: aload_0
    //   269: iload_2
    //   270: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   273: ifeq -> 284
    //   276: iload_1
    //   277: aload_0
    //   278: getfield mDividerHeight : I
    //   281: iadd
    //   282: istore #4
    //   284: iload #4
    //   286: aload #14
    //   288: getfield topMargin : I
    //   291: iadd
    //   292: istore_1
    //   293: aload_0
    //   294: aload #13
    //   296: iload_3
    //   297: iload_1
    //   298: aload_0
    //   299: aload #13
    //   301: invokevirtual getLocationOffset : (Landroid/view/View;)I
    //   304: iadd
    //   305: iload #11
    //   307: iload #12
    //   309: invokespecial setChildFrame : (Landroid/view/View;IIII)V
    //   312: iload_1
    //   313: aload #14
    //   315: getfield bottomMargin : I
    //   318: iload #12
    //   320: iadd
    //   321: aload_0
    //   322: aload #13
    //   324: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   327: iadd
    //   328: iadd
    //   329: istore_3
    //   330: iload_2
    //   331: aload_0
    //   332: aload #13
    //   334: iload_2
    //   335: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   338: iadd
    //   339: istore #4
    //   341: goto -> 108
    //   344: iload #6
    //   346: iload #5
    //   348: isub
    //   349: iload #8
    //   351: isub
    //   352: iload #11
    //   354: isub
    //   355: iconst_2
    //   356: idiv
    //   357: iload #5
    //   359: iadd
    //   360: aload #14
    //   362: getfield leftMargin : I
    //   365: iadd
    //   366: aload #14
    //   368: getfield rightMargin : I
    //   371: isub
    //   372: istore_3
    //   373: goto -> 265
    //   376: iload #6
    //   378: iload #7
    //   380: isub
    //   381: iload #11
    //   383: isub
    //   384: aload #14
    //   386: getfield rightMargin : I
    //   389: isub
    //   390: istore_3
    //   391: goto -> 265
    //   394: return
  }
  
  void measureChildBeforeLayout(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5) {
    measureChildWithMargins(paramView, paramInt2, paramInt3, paramInt4, paramInt5);
  }
  
  void measureHorizontal(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: iconst_0
    //   6: istore #13
    //   8: iconst_0
    //   9: istore #8
    //   11: iconst_0
    //   12: istore #6
    //   14: iconst_0
    //   15: istore #10
    //   17: iconst_1
    //   18: istore #7
    //   20: fconst_0
    //   21: fstore_3
    //   22: aload_0
    //   23: invokevirtual getVirtualChildCount : ()I
    //   26: istore #21
    //   28: iload_1
    //   29: invokestatic getMode : (I)I
    //   32: istore #23
    //   34: iload_2
    //   35: invokestatic getMode : (I)I
    //   38: istore #22
    //   40: iconst_0
    //   41: istore #9
    //   43: iconst_0
    //   44: istore #14
    //   46: aload_0
    //   47: getfield mMaxAscent : [I
    //   50: ifnull -> 60
    //   53: aload_0
    //   54: getfield mMaxDescent : [I
    //   57: ifnonnull -> 74
    //   60: aload_0
    //   61: iconst_4
    //   62: newarray int
    //   64: putfield mMaxAscent : [I
    //   67: aload_0
    //   68: iconst_4
    //   69: newarray int
    //   71: putfield mMaxDescent : [I
    //   74: aload_0
    //   75: getfield mMaxAscent : [I
    //   78: astore #28
    //   80: aload_0
    //   81: getfield mMaxDescent : [I
    //   84: astore #29
    //   86: aload #28
    //   88: iconst_3
    //   89: iconst_m1
    //   90: iastore
    //   91: aload #28
    //   93: iconst_2
    //   94: iconst_m1
    //   95: iastore
    //   96: aload #28
    //   98: iconst_1
    //   99: iconst_m1
    //   100: iastore
    //   101: aload #28
    //   103: iconst_0
    //   104: iconst_m1
    //   105: iastore
    //   106: aload #29
    //   108: iconst_3
    //   109: iconst_m1
    //   110: iastore
    //   111: aload #29
    //   113: iconst_2
    //   114: iconst_m1
    //   115: iastore
    //   116: aload #29
    //   118: iconst_1
    //   119: iconst_m1
    //   120: iastore
    //   121: aload #29
    //   123: iconst_0
    //   124: iconst_m1
    //   125: iastore
    //   126: aload_0
    //   127: getfield mBaselineAligned : Z
    //   130: istore #26
    //   132: aload_0
    //   133: getfield mUseLargestChild : Z
    //   136: istore #27
    //   138: iload #23
    //   140: ldc 1073741824
    //   142: if_icmpne -> 206
    //   145: iconst_1
    //   146: istore #17
    //   148: iconst_0
    //   149: istore #12
    //   151: iconst_0
    //   152: istore #11
    //   154: iload #11
    //   156: iload #21
    //   158: if_icmpge -> 883
    //   161: aload_0
    //   162: iload #11
    //   164: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   167: astore #30
    //   169: aload #30
    //   171: ifnonnull -> 212
    //   174: aload_0
    //   175: aload_0
    //   176: getfield mTotalLength : I
    //   179: aload_0
    //   180: iload #11
    //   182: invokevirtual measureNullChild : (I)I
    //   185: iadd
    //   186: putfield mTotalLength : I
    //   189: iload #12
    //   191: istore #16
    //   193: iload #11
    //   195: iconst_1
    //   196: iadd
    //   197: istore #11
    //   199: iload #16
    //   201: istore #12
    //   203: goto -> 154
    //   206: iconst_0
    //   207: istore #17
    //   209: goto -> 148
    //   212: aload #30
    //   214: invokevirtual getVisibility : ()I
    //   217: bipush #8
    //   219: if_icmpne -> 242
    //   222: iload #11
    //   224: aload_0
    //   225: aload #30
    //   227: iload #11
    //   229: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   232: iadd
    //   233: istore #11
    //   235: iload #12
    //   237: istore #16
    //   239: goto -> 193
    //   242: aload_0
    //   243: iload #11
    //   245: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   248: ifeq -> 264
    //   251: aload_0
    //   252: aload_0
    //   253: getfield mTotalLength : I
    //   256: aload_0
    //   257: getfield mDividerWidth : I
    //   260: iadd
    //   261: putfield mTotalLength : I
    //   264: aload #30
    //   266: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   269: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   272: astore #31
    //   274: fload_3
    //   275: aload #31
    //   277: getfield weight : F
    //   280: fadd
    //   281: fstore_3
    //   282: iload #23
    //   284: ldc 1073741824
    //   286: if_icmpne -> 636
    //   289: aload #31
    //   291: getfield width : I
    //   294: ifne -> 636
    //   297: aload #31
    //   299: getfield weight : F
    //   302: fconst_0
    //   303: fcmpl
    //   304: ifle -> 636
    //   307: iload #17
    //   309: ifeq -> 594
    //   312: aload_0
    //   313: aload_0
    //   314: getfield mTotalLength : I
    //   317: aload #31
    //   319: getfield leftMargin : I
    //   322: aload #31
    //   324: getfield rightMargin : I
    //   327: iadd
    //   328: iadd
    //   329: putfield mTotalLength : I
    //   332: iload #26
    //   334: ifeq -> 626
    //   337: iconst_0
    //   338: iconst_0
    //   339: invokestatic makeMeasureSpec : (II)I
    //   342: istore #15
    //   344: aload #30
    //   346: iload #15
    //   348: iload #15
    //   350: invokevirtual measure : (II)V
    //   353: iload #14
    //   355: istore #15
    //   357: iload #12
    //   359: istore #16
    //   361: iconst_0
    //   362: istore #18
    //   364: iload #9
    //   366: istore #12
    //   368: iload #18
    //   370: istore #14
    //   372: iload #22
    //   374: ldc 1073741824
    //   376: if_icmpeq -> 402
    //   379: iload #9
    //   381: istore #12
    //   383: iload #18
    //   385: istore #14
    //   387: aload #31
    //   389: getfield height : I
    //   392: iconst_m1
    //   393: if_icmpne -> 402
    //   396: iconst_1
    //   397: istore #12
    //   399: iconst_1
    //   400: istore #14
    //   402: aload #31
    //   404: getfield topMargin : I
    //   407: aload #31
    //   409: getfield bottomMargin : I
    //   412: iadd
    //   413: istore #9
    //   415: aload #30
    //   417: invokevirtual getMeasuredHeight : ()I
    //   420: iload #9
    //   422: iadd
    //   423: istore #18
    //   425: iload #8
    //   427: aload #30
    //   429: invokevirtual getMeasuredState : ()I
    //   432: invokestatic combineMeasuredStates : (II)I
    //   435: istore #19
    //   437: iload #26
    //   439: ifeq -> 516
    //   442: aload #30
    //   444: invokevirtual getBaseline : ()I
    //   447: istore #20
    //   449: iload #20
    //   451: iconst_m1
    //   452: if_icmpeq -> 516
    //   455: aload #31
    //   457: getfield gravity : I
    //   460: ifge -> 836
    //   463: aload_0
    //   464: getfield mGravity : I
    //   467: istore #8
    //   469: iload #8
    //   471: bipush #112
    //   473: iand
    //   474: iconst_4
    //   475: ishr
    //   476: bipush #-2
    //   478: iand
    //   479: iconst_1
    //   480: ishr
    //   481: istore #8
    //   483: aload #28
    //   485: iload #8
    //   487: aload #28
    //   489: iload #8
    //   491: iaload
    //   492: iload #20
    //   494: invokestatic max : (II)I
    //   497: iastore
    //   498: aload #29
    //   500: iload #8
    //   502: aload #29
    //   504: iload #8
    //   506: iaload
    //   507: iload #18
    //   509: iload #20
    //   511: isub
    //   512: invokestatic max : (II)I
    //   515: iastore
    //   516: iload #13
    //   518: iload #18
    //   520: invokestatic max : (II)I
    //   523: istore #13
    //   525: iload #7
    //   527: ifeq -> 846
    //   530: aload #31
    //   532: getfield height : I
    //   535: iconst_m1
    //   536: if_icmpne -> 846
    //   539: iconst_1
    //   540: istore #7
    //   542: aload #31
    //   544: getfield weight : F
    //   547: fconst_0
    //   548: fcmpl
    //   549: ifle -> 859
    //   552: iload #14
    //   554: ifeq -> 852
    //   557: iload #10
    //   559: iload #9
    //   561: invokestatic max : (II)I
    //   564: istore #10
    //   566: iload #11
    //   568: aload_0
    //   569: aload #30
    //   571: iload #11
    //   573: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   576: iadd
    //   577: istore #11
    //   579: iload #19
    //   581: istore #8
    //   583: iload #12
    //   585: istore #9
    //   587: iload #15
    //   589: istore #14
    //   591: goto -> 193
    //   594: aload_0
    //   595: getfield mTotalLength : I
    //   598: istore #15
    //   600: aload_0
    //   601: iload #15
    //   603: aload #31
    //   605: getfield leftMargin : I
    //   608: iload #15
    //   610: iadd
    //   611: aload #31
    //   613: getfield rightMargin : I
    //   616: iadd
    //   617: invokestatic max : (II)I
    //   620: putfield mTotalLength : I
    //   623: goto -> 332
    //   626: iconst_1
    //   627: istore #15
    //   629: iload #12
    //   631: istore #16
    //   633: goto -> 361
    //   636: ldc_w -2147483648
    //   639: istore #16
    //   641: iload #16
    //   643: istore #15
    //   645: aload #31
    //   647: getfield width : I
    //   650: ifne -> 677
    //   653: iload #16
    //   655: istore #15
    //   657: aload #31
    //   659: getfield weight : F
    //   662: fconst_0
    //   663: fcmpl
    //   664: ifle -> 677
    //   667: iconst_0
    //   668: istore #15
    //   670: aload #31
    //   672: bipush #-2
    //   674: putfield width : I
    //   677: fload_3
    //   678: fconst_0
    //   679: fcmpl
    //   680: ifne -> 788
    //   683: aload_0
    //   684: getfield mTotalLength : I
    //   687: istore #16
    //   689: aload_0
    //   690: aload #30
    //   692: iload #11
    //   694: iload_1
    //   695: iload #16
    //   697: iload_2
    //   698: iconst_0
    //   699: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   702: iload #15
    //   704: ldc_w -2147483648
    //   707: if_icmpeq -> 717
    //   710: aload #31
    //   712: iload #15
    //   714: putfield width : I
    //   717: aload #30
    //   719: invokevirtual getMeasuredWidth : ()I
    //   722: istore #18
    //   724: iload #17
    //   726: ifeq -> 794
    //   729: aload_0
    //   730: aload_0
    //   731: getfield mTotalLength : I
    //   734: aload #31
    //   736: getfield leftMargin : I
    //   739: iload #18
    //   741: iadd
    //   742: aload #31
    //   744: getfield rightMargin : I
    //   747: iadd
    //   748: aload_0
    //   749: aload #30
    //   751: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   754: iadd
    //   755: iadd
    //   756: putfield mTotalLength : I
    //   759: iload #12
    //   761: istore #16
    //   763: iload #14
    //   765: istore #15
    //   767: iload #27
    //   769: ifeq -> 361
    //   772: iload #18
    //   774: iload #12
    //   776: invokestatic max : (II)I
    //   779: istore #16
    //   781: iload #14
    //   783: istore #15
    //   785: goto -> 361
    //   788: iconst_0
    //   789: istore #16
    //   791: goto -> 689
    //   794: aload_0
    //   795: getfield mTotalLength : I
    //   798: istore #15
    //   800: aload_0
    //   801: iload #15
    //   803: iload #15
    //   805: iload #18
    //   807: iadd
    //   808: aload #31
    //   810: getfield leftMargin : I
    //   813: iadd
    //   814: aload #31
    //   816: getfield rightMargin : I
    //   819: iadd
    //   820: aload_0
    //   821: aload #30
    //   823: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   826: iadd
    //   827: invokestatic max : (II)I
    //   830: putfield mTotalLength : I
    //   833: goto -> 759
    //   836: aload #31
    //   838: getfield gravity : I
    //   841: istore #8
    //   843: goto -> 469
    //   846: iconst_0
    //   847: istore #7
    //   849: goto -> 542
    //   852: iload #18
    //   854: istore #9
    //   856: goto -> 557
    //   859: iload #14
    //   861: ifeq -> 876
    //   864: iload #6
    //   866: iload #9
    //   868: invokestatic max : (II)I
    //   871: istore #6
    //   873: goto -> 566
    //   876: iload #18
    //   878: istore #9
    //   880: goto -> 864
    //   883: aload_0
    //   884: getfield mTotalLength : I
    //   887: ifle -> 912
    //   890: aload_0
    //   891: iload #21
    //   893: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   896: ifeq -> 912
    //   899: aload_0
    //   900: aload_0
    //   901: getfield mTotalLength : I
    //   904: aload_0
    //   905: getfield mDividerWidth : I
    //   908: iadd
    //   909: putfield mTotalLength : I
    //   912: aload #28
    //   914: iconst_1
    //   915: iaload
    //   916: iconst_m1
    //   917: if_icmpne -> 948
    //   920: aload #28
    //   922: iconst_0
    //   923: iaload
    //   924: iconst_m1
    //   925: if_icmpne -> 948
    //   928: aload #28
    //   930: iconst_2
    //   931: iaload
    //   932: iconst_m1
    //   933: if_icmpne -> 948
    //   936: iload #13
    //   938: istore #11
    //   940: aload #28
    //   942: iconst_3
    //   943: iaload
    //   944: iconst_m1
    //   945: if_icmpeq -> 1006
    //   948: iload #13
    //   950: aload #28
    //   952: iconst_3
    //   953: iaload
    //   954: aload #28
    //   956: iconst_0
    //   957: iaload
    //   958: aload #28
    //   960: iconst_1
    //   961: iaload
    //   962: aload #28
    //   964: iconst_2
    //   965: iaload
    //   966: invokestatic max : (II)I
    //   969: invokestatic max : (II)I
    //   972: invokestatic max : (II)I
    //   975: aload #29
    //   977: iconst_3
    //   978: iaload
    //   979: aload #29
    //   981: iconst_0
    //   982: iaload
    //   983: aload #29
    //   985: iconst_1
    //   986: iaload
    //   987: aload #29
    //   989: iconst_2
    //   990: iaload
    //   991: invokestatic max : (II)I
    //   994: invokestatic max : (II)I
    //   997: invokestatic max : (II)I
    //   1000: iadd
    //   1001: invokestatic max : (II)I
    //   1004: istore #11
    //   1006: iload #27
    //   1008: ifeq -> 1192
    //   1011: iload #23
    //   1013: ldc_w -2147483648
    //   1016: if_icmpeq -> 1024
    //   1019: iload #23
    //   1021: ifne -> 1192
    //   1024: aload_0
    //   1025: iconst_0
    //   1026: putfield mTotalLength : I
    //   1029: iconst_0
    //   1030: istore #13
    //   1032: iload #13
    //   1034: iload #21
    //   1036: if_icmpge -> 1192
    //   1039: aload_0
    //   1040: iload #13
    //   1042: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1045: astore #30
    //   1047: aload #30
    //   1049: ifnonnull -> 1076
    //   1052: aload_0
    //   1053: aload_0
    //   1054: getfield mTotalLength : I
    //   1057: aload_0
    //   1058: iload #13
    //   1060: invokevirtual measureNullChild : (I)I
    //   1063: iadd
    //   1064: putfield mTotalLength : I
    //   1067: iload #13
    //   1069: iconst_1
    //   1070: iadd
    //   1071: istore #13
    //   1073: goto -> 1032
    //   1076: aload #30
    //   1078: invokevirtual getVisibility : ()I
    //   1081: bipush #8
    //   1083: if_icmpne -> 1102
    //   1086: iload #13
    //   1088: aload_0
    //   1089: aload #30
    //   1091: iload #13
    //   1093: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   1096: iadd
    //   1097: istore #13
    //   1099: goto -> 1067
    //   1102: aload #30
    //   1104: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1107: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1110: astore #31
    //   1112: iload #17
    //   1114: ifeq -> 1150
    //   1117: aload_0
    //   1118: aload_0
    //   1119: getfield mTotalLength : I
    //   1122: aload #31
    //   1124: getfield leftMargin : I
    //   1127: iload #12
    //   1129: iadd
    //   1130: aload #31
    //   1132: getfield rightMargin : I
    //   1135: iadd
    //   1136: aload_0
    //   1137: aload #30
    //   1139: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1142: iadd
    //   1143: iadd
    //   1144: putfield mTotalLength : I
    //   1147: goto -> 1067
    //   1150: aload_0
    //   1151: getfield mTotalLength : I
    //   1154: istore #15
    //   1156: aload_0
    //   1157: iload #15
    //   1159: iload #15
    //   1161: iload #12
    //   1163: iadd
    //   1164: aload #31
    //   1166: getfield leftMargin : I
    //   1169: iadd
    //   1170: aload #31
    //   1172: getfield rightMargin : I
    //   1175: iadd
    //   1176: aload_0
    //   1177: aload #30
    //   1179: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1182: iadd
    //   1183: invokestatic max : (II)I
    //   1186: putfield mTotalLength : I
    //   1189: goto -> 1067
    //   1192: aload_0
    //   1193: aload_0
    //   1194: getfield mTotalLength : I
    //   1197: aload_0
    //   1198: invokevirtual getPaddingLeft : ()I
    //   1201: aload_0
    //   1202: invokevirtual getPaddingRight : ()I
    //   1205: iadd
    //   1206: iadd
    //   1207: putfield mTotalLength : I
    //   1210: aload_0
    //   1211: getfield mTotalLength : I
    //   1214: aload_0
    //   1215: invokevirtual getSuggestedMinimumWidth : ()I
    //   1218: invokestatic max : (II)I
    //   1221: iload_1
    //   1222: iconst_0
    //   1223: invokestatic resolveSizeAndState : (III)I
    //   1226: istore #24
    //   1228: iload #24
    //   1230: ldc_w 16777215
    //   1233: iand
    //   1234: aload_0
    //   1235: getfield mTotalLength : I
    //   1238: isub
    //   1239: istore #13
    //   1241: iload #14
    //   1243: ifne -> 1257
    //   1246: iload #13
    //   1248: ifeq -> 2197
    //   1251: fload_3
    //   1252: fconst_0
    //   1253: fcmpl
    //   1254: ifle -> 2197
    //   1257: aload_0
    //   1258: getfield mWeightSum : F
    //   1261: fconst_0
    //   1262: fcmpl
    //   1263: ifle -> 1434
    //   1266: aload_0
    //   1267: getfield mWeightSum : F
    //   1270: fstore_3
    //   1271: aload #28
    //   1273: iconst_3
    //   1274: iconst_m1
    //   1275: iastore
    //   1276: aload #28
    //   1278: iconst_2
    //   1279: iconst_m1
    //   1280: iastore
    //   1281: aload #28
    //   1283: iconst_1
    //   1284: iconst_m1
    //   1285: iastore
    //   1286: aload #28
    //   1288: iconst_0
    //   1289: iconst_m1
    //   1290: iastore
    //   1291: aload #29
    //   1293: iconst_3
    //   1294: iconst_m1
    //   1295: iastore
    //   1296: aload #29
    //   1298: iconst_2
    //   1299: iconst_m1
    //   1300: iastore
    //   1301: aload #29
    //   1303: iconst_1
    //   1304: iconst_m1
    //   1305: iastore
    //   1306: aload #29
    //   1308: iconst_0
    //   1309: iconst_m1
    //   1310: iastore
    //   1311: iconst_m1
    //   1312: istore #11
    //   1314: aload_0
    //   1315: iconst_0
    //   1316: putfield mTotalLength : I
    //   1319: iconst_0
    //   1320: istore #14
    //   1322: iload #6
    //   1324: istore #10
    //   1326: iload #14
    //   1328: iload #21
    //   1330: if_icmpge -> 1983
    //   1333: aload_0
    //   1334: iload #14
    //   1336: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1339: astore #30
    //   1341: iload #7
    //   1343: istore #15
    //   1345: iload #10
    //   1347: istore #16
    //   1349: iload #8
    //   1351: istore #18
    //   1353: iload #13
    //   1355: istore #19
    //   1357: iload #11
    //   1359: istore #20
    //   1361: fload_3
    //   1362: fstore #5
    //   1364: aload #30
    //   1366: ifnull -> 1402
    //   1369: aload #30
    //   1371: invokevirtual getVisibility : ()I
    //   1374: bipush #8
    //   1376: if_icmpne -> 1437
    //   1379: fload_3
    //   1380: fstore #5
    //   1382: iload #11
    //   1384: istore #20
    //   1386: iload #13
    //   1388: istore #19
    //   1390: iload #8
    //   1392: istore #18
    //   1394: iload #10
    //   1396: istore #16
    //   1398: iload #7
    //   1400: istore #15
    //   1402: iload #14
    //   1404: iconst_1
    //   1405: iadd
    //   1406: istore #14
    //   1408: iload #15
    //   1410: istore #7
    //   1412: iload #16
    //   1414: istore #10
    //   1416: iload #18
    //   1418: istore #8
    //   1420: iload #19
    //   1422: istore #13
    //   1424: iload #20
    //   1426: istore #11
    //   1428: fload #5
    //   1430: fstore_3
    //   1431: goto -> 1326
    //   1434: goto -> 1271
    //   1437: aload #30
    //   1439: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1442: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1445: astore #31
    //   1447: aload #31
    //   1449: getfield weight : F
    //   1452: fstore #5
    //   1454: iload #8
    //   1456: istore #12
    //   1458: iload #13
    //   1460: istore #6
    //   1462: fload_3
    //   1463: fstore #4
    //   1465: fload #5
    //   1467: fconst_0
    //   1468: fcmpl
    //   1469: ifle -> 1603
    //   1472: iload #13
    //   1474: i2f
    //   1475: fload #5
    //   1477: fmul
    //   1478: fload_3
    //   1479: fdiv
    //   1480: f2i
    //   1481: istore #6
    //   1483: fload_3
    //   1484: fload #5
    //   1486: fsub
    //   1487: fstore #4
    //   1489: iload #13
    //   1491: iload #6
    //   1493: isub
    //   1494: istore #12
    //   1496: iload_2
    //   1497: aload_0
    //   1498: invokevirtual getPaddingTop : ()I
    //   1501: aload_0
    //   1502: invokevirtual getPaddingBottom : ()I
    //   1505: iadd
    //   1506: aload #31
    //   1508: getfield topMargin : I
    //   1511: iadd
    //   1512: aload #31
    //   1514: getfield bottomMargin : I
    //   1517: iadd
    //   1518: aload #31
    //   1520: getfield height : I
    //   1523: invokestatic getChildMeasureSpec : (III)I
    //   1526: istore #15
    //   1528: aload #31
    //   1530: getfield width : I
    //   1533: ifne -> 1543
    //   1536: iload #23
    //   1538: ldc 1073741824
    //   1540: if_icmpeq -> 1881
    //   1543: aload #30
    //   1545: invokevirtual getMeasuredWidth : ()I
    //   1548: iload #6
    //   1550: iadd
    //   1551: istore #13
    //   1553: iload #13
    //   1555: istore #6
    //   1557: iload #13
    //   1559: ifge -> 1565
    //   1562: iconst_0
    //   1563: istore #6
    //   1565: aload #30
    //   1567: iload #6
    //   1569: ldc 1073741824
    //   1571: invokestatic makeMeasureSpec : (II)I
    //   1574: iload #15
    //   1576: invokevirtual measure : (II)V
    //   1579: iload #8
    //   1581: aload #30
    //   1583: invokevirtual getMeasuredState : ()I
    //   1586: ldc_w -16777216
    //   1589: iand
    //   1590: invokestatic combineMeasuredStates : (II)I
    //   1593: istore #8
    //   1595: iload #12
    //   1597: istore #6
    //   1599: iload #8
    //   1601: istore #12
    //   1603: iload #17
    //   1605: ifeq -> 1909
    //   1608: aload_0
    //   1609: aload_0
    //   1610: getfield mTotalLength : I
    //   1613: aload #30
    //   1615: invokevirtual getMeasuredWidth : ()I
    //   1618: aload #31
    //   1620: getfield leftMargin : I
    //   1623: iadd
    //   1624: aload #31
    //   1626: getfield rightMargin : I
    //   1629: iadd
    //   1630: aload_0
    //   1631: aload #30
    //   1633: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1636: iadd
    //   1637: iadd
    //   1638: putfield mTotalLength : I
    //   1641: iload #22
    //   1643: ldc 1073741824
    //   1645: if_icmpeq -> 1954
    //   1648: aload #31
    //   1650: getfield height : I
    //   1653: iconst_m1
    //   1654: if_icmpne -> 1954
    //   1657: iconst_1
    //   1658: istore #8
    //   1660: aload #31
    //   1662: getfield topMargin : I
    //   1665: aload #31
    //   1667: getfield bottomMargin : I
    //   1670: iadd
    //   1671: istore #15
    //   1673: aload #30
    //   1675: invokevirtual getMeasuredHeight : ()I
    //   1678: iload #15
    //   1680: iadd
    //   1681: istore #13
    //   1683: iload #11
    //   1685: iload #13
    //   1687: invokestatic max : (II)I
    //   1690: istore #11
    //   1692: iload #8
    //   1694: ifeq -> 1960
    //   1697: iload #15
    //   1699: istore #8
    //   1701: iload #10
    //   1703: iload #8
    //   1705: invokestatic max : (II)I
    //   1708: istore #10
    //   1710: iload #7
    //   1712: ifeq -> 1967
    //   1715: aload #31
    //   1717: getfield height : I
    //   1720: iconst_m1
    //   1721: if_icmpne -> 1967
    //   1724: iconst_1
    //   1725: istore #7
    //   1727: iload #7
    //   1729: istore #15
    //   1731: iload #10
    //   1733: istore #16
    //   1735: iload #12
    //   1737: istore #18
    //   1739: iload #6
    //   1741: istore #19
    //   1743: iload #11
    //   1745: istore #20
    //   1747: fload #4
    //   1749: fstore #5
    //   1751: iload #26
    //   1753: ifeq -> 1402
    //   1756: aload #30
    //   1758: invokevirtual getBaseline : ()I
    //   1761: istore #25
    //   1763: iload #7
    //   1765: istore #15
    //   1767: iload #10
    //   1769: istore #16
    //   1771: iload #12
    //   1773: istore #18
    //   1775: iload #6
    //   1777: istore #19
    //   1779: iload #11
    //   1781: istore #20
    //   1783: fload #4
    //   1785: fstore #5
    //   1787: iload #25
    //   1789: iconst_m1
    //   1790: if_icmpeq -> 1402
    //   1793: aload #31
    //   1795: getfield gravity : I
    //   1798: ifge -> 1973
    //   1801: aload_0
    //   1802: getfield mGravity : I
    //   1805: istore #8
    //   1807: iload #8
    //   1809: bipush #112
    //   1811: iand
    //   1812: iconst_4
    //   1813: ishr
    //   1814: bipush #-2
    //   1816: iand
    //   1817: iconst_1
    //   1818: ishr
    //   1819: istore #8
    //   1821: aload #28
    //   1823: iload #8
    //   1825: aload #28
    //   1827: iload #8
    //   1829: iaload
    //   1830: iload #25
    //   1832: invokestatic max : (II)I
    //   1835: iastore
    //   1836: aload #29
    //   1838: iload #8
    //   1840: aload #29
    //   1842: iload #8
    //   1844: iaload
    //   1845: iload #13
    //   1847: iload #25
    //   1849: isub
    //   1850: invokestatic max : (II)I
    //   1853: iastore
    //   1854: iload #7
    //   1856: istore #15
    //   1858: iload #10
    //   1860: istore #16
    //   1862: iload #12
    //   1864: istore #18
    //   1866: iload #6
    //   1868: istore #19
    //   1870: iload #11
    //   1872: istore #20
    //   1874: fload #4
    //   1876: fstore #5
    //   1878: goto -> 1402
    //   1881: iload #6
    //   1883: ifle -> 1903
    //   1886: aload #30
    //   1888: iload #6
    //   1890: ldc 1073741824
    //   1892: invokestatic makeMeasureSpec : (II)I
    //   1895: iload #15
    //   1897: invokevirtual measure : (II)V
    //   1900: goto -> 1579
    //   1903: iconst_0
    //   1904: istore #6
    //   1906: goto -> 1886
    //   1909: aload_0
    //   1910: getfield mTotalLength : I
    //   1913: istore #8
    //   1915: aload_0
    //   1916: iload #8
    //   1918: aload #30
    //   1920: invokevirtual getMeasuredWidth : ()I
    //   1923: iload #8
    //   1925: iadd
    //   1926: aload #31
    //   1928: getfield leftMargin : I
    //   1931: iadd
    //   1932: aload #31
    //   1934: getfield rightMargin : I
    //   1937: iadd
    //   1938: aload_0
    //   1939: aload #30
    //   1941: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1944: iadd
    //   1945: invokestatic max : (II)I
    //   1948: putfield mTotalLength : I
    //   1951: goto -> 1641
    //   1954: iconst_0
    //   1955: istore #8
    //   1957: goto -> 1660
    //   1960: iload #13
    //   1962: istore #8
    //   1964: goto -> 1701
    //   1967: iconst_0
    //   1968: istore #7
    //   1970: goto -> 1727
    //   1973: aload #31
    //   1975: getfield gravity : I
    //   1978: istore #8
    //   1980: goto -> 1807
    //   1983: aload_0
    //   1984: aload_0
    //   1985: getfield mTotalLength : I
    //   1988: aload_0
    //   1989: invokevirtual getPaddingLeft : ()I
    //   1992: aload_0
    //   1993: invokevirtual getPaddingRight : ()I
    //   1996: iadd
    //   1997: iadd
    //   1998: putfield mTotalLength : I
    //   2001: aload #28
    //   2003: iconst_1
    //   2004: iaload
    //   2005: iconst_m1
    //   2006: if_icmpne -> 2049
    //   2009: aload #28
    //   2011: iconst_0
    //   2012: iaload
    //   2013: iconst_m1
    //   2014: if_icmpne -> 2049
    //   2017: aload #28
    //   2019: iconst_2
    //   2020: iaload
    //   2021: iconst_m1
    //   2022: if_icmpne -> 2049
    //   2025: iload #7
    //   2027: istore #15
    //   2029: iload #10
    //   2031: istore #13
    //   2033: iload #8
    //   2035: istore #14
    //   2037: iload #11
    //   2039: istore #6
    //   2041: aload #28
    //   2043: iconst_3
    //   2044: iaload
    //   2045: iconst_m1
    //   2046: if_icmpeq -> 2119
    //   2049: iload #11
    //   2051: aload #28
    //   2053: iconst_3
    //   2054: iaload
    //   2055: aload #28
    //   2057: iconst_0
    //   2058: iaload
    //   2059: aload #28
    //   2061: iconst_1
    //   2062: iaload
    //   2063: aload #28
    //   2065: iconst_2
    //   2066: iaload
    //   2067: invokestatic max : (II)I
    //   2070: invokestatic max : (II)I
    //   2073: invokestatic max : (II)I
    //   2076: aload #29
    //   2078: iconst_3
    //   2079: iaload
    //   2080: aload #29
    //   2082: iconst_0
    //   2083: iaload
    //   2084: aload #29
    //   2086: iconst_1
    //   2087: iaload
    //   2088: aload #29
    //   2090: iconst_2
    //   2091: iaload
    //   2092: invokestatic max : (II)I
    //   2095: invokestatic max : (II)I
    //   2098: invokestatic max : (II)I
    //   2101: iadd
    //   2102: invokestatic max : (II)I
    //   2105: istore #6
    //   2107: iload #8
    //   2109: istore #14
    //   2111: iload #10
    //   2113: istore #13
    //   2115: iload #7
    //   2117: istore #15
    //   2119: iload #6
    //   2121: istore #7
    //   2123: iload #15
    //   2125: ifne -> 2143
    //   2128: iload #6
    //   2130: istore #7
    //   2132: iload #22
    //   2134: ldc 1073741824
    //   2136: if_icmpeq -> 2143
    //   2139: iload #13
    //   2141: istore #7
    //   2143: aload_0
    //   2144: ldc_w -16777216
    //   2147: iload #14
    //   2149: iand
    //   2150: iload #24
    //   2152: ior
    //   2153: iload #7
    //   2155: aload_0
    //   2156: invokevirtual getPaddingTop : ()I
    //   2159: aload_0
    //   2160: invokevirtual getPaddingBottom : ()I
    //   2163: iadd
    //   2164: iadd
    //   2165: aload_0
    //   2166: invokevirtual getSuggestedMinimumHeight : ()I
    //   2169: invokestatic max : (II)I
    //   2172: iload_2
    //   2173: iload #14
    //   2175: bipush #16
    //   2177: ishl
    //   2178: invokestatic resolveSizeAndState : (III)I
    //   2181: invokevirtual setMeasuredDimension : (II)V
    //   2184: iload #9
    //   2186: ifeq -> 2196
    //   2189: aload_0
    //   2190: iload #21
    //   2192: iload_1
    //   2193: invokespecial forceUniformHeight : (II)V
    //   2196: return
    //   2197: iload #6
    //   2199: iload #10
    //   2201: invokestatic max : (II)I
    //   2204: istore #16
    //   2206: iload #7
    //   2208: istore #15
    //   2210: iload #16
    //   2212: istore #13
    //   2214: iload #8
    //   2216: istore #14
    //   2218: iload #11
    //   2220: istore #6
    //   2222: iload #27
    //   2224: ifeq -> 2119
    //   2227: iload #7
    //   2229: istore #15
    //   2231: iload #16
    //   2233: istore #13
    //   2235: iload #8
    //   2237: istore #14
    //   2239: iload #11
    //   2241: istore #6
    //   2243: iload #23
    //   2245: ldc 1073741824
    //   2247: if_icmpeq -> 2119
    //   2250: iconst_0
    //   2251: istore #10
    //   2253: iload #7
    //   2255: istore #15
    //   2257: iload #16
    //   2259: istore #13
    //   2261: iload #8
    //   2263: istore #14
    //   2265: iload #11
    //   2267: istore #6
    //   2269: iload #10
    //   2271: iload #21
    //   2273: if_icmpge -> 2119
    //   2276: aload_0
    //   2277: iload #10
    //   2279: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   2282: astore #28
    //   2284: aload #28
    //   2286: ifnull -> 2299
    //   2289: aload #28
    //   2291: invokevirtual getVisibility : ()I
    //   2294: bipush #8
    //   2296: if_icmpne -> 2308
    //   2299: iload #10
    //   2301: iconst_1
    //   2302: iadd
    //   2303: istore #10
    //   2305: goto -> 2253
    //   2308: aload #28
    //   2310: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   2313: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   2316: getfield weight : F
    //   2319: fconst_0
    //   2320: fcmpl
    //   2321: ifle -> 2299
    //   2324: aload #28
    //   2326: iload #12
    //   2328: ldc 1073741824
    //   2330: invokestatic makeMeasureSpec : (II)I
    //   2333: aload #28
    //   2335: invokevirtual getMeasuredHeight : ()I
    //   2338: ldc 1073741824
    //   2340: invokestatic makeMeasureSpec : (II)I
    //   2343: invokevirtual measure : (II)V
    //   2346: goto -> 2299
  }
  
  int measureNullChild(int paramInt) {
    return 0;
  }
  
  void measureVertical(int paramInt1, int paramInt2) {
    // Byte code:
    //   0: aload_0
    //   1: iconst_0
    //   2: putfield mTotalLength : I
    //   5: iconst_0
    //   6: istore #9
    //   8: iconst_0
    //   9: istore #8
    //   11: iconst_0
    //   12: istore #6
    //   14: iconst_0
    //   15: istore #11
    //   17: iconst_1
    //   18: istore #7
    //   20: fconst_0
    //   21: fstore_3
    //   22: aload_0
    //   23: invokevirtual getVirtualChildCount : ()I
    //   26: istore #18
    //   28: iload_1
    //   29: invokestatic getMode : (I)I
    //   32: istore #19
    //   34: iload_2
    //   35: invokestatic getMode : (I)I
    //   38: istore #20
    //   40: iconst_0
    //   41: istore #10
    //   43: iconst_0
    //   44: istore #14
    //   46: aload_0
    //   47: getfield mBaselineAlignedChildIndex : I
    //   50: istore #21
    //   52: aload_0
    //   53: getfield mUseLargestChild : Z
    //   56: istore #22
    //   58: iconst_0
    //   59: istore #13
    //   61: iconst_0
    //   62: istore #12
    //   64: iload #12
    //   66: iload #18
    //   68: if_icmpge -> 646
    //   71: aload_0
    //   72: iload #12
    //   74: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   77: astore #23
    //   79: aload #23
    //   81: ifnonnull -> 116
    //   84: aload_0
    //   85: aload_0
    //   86: getfield mTotalLength : I
    //   89: aload_0
    //   90: iload #12
    //   92: invokevirtual measureNullChild : (I)I
    //   95: iadd
    //   96: putfield mTotalLength : I
    //   99: iload #13
    //   101: istore #16
    //   103: iload #12
    //   105: iconst_1
    //   106: iadd
    //   107: istore #12
    //   109: iload #16
    //   111: istore #13
    //   113: goto -> 64
    //   116: aload #23
    //   118: invokevirtual getVisibility : ()I
    //   121: bipush #8
    //   123: if_icmpne -> 146
    //   126: iload #12
    //   128: aload_0
    //   129: aload #23
    //   131: iload #12
    //   133: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   136: iadd
    //   137: istore #12
    //   139: iload #13
    //   141: istore #16
    //   143: goto -> 103
    //   146: aload_0
    //   147: iload #12
    //   149: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   152: ifeq -> 168
    //   155: aload_0
    //   156: aload_0
    //   157: getfield mTotalLength : I
    //   160: aload_0
    //   161: getfield mDividerHeight : I
    //   164: iadd
    //   165: putfield mTotalLength : I
    //   168: aload #23
    //   170: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   173: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   176: astore #24
    //   178: fload_3
    //   179: aload #24
    //   181: getfield weight : F
    //   184: fadd
    //   185: fstore_3
    //   186: iload #20
    //   188: ldc 1073741824
    //   190: if_icmpne -> 297
    //   193: aload #24
    //   195: getfield height : I
    //   198: ifne -> 297
    //   201: aload #24
    //   203: getfield weight : F
    //   206: fconst_0
    //   207: fcmpl
    //   208: ifle -> 297
    //   211: aload_0
    //   212: getfield mTotalLength : I
    //   215: istore #14
    //   217: aload_0
    //   218: iload #14
    //   220: aload #24
    //   222: getfield topMargin : I
    //   225: iload #14
    //   227: iadd
    //   228: aload #24
    //   230: getfield bottomMargin : I
    //   233: iadd
    //   234: invokestatic max : (II)I
    //   237: putfield mTotalLength : I
    //   240: iconst_1
    //   241: istore #15
    //   243: iload #13
    //   245: istore #16
    //   247: iload #21
    //   249: iflt -> 269
    //   252: iload #21
    //   254: iload #12
    //   256: iconst_1
    //   257: iadd
    //   258: if_icmpne -> 269
    //   261: aload_0
    //   262: aload_0
    //   263: getfield mTotalLength : I
    //   266: putfield mBaselineChildTop : I
    //   269: iload #12
    //   271: iload #21
    //   273: if_icmpge -> 459
    //   276: aload #24
    //   278: getfield weight : F
    //   281: fconst_0
    //   282: fcmpl
    //   283: ifle -> 459
    //   286: new java/lang/RuntimeException
    //   289: dup
    //   290: ldc_w 'A child of LinearLayout with index less than mBaselineAlignedChildIndex has weight > 0, which won't work.  Either remove the weight, or don't set mBaselineAlignedChildIndex.'
    //   293: invokespecial <init> : (Ljava/lang/String;)V
    //   296: athrow
    //   297: ldc_w -2147483648
    //   300: istore #16
    //   302: iload #16
    //   304: istore #15
    //   306: aload #24
    //   308: getfield height : I
    //   311: ifne -> 338
    //   314: iload #16
    //   316: istore #15
    //   318: aload #24
    //   320: getfield weight : F
    //   323: fconst_0
    //   324: fcmpl
    //   325: ifle -> 338
    //   328: iconst_0
    //   329: istore #15
    //   331: aload #24
    //   333: bipush #-2
    //   335: putfield height : I
    //   338: fload_3
    //   339: fconst_0
    //   340: fcmpl
    //   341: ifne -> 453
    //   344: aload_0
    //   345: getfield mTotalLength : I
    //   348: istore #16
    //   350: aload_0
    //   351: aload #23
    //   353: iload #12
    //   355: iload_1
    //   356: iconst_0
    //   357: iload_2
    //   358: iload #16
    //   360: invokevirtual measureChildBeforeLayout : (Landroid/view/View;IIIII)V
    //   363: iload #15
    //   365: ldc_w -2147483648
    //   368: if_icmpeq -> 378
    //   371: aload #24
    //   373: iload #15
    //   375: putfield height : I
    //   378: aload #23
    //   380: invokevirtual getMeasuredHeight : ()I
    //   383: istore #17
    //   385: aload_0
    //   386: getfield mTotalLength : I
    //   389: istore #15
    //   391: aload_0
    //   392: iload #15
    //   394: iload #15
    //   396: iload #17
    //   398: iadd
    //   399: aload #24
    //   401: getfield topMargin : I
    //   404: iadd
    //   405: aload #24
    //   407: getfield bottomMargin : I
    //   410: iadd
    //   411: aload_0
    //   412: aload #23
    //   414: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   417: iadd
    //   418: invokestatic max : (II)I
    //   421: putfield mTotalLength : I
    //   424: iload #13
    //   426: istore #16
    //   428: iload #14
    //   430: istore #15
    //   432: iload #22
    //   434: ifeq -> 247
    //   437: iload #17
    //   439: iload #13
    //   441: invokestatic max : (II)I
    //   444: istore #16
    //   446: iload #14
    //   448: istore #15
    //   450: goto -> 247
    //   453: iconst_0
    //   454: istore #16
    //   456: goto -> 350
    //   459: iconst_0
    //   460: istore #17
    //   462: iload #10
    //   464: istore #13
    //   466: iload #17
    //   468: istore #14
    //   470: iload #19
    //   472: ldc 1073741824
    //   474: if_icmpeq -> 500
    //   477: iload #10
    //   479: istore #13
    //   481: iload #17
    //   483: istore #14
    //   485: aload #24
    //   487: getfield width : I
    //   490: iconst_m1
    //   491: if_icmpne -> 500
    //   494: iconst_1
    //   495: istore #13
    //   497: iconst_1
    //   498: istore #14
    //   500: aload #24
    //   502: getfield leftMargin : I
    //   505: aload #24
    //   507: getfield rightMargin : I
    //   510: iadd
    //   511: istore #10
    //   513: aload #23
    //   515: invokevirtual getMeasuredWidth : ()I
    //   518: iload #10
    //   520: iadd
    //   521: istore #17
    //   523: iload #9
    //   525: iload #17
    //   527: invokestatic max : (II)I
    //   530: istore #9
    //   532: iload #8
    //   534: aload #23
    //   536: invokevirtual getMeasuredState : ()I
    //   539: invokestatic combineMeasuredStates : (II)I
    //   542: istore #8
    //   544: iload #7
    //   546: ifeq -> 609
    //   549: aload #24
    //   551: getfield width : I
    //   554: iconst_m1
    //   555: if_icmpne -> 609
    //   558: iconst_1
    //   559: istore #7
    //   561: aload #24
    //   563: getfield weight : F
    //   566: fconst_0
    //   567: fcmpl
    //   568: ifle -> 622
    //   571: iload #14
    //   573: ifeq -> 615
    //   576: iload #11
    //   578: iload #10
    //   580: invokestatic max : (II)I
    //   583: istore #11
    //   585: iload #12
    //   587: aload_0
    //   588: aload #23
    //   590: iload #12
    //   592: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   595: iadd
    //   596: istore #12
    //   598: iload #13
    //   600: istore #10
    //   602: iload #15
    //   604: istore #14
    //   606: goto -> 103
    //   609: iconst_0
    //   610: istore #7
    //   612: goto -> 561
    //   615: iload #17
    //   617: istore #10
    //   619: goto -> 576
    //   622: iload #14
    //   624: ifeq -> 639
    //   627: iload #6
    //   629: iload #10
    //   631: invokestatic max : (II)I
    //   634: istore #6
    //   636: goto -> 585
    //   639: iload #17
    //   641: istore #10
    //   643: goto -> 627
    //   646: aload_0
    //   647: getfield mTotalLength : I
    //   650: ifle -> 675
    //   653: aload_0
    //   654: iload #18
    //   656: invokevirtual hasDividerBeforeChildAt : (I)Z
    //   659: ifeq -> 675
    //   662: aload_0
    //   663: aload_0
    //   664: getfield mTotalLength : I
    //   667: aload_0
    //   668: getfield mDividerHeight : I
    //   671: iadd
    //   672: putfield mTotalLength : I
    //   675: iload #22
    //   677: ifeq -> 823
    //   680: iload #20
    //   682: ldc_w -2147483648
    //   685: if_icmpeq -> 693
    //   688: iload #20
    //   690: ifne -> 823
    //   693: aload_0
    //   694: iconst_0
    //   695: putfield mTotalLength : I
    //   698: iconst_0
    //   699: istore #12
    //   701: iload #12
    //   703: iload #18
    //   705: if_icmpge -> 823
    //   708: aload_0
    //   709: iload #12
    //   711: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   714: astore #23
    //   716: aload #23
    //   718: ifnonnull -> 745
    //   721: aload_0
    //   722: aload_0
    //   723: getfield mTotalLength : I
    //   726: aload_0
    //   727: iload #12
    //   729: invokevirtual measureNullChild : (I)I
    //   732: iadd
    //   733: putfield mTotalLength : I
    //   736: iload #12
    //   738: iconst_1
    //   739: iadd
    //   740: istore #12
    //   742: goto -> 701
    //   745: aload #23
    //   747: invokevirtual getVisibility : ()I
    //   750: bipush #8
    //   752: if_icmpne -> 771
    //   755: iload #12
    //   757: aload_0
    //   758: aload #23
    //   760: iload #12
    //   762: invokevirtual getChildrenSkipCount : (Landroid/view/View;I)I
    //   765: iadd
    //   766: istore #12
    //   768: goto -> 736
    //   771: aload #23
    //   773: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   776: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   779: astore #24
    //   781: aload_0
    //   782: getfield mTotalLength : I
    //   785: istore #15
    //   787: aload_0
    //   788: iload #15
    //   790: iload #15
    //   792: iload #13
    //   794: iadd
    //   795: aload #24
    //   797: getfield topMargin : I
    //   800: iadd
    //   801: aload #24
    //   803: getfield bottomMargin : I
    //   806: iadd
    //   807: aload_0
    //   808: aload #23
    //   810: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   813: iadd
    //   814: invokestatic max : (II)I
    //   817: putfield mTotalLength : I
    //   820: goto -> 736
    //   823: aload_0
    //   824: aload_0
    //   825: getfield mTotalLength : I
    //   828: aload_0
    //   829: invokevirtual getPaddingTop : ()I
    //   832: aload_0
    //   833: invokevirtual getPaddingBottom : ()I
    //   836: iadd
    //   837: iadd
    //   838: putfield mTotalLength : I
    //   841: aload_0
    //   842: getfield mTotalLength : I
    //   845: aload_0
    //   846: invokevirtual getSuggestedMinimumHeight : ()I
    //   849: invokestatic max : (II)I
    //   852: iload_2
    //   853: iconst_0
    //   854: invokestatic resolveSizeAndState : (III)I
    //   857: istore #17
    //   859: iload #17
    //   861: ldc_w 16777215
    //   864: iand
    //   865: aload_0
    //   866: getfield mTotalLength : I
    //   869: isub
    //   870: istore #12
    //   872: iload #14
    //   874: ifne -> 888
    //   877: iload #12
    //   879: ifeq -> 1428
    //   882: fload_3
    //   883: fconst_0
    //   884: fcmpl
    //   885: ifle -> 1428
    //   888: aload_0
    //   889: getfield mWeightSum : F
    //   892: fconst_0
    //   893: fcmpl
    //   894: ifle -> 984
    //   897: aload_0
    //   898: getfield mWeightSum : F
    //   901: fstore_3
    //   902: aload_0
    //   903: iconst_0
    //   904: putfield mTotalLength : I
    //   907: iconst_0
    //   908: istore #14
    //   910: iload #9
    //   912: istore #11
    //   914: iload #12
    //   916: istore #13
    //   918: iload #14
    //   920: iload #18
    //   922: if_icmpge -> 1334
    //   925: aload_0
    //   926: iload #14
    //   928: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   931: astore #23
    //   933: aload #23
    //   935: invokevirtual getVisibility : ()I
    //   938: bipush #8
    //   940: if_icmpne -> 987
    //   943: iload #13
    //   945: istore #9
    //   947: iload #8
    //   949: istore #12
    //   951: iload #6
    //   953: istore #8
    //   955: iload #7
    //   957: istore #6
    //   959: iload #14
    //   961: iconst_1
    //   962: iadd
    //   963: istore #14
    //   965: iload #6
    //   967: istore #7
    //   969: iload #8
    //   971: istore #6
    //   973: iload #12
    //   975: istore #8
    //   977: iload #9
    //   979: istore #13
    //   981: goto -> 918
    //   984: goto -> 902
    //   987: aload #23
    //   989: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   992: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   995: astore #24
    //   997: aload #24
    //   999: getfield weight : F
    //   1002: fstore #5
    //   1004: iload #8
    //   1006: istore #12
    //   1008: iload #13
    //   1010: istore #9
    //   1012: fload_3
    //   1013: fstore #4
    //   1015: fload #5
    //   1017: fconst_0
    //   1018: fcmpl
    //   1019: ifle -> 1153
    //   1022: iload #13
    //   1024: i2f
    //   1025: fload #5
    //   1027: fmul
    //   1028: fload_3
    //   1029: fdiv
    //   1030: f2i
    //   1031: istore #9
    //   1033: fload_3
    //   1034: fload #5
    //   1036: fsub
    //   1037: fstore #4
    //   1039: iload #13
    //   1041: iload #9
    //   1043: isub
    //   1044: istore #12
    //   1046: iload_1
    //   1047: aload_0
    //   1048: invokevirtual getPaddingLeft : ()I
    //   1051: aload_0
    //   1052: invokevirtual getPaddingRight : ()I
    //   1055: iadd
    //   1056: aload #24
    //   1058: getfield leftMargin : I
    //   1061: iadd
    //   1062: aload #24
    //   1064: getfield rightMargin : I
    //   1067: iadd
    //   1068: aload #24
    //   1070: getfield width : I
    //   1073: invokestatic getChildMeasureSpec : (III)I
    //   1076: istore #15
    //   1078: aload #24
    //   1080: getfield height : I
    //   1083: ifne -> 1093
    //   1086: iload #20
    //   1088: ldc 1073741824
    //   1090: if_icmpeq -> 1287
    //   1093: aload #23
    //   1095: invokevirtual getMeasuredHeight : ()I
    //   1098: iload #9
    //   1100: iadd
    //   1101: istore #13
    //   1103: iload #13
    //   1105: istore #9
    //   1107: iload #13
    //   1109: ifge -> 1115
    //   1112: iconst_0
    //   1113: istore #9
    //   1115: aload #23
    //   1117: iload #15
    //   1119: iload #9
    //   1121: ldc 1073741824
    //   1123: invokestatic makeMeasureSpec : (II)I
    //   1126: invokevirtual measure : (II)V
    //   1129: iload #8
    //   1131: aload #23
    //   1133: invokevirtual getMeasuredState : ()I
    //   1136: sipush #-256
    //   1139: iand
    //   1140: invokestatic combineMeasuredStates : (II)I
    //   1143: istore #8
    //   1145: iload #12
    //   1147: istore #9
    //   1149: iload #8
    //   1151: istore #12
    //   1153: aload #24
    //   1155: getfield leftMargin : I
    //   1158: aload #24
    //   1160: getfield rightMargin : I
    //   1163: iadd
    //   1164: istore #13
    //   1166: aload #23
    //   1168: invokevirtual getMeasuredWidth : ()I
    //   1171: iload #13
    //   1173: iadd
    //   1174: istore #15
    //   1176: iload #11
    //   1178: iload #15
    //   1180: invokestatic max : (II)I
    //   1183: istore #11
    //   1185: iload #19
    //   1187: ldc 1073741824
    //   1189: if_icmpeq -> 1315
    //   1192: aload #24
    //   1194: getfield width : I
    //   1197: iconst_m1
    //   1198: if_icmpne -> 1315
    //   1201: iconst_1
    //   1202: istore #8
    //   1204: iload #8
    //   1206: ifeq -> 1321
    //   1209: iload #13
    //   1211: istore #8
    //   1213: iload #6
    //   1215: iload #8
    //   1217: invokestatic max : (II)I
    //   1220: istore #8
    //   1222: iload #7
    //   1224: ifeq -> 1328
    //   1227: aload #24
    //   1229: getfield width : I
    //   1232: iconst_m1
    //   1233: if_icmpne -> 1328
    //   1236: iconst_1
    //   1237: istore #6
    //   1239: aload_0
    //   1240: getfield mTotalLength : I
    //   1243: istore #7
    //   1245: aload_0
    //   1246: iload #7
    //   1248: aload #23
    //   1250: invokevirtual getMeasuredHeight : ()I
    //   1253: iload #7
    //   1255: iadd
    //   1256: aload #24
    //   1258: getfield topMargin : I
    //   1261: iadd
    //   1262: aload #24
    //   1264: getfield bottomMargin : I
    //   1267: iadd
    //   1268: aload_0
    //   1269: aload #23
    //   1271: invokevirtual getNextLocationOffset : (Landroid/view/View;)I
    //   1274: iadd
    //   1275: invokestatic max : (II)I
    //   1278: putfield mTotalLength : I
    //   1281: fload #4
    //   1283: fstore_3
    //   1284: goto -> 959
    //   1287: iload #9
    //   1289: ifle -> 1309
    //   1292: aload #23
    //   1294: iload #15
    //   1296: iload #9
    //   1298: ldc 1073741824
    //   1300: invokestatic makeMeasureSpec : (II)I
    //   1303: invokevirtual measure : (II)V
    //   1306: goto -> 1129
    //   1309: iconst_0
    //   1310: istore #9
    //   1312: goto -> 1292
    //   1315: iconst_0
    //   1316: istore #8
    //   1318: goto -> 1204
    //   1321: iload #15
    //   1323: istore #8
    //   1325: goto -> 1213
    //   1328: iconst_0
    //   1329: istore #6
    //   1331: goto -> 1239
    //   1334: aload_0
    //   1335: aload_0
    //   1336: getfield mTotalLength : I
    //   1339: aload_0
    //   1340: invokevirtual getPaddingTop : ()I
    //   1343: aload_0
    //   1344: invokevirtual getPaddingBottom : ()I
    //   1347: iadd
    //   1348: iadd
    //   1349: putfield mTotalLength : I
    //   1352: iload #8
    //   1354: istore #12
    //   1356: iload #7
    //   1358: istore #14
    //   1360: iload #11
    //   1362: istore #7
    //   1364: iload #14
    //   1366: ifne -> 1384
    //   1369: iload #11
    //   1371: istore #7
    //   1373: iload #19
    //   1375: ldc 1073741824
    //   1377: if_icmpeq -> 1384
    //   1380: iload #6
    //   1382: istore #7
    //   1384: aload_0
    //   1385: iload #7
    //   1387: aload_0
    //   1388: invokevirtual getPaddingLeft : ()I
    //   1391: aload_0
    //   1392: invokevirtual getPaddingRight : ()I
    //   1395: iadd
    //   1396: iadd
    //   1397: aload_0
    //   1398: invokevirtual getSuggestedMinimumWidth : ()I
    //   1401: invokestatic max : (II)I
    //   1404: iload_1
    //   1405: iload #12
    //   1407: invokestatic resolveSizeAndState : (III)I
    //   1410: iload #17
    //   1412: invokevirtual setMeasuredDimension : (II)V
    //   1415: iload #10
    //   1417: ifeq -> 1427
    //   1420: aload_0
    //   1421: iload #18
    //   1423: iload_2
    //   1424: invokespecial forceUniformWidth : (II)V
    //   1427: return
    //   1428: iload #6
    //   1430: iload #11
    //   1432: invokestatic max : (II)I
    //   1435: istore #16
    //   1437: iload #7
    //   1439: istore #14
    //   1441: iload #16
    //   1443: istore #6
    //   1445: iload #8
    //   1447: istore #12
    //   1449: iload #9
    //   1451: istore #11
    //   1453: iload #22
    //   1455: ifeq -> 1360
    //   1458: iload #7
    //   1460: istore #14
    //   1462: iload #16
    //   1464: istore #6
    //   1466: iload #8
    //   1468: istore #12
    //   1470: iload #9
    //   1472: istore #11
    //   1474: iload #20
    //   1476: ldc 1073741824
    //   1478: if_icmpeq -> 1360
    //   1481: iconst_0
    //   1482: istore #15
    //   1484: iload #7
    //   1486: istore #14
    //   1488: iload #16
    //   1490: istore #6
    //   1492: iload #8
    //   1494: istore #12
    //   1496: iload #9
    //   1498: istore #11
    //   1500: iload #15
    //   1502: iload #18
    //   1504: if_icmpge -> 1360
    //   1507: aload_0
    //   1508: iload #15
    //   1510: invokevirtual getVirtualChildAt : (I)Landroid/view/View;
    //   1513: astore #23
    //   1515: aload #23
    //   1517: ifnull -> 1530
    //   1520: aload #23
    //   1522: invokevirtual getVisibility : ()I
    //   1525: bipush #8
    //   1527: if_icmpne -> 1539
    //   1530: iload #15
    //   1532: iconst_1
    //   1533: iadd
    //   1534: istore #15
    //   1536: goto -> 1484
    //   1539: aload #23
    //   1541: invokevirtual getLayoutParams : ()Landroid/view/ViewGroup$LayoutParams;
    //   1544: checkcast androidx/appcompat/widget/LinearLayoutCompat$LayoutParams
    //   1547: getfield weight : F
    //   1550: fconst_0
    //   1551: fcmpl
    //   1552: ifle -> 1530
    //   1555: aload #23
    //   1557: aload #23
    //   1559: invokevirtual getMeasuredWidth : ()I
    //   1562: ldc 1073741824
    //   1564: invokestatic makeMeasureSpec : (II)I
    //   1567: iload #13
    //   1569: ldc 1073741824
    //   1571: invokestatic makeMeasureSpec : (II)I
    //   1574: invokevirtual measure : (II)V
    //   1577: goto -> 1530
  }
  
  protected void onDraw(Canvas paramCanvas) {
    if (this.mDivider == null)
      return; 
    if (this.mOrientation == 1) {
      drawDividersVertical(paramCanvas);
      return;
    } 
    drawDividersHorizontal(paramCanvas);
  }
  
  public void onInitializeAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent) {
    super.onInitializeAccessibilityEvent(paramAccessibilityEvent);
    paramAccessibilityEvent.setClassName(LinearLayoutCompat.class.getName());
  }
  
  public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo paramAccessibilityNodeInfo) {
    super.onInitializeAccessibilityNodeInfo(paramAccessibilityNodeInfo);
    paramAccessibilityNodeInfo.setClassName(LinearLayoutCompat.class.getName());
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (this.mOrientation == 1) {
      layoutVertical(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    layoutHorizontal(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    if (this.mOrientation == 1) {
      measureVertical(paramInt1, paramInt2);
      return;
    } 
    measureHorizontal(paramInt1, paramInt2);
  }
  
  public void setBaselineAligned(boolean paramBoolean) {
    this.mBaselineAligned = paramBoolean;
  }
  
  public void setBaselineAlignedChildIndex(int paramInt) {
    if (paramInt < 0 || paramInt >= getChildCount())
      throw new IllegalArgumentException("base aligned child index out of range (0, " + getChildCount() + ")"); 
    this.mBaselineAlignedChildIndex = paramInt;
  }
  
  public void setDividerDrawable(Drawable paramDrawable) {
    boolean bool = false;
    if (paramDrawable == this.mDivider)
      return; 
    this.mDivider = paramDrawable;
    if (paramDrawable != null) {
      this.mDividerWidth = paramDrawable.getIntrinsicWidth();
      this.mDividerHeight = paramDrawable.getIntrinsicHeight();
    } else {
      this.mDividerWidth = 0;
      this.mDividerHeight = 0;
    } 
    if (paramDrawable == null)
      bool = true; 
    setWillNotDraw(bool);
    requestLayout();
  }
  
  public void setDividerPadding(int paramInt) {
    this.mDividerPadding = paramInt;
  }
  
  public void setGravity(int paramInt) {
    if (this.mGravity != paramInt) {
      int i = paramInt;
      if ((0x800007 & paramInt) == 0)
        i = paramInt | 0x800003; 
      paramInt = i;
      if ((i & 0x70) == 0)
        paramInt = i | 0x30; 
      this.mGravity = paramInt;
      requestLayout();
    } 
  }
  
  public void setHorizontalGravity(int paramInt) {
    paramInt &= 0x800007;
    if ((this.mGravity & 0x800007) != paramInt) {
      this.mGravity = this.mGravity & 0xFF7FFFF8 | paramInt;
      requestLayout();
    } 
  }
  
  public void setMeasureWithLargestChildEnabled(boolean paramBoolean) {
    this.mUseLargestChild = paramBoolean;
  }
  
  public void setOrientation(int paramInt) {
    if (this.mOrientation != paramInt) {
      this.mOrientation = paramInt;
      requestLayout();
    } 
  }
  
  public void setShowDividers(int paramInt) {
    if (paramInt != this.mShowDividers)
      requestLayout(); 
    this.mShowDividers = paramInt;
  }
  
  public void setVerticalGravity(int paramInt) {
    paramInt &= 0x70;
    if ((this.mGravity & 0x70) != paramInt) {
      this.mGravity = this.mGravity & 0xFFFFFF8F | paramInt;
      requestLayout();
    } 
  }
  
  public void setWeightSum(float paramFloat) {
    this.mWeightSum = Math.max(0.0F, paramFloat);
  }
  
  public boolean shouldDelayChildPressedState() {
    return false;
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface DividerMode {}
  
  public static class LayoutParams extends ViewGroup.MarginLayoutParams {
    public int gravity = -1;
    
    public float weight;
    
    public LayoutParams(int param1Int1, int param1Int2) {
      super(param1Int1, param1Int2);
      this.weight = 0.0F;
    }
    
    public LayoutParams(int param1Int1, int param1Int2, float param1Float) {
      super(param1Int1, param1Int2);
      this.weight = param1Float;
    }
    
    public LayoutParams(Context param1Context, AttributeSet param1AttributeSet) {
      super(param1Context, param1AttributeSet);
      TypedArray typedArray = param1Context.obtainStyledAttributes(param1AttributeSet, R.styleable.LinearLayoutCompat_Layout);
      this.weight = typedArray.getFloat(R.styleable.LinearLayoutCompat_Layout_android_layout_weight, 0.0F);
      this.gravity = typedArray.getInt(R.styleable.LinearLayoutCompat_Layout_android_layout_gravity, -1);
      typedArray.recycle();
    }
    
    public LayoutParams(ViewGroup.LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
    }
    
    public LayoutParams(ViewGroup.MarginLayoutParams param1MarginLayoutParams) {
      super(param1MarginLayoutParams);
    }
    
    public LayoutParams(LayoutParams param1LayoutParams) {
      super(param1LayoutParams);
      this.weight = param1LayoutParams.weight;
      this.gravity = param1LayoutParams.gravity;
    }
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface OrientationMode {}
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/LinearLayoutCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */