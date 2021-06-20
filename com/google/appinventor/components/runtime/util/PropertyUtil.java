package com.google.appinventor.components.runtime.util;

import com.google.appinventor.components.annotations.SimpleProperty;
import com.google.appinventor.components.annotations.SimplePropertyCopier;
import com.google.appinventor.components.runtime.Component;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PropertyUtil {
  public static Component copyComponentProperties(Component paramComponent1, Component paramComponent2) throws Throwable {
    if (!paramComponent1.getClass().equals(paramComponent2.getClass()))
      throw new IllegalArgumentException("Source and target classes must be identical"); 
    Class<?> clazz = paramComponent1.getClass();
    Method[] arrayOfMethod = clazz.getMethods();
    int j = arrayOfMethod.length;
    for (int i = 0;; i++) {
      if (i < j) {
        Method method = arrayOfMethod[i];
        if (method.isAnnotationPresent((Class)SimpleProperty.class) && (method.getParameterTypes()).length == 1)
          try {
            String str = method.getName();
            Method method1 = getPropertyCopierMethod("Copy" + str, clazz);
            if (method1 != null) {
              method1.invoke(paramComponent2, new Object[] { paramComponent1 });
            } else {
              Method method2 = clazz.getMethod(str, new Class[0]);
              Class<?> clazz1 = method.getParameterTypes()[0];
              if (method2.isAnnotationPresent((Class)SimpleProperty.class) && clazz1.isAssignableFrom(method2.getReturnType()))
                method.invoke(paramComponent2, new Object[] { method2.invoke(paramComponent1, new Object[0]) }); 
            } 
          } catch (NoSuchMethodException noSuchMethodException) {
          
          } catch (InvocationTargetException invocationTargetException) {
            throw invocationTargetException.getCause();
          }  
      } else {
        return paramComponent2;
      } 
    } 
  }
  
  private static Method getPropertyCopierMethod(String paramString, Class paramClass) {
    while (true) {
      try {
        Method method = paramClass.getMethod(paramString, new Class[] { paramClass });
        boolean bool = method.isAnnotationPresent((Class)SimplePropertyCopier.class);
        if (bool)
          return method; 
      } catch (NoSuchMethodException noSuchMethodException) {}
      Class clazz = paramClass.getSuperclass();
      paramClass = clazz;
      if (clazz == null)
        return null; 
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/com/google/appinventor/components/runtime/util/PropertyUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */