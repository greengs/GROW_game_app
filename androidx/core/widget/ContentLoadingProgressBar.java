package androidx.core.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentLoadingProgressBar extends ProgressBar {
  private static final int MIN_DELAY = 500;
  
  private static final int MIN_SHOW_TIME = 500;
  
  private final Runnable mDelayedHide = new Runnable() {
      public void run() {
        ContentLoadingProgressBar.this.mPostedHide = false;
        ContentLoadingProgressBar.this.mStartTime = -1L;
        ContentLoadingProgressBar.this.setVisibility(8);
      }
    };
  
  private final Runnable mDelayedShow = new Runnable() {
      public void run() {
        ContentLoadingProgressBar.this.mPostedShow = false;
        if (!ContentLoadingProgressBar.this.mDismissed) {
          ContentLoadingProgressBar.this.mStartTime = System.currentTimeMillis();
          ContentLoadingProgressBar.this.setVisibility(0);
        } 
      }
    };
  
  boolean mDismissed = false;
  
  boolean mPostedHide = false;
  
  boolean mPostedShow = false;
  
  long mStartTime = -1L;
  
  public ContentLoadingProgressBar(@NonNull Context paramContext) {
    this(paramContext, (AttributeSet)null);
  }
  
  public ContentLoadingProgressBar(@NonNull Context paramContext, @Nullable AttributeSet paramAttributeSet) {
    super(paramContext, paramAttributeSet, 0);
  }
  
  private void removeCallbacks() {
    removeCallbacks(this.mDelayedHide);
    removeCallbacks(this.mDelayedShow);
  }
  
  public void hide() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: iconst_1
    //   4: putfield mDismissed : Z
    //   7: aload_0
    //   8: aload_0
    //   9: getfield mDelayedShow : Ljava/lang/Runnable;
    //   12: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)Z
    //   15: pop
    //   16: aload_0
    //   17: iconst_0
    //   18: putfield mPostedShow : Z
    //   21: invokestatic currentTimeMillis : ()J
    //   24: aload_0
    //   25: getfield mStartTime : J
    //   28: lsub
    //   29: lstore_1
    //   30: lload_1
    //   31: ldc2_w 500
    //   34: lcmp
    //   35: ifge -> 49
    //   38: aload_0
    //   39: getfield mStartTime : J
    //   42: ldc2_w -1
    //   45: lcmp
    //   46: ifne -> 58
    //   49: aload_0
    //   50: bipush #8
    //   52: invokevirtual setVisibility : (I)V
    //   55: aload_0
    //   56: monitorexit
    //   57: return
    //   58: aload_0
    //   59: getfield mPostedHide : Z
    //   62: ifne -> 55
    //   65: aload_0
    //   66: aload_0
    //   67: getfield mDelayedHide : Ljava/lang/Runnable;
    //   70: ldc2_w 500
    //   73: lload_1
    //   74: lsub
    //   75: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   78: pop
    //   79: aload_0
    //   80: iconst_1
    //   81: putfield mPostedHide : Z
    //   84: goto -> 55
    //   87: astore_3
    //   88: aload_0
    //   89: monitorexit
    //   90: aload_3
    //   91: athrow
    // Exception table:
    //   from	to	target	type
    //   2	30	87	finally
    //   38	49	87	finally
    //   49	55	87	finally
    //   58	84	87	finally
  }
  
  public void onAttachedToWindow() {
    super.onAttachedToWindow();
    removeCallbacks();
  }
  
  public void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    removeCallbacks();
  }
  
  public void show() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: ldc2_w -1
    //   6: putfield mStartTime : J
    //   9: aload_0
    //   10: iconst_0
    //   11: putfield mDismissed : Z
    //   14: aload_0
    //   15: aload_0
    //   16: getfield mDelayedHide : Ljava/lang/Runnable;
    //   19: invokevirtual removeCallbacks : (Ljava/lang/Runnable;)Z
    //   22: pop
    //   23: aload_0
    //   24: iconst_0
    //   25: putfield mPostedHide : Z
    //   28: aload_0
    //   29: getfield mPostedShow : Z
    //   32: ifne -> 52
    //   35: aload_0
    //   36: aload_0
    //   37: getfield mDelayedShow : Ljava/lang/Runnable;
    //   40: ldc2_w 500
    //   43: invokevirtual postDelayed : (Ljava/lang/Runnable;J)Z
    //   46: pop
    //   47: aload_0
    //   48: iconst_1
    //   49: putfield mPostedShow : Z
    //   52: aload_0
    //   53: monitorexit
    //   54: return
    //   55: astore_1
    //   56: aload_0
    //   57: monitorexit
    //   58: aload_1
    //   59: athrow
    // Exception table:
    //   from	to	target	type
    //   2	52	55	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/widget/ContentLoadingProgressBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */