package androidx.loader.app;

import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.collection.SparseArrayCompat;
import androidx.core.util.DebugUtils;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Modifier;

class LoaderManagerImpl extends LoaderManager {
  static boolean DEBUG = false;
  
  static final String TAG = "LoaderManager";
  
  @NonNull
  private final LifecycleOwner mLifecycleOwner;
  
  @NonNull
  private final LoaderViewModel mLoaderViewModel;
  
  LoaderManagerImpl(@NonNull LifecycleOwner paramLifecycleOwner, @NonNull ViewModelStore paramViewModelStore) {
    this.mLifecycleOwner = paramLifecycleOwner;
    this.mLoaderViewModel = LoaderViewModel.getInstance(paramViewModelStore);
  }
  
  @MainThread
  @NonNull
  private <D> Loader<D> createAndInstallLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks, @Nullable Loader<D> paramLoader) {
    Loader<D> loader;
    try {
      this.mLoaderViewModel.startCreatingLoader();
      loader = paramLoaderCallbacks.onCreateLoader(paramInt, paramBundle);
    } finally {
      this.mLoaderViewModel.finishCreatingLoader();
    } 
    if (loader.getClass().isMemberClass() && !Modifier.isStatic(loader.getClass().getModifiers()))
      throw new IllegalArgumentException("Object returned from onCreateLoader must not be a non-static inner member class: " + loader); 
    LoaderInfo<D> loaderInfo = new LoaderInfo<D>(paramInt, paramBundle, loader, paramLoader);
    if (DEBUG)
      Log.v("LoaderManager", "  Created new loader " + loaderInfo); 
    this.mLoaderViewModel.putLoader(paramInt, loaderInfo);
    this.mLoaderViewModel.finishCreatingLoader();
    return loaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
  }
  
  @MainThread
  public void destroyLoader(int paramInt) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("destroyLoader must be called on the main thread"); 
    if (DEBUG)
      Log.v("LoaderManager", "destroyLoader in " + this + " of " + paramInt); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (loaderInfo != null) {
      loaderInfo.destroy(true);
      this.mLoaderViewModel.removeLoader(paramInt);
    } 
  }
  
  @Deprecated
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString) {
    this.mLoaderViewModel.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
  }
  
  @Nullable
  public <D> Loader<D> getLoader(int paramInt) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    return (Loader)((loaderInfo != null) ? loaderInfo.getLoader() : null);
  }
  
  public boolean hasRunningLoaders() {
    return this.mLoaderViewModel.hasRunningLoaders();
  }
  
  @MainThread
  @NonNull
  public <D> Loader<D> initLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("initLoader must be called on the main thread"); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    if (DEBUG)
      Log.v("LoaderManager", "initLoader in " + this + ": args=" + paramBundle); 
    if (loaderInfo == null)
      return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, null); 
    if (DEBUG)
      Log.v("LoaderManager", "  Re-using existing loader " + loaderInfo); 
    return (Loader)loaderInfo.setCallback(this.mLifecycleOwner, paramLoaderCallbacks);
  }
  
  public void markForRedelivery() {
    this.mLoaderViewModel.markForRedelivery();
  }
  
  @MainThread
  @NonNull
  public <D> Loader<D> restartLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderManager.LoaderCallbacks<D> paramLoaderCallbacks) {
    if (this.mLoaderViewModel.isCreatingLoader())
      throw new IllegalStateException("Called while creating a loader"); 
    if (Looper.getMainLooper() != Looper.myLooper())
      throw new IllegalStateException("restartLoader must be called on the main thread"); 
    if (DEBUG)
      Log.v("LoaderManager", "restartLoader in " + this + ": args=" + paramBundle); 
    LoaderInfo<?> loaderInfo = this.mLoaderViewModel.getLoader(paramInt);
    Loader<?> loader = null;
    if (loaderInfo != null)
      loader = loaderInfo.destroy(false); 
    return createAndInstallLoader(paramInt, paramBundle, paramLoaderCallbacks, (Loader)loader);
  }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder(128);
    stringBuilder.append("LoaderManager{");
    stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    stringBuilder.append(" in ");
    DebugUtils.buildShortClassTag(this.mLifecycleOwner, stringBuilder);
    stringBuilder.append("}}");
    return stringBuilder.toString();
  }
  
  public static class LoaderInfo<D> extends MutableLiveData<D> implements Loader.OnLoadCompleteListener<D> {
    @Nullable
    private final Bundle mArgs;
    
    private final int mId;
    
    private LifecycleOwner mLifecycleOwner;
    
    @NonNull
    private final Loader<D> mLoader;
    
    private LoaderManagerImpl.LoaderObserver<D> mObserver;
    
    private Loader<D> mPriorLoader;
    
    LoaderInfo(int param1Int, @Nullable Bundle param1Bundle, @NonNull Loader<D> param1Loader1, @Nullable Loader<D> param1Loader2) {
      this.mId = param1Int;
      this.mArgs = param1Bundle;
      this.mLoader = param1Loader1;
      this.mPriorLoader = param1Loader2;
      this.mLoader.registerListener(param1Int, this);
    }
    
    @MainThread
    Loader<D> destroy(boolean param1Boolean) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Destroying: " + this); 
      this.mLoader.cancelLoad();
      this.mLoader.abandon();
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
      if (loaderObserver != null) {
        removeObserver(loaderObserver);
        if (param1Boolean)
          loaderObserver.reset(); 
      } 
      this.mLoader.unregisterListener(this);
      if ((loaderObserver != null && !loaderObserver.hasDeliveredData()) || param1Boolean) {
        this.mLoader.reset();
        return this.mPriorLoader;
      } 
      return this.mLoader;
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mId=");
      param1PrintWriter.print(this.mId);
      param1PrintWriter.print(" mArgs=");
      param1PrintWriter.println(this.mArgs);
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mLoader=");
      param1PrintWriter.println(this.mLoader);
      this.mLoader.dump(param1String + "  ", param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
      if (this.mObserver != null) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.print("mCallbacks=");
        param1PrintWriter.println(this.mObserver);
        this.mObserver.dump(param1String + "  ", param1PrintWriter);
      } 
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mData=");
      param1PrintWriter.println(getLoader().dataToString(getValue()));
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mStarted=");
      param1PrintWriter.println(hasActiveObservers());
    }
    
    @NonNull
    Loader<D> getLoader() {
      return this.mLoader;
    }
    
    boolean isCallbackWaitingForData() {
      return (hasActiveObservers() && this.mObserver != null && !this.mObserver.hasDeliveredData());
    }
    
    void markForRedelivery() {
      LifecycleOwner lifecycleOwner = this.mLifecycleOwner;
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = this.mObserver;
      if (lifecycleOwner != null && loaderObserver != null) {
        super.removeObserver(loaderObserver);
        observe(lifecycleOwner, loaderObserver);
      } 
    }
    
    protected void onActive() {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Starting: " + this); 
      this.mLoader.startLoading();
    }
    
    protected void onInactive() {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  Stopping: " + this); 
      this.mLoader.stopLoading();
    }
    
    public void onLoadComplete(@NonNull Loader<D> param1Loader, @Nullable D param1D) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "onLoadComplete: " + this); 
      if (Looper.myLooper() == Looper.getMainLooper()) {
        setValue(param1D);
        return;
      } 
      if (LoaderManagerImpl.DEBUG)
        Log.w("LoaderManager", "onLoadComplete was incorrectly called on a background thread"); 
      postValue(param1D);
    }
    
    public void removeObserver(@NonNull Observer<? super D> param1Observer) {
      super.removeObserver(param1Observer);
      this.mLifecycleOwner = null;
      this.mObserver = null;
    }
    
    @MainThread
    @NonNull
    Loader<D> setCallback(@NonNull LifecycleOwner param1LifecycleOwner, @NonNull LoaderManager.LoaderCallbacks<D> param1LoaderCallbacks) {
      LoaderManagerImpl.LoaderObserver<D> loaderObserver = new LoaderManagerImpl.LoaderObserver<D>(this.mLoader, param1LoaderCallbacks);
      observe(param1LifecycleOwner, loaderObserver);
      if (this.mObserver != null)
        removeObserver(this.mObserver); 
      this.mLifecycleOwner = param1LifecycleOwner;
      this.mObserver = loaderObserver;
      return this.mLoader;
    }
    
    public void setValue(D param1D) {
      super.setValue(param1D);
      if (this.mPriorLoader != null) {
        this.mPriorLoader.reset();
        this.mPriorLoader = null;
      } 
    }
    
    public String toString() {
      StringBuilder stringBuilder = new StringBuilder(64);
      stringBuilder.append("LoaderInfo{");
      stringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      stringBuilder.append(" #");
      stringBuilder.append(this.mId);
      stringBuilder.append(" : ");
      DebugUtils.buildShortClassTag(this.mLoader, stringBuilder);
      stringBuilder.append("}}");
      return stringBuilder.toString();
    }
  }
  
  static class LoaderObserver<D> implements Observer<D> {
    @NonNull
    private final LoaderManager.LoaderCallbacks<D> mCallback;
    
    private boolean mDeliveredData = false;
    
    @NonNull
    private final Loader<D> mLoader;
    
    LoaderObserver(@NonNull Loader<D> param1Loader, @NonNull LoaderManager.LoaderCallbacks<D> param1LoaderCallbacks) {
      this.mLoader = param1Loader;
      this.mCallback = param1LoaderCallbacks;
    }
    
    public void dump(String param1String, PrintWriter param1PrintWriter) {
      param1PrintWriter.print(param1String);
      param1PrintWriter.print("mDeliveredData=");
      param1PrintWriter.println(this.mDeliveredData);
    }
    
    boolean hasDeliveredData() {
      return this.mDeliveredData;
    }
    
    public void onChanged(@Nullable D param1D) {
      if (LoaderManagerImpl.DEBUG)
        Log.v("LoaderManager", "  onLoadFinished in " + this.mLoader + ": " + this.mLoader.dataToString(param1D)); 
      this.mCallback.onLoadFinished(this.mLoader, param1D);
      this.mDeliveredData = true;
    }
    
    @MainThread
    void reset() {
      if (this.mDeliveredData) {
        if (LoaderManagerImpl.DEBUG)
          Log.v("LoaderManager", "  Resetting: " + this.mLoader); 
        this.mCallback.onLoaderReset(this.mLoader);
      } 
    }
    
    public String toString() {
      return this.mCallback.toString();
    }
  }
  
  static class LoaderViewModel extends ViewModel {
    private static final ViewModelProvider.Factory FACTORY = new ViewModelProvider.Factory() {
        @NonNull
        public <T extends ViewModel> T create(@NonNull Class<T> param2Class) {
          return (T)new LoaderManagerImpl.LoaderViewModel();
        }
      };
    
    private boolean mCreatingLoader = false;
    
    private SparseArrayCompat<LoaderManagerImpl.LoaderInfo> mLoaders = new SparseArrayCompat();
    
    @NonNull
    static LoaderViewModel getInstance(ViewModelStore param1ViewModelStore) {
      return (LoaderViewModel)(new ViewModelProvider(param1ViewModelStore, FACTORY)).get(LoaderViewModel.class);
    }
    
    public void dump(String param1String, FileDescriptor param1FileDescriptor, PrintWriter param1PrintWriter, String[] param1ArrayOfString) {
      if (this.mLoaders.size() > 0) {
        param1PrintWriter.print(param1String);
        param1PrintWriter.println("Loaders:");
        String str = param1String + "    ";
        int i;
        for (i = 0; i < this.mLoaders.size(); i++) {
          LoaderManagerImpl.LoaderInfo loaderInfo = (LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i);
          param1PrintWriter.print(param1String);
          param1PrintWriter.print("  #");
          param1PrintWriter.print(this.mLoaders.keyAt(i));
          param1PrintWriter.print(": ");
          param1PrintWriter.println(loaderInfo.toString());
          loaderInfo.dump(str, param1FileDescriptor, param1PrintWriter, param1ArrayOfString);
        } 
      } 
    }
    
    void finishCreatingLoader() {
      this.mCreatingLoader = false;
    }
    
    <D> LoaderManagerImpl.LoaderInfo<D> getLoader(int param1Int) {
      return (LoaderManagerImpl.LoaderInfo<D>)this.mLoaders.get(param1Int);
    }
    
    boolean hasRunningLoaders() {
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++) {
        if (((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).isCallbackWaitingForData())
          return true; 
      } 
      return false;
    }
    
    boolean isCreatingLoader() {
      return this.mCreatingLoader;
    }
    
    void markForRedelivery() {
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).markForRedelivery(); 
    }
    
    protected void onCleared() {
      super.onCleared();
      int j = this.mLoaders.size();
      for (int i = 0; i < j; i++)
        ((LoaderManagerImpl.LoaderInfo)this.mLoaders.valueAt(i)).destroy(true); 
      this.mLoaders.clear();
    }
    
    void putLoader(int param1Int, @NonNull LoaderManagerImpl.LoaderInfo param1LoaderInfo) {
      this.mLoaders.put(param1Int, param1LoaderInfo);
    }
    
    void removeLoader(int param1Int) {
      this.mLoaders.remove(param1Int);
    }
    
    void startCreatingLoader() {
      this.mCreatingLoader = true;
    }
  }
  
  static final class null implements ViewModelProvider.Factory {
    @NonNull
    public <T extends ViewModel> T create(@NonNull Class<T> param1Class) {
      return (T)new LoaderManagerImpl.LoaderViewModel();
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/loader/app/LoaderManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */