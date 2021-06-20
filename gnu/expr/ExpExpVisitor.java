package gnu.expr;

public abstract class ExpExpVisitor<D> extends ExpVisitor<Expression, D> {
  protected Expression defaultValue(Expression paramExpression, D paramD) {
    return paramExpression;
  }
  
  protected Expression update(Expression paramExpression1, Expression paramExpression2) {
    return paramExpression2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ExpExpVisitor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */