package gnu.kawa.functions;

import gnu.expr.Language;
import gnu.mapping.Procedure;
import gnu.mapping.Procedure1;
import gnu.mapping.PropertySet;

public class Not extends Procedure1 {
  Language language;
  
  public Not(Language paramLanguage) {
    this.language = paramLanguage;
    setProperty(Procedure.validateApplyKey, "gnu.kawa.functions.CompileMisc:validateApplyNot");
    Procedure.compilerKey.set((PropertySet)this, "*gnu.kawa.functions.CompileMisc:forNot");
  }
  
  public Not(Language paramLanguage, String paramString) {
    this(paramLanguage);
    setName(paramString);
  }
  
  public Object apply1(Object paramObject) {
    Language language = this.language;
    if (!this.language.isTrue(paramObject)) {
      boolean bool1 = true;
      return language.booleanObject(bool1);
    } 
    boolean bool = false;
    return language.booleanObject(bool);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/kawa/functions/Not.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */