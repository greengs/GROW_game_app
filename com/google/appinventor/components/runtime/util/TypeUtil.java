package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.runtime.errors.DispatchableError;

public final class TypeUtil {
  public static <T> T cast(Object paramObject, Class<T> paramClass, String paramString) {
    if (paramObject == null)
      return null; 
    if (paramClass.isInstance(paramObject))
      return paramClass.cast(paramObject); 
    throw new DispatchableError(3410, new Object[] { paramObject.getClass().getSimpleName(), paramString });
  }
  
  public static <T> T castNotNull(Object paramObject, Class<T> paramClass, String paramString) {
    if (paramObject == null)
      throw new DispatchableError(3410, new Object[] { "null", paramString }); 
    return cast(paramObject, paramClass, paramString);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/TypeUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */