package gnu.mapping;

import java.util.Map;

public abstract class NamedLocation extends IndirectableLocation implements Map.Entry, EnvironmentKey {
  final Symbol name;
  
  NamedLocation next;
  
  final Object property;
  
  public NamedLocation(NamedLocation paramNamedLocation) {
    this.name = paramNamedLocation.name;
    this.property = paramNamedLocation.property;
  }
  
  public NamedLocation(Symbol paramSymbol, Object paramObject) {
    this.name = paramSymbol;
    this.property = paramObject;
  }
  
  public boolean entered() {
    return (this.next != null);
  }
  
  public boolean equals(Object paramObject) {
    // Byte code:
    //   0: aload_1
    //   1: instanceof gnu/mapping/NamedLocation
    //   4: ifne -> 9
    //   7: iconst_0
    //   8: ireturn
    //   9: aload_1
    //   10: checkcast gnu/mapping/NamedLocation
    //   13: astore_2
    //   14: aload_0
    //   15: getfield name : Lgnu/mapping/Symbol;
    //   18: ifnonnull -> 56
    //   21: aload_2
    //   22: getfield name : Lgnu/mapping/Symbol;
    //   25: ifnonnull -> 7
    //   28: aload_0
    //   29: getfield property : Ljava/lang/Object;
    //   32: aload_2
    //   33: getfield property : Ljava/lang/Object;
    //   36: if_acmpne -> 7
    //   39: aload_0
    //   40: invokevirtual getValue : ()Ljava/lang/Object;
    //   43: astore_1
    //   44: aload_2
    //   45: invokevirtual getValue : ()Ljava/lang/Object;
    //   48: astore_2
    //   49: aload_1
    //   50: aload_2
    //   51: if_acmpne -> 72
    //   54: iconst_1
    //   55: ireturn
    //   56: aload_0
    //   57: getfield name : Lgnu/mapping/Symbol;
    //   60: aload_2
    //   61: getfield name : Lgnu/mapping/Symbol;
    //   64: invokevirtual equals : (Ljava/lang/Object;)Z
    //   67: ifne -> 28
    //   70: iconst_0
    //   71: ireturn
    //   72: aload_1
    //   73: ifnull -> 7
    //   76: aload_2
    //   77: ifnull -> 7
    //   80: aload_1
    //   81: aload_2
    //   82: invokevirtual equals : (Ljava/lang/Object;)Z
    //   85: ireturn
  }
  
  public Environment getEnvironment() {
    for (NamedLocation namedLocation = this; namedLocation != null; namedLocation = namedLocation.next) {
      if (namedLocation.name == null) {
        Environment environment = (Environment)namedLocation.value;
        if (environment != null)
          return environment; 
      } 
    } 
    return super.getEnvironment();
  }
  
  public final Object getKey() {
    Symbol symbol;
    NamedLocation namedLocation = this;
    if (this.property == null)
      symbol = this.name; 
    return symbol;
  }
  
  public final Object getKeyProperty() {
    return this.property;
  }
  
  public final Symbol getKeySymbol() {
    return this.name;
  }
  
  public int hashCode() {
    int j = this.name.hashCode() ^ System.identityHashCode(this.property);
    Object object = getValue();
    int i = j;
    if (object != null)
      i = j ^ object.hashCode(); 
    return i;
  }
  
  public final boolean matches(EnvironmentKey paramEnvironmentKey) {
    return (Symbol.equals(paramEnvironmentKey.getKeySymbol(), this.name) && paramEnvironmentKey.getKeyProperty() == this.property);
  }
  
  public final boolean matches(Symbol paramSymbol, Object paramObject) {
    return (Symbol.equals(paramSymbol, this.name) && paramObject == this.property);
  }
  
  public void setRestore(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield value : Ljava/lang/Object;
    //   6: getstatic gnu/mapping/NamedLocation.INDIRECT_FLUIDS : Ljava/lang/Object;
    //   9: if_acmpne -> 23
    //   12: aload_0
    //   13: getfield base : Lgnu/mapping/Location;
    //   16: aload_1
    //   17: invokevirtual setRestore : (Ljava/lang/Object;)V
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: aload_1
    //   24: instanceof gnu/mapping/Location
    //   27: ifeq -> 51
    //   30: aload_0
    //   31: aconst_null
    //   32: putfield value : Ljava/lang/Object;
    //   35: aload_0
    //   36: aload_1
    //   37: checkcast gnu/mapping/Location
    //   40: putfield base : Lgnu/mapping/Location;
    //   43: goto -> 20
    //   46: astore_1
    //   47: aload_0
    //   48: monitorexit
    //   49: aload_1
    //   50: athrow
    //   51: aload_0
    //   52: aload_1
    //   53: putfield value : Ljava/lang/Object;
    //   56: aload_0
    //   57: aconst_null
    //   58: putfield base : Lgnu/mapping/Location;
    //   61: goto -> 20
    // Exception table:
    //   from	to	target	type
    //   2	20	46	finally
    //   23	43	46	finally
    //   51	61	46	finally
  }
  
  public Object setWithSave(Object paramObject) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield value : Ljava/lang/Object;
    //   6: getstatic gnu/mapping/NamedLocation.INDIRECT_FLUIDS : Ljava/lang/Object;
    //   9: if_acmpne -> 25
    //   12: aload_0
    //   13: getfield base : Lgnu/mapping/Location;
    //   16: aload_1
    //   17: invokevirtual setWithSave : (Ljava/lang/Object;)Ljava/lang/Object;
    //   20: astore_1
    //   21: aload_0
    //   22: monitorexit
    //   23: aload_1
    //   24: areturn
    //   25: aload_0
    //   26: getfield name : Lgnu/mapping/Symbol;
    //   29: invokestatic makeAnonymous : (Lgnu/mapping/Symbol;)Lgnu/mapping/ThreadLocation;
    //   32: astore_2
    //   33: aload_2
    //   34: getfield global : Lgnu/mapping/SharedLocation;
    //   37: aload_0
    //   38: getfield base : Lgnu/mapping/Location;
    //   41: putfield base : Lgnu/mapping/Location;
    //   44: aload_2
    //   45: getfield global : Lgnu/mapping/SharedLocation;
    //   48: aload_0
    //   49: getfield value : Ljava/lang/Object;
    //   52: putfield value : Ljava/lang/Object;
    //   55: aload_0
    //   56: aload_2
    //   57: invokevirtual setAlias : (Lgnu/mapping/Location;)V
    //   60: aload_2
    //   61: invokevirtual getLocation : ()Lgnu/mapping/NamedLocation;
    //   64: astore_3
    //   65: aload_3
    //   66: aload_1
    //   67: putfield value : Ljava/lang/Object;
    //   70: aload_3
    //   71: aconst_null
    //   72: putfield base : Lgnu/mapping/Location;
    //   75: aload_2
    //   76: getfield global : Lgnu/mapping/SharedLocation;
    //   79: astore_1
    //   80: goto -> 21
    //   83: astore_1
    //   84: aload_0
    //   85: monitorexit
    //   86: aload_1
    //   87: athrow
    // Exception table:
    //   from	to	target	type
    //   2	21	83	finally
    //   25	80	83	finally
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/NamedLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */