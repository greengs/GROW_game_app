package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.PropertySet;

public class Convert extends Procedure2 {
  public static final Convert as = new Convert();
  
  static {
    as.setName("as");
    as.setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyConvert");
    Procedure.compilerKey.set((PropertySet)as, "*gnu.kawa.functions.CompileMisc:forConvert");
  }
  
  public static Convert getInstance() {
    return as;
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) {
    if (paramObject1 instanceof Class) {
      paramObject1 = Type.make((Class)paramObject1);
      return paramObject1.coerceFromObject(paramObject2);
    } 
    paramObject1 = paramObject1;
    return paramObject1.coerceFromObject(paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */