package androidx.arch.core.executor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import java.util.concurrent.Executor;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class ArchTaskExecutor extends TaskExecutor {
  @NonNull
  private static final Executor sIOThreadExecutor;
  
  private static volatile ArchTaskExecutor sInstance;
  
  @NonNull
  private static final Executor sMainThreadExecutor = new Executor() {
      public void execute(Runnable param1Runnable) {
        ArchTaskExecutor.getInstance().postToMainThread(param1Runnable);
      }
    };
  
  @NonNull
  private TaskExecutor mDefaultTaskExecutor = new DefaultTaskExecutor();
  
  @NonNull
  private TaskExecutor mDelegate = this.mDefaultTaskExecutor;
  
  static {
    sIOThreadExecutor = new Executor() {
        public void execute(Runnable param1Runnable) {
          ArchTaskExecutor.getInstance().executeOnDiskIO(param1Runnable);
        }
      };
  }
  
  @NonNull
  public static Executor getIOThreadExecutor() {
    return sIOThreadExecutor;
  }
  
  @NonNull
  public static ArchTaskExecutor getInstance() {
    // Byte code:
    //   0: getstatic androidx/arch/core/executor/ArchTaskExecutor.sInstance : Landroidx/arch/core/executor/ArchTaskExecutor;
    //   3: ifnull -> 10
    //   6: getstatic androidx/arch/core/executor/ArchTaskExecutor.sInstance : Landroidx/arch/core/executor/ArchTaskExecutor;
    //   9: areturn
    //   10: ldc androidx/arch/core/executor/ArchTaskExecutor
    //   12: monitorenter
    //   13: getstatic androidx/arch/core/executor/ArchTaskExecutor.sInstance : Landroidx/arch/core/executor/ArchTaskExecutor;
    //   16: ifnonnull -> 29
    //   19: new androidx/arch/core/executor/ArchTaskExecutor
    //   22: dup
    //   23: invokespecial <init> : ()V
    //   26: putstatic androidx/arch/core/executor/ArchTaskExecutor.sInstance : Landroidx/arch/core/executor/ArchTaskExecutor;
    //   29: ldc androidx/arch/core/executor/ArchTaskExecutor
    //   31: monitorexit
    //   32: getstatic androidx/arch/core/executor/ArchTaskExecutor.sInstance : Landroidx/arch/core/executor/ArchTaskExecutor;
    //   35: areturn
    //   36: astore_0
    //   37: ldc androidx/arch/core/executor/ArchTaskExecutor
    //   39: monitorexit
    //   40: aload_0
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   13	29	36	finally
    //   29	32	36	finally
    //   37	40	36	finally
  }
  
  @NonNull
  public static Executor getMainThreadExecutor() {
    return sMainThreadExecutor;
  }
  
  public void executeOnDiskIO(Runnable paramRunnable) {
    this.mDelegate.executeOnDiskIO(paramRunnable);
  }
  
  public boolean isMainThread() {
    return this.mDelegate.isMainThread();
  }
  
  public void postToMainThread(Runnable paramRunnable) {
    this.mDelegate.postToMainThread(paramRunnable);
  }
  
  public void setDelegate(@Nullable TaskExecutor paramTaskExecutor) {
    TaskExecutor taskExecutor = paramTaskExecutor;
    if (paramTaskExecutor == null)
      taskExecutor = this.mDefaultTaskExecutor; 
    this.mDelegate = taskExecutor;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/arch/core/executor/ArchTaskExecutor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */