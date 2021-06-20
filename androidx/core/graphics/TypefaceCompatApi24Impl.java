package androidx.core.graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.collection.SimpleArrayMap;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.List;

@RequiresApi(24)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
class TypefaceCompatApi24Impl extends TypefaceCompatBaseImpl {
  private static final String ADD_FONT_WEIGHT_STYLE_METHOD = "addFontWeightStyle";
  
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  
  private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
  
  private static final String TAG = "TypefaceCompatApi24Impl";
  
  private static final Method sAddFontWeightStyle;
  
  private static final Method sCreateFromFamiliesWithDefault;
  
  private static final Class sFontFamily;
  
  private static final Constructor sFontFamilyCtor;
  
  static {
    try {
      Class<?> clazz1 = Class.forName("android.graphics.FontFamily");
      Constructor<?> constructor1 = clazz1.getConstructor(new Class[0]);
      Method method1 = clazz1.getMethod("addFontWeightStyle", new Class[] { ByteBuffer.class, int.class, List.class, int.class, boolean.class });
      Method method2 = Typeface.class.getMethod("createFromFamiliesWithDefault", new Class[] { Array.newInstance(clazz1, 1).getClass() });
      sFontFamilyCtor = constructor1;
      sFontFamily = clazz1;
      sAddFontWeightStyle = method1;
      sCreateFromFamiliesWithDefault = method2;
      return;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    Log.e("TypefaceCompatApi24Impl", noSuchMethodException.getClass().getName(), noSuchMethodException);
    Class clazz = null;
    Constructor constructor = null;
    noSuchMethodException = null;
    Method method = null;
    sFontFamilyCtor = constructor;
    sFontFamily = clazz;
    sAddFontWeightStyle = (Method)noSuchMethodException;
    sCreateFromFamiliesWithDefault = method;
  }
  
  private static boolean addFontWeightStyle(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, boolean paramBoolean) {
    try {
      return ((Boolean)sAddFontWeightStyle.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Boolean.valueOf(paramBoolean) })).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private static Typeface createFromFamiliesWithDefault(Object paramObject) {
    try {
      Object object = Array.newInstance(sFontFamily, 1);
      Array.set(object, 0, paramObject);
      return (Typeface)sCreateFromFamiliesWithDefault.invoke(null, new Object[] { object });
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  public static boolean isUsable() {
    if (sAddFontWeightStyle == null)
      Log.w("TypefaceCompatApi24Impl", "Unable to collect necessary private methods.Fallback to legacy implementation."); 
    return (sAddFontWeightStyle != null);
  }
  
  private static Object newFamily() {
    try {
      return sFontFamilyCtor.newInstance(new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt) {
    Object object = newFamily();
    FontResourcesParserCompat.FontFileResourceEntry[] arrayOfFontFileResourceEntry = paramFontFamilyFilesResourceEntry.getEntries();
    int i = arrayOfFontFileResourceEntry.length;
    paramInt = 0;
    while (paramInt < i) {
      FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = arrayOfFontFileResourceEntry[paramInt];
      ByteBuffer byteBuffer = TypefaceCompatUtil.copyToDirectBuffer(paramContext, paramResources, fontFileResourceEntry.getResourceId());
      if (byteBuffer != null && addFontWeightStyle(object, byteBuffer, fontFileResourceEntry.getTtcIndex(), fontFileResourceEntry.getWeight(), fontFileResourceEntry.isItalic())) {
        paramInt++;
        continue;
      } 
      return null;
    } 
    return createFromFamiliesWithDefault(object);
  }
  
  public Typeface createFromFontInfo(Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    Object object = newFamily();
    SimpleArrayMap simpleArrayMap = new SimpleArrayMap();
    int j = paramArrayOfFontInfo.length;
    int i;
    for (i = 0; i < j; i++) {
      FontsContractCompat.FontInfo fontInfo = paramArrayOfFontInfo[i];
      Uri uri = fontInfo.getUri();
      ByteBuffer byteBuffer2 = (ByteBuffer)simpleArrayMap.get(uri);
      ByteBuffer byteBuffer1 = byteBuffer2;
      if (byteBuffer2 == null) {
        byteBuffer1 = TypefaceCompatUtil.mmap(paramContext, paramCancellationSignal, uri);
        simpleArrayMap.put(uri, byteBuffer1);
      } 
      if (!addFontWeightStyle(object, byteBuffer1, fontInfo.getTtcIndex(), fontInfo.getWeight(), fontInfo.isItalic()))
        return null; 
    } 
    return Typeface.create(createFromFamiliesWithDefault(object), paramInt);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompatApi24Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */