package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.arch.core.executor.ArchTaskExecutor;
import androidx.arch.core.internal.SafeIterableMap;
import java.util.Map;

public abstract class LiveData<T> {
  static final Object NOT_SET = new Object();
  
  static final int START_VERSION = -1;
  
  int mActiveCount = 0;
  
  private volatile Object mData = NOT_SET;
  
  final Object mDataLock = new Object();
  
  private boolean mDispatchInvalidated;
  
  private boolean mDispatchingValue;
  
  private SafeIterableMap<Observer<? super T>, ObserverWrapper> mObservers = new SafeIterableMap();
  
  volatile Object mPendingData = NOT_SET;
  
  private final Runnable mPostValueRunnable = new Runnable() {
      public void run() {
        synchronized (LiveData.this.mDataLock) {
          Object object = LiveData.this.mPendingData;
          LiveData.this.mPendingData = LiveData.NOT_SET;
          LiveData.this.setValue(object);
          return;
        } 
      }
    };
  
  private int mVersion = -1;
  
  private static void assertMainThread(String paramString) {
    if (!ArchTaskExecutor.getInstance().isMainThread())
      throw new IllegalStateException("Cannot invoke " + paramString + " on a background" + " thread"); 
  }
  
  private void considerNotify(ObserverWrapper paramObserverWrapper) {
    if (paramObserverWrapper.mActive) {
      if (!paramObserverWrapper.shouldBeActive()) {
        paramObserverWrapper.activeStateChanged(false);
        return;
      } 
      if (paramObserverWrapper.mLastVersion < this.mVersion) {
        paramObserverWrapper.mLastVersion = this.mVersion;
        paramObserverWrapper.mObserver.onChanged((T)this.mData);
        return;
      } 
    } 
  }
  
  void dispatchingValue(@Nullable ObserverWrapper paramObserverWrapper) {
    if (this.mDispatchingValue) {
      this.mDispatchInvalidated = true;
      return;
    } 
    this.mDispatchingValue = true;
    label21: while (true) {
      this.mDispatchInvalidated = false;
      if (paramObserverWrapper != null) {
        considerNotify(paramObserverWrapper);
        Object object = null;
        continue;
      } 
      SafeIterableMap.IteratorWithAdditions<Map.Entry> iteratorWithAdditions = this.mObservers.iteratorWithAdditions();
      while (true) {
        ObserverWrapper observerWrapper = paramObserverWrapper;
        if (iteratorWithAdditions.hasNext()) {
          considerNotify((ObserverWrapper)((Map.Entry)iteratorWithAdditions.next()).getValue());
          if (this.mDispatchInvalidated) {
            observerWrapper = paramObserverWrapper;
          } else {
            continue;
          } 
        } 
        paramObserverWrapper = observerWrapper;
        if (!this.mDispatchInvalidated) {
          this.mDispatchingValue = false;
          return;
        } 
        continue label21;
      } 
      break;
    } 
  }
  
  @Nullable
  public T getValue() {
    Object object = this.mData;
    return (T)((object != NOT_SET) ? object : null);
  }
  
  int getVersion() {
    return this.mVersion;
  }
  
  public boolean hasActiveObservers() {
    return (this.mActiveCount > 0);
  }
  
  public boolean hasObservers() {
    return (this.mObservers.size() > 0);
  }
  
  @MainThread
  public void observe(@NonNull LifecycleOwner paramLifecycleOwner, @NonNull Observer<? super T> paramObserver) {
    assertMainThread("observe");
    if (paramLifecycleOwner.getLifecycle().getCurrentState() != Lifecycle.State.DESTROYED) {
      LifecycleBoundObserver lifecycleBoundObserver = new LifecycleBoundObserver(paramLifecycleOwner, paramObserver);
      ObserverWrapper observerWrapper = (ObserverWrapper)this.mObservers.putIfAbsent(paramObserver, lifecycleBoundObserver);
      if (observerWrapper != null && !observerWrapper.isAttachedTo(paramLifecycleOwner))
        throw new IllegalArgumentException("Cannot add the same observer with different lifecycles"); 
      if (observerWrapper == null) {
        paramLifecycleOwner.getLifecycle().addObserver(lifecycleBoundObserver);
        return;
      } 
    } 
  }
  
  @MainThread
  public void observeForever(@NonNull Observer<? super T> paramObserver) {
    assertMainThread("observeForever");
    AlwaysActiveObserver alwaysActiveObserver = new AlwaysActiveObserver(paramObserver);
    ObserverWrapper observerWrapper = (ObserverWrapper)this.mObservers.putIfAbsent(paramObserver, alwaysActiveObserver);
    if (observerWrapper != null && observerWrapper instanceof LifecycleBoundObserver)
      throw new IllegalArgumentException("Cannot add the same observer with different lifecycles"); 
    if (observerWrapper != null)
      return; 
    alwaysActiveObserver.activeStateChanged(true);
  }
  
  protected void onActive() {}
  
  protected void onInactive() {}
  
  protected void postValue(T paramT) {
    synchronized (this.mDataLock) {
      boolean bool;
      if (this.mPendingData == NOT_SET) {
        bool = true;
      } else {
        bool = false;
      } 
      this.mPendingData = paramT;
      if (!bool)
        return; 
    } 
    ArchTaskExecutor.getInstance().postToMainThread(this.mPostValueRunnable);
  }
  
  @MainThread
  public void removeObserver(@NonNull Observer<? super T> paramObserver) {
    assertMainThread("removeObserver");
    ObserverWrapper observerWrapper = (ObserverWrapper)this.mObservers.remove(paramObserver);
    if (observerWrapper == null)
      return; 
    observerWrapper.detachObserver();
    observerWrapper.activeStateChanged(false);
  }
  
  @MainThread
  public void removeObservers(@NonNull LifecycleOwner paramLifecycleOwner) {
    assertMainThread("removeObservers");
    for (Map.Entry entry : this.mObservers) {
      if (((ObserverWrapper)entry.getValue()).isAttachedTo(paramLifecycleOwner))
        removeObserver((Observer<? super T>)entry.getKey()); 
    } 
  }
  
  @MainThread
  protected void setValue(T paramT) {
    assertMainThread("setValue");
    this.mVersion++;
    this.mData = paramT;
    dispatchingValue(null);
  }
  
  private class AlwaysActiveObserver extends ObserverWrapper {
    AlwaysActiveObserver(Observer<? super T> param1Observer) {
      super(param1Observer);
    }
    
    boolean shouldBeActive() {
      return true;
    }
  }
  
  class LifecycleBoundObserver extends ObserverWrapper implements GenericLifecycleObserver {
    @NonNull
    final LifecycleOwner mOwner;
    
    LifecycleBoundObserver(LifecycleOwner param1LifecycleOwner, Observer<? super T> param1Observer) {
      super(param1Observer);
      this.mOwner = param1LifecycleOwner;
    }
    
    void detachObserver() {
      this.mOwner.getLifecycle().removeObserver(this);
    }
    
    boolean isAttachedTo(LifecycleOwner param1LifecycleOwner) {
      return (this.mOwner == param1LifecycleOwner);
    }
    
    public void onStateChanged(LifecycleOwner param1LifecycleOwner, Lifecycle.Event param1Event) {
      if (this.mOwner.getLifecycle().getCurrentState() == Lifecycle.State.DESTROYED) {
        LiveData.this.removeObserver(this.mObserver);
        return;
      } 
      activeStateChanged(shouldBeActive());
    }
    
    boolean shouldBeActive() {
      return this.mOwner.getLifecycle().getCurrentState().isAtLeast(Lifecycle.State.STARTED);
    }
  }
  
  private abstract class ObserverWrapper {
    boolean mActive;
    
    int mLastVersion = -1;
    
    final Observer<? super T> mObserver;
    
    ObserverWrapper(Observer<? super T> param1Observer) {
      this.mObserver = param1Observer;
    }
    
    void activeStateChanged(boolean param1Boolean) {
      byte b = 1;
      if (param1Boolean != this.mActive) {
        boolean bool;
        this.mActive = param1Boolean;
        if (LiveData.this.mActiveCount == 0) {
          bool = true;
        } else {
          bool = false;
        } 
        LiveData liveData = LiveData.this;
        int i = liveData.mActiveCount;
        if (!this.mActive)
          b = -1; 
        liveData.mActiveCount = b + i;
        if (bool && this.mActive)
          LiveData.this.onActive(); 
        if (LiveData.this.mActiveCount == 0 && !this.mActive)
          LiveData.this.onInactive(); 
        if (this.mActive) {
          LiveData.this.dispatchingValue(this);
          return;
        } 
      } 
    }
    
    void detachObserver() {}
    
    boolean isAttachedTo(LifecycleOwner param1LifecycleOwner) {
      return false;
    }
    
    abstract boolean shouldBeActive();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/LiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */