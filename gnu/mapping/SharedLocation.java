package gnu.mapping;

public class SharedLocation extends NamedLocation {
  int timestamp;
  
  public SharedLocation(Symbol paramSymbol, Object paramObject, int paramInt) {
    super(paramSymbol, paramObject);
    this.timestamp = paramInt;
  }
  
  public final Object get(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield base : Lgnu/mapping/Location;
    //   6: ifnull -> 22
    //   9: aload_0
    //   10: getfield base : Lgnu/mapping/Location;
    //   13: aload_1
    //   14: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   17: astore_1
    //   18: aload_0
    //   19: monitorexit
    //   20: aload_1
    //   21: areturn
    //   22: aload_0
    //   23: getfield value : Ljava/lang/Object;
    //   26: getstatic gnu/mapping/Location.UNBOUND : Ljava/lang/String;
    //   29: if_acmpeq -> 18
    //   32: aload_0
    //   33: getfield value : Ljava/lang/Object;
    //   36: astore_1
    //   37: goto -> 18
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Exception table:
    //   from	to	target	type
    //   2	18	40	finally
    //   22	37	40	finally
  }
  
  public boolean isBound() {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield base : Lgnu/mapping/Location;
    //   6: ifnull -> 21
    //   9: aload_0
    //   10: getfield base : Lgnu/mapping/Location;
    //   13: invokevirtual isBound : ()Z
    //   16: istore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: iload_1
    //   20: ireturn
    //   21: aload_0
    //   22: getfield value : Ljava/lang/Object;
    //   25: astore_2
    //   26: getstatic gnu/mapping/Location.UNBOUND : Ljava/lang/String;
    //   29: astore_3
    //   30: aload_2
    //   31: aload_3
    //   32: if_acmpeq -> 40
    //   35: iconst_1
    //   36: istore_1
    //   37: goto -> 17
    //   40: iconst_0
    //   41: istore_1
    //   42: goto -> 17
    //   45: astore_2
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_2
    //   49: athrow
    // Exception table:
    //   from	to	target	type
    //   2	17	45	finally
    //   21	30	45	finally
  }
  
  public final void set(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield base : Lgnu/mapping/Location;
    //   6: ifnonnull -> 17
    //   9: aload_0
    //   10: aload_1
    //   11: putfield value : Ljava/lang/Object;
    //   14: aload_0
    //   15: monitorexit
    //   16: return
    //   17: aload_0
    //   18: getfield value : Ljava/lang/Object;
    //   21: getstatic gnu/mapping/SharedLocation.DIRECT_ON_SET : Ljava/lang/Object;
    //   24: if_acmpne -> 45
    //   27: aload_0
    //   28: aconst_null
    //   29: putfield base : Lgnu/mapping/Location;
    //   32: aload_0
    //   33: aload_1
    //   34: putfield value : Ljava/lang/Object;
    //   37: goto -> 14
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    //   45: aload_0
    //   46: getfield base : Lgnu/mapping/Location;
    //   49: invokevirtual isConstant : ()Z
    //   52: ifeq -> 74
    //   55: aload_0
    //   56: invokevirtual getEnvironment : ()Lgnu/mapping/Environment;
    //   59: aload_0
    //   60: invokevirtual getKeySymbol : ()Lgnu/mapping/Symbol;
    //   63: aload_0
    //   64: invokevirtual getKeyProperty : ()Ljava/lang/Object;
    //   67: aload_1
    //   68: invokevirtual put : (Lgnu/mapping/Symbol;Ljava/lang/Object;Ljava/lang/Object;)V
    //   71: goto -> 14
    //   74: aload_0
    //   75: getfield base : Lgnu/mapping/Location;
    //   78: aload_1
    //   79: invokevirtual set : (Ljava/lang/Object;)V
    //   82: goto -> 14
    // Exception table:
    //   from	to	target	type
    //   2	14	40	finally
    //   17	37	40	finally
    //   45	71	40	finally
    //   74	82	40	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/SharedLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */