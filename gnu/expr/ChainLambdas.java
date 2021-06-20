package gnu.expr;

public class ChainLambdas extends ExpExpVisitor<ScopeExp> {
  public static void chainLambdas(Expression paramExpression, Compilation paramCompilation) {
    ChainLambdas chainLambdas = new ChainLambdas();
    chainLambdas.setContext(paramCompilation);
    chainLambdas.visit(paramExpression, null);
  }
  
  protected Expression visitClassExp(ClassExp paramClassExp, ScopeExp paramScopeExp) {
    LambdaExp lambdaExp = this.currentLambda;
    if (lambdaExp != null && !(lambdaExp instanceof ClassExp)) {
      paramClassExp.nextSibling = lambdaExp.firstChild;
      lambdaExp.firstChild = paramClassExp;
    } 
    visitScopeExp(paramClassExp, paramScopeExp);
    return paramClassExp;
  }
  
  protected Expression visitLambdaExp(LambdaExp paramLambdaExp, ScopeExp paramScopeExp) {
    ScopeExp scopeExp = this.currentLambda;
    if (scopeExp != null && !(scopeExp instanceof ClassExp)) {
      paramLambdaExp.nextSibling = ((LambdaExp)scopeExp).firstChild;
      ((LambdaExp)scopeExp).firstChild = paramLambdaExp;
    } 
    paramLambdaExp.outer = paramScopeExp;
    paramLambdaExp.firstChild = null;
    paramLambdaExp.visitChildrenOnly(this, paramLambdaExp);
    paramLambdaExp.visitProperties(this, paramLambdaExp);
    scopeExp = null;
    for (paramScopeExp = paramLambdaExp.firstChild; paramScopeExp != null; paramScopeExp = lambdaExp) {
      LambdaExp lambdaExp = ((LambdaExp)paramScopeExp).nextSibling;
      ((LambdaExp)paramScopeExp).nextSibling = (LambdaExp)scopeExp;
      scopeExp = paramScopeExp;
    } 
    paramLambdaExp.firstChild = (LambdaExp)scopeExp;
    if (paramLambdaExp.getName() == null && paramLambdaExp.nameDecl != null)
      paramLambdaExp.setName(paramLambdaExp.nameDecl.getName()); 
    paramLambdaExp.setIndexes();
    if (paramLambdaExp.mustCompile())
      this.comp.mustCompileHere(); 
    return paramLambdaExp;
  }
  
  protected Expression visitScopeExp(ScopeExp paramScopeExp1, ScopeExp paramScopeExp2) {
    paramScopeExp1.outer = paramScopeExp2;
    paramScopeExp1.visitChildren(this, paramScopeExp1);
    paramScopeExp1.setIndexes();
    if (paramScopeExp1.mustCompile())
      this.comp.mustCompileHere(); 
    return paramScopeExp1;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/ChainLambdas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */