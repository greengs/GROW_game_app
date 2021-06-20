package androidx.lifecycle;

class FullLifecycleObserverAdapter implements GenericLifecycleObserver {
  private final FullLifecycleObserver mObserver;
  
  FullLifecycleObserverAdapter(FullLifecycleObserver paramFullLifecycleObserver) {
    this.mObserver = paramFullLifecycleObserver;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent) {
    switch (paramEvent) {
      default:
        return;
      case ON_CREATE:
        this.mObserver.onCreate(paramLifecycleOwner);
        return;
      case ON_START:
        this.mObserver.onStart(paramLifecycleOwner);
        return;
      case ON_RESUME:
        this.mObserver.onResume(paramLifecycleOwner);
        return;
      case ON_PAUSE:
        this.mObserver.onPause(paramLifecycleOwner);
        return;
      case ON_STOP:
        this.mObserver.onStop(paramLifecycleOwner);
        return;
      case ON_DESTROY:
        this.mObserver.onDestroy(paramLifecycleOwner);
        return;
      case ON_ANY:
        break;
    } 
    throw new IllegalArgumentException("ON_ANY must not been send by anybody");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/FullLifecycleObserverAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */