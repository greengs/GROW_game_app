package androidx.lifecycle;

import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CompositeGeneratedAdaptersObserver implements GenericLifecycleObserver {
  private final GeneratedAdapter[] mGeneratedAdapters;
  
  CompositeGeneratedAdaptersObserver(GeneratedAdapter[] paramArrayOfGeneratedAdapter) {
    this.mGeneratedAdapters = paramArrayOfGeneratedAdapter;
  }
  
  public void onStateChanged(LifecycleOwner paramLifecycleOwner, Lifecycle.Event paramEvent) {
    boolean bool = false;
    MethodCallsLogger methodCallsLogger = new MethodCallsLogger();
    GeneratedAdapter[] arrayOfGeneratedAdapter = this.mGeneratedAdapters;
    int j = arrayOfGeneratedAdapter.length;
    int i;
    for (i = 0; i < j; i++)
      arrayOfGeneratedAdapter[i].callMethods(paramLifecycleOwner, paramEvent, false, methodCallsLogger); 
    arrayOfGeneratedAdapter = this.mGeneratedAdapters;
    j = arrayOfGeneratedAdapter.length;
    for (i = bool; i < j; i++)
      arrayOfGeneratedAdapter[i].callMethods(paramLifecycleOwner, paramEvent, true, methodCallsLogger); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/CompositeGeneratedAdaptersObserver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */