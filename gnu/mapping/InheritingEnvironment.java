package gnu.mapping;

public class InheritingEnvironment extends SimpleEnvironment {
  int baseTimestamp;
  
  Environment[] inherited;
  
  Namespace[] namespaceMap;
  
  int numInherited;
  
  Object[] propertyMap;
  
  public InheritingEnvironment(String paramString, Environment paramEnvironment) {
    super(paramString);
    addParent(paramEnvironment);
    if (paramEnvironment instanceof SimpleEnvironment) {
      SimpleEnvironment simpleEnvironment = (SimpleEnvironment)paramEnvironment;
      int i = simpleEnvironment.currentTimestamp + 1;
      simpleEnvironment.currentTimestamp = i;
      this.baseTimestamp = i;
      this.currentTimestamp = i;
    } 
  }
  
  public void addParent(Environment paramEnvironment) {
    if (this.numInherited == 0) {
      this.inherited = new Environment[4];
    } else if (this.numInherited <= this.inherited.length) {
      Environment[] arrayOfEnvironment = new Environment[this.numInherited * 2];
      System.arraycopy(this.inherited, 0, arrayOfEnvironment, 0, this.numInherited);
      this.inherited = arrayOfEnvironment;
    } 
    this.inherited[this.numInherited] = paramEnvironment;
    this.numInherited++;
  }
  
  public LocationEnumeration enumerateAllLocations() {
    LocationEnumeration locationEnumeration = new LocationEnumeration(this.table, 1 << this.log2Size);
    locationEnumeration.env = this;
    if (this.inherited != null && this.inherited.length > 0) {
      locationEnumeration.inherited = this.inherited[0].enumerateAllLocations();
      locationEnumeration.index = 0;
    } 
    return locationEnumeration;
  }
  
  public NamedLocation getLocation(Symbol paramSymbol, Object paramObject, int paramInt, boolean paramBoolean) {
    // Byte code:
    //   0: aconst_null
    //   1: astore #7
    //   3: aload_0
    //   4: monitorenter
    //   5: aload_0
    //   6: aload_1
    //   7: aload_2
    //   8: iload_3
    //   9: invokevirtual lookupDirect : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   12: astore #6
    //   14: aload #6
    //   16: ifnull -> 43
    //   19: iload #4
    //   21: ifne -> 36
    //   24: aload #6
    //   26: invokevirtual isBound : ()Z
    //   29: istore #5
    //   31: iload #5
    //   33: ifeq -> 43
    //   36: aload #6
    //   38: astore_1
    //   39: aload_0
    //   40: monitorexit
    //   41: aload_1
    //   42: areturn
    //   43: aload_0
    //   44: getfield flags : I
    //   47: bipush #32
    //   49: iand
    //   50: ifeq -> 179
    //   53: iload #4
    //   55: ifeq -> 179
    //   58: aload_0
    //   59: getfield inherited : [Lgnu/mapping/Environment;
    //   62: iconst_0
    //   63: aaload
    //   64: aload_1
    //   65: aload_2
    //   66: iload_3
    //   67: iconst_1
    //   68: invokevirtual getLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;IZ)Lgnu/mapping/NamedLocation;
    //   71: astore #6
    //   73: aload #6
    //   75: ifnull -> 221
    //   78: iload #4
    //   80: ifeq -> 245
    //   83: aload_0
    //   84: aload_1
    //   85: aload_2
    //   86: iload_3
    //   87: invokevirtual addUnboundLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   90: astore #7
    //   92: aload_0
    //   93: getfield flags : I
    //   96: iconst_1
    //   97: iand
    //   98: ifne -> 117
    //   101: aload #6
    //   103: invokevirtual isBound : ()Z
    //   106: ifeq -> 117
    //   109: aload_0
    //   110: aload_1
    //   111: aload_2
    //   112: aload #7
    //   114: invokevirtual redefineError : (Lgnu/mapping/Symbol;Ljava/lang/Object;Lgnu/mapping/Location;)V
    //   117: aload #7
    //   119: aload #6
    //   121: putfield base : Lgnu/mapping/Location;
    //   124: aload #6
    //   126: getfield value : Ljava/lang/Object;
    //   129: getstatic gnu/mapping/IndirectableLocation.INDIRECT_FLUIDS : Ljava/lang/Object;
    //   132: if_acmpne -> 191
    //   135: aload #7
    //   137: aload #6
    //   139: getfield value : Ljava/lang/Object;
    //   142: putfield value : Ljava/lang/Object;
    //   145: aload #7
    //   147: astore_1
    //   148: aload #7
    //   150: instanceof gnu/mapping/SharedLocation
    //   153: ifeq -> 39
    //   156: aload #7
    //   158: checkcast gnu/mapping/SharedLocation
    //   161: aload_0
    //   162: getfield baseTimestamp : I
    //   165: putfield timestamp : I
    //   168: aload #7
    //   170: astore_1
    //   171: goto -> 39
    //   174: astore_1
    //   175: aload_0
    //   176: monitorexit
    //   177: aload_1
    //   178: athrow
    //   179: aload_0
    //   180: aload_1
    //   181: aload_2
    //   182: iload_3
    //   183: invokevirtual lookupInherited : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   186: astore #6
    //   188: goto -> 73
    //   191: aload_0
    //   192: getfield flags : I
    //   195: bipush #16
    //   197: iand
    //   198: ifeq -> 212
    //   201: aload #7
    //   203: getstatic gnu/mapping/IndirectableLocation.DIRECT_ON_SET : Ljava/lang/Object;
    //   206: putfield value : Ljava/lang/Object;
    //   209: goto -> 145
    //   212: aload #7
    //   214: aconst_null
    //   215: putfield value : Ljava/lang/Object;
    //   218: goto -> 145
    //   221: aload #7
    //   223: astore #6
    //   225: iload #4
    //   227: ifeq -> 239
    //   230: aload_0
    //   231: aload_1
    //   232: aload_2
    //   233: iload_3
    //   234: invokevirtual addUnboundLocation : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   237: astore #6
    //   239: aload #6
    //   241: astore_1
    //   242: goto -> 39
    //   245: aload #6
    //   247: astore_1
    //   248: goto -> 39
    // Exception table:
    //   from	to	target	type
    //   5	14	174	finally
    //   24	31	174	finally
    //   43	53	174	finally
    //   58	73	174	finally
    //   83	117	174	finally
    //   117	145	174	finally
    //   148	168	174	finally
    //   179	188	174	finally
    //   191	209	174	finally
    //   212	218	174	finally
    //   230	239	174	finally
  }
  
  public final int getNumParents() {
    return this.numInherited;
  }
  
  public final Environment getParent(int paramInt) {
    return this.inherited[paramInt];
  }
  
  protected boolean hasMoreElements(LocationEnumeration paramLocationEnumeration) {
    if (paramLocationEnumeration.inherited != null)
      while (true) {
        NamedLocation namedLocation = paramLocationEnumeration.nextLoc;
        while (true) {
          paramLocationEnumeration.inherited.nextLoc = namedLocation;
          if (!paramLocationEnumeration.inherited.hasMoreElements()) {
            paramLocationEnumeration.prevLoc = null;
            paramLocationEnumeration.nextLoc = paramLocationEnumeration.inherited.nextLoc;
            int i = paramLocationEnumeration.index + 1;
            paramLocationEnumeration.index = i;
            if (i == this.numInherited) {
              paramLocationEnumeration.inherited = null;
              paramLocationEnumeration.bindings = this.table;
              paramLocationEnumeration.index = 1 << this.log2Size;
              return super.hasMoreElements(paramLocationEnumeration);
            } 
          } else {
            namedLocation = paramLocationEnumeration.inherited.nextLoc;
            if (lookup(namedLocation.name, namedLocation.property) == namedLocation) {
              paramLocationEnumeration.nextLoc = namedLocation;
              return true;
            } 
            namedLocation = namedLocation.next;
            continue;
          } 
          paramLocationEnumeration.inherited = this.inherited[paramLocationEnumeration.index].enumerateAllLocations();
        } 
        break;
      }  
    return super.hasMoreElements(paramLocationEnumeration);
  }
  
  public NamedLocation lookup(Symbol paramSymbol, Object paramObject, int paramInt) {
    NamedLocation namedLocation = super.lookup(paramSymbol, paramObject, paramInt);
    return (namedLocation != null && namedLocation.isBound()) ? namedLocation : lookupInherited(paramSymbol, paramObject, paramInt);
  }
  
  public NamedLocation lookupInherited(Symbol paramSymbol, Object paramObject, int paramInt) {
    // Byte code:
    //   0: iconst_0
    //   1: istore #4
    //   3: iload #4
    //   5: aload_0
    //   6: getfield numInherited : I
    //   9: if_icmpge -> 243
    //   12: aload_1
    //   13: astore #7
    //   15: aload_2
    //   16: astore #5
    //   18: aload #7
    //   20: astore #6
    //   22: aload_0
    //   23: getfield namespaceMap : [Lgnu/mapping/Namespace;
    //   26: ifnull -> 112
    //   29: aload #7
    //   31: astore #6
    //   33: aload_0
    //   34: getfield namespaceMap : [Lgnu/mapping/Namespace;
    //   37: arraylength
    //   38: iload #4
    //   40: iconst_2
    //   41: imul
    //   42: if_icmple -> 112
    //   45: aload_0
    //   46: getfield namespaceMap : [Lgnu/mapping/Namespace;
    //   49: iload #4
    //   51: iconst_2
    //   52: imul
    //   53: aaload
    //   54: astore #8
    //   56: aload_0
    //   57: getfield namespaceMap : [Lgnu/mapping/Namespace;
    //   60: iload #4
    //   62: iconst_2
    //   63: imul
    //   64: iconst_1
    //   65: iadd
    //   66: aaload
    //   67: astore #9
    //   69: aload #8
    //   71: ifnonnull -> 83
    //   74: aload #7
    //   76: astore #6
    //   78: aload #9
    //   80: ifnull -> 112
    //   83: aload_1
    //   84: invokevirtual getNamespace : ()Lgnu/mapping/Namespace;
    //   87: aload #9
    //   89: if_acmpeq -> 101
    //   92: iload #4
    //   94: iconst_1
    //   95: iadd
    //   96: istore #4
    //   98: goto -> 3
    //   101: aload #8
    //   103: aload_1
    //   104: invokevirtual getName : ()Ljava/lang/String;
    //   107: invokestatic make : (Ljava/lang/Object;Ljava/lang/String;)Lgnu/mapping/Symbol;
    //   110: astore #6
    //   112: aload #5
    //   114: astore #7
    //   116: aload_0
    //   117: getfield propertyMap : [Ljava/lang/Object;
    //   120: ifnull -> 187
    //   123: aload #5
    //   125: astore #7
    //   127: aload_0
    //   128: getfield propertyMap : [Ljava/lang/Object;
    //   131: arraylength
    //   132: iload #4
    //   134: iconst_2
    //   135: imul
    //   136: if_icmple -> 187
    //   139: aload_0
    //   140: getfield propertyMap : [Ljava/lang/Object;
    //   143: iload #4
    //   145: iconst_2
    //   146: imul
    //   147: aaload
    //   148: astore #8
    //   150: aload_0
    //   151: getfield propertyMap : [Ljava/lang/Object;
    //   154: iload #4
    //   156: iconst_2
    //   157: imul
    //   158: iconst_1
    //   159: iadd
    //   160: aaload
    //   161: astore #9
    //   163: aload #8
    //   165: ifnonnull -> 177
    //   168: aload #5
    //   170: astore #7
    //   172: aload #9
    //   174: ifnull -> 187
    //   177: aload_2
    //   178: aload #9
    //   180: if_acmpne -> 92
    //   183: aload #8
    //   185: astore #7
    //   187: aload_0
    //   188: getfield inherited : [Lgnu/mapping/Environment;
    //   191: iload #4
    //   193: aaload
    //   194: aload #6
    //   196: aload #7
    //   198: iload_3
    //   199: invokevirtual lookup : (Lgnu/mapping/Symbol;Ljava/lang/Object;I)Lgnu/mapping/NamedLocation;
    //   202: astore #5
    //   204: aload #5
    //   206: ifnull -> 92
    //   209: aload #5
    //   211: invokevirtual isBound : ()Z
    //   214: ifeq -> 92
    //   217: aload #5
    //   219: instanceof gnu/mapping/SharedLocation
    //   222: ifeq -> 240
    //   225: aload #5
    //   227: checkcast gnu/mapping/SharedLocation
    //   230: getfield timestamp : I
    //   233: aload_0
    //   234: getfield baseTimestamp : I
    //   237: if_icmpge -> 92
    //   240: aload #5
    //   242: areturn
    //   243: aconst_null
    //   244: areturn
  }
  
  protected void toStringBase(StringBuffer paramStringBuffer) {
    paramStringBuffer.append(" baseTs:");
    paramStringBuffer.append(this.baseTimestamp);
    for (int i = 0; i < this.numInherited; i++) {
      paramStringBuffer.append(" base:");
      paramStringBuffer.append(this.inherited[i].toStringVerbose());
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/InheritingEnvironment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */