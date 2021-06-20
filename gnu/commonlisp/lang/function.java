package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.ReferenceExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import gnu.mapping.Symbol;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class function extends Syntax {
  Syntax lambda;
  
  public function(Syntax paramSyntax) {
    this.lambda = paramSyntax;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Object object = paramPair.getCdr();
    if (object instanceof Pair) {
      object = object;
      if (object.getCdr() != LList.Empty)
        return paramTranslator.syntaxError("too many forms after 'function'"); 
      object = object.getCar();
      if (object instanceof String || object instanceof Symbol) {
        object = new ReferenceExp(object);
        object.setProcedureName(true);
        object.setFlag(8);
        return (Expression)object;
      } 
      if (object instanceof Pair) {
        object = object;
        Object object1 = object.getCar();
        if ((object1 instanceof String) ? "lambda".equals(object1) : (object1 instanceof Symbol && "lambda".equals(((Symbol)object1).getName())))
          return this.lambda.rewriteForm((Pair)object, paramTranslator); 
      } 
    } 
    return paramTranslator.syntaxError("function must be followed by name or lambda expression");
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */