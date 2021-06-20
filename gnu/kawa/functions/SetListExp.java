package gnu.kawa.functions;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;

class SetListExp extends ApplyExp {
  public SetListExp(Expression paramExpression, Expression[] paramArrayOfExpression) {
    super(paramExpression, paramArrayOfExpression);
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    Expression expression;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 2) {
      expression = getArgs()[0];
      QuoteExp quoteExp = QuoteExp.getInstance("set");
      Expression expression2 = Compilation.makeCoercion(arrayOfExpression[0], (Type)Type.intType);
      Expression expression1 = arrayOfExpression[1];
      expression = Compilation.makeCoercion(paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)Invoke.invoke, new Expression[] { expression, (Expression)quoteExp, expression2, expression1 }), paramType), (Type)Type.voidType);
    } 
    return expression;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/SetListExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */