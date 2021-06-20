package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class begin extends Syntax {
  public static final begin begin = new begin();
  
  static {
    begin.setName("begin");
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    return paramTranslator.rewrite_body(paramObject);
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    LList lList = paramTranslator.scanBody(paramPair.getCdr(), paramScopeExp, true);
    if (lList != LList.Empty)
      paramTranslator.formStack.add(Translator.makePair(paramPair, paramPair.getCar(), lList)); 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/begin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */