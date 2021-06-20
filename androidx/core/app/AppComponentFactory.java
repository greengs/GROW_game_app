package androidx.core.app;

import android.app.Activity;
import android.app.AppComponentFactory;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ContentProvider;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import java.lang.reflect.InvocationTargetException;

@RequiresApi(28)
public class AppComponentFactory extends AppComponentFactory {
  public final Activity instantiateActivity(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return CoreComponentFactory.<Activity>checkCompatWrapper(instantiateActivityCompat(paramClassLoader, paramString, paramIntent));
  }
  
  @NonNull
  public Activity instantiateActivityCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    try {
      return paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new RuntimeException("Couldn't call constructor", noSuchMethodException);
  }
  
  public final Application instantiateApplication(ClassLoader paramClassLoader, String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return CoreComponentFactory.<Application>checkCompatWrapper(instantiateApplicationCompat(paramClassLoader, paramString));
  }
  
  @NonNull
  public Application instantiateApplicationCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    try {
      return paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new RuntimeException("Couldn't call constructor", noSuchMethodException);
  }
  
  public final ContentProvider instantiateProvider(ClassLoader paramClassLoader, String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return CoreComponentFactory.<ContentProvider>checkCompatWrapper(instantiateProviderCompat(paramClassLoader, paramString));
  }
  
  @NonNull
  public ContentProvider instantiateProviderCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    try {
      return paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new RuntimeException("Couldn't call constructor", noSuchMethodException);
  }
  
  public final BroadcastReceiver instantiateReceiver(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return CoreComponentFactory.<BroadcastReceiver>checkCompatWrapper(instantiateReceiverCompat(paramClassLoader, paramString, paramIntent));
  }
  
  @NonNull
  public BroadcastReceiver instantiateReceiverCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    try {
      return paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new RuntimeException("Couldn't call constructor", noSuchMethodException);
  }
  
  public final Service instantiateService(ClassLoader paramClassLoader, String paramString, Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    return CoreComponentFactory.<Service>checkCompatWrapper(instantiateServiceCompat(paramClassLoader, paramString, paramIntent));
  }
  
  @NonNull
  public Service instantiateServiceCompat(@NonNull ClassLoader paramClassLoader, @NonNull String paramString, @Nullable Intent paramIntent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    try {
      return paramClassLoader.loadClass(paramString).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
    } catch (InvocationTargetException invocationTargetException) {
    
    } catch (NoSuchMethodException noSuchMethodException) {}
    throw new RuntimeException("Couldn't call constructor", noSuchMethodException);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/app/AppComponentFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */