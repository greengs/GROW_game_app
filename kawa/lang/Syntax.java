package kawa.lang;

import gnu.expr.ErrorExp;
import gnu.expr.Expression;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.mapping.Named;
import gnu.mapping.Symbol;
import gnu.text.Printable;
import java.util.Vector;

public abstract class Syntax implements Printable, Named {
  Object name;
  
  public Syntax() {}
  
  public Syntax(Object paramObject) {
    setName(paramObject);
  }
  
  public final String getName() {
    return (this.name == null) ? null : ((this.name instanceof Symbol) ? ((Symbol)this.name).getName() : this.name.toString());
  }
  
  public Object getSymbol() {
    return this.name;
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<syntax ");
    String str2 = getName();
    String str1 = str2;
    if (str2 == null)
      str1 = "<unnamed>"; 
    paramConsumer.write(str1);
    paramConsumer.write(62);
  }
  
  public Expression rewrite(Object paramObject, Translator paramTranslator) {
    throw new InternalError("rewrite method not defined");
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return rewrite(paramPair.getCdr(), paramTranslator);
  }
  
  public Expression rewriteForm(Object paramObject, Translator paramTranslator) {
    return (paramObject instanceof Pair) ? rewriteForm((Pair)paramObject, paramTranslator) : paramTranslator.syntaxError("non-list form for " + this);
  }
  
  public boolean scanForDefinitions(Pair paramPair, Vector<Pair> paramVector, ScopeExp paramScopeExp, Translator paramTranslator) {
    paramVector.addElement(paramPair);
    return true;
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    if (!scanForDefinitions(paramPair, paramTranslator.formStack, paramScopeExp, paramTranslator))
      paramTranslator.formStack.add(new ErrorExp("syntax error expanding " + this)); 
  }
  
  public void setName(Object paramObject) {
    this.name = paramObject;
  }
  
  public void setName(String paramString) {
    this.name = paramString;
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Syntax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */