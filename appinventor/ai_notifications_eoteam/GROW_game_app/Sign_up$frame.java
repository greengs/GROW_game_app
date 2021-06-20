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

public class Sign_up$frame extends ModuleBody {
  Sign_up $main;
  
  public Object apply0(ModuleMethod paramModuleMethod) {
    switch (paramModuleMethod.selector) {
      default:
        return super.apply0(paramModuleMethod);
      case 18:
        return Sign_up.lambda2();
      case 19:
        this.$main.$define();
        return Values.empty;
      case 20:
        return Sign_up.lambda3();
      case 21:
        return this.$main.Sign_up$Initialize();
      case 22:
        return Sign_up.lambda4();
      case 23:
        return Sign_up.lambda5();
      case 24:
        return Sign_up.lambda6();
      case 25:
        return Sign_up.lambda7();
      case 26:
        return Sign_up.lambda8();
      case 27:
        return Sign_up.lambda9();
      case 28:
        return Sign_up.lambda10();
      case 29:
        return Sign_up.lambda11();
      case 30:
        return Sign_up.lambda12();
      case 31:
        return Sign_up.lambda13();
      case 32:
        return Sign_up.lambda14();
      case 33:
        return Sign_up.lambda15();
      case 34:
        return Sign_up.lambda16();
      case 35:
        return Sign_up.lambda17();
      case 36:
        return Sign_up.lambda18();
      case 37:
        return Sign_up.lambda19();
      case 38:
        return Sign_up.lambda20();
      case 39:
        return Sign_up.lambda21();
      case 40:
        return Sign_up.lambda22();
      case 41:
        return Sign_up.lambda23();
      case 42:
        return Sign_up.lambda24();
      case 43:
        return Sign_up.lambda25();
      case 44:
        return Sign_up.lambda26();
      case 45:
        return Sign_up.lambda27();
      case 46:
        return Sign_up.lambda28();
      case 47:
        return Sign_up.lambda29();
      case 48:
        return Sign_up.lambda30();
      case 49:
        return Sign_up.lambda31();
      case 50:
        return Sign_up.lambda32();
      case 51:
        return Sign_up.lambda33();
      case 52:
        return Sign_up.lambda34();
      case 53:
        return Sign_up.lambda35();
      case 54:
        return Sign_up.lambda36();
      case 55:
        return Sign_up.lambda37();
      case 56:
        return Sign_up.lambda38();
      case 57:
        return Sign_up.lambda39();
      case 58:
        return Sign_up.lambda40();
      case 59:
        return Sign_up.lambda41();
      case 60:
        return Sign_up.lambda42();
      case 61:
        return Sign_up.lambda43();
      case 62:
        return Sign_up.lambda44();
      case 63:
        return Sign_up.lambda45();
      case 64:
        return Sign_up.lambda46();
      case 65:
        return Sign_up.lambda47();
      case 66:
        return Sign_up.lambda48();
      case 67:
        return Sign_up.lambda49();
      case 68:
        return Sign_up.lambda50();
      case 69:
        return Sign_up.lambda51();
      case 70:
        break;
    } 
    return this.$main.Button1$Click();
  }
  
  public Object apply1(ModuleMethod paramModuleMethod, Object paramObject) {
    Sign_up sign_up;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply1(paramModuleMethod, paramObject);
      case 1:
        return this.$main.getSimpleName(paramObject);
      case 2:
        sign_up = this.$main;
        try {
          Bundle bundle = (Bundle)paramObject;
          sign_up.onCreate(bundle);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "onCreate", 1, paramObject);
        } 
      case 3:
        this.$main.androidLogForm(paramObject);
        return Values.empty;
      case 5:
        sign_up = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return sign_up.lookupInFormEnvironment(symbol);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject);
        } 
      case 7:
        sign_up = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject;
          return sign_up.isBoundInFormEnvironment(symbol) ? Boolean.TRUE : Boolean.FALSE;
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
    Sign_up sign_up;
    switch (paramModuleMethod.selector) {
      default:
        return super.apply2(paramModuleMethod, paramObject1, paramObject2);
      case 4:
        sign_up = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          sign_up.addToFormEnvironment(symbol, paramObject2);
          return Values.empty;
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "add-to-form-environment", 1, paramObject1);
        } 
      case 5:
        sign_up = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          return sign_up.lookupInFormEnvironment(symbol, paramObject2);
        } catch (ClassCastException classCastException) {
          throw new WrongType(classCastException, "lookup-in-form-environment", 1, paramObject1);
        } 
      case 8:
        sign_up = this.$main;
        try {
          Symbol symbol = (Symbol)paramObject1;
          sign_up.addToGlobalVarEnvironment(symbol, paramObject2);
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
        sign_up = this.$main;
        try {
          Component component = (Component)paramObject1;
          try {
            paramObject1 = paramObject2;
            try {
              paramObject2 = paramObject3;
              try {
                paramObject3 = paramObject4;
                return sign_up.dispatchEvent(component, (String)paramObject1, (String)paramObject2, (Object[])paramObject3) ? Boolean.TRUE : Boolean.FALSE;
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
    Sign_up sign_up = this.$main;
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
            sign_up.dispatchGenericEvent(component, (String)paramObject1, bool, (Object[])paramObject2);
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
        if (paramObject instanceof Sign_up) {
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
        if (paramObject instanceof Sign_up) {
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
        if (paramObject1 instanceof Sign_up) {
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
        if (paramObject1 instanceof Sign_up) {
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


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/appinventor/ai_notifications_eoteam/GROW_game_app/Sign_up$frame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */