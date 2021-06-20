package kawa.lib;

import gnu.expr.GenericProc;
import gnu.expr.Keyword;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleInfo;
import gnu.expr.ModuleMethod;
import gnu.expr.Special;
import gnu.lists.Consumer;
import gnu.lists.FVector;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.SimpleSymbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;
import java.util.List;

public class vectors extends ModuleBody {
  public static final vectors $instance;
  
  static final Keyword Lit0;
  
  static final SimpleSymbol Lit1;
  
  static final SimpleSymbol Lit2;
  
  static final SimpleSymbol Lit3;
  
  static final SimpleSymbol Lit4;
  
  static final SimpleSymbol Lit5;
  
  static final SimpleSymbol Lit6;
  
  static final SimpleSymbol Lit7;
  
  static final SimpleSymbol Lit8 = (SimpleSymbol)(new SimpleSymbol("vector-fill!")).readResolve();
  
  public static final ModuleMethod list$Mn$Grvector;
  
  public static final ModuleMethod make$Mnvector;
  
  public static final ModuleMethod vector$Mn$Grlist;
  
  public static final ModuleMethod vector$Mnfill$Ex;
  
  public static final ModuleMethod vector$Mnlength;
  
  public static final GenericProc vector$Mnref;
  
  static final ModuleMethod vector$Mnref$Fn1;
  
  public static final ModuleMethod vector$Mnset$Ex;
  
  public static final ModuleMethod vector$Qu;
  
  static {
    Lit7 = (SimpleSymbol)(new SimpleSymbol("list->vector")).readResolve();
    Lit6 = (SimpleSymbol)(new SimpleSymbol("vector->list")).readResolve();
    Lit5 = (SimpleSymbol)(new SimpleSymbol("vector-ref")).readResolve();
    Lit4 = (SimpleSymbol)(new SimpleSymbol("vector-set!")).readResolve();
    Lit3 = (SimpleSymbol)(new SimpleSymbol("vector-length")).readResolve();
    Lit2 = (SimpleSymbol)(new SimpleSymbol("make-vector")).readResolve();
    Lit1 = (SimpleSymbol)(new SimpleSymbol("vector?")).readResolve();
    Lit0 = Keyword.make("setter");
    $instance = new vectors();
    vectors vectors1 = $instance;
    vector$Qu = new ModuleMethod(vectors1, 1, Lit1, 4097);
    make$Mnvector = new ModuleMethod(vectors1, 2, Lit2, 8193);
    vector$Mnlength = new ModuleMethod(vectors1, 4, Lit3, 4097);
    vector$Mnset$Ex = new ModuleMethod(vectors1, 5, Lit4, 12291);
    vector$Mnref$Fn1 = new ModuleMethod(vectors1, 6, Lit5, 8194);
    vector$Mn$Grlist = new ModuleMethod(vectors1, 7, Lit6, 4097);
    list$Mn$Grvector = new ModuleMethod(vectors1, 8, Lit7, 4097);
    vector$Mnfill$Ex = new ModuleMethod(vectors1, 9, Lit8, 8194);
    $instance.run();
  }
  
  public vectors() {
    ModuleInfo.register(this);
  }
  
  public static boolean isVector(Object paramObject) {
    return paramObject instanceof FVector;
  }
  
  public static FVector list$To$Vector(LList paramLList) {
    return new FVector((List)paramLList);
  }
  
  public static FVector makeVector(int paramInt) {
    return makeVector(paramInt, Special.undefined);
  }
  
  public static FVector makeVector(int paramInt, Object paramObject) {
    return new FVector(paramInt, paramObject);
  }
  
  public static LList vector$To$List(FVector paramFVector) {
    LList lList = LList.Empty;
    int i = vectorLength(paramFVector);
    while (true) {
      if (--i < 0)
        return lList; 
      Pair pair = lists.cons(vector$Mnref.apply2(paramFVector, Integer.valueOf(i)), lList);
    } 
  }
  
  public static void vectorFill$Ex(FVector paramFVector, Object paramObject) {
    paramFVector.setAll(paramObject);
  }
  
  public static int vectorLength(FVector paramFVector) {
    return paramFVector.size();
  }
  
  public static Object vectorRef(FVector paramFVector, int paramInt) {
    return paramFVector.get(paramInt);
  }
  
  public static void vectorSet$Ex(FVector paramFVector, int paramInt, Object paramObject) {
    paramFVector.set(paramInt, paramObject);
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return isVector(paramObject) ? Boolean.TRUE : Boolean.FALSE;
      case 2:
        try {
          int i = ((Number)paramObject).intValue();
          return makeVector(i);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-vector", 1, paramObject);
        } 
      case 4:
        try {
          FVector fVector = (FVector)paramObject;
          return Integer.valueOf(vectorLength(fVector));
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "vector-length", 1, paramObject);
        } 
      case 7:
        try {
          FVector fVector = (FVector)paramObject;
          return vector$To$List(fVector);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "vector->list", 1, paramObject);
        } 
      case 8:
        break;
    } 
    try {
      LList lList = (LList)paramObject;
      return list$To$Vector(lList);
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "list->vector", 1, paramObject);
    } 
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 2:
        try {
          int i = ((Number)paramObject1).intValue();
          return makeVector(i, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "make-vector", 1, paramObject1);
        } 
      case 6:
        try {
          FVector fVector = (FVector)paramObject1;
          try {
            int i = ((Number)paramObject2).intValue();
            return vectorRef(fVector, i);
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "vector-ref", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "vector-ref", 1, paramObject1);
        } 
      case 9:
        break;
    } 
    try {
      FVector fVector = (FVector)paramObject1;
      vectorFill$Ex(fVector, paramObject2);
      return Values.empty;
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "vector-fill!", 1, paramObject1);
    } 
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) {
    if (paramModuleMethod.selector == 5)
      try {
        FVector fVector = (FVector)paramObject1;
        try {
          int i = ((Number)paramObject2).intValue();
          vectorSet$Ex(fVector, i, paramObject3);
          return Values.empty;
        } catch (ClassCastException null) {
          throw new WrongType(classCastException, "vector-set!", 2, paramObject2);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "vector-set!", 1, paramObject1);
      }  
    return super.apply3((ModuleMethod)classCastException, paramObject1, paramObject2, paramObject3);
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 8:
        if (paramObject instanceof LList) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 7:
        if (paramObject instanceof FVector) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 4:
        if (paramObject instanceof FVector) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return -786431;
      case 2:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 1:
        break;
    } 
    paramCallContext.value1 = paramObject;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 1;
    return 0;
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 9:
        if (paramObject1 instanceof FVector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 6:
        if (paramObject1 instanceof FVector) {
          paramCallContext.value1 = paramObject1;
          paramCallContext.value2 = paramObject2;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 2;
          return 0;
        } 
        return -786431;
      case 2:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    if (paramModuleMethod.selector == 5) {
      if (paramObject1 instanceof FVector) {
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.value3 = paramObject3;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 3;
        return 0;
      } 
      return -786431;
    } 
    return super.match3(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public final void run(CallContext paramCallContext) {
    Consumer consumer = paramCallContext.consumer;
    vector$Mnref = new GenericProc("vector-ref");
    GenericProc genericProc = vector$Mnref;
    Keyword keyword = Lit0;
    ModuleMethod moduleMethod1 = vector$Mnset$Ex;
    ModuleMethod moduleMethod2 = vector$Mnref$Fn1;
    genericProc.setProperties(new Object[] { keyword, moduleMethod1, vector$Mnref$Fn1 });
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lib/vectors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */