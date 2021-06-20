package gnu.mapping;

import gnu.lists.LList;
import gnu.lists.Pair;

public class PropertyLocation extends Location {
  Pair pair;
  
  public static Object getProperty(Object paramObject1, Object paramObject2, Object paramObject3) {
    return getProperty(paramObject1, paramObject2, paramObject3, Environment.getCurrent());
  }
  
  public static Object getProperty(Object paramObject1, Object paramObject2, Object paramObject3, Environment paramEnvironment) {
    Object object = paramObject1;
    if (!(paramObject1 instanceof Symbol)) {
      if (paramObject1 instanceof String) {
        object = Namespace.getDefaultSymbol((String)paramObject1);
        return paramEnvironment.get((Symbol)object, paramObject2, paramObject3);
      } 
    } else {
      return paramEnvironment.get((Symbol)object, paramObject2, paramObject3);
    } 
    return plistGet(paramEnvironment.get(Symbol.PLIST, paramObject1, LList.Empty), paramObject2, paramObject3);
  }
  
  public static Object getPropertyList(Object paramObject) {
    return Environment.getCurrent().get(Symbol.PLIST, paramObject, LList.Empty);
  }
  
  public static Object getPropertyList(Object paramObject, Environment paramEnvironment) {
    return paramEnvironment.get(Symbol.PLIST, paramObject, LList.Empty);
  }
  
  public static Object plistGet(Object paramObject1, Object paramObject2, Object paramObject3) {
    Object object;
    while (true) {
      object = paramObject3;
      if (paramObject1 instanceof Pair) {
        object = paramObject1;
        if (object.getCar() == paramObject2) {
          object = ((Pair)object.getCdr()).getCar();
          break;
        } 
        continue;
      } 
      break;
    } 
    return object;
  }
  
  public static Object plistPut(Object paramObject1, Object paramObject2, Object paramObject3) {
    for (Object object = paramObject1; object instanceof Pair; object = pair.getCdr()) {
      object = object;
      Pair pair = (Pair)object.getCdr();
      if (object.getCar() == paramObject2) {
        pair.setCar(paramObject3);
        return paramObject1;
      } 
    } 
    return new Pair(paramObject2, new Pair(paramObject3, paramObject1));
  }
  
  public static Object plistRemove(Object paramObject1, Object paramObject2) {
    Pair pair = null;
    Object object = paramObject1;
    while (true) {
      Object object2 = paramObject1;
      if (object instanceof Pair) {
        Pair pair1 = (Pair)object;
        object2 = pair1.getCdr();
        object = object2.getCdr();
        if (pair1.getCar() == paramObject2) {
          if (pair == null)
            return object; 
          pair.setCdr(object);
          return paramObject1;
        } 
      } else {
        return object2;
      } 
      Object object1 = object2;
    } 
  }
  
  public static void putProperty(Object paramObject1, Object paramObject2, Object paramObject3) {
    putProperty(paramObject1, paramObject2, paramObject3, Environment.getCurrent());
  }
  
  public static void putProperty(Object paramObject1, Object paramObject2, Object paramObject3, Environment paramEnvironment) {
    Object object = paramObject1;
    if (!(paramObject1 instanceof Symbol))
      if (paramObject1 instanceof String) {
        object = Namespace.getDefaultSymbol((String)paramObject1);
      } else {
        paramObject1 = paramEnvironment.getLocation(Symbol.PLIST, paramObject1);
        paramObject1.set(plistPut(paramObject1.get(LList.Empty), paramObject2, paramObject3));
        return;
      }  
    paramObject1 = paramEnvironment.lookup((Symbol)object, paramObject2);
    if (paramObject1 != null) {
      paramObject1 = paramObject1.getBase();
      if (paramObject1 instanceof PropertyLocation) {
        ((PropertyLocation)paramObject1).set(paramObject3);
        return;
      } 
    } 
    paramObject1 = paramEnvironment.getLocation(Symbol.PLIST, object);
    paramObject3 = new Pair(paramObject3, paramObject1.get(LList.Empty));
    paramObject1.set(new Pair(paramObject2, paramObject3));
    paramObject1 = new PropertyLocation();
    ((PropertyLocation)paramObject1).pair = (Pair)paramObject3;
    paramEnvironment.addLocation((Symbol)object, paramObject2, (Location)paramObject1);
  }
  
  public static boolean removeProperty(Object paramObject1, Object paramObject2) {
    return removeProperty(paramObject1, paramObject2, Environment.getCurrent());
  }
  
  public static boolean removeProperty(Object paramObject1, Object paramObject2, Environment paramEnvironment) {
    Location location = paramEnvironment.lookup(Symbol.PLIST, paramObject1);
    if (location != null) {
      Object object = location.get(LList.Empty);
      if (object instanceof Pair) {
        object = object;
        Pair pair = null;
        while (true) {
          if (object.getCar() == paramObject2) {
            object = ((Pair)object.getCdr()).getCdr();
            if (pair == null) {
              location.set(object);
            } else {
              pair.setCdr(object);
            } 
            if (paramObject1 instanceof Symbol)
              paramEnvironment.remove((Symbol)paramObject1, paramObject2); 
            return true;
          } 
          Object object1 = object.getCdr();
          if (object1 instanceof Pair) {
            Object object2 = object;
            object = object1;
            continue;
          } 
          return false;
        } 
      } 
    } 
    return false;
  }
  
  public static void setPropertyList(Object paramObject1, Object paramObject2) {
    setPropertyList(paramObject1, paramObject2, Environment.getCurrent());
  }
  
  public static void setPropertyList(Object paramObject1, Object paramObject2, Environment paramEnvironment) {
    // Byte code:
    //   0: aload_2
    //   1: monitorenter
    //   2: aload_2
    //   3: getstatic gnu/mapping/Symbol.PLIST : Lgnu/mapping/Symbol;
    //   6: aload_0
    //   7: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   10: astore_3
    //   11: aload_0
    //   12: instanceof gnu/mapping/Symbol
    //   15: ifeq -> 48
    //   18: aload_0
    //   19: checkcast gnu/mapping/Symbol
    //   22: astore #4
    //   24: aload_3
    //   25: getstatic gnu/lists/LList.Empty : Lgnu/lists/LList;
    //   28: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   31: astore_0
    //   32: aload_0
    //   33: instanceof gnu/lists/Pair
    //   36: ifne -> 56
    //   39: aload_1
    //   40: astore_0
    //   41: aload_0
    //   42: instanceof gnu/lists/Pair
    //   45: ifne -> 100
    //   48: aload_3
    //   49: aload_1
    //   50: invokevirtual set : (Ljava/lang/Object;)V
    //   53: aload_2
    //   54: monitorexit
    //   55: return
    //   56: aload_0
    //   57: checkcast gnu/lists/Pair
    //   60: astore_0
    //   61: aload_0
    //   62: invokevirtual getCar : ()Ljava/lang/Object;
    //   65: astore #5
    //   67: aload_1
    //   68: aload #5
    //   70: aconst_null
    //   71: invokestatic plistGet : (Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   74: ifnull -> 86
    //   77: aload_2
    //   78: aload #4
    //   80: aload #5
    //   82: invokevirtual remove : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Ljava/lang/Object;
    //   85: pop
    //   86: aload_0
    //   87: invokevirtual getCdr : ()Ljava/lang/Object;
    //   90: checkcast gnu/lists/Pair
    //   93: invokevirtual getCdr : ()Ljava/lang/Object;
    //   96: astore_0
    //   97: goto -> 32
    //   100: aload_0
    //   101: checkcast gnu/lists/Pair
    //   104: astore #5
    //   106: aload #5
    //   108: invokevirtual getCar : ()Ljava/lang/Object;
    //   111: astore #6
    //   113: aload_2
    //   114: aload #4
    //   116: aload #6
    //   118: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;)Lgnu/mapping/Location;
    //   121: astore_0
    //   122: aload_0
    //   123: ifnull -> 168
    //   126: aload_0
    //   127: invokevirtual getBase : ()Lgnu/mapping/Location;
    //   130: astore_0
    //   131: aload_0
    //   132: instanceof gnu/mapping/PropertyLocation
    //   135: ifeq -> 168
    //   138: aload_0
    //   139: checkcast gnu/mapping/PropertyLocation
    //   142: astore_0
    //   143: aload #5
    //   145: invokevirtual getCdr : ()Ljava/lang/Object;
    //   148: checkcast gnu/lists/Pair
    //   151: astore #5
    //   153: aload_0
    //   154: aload #5
    //   156: putfield pair : Lgnu/lists/Pair;
    //   159: aload #5
    //   161: invokevirtual getCdr : ()Ljava/lang/Object;
    //   164: astore_0
    //   165: goto -> 41
    //   168: new gnu/mapping/PropertyLocation
    //   171: dup
    //   172: invokespecial <init> : ()V
    //   175: astore_0
    //   176: aload_2
    //   177: aload #4
    //   179: aload #6
    //   181: aload_0
    //   182: invokevirtual addLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/Location;)Lgnu/mapping/NamedLocation;
    //   185: pop
    //   186: goto -> 143
    //   189: astore_0
    //   190: aload_2
    //   191: monitorexit
    //   192: aload_0
    //   193: athrow
    // Exception table:
    //   from	to	target	type
    //   2	32	189	finally
    //   32	39	189	finally
    //   41	48	189	finally
    //   48	55	189	finally
    //   56	86	189	finally
    //   86	97	189	finally
    //   100	122	189	finally
    //   126	143	189	finally
    //   143	165	189	finally
    //   168	186	189	finally
    //   190	192	189	finally
  }
  
  public final Object get(Object paramObject) {
    return this.pair.getCar();
  }
  
  public boolean isBound() {
    return true;
  }
  
  public final void set(Object paramObject) {
    this.pair.setCar(paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/PropertyLocation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */