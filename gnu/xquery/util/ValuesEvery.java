package gnu.xquery.util;

import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class ValuesEvery extends MethodProc {
  public static final ValuesEvery every = new ValuesEvery(true);
  
  public static final ValuesEvery some = new ValuesEvery(false);
  
  boolean matchAll;
  
  public ValuesEvery(boolean paramBoolean) {
    this.matchAll = paramBoolean;
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Procedure procedure = (Procedure)paramCallContext.getNextArg();
    Object object = paramCallContext.getNextArg();
    boolean bool = this.matchAll;
    Procedure.checkArgCount(procedure, 1);
    if (object instanceof gnu.mapping.Values) {
      int i = 0;
      object = object;
      while (true) {
        i = object.nextPos(i);
        if (i != 0) {
          procedure.check1(object.getPosPrevious(i), paramCallContext);
          boolean bool1 = BooleanValue.booleanValue(paramCallContext.runUntilValue());
          bool = bool1;
          if (bool1 != this.matchAll) {
            bool = bool1;
            break;
          } 
          continue;
        } 
        break;
      } 
    } else {
      procedure.check1(object, paramCallContext);
      bool = BooleanValue.booleanValue(paramCallContext.runUntilValue());
    } 
    paramCallContext.consumer.writeBoolean(bool);
  }
  
  public int numArgs() {
    return 8194;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/xquery/util/ValuesEvery.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */