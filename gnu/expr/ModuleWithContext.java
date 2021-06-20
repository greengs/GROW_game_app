package gnu.expr;

import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

public abstract class ModuleWithContext extends ModuleBody {
  public Object apply0(ModuleMethod paramModuleMethod) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.check0(callContext);
    return callContext.runUntilValue();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.check1(paramObject, callContext);
    return callContext.runUntilValue();
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.check2(paramObject1, paramObject2, callContext);
    return callContext.runUntilValue();
  }
  
  public Object apply3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.check3(paramObject1, paramObject2, paramObject3, callContext);
    return callContext.runUntilValue();
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.check4(paramObject1, paramObject2, paramObject3, paramObject4, callContext);
    return callContext.runUntilValue();
  }
  
  public Object applyN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) throws Throwable {
    CallContext callContext = CallContext.getInstance();
    paramModuleMethod.checkN(paramArrayOfObject, callContext);
    return callContext.runUntilValue();
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 0)
      return 0xFFF10000 | j; 
    paramCallContext.count = 0;
    paramCallContext.where = 0;
    if (i < 0)
      return matchN(paramModuleMethod, ProcedureN.noArgs, paramCallContext); 
    paramCallContext.next = 0;
    paramCallContext.proc = (Procedure)this;
    paramCallContext.pc = paramModuleMethod.selector;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 1)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 1)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject;
      paramCallContext.count = 1;
      paramCallContext.where = 1;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)this;
      paramCallContext.pc = paramModuleMethod.selector;
      return 0;
    } 
    paramCallContext.where = 0;
    return matchN(paramModuleMethod, new Object[] { paramObject }, paramCallContext);
  }
  
  public int match2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 2)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 2)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.count = 2;
      paramCallContext.where = 33;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)this;
      paramCallContext.pc = paramModuleMethod.selector;
      return 0;
    } 
    paramCallContext.where = 0;
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2 }, paramCallContext);
  }
  
  public int match3(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 3)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 3)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.count = 3;
      paramCallContext.where = 801;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)this;
      paramCallContext.pc = paramModuleMethod.selector;
      return 0;
    } 
    paramCallContext.where = 0;
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 }, paramCallContext);
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (j > 4)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      i >>= 12;
      if (i < 4)
        return 0xFFF20000 | i; 
      paramCallContext.value1 = paramObject1;
      paramCallContext.value2 = paramObject2;
      paramCallContext.value3 = paramObject3;
      paramCallContext.value4 = paramObject4;
      paramCallContext.count = 4;
      paramCallContext.where = 17185;
      paramCallContext.next = 0;
      paramCallContext.proc = (Procedure)this;
      paramCallContext.pc = paramModuleMethod.selector;
      return 0;
    } 
    paramCallContext.where = 0;
    return matchN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 }, paramCallContext);
  }
  
  public int matchN(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject, CallContext paramCallContext) {
    int i = paramModuleMethod.numArgs();
    int j = i & 0xFFF;
    if (paramArrayOfObject.length < j)
      return 0xFFF10000 | j; 
    if (i >= 0) {
      switch (paramArrayOfObject.length) {
        default:
          i >>= 12;
          if (paramArrayOfObject.length > i)
            return 0xFFF20000 | i; 
          paramCallContext.values = paramArrayOfObject;
          paramCallContext.count = paramArrayOfObject.length;
          paramCallContext.where = 0;
          paramCallContext.next = 0;
          paramCallContext.proc = (Procedure)this;
          paramCallContext.pc = paramModuleMethod.selector;
          return 0;
        case 0:
          return match0(paramModuleMethod, paramCallContext);
        case 1:
          return match1(paramModuleMethod, paramArrayOfObject[0], paramCallContext);
        case 2:
          return match2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramCallContext);
        case 3:
          return match3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramCallContext);
        case 4:
          break;
      } 
      return match4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3], paramCallContext);
    } 
    paramCallContext.values = paramArrayOfObject;
    paramCallContext.count = paramArrayOfObject.length;
    paramCallContext.where = 0;
    paramCallContext.next = 0;
    paramCallContext.proc = (Procedure)this;
    paramCallContext.pc = paramModuleMethod.selector;
    return 0;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleWithContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */