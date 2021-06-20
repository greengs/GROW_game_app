package kawa.standard;

import gnu.bytecode.ClassType;
import gnu.expr.ScopeExp;
import gnu.lists.LList;
import gnu.lists.Pair;
import kawa.lang.Syntax;
import kawa.lang.Translator;

public class module_implements extends Syntax {
  public static final module_implements module_implements = new module_implements();
  
  static {
    module_implements.setName("module-implements");
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    Object object = paramPair.getCdr();
    int j = LList.listLength(object, false);
    if (j < 0) {
      paramTranslator.syntaxError("improper argument list for " + getName());
      return;
    } 
    ClassType[] arrayOfClassType = new ClassType[j];
    int i;
    for (i = 0; i < j; i++) {
      object = object;
      arrayOfClassType[i] = (ClassType)paramTranslator.exp2Type((Pair)object);
      object = object.getCdr();
    } 
    object = paramTranslator.getModule();
    object.setInterfaces(arrayOfClassType);
    object.setFlag(131072);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/standard/module_implements.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */