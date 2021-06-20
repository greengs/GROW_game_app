package androidx.appcompat.app;

import android.content.res.Resources;
import android.os.Build;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import java.lang.reflect.Field;
import java.util.Map;

class ResourcesFlusher {
  private static final String TAG = "ResourcesFlusher";
  
  private static Field sDrawableCacheField;
  
  private static boolean sDrawableCacheFieldFetched;
  
  private static Field sResourcesImplField;
  
  private static boolean sResourcesImplFieldFetched;
  
  private static Class sThemedResourceCacheClazz;
  
  private static boolean sThemedResourceCacheClazzFetched;
  
  private static Field sThemedResourceCache_mUnthemedEntriesField;
  
  private static boolean sThemedResourceCache_mUnthemedEntriesFieldFetched;
  
  static void flush(@NonNull Resources paramResources) {
    if (Build.VERSION.SDK_INT < 28) {
      if (Build.VERSION.SDK_INT >= 24) {
        flushNougats(paramResources);
        return;
      } 
      if (Build.VERSION.SDK_INT >= 23) {
        flushMarshmallows(paramResources);
        return;
      } 
      if (Build.VERSION.SDK_INT >= 21) {
        flushLollipops(paramResources);
        return;
      } 
    } 
  }
  
  @RequiresApi(21)
  private static void flushLollipops(@NonNull Resources paramResources) {
    if (!sDrawableCacheFieldFetched) {
      try {
        sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException);
      } 
      sDrawableCacheFieldFetched = true;
    } 
    if (sDrawableCacheField != null) {
      IllegalAccessException illegalAccessException2 = null;
      try {
        Map map = (Map)sDrawableCacheField.get(paramResources);
      } catch (IllegalAccessException illegalAccessException1) {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException1);
        illegalAccessException1 = illegalAccessException2;
      } 
      if (illegalAccessException1 != null)
        illegalAccessException1.clear(); 
    } 
  }
  
  @RequiresApi(23)
  private static void flushMarshmallows(@NonNull Resources paramResources) {
    if (!sDrawableCacheFieldFetched) {
      try {
        sDrawableCacheField = Resources.class.getDeclaredField("mDrawableCache");
        sDrawableCacheField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mDrawableCache field", noSuchFieldException);
      } 
      sDrawableCacheFieldFetched = true;
    } 
    Object object1 = null;
    Object object = object1;
    if (sDrawableCacheField != null) {
      try {
        object = sDrawableCacheField.get(paramResources);
        if (object == null)
          return; 
      } catch (IllegalAccessException illegalAccessException) {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mDrawableCache", illegalAccessException);
        object = object1;
        if (object == null)
          return; 
      } 
      flushThemedResourcesCache(object);
      return;
    } 
    if (object == null)
      return; 
  }
  
  @RequiresApi(24)
  private static void flushNougats(@NonNull Resources paramResources) {
    if (!sResourcesImplFieldFetched) {
      try {
        sResourcesImplField = Resources.class.getDeclaredField("mResourcesImpl");
        sResourcesImplField.setAccessible(true);
      } catch (NoSuchFieldException noSuchFieldException) {
        Log.e("ResourcesFlusher", "Could not retrieve Resources#mResourcesImpl field", noSuchFieldException);
      } 
      sResourcesImplFieldFetched = true;
    } 
    if (sResourcesImplField != null) {
      IllegalAccessException illegalAccessException = null;
      try {
        object = sResourcesImplField.get(paramResources);
      } catch (IllegalAccessException object) {
        Log.e("ResourcesFlusher", "Could not retrieve value from Resources#mResourcesImpl", (Throwable)object);
        object = illegalAccessException;
      } 
      if (object != null) {
        if (!sDrawableCacheFieldFetched) {
          try {
            sDrawableCacheField = object.getClass().getDeclaredField("mDrawableCache");
            sDrawableCacheField.setAccessible(true);
          } catch (NoSuchFieldException noSuchFieldException) {
            Log.e("ResourcesFlusher", "Could not retrieve ResourcesImpl#mDrawableCache field", noSuchFieldException);
          } 
          sDrawableCacheFieldFetched = true;
        } 
        IllegalAccessException illegalAccessException1 = null;
        illegalAccessException = illegalAccessException1;
        if (sDrawableCacheField != null)
          try {
            Object object1 = sDrawableCacheField.get(object);
          } catch (IllegalAccessException illegalAccessException2) {
            Log.e("ResourcesFlusher", "Could not retrieve value from ResourcesImpl#mDrawableCache", illegalAccessException2);
            illegalAccessException = illegalAccessException1;
          }  
        if (illegalAccessException != null) {
          flushThemedResourcesCache(illegalAccessException);
          return;
        } 
      } 
    } 
  }
  
  @RequiresApi(16)
  private static void flushThemedResourcesCache(@NonNull Object paramObject) {
    if (!sThemedResourceCacheClazzFetched) {
      try {
        sThemedResourceCacheClazz = Class.forName("android.content.res.ThemedResourceCache");
      } catch (ClassNotFoundException classNotFoundException) {
        Log.e("ResourcesFlusher", "Could not find ThemedResourceCache class", classNotFoundException);
      } 
      sThemedResourceCacheClazzFetched = true;
    } 
    if (sThemedResourceCacheClazz != null) {
      if (!sThemedResourceCache_mUnthemedEntriesFieldFetched) {
        try {
          sThemedResourceCache_mUnthemedEntriesField = sThemedResourceCacheClazz.getDeclaredField("mUnthemedEntries");
          sThemedResourceCache_mUnthemedEntriesField.setAccessible(true);
        } catch (NoSuchFieldException noSuchFieldException) {
          Log.e("ResourcesFlusher", "Could not retrieve ThemedResourceCache#mUnthemedEntries field", noSuchFieldException);
        } 
        sThemedResourceCache_mUnthemedEntriesFieldFetched = true;
      } 
      if (sThemedResourceCache_mUnthemedEntriesField != null) {
        IllegalAccessException illegalAccessException2 = null;
        try {
          paramObject = sThemedResourceCache_mUnthemedEntriesField.get(paramObject);
        } catch (IllegalAccessException illegalAccessException1) {
          Log.e("ResourcesFlusher", "Could not retrieve value from ThemedResourceCache#mUnthemedEntries", illegalAccessException1);
          illegalAccessException1 = illegalAccessException2;
        } 
        if (illegalAccessException1 != null) {
          illegalAccessException1.clear();
          return;
        } 
      } 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/appcompat/app/ResourcesFlusher.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */