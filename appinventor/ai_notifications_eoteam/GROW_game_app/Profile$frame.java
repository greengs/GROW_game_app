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

public class Profile$frame extends ModuleBody {
  Profile $main;
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 18:
        return Profile.lambda2();
      case 19:
        this.$main.$define();
        return Values.empty;
      case 20:
        return Profile.lambda3();
      case 21:
        return Profile.lambda4();
      case 22:
        return Profile.lambda5();
      case 23:
        return Profile.lambda6();
      case 24:
        return Profile.lambda7();
      case 25:
        return Profile.lambda8();
      case 26:
        return Profile.lambda9();
      case 27:
        return Profile.lambda10();
      case 28:
        return Profile.lambda11();
      case 29:
        return Profile.lambda12();
      case 30:
        return Profile.lambda13();
      case 31:
        return this.$main.Button1$Click();
      case 32:
        return Profile.lambda14();
      case 33:
        return Profile.lambda15();
      case 34:
        return Profile.lambda16();
      case 35:
        return Profile.lambda17();
      case 36:
        return Profile.lambda18();
      case 37:
        return Profile.lambda19();
      case 38:
        return Profile.lambda20();
      case 39:
        return Profile.lambda21();
      case 40:
        return Profile.lambda22();
      case 41:
        return Profile.lambda23();
      case 42:
        return Profile.lambda24();
      case 43:
        return Profile.lambda25();
      case 44:
        return Profile.lambda26();
      case 45:
        return Profile.lambda27();
      case 46:
        return Profile.lambda28();
      case 47:
        return Profile.lambda29();
      case 48:
        return Profile.lambda30();
      case 49:
        return Profile.lambda31();
      case 50:
        return Profile.lambda32();
      case 51:
        return Profile.lambda33();
      case 52:
        return Profile.lambda34();
      case 53:
        return Profile.lambda35();
      case 54:
        return Profile.lambda36();
      case 55:
        return Profile.lambda37();
      case 56:
        return Profile.lambda38();
      case 57:
        return Profile.lambda39();
      case 58:
        return Profile.lambda40();
      case 59:
        return Profile.lambda41();
      case 60:
        return Profile.lambda42();
      case 61:
        return Profile.lambda43();
      case 62:
        return Profile.lambda44();
      case 63:
        return Profile.lambda45();
      case 64:
        return Profile.lambda46();
      case 65:
        return Profile.lambda47();
      case 66:
        return this.$main.Button5$Click();
      case 67:
        return Profile.lambda48();
      case 68:
        return Profile.lambda49();
      case 69:
        return Profile.lambda50();
      case 70:
        return Profile.lambda51();
      case 71:
        return Profile.lambda52();
      case 72:
        return Profile.lambda53();
      case 73:
        return this.$main.Button2$Click();
      case 74:
        return Profile.lambda54();
      case 75:
        return Profile.lambda55();
      case 76:
        return Profile.lambda56();
      case 77:
        return Profile.lambda57();
      case 78:
        return Profile.lambda58();
      case 79:
        return Profile.lambda59();
      case 80:
        return Profile.lambda60();
      case 81:
        return Profile.lambda61();
      case 82:
        return Profile.lambda62();
      case 83:
        return Profile.lambda63();
      case 84:
        return Profile.lambda64();
      case 85:
        break;
    } 
    return Profile.lambda65();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    Profile profile;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return this.$main.getSimpleName(paramObject);
      case 2:
        profile = this.$main;
        try {
          Bundle bundle = (Bundle)paramObject;
          profile.onCreate(bundle);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "onCreate", 1, paramObject);
        } 
      case 3:
        this.$main.androidLogForm(paramObject);
        return Values.empty;
      case 5:
        profile = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return profile.lookupInFormEnvironment(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject);
        } 
      case 7:
        profile = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return profile.isBoundInFormEnvironment(symbol) ? Boolean.TRUE : Boolean.FALSE;
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
    Profile profile;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        profile = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          profile.addToFormEnvironment(symbol, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "add-to-form-environment", 1, paramObject1);
        } 
      case 5:
        profile = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          return profile.lookupInFormEnvironment(symbol, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject1);
        } 
      case 8:
        profile = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          profile.addToGlobalVarEnvironment(symbol, paramObject2);
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
        profile = this.$main;
        try {
          Component component = (Component)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              paramObject2 = paramObject3;
              try {
                paramObject3 = paramObject4;
                return profile.dispatchEvent(component, (String)paramObject1, (String)paramObject2, (Object[])paramObject3) ? Boolean.TRUE : Boolean.FALSE;
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
    Profile profile = this.$main;
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
            profile.dispatchGenericEvent(component, (String)paramObject1, bool, (Object[])paramObject2);
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
      case 85:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 84:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 83:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 82:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 81:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 80:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 79:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 78:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
      case 77:
        paramCallContext.proc = (Procedure)paramModuleMethod;
        paramCallContext.pc = 0;
        return 0;
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
        if (paramObject instanceof Profile) {
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
        if (paramObject instanceof Profile) {
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
        if (paramObject1 instanceof Profile) {
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
        if (paramObject1 instanceof Profile) {
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Profile$frame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */