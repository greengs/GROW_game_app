package androidx.arch.core.executor;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class TaskExecutor {
  public abstract void executeOnDiskIO(@NonNull Runnable paramRunnable);
  
  public void executeOnMainThread(@NonNull Runnable paramRunnable) {
    if (isMainThread()) {
      paramRunnable.run();
      return;
    } 
    postToMainThread(paramRunnable);
  }
  
  public abstract boolean isMainThread();
  
  public abstract void postToMainThread(@NonNull Runnable paramRunnable);
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/arch/core/executor/TaskExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */