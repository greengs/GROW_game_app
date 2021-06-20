package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.SyntaxPattern;
import kawa.lang.SyntaxRules;
import kawa.lang.Translator;

public class syntax_rules extends Syntax {
  public static final syntax_rules syntax_rules = new syntax_rules();
  
  static {
    syntax_rules.setName("syntax-rules");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    paramPair = (Pair)paramPair.getCdr();
    return (Expression)new QuoteExp(new SyntaxRules(SyntaxPattern.getLiteralsList(paramPair.getCar(), null, paramTranslator), paramPair.getCdr(), paramTranslator));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/syntax_rules.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */