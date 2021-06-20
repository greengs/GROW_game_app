package gnu.expr;

import gnu.mapping.Symbol;

public abstract class AccessExp extends Expression {
  Declaration binding;
  
  private Declaration context;
  
  Object symbol;
  
  public final Declaration contextDecl() {
    return this.context;
  }
  
  public final Declaration getBinding() {
    return this.binding;
  }
  
  public final String getName() {
    return (this.symbol instanceof Symbol) ? ((Symbol)this.symbol).getName() : this.symbol.toString();
  }
  
  public final String getSimpleName() {
    if (this.symbol instanceof String)
      return (String)this.symbol; 
    if (this.symbol instanceof Symbol) {
      Symbol symbol = (Symbol)this.symbol;
      if (symbol.hasEmptyNamespace())
        return symbol.getLocalName(); 
    } 
    return null;
  }
  
  public final Object getSymbol() {
    return this.symbol;
  }
  
  public final void setBinding(Declaration paramDeclaration) {
    this.binding = paramDeclaration;
  }
  
  public final void setContextDecl(Declaration paramDeclaration) {
    this.context = paramDeclaration;
  }
  
  public String string_name() {
    return this.symbol.toString();
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/expr/AccessExp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */