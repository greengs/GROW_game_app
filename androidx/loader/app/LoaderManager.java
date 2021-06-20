package androidx.loader.app;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.loader.content.Loader;
import java.io.FileDescriptor;
import java.io.PrintWriter;

public abstract class LoaderManager {
  public static void enableDebugLogging(boolean paramBoolean) {
    LoaderManagerImpl.DEBUG = paramBoolean;
  }
  
  @NonNull
  public static <T extends LifecycleOwner & ViewModelStoreOwner> LoaderManager getInstance(@NonNull T paramT) {
    return new LoaderManagerImpl((LifecycleOwner)paramT, ((ViewModelStoreOwner)paramT).getViewModelStore());
  }
  
  @MainThread
  public abstract void destroyLoader(int paramInt);
  
  @Deprecated
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @Nullable
  public abstract <D> Loader<D> getLoader(int paramInt);
  
  public boolean hasRunningLoaders() {
    return false;
  }
  
  @MainThread
  @NonNull
  public abstract <D> Loader<D> initLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderCallbacks<D> paramLoaderCallbacks);
  
  public abstract void markForRedelivery();
  
  @MainThread
  @NonNull
  public abstract <D> Loader<D> restartLoader(int paramInt, @Nullable Bundle paramBundle, @NonNull LoaderCallbacks<D> paramLoaderCallbacks);
  
  public static interface LoaderCallbacks<D> {
    @MainThread
    @NonNull
    Loader<D> onCreateLoader(int param1Int, @Nullable Bundle param1Bundle);
    
    @MainThread
    void onLoadFinished(@NonNull Loader<D> param1Loader, D param1D);
    
    @MainThread
    void onLoaderReset(@NonNull Loader<D> param1Loader);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/loader/app/LoaderManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */