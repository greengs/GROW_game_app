package gnu.kawa.functions;

import gnu.bytecode.ExitableBlock;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Target;
import gnu.mapping.Procedure;
import gnu.mapping.ProcedureN;

class CompileTimeContinuation extends ProcedureN implements Inlineable {
  Target blockTarget;
  
  ExitableBlock exitableBlock;
  
  public Object applyN(Object[] paramArrayOfObject) throws Throwable {
    throw new Error("internal error");
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    int i;
    paramCompilation.getCode();
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int j = arrayOfExpression.length;
    if (this.blockTarget instanceof gnu.expr.IgnoreTarget || this.blockTarget instanceof gnu.expr.ConsumerTarget) {
      i = 1;
    } else {
      i = 0;
    } 
    if (!i)
      paramTarget.getType(); 
    if (i || j == 1) {
      for (i = 0; i < j; i++)
        arrayOfExpression[i].compileWithPosition(paramCompilation, this.blockTarget); 
    } else {
      AppendValues appendValues = AppendValues.appendValues;
      appendValues.compile(new ApplyExp((Procedure)appendValues, arrayOfExpression), paramCompilation, this.blockTarget);
    } 
    this.exitableBlock.exit();
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Type.neverReturnsType;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/CompileTimeContinuation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */