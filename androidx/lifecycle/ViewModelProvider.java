package androidx.lifecycle;

import android.app.Application;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import java.lang.reflect.InvocationTargetException;

public class ViewModelProvider {
  private static final String DEFAULT_KEY = "androidx.lifecycle.ViewModelProvider.DefaultKey";
  
  private final Factory mFactory;
  
  private final ViewModelStore mViewModelStore;
  
  public ViewModelProvider(@NonNull ViewModelStore paramViewModelStore, @NonNull Factory paramFactory) {
    this.mFactory = paramFactory;
    this.mViewModelStore = paramViewModelStore;
  }
  
  public ViewModelProvider(@NonNull ViewModelStoreOwner paramViewModelStoreOwner, @NonNull Factory paramFactory) {
    this(paramViewModelStoreOwner.getViewModelStore(), paramFactory);
  }
  
  @MainThread
  @NonNull
  public <T extends ViewModel> T get(@NonNull Class<T> paramClass) {
    String str = paramClass.getCanonicalName();
    if (str == null)
      throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels"); 
    return get("androidx.lifecycle.ViewModelProvider.DefaultKey:" + str, paramClass);
  }
  
  @MainThread
  @NonNull
  public <T extends ViewModel> T get(@NonNull String paramString, @NonNull Class<T> paramClass) {
    ViewModel viewModel = this.mViewModelStore.get(paramString);
    if (paramClass.isInstance(viewModel))
      return (T)viewModel; 
    if (viewModel != null);
    paramClass = this.mFactory.create((Class)paramClass);
    this.mViewModelStore.put(paramString, (ViewModel)paramClass);
    return (T)paramClass;
  }
  
  public static class AndroidViewModelFactory extends NewInstanceFactory {
    private static AndroidViewModelFactory sInstance;
    
    private Application mApplication;
    
    public AndroidViewModelFactory(@NonNull Application param1Application) {
      this.mApplication = param1Application;
    }
    
    @NonNull
    public static AndroidViewModelFactory getInstance(@NonNull Application param1Application) {
      if (sInstance == null)
        sInstance = new AndroidViewModelFactory(param1Application); 
      return sInstance;
    }
    
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> param1Class) {
      if (AndroidViewModel.class.isAssignableFrom(param1Class))
        try {
          return (T)param1Class.getConstructor(new Class[] { Application.class }).newInstance(new Object[] { this.mApplication });
        } catch (NoSuchMethodException noSuchMethodException) {
          throw new RuntimeException("Cannot create an instance of " + param1Class, noSuchMethodException);
        } catch (IllegalAccessException illegalAccessException) {
          throw new RuntimeException("Cannot create an instance of " + param1Class, illegalAccessException);
        } catch (InstantiationException instantiationException) {
          throw new RuntimeException("Cannot create an instance of " + param1Class, instantiationException);
        } catch (InvocationTargetException invocationTargetException) {
          throw new RuntimeException("Cannot create an instance of " + param1Class, invocationTargetException);
        }  
      return super.create(param1Class);
    }
  }
  
  public static interface Factory {
    @NonNull
    <T extends ViewModel> T create(@NonNull Class<T> param1Class);
  }
  
  public static class NewInstanceFactory implements Factory {
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> param1Class) {
      try {
        return param1Class.newInstance();
      } catch (InstantiationException instantiationException) {
        throw new RuntimeException("Cannot create an instance of " + param1Class, instantiationException);
      } catch (IllegalAccessException illegalAccessException) {
        throw new RuntimeException("Cannot create an instance of " + param1Class, illegalAccessException);
      } 
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/ViewModelProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */