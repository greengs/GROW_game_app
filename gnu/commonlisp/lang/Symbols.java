package gnu.commonlisp.lang;

import gnu.mapping.Environment;
import gnu.mapping.EnvironmentKey;
import gnu.mapping.Namespace;
import gnu.mapping.Symbol;

public class Symbols {
  public static Object getFunctionBinding(Environment paramEnvironment, Object paramObject) {
    return paramEnvironment.getFunction(getSymbol(paramObject));
  }
  
  public static Object getFunctionBinding(Object paramObject) {
    return Environment.getCurrent().getFunction(getSymbol(paramObject));
  }
  
  public static Object getPrintName(Object paramObject) {
    return (paramObject == Lisp2.FALSE) ? "nil" : Lisp2.getString(((Symbol)paramObject).getName());
  }
  
  public static Symbol getSymbol(Environment paramEnvironment, Object paramObject) {
    Object object = paramObject;
    if (paramObject == Lisp2.FALSE)
      object = "nil"; 
    return (object instanceof Symbol) ? (Symbol)object : paramEnvironment.defaultNamespace().getSymbol((String)object);
  }
  
  public static Symbol getSymbol(Object paramObject) {
    Object object = paramObject;
    if (paramObject == Lisp2.FALSE)
      object = "nil"; 
    return (object instanceof Symbol) ? (Symbol)object : Namespace.getDefaultSymbol((String)object);
  }
  
  public static boolean isBound(Object paramObject) {
    if (paramObject != Lisp2.FALSE) {
      Environment environment = Environment.getCurrent();
      if (paramObject instanceof Symbol) {
        paramObject = paramObject;
      } else {
        paramObject = environment.defaultNamespace().lookup((String)paramObject);
      } 
      if (paramObject == null || !environment.isBound((Symbol)paramObject))
        return false; 
    } 
    return true;
  }
  
  public static boolean isSymbol(Object paramObject) {
    return (paramObject instanceof String || paramObject == Lisp2.FALSE || paramObject instanceof Symbol);
  }
  
  public static void setFunctionBinding(Environment paramEnvironment, Object paramObject1, Object paramObject2) {
    paramEnvironment.put(getSymbol(paramObject1), EnvironmentKey.FUNCTION, paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/Symbols.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */