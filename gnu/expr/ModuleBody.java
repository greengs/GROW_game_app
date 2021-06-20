package gnu.expr;

import gnu.lists.Consumer;
import gnu.mapping.CallContext;
import gnu.mapping.OutPort;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure0;
import gnu.mapping.ProcedureN;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import gnu.text.WriterManager;
import kawa.Shell;

public abstract class ModuleBody extends Procedure0 {
  private static int exitCounter;
  
  private static boolean mainPrintValues;
  
  protected boolean runDone;
  
  public static void exitDecrement() {
    // Byte code:
    //   0: ldc gnu/expr/ModuleBody
    //   2: monitorenter
    //   3: getstatic gnu/expr/ModuleBody.exitCounter : I
    //   6: istore_0
    //   7: iload_0
    //   8: ifle -> 23
    //   11: iload_0
    //   12: iconst_1
    //   13: isub
    //   14: istore_0
    //   15: iload_0
    //   16: ifne -> 27
    //   19: iconst_0
    //   20: invokestatic exit : (I)V
    //   23: ldc gnu/expr/ModuleBody
    //   25: monitorexit
    //   26: return
    //   27: iload_0
    //   28: putstatic gnu/expr/ModuleBody.exitCounter : I
    //   31: goto -> 23
    //   34: astore_1
    //   35: ldc gnu/expr/ModuleBody
    //   37: monitorexit
    //   38: aload_1
    //   39: athrow
    // Exception table:
    //   from	to	target	type
    //   3	7	34	finally
    //   19	23	34	finally
    //   27	31	34	finally
  }
  
  public static void exitIncrement() {
    // Byte code:
    //   0: ldc gnu/expr/ModuleBody
    //   2: monitorenter
    //   3: getstatic gnu/expr/ModuleBody.exitCounter : I
    //   6: ifne -> 17
    //   9: getstatic gnu/expr/ModuleBody.exitCounter : I
    //   12: iconst_1
    //   13: iadd
    //   14: putstatic gnu/expr/ModuleBody.exitCounter : I
    //   17: getstatic gnu/expr/ModuleBody.exitCounter : I
    //   20: iconst_1
    //   21: iadd
    //   22: putstatic gnu/expr/ModuleBody.exitCounter : I
    //   25: ldc gnu/expr/ModuleBody
    //   27: monitorexit
    //   28: return
    //   29: astore_0
    //   30: ldc gnu/expr/ModuleBody
    //   32: monitorexit
    //   33: aload_0
    //   34: athrow
    // Exception table:
    //   from	to	target	type
    //   3	17	29	finally
    //   17	25	29	finally
  }
  
  public static boolean getMainPrintValues() {
    return mainPrintValues;
  }
  
  public static void runCleanup(CallContext paramCallContext, Throwable paramThrowable, Consumer paramConsumer) {
    // Byte code:
    //   0: aload_1
    //   1: astore_3
    //   2: aload_1
    //   3: ifnonnull -> 12
    //   6: aload_0
    //   7: invokevirtual runUntilDone : ()V
    //   10: aload_1
    //   11: astore_3
    //   12: aload_0
    //   13: aload_2
    //   14: putfield consumer : Lgnu/lists/Consumer;
    //   17: aload_3
    //   18: ifnull -> 58
    //   21: aload_3
    //   22: instanceof java/lang/RuntimeException
    //   25: ifeq -> 37
    //   28: aload_3
    //   29: checkcast java/lang/RuntimeException
    //   32: athrow
    //   33: astore_3
    //   34: goto -> 12
    //   37: aload_3
    //   38: instanceof java/lang/Error
    //   41: ifeq -> 49
    //   44: aload_3
    //   45: checkcast java/lang/Error
    //   48: athrow
    //   49: new gnu/mapping/WrappedException
    //   52: dup
    //   53: aload_3
    //   54: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   57: athrow
    //   58: return
    // Exception table:
    //   from	to	target	type
    //   6	10	33	java/lang/Throwable
  }
  
  public static void setMainPrintValues(boolean paramBoolean) {
    mainPrintValues = paramBoolean;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    if (paramCallContext.pc == 0)
      run(paramCallContext); 
  }
  
  public Object apply0() throws Throwable {
    CallContext callContext = CallContext.getInstance();
    match0(callContext);
    return callContext.runUntilValue();
  }
  
  public Object apply0(ModuleMethod paramModuleMethod) throws Throwable {
    return applyN(paramModuleMethod, Values.noArgs);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) throws Throwable {
    return applyN(paramModuleMethod, new Object[] { paramObject });
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) throws Throwable {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2 });
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 });
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    return applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) throws Throwable {
    int i = paramArrayOfObject.length;
    int j = paramModuleMethod.numArgs();
    if (i >= (j & 0xFFF) && (j < 0 || i <= j >> 12)) {
      switch (i) {
        default:
          throw new WrongArguments(paramModuleMethod, i);
        case 0:
          return apply0(paramModuleMethod);
        case 1:
          return apply1(paramModuleMethod, paramArrayOfObject[0]);
        case 2:
          return apply2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1]);
        case 3:
          return apply3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
        case 4:
          break;
      } 
      return apply4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3]);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 0)
      return 0xFFF10000 | j; 
    if (i < 0)
      return matchN(paramModuleMethod, ProcedureN.noArgs, paramCallContext); 
    paramCallContext.count = 0;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 1)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 1)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject;
      paramCallContext.count = 1;
      paramCallContext.where = 1;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      return 0;
    } 
    return matchN(paramModuleMethod, new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 2)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 2)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.count = 2;
      paramCallContext.where = 33;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      return 0;
    } 
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 3)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 3)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.count = 3;
      paramCallContext.where = 801;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      return 0;
    } 
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 4)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 4)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.count = 4;
      paramCallContext.where = 17185;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      return 0;
    } 
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (paramArrayOfObject.length < j)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      switch (paramArrayOfObject.length) {
        default:
          i >>= 12;
          if (paramArrayOfObject.length > i)
            return 0xFFF20000 | i; 
          paramCallContext.values = paramArrayOfObject;
          paramCallContext.count = paramArrayOfObject.length;
          paramCallContext.where = 0;
          paramCallContext.next = 0;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          return 0;
        case 0:
          return match0(paramModuleMethod, paramCallContext);
        case 1:
          return match1(paramModuleMethod, paramArrayOfObject[0], paramCallContext);
        case 2:
          return match2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramCallContext);
        case 3:
          return match3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramCallContext);
        case 4:
          break;
      } 
      return match4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramCallContext);
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.count = paramArrayOfObject.length;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    return 0;
  }
  
  public void run() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield runDone : Z
    //   6: ifeq -> 12
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: aload_0
    //   13: iconst_1
    //   14: putfield runDone : Z
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_0
    //   20: getstatic gnu/lists/VoidConsumer.instance : Lgnu/lists/VoidConsumer;
    //   23: invokevirtual run : (Lgnu/lists/Consumer;)V
    //   26: return
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	27	finally
    //   12	19	27	finally
    //   28	30	27	finally
  }
  
  public void run(Consumer paramConsumer) {
    CallContext callContext = CallContext.getInstance();
    Consumer consumer = callContext.consumer;
    callContext.consumer = paramConsumer;
    try {
      run(callContext);
      paramConsumer = null;
    } catch (Throwable throwable) {}
    runCleanup(callContext, throwable, consumer);
  }
  
  public void run(CallContext paramCallContext) throws Throwable {}
  
  public final void runAsMain() {
    WriterManager.instance.registerShutdownHook();
    try {
      CallContext callContext = CallContext.getInstance();
      if (getMainPrintValues()) {
        OutPort outPort = OutPort.outDefault();
        callContext.consumer = Shell.getOutputConsumer(outPort);
        run(callContext);
        callContext.runUntilDone();
        outPort.freshLine();
      } else {
        run();
        callContext.runUntilDone();
      } 
      OutPort.runCleanups();
      exitDecrement();
      return;
    } catch (Throwable throwable) {
      throwable.printStackTrace();
      OutPort.runCleanups();
      System.exit(-1);
      return;
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleBody.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */