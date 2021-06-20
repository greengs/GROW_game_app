package androidx.lifecycle;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Iterator;
import java.util.Map;

public class MediatorLiveData<T> extends MutableLiveData<T> {
  private SafeIterableMap<LiveData<?>, Source<?>> mSources = new SafeIterableMap();
  
  @MainThread
  public <S> void addSource(@NonNull LiveData<S> paramLiveData, @NonNull Observer<? super S> paramObserver) {
    Source<S> source1 = new Source<S>(paramLiveData, paramObserver);
    Source source = (Source)this.mSources.putIfAbsent(paramLiveData, source1);
    if (source != null && source.mObserver != paramObserver)
      throw new IllegalArgumentException("This source was already added with the different observer"); 
    if (source == null && hasActiveObservers()) {
      source1.plug();
      return;
    } 
  }
  
  @CallSuper
  protected void onActive() {
    Iterator<Map.Entry> iterator = this.mSources.iterator();
    while (iterator.hasNext())
      ((Source)((Map.Entry)iterator.next()).getValue()).plug(); 
  }
  
  @CallSuper
  protected void onInactive() {
    Iterator<Map.Entry> iterator = this.mSources.iterator();
    while (iterator.hasNext())
      ((Source)((Map.Entry)iterator.next()).getValue()).unplug(); 
  }
  
  @MainThread
  public <S> void removeSource(@NonNull LiveData<S> paramLiveData) {
    Source source = (Source)this.mSources.remove(paramLiveData);
    if (source != null)
      source.unplug(); 
  }
  
  private static class Source<V> implements Observer<V> {
    final LiveData<V> mLiveData;
    
    final Observer<? super V> mObserver;
    
    int mVersion = -1;
    
    Source(LiveData<V> param1LiveData, Observer<? super V> param1Observer) {
      this.mLiveData = param1LiveData;
      this.mObserver = param1Observer;
    }
    
    public void onChanged(@Nullable V param1V) {
      if (this.mVersion != this.mLiveData.getVersion()) {
        this.mVersion = this.mLiveData.getVersion();
        this.mObserver.onChanged(param1V);
      } 
    }
    
    void plug() {
      this.mLiveData.observeForever(this);
    }
    
    void unplug() {
      this.mLiveData.removeObserver(this);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/MediatorLiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */