package androidx.core.graphics;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.graphics.fonts.FontVariationAxis;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Map;

@RequiresApi(26)
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class TypefaceCompatApi26Impl extends TypefaceCompatApi21Impl {
  private static final String ABORT_CREATION_METHOD = "abortCreation";
  
  private static final String ADD_FONT_FROM_ASSET_MANAGER_METHOD = "addFontFromAssetManager";
  
  private static final String ADD_FONT_FROM_BUFFER_METHOD = "addFontFromBuffer";
  
  private static final String CREATE_FROM_FAMILIES_WITH_DEFAULT_METHOD = "createFromFamiliesWithDefault";
  
  private static final String DEFAULT_FAMILY = "sans-serif";
  
  private static final String FONT_FAMILY_CLASS = "android.graphics.FontFamily";
  
  private static final String FREEZE_METHOD = "freeze";
  
  private static final int RESOLVE_BY_FONT_TABLE = -1;
  
  private static final String TAG = "TypefaceCompatApi26Impl";
  
  protected final Method mAbortCreation;
  
  protected final Method mAddFontFromAssetManager;
  
  protected final Method mAddFontFromBuffer;
  
  protected final Method mCreateFromFamiliesWithDefault;
  
  protected final Class mFontFamily;
  
  protected final Constructor mFontFamilyCtor;
  
  protected final Method mFreeze;
  
  public TypefaceCompatApi26Impl() {
    try {
      Class clazz1 = obtainFontFamily();
      Constructor constructor1 = obtainFontFamilyCtor(clazz1);
      Method method6 = obtainAddFontFromAssetManagerMethod(clazz1);
      Method method7 = obtainAddFontFromBufferMethod(clazz1);
      Method method9 = obtainFreezeMethod(clazz1);
      Method method5 = obtainAbortCreationMethod(clazz1);
      Method method8 = obtainCreateFromFamiliesWithDefaultMethod(clazz1);
      this.mFontFamily = clazz1;
      this.mFontFamilyCtor = constructor1;
      this.mAddFontFromAssetManager = method6;
      this.mAddFontFromBuffer = method7;
      this.mFreeze = method9;
      this.mAbortCreation = method5;
      this.mCreateFromFamiliesWithDefault = method8;
      return;
    } catch (ClassNotFoundException classNotFoundException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    Log.e("TypefaceCompatApi26Impl", "Unable to collect necessary methods for class " + noSuchMethodException.getClass().getName(), noSuchMethodException);
    Class clazz = null;
    Constructor constructor = null;
    Method method1 = null;
    Method method2 = null;
    Method method4 = null;
    noSuchMethodException = null;
    Method method3 = null;
    this.mFontFamily = clazz;
    this.mFontFamilyCtor = constructor;
    this.mAddFontFromAssetManager = method1;
    this.mAddFontFromBuffer = method2;
    this.mFreeze = method4;
    this.mAbortCreation = (Method)noSuchMethodException;
    this.mCreateFromFamiliesWithDefault = method3;
  }
  
  private void abortCreation(Object paramObject) {
    try {
      this.mAbortCreation.invoke(paramObject, new Object[0]);
      return;
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean addFontFromAssetManager(Context paramContext, Object paramObject, String paramString, int paramInt1, int paramInt2, int paramInt3, @Nullable FontVariationAxis[] paramArrayOfFontVariationAxis) {
    try {
      return ((Boolean)this.mAddFontFromAssetManager.invoke(paramObject, new Object[] { paramContext.getAssets(), paramString, Integer.valueOf(0), Boolean.valueOf(false), Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramArrayOfFontVariationAxis })).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean addFontFromBuffer(Object paramObject, ByteBuffer paramByteBuffer, int paramInt1, int paramInt2, int paramInt3) {
    try {
      return ((Boolean)this.mAddFontFromBuffer.invoke(paramObject, new Object[] { paramByteBuffer, Integer.valueOf(paramInt1), null, Integer.valueOf(paramInt2), Integer.valueOf(paramInt3) })).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean freeze(Object paramObject) {
    try {
      return ((Boolean)this.mFreeze.invoke(paramObject, new Object[0])).booleanValue();
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  private boolean isFontFamilyPrivateAPIAvailable() {
    if (this.mAddFontFromAssetManager == null)
      Log.w("TypefaceCompatApi26Impl", "Unable to collect necessary private methods. Fallback to legacy implementation."); 
    return (this.mAddFontFromAssetManager != null);
  }
  
  private Object newFamily() {
    try {
      return this.mFontFamilyCtor.newInstance(new Object[0]);
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InstantiationException instantiationException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  protected Typeface createFromFamiliesWithDefault(Object paramObject) {
    try {
      Object object = Array.newInstance(this.mFontFamily, 1);
      Array.set(object, 0, paramObject);
      return (Typeface)this.mCreateFromFamiliesWithDefault.invoke(null, new Object[] { object, Integer.valueOf(-1), Integer.valueOf(-1) });
    } catch (IllegalAccessException illegalAccessException) {
    
    } catch (InvocationTargetException invocationTargetException) {}
    throw new RuntimeException(invocationTargetException);
  }
  
  public Typeface createFromFontFamilyFilesResourceEntry(Context paramContext, FontResourcesParserCompat.FontFamilyFilesResourceEntry paramFontFamilyFilesResourceEntry, Resources paramResources, int paramInt) {
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromFontFamilyFilesResourceEntry(paramContext, paramFontFamilyFilesResourceEntry, paramResources, paramInt); 
    Object object = newFamily();
    FontResourcesParserCompat.FontFileResourceEntry[] arrayOfFontFileResourceEntry = paramFontFamilyFilesResourceEntry.getEntries();
    int i = arrayOfFontFileResourceEntry.length;
    for (paramInt = 0; paramInt < i; paramInt++) {
      boolean bool;
      FontResourcesParserCompat.FontFileResourceEntry fontFileResourceEntry = arrayOfFontFileResourceEntry[paramInt];
      String str = fontFileResourceEntry.getFileName();
      int j = fontFileResourceEntry.getTtcIndex();
      int k = fontFileResourceEntry.getWeight();
      if (fontFileResourceEntry.isItalic()) {
        bool = true;
      } else {
        bool = false;
      } 
      if (!addFontFromAssetManager(paramContext, object, str, j, k, bool, FontVariationAxis.fromFontVariationSettings(fontFileResourceEntry.getVariationSettings()))) {
        abortCreation(object);
        return null;
      } 
    } 
    return !freeze(object) ? null : createFromFamiliesWithDefault(object);
  }
  
  public Typeface createFromFontInfo(Context paramContext, @Nullable CancellationSignal paramCancellationSignal, @NonNull FontsContractCompat.FontInfo[] paramArrayOfFontInfo, int paramInt) {
    if (paramArrayOfFontInfo.length < 1)
      return null; 
    if (!isFontFamilyPrivateAPIAvailable()) {
      FontsContractCompat.FontInfo fontInfo = findBestInfo(paramArrayOfFontInfo, paramInt);
      ContentResolver contentResolver = paramContext.getContentResolver();
      try {
        ParcelFileDescriptor parcelFileDescriptor = contentResolver.openFileDescriptor(fontInfo.getUri(), "r", paramCancellationSignal);
        paramCancellationSignal = null;
        if (parcelFileDescriptor == null) {
          contentResolver = null;
          if (parcelFileDescriptor != null) {
            if (false)
              try {
                parcelFileDescriptor.close();
                return null;
              } catch (Throwable null) {
                throw new NullPointerException();
              }  
            parcelFileDescriptor.close();
            return null;
          } 
          return (Typeface)throwable1;
        } 
        try {
          Typeface typeface1 = (new Typeface.Builder(parcelFileDescriptor.getFileDescriptor())).setWeight(fontInfo.getWeight()).setItalic(fontInfo.isItalic()).build();
          Typeface typeface2 = typeface1;
          typeface1 = typeface2;
          if (parcelFileDescriptor != null) {
            if (false)
              try {
                parcelFileDescriptor.close();
                return typeface2;
              } catch (Throwable null) {
                throw new NullPointerException();
              }  
            parcelFileDescriptor.close();
            return typeface2;
          } 
        } catch (Throwable throwable2) {
          try {
            throw throwable2;
          } finally {}
          if (parcelFileDescriptor != null) {
            if (throwable2 != null) {
              try {
                parcelFileDescriptor.close();
              } catch (Throwable throwable3) {}
              throw contentResolver;
            } 
          } else {
            throw contentResolver;
          } 
          throwable3.close();
          throw contentResolver;
        } finally {}
      } catch (IOException throwable1) {
        return null;
      } 
      return (Typeface)throwable1;
    } 
    Map map = FontsContractCompat.prepareFontData((Context)throwable1, (FontsContractCompat.FontInfo[])throwable3, (CancellationSignal)throwable2);
    Object object = newFamily();
    boolean bool = false;
    int j = throwable3.length;
    for (int i = 0; i < j; i++) {
      Throwable throwable = throwable3[i];
      ByteBuffer byteBuffer = (ByteBuffer)map.get(throwable.getUri());
      if (byteBuffer != null) {
        int m = throwable.getTtcIndex();
        int k = throwable.getWeight();
        if (throwable.isItalic()) {
          bool = true;
        } else {
          bool = false;
        } 
        if (!addFontFromBuffer(object, byteBuffer, m, k, bool)) {
          abortCreation(object);
          return null;
        } 
        bool = true;
      } 
    } 
    if (!bool) {
      abortCreation(object);
      return null;
    } 
    return !freeze(object) ? null : Typeface.create(createFromFamiliesWithDefault(object), paramInt);
  }
  
  @Nullable
  public Typeface createFromResourcesFontFile(Context paramContext, Resources paramResources, int paramInt1, String paramString, int paramInt2) {
    Context context = null;
    if (!isFontFamilyPrivateAPIAvailable())
      return super.createFromResourcesFontFile(paramContext, paramResources, paramInt1, paramString, paramInt2); 
    Object object = newFamily();
    if (!addFontFromAssetManager(paramContext, object, paramString, 0, -1, -1, (FontVariationAxis[])null)) {
      abortCreation(object);
      return null;
    } 
    paramContext = context;
    return (Typeface)(freeze(object) ? createFromFamiliesWithDefault(object) : paramContext);
  }
  
  protected Method obtainAbortCreationMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("abortCreation", new Class[0]);
  }
  
  protected Method obtainAddFontFromAssetManagerMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("addFontFromAssetManager", new Class[] { AssetManager.class, String.class, int.class, boolean.class, int.class, int.class, int.class, FontVariationAxis[].class });
  }
  
  protected Method obtainAddFontFromBufferMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("addFontFromBuffer", new Class[] { ByteBuffer.class, int.class, FontVariationAxis[].class, int.class, int.class });
  }
  
  protected Method obtainCreateFromFamiliesWithDefaultMethod(Class<?> paramClass) throws NoSuchMethodException {
    Method method = Typeface.class.getDeclaredMethod("createFromFamiliesWithDefault", new Class[] { Array.newInstance(paramClass, 1).getClass(), int.class, int.class });
    method.setAccessible(true);
    return method;
  }
  
  protected Class obtainFontFamily() throws ClassNotFoundException {
    return Class.forName("android.graphics.FontFamily");
  }
  
  protected Constructor obtainFontFamilyCtor(Class paramClass) throws NoSuchMethodException {
    return paramClass.getConstructor(new Class[0]);
  }
  
  protected Method obtainFreezeMethod(Class paramClass) throws NoSuchMethodException {
    return paramClass.getMethod("freeze", new Class[0]);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/graphics/TypefaceCompatApi26Impl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */