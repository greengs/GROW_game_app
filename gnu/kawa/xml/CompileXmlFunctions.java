package gnu.kawa.xml;

import gnu.bytecode.Type;
import gnu.expr.ApplyExp;
import gnu.expr.Expression;
import gnu.expr.InlineCalls;
import gnu.expr.QuoteExp;
import gnu.lists.NodePredicate;
import gnu.mapping.Procedure;

public class CompileXmlFunctions {
  public static Expression validateApplyMakeUnescapedData(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    QuoteExp quoteExp;
    paramApplyExp.visitArgs(paramInlineCalls);
    Expression[] arrayOfExpression = paramApplyExp.getArgs();
    ApplyExp applyExp = paramApplyExp;
    if (arrayOfExpression.length == 1) {
      applyExp = paramApplyExp;
      if (arrayOfExpression[0] instanceof QuoteExp)
        quoteExp = new QuoteExp(((MakeUnescapedData)paramProcedure).apply1(((QuoteExp)arrayOfExpression[0]).getValue())); 
    } 
    return (Expression)quoteExp;
  }
  
  public static Expression validateApplyTreeScanner(ApplyExp paramApplyExp, InlineCalls paramInlineCalls, Type paramType, Procedure paramProcedure) {
    paramApplyExp.visitArgs(paramInlineCalls);
    NodePredicate nodePredicate = ((TreeScanner)paramProcedure).type;
    if (paramApplyExp.getTypeRaw() == null && nodePredicate instanceof Type)
      paramApplyExp.setType(NodeSetType.getInstance((Type)nodePredicate)); 
    return (Expression)paramApplyExp;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/xml/CompileXmlFunctions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */