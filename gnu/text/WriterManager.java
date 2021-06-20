package gnu.text;

import java.io.Writer;

public class WriterManager implements Runnable {
  public static final WriterManager instance = new WriterManager();
  
  WriterRef first;
  
  public WriterRef register(Writer paramWriter) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new gnu/text/WriterRef
    //   5: dup
    //   6: aload_1
    //   7: invokespecial <init> : (Ljava/io/Writer;)V
    //   10: astore_1
    //   11: aload_0
    //   12: getfield first : Lgnu/text/WriterRef;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 33
    //   20: aload_1
    //   21: aload_2
    //   22: getfield next : Lgnu/text/WriterRef;
    //   25: putfield next : Lgnu/text/WriterRef;
    //   28: aload_2
    //   29: aload_1
    //   30: putfield prev : Lgnu/text/WriterRef;
    //   33: aload_0
    //   34: aload_1
    //   35: putfield first : Lgnu/text/WriterRef;
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: areturn
    //   42: astore_1
    //   43: aload_0
    //   44: monitorexit
    //   45: aload_1
    //   46: athrow
    // Exception table:
    //   from	to	target	type
    //   2	16	42	finally
    //   20	33	42	finally
    //   33	38	42	finally
  }
  
  public boolean registerShutdownHook() {
    try {
      Runtime runtime = Runtime.getRuntime();
      runtime.getClass().getDeclaredMethod("addShutdownHook", new Class[] { Thread.class }).invoke(runtime, new Object[] { new Thread(this) });
      return true;
    } catch (Throwable throwable) {
      return false;
    } 
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield first : Lgnu/text/WriterRef;
    //   6: astore_1
    //   7: aload_1
    //   8: ifnull -> 35
    //   11: aload_1
    //   12: invokevirtual get : ()Ljava/lang/Object;
    //   15: astore_2
    //   16: aload_2
    //   17: ifnull -> 27
    //   20: aload_2
    //   21: checkcast java/io/Writer
    //   24: invokevirtual close : ()V
    //   27: aload_1
    //   28: getfield next : Lgnu/text/WriterRef;
    //   31: astore_1
    //   32: goto -> 7
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield first : Lgnu/text/WriterRef;
    //   40: aload_0
    //   41: monitorexit
    //   42: return
    //   43: astore_1
    //   44: aload_0
    //   45: monitorexit
    //   46: aload_1
    //   47: athrow
    //   48: astore_2
    //   49: goto -> 27
    // Exception table:
    //   from	to	target	type
    //   2	7	43	finally
    //   11	16	43	finally
    //   20	27	48	java/lang/Exception
    //   20	27	43	finally
    //   27	32	43	finally
    //   35	40	43	finally
  }
  
  public void unregister(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: ifnonnull -> 9
    //   6: aload_0
    //   7: monitorexit
    //   8: return
    //   9: aload_1
    //   10: checkcast gnu/text/WriterRef
    //   13: astore_1
    //   14: aload_1
    //   15: getfield next : Lgnu/text/WriterRef;
    //   18: astore_2
    //   19: aload_1
    //   20: getfield prev : Lgnu/text/WriterRef;
    //   23: astore_3
    //   24: aload_2
    //   25: ifnull -> 33
    //   28: aload_2
    //   29: aload_3
    //   30: putfield prev : Lgnu/text/WriterRef;
    //   33: aload_3
    //   34: ifnull -> 42
    //   37: aload_3
    //   38: aload_2
    //   39: putfield next : Lgnu/text/WriterRef;
    //   42: aload_1
    //   43: aload_0
    //   44: getfield first : Lgnu/text/WriterRef;
    //   47: if_acmpne -> 6
    //   50: aload_0
    //   51: aload_2
    //   52: putfield first : Lgnu/text/WriterRef;
    //   55: goto -> 6
    //   58: astore_1
    //   59: aload_0
    //   60: monitorexit
    //   61: aload_1
    //   62: athrow
    // Exception table:
    //   from	to	target	type
    //   9	24	58	finally
    //   28	33	58	finally
    //   37	42	58	finally
    //   42	55	58	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/text/WriterManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */