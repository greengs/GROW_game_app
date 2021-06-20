package gnu.mapping;

public class ThreadLocation extends NamedLocation implements Named {
  public static final String ANONYMOUS = new String("(dynamic)");
  
  static int counter;
  
  static SimpleEnvironment env;
  
  SharedLocation global;
  
  private int hash;
  
  private ThreadLocal<NamedLocation> thLocal;
  
  public ThreadLocation() {
    this("param#" + nextCounter());
  }
  
  private ThreadLocation(Symbol paramSymbol) {
    super(paramSymbol, ANONYMOUS);
    String str;
    this.thLocal = new InheritingLocation();
    if (paramSymbol == null) {
      paramSymbol = null;
    } else {
      str = paramSymbol.toString();
    } 
    this.global = new SharedLocation(Symbol.makeUninterned(str), null, 0);
  }
  
  public ThreadLocation(Symbol paramSymbol, Object paramObject, SharedLocation paramSharedLocation) {
    super(paramSymbol, paramObject);
    this.hash = paramSymbol.hashCode() ^ System.identityHashCode(paramObject);
    this.global = paramSharedLocation;
  }
  
  public ThreadLocation(String paramString) {
    super(Symbol.makeUninterned(paramString), ANONYMOUS);
    this.thLocal = new InheritingLocation();
    this.global = new SharedLocation(this.name, null, 0);
  }
  
  public static ThreadLocation getInstance(Symbol paramSymbol, Object paramObject) {
    // Byte code:
    //   0: ldc gnu/mapping/ThreadLocation
    //   2: monitorenter
    //   3: getstatic gnu/mapping/ThreadLocation.env : Lgnu/mapping/SimpleEnvironment;
    //   6: ifnonnull -> 21
    //   9: new gnu/mapping/SimpleEnvironment
    //   12: dup
    //   13: ldc '[thread-locations]'
    //   15: invokespecial <init> : (Ljava/lang/String;)V
    //   18: putstatic gnu/mapping/ThreadLocation.env : Lgnu/mapping/SimpleEnvironment;
    //   21: getstatic gnu/mapping/ThreadLocation.env : Lgnu/mapping/SimpleEnvironment;
    //   24: aload_0
    //   25: aload_1
    //   26: invokevirtual getLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   29: checkcast gnu/mapping/IndirectableLocation
    //   32: astore_2
    //   33: aload_2
    //   34: getfield base : Lgnu/mapping/Location;
    //   37: ifnull -> 53
    //   40: aload_2
    //   41: getfield base : Lgnu/mapping/Location;
    //   44: checkcast gnu/mapping/ThreadLocation
    //   47: astore_0
    //   48: ldc gnu/mapping/ThreadLocation
    //   50: monitorexit
    //   51: aload_0
    //   52: areturn
    //   53: new gnu/mapping/ThreadLocation
    //   56: dup
    //   57: aload_0
    //   58: aload_1
    //   59: aconst_null
    //   60: invokespecial <init> : (Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/SharedLocation;)V
    //   63: astore_0
    //   64: aload_2
    //   65: aload_0
    //   66: putfield base : Lgnu/mapping/Location;
    //   69: goto -> 48
    //   72: astore_0
    //   73: ldc gnu/mapping/ThreadLocation
    //   75: monitorexit
    //   76: aload_0
    //   77: athrow
    // Exception table:
    //   from	to	target	type
    //   3	21	72	finally
    //   21	48	72	finally
    //   53	69	72	finally
  }
  
  public static ThreadLocation makeAnonymous(Symbol paramSymbol) {
    return new ThreadLocation(paramSymbol);
  }
  
  public static ThreadLocation makeAnonymous(String paramString) {
    return new ThreadLocation(paramString);
  }
  
  private static int nextCounter() {
    // Byte code:
    //   0: ldc gnu/mapping/ThreadLocation
    //   2: monitorenter
    //   3: getstatic gnu/mapping/ThreadLocation.counter : I
    //   6: iconst_1
    //   7: iadd
    //   8: istore_0
    //   9: iload_0
    //   10: putstatic gnu/mapping/ThreadLocation.counter : I
    //   13: ldc gnu/mapping/ThreadLocation
    //   15: monitorexit
    //   16: iload_0
    //   17: ireturn
    //   18: astore_1
    //   19: ldc gnu/mapping/ThreadLocation
    //   21: monitorexit
    //   22: aload_1
    //   23: athrow
    // Exception table:
    //   from	to	target	type
    //   3	13	18	finally
  }
  
  public Object get(Object paramObject) {
    return getLocation().get(paramObject);
  }
  
  public NamedLocation getLocation() {
    if (this.property != ANONYMOUS)
      return Environment.getCurrent().getLocation(this.name, this.property, this.hash, true); 
    NamedLocation namedLocation2 = this.thLocal.get();
    NamedLocation namedLocation1 = namedLocation2;
    if (namedLocation2 == null) {
      namedLocation1 = new SharedLocation(this.name, this.property, 0);
      if (this.global != null)
        namedLocation1.setBase(this.global); 
      this.thLocal.set(namedLocation1);
      return namedLocation1;
    } 
    return namedLocation1;
  }
  
  public String getName() {
    return (this.name == null) ? null : this.name.toString();
  }
  
  public Object getSymbol() {
    return (this.name != null && this.property == ANONYMOUS && this.global.getKeySymbol() == this.name) ? this.name.toString() : this.name;
  }
  
  public void set(Object paramObject) {
    getLocation().set(paramObject);
  }
  
  public void setGlobal(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield global : Lgnu/mapping/SharedLocation;
    //   6: ifnonnull -> 26
    //   9: aload_0
    //   10: new gnu/mapping/SharedLocation
    //   13: dup
    //   14: aload_0
    //   15: getfield name : Lgnu/mapping/Symbol;
    //   18: aconst_null
    //   19: iconst_0
    //   20: invokespecial <init> : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)V
    //   23: putfield global : Lgnu/mapping/SharedLocation;
    //   26: aload_0
    //   27: getfield global : Lgnu/mapping/SharedLocation;
    //   30: aload_1
    //   31: invokevirtual set : (Ljava/lang/Object;)V
    //   34: aload_0
    //   35: monitorexit
    //   36: return
    //   37: astore_1
    //   38: aload_0
    //   39: monitorexit
    //   40: aload_1
    //   41: athrow
    // Exception table:
    //   from	to	target	type
    //   2	26	37	finally
    //   26	36	37	finally
    //   38	40	37	finally
  }
  
  public void setName(String paramString) {
    throw new RuntimeException("setName not allowed");
  }
  
  public void setRestore(Object paramObject) {
    getLocation().setRestore(paramObject);
  }
  
  public Object setWithSave(Object paramObject) {
    return getLocation().setWithSave(paramObject);
  }
  
  public class InheritingLocation extends InheritableThreadLocal<NamedLocation> {
    protected SharedLocation childValue(NamedLocation param1NamedLocation) {
      if (ThreadLocation.this.property != ThreadLocation.ANONYMOUS)
        throw new Error(); 
      NamedLocation namedLocation = param1NamedLocation;
      if (param1NamedLocation == null)
        namedLocation = ThreadLocation.this.getLocation(); 
      param1NamedLocation = namedLocation;
      if (namedLocation.base == null) {
        param1NamedLocation = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
        ((SharedLocation)param1NamedLocation).value = namedLocation.value;
        namedLocation.base = param1NamedLocation;
        namedLocation.value = null;
      } 
      namedLocation = new SharedLocation(ThreadLocation.this.name, ThreadLocation.this.property, 0);
      ((SharedLocation)namedLocation).value = null;
      ((SharedLocation)namedLocation).base = param1NamedLocation;
      return (SharedLocation)namedLocation;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/ThreadLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */