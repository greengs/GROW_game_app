package gnu.kawa.lispexpr;

import gnu.expr.Expression;
import gnu.expr.QuoteExp;
import gnu.lists.Pair;
import gnu.mapping.Namespace;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class ResolveNamespace extends Syntax {
  public static final ResolveNamespace resolveNamespace = new ResolveNamespace("$resolve-namespace$", false);
  
  public static final ResolveNamespace resolveQName = new ResolveNamespace("$resolve-qname", true);
  
  boolean resolvingQName;
  
  static {
    resolveNamespace.setName("$resolve-namespace$");
  }
  
  public ResolveNamespace(String paramString, boolean paramBoolean) {
    super(paramString);
    this.resolvingQName = paramBoolean;
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    Pair pair = (Pair)paramPair.getCdr();
    Namespace namespace2 = paramTranslator.namespaceResolvePrefix(paramTranslator.rewrite_car(pair, false));
    Namespace namespace1 = namespace2;
    if (namespace2 == null) {
      String str = pair.getCar().toString();
      if (str == "[default-element-namespace]") {
        namespace1 = Namespace.EmptyNamespace;
      } else {
        Object object = paramTranslator.pushPositionOf(pair);
        paramTranslator.error('e', "unknown namespace prefix " + namespace1);
        paramTranslator.popPositionOf(object);
        namespace1 = Namespace.valueOf((String)namespace1, (String)namespace1);
      } 
    } 
    return (Expression)(this.resolvingQName ? new QuoteExp(namespace1.getSymbol(((Pair)pair.getCdr()).getCar().toString())) : new QuoteExp(namespace1));
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/lispexpr/ResolveNamespace.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */