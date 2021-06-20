package kawa.standard;

import gnu.expr.CatchClause;
import gnu.expr.Compilation;
import gnu.expr.Expression;
import gnu.expr.LambdaExp;
import gnu.expr.TryExp;
import gnu.lists.FVector;
import kawa.lang.Translator;

public class try_catch {
  public static Expression rewrite(Object paramObject1, Object paramObject2) {
    Translator translator = (Translator)Compilation.getCurrent();
    Expression expression2 = translator.rewrite(paramObject1);
    Expression expression1 = null;
    paramObject1 = null;
    FVector fVector = (FVector)paramObject2;
    int j = fVector.size();
    int i = 0;
    paramObject2 = expression1;
    while (i < j) {
      expression1 = SchemeCompilation.lambda.rewrite(fVector.get(i), translator);
      if (expression1 instanceof gnu.expr.ErrorExp)
        return expression1; 
      if (!(expression1 instanceof LambdaExp))
        return translator.syntaxError("internal error with try-catch"); 
      CatchClause catchClause = new CatchClause((LambdaExp)expression1);
      if (paramObject2 == null) {
        paramObject1 = catchClause;
      } else {
        paramObject2.setNext(catchClause);
      } 
      paramObject2 = catchClause;
      i++;
    } 
    if (expression2 instanceof gnu.expr.ErrorExp)
      return expression2; 
    paramObject2 = new TryExp(expression2, null);
    paramObject2.setCatchClauses((CatchClause)paramObject1);
    return (Expression)paramObject2;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/try_catch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */