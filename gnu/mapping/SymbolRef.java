package gnu.mapping;

import java.lang.ref.WeakReference;

class SymbolRef extends WeakReference {
  SymbolRef next;
  
  SymbolRef(Symbol paramSymbol, Namespace paramNamespace) {
    super((T)paramSymbol);
  }
  
  Symbol getSymbol() {
    return (Symbol)get();
  }
  
  public String toString() {
    return "SymbolRef[" + getSymbol() + "]";
  }
}


/* Location:              /home/geo/hackathon/GROW/apk/dex2jar-2.0/classes-dex2jar.jar!/gnu/mapping/SymbolRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */