package kawa.lib;

import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.kawa.functions.Arrays;
import gnu.lists.Array;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.SimpleVector;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.WrongType;

public class arrays extends ModuleBody {
  public static final Class $Lsarray$Gr;
  
  public static final arrays $instance;
  
  static final SimpleSymbol Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7 = (SimpleSymbol)(new SimpleSymbol("share-array")).readResolve();
  
  public static final ModuleMethod array;
  
  public static final ModuleMethod array$Mnend;
  
  public static final ModuleMethod array$Mnrank;
  
  public static final ModuleMethod array$Mnstart;
  
  public static final ModuleMethod array$Qu;
  
  public static final ModuleMethod make$Mnarray;
  
  public static final ModuleMethod shape;
  
  public static final ModuleMethod share$Mnarray;
  
  static {
    Lit6 = (SimpleSymbol)(new SimpleSymbol("array-end")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("array-start")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("array-rank")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("array")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-array")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("shape")).readResolve();
    Lit0 = (SimpleSymbol)(new SimpleSymbol("array?")).readResolve();
    $Lsarray$Gr = Array.class;
    $instance = new arrays();
    arrays arrays1 = $instance;
    array$Qu = new ModuleMethod(arrays1, 1, Lit0, 4097);
    shape = new ModuleMethod(arrays1, 2, Lit1, -4096);
    make$Mnarray = new ModuleMethod(arrays1, 3, Lit2, 8193);
    array = new ModuleMethod(arrays1, 5, Lit3, -4095);
    array$Mnrank = new ModuleMethod(arrays1, 6, Lit4, 4097);
    array$Mnstart = new ModuleMethod(arrays1, 7, Lit5, 8194);
    array$Mnend = new ModuleMethod(arrays1, 8, Lit6, 8194);
    share$Mnarray = new ModuleMethod(arrays1, 9, Lit7, 12291);
    $instance.run();
  }
  
  public arrays() {
    ModuleInfo.register(this);
  }
  
  public static Array array(Array paramArray, Object... paramVarArgs) {
    return Arrays.makeSimple(paramArray, (SimpleVector)new FVector(paramVarArgs));
  }
  
  public static int arrayEnd(Array paramArray, int paramInt) {
    return paramArray.getLowBound(paramInt) + paramArray.getSize(paramInt);
  }
  
  public static int arrayRank(Array paramArray) {
    return paramArray.rank();
  }
  
  public static int arrayStart(Array paramArray, int paramInt) {
    return paramArray.getLowBound(paramInt);
  }
  
  public static boolean isArray(Object paramObject) {
    return paramObject instanceof Array;
  }
  
  public static Array makeArray(Array paramArray) {
    return makeArray(paramArray, null);
  }
  
  public static Array makeArray(Array paramArray, Object paramObject) {
    return Arrays.make(paramArray, paramObject);
  }
  
  public static Array shape(Object... paramVarArgs) {
    return Arrays.shape(paramVarArgs);
  }
  
  public static Array shareArray(Array paramArray1, Array paramArray2, Procedure paramProcedure) {
    return Arrays.shareArray(paramArray1, paramArray2, paramProcedure);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isArray(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 3:
        try {
          Array array = (Array)paramObject;
          return makeArray(array);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-array", 1, paramObject);
        } 
      case 6:
        break;
    } 
    try {
      Array array = (Array)paramObject;
      return Integer.valueOf(arrayRank(array));
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "array-rank", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 3:
        try {
          Array array = (Array)paramObject1;
          return makeArray(array, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-array", 1, paramObject1);
        } 
      case 7:
        try {
          Array array = (Array)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return Integer.valueOf(arrayStart(array, i));
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "array-start", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "array-start", 1, paramObject1);
        } 
      case 8:
        break;
    } 
    try {
      Array array = (Array)paramObject1;
      try {
        int i = ((Number)paramObject2).intValue();
        return Integer.valueOf(arrayEnd(array, i));
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "array-end", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "array-end", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (paramModuleMethod.selector == 9)
      try {
        Array array = (Array)paramObject1;
        try {
          paramObject1 = paramObject2;
          try {
            paramObject2 = paramObject3;
            return shareArray(array, (Array)paramObject1, (Procedure)paramObject2);
          } catch (ClassCastException null) {
            throw new WrongType(classCastException, "share-array", 3, paramObject3);
          } 
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "share-array", 2, paramObject2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "share-array", 1, paramObject1);
      }  
    return super.apply3((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3);
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.applyN(paramModuleMethod, paramArrayOfObject);
      case 2:
        return shape(paramArrayOfObject);
      case 5:
        break;
    } 
    Object object = paramArrayOfObject[0];
    try {
      Array array = (Array)object;
      int i = paramArrayOfObject.length - 1;
      object = new Object[i];
      while (true) {
        if (--i < 0)
          return array(array, (Object[])object); 
        object[i] = paramArrayOfObject[i + 1];
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "array", 1, object);
    } 
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 6:
        if (paramObject instanceof Array) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 3:
        if (paramObject instanceof Array) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 8:
        if (paramObject1 instanceof Array) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 7:
        if (paramObject1 instanceof Array) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_5;
      case 3:
        break;
    } 
    if (paramObject1 instanceof Array) {
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 2;
      return 0;
    } 
    return SYNTHETIC_LOCAL_VARIABLE_5;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 9) {
      if (!(paramObject1 instanceof Array))
        return -786431; 
      paramCallContext.value1 = paramObject1;
      if (!(paramObject2 instanceof Array))
        return -786430; 
      paramCallContext.value2 = paramObject2;
      if (!(paramObject3 instanceof Procedure))
        return -786429; 
      paramCallContext.value3 = paramObject3;
      paramCallContext.proc = (Procedure)paramModuleMethod;
      paramCallContext.pc = 3;
      return 0;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.matchN(paramModuleMethod, paramArrayOfObject, paramCallContext);
      case 5:
        paramCallContext.values = paramArrayOfObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 5;
        return 0;
      case 2:
        break;
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 5;
    return 0;
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/arrays.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */