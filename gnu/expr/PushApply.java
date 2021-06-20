package gnu.expr;

public class PushApply extends ExpVisitor<Expression, Void> {
  public static void pushApply(Expression paramExpression) {
    (new PushApply()).visit(paramExpression, null);
  }
  
  protected Expression defaultValue(Expression paramExpression, Void paramVoid) {
    return paramExpression;
  }
  
  protected Expression update(Expression paramExpression1, Expression paramExpression2) {
    return paramExpression2;
  }
  
  protected Expression visitApplyExp(ApplyExp paramApplyExp, Void paramVoid) {
    Expression expression = paramApplyExp.func;
    if (expression instanceof LetExp && !(expression instanceof FluidLetExp)) {
      expression = expression;
      Expression expression1 = ((LetExp)expression).body;
      ((LetExp)expression).body = paramApplyExp;
      paramApplyExp.func = expression1;
      return visit(expression, paramVoid);
    } 
    if (expression instanceof BeginExp) {
      expression = expression;
      Expression[] arrayOfExpression = ((BeginExp)expression).exps;
      int i = ((BeginExp)expression).exps.length - 1;
      paramApplyExp.func = arrayOfExpression[i];
      arrayOfExpression[i] = paramApplyExp;
      return visit(expression, paramVoid);
    } 
    paramApplyExp.visitChildren(this, paramVoid);
    return paramApplyExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/PushApply.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */