package gnu.commonlisp.lang;

import gnu.expr.Expression;
import gnu.expr.TryExp;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class UnwindProtect extends Syntax {
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    if (!(paramObject instanceof gnu.lists.Pair))
      return paramTranslator.syntaxError("invalid syntax for unwind-protect"); 
    paramObject = paramObject;
    return (Expression)new TryExp(paramTranslator.rewrite(paramObject.getCar()), paramTranslator.rewrite_body(paramObject.getCdr()));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/commonlisp/lang/UnwindProtect.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */