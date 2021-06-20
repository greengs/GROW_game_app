package androidx.appcompat.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.AutoSizeableTextView;
import androidx.core.widget.TextViewCompat;
import java.lang.ref.WeakReference;

class AppCompatTextHelper {
  private static final int MONOSPACE = 3;
  
  private static final int SANS = 1;
  
  private static final int SERIF = 2;
  
  private boolean mAsyncFontPending;
  
  @NonNull
  private final AppCompatTextViewAutoSizeHelper mAutoSizeTextHelper;
  
  private TintInfo mDrawableBottomTint;
  
  private TintInfo mDrawableEndTint;
  
  private TintInfo mDrawableLeftTint;
  
  private TintInfo mDrawableRightTint;
  
  private TintInfo mDrawableStartTint;
  
  private TintInfo mDrawableTopTint;
  
  private Typeface mFontTypeface;
  
  private int mStyle = 0;
  
  private final TextView mView;
  
  AppCompatTextHelper(TextView paramTextView) {
    this.mView = paramTextView;
    this.mAutoSizeTextHelper = new AppCompatTextViewAutoSizeHelper(this.mView);
  }
  
  private void applyCompoundDrawableTint(Drawable paramDrawable, TintInfo paramTintInfo) {
    if (paramDrawable != null && paramTintInfo != null)
      AppCompatDrawableManager.tintDrawable(paramDrawable, paramTintInfo, this.mView.getDrawableState()); 
  }
  
  private static TintInfo createTintInfo(Context paramContext, AppCompatDrawableManager paramAppCompatDrawableManager, int paramInt) {
    ColorStateList colorStateList = paramAppCompatDrawableManager.getTintList(paramContext, paramInt);
    if (colorStateList != null) {
      TintInfo tintInfo = new TintInfo();
      tintInfo.mHasTintList = true;
      tintInfo.mTintList = colorStateList;
      return tintInfo;
    } 
    return null;
  }
  
  private void setTextSizeInternal(int paramInt, float paramFloat) {
    this.mAutoSizeTextHelper.setTextSizeInternal(paramInt, paramFloat);
  }
  
  private void updateTypefaceAndStyle(Context paramContext, TintTypedArray paramTintTypedArray) {
    boolean bool = true;
    this.mStyle = paramTintTypedArray.getInt(R.styleable.TextAppearance_android_textStyle, this.mStyle);
    if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_fontFamily) || paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
      int i;
      this.mFontTypeface = null;
      if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_fontFamily)) {
        i = R.styleable.TextAppearance_fontFamily;
      } else {
        i = R.styleable.TextAppearance_android_fontFamily;
      } 
      if (!paramContext.isRestricted()) {
        ResourcesCompat.FontCallback fontCallback = new ResourcesCompat.FontCallback() {
            public void onFontRetrievalFailed(int param1Int) {}
            
            public void onFontRetrieved(@NonNull Typeface param1Typeface) {
              AppCompatTextHelper.this.onAsyncTypefaceReceived(textViewWeak, param1Typeface);
            }
          };
        try {
          this.mFontTypeface = paramTintTypedArray.getFont(i, this.mStyle, fontCallback);
          if (this.mFontTypeface != null)
            bool = false; 
          this.mAsyncFontPending = bool;
        } catch (UnsupportedOperationException unsupportedOperationException) {
        
        } catch (android.content.res.Resources.NotFoundException notFoundException) {}
      } 
      if (this.mFontTypeface == null) {
        String str = paramTintTypedArray.getString(i);
        if (str != null)
          this.mFontTypeface = Typeface.create(str, this.mStyle); 
      } 
      return;
    } 
    if (paramTintTypedArray.hasValue(R.styleable.TextAppearance_android_typeface)) {
      this.mAsyncFontPending = false;
      switch (paramTintTypedArray.getInt(R.styleable.TextAppearance_android_typeface, 1)) {
        default:
          return;
        case 1:
          this.mFontTypeface = Typeface.SANS_SERIF;
          return;
        case 2:
          this.mFontTypeface = Typeface.SERIF;
          return;
        case 3:
          break;
      } 
      this.mFontTypeface = Typeface.MONOSPACE;
      return;
    } 
  }
  
  void applyCompoundDrawablesTints() {
    if (this.mDrawableLeftTint != null || this.mDrawableTopTint != null || this.mDrawableRightTint != null || this.mDrawableBottomTint != null) {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawables();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableLeftTint);
      applyCompoundDrawableTint(arrayOfDrawable[1], this.mDrawableTopTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableRightTint);
      applyCompoundDrawableTint(arrayOfDrawable[3], this.mDrawableBottomTint);
    } 
    if (Build.VERSION.SDK_INT >= 17 && (this.mDrawableStartTint != null || this.mDrawableEndTint != null)) {
      Drawable[] arrayOfDrawable = this.mView.getCompoundDrawablesRelative();
      applyCompoundDrawableTint(arrayOfDrawable[0], this.mDrawableStartTint);
      applyCompoundDrawableTint(arrayOfDrawable[2], this.mDrawableEndTint);
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void autoSizeText() {
    this.mAutoSizeTextHelper.autoSizeText();
  }
  
  int getAutoSizeMaxTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMaxTextSize();
  }
  
  int getAutoSizeMinTextSize() {
    return this.mAutoSizeTextHelper.getAutoSizeMinTextSize();
  }
  
  int getAutoSizeStepGranularity() {
    return this.mAutoSizeTextHelper.getAutoSizeStepGranularity();
  }
  
  int[] getAutoSizeTextAvailableSizes() {
    return this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
  }
  
  int getAutoSizeTextType() {
    return this.mAutoSizeTextHelper.getAutoSizeTextType();
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isAutoSizeEnabled() {
    return this.mAutoSizeTextHelper.isAutoSizeEnabled();
  }
  
  @SuppressLint({"NewApi"})
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    ColorStateList colorStateList1;
    ColorStateList colorStateList2;
    Context context = this.mView.getContext();
    AppCompatDrawableManager appCompatDrawableManager1 = AppCompatDrawableManager.get();
    TintTypedArray tintTypedArray2 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.AppCompatTextHelper, paramInt, 0);
    int k = tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_textAppearance, -1);
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableLeft))
      this.mDrawableLeftTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableLeft, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableTop))
      this.mDrawableTopTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableTop, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableRight))
      this.mDrawableRightTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableRight, 0)); 
    if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableBottom))
      this.mDrawableBottomTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableBottom, 0)); 
    if (Build.VERSION.SDK_INT >= 17) {
      if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableStart))
        this.mDrawableStartTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableStart, 0)); 
      if (tintTypedArray2.hasValue(R.styleable.AppCompatTextHelper_android_drawableEnd))
        this.mDrawableEndTint = createTintInfo(context, appCompatDrawableManager1, tintTypedArray2.getResourceId(R.styleable.AppCompatTextHelper_android_drawableEnd, 0)); 
    } 
    tintTypedArray2.recycle();
    boolean bool3 = this.mView.getTransformationMethod() instanceof android.text.method.PasswordTransformationMethod;
    boolean bool1 = false;
    boolean bool2 = false;
    int i = 0;
    int j = 0;
    TintTypedArray tintTypedArray4 = null;
    tintTypedArray2 = null;
    AppCompatDrawableManager appCompatDrawableManager2 = null;
    ColorStateList colorStateList4 = null;
    appCompatDrawableManager1 = null;
    ColorStateList colorStateList5 = null;
    ColorStateList colorStateList3 = null;
    ColorStateList colorStateList6 = null;
    if (k != -1) {
      TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, k, R.styleable.TextAppearance);
      bool1 = bool2;
      i = j;
      if (!bool3) {
        bool1 = bool2;
        i = j;
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps)) {
          i = 1;
          bool1 = tintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
        } 
      } 
      updateTypefaceAndStyle(context, tintTypedArray);
      tintTypedArray2 = tintTypedArray4;
      colorStateList3 = colorStateList6;
      if (Build.VERSION.SDK_INT < 23) {
        ColorStateList colorStateList;
        appCompatDrawableManager1 = appCompatDrawableManager2;
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor))
          colorStateList = tintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor); 
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorHint))
          colorStateList5 = tintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorHint); 
        colorStateList2 = colorStateList;
        colorStateList4 = colorStateList5;
        colorStateList3 = colorStateList6;
        if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
          colorStateList3 = tintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
          colorStateList4 = colorStateList5;
          colorStateList2 = colorStateList;
        } 
      } 
      tintTypedArray.recycle();
      colorStateList1 = colorStateList4;
    } 
    TintTypedArray tintTypedArray3 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.TextAppearance, paramInt, 0);
    bool2 = bool1;
    j = i;
    if (!bool3) {
      bool2 = bool1;
      j = i;
      if (tintTypedArray3.hasValue(R.styleable.TextAppearance_textAllCaps)) {
        j = 1;
        bool2 = tintTypedArray3.getBoolean(R.styleable.TextAppearance_textAllCaps, false);
      } 
    } 
    colorStateList4 = colorStateList2;
    colorStateList5 = colorStateList1;
    colorStateList6 = colorStateList3;
    if (Build.VERSION.SDK_INT < 23) {
      if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColor))
        colorStateList2 = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColor); 
      if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColorHint))
        colorStateList1 = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColorHint); 
      colorStateList4 = colorStateList2;
      colorStateList5 = colorStateList1;
      colorStateList6 = colorStateList3;
      if (tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textColorLink)) {
        colorStateList6 = tintTypedArray3.getColorStateList(R.styleable.TextAppearance_android_textColorLink);
        colorStateList5 = colorStateList1;
        colorStateList4 = colorStateList2;
      } 
    } 
    if (Build.VERSION.SDK_INT >= 28 && tintTypedArray3.hasValue(R.styleable.TextAppearance_android_textSize) && tintTypedArray3.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)
      this.mView.setTextSize(0, 0.0F); 
    updateTypefaceAndStyle(context, tintTypedArray3);
    tintTypedArray3.recycle();
    if (colorStateList4 != null)
      this.mView.setTextColor(colorStateList4); 
    if (colorStateList5 != null)
      this.mView.setHintTextColor(colorStateList5); 
    if (colorStateList6 != null)
      this.mView.setLinkTextColor(colorStateList6); 
    if (!bool3 && j)
      setAllCaps(bool2); 
    if (this.mFontTypeface != null)
      this.mView.setTypeface(this.mFontTypeface, this.mStyle); 
    this.mAutoSizeTextHelper.loadFromAttributes(paramAttributeSet, paramInt);
    if (AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && this.mAutoSizeTextHelper.getAutoSizeTextType() != 0) {
      int[] arrayOfInt = this.mAutoSizeTextHelper.getAutoSizeTextAvailableSizes();
      if (arrayOfInt.length > 0)
        if (this.mView.getAutoSizeStepGranularity() != -1.0F) {
          this.mView.setAutoSizeTextTypeUniformWithConfiguration(this.mAutoSizeTextHelper.getAutoSizeMinTextSize(), this.mAutoSizeTextHelper.getAutoSizeMaxTextSize(), this.mAutoSizeTextHelper.getAutoSizeStepGranularity(), 0);
        } else {
          this.mView.setAutoSizeTextTypeUniformWithPresetSizes(arrayOfInt, 0);
        }  
    } 
    TintTypedArray tintTypedArray1 = TintTypedArray.obtainStyledAttributes(context, paramAttributeSet, R.styleable.AppCompatTextView);
    paramInt = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_firstBaselineToTopHeight, -1);
    i = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_lastBaselineToBottomHeight, -1);
    j = tintTypedArray1.getDimensionPixelSize(R.styleable.AppCompatTextView_lineHeight, -1);
    tintTypedArray1.recycle();
    if (paramInt != -1)
      TextViewCompat.setFirstBaselineToTopHeight(this.mView, paramInt); 
    if (i != -1)
      TextViewCompat.setLastBaselineToBottomHeight(this.mView, i); 
    if (j != -1)
      TextViewCompat.setLineHeight(this.mView, j); 
  }
  
  void onAsyncTypefaceReceived(WeakReference<TextView> paramWeakReference, Typeface paramTypeface) {
    if (this.mAsyncFontPending) {
      this.mFontTypeface = paramTypeface;
      TextView textView = paramWeakReference.get();
      if (textView != null)
        textView.setTypeface(paramTypeface, this.mStyle); 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE)
      autoSizeText(); 
  }
  
  void onSetTextAppearance(Context paramContext, int paramInt) {
    TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(paramContext, paramInt, R.styleable.TextAppearance);
    if (tintTypedArray.hasValue(R.styleable.TextAppearance_textAllCaps))
      setAllCaps(tintTypedArray.getBoolean(R.styleable.TextAppearance_textAllCaps, false)); 
    if (Build.VERSION.SDK_INT < 23 && tintTypedArray.hasValue(R.styleable.TextAppearance_android_textColor)) {
      ColorStateList colorStateList = tintTypedArray.getColorStateList(R.styleable.TextAppearance_android_textColor);
      if (colorStateList != null)
        this.mView.setTextColor(colorStateList); 
    } 
    if (tintTypedArray.hasValue(R.styleable.TextAppearance_android_textSize) && tintTypedArray.getDimensionPixelSize(R.styleable.TextAppearance_android_textSize, -1) == 0)
      this.mView.setTextSize(0, 0.0F); 
    updateTypefaceAndStyle(paramContext, tintTypedArray);
    tintTypedArray.recycle();
    if (this.mFontTypeface != null)
      this.mView.setTypeface(this.mFontTypeface, this.mStyle); 
  }
  
  void setAllCaps(boolean paramBoolean) {
    this.mView.setAllCaps(paramBoolean);
  }
  
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
  }
  
  void setAutoSizeTextTypeWithDefaults(int paramInt) {
    this.mAutoSizeTextHelper.setAutoSizeTextTypeWithDefaults(paramInt);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setTextSize(int paramInt, float paramFloat) {
    if (!AutoSizeableTextView.PLATFORM_SUPPORTS_AUTOSIZE && !isAutoSizeEnabled())
      setTextSizeInternal(paramInt, paramFloat); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatTextHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */