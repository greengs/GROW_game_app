package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.HasSetter;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import java.util.List;

public class Setter extends Procedure1 implements HasSetter {
  public static final Setter setter = new Setter();
  
  static {
    setter.setName("setter");
    setter.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompilationHelpers:validateSetter");
  }
  
  public static Object setter(Procedure paramProcedure) {
    return paramProcedure.getSetter();
  }
  
  public Object apply1(Object paramObject) {
    if (!(paramObject instanceof Procedure)) {
      if (paramObject instanceof List)
        return new SetList((List)paramObject); 
      if (paramObject.getClass().isArray())
        return new SetArray(paramObject, Language.getDefaultLanguage()); 
    } 
    return ((Procedure)paramObject).getSetter();
  }
  
  public void set1(Object paramObject1, Object paramObject2) throws Throwable {
    ((Procedure)paramObject1).setSetter((Procedure)paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Setter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */