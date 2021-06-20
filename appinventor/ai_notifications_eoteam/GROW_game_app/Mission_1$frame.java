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

public class Mission_1$frame extends ModuleBody {
  Mission_1 $main;
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 18:
        return Mission_1.lambda2();
      case 19:
        this.$main.$define();
        return Values.empty;
      case 20:
        return Mission_1.lambda3();
      case 21:
        return Mission_1.lambda4();
      case 22:
        return Mission_1.lambda5();
      case 23:
        return Mission_1.lambda6();
      case 24:
        return Mission_1.lambda7();
      case 25:
        return Mission_1.lambda8();
      case 26:
        return Mission_1.lambda9();
      case 27:
        return Mission_1.lambda10();
      case 28:
        return Mission_1.lambda11();
      case 29:
        return Mission_1.lambda12();
      case 30:
        return Mission_1.lambda13();
      case 31:
        return Mission_1.lambda14();
      case 32:
        return Mission_1.lambda15();
      case 33:
        return this.$main.Button4$Click();
      case 34:
        return Mission_1.lambda16();
      case 35:
        return Mission_1.lambda17();
      case 36:
        return Mission_1.lambda18();
      case 37:
        return Mission_1.lambda19();
      case 38:
        return this.$main.Button1$Click();
      case 39:
        return Mission_1.lambda20();
      case 40:
        return Mission_1.lambda21();
      case 41:
        return Mission_1.lambda22();
      case 42:
        return Mission_1.lambda23();
      case 43:
        return this.$main.Button2$Click();
      case 44:
        return Mission_1.lambda24();
      case 45:
        return Mission_1.lambda25();
      case 46:
        return Mission_1.lambda26();
      case 47:
        return Mission_1.lambda27();
      case 48:
        return this.$main.Button3$Click();
      case 49:
        return Mission_1.lambda28();
      case 50:
        return Mission_1.lambda29();
      case 51:
        return Mission_1.lambda30();
      case 52:
        return Mission_1.lambda31();
      case 53:
        return Mission_1.lambda32();
      case 54:
        return Mission_1.lambda33();
      case 55:
        return Mission_1.lambda34();
      case 56:
        return Mission_1.lambda35();
      case 57:
        return Mission_1.lambda36();
      case 58:
        return Mission_1.lambda37();
      case 59:
        return Mission_1.lambda38();
      case 60:
        return Mission_1.lambda39();
      case 61:
        return Mission_1.lambda40();
      case 62:
        return Mission_1.lambda41();
      case 63:
        return Mission_1.lambda42();
      case 64:
        return Mission_1.lambda43();
      case 65:
        return Mission_1.lambda44();
      case 66:
        return Mission_1.lambda45();
      case 67:
        return Mission_1.lambda46();
      case 68:
        return Mission_1.lambda47();
      case 69:
        return Mission_1.lambda48();
      case 70:
        return Mission_1.lambda49();
      case 71:
        return Mission_1.lambda50();
      case 72:
        return Mission_1.lambda51();
      case 73:
        return Mission_1.lambda52();
      case 74:
        return Mission_1.lambda53();
      case 75:
        return Mission_1.lambda54();
      case 76:
        break;
    } 
    return Mission_1.lambda55();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    Mission_1 mission_1;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return this.$main.getSimpleName(paramObject);
      case 2:
        mission_1 = this.$main;
        try {
          Bundle bundle = (Bundle)paramObject;
          mission_1.onCreate(bundle);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "onCreate", 1, paramObject);
        } 
      case 3:
        this.$main.androidLogForm(paramObject);
        return Values.empty;
      case 5:
        mission_1 = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return mission_1.lookupInFormEnvironment(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject);
        } 
      case 7:
        mission_1 = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return mission_1.isBoundInFormEnvironment(symbol) ? Boolean.TRUE : Boolean.FALSE;
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
    Mission_1 mission_1;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        mission_1 = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          mission_1.addToFormEnvironment(symbol, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "add-to-form-environment", 1, paramObject1);
        } 
      case 5:
        mission_1 = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          return mission_1.lookupInFormEnvironment(symbol, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject1);
        } 
      case 8:
        mission_1 = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          mission_1.addToGlobalVarEnvironment(symbol, paramObject2);
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
        mission_1 = this.$main;
        try {
          Component component = (Component)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              paramObject2 = paramObject3;
              try {
                paramObject3 = paramObject4;
                return mission_1.dispatchEvent(component, (String)paramObject1, (String)paramObject2, (Object[])paramObject3) ? Boolean.TRUE : Boolean.FALSE;
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
    Mission_1 mission_1 = this.$main;
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
            mission_1.dispatchGenericEvent(component, (String)paramObject1, bool, (Object[])paramObject2);
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
      case 76:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 75:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 74:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 73:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 72:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 71:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 70:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 69:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 68:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 67:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 66:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 65:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 64:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 63:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 62:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 61:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 60:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 59:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 58:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 57:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 56:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 55:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 54:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 53:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 52:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 51:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 50:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 49:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 48:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 47:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 46:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 45:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 44:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 43:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 42:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 41:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 40:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 39:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 38:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 37:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 36:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 35:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 34:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 33:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 32:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 31:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 30:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 29:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
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
        if (paramObject instanceof Mission_1) {
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
        if (paramObject instanceof Mission_1) {
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
        if (paramObject1 instanceof Mission_1) {
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
        if (paramObject1 instanceof Mission_1) {
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Mission_1$frame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */