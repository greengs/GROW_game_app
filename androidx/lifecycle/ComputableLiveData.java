package androidx.lifecycle;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.arch.core.executor.ArchTaskExecutor;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public abstract class ComputableLiveData<T> {
  final AtomicBoolean mComputing = new AtomicBoolean(false);
  
  final Executor mExecutor;
  
  final AtomicBoolean mInvalid = new AtomicBoolean(true);
  
  @VisibleForTesting
  final Runnable mInvalidationRunnable = new Runnable() {
      @MainThread
      public void run() {
        boolean bool = ComputableLiveData.this.mLiveData.hasActiveObservers();
        if (ComputableLiveData.this.mInvalid.compareAndSet(false, true) && bool)
          ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable); 
      }
    };
  
  final LiveData<T> mLiveData;
  
  @VisibleForTesting
  final Runnable mRefreshRunnable = new Runnable() {
      @WorkerThread
      public void run() {
        // Byte code:
        //   0: iconst_0
        //   1: istore_1
        //   2: iconst_0
        //   3: istore_2
        //   4: aload_0
        //   5: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   8: getfield mComputing : Ljava/util/concurrent/atomic/AtomicBoolean;
        //   11: iconst_0
        //   12: iconst_1
        //   13: invokevirtual compareAndSet : (ZZ)Z
        //   16: ifeq -> 77
        //   19: aconst_null
        //   20: astore_3
        //   21: iload_2
        //   22: istore_1
        //   23: aload_0
        //   24: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   27: getfield mInvalid : Ljava/util/concurrent/atomic/AtomicBoolean;
        //   30: iconst_1
        //   31: iconst_0
        //   32: invokevirtual compareAndSet : (ZZ)Z
        //   35: ifeq -> 51
        //   38: iconst_1
        //   39: istore_1
        //   40: aload_0
        //   41: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   44: invokevirtual compute : ()Ljava/lang/Object;
        //   47: astore_3
        //   48: goto -> 23
        //   51: iload_1
        //   52: ifeq -> 66
        //   55: aload_0
        //   56: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   59: getfield mLiveData : Landroidx/lifecycle/LiveData;
        //   62: aload_3
        //   63: invokevirtual postValue : (Ljava/lang/Object;)V
        //   66: aload_0
        //   67: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   70: getfield mComputing : Ljava/util/concurrent/atomic/AtomicBoolean;
        //   73: iconst_0
        //   74: invokevirtual set : (Z)V
        //   77: iload_1
        //   78: ifeq -> 94
        //   81: aload_0
        //   82: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   85: getfield mInvalid : Ljava/util/concurrent/atomic/AtomicBoolean;
        //   88: invokevirtual get : ()Z
        //   91: ifne -> 0
        //   94: return
        //   95: astore_3
        //   96: aload_0
        //   97: getfield this$0 : Landroidx/lifecycle/ComputableLiveData;
        //   100: getfield mComputing : Ljava/util/concurrent/atomic/AtomicBoolean;
        //   103: iconst_0
        //   104: invokevirtual set : (Z)V
        //   107: aload_3
        //   108: athrow
        // Exception table:
        //   from	to	target	type
        //   23	38	95	finally
        //   40	48	95	finally
        //   55	66	95	finally
      }
    };
  
  public ComputableLiveData() {
    this(ArchTaskExecutor.getIOThreadExecutor());
  }
  
  public ComputableLiveData(@NonNull Executor paramExecutor) {
    this.mExecutor = paramExecutor;
    this.mLiveData = new LiveData<T>() {
        protected void onActive() {
          ComputableLiveData.this.mExecutor.execute(ComputableLiveData.this.mRefreshRunnable);
        }
      };
  }
  
  @WorkerThread
  protected abstract T compute();
  
  @NonNull
  public LiveData<T> getLiveData() {
    return this.mLiveData;
  }
  
  public void invalidate() {
    ArchTaskExecutor.getInstance().executeOnMainThread(this.mInvalidationRunnable);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/lifecycle/ComputableLiveData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */