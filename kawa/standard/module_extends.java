package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.bytecode.Type;
import gnu.expr.ModuleExp;
import gnu.expr.ScopeExp;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_extends extends Syntax {
  public static final module_extends module_extends = new module_extends();
  
  static {
    module_extends.setName("module-extends");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    Type type = paramTranslator.exp2Type((Pair)paramPair.getCdr());
    ModuleExp moduleExp = paramTranslator.getModule();
    moduleExp.setSuperType((ClassType)type);
    moduleExp.setFlag(131072);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/module_extends.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */