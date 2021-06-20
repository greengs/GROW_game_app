package androidx.core.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.util.TypedValue;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.FontRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.graphics.TypefaceCompat;
import androidx.core.util.Preconditions;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public final class ResourcesCompat {
  private static final String TAG = "ResourcesCompat";
  
  @ColorInt
  public static int getColor(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 23) ? paramResources.getColor(paramInt, paramTheme) : paramResources.getColor(paramInt);
  }
  
  @Nullable
  public static ColorStateList getColorStateList(@NonNull Resources paramResources, @ColorRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 23) ? paramResources.getColorStateList(paramInt, paramTheme) : paramResources.getColorStateList(paramInt);
  }
  
  @Nullable
  public static Drawable getDrawable(@NonNull Resources paramResources, @DrawableRes int paramInt, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 21) ? paramResources.getDrawable(paramInt, paramTheme) : paramResources.getDrawable(paramInt);
  }
  
  @Nullable
  public static Drawable getDrawableForDensity(@NonNull Resources paramResources, @DrawableRes int paramInt1, int paramInt2, @Nullable Resources.Theme paramTheme) throws Resources.NotFoundException {
    return (Build.VERSION.SDK_INT >= 21) ? paramResources.getDrawableForDensity(paramInt1, paramInt2, paramTheme) : ((Build.VERSION.SDK_INT >= 15) ? paramResources.getDrawableForDensity(paramInt1, paramInt2) : paramResources.getDrawable(paramInt1));
  }
  
  @Nullable
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt) throws Resources.NotFoundException {
    return paramContext.isRestricted() ? null : loadFont(paramContext, paramInt, new TypedValue(), 0, null, null, false);
  }
  
  @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
  public static Typeface getFont(@NonNull Context paramContext, @FontRes int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable FontCallback paramFontCallback) throws Resources.NotFoundException {
    return paramContext.isRestricted() ? null : loadFont(paramContext, paramInt1, paramTypedValue, paramInt2, paramFontCallback, null, true);
  }
  
  public static void getFont(@NonNull Context paramContext, @FontRes int paramInt, @NonNull FontCallback paramFontCallback, @Nullable Handler paramHandler) throws Resources.NotFoundException {
    Preconditions.checkNotNull(paramFontCallback);
    if (paramContext.isRestricted()) {
      paramFontCallback.callbackFailAsync(-4, paramHandler);
      return;
    } 
    loadFont(paramContext, paramInt, new TypedValue(), 0, paramFontCallback, paramHandler, false);
  }
  
  private static Typeface loadFont(@NonNull Context paramContext, int paramInt1, TypedValue paramTypedValue, int paramInt2, @Nullable FontCallback paramFontCallback, @Nullable Handler paramHandler, boolean paramBoolean) {
    Resources resources = paramContext.getResources();
    resources.getValue(paramInt1, paramTypedValue, true);
    Typeface typeface = loadFont(paramContext, resources, paramTypedValue, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
    if (typeface == null && paramFontCallback == null)
      throw new Resources.NotFoundException("Font resource ID #0x" + Integer.toHexString(paramInt1) + " could not be retrieved."); 
    return typeface;
  }
  
  private static Typeface loadFont(@NonNull Context paramContext, Resources paramResources, TypedValue paramTypedValue, int paramInt1, int paramInt2, @Nullable FontCallback paramFontCallback, @Nullable Handler paramHandler, boolean paramBoolean) {
    Typeface typeface1;
    if (paramTypedValue.string == null)
      throw new Resources.NotFoundException("Resource \"" + paramResources.getResourceName(paramInt1) + "\" (" + Integer.toHexString(paramInt1) + ") is not a Font: " + paramTypedValue); 
    String str = paramTypedValue.string.toString();
    if (!str.startsWith("res/")) {
      if (paramFontCallback != null)
        paramFontCallback.callbackFailAsync(-3, paramHandler); 
      return null;
    } 
    Typeface typeface2 = TypefaceCompat.findFromCache(paramResources, paramInt1, paramInt2);
    if (typeface2 != null) {
      typeface1 = typeface2;
      if (paramFontCallback != null) {
        paramFontCallback.callbackSuccessAsync(typeface2, paramHandler);
        return typeface2;
      } 
      return typeface1;
    } 
    try {
      if (str.toLowerCase().endsWith(".xml")) {
        FontResourcesParserCompat.FamilyResourceEntry familyResourceEntry = FontResourcesParserCompat.parse((XmlPullParser)paramResources.getXml(paramInt1), paramResources);
        if (familyResourceEntry == null) {
          Log.e("ResourcesCompat", "Failed to find font-family tag");
          if (paramFontCallback != null)
            paramFontCallback.callbackFailAsync(-3, paramHandler); 
        } else {
          return TypefaceCompat.createFromResourcesFamilyXml((Context)typeface1, familyResourceEntry, paramResources, paramInt1, paramInt2, paramFontCallback, paramHandler, paramBoolean);
        } 
      } else {
        Typeface typeface = TypefaceCompat.createFromResourcesFontFile((Context)typeface1, paramResources, paramInt1, str, paramInt2);
        typeface1 = typeface;
        if (paramFontCallback != null) {
          if (typeface != null) {
            paramFontCallback.callbackSuccessAsync(typeface, paramHandler);
            return typeface;
          } 
          paramFontCallback.callbackFailAsync(-3, paramHandler);
          return typeface;
        } 
        return typeface1;
      } 
    } catch (XmlPullParserException xmlPullParserException) {
      Log.e("ResourcesCompat", "Failed to parse xml resource " + str, (Throwable)xmlPullParserException);
      if (paramFontCallback != null)
        paramFontCallback.callbackFailAsync(-3, paramHandler); 
      return null;
    } catch (IOException iOException) {
      Log.e("ResourcesCompat", "Failed to read xml resource " + str, iOException);
      if (paramFontCallback != null)
        paramFontCallback.callbackFailAsync(-3, paramHandler); 
      return null;
    } 
    return null;
  }
  
  public static abstract class FontCallback {
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void callbackFailAsync(final int reason, @Nullable Handler param1Handler) {
      Handler handler = param1Handler;
      if (param1Handler == null)
        handler = new Handler(Looper.getMainLooper()); 
      handler.post(new Runnable() {
            public void run() {
              ResourcesCompat.FontCallback.this.onFontRetrievalFailed(reason);
            }
          });
    }
    
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public final void callbackSuccessAsync(final Typeface typeface, @Nullable Handler param1Handler) {
      Handler handler = param1Handler;
      if (param1Handler == null)
        handler = new Handler(Looper.getMainLooper()); 
      handler.post(new Runnable() {
            public void run() {
              ResourcesCompat.FontCallback.this.onFontRetrieved(typeface);
            }
          });
    }
    
    public abstract void onFontRetrievalFailed(int param1Int);
    
    public abstract void onFontRetrieved(@NonNull Typeface param1Typeface);
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.onFontRetrieved(typeface);
    }
  }
  
  class null implements Runnable {
    public void run() {
      this.this$0.onFontRetrievalFailed(reason);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/content/res/ResourcesCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */