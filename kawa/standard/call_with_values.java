package kawa.standard;

import gnu.mapping.CallContext;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure2;
import gnu.mapping.Values;

public class call_with_values extends Procedure2 {
  public static final call_with_values callWithValues = new call_with_values();
  
  static {
    callWithValues.setName("call-with-values");
  }
  
  public static Object callWithValues(Procedure paramProcedure1, Procedure paramProcedure2) throws Throwable {
    Object object = paramProcedure1.apply0();
    return (object instanceof Values) ? ((Values)object).call_with(paramProcedure2) : paramProcedure2.apply1(object);
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Procedure.checkArgCount((Procedure)this, 2);
    Object[] arrayOfObject = paramCallContext.getArgs();
    Object object = ((Procedure)arrayOfObject[0]).apply0();
    Procedure procedure = (Procedure)arrayOfObject[1];
    if (object instanceof Values) {
      procedure.checkN(((Values)object).getValues(), paramCallContext);
      return;
    } 
    procedure.check1(object, paramCallContext);
  }
  
  public Object apply2(Object paramObject1, Object paramObject2) throws Throwable {
    return callWithValues((Procedure)paramObject1, (Procedure)paramObject2);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/call_with_values.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */