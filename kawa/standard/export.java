package kawa.standard;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import java.util.Vector;
import kawa.lang.Syntax;
import kawa.lang.SyntaxForm;
import kawa.lang.Translator;

public class export extends Syntax {
  public static final export export;
  
  public static final export module_export = new export();
  
  static {
    module_export.setName("module-export");
    export = new export();
    module_export.setName("export");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return null;
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    Object object1 = paramPair.getCdr();
    Object object2 = paramTranslator.pushPositionOf(paramPair);
    try {
      if (paramScopeExp instanceof ModuleExp) {
        ((ModuleExp)paramScopeExp).setFlag(16384);
        paramPair = null;
        while (true) {
          if (object1 != LList.Empty) {
            SyntaxForm syntaxForm1;
            paramTranslator.pushPositionOf(object1);
            while (object1 instanceof SyntaxForm) {
              syntaxForm1 = (SyntaxForm)object1;
              object1 = syntaxForm1.getDatum();
            } 
            SyntaxForm syntaxForm2 = syntaxForm1;
            if (object1 instanceof Pair) {
              Pair pair = (Pair)object1;
              for (object1 = pair.getCar(); object1 instanceof SyntaxForm; object1 = syntaxForm2.getDatum())
                syntaxForm2 = (SyntaxForm)object1; 
              Object object = object1;
              if (object1 instanceof String) {
                String str = (String)object1;
                object = object1;
                if (str.startsWith("namespace:")) {
                  paramTranslator.error('w', "'namespace:' prefix ignored");
                  object = str.substring(10).intern();
                } 
              } 
              if (object instanceof String || object instanceof gnu.mapping.Symbol) {
                if (syntaxForm2 != null);
                object1 = paramScopeExp.getNoDefine(object);
                if (object1.getFlag(512L))
                  Translator.setLine((Declaration)object1, pair); 
                object1.setFlag(1024L);
                object1 = pair.getCdr();
                continue;
              } 
            } 
            paramTranslator.error('e', "invalid syntax in '" + getName() + '\'');
            return false;
          } 
          return true;
        } 
      } 
      paramTranslator.error('e', "'" + getName() + "' not at module level");
      return true;
    } finally {
      paramTranslator.popPositionOf(object2);
    } 
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/export.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */