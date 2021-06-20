package gnu.kawa.functions;

import gnu.bytecode.ArrayType;
import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.kawa.reflect.ArraySet;
import gnu.kawa.reflect.Invoke;
import gnu.mapping.Procedure;

class SetArrayExp extends ApplyExp {
  public static final ClassType typeSetArray = ClassType.make("gnu.kawa.functions.SetArray");
  
  Type elementType;
  
  public SetArrayExp(Expression paramExpression, ArrayType paramArrayType) {
    super((Procedure)Invoke.make, new Expression[] { (Expression)new QuoteExp(typeSetArray), paramExpression });
    this.elementType = paramArrayType.getComponentType();
  }
  
  public Expression validateApply(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Declaration paramDeclaration) {
    Expression expression;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    if (arrayOfExpression.length == 2) {
      expression = getArgs()[1];
      Expression expression2 = arrayOfExpression[0];
      Expression expression1 = arrayOfExpression[1];
      expression = paramInlineCalls.visitApplyOnly(new ApplyExp((Procedure)new ArraySet(this.elementType), new Expression[] { expression, expression2, expression1 }), paramType);
    } 
    return expression;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/SetArrayExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */