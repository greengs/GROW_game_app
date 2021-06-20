package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.ThemedSpinnerAdapter;
import androidx.annotation.DrawableRes;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.menu.ShowableListMenu;
import androidx.core.view.TintableBackgroundView;
import androidx.core.view.ViewCompat;

public class AppCompatSpinner extends Spinner implements TintableBackgroundView {
  private static final int[] ATTRS_ANDROID_SPINNERMODE = new int[] { 16843505 };
  
  private static final int MAX_ITEMS_MEASURED = 15;
  
  private static final int MODE_DIALOG = 0;
  
  private static final int MODE_DROPDOWN = 1;
  
  private static final int MODE_THEME = -1;
  
  private static final String TAG = "AppCompatSpinner";
  
  private final AppCompatBackgroundHelper mBackgroundTintHelper;
  
  int mDropDownWidth;
  
  private ForwardingListener mForwardingListener;
  
  DropdownPopup mPopup;
  
  private final Context mPopupContext;
  
  private final boolean mPopupSet;
  
  private SpinnerAdapter mTempAdapter;
  
  final Rect mTempRect;
  
  public AppCompatSpinner(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public AppCompatSpinner(Context paramContext, int paramInt) {
    this(paramContext, (AttributeSet)null, R.attr.spinnerStyle, paramInt);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, R.attr.spinnerStyle);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    this(paramContext, paramAttributeSet, paramInt, -1);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2) {
    this(paramContext, paramAttributeSet, paramInt1, paramInt2, (Resources.Theme)null);
  }
  
  public AppCompatSpinner(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2, Resources.Theme paramTheme) {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_2
    //   3: iload_3
    //   4: invokespecial <init> : (Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   7: aload_0
    //   8: new android/graphics/Rect
    //   11: dup
    //   12: invokespecial <init> : ()V
    //   15: putfield mTempRect : Landroid/graphics/Rect;
    //   18: aload_1
    //   19: aload_2
    //   20: getstatic androidx/appcompat/R$styleable.Spinner : [I
    //   23: iload_3
    //   24: iconst_0
    //   25: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroidx/appcompat/widget/TintTypedArray;
    //   28: astore #10
    //   30: aload_0
    //   31: new androidx/appcompat/widget/AppCompatBackgroundHelper
    //   34: dup
    //   35: aload_0
    //   36: invokespecial <init> : (Landroid/view/View;)V
    //   39: putfield mBackgroundTintHelper : Landroidx/appcompat/widget/AppCompatBackgroundHelper;
    //   42: aload #5
    //   44: ifnull -> 335
    //   47: aload_0
    //   48: new androidx/appcompat/view/ContextThemeWrapper
    //   51: dup
    //   52: aload_1
    //   53: aload #5
    //   55: invokespecial <init> : (Landroid/content/Context;Landroid/content/res/Resources$Theme;)V
    //   58: putfield mPopupContext : Landroid/content/Context;
    //   61: aload_0
    //   62: getfield mPopupContext : Landroid/content/Context;
    //   65: ifnull -> 255
    //   68: iload #4
    //   70: istore #7
    //   72: iload #4
    //   74: iconst_m1
    //   75: if_icmpne -> 152
    //   78: aconst_null
    //   79: astore #8
    //   81: aconst_null
    //   82: astore #5
    //   84: aload_1
    //   85: aload_2
    //   86: getstatic androidx/appcompat/widget/AppCompatSpinner.ATTRS_ANDROID_SPINNERMODE : [I
    //   89: iload_3
    //   90: iconst_0
    //   91: invokevirtual obtainStyledAttributes : (Landroid/util/AttributeSet;[III)Landroid/content/res/TypedArray;
    //   94: astore #9
    //   96: iload #4
    //   98: istore #6
    //   100: aload #9
    //   102: astore #5
    //   104: aload #9
    //   106: astore #8
    //   108: aload #9
    //   110: iconst_0
    //   111: invokevirtual hasValue : (I)Z
    //   114: ifeq -> 134
    //   117: aload #9
    //   119: astore #5
    //   121: aload #9
    //   123: astore #8
    //   125: aload #9
    //   127: iconst_0
    //   128: iconst_0
    //   129: invokevirtual getInt : (II)I
    //   132: istore #6
    //   134: iload #6
    //   136: istore #7
    //   138: aload #9
    //   140: ifnull -> 152
    //   143: aload #9
    //   145: invokevirtual recycle : ()V
    //   148: iload #6
    //   150: istore #7
    //   152: iload #7
    //   154: iconst_1
    //   155: if_icmpne -> 255
    //   158: new androidx/appcompat/widget/AppCompatSpinner$DropdownPopup
    //   161: dup
    //   162: aload_0
    //   163: aload_0
    //   164: getfield mPopupContext : Landroid/content/Context;
    //   167: aload_2
    //   168: iload_3
    //   169: invokespecial <init> : (Landroidx/appcompat/widget/AppCompatSpinner;Landroid/content/Context;Landroid/util/AttributeSet;I)V
    //   172: astore #5
    //   174: aload_0
    //   175: getfield mPopupContext : Landroid/content/Context;
    //   178: aload_2
    //   179: getstatic androidx/appcompat/R$styleable.Spinner : [I
    //   182: iload_3
    //   183: iconst_0
    //   184: invokestatic obtainStyledAttributes : (Landroid/content/Context;Landroid/util/AttributeSet;[III)Landroidx/appcompat/widget/TintTypedArray;
    //   187: astore #8
    //   189: aload_0
    //   190: aload #8
    //   192: getstatic androidx/appcompat/R$styleable.Spinner_android_dropDownWidth : I
    //   195: bipush #-2
    //   197: invokevirtual getLayoutDimension : (II)I
    //   200: putfield mDropDownWidth : I
    //   203: aload #5
    //   205: aload #8
    //   207: getstatic androidx/appcompat/R$styleable.Spinner_android_popupBackground : I
    //   210: invokevirtual getDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   213: invokevirtual setBackgroundDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   216: aload #5
    //   218: aload #10
    //   220: getstatic androidx/appcompat/R$styleable.Spinner_android_prompt : I
    //   223: invokevirtual getString : (I)Ljava/lang/String;
    //   226: invokevirtual setPromptText : (Ljava/lang/CharSequence;)V
    //   229: aload #8
    //   231: invokevirtual recycle : ()V
    //   234: aload_0
    //   235: aload #5
    //   237: putfield mPopup : Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;
    //   240: aload_0
    //   241: new androidx/appcompat/widget/AppCompatSpinner$1
    //   244: dup
    //   245: aload_0
    //   246: aload_0
    //   247: aload #5
    //   249: invokespecial <init> : (Landroidx/appcompat/widget/AppCompatSpinner;Landroid/view/View;Landroidx/appcompat/widget/AppCompatSpinner$DropdownPopup;)V
    //   252: putfield mForwardingListener : Landroidx/appcompat/widget/ForwardingListener;
    //   255: aload #10
    //   257: getstatic androidx/appcompat/R$styleable.Spinner_android_entries : I
    //   260: invokevirtual getTextArray : (I)[Ljava/lang/CharSequence;
    //   263: astore #5
    //   265: aload #5
    //   267: ifnull -> 295
    //   270: new android/widget/ArrayAdapter
    //   273: dup
    //   274: aload_1
    //   275: ldc 17367048
    //   277: aload #5
    //   279: invokespecial <init> : (Landroid/content/Context;I[Ljava/lang/Object;)V
    //   282: astore_1
    //   283: aload_1
    //   284: getstatic androidx/appcompat/R$layout.support_simple_spinner_dropdown_item : I
    //   287: invokevirtual setDropDownViewResource : (I)V
    //   290: aload_0
    //   291: aload_1
    //   292: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   295: aload #10
    //   297: invokevirtual recycle : ()V
    //   300: aload_0
    //   301: iconst_1
    //   302: putfield mPopupSet : Z
    //   305: aload_0
    //   306: getfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   309: ifnull -> 325
    //   312: aload_0
    //   313: aload_0
    //   314: getfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   317: invokevirtual setAdapter : (Landroid/widget/SpinnerAdapter;)V
    //   320: aload_0
    //   321: aconst_null
    //   322: putfield mTempAdapter : Landroid/widget/SpinnerAdapter;
    //   325: aload_0
    //   326: getfield mBackgroundTintHelper : Landroidx/appcompat/widget/AppCompatBackgroundHelper;
    //   329: aload_2
    //   330: iload_3
    //   331: invokevirtual loadFromAttributes : (Landroid/util/AttributeSet;I)V
    //   334: return
    //   335: aload #10
    //   337: getstatic androidx/appcompat/R$styleable.Spinner_popupTheme : I
    //   340: iconst_0
    //   341: invokevirtual getResourceId : (II)I
    //   344: istore #6
    //   346: iload #6
    //   348: ifeq -> 368
    //   351: aload_0
    //   352: new androidx/appcompat/view/ContextThemeWrapper
    //   355: dup
    //   356: aload_1
    //   357: iload #6
    //   359: invokespecial <init> : (Landroid/content/Context;I)V
    //   362: putfield mPopupContext : Landroid/content/Context;
    //   365: goto -> 61
    //   368: getstatic android/os/Build$VERSION.SDK_INT : I
    //   371: bipush #23
    //   373: if_icmpge -> 388
    //   376: aload_1
    //   377: astore #5
    //   379: aload_0
    //   380: aload #5
    //   382: putfield mPopupContext : Landroid/content/Context;
    //   385: goto -> 61
    //   388: aconst_null
    //   389: astore #5
    //   391: goto -> 379
    //   394: astore #9
    //   396: aload #5
    //   398: astore #8
    //   400: ldc 'AppCompatSpinner'
    //   402: ldc 'Could not read android:spinnerMode'
    //   404: aload #9
    //   406: invokestatic i : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   409: pop
    //   410: iload #4
    //   412: istore #7
    //   414: aload #5
    //   416: ifnull -> 152
    //   419: aload #5
    //   421: invokevirtual recycle : ()V
    //   424: iload #4
    //   426: istore #7
    //   428: goto -> 152
    //   431: astore_1
    //   432: aload #8
    //   434: ifnull -> 442
    //   437: aload #8
    //   439: invokevirtual recycle : ()V
    //   442: aload_1
    //   443: athrow
    // Exception table:
    //   from	to	target	type
    //   84	96	394	java/lang/Exception
    //   84	96	431	finally
    //   108	117	394	java/lang/Exception
    //   108	117	431	finally
    //   125	134	394	java/lang/Exception
    //   125	134	431	finally
    //   400	410	431	finally
  }
  
  int compatMeasureContentWidth(SpinnerAdapter paramSpinnerAdapter, Drawable paramDrawable) {
    if (paramSpinnerAdapter == null)
      return 0; 
    int i = 0;
    View view = null;
    int k = 0;
    int m = View.MeasureSpec.makeMeasureSpec(getMeasuredWidth(), 0);
    int n = View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 0);
    int j = Math.max(0, getSelectedItemPosition());
    int i1 = Math.min(paramSpinnerAdapter.getCount(), j + 15);
    j = Math.max(0, j - 15 - i1 - j);
    while (j < i1) {
      int i3 = paramSpinnerAdapter.getItemViewType(j);
      int i2 = k;
      if (i3 != k) {
        i2 = i3;
        view = null;
      } 
      view = paramSpinnerAdapter.getView(j, view, (ViewGroup)this);
      if (view.getLayoutParams() == null)
        view.setLayoutParams(new ViewGroup.LayoutParams(-2, -2)); 
      view.measure(m, n);
      i = Math.max(i, view.getMeasuredWidth());
      j++;
      k = i2;
    } 
    j = i;
    if (paramDrawable != null) {
      paramDrawable.getPadding(this.mTempRect);
      return i + this.mTempRect.left + this.mTempRect.right;
    } 
    return j;
  }
  
  protected void drawableStateChanged() {
    super.drawableStateChanged();
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.applySupportBackgroundTint(); 
  }
  
  public int getDropDownHorizontalOffset() {
    return (this.mPopup != null) ? this.mPopup.getHorizontalOffset() : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownHorizontalOffset() : 0);
  }
  
  public int getDropDownVerticalOffset() {
    return (this.mPopup != null) ? this.mPopup.getVerticalOffset() : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownVerticalOffset() : 0);
  }
  
  public int getDropDownWidth() {
    return (this.mPopup != null) ? this.mDropDownWidth : ((Build.VERSION.SDK_INT >= 16) ? super.getDropDownWidth() : 0);
  }
  
  public Drawable getPopupBackground() {
    return (this.mPopup != null) ? this.mPopup.getBackground() : ((Build.VERSION.SDK_INT >= 16) ? super.getPopupBackground() : null);
  }
  
  public Context getPopupContext() {
    return (this.mPopup != null) ? this.mPopupContext : ((Build.VERSION.SDK_INT >= 23) ? super.getPopupContext() : null);
  }
  
  public CharSequence getPrompt() {
    return (this.mPopup != null) ? this.mPopup.getHintText() : super.getPrompt();
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public ColorStateList getSupportBackgroundTintList() {
    return (this.mBackgroundTintHelper != null) ? this.mBackgroundTintHelper.getSupportBackgroundTintList() : null;
  }
  
  @Nullable
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public PorterDuff.Mode getSupportBackgroundTintMode() {
    return (this.mBackgroundTintHelper != null) ? this.mBackgroundTintHelper.getSupportBackgroundTintMode() : null;
  }
  
  protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    if (this.mPopup != null && this.mPopup.isShowing())
      this.mPopup.dismiss(); 
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    super.onMeasure(paramInt1, paramInt2);
    if (this.mPopup != null && View.MeasureSpec.getMode(paramInt1) == Integer.MIN_VALUE)
      setMeasuredDimension(Math.min(Math.max(getMeasuredWidth(), compatMeasureContentWidth(getAdapter(), getBackground())), View.MeasureSpec.getSize(paramInt1)), getMeasuredHeight()); 
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return (this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, paramMotionEvent)) ? true : super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean performClick() {
    if (this.mPopup != null) {
      if (!this.mPopup.isShowing())
        this.mPopup.show(); 
      return true;
    } 
    return super.performClick();
  }
  
  public void setAdapter(SpinnerAdapter paramSpinnerAdapter) {
    if (!this.mPopupSet) {
      this.mTempAdapter = paramSpinnerAdapter;
      return;
    } 
    super.setAdapter(paramSpinnerAdapter);
    if (this.mPopup != null) {
      Context context;
      if (this.mPopupContext == null) {
        context = getContext();
      } else {
        context = this.mPopupContext;
      } 
      this.mPopup.setAdapter(new DropDownAdapter(paramSpinnerAdapter, context.getTheme()));
      return;
    } 
  }
  
  public void setBackgroundDrawable(Drawable paramDrawable) {
    super.setBackgroundDrawable(paramDrawable);
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.onSetBackgroundDrawable(paramDrawable); 
  }
  
  public void setBackgroundResource(@DrawableRes int paramInt) {
    super.setBackgroundResource(paramInt);
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.onSetBackgroundResource(paramInt); 
  }
  
  public void setDropDownHorizontalOffset(int paramInt) {
    if (this.mPopup != null) {
      this.mPopup.setHorizontalOffset(paramInt);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownHorizontalOffset(paramInt);
      return;
    } 
  }
  
  public void setDropDownVerticalOffset(int paramInt) {
    if (this.mPopup != null) {
      this.mPopup.setVerticalOffset(paramInt);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownVerticalOffset(paramInt);
      return;
    } 
  }
  
  public void setDropDownWidth(int paramInt) {
    if (this.mPopup != null) {
      this.mDropDownWidth = paramInt;
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      super.setDropDownWidth(paramInt);
      return;
    } 
  }
  
  public void setPopupBackgroundDrawable(Drawable paramDrawable) {
    if (this.mPopup != null) {
      this.mPopup.setBackgroundDrawable(paramDrawable);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      super.setPopupBackgroundDrawable(paramDrawable);
      return;
    } 
  }
  
  public void setPopupBackgroundResource(@DrawableRes int paramInt) {
    setPopupBackgroundDrawable(AppCompatResources.getDrawable(getPopupContext(), paramInt));
  }
  
  public void setPrompt(CharSequence paramCharSequence) {
    if (this.mPopup != null) {
      this.mPopup.setPromptText(paramCharSequence);
      return;
    } 
    super.setPrompt(paramCharSequence);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintList(@Nullable ColorStateList paramColorStateList) {
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.setSupportBackgroundTintList(paramColorStateList); 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public void setSupportBackgroundTintMode(@Nullable PorterDuff.Mode paramMode) {
    if (this.mBackgroundTintHelper != null)
      this.mBackgroundTintHelper.setSupportBackgroundTintMode(paramMode); 
  }
  
  private static class DropDownAdapter implements ListAdapter, SpinnerAdapter {
    private SpinnerAdapter mAdapter;
    
    private ListAdapter mListAdapter;
    
    public DropDownAdapter(@Nullable SpinnerAdapter param1SpinnerAdapter, @Nullable Resources.Theme param1Theme) {
      ThemedSpinnerAdapter themedSpinnerAdapter;
      this.mAdapter = param1SpinnerAdapter;
      if (param1SpinnerAdapter instanceof ListAdapter)
        this.mListAdapter = (ListAdapter)param1SpinnerAdapter; 
      if (param1Theme != null) {
        if (Build.VERSION.SDK_INT >= 23 && param1SpinnerAdapter instanceof ThemedSpinnerAdapter) {
          themedSpinnerAdapter = (ThemedSpinnerAdapter)param1SpinnerAdapter;
          if (themedSpinnerAdapter.getDropDownViewTheme() != param1Theme)
            themedSpinnerAdapter.setDropDownViewTheme(param1Theme); 
          return;
        } 
      } else {
        return;
      } 
      if (themedSpinnerAdapter instanceof ThemedSpinnerAdapter) {
        ThemedSpinnerAdapter themedSpinnerAdapter1 = (ThemedSpinnerAdapter)themedSpinnerAdapter;
        if (themedSpinnerAdapter1.getDropDownViewTheme() == null) {
          themedSpinnerAdapter1.setDropDownViewTheme(param1Theme);
          return;
        } 
      } 
    }
    
    public boolean areAllItemsEnabled() {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.areAllItemsEnabled() : true;
    }
    
    public int getCount() {
      return (this.mAdapter == null) ? 0 : this.mAdapter.getCount();
    }
    
    public View getDropDownView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      return (this.mAdapter == null) ? null : this.mAdapter.getDropDownView(param1Int, param1View, param1ViewGroup);
    }
    
    public Object getItem(int param1Int) {
      return (this.mAdapter == null) ? null : this.mAdapter.getItem(param1Int);
    }
    
    public long getItemId(int param1Int) {
      return (this.mAdapter == null) ? -1L : this.mAdapter.getItemId(param1Int);
    }
    
    public int getItemViewType(int param1Int) {
      return 0;
    }
    
    public View getView(int param1Int, View param1View, ViewGroup param1ViewGroup) {
      return getDropDownView(param1Int, param1View, param1ViewGroup);
    }
    
    public int getViewTypeCount() {
      return 1;
    }
    
    public boolean hasStableIds() {
      return (this.mAdapter != null && this.mAdapter.hasStableIds());
    }
    
    public boolean isEmpty() {
      return (getCount() == 0);
    }
    
    public boolean isEnabled(int param1Int) {
      ListAdapter listAdapter = this.mListAdapter;
      return (listAdapter != null) ? listAdapter.isEnabled(param1Int) : true;
    }
    
    public void registerDataSetObserver(DataSetObserver param1DataSetObserver) {
      if (this.mAdapter != null)
        this.mAdapter.registerDataSetObserver(param1DataSetObserver); 
    }
    
    public void unregisterDataSetObserver(DataSetObserver param1DataSetObserver) {
      if (this.mAdapter != null)
        this.mAdapter.unregisterDataSetObserver(param1DataSetObserver); 
    }
  }
  
  private class DropdownPopup extends ListPopupWindow {
    ListAdapter mAdapter;
    
    private CharSequence mHintText;
    
    private final Rect mVisibleRect = new Rect();
    
    public DropdownPopup(Context param1Context, AttributeSet param1AttributeSet, int param1Int) {
      super(param1Context, param1AttributeSet, param1Int);
      setAnchorView((View)AppCompatSpinner.this);
      setModal(true);
      setPromptPosition(0);
      setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> param2AdapterView, View param2View, int param2Int, long param2Long) {
              AppCompatSpinner.this.setSelection(param2Int);
              if (AppCompatSpinner.this.getOnItemClickListener() != null)
                AppCompatSpinner.this.performItemClick(param2View, param2Int, AppCompatSpinner.DropdownPopup.this.mAdapter.getItemId(param2Int)); 
              AppCompatSpinner.DropdownPopup.this.dismiss();
            }
          });
    }
    
    void computeContentWidth() {
      Drawable drawable = getBackground();
      int i = 0;
      if (drawable != null) {
        drawable.getPadding(AppCompatSpinner.this.mTempRect);
        if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
          i = AppCompatSpinner.this.mTempRect.right;
        } else {
          i = -AppCompatSpinner.this.mTempRect.left;
        } 
      } else {
        Rect rect = AppCompatSpinner.this.mTempRect;
        AppCompatSpinner.this.mTempRect.right = 0;
        rect.left = 0;
      } 
      int j = AppCompatSpinner.this.getPaddingLeft();
      int k = AppCompatSpinner.this.getPaddingRight();
      int m = AppCompatSpinner.this.getWidth();
      if (AppCompatSpinner.this.mDropDownWidth == -2) {
        int i1 = AppCompatSpinner.this.compatMeasureContentWidth((SpinnerAdapter)this.mAdapter, getBackground());
        int i2 = (AppCompatSpinner.this.getContext().getResources().getDisplayMetrics()).widthPixels - AppCompatSpinner.this.mTempRect.left - AppCompatSpinner.this.mTempRect.right;
        int n = i1;
        if (i1 > i2)
          n = i2; 
        setContentWidth(Math.max(n, m - j - k));
      } else if (AppCompatSpinner.this.mDropDownWidth == -1) {
        setContentWidth(m - j - k);
      } else {
        setContentWidth(AppCompatSpinner.this.mDropDownWidth);
      } 
      if (ViewUtils.isLayoutRtl((View)AppCompatSpinner.this)) {
        i += m - k - getWidth();
      } else {
        i += j;
      } 
      setHorizontalOffset(i);
    }
    
    public CharSequence getHintText() {
      return this.mHintText;
    }
    
    boolean isVisibleToUser(View param1View) {
      return (ViewCompat.isAttachedToWindow(param1View) && param1View.getGlobalVisibleRect(this.mVisibleRect));
    }
    
    public void setAdapter(ListAdapter param1ListAdapter) {
      super.setAdapter(param1ListAdapter);
      this.mAdapter = param1ListAdapter;
    }
    
    public void setPromptText(CharSequence param1CharSequence) {
      this.mHintText = param1CharSequence;
    }
    
    public void show() {
      boolean bool = isShowing();
      computeContentWidth();
      setInputMethodMode(2);
      super.show();
      getListView().setChoiceMode(1);
      setSelection(AppCompatSpinner.this.getSelectedItemPosition());
      if (!bool) {
        ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
        if (viewTreeObserver != null) {
          final ViewTreeObserver.OnGlobalLayoutListener layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
              public void onGlobalLayout() {
                if (!AppCompatSpinner.DropdownPopup.this.isVisibleToUser((View)AppCompatSpinner.this)) {
                  AppCompatSpinner.DropdownPopup.this.dismiss();
                  return;
                } 
                AppCompatSpinner.DropdownPopup.this.computeContentWidth();
                AppCompatSpinner.DropdownPopup.this.show();
              }
            };
          viewTreeObserver.addOnGlobalLayoutListener(onGlobalLayoutListener);
          setOnDismissListener(new PopupWindow.OnDismissListener() {
                public void onDismiss() {
                  ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
                  if (viewTreeObserver != null)
                    viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
                }
              });
          return;
        } 
      } 
    }
  }
  
  class null implements AdapterView.OnItemClickListener {
    public void onItemClick(AdapterView<?> param1AdapterView, View param1View, int param1Int, long param1Long) {
      AppCompatSpinner.this.setSelection(param1Int);
      if (AppCompatSpinner.this.getOnItemClickListener() != null)
        AppCompatSpinner.this.performItemClick(param1View, param1Int, this.this$1.mAdapter.getItemId(param1Int)); 
      this.this$1.dismiss();
    }
  }
  
  class null implements ViewTreeObserver.OnGlobalLayoutListener {
    public void onGlobalLayout() {
      if (!this.this$1.isVisibleToUser((View)AppCompatSpinner.this)) {
        this.this$1.dismiss();
        return;
      } 
      this.this$1.computeContentWidth();
      this.this$1.show();
    }
  }
  
  class null implements PopupWindow.OnDismissListener {
    public void onDismiss() {
      ViewTreeObserver viewTreeObserver = AppCompatSpinner.this.getViewTreeObserver();
      if (viewTreeObserver != null)
        viewTreeObserver.removeGlobalOnLayoutListener(layoutListener); 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatSpinner.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */