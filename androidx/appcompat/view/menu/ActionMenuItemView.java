package androidx.appcompat.view.menu;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.ForwardingListener;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ActionMenuItemView extends AppCompatTextView implements MenuView.ItemView, View.OnClickListener, ActionMenuView.ActionMenuChildView {
  private static final int MAX_ICON_SIZE = 32;
  
  private static final String TAG = "ActionMenuItemView";
  
  private boolean mAllowTextWithIcon;
  
  private boolean mExpandedFormat;
  
  private ForwardingListener mForwardingListener;
  
  private Drawable mIcon;
  
  MenuItemImpl mItemData;
  
  MenuBuilder.ItemInvoker mItemInvoker;
  
  private int mMaxIconSize;
  
  private int mMinWidth;
  
  PopupCallback mPopupCallback;
  
  private int mSavedPaddingLeft;
  
  private CharSequence mTitle;
  
  public ActionMenuItemView(Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet) {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public ActionMenuItemView(Context paramContext, AttributeSet paramAttributeSet, int paramInt) {
    super(paramContext, paramAttributeSet, paramInt);
    Resources resources = paramContext.getResources();
    this.mAllowTextWithIcon = shouldAllowTextWithIcon();
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ActionMenuItemView, paramInt, 0);
    this.mMinWidth = typedArray.getDimensionPixelSize(R.styleable.ActionMenuItemView_android_minWidth, 0);
    typedArray.recycle();
    this.mMaxIconSize = (int)(32.0F * (resources.getDisplayMetrics()).density + 0.5F);
    setOnClickListener(this);
    this.mSavedPaddingLeft = -1;
    setSaveEnabled(false);
  }
  
  private boolean shouldAllowTextWithIcon() {
    Configuration configuration = getContext().getResources().getConfiguration();
    int i = configuration.screenWidthDp;
    int j = configuration.screenHeightDp;
    return (i >= 480 || (i >= 640 && j >= 480) || configuration.orientation == 2);
  }
  
  private void updateTextButtonVisibility() {
    // Byte code:
    //   0: iconst_0
    //   1: istore_3
    //   2: aconst_null
    //   3: astore #5
    //   5: aload_0
    //   6: getfield mTitle : Ljava/lang/CharSequence;
    //   9: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   12: ifne -> 136
    //   15: iconst_1
    //   16: istore_1
    //   17: aload_0
    //   18: getfield mIcon : Landroid/graphics/drawable/Drawable;
    //   21: ifnull -> 52
    //   24: iload_3
    //   25: istore_2
    //   26: aload_0
    //   27: getfield mItemData : Landroidx/appcompat/view/menu/MenuItemImpl;
    //   30: invokevirtual showsTextAsAction : ()Z
    //   33: ifeq -> 54
    //   36: aload_0
    //   37: getfield mAllowTextWithIcon : Z
    //   40: ifne -> 52
    //   43: iload_3
    //   44: istore_2
    //   45: aload_0
    //   46: getfield mExpandedFormat : Z
    //   49: ifeq -> 54
    //   52: iconst_1
    //   53: istore_2
    //   54: iload_1
    //   55: iload_2
    //   56: iand
    //   57: istore_1
    //   58: iload_1
    //   59: ifeq -> 141
    //   62: aload_0
    //   63: getfield mTitle : Ljava/lang/CharSequence;
    //   66: astore #4
    //   68: aload_0
    //   69: aload #4
    //   71: invokevirtual setText : (Ljava/lang/CharSequence;)V
    //   74: aload_0
    //   75: getfield mItemData : Landroidx/appcompat/view/menu/MenuItemImpl;
    //   78: invokevirtual getContentDescription : ()Ljava/lang/CharSequence;
    //   81: astore #4
    //   83: aload #4
    //   85: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   88: ifeq -> 159
    //   91: iload_1
    //   92: ifeq -> 147
    //   95: aconst_null
    //   96: astore #4
    //   98: aload_0
    //   99: aload #4
    //   101: invokevirtual setContentDescription : (Ljava/lang/CharSequence;)V
    //   104: aload_0
    //   105: getfield mItemData : Landroidx/appcompat/view/menu/MenuItemImpl;
    //   108: invokevirtual getTooltipText : ()Ljava/lang/CharSequence;
    //   111: astore #4
    //   113: aload #4
    //   115: invokestatic isEmpty : (Ljava/lang/CharSequence;)Z
    //   118: ifeq -> 180
    //   121: iload_1
    //   122: ifeq -> 168
    //   125: aload #5
    //   127: astore #4
    //   129: aload_0
    //   130: aload #4
    //   132: invokestatic setTooltipText : (Landroid/view/View;Ljava/lang/CharSequence;)V
    //   135: return
    //   136: iconst_0
    //   137: istore_1
    //   138: goto -> 17
    //   141: aconst_null
    //   142: astore #4
    //   144: goto -> 68
    //   147: aload_0
    //   148: getfield mItemData : Landroidx/appcompat/view/menu/MenuItemImpl;
    //   151: invokevirtual getTitle : ()Ljava/lang/CharSequence;
    //   154: astore #4
    //   156: goto -> 98
    //   159: aload_0
    //   160: aload #4
    //   162: invokevirtual setContentDescription : (Ljava/lang/CharSequence;)V
    //   165: goto -> 104
    //   168: aload_0
    //   169: getfield mItemData : Landroidx/appcompat/view/menu/MenuItemImpl;
    //   172: invokevirtual getTitle : ()Ljava/lang/CharSequence;
    //   175: astore #4
    //   177: goto -> 129
    //   180: aload_0
    //   181: aload #4
    //   183: invokestatic setTooltipText : (Landroid/view/View;Ljava/lang/CharSequence;)V
    //   186: return
  }
  
  public MenuItemImpl getItemData() {
    return this.mItemData;
  }
  
  public boolean hasText() {
    return !TextUtils.isEmpty(getText());
  }
  
  public void initialize(MenuItemImpl paramMenuItemImpl, int paramInt) {
    this.mItemData = paramMenuItemImpl;
    setIcon(paramMenuItemImpl.getIcon());
    setTitle(paramMenuItemImpl.getTitleForItemView(this));
    setId(paramMenuItemImpl.getItemId());
    if (paramMenuItemImpl.isVisible()) {
      paramInt = 0;
    } else {
      paramInt = 8;
    } 
    setVisibility(paramInt);
    setEnabled(paramMenuItemImpl.isEnabled());
    if (paramMenuItemImpl.hasSubMenu() && this.mForwardingListener == null)
      this.mForwardingListener = new ActionMenuItemForwardingListener(); 
  }
  
  public boolean needsDividerAfter() {
    return hasText();
  }
  
  public boolean needsDividerBefore() {
    return (hasText() && this.mItemData.getIcon() == null);
  }
  
  public void onClick(View paramView) {
    if (this.mItemInvoker != null)
      this.mItemInvoker.invokeItem(this.mItemData); 
  }
  
  public void onConfigurationChanged(Configuration paramConfiguration) {
    super.onConfigurationChanged(paramConfiguration);
    this.mAllowTextWithIcon = shouldAllowTextWithIcon();
    updateTextButtonVisibility();
  }
  
  protected void onMeasure(int paramInt1, int paramInt2) {
    boolean bool = hasText();
    if (bool && this.mSavedPaddingLeft >= 0)
      super.setPadding(this.mSavedPaddingLeft, getPaddingTop(), getPaddingRight(), getPaddingBottom()); 
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt1);
    paramInt1 = View.MeasureSpec.getSize(paramInt1);
    int j = getMeasuredWidth();
    if (i == Integer.MIN_VALUE) {
      paramInt1 = Math.min(paramInt1, this.mMinWidth);
    } else {
      paramInt1 = this.mMinWidth;
    } 
    if (i != 1073741824 && this.mMinWidth > 0 && j < paramInt1)
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824), paramInt2); 
    if (!bool && this.mIcon != null)
      super.setPadding((getMeasuredWidth() - this.mIcon.getBounds().width()) / 2, getPaddingTop(), getPaddingRight(), getPaddingBottom()); 
  }
  
  public void onRestoreInstanceState(Parcelable paramParcelable) {
    super.onRestoreInstanceState(null);
  }
  
  public boolean onTouchEvent(MotionEvent paramMotionEvent) {
    return (this.mItemData.hasSubMenu() && this.mForwardingListener != null && this.mForwardingListener.onTouch((View)this, paramMotionEvent)) ? true : super.onTouchEvent(paramMotionEvent);
  }
  
  public boolean prefersCondensedTitle() {
    return true;
  }
  
  public void setCheckable(boolean paramBoolean) {}
  
  public void setChecked(boolean paramBoolean) {}
  
  public void setExpandedFormat(boolean paramBoolean) {
    if (this.mExpandedFormat != paramBoolean) {
      this.mExpandedFormat = paramBoolean;
      if (this.mItemData != null)
        this.mItemData.actionFormatChanged(); 
    } 
  }
  
  public void setIcon(Drawable paramDrawable) {
    this.mIcon = paramDrawable;
    if (paramDrawable != null) {
      int m = paramDrawable.getIntrinsicWidth();
      int k = paramDrawable.getIntrinsicHeight();
      int j = k;
      int i = m;
      if (m > this.mMaxIconSize) {
        float f = this.mMaxIconSize / m;
        i = this.mMaxIconSize;
        j = (int)(k * f);
      } 
      m = j;
      k = i;
      if (j > this.mMaxIconSize) {
        float f = this.mMaxIconSize / j;
        m = this.mMaxIconSize;
        k = (int)(i * f);
      } 
      paramDrawable.setBounds(0, 0, k, m);
    } 
    setCompoundDrawables(paramDrawable, null, null, null);
    updateTextButtonVisibility();
  }
  
  public void setItemInvoker(MenuBuilder.ItemInvoker paramItemInvoker) {
    this.mItemInvoker = paramItemInvoker;
  }
  
  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    this.mSavedPaddingLeft = paramInt1;
    super.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public void setPopupCallback(PopupCallback paramPopupCallback) {
    this.mPopupCallback = paramPopupCallback;
  }
  
  public void setShortcut(boolean paramBoolean, char paramChar) {}
  
  public void setTitle(CharSequence paramCharSequence) {
    this.mTitle = paramCharSequence;
    updateTextButtonVisibility();
  }
  
  public boolean showsIcon() {
    return true;
  }
  
  private class ActionMenuItemForwardingListener extends ForwardingListener {
    public ActionMenuItemForwardingListener() {
      super((View)ActionMenuItemView.this);
    }
    
    public ShowableListMenu getPopup() {
      return (ActionMenuItemView.this.mPopupCallback != null) ? ActionMenuItemView.this.mPopupCallback.getPopup() : null;
    }
    
    protected boolean onForwardingStarted() {
      boolean bool2 = false;
      boolean bool1 = bool2;
      if (ActionMenuItemView.this.mItemInvoker != null) {
        bool1 = bool2;
        if (ActionMenuItemView.this.mItemInvoker.invokeItem(ActionMenuItemView.this.mItemData)) {
          ShowableListMenu showableListMenu = getPopup();
          bool1 = bool2;
          if (showableListMenu != null) {
            bool1 = bool2;
            if (showableListMenu.isShowing())
              bool1 = true; 
          } 
        } 
      } 
      return bool1;
    }
  }
  
  public static abstract class PopupCallback {
    public abstract ShowableListMenu getPopup();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/view/menu/ActionMenuItemView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */