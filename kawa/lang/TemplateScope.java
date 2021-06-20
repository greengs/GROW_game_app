package kawa.lang;

import gnu.expr.Compilation;
import gnu.expr.Declaration;
import gnu.expr.LetExp;
import gnu.expr.ScopeExp;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class TemplateScope extends LetExp implements Externalizable {
  Declaration macroContext;
  
  private Syntax syntax;
  
  public TemplateScope() {
    super(null);
  }
  
  public TemplateScope(ScopeExp paramScopeExp) {
    super(null);
    this.outer = paramScopeExp;
  }
  
  public static TemplateScope make() {
    return make((Translator)Compilation.getCurrent());
  }
  
  public static TemplateScope make(Translator paramTranslator) {
    TemplateScope templateScope = new TemplateScope();
    Syntax syntax = paramTranslator.getCurrentSyntax();
    if (syntax instanceof Macro) {
      templateScope.outer = ((Macro)syntax).getCapturedScope();
      templateScope.macroContext = paramTranslator.macroContext;
    } 
    templateScope.syntax = syntax;
    return templateScope;
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    this.outer = (ScopeExp)paramObjectInput.readObject();
  }
  
  public String toString() {
    return super.toString() + "(for " + this.syntax + ")";
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(this.outer);
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/TemplateScope.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */