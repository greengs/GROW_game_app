package androidx.core.text;

import android.os.Build;
import android.util.Log;
import androidx.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

public final class ICUCompat {
  private static final String TAG = "ICUCompat";
  
  private static Method sAddLikelySubtagsMethod;
  
  private static Method sGetScriptMethod;
  
  static {
    if (Build.VERSION.SDK_INT >= 21)
      try {
        sAddLikelySubtagsMethod = Class.forName("libcore.icu.ICU").getMethod("addLikelySubtags", new Class[] { Locale.class });
        return;
      } catch (Exception exception) {
        throw new IllegalStateException(exception);
      }  
    try {
      Class<?> clazz = Class.forName("libcore.icu.ICU");
      if (clazz != null) {
        sGetScriptMethod = clazz.getMethod("getScript", new Class[] { String.class });
        sAddLikelySubtagsMethod = clazz.getMethod("addLikelySubtags", new Class[] { String.class });
        return;
      } 
      return;
    } catch (Exception exception) {
      sGetScriptMethod = null;
      sAddLikelySubtagsMethod = null;
      Log.w("ICUCompat", exception);
      return;
    } 
  }
  
  private static String addLikelySubtags(Locale paramLocale) {
    String str = paramLocale.toString();
    try {
      if (sAddLikelySubtagsMethod != null)
        return (String)sAddLikelySubtagsMethod.invoke(null, new Object[] { str }); 
    } catch (IllegalAccessException illegalAccessException) {
      Log.w("ICUCompat", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Log.w("ICUCompat", invocationTargetException);
    } 
    return str;
  }
  
  private static String getScript(String paramString) {
    try {
      if (sGetScriptMethod != null)
        return (String)sGetScriptMethod.invoke(null, new Object[] { paramString }); 
    } catch (IllegalAccessException illegalAccessException) {
      Log.w("ICUCompat", illegalAccessException);
    } catch (InvocationTargetException invocationTargetException) {
      Log.w("ICUCompat", invocationTargetException);
    } 
    return null;
  }
  
  @Nullable
  public static String maximizeAndGetScript(Locale paramLocale) {
    String str1 = null;
    if (Build.VERSION.SDK_INT >= 21) {
      try {
        str1 = ((Locale)sAddLikelySubtagsMethod.invoke(null, new Object[] { paramLocale })).getScript();
        return str1;
      } catch (InvocationTargetException invocationTargetException) {
        Log.w("ICUCompat", invocationTargetException);
      } catch (IllegalAccessException illegalAccessException2) {
        Log.w("ICUCompat", illegalAccessException2);
      } 
      return paramLocale.getScript();
    } 
    String str2 = addLikelySubtags(paramLocale);
    IllegalAccessException illegalAccessException1 = illegalAccessException2;
    return (String)((str2 != null) ? getScript(str2) : illegalAccessException1);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/text/ICUCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */