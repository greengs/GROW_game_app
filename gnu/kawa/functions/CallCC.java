package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;
import kawa.lang.Continuation;

public class CallCC extends MethodProc implements Inlineable {
  public static final CallCC callcc = new CallCC();
  
  CallCC() {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyCallCC");
  }
  
  public void apply(CallContext paramCallContext) throws Throwable {
    Procedure procedure = (Procedure)paramCallContext.value1;
    Continuation continuation = new Continuation(paramCallContext);
    procedure.check1(continuation, paramCallContext);
    procedure = paramCallContext.proc;
    paramCallContext.proc = null;
    try {
      procedure.apply(paramCallContext);
      paramCallContext.runUntilDone();
      continuation.invoked = true;
      return;
    } catch (Throwable throwable) {
      Continuation.handleException$X(throwable, continuation, paramCallContext);
      return;
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    CompileMisc.compileCallCC(paramApplyExp, paramCompilation, paramTarget, (Procedure)this);
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.pointer_type;
  }
  
  public int match1(Object paramObject, CallContext paramCallContext) {
    return !(paramObject instanceof Procedure) ? -786432 : super.match1(paramObject, paramCallContext);
  }
  
  public int numArgs() {
    return 4097;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CallCC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */