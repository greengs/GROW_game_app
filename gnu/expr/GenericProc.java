package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import gnu.mapping.WrongType;

public class GenericProc extends MethodProc {
  int count;
  
  int maxArgs;
  
  protected MethodProc[] methods;
  
  int minArgs;
  
  public GenericProc() {}
  
  public GenericProc(String paramString) {
    setName(paramString);
  }
  
  public static GenericProc make(Object[] paramArrayOfObject) {
    GenericProc genericProc = new GenericProc();
    genericProc.setProperties(paramArrayOfObject);
    return genericProc;
  }
  
  public static GenericProc makeWithoutSorting(Object... paramVarArgs) {
    GenericProc genericProc = new GenericProc();
    int j = paramVarArgs.length;
    for (int i = 0; i < j; i++) {
      Object object = paramVarArgs[i];
      if (object instanceof Keyword) {
        object = object;
        genericProc.setProperty((Keyword)object, paramVarArgs[++i]);
      } else {
        genericProc.addAtEnd((MethodProc)object);
      } 
    } 
    return genericProc;
  }
  
  public void add(MethodProc paramMethodProc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield count : I
    //   6: istore_3
    //   7: aload_0
    //   8: aload_1
    //   9: invokevirtual addAtEnd : (Lgnu/mapping/MethodProc;)V
    //   12: iconst_0
    //   13: istore_2
    //   14: iload_2
    //   15: iload_3
    //   16: if_icmpge -> 58
    //   19: aload_1
    //   20: aload_0
    //   21: getfield methods : [Lgnu/mapping/MethodProc;
    //   24: iload_2
    //   25: aaload
    //   26: invokestatic mostSpecific : (Lgnu/mapping/MethodProc;Lgnu/mapping/MethodProc;)Lgnu/mapping/MethodProc;
    //   29: aload_1
    //   30: if_acmpne -> 61
    //   33: aload_0
    //   34: getfield methods : [Lgnu/mapping/MethodProc;
    //   37: iload_2
    //   38: aload_0
    //   39: getfield methods : [Lgnu/mapping/MethodProc;
    //   42: iload_2
    //   43: iconst_1
    //   44: iadd
    //   45: iload_3
    //   46: iload_2
    //   47: isub
    //   48: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   51: aload_0
    //   52: getfield methods : [Lgnu/mapping/MethodProc;
    //   55: iload_2
    //   56: aload_1
    //   57: aastore
    //   58: aload_0
    //   59: monitorexit
    //   60: return
    //   61: iload_2
    //   62: iconst_1
    //   63: iadd
    //   64: istore_2
    //   65: goto -> 14
    //   68: astore_1
    //   69: aload_0
    //   70: monitorexit
    //   71: aload_1
    //   72: athrow
    // Exception table:
    //   from	to	target	type
    //   2	12	68	finally
    //   19	58	68	finally
  }
  
  protected void add(MethodProc[] paramArrayOfMethodProc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: arraylength
    //   4: istore_3
    //   5: aload_0
    //   6: getfield methods : [Lgnu/mapping/MethodProc;
    //   9: ifnonnull -> 50
    //   12: aload_0
    //   13: iload_3
    //   14: anewarray gnu/mapping/MethodProc
    //   17: putfield methods : [Lgnu/mapping/MethodProc;
    //   20: goto -> 50
    //   23: iload_2
    //   24: iload_3
    //   25: if_icmpge -> 42
    //   28: aload_0
    //   29: aload_1
    //   30: iload_2
    //   31: aaload
    //   32: invokevirtual add : (Lgnu/mapping/MethodProc;)V
    //   35: iload_2
    //   36: iconst_1
    //   37: iadd
    //   38: istore_2
    //   39: goto -> 23
    //   42: aload_0
    //   43: monitorexit
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: monitorexit
    //   48: aload_1
    //   49: athrow
    //   50: iconst_0
    //   51: istore_2
    //   52: goto -> 23
    // Exception table:
    //   from	to	target	type
    //   2	20	45	finally
    //   28	35	45	finally
  }
  
  public void addAtEnd(MethodProc paramMethodProc) {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield count : I
    //   6: istore_2
    //   7: aload_0
    //   8: getfield methods : [Lgnu/mapping/MethodProc;
    //   11: ifnonnull -> 88
    //   14: aload_0
    //   15: bipush #8
    //   17: anewarray gnu/mapping/MethodProc
    //   20: putfield methods : [Lgnu/mapping/MethodProc;
    //   23: aload_0
    //   24: getfield methods : [Lgnu/mapping/MethodProc;
    //   27: iload_2
    //   28: aload_1
    //   29: aastore
    //   30: aload_1
    //   31: invokevirtual minArgs : ()I
    //   34: istore_3
    //   35: iload_3
    //   36: aload_0
    //   37: getfield minArgs : I
    //   40: if_icmplt -> 50
    //   43: aload_0
    //   44: getfield count : I
    //   47: ifne -> 55
    //   50: aload_0
    //   51: iload_3
    //   52: putfield minArgs : I
    //   55: aload_1
    //   56: invokevirtual maxArgs : ()I
    //   59: istore_3
    //   60: iload_3
    //   61: iconst_m1
    //   62: if_icmpeq -> 73
    //   65: iload_3
    //   66: aload_0
    //   67: getfield maxArgs : I
    //   70: if_icmple -> 78
    //   73: aload_0
    //   74: iload_3
    //   75: putfield maxArgs : I
    //   78: aload_0
    //   79: iload_2
    //   80: iconst_1
    //   81: iadd
    //   82: putfield count : I
    //   85: aload_0
    //   86: monitorexit
    //   87: return
    //   88: iload_2
    //   89: aload_0
    //   90: getfield methods : [Lgnu/mapping/MethodProc;
    //   93: arraylength
    //   94: if_icmplt -> 23
    //   97: aload_0
    //   98: getfield methods : [Lgnu/mapping/MethodProc;
    //   101: arraylength
    //   102: iconst_2
    //   103: imul
    //   104: anewarray gnu/mapping/MethodProc
    //   107: astore #4
    //   109: aload_0
    //   110: getfield methods : [Lgnu/mapping/MethodProc;
    //   113: iconst_0
    //   114: aload #4
    //   116: iconst_0
    //   117: iload_2
    //   118: invokestatic arraycopy : (Ljava/lang/Object;ILjava/lang/Object;II)V
    //   121: aload_0
    //   122: aload #4
    //   124: putfield methods : [Lgnu/mapping/MethodProc;
    //   127: goto -> 23
    //   130: astore_1
    //   131: aload_0
    //   132: monitorexit
    //   133: aload_1
    //   134: athrow
    // Exception table:
    //   from	to	target	type
    //   2	23	130	finally
    //   23	50	130	finally
    //   50	55	130	finally
    //   55	60	130	finally
    //   65	73	130	finally
    //   73	78	130	finally
    //   78	85	130	finally
    //   88	127	130	finally
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    if (this.count == 1)
      return this.methods[0].applyN(paramArrayOfObject); 
    checkArgCount((Procedure)this, paramArrayOfObject.length);
    CallContext callContext = CallContext.getInstance();
    for (int i = 0; i < this.count; i++) {
      if (this.methods[i].matchN(paramArrayOfObject, callContext) == 0)
        return callContext.runUntilValue(); 
    } 
    throw new WrongType(this, -1, null);
  }
  
  public MethodProc getMethod(int paramInt) {
    return (paramInt >= this.count) ? null : this.methods[paramInt];
  }
  
  public int getMethodCount() {
    return this.count;
  }
  
  public int isApplicable(Type[] paramArrayOfType) {
    byte b = -1;
    int i = this.count;
    while (true) {
      int k;
      int j = i - 1;
      i = b;
      if (j >= 0) {
        k = this.methods[j].isApplicable(paramArrayOfType);
        if (k == 1)
          return 1; 
      } else {
        return i;
      } 
      i = j;
      if (k == 0) {
        b = 0;
        i = j;
      } 
    } 
  }
  
  public int match0(CallContext paramCallContext) {
    boolean bool = false;
    if (this.count == 1)
      return this.methods[0].match0(paramCallContext); 
    int i = 0;
    while (i < this.count) {
      boolean bool1 = bool;
      if (this.methods[i].match0(paramCallContext) != 0) {
        i++;
        continue;
      } 
      return bool1;
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    boolean bool = false;
    if (this.count == 1)
      return this.methods[0].match1(paramObject, paramCallContext); 
    int i = 0;
    while (i < this.count) {
      boolean bool1 = bool;
      if (this.methods[i].match1(paramObject, paramCallContext) != 0) {
        i++;
        continue;
      } 
      return bool1;
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    boolean bool = false;
    if (this.count == 1)
      return this.methods[0].match2(paramObject1, paramObject2, paramCallContext); 
    int i = 0;
    while (i < this.count) {
      boolean bool1 = bool;
      if (this.methods[i].match2(paramObject1, paramObject2, paramCallContext) != 0) {
        i++;
        continue;
      } 
      return bool1;
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    boolean bool = false;
    if (this.count == 1)
      return this.methods[0].match3(paramObject1, paramObject2, paramObject3, paramCallContext); 
    int i = 0;
    while (i < this.count) {
      boolean bool1 = bool;
      if (this.methods[i].match3(paramObject1, paramObject2, paramObject3, paramCallContext) != 0) {
        i++;
        continue;
      } 
      return bool1;
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    if (this.count == 1)
      return this.methods[0].match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext); 
    int i;
    for (i = 0; i < this.count; i++) {
      if (this.methods[i].match4(paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext) == 0)
        return 0; 
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    if (this.count == 1)
      return this.methods[0].matchN(paramArrayOfObject, paramCallContext); 
    int j = paramArrayOfObject.length;
    Type[] arrayOfType = new Type[j];
    Language language = Language.getDefaultLanguage();
    int i;
    for (i = 0; i < j; i++) {
      Type type;
      Object<?> object = (Object<?>)paramArrayOfObject[i];
      if (object == null) {
        object = (Object<?>)Type.nullType;
      } else {
        object = (Object<?>)object.getClass();
        if (language != null) {
          type = language.getTypeFor((Class)object);
        } else {
          type = Type.make((Class)type);
        } 
      } 
      arrayOfType[i] = type;
    } 
    int[] arrayOfInt = new int[this.count];
    j = 0;
    int k = 0;
    int m = -1;
    i = 0;
    while (i < this.count) {
      int i1;
      int i2;
      int i3 = this.methods[i].isApplicable(arrayOfType);
      int n = m;
      if (j == 0) {
        n = m;
        if (i3 >= 0)
          n = i; 
      } 
      if (i3 > 0) {
        i1 = j + 1;
        i2 = k;
      } else {
        i1 = j;
        i2 = k;
        if (i3 == 0) {
          i2 = k + 1;
          i1 = j;
        } 
      } 
      arrayOfInt[i] = i3;
      i++;
      m = n;
      j = i1;
      k = i2;
    } 
    if (j == 1 || (j == 0 && k == 1))
      return this.methods[m].matchN(paramArrayOfObject, paramCallContext); 
    for (i = 0; i < this.count; i++) {
      k = arrayOfInt[i];
      if (k >= 0 && (k != 0 || j <= 0) && this.methods[i].matchN(paramArrayOfObject, paramCallContext) == 0)
        return 0; 
    } 
    paramCallContext.proc = null;
    return -1;
  }
  
  public int numArgs() {
    return this.minArgs | this.maxArgs << 12;
  }
  
  public final void setProperties(Object[] paramArrayOfObject) {
    int j = paramArrayOfObject.length;
    for (int i = 0; i < j; i++) {
      Object object = paramArrayOfObject[i];
      if (object instanceof Keyword) {
        object = object;
        setProperty((Keyword)object, paramArrayOfObject[++i]);
      } else {
        add((MethodProc)object);
      } 
    } 
  }
  
  public void setProperty(Keyword paramKeyword, Object paramObject) {
    String str = paramKeyword.getName();
    if (str == "name") {
      setName(paramObject.toString());
      return;
    } 
    if (str == "method") {
      add((MethodProc)paramObject);
      return;
    } 
    setProperty(paramKeyword.asSymbol(), paramObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/GenericProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */