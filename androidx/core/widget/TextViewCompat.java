package androidx.core.widget;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.text.TextDirectionHeuristic;
import android.text.TextDirectionHeuristics;
import android.text.TextPaint;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.core.text.PrecomputedTextCompat;
import androidx.core.util.Preconditions;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class TextViewCompat {
  public static final int AUTO_SIZE_TEXT_TYPE_NONE = 0;
  
  public static final int AUTO_SIZE_TEXT_TYPE_UNIFORM = 1;
  
  private static final int LINES = 1;
  
  private static final String LOG_TAG = "TextViewCompat";
  
  private static Field sMaxModeField;
  
  private static boolean sMaxModeFieldFetched;
  
  private static Field sMaximumField;
  
  private static boolean sMaximumFieldFetched;
  
  private static Field sMinModeField;
  
  private static boolean sMinModeFieldFetched;
  
  private static Field sMinimumField;
  
  private static boolean sMinimumFieldFetched;
  
  public static int getAutoSizeMaxTextSize(@NonNull TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 27) ? paramTextView.getAutoSizeMaxTextSize() : ((paramTextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)paramTextView).getAutoSizeMaxTextSize() : -1);
  }
  
  public static int getAutoSizeMinTextSize(@NonNull TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 27) ? paramTextView.getAutoSizeMinTextSize() : ((paramTextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)paramTextView).getAutoSizeMinTextSize() : -1);
  }
  
  public static int getAutoSizeStepGranularity(@NonNull TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 27) ? paramTextView.getAutoSizeStepGranularity() : ((paramTextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)paramTextView).getAutoSizeStepGranularity() : -1);
  }
  
  @NonNull
  public static int[] getAutoSizeTextAvailableSizes(@NonNull TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 27) ? paramTextView.getAutoSizeTextAvailableSizes() : ((paramTextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)paramTextView).getAutoSizeTextAvailableSizes() : new int[0]);
  }
  
  public static int getAutoSizeTextType(@NonNull TextView paramTextView) {
    return (Build.VERSION.SDK_INT >= 27) ? paramTextView.getAutoSizeTextType() : ((paramTextView instanceof AutoSizeableTextView) ? ((AutoSizeableTextView)paramTextView).getAutoSizeTextType() : 0);
  }
  
  @NonNull
  public static Drawable[] getCompoundDrawablesRelative(@NonNull TextView paramTextView) {
    Drawable drawable;
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 18)
      return paramTextView.getCompoundDrawablesRelative(); 
    if (Build.VERSION.SDK_INT >= 17) {
      if (paramTextView.getLayoutDirection() != 1)
        bool = false; 
      Drawable[] arrayOfDrawable2 = paramTextView.getCompoundDrawables();
      Drawable[] arrayOfDrawable1 = arrayOfDrawable2;
      if (bool) {
        drawable = arrayOfDrawable2[2];
        Drawable drawable1 = arrayOfDrawable2[0];
        arrayOfDrawable2[0] = drawable;
        arrayOfDrawable2[2] = drawable1;
        return arrayOfDrawable2;
      } 
      return (Drawable[])drawable;
    } 
    return drawable.getCompoundDrawables();
  }
  
  public static int getFirstBaselineToTopHeight(@NonNull TextView paramTextView) {
    return paramTextView.getPaddingTop() - (paramTextView.getPaint().getFontMetricsInt()).top;
  }
  
  public static int getLastBaselineToBottomHeight(@NonNull TextView paramTextView) {
    return paramTextView.getPaddingBottom() + (paramTextView.getPaint().getFontMetricsInt()).bottom;
  }
  
  public static int getMaxLines(@NonNull TextView paramTextView) {
    if (Build.VERSION.SDK_INT >= 16)
      return paramTextView.getMaxLines(); 
    if (!sMaxModeFieldFetched) {
      sMaxModeField = retrieveField("mMaxMode");
      sMaxModeFieldFetched = true;
    } 
    if (sMaxModeField != null && retrieveIntFromField(sMaxModeField, paramTextView) == 1) {
      if (!sMaximumFieldFetched) {
        sMaximumField = retrieveField("mMaximum");
        sMaximumFieldFetched = true;
      } 
      if (sMaximumField != null)
        return retrieveIntFromField(sMaximumField, paramTextView); 
    } 
    return -1;
  }
  
  public static int getMinLines(@NonNull TextView paramTextView) {
    if (Build.VERSION.SDK_INT >= 16)
      return paramTextView.getMinLines(); 
    if (!sMinModeFieldFetched) {
      sMinModeField = retrieveField("mMinMode");
      sMinModeFieldFetched = true;
    } 
    if (sMinModeField != null && retrieveIntFromField(sMinModeField, paramTextView) == 1) {
      if (!sMinimumFieldFetched) {
        sMinimumField = retrieveField("mMinimum");
        sMinimumFieldFetched = true;
      } 
      if (sMinimumField != null)
        return retrieveIntFromField(sMinimumField, paramTextView); 
    } 
    return -1;
  }
  
  @RequiresApi(18)
  private static int getTextDirection(@NonNull TextDirectionHeuristic paramTextDirectionHeuristic) {
    if (paramTextDirectionHeuristic != TextDirectionHeuristics.FIRSTSTRONG_RTL && paramTextDirectionHeuristic != TextDirectionHeuristics.FIRSTSTRONG_LTR) {
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.ANYRTL_LTR)
        return 2; 
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.LTR)
        return 3; 
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.RTL)
        return 4; 
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.LOCALE)
        return 5; 
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_LTR)
        return 6; 
      if (paramTextDirectionHeuristic == TextDirectionHeuristics.FIRSTSTRONG_RTL)
        return 7; 
    } 
    return 1;
  }
  
  @RequiresApi(18)
  private static TextDirectionHeuristic getTextDirectionHeuristic(@NonNull TextView paramTextView) {
    byte b = 1;
    if (paramTextView.getTransformationMethod() instanceof android.text.method.PasswordTransformationMethod)
      return TextDirectionHeuristics.LTR; 
    if (Build.VERSION.SDK_INT >= 28 && (paramTextView.getInputType() & 0xF) == 3) {
      b = Character.getDirectionality(DecimalFormatSymbols.getInstance(paramTextView.getTextLocale()).getDigitStrings()[0].codePointAt(0));
      return (b == 1 || b == 2) ? TextDirectionHeuristics.RTL : TextDirectionHeuristics.LTR;
    } 
    if (paramTextView.getLayoutDirection() != 1)
      b = 0; 
    switch (paramTextView.getTextDirection()) {
      default:
        return (b != 0) ? TextDirectionHeuristics.FIRSTSTRONG_RTL : TextDirectionHeuristics.FIRSTSTRONG_LTR;
      case 2:
        return TextDirectionHeuristics.ANYRTL_LTR;
      case 3:
        return TextDirectionHeuristics.LTR;
      case 4:
        return TextDirectionHeuristics.RTL;
      case 5:
        return TextDirectionHeuristics.LOCALE;
      case 6:
        return TextDirectionHeuristics.FIRSTSTRONG_LTR;
      case 7:
        break;
    } 
    return TextDirectionHeuristics.FIRSTSTRONG_RTL;
  }
  
  @NonNull
  public static PrecomputedTextCompat.Params getTextMetricsParams(@NonNull TextView paramTextView) {
    if (Build.VERSION.SDK_INT >= 28)
      return new PrecomputedTextCompat.Params(paramTextView.getTextMetricsParams()); 
    PrecomputedTextCompat.Params.Builder builder = new PrecomputedTextCompat.Params.Builder(new TextPaint((Paint)paramTextView.getPaint()));
    if (Build.VERSION.SDK_INT >= 23) {
      builder.setBreakStrategy(paramTextView.getBreakStrategy());
      builder.setHyphenationFrequency(paramTextView.getHyphenationFrequency());
    } 
    if (Build.VERSION.SDK_INT >= 18)
      builder.setTextDirection(getTextDirectionHeuristic(paramTextView)); 
    return builder.build();
  }
  
  private static Field retrieveField(String paramString) {
    Field field = null;
    try {
      Field field1 = TextView.class.getDeclaredField(paramString);
      field = field1;
      field1.setAccessible(true);
      return field1;
    } catch (NoSuchFieldException noSuchFieldException) {
      Log.e("TextViewCompat", "Could not retrieve " + paramString + " field.");
      return field;
    } 
  }
  
  private static int retrieveIntFromField(Field paramField, TextView paramTextView) {
    try {
      return paramField.getInt(paramTextView);
    } catch (IllegalAccessException illegalAccessException) {
      Log.d("TextViewCompat", "Could not retrieve value of " + paramField.getName() + " field.");
      return -1;
    } 
  }
  
  public static void setAutoSizeTextTypeUniformWithConfiguration(@NonNull TextView paramTextView, int paramInt1, int paramInt2, int paramInt3, int paramInt4) throws IllegalArgumentException {
    if (Build.VERSION.SDK_INT >= 27) {
      paramTextView.setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    if (paramTextView instanceof AutoSizeableTextView) {
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithConfiguration(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
  }
  
  public static void setAutoSizeTextTypeUniformWithPresetSizes(@NonNull TextView paramTextView, @NonNull int[] paramArrayOfint, int paramInt) throws IllegalArgumentException {
    if (Build.VERSION.SDK_INT >= 27) {
      paramTextView.setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
      return;
    } 
    if (paramTextView instanceof AutoSizeableTextView) {
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeUniformWithPresetSizes(paramArrayOfint, paramInt);
      return;
    } 
  }
  
  public static void setAutoSizeTextTypeWithDefaults(@NonNull TextView paramTextView, int paramInt) {
    if (Build.VERSION.SDK_INT >= 27) {
      paramTextView.setAutoSizeTextTypeWithDefaults(paramInt);
      return;
    } 
    if (paramTextView instanceof AutoSizeableTextView) {
      ((AutoSizeableTextView)paramTextView).setAutoSizeTextTypeWithDefaults(paramInt);
      return;
    } 
  }
  
  public static void setCompoundDrawablesRelative(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 18) {
      paramTextView.setCompoundDrawablesRelative(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 17) {
      Drawable drawable;
      if (paramTextView.getLayoutDirection() != 1)
        bool = false; 
      if (bool) {
        drawable = paramDrawable3;
      } else {
        drawable = paramDrawable1;
      } 
      if (!bool)
        paramDrawable1 = paramDrawable3; 
      paramTextView.setCompoundDrawables(drawable, paramDrawable2, paramDrawable1, paramDrawable4);
      return;
    } 
    paramTextView.setCompoundDrawables(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @DrawableRes int paramInt1, @DrawableRes int paramInt2, @DrawableRes int paramInt3, @DrawableRes int paramInt4) {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 18) {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 17) {
      int i;
      if (paramTextView.getLayoutDirection() != 1)
        bool = false; 
      if (bool) {
        i = paramInt3;
      } else {
        i = paramInt1;
      } 
      if (!bool)
        paramInt1 = paramInt3; 
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(i, paramInt2, paramInt1, paramInt4);
      return;
    } 
    paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  public static void setCompoundDrawablesRelativeWithIntrinsicBounds(@NonNull TextView paramTextView, @Nullable Drawable paramDrawable1, @Nullable Drawable paramDrawable2, @Nullable Drawable paramDrawable3, @Nullable Drawable paramDrawable4) {
    boolean bool = true;
    if (Build.VERSION.SDK_INT >= 18) {
      paramTextView.setCompoundDrawablesRelativeWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
      return;
    } 
    if (Build.VERSION.SDK_INT >= 17) {
      Drawable drawable;
      if (paramTextView.getLayoutDirection() != 1)
        bool = false; 
      if (bool) {
        drawable = paramDrawable3;
      } else {
        drawable = paramDrawable1;
      } 
      if (!bool)
        paramDrawable1 = paramDrawable3; 
      paramTextView.setCompoundDrawablesWithIntrinsicBounds(drawable, paramDrawable2, paramDrawable1, paramDrawable4);
      return;
    } 
    paramTextView.setCompoundDrawablesWithIntrinsicBounds(paramDrawable1, paramDrawable2, paramDrawable3, paramDrawable4);
  }
  
  public static void setCustomSelectionActionModeCallback(@NonNull TextView paramTextView, @NonNull ActionMode.Callback paramCallback) {
    paramTextView.setCustomSelectionActionModeCallback(wrapCustomSelectionActionModeCallback(paramTextView, paramCallback));
  }
  
  public static void setFirstBaselineToTopHeight(@NonNull TextView paramTextView, @IntRange(from = 0L) @Px int paramInt) {
    int i;
    Preconditions.checkArgumentNonnegative(paramInt);
    if (Build.VERSION.SDK_INT >= 28) {
      paramTextView.setFirstBaselineToTopHeight(paramInt);
      return;
    } 
    Paint.FontMetricsInt fontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    if (Build.VERSION.SDK_INT < 16 || paramTextView.getIncludeFontPadding()) {
      i = fontMetricsInt.top;
    } else {
      i = fontMetricsInt.ascent;
    } 
    if (paramInt > Math.abs(i)) {
      i = -i;
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramInt - i, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
      return;
    } 
  }
  
  public static void setLastBaselineToBottomHeight(@NonNull TextView paramTextView, @IntRange(from = 0L) @Px int paramInt) {
    int i;
    Preconditions.checkArgumentNonnegative(paramInt);
    Paint.FontMetricsInt fontMetricsInt = paramTextView.getPaint().getFontMetricsInt();
    if (Build.VERSION.SDK_INT < 16 || paramTextView.getIncludeFontPadding()) {
      i = fontMetricsInt.bottom;
    } else {
      i = fontMetricsInt.descent;
    } 
    if (paramInt > Math.abs(i))
      paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramInt - i); 
  }
  
  public static void setLineHeight(@NonNull TextView paramTextView, @IntRange(from = 0L) @Px int paramInt) {
    Preconditions.checkArgumentNonnegative(paramInt);
    int i = paramTextView.getPaint().getFontMetricsInt(null);
    if (paramInt != i)
      paramTextView.setLineSpacing((paramInt - i), 1.0F); 
  }
  
  public static void setPrecomputedText(@NonNull TextView paramTextView, @NonNull PrecomputedTextCompat paramPrecomputedTextCompat) {
    if (Build.VERSION.SDK_INT >= 28) {
      paramTextView.setText((CharSequence)paramPrecomputedTextCompat.getPrecomputedText());
      return;
    } 
    if (!getTextMetricsParams(paramTextView).equals(paramPrecomputedTextCompat.getParams()))
      throw new IllegalArgumentException("Given text can not be applied to TextView."); 
    paramTextView.setText((CharSequence)paramPrecomputedTextCompat);
  }
  
  public static void setTextAppearance(@NonNull TextView paramTextView, @StyleRes int paramInt) {
    if (Build.VERSION.SDK_INT >= 23) {
      paramTextView.setTextAppearance(paramInt);
      return;
    } 
    paramTextView.setTextAppearance(paramTextView.getContext(), paramInt);
  }
  
  public static void setTextMetricsParams(@NonNull TextView paramTextView, @NonNull PrecomputedTextCompat.Params paramParams) {
    if (Build.VERSION.SDK_INT >= 18)
      paramTextView.setTextDirection(getTextDirection(paramParams.getTextDirection())); 
    if (Build.VERSION.SDK_INT < 23) {
      float f = paramParams.getTextPaint().getTextScaleX();
      paramTextView.getPaint().set(paramParams.getTextPaint());
      if (f == paramTextView.getTextScaleX())
        paramTextView.setTextScaleX(f / 2.0F + 1.0F); 
      paramTextView.setTextScaleX(f);
      return;
    } 
    paramTextView.getPaint().set(paramParams.getTextPaint());
    paramTextView.setBreakStrategy(paramParams.getBreakStrategy());
    paramTextView.setHyphenationFrequency(paramParams.getHyphenationFrequency());
  }
  
  @NonNull
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static ActionMode.Callback wrapCustomSelectionActionModeCallback(@NonNull TextView paramTextView, @NonNull ActionMode.Callback paramCallback) {
    return (Build.VERSION.SDK_INT < 26 || Build.VERSION.SDK_INT > 27 || paramCallback instanceof OreoCallback) ? paramCallback : new OreoCallback(paramCallback, paramTextView);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static @interface AutoSizeTextType {}
  
  @RequiresApi(26)
  private static class OreoCallback implements ActionMode.Callback {
    private static final int MENU_ITEM_ORDER_PROCESS_TEXT_INTENT_ACTIONS_START = 100;
    
    private final ActionMode.Callback mCallback;
    
    private boolean mCanUseMenuBuilderReferences;
    
    private boolean mInitializedMenuBuilderReferences;
    
    private Class mMenuBuilderClass;
    
    private Method mMenuBuilderRemoveItemAtMethod;
    
    private final TextView mTextView;
    
    OreoCallback(ActionMode.Callback param1Callback, TextView param1TextView) {
      this.mCallback = param1Callback;
      this.mTextView = param1TextView;
      this.mInitializedMenuBuilderReferences = false;
    }
    
    private Intent createProcessTextIntent() {
      return (new Intent()).setAction("android.intent.action.PROCESS_TEXT").setType("text/plain");
    }
    
    private Intent createProcessTextIntentForResolveInfo(ResolveInfo param1ResolveInfo, TextView param1TextView) {
      Intent intent = createProcessTextIntent();
      if (!isEditable(param1TextView)) {
        boolean bool1 = true;
        return intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", bool1).setClassName(param1ResolveInfo.activityInfo.packageName, param1ResolveInfo.activityInfo.name);
      } 
      boolean bool = false;
      return intent.putExtra("android.intent.extra.PROCESS_TEXT_READONLY", bool).setClassName(param1ResolveInfo.activityInfo.packageName, param1ResolveInfo.activityInfo.name);
    }
    
    private List<ResolveInfo> getSupportedActivities(Context param1Context, PackageManager param1PackageManager) {
      ArrayList<ResolveInfo> arrayList = new ArrayList();
      if (param1Context instanceof android.app.Activity) {
        Iterator<ResolveInfo> iterator = param1PackageManager.queryIntentActivities(createProcessTextIntent(), 0).iterator();
        while (true) {
          if (iterator.hasNext()) {
            ResolveInfo resolveInfo = iterator.next();
            if (isSupportedActivity(resolveInfo, param1Context))
              arrayList.add(resolveInfo); 
            continue;
          } 
          return arrayList;
        } 
      } 
      return arrayList;
    }
    
    private boolean isEditable(TextView param1TextView) {
      return (param1TextView instanceof android.text.Editable && param1TextView.onCheckIsTextEditor() && param1TextView.isEnabled());
    }
    
    private boolean isSupportedActivity(ResolveInfo param1ResolveInfo, Context param1Context) {
      boolean bool2 = false;
      if (param1Context.getPackageName().equals(param1ResolveInfo.activityInfo.packageName))
        return true; 
      boolean bool1 = bool2;
      if (param1ResolveInfo.activityInfo.exported) {
        if (param1ResolveInfo.activityInfo.permission != null) {
          bool1 = bool2;
          return (param1Context.checkSelfPermission(param1ResolveInfo.activityInfo.permission) == 0) ? true : bool1;
        } 
        return true;
      } 
      return bool1;
    }
    
    private void recomputeProcessTextMenuItems(Menu param1Menu) {
      // Byte code:
      //   0: aload_0
      //   1: getfield mTextView : Landroid/widget/TextView;
      //   4: invokevirtual getContext : ()Landroid/content/Context;
      //   7: astore #5
      //   9: aload #5
      //   11: invokevirtual getPackageManager : ()Landroid/content/pm/PackageManager;
      //   14: astore #4
      //   16: aload_0
      //   17: getfield mInitializedMenuBuilderReferences : Z
      //   20: ifne -> 65
      //   23: aload_0
      //   24: iconst_1
      //   25: putfield mInitializedMenuBuilderReferences : Z
      //   28: aload_0
      //   29: ldc 'com.android.internal.view.menu.MenuBuilder'
      //   31: invokestatic forName : (Ljava/lang/String;)Ljava/lang/Class;
      //   34: putfield mMenuBuilderClass : Ljava/lang/Class;
      //   37: aload_0
      //   38: aload_0
      //   39: getfield mMenuBuilderClass : Ljava/lang/Class;
      //   42: ldc 'removeItemAt'
      //   44: iconst_1
      //   45: anewarray java/lang/Class
      //   48: dup
      //   49: iconst_0
      //   50: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
      //   53: aastore
      //   54: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   57: putfield mMenuBuilderRemoveItemAtMethod : Ljava/lang/reflect/Method;
      //   60: aload_0
      //   61: iconst_1
      //   62: putfield mCanUseMenuBuilderReferences : Z
      //   65: aload_0
      //   66: getfield mCanUseMenuBuilderReferences : Z
      //   69: ifeq -> 181
      //   72: aload_0
      //   73: getfield mMenuBuilderClass : Ljava/lang/Class;
      //   76: aload_1
      //   77: invokevirtual isInstance : (Ljava/lang/Object;)Z
      //   80: ifeq -> 181
      //   83: aload_0
      //   84: getfield mMenuBuilderRemoveItemAtMethod : Ljava/lang/reflect/Method;
      //   87: astore_3
      //   88: aload_1
      //   89: invokeinterface size : ()I
      //   94: iconst_1
      //   95: isub
      //   96: istore_2
      //   97: iload_2
      //   98: iflt -> 206
      //   101: aload_1
      //   102: iload_2
      //   103: invokeinterface getItem : (I)Landroid/view/MenuItem;
      //   108: astore #6
      //   110: aload #6
      //   112: invokeinterface getIntent : ()Landroid/content/Intent;
      //   117: ifnull -> 155
      //   120: ldc 'android.intent.action.PROCESS_TEXT'
      //   122: aload #6
      //   124: invokeinterface getIntent : ()Landroid/content/Intent;
      //   129: invokevirtual getAction : ()Ljava/lang/String;
      //   132: invokevirtual equals : (Ljava/lang/Object;)Z
      //   135: ifeq -> 155
      //   138: aload_3
      //   139: aload_1
      //   140: iconst_1
      //   141: anewarray java/lang/Object
      //   144: dup
      //   145: iconst_0
      //   146: iload_2
      //   147: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   150: aastore
      //   151: invokevirtual invoke : (Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
      //   154: pop
      //   155: iload_2
      //   156: iconst_1
      //   157: isub
      //   158: istore_2
      //   159: goto -> 97
      //   162: astore_3
      //   163: aload_0
      //   164: aconst_null
      //   165: putfield mMenuBuilderClass : Ljava/lang/Class;
      //   168: aload_0
      //   169: aconst_null
      //   170: putfield mMenuBuilderRemoveItemAtMethod : Ljava/lang/reflect/Method;
      //   173: aload_0
      //   174: iconst_0
      //   175: putfield mCanUseMenuBuilderReferences : Z
      //   178: goto -> 65
      //   181: aload_1
      //   182: invokevirtual getClass : ()Ljava/lang/Class;
      //   185: ldc 'removeItemAt'
      //   187: iconst_1
      //   188: anewarray java/lang/Class
      //   191: dup
      //   192: iconst_0
      //   193: getstatic java/lang/Integer.TYPE : Ljava/lang/Class;
      //   196: aastore
      //   197: invokevirtual getDeclaredMethod : (Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
      //   200: astore_3
      //   201: goto -> 88
      //   204: astore_1
      //   205: return
      //   206: aload_0
      //   207: aload #5
      //   209: aload #4
      //   211: invokespecial getSupportedActivities : (Landroid/content/Context;Landroid/content/pm/PackageManager;)Ljava/util/List;
      //   214: astore_3
      //   215: iconst_0
      //   216: istore_2
      //   217: iload_2
      //   218: aload_3
      //   219: invokeinterface size : ()I
      //   224: if_icmpge -> 205
      //   227: aload_3
      //   228: iload_2
      //   229: invokeinterface get : (I)Ljava/lang/Object;
      //   234: checkcast android/content/pm/ResolveInfo
      //   237: astore #5
      //   239: aload_1
      //   240: iconst_0
      //   241: iconst_0
      //   242: iload_2
      //   243: bipush #100
      //   245: iadd
      //   246: aload #5
      //   248: aload #4
      //   250: invokevirtual loadLabel : (Landroid/content/pm/PackageManager;)Ljava/lang/CharSequence;
      //   253: invokeinterface add : (IIILjava/lang/CharSequence;)Landroid/view/MenuItem;
      //   258: aload_0
      //   259: aload #5
      //   261: aload_0
      //   262: getfield mTextView : Landroid/widget/TextView;
      //   265: invokespecial createProcessTextIntentForResolveInfo : (Landroid/content/pm/ResolveInfo;Landroid/widget/TextView;)Landroid/content/Intent;
      //   268: invokeinterface setIntent : (Landroid/content/Intent;)Landroid/view/MenuItem;
      //   273: iconst_1
      //   274: invokeinterface setShowAsAction : (I)V
      //   279: iload_2
      //   280: iconst_1
      //   281: iadd
      //   282: istore_2
      //   283: goto -> 217
      //   286: astore_1
      //   287: return
      //   288: astore_1
      //   289: return
      //   290: astore_3
      //   291: goto -> 163
      // Exception table:
      //   from	to	target	type
      //   28	65	162	java/lang/ClassNotFoundException
      //   28	65	290	java/lang/NoSuchMethodException
      //   65	88	286	java/lang/NoSuchMethodException
      //   65	88	204	java/lang/IllegalAccessException
      //   65	88	288	java/lang/reflect/InvocationTargetException
      //   88	97	286	java/lang/NoSuchMethodException
      //   88	97	204	java/lang/IllegalAccessException
      //   88	97	288	java/lang/reflect/InvocationTargetException
      //   101	155	286	java/lang/NoSuchMethodException
      //   101	155	204	java/lang/IllegalAccessException
      //   101	155	288	java/lang/reflect/InvocationTargetException
      //   181	201	286	java/lang/NoSuchMethodException
      //   181	201	204	java/lang/IllegalAccessException
      //   181	201	288	java/lang/reflect/InvocationTargetException
    }
    
    public boolean onActionItemClicked(ActionMode param1ActionMode, MenuItem param1MenuItem) {
      return this.mCallback.onActionItemClicked(param1ActionMode, param1MenuItem);
    }
    
    public boolean onCreateActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      return this.mCallback.onCreateActionMode(param1ActionMode, param1Menu);
    }
    
    public void onDestroyActionMode(ActionMode param1ActionMode) {
      this.mCallback.onDestroyActionMode(param1ActionMode);
    }
    
    public boolean onPrepareActionMode(ActionMode param1ActionMode, Menu param1Menu) {
      recomputeProcessTextMenuItems(param1Menu);
      return this.mCallback.onPrepareActionMode(param1ActionMode, param1Menu);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/TextViewCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */