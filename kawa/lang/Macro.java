package kawa.lang;

import gnu.expr.Declaration;
import gnu.expr.Expression;
import gnu.expr.ModuleInfo;
import gnu.expr.QuoteExp;
import gnu.expr.ScopeExp;
import gnu.lists.Consumer;
import gnu.lists.Pair;
import gnu.lists.PairWithPosition;
import gnu.mapping.Procedure;
import gnu.text.Printable;
import gnu.text.SourceLocator;
import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Macro extends Syntax implements Printable, Externalizable {
  private ScopeExp capturedScope;
  
  public Object expander;
  
  private boolean hygienic = true;
  
  Object instance;
  
  public Macro() {}
  
  public Macro(Object paramObject) {
    super(paramObject);
  }
  
  public Macro(Object paramObject, Procedure paramProcedure) {
    super(paramObject);
    this.expander = new QuoteExp(paramProcedure);
  }
  
  public Macro(Macro paramMacro) {
    this.name = paramMacro.name;
    this.expander = paramMacro.expander;
    this.hygienic = paramMacro.hygienic;
  }
  
  public static Macro make(Declaration paramDeclaration) {
    Macro macro = new Macro(paramDeclaration.getSymbol());
    paramDeclaration.setSyntax();
    macro.capturedScope = paramDeclaration.context;
    return macro;
  }
  
  public static Macro make(Object paramObject, Procedure paramProcedure) {
    return new Macro(paramObject, paramProcedure);
  }
  
  public static Macro make(Object paramObject1, Procedure paramProcedure, Object paramObject2) {
    paramObject1 = new Macro(paramObject1, paramProcedure);
    ((Macro)paramObject1).instance = paramObject2;
    return (Macro)paramObject1;
  }
  
  public static Macro makeNonHygienic(Object paramObject, Procedure paramProcedure) {
    paramObject = new Macro(paramObject, paramProcedure);
    ((Macro)paramObject).hygienic = false;
    return (Macro)paramObject;
  }
  
  public static Macro makeNonHygienic(Object paramObject1, Procedure paramProcedure, Object paramObject2) {
    paramObject1 = new Macro(paramObject1, paramProcedure);
    ((Macro)paramObject1).hygienic = false;
    ((Macro)paramObject1).instance = paramObject2;
    return (Macro)paramObject1;
  }
  
  public Object expand(Object paramObject, Translator paramTranslator) {
    try {
      Object object1;
      Object object2 = this.expander;
      if (object2 instanceof Procedure && !(object2 instanceof Expression)) {
        object1 = object2;
      } else {
        object1 = object2;
        if (!(object2 instanceof Expression)) {
          Macro macro = paramTranslator.currentMacroDefinition;
          paramTranslator.currentMacroDefinition = this;
          try {
            object1 = paramTranslator.rewrite(object2);
            this.expander = object1;
            paramTranslator.currentMacroDefinition = macro;
          } finally {
            paramTranslator.currentMacroDefinition = macro;
          } 
          if (paramObject instanceof PairWithPosition && object1 instanceof Pair && !(object1 instanceof PairWithPosition)) {
            object1 = object1;
            return new PairWithPosition((SourceLocator)paramObject, object1.getCar(), object1.getCdr());
          } 
          return object1;
        } 
        object1 = ((Expression)object1).eval(paramTranslator.getGlobalEnvironment());
      } 
      if (!this.hygienic) {
        paramObject = Quote.quote(paramObject, paramTranslator);
        int j = Translator.listLength(paramObject);
        if (j <= 0)
          return paramTranslator.syntaxError("invalid macro argument list to " + this); 
        object2 = new Object[j - 1];
        for (int i = 0; i < j; i++) {
          paramObject = paramObject;
          if (i > 0)
            object2[i - 1] = paramObject.getCar(); 
          paramObject = paramObject.getCdr();
        } 
        object1 = object1.applyN((Object[])object2);
      } else {
        object1 = object1.apply1(paramObject);
      } 
    } catch (Throwable throwable) {
      return paramTranslator.syntaxError("evaluating syntax transformer '" + getName() + "' threw " + throwable);
    } 
  }
  
  public ScopeExp getCapturedScope() {
    if (this.capturedScope == null) {
      if (this.instance instanceof gnu.expr.ModuleExp) {
        this.capturedScope = (ScopeExp)this.instance;
        return this.capturedScope;
      } 
    } else {
      return this.capturedScope;
    } 
    if (this.instance != null)
      this.capturedScope = (ScopeExp)ModuleInfo.findFromInstance(this.instance).getModuleExp(); 
    return this.capturedScope;
  }
  
  public final boolean isHygienic() {
    return this.hygienic;
  }
  
  public void print(Consumer paramConsumer) {
    paramConsumer.write("#<macro ");
    paramConsumer.write(getName());
    paramConsumer.write(62);
  }
  
  public void readExternal(ObjectInput paramObjectInput) throws IOException, ClassNotFoundException {
    setName((String)paramObjectInput.readObject());
    this.expander = new QuoteExp(paramObjectInput.readObject());
  }
  
  public Expression rewriteForm(Pair paramPair, Translator paramTranslator) {
    return paramTranslator.rewrite(expand(paramPair, paramTranslator));
  }
  
  public Expression rewriteForm(Object paramObject, Translator paramTranslator) {
    return paramTranslator.rewrite(expand(paramObject, paramTranslator));
  }
  
  public void scanForm(Pair paramPair, ScopeExp paramScopeExp, Translator paramTranslator) {
    String str = paramTranslator.getFileName();
    int i = paramTranslator.getLineNumber();
    int j = paramTranslator.getColumnNumber();
    Syntax syntax = paramTranslator.currentSyntax;
    try {
      paramTranslator.setLine(paramPair);
      paramTranslator.currentSyntax = this;
      paramTranslator.scanForm(expand(paramPair, paramTranslator), paramScopeExp);
      return;
    } finally {
      paramTranslator.setLine(str, i, j);
      paramTranslator.currentSyntax = syntax;
    } 
  }
  
  public void setCapturedScope(ScopeExp paramScopeExp) {
    this.capturedScope = paramScopeExp;
  }
  
  public final void setHygienic(boolean paramBoolean) {
    this.hygienic = paramBoolean;
  }
  
  public String toString() {
    return "#<macro " + getName() + '>';
  }
  
  public void writeExternal(ObjectOutput paramObjectOutput) throws IOException {
    paramObjectOutput.writeObject(getName());
    paramObjectOutput.writeObject(((QuoteExp)this.expander).getValue());
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/kawa/lang/Macro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */