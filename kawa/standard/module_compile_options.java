package kawa.standard;

import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_compile_options extends Syntax {
  public static final module_compile_options module_compile_options = new module_compile_options();
  
  static {
    module_compile_options.setName("module-compile-options");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (with_compile_options.getOptions(paramPair.getCdr(), null, this, paramTranslator) != LList.Empty)
      paramTranslator.error('e', getName() + " key must be a keyword"); 
    return true;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/module_compile_options.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */