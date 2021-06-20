package androidx.lifecycle;

interface FullLifecycleObserver extends LifecycleObserver {
  void onCreate(LifecycleOwner paramLifecycleOwner);
  
  void onDestroy(LifecycleOwner paramLifecycleOwner);
  
  void onPause(LifecycleOwner paramLifecycleOwner);
  
  void onResume(LifecycleOwner paramLifecycleOwner);
  
  void onStart(LifecycleOwner paramLifecycleOwner);
  
  void onStop(LifecycleOwner paramLifecycleOwner);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/FullLifecycleObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */