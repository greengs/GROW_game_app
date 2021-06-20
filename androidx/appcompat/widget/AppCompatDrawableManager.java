package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.appcompat.R;
import androidx.appcompat.graphics.drawable.AnimatedStateListDrawableCompat;
import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.collection.LruCache;
import androidx.collection.SparseArrayCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;
import androidx.vectordrawable.graphics.drawable.VectorDrawableCompat;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;
import org.xmlpull.v1.XmlPullParser;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class AppCompatDrawableManager {
  private static final int[] COLORFILTER_COLOR_BACKGROUND_MULTIPLY;
  
  private static final int[] COLORFILTER_COLOR_CONTROL_ACTIVATED;
  
  private static final int[] COLORFILTER_TINT_COLOR_CONTROL_NORMAL;
  
  private static final ColorFilterLruCache COLOR_FILTER_CACHE;
  
  private static final boolean DEBUG = false;
  
  private static final PorterDuff.Mode DEFAULT_MODE = PorterDuff.Mode.SRC_IN;
  
  private static AppCompatDrawableManager INSTANCE;
  
  private static final String PLATFORM_VD_CLAZZ = "android.graphics.drawable.VectorDrawable";
  
  private static final String SKIP_DRAWABLE_TAG = "appcompat_skip_skip";
  
  private static final String TAG = "AppCompatDrawableManag";
  
  private static final int[] TINT_CHECKABLE_BUTTON_LIST;
  
  private static final int[] TINT_COLOR_CONTROL_NORMAL;
  
  private static final int[] TINT_COLOR_CONTROL_STATE_LIST;
  
  private ArrayMap<String, InflateDelegate> mDelegates;
  
  private final WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>> mDrawableCaches = new WeakHashMap<Context, LongSparseArray<WeakReference<Drawable.ConstantState>>>(0);
  
  private boolean mHasCheckedVectorDrawableSetup;
  
  private SparseArrayCompat<String> mKnownDrawableIdTags;
  
  private WeakHashMap<Context, SparseArrayCompat<ColorStateList>> mTintLists;
  
  private TypedValue mTypedValue;
  
  static {
    COLOR_FILTER_CACHE = new ColorFilterLruCache(6);
    COLORFILTER_TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha };
    TINT_COLOR_CONTROL_NORMAL = new int[] { R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha };
    COLORFILTER_COLOR_CONTROL_ACTIVATED = new int[] { R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl_dark, R.drawable.abc_text_select_handle_middle_mtrl_dark, R.drawable.abc_text_select_handle_right_mtrl_dark, R.drawable.abc_text_select_handle_left_mtrl_light, R.drawable.abc_text_select_handle_middle_mtrl_light, R.drawable.abc_text_select_handle_right_mtrl_light };
    COLORFILTER_COLOR_BACKGROUND_MULTIPLY = new int[] { R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult };
    TINT_COLOR_CONTROL_STATE_LIST = new int[] { R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material };
    TINT_CHECKABLE_BUTTON_LIST = new int[] { R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material };
  }
  
  private void addDelegate(@NonNull String paramString, @NonNull InflateDelegate paramInflateDelegate) {
    if (this.mDelegates == null)
      this.mDelegates = new ArrayMap(); 
    this.mDelegates.put(paramString, paramInflateDelegate);
  }
  
  private boolean addDrawableToCache(@NonNull Context paramContext, long paramLong, @NonNull Drawable paramDrawable) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload #4
    //   4: invokevirtual getConstantState : ()Landroid/graphics/drawable/Drawable$ConstantState;
    //   7: astore #7
    //   9: aload #7
    //   11: ifnull -> 79
    //   14: aload_0
    //   15: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   18: aload_1
    //   19: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   22: checkcast androidx/collection/LongSparseArray
    //   25: astore #6
    //   27: aload #6
    //   29: astore #4
    //   31: aload #6
    //   33: ifnonnull -> 56
    //   36: new androidx/collection/LongSparseArray
    //   39: dup
    //   40: invokespecial <init> : ()V
    //   43: astore #4
    //   45: aload_0
    //   46: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   49: aload_1
    //   50: aload #4
    //   52: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   55: pop
    //   56: aload #4
    //   58: lload_2
    //   59: new java/lang/ref/WeakReference
    //   62: dup
    //   63: aload #7
    //   65: invokespecial <init> : (Ljava/lang/Object;)V
    //   68: invokevirtual put : (JLjava/lang/Object;)V
    //   71: iconst_1
    //   72: istore #5
    //   74: aload_0
    //   75: monitorexit
    //   76: iload #5
    //   78: ireturn
    //   79: iconst_0
    //   80: istore #5
    //   82: goto -> 74
    //   85: astore_1
    //   86: aload_0
    //   87: monitorexit
    //   88: aload_1
    //   89: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	85	finally
    //   14	27	85	finally
    //   36	56	85	finally
    //   56	71	85	finally
  }
  
  private void addTintListToCache(@NonNull Context paramContext, @DrawableRes int paramInt, @NonNull ColorStateList paramColorStateList) {
    if (this.mTintLists == null)
      this.mTintLists = new WeakHashMap<Context, SparseArrayCompat<ColorStateList>>(); 
    SparseArrayCompat<ColorStateList> sparseArrayCompat2 = this.mTintLists.get(paramContext);
    SparseArrayCompat<ColorStateList> sparseArrayCompat1 = sparseArrayCompat2;
    if (sparseArrayCompat2 == null) {
      sparseArrayCompat1 = new SparseArrayCompat();
      this.mTintLists.put(paramContext, sparseArrayCompat1);
    } 
    sparseArrayCompat1.append(paramInt, paramColorStateList);
  }
  
  private static boolean arrayContains(int[] paramArrayOfint, int paramInt) {
    boolean bool = false;
    int j = paramArrayOfint.length;
    for (int i = 0;; i++) {
      boolean bool1 = bool;
      if (i < j) {
        if (paramArrayOfint[i] == paramInt)
          return true; 
      } else {
        return bool1;
      } 
    } 
  }
  
  private void checkVectorDrawableSetup(@NonNull Context paramContext) {
    if (!this.mHasCheckedVectorDrawableSetup) {
      this.mHasCheckedVectorDrawableSetup = true;
      Drawable drawable = getDrawable(paramContext, R.drawable.abc_vector_test);
      if (drawable == null || !isVectorDrawable(drawable)) {
        this.mHasCheckedVectorDrawableSetup = false;
        throw new IllegalStateException("This app has been built with an incorrect configuration. Please configure your build for VectorDrawableCompat.");
      } 
    } 
  }
  
  private ColorStateList createBorderlessButtonColorStateList(@NonNull Context paramContext) {
    return createButtonColorStateList(paramContext, 0);
  }
  
  private ColorStateList createButtonColorStateList(@NonNull Context paramContext, @ColorInt int paramInt) {
    int[][] arrayOfInt = new int[4][];
    int[] arrayOfInt1 = new int[4];
    int i = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlHighlight);
    int j = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorButtonNormal);
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = j;
    j = 0 + 1;
    arrayOfInt[j] = ThemeUtils.PRESSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    arrayOfInt[++j] = ThemeUtils.FOCUSED_STATE_SET;
    arrayOfInt1[j] = ColorUtils.compositeColors(i, paramInt);
    i = j + 1;
    arrayOfInt[i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = paramInt;
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static long createCacheKey(TypedValue paramTypedValue) {
    return paramTypedValue.assetCookie << 32L | paramTypedValue.data;
  }
  
  private ColorStateList createColoredButtonColorStateList(@NonNull Context paramContext) {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorAccent));
  }
  
  private ColorStateList createDefaultButtonColorStateList(@NonNull Context paramContext) {
    return createButtonColorStateList(paramContext, ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorButtonNormal));
  }
  
  private Drawable createDrawableIfNeeded(@NonNull Context paramContext, @DrawableRes int paramInt) {
    LayerDrawable layerDrawable;
    if (this.mTypedValue == null)
      this.mTypedValue = new TypedValue(); 
    TypedValue typedValue = this.mTypedValue;
    paramContext.getResources().getValue(paramInt, typedValue, true);
    long l = createCacheKey(typedValue);
    Drawable drawable = getCachedDrawable(paramContext, l);
    if (drawable != null)
      return drawable; 
    if (paramInt == R.drawable.abc_cab_background_top_material)
      layerDrawable = new LayerDrawable(new Drawable[] { getDrawable(paramContext, R.drawable.abc_cab_background_internal_bg), getDrawable(paramContext, R.drawable.abc_cab_background_top_mtrl_alpha) }); 
    if (layerDrawable != null) {
      layerDrawable.setChangingConfigurations(typedValue.changingConfigurations);
      addDrawableToCache(paramContext, l, (Drawable)layerDrawable);
    } 
    return (Drawable)layerDrawable;
  }
  
  private ColorStateList createSwitchThumbColorStateList(Context paramContext) {
    int[][] arrayOfInt = new int[3][];
    int[] arrayOfInt1 = new int[3];
    ColorStateList colorStateList = ThemeUtils.getThemeAttrColorStateList(paramContext, R.attr.colorSwitchThumbNormal);
    if (colorStateList != null && colorStateList.isStateful()) {
      arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
      arrayOfInt1[0] = colorStateList.getColorForState(arrayOfInt[0], 0);
      int j = 0 + 1;
      arrayOfInt[j] = ThemeUtils.CHECKED_STATE_SET;
      arrayOfInt1[j] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
      arrayOfInt[++j] = ThemeUtils.EMPTY_STATE_SET;
      arrayOfInt1[j] = colorStateList.getDefaultColor();
      return new ColorStateList(arrayOfInt, arrayOfInt1);
    } 
    arrayOfInt[0] = ThemeUtils.DISABLED_STATE_SET;
    arrayOfInt1[0] = ThemeUtils.getDisabledThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    int i = 0 + 1;
    arrayOfInt[i] = ThemeUtils.CHECKED_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorControlActivated);
    arrayOfInt[++i] = ThemeUtils.EMPTY_STATE_SET;
    arrayOfInt1[i] = ThemeUtils.getThemeAttrColor(paramContext, R.attr.colorSwitchThumbNormal);
    return new ColorStateList(arrayOfInt, arrayOfInt1);
  }
  
  private static PorterDuffColorFilter createTintFilter(ColorStateList paramColorStateList, PorterDuff.Mode paramMode, int[] paramArrayOfint) {
    return (paramColorStateList == null || paramMode == null) ? null : getPorterDuffColorFilter(paramColorStateList.getColorForState(paramArrayOfint, 0), paramMode);
  }
  
  public static AppCompatDrawableManager get() {
    // Byte code:
    //   0: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   2: monitorenter
    //   3: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   6: ifnonnull -> 25
    //   9: new androidx/appcompat/widget/AppCompatDrawableManager
    //   12: dup
    //   13: invokespecial <init> : ()V
    //   16: putstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   19: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   22: invokestatic installDefaultInflateDelegates : (Landroidx/appcompat/widget/AppCompatDrawableManager;)V
    //   25: getstatic androidx/appcompat/widget/AppCompatDrawableManager.INSTANCE : Landroidx/appcompat/widget/AppCompatDrawableManager;
    //   28: astore_0
    //   29: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   31: monitorexit
    //   32: aload_0
    //   33: areturn
    //   34: astore_0
    //   35: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   37: monitorexit
    //   38: aload_0
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   3	25	34	finally
    //   25	29	34	finally
  }
  
  private Drawable getCachedDrawable(@NonNull Context paramContext, long paramLong) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #5
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   9: aload_1
    //   10: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   13: checkcast androidx/collection/LongSparseArray
    //   16: astore #6
    //   18: aload #6
    //   20: ifnonnull -> 32
    //   23: aload #5
    //   25: astore #4
    //   27: aload_0
    //   28: monitorexit
    //   29: aload #4
    //   31: areturn
    //   32: aload #6
    //   34: lload_2
    //   35: invokevirtual get : (J)Ljava/lang/Object;
    //   38: checkcast java/lang/ref/WeakReference
    //   41: astore #7
    //   43: aload #5
    //   45: astore #4
    //   47: aload #7
    //   49: ifnull -> 27
    //   52: aload #7
    //   54: invokevirtual get : ()Ljava/lang/Object;
    //   57: checkcast android/graphics/drawable/Drawable$ConstantState
    //   60: astore #4
    //   62: aload #4
    //   64: ifnull -> 81
    //   67: aload #4
    //   69: aload_1
    //   70: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   73: invokevirtual newDrawable : (Landroid/content/res/Resources;)Landroid/graphics/drawable/Drawable;
    //   76: astore #4
    //   78: goto -> 27
    //   81: aload #6
    //   83: lload_2
    //   84: invokevirtual delete : (J)V
    //   87: aload #5
    //   89: astore #4
    //   91: goto -> 27
    //   94: astore_1
    //   95: aload_0
    //   96: monitorexit
    //   97: aload_1
    //   98: athrow
    // Exception table:
    //   from	to	target	type
    //   5	18	94	finally
    //   32	43	94	finally
    //   52	62	94	finally
    //   67	78	94	finally
    //   81	87	94	finally
  }
  
  public static PorterDuffColorFilter getPorterDuffColorFilter(int paramInt, PorterDuff.Mode paramMode) {
    // Byte code:
    //   0: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   2: monitorenter
    //   3: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLOR_FILTER_CACHE : Landroidx/appcompat/widget/AppCompatDrawableManager$ColorFilterLruCache;
    //   6: iload_0
    //   7: aload_1
    //   8: invokevirtual get : (ILandroid/graphics/PorterDuff$Mode;)Landroid/graphics/PorterDuffColorFilter;
    //   11: astore_3
    //   12: aload_3
    //   13: astore_2
    //   14: aload_3
    //   15: ifnonnull -> 38
    //   18: new android/graphics/PorterDuffColorFilter
    //   21: dup
    //   22: iload_0
    //   23: aload_1
    //   24: invokespecial <init> : (ILandroid/graphics/PorterDuff$Mode;)V
    //   27: astore_2
    //   28: getstatic androidx/appcompat/widget/AppCompatDrawableManager.COLOR_FILTER_CACHE : Landroidx/appcompat/widget/AppCompatDrawableManager$ColorFilterLruCache;
    //   31: iload_0
    //   32: aload_1
    //   33: aload_2
    //   34: invokevirtual put : (ILandroid/graphics/PorterDuff$Mode;Landroid/graphics/PorterDuffColorFilter;)Landroid/graphics/PorterDuffColorFilter;
    //   37: pop
    //   38: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   40: monitorexit
    //   41: aload_2
    //   42: areturn
    //   43: astore_1
    //   44: ldc androidx/appcompat/widget/AppCompatDrawableManager
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   3	12	43	finally
    //   18	38	43	finally
  }
  
  private ColorStateList getTintListFromCache(@NonNull Context paramContext, @DrawableRes int paramInt) {
    ColorStateList colorStateList2 = null;
    ColorStateList colorStateList1 = colorStateList2;
    if (this.mTintLists != null) {
      SparseArrayCompat sparseArrayCompat = this.mTintLists.get(paramContext);
      colorStateList1 = colorStateList2;
      if (sparseArrayCompat != null)
        colorStateList1 = (ColorStateList)sparseArrayCompat.get(paramInt); 
    } 
    return colorStateList1;
  }
  
  static PorterDuff.Mode getTintMode(int paramInt) {
    PorterDuff.Mode mode = null;
    if (paramInt == R.drawable.abc_switch_thumb_material)
      mode = PorterDuff.Mode.MULTIPLY; 
    return mode;
  }
  
  private static void installDefaultInflateDelegates(@NonNull AppCompatDrawableManager paramAppCompatDrawableManager) {
    if (Build.VERSION.SDK_INT < 24) {
      paramAppCompatDrawableManager.addDelegate("vector", new VdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-vector", new AvdcInflateDelegate());
      paramAppCompatDrawableManager.addDelegate("animated-selector", new AsldcInflateDelegate());
    } 
  }
  
  private static boolean isVectorDrawable(@NonNull Drawable paramDrawable) {
    return (paramDrawable instanceof VectorDrawableCompat || "android.graphics.drawable.VectorDrawable".equals(paramDrawable.getClass().getName()));
  }
  
  private Drawable loadDrawableFromDelegates(@NonNull Context paramContext, @DrawableRes int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: getfield mDelegates : Landroidx/collection/ArrayMap;
    //   4: ifnull -> 408
    //   7: aload_0
    //   8: getfield mDelegates : Landroidx/collection/ArrayMap;
    //   11: invokevirtual isEmpty : ()Z
    //   14: ifne -> 408
    //   17: aload_0
    //   18: getfield mKnownDrawableIdTags : Landroidx/collection/SparseArrayCompat;
    //   21: ifnull -> 70
    //   24: aload_0
    //   25: getfield mKnownDrawableIdTags : Landroidx/collection/SparseArrayCompat;
    //   28: iload_2
    //   29: invokevirtual get : (I)Ljava/lang/Object;
    //   32: checkcast java/lang/String
    //   35: astore #7
    //   37: ldc 'appcompat_skip_skip'
    //   39: aload #7
    //   41: invokevirtual equals : (Ljava/lang/Object;)Z
    //   44: ifne -> 64
    //   47: aload #7
    //   49: ifnull -> 81
    //   52: aload_0
    //   53: getfield mDelegates : Landroidx/collection/ArrayMap;
    //   56: aload #7
    //   58: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   61: ifnonnull -> 81
    //   64: aconst_null
    //   65: astore #8
    //   67: aload #8
    //   69: areturn
    //   70: aload_0
    //   71: new androidx/collection/SparseArrayCompat
    //   74: dup
    //   75: invokespecial <init> : ()V
    //   78: putfield mKnownDrawableIdTags : Landroidx/collection/SparseArrayCompat;
    //   81: aload_0
    //   82: getfield mTypedValue : Landroid/util/TypedValue;
    //   85: ifnonnull -> 99
    //   88: aload_0
    //   89: new android/util/TypedValue
    //   92: dup
    //   93: invokespecial <init> : ()V
    //   96: putfield mTypedValue : Landroid/util/TypedValue;
    //   99: aload_0
    //   100: getfield mTypedValue : Landroid/util/TypedValue;
    //   103: astore #10
    //   105: aload_1
    //   106: invokevirtual getResources : ()Landroid/content/res/Resources;
    //   109: astore #11
    //   111: aload #11
    //   113: iload_2
    //   114: aload #10
    //   116: iconst_1
    //   117: invokevirtual getValue : (ILandroid/util/TypedValue;Z)V
    //   120: aload #10
    //   122: invokestatic createCacheKey : (Landroid/util/TypedValue;)J
    //   125: lstore #4
    //   127: aload_0
    //   128: aload_1
    //   129: lload #4
    //   131: invokespecial getCachedDrawable : (Landroid/content/Context;J)Landroid/graphics/drawable/Drawable;
    //   134: astore #7
    //   136: aload #7
    //   138: astore #8
    //   140: aload #7
    //   142: ifnonnull -> 67
    //   145: aload #7
    //   147: astore #9
    //   149: aload #10
    //   151: getfield string : Ljava/lang/CharSequence;
    //   154: ifnull -> 256
    //   157: aload #7
    //   159: astore #9
    //   161: aload #10
    //   163: getfield string : Ljava/lang/CharSequence;
    //   166: invokeinterface toString : ()Ljava/lang/String;
    //   171: ldc_w '.xml'
    //   174: invokevirtual endsWith : (Ljava/lang/String;)Z
    //   177: ifeq -> 256
    //   180: aload #7
    //   182: astore #9
    //   184: aload #11
    //   186: iload_2
    //   187: invokevirtual getXml : (I)Landroid/content/res/XmlResourceParser;
    //   190: astore #11
    //   192: aload #7
    //   194: astore #9
    //   196: aload #11
    //   198: invokestatic asAttributeSet : (Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   201: astore #12
    //   203: aload #7
    //   205: astore #9
    //   207: aload #11
    //   209: invokeinterface next : ()I
    //   214: istore_3
    //   215: iload_3
    //   216: iconst_2
    //   217: if_icmpeq -> 225
    //   220: iload_3
    //   221: iconst_1
    //   222: if_icmpne -> 203
    //   225: iload_3
    //   226: iconst_2
    //   227: if_icmpeq -> 278
    //   230: aload #7
    //   232: astore #9
    //   234: new org/xmlpull/v1/XmlPullParserException
    //   237: dup
    //   238: ldc_w 'No start tag found'
    //   241: invokespecial <init> : (Ljava/lang/String;)V
    //   244: athrow
    //   245: astore_1
    //   246: ldc 'AppCompatDrawableManag'
    //   248: ldc_w 'Exception while inflating drawable'
    //   251: aload_1
    //   252: invokestatic e : (Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   255: pop
    //   256: aload #9
    //   258: astore #8
    //   260: aload #9
    //   262: ifnonnull -> 67
    //   265: aload_0
    //   266: getfield mKnownDrawableIdTags : Landroidx/collection/SparseArrayCompat;
    //   269: iload_2
    //   270: ldc 'appcompat_skip_skip'
    //   272: invokevirtual append : (ILjava/lang/Object;)V
    //   275: aload #9
    //   277: areturn
    //   278: aload #7
    //   280: astore #9
    //   282: aload #11
    //   284: invokeinterface getName : ()Ljava/lang/String;
    //   289: astore #8
    //   291: aload #7
    //   293: astore #9
    //   295: aload_0
    //   296: getfield mKnownDrawableIdTags : Landroidx/collection/SparseArrayCompat;
    //   299: iload_2
    //   300: aload #8
    //   302: invokevirtual append : (ILjava/lang/Object;)V
    //   305: aload #7
    //   307: astore #9
    //   309: aload_0
    //   310: getfield mDelegates : Landroidx/collection/ArrayMap;
    //   313: aload #8
    //   315: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   318: checkcast androidx/appcompat/widget/AppCompatDrawableManager$InflateDelegate
    //   321: astore #13
    //   323: aload #7
    //   325: astore #8
    //   327: aload #13
    //   329: ifnull -> 354
    //   332: aload #7
    //   334: astore #9
    //   336: aload #13
    //   338: aload_1
    //   339: aload #11
    //   341: aload #12
    //   343: aload_1
    //   344: invokevirtual getTheme : ()Landroid/content/res/Resources$Theme;
    //   347: invokeinterface createFromXmlInner : (Landroid/content/Context;Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/content/res/Resources$Theme;)Landroid/graphics/drawable/Drawable;
    //   352: astore #8
    //   354: aload #8
    //   356: astore #9
    //   358: aload #8
    //   360: ifnull -> 256
    //   363: aload #8
    //   365: astore #9
    //   367: aload #8
    //   369: aload #10
    //   371: getfield changingConfigurations : I
    //   374: invokevirtual setChangingConfigurations : (I)V
    //   377: aload #8
    //   379: astore #9
    //   381: aload_0
    //   382: aload_1
    //   383: lload #4
    //   385: aload #8
    //   387: invokespecial addDrawableToCache : (Landroid/content/Context;JLandroid/graphics/drawable/Drawable;)Z
    //   390: istore #6
    //   392: aload #8
    //   394: astore #9
    //   396: iload #6
    //   398: ifeq -> 256
    //   401: aload #8
    //   403: astore #9
    //   405: goto -> 256
    //   408: aconst_null
    //   409: areturn
    // Exception table:
    //   from	to	target	type
    //   184	192	245	java/lang/Exception
    //   196	203	245	java/lang/Exception
    //   207	215	245	java/lang/Exception
    //   234	245	245	java/lang/Exception
    //   282	291	245	java/lang/Exception
    //   295	305	245	java/lang/Exception
    //   309	323	245	java/lang/Exception
    //   336	354	245	java/lang/Exception
    //   367	377	245	java/lang/Exception
    //   381	392	245	java/lang/Exception
  }
  
  private void removeDelegate(@NonNull String paramString, @NonNull InflateDelegate paramInflateDelegate) {
    if (this.mDelegates != null && this.mDelegates.get(paramString) == paramInflateDelegate)
      this.mDelegates.remove(paramString); 
  }
  
  private static void setPorterDuffColorFilter(Drawable paramDrawable, int paramInt, PorterDuff.Mode paramMode) {
    Drawable drawable = paramDrawable;
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable))
      drawable = paramDrawable.mutate(); 
    PorterDuff.Mode mode = paramMode;
    if (paramMode == null)
      mode = DEFAULT_MODE; 
    drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(paramInt, mode));
  }
  
  private Drawable tintDrawable(@NonNull Context paramContext, @DrawableRes int paramInt, boolean paramBoolean, @NonNull Drawable paramDrawable) {
    Drawable drawable;
    PorterDuff.Mode mode1;
    ColorStateList colorStateList = getTintList(paramContext, paramInt);
    if (colorStateList != null) {
      drawable = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable))
        drawable = paramDrawable.mutate(); 
      drawable = DrawableCompat.wrap(drawable);
      DrawableCompat.setTintList(drawable, colorStateList);
      mode1 = getTintMode(paramInt);
      Drawable drawable1 = drawable;
      if (mode1 != null) {
        DrawableCompat.setTintMode(drawable, mode1);
        drawable1 = drawable;
      } 
      return drawable1;
    } 
    if (paramInt == R.drawable.abc_seekbar_track_material) {
      LayerDrawable layerDrawable = (LayerDrawable)mode1;
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
      return (Drawable)mode1;
    } 
    if (paramInt == R.drawable.abc_ratingbar_material || paramInt == R.drawable.abc_ratingbar_indicator_material || paramInt == R.drawable.abc_ratingbar_small_material) {
      LayerDrawable layerDrawable = (LayerDrawable)mode1;
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908288), ThemeUtils.getDisabledThemeAttrColor((Context)drawable, R.attr.colorControlNormal), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908303), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
      setPorterDuffColorFilter(layerDrawable.findDrawableByLayerId(16908301), ThemeUtils.getThemeAttrColor((Context)drawable, R.attr.colorControlActivated), DEFAULT_MODE);
      return (Drawable)mode1;
    } 
    PorterDuff.Mode mode2 = mode1;
    if (!tintDrawableUsingColorFilter((Context)drawable, paramInt, (Drawable)mode1)) {
      mode2 = mode1;
      if (paramBoolean)
        return null; 
    } 
    return (Drawable)mode2;
  }
  
  static void tintDrawable(Drawable paramDrawable, TintInfo paramTintInfo, int[] paramArrayOfint) {
    if (DrawableUtils.canSafelyMutateDrawable(paramDrawable) && paramDrawable.mutate() != paramDrawable) {
      Log.d("AppCompatDrawableManag", "Mutated drawable is not the same instance as the input.");
      return;
    } 
    if (paramTintInfo.mHasTintList || paramTintInfo.mHasTintMode) {
      PorterDuff.Mode mode;
      ColorStateList colorStateList;
      if (paramTintInfo.mHasTintList) {
        colorStateList = paramTintInfo.mTintList;
      } else {
        colorStateList = null;
      } 
      if (paramTintInfo.mHasTintMode) {
        mode = paramTintInfo.mTintMode;
      } else {
        mode = DEFAULT_MODE;
      } 
      paramDrawable.setColorFilter((ColorFilter)createTintFilter(colorStateList, mode, paramArrayOfint));
    } else {
      paramDrawable.clearColorFilter();
    } 
    if (Build.VERSION.SDK_INT <= 23) {
      paramDrawable.invalidateSelf();
      return;
    } 
  }
  
  static boolean tintDrawableUsingColorFilter(@NonNull Context paramContext, @DrawableRes int paramInt, @NonNull Drawable paramDrawable) {
    byte b1;
    PorterDuff.Mode mode1;
    PorterDuff.Mode mode2 = DEFAULT_MODE;
    boolean bool = false;
    int i = 0;
    byte b2 = -1;
    if (arrayContains(COLORFILTER_TINT_COLOR_CONTROL_NORMAL, paramInt)) {
      i = R.attr.colorControlNormal;
      bool = true;
      mode1 = mode2;
      b1 = b2;
    } else if (arrayContains(COLORFILTER_COLOR_CONTROL_ACTIVATED, paramInt)) {
      i = R.attr.colorControlActivated;
      bool = true;
      b1 = b2;
      mode1 = mode2;
    } else if (arrayContains(COLORFILTER_COLOR_BACKGROUND_MULTIPLY, paramInt)) {
      i = 16842801;
      bool = true;
      mode1 = PorterDuff.Mode.MULTIPLY;
      b1 = b2;
    } else if (paramInt == R.drawable.abc_list_divider_mtrl_alpha) {
      i = 16842800;
      bool = true;
      b1 = Math.round(40.8F);
      mode1 = mode2;
    } else {
      b1 = b2;
      mode1 = mode2;
      if (paramInt == R.drawable.abc_dialog_material_background) {
        i = 16842801;
        bool = true;
        b1 = b2;
        mode1 = mode2;
      } 
    } 
    if (bool) {
      Drawable drawable = paramDrawable;
      if (DrawableUtils.canSafelyMutateDrawable(paramDrawable))
        drawable = paramDrawable.mutate(); 
      drawable.setColorFilter((ColorFilter)getPorterDuffColorFilter(ThemeUtils.getThemeAttrColor(paramContext, i), mode1));
      if (b1 != -1)
        drawable.setAlpha(b1); 
      return true;
    } 
    return false;
  }
  
  public Drawable getDrawable(@NonNull Context paramContext, @DrawableRes int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: iconst_0
    //   6: invokevirtual getDrawable : (Landroid/content/Context;IZ)Landroid/graphics/drawable/Drawable;
    //   9: astore_1
    //   10: aload_0
    //   11: monitorexit
    //   12: aload_1
    //   13: areturn
    //   14: astore_1
    //   15: aload_0
    //   16: monitorexit
    //   17: aload_1
    //   18: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	14	finally
  }
  
  Drawable getDrawable(@NonNull Context paramContext, @DrawableRes int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokespecial checkVectorDrawableSetup : (Landroid/content/Context;)V
    //   7: aload_0
    //   8: aload_1
    //   9: iload_2
    //   10: invokespecial loadDrawableFromDelegates : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   13: astore #5
    //   15: aload #5
    //   17: astore #4
    //   19: aload #5
    //   21: ifnonnull -> 32
    //   24: aload_0
    //   25: aload_1
    //   26: iload_2
    //   27: invokespecial createDrawableIfNeeded : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   30: astore #4
    //   32: aload #4
    //   34: astore #5
    //   36: aload #4
    //   38: ifnonnull -> 48
    //   41: aload_1
    //   42: iload_2
    //   43: invokestatic getDrawable : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   46: astore #5
    //   48: aload #5
    //   50: astore #4
    //   52: aload #5
    //   54: ifnull -> 68
    //   57: aload_0
    //   58: aload_1
    //   59: iload_2
    //   60: iload_3
    //   61: aload #5
    //   63: invokespecial tintDrawable : (Landroid/content/Context;IZLandroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    //   66: astore #4
    //   68: aload #4
    //   70: ifnull -> 78
    //   73: aload #4
    //   75: invokestatic fixDrawable : (Landroid/graphics/drawable/Drawable;)V
    //   78: aload_0
    //   79: monitorexit
    //   80: aload #4
    //   82: areturn
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	83	finally
    //   24	32	83	finally
    //   41	48	83	finally
    //   57	68	83	finally
    //   73	78	83	finally
  }
  
  ColorStateList getTintList(@NonNull Context paramContext, @DrawableRes int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_2
    //   5: invokespecial getTintListFromCache : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   8: astore_3
    //   9: aload_3
    //   10: astore #4
    //   12: aload_3
    //   13: ifnonnull -> 48
    //   16: iload_2
    //   17: getstatic androidx/appcompat/R$drawable.abc_edit_text_material : I
    //   20: if_icmpne -> 53
    //   23: aload_1
    //   24: getstatic androidx/appcompat/R$color.abc_tint_edittext : I
    //   27: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   30: astore_3
    //   31: aload_3
    //   32: astore #4
    //   34: aload_3
    //   35: ifnull -> 48
    //   38: aload_0
    //   39: aload_1
    //   40: iload_2
    //   41: aload_3
    //   42: invokespecial addTintListToCache : (Landroid/content/Context;ILandroid/content/res/ColorStateList;)V
    //   45: aload_3
    //   46: astore #4
    //   48: aload_0
    //   49: monitorexit
    //   50: aload #4
    //   52: areturn
    //   53: iload_2
    //   54: getstatic androidx/appcompat/R$drawable.abc_switch_track_mtrl_alpha : I
    //   57: if_icmpne -> 71
    //   60: aload_1
    //   61: getstatic androidx/appcompat/R$color.abc_tint_switch_track : I
    //   64: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   67: astore_3
    //   68: goto -> 31
    //   71: iload_2
    //   72: getstatic androidx/appcompat/R$drawable.abc_switch_thumb_material : I
    //   75: if_icmpne -> 87
    //   78: aload_0
    //   79: aload_1
    //   80: invokespecial createSwitchThumbColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   83: astore_3
    //   84: goto -> 31
    //   87: iload_2
    //   88: getstatic androidx/appcompat/R$drawable.abc_btn_default_mtrl_shape : I
    //   91: if_icmpne -> 103
    //   94: aload_0
    //   95: aload_1
    //   96: invokespecial createDefaultButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   99: astore_3
    //   100: goto -> 31
    //   103: iload_2
    //   104: getstatic androidx/appcompat/R$drawable.abc_btn_borderless_material : I
    //   107: if_icmpne -> 119
    //   110: aload_0
    //   111: aload_1
    //   112: invokespecial createBorderlessButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   115: astore_3
    //   116: goto -> 31
    //   119: iload_2
    //   120: getstatic androidx/appcompat/R$drawable.abc_btn_colored_material : I
    //   123: if_icmpne -> 135
    //   126: aload_0
    //   127: aload_1
    //   128: invokespecial createColoredButtonColorStateList : (Landroid/content/Context;)Landroid/content/res/ColorStateList;
    //   131: astore_3
    //   132: goto -> 31
    //   135: iload_2
    //   136: getstatic androidx/appcompat/R$drawable.abc_spinner_mtrl_am_alpha : I
    //   139: if_icmpeq -> 149
    //   142: iload_2
    //   143: getstatic androidx/appcompat/R$drawable.abc_spinner_textfield_background_material : I
    //   146: if_icmpne -> 160
    //   149: aload_1
    //   150: getstatic androidx/appcompat/R$color.abc_tint_spinner : I
    //   153: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   156: astore_3
    //   157: goto -> 31
    //   160: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_COLOR_CONTROL_NORMAL : [I
    //   163: iload_2
    //   164: invokestatic arrayContains : ([II)Z
    //   167: ifeq -> 181
    //   170: aload_1
    //   171: getstatic androidx/appcompat/R$attr.colorControlNormal : I
    //   174: invokestatic getThemeAttrColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   177: astore_3
    //   178: goto -> 31
    //   181: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_COLOR_CONTROL_STATE_LIST : [I
    //   184: iload_2
    //   185: invokestatic arrayContains : ([II)Z
    //   188: ifeq -> 202
    //   191: aload_1
    //   192: getstatic androidx/appcompat/R$color.abc_tint_default : I
    //   195: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   198: astore_3
    //   199: goto -> 31
    //   202: getstatic androidx/appcompat/widget/AppCompatDrawableManager.TINT_CHECKABLE_BUTTON_LIST : [I
    //   205: iload_2
    //   206: invokestatic arrayContains : ([II)Z
    //   209: ifeq -> 223
    //   212: aload_1
    //   213: getstatic androidx/appcompat/R$color.abc_tint_btn_checkable : I
    //   216: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   219: astore_3
    //   220: goto -> 31
    //   223: iload_2
    //   224: getstatic androidx/appcompat/R$drawable.abc_seekbar_thumb_material : I
    //   227: if_icmpne -> 31
    //   230: aload_1
    //   231: getstatic androidx/appcompat/R$color.abc_tint_seek_thumb : I
    //   234: invokestatic getColorStateList : (Landroid/content/Context;I)Landroid/content/res/ColorStateList;
    //   237: astore_3
    //   238: goto -> 31
    //   241: astore_1
    //   242: aload_0
    //   243: monitorexit
    //   244: aload_1
    //   245: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	241	finally
    //   16	31	241	finally
    //   38	45	241	finally
    //   53	68	241	finally
    //   71	84	241	finally
    //   87	100	241	finally
    //   103	116	241	finally
    //   119	132	241	finally
    //   135	149	241	finally
    //   149	157	241	finally
    //   160	178	241	finally
    //   181	199	241	finally
    //   202	220	241	finally
    //   223	238	241	finally
  }
  
  public void onConfigurationChanged(@NonNull Context paramContext) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mDrawableCaches : Ljava/util/WeakHashMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: checkcast androidx/collection/LongSparseArray
    //   13: astore_1
    //   14: aload_1
    //   15: ifnull -> 22
    //   18: aload_1
    //   19: invokevirtual clear : ()V
    //   22: aload_0
    //   23: monitorexit
    //   24: return
    //   25: astore_1
    //   26: aload_0
    //   27: monitorexit
    //   28: aload_1
    //   29: athrow
    // Exception table:
    //   from	to	target	type
    //   2	14	25	finally
    //   18	22	25	finally
  }
  
  Drawable onDrawableLoadedFromResources(@NonNull Context paramContext, @NonNull VectorEnabledTintResources paramVectorEnabledTintResources, @DrawableRes int paramInt) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: iload_3
    //   5: invokespecial loadDrawableFromDelegates : (Landroid/content/Context;I)Landroid/graphics/drawable/Drawable;
    //   8: astore #5
    //   10: aload #5
    //   12: astore #4
    //   14: aload #5
    //   16: ifnonnull -> 26
    //   19: aload_2
    //   20: iload_3
    //   21: invokevirtual superGetDrawable : (I)Landroid/graphics/drawable/Drawable;
    //   24: astore #4
    //   26: aload #4
    //   28: ifnull -> 45
    //   31: aload_0
    //   32: aload_1
    //   33: iload_3
    //   34: iconst_0
    //   35: aload #4
    //   37: invokespecial tintDrawable : (Landroid/content/Context;IZLandroid/graphics/drawable/Drawable;)Landroid/graphics/drawable/Drawable;
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: areturn
    //   45: aconst_null
    //   46: astore_1
    //   47: goto -> 41
    //   50: astore_1
    //   51: aload_0
    //   52: monitorexit
    //   53: aload_1
    //   54: athrow
    // Exception table:
    //   from	to	target	type
    //   2	10	50	finally
    //   19	26	50	finally
    //   31	41	50	finally
  }
  
  @RequiresApi(11)
  static class AsldcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(@NonNull Context param1Context, @NonNull XmlPullParser param1XmlPullParser, @NonNull AttributeSet param1AttributeSet, @Nullable Resources.Theme param1Theme) {
      try {
        return (Drawable)AnimatedStateListDrawableCompat.createFromXmlInner(param1Context, param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        Log.e("AsldcInflateDelegate", "Exception while inflating <animated-selector>", exception);
        return null;
      } 
    }
  }
  
  private static class AvdcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(@NonNull Context param1Context, @NonNull XmlPullParser param1XmlPullParser, @NonNull AttributeSet param1AttributeSet, @Nullable Resources.Theme param1Theme) {
      try {
        return (Drawable)AnimatedVectorDrawableCompat.createFromXmlInner(param1Context, param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        Log.e("AvdcInflateDelegate", "Exception while inflating <animated-vector>", exception);
        return null;
      } 
    }
  }
  
  private static class ColorFilterLruCache extends LruCache<Integer, PorterDuffColorFilter> {
    public ColorFilterLruCache(int param1Int) {
      super(param1Int);
    }
    
    private static int generateCacheKey(int param1Int, PorterDuff.Mode param1Mode) {
      return (param1Int + 31) * 31 + param1Mode.hashCode();
    }
    
    PorterDuffColorFilter get(int param1Int, PorterDuff.Mode param1Mode) {
      return (PorterDuffColorFilter)get(Integer.valueOf(generateCacheKey(param1Int, param1Mode)));
    }
    
    PorterDuffColorFilter put(int param1Int, PorterDuff.Mode param1Mode, PorterDuffColorFilter param1PorterDuffColorFilter) {
      return (PorterDuffColorFilter)put(Integer.valueOf(generateCacheKey(param1Int, param1Mode)), param1PorterDuffColorFilter);
    }
  }
  
  private static interface InflateDelegate {
    Drawable createFromXmlInner(@NonNull Context param1Context, @NonNull XmlPullParser param1XmlPullParser, @NonNull AttributeSet param1AttributeSet, @Nullable Resources.Theme param1Theme);
  }
  
  private static class VdcInflateDelegate implements InflateDelegate {
    public Drawable createFromXmlInner(@NonNull Context param1Context, @NonNull XmlPullParser param1XmlPullParser, @NonNull AttributeSet param1AttributeSet, @Nullable Resources.Theme param1Theme) {
      try {
        return (Drawable)VectorDrawableCompat.createFromXmlInner(param1Context.getResources(), param1XmlPullParser, param1AttributeSet, param1Theme);
      } catch (Exception exception) {
        Log.e("VdcInflateDelegate", "Exception while inflating <vector>", exception);
        return null;
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/widget/AppCompatDrawableManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */