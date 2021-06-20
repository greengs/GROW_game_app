package gnu.bytecode;

public class TryState {
  Label end_label;
  
  Label end_try;
  
  Variable exception;
  
  ExitableBlock exitCases;
  
  Variable finally_ret_addr;
  
  Label finally_subr;
  
  TryState previous;
  
  Variable[] savedStack;
  
  Type[] savedTypes;
  
  Variable saved_result;
  
  Label start_try;
  
  boolean tryClauseDone;
  
  ClassType try_type;
  
  public TryState(CodeAttr paramCodeAttr) {
    this.previous = paramCodeAttr.try_stack;
    paramCodeAttr.try_stack = this;
    this.start_try = paramCodeAttr.getLabel();
  }
  
  static TryState outerHandler(TryState paramTryState1, TryState paramTryState2) {
    while (paramTryState1 != paramTryState2 && (paramTryState1.finally_subr == null || paramTryState1.tryClauseDone))
      paramTryState1 = paramTryState1.previous; 
    return paramTryState1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/bytecode/TryState.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */