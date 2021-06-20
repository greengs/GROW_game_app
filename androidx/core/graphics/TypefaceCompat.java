package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.LruCache;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.provider.FontsContractCompat;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompat {
  private static final String TAG = "TypefaceCompat";
  
  private static final LruCache<String, Typeface> sTypefaceCache = new LruCache(16);
  
  private static final TypefaceCompatBaseImpl sTypefaceCompatImpl;
  
  @Nullable
  public static Typeface createFromFontInfo(@NonNull Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    return sTypefaceCompatImpl.createFromFontInfo(paramContext, paramCancellationSignal, paramArrayOfFontInfo, paramInt);
  }
  
  @Nullable
  public static Typeface createFromResourcesFamilyXml(@NonNull Context paramContext, @NonNull FontResourcesParserCompat.FamilyResourceEntry paramFamilyResourceEntry, @NonNull Resources paramResources, int paramInt1, int paramInt2, @Nullable ResourcesCompat.FontCallback paramFontCallback, @Nullable Handler paramHandler, boolean paramBoolean) {
    Typeface typeface;
    FontResourcesParserCompat.ProviderResourceEntry providerResourceEntry;
    if (paramFamilyResourceEntry instanceof FontResourcesParserCompat.ProviderResourceEntry) {
      byte b;
      boolean bool;
      providerResourceEntry = (FontResourcesParserCompat.ProviderResourceEntry)paramFamilyResourceEntry;
      if (paramBoolean) {
        if (providerResourceEntry.getFetchStrategy() == 0) {
          bool = true;
        } else {
          bool = false;
        } 
      } else if (paramFontCallback == null) {
        bool = true;
      } else {
        bool = false;
      } 
      if (paramBoolean) {
        b = providerResourceEntry.getTimeout();
      } else {
        b = -1;
      } 
      typeface = FontsContractCompat.getFontSync(paramContext, providerResourceEntry.getRequest(), paramFontCallback, paramHandler, bool, b, paramInt2);
    } else {
      Typeface typeface1 = sTypefaceCompatImpl.createFromFontFamilyFilesResourceEntry((Context)typeface, (FontResourcesParserCompat.FontFamilyFilesResourceEntry)providerResourceEntry, paramResources, paramInt2);
      typeface = typeface1;
      if (paramFontCallback != null)
        if (typeface1 != null) {
          paramFontCallback.callbackSuccessAsync(typeface1, paramHandler);
          typeface = typeface1;
        } else {
          paramFontCallback.callbackFailAsync(-3, paramHandler);
          typeface = typeface1;
        }  
    } 
    if (typeface != null)
      sTypefaceCache.put(createResourceUid(paramResources, paramInt1, paramInt2), typeface); 
    return typeface;
  }
  
  @Nullable
  public static Typeface createFromResourcesFontFile(@NonNull Context paramContext, @NonNull Resources paramResources, int paramInt1, String paramString, int paramInt2) {
    Typeface typeface = sTypefaceCompatImpl.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2);
    if (typeface != null) {
      String str = createResourceUid(paramResources, paramInt1, paramInt2);
      sTypefaceCache.put(str, typeface);
    } 
    return typeface;
  }
  
  private static String createResourceUid(Resources paramResources, int paramInt1, int paramInt2) {
    return paramResources.getResourcePackageName(paramInt1) + "-" + paramInt1 + "-" + paramInt2;
  }
  
  @Nullable
  public static Typeface findFromCache(@NonNull Resources paramResources, int paramInt1, int paramInt2) {
    return (Typeface)sTypefaceCache.get(createResourceUid(paramResources, paramInt1, paramInt2));
  }
  
  static {
    if (Build.VERSION.SDK_INT >= 28) {
      sTypefaceCompatImpl = new TypefaceCompatApi28Impl();
    } else if (Build.VERSION.SDK_INT >= 26) {
      sTypefaceCompatImpl = new TypefaceCompatApi26Impl();
    } else if (Build.VERSION.SDK_INT >= 24 && TypefaceCompatApi24Impl.isUsable()) {
      sTypefaceCompatImpl = new TypefaceCompatApi24Impl();
    } else if (Build.VERSION.SDK_INT >= 21) {
      sTypefaceCompatImpl = new TypefaceCompatApi21Impl();
    } else {
      sTypefaceCompatImpl = new TypefaceCompatBaseImpl();
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */