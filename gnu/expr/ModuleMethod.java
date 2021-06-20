package gnu.expr;

import gnu.bytecode.Type;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Values;
import gnu.mapping.WrongArguments;
import java.lang.reflect.Method;

public class ModuleMethod extends MethodProc {
  public ModuleBody module;
  
  protected int numArgs;
  
  public int selector;
  
  public ModuleMethod(ModuleBody paramModuleBody, int paramInt1, Object paramObject, int paramInt2) {
    init(paramModuleBody, paramInt1, paramObject, paramInt2);
  }
  
  public ModuleMethod(ModuleBody paramModuleBody, int paramInt1, Object paramObject1, int paramInt2, Object paramObject2) {
    init(paramModuleBody, paramInt1, paramObject1, paramInt2);
    this.argTypes = paramObject2;
  }
  
  public static Object apply0Default(ModuleMethod paramModuleMethod) throws Throwable {
    return paramModuleMethod.module.applyN(paramModuleMethod, Values.noArgs);
  }
  
  public static Object apply1Default(ModuleMethod paramModuleMethod, Object paramObject) throws Throwable {
    return paramModuleMethod.module.applyN(paramModuleMethod, new Object[] { paramObject });
  }
  
  public static Object apply2Default(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) throws Throwable {
    return paramModuleMethod.module.applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2 });
  }
  
  public static Object apply3Default(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    return paramModuleMethod.module.applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3 });
  }
  
  public static Object apply4Default(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    return paramModuleMethod.module.applyN(paramModuleMethod, new Object[] { paramObject1, paramObject2, paramObject3, paramObject4 });
  }
  
  public static void applyError() {
    throw new Error("internal error - bad selector");
  }
  
  public static Object applyNDefault(ModuleMethod paramModuleMethod, Object[] paramArrayOfObject) throws Throwable {
    int i = paramArrayOfObject.length;
    int j = paramModuleMethod.numArgs();
    ModuleBody moduleBody = paramModuleMethod.module;
    if (i >= (j & 0xFFF) && (j < 0 || i <= j >> 12)) {
      switch (i) {
        default:
          throw new WrongArguments(paramModuleMethod, i);
        case 0:
          return moduleBody.apply0(paramModuleMethod);
        case 1:
          return moduleBody.apply1(paramModuleMethod, paramArrayOfObject[0]);
        case 2:
          return moduleBody.apply2(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1]);
        case 3:
          return moduleBody.apply3(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2]);
        case 4:
          break;
      } 
      return moduleBody.apply4(paramModuleMethod, paramArrayOfObject[0], paramArrayOfObject[1], paramArrayOfObject[2], paramArrayOfObject[3]);
    } 
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    switch (paramCallContext.pc) {
      default:
        throw new Error("internal error - apply " + this);
      case 0:
        object = apply0();
        paramCallContext.writeValue(object);
        return;
      case 1:
        object = apply1(paramCallContext.value1);
        paramCallContext.writeValue(object);
        return;
      case 2:
        object = apply2(paramCallContext.value1, paramCallContext.value2);
        paramCallContext.writeValue(object);
        return;
      case 3:
        object = apply3(paramCallContext.value1, paramCallContext.value2, paramCallContext.value3);
        paramCallContext.writeValue(object);
        return;
      case 4:
        object = apply4(paramCallContext.value1, paramCallContext.value2, paramCallContext.value3, paramCallContext.value4);
        paramCallContext.writeValue(object);
        return;
      case 5:
        break;
    } 
    Object object = applyN(paramCallContext.values);
    paramCallContext.writeValue(object);
  }
  
  public Object apply0() throws Throwable {
    return this.module.apply0(this);
  }
  
  public Object apply1(Object paramObject) throws Throwable {
    return this.module.apply1(this, paramObject);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return this.module.apply2(this, paramObject1, paramObject2);
  }
  
  public Object apply3(Object paramObject1, Object paramObject2, Object paramObject3) throws Throwable {
    return this.module.apply3(this, paramObject1, paramObject2, paramObject3);
  }
  
  public Object apply4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) throws Throwable {
    return this.module.apply4(this, paramObject1, paramObject2, paramObject3, paramObject4);
  }
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    return this.module.applyN(this, paramArrayOfObject);
  }
  
  public ModuleMethod init(ModuleBody paramModuleBody, int paramInt1, Object paramObject, int paramInt2) {
    this.module = paramModuleBody;
    this.selector = paramInt1;
    this.numArgs = paramInt2;
    if (paramObject != null)
      setSymbol(paramObject); 
    return this;
  }
  
  public int match0(CallContext paramCallContext) {
    paramCallContext.count = 0;
    paramCallContext.where = 0;
    return this.module.match0(this, paramCallContext);
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    paramCallContext.count = 1;
    paramCallContext.where = 1;
    return this.module.match1(this, paramObject, paramCallContext);
  }
  
  public int match2(Object paramObject1, Object paramObject2, CallContext paramCallContext) {
    paramCallContext.count = 2;
    paramCallContext.where = 33;
    return this.module.match2(this, paramObject1, paramObject2, paramCallContext);
  }
  
  public int match3(Object paramObject1, Object paramObject2, Object paramObject3, CallContext paramCallContext) {
    paramCallContext.count = 3;
    paramCallContext.where = 801;
    return this.module.match3(this, paramObject1, paramObject2, paramObject3, paramCallContext);
  }
  
  public int match4(Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    paramCallContext.count = 4;
    paramCallContext.where = 17185;
    return this.module.match4(this, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
  }
  
  public int matchN(Object[] paramArrayOfObject, CallContext paramCallContext) {
    paramCallContext.count = paramArrayOfObject.length;
    paramCallContext.where = 0;
    return this.module.matchN(this, paramArrayOfObject, paramCallContext);
  }
  
  public int numArgs() {
    return this.numArgs;
  }
  
  protected void resolveParameterTypes() {
    String str1 = null;
    String str2 = getName();
    if (str2 != null)
      try {
        Method[] arrayOfMethod = this.module.getClass().getDeclaredMethods();
        String str = Compilation.mangleNameIfNeeded(str2);
        int i = arrayOfMethod.length;
        while (true) {
          int j = i - 1;
          str2 = str1;
          if (j >= 0) {
            i = j;
            if (arrayOfMethod[j].getName().equals(str)) {
              if (str1 != null) {
                str2 = null;
              } else {
                Method method = arrayOfMethod[j];
                i = j;
                continue;
              } 
            } else {
              continue;
            } 
          } 
          if (str2 != null) {
            Language language = Language.getDefaultLanguage();
            if (language != null) {
              Class[] arrayOfClass = str2.getParameterTypes();
              i = arrayOfClass.length;
              Type[] arrayOfType = new Type[i];
              while (true) {
                if (--i >= 0) {
                  arrayOfType[i] = language.getTypeFor(arrayOfClass[i]);
                  continue;
                } 
                this.argTypes = arrayOfType;
                break;
              } 
            } 
          } 
          if (this.argTypes == null)
            super.resolveParameterTypes(); 
          return;
        } 
      } catch (Throwable throwable) {} 
    if (this.argTypes == null)
      super.resolveParameterTypes(); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ModuleMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */