package gnu.kawa.functions;

import gnu.expr.GenericProc;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

public class MakeProcedure extends ProcedureN {
  public static final MakeProcedure makeProcedure = new MakeProcedure("make-procedure");
  
  public MakeProcedure(String paramString) {
    super(paramString);
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyMakeProcedure");
  }
  
  public static GenericProc makeProcedure$V(Object[] paramArrayOfObject) {
    return GenericProc.make(paramArrayOfObject);
  }
  
  public Object applyN(Object[] paramArrayOfObject) {
    return GenericProc.make(paramArrayOfObject);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/MakeProcedure.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */