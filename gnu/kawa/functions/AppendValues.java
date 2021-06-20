package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.ConsumerTarget;
import gnu.expr.Expression;
import gnu.expr.Inlineable;
import gnu.expr.Special;
import gnu.expr.Target;
import gnu.lists.Consumable;
import gnu.mapping.CallContext;
import gnu.mapping.MethodProc;
import gnu.mapping.Procedure;

public class AppendValues extends MethodProc implements Inlineable {
  public static final AppendValues appendValues = new AppendValues();
  
  public AppendValues() {
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyAppendValues");
  }
  
  public void apply(CallContext paramCallContext) {
    Special special = Special.dfault;
    while (true) {
      Object object = paramCallContext.getNextArg(special);
      if (object == special)
        return; 
      if (object instanceof Consumable) {
        ((Consumable)object).consume(paramCallContext.consumer);
        continue;
      } 
      paramCallContext.writeValue(object);
    } 
  }
  
  public void compile(ApplyExp paramApplyExp, Compilation paramCompilation, Target paramTarget) {
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    int i = arrayOfExpression.length;
    if (paramTarget instanceof ConsumerTarget || paramTarget instanceof gnu.expr.IgnoreTarget) {
      int j;
      for (j = 0; j < i; j++)
        arrayOfExpression[j].compileWithPosition(paramCompilation, paramTarget); 
    } else {
      ConsumerTarget.compileUsingConsumer((Expression)paramApplyExp, paramCompilation, paramTarget);
    } 
  }
  
  public Type getReturnType(Expression[] paramArrayOfExpression) {
    return (Type)Compilation.typeObject;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/AppendValues.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */