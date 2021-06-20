package androidx.appcompat.app;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.R;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatCheckedTextView;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatMultiAutoCompleteTextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.collection.ArrayMap;
import androidx.core.view.ViewCompat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class AppCompatViewInflater {
  private static final String LOG_TAG = "AppCompatViewInflater";
  
  private static final String[] sClassPrefixList;
  
  private static final Map<String, Constructor<? extends View>> sConstructorMap;
  
  private static final Class<?>[] sConstructorSignature = new Class[] { Context.class, AttributeSet.class };
  
  private static final int[] sOnClickAttrs = new int[] { 16843375 };
  
  private final Object[] mConstructorArgs = new Object[2];
  
  static {
    sClassPrefixList = new String[] { "android.widget.", "android.view.", "android.webkit." };
    sConstructorMap = (Map<String, Constructor<? extends View>>)new ArrayMap();
  }
  
  private void checkOnClickListener(View paramView, AttributeSet paramAttributeSet) {
    Context context = paramView.getContext();
    if (!(context instanceof ContextWrapper) || (Build.VERSION.SDK_INT >= 15 && !ViewCompat.hasOnClickListeners(paramView)))
      return; 
    TypedArray typedArray = context.obtainStyledAttributes(paramAttributeSet, sOnClickAttrs);
    String str = typedArray.getString(0);
    if (str != null)
      paramView.setOnClickListener(new DeclaredOnClickListener(paramView, str)); 
    typedArray.recycle();
  }
  
  private View createViewByPrefix(Context paramContext, String paramString1, String paramString2) throws ClassNotFoundException, InflateException {
    Constructor constructor1 = sConstructorMap.get(paramString1);
    Constructor<? extends View> constructor = constructor1;
    if (constructor1 == null)
      try {
        String str;
        ClassLoader classLoader = paramContext.getClassLoader();
        if (paramString2 != null) {
          str = paramString2 + paramString1;
        } else {
          str = paramString1;
        } 
        constructor = classLoader.loadClass(str).<View>asSubclass(View.class).getConstructor(sConstructorSignature);
        sConstructorMap.put(paramString1, constructor);
        constructor.setAccessible(true);
        return constructor.newInstance(this.mConstructorArgs);
      } catch (Exception exception) {
        return null;
      }  
    constructor.setAccessible(true);
    return constructor.newInstance(this.mConstructorArgs);
  }
  
  private View createViewFromTag(Context paramContext, String paramString, AttributeSet paramAttributeSet) {
    String str = paramString;
    if (paramString.equals("view"))
      str = paramAttributeSet.getAttributeValue(null, "class"); 
    try {
      this.mConstructorArgs[0] = paramContext;
      this.mConstructorArgs[1] = paramAttributeSet;
      if (-1 == str.indexOf('.')) {
        int i;
        for (i = 0; i < sClassPrefixList.length; i++) {
          View view = createViewByPrefix(paramContext, str, sClassPrefixList[i]);
          if (view != null)
            return view; 
        } 
        return null;
      } 
      return createViewByPrefix(paramContext, str, null);
    } catch (Exception exception) {
      return null;
    } finally {
      this.mConstructorArgs[0] = null;
      this.mConstructorArgs[1] = null;
    } 
  }
  
  private static Context themifyContext(Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2) {
    TypedArray typedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.View, 0, 0);
    int i = 0;
    if (paramBoolean1)
      i = typedArray.getResourceId(R.styleable.View_android_theme, 0); 
    int j = i;
    if (paramBoolean2) {
      j = i;
      if (i == 0) {
        i = typedArray.getResourceId(R.styleable.View_theme, 0);
        j = i;
        if (i != 0) {
          Log.i("AppCompatViewInflater", "app:theme is now deprecated. Please move to using android:theme instead.");
          j = i;
        } 
      } 
    } 
    typedArray.recycle();
    Context context = paramContext;
    if (j != 0) {
      if (paramContext instanceof ContextThemeWrapper) {
        context = paramContext;
        return (Context)((((ContextThemeWrapper)paramContext).getThemeResId() != j) ? new ContextThemeWrapper(paramContext, j) : context);
      } 
    } else {
      return context;
    } 
    return (Context)new ContextThemeWrapper(paramContext, j);
  }
  
  private void verifyNotNull(View paramView, String paramString) {
    if (paramView == null)
      throw new IllegalStateException(getClass().getName() + " asked to inflate view for <" + paramString + ">, but returned null"); 
  }
  
  @NonNull
  protected AppCompatAutoCompleteTextView createAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatButton createButton(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatButton(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatCheckBox createCheckBox(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatCheckBox(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatCheckedTextView createCheckedTextView(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatCheckedTextView(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatEditText createEditText(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatEditText(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatImageButton createImageButton(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatImageButton(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatImageView createImageView(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatImageView(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatMultiAutoCompleteTextView createMultiAutoCompleteTextView(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatMultiAutoCompleteTextView(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatRadioButton createRadioButton(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatRadioButton(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatRatingBar createRatingBar(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatRatingBar(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatSeekBar createSeekBar(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatSeekBar(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatSpinner createSpinner(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatSpinner(paramContext, paramAttributeSet);
  }
  
  @NonNull
  protected AppCompatTextView createTextView(Context paramContext, AttributeSet paramAttributeSet) {
    return new AppCompatTextView(paramContext, paramAttributeSet);
  }
  
  @Nullable
  protected View createView(Context paramContext, String paramString, AttributeSet paramAttributeSet) {
    return null;
  }
  
  final View createView(View paramView, String paramString, @NonNull Context paramContext, @NonNull AttributeSet paramAttributeSet, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4) {
    // Byte code:
    //   0: aload_3
    //   1: astore #10
    //   3: iload #5
    //   5: ifeq -> 21
    //   8: aload_3
    //   9: astore #10
    //   11: aload_1
    //   12: ifnull -> 21
    //   15: aload_1
    //   16: invokevirtual getContext : ()Landroid/content/Context;
    //   19: astore #10
    //   21: iload #6
    //   23: ifne -> 34
    //   26: aload #10
    //   28: astore_1
    //   29: iload #7
    //   31: ifeq -> 46
    //   34: aload #10
    //   36: aload #4
    //   38: iload #6
    //   40: iload #7
    //   42: invokestatic themifyContext : (Landroid/content/Context;Landroid/util/AttributeSet;ZZ)Landroid/content/Context;
    //   45: astore_1
    //   46: aload_1
    //   47: astore #10
    //   49: iload #8
    //   51: ifeq -> 60
    //   54: aload_1
    //   55: invokestatic wrap : (Landroid/content/Context;)Landroid/content/Context;
    //   58: astore #10
    //   60: iconst_m1
    //   61: istore #9
    //   63: aload_2
    //   64: invokevirtual hashCode : ()I
    //   67: lookupswitch default -> 180, -1946472170 -> 482, -1455429095 -> 431, -1346021293 -> 465, -938935918 -> 301, -937446323 -> 381, -658531749 -> 499, -339785223 -> 365, 776382189 -> 414, 1125864064 -> 317, 1413872058 -> 448, 1601505219 -> 397, 1666676343 -> 349, 2001146706 -> 333
    //   180: iload #9
    //   182: tableswitch default -> 248, 0 -> 516, 1 -> 534, 2 -> 552, 3 -> 570, 4 -> 588, 5 -> 606, 6 -> 624, 7 -> 642, 8 -> 660, 9 -> 678, 10 -> 696, 11 -> 714, 12 -> 732
    //   248: aload_0
    //   249: aload #10
    //   251: aload_2
    //   252: aload #4
    //   254: invokevirtual createView : (Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;)Landroid/view/View;
    //   257: astore_1
    //   258: aload_1
    //   259: astore #11
    //   261: aload_1
    //   262: ifnonnull -> 285
    //   265: aload_1
    //   266: astore #11
    //   268: aload_3
    //   269: aload #10
    //   271: if_acmpeq -> 285
    //   274: aload_0
    //   275: aload #10
    //   277: aload_2
    //   278: aload #4
    //   280: invokespecial createViewFromTag : (Landroid/content/Context;Ljava/lang/String;Landroid/util/AttributeSet;)Landroid/view/View;
    //   283: astore #11
    //   285: aload #11
    //   287: ifnull -> 298
    //   290: aload_0
    //   291: aload #11
    //   293: aload #4
    //   295: invokespecial checkOnClickListener : (Landroid/view/View;Landroid/util/AttributeSet;)V
    //   298: aload #11
    //   300: areturn
    //   301: aload_2
    //   302: ldc_w 'TextView'
    //   305: invokevirtual equals : (Ljava/lang/Object;)Z
    //   308: ifeq -> 180
    //   311: iconst_0
    //   312: istore #9
    //   314: goto -> 180
    //   317: aload_2
    //   318: ldc_w 'ImageView'
    //   321: invokevirtual equals : (Ljava/lang/Object;)Z
    //   324: ifeq -> 180
    //   327: iconst_1
    //   328: istore #9
    //   330: goto -> 180
    //   333: aload_2
    //   334: ldc_w 'Button'
    //   337: invokevirtual equals : (Ljava/lang/Object;)Z
    //   340: ifeq -> 180
    //   343: iconst_2
    //   344: istore #9
    //   346: goto -> 180
    //   349: aload_2
    //   350: ldc_w 'EditText'
    //   353: invokevirtual equals : (Ljava/lang/Object;)Z
    //   356: ifeq -> 180
    //   359: iconst_3
    //   360: istore #9
    //   362: goto -> 180
    //   365: aload_2
    //   366: ldc_w 'Spinner'
    //   369: invokevirtual equals : (Ljava/lang/Object;)Z
    //   372: ifeq -> 180
    //   375: iconst_4
    //   376: istore #9
    //   378: goto -> 180
    //   381: aload_2
    //   382: ldc_w 'ImageButton'
    //   385: invokevirtual equals : (Ljava/lang/Object;)Z
    //   388: ifeq -> 180
    //   391: iconst_5
    //   392: istore #9
    //   394: goto -> 180
    //   397: aload_2
    //   398: ldc_w 'CheckBox'
    //   401: invokevirtual equals : (Ljava/lang/Object;)Z
    //   404: ifeq -> 180
    //   407: bipush #6
    //   409: istore #9
    //   411: goto -> 180
    //   414: aload_2
    //   415: ldc_w 'RadioButton'
    //   418: invokevirtual equals : (Ljava/lang/Object;)Z
    //   421: ifeq -> 180
    //   424: bipush #7
    //   426: istore #9
    //   428: goto -> 180
    //   431: aload_2
    //   432: ldc_w 'CheckedTextView'
    //   435: invokevirtual equals : (Ljava/lang/Object;)Z
    //   438: ifeq -> 180
    //   441: bipush #8
    //   443: istore #9
    //   445: goto -> 180
    //   448: aload_2
    //   449: ldc_w 'AutoCompleteTextView'
    //   452: invokevirtual equals : (Ljava/lang/Object;)Z
    //   455: ifeq -> 180
    //   458: bipush #9
    //   460: istore #9
    //   462: goto -> 180
    //   465: aload_2
    //   466: ldc_w 'MultiAutoCompleteTextView'
    //   469: invokevirtual equals : (Ljava/lang/Object;)Z
    //   472: ifeq -> 180
    //   475: bipush #10
    //   477: istore #9
    //   479: goto -> 180
    //   482: aload_2
    //   483: ldc_w 'RatingBar'
    //   486: invokevirtual equals : (Ljava/lang/Object;)Z
    //   489: ifeq -> 180
    //   492: bipush #11
    //   494: istore #9
    //   496: goto -> 180
    //   499: aload_2
    //   500: ldc_w 'SeekBar'
    //   503: invokevirtual equals : (Ljava/lang/Object;)Z
    //   506: ifeq -> 180
    //   509: bipush #12
    //   511: istore #9
    //   513: goto -> 180
    //   516: aload_0
    //   517: aload #10
    //   519: aload #4
    //   521: invokevirtual createTextView : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatTextView;
    //   524: astore_1
    //   525: aload_0
    //   526: aload_1
    //   527: aload_2
    //   528: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   531: goto -> 258
    //   534: aload_0
    //   535: aload #10
    //   537: aload #4
    //   539: invokevirtual createImageView : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatImageView;
    //   542: astore_1
    //   543: aload_0
    //   544: aload_1
    //   545: aload_2
    //   546: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   549: goto -> 258
    //   552: aload_0
    //   553: aload #10
    //   555: aload #4
    //   557: invokevirtual createButton : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatButton;
    //   560: astore_1
    //   561: aload_0
    //   562: aload_1
    //   563: aload_2
    //   564: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   567: goto -> 258
    //   570: aload_0
    //   571: aload #10
    //   573: aload #4
    //   575: invokevirtual createEditText : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatEditText;
    //   578: astore_1
    //   579: aload_0
    //   580: aload_1
    //   581: aload_2
    //   582: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   585: goto -> 258
    //   588: aload_0
    //   589: aload #10
    //   591: aload #4
    //   593: invokevirtual createSpinner : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatSpinner;
    //   596: astore_1
    //   597: aload_0
    //   598: aload_1
    //   599: aload_2
    //   600: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   603: goto -> 258
    //   606: aload_0
    //   607: aload #10
    //   609: aload #4
    //   611: invokevirtual createImageButton : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatImageButton;
    //   614: astore_1
    //   615: aload_0
    //   616: aload_1
    //   617: aload_2
    //   618: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   621: goto -> 258
    //   624: aload_0
    //   625: aload #10
    //   627: aload #4
    //   629: invokevirtual createCheckBox : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatCheckBox;
    //   632: astore_1
    //   633: aload_0
    //   634: aload_1
    //   635: aload_2
    //   636: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   639: goto -> 258
    //   642: aload_0
    //   643: aload #10
    //   645: aload #4
    //   647: invokevirtual createRadioButton : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatRadioButton;
    //   650: astore_1
    //   651: aload_0
    //   652: aload_1
    //   653: aload_2
    //   654: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   657: goto -> 258
    //   660: aload_0
    //   661: aload #10
    //   663: aload #4
    //   665: invokevirtual createCheckedTextView : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatCheckedTextView;
    //   668: astore_1
    //   669: aload_0
    //   670: aload_1
    //   671: aload_2
    //   672: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   675: goto -> 258
    //   678: aload_0
    //   679: aload #10
    //   681: aload #4
    //   683: invokevirtual createAutoCompleteTextView : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatAutoCompleteTextView;
    //   686: astore_1
    //   687: aload_0
    //   688: aload_1
    //   689: aload_2
    //   690: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   693: goto -> 258
    //   696: aload_0
    //   697: aload #10
    //   699: aload #4
    //   701: invokevirtual createMultiAutoCompleteTextView : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatMultiAutoCompleteTextView;
    //   704: astore_1
    //   705: aload_0
    //   706: aload_1
    //   707: aload_2
    //   708: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   711: goto -> 258
    //   714: aload_0
    //   715: aload #10
    //   717: aload #4
    //   719: invokevirtual createRatingBar : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatRatingBar;
    //   722: astore_1
    //   723: aload_0
    //   724: aload_1
    //   725: aload_2
    //   726: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   729: goto -> 258
    //   732: aload_0
    //   733: aload #10
    //   735: aload #4
    //   737: invokevirtual createSeekBar : (Landroid/content/Context;Landroid/util/AttributeSet;)Landroidx/appcompat/widget/AppCompatSeekBar;
    //   740: astore_1
    //   741: aload_0
    //   742: aload_1
    //   743: aload_2
    //   744: invokespecial verifyNotNull : (Landroid/view/View;Ljava/lang/String;)V
    //   747: goto -> 258
  }
  
  private static class DeclaredOnClickListener implements View.OnClickListener {
    private final View mHostView;
    
    private final String mMethodName;
    
    private Context mResolvedContext;
    
    private Method mResolvedMethod;
    
    public DeclaredOnClickListener(@NonNull View param1View, @NonNull String param1String) {
      this.mHostView = param1View;
      this.mMethodName = param1String;
    }
    
    @NonNull
    private void resolveMethod(@Nullable Context param1Context, @NonNull String param1String) {
      while (param1Context != null) {
        try {
          if (!param1Context.isRestricted()) {
            Method method = param1Context.getClass().getMethod(this.mMethodName, new Class[] { View.class });
            if (method != null) {
              this.mResolvedMethod = method;
              this.mResolvedContext = param1Context;
              return;
            } 
          } 
        } catch (NoSuchMethodException noSuchMethodException) {}
        if (param1Context instanceof ContextWrapper) {
          param1Context = ((ContextWrapper)param1Context).getBaseContext();
          continue;
        } 
        param1Context = null;
      } 
      int i = this.mHostView.getId();
      if (i == -1) {
        String str1 = "";
        throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + str1);
      } 
      String str = " with id '" + this.mHostView.getContext().getResources().getResourceEntryName(i) + "'";
      throw new IllegalStateException("Could not find method " + this.mMethodName + "(View) in a parent or ancestor Context for android:onClick " + "attribute defined on view " + this.mHostView.getClass() + str);
    }
    
    public void onClick(@NonNull View param1View) {
      if (this.mResolvedMethod == null)
        resolveMethod(this.mHostView.getContext(), this.mMethodName); 
      try {
        this.mResolvedMethod.invoke(this.mResolvedContext, new Object[] { param1View });
        return;
      } catch (IllegalAccessException illegalAccessException) {
        throw new IllegalStateException("Could not execute non-public method for android:onClick", illegalAccessException);
      } catch (InvocationTargetException invocationTargetException) {
        throw new IllegalStateException("Could not execute method for android:onClick", invocationTargetException);
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/AppCompatViewInflater.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */