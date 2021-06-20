package gnu.mapping;

import java.util.Hashtable;

public abstract class Environment extends PropertySet {
  static final int CAN_DEFINE = 1;
  
  static final int CAN_IMPLICITLY_DEFINE = 4;
  
  static final int CAN_REDEFINE = 2;
  
  static final int DIRECT_INHERITED_ON_SET = 16;
  
  public static final int INDIRECT_DEFINES = 32;
  
  static final int THREAD_SAFE = 8;
  
  protected static final InheritedLocal curEnvironment;
  
  static final Hashtable envTable = new Hashtable<Object, Object>(50);
  
  static Environment global;
  
  int flags = 23;
  
  static {
    curEnvironment = new InheritedLocal();
  }
  
  public static Environment current() {
    return getCurrent();
  }
  
  public static Environment getCurrent() {
    Environment environment2 = curEnvironment.get();
    Environment environment1 = environment2;
    if (environment2 == null) {
      environment1 = make(Thread.currentThread().getName(), global);
      environment1.flags |= 0x8;
      curEnvironment.set(environment1);
    } 
    return environment1;
  }
  
  public static Environment getGlobal() {
    return global;
  }
  
  public static Environment getInstance(String paramString) {
    null = paramString;
    if (paramString == null)
      null = ""; 
    synchronized (envTable) {
      Environment environment = (Environment)envTable.get(null);
      if (environment != null)
        return environment; 
      environment = new SimpleEnvironment();
      environment.setName(null);
      envTable.put(null, environment);
      return environment;
    } 
  }
  
  public static InheritingEnvironment make(String paramString, Environment paramEnvironment) {
    return new InheritingEnvironment(paramString, paramEnvironment);
  }
  
  public static SimpleEnvironment make() {
    return new SimpleEnvironment();
  }
  
  public static SimpleEnvironment make(String paramString) {
    return new SimpleEnvironment(paramString);
  }
  
  public static void restoreCurrent(Environment paramEnvironment) {
    curEnvironment.set(paramEnvironment);
  }
  
  public static void setCurrent(Environment paramEnvironment) {
    curEnvironment.set(paramEnvironment);
  }
  
  public static void setGlobal(Environment paramEnvironment) {
    global = paramEnvironment;
  }
  
  public static Environment setSaveCurrent(Environment paramEnvironment) {
    Environment environment = curEnvironment.get();
    curEnvironment.set(paramEnvironment);
    return environment;
  }
  
  public static Environment user() {
    return getCurrent();
  }
  
  public abstract NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation);
  
  public final void addLocation(EnvironmentKey paramEnvironmentKey, Location paramLocation) {
    addLocation(paramEnvironmentKey.getKeySymbol(), paramEnvironmentKey.getKeyProperty(), paramLocation);
  }
  
  public final void addLocation(NamedLocation paramNamedLocation) {
    addLocation(paramNamedLocation.getKeySymbol(), paramNamedLocation.getKeyProperty(), paramNamedLocation);
  }
  
  SimpleEnvironment cloneForThread() {
    InheritingEnvironment inheritingEnvironment = new InheritingEnvironment(null, this);
    if (this instanceof InheritingEnvironment) {
      InheritingEnvironment inheritingEnvironment1 = (InheritingEnvironment)this;
      int j = inheritingEnvironment1.numInherited;
      inheritingEnvironment.numInherited = j;
      inheritingEnvironment.inherited = new Environment[j];
      for (int i = 0; i < j; i++)
        inheritingEnvironment.inherited[i] = inheritingEnvironment1.inherited[i]; 
    } 
    LocationEnumeration locationEnumeration = enumerateLocations();
    while (locationEnumeration.hasMoreElements()) {
      Location location = locationEnumeration.nextLocation();
      Symbol symbol = location.getKeySymbol();
      Object object = location.getKeyProperty();
      if (symbol != null && location instanceof NamedLocation) {
        NamedLocation namedLocation = (NamedLocation)location;
        location = namedLocation;
        if (namedLocation.base == null) {
          location = new SharedLocation(symbol, object, 0);
          ((SharedLocation)location).value = namedLocation.value;
          namedLocation.base = location;
          namedLocation.value = null;
        } 
        (inheritingEnvironment.addUnboundLocation(symbol, object, symbol.hashCode() ^ System.identityHashCode(object))).base = location;
      } 
    } 
    return inheritingEnvironment;
  }
  
  public final boolean containsKey(Object paramObject) {
    Object object1 = null;
    Object object2 = paramObject;
    if (paramObject instanceof EnvironmentKey) {
      paramObject = paramObject;
      object2 = paramObject.getKeySymbol();
      object1 = paramObject.getKeyProperty();
    } 
    if (object2 instanceof Symbol) {
      paramObject = object2;
      return isBound((Symbol)paramObject, object1);
    } 
    paramObject = getSymbol((String)object2);
    return isBound((Symbol)paramObject, object1);
  }
  
  public Namespace defaultNamespace() {
    return Namespace.getDefault();
  }
  
  public abstract void define(Symbol paramSymbol, Object paramObject1, Object paramObject2);
  
  public abstract LocationEnumeration enumerateAllLocations();
  
  public abstract LocationEnumeration enumerateLocations();
  
  public final Object get(EnvironmentKey paramEnvironmentKey, Object paramObject) {
    return get(paramEnvironmentKey.getKeySymbol(), paramEnvironmentKey.getKeyProperty(), paramObject);
  }
  
  public Object get(Symbol paramSymbol) {
    String str = Location.UNBOUND;
    Object object = get(paramSymbol, (Object)null, str);
    if (object == str)
      throw new UnboundLocationException(paramSymbol); 
    return object;
  }
  
  public Object get(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    Location location = lookup(paramSymbol, paramObject1);
    return (location == null) ? paramObject2 : location.get(paramObject2);
  }
  
  public final Object get(Object paramObject) {
    Object object1 = null;
    Object object2 = paramObject;
    if (paramObject instanceof EnvironmentKey) {
      paramObject = paramObject;
      object2 = paramObject.getKeySymbol();
      object1 = paramObject.getKeyProperty();
    } 
    if (object2 instanceof Symbol) {
      paramObject = object2;
      return get((Symbol)paramObject, object1, (Object)null);
    } 
    paramObject = getSymbol((String)object2);
    return get((Symbol)paramObject, object1, (Object)null);
  }
  
  public final Object get(String paramString, Object paramObject) {
    return get(getSymbol(paramString), (Object)null, paramObject);
  }
  
  public boolean getCanDefine() {
    return ((this.flags & 0x1) != 0);
  }
  
  public boolean getCanRedefine() {
    return ((this.flags & 0x2) != 0);
  }
  
  public final Object getChecked(String paramString) {
    Object object = get(paramString, Location.UNBOUND);
    if (object == Location.UNBOUND)
      throw new UnboundLocationException(paramString + " in " + this); 
    return object;
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public final Object getFunction(Symbol paramSymbol) {
    String str = Location.UNBOUND;
    Object object = get(paramSymbol, EnvironmentKey.FUNCTION, str);
    if (object == str)
      throw new UnboundLocationException(paramSymbol); 
    return object;
  }
  
  public final Object getFunction(Symbol paramSymbol, Object paramObject) {
    return get(paramSymbol, EnvironmentKey.FUNCTION, paramObject);
  }
  
  public final Location getLocation(Symbol paramSymbol) {
    return getLocation(paramSymbol, (Object)null, true);
  }
  
  public final Location getLocation(Symbol paramSymbol, Object paramObject) {
    return getLocation(paramSymbol, paramObject, true);
  }
  
  public final Location getLocation(Object paramObject, boolean paramBoolean) {
    Object object1 = null;
    Object object2 = paramObject;
    if (paramObject instanceof EnvironmentKey) {
      paramObject = paramObject;
      object2 = paramObject.getKeySymbol();
      object1 = paramObject.getKeyProperty();
    } 
    if (object2 instanceof Symbol) {
      paramObject = object2;
      return getLocation((Symbol)paramObject, object1, paramBoolean);
    } 
    paramObject = getSymbol((String)object2);
    return getLocation((Symbol)paramObject, object1, paramBoolean);
  }
  
  public abstract NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean);
  
  public final NamedLocation getLocation(Symbol paramSymbol, Object paramObject, boolean paramBoolean) {
    return getLocation(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject), paramBoolean);
  }
  
  public Symbol getSymbol(String paramString) {
    return defaultNamespace().getSymbol(paramString);
  }
  
  protected abstract boolean hasMoreElements(LocationEnumeration paramLocationEnumeration);
  
  public final boolean isBound(Symbol paramSymbol) {
    return isBound(paramSymbol, (Object)null);
  }
  
  public boolean isBound(Symbol paramSymbol, Object paramObject) {
    Location location = lookup(paramSymbol, paramObject);
    return (location == null) ? false : location.isBound();
  }
  
  public final boolean isLocked() {
    return ((this.flags & 0x3) == 0);
  }
  
  public final Location lookup(Symbol paramSymbol) {
    return getLocation(paramSymbol, (Object)null, false);
  }
  
  public final Location lookup(Symbol paramSymbol, Object paramObject) {
    return getLocation(paramSymbol, paramObject, false);
  }
  
  public abstract NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt);
  
  public final Object put(Object paramObject1, Object paramObject2) {
    paramObject1 = getLocation(paramObject1, true);
    Object object = paramObject1.get(null);
    paramObject1.set(paramObject2);
    return object;
  }
  
  public final Object put(String paramString, Object paramObject) {
    return put(paramString, paramObject);
  }
  
  public final void put(Symbol paramSymbol, Object paramObject) {
    put(paramSymbol, (Object)null, paramObject);
  }
  
  public void put(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    Location location = getLocation(paramSymbol, paramObject1);
    if (location.isConstant()) {
      define(paramSymbol, paramObject1, paramObject2);
      return;
    } 
    location.set(paramObject2);
  }
  
  public final void putFunction(Symbol paramSymbol, Object paramObject) {
    put(paramSymbol, EnvironmentKey.FUNCTION, paramObject);
  }
  
  public final Object remove(EnvironmentKey paramEnvironmentKey) {
    Symbol symbol = paramEnvironmentKey.getKeySymbol();
    Object object = paramEnvironmentKey.getKeyProperty();
    return remove(symbol, object, symbol.hashCode() ^ System.identityHashCode(object));
  }
  
  public final Object remove(Symbol paramSymbol, Object paramObject) {
    return remove(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject));
  }
  
  public Object remove(Symbol paramSymbol, Object paramObject, int paramInt) {
    Location location = unlink(paramSymbol, paramObject, paramInt);
    if (location == null)
      return null; 
    paramObject = location.get(null);
    location.undefine();
    return paramObject;
  }
  
  public final Object remove(Object paramObject) {
    if (paramObject instanceof EnvironmentKey) {
      paramObject = paramObject;
      return remove(paramObject.getKeySymbol(), paramObject.getKeyProperty());
    } 
    if (paramObject instanceof Symbol) {
      paramObject = paramObject;
      return remove((Symbol)paramObject, (Object)null, paramObject.hashCode() ^ System.identityHashCode(null));
    } 
    paramObject = getSymbol((String)paramObject);
    return remove((Symbol)paramObject, (Object)null, paramObject.hashCode() ^ System.identityHashCode(null));
  }
  
  public final void remove(Symbol paramSymbol) {
    remove(paramSymbol, (Object)null, paramSymbol.hashCode());
  }
  
  public final void removeFunction(Symbol paramSymbol) {
    remove(paramSymbol, EnvironmentKey.FUNCTION);
  }
  
  public void setCanDefine(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x1;
      return;
    } 
    this.flags &= 0xFFFFFFFE;
  }
  
  public void setCanRedefine(boolean paramBoolean) {
    if (paramBoolean) {
      this.flags |= 0x2;
      return;
    } 
    this.flags &= 0xFFFFFFFD;
  }
  
  public void setFlag(boolean paramBoolean, int paramInt) {
    if (paramBoolean) {
      this.flags |= paramInt;
      return;
    } 
    this.flags &= paramInt ^ 0xFFFFFFFF;
  }
  
  public final void setIndirectDefines() {
    this.flags |= 0x20;
    ((InheritingEnvironment)this).baseTimestamp = Integer.MAX_VALUE;
  }
  
  public void setLocked() {
    this.flags &= 0xFFFFFFF8;
  }
  
  public String toString() {
    return "#<environment " + getName() + '>';
  }
  
  public String toStringVerbose() {
    return toString();
  }
  
  public Location unlink(Symbol paramSymbol, Object paramObject, int paramInt) {
    throw new RuntimeException("unsupported operation: unlink (aka undefine)");
  }
  
  static class InheritedLocal extends InheritableThreadLocal<Environment> {
    protected Environment childValue(Environment param1Environment) {
      Environment environment = param1Environment;
      if (param1Environment == null)
        environment = Environment.getCurrent(); 
      param1Environment = environment.cloneForThread();
      ((SimpleEnvironment)param1Environment).flags |= 0x8;
      ((SimpleEnvironment)param1Environment).flags &= 0xFFFFFFEF;
      return param1Environment;
    }
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/Environment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */