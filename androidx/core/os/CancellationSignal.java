package androidx.core.os;

public final class CancellationSignal {
  private boolean mCancelInProgress;
  
  private Object mCancellationSignalObj;
  
  private boolean mIsCanceled;
  
  private OnCancelListener mOnCancelListener;
  
  private void waitForCancelFinishedLocked() {
    while (this.mCancelInProgress) {
      try {
        wait();
      } catch (InterruptedException interruptedException) {}
    } 
  }
  
  public void cancel() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsCanceled : Z
    //   6: ifeq -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield mIsCanceled : Z
    //   17: aload_0
    //   18: iconst_1
    //   19: putfield mCancelInProgress : Z
    //   22: aload_0
    //   23: getfield mOnCancelListener : Landroidx/core/os/CancellationSignal$OnCancelListener;
    //   26: astore_1
    //   27: aload_0
    //   28: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   31: astore_2
    //   32: aload_0
    //   33: monitorexit
    //   34: aload_1
    //   35: ifnull -> 44
    //   38: aload_1
    //   39: invokeinterface onCancel : ()V
    //   44: aload_2
    //   45: ifnull -> 63
    //   48: getstatic android/os/Build$VERSION.SDK_INT : I
    //   51: bipush #16
    //   53: if_icmplt -> 63
    //   56: aload_2
    //   57: checkcast android/os/CancellationSignal
    //   60: invokevirtual cancel : ()V
    //   63: aload_0
    //   64: monitorenter
    //   65: aload_0
    //   66: iconst_0
    //   67: putfield mCancelInProgress : Z
    //   70: aload_0
    //   71: invokevirtual notifyAll : ()V
    //   74: aload_0
    //   75: monitorexit
    //   76: return
    //   77: astore_1
    //   78: aload_0
    //   79: monitorexit
    //   80: aload_1
    //   81: athrow
    //   82: astore_1
    //   83: aload_0
    //   84: monitorexit
    //   85: aload_1
    //   86: athrow
    //   87: astore_1
    //   88: aload_0
    //   89: monitorenter
    //   90: aload_0
    //   91: iconst_0
    //   92: putfield mCancelInProgress : Z
    //   95: aload_0
    //   96: invokevirtual notifyAll : ()V
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_1
    //   102: athrow
    //   103: astore_1
    //   104: aload_0
    //   105: monitorexit
    //   106: aload_1
    //   107: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	82	finally
    //   12	34	82	finally
    //   38	44	87	finally
    //   48	63	87	finally
    //   65	76	77	finally
    //   78	80	77	finally
    //   83	85	82	finally
    //   90	101	103	finally
    //   104	106	103	finally
  }
  
  public Object getCancellationSignalObject() {
    // Byte code:
    //   0: getstatic android/os/Build$VERSION.SDK_INT : I
    //   3: bipush #16
    //   5: if_icmpge -> 10
    //   8: aconst_null
    //   9: areturn
    //   10: aload_0
    //   11: monitorenter
    //   12: aload_0
    //   13: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   16: ifnonnull -> 47
    //   19: aload_0
    //   20: new android/os/CancellationSignal
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: putfield mCancellationSignalObj : Ljava/lang/Object;
    //   30: aload_0
    //   31: getfield mIsCanceled : Z
    //   34: ifeq -> 47
    //   37: aload_0
    //   38: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   41: checkcast android/os/CancellationSignal
    //   44: invokevirtual cancel : ()V
    //   47: aload_0
    //   48: getfield mCancellationSignalObj : Ljava/lang/Object;
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: areturn
    //   56: astore_1
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_1
    //   60: athrow
    // Exception table:
    //   from	to	target	type
    //   12	47	56	finally
    //   47	54	56	finally
    //   57	59	56	finally
  }
  
  public boolean isCanceled() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield mIsCanceled : Z
    //   6: istore_1
    //   7: aload_0
    //   8: monitorexit
    //   9: iload_1
    //   10: ireturn
    //   11: astore_2
    //   12: aload_0
    //   13: monitorexit
    //   14: aload_2
    //   15: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	11	finally
    //   12	14	11	finally
  }
  
  public void setOnCancelListener(OnCancelListener paramOnCancelListener) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokespecial waitForCancelFinishedLocked : ()V
    //   6: aload_0
    //   7: getfield mOnCancelListener : Landroidx/core/os/CancellationSignal$OnCancelListener;
    //   10: aload_1
    //   11: if_acmpne -> 17
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: aload_1
    //   19: putfield mOnCancelListener : Landroidx/core/os/CancellationSignal$OnCancelListener;
    //   22: aload_0
    //   23: getfield mIsCanceled : Z
    //   26: ifeq -> 33
    //   29: aload_1
    //   30: ifnonnull -> 41
    //   33: aload_0
    //   34: monitorexit
    //   35: return
    //   36: astore_1
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_1
    //   40: athrow
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: invokeinterface onCancel : ()V
    //   49: return
    // Exception table:
    //   from	to	target	type
    //   2	16	36	finally
    //   17	29	36	finally
    //   33	35	36	finally
    //   37	39	36	finally
    //   41	43	36	finally
  }
  
  public void throwIfCanceled() {
    if (isCanceled())
      throw new OperationCanceledException(); 
  }
  
  public static interface OnCancelListener {
    void onCancel();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/androidx/core/os/CancellationSignal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */