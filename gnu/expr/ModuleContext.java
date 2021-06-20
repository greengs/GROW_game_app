package gnu.expr;

import gnu.kawa.util.AbstractWeakHashTable;

public class ModuleContext {
  public static int IN_HTTP_SERVER;
  
  public static int IN_SERVLET;
  
  static ModuleContext global = new ModuleContext(ModuleManager.instance);
  
  int flags;
  
  ModuleManager manager;
  
  private ClassToInstanceMap table = new ClassToInstanceMap();
  
  static {
    IN_HTTP_SERVER = 1;
    IN_SERVLET = 2;
  }
  
  public ModuleContext(ModuleManager paramModuleManager) {
    this.manager = paramModuleManager;
  }
  
  public static ModuleContext getContext() {
    return global;
  }
  
  public void addFlags(int paramInt) {
    this.flags |= paramInt;
  }
  
  public void clear() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield table : Lgnu/expr/ModuleContext$ClassToInstanceMap;
    //   6: invokevirtual clear : ()V
    //   9: aload_0
    //   10: monitorexit
    //   11: return
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: athrow
    // Exception table:
    //   from	to	target	type
    //   2	9	12	finally
  }
  
  public ModuleInfo findFromInstance(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual getClass : ()Ljava/lang/Class;
    //   4: astore_2
    //   5: aload_0
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield manager : Lgnu/expr/ModuleManager;
    //   11: astore_3
    //   12: aload_2
    //   13: invokestatic findWithClass : (Ljava/lang/Class;)Lgnu/expr/ModuleInfo;
    //   16: astore_2
    //   17: aload_0
    //   18: aload_1
    //   19: invokevirtual setInstance : (Ljava/lang/Object;)V
    //   22: aload_0
    //   23: monitorexit
    //   24: aload_2
    //   25: areturn
    //   26: astore_1
    //   27: aload_0
    //   28: monitorexit
    //   29: aload_1
    //   30: athrow
    // Exception table:
    //   from	to	target	type
    //   7	24	26	finally
    //   27	29	26	finally
  }
  
  public Object findInstance(ModuleInfo paramModuleInfo) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: invokevirtual getModuleClass : ()Ljava/lang/Class;
    //   6: astore_2
    //   7: aload_0
    //   8: aload_2
    //   9: invokevirtual findInstance : (Ljava/lang/Class;)Ljava/lang/Object;
    //   12: astore_1
    //   13: aload_0
    //   14: monitorexit
    //   15: aload_1
    //   16: areturn
    //   17: astore_2
    //   18: aload_1
    //   19: invokevirtual getClassName : ()Ljava/lang/String;
    //   22: astore_1
    //   23: new gnu/mapping/WrappedException
    //   26: dup
    //   27: new java/lang/StringBuilder
    //   30: dup
    //   31: invokespecial <init> : ()V
    //   34: ldc 'cannot find module '
    //   36: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: aload_1
    //   40: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: invokevirtual toString : ()Ljava/lang/String;
    //   46: aload_2
    //   47: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   50: athrow
    //   51: astore_1
    //   52: aload_0
    //   53: monitorexit
    //   54: aload_1
    //   55: athrow
    // Exception table:
    //   from	to	target	type
    //   2	7	17	java/lang/ClassNotFoundException
    //   2	7	51	finally
    //   7	13	51	finally
    //   18	51	51	finally
  }
  
  public Object findInstance(Class paramClass) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield table : Lgnu/expr/ModuleContext$ClassToInstanceMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: astore_2
    //   11: aload_2
    //   12: astore_3
    //   13: aload_2
    //   14: ifnonnull -> 37
    //   17: aload_1
    //   18: ldc '$instance'
    //   20: invokevirtual getDeclaredField : (Ljava/lang/String;)Ljava/lang/reflect/Field;
    //   23: aconst_null
    //   24: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   27: astore_2
    //   28: aload_2
    //   29: astore_1
    //   30: aload_0
    //   31: aload_1
    //   32: invokevirtual setInstance : (Ljava/lang/Object;)V
    //   35: aload_1
    //   36: astore_3
    //   37: aload_0
    //   38: monitorexit
    //   39: aload_3
    //   40: areturn
    //   41: astore_2
    //   42: aload_1
    //   43: invokevirtual newInstance : ()Ljava/lang/Object;
    //   46: astore_2
    //   47: aload_2
    //   48: astore_1
    //   49: goto -> 30
    //   52: astore_2
    //   53: new gnu/mapping/WrappedException
    //   56: dup
    //   57: new java/lang/StringBuilder
    //   60: dup
    //   61: invokespecial <init> : ()V
    //   64: ldc 'exception while initializing module '
    //   66: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   69: aload_1
    //   70: invokevirtual getName : ()Ljava/lang/String;
    //   73: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: invokevirtual toString : ()Ljava/lang/String;
    //   79: aload_2
    //   80: invokespecial <init> : (Ljava/lang/String;Ljava/lang/Throwable;)V
    //   83: athrow
    //   84: astore_1
    //   85: aload_0
    //   86: monitorexit
    //   87: aload_1
    //   88: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	84	finally
    //   17	28	41	java/lang/NoSuchFieldException
    //   17	28	52	java/lang/Throwable
    //   17	28	84	finally
    //   30	35	84	finally
    //   42	47	52	java/lang/Throwable
    //   42	47	84	finally
    //   53	84	84	finally
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public ModuleManager getManager() {
    return this.manager;
  }
  
  public Object searchInstance(Class paramClass) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield table : Lgnu/expr/ModuleContext$ClassToInstanceMap;
    //   6: aload_1
    //   7: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   10: astore_1
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_1
    //   14: areturn
    //   15: astore_1
    //   16: aload_0
    //   17: monitorexit
    //   18: aload_1
    //   19: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	15	finally
  }
  
  public void setFlags(int paramInt) {
    this.flags = paramInt;
  }
  
  public void setInstance(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield table : Lgnu/expr/ModuleContext$ClassToInstanceMap;
    //   6: aload_1
    //   7: invokevirtual getClass : ()Ljava/lang/Class;
    //   10: aload_1
    //   11: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   14: pop
    //   15: aload_0
    //   16: monitorexit
    //   17: return
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: athrow
    // Exception table:
    //   from	to	target	type
    //   2	15	18	finally
  }
  
  static class ClassToInstanceMap extends AbstractWeakHashTable<Class, Object> {
    protected Class getKeyFromValue(Object param1Object) {
      return param1Object.getClass();
    }
    
    protected boolean matches(Class param1Class1, Class param1Class2) {
      return (param1Class1 == param1Class2);
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */