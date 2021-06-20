package appinventor.ai_notifications_eoteam.GROW_game_app;

import android.os.Bundle;
import com.google.appinventor.components.runtime.Component;
import gnu.expr.ModuleBody;
import gnu.expr.ModuleMethod;
import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.Symbol;
import gnu.mapping.Values;
import gnu.mapping.WrongType;

public class Missions$frame extends ModuleBody {
  Missions $main;
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 18:
        return Missions.lambda2();
      case 19:
        this.$main.$define();
        return Values.empty;
      case 20:
        return Missions.lambda3();
      case 21:
        return Missions.lambda4();
      case 22:
        return Missions.lambda5();
      case 23:
        return Missions.lambda6();
      case 24:
        return Missions.lambda7();
      case 25:
        return Missions.lambda8();
      case 26:
        return Missions.lambda9();
      case 27:
        return Missions.lambda10();
      case 28:
        break;
    } 
    return Missions.lambda11();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    Missions missions;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return this.$main.getSimpleName(paramObject);
      case 2:
        missions = this.$main;
        try {
          Bundle bundle = (Bundle)paramObject;
          missions.onCreate(bundle);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "onCreate", 1, paramObject);
        } 
      case 3:
        this.$main.androidLogForm(paramObject);
        return Values.empty;
      case 5:
        missions = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return missions.lookupInFormEnvironment(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject);
        } 
      case 7:
        missions = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return missions.isBoundInFormEnvironment(symbol) ? Boolean.TRUE : Boolean.FALSE;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "is-bound-in-form-environment", 1, paramObject);
        } 
      case 12:
        this.$main.addToFormDoAfterCreation(paramObject);
        return Values.empty;
      case 13:
        this.$main.sendError(paramObject);
        return Values.empty;
      case 14:
        break;
    } 
    this.$main.processException(paramObject);
    return Values.empty;
  }
  
  public Object apply2(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2) {
    Missions missions;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        missions = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          missions.addToFormEnvironment(symbol, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "add-to-form-environment", 1, paramObject1);
        } 
      case 5:
        missions = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          return missions.lookupInFormEnvironment(symbol, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject1);
        } 
      case 8:
        missions = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          missions.addToGlobalVarEnvironment(symbol, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "add-to-global-var-environment", 1, paramObject1);
        } 
      case 9:
        this.$main.addToEvents(paramObject1, paramObject2);
        return Values.empty;
      case 11:
        this.$main.addToGlobalVars(paramObject1, paramObject2);
        return Values.empty;
      case 17:
        break;
    } 
    return this.$main.lookupHandler(paramObject1, paramObject2);
  }
  
  public Object apply4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4) {
    boolean bool = true;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4);
      case 10:
        this.$main.addToComponents(paramObject1, paramObject2, paramObject3, paramObject4);
        return Values.empty;
      case 15:
        missions = this.$main;
        try {
          Component component = (Component)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              paramObject2 = paramObject3;
              try {
                paramObject3 = paramObject4;
                return missions.dispatchEvent(component, (String)paramObject1, (String)paramObject2, (Object[])paramObject3) ? Boolean.TRUE : Boolean.FALSE;
              } catch (ClassCastException classCastException) {
                throw new WrongType(classCastException, "dispatchEvent", 4, paramObject4);
              } 
            } catch (ClassCastException classCastException) {
              throw new WrongType(classCastException, "dispatchEvent", 3, paramObject3);
            } 
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "dispatchEvent", 2, paramObject2);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "dispatchEvent", 1, paramObject1);
        } 
      case 16:
        break;
    } 
    Missions missions = this.$main;
    try {
      Component component = (Component)paramObject1;
      try {
        paramObject1 = paramObject2;
        try {
          paramObject2 = Boolean.FALSE;
          if (paramObject3 == paramObject2)
            bool = false; 
          try {
            paramObject2 = paramObject4;
            missions.dispatchGenericEvent(component, (String)paramObject1, bool, (Object[])paramObject2);
            return Values.empty;
          } catch (ClassCastException classCastException) {
            throw new WrongType(classCastException, "dispatchGenericEvent", 4, paramObject4);
          } 
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "dispatchGenericEvent", 3, paramObject3);
        } 
      } catch (ClassCastException classCastException) {
        throw new WrongType(classCastException, "dispatchGenericEvent", 2, paramObject2);
      } 
    } catch (ClassCastException classCastException) {
      throw new WrongType(classCastException, "dispatchGenericEvent", 1, paramObject1);
    } 
  }
  
  public int match0(ModuleMethod paramModuleMethod, CallContext paramCallContext) {
    switch (paramModuleMethod.selector) {
      default:
        return super.match0(paramModuleMethod, paramCallContext);
      case 28:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 27:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 26:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 25:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 24:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 23:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 22:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 21:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 20:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 19:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 18:
        break;
    } 
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 0;
    return 0;
  }
  
  public int match1(ModuleMethod paramModuleMethod, Object paramObject, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match1(paramModuleMethod, paramObject, paramCallContext);
      case 14:
        if (paramObject instanceof Missions) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 13:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 12:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 7:
        if (paramObject instanceof Symbol) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 5:
        if (paramObject instanceof Symbol) {
          paramCallContext.value1 = paramObject;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 1;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_4;
      case 3:
        paramCallContext.value1 = paramObject;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 1;
        return 0;
      case 2:
        if (paramObject instanceof Missions) {
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
    switch (paramModuleMethod.selector) {
      default:
        return super.match2(paramModuleMethod, paramObject1, paramObject2, paramCallContext);
      case 17:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 11:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 9:
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 8:
        if (!(paramObject1 instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 5:
        if (!(paramObject1 instanceof Symbol))
          return -786431; 
        paramCallContext.value1 = paramObject1;
        paramCallContext.value2 = paramObject2;
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 2;
        return 0;
      case 4:
        break;
    } 
    if (!(paramObject1 instanceof Symbol))
      return -786431; 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 2;
    return 0;
  }
  
  public int match4(ModuleMethod paramModuleMethod, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, CallContext paramCallContext) {
    null = -786431;
    switch (paramModuleMethod.selector) {
      default:
        return super.match4(paramModuleMethod, paramObject1, paramObject2, paramObject3, paramObject4, paramCallContext);
      case 16:
        if (paramObject1 instanceof Missions) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Component))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          if (!(paramObject3 instanceof String))
            return -786429; 
          paramCallContext.value3 = paramObject3;
          paramCallContext.value4 = paramObject4;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 4;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_7;
      case 15:
        if (paramObject1 instanceof Missions) {
          paramCallContext.value1 = paramObject1;
          if (!(paramObject2 instanceof Component))
            return -786430; 
          paramCallContext.value2 = paramObject2;
          if (!(paramObject3 instanceof String))
            return -786429; 
          paramCallContext.value3 = paramObject3;
          if (!(paramObject4 instanceof String))
            return -786428; 
          paramCallContext.value4 = paramObject4;
          paramCallContext.proc = (Procedure)paramModuleMethod;
          paramCallContext.pc = 4;
          return 0;
        } 
        return SYNTHETIC_LOCAL_VARIABLE_7;
      case 10:
        break;
    } 
    paramCallContext.value1 = paramObject1;
    paramCallContext.value2 = paramObject2;
    paramCallContext.value3 = paramObject3;
    paramCallContext.value4 = paramObject4;
    paramCallContext.proc = (Procedure)paramModuleMethod;
    paramCallContext.pc = 4;
    return 0;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Missions$frame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */