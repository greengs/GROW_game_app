package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

class AppCompatTextViewAutoSizeHelper {
  private static final int DEFAULT_AUTO_SIZE_GRANULARITY_IN_PX = 1;
  
  private static final int DEFAULT_AUTO_SIZE_MAX_TEXT_SIZE_IN_SP = 112;
  
  private static final int DEFAULT_AUTO_SIZE_MIN_TEXT_SIZE_IN_SP = 12;
  
  private static final String TAG = "ACTVAutoSizeHelper";
  
  private static final RectF TEMP_RECTF = new RectF();
  
  static final float UNSET_AUTO_SIZE_UNIFORM_CONFIGURATION_VALUE = -1.0F;
  
  private static final int VERY_WIDE = 1048576;
  
  private static ConcurrentHashMap<String, Method> sTextViewMethodByNameCache = new ConcurrentHashMap<String, Method>();
  
  private float mAutoSizeMaxTextSizeInPx = -1.0F;
  
  private float mAutoSizeMinTextSizeInPx = -1.0F;
  
  private float mAutoSizeStepGranularityInPx = -1.0F;
  
  private int[] mAutoSizeTextSizesInPx = new int[0];
  
  private int mAutoSizeTextType = 0;
  
  private final Context mContext;
  
  private boolean mHasPresetAutoSizeValues = false;
  
  private boolean mNeedsAutoSizeText = false;
  
  private TextPaint mTempTextPaint;
  
  private final TextView mTextView;
  
  AppCompatTextViewAutoSizeHelper(TextView paramTextView) {
    this.mTextView = paramTextView;
    this.mContext = this.mTextView.getContext();
  }
  
  private int[] cleanupAutoSizePresetSizes(int[] paramArrayOfint) {
    int i = paramArrayOfint.length;
    if (i != 0) {
      Arrays.sort(paramArrayOfint);
      ArrayList<? extends Comparable<? super Integer>> arrayList = new ArrayList();
      int j;
      for (j = 0; j < i; j++) {
        int k = paramArrayOfint[j];
        if (k > 0 && Collections.binarySearch(arrayList, Integer.valueOf(k)) < 0)
          arrayList.add(Integer.valueOf(k)); 
      } 
      if (i != arrayList.size()) {
        i = arrayList.size();
        paramArrayOfint = new int[i];
        for (j = 0; j < i; j++)
          paramArrayOfint[j] = ((Integer)arrayList.get(j)).intValue(); 
        return paramArrayOfint;
      } 
    } 
    return paramArrayOfint;
  }
  
  private void clearAutoSizeConfiguration() {
    this.mAutoSizeTextType = 0;
    this.mAutoSizeMinTextSizeInPx = -1.0F;
    this.mAutoSizeMaxTextSizeInPx = -1.0F;
    this.mAutoSizeStepGranularityInPx = -1.0F;
    this.mAutoSizeTextSizesInPx = new int[0];
    this.mNeedsAutoSizeText = false;
  }
  
  @RequiresApi(23)
  private StaticLayout createStaticLayoutForMeasuring(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt1, int paramInt2) {
    TextDirectionHeuristic textDirectionHeuristic = invokeAndReturnWithDefault(this.mTextView, "getTextDirectionHeuristic", TextDirectionHeuristics.FIRSTSTRONG_LTR);
    StaticLayout.Builder builder = StaticLayout.Builder.obtain(paramCharSequence, 0, paramCharSequence.length(), this.mTempTextPaint, paramInt1).setAlignment(paramAlignment).setLineSpacing(this.mTextView.getLineSpacingExtra(), this.mTextView.getLineSpacingMultiplier()).setIncludePad(this.mTextView.getIncludeFontPadding()).setBreakStrategy(this.mTextView.getBreakStrategy()).setHyphenationFrequency(this.mTextView.getHyphenationFrequency());
    paramInt1 = paramInt2;
    if (paramInt2 == -1)
      paramInt1 = Integer.MAX_VALUE; 
    return builder.setMaxLines(paramInt1).setTextDirection(textDirectionHeuristic).build();
  }
  
  private StaticLayout createStaticLayoutForMeasuringPre23(CharSequence paramCharSequence, Layout.Alignment paramAlignment, int paramInt) {
    if (Build.VERSION.SDK_INT >= 16) {
      float f3 = this.mTextView.getLineSpacingMultiplier();
      float f4 = this.mTextView.getLineSpacingExtra();
      boolean bool1 = this.mTextView.getIncludeFontPadding();
      return new StaticLayout(paramCharSequence, this.mTempTextPaint, paramInt, paramAlignment, f3, f4, bool1);
    } 
    float f1 = ((Float)invokeAndReturnWithDefault(this.mTextView, "getLineSpacingMultiplier", Float.valueOf(1.0F))).floatValue();
    float f2 = ((Float)invokeAndReturnWithDefault(this.mTextView, "getLineSpacingExtra", Float.valueOf(0.0F))).floatValue();
    boolean bool = ((Boolean)invokeAndReturnWithDefault(this.mTextView, "getIncludeFontPadding", Boolean.valueOf(true))).booleanValue();
    return new StaticLayout(paramCharSequence, this.mTempTextPaint, paramInt, paramAlignment, f1, f2, bool);
  }
  
  private int findLargestTextSizeWhichFits(RectF paramRectF) {
    int k = this.mAutoSizeTextSizesInPx.length;
    if (k == 0)
      throw new IllegalStateException("No available text sizes to choose from."); 
    int j = 0;
    int i = 0 + 1;
    while (i <= --k) {
      j = (i + k) / 2;
      if (suggestedSizeFitsInSpace(this.mAutoSizeTextSizesInPx[j], paramRectF)) {
        int m = j + 1;
        j = i;
        i = m;
        continue;
      } 
      k = j - 1;
      j = k;
    } 
    return this.mAutoSizeTextSizesInPx[j];
  }
  
  @Nullable
  private Method getTextViewMethod(@NonNull String paramString) {
    try {
      Method method2 = sTextViewMethodByNameCache.get(paramString);
      Method method1 = method2;
      if (method2 == null) {
        method2 = TextView.class.getDeclaredMethod(paramString, new Class[0]);
        method1 = method2;
        if (method2 != null) {
          method2.setAccessible(true);
          sTextViewMethodByNameCache.put(paramString, method2);
          method1 = method2;
        } 
      } 
      return method1;
    } catch (Exception exception) {
      Log.w("ACTVAutoSizeHelper", "Failed to retrieve TextView#" + paramString + "() method", exception);
      return null;
    } 
  }
  
  private <T> T invokeAndReturnWithDefault(@NonNull Object paramObject, @NonNull String paramString, @NonNull T paramT) {
    Object object;
    Exception exception = null;
    boolean bool = false;
    try {
      paramObject = getTextViewMethod(paramString).invoke(paramObject, new Object[0]);
      object = paramObject;
      paramObject = object;
      return (T)paramObject;
    } catch (Exception exception1) {
      bool = true;
      Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#" + object + "() method", exception1);
      exception1 = exception;
      return (T)exception1;
    } finally {
      if (false || bool);
    } 
  }
  
  private void setRawTextSize(float paramFloat) {
    if (paramFloat != this.mTextView.getPaint().getTextSize()) {
      this.mTextView.getPaint().setTextSize(paramFloat);
      boolean bool = false;
      if (Build.VERSION.SDK_INT >= 18)
        bool = this.mTextView.isInLayout(); 
      if (this.mTextView.getLayout() != null) {
        this.mNeedsAutoSizeText = false;
        try {
          Method method = getTextViewMethod("nullLayouts");
          if (method != null)
            method.invoke(this.mTextView, new Object[0]); 
        } catch (Exception exception) {
          Log.w("ACTVAutoSizeHelper", "Failed to invoke TextView#nullLayouts() method", exception);
        } 
        if (!bool) {
          this.mTextView.requestLayout();
        } else {
          this.mTextView.forceLayout();
        } 
        this.mTextView.invalidate();
      } 
    } 
  }
  
  private boolean setupAutoSizeText() {
    if (supportsAutoSizeText() && this.mAutoSizeTextType == 1) {
      if (!this.mHasPresetAutoSizeValues || this.mAutoSizeTextSizesInPx.length == 0) {
        int i = 1;
        float f;
        for (f = Math.round(this.mAutoSizeMinTextSizeInPx); Math.round(this.mAutoSizeStepGranularityInPx + f) <= Math.round(this.mAutoSizeMaxTextSizeInPx); f += this.mAutoSizeStepGranularityInPx)
          i++; 
        int[] arrayOfInt = new int[i];
        f = this.mAutoSizeMinTextSizeInPx;
        for (int j = 0; j < i; j++) {
          arrayOfInt[j] = Math.round(f);
          f += this.mAutoSizeStepGranularityInPx;
        } 
        this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      } 
      this.mNeedsAutoSizeText = true;
      return this.mNeedsAutoSizeText;
    } 
    this.mNeedsAutoSizeText = false;
    return this.mNeedsAutoSizeText;
  }
  
  private void setupAutoSizeUniformPresetSizes(TypedArray paramTypedArray) {
    int i = paramTypedArray.length();
    int[] arrayOfInt = new int[i];
    if (i > 0) {
      for (int j = 0; j < i; j++)
        arrayOfInt[j] = paramTypedArray.getDimensionPixelSize(j, -1); 
      this.mAutoSizeTextSizesInPx = cleanupAutoSizePresetSizes(arrayOfInt);
      setupAutoSizeUniformPresetSizesConfiguration();
    } 
  }
  
  private boolean setupAutoSizeUniformPresetSizesConfiguration() {
    boolean bool;
    int i = this.mAutoSizeTextSizesInPx.length;
    if (i > 0) {
      bool = true;
    } else {
      bool = false;
    } 
    this.mHasPresetAutoSizeValues = bool;
    if (this.mHasPresetAutoSizeValues) {
      this.mAutoSizeTextType = 1;
      this.mAutoSizeMinTextSizeInPx = this.mAutoSizeTextSizesInPx[0];
      this.mAutoSizeMaxTextSizeInPx = this.mAutoSizeTextSizesInPx[i - 1];
      this.mAutoSizeStepGranularityInPx = -1.0F;
    } 
    return this.mHasPresetAutoSizeValues;
  }
  
  private boolean suggestedSizeFitsInSpace(int paramInt, RectF paramRectF) {
    byte b;
    StaticLayout staticLayout;
    CharSequence charSequence2 = this.mTextView.getText();
    TransformationMethod transformationMethod = this.mTextView.getTransformationMethod();
    CharSequence charSequence1 = charSequence2;
    if (transformationMethod != null) {
      CharSequence charSequence = transformationMethod.getTransformation(charSequence2, (View)this.mTextView);
      charSequence1 = charSequence2;
      if (charSequence != null)
        charSequence1 = charSequence; 
    } 
    if (Build.VERSION.SDK_INT >= 16) {
      b = this.mTextView.getMaxLines();
    } else {
      b = -1;
    } 
    if (this.mTempTextPaint == null) {
      this.mTempTextPaint = new TextPaint();
    } else {
      this.mTempTextPaint.reset();
    } 
    this.mTempTextPaint.set(this.mTextView.getPaint());
    this.mTempTextPaint.setTextSize(paramInt);
    Layout.Alignment alignment = invokeAndReturnWithDefault(this.mTextView, "getLayoutAlignment", Layout.Alignment.ALIGN_NORMAL);
    if (Build.VERSION.SDK_INT >= 23) {
      staticLayout = createStaticLayoutForMeasuring(charSequence1, alignment, Math.round(paramRectF.right), b);
    } else {
      staticLayout = createStaticLayoutForMeasuringPre23(charSequence1, (Layout.Alignment)staticLayout, Math.round(paramRectF.right));
    } 
    return (b != -1 && (staticLayout.getLineCount() > b || staticLayout.getLineEnd(staticLayout.getLineCount() - 1) != charSequence1.length())) ? false : (!(staticLayout.getHeight() > paramRectF.bottom));
  }
  
  private boolean supportsAutoSizeText() {
    return !(this.mTextView instanceof AppCompatEditText);
  }
  
  private void validateAndSetAutoSizeTextTypeUniformConfiguration(float paramFloat1, float paramFloat2, float paramFloat3) throws IllegalArgumentException {
    if (paramFloat1 <= 0.0F)
      throw new IllegalArgumentException("Minimum auto-size text size (" + paramFloat1 + "px) is less or equal to (0px)"); 
    if (paramFloat2 <= paramFloat1)
      throw new IllegalArgumentException("Maximum auto-size text size (" + paramFloat2 + "px) is less or equal to minimum auto-size " + "text size (" + paramFloat1 + "px)"); 
    if (paramFloat3 <= 0.0F)
      throw new IllegalArgumentException("The auto-size step granularity (" + paramFloat3 + "px) is less or equal to (0px)"); 
    this.mAutoSizeTextType = 1;
    this.mAutoSizeMinTextSizeInPx = paramFloat1;
    this.mAutoSizeMaxTextSizeInPx = paramFloat2;
    this.mAutoSizeStepGranularityInPx = paramFloat3;
    this.mHasPresetAutoSizeValues = false;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void autoSizeText() {
    if (!isAutoSizeEnabled())
      return; 
    if (this.mNeedsAutoSizeText) {
      if (this.mTextView.getMeasuredHeight() > 0 && this.mTextView.getMeasuredWidth() > 0) {
        int i;
        if (((Boolean)invokeAndReturnWithDefault(this.mTextView, "getHorizontallyScrolling", Boolean.valueOf(false))).booleanValue()) {
          i = 1048576;
        } else {
          i = this.mTextView.getMeasuredWidth() - this.mTextView.getTotalPaddingLeft() - this.mTextView.getTotalPaddingRight();
        } 
        int j = this.mTextView.getHeight() - this.mTextView.getCompoundPaddingBottom() - this.mTextView.getCompoundPaddingTop();
        if (i > 0 && j > 0)
          synchronized (TEMP_RECTF) {
            TEMP_RECTF.setEmpty();
            TEMP_RECTF.right = i;
            TEMP_RECTF.bottom = j;
            float f = findLargestTextSizeWhichFits(TEMP_RECTF);
            if (f != this.mTextView.getTextSize())
              setTextSizeInternal(0, f); 
            this.mNeedsAutoSizeText = true;
            return;
          }  
      } 
      return;
    } 
    this.mNeedsAutoSizeText = true;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeMaxTextSize() {
    return Math.round(this.mAutoSizeMaxTextSizeInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeMinTextSize() {
    return Math.round(this.mAutoSizeMinTextSizeInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeStepGranularity() {
    return Math.round(this.mAutoSizeStepGranularityInPx);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int[] getAutoSizeTextAvailableSizes() {
    return this.mAutoSizeTextSizesInPx;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  int getAutoSizeTextType() {
    return this.mAutoSizeTextType;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  boolean isAutoSizeEnabled() {
    return (supportsAutoSizeText() && this.mAutoSizeTextType != 0);
  }
  
  void loadFromAttributes(AttributeSet paramAttributeSet, int paramInt) {
    float f2 = -1.0F;
    float f3 = -1.0F;
    float f1 = -1.0F;
    TypedArray typedArray = this.mContext.obtainStyledAttributes(paramAttributeSet, R.styleable.AppCompatTextView, paramInt, 0);
    if (typedArray.hasValue(R.styleable.AppCompatTextView_autoSizeTextType))
      this.mAutoSizeTextType = typedArray.getInt(R.styleable.AppCompatTextView_autoSizeTextType, 0); 
    if (typedArray.hasValue(R.styleable.AppCompatTextView_autoSizeStepGranularity))
      f1 = typedArray.getDimension(R.styleable.AppCompatTextView_autoSizeStepGranularity, -1.0F); 
    if (typedArray.hasValue(R.styleable.AppCompatTextView_autoSizeMinTextSize))
      f2 = typedArray.getDimension(R.styleable.AppCompatTextView_autoSizeMinTextSize, -1.0F); 
    if (typedArray.hasValue(R.styleable.AppCompatTextView_autoSizeMaxTextSize))
      f3 = typedArray.getDimension(R.styleable.AppCompatTextView_autoSizeMaxTextSize, -1.0F); 
    if (typedArray.hasValue(R.styleable.AppCompatTextView_autoSizePresetSizes)) {
      paramInt = typedArray.getResourceId(R.styleable.AppCompatTextView_autoSizePresetSizes, 0);
      if (paramInt > 0) {
        TypedArray typedArray1 = typedArray.getResources().obtainTypedArray(paramInt);
        setupAutoSizeUniformPresetSizes(typedArray1);
        typedArray1.recycle();
      } 
    } 
    typedArray.recycle();
    if (supportsAutoSizeText()) {
      if (this.mAutoSizeTextType == 1) {
        if (!this.mHasPresetAutoSizeValues) {
          DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
          float f = f2;
          if (f2 == -1.0F)
            f = TypedValue.applyDimension(2, 12.0F, displayMetrics); 
          f2 = f3;
          if (f3 == -1.0F)
            f2 = TypedValue.applyDimension(2, 112.0F, displayMetrics); 
          f3 = f1;
          if (f1 == -1.0F)
            f3 = 1.0F; 
          validateAndSetAutoSizeTextTypeUniformConfiguration(f, f2, f3);
        } 
        setupAutoSizeText();
      } 
      return;
    } 
    this.mAutoSizeTextType = 0;
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeUniformWithConfiguration(int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    if (supportsAutoSizeText()) {
      DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
      validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(paramInt4, paramInt1, displayMetrics), TypedValue.applyDimension(paramInt4, paramInt2, displayMetrics), TypedValue.applyDimension(paramInt4, paramInt3, displayMetrics));
      if (setupAutoSizeText())
        autoSizeText(); 
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    // Byte code:
    //   0: aload_0
    //   1: invokespecial supportsAutoSizeText : ()Z
    //   4: ifeq -> 146
    //   7: aload_1
    //   8: arraylength
    //   9: istore #4
    //   11: iload #4
    //   13: ifle -> 130
    //   16: iload #4
    //   18: newarray int
    //   20: astore #6
    //   22: iload_2
    //   23: ifne -> 82
    //   26: aload_1
    //   27: iload #4
    //   29: invokestatic copyOf : ([II)[I
    //   32: astore #5
    //   34: aload_0
    //   35: aload_0
    //   36: aload #5
    //   38: invokespecial cleanupAutoSizePresetSizes : ([I)[I
    //   41: putfield mAutoSizeTextSizesInPx : [I
    //   44: aload_0
    //   45: invokespecial setupAutoSizeUniformPresetSizesConfiguration : ()Z
    //   48: ifne -> 135
    //   51: new java/lang/IllegalArgumentException
    //   54: dup
    //   55: new java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial <init> : ()V
    //   62: ldc_w 'None of the preset sizes is valid: '
    //   65: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   68: aload_1
    //   69: invokestatic toString : ([I)Ljava/lang/String;
    //   72: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: invokevirtual toString : ()Ljava/lang/String;
    //   78: invokespecial <init> : (Ljava/lang/String;)V
    //   81: athrow
    //   82: aload_0
    //   83: getfield mContext : Landroid/content/Context;
    //   86: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   89: invokevirtual getDisplayMetrics : ()Landroid/util/DisplayMetrics;
    //   92: astore #7
    //   94: iconst_0
    //   95: istore_3
    //   96: aload #6
    //   98: astore #5
    //   100: iload_3
    //   101: iload #4
    //   103: if_icmpge -> 34
    //   106: aload #6
    //   108: iload_3
    //   109: iload_2
    //   110: aload_1
    //   111: iload_3
    //   112: iaload
    //   113: i2f
    //   114: aload #7
    //   116: invokestatic applyDimension : (IFLandroid/util/DisplayMetrics;)F
    //   119: invokestatic round : (F)I
    //   122: iastore
    //   123: iload_3
    //   124: iconst_1
    //   125: iadd
    //   126: istore_3
    //   127: goto -> 96
    //   130: aload_0
    //   131: iconst_0
    //   132: putfield mHasPresetAutoSizeValues : Z
    //   135: aload_0
    //   136: invokespecial setupAutoSizeText : ()Z
    //   139: ifeq -> 146
    //   142: aload_0
    //   143: invokevirtual autoSizeText : ()V
    //   146: return
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setAutoSizeTextTypeWithDefaults(int paramInt) {
    if (supportsAutoSizeText()) {
      switch (paramInt) {
        default:
          throw new IllegalArgumentException("Unknown auto-size text type: " + paramInt);
        case 0:
          clearAutoSizeConfiguration();
          return;
        case 1:
          break;
      } 
    } else {
      return;
    } 
    DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
    validateAndSetAutoSizeTextTypeUniformConfiguration(TypedValue.applyDimension(2, 12.0F, displayMetrics), TypedValue.applyDimension(2, 112.0F, displayMetrics), 1.0F);
    if (setupAutoSizeText()) {
      autoSizeText();
      return;
    } 
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  void setTextSizeInternal(int paramInt, float paramFloat) {
    Resources resources;
    if (this.mContext == null) {
      resources = Resources.getSystem();
    } else {
      resources = this.mContext.getResources();
    } 
    setRawTextSize(TypedValue.applyDimension(paramInt, paramFloat, resources.getDisplayMetrics()));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatTextViewAutoSizeHelper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */