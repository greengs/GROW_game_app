package gnu.mapping;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.ObjectStreamException;
import java.util.Set;

public class SimpleEnvironment extends Environment {
  int currentTimestamp;
  
  int log2Size = 4;
  
  private int mask;
  
  int num_bindings;
  
  NamedLocation sharedTail;
  
  NamedLocation[] table;
  
  public SimpleEnvironment() {
    this(64);
  }
  
  public SimpleEnvironment(int paramInt) {
    while (paramInt > 1 << this.log2Size)
      this.log2Size++; 
    paramInt = 1 << this.log2Size;
    this.table = new NamedLocation[paramInt];
    this.mask = paramInt - 1;
    this.sharedTail = new PlainLocation(null, null, this);
  }
  
  public SimpleEnvironment(String paramString) {
    this();
    setName(paramString);
  }
  
  public static Location getCurrentLocation(String paramString) {
    return getCurrent().getLocation(paramString, true);
  }
  
  public static Object lookup_global(Symbol paramSymbol) throws UnboundLocationException {
    Location location = getCurrent().lookup(paramSymbol);
    if (location == null)
      throw new UnboundLocationException(paramSymbol); 
    return location.get();
  }
  
  NamedLocation addLocation(Symbol paramSymbol, Object paramObject, int paramInt, Location paramLocation) {
    boolean bool;
    Location location = paramLocation;
    if (paramLocation instanceof ThreadLocation) {
      location = paramLocation;
      if (((ThreadLocation)paramLocation).property == paramObject)
        location = ((ThreadLocation)paramLocation).getLocation(); 
    } 
    paramLocation = lookupDirect(paramSymbol, paramObject, paramInt);
    if (location == paramLocation)
      return (NamedLocation)paramLocation; 
    if (paramLocation != null) {
      bool = true;
    } else {
      bool = false;
    } 
    if (!bool)
      paramLocation = addUnboundLocation(paramSymbol, paramObject, paramInt); 
    if ((this.flags & 0x3) != 3) {
      boolean bool1 = bool;
      if (bool)
        bool1 = paramLocation.isBound(); 
      if (bool1 ? ((this.flags & 0x2) == 0) : ((this.flags & 0x1) == 0 && location.isBound()))
        redefineError(paramSymbol, paramObject, paramLocation); 
    } 
    if ((this.flags & 0x20) != 0) {
      ((NamedLocation)paramLocation).base = ((SimpleEnvironment)((InheritingEnvironment)this).getParent(0)).addLocation(paramSymbol, paramObject, paramInt, location);
      ((NamedLocation)paramLocation).value = IndirectableLocation.INDIRECT_FLUIDS;
      return (NamedLocation)paramLocation;
    } 
    ((NamedLocation)paramLocation).base = location;
    ((NamedLocation)paramLocation).value = IndirectableLocation.INDIRECT_FLUIDS;
    return (NamedLocation)paramLocation;
  }
  
  public NamedLocation addLocation(Symbol paramSymbol, Object paramObject, Location paramLocation) {
    return addLocation(paramSymbol, paramObject, paramSymbol.hashCode() ^ System.identityHashCode(paramObject), paramLocation);
  }
  
  protected NamedLocation addUnboundLocation(Symbol paramSymbol, Object paramObject, int paramInt) {
    NamedLocation namedLocation = newEntry(paramSymbol, paramObject, paramInt & this.mask);
    namedLocation.base = null;
    namedLocation.value = Location.UNBOUND;
    return namedLocation;
  }
  
  public NamedLocation define(Symbol paramSymbol, Object paramObject1, int paramInt, Object paramObject2) {
    paramInt &= this.mask;
    for (NamedLocation namedLocation = this.table[paramInt];; namedLocation = namedLocation.next) {
      NamedLocation namedLocation1;
      if (namedLocation == null) {
        namedLocation1 = newEntry(paramSymbol, paramObject1, paramInt);
        namedLocation1.set(paramObject2);
        return namedLocation1;
      } 
      if (namedLocation.matches((Symbol)namedLocation1, paramObject1)) {
        if (namedLocation.isBound() ? getCanDefine() : getCanRedefine())
          redefineError((Symbol)namedLocation1, paramObject1, namedLocation); 
        namedLocation.base = null;
        namedLocation.value = paramObject2;
        return namedLocation;
      } 
    } 
  }
  
  public void define(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    define(paramSymbol, paramObject1, paramSymbol.hashCode() ^ System.identityHashCode(paramObject1), paramObject2);
  }
  
  public Set entrySet() {
    return new EnvironmentMappings(this);
  }
  
  public LocationEnumeration enumerateAllLocations() {
    return enumerateLocations();
  }
  
  public LocationEnumeration enumerateLocations() {
    LocationEnumeration locationEnumeration = new LocationEnumeration(this.table, 1 << this.log2Size);
    locationEnumeration.env = this;
    return locationEnumeration;
  }
  
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: aload_2
    //   5: iload_3
    //   6: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   9: astore #5
    //   11: aload #5
    //   13: ifnull -> 23
    //   16: aload #5
    //   18: astore_1
    //   19: aload_0
    //   20: monitorexit
    //   21: aload_1
    //   22: areturn
    //   23: iload #4
    //   25: ifne -> 33
    //   28: aconst_null
    //   29: astore_1
    //   30: goto -> 19
    //   33: aload_0
    //   34: aload_1
    //   35: aload_2
    //   36: iload_3
    //   37: invokevirtual addUnboundLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   40: astore_1
    //   41: goto -> 19
    //   44: astore_1
    //   45: aload_0
    //   46: monitorexit
    //   47: aload_1
    //   48: athrow
    // Exception table:
    //   from	to	target	type
    //   2	11	44	finally
    //   33	41	44	finally
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration) {
    while (true) {
      if (paramLocationEnumeration.nextLoc == null) {
        paramLocationEnumeration.prevLoc = null;
        int i = paramLocationEnumeration.index - 1;
        paramLocationEnumeration.index = i;
        if (i < 0)
          return false; 
        paramLocationEnumeration.nextLoc = paramLocationEnumeration.bindings[paramLocationEnumeration.index];
        if (paramLocationEnumeration.nextLoc != null) {
          if (paramLocationEnumeration.nextLoc.name == null) {
            paramLocationEnumeration.nextLoc = paramLocationEnumeration.nextLoc.next;
            continue;
          } 
          return true;
        } 
        continue;
      } 
      if (paramLocationEnumeration.nextLoc.name == null) {
        paramLocationEnumeration.nextLoc = paramLocationEnumeration.nextLoc.next;
        continue;
      } 
      return true;
    } 
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt) {
    return lookupDirect(paramSymbol, paramObject, paramInt);
  }
  
  public NamedLocation lookupDirect(Symbol paramSymbol, Object paramObject, int paramInt) {
    int i = this.mask;
    for (NamedLocation namedLocation = this.table[paramInt & i]; namedLocation != null; namedLocation = namedLocation.next) {
      if (namedLocation.matches(paramSymbol, paramObject))
        return namedLocation; 
    } 
    return null;
  }
  
  NamedLocation newEntry(Symbol paramSymbol, Object paramObject, int paramInt) {
    NamedLocation namedLocation = newLocation(paramSymbol, paramObject);
    paramObject = this.table[paramInt];
    Object object = paramObject;
    if (paramObject == null)
      object = this.sharedTail; 
    namedLocation.next = (NamedLocation)object;
    this.table[paramInt] = namedLocation;
    this.num_bindings++;
    if (this.num_bindings >= this.table.length)
      rehash(); 
    return namedLocation;
  }
  
  NamedLocation newLocation(Symbol paramSymbol, Object paramObject) {
    return (NamedLocation)(((this.flags & 0x8) != 0) ? new SharedLocation(paramSymbol, paramObject, this.currentTimestamp) : new PlainLocation(paramSymbol, paramObject));
  }
  
  public void put(Symbol paramSymbol, Object paramObject1, Object paramObject2) {
    boolean bool;
    if ((this.flags & 0x4) != 0) {
      bool = true;
    } else {
      bool = false;
    } 
    paramObject1 = getLocation(paramSymbol, paramObject1, bool);
    if (paramObject1 == null)
      throw new UnboundLocationException(paramSymbol); 
    if (paramObject1.isConstant())
      throw new IllegalStateException("attempt to modify read-only location: " + paramSymbol + " in " + this + " loc:" + paramObject1); 
    paramObject1.set(paramObject2);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setSymbol(paramObjectInput.readObject());
  }
  
  public Object readResolve() throws ObjectStreamException {
    String str = getName();
    Environment environment = (Environment)envTable.get(str);
    if (environment != null)
      return environment; 
    envTable.put(str, this);
    return this;
  }
  
  protected void redefineError(Symbol paramSymbol, Object paramObject, Location paramLocation) {
    throw new IllegalStateException("prohibited define/redefine of " + paramSymbol + " in " + this);
  }
  
  void rehash() {
    NamedLocation[] arrayOfNamedLocation1 = this.table;
    int i = arrayOfNamedLocation1.length;
    int j = i * 2;
    NamedLocation[] arrayOfNamedLocation2 = new NamedLocation[j];
    int k = j - 1;
    label19: while (true) {
      j = i - 1;
      if (j >= 0) {
        NamedLocation namedLocation = arrayOfNamedLocation1[j];
        while (true) {
          i = j;
          if (namedLocation != null) {
            i = j;
            if (namedLocation != this.sharedTail) {
              NamedLocation namedLocation1 = namedLocation.next;
              Symbol symbol = namedLocation.name;
              Object object2 = namedLocation.property;
              i = (symbol.hashCode() ^ System.identityHashCode(object2)) & k;
              object2 = arrayOfNamedLocation2[i];
              Object object1 = object2;
              if (object2 == null)
                object1 = this.sharedTail; 
              namedLocation.next = (NamedLocation)object1;
              arrayOfNamedLocation2[i] = namedLocation;
              namedLocation = namedLocation1;
              continue;
            } 
            continue label19;
          } 
          continue label19;
        } 
        break;
      } 
      this.table = arrayOfNamedLocation2;
      this.log2Size++;
      this.mask = k;
      return;
    } 
  }
  
  public int size() {
    return this.num_bindings;
  }
  
  protected void toStringBase(StringBuffer paramStringBuffer) {}
  
  public String toStringVerbose() {
    StringBuffer stringBuffer = new StringBuffer();
    toStringBase(stringBuffer);
    return "#<environment " + getName() + " num:" + this.num_bindings + " ts:" + this.currentTimestamp + stringBuffer + '>';
  }
  
  public Location unlink(Symbol paramSymbol, Object paramObject, int paramInt) {
    paramInt &= this.mask;
    NamedLocation namedLocation2 = null;
    for (NamedLocation namedLocation1 = this.table[paramInt]; namedLocation1 != null; namedLocation1 = namedLocation) {
      NamedLocation namedLocation = namedLocation1.next;
      if (namedLocation1.matches(paramSymbol, paramObject)) {
        if (!getCanRedefine())
          redefineError(paramSymbol, paramObject, namedLocation1); 
        if (namedLocation2 == null) {
          this.table[paramInt] = namedLocation;
          this.num_bindings--;
          return namedLocation1;
        } 
        namedLocation2.next = namedLocation1;
        this.num_bindings--;
        return namedLocation1;
      } 
      namedLocation2 = namedLocation1;
    } 
    return null;
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getSymbol());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/SimpleEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */